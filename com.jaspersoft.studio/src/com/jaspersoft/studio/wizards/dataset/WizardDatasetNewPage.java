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

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.utils.ModelUtils;

public class WizardDatasetNewPage extends WizardPage {
	private JasperDesign jasperDesign;
	private MDataset dataset;
	private boolean emptyDataset = false;
	private Text dsname;

	public void setDataSet(MDataset dataset) {
		this.dataset = dataset;
		JRDesignDataset ct = (JRDesignDataset) dataset.getValue();
		if (ct == null)
			dataset.setValue(new JRDesignDataset(false));
	}

	public MDataset getDataSet() {
		return dataset;
	}

	public boolean isEmptyDataset() {
		return emptyDataset;
	}

	public WizardDatasetNewPage(JasperDesign jasperDesign) {
		super("datasetpage"); //$NON-NLS-1$
		setTitle(Messages.WizardDatasetNewPage_0);
		setImageDescriptor(MDataset.getIconDescriptor().getIcon32());
		setDescription(Messages.WizardDatasetNewPage_1);
		this.jasperDesign = jasperDesign;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText(Messages.WizardDatasetNewPage_2);

		dsname = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		dsname.setText(ModelUtils.getDefaultName(jasperDesign.getDatasetMap(), Messages.WizardDatasetNewPage_3)); 
		dsname.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {

				String dstext = dsname.getText();
				if (jasperDesign.getDatasetMap().get(dstext) != null) {
					setErrorMessage(Messages.WizardDatasetNewPage_4 + dstext + Messages.WizardDatasetNewPage_5);
				} else {
					setMessage(getDescription());
					dataset.setPropertyValue(JRDesignDataset.PROPERTY_NAME, dstext);
				}
			}
		});
		dsname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final Button fromConn = new Button(composite, SWT.RADIO);
		fromConn.setText(Messages.WizardDatasetNewPage_6);
		fromConn.setSelection(true);
		fromConn.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				emptyDataset = !fromConn.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		fromConn.setLayoutData(gd);

		final Button empty = new Button(composite, SWT.RADIO);
		empty.setText(Messages.WizardDatasetNewPage_7);
		empty.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				emptyDataset = empty.getSelection();
				getWizard().getContainer().updateButtons();
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		gd = new GridData();
		gd.horizontalSpan = 2;
		empty.setLayoutData(gd);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard"); //$NON-NLS-1$
	}

	@Override
	public boolean canFlipToNextPage() {
		return !emptyDataset;
	}
}
