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
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb.DateToStringXmlAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

/**
 * Abstract base class for report job triggers.
 * 
 * <p>
 * Job triggers determine when the report job is going to fire.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobTrigger.java 38683 2013-10-09 02:39:26Z carbiv $
 * @see ReportJob#getTrigger()
 * @see ReportJobSimpleTrigger
 * @see ReportJobCalendarTrigger
 * @since 1.0
 */
@JasperServerAPI
public abstract class ReportJobTrigger implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Start type that indicates that the job should be scheduled to start
	 * immediately.
	 * 
	 * @see #getStartType()
	 */
	public static final byte START_TYPE_NOW = 1;
	
	/**
	 * Start type that indicates that the job should be scheduled to start
	 * at the specified start date.
	 * 
	 * @see #getStartType()
	 * @see #getStartDate()
	 */
	public static final byte START_TYPE_SCHEDULE = 2;

  public static final int JS_MISFIRE_INSTRUCTION_NOT_SET = 0;   // this is JS specific code for 'NO VALUE'.  Unfortunately it clashes with Quartz code for 'SMART_POLICY'
  public static final int JS_MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY = -1;
  public static final int JS_MISFIRE_INSTRUCTION_SMART_POLICY = -999;     // the Quartz value for this is '0', but we can't have that in JS


  private long id;
	private int version = ReportJob.VERSION_NEW;
	private String timezone;
    private String calendarName;
	private byte startType = START_TYPE_SCHEDULE;
	private Date startDate;
	private Date endDate;
  private int misfireInstruction =  JS_MISFIRE_INSTRUCTION_NOT_SET;
	
	/**
	 * Creates an empty trigger.
	 */
	public ReportJobTrigger() {
	}

	/**
	 * Returns the ID of the trigger.
	 * 
	 * <p>
	 * The ID is automatically generated when the job is scheduled.
	 * </p>
	 * 
	 * @return the ID of the trigger
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the ID of the trigger.
	 * 
	 * <p>
	 * The ID needs to be set when updating a report job, but not when saving
	 * a new job.
	 * </p>
	 * 
	 * @param id the trigger ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns the persistent version of this trigger.
	 * 
	 * <p>
	 * The version is automatically set when the trigger is retrieved
	 * (along with the job) from the scheduling service.
	 * </p>
	 * 
	 * @return the persistent trigger version 
	 * @see ReportJob#VERSION_NEW
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the persistent trigger version.
	 * 
	 * <p>
	 * The version doesn't have to be set when saving a new report job,
	 * but it needs to be set when updating a report job (along with its trigger).
	 * </p>
	 * 
	 * @param version the persistent trigger version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Returns the date at which the job should be scheduled to start.
	 * 
	 * <p>
	 * The start date can be <code>null</code> when the trigger start type
	 * is set to <code>START_TYPE_NOW</code>.
	 * </p>
	 * 
	 * @return the date at which the job is scheduled to start, or 
	 * <code>null</code> if the job is to start immediately
	 * @see #START_TYPE_SCHEDULE
	 */
    @XmlJavaTypeAdapter(DateToStringXmlAdapter.class)
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the date at which the report job should be scheduled to start.
	 * 
	 * <p>
	 * When setting a start date, the start type should also be set to
	 * <code>START_TYPE_SCHEDULE</code>.
	 * </p>
	 * 
	 * @param startDate the date at which the report job should start. 
	 * @see #getStartDate()
	 * @see #START_TYPE_SCHEDULE
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Determines whether the job should be scheduled to start immediately,
	 * or at the specified start date.
	 * 
	 * @return the job start type
	 * @see #START_TYPE_NOW
	 * @see #START_TYPE_SCHEDULE
	 */
	public byte getStartType() {
		return startType;
	}

	/**
	 * Specify whether the job should be scheduled to start immediately,
	 * or at the specified start date.
	 * 
	 * <p>
	 * The job start date is not necessarily the date of the first execution.
	 * For calendar triggers, it's the date at which the trigger becomes
	 * effective and starts firing at the specified calendar moments.
	 * </p>
	 * 
	 * @param startType one of {@link #START_TYPE_NOW} and 
	 * {@value #START_TYPE_SCHEDULE}
	 */
    @XmlElement
	public void setStartType(byte startType) {
		this.startType = startType;
	}

	/**
	 * Returns the date at which job trigger should stop firing executions.
	 * 
	 * @return the trigger end date, or <code>null</code> if none set 
	 */
    @XmlJavaTypeAdapter(DateToStringXmlAdapter.class)
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets a date at which the trigger should cease firing job executions.
	 * 
	 * <p>
	 * Once the end date is reached, the job will not longer fire and will
	 * automatically be deleted.
	 * </p>
	 * 
	 * @param endDate an end date for the job
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the timezone according to which the trigger date/time values
	 * are interpreted.
	 * 
	 *  <p>
	 *  If a timezone is not set for the trigger, the date/time values will be 
	 *  interpreted in the default server timezone.
	 *  </p>
	 * 
	 * @return the trigger timezone, or <code>null</code> if none set
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * Sets a timezone according to which trigger date/time values are
	 * interpreted.
	 * 
	 * @param timezone the trigger timezone 
	 */
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

    /**
	 * Get the name of the Calendar associated with this Trigger.
	 *
	 * @return null if there is no associated Calendar.
	 */
    public String getCalendarName() {
        return calendarName;
    }

    /**
	 * Associate the Calendar with the given name with this Trigger.
	 *
	 * @return null if there is no associated Calendar.
     * Specified by: setCalendarName in interface org.quartz.spi.MutableTrigger
     *
     * @param calendarName - use null to dis-associate a Calendar.
	 */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

  public int getMisfireInstruction() {
    return misfireInstruction;
  }
        
  public void setMisfireInstruction(int i) {
    this.misfireInstruction = i;
  }


  /**
   * method to translate JS Misfire Code to Quartz Misfire Code.
   *
   * This is necessary because the JS DB value for migrated apps on Upgrade == '0',
   * conflicts with the Quartz value of:  SimpleTrigger.MISFIRE_INSTRUCTION_SMART_POLICY == '0'
   *
   * @return
   */
  /*  2012-03-09 thorick:  moved to ReportJobsQuartzScheduler, where it belongs
  public int getQuartzMisfireCode() {
    int i = getMisfireInstruction();
    if (i == JS_MISFIRE_INSTRUCTION_NOT_SET) return SimpleTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY;
    if (i == JS_MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY) return SimpleTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY;
    if (i == JS_MISFIRE_INSTRUCTION_SMART_POLICY) return SimpleTrigger.MISFIRE_INSTRUCTION_SMART_POLICY;
    throw  new JSException("Unhandled misfire instruction of value "+i);
  }
  */

  
    /**
     * Compares two ReportJob triggers.
     *
     *
     * @param rjt the ReportJob trigger wich compares to this one
     * @return <code>true</code> if both triggers are equal
     */
	public boolean equals(ReportJobTrigger rjt) {
        return
                (this.startType == rjt.getStartType()) &&
                        this.timezone.equals(rjt.getTimezone()) &&
                        (((this.calendarName == null) && (rjt.getCalendarName() == null)) ||
                                ((this.calendarName != null) && (rjt.getCalendarName() != null) && (this.calendarName.compareTo(rjt.getCalendarName()) == 0))) &&
                        (((this.startDate == null) && (rjt.getStartDate() == null)) ||
                                ((this.startDate != null) && (rjt.getStartDate() != null) && (this.startDate.compareTo(rjt.getStartDate()) == 0))) &&
                        (((this.endDate == null) && (rjt.getEndDate() == null)) ||
                                ((this.endDate != null) && (rjt.getEndDate() != null) && (this.endDate.compareTo(rjt.getEndDate()) == 0))) &&
                        (this.misfireInstruction == rjt.getMisfireInstruction());

	}

  /**
   * Convenience constructor that returns a distinct copy of the input ReportJobSource.
   * All of the copy's Object members are themselves copies as well.
   *
   * We're deliberately avoiding using clone()
   */
  public ReportJobTrigger(ReportJobTrigger jobTrigger) {

      // we need the Id on the trigger since if the trigger and the version are the same we dont generate a new trigger upon update
      this.setId(jobTrigger.getId());
      this.setVersion(jobTrigger.getVersion());
      this.setStartDate(jobTrigger.getStartDate());
      this.setStartType(jobTrigger.getStartType());
      this.setEndDate(jobTrigger.getEndDate());
      this.setTimezone(jobTrigger.getTimezone());
      this.setCalendarName(jobTrigger.getCalendarName());
      this.setMisfireInstruction(jobTrigger.getMisfireInstruction());
  }

}
