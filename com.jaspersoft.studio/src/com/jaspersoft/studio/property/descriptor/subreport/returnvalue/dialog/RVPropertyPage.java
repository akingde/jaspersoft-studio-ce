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
package com.jaspersoft.studio.property.descriptor.subreport.returnvalue.dialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignSubreportReturnValue;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
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

import com.jaspersoft.studio.model.JReportsDTO;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.utils.EnumHelper;

public class RVPropertyPage extends WizardPage {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return ((JRDesignSubreportReturnValue) element).getSubreportVariable();
			case 1:
				return ((JRDesignSubreportReturnValue) element).getToVariable();
			case 2:
				return ((JRDesignSubreportReturnValue) element).getCalculationValue().getName();
			case 3:
				return ((JRDesignSubreportReturnValue) element).getIncrementerFactoryClassName();
			}
			return "";
		}
	}

	private JReportsDTO dto;

	public JReportsDTO getDto() {
		dto.setValue(getValue());
		return dto;
	}

	public void setDto(JReportsDTO dto) {
		this.dto = dto;
		value = (List<JRDesignSubreportReturnValue>) dto.getValue();
		toVariables = null;
	}

	private List<JRDesignSubreportReturnValue> value;
	private Table table;
	private TableViewer tableViewer;
	private String[] toVariables;

	// private TableCursor cursor;

	public List<JRDesignSubreportReturnValue> getValue() {
		return value;
	}

	@Override
	public void dispose() {
		value = (List<JRDesignSubreportReturnValue>) tableViewer.getInput();
		super.dispose();
	}

	public void setValue(List<JRDesignSubreportReturnValue> value) {
		this.value = value;
		if (value == null) {
			value = new ArrayList<JRDesignSubreportReturnValue>();
		}
		if (table != null)
			fillTable(table);
	}

	protected RVPropertyPage(String pageName) {
		super(pageName);
		setTitle("Subreport Parameters");
		setDescription("Subreport Parameters.");

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
		gd.widthHint = 700;
		table.setLayoutData(gd);

		Button addB = new Button(composite, SWT.PUSH | SWT.CENTER);
		addB.setText("&add");
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = 80;
		addB.setLayoutData(gridData);
		addB.addSelectionListener(new SelectionAdapter() {

			// Remove the selection and refresh the view
			public void widgetSelected(SelectionEvent e) {
				List<JRDesignSubreportReturnValue> list = (List<JRDesignSubreportReturnValue>) tableViewer.getInput();
				JRDesignSubreportReturnValue p = new JRDesignSubreportReturnValue();
				p.setSubreportVariable("new_subreport_variable");

				// get toVariable from list
				String[] toV = getToVariables();
				for (int i = 0; i < toV.length; i++) {
					boolean vExists = false;
					for (JRDesignSubreportReturnValue v : list)
						if (toV[i].equals(v.getToVariable())) {
							vExists = true;
							break;
						}
					if (!vExists) {
						p.setToVariable(toV[i]);
						p.setCalculation(CalculationEnum.NOTHING);
						p.setIncrementerFactoryClassName(" ");
						list.add(p);
						tableViewer.add(p);
						tableViewer.setSelection(new StructuredSelection(p));
						// cursor.setSelection(table.getSelectionIndex(), 0);
						tableViewer.refresh();
						table.setFocus();
						return;
					}
				}
				// should I have a message? or just disable the button if it's not possible
				MessageDialog.openError(getShell(), "Error", "All report variables are allready used, you can't add more");
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
				JRDesignSubreportReturnValue property = (JRDesignSubreportReturnValue) iStructuredSelection.getFirstElement();
				Object input = tableViewer.getInput();
				if (input instanceof List<?>) {
					List<?> list = (List<?>) input;
					int index = list.indexOf(property);
					list.remove(property);
					tableViewer.remove(property);
					tableViewer.refresh();
					Object sp = null;
					if (index >= list.size())
						index = list.size() - 1;
					if (index >= 0)
						sp = list.get(index);

					if (sp != null) {
						tableViewer.setSelection(new StructuredSelection(sp));
						// cursor.setSelection(table.getSelectionIndex(), 0);
						validate();
					} else
						setMessage("Table is empty");
				}
			}
		});
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		table.setToolTipText("");
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		// cursor = new TableCursor(table, SWT.NONE);

		tableViewer = new TableViewer(table);
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		tlayout.addColumnData(new ColumnWeightData(50, 100, true));
		table.setLayout(tlayout);

		setColumnToolTip();

		TableColumn[] column = new TableColumn[4];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Subresport Variable");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("To Variable");

		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText("Calculation Type");

		column[3] = new TableColumn(table, SWT.NONE);
		column[3].setText("Incrementer Factory Class");

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
				return ((List<JRDesignSubreportReturnValue>) inputElement).toArray();
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
				if (property.equals("SUBREPORTVARIABLE"))
					return true;
				if (property.equals("TOVARIABLE"))
					return true;
				if (property.equals("CALCULATIONTYPE"))
					return true;
				if (property.equals("INCREMENTERFACTORYCLASS"))
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				JRDesignSubreportReturnValue prop = (JRDesignSubreportReturnValue) element;
				if ("SUBREPORTVARIABLE".equals(property))
					return prop.getSubreportVariable();
				if ("TOVARIABLE".equals(property)) {
					String[] toV = getToVariables();
					for (int i = 0; i < toV.length; i++) {
						if (toV[i].equals(prop.getToVariable()))
							return i;
					}
				}
				if ("CALCULATIONTYPE".equals(property))
					return EnumHelper.getValue(prop.getCalculationValue(), 0, false);
				if ("INCREMENTERFACTORYCLASS".equals(property))
					return prop.getIncrementerFactoryClassName();
				return "";
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				setErrorMessage(null);
				setMessage(getDescription(tableItem));
				JRDesignSubreportReturnValue data = (JRDesignSubreportReturnValue) tableItem.getData();
				if ("SUBREPORTVARIABLE".equals(property)) {
					data.setSubreportVariable((String) value);
				} else if ("TOVARIABLE".equals(property)) {
					String[] tv = getToVariables();
					int val = (Integer) value;
					if (val >= 0 && val < tv.length)
						data.setToVariable(tv[val]);
				} else if ("CALCULATIONTYPE".equals(property)) {
					data.setCalculation((CalculationEnum) EnumHelper.getSetValue(CalculationEnum.values(), value, 0, false));
				} else if ("INCREMENTERFACTORYCLASS".equals(property)) {
					data.setIncrementerFactoryClassName((String) value);
				}
				validate();
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent),
				new ComboBoxCellEditor(parent, getToVariables()),
				new ComboBoxCellEditor(parent, EnumHelper.getEnumNames(CalculationEnum.values(), NullEnum.NOTNULL)),
				new ClassTypeCellEditor(parent) });
		viewer.setColumnProperties(new String[] { "SUBREPORTVARIABLE", "TOVARIABLE", "CALCULATIONTYPE",
				"INCREMENTERFACTORYCLASS" });
	}

	public void validate() {
		// validate toVariable is unique
		List<String> lto = new ArrayList<String>();
		List<JRDesignSubreportReturnValue> input = (List<JRDesignSubreportReturnValue>) tableViewer.getInput();
		for (JRDesignSubreportReturnValue d : input)
			lto.add(d.getToVariable());
		int size = lto.size();
		int setSize = new HashSet<String>(lto).size();
		if (size != setSize) {
			setErrorMessage("Your Rerurn Variables contains duplicate ToVariable values.");
			setPageComplete(false);
		} else {
			setErrorMessage(null);
			setPageComplete(true);
		}

	}

	public String[] getToVariables() {
		if (toVariables == null) {
			List<String> res = new ArrayList<String>();
			for (Object o : dto.getJasperDesign().getVariablesList()) {
				JRDesignVariable jdVar = (JRDesignVariable) o;
				res.add(jdVar.getName());
			}
			toVariables = res.toArray(new String[res.size()]);
		}
		return toVariables;
	}

	private void fillTable(Table table) {
		List<JRDesignSubreportReturnValue> props = new ArrayList<JRDesignSubreportReturnValue>();
		for (JRDesignSubreportReturnValue v : value)
			props.add(v);
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

	private String getDescription(TableItem item) {
		// String key = ((SubreportPropertyDTO) item.getData()).getProperty();
		// List<SubreportPropertyDTO> dp = getDefaultProperties();
		// for (SubreportPropertyDTO p : dp) {
		// if (p.getProperty().equals(key))
		// return p.getDescription();
		// }
		return "";
	}
}
