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


/**
 * 
 */
@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "TEMPLATE")
public class TEMPLATE {

    @XmlAttribute(name = "dc_platform")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dcPlatform;
    @XmlAttribute(name = "dc_version")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dcVersion;
    @XmlAttribute(name = "description")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String description;
    @XmlAttribute(name = "dc_name")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dcName;

    /**
     * Gets the value of the dcPlatform property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDcPlatform() {
        return dcPlatform;
    }

    /**
     * Sets the value of the dcPlatform property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDcPlatform(String value) {
        this.dcPlatform = value;
    }

    /**
     * Gets the value of the dcVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDcVersion() {
        return dcVersion;
    }

    /**
     * Sets the value of the dcVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDcVersion(String value) {
        this.dcVersion = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
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
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the dcName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDcName() {
        return dcName;
    }

    /**
     * Sets the value of the dcName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDcName(String value) {
        this.dcName = value;
    }

}