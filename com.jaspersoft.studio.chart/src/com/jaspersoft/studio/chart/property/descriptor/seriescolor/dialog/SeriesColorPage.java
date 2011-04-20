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
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.property.descriptor.seriescolor.dialog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.jasperreports.engine.JRChartPlot.JRSeriesColor;
import net.sf.jasperreports.engine.base.JRBaseChartPlot.JRBaseSeriesColor;

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
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorCellEditor;
import com.jaspersoft.studio.property.descriptor.color.ColorLabelProvider;
import com.jaspersoft.studio.utils.Colors;

public class SeriesColorPage extends WizardPage {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {
		ColorLabelProvider clb = new ColorLabelProvider(NullEnum.NOTNULL);

		public Image getColumnImage(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return clb.getImage(element);
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return clb.getText(Colors.getSWTRGB4AWTGBColor(((JRBaseSeriesColor) element).getColor()));
			}
			return ""; //$NON-NLS-1$
		}
	}

	private SortedSet value;
	private Table table;
	private TableViewer tableViewer;

	// private TableCursor cursor;

	public SortedSet<?> getValue() {
		return value;
	}

	@Override
	public void dispose() {
		// clear all properties
		List<JRBaseSeriesColor> props = (List<JRBaseSeriesColor>) tableViewer.getInput();
		value = new TreeSet(props);
		int i = 0;
		for (JRBaseSeriesColor p : props) {
			value.add(new JRBaseSeriesColor(i, p.getColor()));
			i++;
		}
		super.dispose();
	}

	public void setValue(SortedSet<?> value) {
		this.value = value;
		if (value == null) {
			value = new TreeSet();
		}
		if (table != null)
			fillTable(table);
	}

	protected SeriesColorPage(String pageName) {
		super(pageName);
		setTitle(Messages.common_series_colors);
		setDescription(Messages.SeriesColorPage_description);

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
		gd.verticalSpan = 4;
		gd.heightHint = 400;
		gd.widthHint = 600;
		table.setLayoutData(gd);

		Button addB = new Button(composite, SWT.PUSH | SWT.CENTER);
		addB.setText(Messages.SeriesColorPage_add);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = 100;
		addB.setLayoutData(gridData);
		addB.addSelectionListener(new SelectionAdapter() {

			// Remove the selection and refresh the view
			public void widgetSelected(SelectionEvent e) {
				List list = (List<?>) tableViewer.getInput();
				ColorDialog dialog = new ColorDialog(Display.getCurrent().getActiveShell());
				dialog.setRGB(new RGB(255, 0, 255));
				if (dialog.open() == null)
					return;
				int selection = table.getSelectionIndex();
				if (selection < 0)
					selection = list.size();
				JRSeriesColor p = new JRBaseSeriesColor(selection, Colors.getAWT4SWTRGBColor(dialog.getRGB()));
				list.add(selection, p);
				tableViewer.add(p);
				tableViewer.setSelection(new StructuredSelection(p));
				// cursor.setSelection(table.getSelectionIndex(), 0);
				tableViewer.refresh();
				table.setFocus();
			}
		});

		Button delB = new Button(composite, SWT.PUSH | SWT.CENTER);
		delB.setText(Messages.SeriesColorPage_delete);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = 100;
		delB.setLayoutData(gridData);
		delB.addSelectionListener(new SelectionAdapter() {

			// Remove the selection and refresh the view
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer.getSelection();
				JRBaseSeriesColor property = (JRBaseSeriesColor) iStructuredSelection.getFirstElement();
				int selection = table.getSelectionIndex();
				Object input = tableViewer.getInput();
				if (input instanceof List<?>) {
					List list = (List) input;
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
					} else
						setMessage(Messages.SeriesColorPage_table_is_empty);
				}
			}
		});

		Button upB = new Button(composite, SWT.PUSH | SWT.CENTER);
		upB.setText(Messages.SeriesColorPage_move_up);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = 100;
		upB.setLayoutData(gridData);
		upB.addSelectionListener(new SelectionAdapter() {

			// Remove the selection and refresh the view
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer.getSelection();
				JRBaseSeriesColor property = (JRBaseSeriesColor) iStructuredSelection.getFirstElement();
				Object input = tableViewer.getInput();
				if (input instanceof List<?>) {
					List list = (List<?>) input;
					int index = list.indexOf(property);
					if (index > 0) {
						int sel = index - 1;

						list.remove(property);
						list.add(sel, property);

						tableViewer.refresh();
						// Object sp = list.get(sel);
						//
						// if (sp != null) {
						tableViewer.setSelection(new StructuredSelection(property));
						// cursor.setSelection(table.getSelectionIndex(), 0);
						// }
					}
				}
			}
		});

		Button downB = new Button(composite, SWT.PUSH | SWT.CENTER);
		downB.setText(Messages.SeriesColorPage_move_down);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING);
		gridData.widthHint = 100;
		downB.setLayoutData(gridData);
		downB.addSelectionListener(new SelectionAdapter() {

			// Remove the selection and refresh the view
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection iStructuredSelection = (IStructuredSelection) tableViewer.getSelection();
				JRBaseSeriesColor property = (JRBaseSeriesColor) iStructuredSelection.getFirstElement();
				Object input = tableViewer.getInput();
				if (input instanceof List<?>) {
					List list = (List<?>) input;
					int index = list.indexOf(property);
					if (index <= list.size()) {
						list.remove(property);

						int sel = index + 1;
						if (sel <= list.size() + 1) {
							list.add(sel, property);
						}

						tableViewer.refresh();
						Object sp = list.get(sel);

						if (sp != null) {
							tableViewer.setSelection(new StructuredSelection(sp));
							// cursor.setSelection(table.getSelectionIndex(), 0);
						}
					}
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
		table.setLayout(tlayout);

		setColumnToolTip();

		TableColumn[] column = new TableColumn[1];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.SeriesColorPage_color);

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
				return ((List<?>) inputElement).toArray();
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
				return false;
			}

			public Object getValue(Object element, String property) {
				JRBaseSeriesColor prop = (JRBaseSeriesColor) element;
				if ("COLOR".equals(property)) //$NON-NLS-1$
					return Colors.getSWTRGB4AWTGBColor(prop.getColor());
				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {

			}
		});

		viewer.setCellEditors(new CellEditor[] { new ColorCellEditor(parent) });
		viewer.setColumnProperties(new String[] { "COLOR" }); //$NON-NLS-1$
	}

	private void fillTable(Table table) {
		List<JRBaseSeriesColor> lst = new ArrayList<JRBaseSeriesColor>();
		for (Iterator it = value.iterator(); it.hasNext();) {
			JRBaseSeriesColor bsc = (JRBaseSeriesColor) it.next();
			lst.add(new JRBaseSeriesColor(bsc.getSeriesOrder(), bsc.getColor()));
		}
		tableViewer.setInput(lst);
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

	private String getDescription(TableItem item) {
		// String key = ((SubreportPropertyDTO) item.getData()).getProperty();
		// List<SubreportPropertyDTO> dp = getDefaultProperties();
		// for (SubreportPropertyDTO p : dp) {
		// if (p.getProperty().equals(key))
		// return p.getDescription();
		// }
		return ""; //$NON-NLS-1$
	}
}
