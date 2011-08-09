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

package com.jaspersoft.studio.preferences.fonts.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;

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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxCellEditor;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;

public class FontMappingPage extends WizardPage {
	public class TableLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			String[] el = (String[]) element;
			switch (columnIndex) {
			case 0:
				return el[0];
			case 1:
				return el[1];

			default:
				return (element != null ? element.toString() : "");
			}
		}

		public void dispose() {
		}
	}

	private SimpleFontFamily fontFamily;

	public FontMappingPage(FontFamily fontFamily) {
		super("fontmappingpage"); //$NON-NLS-1$
		setTitle("Font Mapping");
		setDescription("Font mapping page");
		this.fontFamily = (SimpleFontFamily) fontFamily;
	}

	@Override
	public void dispose() {
		Map<String, String> map = new HashMap<String, String>();
		List<String[]> inlist = (List<String[]>) tableViewer.getInput();
		for (String[] str : inlist) {
			map.put(str[0], str[1]);
		}
		fontFamily.setExportFonts(map);
		super.dispose();
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);
		setControl(composite);

		Label lbl = new Label(composite, SWT.WRAP);
		lbl.setText("Some exporters (for instance the HTML) require a replacement for this font family name, "
				+ "since this font may not be available on the target environement. This is the porpose of the "
				+ "mapping properties. For example in HTML the font name may be set to: 'My font family', "
				+ "'Times New Roman', 'Times', 'Serif'");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		gd.widthHint = 300;
		lbl.setLayoutData(gd);

		new Label(composite, SWT.NONE).setText("Default Mapping");
		defaultfont = new Text(composite, SWT.BORDER);
		defaultfont.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				fontFamily.setDefaultExportFont(defaultfont.getText());
			}
		});
		defaultfont.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Composite c = new Composite(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);
		c.setLayout(new GridLayout(2, false));

		buildTable(c);
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite bGroup = new Composite(c, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input) {
				return new String[] { "", "" };
			}

		});

		new DeleteButton().createDeleteButton(bGroup, tableViewer);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard"); //$NON-NLS-1$
		fillWidgets();
	}

	private Table table;
	private TableViewer tableViewer;
	private Text defaultfont;

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText("Export Type");

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText("Mapped Font Name");

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

		fillTable(table);
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				if (property.equals("EXPORTER")) //$NON-NLS-1$
					return true;
				if (property.equals("MAPPING")) //$NON-NLS-1$
					return true;
				return false;
			}

			public Object getValue(Object element, String property) {
				String[] data = (String[]) element;
				if ("EXPORTER".equals(property)) //$NON-NLS-1$
					return data[0];
				if ("MAPPING".equals(property)) //$NON-NLS-1$
					return data[1];

				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				setErrorMessage(null);
				setMessage(getDescription());
				String[] data = (String[]) tableItem.getData();
				if ("EXPORTER".equals(property)) { //$NON-NLS-1$
					// check duplicatesS
					List<String[]> inlist = (List<String[]>) tableViewer.getInput();
					for (String[] s : inlist) {
						if (s[0].equals(value))
							return;
					}

					data[0] = (String) value;
				} else if ("MAPPING".equals(property)) { //$NON-NLS-1$
					data[1] = (String) value;
				}
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] {
				new RWComboBoxCellEditor(parent, new String[] { "", "html", "xhtml", "rtf" }), new TextCellEditor(parent) });
		viewer.setColumnProperties(new String[] { "EXPORTER", "MAPPING" }); //$NON-NLS-1$ //$NON-NLS-2$ 
	}

	private void fillTable(Table table) {
		List<String[]> lst = new ArrayList<String[]>();

		Map<String, String> map = fontFamily.getExportFonts();
		if (map != null) {
			for (String key : map.keySet())
				lst.add(new String[] { key, map.get(key) });
		}
		tableViewer.setInput(lst);
	}

	private void fillWidgets() {
		if (fontFamily.getDefaultExportFont() != null)
			defaultfont.setText(fontFamily.getDefaultExportFont());
	}

	@Override
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}
}
