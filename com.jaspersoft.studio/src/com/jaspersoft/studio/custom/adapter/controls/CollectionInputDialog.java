/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.custom.adapter.controls;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.table.TableLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;

/**
 * Dialog to edit, add or remove values from a list control
 * 
 * @author Orlandin Marco
 *
 */
public class CollectionInputDialog extends PersistentLocationDialog{
	
	/**
	 * The viewer of a table with one column where the content of the list
	 * is shown
	 */
	private TableViewer tableViewer;
	
	/**
	 * Represent the type of the value inside the list, valid value are int,float,string,boolean
	 */
	private String type;
	
	/**
	 * The current values inside the list
	 */
	private List<Object> values;
	
	/**
	 * Create the dialog
	 * 
	 * @param parentShell the parent shell
	 * @param values the values inside the list, it can be null if the list is empty
	 * @param elementType the type of the value inside the list, valid value are int,float,string,boolean
	 */
	public CollectionInputDialog(Shell parentShell, List<Object> values, String elementType) {
		super(parentShell);
		this.type = elementType;
		if (values == null){
			this.values = new ArrayList<Object>();
		} else {
			this.values = new ArrayList<Object>(values);
		}
	}

	/**
	 * Configure Shell attributes like setText
	 */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.CollectionInputDialog_dialogTitle);
	}

	/**
	 * Create the table and the button to add, edit, or delete
	 * the content of the list values.
	 * 
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		buildTable(composite);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(parent.getBackground());
		
		//Create the add button
		final Button bnew = new Button(bGroup, SWT.PUSH);
		bnew.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				ElementValueDialog dialog = new ElementValueDialog(getShell(), null, type);
				if (dialog.open() == Dialog.OK){
					values.add(dialog.getReturnValue());
					tableViewer.refresh();
				}
			}
		});
		bnew.setText(Messages.common_add);
		bnew.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		
		//Create the edit button
		final Button editButton = new Button(bGroup, SWT.PUSH);
		editButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				int index = tableViewer.getTable().getSelectionIndex();
				if (index != -1){
					ElementValueDialog dialog = new ElementValueDialog(getShell(), values.get(index).toString(), type);
					if (dialog.open() == Dialog.OK){
						values.set(index, dialog.getReturnValue());
						tableViewer.refresh();
					}
				}
			}
		});
		editButton.setText(Messages.common_edit);
		editButton.setEnabled(false);
		editButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		
		//Create the delete button
		final Button deleteButton = new Button(bGroup, SWT.PUSH);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				int index = tableViewer.getTable().getSelectionIndex();
				if (index != -1){
					values.remove(index);
					tableViewer.refresh();
				}
			}
		});
		deleteButton.setText(Messages.common_delete);
		deleteButton.setEnabled(false);
		deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		//Add a listener to the list to enable edit and delete only when an element is selected
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				int selectionIndex = tableViewer.getTable().getSelectionIndex();
				deleteButton.setEnabled(selectionIndex != -1);
				editButton.setEnabled(selectionIndex != -1);
			}
		});
		tableViewer.setInput(values);
		return composite;
	}

	/**
	 * Create the table control
	 * 
	 * @param composite composite where the table is placed
	 */
	private void buildTable(Composite composite) {
		Table table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 400;
		gd.minimumWidth = 400;
		table.setLayoutData(gd);
		table.setHeaderVisible(false);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[1];
		column[0] = new TableColumn(table, SWT.NONE);

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

	}

	private void attachLabelProvider(TableViewer viewer) {
		viewer.setLabelProvider(new TableLabelProvider());
	}

	private void attachContentProvider(TableViewer viewer) {
		viewer.setContentProvider(new ListContentProvider());
	}
	
	/**
	 * Return the values actually defined
	 * 
	 * @return a not null list of the value actually defined
	 */
	public List<Object> getValues(){
		return values;
	}
}
