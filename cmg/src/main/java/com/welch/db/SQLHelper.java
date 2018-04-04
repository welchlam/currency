package com.welch.db;

import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.PropertiesReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLHelper {
	private JavaLogger log = null;
	private DBConnectionFactory dbConFactory = null;
	private Connection conn = null;

	public SQLHelper(PropertiesReader pReader, JavaLogger log, String oracleUser, String password) throws CriticalException {
		this.log = log;
		this.dbConFactory = new DBConnectionFactory(pReader, log, oracleUser, password);
		conn = dbConFactory.openConnection();
	}

	public SQLHelper(JavaLogger log, String hostName, String port, String sid, String oracleUser, String password) throws CriticalException {
		this.log = log;
		this.dbConFactory = new DBConnectionFactory(log, hostName, port, sid, oracleUser, password);
		conn = dbConFactory.openConnection();
	}

	public SQLHelper(String dbPassword, PropertiesReader pReader, JavaLogger log) throws CriticalException {
		this.log = log;
		this.dbConFactory = new DBConnectionFactory(dbPassword, pReader, log);
		conn = dbConFactory.openConnection();
	}

	public ResultSet execMultiSelect(String sql) throws CriticalException, NoResultException {
		PreparedStatement sqlStmt = null;
		ResultSet rs = null;
		try {
			sqlStmt = conn.prepareStatement(sql);
			rs = sqlStmt.executeQuery();

			if (rs.next()) {
				return rs;
			} else {
				rs.close();
				sqlStmt.close();
				log.writeToLog("LM_INFOR", "");
				log.writeToLog("LM_INFOR", "No results returned from select statment.");
				log.writeToLog("LM_INFOR", "Command: " + sql);
				throw new NoResultException("No results returned from select statment.");
			}
		} catch (SQLException e) {
			log.writeToLog("LM_ERROR", "Error while trying to execute sql command");
			log.writeToLog("LM_ERROR", "Command: " + sql);
			log.writeToLog("LM_ERROR", "Exception: " + e);
			throw new CriticalException("Sql command failed");
		}
	}
}
