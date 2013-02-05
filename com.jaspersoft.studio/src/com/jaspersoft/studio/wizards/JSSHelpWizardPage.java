/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;

public abstract class JSSHelpWizardPage extends WizardPage implements ContextData {

	protected String contextName;
	
	protected JSSHelpWizardPage(String pageName) {
		super(pageName);
		contextName = null;
	}
	
	/**
	 * Set the root control of the wizard, and also add a listener to do the perform help action 
	 * and set the context of the top control.
	 */
	@Override
	protected void setControl(Control newControl) {
		super.setControl(newControl);
		newControl.addListener(SWT.Help, new Listener() {			
			@Override
			public void handleEvent(Event event) {
				performHelp();	
			}
		});
		setHelpData();
	};

	/**
	 * Set the help data that should be seen in this step
	 */
	@Override
	public void setHelpData(){
		if (contextName != null){
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(),contextName);
		}
	}
	
	/**
	 * Set and show the help data if a context, that bind this wizard with the data, is provided
	 */
	@Override
	public void performHelp() {
		if (contextName != null){
			PlatformUI.getWorkbench().getHelpSystem().displayHelp(contextName);
		}
	};
	

}
