package com.welch.factory;

import com.welch.conversion.Converter;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.writer.XmlGenerator;
import com.welch.xml.object.AUTOEDIT2;
import com.welch.xml.object.JOB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.LinkedHashMap;

public class AutoEditVariableFactory {
	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	private String sql = "Select * From CTLM_V_AUTO_EDIT_VARIABLE Where Action_Detail_Id Is Null Order By Job_Name,AEV_ORDER";
	private Hashtable<String, LinkedHashMap<String, AUTOEDIT2>> autoedits = null;
	private Converter converter;

	public AutoEditVariableFactory(JavaLogger log, SQLHelper sqlHelper, Converter converter) {
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.converter = converter;
		this.autoedits = new Hashtable<String, LinkedHashMap<String, AUTOEDIT2>>();
		this.getAutoEdits();
	}

	private void getAutoEdits() {
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(this.sql);
			do {
				AUTOEDIT2 autoedit = new AUTOEDIT2();
				if (XmlGenerator.testModel) {
					autoedit.setNAME(this.converter.convert("AUTOEDIT2", "NAME", StringHelper.convertNull(rs.getString("NAME"))));
					autoedit.setVALUE(this.converter.convert("AUTOEDIT2", "VALUE", StringHelper.convertNull(rs.getString("VALUE"))));
				} else {
					autoedit.setNAME(StringHelper.convertNull(rs.getString("NAME")));
					autoedit.setVALUE(StringHelper.convertNull(rs.getString("VALUE")));
				}
				if (this.autoedits.get(rs.getString("JOB_NAME")) == null) {
					this.autoedits.put(rs.getString("JOB_NAME"), new LinkedHashMap<String, AUTOEDIT2>());
				}
				this.autoedits.get(rs.getString("JOB_NAME")).put(rs.getString("NAME"), autoedit);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No autoedits are found, maybe OK");
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

	public void addAutoEdits(JOB job) {
		if (this.autoedits.get(job.getJOBNAME()) == null) {
			// log.writeToLog("LM_INFOR", "No auto edit variables are found for job "+job.getJOBNAME());
		} else {
			for (String key : this.autoedits.get(job.getJOBNAME()).keySet()) {
				job.getTAGNAMESOrRULEBASEDCALENDARSOrINCONDOrOUTCONDOrAUTOEDITOrQUANTITATIVEOrCONTROLOrSHOUTOrSTEPRANGEOrONOrAUTOEDIT2().add(this.autoedits.get(job.getJOBNAME()).get(key));
			}
		}
	}
}
