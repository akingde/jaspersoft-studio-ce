/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.custom.adapter;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.custom.adapter.export.ExportAdapterWizard;

/**
 * Command used to open the wizard to create a data adapter plugin project
 * 
 * @author Orlandin Marco
 *
 */
public class CreateAdapterJarCommand implements IHandler {

	/**
	 * Open the wizard to create the data adapter plguin project
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ExportAdapterWizard exportWizard = new ExportAdapterWizard(PlatformUI.getWorkbench(), (IStructuredSelection)activeWorkbenchWindow.getActivePage().getSelection());
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), exportWizard);
		dialog.create();
		UIUtils.resizeAndCenterShell(dialog.getShell(), 800,  600);
		dialog.open();
		return null;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}
	
}
