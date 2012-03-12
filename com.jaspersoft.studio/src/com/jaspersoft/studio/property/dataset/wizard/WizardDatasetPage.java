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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class WizardDatasetPage extends WizardPage {
	private final class DatasetListListener implements Listener {
		public void handleEvent(Event event) {
			if (datasets.getSelectionIndex() == 0 && all)
				datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME, null);
			else if (datasets.getSelectionIndex() >= 0 && datasets.getSelectionIndex() < datasets.getItemCount())
				datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME,
						datasets.getItem(datasets.getSelectionIndex()));
		}
	}

	private JasperReportsConfiguration jConfig;
	private boolean all = true;
	private MDatasetRun datasetrun;
	private List datasets;
	private DatasetListListener dsListener;

	public void setDataSetRun(MDatasetRun datasetrun) {
		this.datasetrun = datasetrun;
		JRDesignDatasetRun ct = (JRDesignDatasetRun) datasetrun.getValue();
		if (ct == null)
			datasetrun.setValue(new JRDesignDatasetRun());
	}

	public MDatasetRun getDataSetRun() {
		return datasetrun;
	}

	public WizardDatasetPage(JasperReportsConfiguration jConfig) {
		this(jConfig, true);
	}

	public WizardDatasetPage(JasperReportsConfiguration jConfig, boolean all) {
		super("datasetpage"); //$NON-NLS-1$
		setTitle(Messages.common_dataset);
		setImageDescriptor(MDataset.getIconDescriptor().getIcon32());
		setDescription(Messages.WizardDatasetPage_description);
		this.jConfig = jConfig;
		this.all = all;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);

		String[] dsNames = ModelUtils.getDataSets(jConfig.getJasperDesign(), all);

		datasets = new List(composite, SWT.BORDER | SWT.READ_ONLY);
		datasets.setItems(dsNames);
		dsListener = new DatasetListListener();
		datasets.addListener(SWT.Selection, dsListener);
		datasets.setSelection(0);
		String dsname = (String) datasetrun.getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME);
		if (dsname != null) {
			String[] items = datasets.getItems();
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(dsname)) {
					datasets.setSelection(i);
					break;
				}
			}
		}
		dsListener.handleEvent(new Event());
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 300;
		datasets.setLayoutData(gd);

		Button newDataset = new Button(composite, SWT.PUSH);
		newDataset.setText(Messages.common_new);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		newDataset.setLayoutData(gd);
		newDataset.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				DatasetWizard wizard = new DatasetWizard();
				WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
				wizard.init(jConfig);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					String ds = (String) wizard.getDataset().getPropertyValue(JRDesignDataset.PROPERTY_NAME);
					datasets.add(ds);
					datasets.select(datasets.getItemCount() - 1);

					try {
						jConfig.getJasperDesign().addDataset((JRDesignDataset) wizard.getDataset().getValue());
						datasetrun.setPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME, ds);
					} catch (JRException e1) {
						e1.printStackTrace();
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");//$NON-NLS-1$
	}

}
