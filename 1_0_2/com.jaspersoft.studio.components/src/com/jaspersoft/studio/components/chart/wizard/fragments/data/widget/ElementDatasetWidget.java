/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.chart.wizard.fragments.data.widget;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.IncrementTypeEnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.property.dataset.DatasetRunWidget;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterDTO;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterEditor;
import com.jaspersoft.studio.utils.ModelUtils;

public class ElementDatasetWidget {
	private static final String GROUPPREFIX = "[Group] ";
	private JRDesignElementDataset eDataset;
	private JasperDesign jrDesign;
	private Combo dsCombo;
	private Combo cbIncrement;
	private Combo cbReset;
	private Button btnIncrement;
	private ToolItem prmItem;
	private ToolItem prmMapItem;
	private DatasetRunWidget dsRun;

	public ElementDatasetWidget(Composite parent) {
		createDataset(parent);
		bindData();
	}

	public void setDataset(JRDesignElementDataset eDataset,
			JasperDesign jrDesign) {
		this.eDataset = eDataset;
		this.jrDesign = jrDesign;
		fillData();
	}

	private void fillData() {
		final String[] ds = ModelUtils.getDataSets(jrDesign, true);
		dsCombo.setItems(ds);
		dsCombo.select(0);
		if (eDataset.getDatasetRun() != null) {
			for (int i = 0; i < ds.length; i++) {
				if (ds[i].equals(eDataset.getDatasetRun().getDatasetName())) {
					dsCombo.select(i);
					break;
				}
			}
			dsRun.setData((JRDesignDatasetRun) eDataset.getDatasetRun());
		}

		enableMainDatasetRun();
		fillIncrement();
		fillResetGroup();
	}

	private void fillIncrement() {
		List<String> lsIncs = new ArrayList<String>();
		lsIncs.add(IncrementTypeEnum.REPORT.getName());
		lsIncs.add(IncrementTypeEnum.PAGE.getName());
		lsIncs.add(IncrementTypeEnum.COLUMN.getName());
		JRDataset jrds = getJRdataset(eDataset);
		for (JRGroup gr : jrds.getGroups())
			lsIncs.add(GROUPPREFIX + gr.getName());
		lsIncs.add(IncrementTypeEnum.NONE.getName());

		cbIncrement.setItems(lsIncs.toArray(new String[lsIncs.size()]));

		IncrementTypeEnum rst = eDataset.getIncrementTypeValue();
		String grname = eDataset.getIncrementGroup() != null ? eDataset
				.getIncrementGroup().getName() : null;
		for (int i = 0; i < lsIncs.size(); i++) {
			String rsttype = lsIncs.get(i);
			if (rst.equals(IncrementTypeEnum.GROUP)) {
				if (rsttype.startsWith(GROUPPREFIX)
						&& grname
								.equals(rsttype.substring(GROUPPREFIX.length()))) {
					cbIncrement.select(i);
					break;
				}
			} else if (rsttype.equals(rst.getName())) {
				cbIncrement.select(i);
				break;
			}
		}
	}

	private void fillResetGroup() {
		JRDataset jrds = getJRdataset(eDataset);
		List<String> lsRsts = new ArrayList<String>();
		lsRsts.add(ResetTypeEnum.REPORT.getName());
		lsRsts.add(ResetTypeEnum.COLUMN.getName());
		lsRsts.add(ResetTypeEnum.PAGE.getName());

		for (JRGroup gr : jrds.getGroups())
			lsRsts.add(GROUPPREFIX + gr.getName());
		lsRsts.add(ResetTypeEnum.NONE.getName());
		cbReset.setItems(lsRsts.toArray(new String[lsRsts.size()]));

		ResetTypeEnum rst = eDataset.getResetTypeValue();
		String grname = eDataset.getResetGroup() != null ? eDataset
				.getResetGroup().getName() : null;
		for (int i = 0; i < lsRsts.size(); i++) {
			String rsttype = lsRsts.get(i);
			if (rst.equals(ResetTypeEnum.GROUP)) {
				if (rsttype.startsWith(GROUPPREFIX)
						&& grname
								.equals(rsttype.substring(GROUPPREFIX.length()))) {
					cbReset.select(i);
					break;
				}
			} else if (rsttype.equals(rst.getName())) {
				cbReset.select(i);
				break;
			}
		}
	}

	private void bindData() {
		dsCombo.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (eDataset.getIncrementTypeValue().equals(
						IncrementTypeEnum.GROUP)) {
					eDataset.setIncrementType(IncrementTypeEnum.REPORT);
					eDataset.setIncrementGroup(null);
					cbIncrement.select(0);
				}
				if (eDataset.getResetTypeValue().equals(ResetTypeEnum.GROUP)) {
					eDataset.setResetType(ResetTypeEnum.REPORT);
					eDataset.setResetGroup(null);
					cbReset.select(0);
				}
				if (dsCombo.getSelectionIndex() == 0) {
					eDataset.setDatasetRun(null);
				} else {
					JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();
					datasetRun.setDatasetName(dsCombo.getText());
					eDataset.setDatasetRun(datasetRun);
				}
				dsRun.setData((JRDesignDatasetRun) eDataset.getDatasetRun());
				enableMainDatasetRun();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		bindIncrementGroup();
		bindResetGroup();

		prmItem.addSelectionListener(new SelectionListener() {
			private ParameterDTO prmDTO;

			public void widgetSelected(SelectionEvent e) {
				JRDesignDatasetRun datasetRun = (JRDesignDatasetRun) eDataset
						.getDatasetRun();
				if (prmDTO == null) {
					prmDTO = new ParameterDTO();
					prmDTO.setJasperDesign(jrDesign);
					prmDTO.setValue(datasetRun.getParameters());
				}

				ParameterEditor wizard = new ParameterEditor();
				wizard.setValue(prmDTO);
				WizardDialog dialog = new WizardDialog(btnIncrement.getShell(),
						wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					prmDTO = wizard.getValue();

					for (JRDatasetParameter prm : prmDTO.getValue())
						datasetRun.removeParameter(prm);

					for (JRDatasetParameter param : prmDTO.getValue())
						try {
							datasetRun.addParameter(param);
						} catch (JRException er) {
							er.printStackTrace();
						}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		prmMapItem.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();
				wizard.setValue((JRDesignExpression) eDataset.getDatasetRun()
						.getParametersMapExpression());
				WizardDialog dialog = new WizardDialog(btnIncrement.getShell(),
						wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					((JRDesignDatasetRun) eDataset.getDatasetRun())
							.setParametersMapExpression(wizard.getValue());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	private void enableMainDatasetRun() {
		boolean en = dsCombo.getSelectionIndex() != 0;
		prmItem.setEnabled(en);
		prmMapItem.setEnabled(en);
		dsRun.setEnabled(en);
	}

	private void bindResetGroup() {
		cbReset.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String newval = cbReset.getText();
				ResetTypeEnum val = ResetTypeEnum.getByName(newval);
				if (val != null) {
					eDataset.setResetType(val);
				} else {
					eDataset.setResetType(ResetTypeEnum.GROUP);
					JRDataset jrds = getJRdataset(eDataset);
					for (JRGroup gr : jrds.getGroups()) {
						if (gr.getName().equals(
								newval.substring(GROUPPREFIX.length()))) {
							eDataset.setResetGroup(gr);
							break;
						}

					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	private void bindIncrementGroup() {
		cbIncrement.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String newval = cbIncrement.getText();
				IncrementTypeEnum val = IncrementTypeEnum.getByName(newval);
				if (val != null) {
					eDataset.setIncrementType(val);
				} else {
					eDataset.setIncrementType(IncrementTypeEnum.GROUP);
					JRDataset jrds = getJRdataset(eDataset);
					for (JRGroup gr : jrds.getGroups()) {
						if (gr.getName().equals(
								newval.substring(GROUPPREFIX.length()))) {
							eDataset.setIncrementGroup(gr);
							break;
						}
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		btnIncrement.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				JRExpressionEditor wizard = new JRExpressionEditor();
				wizard.setValue((JRDesignExpression) eDataset
						.getIncrementWhenExpression());
				WizardDialog dialog = new WizardDialog(btnIncrement.getShell(),
						wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					eDataset.setIncrementWhenExpression(wizard.getValue());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

	private JRDataset getJRdataset(final JRDesignElementDataset jrDataset) {
		JRDataset jrds = jrDesign.getMainDataset();
		if (jrDataset.getDatasetRun() != null) {
			String dsname = jrDataset.getDatasetRun().getDatasetName();
			jrDesign.getDatasetMap().get(dsname);
		}
		final JRDataset jrdsfinal = jrds;
		return jrdsfinal;
	}

	public void createDataset(Composite composite) {
		Composite grDataset = new Composite(composite, SWT.NONE);
		grDataset.setLayoutData(new GridData(GridData.FILL_BOTH));
		grDataset.setLayout(new GridLayout());

		CTabFolder ctFolder = new CTabFolder(grDataset, SWT.TOP);
		ctFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		// createFields(ctFolder);
		// createData(ctFolder);
		createParametersMap(ctFolder);
		createConnection(ctFolder);

		ctFolder.setSelection(0);
	}

	private void createConnection(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Dataset");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, true));

		Composite leftComposite = new Composite(composite, SWT.NONE);
		leftComposite.setLayout(new GridLayout(3, false));
		leftComposite.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.VERTICAL_ALIGN_BEGINNING));

		new Label(leftComposite, SWT.NONE).setText("Increment on");
		cbIncrement = new Combo(leftComposite, SWT.BORDER | SWT.READ_ONLY
				| SWT.SINGLE);
		cbIncrement.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		btnIncrement = new Button(leftComposite, SWT.PUSH);
		btnIncrement.setText("...");
		btnIncrement.setToolTipText("Increment When Expression");

		new Label(leftComposite, SWT.NONE).setText("Reset on");
		cbReset = new Combo(leftComposite, SWT.BORDER | SWT.READ_ONLY
				| SWT.SINGLE);
		cbReset.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(leftComposite, SWT.NONE);

		dsRun = new DatasetRunWidget(composite);
		dsRun.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));

		bptab.setControl(composite);
	}

	private void createFields(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Fields");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout());

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText("[dataset fields table here]");
		lbl.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.HORIZONTAL_ALIGN_CENTER
				| GridData.VERTICAL_ALIGN_CENTER));

		bptab.setControl(composite);
	}

	private void createData(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Data");

		Composite composite = new Composite(tabFolder, SWT.NONE);

		bptab.setControl(composite);
	}

	private void createParametersMap(CTabFolder ctfolder) {
		Composite composite = new Composite(ctfolder, SWT.NONE);
		GridLayout layout = new GridLayout(10, false);
		layout.verticalSpacing = 1;
		layout.marginWidth = 1;
		layout.marginTop = 1;
		layout.marginBottom = 1;
		composite.setLayout(layout);

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText("Dataset");

		dsCombo = new Combo(composite, SWT.BORDER | SWT.READ_ONLY | SWT.SINGLE);
		dsCombo.setItems(new String[] { "main dataset" });

		Button newDataset = new Button(composite, SWT.PUSH);
		newDataset.setText("new");

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.HORIZONTAL
				| SWT.WRAP | SWT.RIGHT);
		prmItem = new ToolItem(toolBar, SWT.PUSH);
		prmItem.setText("Parameters");

		prmMapItem = new ToolItem(toolBar, SWT.PUSH);
		prmMapItem.setText("Parameters Map");

		int tabHeight = composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		tabHeight = Math.max(tabHeight, ctfolder.getTabHeight());
		ctfolder.setTabHeight(tabHeight);

		ctfolder.setTopRight(composite);
	}
}