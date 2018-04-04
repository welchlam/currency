package com.welch.util;

import com.welch.exception.CriticalException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesReader {

	private String[] propNames = { "HOSTNAME", "PORT", "SID", "USER", "PASSWORD", "OUTPUTFILE"};

	private HashMap<String, String> propMap = new HashMap<String, String>();
	private JavaLogger log;
	private Properties properties = new Properties();

	public PropertiesReader(String propFilePath, JavaLogger log) throws CriticalException {
		try {
			this.log = log;
			this.properties.load(new FileInputStream(propFilePath));
			this.loadFile();
		} catch (IOException e) {
			log.writeToLog("LM_ERROR", "Failed to read properties file");
			log.writeToLog("LM_ERROR", "Properties file path: " + propFilePath);
			log.writeToLog("LM_ERROR", "Exception: " + e);
			throw new CriticalException("Could not read properties file");
		} catch (NullPointerException e) {
			log.writeToLog("LM_ERROR", "Properties file path not set");
			log.writeToLog("LM_ERROR", "Exception: " + e);
			throw new CriticalException("Property file path not set");
		}
	}

	private void loadFile() throws CriticalException {
		for (int x = 0; x < propNames.length; x++) {
			try {
				String propStr = properties.getProperty(propNames[x]).trim();

				if (!propStr.equals(null)) {
					if (propStr.contains("|$")) {
						propStr = resolveString(propStr);
					}
					propMap.put(propNames[x], propStr);

					while (propNames[x].length() < 16) {
						propNames[x] += " ";
					}

					log.writeToLog("LM_INFO", propNames[x] + " : " + propStr);
				} else {
					throw new Exception("Properties file returned null from value : " + propNames[x]);
				}
			} catch (Exception e) {
				log.writeToLog("LM_ERROR", "Required property was missing from the properties file");
				log.writeToLog("LM_ERROR", "Properties file returned null from value : " + propNames[x]);
				throw new CriticalException("Missing required values from properties file");
			}
		}
	}

	private String resolveString(String propStr) throws CriticalException {
		String resolvedProp = "";
		String splitProp[] = propStr.split("\\|");
		for (int i = 0; i < splitProp.length; i++) {
			String part = splitProp[i];

			if (part.startsWith("$")) {
				String sysEnv = System.getenv(part.substring(1));
				if (sysEnv != null) {
					resolvedProp += sysEnv;
				} else {
					log.writeToLog("LM_ERROR", "Cant find environment variable : " + part);
					throw new CriticalException("Could not find required enviroment variable");
				}
			} else if (part != null) {
				resolvedProp += part;
			}
		}
		log.writeToLog("LM_DEBUG", "Resolved string  : " + propStr);
		log.writeToLog("LM_DEBUG", "To string        : " + resolvedProp);
		return resolvedProp;
	}

	public String getProp(String propName) throws CriticalException {

		if (propMap.get(propName).equals(null)) {
			log.writeToLog("LM_ERROR", "Cannot find property from MAP " + propName);
			throw new CriticalException("Property not found in list of available properties");
		} else {
			return propMap.get(propName);
		}
	}

}
