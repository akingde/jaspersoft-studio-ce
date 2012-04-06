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

import net.sf.jasperreports.eclipse.ui.util.ExceptionDetailsErrorDialog;

import org.eclipse.core.commands.operations.OperationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

public class UIUtils {

	public static void showError(final Throwable t) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {

				IStatus status = new OperationStatus(IStatus.ERROR, JaspersoftStudioPlugin.getUniqueIdentifier(),
						OperationStatus.NOTHING_TO_REDO, t.getMessage(), t);
				new ExceptionDetailsErrorDialog(Display.getDefault().getActiveShell(), Messages.common_exception,
						Messages.common_exception_detail, status, IStatus.OK | IStatus.INFO | IStatus.WARNING | IStatus.ERROR) {
					protected void setShellStyle(int newShellStyle) {
						super.setShellStyle(newShellStyle | SWT.SHEET);
					};
				}.open();
			}
		});
		t.printStackTrace();
	}

	public static void showWarning(final String message) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialog.open(MessageDialog.WARNING, Display.getDefault().getActiveShell(), Messages.common_warning,
						message, SWT.SHEET);
			}
		});
	}

	/**
	 * @return true if yes
	 */
	public static boolean showConfirmation(String title, String message) {
		MessageDialog dialog = new MessageDialog(null, title, null, message, MessageDialog.QUESTION, new String[] {
				Messages.common_yes, Messages.common_no }, 0) {

			@Override
			protected void setShellStyle(int newShellStyle) {
				super.setShellStyle(newShellStyle | SWT.SHEET);
			}
		};
		return dialog.open() == 0;
	}

	/**
	 * @return true if yes
	 */
	public static boolean showDeleteConfirmation() {
		return showConfirmation(Messages.common_delete.replace("&", ""), Messages.common_confirmdelete);
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

	public static CLabel createLabel(Composite parent, String txt) {
		return createLabel(parent, txt, -1);
	}

	public static CLabel createLabel(Composite parent, String txt, int span) {
		CLabel lbl = new CLabel(parent, SWT.RIGHT);
		lbl.setText(txt);
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			lbl.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.VERTICAL_ALIGN_BEGINNING);
			if (span > 0)
				gd.horizontalSpan = span;
			lbl.setLayoutData(gd);
		}
		return lbl;
	}

	public static Label createSeparator(Composite parent, int span) {
		Label lbl = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.WRAP);
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			lbl.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = span;
			lbl.setLayoutData(gd);
		}
		return lbl;
	}

	public static void setBold(Control control) {
		int fontHeight = 12;
		String name = "";
		FontData[] fontData = control.getFont().getFontData();
		for (int i = 0; i < fontData.length; ++i) {
			fontHeight = fontData[i].getHeight();
			name = fontData[i].getName();
		}
		control.setFont(SWTResourceManager.getFont(name, fontHeight, SWT.BOLD));
	}

}
