package com.welch.factory;

import com.welch.conversion.Converter;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.writer.XmlGenerator;
import com.welch.xml.object.JOB;
import com.welch.xml.object.QUANTITATIVE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class QuantativeResourceFactory {
	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	//CTLM_V_QUANT_RESOURCE
	private String sql = "SELECT * FROM ctlm_v_quantative_res order by JOB_NAME,NAME";
	private Hashtable<String, Hashtable<String, QUANTITATIVE>> all_q_res = null;
	private Converter converter;

	public QuantativeResourceFactory(JavaLogger log, SQLHelper sqlHelper, Converter converter) {
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.converter = converter;
		this.all_q_res = new Hashtable<String, Hashtable<String, QUANTITATIVE>>();
		this.getQRes();
	}

	private void getQRes() {
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(this.sql);
			do {
				QUANTITATIVE q_res = new QUANTITATIVE();
				if (XmlGenerator.testModel) {
					q_res.setNAME(this.converter.convert("QUANTITATIVE", "NAME", StringHelper.convertNull(rs.getString("NAME"))));
				} else {
					q_res.setNAME(StringHelper.convertNull(rs.getString("NAME")));
				}
				q_res.setQUANT(rs.getInt("QUANT") + "");
				if (this.all_q_res.get(rs.getString("JOB_NAME")) == null) {
					this.all_q_res.put(rs.getString("JOB_NAME"), new Hashtable<String, QUANTITATIVE>());
				}
				this.all_q_res.get(rs.getString("JOB_NAME")).put(rs.getString("NAME"), q_res);
			} while (rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			// log.writeToLog("LM_INFOR", "No quantative resources are found, maybe OK");
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

	public void addQRes(JOB job) {
		if (this.all_q_res.get(job.getJOBNAME()) == null) {
			// log.writeToLog("LM_INFOR", "No quantative resource found for job "+job.getJOBNAME());
		} else {
			for (String key : this.all_q_res.get(job.getJOBNAME()).keySet()) {
				job.getTAGNAMESOrRULEBASEDCALENDARSOrINCONDOrOUTCONDOrAUTOEDITOrQUANTITATIVEOrCONTROLOrSHOUTOrSTEPRANGEOrONOrAUTOEDIT2().add(this.all_q_res.get(job.getJOBNAME()).get(key));
			}
		}
	}
}
