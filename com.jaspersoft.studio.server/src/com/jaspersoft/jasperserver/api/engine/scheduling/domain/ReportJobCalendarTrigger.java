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

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb.MongthsByteXmlAdapter;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb.ReportJobTriggerCalendarDaysXmlAdapter;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb.WeekDaysByteXmlAdapter;

/**
 * Job trigger that fires at specified calendar moments.
 * 
 * <p>
 * Calendar triggers can be used to define jobs that occur on specific month or
 * week days at certain time(s) of the day.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobCalendarTrigger.java 22538 2012-03-14 19:14:36Z ichan $
 * @since 1.0
 */
@JasperServerAPI
@XmlRootElement(name = "calendarTrigger")
public class ReportJobCalendarTrigger extends ReportJobTrigger implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Trigger days type that indicates that the job will occur on every day.
	 * 
	 * @see #getDaysType()
	 */
	public static final byte DAYS_TYPE_ALL = 1;
	
	/**
	 * Trigger days type for jobs that occur on specific week days.
	 * 
	 * @see #getDaysType()
	 * @see #getWeekDays()
	 */
	public static final byte DAYS_TYPE_WEEK = 2;
	
	/**
	 * Trigger days type for jobs that occur on specific month days.
	 * 
	 * @see #getDaysType()
	 * @see #getMonthDays()
	 */
	public static final byte DAYS_TYPE_MONTH = 3;

  public static final int JS_MISFIRE_INSTRUCTION_DO_NOTHING=2;
  public static final int JS_MISFIRE_INSTRUCTION_FIRE_ONCE_NOW=1;

	private String minutes;
	private String hours;
	private Byte daysType;
	private SortedSet weekDays;
	private String monthDays;
	private SortedSet months;
	
	/**
	 * Creates an empty calendar trigger.
	 */
	public ReportJobCalendarTrigger() {
	}

	/**
	 * Returns the pattern that describes at which minutes (within an hour)
	 * the trigger will fire.
	 * 
	 * @return the trigger minutes pattern
	 * @see #setMinutes(String)
	 * @see #getHours()
	 */
	public String getMinutes() {
		return minutes;
	}

	/**
	 * Specifies the pattern that determines the minutes part of the trigger
	 * fire times.
	 * 
	 * The pattern can consist of the following tokens:
	 * <ul>
	 * <li>
	 * A single minute value between <code>0</code> and <code>59</code>.
	 * </li>
	 * <li>
	 * A minutes range, for instance <code>0-10</code> which means that the
	 * trigger should fire every minute starting from HH:00 to HH:10.
	 * </li>
	 * <li>
	 * Minute values and ranges can be concatenated using commas as separators.
	 * </li>
	 * <li>
	 * A minute value with an increment, for instance 5/10 which means that the
	 * trigger would fire every 10 minutes starting from HH:05.
	 * </li>
	 * <li>
	 * <code>*</code> which means the the job would fire every minute of the hour. 
	 * </li>
	 * </ul>
	 * 
	 * @param minutes the minutes pattern to be used for the trigger
	 */
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	/**
	 * Returns the pattern that describes at which hours (within a day)
	 * the trigger will fire.
	 * 
	 * @return the trigger hour pattern
	 * @see #setHours(String)
	 */
	public String getHours() {
		return hours;
	}

	/**
	 * Specifies the pattern that determines the hours at which the trigger
	 * should fire.
	 * 
	 * The pattern can consist of the following tokens:
	 * <ul>
	 * <li>
	 * A single hour value between <code>0</code> and <code>23</code>.
	 * </li>
	 * <li>
	 * A hours range, for instance <code>8-16</code> which means that the
	 * trigger should fire every hour starting from 8 AM to 4 PM.
	 * </li>
	 * <li>
	 * Hour values and ranges can be concatenated using commas as separators.
	 * </li>
	 * <li>
	 * A hour value with an increment, for instance 10/2 which means that the
	 * trigger would fire every 2 hours starting from 10 AM.
	 * </li>
	 * <li>
	 * <code>*</code> which means the the job would fire every hour. 
	 * </li>
	 * </ul>
	 * 
	 * @param hours the hours pattern to be used for the trigger
	 */
	public void setHours(String hours) {
		this.hours = hours;
	}

	/**
	 * Returns the type of days on which the trigger should fire.
	 * 
	 * @return one of {@link #DAYS_TYPE_ALL}, {@link #DAYS_TYPE_MONTH} and 
	 * {@link #DAYS_TYPE_WEEK}
     * @deprecated use #getDaysTypeCode() instead
	 */
    @XmlTransient
	public byte getDaysType() {
		return getDaysTypeCode();
	}

	/**
	 * Returns the type of days on which the trigger should fire.
	 *
	 * @return one of {@link #DAYS_TYPE_ALL}, {@link #DAYS_TYPE_MONTH} and
	 * {@link #DAYS_TYPE_WEEK}
	 */
    @XmlElement(name = "daysType")
    @XmlJavaTypeAdapter(ReportJobTriggerCalendarDaysXmlAdapter.class)
	public Byte getDaysTypeCode() {
		return daysType;
	}

	/**
	 * Sets the type of days on which the trigger should fire.
	 * 
	 * @param daysType one of {@link #DAYS_TYPE_ALL}, {@link #DAYS_TYPE_MONTH} and 
	 * {@link #DAYS_TYPE_WEEK}
	 * @see #setMonthDays(String)
	 * @see #setWeekDays(SortedSet)
     * @deprecated use #setDaysTypeCode(Byte daysType) instead
	 */
	public void setDaysType(byte daysType) {
		setDaysTypeCode(daysType);
	}

	/**
	 * Sets the type of days on which the trigger should fire.
	 *
	 * @param daysType one of {@link #DAYS_TYPE_ALL}, {@link #DAYS_TYPE_MONTH} and
	 * {@link #DAYS_TYPE_WEEK}
	 * @see #setMonthDays(String)
	 * @see #setWeekDays(SortedSet)
	 */
	public void setDaysTypeCode(Byte daysType) {
		this.daysType = daysType;
	}

	/**
	 * Returns the months days on which the trigger would fire.
	 * 
	 * @return the trigger month days pattern
	 */
	public String getMonthDays() {
		return monthDays;
	}

	/**
	 * Specifies the pattern that determines the month days on which the trigger
	 * should fire.
	 * 
	 * The pattern can consist of the following tokens:
	 * <ul>
	 * <li>
	 * A single day value between <code>1</code> and <code>31</code>.
	 * </li>
	 * <li>
	 * A days range, for instance <code>2-5</code> which means that the
	 * trigger should fire every on each day starting from 2nd to 5th.
	 * </li>
	 * <li>
	 * Day values and ranges can be concatenated using commas as separators.
	 * </li>
	 * <li>
	 * A day value with an increment, for instance 1/5 which means that the
	 * trigger would fire every 5 days starting on 1st of the month.
	 * </li>
	 * <li>
	 * <code>*</code> which means the the job would fire every day. 
	 * </li>
	 * </ul>
	 * 
	 * @param monthDays the month days pattern to be used for the trigger
	 * @see #DAYS_TYPE_MONTH
	 */
	public void setMonthDays(String monthDays) {
		this.monthDays = monthDays;
	}

	/**
	 * Returns the months on which the trigger should fire.
	 * 
	 * <p>
	 * The months are represented as <code>java.lang.Byte</code> values between
	 * <code>0</code> (Jan) and <code>11</code> (Dec).
	 * </p>
	 * 
	 * @return the months on which the trigger should fire as 
	 * <code>java.lang.Byte</code> values
	 */
    @XmlElement
    @XmlJavaTypeAdapter(MongthsByteXmlAdapter.class)
	public SortedSet getMonths() {
		return months;
	}

	/**
	 * Sets the months on which the trigger should fire.
	 * 
	 * <p>
	 * The months are specified as <code>java.lang.Byte</code> values between
	 * <code>0</code> (Jan) and <code>11</code> (Dec).
	 * </p>
	 * 
	 * @param months the months as <code>java.lang.Byte</code> values
	 */
    public void setMonths(SortedSet months) {
		this.months = months;
	}

	/**
	 * Returns the week days on which the trigger should fire.
	 * 
	 * <p>
	 * The days are represented as <code>java.lang.Byte</code> values between
	 * <code>1</code> (Sunday) and <code>7</code> (Saturday).
	 * </p>
	 * 
	 * @return the trigger week days
	 */
    @XmlElement
    @XmlJavaTypeAdapter(WeekDaysByteXmlAdapter.class)
	public SortedSet getWeekDays() {
		return weekDays;
	}

	/**
	 * Sets the week days on which the trigger should fire.
	 * 
	 * <p>
	 * The days are specified as <code>java.lang.Byte</code> values between
	 * <code>1</code> (Sunday) and <code>7</code> (Saturday).
	 * </p>
	 * 
	 * @param weekDays the week days as <code>java.lang.Byte</code> values
	 */
	public void setWeekDays(SortedSet weekDays) {
		this.weekDays = weekDays;
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
        ReportJobCalendarTrigger rjct;
        try {
            rjct = (ReportJobCalendarTrigger) rjt;
        } catch (ClassCastException e) {
            // obviously we have different triggers if they are different classes
            return false;
        }
        return
            super.equals(rjt) &&
            this.minutes.equals(rjct.getMinutes()) &&
            this.hours.equals(rjct.getHours()) &&
            this.daysType == rjct.getDaysType() &&
            (this.monthDays != null && this.monthDays.equals(rjct.getMonthDays()));
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
    if (i == JS_MISFIRE_INSTRUCTION_DO_NOTHING) return (CalendarIntervalTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
    if (i == JS_MISFIRE_INSTRUCTION_FIRE_ONCE_NOW) return (CalendarIntervalTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
    return super.getQuartzMisfireCode();
  }
      */

  /**
   * Convenience constructor that returns a distinct copy of the input ReportJobSource.
   * All of the copy's Object members are themselves copies as well.
   *
   * We're deliberately avoiding using clone()
   */
  public ReportJobCalendarTrigger(ReportJobCalendarTrigger jobTrigger) {
    super(jobTrigger);
    this.setMinutes(jobTrigger.getMinutes());
    this.setHours(jobTrigger.getHours());
    this.setDaysTypeCode(jobTrigger.getDaysTypeCode());
    this.setMonthDays(jobTrigger.getMonthDays());
    if (jobTrigger.getMonths() != null) this.setMonths(new TreeSet(jobTrigger.getMonths()));
    if (jobTrigger.getWeekDays() != null)  this.setWeekDays(new TreeSet(jobTrigger.getWeekDays()));
  }

}
