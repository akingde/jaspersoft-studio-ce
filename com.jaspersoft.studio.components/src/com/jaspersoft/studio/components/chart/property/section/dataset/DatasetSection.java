/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chart.property.section.dataset;

import java.sql.Connection;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.property.dataset.DatasetRunWidget;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterDTO;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.property.section.widgets.SPIncrementType;
import com.jaspersoft.studio.property.section.widgets.SPRCombo;
import com.jaspersoft.studio.property.section.widgets.SPResetType;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetSection extends AbstractSection {
	private SPExpression expr;
	private SPExpression connection;

	protected Composite composite;
	private SPIncrementType incrementType;
	private SPResetType resetType;
	protected Composite parent;
	private SPRCombo dsCombo;
	private Button params;
	private Button paramMap;
	private Combo drcombo;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		parent.setBackground(parent.getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));
		this.parent = parent;

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		CLabel lbl = getWidgetFactory().createCLabel(composite,
				"Increment Type:", SWT.RIGHT); //$NON-NLS-1$
		RowData rd = new RowData();
		rd.width = 120;
		lbl.setLayoutData(rd);

		incrementType = new SPIncrementType(composite, this,
				JRDesignElementDataset.PROPERTY_INCREMENT_TYPE,
				JRDesignElementDataset.PROPERTY_INCREMENT_GROUP,
				"Increment type");

		getWidgetFactory()
				.createCLabel(composite, "Increment When:", SWT.RIGHT);

		Composite cmp = new Composite(composite, SWT.NONE);
		GridLayout gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());

		expr = new SPExpression(cmp, this,
				JRDesignElementDataset.PROPERTY_INCREMENT_WHEN_EXPRESSION);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite,
				"Reset Type:", SWT.RIGHT); //$NON-NLS-1$
		rd = new RowData();
		rd.width = 120;
		lbl.setLayoutData(rd);

		resetType = new SPResetType(composite, this,
				JRDesignElementDataset.PROPERTY_RESET_TYPE,
				JRDesignElementDataset.PROPERTY_RESET_GROUP, "Reset type");

		Section sectioncmp = getWidgetFactory().createSection(
				parent,
				ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
						| ExpandableComposite.EXPANDED);
		sectioncmp.setText("Dataset Run");

		Composite parentsec = new Composite(sectioncmp, SWT.NONE);
		parentsec.setBackground(parent.getBackground());
		parentsec.setLayout(new RowLayout(SWT.VERTICAL));

		sectioncmp.setClient(parentsec);

		composite = new Composite(parentsec, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite,
				"Dataset Name:", SWT.RIGHT); //$NON-NLS-1$
		rd = new RowData();
		rd.width = 120;
		lbl.setLayoutData(rd);

		dsCombo = new SPRCombo(composite, this,
				JRDesignElementDataset.PROPERTY_DATASET_RUN, "Dataset run",
				new String[] {}) {
			@Override
			protected void changeProperty(AbstractSection section,
					String property) {
				int dsind = rcombo.getSelectionIndex();
				if (dsind == 0) {
					section.changeProperty(
							JRDesignElementDataset.PROPERTY_DATASET_RUN, null);
				} else {
					MDatasetRun mdr = (MDatasetRun) getElement()
							.getPropertyValue(
									JRDesignElementDataset.PROPERTY_DATASET_RUN);
					JRDesignDatasetRun dr = getDatasetRun();
					dr.setDatasetName(rcombo.getItem(dsind));

					section.changeProperty(
							JRDesignElementDataset.PROPERTY_DATASET_RUN,
							new MDatasetRun(dr, mdr.getJasperDesign()));

				}
				setDatasetEnabled(dsind != 0);
			}
		};

		composite = new Composite(parentsec, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite, "", SWT.RIGHT); //$NON-NLS-1$
		rd = new RowData();
		rd.width = 120;
		lbl.setLayoutData(rd);

		params = new Button(composite, SWT.PUSH | SWT.FLAT);
		params.setText("Parameters");
		params.addSelectionListener(new SelectionListener() {
			private ParameterDTO prmDTO;

			public void widgetSelected(SelectionEvent e) {
				MDatasetRun mdr = (MDatasetRun) getElement().getPropertyValue(
						JRDesignElementDataset.PROPERTY_DATASET_RUN);
				JRDesignDatasetRun datasetRun = (JRDesignDatasetRun) mdr
						.getValue();
				if (prmDTO == null) {
					prmDTO = new ParameterDTO();
					prmDTO.setJasperDesign(getElement().getJasperDesign());
					prmDTO.setValue(datasetRun.getParameters());
				}

				ParameterEditor wizard = new ParameterEditor();
				wizard.setValue(prmDTO);
				WizardDialog dialog = new WizardDialog(params.getShell(),
						wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					prmDTO = wizard.getValue();

					changePropertyOn(JRDesignSubreport.PROPERTY_PARAMETERS,
							prmDTO, mdr);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		paramMap = new Button(composite, SWT.PUSH | SWT.FLAT);
		paramMap.setText("Parameters Map");
		paramMap.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				MDatasetRun mdr = (MDatasetRun) getElement().getPropertyValue(
						JRDesignElementDataset.PROPERTY_DATASET_RUN);
				JRDesignDatasetRun datasetRun = (JRDesignDatasetRun) mdr
						.getValue();

				JRExpressionEditor wizard = new JRExpressionEditor();
				wizard.setValue((JRDesignExpression) datasetRun
						.getParametersMapExpression());
				WizardDialog dialog = new WizardDialog(paramMap.getShell(),
						wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					changePropertyOn(
							JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION,
							wizard.getValue(), mdr);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		composite = new Composite(parentsec, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite, "", SWT.RIGHT); //$NON-NLS-1$
		rd = new RowData();
		rd.width = 120;
		lbl.setLayoutData(rd);

		drcombo = new Combo(composite, SWT.READ_ONLY);
		drcombo.setItems(DatasetRunWidget.ITEMS);

		drcombo.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int sel = drcombo.getSelectionIndex();
				MDatasetRun mdr = (MDatasetRun) getElement().getPropertyValue(
						JRDesignElementDataset.PROPERTY_DATASET_RUN);
				JRDesignDatasetRun dr = getDatasetRun();

				switch (sel) {
				case 0:
					dr = setNoConnection(dr);
					break;
				case 1:
					dr = setConnection(dr, ""); //$NON-NLS-1$
					break;
				case 2:
					dr = setDatasource(dr, "");//$NON-NLS-1$
					break;
				case 3:
					dr = setConnection(dr, "$P{REPORT_CONNECTION}"); //$NON-NLS-1$
					break;
				case 4:
					dr = setDatasource(dr,
							"new net.sf.jasperreports.engine.JREmptyDataSource()");//$NON-NLS-1$
					break;

				}
				changeProperty(JRDesignElementDataset.PROPERTY_DATASET_RUN,
						new MDatasetRun(dr, mdr.getJasperDesign()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		composite = new Composite(parentsec, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite, "", SWT.RIGHT); //$NON-NLS-1$
		rd = new RowData();
		rd.width = 120;
		lbl.setLayoutData(rd);

		cmp = new Composite(composite, SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());

		connection = new SPExpression(cmp, this,
				JRDesignElementDataset.PROPERTY_INCREMENT_WHEN_EXPRESSION);

	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {

			expr.setData((JRDesignExpression) element
					.getPropertyValue(JRDesignElementDataset.PROPERTY_INCREMENT_WHEN_EXPRESSION));

			JRDesignElementDataset chart = (JRDesignElementDataset) element
					.getValue();
			JasperDesign jasperDesign = element.getJasperDesign();
			JRDataset dataset = null;
			JRDatasetRun datasetRun = chart.getDatasetRun();
			if (datasetRun != null) {
				String dsname = datasetRun.getDatasetName();
				dataset = jasperDesign.getDatasetMap().get(dsname);
			}
			if (dataset == null)
				dataset = jasperDesign.getMainDataset();

			incrementType
					.setData(
							(Integer) element
									.getPropertyValue(JRDesignElementDataset.PROPERTY_INCREMENT_TYPE),
							(String) element
									.getPropertyValue(JRDesignElementDataset.PROPERTY_INCREMENT_GROUP),
							SPIncrementType.getItems(dataset));
			resetType
					.setData(
							(Integer) element
									.getPropertyValue(JRDesignElementDataset.PROPERTY_RESET_TYPE),
							(String) element
									.getPropertyValue(JRDesignElementDataset.PROPERTY_RESET_GROUP),
							SPResetType.getItems(dataset));

			String[] items = ModelUtils.getDataSets(jasperDesign, true);
			int dsindex = 0;
			if (datasetRun != null) {
				for (int i = 0; i < items.length; i++) {
					if (items[i].equals(datasetRun.getDatasetName())) {
						dsindex = i;
						break;
					}
				}
			}
			dsCombo.setData(dsindex, items);
			setDatasetEnabled(dsindex != 0);
			if (datasetRun != null) {
				if (datasetRun.getConnectionExpression() == null
						&& datasetRun.getDataSourceExpression() == null) {
					connection.setEnabled(false);
					drcombo.select(0);
				} else if (datasetRun.getConnectionExpression() != null) {
					connection.setEnabled(true);
					drcombo.select(1);
				} else if (datasetRun.getDataSourceExpression() != null) {
					connection.setEnabled(true);
					drcombo.select(2);
				}
			}
			if (datasetRun.getConnectionExpression() != null)
				connection.setData((JRDesignExpression) datasetRun.getConnectionExpression());
			else if (datasetRun.getDataSourceExpression() != null)
				connection.setData((JRDesignExpression) datasetRun.getDataSourceExpression());
		}
		isRefreshing = false;
	}

	private void setDatasetEnabled(boolean enabled) {
		paramMap.setEnabled(enabled);
		params.setEnabled(enabled);
		drcombo.setEnabled(enabled);
		connection.setEnabled(enabled);
	}

	private JRDesignDatasetRun setNoConnection(JRDesignDatasetRun datasetrun) {
		if (datasetrun != null) {
			datasetrun.setConnectionExpression(null);
			datasetrun.setDataSourceExpression(null);
		}
		return datasetrun;
	}

	private JRDesignDatasetRun setDatasource(JRDesignDatasetRun datasetrun,
			String exTxt) {
		if (datasetrun != null) {
			JRDesignExpression jde = (JRDesignExpression) datasetrun
					.getDataSourceExpression();
			if (jde == null)
				jde = new JRDesignExpression();
			jde.setValueClass(JRDataSource.class);
			jde.setText(exTxt);
			datasetrun.setConnectionExpression(null);
			datasetrun.setDataSourceExpression(jde);
		}
		return datasetrun;
	}

	private JRDesignDatasetRun setConnection(JRDesignDatasetRun datasetrun,
			String exTxt) {
		if (datasetrun != null) {
			JRDesignExpression jde = (JRDesignExpression) datasetrun
					.getConnectionExpression();
			if (jde == null)
				jde = new JRDesignExpression();
			jde.setValueClass(Connection.class);
			jde.setText(exTxt);
			datasetrun.setConnectionExpression(jde);
			datasetrun.setDataSourceExpression(null);
		}
		return datasetrun;
	}

	private JRDesignDatasetRun getDatasetRun() {
		MDatasetRun mdr = (MDatasetRun) getElement().getPropertyValue(
				JRDesignElementDataset.PROPERTY_DATASET_RUN);
		JRDesignDatasetRun dsrun = (JRDesignDatasetRun) mdr.getValue();
		return (JRDesignDatasetRun) dsrun.clone();
	}

	@Override
	public boolean isDisposed() {
		return composite.isDisposed();
	}
}
