package com.welch.factory;

import com.welch.conversion.Converter;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.writer.XmlGenerator;
import com.welch.xml.object.DEFTABLE;
import com.welch.xml.object.TABLE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TableFactory {
	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	//private String sql = "SELECT * FROM CTLM_V_TABLE order by TABLE_NAME";
	private String sql = null;
	private String key = "TABLE_NAME";
	private LinkedHashMap<String, TABLE> tables = null;
	private JobFactory jFactory = null;
	private Converter converter;

	public TableFactory(JavaLogger log, SQLHelper sqlHelper, Converter converter) {
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.tables = new LinkedHashMap<String, TABLE>();
		this.converter = converter;
		this.sql = this.buildSql();
		this.jFactory = new JobFactory(this.log, this.sqlHelper, converter);
		try {
			this.getTables();
		} catch (CriticalException e) {
			this.log.writeToLog("LM_ERROR", e.toString());
		}
	}
	
	private String buildSql(){
		String sql = "SELECT * FROM CTLM_V_TABLE";
		if(XmlGenerator.testModel && XmlGenerator.groups != null && (!XmlGenerator.groups.equals(""))){
			sql += " where table_name in (";
			sql += " select PARENT_TABLE from CTLM_V_JOB where group_name in ("+ XmlGenerator.groups+"))";
		}
		sql+=" order by TABLE_NAME";
		return sql;
	}

	private void getTables() throws CriticalException {
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(sql);
			do {
				TABLE table = new TABLE();
				table.setUSEDBYCODE(StringHelper.convertNull(rs.getString("USED_BY_CODE")));
				if (XmlGenerator.testModel) {
					table.setDATACENTER(this.converter.convert("TABLE", "DATACENTER", rs.getString("DATACENTER")));
					table.setTABLENAME(this.converter.convert("TABLE", "TABLE_NAME", rs.getString("TABLE_NAME")));
					table.setTABLEUSERDAILY(this.converter.convert("TABLE", "TABLE_USERDAILY", StringHelper.convertNull(rs.getString("TABLE_USERDAILY"))));
					this.tables.put(this.converter.convert("TABLE", "TABLE_NAME", rs.getString(key)), table);
				} else {
					table.setDATACENTER(rs.getString("DATACENTER"));
					table.setTABLENAME(rs.getString("TABLE_NAME"));
					table.setTABLEUSERDAILY(StringHelper.convertNull(rs.getString("TABLE_USERDAILY")));
					this.tables.put(rs.getString(key), table);
				}
			} while (rs.next());
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + this.sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			log.writeToLog("LM_ERROR", "No Control-M tables were found");
			log.writeToLog("LM_ERROR", "Query : " + this.sql);
			throw new CriticalException("No Control-M tables were found");
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
		log.writeToLog("LM_INFOR", "Loaded controlM tables successfully");
	}

	public void addTables(DEFTABLE xml) {
		for (String key : this.tables.keySet()) {
			xml.getSCHEDTABLEOrSCHEDGROUPOrTABLEOrSMARTTABLE().add(this.tables.get(key));
			this.jFactory.addJobs(this.tables.get(key));
		}
	}
	
	public void addDeltaTables(DEFTABLE xml, ArrayList<String> tableNames){
		for(int i=0;i<tableNames.size();i++){
			xml.getSCHEDTABLEOrSCHEDGROUPOrTABLEOrSMARTTABLE().add(this.tables.get(tableNames.get(i)));
		}
	}
}
