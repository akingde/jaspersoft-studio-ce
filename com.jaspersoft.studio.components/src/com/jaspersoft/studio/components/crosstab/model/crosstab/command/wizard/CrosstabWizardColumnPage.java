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
package com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsPage;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;

public class CrosstabWizardColumnPage extends WizardFieldsPage {
	private static final String F_CALCULATION = "CALCULATION";
	private static final String F_TOTALPOSITION = "TOTALPOSITION";
	private static final String F_ORDER = "ORDER";
	private static final String F_NAME = "NAME";

	private final class TLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			Wrapper w = (Wrapper) element;
			JRDesignCrosstabColumnGroup m = (JRDesignCrosstabColumnGroup) w
					.getValue();
			String txt = m.getBucket().getExpression().getText();
			switch (columnIndex) {
			case 0:
				if (txt.startsWith("$F{")) //$NON-NLS-1$
					return JaspersoftStudioPlugin.getImage(MField
							.getIconDescriptor().getIcon16());
				if (txt.startsWith("$P{")) //$NON-NLS-1$
					return JaspersoftStudioPlugin.getImage(MParameter
							.getIconDescriptor().getIcon16());
				if (txt.startsWith("$V{")) //$NON-NLS-1$
					return JaspersoftStudioPlugin.getImage(MVariable
							.getIconDescriptor().getIcon16());
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			Wrapper w = (Wrapper) element;
			JRDesignCrosstabColumnGroup m = (JRDesignCrosstabColumnGroup) w
					.getValue();
			JRCrosstabBucket bucket = m.getBucket();
			String txt = bucket.getExpression().getText();
			switch (columnIndex) {
			case 0:
				return txt.substring(3, txt.length() - 1);
			case 1:
				return bucket.getOrderValue().getName();
			case 2:
				return m.getTotalPositionValue().getName();
			case 3:
				return w.getCalculation().getName();
			}
			return ""; //$NON-NLS-1$
		}
	}

	protected CrosstabWizardColumnPage() {
		super("crosstabcolumnpage"); //$NON-NLS-1$
		setTitle(Messages.CrosstabWizardColumnPage_columns);
		setImageDescriptor(Activator
				.getImageDescriptor("icons/wizard_columns.png"));//$NON-NLS-1$
		setDescription(Messages.CrosstabWizardColumnPage_description);
	}

	@Override
	public void setFields(List<?> inFields) {
		List<Wrapper> nlist = new ArrayList<Wrapper>();
		for (Object obj : inFields)
			nlist.add(new Wrapper(obj));
		super.setFields(nlist);
	}

	@Override
	public List<Object> getInFields() {
		List<Object> wlist = super.getInFields();
		if (wlist != null) {
			List<Object> out = new ArrayList<Object>(wlist.size());
			for (Object w : wlist)
				out.add(((Wrapper) w).getValue());
			return out;
		}
		return null;
	}

	@Override
	public List<Object> getFields() {
		List<Object> wlist = super.getFields();
		if (wlist != null) {
			List<Object> out = new ArrayList<Object>(wlist.size());
			for (Object w : wlist)
				out.add(((Wrapper) w).getValue());
			return out;
		}
		return null;
	}

	@Override
	protected void setLabelProvider(TableViewer tableViewer) {
		tableViewer.setLabelProvider(new TLabelProvider());
	}

	@Override
	protected void createColumns() {
		TableColumn[] col = new TableColumn[4];
		col[0] = new TableColumn(rightTable, SWT.NONE);
		col[0].setText(Messages.common_fields);
		col[0].pack();

		col[1] = new TableColumn(rightTable, SWT.NONE);
		col[1].setText(Messages.common_order);
		col[1].pack();

		col[2] = new TableColumn(rightTable, SWT.NONE);
		col[2].setText(Messages.common_total_position);
		col[2].pack();

		col[3] = new TableColumn(rightTable, SWT.NONE);
		col[3].setText(Messages.common_calculation);
		col[3].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(30, false));
		tlayout.addColumnData(new ColumnWeightData(20, false));
		tlayout.addColumnData(new ColumnWeightData(20, false));
		tlayout.addColumnData(new ColumnWeightData(30, false));
		rightTable.setLayout(tlayout);
	}

	@Override
	protected void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals(F_ORDER)) //$NON-NLS-1$
					return true;
				if (property.equals(F_TOTALPOSITION)) //$NON-NLS-1$
					return true;
				if (property.equals(F_CALCULATION)) { //$NON-NLS-1$
					Wrapper w = (Wrapper) element;
					JRDesignCrosstabColumnGroup rg = (JRDesignCrosstabColumnGroup) w
							.getValue();
					if (Date.class.isAssignableFrom(rg.getBucket()
							.getValueClass()))
						return true;
				}
				return false;
			}

			public Object getValue(Object element, String property) {
				Wrapper w = (Wrapper) element;
				JRDesignCrosstabColumnGroup prop = (JRDesignCrosstabColumnGroup) w
						.getValue();
				if (F_NAME.equals(property)) //$NON-NLS-1$
					return ((TLabelProvider) viewer.getLabelProvider())
							.getColumnText(element, 1);

				if (F_ORDER.equals(property)) //$NON-NLS-1$
					return EnumHelper.getValue(
							prop.getBucket().getOrderValue(), 1, false);

				if (F_TOTALPOSITION.equals(property)) //$NON-NLS-1$
					return EnumHelper.getValue(prop.getTotalPositionValue(), 0,
							false);

				if (F_CALCULATION.equals(property)) //$NON-NLS-1$
					return w.getCalculation().getValue();

				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				setErrorMessage(null);
				setMessage(getDescription());
				Wrapper w = (Wrapper) tableItem.getData();
				JRDesignCrosstabColumnGroup data = (JRDesignCrosstabColumnGroup) w
						.getValue();
				JRDesignCrosstabBucket bucket = (JRDesignCrosstabBucket) data
						.getBucket();
				if (F_ORDER.equals(property)) { //$NON-NLS-1$
					bucket.setOrder((SortOrderEnum) EnumHelper.getSetValue(
							SortOrderEnum.values(), value, 1, false));
				} else if (F_TOTALPOSITION.equals(property)) { //$NON-NLS-1$
					data.setTotalPosition((CrosstabTotalPositionEnum) EnumHelper
							.getSetValue(CrosstabTotalPositionEnum.values(),
									value, 0, false));
				} else if (F_CALCULATION.equals(property)) { //$NON-NLS-1$
					AgregationFunctionEnum function = AgregationFunctionEnum
							.getByValue((Integer) value);
					w.setCalculation(function);
					CrosstabWizard.setBucketExpression(bucket, w.getOldExpText(), function);
				}
				viewer.update(element, new String[] { property });
				viewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] {
				new TextCellEditor(parent),
				new ComboBoxCellEditor(parent, EnumHelper.getEnumNames(
						SortOrderEnum.values(), NullEnum.NOTNULL)),
				new ComboBoxCellEditor(parent, EnumHelper.getEnumNames(
						CrosstabTotalPositionEnum.values(), NullEnum.NOTNULL)),
				new ComboBoxCellEditor(parent, AgregationFunctionEnum
						.getStringValues()) });
		viewer.setColumnProperties(new String[] { F_NAME, F_ORDER,
				F_TOTALPOSITION, F_CALCULATION });
	}
}
