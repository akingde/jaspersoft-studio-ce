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
package com.jaspersoft.studio.property.dataset.wizard;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSourceProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.MDatasources;
import com.jaspersoft.studio.repository.RepositoryManager;

public class WizardDataSourcePage extends WizardPage {
	private JRDataSourceProvider dataSource;

	public JRDataSourceProvider getDataSource() {
		return dataSource;
	}

	private List<AMDatasource> dsList;
	private String[] dsListName;

	public WizardDataSourcePage() {
		super("datasourcepage"); //$NON-NLS-1$
		setTitle(Messages.WizardDataSourcePage_datasource);
		setImageDescriptor(MDatasources.getIconDescriptor().getIcon32());
		setDescription(Messages.WizardDataSourcePage_description);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText(Messages.WizardDataSourcePage_datasource + ":"); //$NON-NLS-1$

		dsList = RepositoryManager.getDatasources();

		Combo dsCombo = new Combo(composite, SWT.BORDER);
		dsCombo.setItems(getDSNameList(dsList));
		dsCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");
	}

	private String[] getDSNameList(List<AMDatasource> dsList) {
		String[] dsListName = new String[dsList.size()];
		for (int i = 0; i < dsList.size(); i++) {
			dsListName[i] = (String) dsList.get(i).getPropertyValue(AMDatasource.PROPERTY_NAME);
		}
		return dsListName;
	}

}
