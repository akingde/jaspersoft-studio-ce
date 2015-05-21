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
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.editor.action.copy.PastableProperties;
import com.jaspersoft.studio.help.TableHelpListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.CopyElementExpressionProperty;
import com.jaspersoft.studio.model.CopyElementProperty;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.JRPropertyDialog;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

public class JRPropertyPage extends JSSHelpWizardPage {

	private JRPropertiesMap value;
	
	private Table table;
	
	private TableViewer tableViewer;
	
	private List<PropertyDTO> props = new ArrayList<PropertyDTO>();
	
	/**
	 * Button used to edit a parameter 
	 */
	private Button editButton;
	
	/**
	 * Button used to delete a parameter 
	 */
	private Button deleteButton;

	@Override
	public void dispose() {
		// clear all properties
		value = new JRPropertiesMap();
		for (PropertyDTO p : props) {
			if (p.getName() != null && !p.getName().equals("")) //$NON-NLS-1$
				value.setProperty(p.getName(), p.getValue().toString());
		}
		super.dispose();
	}

	public void setValue(JRPropertiesMap value) {
		this.value = value;
		if (value == null) {
			value = new JRPropertiesMap();
		}
		if (table != null){
			fillTable();
		}
	}

	protected JRPropertyPage(String pageName) {
		super(pageName);
		setTitle(Messages.common_properties);
		setDescription(Messages.JRPropertyPage_description);
	}
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_PROPERTIES;
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

		Button addButton = new Button(bGroup, SWT.PUSH);
		addButton.setText(Messages.common_add);
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				PropertyDTO p = new PropertyDTO(getPropertyName(), "NEW_VALUE");
				JRPropertyDialog dialog = new JRPropertyDialog(Display.getDefault().getActiveShell());
				dialog.setValue(p);
				if (dialog.open() == Window.OK){
					props.add(p);
					tableViewer.refresh();
				}
			}
		});
		
		editButton = new Button(bGroup, SWT.PUSH);
		editButton.setText(Messages.common_edit);
		editButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		editButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection)tableViewer.getSelection();
				if (selection.size() > 0){
					PropertyDTO selectedValue = (PropertyDTO)selection.getFirstElement();
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
					PropertyDTO selectedValue = (PropertyDTO)selection.getFirstElement();
					int index = props.indexOf(selectedValue);
					props.remove(index);
					tableViewer.refresh();
				}
			}
		});
		
		deleteButton.setEnabled(false);
		
		new ListOrderButtons().createOrderButtons(bGroup, tableViewer);
	}
	
	private String getPropertyName() {
		String name = "newproperty";
		int i = 0;
		while (nameExist(name)){
			name = "newproperty_"+i;
			i++;
		}
		return name;
	}
	
	private boolean nameExist(String name){
		for (PropertyDTO prm : props) {
			if (prm.getName() != null && prm.getName().trim().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Edit an element opened a dialog to allow to modify it
	 * 
	 * @param edited the element to edit, must be not null
	 */
	private void editElement(PropertyDTO edited){
		PropertyDTO result = edited.clone();
		JRPropertyDialog inputDialog = new JRPropertyDialog(Display.getDefault().getActiveShell());
		inputDialog.setValue(result);
		if (inputDialog.open() == Dialog.OK){
			int index = props.indexOf(edited);
			props.set(index, result);
			tableViewer.refresh();
		}
	}

	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TPropertyLabelProvider());
	
		attachCellEditors(tableViewer, table);
		
		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.common_name);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.JRPropertyPage_value);

		fillTable();
		for (int i = 0, n = column.length; i < n; i++) {
			column[i].pack();
		}

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(50, true));
		tlayout.addColumnData(new ColumnWeightData(50, true));
		table.setLayout(tlayout);
		//Set the help for the elements
		TableHelpListener.setTableHelp(table);
		
		//Crete the popup menu
		createPopoupMenu();
	}
	
	private void createPopoupMenu(){
		Menu tableMenu = new Menu(table);
		final MenuItem copyItem = new MenuItem(tableMenu, SWT.NONE);
		copyItem.setText(Messages.common_copy);
		copyItem.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection)tableViewer.getSelection();
				List<ICopyable> copyList = new ArrayList<ICopyable>();
				for(Object selected : selection.toList()){
					PropertyDTO prop = (PropertyDTO)selected;
					if (prop.isExpression()){
						copyList.add(new CopyElementExpressionProperty(prop.getName(), prop.getValue()));
					} else {
						copyList.add(new CopyElementProperty(prop.getName(), prop.getValue()));
					}
				}
				if (!copyList.isEmpty()){
					PastableProperties container = new PastableProperties(copyList);
					Clipboard.getDefault().setContents(container);
				}
			}
			
		});
		
		final MenuItem pasteItem = new MenuItem(tableMenu, SWT.NONE);
		pasteItem.setText(Messages.common_paste);
		pasteItem.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				PastableProperties pasteContainer = (PastableProperties)Clipboard.getDefault().getContents();
				List<CopyElementExpressionProperty> copiedProperties = pasteContainer.getCopiedProperties();
				for(CopyElementExpressionProperty property : copiedProperties){
					if (!property.isExpression() && !containsProperty(property.getPropertyName())){
						props.add(new PropertyDTO(property.getPropertyName(), property.getValue()));
					}
				}
				tableViewer.refresh();
			}
			
		});
		
		tableMenu.addMenuListener(new MenuListener() {
			
			@Override
			public void menuShown(MenuEvent e) {
				copyItem.setEnabled(!tableViewer.getSelection().isEmpty());
				boolean pasteEnabled = false;
				if (Clipboard.getDefault().getContents() instanceof PastableProperties){
					PastableProperties pasteContainer = (PastableProperties)Clipboard.getDefault().getContents();
					List<CopyElementExpressionProperty> copiedProperties = pasteContainer.getCopiedProperties();
					pasteEnabled = copiedProperties != null && !copiedProperties.isEmpty() && canPaste(copiedProperties);
				} 
				pasteItem.setEnabled(pasteEnabled);
			}
			
			@Override
			public void menuHidden(MenuEvent e) {
			}
		});
		
		table.setMenu(tableMenu);
	}

	private void attachCellEditors(final TableViewer viewer, Composite parent) {
		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				int selectedIndex = table.getSelectionIndex();
				if (selectedIndex != -1) {
					PropertyDTO selectedElement = props.get(selectedIndex);
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
	}

	private void fillTable() {
		props.clear();
		String[] names = value.getPropertyNames();
		for (int i = 0; i < names.length; i++) {
			PropertyDTO p = new PropertyDTO(names[i], value.getProperty(names[i]));
			props.add(p);
		}
		tableViewer.setInput(props);
	}
	
	private boolean containsProperty(String propertyName){
		for(PropertyDTO property : props){
			if (property.getName().equals(propertyName)) return true;
		}
		return false;
	}
	
	/**
	 * Check if at least one of the copied properties can be pasted on the current element
	 * 
	 * @param copiedProperties the copied properties
	 * @return true if at least one of the copied properties can be pasted, false otherwise
	 */
	private boolean canPaste(List<CopyElementExpressionProperty> copiedProperties){
		for(CopyElementExpressionProperty property : copiedProperties){
			if (!property.isExpression() && !containsProperty(property.getPropertyName())){
				return true;
			}
		}
		return false;
	}
	
	public JRPropertiesMap getValue() {
		return value;
	}
}
