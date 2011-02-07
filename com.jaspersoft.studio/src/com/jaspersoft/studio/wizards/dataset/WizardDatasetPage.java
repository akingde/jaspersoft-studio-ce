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
package com.jaspersoft.studio.wizards.dataset;

import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.utils.ModelUtils;

public class WizardDatasetPage extends WizardPage {
	private JasperDesign jasperDesign;

	private MDatasetRun datasetrun;

	public void setDataSetRun(MDatasetRun datasetrun) {
		this.datasetrun = datasetrun;
		JRDesignDatasetRun ct = (JRDesignDatasetRun) datasetrun.getValue();
		if (ct == null)
			datasetrun.setValue(new JRDesignDatasetRun());
	}

	public MDatasetRun getDataSetRun() {
		return datasetrun;
	}

	public WizardDatasetPage(JasperDesign jasperDesign) {
		super("datasetpage"); //$NON-NLS-1$
		setTitle(Messages.WizardDatasetPage_dataset);
		setImageDescriptor(MDataset.getIconDescriptor().getIcon32());
		setDescription(Messages.WizardDatasetPage_description);
		this.jasperDesign = jasperDesign;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText(Messages.WizardDatasetPage_dataset);

		final CCombo datasets = new CCombo(composite, SWT.BORDER);
		datasets.setItems(ModelUtils.getDataSources(jasperDesign));
		datasets.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				if (datasets.getSelectionIndex() == 0)
					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME, null);
				else
					datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME, datasets.getText());
			}
		});
		datasets.select(0);
		String dsname = (String) datasetrun.getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME);
		if (dsname != null) {
			String[] items = datasets.getItems();
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(dsname)) {
					datasets.select(i);
					break;
				}
			}
		}

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");
	}
}
