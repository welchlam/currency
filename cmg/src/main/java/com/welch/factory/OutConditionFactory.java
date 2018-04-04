package com.welch.factory;

import com.welch.conversion.Converter;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.writer.XmlGenerator;
import com.welch.xml.object.JOB;
import com.welch.xml.object.OUTCOND;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class OutConditionFactory {
	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	//private String sql = "SELECT * FROM CTLM_V_OUT_CONDITION where ACTION_DETAIL_ID is null order by JOB_NAME";
	private String sql = null;
	private LinkedHashMap<String, LinkedHashMap<String, OUTCOND>> outconds = null;
	private Converter converter;

	public OutConditionFactory(JavaLogger log, SQLHelper sqlHelper, Converter converter) {
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.converter = converter;
		this.sql = this.buildSql();
		this.outconds = new LinkedHashMap<String, LinkedHashMap<String, OUTCOND>>();
		this.getOutconds();
	}
	
	private String buildSql(){
		String sql = "SELECT * FROM CTLM_V_OUT_CONDITION where (ACTION_DETAIL_ID is null or ACTION_DETAIL_ID=0) ";
		if(XmlGenerator.testModel && XmlGenerator.groups != null && (!XmlGenerator.groups.equals(""))){
			sql+= " and job_name in";
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
		sql += " order by JOB_NAME,SIGN,PARENT_JOB,CHILD_JOB";
		return sql;
	}
	
	private void getOutconds() {
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(this.sql);
			do {
				OUTCOND outcond = new OUTCOND();
				if (XmlGenerator.testModel) {
					// outcond.setNAME(StringHelper.convertNull(rs.getString("CONDITION_NAME")) + "-" + ControlMGenerator.EXTENSION);
					outcond.setNAME(this.converter.convert("OUTCOND", "NAME", StringHelper.convertNull(rs.getString("CONDITION_NAME"))));
				} else {
					outcond.setNAME(StringHelper.convertNull(rs.getString("CONDITION_NAME")));
				}
				outcond.setODATE(StringHelper.convertNull(rs.getString("ODATE")));
				outcond.setSIGN(StringHelper.convertNull(rs.getString("SIGN")));
				if (this.outconds.get(rs.getString("JOB_NAME")) == null) {
					this.outconds.put(rs.getString("JOB_NAME"), new LinkedHashMap<String, OUTCOND>());
				}
				this.outconds.get(rs.getString("JOB_NAME")).put(rs.getString("CONDITION_NAME"), outcond);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No out conditions are found, maybe OK");
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

	public void addOutconds(JOB job) {
		if (this.outconds.get(job.getJOBNAME()) == null) {
			// log.writeToLog("LM_INFOR", "No in conditions are found for job "+job.getJOBNAME());
		} else {
			for (String key : this.outconds.get(job.getJOBNAME()).keySet()) {
				job.getTAGNAMESOrRULEBASEDCALENDARSOrINCONDOrOUTCONDOrAUTOEDITOrQUANTITATIVEOrCONTROLOrSHOUTOrSTEPRANGEOrONOrAUTOEDIT2().add(this.outconds.get(job.getJOBNAME()).get(key));
			}
		}
	}

	/*
	 * public void getActionCond(){ String sql_action = "Select A.Job_Name, A.Code, A.Stmt, C.Condition_Name, C.Odate, c.sign "; sql_action+=" From Ctlm_V_Action A Left Outer Join Ctlm_V_Action_Detail B "; sql_action+=" On A.Action_Id = B.Action_Id "; sql_action+=" Left Outer Join Ctlm_V_Out_Condition C "; sql_action+=" On B.Action_Detail_Id = C.Action_Detail_Id "; sql_action+=" where c.action_detail_id is not null"; ResultSet rs = null; try { rs = this.sqlHelper.execMultiSelect(sql_action);
	 * 
	 * } catch (CriticalException e) { e.printStackTrace(); } catch (NoResultException e) { e.printStackTrace(); } }
	 */
}
