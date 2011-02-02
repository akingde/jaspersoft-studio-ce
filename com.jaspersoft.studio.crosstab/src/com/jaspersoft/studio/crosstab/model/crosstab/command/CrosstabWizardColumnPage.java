/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.model.crosstab.command;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;

import com.jaspersoft.studio.crosstab.Activator;
import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.crosstab.model.MCrosstab;
import com.jaspersoft.studio.utils.ModelUtils;

public class CrosstabWizardColumnPage extends WizardPage {
	private MCrosstab crosstab;

	public void setCrosstab(MCrosstab crosstab) {
		this.crosstab = crosstab;
	}

	public MCrosstab getCrosstab() {
		return crosstab;
	}

	protected CrosstabWizardColumnPage() {
		super("crosstabcolumnpage"); //$NON-NLS-1$
		setTitle(Messages.CrosstabWizardColumnPage_0);
		setImageDescriptor(Activator.getImageDescriptor("icons/wizard_columns.png"));
		setDescription(Messages.CrosstabWizardColumnPage_1);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(getControl(), IWorkbenchHelpContextIds.PREFERENCES_IMPORT_WIZARD_PAGE);
	}

}
