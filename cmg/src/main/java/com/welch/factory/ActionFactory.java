package com.welch.factory;

import com.welch.conversion.Converter;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.writer.XmlGenerator;
import com.welch.xml.object.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class ActionFactory {
	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	private LinkedHashMap<String, LinkedHashMap<String, ON>> ons = null;
	private LinkedHashMap<String, LinkedHashMap<String, DO>> dos = null;
	private LinkedHashMap<String, LinkedHashMap<String, DOCOND>> doconds = null;
	private LinkedHashMap<String, LinkedHashMap<String, DOSYSOUT>> dosysouts = null;
	private LinkedHashMap<String, LinkedHashMap<String, DOSHOUT>> doshouts = null;
	private LinkedHashMap<String, LinkedHashMap<String, DOREMEDY>> doremedys = null;
	private LinkedHashMap<String, LinkedHashMap<String, DOMAIL>> domails = null;
	private LinkedHashMap<String, LinkedHashMap<String, DOFORCEJOB>> doforcejobs = null;
	private LinkedHashMap<String, LinkedHashMap<String, DOAUTOEDIT2>> doautoedits = null;
	private Converter converter;
	private LinkedHashMap<String, LinkedHashMap<String, DOCOND>> cConditions = null;

	public ActionFactory(JavaLogger log, SQLHelper sqlHelper, Converter converter) {
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.converter = converter;
		this.ons = new LinkedHashMap<String, LinkedHashMap<String, ON>>();
		this.dos = new LinkedHashMap<String, LinkedHashMap<String, DO>>();
		this.doconds = new LinkedHashMap<String, LinkedHashMap<String, DOCOND>>();
		this.dosysouts = new LinkedHashMap<String, LinkedHashMap<String, DOSYSOUT>>();
		this.doshouts = new LinkedHashMap<String, LinkedHashMap<String, DOSHOUT>>();
		this.doremedys = new LinkedHashMap<String, LinkedHashMap<String, DOREMEDY>>();
		this.domails = new LinkedHashMap<String, LinkedHashMap<String, DOMAIL>>();
		this.doforcejobs = new LinkedHashMap<String, LinkedHashMap<String, DOFORCEJOB>>();
		this.doautoedits = new LinkedHashMap<String, LinkedHashMap<String, DOAUTOEDIT2>>();
		this.cConditions = new LinkedHashMap<String, LinkedHashMap<String, DOCOND>>();
		this.getDos();
		this.getDoconds();
		this.getDosysouts();
		this.getDoshouts();
		this.getDoremedys();
		this.getDomails();
		this.getDoforcejobs();
		this.getDoautoedits();
		this.getCConditions();
		this.getOns();
	}

	private void getOns() {
		String sql = "Select Action_Id, Job_Name, Code, Spec_Col From (select ACTION_ID, JOB_NAME, CODE, STMT as spec_col from Ctlm_V_Action)";
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(sql);
			do {
				String code = StringHelper.convertNull(rs.getString("code"));
				String stmt = StringHelper.convertNull(rs.getString("SPEC_COL"));
				ON on = new ON();
				on.setCODE(code);
				on.setSTMT(stmt);
				this.addDos(on, rs.getInt("ACTION_ID") + "");
				this.addDoconds(on, rs.getInt("ACTION_ID") + "");
				this.addDosysouts(on, rs.getInt("ACTION_ID") + "");
				this.addDoshouts(on, rs.getInt("ACTION_ID") + "");
				this.addDoremedys(on, rs.getInt("ACTION_ID") + "");
				this.addDomails(on, rs.getInt("ACTION_ID") + "");
				this.addDoforcejobs(on, rs.getInt("ACTION_ID") + "");
				this.addDoautoedit(on, rs.getInt("ACTION_ID") + "");
				this.addCConditions(on, rs.getInt("ACTION_ID") + "");
				if (this.ons.get(rs.getString("job_name")) == null) {
					this.ons.put(rs.getString("job_name"), new LinkedHashMap<String, ON>());
				}
				this.ons.get(rs.getString("job_name")).put(rs.getInt("action_id") + "", on);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No actions are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + sql);
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
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

	public void addOns(JOB job) {
		if (this.ons.get(job.getJOBNAME()) == null) {
			// log.writeToLog("LM_INFOR", "No actions are found for job "+job.getJOBNAME());
		} else {
			for (String key : this.ons.get(job.getJOBNAME()).keySet()) {
				job.getTAGNAMESOrRULEBASEDCALENDARSOrINCONDOrOUTCONDOrAUTOEDITOrQUANTITATIVEOrCONTROLOrSHOUTOrSTEPRANGEOrONOrAUTOEDIT2().add(this.ons.get(job.getJOBNAME()).get(key));
			}
		}
	}

	private void getDos() {
		ResultSet rs = null;
		String do_sql = " Select * From Ctlm_V_Action_Detail ";
		do_sql += " Where Action_Detail_Id In ( ";
		do_sql += " select Action_Detail_Id from Ctlm_V_Action_Detail ";
		do_sql += " minus  select Action_Detail_Id from CTLM_V_C_CONDITION where action_detail_id is not null and action_detail_id <> 0  ";
		do_sql += " minus  select Action_Detail_Id from CTLM_V_OUT_CONDITION where action_detail_id is not null and action_detail_id <> 0 ";
		do_sql += " minus  select Action_Detail_Id from CTLM_V_SYSOUT where action_detail_id is not null ";
		do_sql += " minus  select Action_Detail_Id from CTLM_V_SHOUT where action_detail_id is not null ";
		do_sql += " minus  Select Action_Detail_Id From CTLM_V_REMEDY where action_detail_id is not null ";
		do_sql += " minus  select Action_Detail_Id from CTLM_V_MAIL where action_detail_id is not null ";
		do_sql += " minus  Select Action_Detail_Id From Ctlm_V_Force_Job where action_detail_id is not null ";
		do_sql += " minus  Select Action_Detail_Id From Ctlm_V_Auto_Edit_Variable where action_detail_id is not null )";
		try {
			rs = this.sqlHelper.execMultiSelect(do_sql);
			do {
				DO ado = new DO();
				ado.setACTION(StringHelper.convertNull(rs.getString("ACTION")));
				if (this.dos.get(rs.getInt("ACTION_ID") + "") == null) {
					this.dos.put(rs.getInt("ACTION_ID") + "", new LinkedHashMap<String, DO>());
				}
				this.dos.get(rs.getInt("ACTION_ID") + "").put(rs.getInt("ACTION_DETAIL_ID") + "", ado);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No dos are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + do_sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.writeToLog("LM_ERROR", "Failed to close result set");
				e.printStackTrace();
			}
		}
	}

	private void addDos(ON on, String action_id) {
		if (this.dos.get(action_id) == null) {
			// log.writeToLog("LM_INFOR", "No dos are found for action "+action_id);
		} else {
			for (String key : this.dos.get(action_id).keySet()) {
				on.getDOOrDOAUTOEDITOrDOSHOUTOrDOFORCEJOBOrDOCTBRULEOrDOSYSOUTOrDOIFRERUNOrDOCONDOrDOMAILOrDOREMEDYOrDOAUTOEDIT2().add(this.dos.get(action_id).get(key));
			}
		}
	}

	private void getDoconds() {
		ResultSet rs = null;
		String cond_sql = "Select A.Action_Id, B.Action_Detail_Id, B.Condition_Name, B.Odate, B.Sign";
		cond_sql += " From Ctlm_V_Action_Detail A, Ctlm_V_Out_Condition B ";
		cond_sql += " Where A.Action_Detail_Id = B.Action_Detail_Id ";
		cond_sql += " and B.Action_Detail_Id is not null ";
		if(XmlGenerator.testModel && XmlGenerator.groups != null && (!XmlGenerator.groups.equals(""))){
			cond_sql += " and b.job_name in ";
			cond_sql += " ( ";
			cond_sql += " select jobname from CTLM_V_JOB where group_name in ("+ XmlGenerator.groups+") ";
			cond_sql += " ) ";
			cond_sql += " and b.parent_job in ";
			cond_sql += " ( ";
			cond_sql += " select jobname from CTLM_V_JOB where group_name in ("+ XmlGenerator.groups+") ";
			cond_sql += " ) ";
			cond_sql += " and b.child_job in ";
			cond_sql += " ( ";
			cond_sql += " select jobname from CTLM_V_JOB where group_name in ("+ XmlGenerator.groups+") ";
			cond_sql += " ) ";
		}
		try {
			rs = this.sqlHelper.execMultiSelect(cond_sql);
			do {
				DOCOND docond = new DOCOND();
				if (XmlGenerator.testModel) {
					docond.setNAME(this.converter.convert("DOCOND", "NAME", StringHelper.convertNull(rs.getString("CONDITION_NAME"))));
				} else {
					docond.setNAME(StringHelper.convertNull(rs.getString("CONDITION_NAME")));
				}
				docond.setODATE(StringHelper.convertNull(rs.getString("ODATE")));
				docond.setSIGN(StringHelper.convertNull(rs.getString("SIGN")));
				if (this.doconds.get(rs.getInt("Action_Id") + "") == null) {
					this.doconds.put(rs.getInt("Action_Id") + "", new LinkedHashMap<String, DOCOND>());
				}
				this.doconds.get(rs.getInt("Action_Id") + "").put(rs.getInt("Action_Detail_Id") + "", docond);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No doconds are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + cond_sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.writeToLog("LM_ERROR", "Failed to close result set");
				log.writeToLog("LM_ERROR", e.getMessage());
				// e.printStackTrace();
			}
		}
	}

	private void addDoconds(ON on, String action_id) {
		if (this.doconds.get(action_id) == null) {
			// log.writeToLog("LM_INFOR", "No doconds are found for action "+action_id);
		} else {
			for (String key : this.doconds.get(action_id).keySet()) {
				on.getDOOrDOAUTOEDITOrDOSHOUTOrDOFORCEJOBOrDOCTBRULEOrDOSYSOUTOrDOIFRERUNOrDOCONDOrDOMAILOrDOREMEDYOrDOAUTOEDIT2().add(this.doconds.get(action_id).get(key));
			}
		}
	}
	
	private void getCConditions() {
		ResultSet rs = null;
		String cond_sql = "Select A.Action_Id, B.Action_Detail_Id, B.Condition_Name, B.Odate, B.Sign";
		cond_sql += " From Ctlm_V_Action_Detail A, CTLM_V_C_CONDITION B ";
		cond_sql += " Where A.Action_Detail_Id = B.Action_Detail_Id ";
		cond_sql += " and B.Action_Detail_Id <> 0 ";
		if(XmlGenerator.testModel && XmlGenerator.groups != null && (!XmlGenerator.groups.equals(""))){
			cond_sql += " and b.job_name in ";
			cond_sql += " ( ";
			cond_sql += " select jobname from CTLM_V_JOB where group_name in ("+ XmlGenerator.groups+") ";
			cond_sql += " ) ";
		}
		try {
			rs = this.sqlHelper.execMultiSelect(cond_sql);
			do {
				DOCOND docond = new DOCOND();
				if (XmlGenerator.testModel) {
					docond.setNAME(this.converter.convert("DOCOND", "NAME", StringHelper.convertNull(rs.getString("CONDITION_NAME"))));
				} else {
					docond.setNAME(StringHelper.convertNull(rs.getString("CONDITION_NAME")));
				}
				docond.setODATE(StringHelper.convertNull(rs.getString("ODATE")));
				docond.setSIGN(StringHelper.convertNull(rs.getString("SIGN")));
				if (this.cConditions.get(rs.getInt("Action_Id") + "") == null) {
					this.cConditions.put(rs.getInt("Action_Id") + "", new LinkedHashMap<String, DOCOND>());
				}
				this.cConditions.get(rs.getInt("Action_Id") + "").put(rs.getInt("Action_Detail_Id") + "", docond);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No doconds are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + cond_sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.writeToLog("LM_ERROR", "Failed to close result set");
				log.writeToLog("LM_ERROR", e.getMessage());
				// e.printStackTrace();
			}
		}
	}

	private void addCConditions(ON on, String action_id) {
		if (this.cConditions.get(action_id) == null) {
			// log.writeToLog("LM_INFOR", "No doconds are found for action "+action_id);
		} else {
			for (String key : this.cConditions.get(action_id).keySet()) {
				on.getDOOrDOAUTOEDITOrDOSHOUTOrDOFORCEJOBOrDOCTBRULEOrDOSYSOUTOrDOIFRERUNOrDOCONDOrDOMAILOrDOREMEDYOrDOAUTOEDIT2().add(this.cConditions.get(action_id).get(key));
			}
		}
	}

	private void getDosysouts() {
		ResultSet rs = null;
		String sysout_sql = "Select A.Action_Id, B.Action_Detail_Id, B.SYSOUT_OPTION, B.PAR";
		sysout_sql += " From Ctlm_V_Action_Detail A, CTLM_V_SYSOUT B ";
		sysout_sql += " Where A.Action_Detail_Id = B.Action_Detail_Id ";
		sysout_sql += " and B.Action_Detail_Id is not null ";
		try {
			rs = this.sqlHelper.execMultiSelect(sysout_sql);
			do {
				DOSYSOUT sysout = new DOSYSOUT();
				sysout.setOPTION(StringHelper.convertNull(rs.getString("SYSOUT_OPTION")));
				sysout.setPAR(StringHelper.convertNull(rs.getString("PAR")));
				if (this.dosysouts.get(rs.getInt("Action_Id") + "") == null) {
					this.dosysouts.put(rs.getInt("Action_Id") + "", new LinkedHashMap<String, DOSYSOUT>());
				}
				this.dosysouts.get(rs.getInt("Action_Id") + "").put(rs.getInt("Action_Detail_Id") + "", sysout);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No dosysouts are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + sysout_sql);
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

	private void addDosysouts(ON on, String action_id) {
		if (this.dosysouts.get(action_id) == null) {
			// log.writeToLog("LM_INFOR", "No dosysouts are found for action "+action_id);
		} else {
			for (String key : this.dosysouts.get(action_id).keySet()) {
				on.getDOOrDOAUTOEDITOrDOSHOUTOrDOFORCEJOBOrDOCTBRULEOrDOSYSOUTOrDOIFRERUNOrDOCONDOrDOMAILOrDOREMEDYOrDOAUTOEDIT2().add(this.dosysouts.get(action_id).get(key));
			}
		}
	}

	private void getDoshouts() {
		ResultSet rs = null;
		String shout_sql = "Select A.Action_Id, B.Action_Detail_Id, B.DEST, B.MESSAGE, B.TIME, B.URGENCY, B.WHEN";
		shout_sql += " From Ctlm_V_Action_Detail A, CTLM_V_SHOUT B ";
		shout_sql += " Where A.Action_Detail_Id = B.Action_Detail_Id ";
		shout_sql += " and B.Action_Detail_Id is not null ";
		try {
			rs = this.sqlHelper.execMultiSelect(shout_sql);
			do {
				DOSHOUT doshout = new DOSHOUT();
				doshout.setDEST(StringHelper.convertNull(rs.getString("DEST")));
				doshout.setMESSAGE(StringHelper.convertNull(rs.getString("MESSAGE")));
				doshout.setURGENCY(StringHelper.convertNull(rs.getString("URGENCY")));
				if (this.doshouts.get(rs.getInt("Action_Id") + "") == null) {
					this.doshouts.put(rs.getInt("Action_Id") + "", new LinkedHashMap<String, DOSHOUT>());
				}
				this.doshouts.get(rs.getInt("Action_Id") + "").put(rs.getInt("Action_Detail_Id") + "", doshout);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No doshouts are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + shout_sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.writeToLog("LM_ERROR", "Failed to close result set");
				log.writeToLog("LM_ERROR", e.getMessage());
				// e.printStackTrace();
			}
		}
	}

	private void addDoshouts(ON on, String action_id) {
		if (this.doshouts.get(action_id) == null) {
			// log.writeToLog("LM_INFOR", "No doshouts are found for action "+action_id);
		} else {
			for (String key : this.doshouts.get(action_id).keySet()) {
				on.getDOOrDOAUTOEDITOrDOSHOUTOrDOFORCEJOBOrDOCTBRULEOrDOSYSOUTOrDOIFRERUNOrDOCONDOrDOMAILOrDOREMEDYOrDOAUTOEDIT2().add(this.doshouts.get(action_id).get(key));
			}
		}
	}

	private void getDoremedys() {
		ResultSet rs = null;
		String remedy_sql = "Select A.Action_Id, B.Action_Detail_Id, B.DESCRIPTION, B.SUMMARY, B.URGENCY";
		remedy_sql += " From Ctlm_V_Action_Detail A, CTLM_V_REMEDY B ";
		remedy_sql += " Where A.Action_Detail_Id = B.Action_Detail_Id ";
		remedy_sql += " and B.Action_Detail_Id is not null ";
		try {
			rs = this.sqlHelper.execMultiSelect(remedy_sql);
			do {
				DOREMEDY doremedy = new DOREMEDY();
				doremedy.setDESCRIPTION(StringHelper.convertNull(rs.getString("DESCRIPTION")));
				doremedy.setSUMMARY(StringHelper.convertNull(rs.getString("SUMMARY")));
				doremedy.setURGENCY(StringHelper.convertNull(rs.getString("URGENCY")));
				if (this.doremedys.get(rs.getInt("Action_Id") + "") == null) {
					this.doremedys.put(rs.getInt("Action_Id") + "", new LinkedHashMap<String, DOREMEDY>());
				}
				this.doremedys.get(rs.getInt("Action_Id") + "").put(rs.getInt("Action_Detail_Id") + "", doremedy);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No doremedys are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + remedy_sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.writeToLog("LM_ERROR", "Failed to close result set");
				log.writeToLog("LM_ERROR", e.getMessage());
				// e.printStackTrace();
			}
		}
	}

	private void addDoremedys(ON on, String action_id) {
		if (this.doremedys.get(action_id) == null) {
			// log.writeToLog("LM_INFOR", "No doremedys are found for action "+action_id);
		} else {
			for (String key : this.doremedys.get(action_id).keySet()) {
				on.getDOOrDOAUTOEDITOrDOSHOUTOrDOFORCEJOBOrDOCTBRULEOrDOSYSOUTOrDOIFRERUNOrDOCONDOrDOMAILOrDOREMEDYOrDOAUTOEDIT2().add(this.doremedys.get(action_id).get(key));
			}
		}
	}

	private void getDomails() {
		ResultSet rs = null;
		String mail_sql = "Select A.Action_Id, B.Action_Detail_Id, B.ATTACH_SYSOUT, B.CC_DEST, B.DEST, B.MESSAGE, B.SUBJECT, B.URGENCY";
		mail_sql += " From Ctlm_V_Action_Detail A, CTLM_V_MAIL B ";
		mail_sql += " Where A.Action_Detail_Id = B.Action_Detail_Id ";
		mail_sql += " and B.Action_Detail_Id is not null ";
		try {
			rs = this.sqlHelper.execMultiSelect(mail_sql);
			do {
				DOMAIL domail = new DOMAIL();
				domail.setATTACHSYSOUT(StringHelper.convertNull(rs.getString("ATTACH_SYSOUT")));
				domail.setCCDEST(StringHelper.convertNull(rs.getString("CC_DEST")));
				domail.setDEST(StringHelper.convertNull(rs.getString("DEST")));
				domail.setMESSAGE(StringHelper.convertNull(rs.getString("SUBJECT")));
				domail.setURGENCY(StringHelper.convertNull(rs.getString("URGENCY")));
				if (this.domails.get(rs.getInt("Action_Id") + "") == null) {
					this.domails.put(rs.getInt("Action_Id") + "", new LinkedHashMap<String, DOMAIL>());
				}
				this.domails.get(rs.getInt("Action_Id") + "").put(rs.getInt("Action_Detail_Id") + "", domail);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No domails are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + mail_sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.writeToLog("LM_ERROR", "Failed to close result set");
				log.writeToLog("LM_ERROR", e.getMessage());
				// e.printStackTrace();
			}
		}
	}

	private void addDomails(ON on, String action_id) {
		if (this.domails.get(action_id) == null) {
			// log.writeToLog("LM_INFOR", "No domails are found for action "+action_id);
		} else {
			for (String key : this.domails.get(action_id).keySet()) {
				on.getDOOrDOAUTOEDITOrDOSHOUTOrDOFORCEJOBOrDOCTBRULEOrDOSYSOUTOrDOIFRERUNOrDOCONDOrDOMAILOrDOREMEDYOrDOAUTOEDIT2().add(this.domails.get(action_id).get(key));
			}
		}
	}

	private void getDoforcejobs() {
		ResultSet rs = null;
		String forcejob_sql = "Select A.Action_Id, B.Action_Detail_Id, B.NAME, B.ODATE, B.TABLE_NAME";
		forcejob_sql += " From Ctlm_V_Action_Detail A, Ctlm_V_Force_Job B ";
		forcejob_sql += " Where A.Action_Detail_Id = B.Action_Detail_Id ";
		forcejob_sql += " and B.Action_Detail_Id is not null ";
		try {
			rs = this.sqlHelper.execMultiSelect(forcejob_sql);
			do {
				DOFORCEJOB doforcejob = new DOFORCEJOB();
				doforcejob.setNAME(StringHelper.convertNull(rs.getString("NAME")));
				doforcejob.setODATE(StringHelper.convertNull(rs.getString("ODATE")));
				doforcejob.setTABLENAME(StringHelper.convertNull(rs.getString("TABLE_NAME")));
				if (this.doforcejobs.get(rs.getInt("Action_Id") + "") == null) {
					this.doforcejobs.put(rs.getInt("Action_Id") + "", new LinkedHashMap<String, DOFORCEJOB>());
				}
				this.doforcejobs.get(rs.getInt("Action_Id") + "").put(rs.getInt("Action_Detail_Id") + "", doforcejob);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
			// e.printStackTrace();
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No doforcejobs are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + forcejob_sql);
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

	private void addDoforcejobs(ON on, String action_id) {
		if (this.doforcejobs.get(action_id) == null) {
			// log.writeToLog("LM_INFOR", "No doforcejobs are found for action "+action_id);
		} else {
			for (String key : this.doforcejobs.get(action_id).keySet()) {
				on.getDOOrDOAUTOEDITOrDOSHOUTOrDOFORCEJOBOrDOCTBRULEOrDOSYSOUTOrDOIFRERUNOrDOCONDOrDOMAILOrDOREMEDYOrDOAUTOEDIT2().add(this.doforcejobs.get(action_id).get(key));
			}
		}
	}

	private void getDoautoedits() {
		ResultSet rs = null;
		String autoedit_sql = "Select A.Action_Id, B.Action_Detail_Id, B.NAME, B.VALUE";
		autoedit_sql += " From Ctlm_V_Action_Detail A, CTLM_V_AUTO_EDIT_VARIABLE B ";
		autoedit_sql += " Where A.Action_Detail_Id = B.Action_Detail_Id ";
		autoedit_sql += " and B.Action_Detail_Id is not null ";
		try {
			rs = this.sqlHelper.execMultiSelect(autoedit_sql);
			do {
				DOAUTOEDIT2 doautoedit = new DOAUTOEDIT2();
				if (XmlGenerator.testModel) {
					doautoedit.setNAME(this.converter.convert("AUTOEDIT2", "NAME", StringHelper.convertNull(rs.getString("NAME"))));
					doautoedit.setVALUE(this.converter.convert("AUTOEDIT2", "VALUE", StringHelper.convertNull(rs.getString("VALUE"))));
				} else {
					doautoedit.setNAME(StringHelper.convertNull(rs.getString("NAME")));
					doautoedit.setVALUE(StringHelper.convertNull(rs.getString("VALUE")));
				}
				//doautoedit.setNAME(StringHelper.convertNull(rs.getString("name")));
				//doautoedit.setVALUE(StringHelper.convertNull(rs.getString("value")));
				if (this.doautoedits.get(rs.getInt("action_id") + "") == null) {
					this.doautoedits.put(rs.getInt("action_id") + "", new LinkedHashMap<String, DOAUTOEDIT2>());
				}
				this.doautoedits.get(rs.getInt("action_id") + "").put(rs.getInt("Action_Detail_Id") + "", doautoedit);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No doautoedits are found, maybe OK");
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + autoedit_sql);
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

	private void addDoautoedit(ON on, String action_id) {
		if (this.doautoedits.get(action_id) == null) {
			// this.log.writeToLog("LM_INFOR", "No doautoedits are found for action "+action_id);
		} else {
			for (String key : this.doautoedits.get(action_id).keySet()) {
				on.getDOOrDOAUTOEDITOrDOSHOUTOrDOFORCEJOBOrDOCTBRULEOrDOSYSOUTOrDOIFRERUNOrDOCONDOrDOMAILOrDOREMEDYOrDOAUTOEDIT2().add(this.doautoedits.get(action_id).get(key));
			}
		}
	}
}
