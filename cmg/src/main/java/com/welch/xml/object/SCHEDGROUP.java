//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.10 at 07:29:59 PM CST 
//


package com.welch.xml.object;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 */
@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tagOrINCONDOrOUTCONDOrCONTROLOrAUTOEDITOrONGROUPOrSHOUT",
    "job"
})
@XmlRootElement(name = "SCHED_GROUP")
public class SCHEDGROUP {

    @XmlAttribute(name = "DATACENTER", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String datacenter;
    @XmlAttribute(name = "TABLE_NAME", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String tablename;
    @XmlAttribute(name = "TABLE_DSN")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String tabledsn;
    @XmlAttribute(name = "TABLE_USERDAILY")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String tableuserdaily;
    @XmlAttribute(name = "GROUP", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String group;
    @XmlAttribute(name = "JOBNAME")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String jobname;
    @XmlAttribute(name = "APPLICATION")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String application;
    @XmlAttribute(name = "MEMNAME")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String memname;
    @XmlAttribute(name = "OWNER")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String owner;
    @XmlAttribute(name = "ADJUST_COND")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String adjustcond;
    @XmlAttribute(name = "CONFIRM")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String confirm;
    @XmlAttribute(name = "PRIORITY")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String priority;
    @XmlAttribute(name = "TIMEFROM")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String timefrom;
    @XmlAttribute(name = "TIMETO")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String timeto;
    @XmlAttribute(name = "TIMEZONE")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String timezone;
    @XmlAttribute(name = "DUE_OUT")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dueout;
    @XmlAttribute(name = "DOCMEM")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String docmem;
    @XmlAttribute(name = "DOCLIB")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String doclib;
    @XmlAttribute(name = "DESCRIPTION")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String description;
    @XmlAttribute(name = "AUTHOR")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String author;
    @XmlAttribute(name = "USED_BY")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String usedby;
    @XmlAttribute(name = "USED_BY_CODE")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String usedbycode;
    @XmlAttribute(name = "MODIFIED")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String modified;
    @XmlAttribute(name = "LAST_UPLOAD")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String lastupload;
    @XmlAttribute(name = "CHECKSUM")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String checksum;
    @XmlAttribute(name = "TABLE_ID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String tableid;
    @XmlAttribute(name = "REAL_TABLEID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String realtableid;
    @XmlAttribute(name = "CREATION_USER")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String creationuser;
    @XmlAttribute(name = "CREATION_DATE")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String creationdate;
    @XmlAttribute(name = "CREATION_TIME")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String creationtime;
    @XmlAttribute(name = "CHANGE_USERID")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String changeuserid;
    @XmlAttribute(name = "CHANGE_DATE")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String changedate;
    @XmlAttribute(name = "CHANGE_TIME")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String changetime;
    @XmlAttribute(name = "MULTY_AGENT")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String multyagent;
    @XmlAttribute(name = "ACTIVE_FROM")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String activefrom;
    @XmlAttribute(name = "ACTIVE_TILL")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String activetill;
    @XmlAttribute(name = "JOBISN")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String jobisn;
    @XmlAttribute(name = "MAXWAIT")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String maxwait;
    @XmlAttribute(name = "STAT_CAL")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String statcal;
    @XmlAttribute(name = "DUE_OUT_DAYSOFFSET")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dueoutdaysoffset;
    @XmlAttribute(name = "FROM_DAYSOFFSET")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String fromdaysoffset;
    @XmlAttribute(name = "TO_DAYSOFFSET")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String todaysoffset;
    @XmlAttribute(name = "PREV_DAY")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String prevday;
    @XmlAttribute(name = "CRITICAL")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String critical;
    @XmlElements({
        @XmlElement(name = "TAG", type = TAG.class),
        @XmlElement(name = "INCOND", type = INCOND.class),
        @XmlElement(name = "OUTCOND", type = OUTCOND.class),
        @XmlElement(name = "CONTROL", type = CONTROL.class),
        @XmlElement(name = "AUTOEDIT", type = AUTOEDIT.class),
        @XmlElement(name = "ON_GROUP", type = ONGROUP.class),
        @XmlElement(name = "SHOUT", type = SHOUT.class)
    })
    protected List<Object> tagOrINCONDOrOUTCONDOrCONTROLOrAUTOEDITOrONGROUPOrSHOUT;
    @XmlElement(name = "JOB")
    protected List<JOB> job;

    /**
     * Gets the value of the datacenter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATACENTER() {
        return datacenter;
    }

    /**
     * Sets the value of the datacenter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATACENTER(String value) {
        this.datacenter = value;
    }

    /**
     * Gets the value of the tablename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTABLENAME() {
        return tablename;
    }

    /**
     * Sets the value of the tablename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTABLENAME(String value) {
        this.tablename = value;
    }

    /**
     * Gets the value of the tabledsn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTABLEDSN() {
        return tabledsn;
    }

    /**
     * Sets the value of the tabledsn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTABLEDSN(String value) {
        this.tabledsn = value;
    }

    /**
     * Gets the value of the tableuserdaily property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTABLEUSERDAILY() {
        return tableuserdaily;
    }

    /**
     * Sets the value of the tableuserdaily property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTABLEUSERDAILY(String value) {
        this.tableuserdaily = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGROUP() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGROUP(String value) {
        this.group = value;
    }

    /**
     * Gets the value of the jobname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJOBNAME() {
        return jobname;
    }

    /**
     * Sets the value of the jobname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJOBNAME(String value) {
        this.jobname = value;
    }

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPPLICATION() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPPLICATION(String value) {
        this.application = value;
    }

    /**
     * Gets the value of the memname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMEMNAME() {
        return memname;
    }

    /**
     * Sets the value of the memname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMEMNAME(String value) {
        this.memname = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOWNER() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOWNER(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the adjustcond property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADJUSTCOND() {
        if (adjustcond == null) {
            return "N";
        } else {
            return adjustcond;
        }
    }

    /**
     * Sets the value of the adjustcond property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADJUSTCOND(String value) {
        this.adjustcond = value;
    }

    /**
     * Gets the value of the confirm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONFIRM() {
        if (confirm == null) {
            return "0";
        } else {
            return confirm;
        }
    }

    /**
     * Sets the value of the confirm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONFIRM(String value) {
        this.confirm = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRIORITY() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRIORITY(String value) {
        this.priority = value;
    }

    /**
     * Gets the value of the timefrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIMEFROM() {
        return timefrom;
    }

    /**
     * Sets the value of the timefrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIMEFROM(String value) {
        this.timefrom = value;
    }

    /**
     * Gets the value of the timeto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIMETO() {
        return timeto;
    }

    /**
     * Sets the value of the timeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIMETO(String value) {
        this.timeto = value;
    }

    /**
     * Gets the value of the timezone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIMEZONE() {
        return timezone;
    }

    /**
     * Sets the value of the timezone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIMEZONE(String value) {
        this.timezone = value;
    }

    /**
     * Gets the value of the dueout property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDUEOUT() {
        return dueout;
    }

    /**
     * Sets the value of the dueout property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDUEOUT(String value) {
        this.dueout = value;
    }

    /**
     * Gets the value of the docmem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCMEM() {
        return docmem;
    }

    /**
     * Sets the value of the docmem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCMEM(String value) {
        this.docmem = value;
    }

    /**
     * Gets the value of the doclib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCLIB() {
        return doclib;
    }

    /**
     * Sets the value of the doclib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCLIB(String value) {
        this.doclib = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCRIPTION() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCRIPTION(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUTHOR() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUTHOR(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the usedby property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSEDBY() {
        return usedby;
    }

    /**
     * Sets the value of the usedby property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSEDBY(String value) {
        this.usedby = value;
    }

    /**
     * Gets the value of the usedbycode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSEDBYCODE() {
        return usedbycode;
    }

    /**
     * Sets the value of the usedbycode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSEDBYCODE(String value) {
        this.usedbycode = value;
    }

    /**
     * Gets the value of the modified property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMODIFIED() {
        return modified;
    }

    /**
     * Sets the value of the modified property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMODIFIED(String value) {
        this.modified = value;
    }

    /**
     * Gets the value of the lastupload property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLASTUPLOAD() {
        return lastupload;
    }

    /**
     * Sets the value of the lastupload property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLASTUPLOAD(String value) {
        this.lastupload = value;
    }

    /**
     * Gets the value of the checksum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHECKSUM() {
        return checksum;
    }

    /**
     * Sets the value of the checksum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHECKSUM(String value) {
        this.checksum = value;
    }

    /**
     * Gets the value of the tableid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTABLEID() {
        return tableid;
    }

    /**
     * Sets the value of the tableid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTABLEID(String value) {
        this.tableid = value;
    }

    /**
     * Gets the value of the realtableid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREALTABLEID() {
        return realtableid;
    }

    /**
     * Sets the value of the realtableid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREALTABLEID(String value) {
        this.realtableid = value;
    }

    /**
     * Gets the value of the creationuser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCREATIONUSER() {
        return creationuser;
    }

    /**
     * Sets the value of the creationuser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCREATIONUSER(String value) {
        this.creationuser = value;
    }

    /**
     * Gets the value of the creationdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCREATIONDATE() {
        if (creationdate == null) {
            return "EMPTY";
        } else {
            return creationdate;
        }
    }

    /**
     * Sets the value of the creationdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCREATIONDATE(String value) {
        this.creationdate = value;
    }

    /**
     * Gets the value of the creationtime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCREATIONTIME() {
        if (creationtime == null) {
            return "EMPTY";
        } else {
            return creationtime;
        }
    }

    /**
     * Sets the value of the creationtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCREATIONTIME(String value) {
        this.creationtime = value;
    }

    /**
     * Gets the value of the changeuserid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHANGEUSERID() {
        return changeuserid;
    }

    /**
     * Sets the value of the changeuserid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHANGEUSERID(String value) {
        this.changeuserid = value;
    }

    /**
     * Gets the value of the changedate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHANGEDATE() {
        return changedate;
    }

    /**
     * Sets the value of the changedate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHANGEDATE(String value) {
        this.changedate = value;
    }

    /**
     * Gets the value of the changetime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCHANGETIME() {
        return changetime;
    }

    /**
     * Sets the value of the changetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCHANGETIME(String value) {
        this.changetime = value;
    }

    /**
     * Gets the value of the multyagent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMULTYAGENT() {
        return multyagent;
    }

    /**
     * Sets the value of the multyagent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMULTYAGENT(String value) {
        this.multyagent = value;
    }

    /**
     * Gets the value of the activefrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACTIVEFROM() {
        return activefrom;
    }

    /**
     * Sets the value of the activefrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTIVEFROM(String value) {
        this.activefrom = value;
    }

    /**
     * Gets the value of the activetill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACTIVETILL() {
        return activetill;
    }

    /**
     * Sets the value of the activetill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTIVETILL(String value) {
        this.activetill = value;
    }

    /**
     * Gets the value of the jobisn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJOBISN() {
        return jobisn;
    }

    /**
     * Sets the value of the jobisn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJOBISN(String value) {
        this.jobisn = value;
    }

    /**
     * Gets the value of the maxwait property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMAXWAIT() {
        return maxwait;
    }

    /**
     * Sets the value of the maxwait property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMAXWAIT(String value) {
        this.maxwait = value;
    }

    /**
     * Gets the value of the statcal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTATCAL() {
        return statcal;
    }

    /**
     * Sets the value of the statcal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTATCAL(String value) {
        this.statcal = value;
    }

    /**
     * Gets the value of the dueoutdaysoffset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDUEOUTDAYSOFFSET() {
        return dueoutdaysoffset;
    }

    /**
     * Sets the value of the dueoutdaysoffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDUEOUTDAYSOFFSET(String value) {
        this.dueoutdaysoffset = value;
    }

    /**
     * Gets the value of the fromdaysoffset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFROMDAYSOFFSET() {
        return fromdaysoffset;
    }

    /**
     * Sets the value of the fromdaysoffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFROMDAYSOFFSET(String value) {
        this.fromdaysoffset = value;
    }

    /**
     * Gets the value of the todaysoffset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTODAYSOFFSET() {
        return todaysoffset;
    }

    /**
     * Sets the value of the todaysoffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTODAYSOFFSET(String value) {
        this.todaysoffset = value;
    }

    /**
     * Gets the value of the prevday property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPREVDAY() {
        return prevday;
    }

    /**
     * Sets the value of the prevday property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPREVDAY(String value) {
        this.prevday = value;
    }

    /**
     * Gets the value of the critical property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCRITICAL() {
        if (critical == null) {
            return "0";
        } else {
            return critical;
        }
    }

    /**
     * Sets the value of the critical property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCRITICAL(String value) {
        this.critical = value;
    }

    /**
     * Gets the value of the tagOrINCONDOrOUTCONDOrCONTROLOrAUTOEDITOrONGROUPOrSHOUT property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tagOrINCONDOrOUTCONDOrCONTROLOrAUTOEDITOrONGROUPOrSHOUT property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTAGOrINCONDOrOUTCONDOrCONTROLOrAUTOEDITOrONGROUPOrSHOUT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TAG }
     * {@link INCOND }
     * {@link OUTCOND }
     * {@link CONTROL }
     * {@link AUTOEDIT }
     * {@link ONGROUP }
     * {@link SHOUT }
     * 
     * 
     */
    public List<Object> getTAGOrINCONDOrOUTCONDOrCONTROLOrAUTOEDITOrONGROUPOrSHOUT() {
        if (tagOrINCONDOrOUTCONDOrCONTROLOrAUTOEDITOrONGROUPOrSHOUT == null) {
            tagOrINCONDOrOUTCONDOrCONTROLOrAUTOEDITOrONGROUPOrSHOUT = new ArrayList<Object>();
        }
        return this.tagOrINCONDOrOUTCONDOrCONTROLOrAUTOEDITOrONGROUPOrSHOUT;
    }

    /**
     * Gets the value of the job property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the job property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJOB().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JOB }
     * 
     * 
     */
    public List<JOB> getJOB() {
        if (job == null) {
            job = new ArrayList<JOB>();
        }
        return this.job;
    }

}
