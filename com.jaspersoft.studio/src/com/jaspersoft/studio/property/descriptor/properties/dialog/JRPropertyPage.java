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
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.internal.help.WorkbenchHelpSystem;

import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxCellEditor;

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
			return "";
		}
	}

	private JRPropertiesMap value;
	private Table table;
	private TableViewer tableViewer;
	private List<PropertyDTO> defaultProperties;
	private TableCursor cursor;

	public JRPropertiesMap getValue() {
		return value;
	}

	/**
	 * Displays the help
	 */
	public void performHelp() {
		getShell().setData(WorkbenchHelpSystem.HELP_KEY, "ch.sahits.tutorial.help.firsthelp");

		// PlatformUI.getWorkbench().getHelpSystem().displayHelp();//displayDynamicHelp();//
		// displayHelp("net.sf.jasperreports.doc");
	}

	@Override
	public void dispose() {
		// clear all properties
		value = new JRPropertiesMap();
		List<PropertyDTO> props = (List<PropertyDTO>) tableViewer.getInput();
		for (PropertyDTO p : props) {
			if (p.getProperty() != null && !p.getProperty().equals(""))
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
		setTitle("Properties");
		setDescription("Properties.");

	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		setControl(composite);

		buildTable(composite);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		gd.verticalAlignment = GridData.FILL;
		gd.grabExcessVerticalSpace = true;
		gd.verticalSpan = 2;
		gd.heightHint = 400;
		gd.widthHint = 600;
		table.setLayoutData(gd);

		Button addB = new Button(composite, SWT.PUSH | SWT.CENTER);
		addB.setText("&add");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = 80;
		addB.setLayoutData(gridData);
		addB.addSelectionListener(new SelectionAdapter() {

			// Remove the selection and refresh the view
			public void widgetSelected(SelectionEvent e) {
				List<PropertyDTO> list = (List<PropertyDTO>) tableViewer.getInput();
				PropertyDTO p = new PropertyDTO();
				p.setValue("");
				p.setValue("");
				list.add(p);
				tableViewer.add(p);
				tableViewer.setSelection(new StructuredSelection(p));
				cursor.setSelection(table.getSelectionIndex(), 0);
				tableViewer.refresh();
				table.setFocus();
			}
		});

		Button delB = new Button(composite, SWT.PUSH | SWT.CENTER);
		delB.setText("&delete");
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = 80;
		delB.setLayoutData(gridData);
		delB.addSelectionListener(new SelectionAdapter() {

			// Remove the selection and refresh the view
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer.getSelection();
				PropertyDTO property = (PropertyDTO) iStructuredSelection.getFirstElement();
				List<PropertyDTO> list = (List<PropertyDTO>) tableViewer.getInput();
				int index = list.indexOf(property);
				list.remove(property);
				tableViewer.remove(property);
				tableViewer.refresh();
				PropertyDTO sp = null;
				if (index >= list.size())
					index = list.size() - 1;
				if (index >= 0)
					sp = list.get(index);

				if (sp != null) {
					tableViewer.setSelection(new StructuredSelection(sp));
					cursor.setSelection(table.getSelectionIndex(), 0);
				} else
					setMessage("Table is empty");
			}
		});
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		table.setToolTipText("");
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// cursor = new TableCursor(table, SWT.NONE);
		//
		// final ControlEditor editor = new ControlEditor(cursor);
		// editor.grabHorizontal = true;
		// editor.grabVertical = true;
		//
		// cursor.addSelectionListener(new SelectionAdapter() {
		// // This is called as the user navigates around the table
		// public void widgetSelected(SelectionEvent event) {
		// // Select the row in the table where the TableCursor is
		// table.setSelection(new TableItem[] { cursor.getRow() });
		// }
		//
		// // This is called when the user hits Enter
		// public void widgetDefaultSelected(SelectionEvent event) {
		// CellEditor cellEditor = tableViewer.getCellEditors()[cursor.getColumn()];
		// // tableViewer.cancelEditing();
		// if (cellEditor != null) {
		// cellEditor.deactivate();
		// }
		// // set cursor-selection to mark whole row
		// tableViewer.setSelection(new StructuredSelection(cursor.getRow()), true);
		// // set selection of table separatly; viewer does incorrectly.
		// table.setSelection(new TableItem[] { cursor.getRow() });
		// // editCell(tableViewer, cursor);
		//
		// }
		// });
		// cursor.addKeyListener(new KeyAdapter() {
		// public void keyPressed(KeyEvent e) {
		// // Hide the TableCursor when the user hits the "CTRL" or "SHIFT" key.
		// // This alows the user to select multiple items in the table.
		// if ((e.keyCode == SWT.CTRL || e.keyCode == SWT.SHIFT)
		// || (((e.stateMask & SWT.CONTROL) != 0 || (e.stateMask & SWT.SHIFT) != 0) && ((e.keyCode & SWT.ARROW) != 0))) {
		// cursor.setVisible(false);
		// } else
		// // ENTER to open editor
		// if ((e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) && e.stateMask == 0) {
		// editCell(tableViewer, cursor);
		// } else
		// // any character
		// if ((e.keyCode < 0x10000 || e.character != '\0') && e.keyCode > 0x1f && e.keyCode != 127 || e.keyCode == 0x00
		// && (e.stateMask == 0 || e.stateMask == SWT.SHIFT)) {
		// editCell(tableViewer, cursor);
		// if (tableViewer.getCellEditors()[cursor.getColumn()] instanceof TextCellEditor) {
		// TextCellEditor editor = ((TextCellEditor) tableViewer.getCellEditors()[cursor.getColumn()]);
		// editor.setValue(String.valueOf(e.character));
		// ((Text) editor.getControl()).setSelection(1);
		// }
		// }
		// }
		// });

		tableViewer = new TableViewer(table);
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		table.setLayout(tlayout);

		setColumnToolTip();

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Name");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Value");

		fillTable(table);
		for (int i = 0, n = column.length; i < n; i++) {
			column[i].pack();
		}
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

	/**
	 * @param tableViewer
	 * @param cursor
	 */
	static void editCell(final TableViewer tableViewer, final TableCursor cursor) {
		tableViewer.editElement(cursor.getRow().getData(), cursor.getColumn());
		// hide cursor only f there is an editor active on the cell
		cursor.setVisible(!tableViewer.isCellEditorActive());
	}

	private void attachContentProvider(TableViewer viewer) {
		viewer.setContentProvider(new IStructuredContentProvider() {
			public Object[] getElements(Object inputElement) {
				return (Object[]) ((List<PropertyDTO>) inputElement).toArray();
			}

			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

			}
		});
	}

	private void attachLabelProvider(TableViewer viewer) {
		viewer.setLabelProvider(new TLabelProvider());
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("VALUE"))
					return true;
				if (property.equals("NAME"))
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				PropertyDTO prop = (PropertyDTO) element;
				if ("VALUE".equals(property))
					return prop.getValue();
				;
				if ("NAME".equals(property)) {
					return prop.getProperty();
				}
				return "";
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				setErrorMessage(null);
				setMessage(getDescription(tableItem));
				PropertyDTO data = (PropertyDTO) tableItem.getData();
				if ("VALUE".equals(property)) {
					data.setValue((String) value);
				}
				if ("NAME".equals(property)) {
					List<PropertyDTO> plist = (List<PropertyDTO>) tableViewer.getInput();
					for (PropertyDTO p : plist) {
						if (p != data && p.getProperty() != null && p.getProperty().equals(value)) {
							setErrorMessage("Properties are unique, you can't put duplicate values");
							return;
						}
					}
					data.setProperty((String) value);
				}
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		RWComboBoxCellEditor comboBoxCellEditor = new RWComboBoxCellEditor(parent, getDefaultPropertyItems()) {
			@Override
			protected void doSetFocus() {
				int index = getComboBox().getSelectionIndex();
				String cVal = (String) getValue();
				String[] propertyItems = getPropertyItems(getItems(), cVal);
				setItems(propertyItems);
				super.doSetFocus();
				for (int i = 0; i < propertyItems.length; i++)
					if (cVal.equals(propertyItems[i])) {
						getComboBox().select(i);
						break;
					}
			}
		};

		viewer.setCellEditors(new CellEditor[] { comboBoxCellEditor, new TextCellEditor(parent) });
		viewer.setColumnProperties(new String[] { "NAME", "VALUE" });
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
					e.item = (TableItem) label.getData("_TABLEITEM");
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
					if (item != null && !description.equals("")) {

						if (tip != null && !tip.isDisposed())
							tip.dispose();
						tip = new Shell(table.getShell(), SWT.ON_TOP | SWT.TOOL);
						tip.setLayout(new FillLayout());
						label = new Label(tip, SWT.NONE);
						label.setForeground(table.getShell().getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
						label.setBackground(table.getShell().getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
						label.setData("_TABLEITEM", item);

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
		l.add(0, isDTO ? "" : dto);
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
		String key = ((PropertyDTO) item.getData()).getProperty();
		List<PropertyDTO> dp = getDefaultProperties();
		for (PropertyDTO p : dp) {
			if (p.getProperty().equals(key))
				return p.getDescription();
		}
		return "";
	}
}
