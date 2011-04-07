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
package com.jaspersoft.studio.data.csv;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.jface.dialogs.DataAdapterErrorDialog;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.utils.Misc;

public class CSVDataAdapterComposite extends Composite {
	
	private CSVDataAdapter csvDataAdapter = null;
	private Text textCSVFileName;
	private Button btnCheckQEMode;
	private TableViewer tableViewer;
	private Table table;
	private Button btnDelete;
	private Button btnCheckSkipFirstLine;
	private Button btnCreateDatePattern;
	private Button btnCheckUseDatePattern;
	private Text textDatePattern;
	private Button btnRadioFieldComma;
	private Button btnRadioFieldTab;
	private Button btnRadioFieldNewLineUnix;
	private Button btnRadioFieldSpace;
	private Button btnRadioFieldSemicolon;
	private Button btnRadioFieldOther;
	private Text textFieldOther;
	private Button btnRadioRowComma;
	private Button btnRadioRowTab;
	private Button btnRadioRowNewLineUnix;
	private Button btnRadioRowNewLineWin;
	private Button btnRadioRowSpace;
	private Button btnRadioRowSemicolon;
	private Button btnRadioRowOther;
	private Text textRowOther;

	// The data model
	private java.util.List<String> rows;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CSVDataAdapterComposite(Composite parent, int style) {
		
		/*
		 * UI ELEMENTS
		 */
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		// data model init
		rows = new ArrayList<String>();
		
		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("CSV file:");
		
		textCSVFileName = new Text(composite, SWT.BORDER);
		textCSVFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnBrowse = new Button(composite, SWT.NONE);
		GridData gd_btnBrowse = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnBrowse.widthHint = 100;
		btnBrowse.setLayoutData(gd_btnBrowse);
		btnBrowse.setText("Browse");
		
		btnCheckQEMode = new Button(this, SWT.CHECK);
		btnCheckQEMode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnCheckQEMode.setText("Use query executor mode (the report must use the CSV query language)");
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(1, false);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		CTabFolder tabFolder = new CTabFolder(composite_1, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmColumns = new CTabItem(tabFolder, SWT.NONE);
		tbtmColumns.setText("Columns");
		tabFolder.setSelection(0);
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmColumns.setControl(composite_2);
		composite_2.setLayout(new GridLayout(1, false));
		
		Group grpColumnNames = new Group(composite_2, SWT.NONE);
		grpColumnNames.setText("Column names");
		grpColumnNames.setLayout(new GridLayout(1, false));
		grpColumnNames.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Button btnGetCSVColumnsName = new Button(grpColumnNames, SWT.NONE);
		btnGetCSVColumnsName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnGetCSVColumnsName.setText("Get columns name from the first row of the file");
		
		Composite composite_3 = new Composite(grpColumnNames, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(2, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_3.setBounds(0, 0, 64, 64);
		
		tableViewer = new TableViewer(composite_3, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		tableViewer.setContentProvider(new CSVContentProvider());
		tableViewer.setInput(rows);
		
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableViewerColumn tableViewerColumnName = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnColumnName = tableViewerColumnName.getColumn();
		tblclmnColumnName.setMoveable(true);
		tblclmnColumnName.setWidth(100);
		tblclmnColumnName.setText("Column name");
		tableViewerColumnName.setLabelProvider(new ColumnLabelProvider());
		tableViewerColumnName.setEditingSupport(new NameEditingSupport(tableViewer));
		
		Composite composite_4 = new Composite(composite_3, SWT.NONE);
		GridLayout gl_composite_4 = new GridLayout(1, false);
		gl_composite_4.marginWidth = 0;
		gl_composite_4.marginHeight = 0;
		composite_4.setLayout(gl_composite_4);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		
		Button btnAdd = new Button(composite_4, SWT.NONE);
		GridData gd_btnAdd = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnAdd.widthHint = 100;
		btnAdd.setLayoutData(gd_btnAdd);
		btnAdd.setText("Add");
		
		btnDelete = new Button(composite_4, SWT.NONE);
		GridData gd_btnDelete = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnDelete.widthHint = 100;
		btnDelete.setLayoutData(gd_btnDelete);
		btnDelete.setText("Delete");
		btnDelete.setEnabled(false);
		
		Group grpOther = new Group(composite_2, SWT.NONE);
		grpOther.setText("Other");
		grpOther.setLayout(new GridLayout(3, false));
		grpOther.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		btnCheckUseDatePattern = new Button(grpOther, SWT.CHECK);
		btnCheckUseDatePattern.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnCheckUseDatePattern.setText("Use custom date pattern");
		
		textDatePattern = new Text(grpOther, SWT.BORDER);
		textDatePattern.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textDatePattern.setEnabled(false);
		
		btnCreateDatePattern = new Button(grpOther, SWT.NONE);
		GridData gd_btnCreateDatePattern = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_btnCreateDatePattern.widthHint = 100;
		btnCreateDatePattern.setLayoutData(gd_btnCreateDatePattern);
		btnCreateDatePattern.setText("Create");
		btnCreateDatePattern.setEnabled(false);
		
		btnCheckSkipFirstLine = new Button(grpOther, SWT.CHECK);
		btnCheckSkipFirstLine.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		btnCheckSkipFirstLine.setText("Skip the first line (the column names will be read from the first line)");
		
		CTabItem tbtmSeparators = new CTabItem(tabFolder, SWT.NONE);
		tbtmSeparators.setText("Separators");
		
		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		tbtmSeparators.setControl(composite_5);
		composite_5.setLayout(new GridLayout(1, false));
		
		Group grpFieldSeparator = new Group(composite_5, SWT.NONE);
		grpFieldSeparator.setText("Field separator (char)");
		grpFieldSeparator.setLayout(new GridLayout(3, true));
		grpFieldSeparator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpFieldSeparator.setBounds(0, 0, 70, 82);
		
		btnRadioFieldComma = new Button(grpFieldSeparator, SWT.RADIO);
		btnRadioFieldComma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioFieldComma.setText("Comma");
		
		btnRadioFieldTab = new Button(grpFieldSeparator, SWT.RADIO);
		btnRadioFieldTab.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioFieldTab.setText("Tab");
		
		btnRadioFieldNewLineUnix = new Button(grpFieldSeparator, SWT.RADIO);
		btnRadioFieldNewLineUnix.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioFieldNewLineUnix.setText("New line (Unix)");
		
		btnRadioFieldSpace = new Button(grpFieldSeparator, SWT.RADIO);
		btnRadioFieldSpace.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioFieldSpace.setText("Space");
		
		btnRadioFieldSemicolon = new Button(grpFieldSeparator, SWT.RADIO);
		btnRadioFieldSemicolon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioFieldSemicolon.setText("Semicolon");
		
		Composite composite_6 = new Composite(grpFieldSeparator, SWT.NONE);
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite_6 = new GridLayout(2, false);
		gl_composite_6.marginWidth = 0;
		gl_composite_6.marginHeight = 0;
		composite_6.setLayout(gl_composite_6);
		btnRadioFieldOther = new Button(composite_6, SWT.RADIO);
		btnRadioFieldOther.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnRadioFieldOther.setText("Other");
		
		textFieldOther = new Text(composite_6, SWT.BORDER);
		textFieldOther.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		textFieldOther.setEnabled(false);
		
		Group grpRowSeparator = new Group(composite_5, SWT.NONE);
		grpRowSeparator.setText("Row separator");
		grpRowSeparator.setLayout(new GridLayout(3, true));
		grpRowSeparator.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnRadioRowComma = new Button(grpRowSeparator, SWT.RADIO);
		btnRadioRowComma.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioRowComma.setText("Comma");
		
		btnRadioRowTab = new Button(grpRowSeparator, SWT.RADIO);
		btnRadioRowTab.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioRowTab.setText("Tab");
		
		btnRadioRowNewLineUnix = new Button(grpRowSeparator, SWT.RADIO);
		btnRadioRowNewLineUnix.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioRowNewLineUnix.setText("New line (Unix)");
		
		btnRadioRowNewLineWin = new Button(grpRowSeparator, SWT.RADIO);
		btnRadioRowNewLineWin.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioRowNewLineWin.setText("New line (Windows)");
		
		btnRadioRowSpace = new Button(grpRowSeparator, SWT.RADIO);
		btnRadioRowSpace.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioRowSpace.setText("Space");
		
		btnRadioRowSemicolon = new Button(grpRowSeparator, SWT.RADIO);
		btnRadioRowSemicolon.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnRadioRowSemicolon.setText("Semicolon");
		
		Composite composite_7 = new Composite(grpRowSeparator, SWT.NONE);
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite_7 = new GridLayout(2, false);
		gl_composite_7.marginWidth = 0;
		gl_composite_7.marginHeight = 0;
		composite_7.setLayout(gl_composite_7);
		btnRadioRowOther = new Button(composite_7, SWT.RADIO);
		btnRadioRowOther.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnRadioRowOther.setText("Other");
		
		textRowOther = new Text(composite_7, SWT.BORDER);
		textRowOther.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		textRowOther.setEnabled(false);
		new Label(grpRowSeparator, SWT.NONE);
		new Label(grpRowSeparator, SWT.NONE);
		
		Group grpSpecialCharacters = new Group(composite_5, SWT.NONE);
		grpSpecialCharacters.setText("Special characters");
		grpSpecialCharacters.setLayout(new GridLayout(2, true));
		grpSpecialCharacters.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Text text_a = new Text(grpSpecialCharacters, SWT.NONE);
		text_a.setEditable(false);
		text_a.setText("\\n for new line (Unix/MacOS)");
		text_a.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Text text_b = new Text(grpSpecialCharacters, SWT.NONE);
		text_b.setText("\\r for carriage return");
		text_b.setEditable(false);
		text_b.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Text text_c = new Text(grpSpecialCharacters, SWT.NONE);
		text_c.setText("\\r\\n for new line (Windows)");
		text_c.setEditable(false);
		text_c.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Text text_d = new Text(grpSpecialCharacters, SWT.NONE);
		text_d.setText("\\t for tab");
		text_d.setEditable(false);
		text_d.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Text text_e = new Text(grpSpecialCharacters, SWT.NONE);
		text_e.setText("\\\\ for backslash");
		text_e.setEditable(false);
		text_e.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpSpecialCharacters, SWT.NONE);
		
		/*
		 * UI ELEMENTS LISTENERS
		 */
		// browse and select the CSV file
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell());
				fd.setFileName(textCSVFileName.getText());
				fd.setFilterExtensions(new String[]{"*.csv","*.*"});
				String selection = fd.open();
				if (selection != null)
					textCSVFileName.setText(selection);
			}
		});
		
		// get CSV file columns
		btnGetCSVColumnsName.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e)  {
				try {
					getCSVColumns();
				} catch (IOException e1) {
					e1.printStackTrace();

			        DataAdapterErrorDialog.showErrorDialog(
			        		Display.getCurrent().getActiveShell(), 
			        		"CSV IOException",
			        		e1);
					
				} catch (Exception e2) {
					e2.printStackTrace();

			        DataAdapterErrorDialog.showErrorDialog(
			        		Display.getCurrent().getActiveShell(), 
			        		"CSV Exception",
			        		e2);
				}
			}
		});
		
		// add an entry and set selection on it
		btnAdd.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				rows.add(createDataModelEntry());
				
				// tableViewer.addPostSelectionChangedListener can't cover
				// the first row added, so we need to manually set delete
				// button enabled for this case.
				if (rows.size() == 1) {
					btnDelete.setEnabled(true);
				}
				
				tableViewer.refresh();
				setLastTableItemSelection();
			}
		});
		
		// delete selected entries and set selection on last table item
		btnDelete.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				removeEntries();
			}
		});
		
		// keys listener
		table.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// nothing
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.character == SWT.DEL) {
					removeEntries();
				}
			}
		});
		
		// When no table items,
		// turns disabled the delete button
		// and set unchecked the skip first line button
		tableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (rows.size() <= 0) {
					btnDelete.setEnabled(false);
					btnCheckSkipFirstLine.setSelection(false);
				} else {
					btnDelete.setEnabled(true);
				}
			}
		});
		
		btnCheckUseDatePattern.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				boolean bool = ((Button)e.widget).getSelection();
				if (!bool) {
					textDatePattern.setText(new SimpleDateFormat().toPattern());
				}
				textDatePattern.setEnabled(bool);
				btnCreateDatePattern.setEnabled(bool);
			}
		});
		
		btnCreateDatePattern.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				PatternEditor wizard = new PatternEditor();
				wizard.setNumberPatterns(false);
				wizard.setValue(textDatePattern.getText());
				WizardDialog dialog = new WizardDialog(getShell(), wizard);
				dialog.create();

				if (dialog.open() == Dialog.OK) {
					String val = wizard.getValue();
					textDatePattern.setText(val);
				}
			}
		});
		
		Button[] fieldButtons = new Button[]{btnRadioFieldComma, btnRadioFieldNewLineUnix, btnRadioFieldSemicolon, btnRadioFieldSpace, btnRadioFieldTab, btnRadioFieldOther};
		for (Button button : fieldButtons) {
			
			button.addSelectionListener(new SelectionAdapter() {
					
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					enableFieldOther( ((Button)e.widget) );
				}
			});
		}
		
		Button[] rowButtons = new Button[]{btnRadioRowComma, btnRadioRowNewLineUnix, btnRadioRowNewLineWin, btnRadioRowSemicolon, btnRadioRowSpace, btnRadioRowTab, btnRadioRowOther};
		for (Button button : rowButtons) {
			
			button.addSelectionListener(new SelectionAdapter() {
					
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					enableRowOther( ((Button)e.widget) );
				}
			});
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Set the CSV DataAdapter and initial values for UI elements.
	 * @param dataAdapter
	 */
	public void setDataAdapter(CSVDataAdapter dataAdapter) {
		
		csvDataAdapter = dataAdapter;
		
		textCSVFileName.setText( Misc.nvl(csvDataAdapter.getFileName(), "") );
		btnCheckQEMode.setSelection(csvDataAdapter.isQeMode());
		
		List<String> listColumnNames = csvDataAdapter.getColumnNames();
		if (listColumnNames != null && listColumnNames.size() > 0) {
			for (String str : listColumnNames) {
				rows.add(str);
			}
			tableViewer.refresh();
			setLastTableItemSelection();
			btnDelete.setEnabled(true);
		}
		
		String customDatePattern = csvDataAdapter.getCustomDatePattern();
		if (customDatePattern != null && customDatePattern.length() > 0) {
			btnCheckUseDatePattern.setSelection(true);
			textDatePattern.setText(customDatePattern);
			textDatePattern.setEnabled(true);
			btnCreateDatePattern.setEnabled(true);
		} else {
			textDatePattern.setText(new SimpleDateFormat().toPattern());
		}
		
		String fieldDelimiter = csvDataAdapter.getFieldDelimiter();
		if (fieldDelimiter != null) { // should never be null
			if (",".equals(fieldDelimiter)) btnRadioFieldComma.setSelection(true);
			else if ("\t".equals(fieldDelimiter)) btnRadioFieldTab.setSelection(true);
			else if (" ".equals(fieldDelimiter)) btnRadioFieldSpace.setSelection(true);
			else if (";".equals(fieldDelimiter)) btnRadioFieldSemicolon.setSelection(true);
			else if ("\n".equals(fieldDelimiter)) btnRadioFieldNewLineUnix.setSelection(true);
			else {
				btnRadioFieldOther.setSelection(true);
				textFieldOther.setText( Misc.addSlashesString(fieldDelimiter) );
				textFieldOther.setEnabled(true);
			}
		}
		
		String recordDelimitier = csvDataAdapter.getRecordDelimiter();
		if (recordDelimitier != null) { // should never be null
			if (",".equals(recordDelimitier)) btnRadioRowComma.setSelection(true);
			else if ("\t".equals(recordDelimitier)) btnRadioRowTab.setSelection(true);
			else if (" ".equals(recordDelimitier)) btnRadioRowSpace.setSelection(true);
			else if (";".equals(recordDelimitier)) btnRadioRowSemicolon.setSelection(true);
			else if ("\n".equals(recordDelimitier)) btnRadioRowNewLineUnix.setSelection(true);
			else if ("\r\n".equals(recordDelimitier)) btnRadioRowNewLineWin.setSelection(true);
			else {
				btnRadioRowOther.setSelection(true);
				textRowOther.setText( Misc.addSlashesString(recordDelimitier) );
				textRowOther.setEnabled(true);
			}
		}
	}

	/**
	 * Get the CSV DataAdapter with the values from the UI elements.
	 * @return
	 */
	public DataAdapter getDataAdapter() {
		
		if (csvDataAdapter == null) csvDataAdapter = new CSVDataAdapter();
		
		csvDataAdapter.setFileName(textCSVFileName.getText());
		csvDataAdapter.setQeMode(btnCheckQEMode.getSelection());
		
		List<String> listColumnNames = new ArrayList<String>();
		for (String row : rows) {
			listColumnNames.add(row);
		}
		csvDataAdapter.setColumnNames(listColumnNames);
		
		csvDataAdapter.setCustomDatePattern(textDatePattern.getText());
		csvDataAdapter.setUseFirstRowAsHeader(btnCheckSkipFirstLine.getSelection());
		
		if (btnRadioFieldComma.getSelection()) csvDataAdapter.setFieldDelimiter(",");
		else if (btnRadioFieldTab.getSelection()) csvDataAdapter.setFieldDelimiter("\t");
		else if (btnRadioFieldSpace.getSelection()) csvDataAdapter.setFieldDelimiter(" ");
		else if (btnRadioFieldSemicolon.getSelection()) csvDataAdapter.setFieldDelimiter(";");
		else if (btnRadioFieldNewLineUnix.getSelection()) csvDataAdapter.setFieldDelimiter("\n");
		else if (btnRadioFieldOther.getSelection()) csvDataAdapter.setFieldDelimiter( Misc.removeSlashesString(textFieldOther.getText()) );
		
		if (btnRadioRowComma.getSelection()) csvDataAdapter.setRecordDelimiter(",");
		else if (btnRadioRowTab.getSelection()) csvDataAdapter.setRecordDelimiter("\t");
		else if (btnRadioRowSpace.getSelection()) csvDataAdapter.setRecordDelimiter(" ");
		else if (btnRadioRowSemicolon.getSelection()) csvDataAdapter.setRecordDelimiter(";");
		else if (btnRadioRowNewLineUnix.getSelection()) csvDataAdapter.setRecordDelimiter("\n");
		else if (btnRadioRowNewLineWin.getSelection()) csvDataAdapter.setRecordDelimiter("\r\n");
		else if (btnRadioRowOther.getSelection()) csvDataAdapter.setRecordDelimiter( Misc.removeSlashesString(textRowOther.getText()) );
		
		return csvDataAdapter;
	}

	public String getHelpContextId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Content provider for CSVDataAdapterComposite TableViewer
	 * @author czhu
	 *
	 */
	private class CSVContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {
			// nothing
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// nothing
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement != null && inputElement instanceof List)
				return ((List<?>) inputElement).toArray();
			return new Object[0];
		}
	}
	
	/**
	 * Extended EditingSupport for the specific column Name
	 * @author czhu
	 *
	 */
	private class NameEditingSupport extends EditingSupport {
		
		private final TableViewer viewer;

		public NameEditingSupport(TableViewer viewer) {
			super(viewer);
			this.viewer = viewer;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return new TextCellEditor(viewer.getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			return ((String)element).toString();
		}

		@Override
		protected void setValue(Object element, Object value) {
			element = (String.valueOf(value));
			viewer.refresh();
		}
	}
	
	/**
	 * This creates and returns a new entry for the data model
	 * @return String
	 */
	private String createDataModelEntry() {
		
		int i;
		// find max index
		if (rows != null) {
			i = rows.size();
		} else { // this case should never happen
			i = 0;
		}
		
		return new String("COLUMN_" + i);
	}
	

	/**
	 * Removes selected entries from the data model
	 */
	private void removeEntries() {
		
		int[] indices = table.getSelectionIndices();
		int removedItems = 0;
		
		for (int i : indices) {	
			// To prevent an IndexOutOfBoundsException
			// we need to subtract number of removed items
			// from the removed item index.
			rows.remove(i - removedItems);
			removedItems++;
		}
		tableViewer.refresh();
		setLastTableItemSelection();
	}
	
	/**
	 * This set selection to the last table item
	 */
	private void setLastTableItemSelection() {
		
		if (rows != null && rows.size() > 0) {
			table.setSelection(rows.size() - 1);
		}
	}
	
	/**
	 * Because the radio button "Other" for field separator
	 * is not in the same component as the others radio button,
	 * we need to manually make the switch.
	 * @param Button
	 */
	private void enableFieldOther(Button button) {
		
		if (btnRadioFieldOther.equals(button)) {
			
			textFieldOther.setEnabled(true);
			
			btnRadioFieldComma.setSelection(false);
			btnRadioFieldNewLineUnix.setSelection(false);
			btnRadioFieldSemicolon.setSelection(false);
			btnRadioFieldSpace.setSelection(false);
			btnRadioFieldTab.setSelection(false);
			
		} else {
			btnRadioFieldOther.setSelection(false);
			textFieldOther.setEnabled(false);
		}
	}
	
	/**
	 * Because the radio button "Other" for row separator
	 * is not in the same component as the others radio button,
	 * we need to manually make the switch.
	 * @param Button
	 */
	private void enableRowOther(Button button) {
		
		if (btnRadioRowOther.equals(button)) {
			
			textRowOther.setEnabled(true);
			
			btnRadioRowComma.setSelection(false);
			btnRadioRowNewLineUnix.setSelection(false);
			btnRadioRowNewLineWin.setSelection(false);
			btnRadioRowSemicolon.setSelection(false);
			btnRadioRowSpace.setSelection(false);
			btnRadioRowTab.setSelection(false);
			
		} else {
			btnRadioRowOther.setSelection(false);
			textRowOther.setEnabled(false);
		}
	}
	
	/**
	 * This method will populate the data model with the CSV columns
	 * This also checks the button "Skip the first line " and
	 * enables the delete button
	 * @throws IOException, Exception 
	 */
	private void getCSVColumns() throws IOException, Exception {
		
		JRCsvDataSourceInspector ds = new JRCsvDataSourceInspector( new File(textCSVFileName.getText()) );
        
        if (btnRadioFieldComma.getSelection()) ds.setFieldDelimiter(',');
        if (btnRadioFieldTab.getSelection()) ds.setFieldDelimiter('\t');
        if (btnRadioFieldSpace.getSelection()) ds.setFieldDelimiter(' ');
        if (btnRadioFieldSemicolon.getSelection()) ds.setFieldDelimiter(';');
        if (btnRadioFieldNewLineUnix.getSelection()) ds.setFieldDelimiter('\n');
        if (btnRadioFieldOther.getSelection()) ds.setFieldDelimiter( Misc.removeSlashesString(textFieldOther.getText()+" ").charAt(0) );
        
        if (btnRadioRowComma.getSelection()) ds.setRecordDelimiter(",");
        if (btnRadioRowTab.getSelection()) ds.setRecordDelimiter("\t");
        if (btnRadioRowSpace.getSelection()) ds.setRecordDelimiter(" ");
        if (btnRadioRowSemicolon.getSelection()) ds.setRecordDelimiter(";");
        if (btnRadioRowNewLineUnix.getSelection()) ds.setRecordDelimiter("\n");
        if (btnRadioRowNewLineWin.getSelection()) ds.setRecordDelimiter("\r\n");
        if (btnRadioRowOther.getSelection()) ds.setRecordDelimiter( Misc.removeSlashesString(textRowOther.getText()) );
        
        // empty data model
        rows.clear();
        
        Vector names = ds.getColumnNames();
        for (int i=0; i < names.size(); ++i )
        {
            String fname = (names.elementAt(i)+"").trim();
            if (fname.length() > 0)
              rows.add(fname);
        }
        
        tableViewer.refresh();
        setLastTableItemSelection();
        btnDelete.setEnabled(true);
        btnCheckSkipFirstLine.setSelection(true);
	}
}
