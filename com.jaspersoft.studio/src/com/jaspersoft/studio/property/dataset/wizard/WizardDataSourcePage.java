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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.IFieldSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.datasource.MDatasources;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;

public class WizardDataSourcePage extends WizardPage implements IFieldSetter {
	private JRDesignDataset dataset;
	private JasperDesign jDesign;

	public JRDesignDataset getDataset() {
		if (dataset == null) {
			dataset = new JRDesignDataset(true);
			JRDesignQuery query = new JRDesignQuery();
			query.setLanguage("SQL");
			dataset.setQuery(query);
		}

		return dataset;
	}

	private IFile file;
	private DataQueryAdapters dataquery;

	public WizardDataSourcePage(IFile file, JasperDesign jDesign) {
		super("datasourcepage"); //$NON-NLS-1$
		setTitle(Messages.WizardDataSourcePage_datasource);
		setImageDescriptor(MDatasources.getIconDescriptor().getIcon32());
		setDescription(Messages.WizardDataSourcePage_description);
		this.file = file;
		this.jDesign = jDesign;
	}

	@Override
	public void dispose() {
		dataquery.dispose();
		super.dispose();
	}

	public void getFields() {
		if (dataquery != null)
			dataquery.getFields();
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataquery != null)
			return dataquery.getDataAdapter();
		return null;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
		setControl(composite);

		dataquery = new DataQueryAdapters(composite, jDesign, getDataset(), composite.getBackground(), file) {

			@Override
			public void setFields(List<JRDesignField> fields) {
				WizardDataSourcePage.this.setFields(fields);
			}

			@Override
			public void setParameters(List<JRDesignParameter> params) {
			}
		};

		CTabFolder ctf = dataquery.createTop(composite, this);

		Composite c = dataquery.createToolbar(ctf);

		int tabHeight = c.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		tabHeight = Math.max(tabHeight, ctf.getTabHeight());
		ctf.setTabHeight(tabHeight);

		ctf.setTopRight(c);

		dataquery.setDataset(jDesign, getDataset());

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");
	}

	public void setFile(IFile file, JasperDesign jDesign) {
		this.file = file;
		this.jDesign = jDesign;
		dataquery.setFile(file, jDesign);

	}

	public void setFields(List<JRDesignField> fields) {
		// DatasetDialog.this.setFields(fields);
		dataset.getFieldsList().clear();
		dataset.getFieldsMap().clear();
		for (JRDesignField field : fields)
			try {
				dataset.addField(field);
			} catch (JRException e) {
				e.printStackTrace();
			}
	}

}
