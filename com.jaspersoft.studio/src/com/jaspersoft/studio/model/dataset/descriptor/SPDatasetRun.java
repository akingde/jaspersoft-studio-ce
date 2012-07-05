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
package com.jaspersoft.studio.model.dataset.descriptor;

import java.sql.Connection;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.property.dataset.DatasetRunWidgetRadio;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterDTO;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.utils.ModelUtils;

public class SPDatasetRun extends ASPropertyWidget {
	private Combo dsetCombo;

	private Button params;
	private Button paramMap;

	private MDatasetRun mDataSet;

	private DatasetRunWidgetRadio dsRunWidget;

	public SPDatasetRun(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor, boolean alldatasets) {
		this(parent, section, pDescriptor);
		this.alldatasets = alldatasets;
	}

	public SPDatasetRun(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	protected void createComponent(Composite parent) {
		dsetCombo = section.getWidgetFactory().createCombo(parent, SWT.FLAT | SWT.READ_ONLY);
		dsetCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean en = dsetCombo.getSelectionIndex() > 0;
				setDatasetEnabled(en);
				changeProperty(section, pDescriptor.getId(), JRDesignDatasetRun.PROPERTY_DATASET_NAME, en ? dsetCombo.getText()
						: "");
			}
		});

		dsRunWidget = new DatasetRunWidgetRadio(parent) {
			@Override
			protected void setNoConnection() {
				changeProperty(section, pDescriptor.getId(), JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, "");
			}

			@Override
			protected void setDatasource(String exTxt) {
				if (datasetrun != null) {
					JRDesignExpression jde = (JRDesignExpression) datasetrun.getDataSourceExpression();
					if (jde == null)
						jde = new JRDesignExpression();
					jde.setValueClass(JRDataSource.class);
					jde.setText(exTxt);
					changeProperty(section, pDescriptor.getId(), JRDesignDatasetRun.PROPERTY_DATA_SOURCE_EXPRESSION, jde);
				}
			}

			@Override
			protected void setConnection(String exTxt) {
				if (datasetrun != null) {
					JRDesignExpression jde = (JRDesignExpression) datasetrun.getConnectionExpression();
					if (jde == null)
						jde = new JRDesignExpression();
					jde.setValueClass(Connection.class);
					jde.setText(exTxt);
					changeProperty(section, pDescriptor.getId(), JRDesignDatasetRun.PROPERTY_CONNECTION_EXPRESSION, jde);
				}
			}
		};
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		dsRunWidget.getControl().setLayoutData(gd);

		params = section.getWidgetFactory().createButton(parent, "Parameters", SWT.PUSH | SWT.FLAT);
		params.addSelectionListener(new SelectionAdapter() {

			private ParameterDTO prmDTO;

			@Override
			public void widgetSelected(SelectionEvent e) {
				JRDesignDatasetRun datasetRun = mDataSet.getValue();
				if (prmDTO == null) {
					prmDTO = new ParameterDTO();
					prmDTO.setJasperDesign(mDataSet.getJasperDesign());
					prmDTO.setValue(datasetRun.getParameters());
				}

				ParameterEditor wizard = new ParameterEditor();
				wizard.setValue(prmDTO);
				WizardDialog dialog = new WizardDialog(params.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					prmDTO = wizard.getValue();

					changeProperty(section, pDescriptor.getId(), JRDesignDatasetRun.PROPERTY_PARAMETERS, prmDTO);
				}
			}

		});

		paramMap = section.getWidgetFactory().createButton(parent, "Parameters Map", SWT.PUSH | SWT.FLAT);
		paramMap.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JRDesignDatasetRun datasetRun = mDataSet.getValue();

				JRExpressionEditor wizard = new JRExpressionEditor();
				wizard.setValue((JRDesignExpression) datasetRun.getParametersMapExpression());
				WizardDialog dialog = new WizardDialog(paramMap.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					changeProperty(section, pDescriptor.getId(), JRDesignDatasetRun.PROPERTY_PARAMETERS_MAP_EXPRESSION,
							wizard.getValue());
				}
			}

		});
	}

	private boolean alldatasets = true;
	private APropertyNode pnode;

	@Override
	public void setData(APropertyNode pnode, Object value) {
		this.pnode = pnode;
		this.mDataSet = (MDatasetRun) value;
		JasperDesign jasperDesign = pnode.getJasperDesign();
		JRDataset dataset = null;
		JRDesignDatasetRun datasetRun = null;
		if (mDataSet != null) {
			datasetRun = mDataSet.getValue();
			if (datasetRun != null) {
				String dsname = datasetRun.getDatasetName();
				dataset = jasperDesign.getDatasetMap().get(dsname);
			}
		}
		if (dataset == null)
			dataset = jasperDesign.getMainDataset();

		String[] items = ModelUtils.getDataSets(jasperDesign, alldatasets);
		int dsindex = 0;
		if (datasetRun != null) {
			for (int i = 0; i < items.length; i++) {
				if (items[i].equals(datasetRun.getDatasetName())) {
					dsindex = i;
					break;
				}
			}
		}
		dsetCombo.setItems(items);
		dsetCombo.select(dsindex);
		setDatasetEnabled(dsindex != 0);
		dsRunWidget.setData(datasetRun);

	}

	@Override
	public Control getControl() {
		return null;
	}

	private void changeProperty(AbstractSection section, Object property, Object prop, Object value) {
		JRDesignDatasetRun jDatasetRun = null;
		if (mDataSet == null && prop.equals(JRDesignDatasetRun.PROPERTY_DATASET_NAME)) {
			jDatasetRun = new JRDesignDatasetRun();
			jDatasetRun.setDatasetName((String) value);
			mDataSet = new MDatasetRun(jDatasetRun, pnode.getJasperDesign());
			dsRunWidget.setData(jDatasetRun);
			section.changeProperty(property, mDataSet);
		} else {
			jDatasetRun = mDataSet.getValue();

			section.changePropertyOn(prop, value, mDataSet);
			if (property != null) {
				mDataSet.setValue(null);
				mDataSet = new MDatasetRun(jDatasetRun, pnode.getJasperDesign());
				section.changePropertyOn(property, mDataSet, pnode);
			}
		}
	}

	private void setDatasetEnabled(boolean enabled) {
		paramMap.setEnabled(enabled);
		params.setEnabled(enabled);
		dsRunWidget.setEnabled(enabled);
	}

}