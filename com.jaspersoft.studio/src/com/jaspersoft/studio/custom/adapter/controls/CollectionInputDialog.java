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

import com.jaspersoft.studio.preferences.editor.table.TableLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;

public class CollectionInputDialog extends Dialog{
	
	private TableViewer tableViewer;
	
	private String type;
	
	private List<Object> values;
	
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
		shell.setText("Edit Collection Values");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		buildTable(composite);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(parent.getBackground());

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
		bnew.setText("Add");
		bnew.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		
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
		editButton.setText("Edit");
		editButton.setEnabled(false);
		editButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		
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
		deleteButton.setText("Delete");
		deleteButton.setEnabled(false);
		deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false));
		
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
	
	public List<Object> getValues(){
		return values;
	}
}
