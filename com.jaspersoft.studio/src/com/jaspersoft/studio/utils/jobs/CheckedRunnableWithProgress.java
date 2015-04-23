/*******************************************************************************
 * Copyright (C) 2005 - 2015 TIBCO Software Inc. All rights reserved.
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
package com.jaspersoft.studio.utils.jobs;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * This class should be implemented when willing to implement long running operations
 * that can be terminated or which execution can be cancelled.
 * <p>
 * 
 * A typical use case is a database connection that is taking to much time and we want
 * to abort the operation.<br>
 * A background thread is checking every 500 milliseconds if a stop operation has 
 * been requested hitting the Cancel button of the progress monitor.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public abstract class CheckedRunnableWithProgress implements IRunnableWithProgress, AbortOperationListener {

	private ProgressMonitorCheckerThread executionChecker = null;
	private int sleepTimeout;
	
	public CheckedRunnableWithProgress() {
		this(ProgressMonitorCheckerThread.DEFAULT_CHECK_INTERVAL);
	}
	
	public CheckedRunnableWithProgress(int sleepTimeout) {
		this.sleepTimeout = sleepTimeout;
	}

	@Override
	public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		executionChecker = new ProgressMonitorCheckerThread(monitor,sleepTimeout);
		executionChecker.addListener(this);
		executionChecker.start();
		
		try {
			runOperations(monitor);
		}
		finally {
			executionChecker.interrupt();
			disposeResources();
		}
	}
	
	/**
	 * All operations that were supposed to be executed inside the
	 * original {@link #run(IProgressMonitor)} method should be 
	 * executed inside this one.
	 * 
	 * @param monitor the progress monitor
	 */
	abstract protected void runOperations(IProgressMonitor monitor);

	/**
	 * This method should be implemented by clients in order to 
	 * perform some operations once the Cancel button of the progress monitor
	 * is pressed.<br>
	 */
	 abstract protected void abortOperationInvoked();
	
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

	/**
	 * Default implementation invoke the {@link #abortOperationInvoked()} method.
	 */
	@Override
	public void abortOperationOccured() {
		abortOperationInvoked();
	}
	
}
