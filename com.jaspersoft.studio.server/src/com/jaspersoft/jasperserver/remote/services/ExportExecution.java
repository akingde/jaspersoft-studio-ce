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

package com.jaspersoft.jasperserver.remote.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.jaspersoft.jasperserver.dto.common.ErrorDescriptor;
import com.jaspersoft.jasperserver.remote.exception.RemoteException;

/**
 * <p>
 * </p>
 * 
 * @author Yaroslav.Kovalchyk
 * @version $Id: ExportExecution.java 26714 2012-12-12 10:18:22Z ykovalchyk $
 */
@XmlRootElement
public class ExportExecution {
	private ExportExecutionOptions options;
	private ExecutionStatus status;
	private ErrorDescriptor errorDescriptor;
	private ReportOutputResource outputResource;
	private Map<String, ReportOutputResource> attachments = new ConcurrentHashMap<String, ReportOutputResource>();
	private Lock lock;
	private Condition condition;
	private volatile Boolean done = false;

	public ExportExecution() {
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}

	@XmlElement
	public String getId() {
		return options.toString();
	}

	@XmlTransient
	public ExportExecutionOptions getOptions() {
		return options;
	}

	public void setOptions(ExportExecutionOptions options) {
		this.options = options;
	}

	public ExecutionStatus getStatus() {
		return status;
	}

	public void setStatus(ExecutionStatus status) {
		lock.lock();
		try {
			this.status = status;
			if (status != ExecutionStatus.queued && status != ExecutionStatus.execution) {
				done = true;
				condition.signalAll();
			}
		} finally {
			lock.unlock();
		}
	}

	public ErrorDescriptor getErrorDescriptor() {
		return errorDescriptor;
	}

	public void setErrorDescriptor(ErrorDescriptor errorDescriptor) {
		lock.lock();
		try {
			this.status = ExecutionStatus.failed;
			this.errorDescriptor = errorDescriptor;
			done = true;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public ReportOutputResource getOutputResource() {
		return outputResource;
	}

	@XmlTransient
	public ReportOutputResource getFinalOutputResource() throws RemoteException {
		lock.lock();
		try {
			while (!done) {
				try {
					condition.await();
				} catch (InterruptedException e) {
					throw new RemoteException(new ErrorDescriptor().setException(e));
				}
			}
			if (status == null)
				throw new IllegalStateException("Status shouldn't be null");
			ErrorDescriptor descriptor = null;
			switch (status) {
			case failed: {
				descriptor = errorDescriptor != null ? errorDescriptor
						: new ErrorDescriptor().setErrorCode("export.failed").setMessage("Export failed");
			}
				break;
			case cancelled: {
				descriptor = new ErrorDescriptor().setErrorCode("export.cancelled").setMessage("Export cancelled");
			}
				break;
			case ready:
				break;
			default: {
				descriptor = new ErrorDescriptor().setErrorCode("export.not.ready").setMessage("Export not ready");
			}
			}
			if (descriptor != null)
				throw new RemoteException(descriptor);
		} finally {
			lock.unlock();
		}
		return outputResource;
	}

	public void setOutputResource(ReportOutputResource outputResource) {
		this.outputResource = outputResource;
	}

	@XmlTransient
	public Map<String, ReportOutputResource> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, ReportOutputResource> attachments) {
		this.attachments = attachments;
	}

	@XmlElementWrapper(name = "attachments")
	@XmlElement(name = "attachment")
	public Set<ReportOutputResource> getAttachmentsSet() {
		return attachments != null && !attachments.isEmpty() ? new HashSet<ReportOutputResource>(attachments.values())
				: null;
	}
}
