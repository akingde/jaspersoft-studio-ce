package com.jaspersoft.studio.data.basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.jaspersoft.studio.data.DataAdapter;

public class DefaultDataAdapterEditorComposite extends Composite {
	
	private DataAdapter dataAdapter = null;
	private static TableViewer tableViewer;
	private Button addButton;
	private Button editButton;
	private Button deleteButton;
	private TableItem selectedTableItem;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DefaultDataAdapterEditorComposite(Composite parent, int style) {
		
		// UI elements
		super(parent, style);
		setLayout(new GridLayout(2, false));
		
		tableViewer = new TableViewer(this, SWT.BORDER | SWT.FULL_SELECTION);
		final Table table = tableViewer.getTable();
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table.minimumWidth = 200;
		table.setLayoutData(gd_table);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableViewerColumn propertyViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		propertyViewerColumn.getColumn().setMoveable(true);
		propertyViewerColumn.getColumn().setWidth(100);
		propertyViewerColumn.getColumn().setText("Property");
		
		TableViewerColumn valueViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		valueViewerColumn.getColumn().setMoveable(true);
		valueViewerColumn.getColumn().setWidth(100);
		valueViewerColumn.getColumn().setText("Value");
		
		Composite buttonComposite = new Composite(this, SWT.NONE);
		GridLayout gl_buttonComposite = new GridLayout(1, false);
		gl_buttonComposite.marginWidth = 0;
		gl_buttonComposite.marginHeight = 0;
		buttonComposite.setLayout(gl_buttonComposite);
		buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		
		addButton = new Button(buttonComposite, SWT.NONE);
		GridData gd_addButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_addButton.widthHint = 100;
		addButton.setLayoutData(gd_addButton);
		addButton.setText("Add");
		
		editButton = new Button(buttonComposite, SWT.NONE);
		GridData gd_editButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_editButton.widthHint = 100;
		editButton.setLayoutData(gd_editButton);
		editButton.setText("Edit");
		editButton.setEnabled(false);
		
		deleteButton = new Button(buttonComposite, SWT.NONE);
		GridData gd_deleteButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_deleteButton.widthHint = 100;
		deleteButton.setLayoutData(gd_deleteButton);
		deleteButton.setText("Delete");
		deleteButton.setEnabled(false);
		
		// UI elements listeners
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			  // Identify the selected row
        TableItem item = (TableItem) e.item;
        if (item == null) {
        	selectedTableItem = null;
          editButton.setEnabled(false);
          deleteButton.setEnabled(false);
        	return;
        } else {
        	selectedTableItem = item;
          editButton.setEnabled(true);
          deleteButton.setEnabled(true);
        }
			}
		});
		
		addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				launchPropertyDialog(getParent().getShell(), null);
			}
		});
		
		editButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				launchPropertyDialog(getParent().getShell(), selectedTableItem);
			}
		});
		
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteRow(selectedTableItem);
				editButton.setEnabled(false);
				deleteButton.setEnabled(false);
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * Set the DataAdapter to edit.
	 * The UI will be updated with the content of this adapter
	 * @param dataAdapter
	 */
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.dataAdapter = dataAdapter;
		Map<String, String> map = new HashMap<String, String>();
		map = this.dataAdapter.getProperties();
		fillContent(map);
	}
	
	private void fillContent(Map<String, String> map) {
		
		//right way to handle map
		String[] keys = new String[map.size()];
		Iterator<String> it = map.keySet().iterator();
		int i=0;
		while (it.hasNext())
		{
			keys[i] = it.next();
			i++;
		}
		Arrays.sort(keys);
		
		for (String key : keys)
		{
			addRow(new String[] {key, map.get(key)});
		}
		
		//another way but blurrier
		/*Iterator< Entry<String, String> > i = map.entrySet().iterator();
		
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			TableItem tableItem = new TableItem(table, SWT.NONE);
			String[] line = new String[] {me.getKey().toString(),me.getValue().toString()};
			tableItem.setText(line);
		}*/
	}

	public DataAdapter getDataAdapter() {
		if(dataAdapter == null){
		 // dataAdapter should never be null
		}
		Map<String, String> map = getAllProperties();
		dataAdapter.loadProperties(map);
		return dataAdapter;
	}

	public String getHelpContextId() {
		return "";
	}

	public static void addRow(String[] keyvalue) {
		TableItem tableItem = new TableItem(tableViewer.getTable(), SWT.NONE);
		String[] row = keyvalue;
		tableItem.setText(row);
	}

	public static void modifyRow(TableItem selectedTableItem, String[] newKeyValue) {
		selectedTableItem.setText(newKeyValue);
	}
	
	public static void deleteRow(TableItem selectedTableItem) {
		int index = tableViewer.getTable().indexOf(selectedTableItem);
		tableViewer.getTable().remove(index);
	}
	
	public static boolean isKeyUnique(String key) {
		TableItem[] tableItems = tableViewer.getTable().getItems();
		for (TableItem tableItem : tableItems) {
			if ( key.equals(tableItem.getText()) ) return false;
		}
		return true;
	}
	
	private void launchPropertyDialog(Shell shell, TableItem tableItem) {
		PropertyDialog testDialog = new PropertyDialog(shell, tableItem);
		testDialog.create();
		if (testDialog.open() == Window.OK) {
			//
		}
	}

	private Map<String, String> getAllProperties() {
		TableItem[] tableItems = tableViewer.getTable().getItems();
		Map<String, String> map = new HashMap<String, String>();
		for (TableItem tableItem : tableItems) {
			map.put(tableItem.getText(0), tableItem.getText(1));
		}
		return map;
	}
}
