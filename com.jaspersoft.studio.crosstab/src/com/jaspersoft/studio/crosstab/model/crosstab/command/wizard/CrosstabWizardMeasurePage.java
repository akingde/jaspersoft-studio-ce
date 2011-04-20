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
package com.jaspersoft.studio.crosstab.model.crosstab.command.wizard;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.crosstab.Activator;
import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.wizards.dataset.WizardFieldsPage;

public class CrosstabWizardMeasurePage extends WizardFieldsPage {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			JRDesignCrosstabMeasure m = (JRDesignCrosstabMeasure) element;
			String txt = m.getValueExpression().getText();
			switch (columnIndex) {
			case 0:
				if (txt.startsWith("$F{")) //$NON-NLS-1$
					return JaspersoftStudioPlugin.getImage(MField.getIconDescriptor().getIcon16());
				if (txt.startsWith("$P{")) //$NON-NLS-1$
					return JaspersoftStudioPlugin.getImage(MParameter.getIconDescriptor().getIcon16());
				if (txt.startsWith("$V{")) //$NON-NLS-1$
					return JaspersoftStudioPlugin.getImage(MVariable.getIconDescriptor().getIcon16());
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			JRDesignCrosstabMeasure m = (JRDesignCrosstabMeasure) element;
			String txt = m.getValueExpression().getText();
			switch (columnIndex) {
			case 0:
				return txt.substring(3, txt.length() - 1);
			case 1:
				return m.getCalculationValue().getName();
			}
			return ""; //$NON-NLS-1$
		}
	}

	protected CrosstabWizardMeasurePage() {
		super("crosstabmeasurepage"); //$NON-NLS-1$
		setTitle(Messages.CrosstabWizardMeasurePage_measures);
		setImageDescriptor(Activator.getImageDescriptor("icons/wizard_details.png"));//$NON-NLS-1$
		setDescription(Messages.CrosstabWizardMeasurePage_description);
	}

	protected void setLabelProvider(TableViewer tableViewer) {
		tableViewer.setLabelProvider(new TLabelProvider());
	}

	protected void createColumns() {
		TableColumn[] col = new TableColumn[2];
		col[0] = new TableColumn(rightTable, SWT.NONE);
		col[0].setText(Messages.common_fields);
		col[0].pack();

		col[1] = new TableColumn(rightTable, SWT.NONE);
		col[1].setText(Messages.common_calculation);
		col[1].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, false));
		tlayout.addColumnData(new ColumnWeightData(50, false));
		rightTable.setLayout(tlayout);
	}

	protected void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("CALCULATION")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				JRDesignCrosstabMeasure prop = (JRDesignCrosstabMeasure) element;
				if ("NAME".equals(property)) //$NON-NLS-1$
					return ((TLabelProvider) viewer.getLabelProvider()).getColumnText(element, 1);

				if ("CALCULATION".equals(property)) //$NON-NLS-1$
					return EnumHelper.getValue(prop.getCalculationValue(), 0, false);

				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				setErrorMessage(null);
				setMessage(getDescription());
				JRDesignCrosstabMeasure data = (JRDesignCrosstabMeasure) tableItem.getData();
				if ("CALCULATION".equals(property)) { //$NON-NLS-1$
					data.setCalculation((CalculationEnum) EnumHelper.getSetValue(CalculationEnum.values(), value, 0, false));
				}
				viewer.update(element, new String[] { property });
				viewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent),
				new ComboBoxCellEditor(parent, EnumHelper.getEnumNames(CalculationEnum.values(), NullEnum.NOTNULL)) });
		viewer.setColumnProperties(new String[] { "NAME", "CALCULATION" }); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
