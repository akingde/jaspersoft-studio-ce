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
package com.jaspersoft.studio.property.dataset.wizard;

import java.util.List;
import java.util.Map;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSWizard;

public class WizardFieldsGroupByPage extends WizardFieldsPage {
	
	

	public WizardFieldsGroupByPage() {
		super("groupfields"); //$NON-NLS-1$
		setTitle(Messages.WizardFieldsGroupByPage_group_by);
		setDescription(Messages.WizardFieldsGroupByPage_description);
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_SELECT_GROUP;
	}

	/**
	 * This procedure initialize the dialog page with the list of available objects.
	 * This implementation looks for object set in the map as DATASET_FIELDS
	 * if this is for real just the first time the page is shown.
	 * 
	 */
	public void loadSettings() {
		
		if (getSettings() == null) return;
		
		if (getSettings().containsKey( WizardDataSourcePage.DATASET_FIELDS))
		{
			setAvailableFields( (List<?>)(getSettings().get( WizardDataSourcePage.DATASET_FIELDS )) );
		}
		else
		{
			setAvailableFields(null);
		}
	}

	
	
	/**
	 * Every time a new selection occurs, the selected fields are stored in the settings map
	 * with the key WizardDataSourcePage.GROUP_FIELDS
	 */
	@Override
	public void storeSettings()
	{
		System.out.println("Saving group fields...");
		if (getWizard() instanceof JSSWizard &&
				getWizard() != null)
			{
				Map<String, Object> settings = ((JSSWizard)getWizard()).getSettings();
			
				if (settings == null) return;
				
				settings.put(WizardDataSourcePage.GROUP_FIELDS,  getSelectedFields() ); // the type is IPath
			}
		
	}
}
