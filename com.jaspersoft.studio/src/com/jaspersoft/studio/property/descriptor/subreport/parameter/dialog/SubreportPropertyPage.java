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
package com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Arrays;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionCellEditor;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

public class SubreportPropertyPage extends WizardPage {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			JRSubreportParameter sp = (JRSubreportParameter) element;
			switch (columnIndex) {
			case 0:
				return sp.getName();
			case 1:
				if (sp != null && sp.getExpression() != null)
					return sp.getExpression().getText();
			}
			return ""; //$NON-NLS-1$
		}
	}

	private JRSubreportParameter[] value;
	private Table table;
	private TableViewer tableViewer;

	public JRSubreportParameter[] getValue() {
		if (!tableViewer.getControl().isDisposed()) {
			List<JRSubreportParameter> lst = (List<JRSubreportParameter>) tableViewer.getInput();
			value = lst.toArray(new JRSubreportParameter[lst.size()]);
		}
		return value;
	}

	@Override
	public void dispose() {
		getValue();
		super.dispose();
	}

	public void setValue(JRSubreportParameter[] value) {
		this.value = value;
		if (table != null)
			fillTable(table);
	}

	public SubreportPropertyPage() {
		this("subreportpage");
	}

	public SubreportPropertyPage(String pageName) {
		super(pageName);
		setTitle(Messages.common_subreport_parameters);
		setDescription(Messages.SubreportPropertyPage_description);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		setControl(composite);

		buildTable(composite);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 400;
		table.setLayoutData(gd);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createOrderButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input) {
				JRDesignSubreportParameter param = new JRDesignSubreportParameter();
				int i = 0;
				String name = "NEW_PARAMETER";//$NON-NLS-1$
				while (getName(input, name, i) == null)
					i++;
				name += "_" + i;
				param.setName(name);
				JRDesignExpression expression = new JRDesignExpression();
				expression.setValueClassName(Object.class.getName());
				expression.setText("");
				param.setExpression(expression);

				return param;
			}

			private String getName(List<?> input, String name, int i) {
				name += "_" + i;
				for (Object dto : input) {
					JRSubreportParameter prm = (JRSubreportParameter) dto;
					if (prm.getName() != null && prm.getName().trim().equals(name)) {
						return null;
					}
				}
				return name;
			}

		});

		new DeleteButton().createOrderButtons(bGroup, tableViewer);

		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TLabelProvider());
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.common_name);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.common_expression);

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

		fillTable(table);
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("VALUE")) //$NON-NLS-1$
					return true;
				if (property.equals("NAME")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				JRSubreportParameter prop = (JRSubreportParameter) element;
				if ("VALUE".equals(property)) //$NON-NLS-1$
					if (prop.getExpression() != null)
						return new MExpression(prop.getExpression());
				if ("NAME".equals(property)) { //$NON-NLS-1$
					return prop.getName();
				}
				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				setErrorMessage(null);
				setMessage(getDescription());
				JRDesignSubreportParameter data = (JRDesignSubreportParameter) tableItem.getData();
				if ("VALUE".equals(property)) { //$NON-NLS-1$
					if (value instanceof MExpression) {
						JRExpression e = (JRExpression) ((MExpression) value).getValue();
						data.setExpression(e);
					}
				}
				if ("NAME".equals(property)) { //$NON-NLS-1$
					List<JRDesignSubreportParameter> plist = (List<JRDesignSubreportParameter>) tableViewer.getInput();
					for (JRDesignSubreportParameter p : plist) {
						if (p != data && p.getName() != null && p.getName().equals(value)) {
							setErrorMessage(Messages.common_error_message_unique_properties);
							return;
						}
					}
					if (value == null || ((String) value).trim().equals("")) {
						setErrorMessage("Properties must have non empty string name.");
						return;
					}
					data.setName((String) value);
				}
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent), new JRExpressionCellEditor(parent) });
		viewer.setColumnProperties(new String[] { "NAME", "VALUE" }); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void fillTable(Table table) {
		if (value != null) {
			List<JRSubreportParameter> plist = new ArrayList<JRSubreportParameter>(Arrays.asList(value));

			tableViewer.setInput(plist);
		}
	}

}
