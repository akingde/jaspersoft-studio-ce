/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
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

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxCellEditor;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontFamily;

public class FontMappingPage extends JSSHelpWizardPage {
	public class TableLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof List) {
				List<String> el = (List<String>) element;
				switch (columnIndex) {
				case 0:
					return el.get(0);
				case 1:
					return el.get(1);
				default:
					return (element != null ? element.toString() : ""); //$NON-NLS-1$
				}
			}
			return Misc.nvl(element, ""); //$NON-NLS-1$
		}

		public void dispose() {
		}
	}

	private SimpleFontFamily fontFamily;

	public FontMappingPage(FontFamily fontFamily) {
		super("fontmappingpage"); //$NON-NLS-1$
		setTitle(Messages.FontMappingPage_2);
		setDescription(Messages.FontMappingPage_3);
		this.fontFamily = (SimpleFontFamily) fontFamily;
	}

	@Override
	public void dispose() {
		Map<String, String> map = new HashMap<String, String>();
		List<List<String>> inlist = (List<List<String>>) tableViewer.getInput();
		for (List<String> str : inlist)
			map.put(str.get(0), str.get(1));
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
		lbl.setText(Messages.FontMappingPage_4);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		gd.widthHint = 300;
		lbl.setLayoutData(gd);

		new Label(composite, SWT.NONE).setText(Messages.FontMappingPage_5);
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

			public Object newElement(List<?> input, int pos) {
				List<String> lst = new ArrayList<String>(2);
				lst.add("html"); //$NON-NLS-1$
				lst.add(Messages.FontMappingPage_7);
				return lst;
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
		column[0].setText(Messages.FontMappingPage_8);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.FontMappingPage_9);

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
				List<String> data = (List<String>) element;
				if ("EXPORTER".equals(property)) //$NON-NLS-1$
					return data.get(0);
				if ("MAPPING".equals(property)) //$NON-NLS-1$
					return data.get(0);

				return ""; //$NON-NLS-1$
			}

			public void modify(Object element, String property, Object value) {
				TableItem tableItem = (TableItem) element;
				setErrorMessage(null);
				setMessage(getDescription());
				List<String> data = (List<String>) tableItem.getData();
				if ("EXPORTER".equals(property)) { //$NON-NLS-1$
					// check duplicatesS
					List<List<String>> inlist = (List<List<String>>) tableViewer.getInput();
					for (List<String> s : inlist) {
						if (s.get(0).equals(value))
							return;
					}

					data.add(0, (String) value);
				} else if ("MAPPING".equals(property)) { //$NON-NLS-1$
					data.add(1, (String) value);
				}
				tableViewer.update(element, new String[] { property });
				tableViewer.refresh();
			}
		});

		viewer.setCellEditors(new CellEditor[] {
				new RWComboBoxCellEditor(parent, new String[] { "", "html", "xhtml", "rtf" }), new TextCellEditor(parent) }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		viewer.setColumnProperties(new String[] { "EXPORTER", "MAPPING" }); //$NON-NLS-1$ //$NON-NLS-2$ 
	}

	private void fillTable(Table table) {
		List<List<String>> lst = new ArrayList<List<String>>();

		Map<String, String> map = fontFamily.getExportFonts();
		if (map != null) {
			for (String key : map.keySet()) {
				List<String> l = new ArrayList<String>(2);
				l.add(key);
				l.add(map.get(key));
				lst.add(l);
			}
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

	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_FONT_EXTENSION;
	}
}
