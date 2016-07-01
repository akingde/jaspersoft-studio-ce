/*******************************************************************************
 * Copyright (C) 2005 - 2016 TIBCO Software Inc. All rights reserved.
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
package com.jaspersoft.studio.editor.expression;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.ExpressionEditorPreferencePage;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationWizardDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Dedicated wizard dialog for the expression editor.
 * It is able to handle the dialog settings persistence.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see IDialogSettings
 * @see PersistentLocationWizardDialog
 *
 */
public class ExpressionPersistentWizardDialog extends PersistentLocationWizardDialog {
	
	public static final String WIZARD_ID = ExpressionPersistentWizardDialog.class.getName();

	public ExpressionPersistentWizardDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		if (ExpressionEditorSupportUtil.shouldRememberExpEditorDialogLocation()){
			return super.getInitialLocation(initialSize);
		} else {
			return getCenteredMonitorLocation(initialSize);
		}
	}
	
	@Override
	protected Point getInitialSize() {
		if (ExpressionEditorSupportUtil.shouldRememberExpEditorDialogSize()){
			return super.getInitialSize();
		} else {
			return new Point(
					ExpressionEditorSupportUtil.EXPEDITOR_INITIAL_WIDTH, 
					ExpressionEditorSupportUtil.EXPEDITOR_INITIAL_HEIGHT);
		}
	}
	
	/*
	 * Possibly ask if the wizard dialog should be closed.
	 */
	private boolean canCloseDialog() {
		boolean askOnClose = JaspersoftStudioPlugin.getInstance().getPreferenceStore().getBoolean(ExpressionEditorPreferencePage.P_CONFIRMATION_ON_CLOSE);
		if(askOnClose) {
			return MessageDialog.openQuestion(UIUtils.getShell(), Messages.ExpressionEditorSupportUtil_ConfirmOnCloseTitle, Messages.ExpressionEditorSupportUtil_ConfirmOnCloseMessage);
		}
		return true;
	}
	
	@Override
	protected void cancelPressed() {
		if(canCloseDialog()) {
			super.cancelPressed();
		}
	}
	@Override
	protected boolean canHandleShellCloseEvent() {
		return canCloseDialog();
	}

}
