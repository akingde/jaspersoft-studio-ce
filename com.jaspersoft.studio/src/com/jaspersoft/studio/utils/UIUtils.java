/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/

package com.jaspersoft.studio.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import net.sf.jasperreports.eclipse.ui.util.ExceptionDetailsErrorDialog;

import org.eclipse.core.commands.operations.OperationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

public class UIUtils {

	public static void showError(final Throwable t) {
		showError(t.getMessage(), t);
	}

	public static void showError(final String message, final Throwable t) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {

				IStatus status = new OperationStatus(IStatus.ERROR, JaspersoftStudioPlugin.getUniqueIdentifier(),
						OperationStatus.NOTHING_TO_REDO, message, t);
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

	public static Label createLabel(Composite parent, String txt) {
		return createLabel(parent, txt, -1);
	}

	public static Label createLabel(Composite parent, String txt, int span) {
		Label lbl = new Label(parent, SWT.RIGHT);
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
		control.setFont(SWTResourceManager.getBoldFont(control.getFont()));
	}

	public static int getCharWidth(Drawable control) {
		GC gc = new GC(control);
		FontMetrics fm = gc.getFontMetrics();
		int w = fm.getAverageCharWidth();
		gc.dispose();
		return w;
	}

	public static int getCharHeight(Drawable control) {
		GC gc = new GC(control);
		FontMetrics fm = gc.getFontMetrics();
		int h = fm.getHeight();
		gc.dispose();
		return h;
	}

	public static void setFontHeight(Control control, int height) {
		String name = "";
		FontData[] fontData = control.getFont().getFontData();
		for (int i = 0; i < fontData.length; ++i) {
			name = fontData[i].getName();
		}
		control.setFont(SWTResourceManager.getFont(name, height, SWT.BOLD));
	}

	/**
	 * This method adds select-on-focus functionality to a {@link Text} component.
	 * 
	 * Specific behavior: - when the Text is already focused -> normal behavior - when the Text is not focused: -> focus
	 * by keyboard -> select all text -> focus by mouse click -> select all text unless user manually selects text
	 * 
	 * @param text
	 */
	public static void addSelectOnFocusToText(Text text) {
		// THIS METHOD WAS TAKEN FROM THE FOLLOWING TOPIC ON STACKOVERFLOW:
		// http://stackoverflow.com/questions/10038570/implementing-select-on-focus-behavior-for-an-eclipse-text-control
		// REFER ALSO TO THE FOLLOWING ECLIPSE BUG 46059
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=46059
		Listener listener = new Listener() {

			private boolean hasFocus = false;
			private boolean hadFocusOnMousedown = false;

			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.FocusIn: {
					Text t = (Text) e.widget;

					// Covers the case where the user focuses by keyboard.
					t.selectAll();

					// The case where the user focuses by mouse click is special because Eclipse,
					// for some reason, fires SWT.FocusIn before SWT.MouseDown, and on mouse down
					// it cancels the selection. So we set a variable to keep track of whether the
					// control is focused (can't rely on isFocusControl() because sometimes it's wrong),
					// and we make it asynchronous so it will get set AFTER SWT.MouseDown is fired.
					t.getDisplay().asyncExec(new Runnable() {
						public void run() {
							hasFocus = true;
						}
					});

					break;
				}
				case SWT.FocusOut: {
					hasFocus = false;
					((Text) e.widget).clearSelection();

					break;
				}
				case SWT.MouseDown: {
					// Set the variable which is used in SWT.MouseUp.
					hadFocusOnMousedown = hasFocus;

					break;
				}
				case SWT.MouseUp: {
					Text t = (Text) e.widget;
					if (t.getSelectionCount() == 0 && !hadFocusOnMousedown) {
						((Text) e.widget).selectAll();
					}

					break;
				}
				}
			}

		};

		text.addListener(SWT.FocusIn, listener);
		text.addListener(SWT.FocusOut, listener);
		text.addListener(SWT.MouseDown, listener);
		text.addListener(SWT.MouseUp, listener);
	}

	/**
	 * Setups the start of cell editing on a {@link TableViewer} when a {@link DoubleClickEvent} occurs.
	 * 
	 * @param tviewer
	 *          the table viewer
	 */
	public static void setViewerCellEditingOnDblClick(TableViewer tviewer) {
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(tviewer) {
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				return event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION;
			}
		};

		TableViewerEditor.create(tviewer, actSupport, ColumnViewerEditor.DEFAULT);
	}
	
	/**
	 * Creates an error decoration on the top left of the specified {@link Text} widget
	 * when the text is empty or null.
	 * 
	 * @param widget the text widget to which attach the decorator
	 * @param marginWidth margin between decoration and widget
	 * @param description description message to show on hover
	 */
	public static void createErrorDecorationForEmptyText(final Text widget, int marginWidth, String description){
		final ControlDecoration textDecoration = createErrorDecoration(widget, marginWidth, description);
		widget.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				if(widget.getText()==null || widget.getText().trim().isEmpty()){
					textDecoration.show();
				}
				else {
					textDecoration.hide();
				}
			}
		});
	}

	/**
	 * Creates an error decoration on the top left of the specified {@link WTextExpression} widget
	 * when the expression text is empty or null.
	 * 
	 * @param widget the expression widget to which attach the decorator
	 * @param marginWidth margin between decoration and widget
	 * @param description description message to show on hover
	 */
	public static void createErrorDecorationForBlankExpression(final WTextExpression widget, int marginWidth, String description){
		final ControlDecoration textDecoration = createErrorDecoration(widget, marginWidth, description);
		widget.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				if(widget.getText()==null || widget.getText().trim().isEmpty()){
					textDecoration.show();
				}
				else {
					textDecoration.hide();
				}
			}
		});
	}
	
	/*
	 * Create a decoration attached to the specified control.
	 */
	private static ControlDecoration createErrorDecoration(final Control control, int marginWidth, String description) {
		final ControlDecoration textDecoration = new ControlDecoration(
				control, SWT.LEFT | SWT.TOP);
		textDecoration.setDescriptionText(description);
		textDecoration.setMarginWidth(marginWidth);
		FieldDecoration fieldDecoration = FieldDecorationRegistry
			    .getDefault().getFieldDecoration(
			         FieldDecorationRegistry.DEC_ERROR);
		textDecoration.setImage(fieldDecoration.getImage());
		return textDecoration;
	}
	
}
