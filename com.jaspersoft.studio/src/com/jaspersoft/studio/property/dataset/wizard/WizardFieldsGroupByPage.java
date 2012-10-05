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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.dataset.wizard;

import java.util.List;
import java.util.Map;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.JSSWizard;

public class WizardFieldsGroupByPage extends WizardFieldsPage {
	
	

	public WizardFieldsGroupByPage() {
		super("groupfields"); //$NON-NLS-1$
		setTitle(Messages.WizardFieldsGroupByPage_group_by);
		setDescription(Messages.WizardFieldsGroupByPage_description);
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