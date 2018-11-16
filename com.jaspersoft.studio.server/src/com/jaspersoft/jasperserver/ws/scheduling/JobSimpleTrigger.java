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

/**
 * JobSimpleTrigger.java
 *
 * This file was auto-generated from WSDL by the Apache Axis 1.3 Oct 05, 2005
 * (05:23:37 EDT) WSDL2Java emitter.
 */
public class JobSimpleTrigger extends com.jaspersoft.jasperserver.ws.scheduling.JobTrigger
		implements java.io.Serializable {
	private int occurrenceCount;

	private java.lang.Integer recurrenceInterval;

	private com.jaspersoft.jasperserver.ws.scheduling.IntervalUnit recurrenceIntervalUnit;

	public JobSimpleTrigger() {
	}

	public JobSimpleTrigger(long id, int version, java.lang.String timezone, java.util.Calendar startDate,
			java.util.Calendar endDate, int occurrenceCount, java.lang.Integer recurrenceInterval,
			com.jaspersoft.jasperserver.ws.scheduling.IntervalUnit recurrenceIntervalUnit) {
		super(id, version, timezone, startDate, endDate);
		this.occurrenceCount = occurrenceCount;
		this.recurrenceInterval = recurrenceInterval;
		this.recurrenceIntervalUnit = recurrenceIntervalUnit;
	}

	/**
	 * Gets the occurrenceCount value for this JobSimpleTrigger.
	 * 
	 * @return occurrenceCount
	 */
	public int getOccurrenceCount() {
		return occurrenceCount;
	}

	/**
	 * Sets the occurrenceCount value for this JobSimpleTrigger.
	 * 
	 * @param occurrenceCount
	 */
	public void setOccurrenceCount(int occurrenceCount) {
		this.occurrenceCount = occurrenceCount;
	}

	/**
	 * Gets the recurrenceInterval value for this JobSimpleTrigger.
	 * 
	 * @return recurrenceInterval
	 */
	public java.lang.Integer getRecurrenceInterval() {
		return recurrenceInterval;
	}

	/**
	 * Sets the recurrenceInterval value for this JobSimpleTrigger.
	 * 
	 * @param recurrenceInterval
	 */
	public void setRecurrenceInterval(java.lang.Integer recurrenceInterval) {
		this.recurrenceInterval = recurrenceInterval;
	}

	/**
	 * Gets the recurrenceIntervalUnit value for this JobSimpleTrigger.
	 * 
	 * @return recurrenceIntervalUnit
	 */
	public com.jaspersoft.jasperserver.ws.scheduling.IntervalUnit getRecurrenceIntervalUnit() {
		return recurrenceIntervalUnit;
	}

	/**
	 * Sets the recurrenceIntervalUnit value for this JobSimpleTrigger.
	 * 
	 * @param recurrenceIntervalUnit
	 */
	public void setRecurrenceIntervalUnit(
			com.jaspersoft.jasperserver.ws.scheduling.IntervalUnit recurrenceIntervalUnit) {
		this.recurrenceIntervalUnit = recurrenceIntervalUnit;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof JobSimpleTrigger))
			return false;
		JobSimpleTrigger other = (JobSimpleTrigger) obj;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = super.equals(obj) && this.occurrenceCount == other.getOccurrenceCount()
				&& ((this.recurrenceInterval == null && other.getRecurrenceInterval() == null)
						|| (this.recurrenceInterval != null
								&& this.recurrenceInterval.equals(other.getRecurrenceInterval())))
				&& ((this.recurrenceIntervalUnit == null && other.getRecurrenceIntervalUnit() == null)
						|| (this.recurrenceIntervalUnit != null
								&& this.recurrenceIntervalUnit.equals(other.getRecurrenceIntervalUnit())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = super.hashCode();
		_hashCode += getOccurrenceCount();
		if (getRecurrenceInterval() != null) {
			_hashCode += getRecurrenceInterval().hashCode();
		}
		if (getRecurrenceIntervalUnit() != null) {
			_hashCode += getRecurrenceIntervalUnit().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

}
