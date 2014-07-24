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

package com.jaspersoft.jasperserver.api.engine.scheduling.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb.ReportJobTriggerIntervalUnitXmlAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

/**
 * Job trigger which fires at fixed time intervals.
 * 
 * <p>
 * Such triggers can be used for jobs that need to fire only once at a specified
 * moment, or for jobs that need to fire several times at fixed intervals.
 * The intervals can be specified in minutes, hours, days (equivalent to 24 hours)
 * and weeks (equivalend to 7 days).
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobSimpleTrigger.java 35226 2013-08-09 07:08:53Z inesterenko $
 * @since 1.0
 */
@JasperServerAPI
@XmlRootElement(name = "simpleTrigger")
public class ReportJobSimpleTrigger extends ReportJobTrigger implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Minute unit for the trigger recurrence interval.
	 * 
	 * @see #getRecurrenceIntervalUnit()
	 */
	public static final byte INTERVAL_MINUTE = 1;

	/**
	 * Hour unit for the trigger recurrence interval.
	 * 
	 * @see #getRecurrenceIntervalUnit()
	 */
	public static final byte INTERVAL_HOUR = 2;

	/**
	 * Day unit for the trigger recurrence interval.
	 * 
	 * <p>
	 * A day is equivalent to 24 hours.
	 * Daylight saving time does not affect the job recurrence, therefore a job
	 * that recurs at an interval specified in days can occur at different hours
	 * after a DST hour shift.
	 * </p>
	 * 
	 * @see #getRecurrenceIntervalUnit()
	 */
	public static final byte INTERVAL_DAY = 3;

	/**
	 * Week unit for the trigger recurrence interval.
	 * 
	 * <p>
	 * A week is equivalent to 7 days and 168 hours.
	 * </p>
	 * 
	 * @see #getRecurrenceIntervalUnit()
	 * @see #INTERVAL_DAY
	 */
	public static final byte INTERVAL_WEEK = 4;

	/**
	 * Occurrence count constant which cause the job to fire indefinitely, or
	 * until the trigger end date if set.
	 * 
	 * @see #getOccurrenceCount()
	 * @see ReportJobTrigger#getEndDate()
	 */
	public static final int RECUR_INDEFINITELY = -1;
  
  public static final int JS_MISFIRE_INSTRUCTION_FIRE_NOW = 1;
  public static final int JS_MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT=5;
  public static final int JS_MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT=4;
  public static final int JS_MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT=2;
  public static final int JS_MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT=3;

	private int occurrenceCount;
	private Integer recurrenceInterval;
	private Byte recurrenceIntervalUnit;
	
	/**
	 * Create an empty simple job trigger;
	 */
	public ReportJobSimpleTrigger() {
	}

	/**
	 * Determines how many times the trigger will fire.
	 * 
	 * @return the trigger occurence count
	 * @see #RECUR_INDEFINITELY
	 */
	public int getOccurrenceCount() {
		return occurrenceCount;
	}

	/**
	 * Specifies how many times the trigger will fire.
	 * 
	 * <p>
	 * If the job should be executed once, <code>1</code> should be used.
	 * If the job should recur indefinitely, {@link #RECUR_INDEFINITELY} should
	 * be used.
	 * </p>
	 * 
	 * <p>
	 * If the trigger has an end date, it will cease to trigger when the end
	 * date is reached even if it has fired less times than the occurrence
	 * count. 
	 * </p>
	 * 
	 * @param recurrenceCount how many times the job should occur
	 * @see ReportJobTrigger#getEndDate()
	 */
	public void setOccurrenceCount(int recurrenceCount) {
		this.occurrenceCount = recurrenceCount;
	}

	/**
	 * Returns the unit in which the trigger recurrence interval should be 
	 * interpreted.
	 * 
	 * @return the trigger recurrence interval unit as one of the 
	 * <code>INTERVAL_*</code> constants, or <code>null</code> if no recurrence
	 * set
	 * 
	 * @see #getRecurrenceInterval()
	 */
    @XmlElement
    @XmlJavaTypeAdapter(ReportJobTriggerIntervalUnitXmlAdapter.class)
	public Byte getRecurrenceIntervalUnit() {
		return recurrenceIntervalUnit;
	}

	/**
	 * Specifies the unit in which the recurrence interval is defined.
	 * 
	 * @param recurrenceInterval the unit in which the recurrence interval is 
	 * defined, as one of the <code>INTERVAL_*</code> constants
	 * @see #INTERVAL_MINUTE
     * @see #INTERVAL_HOUR
     * @see #INTERVAL_DAY
     * @see #INTERVAL_WEEK
	 */
	public void setRecurrenceIntervalUnit(Byte recurrenceInterval) {
		this.recurrenceIntervalUnit = recurrenceInterval;
	}

	/**
	 * Returns the time interval at which the trigger should fire.
	 * The interval unit is provided separately.
	 * 
	 * @return the job recurrence time interval
	 * @see #getRecurrenceIntervalUnit()
	 */
	public Integer getRecurrenceInterval() {
		return recurrenceInterval;
	}

	/**
	 * Sets the length of the time interval at which the trigger should fire.
	 * The interval unit should be set via an additional call to
	 * <code>setRecurrenceIntervalUnit(byte)</code>.
	 * 
	 * @param recurrenceInterval the job recurrence time interval
	 * @see #setRecurrenceIntervalUnit(Byte)
	 */
	public void setRecurrenceInterval(Integer recurrenceInterval) {
		this.recurrenceInterval = recurrenceInterval;
	}

    /**
     * Compares two ReportJob triggers.
     *
     *
     * @param rjt the ReportJob trigger wich compares to this one
     * @return <code>true</code> if both triggers are equal
     */
    @Override
    public boolean equals(ReportJobTrigger rjt) {
        ReportJobSimpleTrigger rjst;
        try {
            rjst = (ReportJobSimpleTrigger) rjt;
        } catch (ClassCastException e) {
            // obviously we have different triggers if they are different classes
            return false;
        }
        return
            super.equals(rjt) &&
            this.occurrenceCount == rjst.getOccurrenceCount() &&
            ((this.recurrenceInterval == null && rjst.getRecurrenceInterval() == null) ||
                    (this.recurrenceInterval != null && rjst.getRecurrenceInterval() != null && this.recurrenceInterval.equals(rjst.getRecurrenceInterval()))) &&
            ((this.recurrenceIntervalUnit == null && rjst.getRecurrenceIntervalUnit() == null) ||
                    (this.recurrenceIntervalUnit != null && rjst.getRecurrenceIntervalUnit() != null && this.recurrenceIntervalUnit.equals(rjst.getRecurrenceIntervalUnit())));
    }

  /**
   * method to translate JS Misfire Code to Quartz Misfire Code.
   *
   * This is necessary because the JS DB value for migrated apps on Upgrade == '0',
   * conflicts with the Quartz value of:  SimpleTrigger.MISFIRE_INSTRUCTION_SMART_POLICY == '0'
   *
   * @return
   */
  /*  moved to ReportJobQuartzScheduler where it belongs
  public int getQuartzMisfireCode() {
    int i = getMisfireInstruction();
    if (i == JS_MISFIRE_INSTRUCTION_FIRE_NOW) return SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW;
    if (i == JS_MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT) return SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT;
    if (i == JS_MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT) return SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT;
    if (i == JS_MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT) return SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT;
    if (i == JS_MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT) return SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT;
    return super.getQuartzMisfireCode();
  }
  */

  /**
   * Convenience constructor that returns a distinct copy of the input ReportJobSource.
   * All of the copy's Object members are themselves copies as well.
   *
   * We're deliberately avoiding using clone()
   */
  public ReportJobSimpleTrigger(ReportJobSimpleTrigger jobTrigger) {
    super(jobTrigger);
    this.setOccurrenceCount(jobTrigger.getOccurrenceCount());
    this.setRecurrenceIntervalUnit(jobTrigger.getRecurrenceIntervalUnit());
    this.setRecurrenceInterval(jobTrigger.getRecurrenceInterval());
  }

}
