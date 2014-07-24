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
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb.AddressesXmlAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * job execution alert that can be defined for a report job.
 * 
 * <p>
 * A notification will result in an email alert being send to the specified recipients
 * at each job execution (including success and fail).
 * </p>
 * 
 * @author Ivan Chan (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobAlert.java 23978 2012-05-31 23:19:25Z ichan $
 * @since 1.0
 * @see com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJob#getAlert()
 */
@JasperServerAPI
@XmlRootElement
public class ReportJobAlert implements Serializable {

	private static final long serialVersionUID = 1L;

    private long id;
	private int version = ReportJob.VERSION_NEW;
    private Recipient recipient = Recipient.OWNER_AND_ADMIN;
    private List<String> toAddresses = new ArrayList<String>();
    private JobState jobState = JobState.FAIL_ONLY;
    private String messageText = null;
    private String messageTextWhenJobFails = null;
    private String subject = null;
    private boolean includingStackTrace = true;
    private boolean includingReportJobInfo = true;
    /**
     * Only send alert mail to specific target user(s)
     * Recipient option:  NONE, JOB OWNER ONLY, ADMIN ONLY, or JOB OWNER AND ADMIN
     */
    public enum Recipient {
        NONE((byte)0),
        OWNER((byte)1),
        ADMIN((byte)2),
        OWNER_AND_ADMIN((byte)3);

        private byte code;
        Recipient(byte code) { this.code = code; }
        public byte getCode() { return code; }
        public static Recipient fromCode(byte code) {
            for (Recipient b : Recipient.values()) if (code == b.code) return b;
            return NONE;
        }
    }
    /**
     * Only send alert mail in specific job state
     * Job state option:  NONE, ALL, FAIL_ONLY, SUCCESS_ONLY
     */
    public static enum JobState {
        NONE((byte)0),
        ALL((byte)1),
        FAIL_ONLY((byte)2),
        SUCCESS_ONLY((byte)3);

        private byte code;
        JobState(byte code) { this.code = code; }
        public byte getCode() { return code; }
        public static JobState fromCode(byte code) {
            for (JobState b : JobState.values()) if (code == b.code) return b;
            return NONE;
        }
    }

	/**
	 * Creates an empty job email alert.
	 */
	public ReportJobAlert() {
		super();
		toAddresses = new ArrayList();
	}

	/**
	 * Returns the ID of the email alert.
	 *
	 * <p>
	 * The ID is automatically generated when the job for which the alert
	 * is defined gets saved in the scheduler.
	 * </p>
	 *
	 * @return the ID of the email alert
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the ID of the email alert.
	 *
	 * <p>
	 * The ID needs to be set when updating a report job, but not when saving
	 * a new job.
	 * </p>
	 *
	 * @param id the notification ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns the persistent version of this email alert.
	 *
	 * <p>
	 * The version is automatically set when the alert is retrieved
	 * (along with the job) from the scheduling service.
	 * </p>
	 *
	 * @return the persistent alert version
	 * @see com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJob#VERSION_NEW
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the persistent email alert version.
	 * 
	 * <p>
	 * The version doesn't have to be set when saving a new report job,
	 * but it needs to be set when updating a report job (along with its 
	 * alert).
	 * </p>
	 * 
	 * @param version the persistent alert version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

    /**
	 * Determines whether the alert would send it to owner, admin, none
     * or both (admin and owner)
	 *
	 * @return one of {@link ReportJobAlert.Recipient}
	 */
    public Recipient getRecipient() {
        return recipient;
    }

	/**
	 * Specifies whether the alert would send it to owner, admin, none
     * or both (admin and owner)
	 *
	 * @param recipient one of {@link ReportJobAlert.Recipient}
	 */
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    /**
	 * Returns the list of additional direct recipients of the email alert.
	 *
	 * @return the list of direct alert recipients as
	 * <code>java.lang.String</code> email addresses
	 */
    @XmlJavaTypeAdapter(AddressesXmlAdapter.class)
    public List<String> getToAddresses() {
        return toAddresses;
    }

    /**
	 * Sets the email addresses that should be used as additional direct recipients for
	 * the email alert.
	 *
	 * @param toAddresses the list of recipients as
	 * <code>java.lang.String</code> email addresses
	 */
    public void setToAddresses(List<String> toAddresses) {
        this.toAddresses = toAddresses;
    }

    /**
	 * Determines whether the alert would send it when job fails, succeeds, none,
     * or both (fail and success)
	 *
	 * @return one of {@link ReportJobAlert.JobState}
	 */
    public JobState getJobState() {
        return jobState;
    }

	/**
	 * Specifies whether the alert would send it when job fails, succeeds, none,
     * or both (fail and success)
	 *
	 * @param jobState one of {@link ReportJobAlert.JobState}
	 */
    public void setJobState(JobState jobState) {
        this.jobState = jobState;
    }

	/**
	 * Returns the text of the email alert.
	 *
	 * <p>
	 * At job execution time, customize alert message text when job succeeds.
	 * </p>
	 *
	 * @return the alert message text
	 * @see #setMessageText(String)
	 */
    public String getMessageText() {
        return messageText;
    }

	/**
	 * Sets the message text to be used for the email alert when job succeeds.
	 *
	 * @param textMessage the alert message text
	 */
    public void setMessageText(String textMessage) {
        this.messageText = textMessage;
    }

	/**
	 * Returns the text of the email alert.
	 *
	 * <p>
	 * At job execution time, customize alert message text when job fails.
	 * </p>
	 *
	 * @return the alert message text
	 * @see #setMessageText(String)
	 */
    public String getMessageTextWhenJobFails() {
        return messageTextWhenJobFails;
    }

    /*
	 * Sets the message text to be used for the email alert when job fails.
	 *
	 * @param textMessageWhenFails the alert message text
	 */
    public void setMessageTextWhenJobFails(String textMessageWhenFails) {
        this.messageTextWhenJobFails = textMessageWhenFails;
    }

	/**
	 * Returns the subject to be used for the email alert.
	 *
	 * @return the email alert subject
	 */
    public String getSubject() {
        return subject;
    }

	/**
	 * Sets the subject to be used for the email alert.
	 *
	 * @param subject the email alert subject
	 */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
	 * Determines whether the alert would include report job info
	 *
	 * @return true if including report job info
	 */
    public boolean isIncludingReportJobInfo() {
        return includingReportJobInfo;
    }

    /**
	 * Specifies whether the alert would include report job info
	 *
	 * @param includingReportJobInfo including stack trace in alert mail
	 */
    public void setIncludingReportJobInfo(boolean includingReportJobInfo) {
        this.includingReportJobInfo = includingReportJobInfo;
    }


    /**
	 * Determines whether the alert would include detail stack trace of exception
	 *
	 * @return true if including stack trace in alert mail
	 */
    public boolean isIncludingStackTrace() {
        return includingStackTrace;
    }

    /**
	 * Specifies whether the alert would include detail stack trace of exception
	 *
	 * @param includingStackTrace including stack trace in alert mail
	 */
    public void setIncludingStackTrace(boolean includingStackTrace) {
        this.includingStackTrace = includingStackTrace;
    }

}
