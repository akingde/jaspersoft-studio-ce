/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.studio.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.eclipse.core.commands.operations.OperationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Spinner;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

public class UIUtils {

	public static void showError(final Throwable t) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {

				IStatus status = new OperationStatus(IStatus.ERROR, JaspersoftStudioPlugin.getUniqueIdentifier(),
						OperationStatus.NOTHING_TO_REDO, t.getMessage(), t);
				new ExceptionDetailsErrorDialog(Display.getDefault().getActiveShell(), Messages.UIUtils_0, Messages.UIUtils_1,
						status, IStatus.OK | IStatus.INFO | IStatus.WARNING | IStatus.ERROR).open();
			}
		});
		t.printStackTrace();
	}

	public static void showWarning(final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {

				MessageDialog.openWarning(Display.getDefault().getActiveShell(), Messages.UIUtils_2, message);
			}
		});
	}

	/**
	 * @return true if yes
	 */
	public static boolean showYesNo() {
		MessageDialog dialog = new MessageDialog(null, Messages.common_delete, null, Messages.UIUtils_4,
				MessageDialog.QUESTION, new String[] { Messages.UIUtils_5, Messages.UIUtils_6 }, 0);
		return dialog.open() == 0;
	}

	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

	/**
	 * Set the value of a spinner. For convenience this method takes an object as value, but if the obj is null, or if it
	 * is not an Integer the method does nothing. If the displayed value is the same as the one provided, nothing is done
	 * (preventing on windows the whole selection of the number).
	 * 
	 * @param spinner
	 * @param obj
	 */
	public static void setSpinnerSelection(Spinner spinner, Object obj) {
		if (obj == null)
			return;
		if (!(obj instanceof Integer))
			return;
		int num = ((Integer) obj).intValue();
		if (spinner.getSelection() != num) {
			spinner.setSelection(num);
		}
	}

	/**
	 * Set the value of a spinner. For convenience this method takes an object as value, but if the obj is null, or if it
	 * is not an Integer the method uses the defValue. If the displayed value is the same as the one provided, nothing is
	 * done (preventing on windows the whole selection of the number).
	 * 
	 * @param spinner
	 * @param obj
	 */
	public static void setSpinnerSelection(Spinner spinner, Object obj, int defValue) {
		int num = defValue;
		if (obj != null && obj instanceof Integer) {
			num = ((Integer) obj).intValue();
		}

		if (!spinner.isDisposed() && spinner.getSelection() != num) {
			spinner.setSelection(num);
		}
	}

}
