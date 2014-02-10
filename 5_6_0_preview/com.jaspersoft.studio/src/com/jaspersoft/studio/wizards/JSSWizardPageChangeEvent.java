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


/**
 * Event fired by a JSSWizardPage Page to notify a change in the UI.
 * A wizard may react by changing the page set which makes up the wizard
 * in response to the event.
 * 
 * @author gtoffoli
 *
 */
public class JSSWizardPageChangeEvent {
	
	private JSSWizardPage page;
	
	public JSSWizardPage getPage() {
		return page;
	}

	public void setPage(JSSWizardPage page) {
		this.page = page;
	}

	public JSSWizardPageChangeEvent(JSSWizardPage page)
	{
		this.page = page;
	}

}
