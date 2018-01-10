/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Custom thread that periodically checks the status of the specified progress monitor.
 * <p>
 * 
 * If the monitor has been canceled it notifies that an abort operation was requested to
 * a possible list of listeners.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 * @see CheckedRunnableWithProgress
 *
 */
public class ProgressMonitorCheckerThread extends Thread {

	public static final int DEFAULT_CHECK_INTERVAL = 500;
	private int sleepTimeout;
	private volatile boolean execute;
	private IProgressMonitor monitor;
	private List<AbortOperationListener> listeners;

	public ProgressMonitorCheckerThread(IProgressMonitor monitor) {
		this(monitor, DEFAULT_CHECK_INTERVAL);
	}
	
	public ProgressMonitorCheckerThread(IProgressMonitor monitor, int sleepTimeout){
		this.sleepTimeout = sleepTimeout;
		this.monitor = monitor;
		this.listeners = new ArrayList<AbortOperationListener>();
	}

	/**
	 * Adds a listener for abort operations
	 * 
	 * @param listener the listener to be added
	 */
	public void addListener(AbortOperationListener listener) {
		this.listeners.add(listener);
	}
	
	/**
	 * Removes the specified listener for abort operations
	 * 
	 * @param listener the listener to be removed
	 */
	public void removeListener(AbortOperationListener listener) {
		this.listeners.remove(listener);
	}
	
	@Override
	public void run() {
		this.execute = true;
		while(execute) {
			try {
				TimeUnit.MILLISECONDS.sleep(sleepTimeout);
				if(monitor.isCanceled()) {
					execute = false;
					abortOperationInvoked();
				}
			}
			catch (InterruptedException ex) {
				execute = false;
			}
		}
	}

	/*
	 * Notifies listeners that the cancel button on the monitor was pressed.
	 */
	private void abortOperationInvoked() {
		for(AbortOperationListener l : listeners) {
			l.abortOperationOccured();
		}
	}
	
}
