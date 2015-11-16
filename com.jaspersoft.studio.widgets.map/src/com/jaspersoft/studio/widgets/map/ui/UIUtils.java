/*******************************************************************************
 * Copyright (c) 2014 Massimo Rabbi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Massimo Rabbi <mrabbi@users.sourceforge.net> - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.widgets.map.MapActivator;

/**
 * Utility methods for UI related operations.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class UIUtils {
	
	/**
	 * Gets a valid {@link Display} instance trying the following steps:
	 * <ol>
	 * <li>get the current display from the UI thread if any;</li>
	 * <li>get the display from the running workbench;</li>
	 * <li>get a default display instance;</li>
	 * </ol>
	 * 
	 * @return a valid {@link Display} instance
	 */
	public static Display getDisplay() {
		// If we are in the UI Thread use that
		Display d = Display.getCurrent();
		if (d != null)
			return d;
		if (PlatformUI.isWorkbenchRunning())
			return PlatformUI.getWorkbench().getDisplay();
		d = Display.getDefault();
		if (d != null)
			return d;

		// Invalid thread access if it is not the UI Thread
		// and the workbench is not created.
		throw new SWTError(SWT.ERROR_THREAD_INVALID_ACCESS);
	}
	
	/**
	 * Gets a valid {@link Shell} instance trying the following steps:
	 * <ol>
	 * <li>get shell from the current active workbench window;</li>
	 * <li>get active shell from the display instance returned by
	 * {@link getDisplay};</li>
	 * </ol>
	 * 
	 * @return a valid {@link Shell} instance
	 */
	public static Shell getShell() {
		Shell shell = null;

		IWorkbenchWindow window = MapActivator.getDefault().getWorkbench().getActiveWorkbenchWindow();

		if (window != null) {
			shell = window.getShell();
		} else {
			shell = getDisplay().getActiveShell();
		}

		return shell;
	}
}
