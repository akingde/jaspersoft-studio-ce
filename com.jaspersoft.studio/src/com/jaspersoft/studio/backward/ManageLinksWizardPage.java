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
package com.jaspersoft.studio.backward;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.builder.JRDefinition;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * 
 * Show a table with all the links to the defined jr definitions, from 
 * this dialog the user can add a new link or edit\delete a link in the table
 * 
 * @author Orlandin Marco
 *
 */
public class ManageLinksWizardPage extends JSSHelpWizardPage {

	/**
	 * List of links used as input of the table
	 */
	private List<JRDefinition> input;
	
	/**
	 * Viewer of the table where all the links are displayed
	 */
	private TableViewer viewer;
	
	/**
	 * Button to add a new link
	 */
	private Button addButton;
	
	/**
	 * Button to delete an existing link
	 */
	private Button deleteButton;
	
	/**
	 * Button to edit an existing link
	 */
	private Button editButton;
	
	/**
	 * Label provider for the table, simply return the version of a jr pointed by
	 * a link for the first column and the link for the second one
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				return ((JRDefinition) element).getVersion();
			case 1:
				return ((JRDefinition) element).getResourceURL();
			}
			return ""; //$NON-NLS-1$
		}
	}
	
	/**
	 * Create the page, the initial list of links is taken directly by copying 
	 * the one on the JRBackwardManager
	 */
	public ManageLinksWizardPage() {
		super("manageLinkWizardPage"); //$NON-NLS-1$
		setTitle(Messages.ManageLinksWizardPage_pageTitle);
		setDescription(Messages.ManageLinksWizardPage_pageDescription);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		//Get the input of the table
		input = new ArrayList<JRDefinition>();
		for(JRDefinition def : JRBackwardManager.INSTANCE.getDefinitions()){
			input.add((JRDefinition)def.clone());
		}
		
		//Create the table
		viewer = new TableViewer(container, SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		viewer.setLabelProvider(new TLabelProvider());
		viewer.setContentProvider(new ListContentProvider());

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(20, 50, true));
		tlayout.addColumnData(new ColumnWeightData(80, 300, true));
		viewer.getTable().setLayout(tlayout);
		GridData tableData = new GridData(GridData.FILL_BOTH);
		tableData.widthHint = 400;
		tableData.heightHint = 450;
		viewer.getTable().setLayoutData(tableData);
		viewer.getTable().setHeaderVisible(true);
		
		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(viewer.getTable(), SWT.NONE);
		column[0].setText(Messages.LinkWizardPage_versionLabel);

		column[1] = new TableColumn(viewer.getTable(), SWT.NONE);
		column[1].setText(Messages.LinkWizardPage_urlLabel);

		for (int i = 0, n = column.length; i < n; i++){
			column[i].pack();
		}
		viewer.setInput(input);
		
		//Create the buttons area
		
		Composite buttonContainer = new Composite(container, SWT.NONE);
		buttonContainer.setLayout(new GridLayout(1,false));
		buttonContainer.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		createButtons(buttonContainer);
		
		//listener to disable the edit and delete button when nothing is selected
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel = (StructuredSelection)viewer.getSelection();
				deleteButton.setEnabled(sel != null && !sel.isEmpty());
				editButton.setEnabled(sel != null && !sel.isEmpty());
			}
		});
		
		//listener to do the edit action on the selected element after a double click
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				editAction();
			}
		});
		
		setControl(container);
	}

	/**
	 * Create the buttons and their listeners
	 * 
	 * @param buttonContainer composite where the button are placed
	 */
	protected void createButtons(Composite buttonContainer){
		addButton = new Button(buttonContainer, SWT.PUSH);
		addButton.setText(Messages.common_add);
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter(){
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				LinkWizard wizard = new LinkWizard(input);
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				dialog.open();
				viewer.setInput(input);
			}
		});
		
		editButton = new Button(buttonContainer, SWT.PUSH);
		editButton.setText(Messages.common_edit);
		editButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		editButton.addSelectionListener(new SelectionAdapter(){
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				editAction();
			}
		});
		editButton.setEnabled(false);
		
		deleteButton = new Button(buttonContainer, SWT.PUSH);
		deleteButton.setText(Messages.common_delete);
		deleteButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteButton.addSelectionListener(new SelectionAdapter(){
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (viewer.getTable().getSelectionIndex() != -1){
					input.remove(viewer.getTable().getSelectionIndex());
					viewer.setInput(input);
				}
			}
		});
		deleteButton.setEnabled(false);
	}
	
	/**
	 * Action to edit the selected link, open the dialog to allow to edit it
	 */
	protected void editAction(){
		if (viewer.getTable().getSelectionIndex() != -1){
			JRDefinition modfiedElement = input.get(viewer.getTable().getSelectionIndex());
			LinkWizard wizard = new LinkWizard(modfiedElement, input);
			WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
			dialog.open();
			viewer.setInput(input);
		}
	}
	
	@Override
	protected String getContextName() {
		return null;
	}

	/**
	 * Return the actual list of the links displayed inside the table
	 * 
	 * @return a not null list of JRDefinition
 	 */
	public List<JRDefinition> getElements(){
		return input;
	}
}
