//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.01.14 at 12:29:50 AM CET 
//


package com.bir.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DaneKomunikatResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "daneKomunikatResult"
})
@XmlRootElement(name = "DaneKomunikatResponse")
public class DaneKomunikatResponse {

    @XmlElement(name = "DaneKomunikatResult", nillable = true)
    protected String daneKomunikatResult;

    /**
     * Gets the value of the daneKomunikatResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaneKomunikatResult() {
        return daneKomunikatResult;
    }

    /**
     * Sets the value of the daneKomunikatResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaneKomunikatResult(String value) {
        this.daneKomunikatResult = value;
    }

}
