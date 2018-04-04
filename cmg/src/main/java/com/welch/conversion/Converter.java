package com.welch.conversion;

import com.welch.conversion.object.Rule;
import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Converter {
	private List<Rule> ruleList = new ArrayList<Rule>();
	private JavaLogger log = null;
	private String envName = null;
	private SQLHelper envSqlHelper = null;

	public Converter(JavaLogger log, String envName, SQLHelper envSqlHelper) {
		this.log = log;
		this.envName = envName;
		this.envSqlHelper = envSqlHelper;
		this.init();
	}

	public Converter(List<Rule> ruleList){
		this.ruleList = ruleList;
	}

	private void init() {
		String sql = "SELECT * FROM CTLM_ENV_CONFIG where env_name = '" + this.envName + "'";
		ResultSet rs = null;
		try {
			rs = this.envSqlHelper.execMultiSelect(sql);
			do {
				Rule rule = new Rule();
				rule.setAttribute(rs.getString("ATTRIBUTE"));
				rule.setConverType(rs.getString("CONVERT_TYPE"));
				rule.setEnvName(rs.getString("ENV_NAME"));
				rule.setNewValue(rs.getString("NEW_VALUE"));
				rule.setOldValue(rs.getString("OLD_VALUE"));
				rule.setTagName(rs.getString("TAG_NAME"));
				this.ruleList.add(rule);
			} while (rs.next());
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "SQL error: " + sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			log.writeToLog("LM_ERROR", "No configuration are found for " + this.envName);
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

	public String convert(String tagName, String attribute, String value) {
		for (int i = 0; i < this.ruleList.size(); i++) {
			Rule rule = this.ruleList.get(i);
			if (tagName.equalsIgnoreCase(rule.getTagName()) && attribute.equalsIgnoreCase(rule.getAttribute())) {
				if (rule.getConverType().toUpperCase().equals("ASSIGN")) {
					value = rule.getNewValue();
				} else if (rule.getConverType().toUpperCase().equals("REPLACE")) {
					value = value.replace(rule.getOldValue(), rule.getNewValue());
				} else if (rule.getConverType().toUpperCase().equals("SUFFIX")) {
					value = value + rule.getNewValue();
				} else if (rule.getConverType().toUpperCase().equals("PREFIX")) {
					value = rule.getNewValue() + value;
				} else {
					this.log.writeToLog("LM_ERROR", "Invalid conversion type " + rule.getConverType());
				}
			}
		}
		return value;
	}
}
