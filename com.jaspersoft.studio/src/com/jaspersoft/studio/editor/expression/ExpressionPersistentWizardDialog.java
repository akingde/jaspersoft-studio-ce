/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
			Point storedLocation = ExpressionEditorSupportUtil.getExpEditorDialogLocation();
			if (storedLocation != null && isInsideMonitor(storedLocation)){
				return storedLocation;
			}
		}
		return getCenteredMonitorLocation(initialSize);
	}
	
	@Override
	protected Point getInitialSize() {
		if (ExpressionEditorSupportUtil.shouldRememberExpEditorDialogSize()){
			Point storedSize = ExpressionEditorSupportUtil.getExpEditorDialogSize();
			if (storedSize != null){
				return storedSize;
			}
		} 
		return new Point(
				ExpressionEditorSupportUtil.EXPEDITOR_INITIAL_WIDTH, 
				ExpressionEditorSupportUtil.EXPEDITOR_INITIAL_HEIGHT);
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
