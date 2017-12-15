/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.jobs;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.jaspersoft.studio.messages.Messages;

/**
 * This class should be implemented when willing to implement long running operations
 * that can be terminated or which execution can be cancelled.
 * <p>
 * 
 * A typical use case is a database connection that is taking to much time and we want
 * to abort the operation.<br>
 * By default a background check is performed every 500 milliseconds to detect 
 * if a stop operation has been requested hitting the Cancel button of the monitor.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public abstract class CheckedRunnableWithProgress implements IRunnableWithProgress {

	public static final int DEFAULT_CHECK_INTERVAL = 500;
	private Thread runnerThread = null;
	private int sleepTimeout;
	private IProgressMonitor progressMonitor;
	private volatile boolean executionCompleted;
	
	public CheckedRunnableWithProgress() {
		this(DEFAULT_CHECK_INTERVAL);
	}
	
	public CheckedRunnableWithProgress(int sleepTimeout) {
		this.sleepTimeout = sleepTimeout;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		this.progressMonitor = monitor;
		try {
			runnerThread = new Thread(new Runnable() {
				@Override
				public void run() {
					runOperations(progressMonitor);
					executionCompleted = true;
				}
			});
			runnerThread.start();
			
			while(!executionCompleted) {
				TimeUnit.MILLISECONDS.sleep(sleepTimeout);
				if(monitor.isCanceled()) {
					abortOperationInvoked();
					throw new InterruptedException(
							Messages.CheckedRunnableWithProgress_GenericAbortMessage);
				}
			}
		}
		finally {
			runnerThread.interrupt();
			disposeResources();
		}
	}
	
	/**
	 * All operations that were supposed to be executed inside the
	 * original {@link #run(IProgressMonitor)} method should be 
	 * executed inside this one.
	 * 
	 * @param progressMonitor the progress progressMonitor
	 */
	abstract protected void runOperations(IProgressMonitor monitor) ;

	/**
	 * This method should be extended by clients in order to 
	 * perform some operations once the Cancel button of the progress progressMonitor
	 * is pressed.<br>
	 */
	 protected void abortOperationInvoked() throws InterruptedException {
		 // do nothing...
	 }
	
	/**
	 * Perform the dispose of possible locked resources.
	 * Clients implementing this method should be aware that
	 * is called in a final block when {@link #runOperations()} completed
	 * or are interrupted.<br>
	 * It's up to the client decide if perform the resources disposal directly
	 * in here or perform it into an outer try-catch-finally block.
	 */
	protected void disposeResources() {
		// do nothing...
	}

}
