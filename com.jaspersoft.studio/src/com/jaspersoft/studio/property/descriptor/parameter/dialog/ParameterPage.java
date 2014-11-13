/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.parameter.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetParameter;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;

public class ParameterPage extends WizardPage implements IExpressionContextSetter {
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return ((JRDatasetParameter) element).getName();
			case 1:
				JRDatasetParameter value2 = (JRDatasetParameter) element;
				if (value2 != null && value2.getExpression() != null)
					return value2.getExpression().getText();
			}
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Parameters of the current dataset run
	 */
	private List<JRDatasetParameter> value = new ArrayList<JRDatasetParameter>();
	
	/**
	 * Table where the user can select the parameter of the dataset run and their expression
	 */
	private Table table;
	
	/**
	 * Viewer of the table
	 */
	private TableViewer tableViewer;
	
	/**
	 * Actual expression context
	 */
	private ExpressionContext expContext;
	
	/**
	 * Button used to edit a parameter inside the dataset run
	 */
	private Button editButton;
	
	/**
	 * Button used to delete a parameter inside the dataset run
	 */
	private Button deleteButton;
	
	/**
	 * Button used to create a new parameter inside the dataset run
	 */
	private Button addButton;
	
	/**
	 * Create an instance of the pace
	 * @param pageName
	 */
	protected ParameterPage(String pageName) {
		super(pageName);
		setTitle(Messages.ParameterPage_dataset_parameters);
		setDescription(Messages.ParameterPage_description);
	}
	
	/**
	 * Return all the parameters that should be inside the dataset run
	 * 
	 * @return a parametersDTO containing ALL the parameters that should be inside 
	 * the dataset run
	 */
	public ParameterDTO getValue() {
		ParameterDTO result = new ParameterDTO();
		result.setValue(value.toArray(new JRDatasetParameter[value.size()]));
		return result;
	}
	
	public void setValue(ParameterDTO value) {
		if (value != null && value.getValue() != null){
			this.value = new ArrayList<JRDatasetParameter>();
			if (value.getValue() != null)
				this.value.addAll(Arrays.asList(value.getValue()));
		} 
		if (table != null)
			fillTable();
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

		//CREATE THE ADD BUTTON
		
		addButton = new Button(bGroup, SWT.PUSH);
		addButton.setText(Messages.common_add);
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputParameterDialog inputDialog = new InputParameterDialog(getShell(), value);
				inputDialog.setExpressionContext(expContext);
				if (inputDialog.open() == Dialog.OK){
					value.add(inputDialog.getValue());
					tableViewer.refresh();
				}
			}
		});
		
		//CREATE THE EDIT BUTTON
		
		editButton = new Button(bGroup, SWT.PUSH);
		editButton.setText(Messages.common_edit);
		editButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		editButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection)tableViewer.getSelection();
				if (selection.size() > 0){
					JRDatasetParameter selectedValue = (JRDatasetParameter)selection.getFirstElement();
					editElement(selectedValue);
				}
			}
		});
		
		editButton.setEnabled(false);
		
		//CREATE THE DELETE BUTTON
		
		deleteButton = new Button(bGroup, SWT.PUSH);
		deleteButton.setText(Messages.common_delete);
		deleteButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection)tableViewer.getSelection();
				if (selection.size() > 0){
					JRDatasetParameter selectedValue = (JRDatasetParameter)selection.getFirstElement();
					int index = value.indexOf(selectedValue);
					value.remove(index);
					tableViewer.refresh();
				}
			}
		});
		
		deleteButton.setEnabled(false);
		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);
	}
	
	/**
	 * Edit an element opened a dialog to allow to modify it
	 * 
	 * @param edited the element to edit, must be not null
	 */
	private void editElement(JRDatasetParameter edited){
		JRDesignDatasetParameter result = (JRDesignDatasetParameter)edited.clone();
		InputParameterDialog inputDialog = new InputParameterDialog(getShell(), result, value);
		inputDialog.setExpressionContext(expContext);
		if (inputDialog.open() == Dialog.OK){
			int index = value.indexOf(edited);
			value.set(index, result);
			tableViewer.refresh();
		}
	}

	/**
	 * Create the table control
	 * 
	 * @param composite parent of the table
	 */
	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TLabelProvider());
		attachCellEditors(tableViewer);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.common_name);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.common_expression);

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

		fillTable();
	}

	/**
	 * Attach the listeners and the labels to the table
	 * 
	 * @param viewer the viewer of the table
	 */
	private void attachCellEditors(final TableViewer viewer) {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				int selectedIndex = table.getSelectionIndex();
				if (selectedIndex != -1){
					JRDatasetParameter selectedElement = value.get(selectedIndex);
					editElement(selectedElement);
				}
			}
		});
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				int selectedIndex = table.getSelectionIndex();
				boolean buttonEnabled = selectedIndex != -1;
				editButton.setEnabled(buttonEnabled);
				deleteButton.setEnabled(buttonEnabled);
			}
		});
		
		viewer.setColumnProperties(new String[] { "NAME", "VALUE" }); //$NON-NLS-1$ //$NON-NLS-2$
		viewer.setLabelProvider(new TLabelProvider());
	}

	/**
	 * Set the input of the table to the stored value list
	 */
	private void fillTable() {
		if (value != null) {
			tableViewer.setInput(value);
		}
	}
	
	/**
	 * Set the expression context
	 */
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}
}

