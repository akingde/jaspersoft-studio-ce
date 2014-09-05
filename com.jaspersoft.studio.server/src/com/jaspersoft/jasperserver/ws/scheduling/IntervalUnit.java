/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/


package com.jaspersoft.jasperserver.ws.scheduling;

import com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb.ReportJobTriggerIntervalUnitXmlAdapter;

/**
 * IntervalUnit.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */
public class IntervalUnit implements java.io.Serializable {
    private java.lang.String value;
    private static java.util.HashMap table = new java.util.HashMap();

    // Constructor
    protected IntervalUnit(java.lang.String value) {
        this.value = value;
        table.put(this.value,this);
    }

    protected IntervalUnit() {

    }

    public static final java.lang.String _MINUTE = ReportJobTriggerIntervalUnitXmlAdapter.IntervalUnit.MINUTE.name();
    public static final java.lang.String _HOUR = ReportJobTriggerIntervalUnitXmlAdapter.IntervalUnit.HOUR.name();
    public static final java.lang.String _DAY = ReportJobTriggerIntervalUnitXmlAdapter.IntervalUnit.DAY.name();
    public static final java.lang.String _WEEK = ReportJobTriggerIntervalUnitXmlAdapter.IntervalUnit.WEEK.name();
    public static final IntervalUnit MINUTE = new IntervalUnit(_MINUTE);
    public static final IntervalUnit HOUR = new IntervalUnit(_HOUR);
    public static final IntervalUnit DAY = new IntervalUnit(_DAY);
    public static final IntervalUnit WEEK = new IntervalUnit(_WEEK);

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static IntervalUnit fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        IntervalUnit enumeration = (IntervalUnit)
            table.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static IntervalUnit fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return value;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(value);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntervalUnit.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.jasperforge.org/jasperserver/ws", "IntervalUnit"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
