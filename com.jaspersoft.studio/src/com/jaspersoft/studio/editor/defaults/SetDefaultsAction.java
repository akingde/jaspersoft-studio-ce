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
package com.jaspersoft.studio.editor.defaults;

import java.text.MessageFormat;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * 
 * Action to add an element to the currently selected template set file. If
 * there is not a selected template set the user is prompt to crate a new 
 * one and if it is created the element is added to it
 * 
 * @author Orlandin Marco
 *
 */
public class SetDefaultsAction extends ACachedSelectionAction {

	/**
	 * Id of the action
	 */
  public static final String ID = "SetDefaultElementAction"; //$NON-NLS-1$

	
	public SetDefaultsAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	@Override
	protected void init() {
		super.init();
		setText(Messages.SetDefaultsAction_text);
		setToolTipText(Messages.SetDefaultsAction_tooltip);
		setId(ID);
		setEnabled(false);
	}
	
	@Override
	public void run() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		MGraphicElement element = (MGraphicElement)elements.get(0);
		if (DefaultManager.INSTANCE.hasDefault()){
			String message = MessageFormat.format(Messages.SetDefaultsAction_message1, new Object[]{DefaultManager.INSTANCE.getDefaultName()});
			MessageDialog dialog = new MessageDialog(UIUtils.getShell(), Messages.SetDefaultsAction_messageTitle, null, message, MessageDialog.QUESTION, new String[]{Messages.common_yes, Messages.common_no}, 1); 
			if (dialog.open() == 0){
				DefaultManager.INSTANCE.addElementToCurrentDefault(element);
			}
		} else {
			MessageDialog dialog = new MessageDialog(UIUtils.getShell(), Messages.SetDefaultsAction_messageTitle, null, Messages.SetDefaultsAction_message2, MessageDialog.QUESTION, new String[]{Messages.common_yes,  Messages.common_no}, 1);  //$NON-NLS-1$
			if (dialog.open() == 0){
				DefaultNewWizard newWizard = new DefaultNewWizard();
				WizardDialog newDialog = new WizardDialog(Display.getDefault().getActiveShell(), newWizard);
				if (newDialog.open() == WizardDialog.OK){
					IFile templateFile = newWizard.getReportFile();
					String templatePath = templateFile.getRawLocation().makeAbsolute().toOSString();
					DefaultManager.INSTANCE.addDefaultFile(templatePath, true);
					DefaultManager.INSTANCE.addElementToCurrentDefault(element);
				}
			}
		}
	}
		
	/**
	 * Only work if the selected element is an MGraphicalElement
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		return (elements.size() == 1);
	}

}
