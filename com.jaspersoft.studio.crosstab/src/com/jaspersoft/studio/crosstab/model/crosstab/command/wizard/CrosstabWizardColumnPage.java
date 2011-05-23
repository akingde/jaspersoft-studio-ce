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

import net.sf.jasperreports.crosstabs.JRCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.type.SortOrderEnum;

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
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsPage;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;

public class CrosstabWizardColumnPage extends WizardFieldsPage {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			JRDesignCrosstabColumnGroup m = (JRDesignCrosstabColumnGroup) element;
			String txt = m.getBucket().getExpression().getText();
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
			JRDesignCrosstabColumnGroup m = (JRDesignCrosstabColumnGroup) element;
			JRCrosstabBucket bucket = m.getBucket();
			String txt = bucket.getExpression().getText();
			switch (columnIndex) {
			case 0:
				return txt.substring(3, txt.length() - 1);
			case 1:
				return bucket.getOrderValue().getName();
			case 2:
				return m.getTotalPositionValue().getName();
			}
			return ""; //$NON-NLS-1$
		}
	}

	protected CrosstabWizardColumnPage() {
		super("crosstabcolumnpage"); //$NON-NLS-1$
		setTitle(Messages.CrosstabWizardColumnPage_columns);
		setImageDescriptor(Activator.getImageDescriptor("icons/wizard_columns.png"));//$NON-NLS-1$
		setDescription(Messages.CrosstabWizardColumnPage_description);
	}

	protected void setLabelProvider(TableViewer tableViewer) {
		tableViewer.setLabelProvider(new TLabelProvider());
	}

	protected void createColumns() {
		TableColumn[] col = new TableColumn[3];
		col[0] = new TableColumn(rightTable, SWT.NONE);
		col[0].setText(Messages.common_fields);
		col[0].pack();

		col[1] = new TableColumn(rightTable, SWT.NONE);
		col[1].setText(Messages.common_order);
		col[1].pack();

		col[2] = new TableColumn(rightTable, SWT.NONE);
		col[2].setText(Messages.common_total_position);
		col[2].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(40, false));
		tlayout.addColumnData(new ColumnWeightData(30, false));
		tlayout.addColumnData(new ColumnWeightData(30, false));
		rightTable.setLayout(tlayout);
	}

	protected void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("ORDER")) //$NON-NLS-1$
					return true;
				if (property.equals("TOTALPOSITION")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				JRDesignCrosstabColumnGroup prop = (JRDesignCrosstabColumnGroup) element;
				if ("NAME".equals(property)) //$NON-NLS-1$
					return ((TLabelProvider) viewer.getLabelProvider()).getColumnText(element, 1);

				if ("ORDER".equals(property)) //$NON-NLS-1$
					return EnumHelper.getValue(prop.getBucket().getOrderValue(), 1, false);

				if ("TOTALPOSITION".equals(property)) //$NON-NLS-1$
					return EnumHelper.getValue(prop.getTotalPositionValue(), 0, false);

				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				setErrorMessage(null);
				setMessage(getDescription());
				JRDesignCrosstabColumnGroup data = (JRDesignCrosstabColumnGroup) tableItem.getData();
				JRDesignCrosstabBucket bucket = (JRDesignCrosstabBucket) data.getBucket();
				if ("ORDER".equals(property)) { //$NON-NLS-1$
					bucket.setOrder((SortOrderEnum) EnumHelper.getSetValue(SortOrderEnum.values(), value, 1, false));
				} else if ("TOTALPOSITION".equals(property)) { //$NON-NLS-1$
					data.setTotalPosition((CrosstabTotalPositionEnum) EnumHelper.getSetValue(CrosstabTotalPositionEnum.values(),
							value, 0, false));
				}
				viewer.update(element, new String[] { property });
				viewer.refresh();
			}
		});

		viewer
				.setCellEditors(new CellEditor[] {
						new TextCellEditor(parent),
						new ComboBoxCellEditor(parent, EnumHelper.getEnumNames(SortOrderEnum.values(), NullEnum.NOTNULL)),
						new ComboBoxCellEditor(parent,
								EnumHelper.getEnumNames(CrosstabTotalPositionEnum.values(), NullEnum.NOTNULL)) });
		viewer.setColumnProperties(new String[] { "NAME", "ORDER", "TOTALPOSITION" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
