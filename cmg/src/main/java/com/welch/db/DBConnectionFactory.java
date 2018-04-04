package com.welch.db;

import com.welch.exception.CriticalException;
import com.welch.util.JavaLogger;
import com.welch.util.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionFactory {
	private PropertiesReader pR = null;
	private JavaLogger log = null;

	private String hostName = null;
	private String port = null;
	private String sid = null;
	private String password = null;
	private String user = null;

	public DBConnectionFactory(PropertiesReader propReader, JavaLogger log, String oracleUser, String password) {
		this.pR = propReader;
		this.log = log;
		this.password = password;
		this.user = oracleUser;
	}

	public DBConnectionFactory(JavaLogger log, String hostName, String port, String sid, String oracleUser, String password) {
		this.log = log;
		this.hostName = hostName;
		this.port = port;
		this.sid = sid;
		this.password = password;
		this.user = oracleUser;
	}

	public DBConnectionFactory(String dbPassword, PropertiesReader propReader, JavaLogger log) {
		this.pR = propReader;
		this.log = log;
		try {
			this.user = this.pR.getProp("USER");
			//this.password = this.pR.getProp("PASSWORD");
			this.password = dbPassword;
		} catch (CriticalException e) {
			this.log.writeToLog("LM_ERROR", e.getMessage());
		}
	}

	public Connection openConnection() throws CriticalException {
		String connectionString = user + "/" + password + "@" + getDatabaseURL();

		Connection conn = null;

		// Load the Oracle JDBC Driver and register it.
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

			// The following statement creates an encrypted database connection object using the
			// DriverManager.getConnection method.

			Properties props = new Properties();
			props.put("oracle.net.encryption_client", "REQUIRED");
			props.put("oracle.net.encryption_types_client", "DES40C");
			props.put("oracle.net.crypto_checksum_types_client", "MD5");
			props.put("user", user);
			props.put("password", password);

			conn = DriverManager.getConnection(getDatabaseURL(), props);
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			// Get the error code for the SQL Exception
			log.writeToLog("LM_ERROR", "Failed to connect to the database");
			log.writeToLog("LM_ERROR", "Connection Information : " + connectionString);
			log.writeToLog("LM_ERROR", "Exception: " + sqle);
			throw new CriticalException("Failed to connect to database");
		}
		// Return the database connection object
		return conn;
	}

	protected String getDatabaseURL() throws CriticalException {
		// Based on the driver return the database URL information
		if (this.pR != null) {
			return "jdbc:oracle:thin:@" + pR.getProp("HOSTNAME") + ":" + pR.getProp("PORT") + ":" + pR.getProp("SID");
		} else {
			return "jdbc:oracle:thin:@" + this.hostName + ":" + this.port + ":" + this.sid;
		}
	}
}
