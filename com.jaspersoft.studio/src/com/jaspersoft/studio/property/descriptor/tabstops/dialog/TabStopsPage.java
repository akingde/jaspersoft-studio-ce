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
package com.jaspersoft.studio.property.descriptor.tabstops.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.TabStop;
import net.sf.jasperreports.engine.type.TabStopAlignEnum;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
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

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.EnumHelper;

public class TabStopsPage extends WizardPage {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			TabStop tabstop = (TabStop) element;
			switch (columnIndex) {
			case 0:
				return Integer.toString(tabstop.getPosition());
			case 1:
				return tabstop.getAlignment().getName();
			}
			return ""; //$NON-NLS-1$
		}
	}

	private List<TabStop> value;
	private Table table;
	private TableViewer tableViewer;

	public List<TabStop> getValue() {
		return value;
	}

	@Override
	public void dispose() {
		// clear all properties
		// value = new ArrayList<TabStop>();
		// List<PropertyDTO> props = (List<PropertyDTO>) tableViewer.getInput();
		// for (PropertyDTO p : props) {
		//			if (p.getProperty() != null && !p.getProperty().equals("")) //$NON-NLS-1$
		// value.setProperty(p.getProperty(), p.getValue());
		// }
		super.dispose();
	}

	public void setValue(List<TabStop> value) {
		this.value = value;
		if (value == null) {
			value = new ArrayList<TabStop>();
		}
		if (table != null)
			fillTable(table, value);
	}

	protected TabStopsPage(String pageName) {
		super(pageName);
		setTitle("Tab Stops");
		setDescription("Tab Stops");
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

		new NewButton().createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				TabStop p = new TabStop();
				p.setPosition(20);
				p.setAlignment(TabStopAlignEnum.LEFT);
				return p;
			}

		});

		new DeleteButton().createDeleteButton(bGroup, tableViewer);
		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TLabelProvider());
		attachCellEditors(tableViewer, table);

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Position [px]");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Alignement");

		fillTable(table, value);
		for (int i = 0, n = column.length; i < n; i++) {
			column[i].pack();
		}

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, true));
		tlayout.addColumnData(new ColumnWeightData(50, true));
		table.setLayout(tlayout);
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("POSITION")) //$NON-NLS-1$
					return true;
				if (property.equals("ALIGNEMENT")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				TabStop prop = (TabStop) element;
				if ("POSITION".equals(property)) //$NON-NLS-1$
					return Integer.toString(prop.getPosition());

				if ("ALIGNEMENT".equals(property)) { //$NON-NLS-1$
					return EnumHelper.getValue(prop.getAlignment(), 1, false);
				}
				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				setErrorMessage(null);
				setMessage(getDescription());
				TableItem tableItem = (TableItem) element;
				TabStop data = (TabStop) tableItem.getData();
				if ("POSITION".equals(property)) { //$NON-NLS-1$
					try {
						data.setPosition(new Integer((String) value));
					} catch (NumberFormatException e) {
						setErrorMessage("Number format incorect");
					}

				} else if ("ALIGNEMENT".equals(property)) { //$NON-NLS-1$
					data.setAlignment((TabStopAlignEnum) EnumHelper.getSetValue(TabStopAlignEnum.values(), value, 1, false));
				}
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent),
				new ComboBoxCellEditor(parent, EnumHelper.getEnumNames(TabStopAlignEnum.values(), NullEnum.NOTNULL)) });
		viewer.setColumnProperties(new String[] { "POSITION", "ALIGNEMENT" }); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void fillTable(Table table, Object val) {
		tableViewer.setInput(val);
	}

}
