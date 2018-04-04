package com.welch.factory;

import com.welch.conversion.Converter;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.writer.XmlGenerator;
import com.welch.xml.object.INCOND;
import com.welch.xml.object.JOB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class InConditionFactory {
	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	//private String sql = "SELECT * FROM Ctlm_V_In_Condition order by JOB_NAME";
	private String sql = null;
	private LinkedHashMap<String, LinkedHashMap<String, INCOND>> inconds = null;
	private Converter converter;

	public InConditionFactory(JavaLogger log, SQLHelper sqlHelper, Converter converter) {
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.converter = converter;
		this.sql = this.buildSql();
		this.inconds = new LinkedHashMap<String, LinkedHashMap<String, INCOND>>();
		this.getInconds();
	}
	
	private String buildSql(){
		String sql = "SELECT * FROM Ctlm_V_In_Condition";
		if(XmlGenerator.testModel && XmlGenerator.groups != null && (!XmlGenerator.groups.equals(""))){
			sql+= " where job_name in";
			sql+= " (";
			sql+= " select jobname from CTLM_V_JOB where group_name in ("+ XmlGenerator.groups+")";
			sql+= " )";
			sql+= " and parent_job in";
			sql+= " (";
			sql+= " select jobname from CTLM_V_JOB where group_name in ("+ XmlGenerator.groups+")";
			sql+= " )";
			sql+= " and child_job in";
			sql+= " (";
			sql+= " select jobname from CTLM_V_JOB where group_name in ("+ XmlGenerator.groups+")";
			sql+= " )";
		}
		sql += " order by JOB_NAME,CONDITION_ORDER";
		return sql;
	}

	private void getInconds() {
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(this.sql);
			do {
				INCOND incond = new INCOND();
				if (XmlGenerator.testModel) {
					// incond.setNAME(StringHelper.convertNull(rs.getString("CONDITION_NAME")) + "-" + ControlMGenerator.EXTENSION);
					incond.setNAME(this.converter.convert("INCOND", "NAME", StringHelper.convertNull(rs.getString("CONDITION_NAME"))));
				} else {
					incond.setNAME(StringHelper.convertNull(rs.getString("CONDITION_NAME")));
				}
				incond.setANDOR(StringHelper.convertNull(rs.getString("AND_OR")));
				incond.setODATE(StringHelper.convertNull(rs.getString("ODATE")));
				incond.setOP(StringHelper.convertNull(rs.getString("OP")));
				if (this.inconds.get(rs.getString("JOB_NAME")) == null) {
					this.inconds.put(rs.getString("JOB_NAME"), new LinkedHashMap<String, INCOND>());
				}
				this.inconds.get(rs.getString("JOB_NAME")).put(rs.getString("CONDITION_NAME"), incond);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No in conditions are found, maybe OK");
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

	public void addInconds(JOB job) {
		if (this.inconds.get(job.getJOBNAME()) == null) {
			// log.writeToLog("LM_INFOR", "No in conditions are found for job "+job.getJOBNAME());
		} else {
			for (String key : this.inconds.get(job.getJOBNAME()).keySet()) {
				job.getTAGNAMESOrRULEBASEDCALENDARSOrINCONDOrOUTCONDOrAUTOEDITOrQUANTITATIVEOrCONTROLOrSHOUTOrSTEPRANGEOrONOrAUTOEDIT2().add(this.inconds.get(job.getJOBNAME()).get(key));
			}
		}
	}
}
