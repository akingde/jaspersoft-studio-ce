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
package com.jaspersoft.studio.property.dataset.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;

public class FieldsTable {
	private TableViewer tviewer;
	private Table wtable;
	private Composite composite;
	private JRDesignDataset dataset;
	private Color background;

	public FieldsTable(Composite parent, JRDesignDataset dataset, Color background) {
		this.dataset = dataset;
		this.background = background;
		createControl(parent);
	}

	public Composite getControl() {
		return composite;
	}

	private void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setBackground(background);
		composite.setBackgroundMode(SWT.INHERIT_FORCE);

		wtable = new Table(composite, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		wtable.setLayoutData(gd);
		wtable.setHeaderVisible(true);

		TableColumn[] col = new TableColumn[3];
		col[0] = new TableColumn(wtable, SWT.NONE);
		col[0].setText("Field Name");
		col[0].pack();

		col[1] = new TableColumn(wtable, SWT.NONE);
		col[1].setText("Class Type");
		col[1].pack();

		col[2] = new TableColumn(wtable, SWT.NONE);
		col[2].setText("Description");
		col[2].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		tlayout.addColumnData(new ColumnWeightData(100, false));
		tlayout.addColumnData(new ColumnWeightData(100, false));
		wtable.setLayout(tlayout);

		tviewer = new TableViewer(wtable);
		tviewer.setContentProvider(new ListContentProvider());
		tviewer.setLabelProvider(new TLabelProvider());
		attachCellEditors(tviewer, wtable);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createNewButtons(bGroup, tviewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				JRDesignField f = new JRDesignField();
				f.setName(getName());
				f.setValueClass(String.class);
				return f;
			}

			private String getName() {
				List<JRDesignField> list = (List<JRDesignField>) tviewer.getInput();
				String name = "Field";
				boolean match = false;
				String tmp = name;
				for (int i = 1; i < 100000; i++) {
					tmp = ModelUtils.getNameFormat(name, i);

					for (JRDesignField f : list) {
						match = f.getName().equals(tmp);
						if (match)
							break;
					}
					if (!match)
						break;
				}
				return tmp;
			}
		});
		new DeleteButton().createDeleteButton(bGroup, tviewer);

		new ListOrderButtons().createOrderButtons(bGroup, tviewer);

		List<JRField> fields = dataset.getFieldsList();
		if (fields == null)
			fields = new ArrayList<JRField>();
		setFields(fields);
	}

	public <T extends JRField> void setFields(List<T> fields) {
		tviewer.setInput(fields);
		tviewer.refresh();
	}

	public List<JRDesignField> getFields() {
		return (List<JRDesignField>) tviewer.getInput();
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("NAME")) //$NON-NLS-1$
					return true;
				if (property.equals("TYPE")) //$NON-NLS-1$
					return true;
				if (property.equals("DESCRIPTION")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				JRDesignField prop = (JRDesignField) element;
				if ("NAME".equals(property)) //$NON-NLS-1$
					return prop.getName();
				if ("TYPE".equals(property)) //$NON-NLS-1$
					return prop.getValueClassName();
				if ("DESCRIPTION".equals(property)) //$NON-NLS-1$
					return Misc.nvl(prop.getDescription(), "");

				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				JRDesignField field = (JRDesignField) tableItem.getData();
				if ("NAME".equals(property)) { //$NON-NLS-1$
					List<JRDesignField> list = (List<JRDesignField>) tviewer.getInput();
					boolean exists = false;
					for (JRDesignField f : list) {
						exists = f.getName().equals(value);
						if (exists)
							break;
					}
					if (!exists)
						field.setName((String) value);
				} else if ("TYPE".equals(property)) { //$NON-NLS-1$
					field.setValueClassName((String) value);
				} else if ("DESCRIPTION".equals(property)) { //$NON-NLS-1$
					field.setDescription((String) value);
				}
				tviewer.update(element, new String[] { property });
				tviewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent), new ClassTypeCellEditor(parent),
				new TextCellEditor(parent) });
		viewer.setColumnProperties(new String[] { "NAME", "TYPE", "DESCRIPTION" }); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public String getColumnText(Object element, int columnIndex) {
			JRDesignField field = (JRDesignField) element;
			switch (columnIndex) {
			case 0:
				return field.getName();
			case 1:
				return Misc.nvl(field.getValueClassName(), "");
			case 2:
				return Misc.nvl(field.getDescription(), "");
			}
			return ""; //$NON-NLS-1$
		}

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
	}
}
