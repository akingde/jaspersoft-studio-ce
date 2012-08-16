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
package com.jaspersoft.studio.wizards;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;

public abstract class JSSWizardPage extends WizardPage {

	protected JSSWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public boolean canFlipToNextPage() {
		IWizard wizard = getWizard();
		if (wizard != null && wizard instanceof JSSWizard)
			return isPageComplete() && ((JSSWizard) wizard).hasNextPage(this) != null;
		return super.canFlipToNextPage();
	}
	
	
	/**
	 * This methos is invoked when the page get visible, and allows to
	 * perform dynamic wizard changes;
	 */
	public void loadSettings()
	{
	}
	
	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		super.setVisible(visible);
		
		if (visible == true)
		{
			loadSettings();
		}
	}
	
	
	
}
