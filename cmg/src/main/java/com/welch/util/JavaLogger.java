package com.welch.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class JavaLogger
{
	private String logPath;
	private String logMsgToWrite;
	private BufferedWriter out;
	private int stackNumber = 2;
	private boolean printToScreenBool = false;

	public JavaLogger(String logName)
	{
		this.logPath = getLogPath(logName);
	}

	/*
	 * Get the full path of where the log id to be written to
	 */
	private String getLogPath(String logName)
	{
		Map<String, String> env = System.getenv();
		String MAH_LOGS = env.get("MAH_LOGS");

		if (MAH_LOGS != null)
		{
			String homePath = (MAH_LOGS+logName+".log");
			System.out.println("Set to path :/"+ homePath);
			return homePath;
		}
		else
		{
			String defualtPath = logName+".log";
			System.out.println("Cant find Environment Variable  : MAH_LOGS");
			System.out.println("Set to defualt application path :/"+ defualtPath);
			return defualtPath;
		}
	}

	public void setStackNumber(int i)
	{
		stackNumber = i;
	}

	/*
	 * Sets the shorthand of each type of log message. In this format as it is similar to
	 * the logs used in the UNIX scripts.
	 */
	private String initaliseMsgType(String logType)
	{
		String shortLogType = null;
		if(logType.equals("LM_WARN"))
		{
			printToScreenBool = true;
			shortLogType = "WRN";
		}
		else if(logType.equals("LM_ERROR"))
		{
			printToScreenBool = true;
			shortLogType = "ERR";
		}
		else if(logType.equals("LM_DEBUG"))
		{
			printToScreenBool = false;
			shortLogType = "DBG";
		}
		else if(logType.equals("LM_SQL"))
		{
			printToScreenBool = false;
			shortLogType = "SQL";
		}
		else
		{
			printToScreenBool = true;
			shortLogType = "INF";
		}

	return shortLogType;
	}

	/*
	 * Gather information about the message to append to the log.
	 */
	public void writeToLog(String logType, String msg)
	{
		String dateTime = getDateTime();
		String processId = UtilsCollection.getProcessId();
		String userName = getUserName();
		String classMethod = getCallingClassMethod();
		String shortLogType = initaliseMsgType(logType);
		String logMessage = msg;

		logMsgToWrite = dateTime +", "+ shortLogType +", "+ processId +", "+ userName +", "+ classMethod +": "+ logMessage;
		printToScreen(msg);
		writeToFile(logMsgToWrite);
	}

	/*
	 * If the type of message is set to print to screen then do so.
	 */
	private void printToScreen(String msg)
	{
		if (printToScreenBool)
		{
			System.out.println(" " + msg);
		}
	}

	/*
	 * Get a time stamp for the message.
	 */
	private String getDateTime()
	{
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
	}

	/*
	 * Get the name of the UNIX user running the logs.
	 */
	private String getUserName()
	{
		return System.getProperty("user.name");
	}

	/*
	 * Get the name of the method which called the log.
	 */
	private String getCallingClassMethod()
	{
		String callingClass = null;
		String callingMethod = null;
		try
		{
			throw new Exception("Who Called Me");
		}
		catch( Exception e )
		{
			callingClass =  e.getStackTrace()[stackNumber].getClassName();
			callingMethod = e.getStackTrace()[stackNumber].getMethodName();
		}
		return (callingClass+"."+callingMethod);
	}

	/*
	 * Write the built message to the logs.
	 */
	private void writeToFile(String msg)
	{
		try
		{
			// Create file
			FileWriter fstream = new FileWriter(logPath,true);
			out = new BufferedWriter(fstream);
			out.write(logMsgToWrite);
			out.newLine();
			out.flush();
		}
		catch (Exception e)
		{
			System.out.println("Failed to write to log file");
			System.out.println("Error: " + e.getMessage());
		}
	}

	protected void finalize()
	{
		try
		{
			out.flush();
			out.close();
		}
		catch(Exception e)
		{
			System.out.println("Failed to close connection to log file");
			System.out.println("Error: " + e.getStackTrace());
		}
	}
}
