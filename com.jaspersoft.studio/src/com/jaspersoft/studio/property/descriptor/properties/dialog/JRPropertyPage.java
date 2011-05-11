/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRPropertiesMap;

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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxCellEditor;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

public class JRPropertyPage extends WizardPage {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return ((PropertyDTO) element).getProperty();
			case 1:
				return ((PropertyDTO) element).getValue();
			}
			return ""; //$NON-NLS-1$
		}
	}

	private JRPropertiesMap value;
	private Table table;
	private TableViewer tableViewer;
	private List<PropertyDTO> defaultProperties;

	public JRPropertiesMap getValue() {
		return value;
	}

	@Override
	public void dispose() {
		// clear all properties
		value = new JRPropertiesMap();
		List<PropertyDTO> props = (List<PropertyDTO>) tableViewer.getInput();
		for (PropertyDTO p : props) {
			if (p.getProperty() != null && !p.getProperty().equals("")) //$NON-NLS-1$
				value.setProperty(p.getProperty(), p.getValue());
		}
		super.dispose();
	}

	public void setValue(JRPropertiesMap value) {
		this.value = value;
		if (value == null) {
			value = new JRPropertiesMap();
		}
		if (table != null)
			fillTable(table);
	}

	protected JRPropertyPage(String pageName) {
		super(pageName);
		setTitle(Messages.common_properties);
		setDescription(Messages.JRPropertyPage_description);
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

			public Object newElement(List<?> input) {
				List<PropertyDTO> list = (List<PropertyDTO>) tableViewer.getInput();
				int i = 1;
				String name = "newproperty"; //$NON-NLS-1$
				while (getName(input, name, i) == null)
					i++;
				name += "_" + i; //$NON-NLS-1$

				PropertyDTO p = new PropertyDTO();
				p.setProperty(name);
				p.setValue("NEW_VALUE"); //$NON-NLS-1$
				return p;
			}

			private String getName(List<?> input, String name, int i) {
				name += "_" + i;
				for (Object dto : input) {
					PropertyDTO prm = (PropertyDTO) dto;
					if (prm.getProperty() != null && prm.getProperty().trim().equals(name)) {
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

		setColumnToolTip();

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.common_name);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.JRPropertyPage_value);

		fillTable(table);
		for (int i = 0, n = column.length; i < n; i++) {
			column[i].pack();
		}

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, true));
		tlayout.addColumnData(new ColumnWeightData(50, true));
		table.setLayout(tlayout);

		table.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (e.item instanceof TableItem) {
					setMessage(getDescription(((TableItem) e.item)));
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
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
				PropertyDTO prop = (PropertyDTO) element;
				if ("VALUE".equals(property)) //$NON-NLS-1$
					return prop.getValue();

				if ("NAME".equals(property)) { //$NON-NLS-1$
					return prop.getProperty();
				}
				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				PropertyDTO data = (PropertyDTO) tableItem.getData();
				if ("VALUE".equals(property)) { //$NON-NLS-1$
					data.setValue((String) value);
				} else if ("NAME".equals(property)) { //$NON-NLS-1$
					String str = (String) value;
					if (str == null || str.trim().equals("")) { //$NON-NLS-1$
						setErrorMessage(Messages.JRPropertyPage_error_message_property_must_not_be_empty);
						setPageComplete(false);
						return;
					}
					List<PropertyDTO> plist = (List<PropertyDTO>) tableViewer.getInput();
					for (PropertyDTO p : plist) {
						if (p != data && p.getProperty() != null && p.getProperty().equals(str)) {
							setErrorMessage(Messages.common_error_message_unique_properties);
							setPageComplete(false);
							return;
						}
					}
					data.setProperty(str);
				}
				setErrorMessage(null);
				setMessage(getDescription(tableItem));
				setPageComplete(true);
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] { new RWComboBoxCellEditor(parent, getDefaultPropertyItems()),
				new TextCellEditor(parent) });
		viewer.setColumnProperties(new String[] { "NAME", "VALUE" }); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private void fillTable(Table table) {
		List<PropertyDTO> props = new ArrayList<PropertyDTO>();
		String[] names = value.getPropertyNames();
		for (int i = 0; i < names.length; i++) {
			PropertyDTO p = new PropertyDTO();
			p.setProperty(names[i]);
			p.setValue(value.getProperty(names[i]));
			props.add(p);
		}
		tableViewer.setInput(props);
	}

	private void setColumnToolTip() {
		final Listener labelListener = new Listener() {
			public void handleEvent(Event event) {
				Label label = (Label) event.widget;
				Shell shell = label.getShell();
				switch (event.type) {
				case SWT.MouseDown:
					Event e = new Event();
					e.item = (TableItem) label.getData("_TABLEITEM"); //$NON-NLS-1$
					// Assuming table is single select, set the selection as if
					// the mouse down event went through to the table
					table.setSelection(new TableItem[] { (TableItem) e.item });
					table.notifyListeners(SWT.Selection, e);
					// fall through
				case SWT.MouseExit:
					shell.dispose();
					break;
				}
			}
		};

		Listener tableListener = new Listener() {
			Shell tip = null;

			Label label = null;

			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Dispose:
				case SWT.KeyDown:
				case SWT.MouseMove: {
					if (tip == null)
						break;
					tip.dispose();
					tip = null;
					label = null;
					break;
				}
				case SWT.MouseHover: {
					TableItem item = table.getItem(new Point(event.x, event.y));
					String description = getDescription(item);
					if (item != null && !description.equals("")) { //$NON-NLS-1$

						if (tip != null && !tip.isDisposed())
							tip.dispose();
						tip = new Shell(table.getShell(), SWT.ON_TOP | SWT.TOOL);
						tip.setLayout(new FillLayout());
						label = new Label(tip, SWT.NONE);
						label.setForeground(table.getShell().getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
						label.setBackground(table.getShell().getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
						label.setData("_TABLEITEM", item); //$NON-NLS-1$

						label.setText(description);
						label.addListener(SWT.MouseExit, labelListener);
						label.addListener(SWT.MouseDown, labelListener);
						Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
						Rectangle rect = item.getBounds(0);
						Point pt = table.toDisplay(rect.x, rect.y);
						tip.setBounds(pt.x, pt.y, size.x, size.y);
						tip.setVisible(true);
					}
				}
				}
			}
		};
		table.addListener(SWT.Dispose, tableListener);
		table.addListener(SWT.KeyDown, tableListener);
		table.addListener(SWT.MouseMove, tableListener);
		table.addListener(SWT.MouseHover, tableListener);
	}

	private String[] getPropertyItems(String[] items, String dto) {
		List<PropertyDTO> props = (List<PropertyDTO>) tableViewer.getInput();
		Set<String> set = new HashSet<String>();
		for (PropertyDTO p : props)
			if (!dto.equals(p.getProperty()))
				set.add(p.getProperty());
		List<String> l = new ArrayList<String>();
		boolean isDTO = false;
		String[] names = getDefaultPropertyItems();
		for (int i = 0; i < names.length; i++) {
			if (!set.contains(names[i]))
				l.add(names[i]);
			if (dto.equals(names[i])) {
				isDTO = true;
			}
		}
		l.add(0, !isDTO ? "" : dto); //$NON-NLS-1$
		// default - exclude existing
		return l.toArray(new String[l.size()]);
	}

	private String[] getDefaultPropertyItems() {
		defaultProperties = getDefaultProperties();
		String[] strnames = new String[defaultProperties.size()];
		for (int i = 0; i < strnames.length; i++)
			strnames[i] = defaultProperties.get(i).getProperty();
		return strnames;
	}

	private List<PropertyDTO> getDefaultProperties() {
		if (defaultProperties == null) {
			defaultProperties = PropertiesList.getJRProperties();
		}
		return defaultProperties;
	}

	private String getDescription(TableItem item) {
		if (item != null && item.getData() != null) {
			String key = ((PropertyDTO) item.getData()).getProperty();
			List<PropertyDTO> dp = getDefaultProperties();
			for (PropertyDTO p : dp) {
				if (p.getProperty().equals(key))
					return p.getDescription();
			}
		}
		return getDescription(); //$NON-NLS-1$
	}
}
