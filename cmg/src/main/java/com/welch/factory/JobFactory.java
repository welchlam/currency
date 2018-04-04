package com.welch.factory;

import com.welch.conversion.Converter;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.writer.XmlGenerator;
import com.welch.xml.object.JOB;
import com.welch.xml.object.TABLE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class JobFactory {

	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	//private String sql = "SELECT * FROM CTLM_V_JOB order by PARENT_TABLE";
	private String sql = null;
	private String tb_key = "PARENT_TABLE";
	private String job_key = "JOBNAME";
	private LinkedHashMap<String, LinkedHashMap<String, JOB>> jobs = null;
	private QuantativeResourceFactory qFactory = null;
	private ControlResourceFactory cFactory = null;
	private InConditionFactory inFactory = null;
	private CConditionFactory cConditionFactory = null;
	private OutConditionFactory outFactory = null;
	private ShoutFactory shoutFactory = null;
	private AutoEditVariableFactory aFactory = null;
	private ActionFactory actionFactory = null;
	private Converter converter;

	public JobFactory(JavaLogger log, SQLHelper sqlHelper, Converter converter) {
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.converter = converter;
		this.sql = this.buildSql();
		this.jobs = new LinkedHashMap<String, LinkedHashMap<String, JOB>>();
		this.qFactory = new QuantativeResourceFactory(this.log, this.sqlHelper, converter);
		this.cFactory = new ControlResourceFactory(this.log, this.sqlHelper, converter);
		this.inFactory = new InConditionFactory(this.log, this.sqlHelper, converter);
		this.cConditionFactory = new CConditionFactory(this.log, this.sqlHelper);
		this.outFactory = new OutConditionFactory(this.log, this.sqlHelper, converter);
		this.shoutFactory = new ShoutFactory(this.log, this.sqlHelper);
		this.aFactory = new AutoEditVariableFactory(this.log, this.sqlHelper, converter);
		this.actionFactory = new ActionFactory(this.log, this.sqlHelper, converter);
		try {
			this.getJobs();
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		}
	}
	
	private String buildSql(){
		String sql = "SELECT * FROM CTLM_V_JOB ";
		if(XmlGenerator.testModel && XmlGenerator.groups != null && (!XmlGenerator.groups.equals(""))){
			sql += " where group_name in ("+ XmlGenerator.groups+") ";
		}
		sql += " order by PARENT_TABLE,JOBNAME";
		return sql;
	}
	
	private void getJobs() throws CriticalException {
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(sql);
			do {
				JOB job = new JOB();
				this.setJobAttributes(job, rs);
				String tableName = rs.getString(this.tb_key);
				if (XmlGenerator.testModel) {
					tableName = this.converter.convert("TABLE", "TABLE_NAME", tableName);
				}
				if (this.jobs.get(tableName) == null) {
					this.jobs.put(tableName, new LinkedHashMap<String, JOB>());
				}
				this.jobs.get(tableName).put(rs.getString(this.job_key), job);
			} while (rs.next());
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + this.sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			log.writeToLog("LM_ERROR", "No Control-M jobs were found");
			log.writeToLog("LM_ERROR", "Query : " + this.sql);
			throw new CriticalException("No Control-M jobs were found");
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

	public void addJobs(TABLE table) {
		String tableName = table.getTABLENAME();
		if (this.jobs.get(tableName) == null) {
			this.log.writeToLog("LM_INFOR", "No jobs are found for table " + table.getTABLENAME());
		} else {
			for (String key : this.jobs.get(tableName).keySet()) {
				table.getJOB().add(this.jobs.get(tableName).get(key));
				this.qFactory.addQRes(this.jobs.get(tableName).get(key));
				this.cFactory.addControls(this.jobs.get(tableName).get(key));
				this.inFactory.addInconds(this.jobs.get(tableName).get(key));
				this.cConditionFactory.addCConds(this.jobs.get(tableName).get(key));
				this.outFactory.addOutconds(this.jobs.get(tableName).get(key));
				this.shoutFactory.addShouts(this.jobs.get(tableName).get(key));
				this.aFactory.addAutoEdits(this.jobs.get(tableName).get(key));
				this.actionFactory.addOns(this.jobs.get(tableName).get(key));
			}
		}
	}

	private void setJobAttributes(JOB job, ResultSet rs) throws SQLException {
		job.setACTIVEFROM((rs.getDate("ACTIVE_FROM") == null) ? "" : rs.getDate("ACTIVE_FROM").toString());
		job.setACTIVETILL((rs.getDate("ACTIVE_TILL") == null) ? "" : rs.getDate("ACTIVE_TILL").toString());
		job.setAPPLICATION(StringHelper.convertNull(rs.getString("APPLICATION")));
		job.setAPR(rs.getInt("APR") + "");
		job.setAUG(rs.getInt("AUG") + "");
		job.setAUTHOR(StringHelper.convertNull(rs.getString("AUTHOR")));
		job.setAUTOARCH(rs.getInt("AUTOARCH") + "");
		job.setCHANGEDATE(StringHelper.convertNull(rs.getString("CHANGE_DATE")));
		job.setCHANGETIME(StringHelper.convertNull(rs.getString("CHANGE_TIME")));
		job.setCHANGEUSERID(StringHelper.convertNull(rs.getString("CHANGE_USERID")));
		job.setCMDLINE(StringHelper.convertNull(rs.getString("CMDLINE")));
		job.setCONFCAL(StringHelper.convertNull(rs.getString("CONFCAL")));
		job.setCONFIRM(rs.getInt("CONFIRM") + "");
		job.setCREATIONDATE(StringHelper.convertNull(rs.getString("CREATION_DATE")));
		job.setCREATIONTIME(StringHelper.convertNull(rs.getString("CREATION_TIME")));
		job.setCREATIONUSER(StringHelper.convertNull(rs.getString("CREATION_USER")));
		job.setCRITICAL(rs.getInt("CRITICAL") + "");
		job.setCYCLIC(rs.getInt("CYCLIC") + "");
		job.setCYCLICTOLERANCE(StringHelper.convertNull(rs.getString("CYCLIC_TOLERANCE")));
		job.setCYCLICTYPE(StringHelper.convertNull(rs.getString("CYCLIC_TYPE")));
		job.setDAYS(StringHelper.convertNull(rs.getString("DAYS")));
		job.setDAYSCAL(StringHelper.convertNull(rs.getString("DAYSCAL")));
		job.setDAYSANDOR(StringHelper.convertNull(rs.getString("DAYS_AND_OR")));
		job.setDEC(rs.getInt("DEC") + "");
		job.setDESCRIPTION(StringHelper.convertNull(rs.getString("DESCRIPTION")));
		job.setDOCLIB(StringHelper.convertNull(rs.getString("DOCLIB")));
		job.setDOCMEM(StringHelper.convertNull(rs.getString("DOCMEM")));
		job.setFEB(rs.getInt("FEB") + "");
		job.setGROUP(StringHelper.convertNull(rs.getString("GROUP_NAME")));// //////////
		
		if((rs.getString("IND_CYCLIC")!=null) && (!rs.getString("IND_CYCLIC").trim().equals(""))){
			job.setINDCYCLIC(StringHelper.convertNull(rs.getString("IND_CYCLIC")));
		}
		
		job.setINSTREAMJCL(StringHelper.convertNull(rs.getString("INSTREAM_JCL")));
		job.setINTERVAL(StringHelper.convertNull(rs.getString("INTERVAL")));
		job.setJAN(rs.getInt("JAN") + "");
		job.setJOBNAME(StringHelper.convertNull(rs.getString("JOBNAME")));
		job.setJUL(rs.getInt("JUL") + "");
		job.setJUN(rs.getInt("JUN") + "");
		job.setMAR(rs.getInt("MAR") + "");
		job.setMAXDAYS(rs.getInt("MAXDAYS") + "");
		job.setMAXRERUN(rs.getInt("MAXRERUN") + "");
		job.setMAXRUNS(rs.getInt("MAXRUNS") + "");
		job.setMAXWAIT(rs.getInt("MAXWAIT") + "");
		job.setMAY(rs.getInt("MAY") + "");
		job.setMEMLIB(StringHelper.convertNull(rs.getString("MEMLIB")));
		job.setMEMNAME(StringHelper.convertNull(rs.getString("MEMNAME")));
		job.setMULTYAGENT(StringHelper.convertNull(rs.getString("MULTY_AGENT")));

		job.setNOV(rs.getInt("NOV") + "");
		job.setOCT(rs.getInt("OCT") + "");
		// job.JOB_OPTION not set;
		job.setOVERLIB(StringHelper.convertNull(rs.getString("OVERLIB")));

		job.setPAR(StringHelper.convertNull(rs.getString("PAR")));

		job.setPRIORITY(StringHelper.convertNull(rs.getString("PRIORITY")));
		job.setRETRO(rs.getInt("RETRO") + "");
		job.setRULEBASEDCALENDARRELATIONSHIP(StringHelper.convertNull(rs.getString("RULE_BASED_CALENDAR_REL")));
		job.setSEP(rs.getInt("SEP") + "");
		job.setSHIFT(StringHelper.convertNull(rs.getString("SHIFT")));
		job.setSHIFTNUM(StringHelper.convertNull(rs.getString("SHIFTNUM")));
		job.setSYSDB(rs.getInt("SYSDB") + "");
		job.setTASKTYPE(StringHelper.convertNull(rs.getString("TASKTYPE")));
		// job.setTIMEFROM(StringHelper.convertNull(rs.getString("TIMEFROM")));
		// job.setTIMETO(StringHelper.convertNull(rs.getString("TIMETO")));
		job.setTIMEZONE(StringHelper.convertNull(rs.getString("TIMEZONE")));
		job.setUSEINSTREAMJCL(StringHelper.convertNull(rs.getString("USE_INSTREAM_JCL")));
		job.setWEEKDAYS(StringHelper.convertNull(rs.getString("WEEKDAYS")));
		job.setWEEKSCAL(StringHelper.convertNull(rs.getString("WEEKSCAL")));
		if (XmlGenerator.testModel) {
			job.setNODEID(this.converter.convert("JOB", "NODEID", rs.getString("NODEID")));
			job.setOWNER(this.converter.convert("JOB", "OWNER", rs.getString("OWNER")));
			job.setPARENTTABLE(this.converter.convert("JOB", "PARENT_TABLE", StringHelper.convertNull(rs.getString("PARENT_TABLE"))));
			//
			job.setTIMEFROM(this.converter.convert("JOB", "TIMEFROM", StringHelper.convertNull(rs.getString("TIMEFROM"))));
			job.setTIMETO(this.converter.convert("JOB", "TIMETO", StringHelper.convertNull(rs.getString("TIMETO"))));
		} else {
			job.setNODEID(StringHelper.convertNull(rs.getString("NODEID")));
			job.setOWNER(StringHelper.convertNull(rs.getString("OWNER")));
			job.setPARENTTABLE(StringHelper.convertNull(rs.getString("PARENT_TABLE")));
			//
			job.setTIMEFROM(StringHelper.convertNull(rs.getString("TIMEFROM")));
			job.setTIMETO(StringHelper.convertNull(rs.getString("TIMETO")));
		}
	}

}
