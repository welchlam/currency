package com.welch.factory;

import com.welch.conversion.Converter;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.writer.XmlGenerator;
import com.welch.xml.object.CONTROL;
import com.welch.xml.object.JOB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class ControlResourceFactory {
	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	private String sql = "SELECT * FROM CTLM_V_CONTROL_RESOURCE order by JOB_NAME";
	private Hashtable<String, Hashtable<String, CONTROL>> controls = null;
	private Converter converter;

	public ControlResourceFactory(JavaLogger log, SQLHelper sqlHelper, Converter converter) {
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.converter = converter;
		this.controls = new Hashtable<String, Hashtable<String, CONTROL>>();
		this.getControls();
	}

	private void getControls() {
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(this.sql);
			do {
				CONTROL control = new CONTROL();
				if (XmlGenerator.testModel) {
					control.setNAME(this.converter.convert("CONTROL", "NAME", StringHelper.convertNull(rs.getString("NAME"))));
				} else {
					control.setNAME(StringHelper.convertNull(rs.getString("NAME")));
				}
				control.setTYPE(StringHelper.convertNull(rs.getString("TYPE")));
				if (this.controls.get(rs.getString("JOB_NAME")) == null) {
					this.controls.put(rs.getString("JOB_NAME"), new Hashtable<String, CONTROL>());
				}
				this.controls.get(rs.getString("JOB_NAME")).put(rs.getString("NAME"), control);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No control resources are found, maybe OK");
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

	public void addControls(JOB job) {
		if (this.controls.get(job.getJOBNAME()) == null) {
			// log.writeToLog("LM_INFOR", "No control resources are found for job "+job.getJOBNAME());
		} else {
			for (String key : this.controls.get(job.getJOBNAME()).keySet()) {
				job.getTAGNAMESOrRULEBASEDCALENDARSOrINCONDOrOUTCONDOrAUTOEDITOrQUANTITATIVEOrCONTROLOrSHOUTOrSTEPRANGEOrONOrAUTOEDIT2().add(this.controls.get(job.getJOBNAME()).get(key));
			}
		}
	}
}
