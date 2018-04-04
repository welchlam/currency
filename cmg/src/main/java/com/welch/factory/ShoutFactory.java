package com.welch.factory;

import com.welch.db.SQLHelper;
import com.welch.exception.CriticalException;
import com.welch.exception.NoResultException;
import com.welch.util.JavaLogger;
import com.welch.util.StringHelper;
import com.welch.xml.object.JOB;
import com.welch.xml.object.SHOUT;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class ShoutFactory {
	private JavaLogger log = null;
	private SQLHelper sqlHelper = null;
	private String sql = "Select * From Ctlm_V_Shout Where Action_Detail_Id Is Null order by job_name";
	private Hashtable<String, Hashtable<String, SHOUT>> shouts = null;
	
	public ShoutFactory(JavaLogger log, SQLHelper sqlHelper){
		this.log = log;
		this.sqlHelper = sqlHelper;
		this.shouts = new Hashtable<String, Hashtable<String, SHOUT>>();
		this.getShouts();
	}
	
	private void getShouts(){
		ResultSet rs = null;
		try {
			rs = this.sqlHelper.execMultiSelect(this.sql);
			int i=1;
			do{
				SHOUT shout = new SHOUT();
				shout.setDEST(StringHelper.convertNull(rs.getString("DEST")));
				shout.setMESSAGE(StringHelper.convertNull(rs.getString("MESSAGE")));
				shout.setTIME(StringHelper.convertNull(rs.getString("TIME")));
				shout.setURGENCY(StringHelper.convertNull(rs.getString("URGENCY")));
				shout.setWHEN(StringHelper.convertNull(rs.getString("WHEN")));
				if(this.shouts.get(rs.getString("JOB_NAME"))==null){
					this.shouts.put(rs.getString("JOB_NAME"), new Hashtable<String,SHOUT>());
				}
				this.shouts.get(rs.getString("JOB_NAME")).put(i+"", shout);
				i++;
			}while(rs.next());
		} catch (CriticalException e) {
			log.writeToLog("LM_ERROR", e.getMessage());
		} catch (NoResultException e) {
			//log.writeToLog("LM_INFOR", "No shouts are found, maybe OK");
		} catch (SQLException e){
			log.writeToLog("LM_ERROR", "SQL error: "+this.sql);
			log.writeToLog("LM_ERROR", e.getMessage());
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				log.writeToLog("LM_ERROR", "Failed to close result set");
				log.writeToLog("LM_ERROR", e.getMessage());
			}
		}
	}
	
	public void addShouts(JOB job){
		if(this.shouts.get(job.getJOBNAME())==null){
			//log.writeToLog("LM_INFOR", "No shouts are found for job "+job.getJOBNAME());
		}else{
			for(String key: this.shouts.get(job.getJOBNAME()).keySet()){
				job.getTAGNAMESOrRULEBASEDCALENDARSOrINCONDOrOUTCONDOrAUTOEDITOrQUANTITATIVEOrCONTROLOrSHOUTOrSTEPRANGEOrONOrAUTOEDIT2().add(this.shouts.get(job.getJOBNAME()).get(key));
			}
		}
	}
}
