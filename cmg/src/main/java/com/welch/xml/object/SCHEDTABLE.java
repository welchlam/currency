//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.10 at 07:29:59 PM CST 
//


package com.welch.xml.object;

import javax.xml.bind.annotation.*;
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
    "job"
})
@XmlRootElement(name = "SCHED_TABLE")
public class SCHEDTABLE {

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