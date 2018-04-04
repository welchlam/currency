package com.welch.main;

import com.welch.util.JavaLogger;
import com.welch.writer.XmlGenerator;
import com.welch.writer.XmlGeneratorCreator;

public class Main {
	public static JavaLogger logger;
	public String logFile = "ctmGenerator";

	//GUI xml file generation
	public String propFile;
	public String userId;
	public String dbName;
	public boolean testModel; //for both GUI & Local
	public String envName;
	public String fullDelta;
	public String xmlFile;
	public String isAllGroups;
	public String groups;

	//Local xml file generation
	public String envType; //LOCAL or GUI, for both GUI & Local
	public String oraHost;
	public String oraSid;
	public String oraPort;
	public String oraUser;
	public String oraPwd;
	public String outputFile;

	public Main(String parameters) {
		logger = new JavaLogger(this.logFile);
		this.processParameters(parameters);
	}

	public void generateXML() {
		XmlGenerator xmlGenerator = XmlGeneratorCreator.createXmlGenerator(this);
		xmlGenerator.generateXml();
	}
	
	private void processParameters(String parameters){
		this.propFile = this.getParameterValue(parameters, "config");
		this.userId = this.getParameterValue(parameters, "userId");
		this.dbName = this.getParameterValue(parameters, "selectedDB");
		this.testModel = this.getParameterValue(parameters, "fileMode")!=null && this.getParameterValue(parameters, "fileMode").equalsIgnoreCase("Test");
		this.envName = this.getParameterValue(parameters, "targetEnv");
		this.fullDelta = this.getParameterValue(parameters, "fullDelta");
		this.xmlFile = this.getParameterValue(parameters, "xmlFile");
		this.isAllGroups = this.getParameterValue(parameters, "allGroups");
		this.groups = this.convertString(this.getParameterValue(parameters, "groups"), ",");

		this.envType = this.getParameterValue(parameters, "envType");
		this.oraHost = this.getParameterValue(parameters, "oraHost");
		this.oraSid = this.getParameterValue(parameters, "oraSid");
		this.oraPort = this.getParameterValue(parameters, "oraPort");
		this.oraUser = this.getParameterValue(parameters, "oraUser");
		this.oraPwd = this.getParameterValue(parameters, "oraPwd");
		this.outputFile = this.getParameterValue(parameters, "outputFile");
	}
	
	private String convertString(String str, String separator){
		String value="''";
		if(str==null){
			return null;
		}
		String[] values = str.split(separator);
		for(int i=0; i<values.length; i++){
			value = value+",'"+values[i]+"'";
		}
		return value;
	}
	
	private String getParameterValue(String parameters, String key){
		String[] parameterArray = parameters.split("\\|");
		for(int i=0; i<parameterArray.length; i++){
			String[] keyValue = parameterArray[i].split("=");
			if(key.equals(keyValue[0]) && keyValue.length>1){
				return keyValue[1];
			}
		}
		return null;
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Main main = new Main(args[0]);
		main.generateXML();
		long endTime = System.currentTimeMillis();
		System.out.println("Draft generated. Total time elapse in Second: --> " + (endTime - startTime) / 1000);
	}
}
