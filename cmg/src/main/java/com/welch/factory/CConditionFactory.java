package com.welch.factory;

import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.writer.XmlGenerator;
import com.welch.xml.object.INCOND;
import com.welch.xml.object.JOB;
import com.welch.xml.object.OUTCOND;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class CConditionFactory {
	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	private String sql = null;
	private Hashtable<String, Hashtable<String, INCOND>> inCConds = null;
	private Hashtable<String, Hashtable<String, OUTCOND>> outCConds = null;

	public CConditionFactory(JavaLogger log, SQLHelper sqlHelper) {
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.sql = this.buildSql();
		this.inCConds = new Hashtable<String, Hashtable<String, INCOND>>();
		this.outCConds = new Hashtable<String, Hashtable<String, OUTCOND>>();
		this.getCConds();
	}
	
	private String buildSql(){
		String sql = "SELECT * FROM CTLM_V_C_CONDITION where action_detail_id = 0 ";
		if(XmlGenerator.testModel && XmlGenerator.groups != null && (!XmlGenerator.groups.equals(""))){
			sql+= " and job_name in";
			sql+= " (";
			sql+= " select jobname from CTLM_V_JOB where group_name in ("+ XmlGenerator.groups+")";
			sql+= " )";
		}
		sql += " order by JOB_NAME";
		return sql;
	}

	private void getCConds() {
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(this.sql);
			do {
				
				String sign = rs.getString("SIGN");
				if(sign==null || sign.trim().equals("")){
					//static in condition
					INCOND incond = new INCOND();
					incond.setNAME(StringHelper.convertNull(rs.getString("CONDITION_NAME")));
					incond.setANDOR(StringHelper.convertNull(rs.getString("AND_OR")));
					incond.setODATE(StringHelper.convertNull(rs.getString("ODATE")));
					if (this.inCConds.get(rs.getString("JOB_NAME")) == null) {
						this.inCConds.put(rs.getString("JOB_NAME"), new Hashtable<String, INCOND>());
					}
					this.inCConds.get(rs.getString("JOB_NAME")).put(rs.getString("CONDITION_NAME"), incond);
				}else{
					//static out condition
					OUTCOND outcond = new OUTCOND();
					outcond.setNAME(StringHelper.convertNull(rs.getString("CONDITION_NAME")));
					outcond.setODATE(StringHelper.convertNull(rs.getString("ODATE")));
					outcond.setSIGN(sign);
					if (this.outCConds.get(rs.getString("JOB_NAME")) == null) {
						this.outCConds.put(rs.getString("JOB_NAME"), new Hashtable<String, OUTCOND>());
					}
					this.outCConds.get(rs.getString("JOB_NAME")).put(rs.getString("CONDITION_NAME"), outcond);
				}
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No C conditions are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + this.sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.writeToLog("LM_ERROR", "Failed to close result set");
				log.writeToLog("LM_ERROR", e.getMessage());
			}
		}
	}

	public void addCConds(JOB job) {
		//add static in condition
		if (this.inCConds.get(job.getJOBNAME()) == null) {
			// log.writeToLog("LM_INFOR", "No in conditions are found for job "+job.getJOBNAME());
		} else {
			for (String key : this.inCConds.get(job.getJOBNAME()).keySet()) {
				job.getTAGNAMESOrRULEBASEDCALENDARSOrINCONDOrOUTCONDOrAUTOEDITOrQUANTITATIVEOrCONTROLOrSHOUTOrSTEPRANGEOrONOrAUTOEDIT2().add(this.inCConds.get(job.getJOBNAME()).get(key));
			}
		}
		//add static out condition
		if (this.outCConds.get(job.getJOBNAME()) == null) {
		} else {
			for (String key : this.outCConds.get(job.getJOBNAME()).keySet()) {
				job.getTAGNAMESOrRULEBASEDCALENDARSOrINCONDOrOUTCONDOrAUTOEDITOrQUANTITATIVEOrCONTROLOrSHOUTOrSTEPRANGEOrONOrAUTOEDIT2().add(this.outCConds.get(job.getJOBNAME()).get(key));
			}
		}
	}
}
