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
package com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.components.chart.property.widget.CustomizerDefinitionManager;
import com.jaspersoft.studio.components.chart.property.widget.CustomizerWidgetsDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationTitleAreaDialog;
import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.util.JRClassLoader;

/**
 * Dialog to select a chart customizer definition from the preferences or providing 
 * a {@link JRChartCustomizer} classname
 * 
 * @author Orlandin Marco
 *
 */
public class SelectCustomizerDialog extends PersistentLocationTitleAreaDialog {

	/**
	 * The tab to switch between preferences customizer and class customizer
	 */
	private TabFolder tabFolder;
	
	/**
	 * The {@link ChartCustomizerDefinition} created from the controls of the preferences customizer (first tab)
	 */
	private ChartCustomizerDefinition valueSelection = null;
	
	/**
	 * The {@link ChartCustomizerDefinition} created from the controls of the class customizer (second tab)
	 */
	private ChartCustomizerDefinition valueAdvanced = null;
	
	/**
	 * Key to be used when a {@link ChartCustomizerDefinition} is created, it can be passed by a constructor when editing
	 */
	private String definitionKey;
	
	/**
	 * List of the customizer available in the preferences
	 */
	private List<CustomizerWidgetsDescriptor> tableInput;
	
	/**
	 * flag to keep track if currently it is selected the preferences customizer tab of the class tab
	 */
	private boolean isAdvacedMode = false;
	
	/**
	 * The text are where the class name can be typed in the class tab
	 */
	private Text textArea;
	
	/**
	 * The preferences where the customizer can be selected among the ones defined in the preferences
	 */
	private TableViewer table;
	
	/**
	 * The modify listener to update the class result when something in the text area changes
	 */
	private ModifyListener textModifyListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			String text = ((Text)e.widget).getText();
			valueAdvanced = new ChartCustomizerDefinition(text, definitionKey);
			validate();
		}
	};
	
	/**
	 * Create a dialog to define a new customizer
	 * 
	 * @param parentShell the shell
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report
	 * @param definitionKey an unique new key for the new {@link ChartCustomizerDefinition}
	 */
	public SelectCustomizerDialog(Shell parentShell, JasperReportsConfiguration jConfig, String definitionKey) {
		super(parentShell);
		tableInput = CustomizerDefinitionManager.getCustomizerDefinitions(jConfig);
		this.definitionKey = definitionKey;
		setShellStyle(getShellStyle() | SWT.RESIZE );
	}

	/**
	 * Create a dialog to edit a customizer
	 * 
	 * @param parentShell the shell
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report
	 * @param editedElement the element to edit, must be not null
	 */
	public SelectCustomizerDialog(Shell parentShell, JasperReportsConfiguration jConfig, ChartCustomizerDefinition editedElement) {
		super(parentShell);
		tableInput = CustomizerDefinitionManager.getCustomizerDefinitions(jConfig);
		this.definitionKey = editedElement.getKey();
		setShellStyle(getShellStyle() | SWT.RESIZE );
		if (editedElement.isOnlyClass()){
			valueAdvanced = editedElement.clone();
		} else {
			valueSelection = editedElement.clone();
		}
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		if (valueAdvanced == null && valueSelection == null){
			setTitle("Select the new Chart Customizer");
		} else {
			setTitle("Edit the selected Chart Customizer");
		}
		tabFolder = new TabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		TabItem selectionTab = new TabItem(tabFolder, SWT.NULL);
		selectionTab.setText("Available Customizers");	
		createTableArea(selectionTab);	
		
		TabItem advancedTab = new TabItem(tabFolder, SWT.NULL);
		advancedTab.setText("Advanced");
		createAdvancedArea(advancedTab);
		
		tabFolder.setSelection(0);
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isAdvacedMode = tabFolder.getSelectionIndex() != 0;
				validate();
			}
		});
		openEditedValues();
		validate();
		return tabFolder;
	}
	
	/**
	 * If it is an edit operation initialize the dialog with the value of the edited 
	 * {@link ChartCustomizerDefinition}
	 */
	protected void openEditedValues(){
		if (valueAdvanced != null){
			isAdvacedMode = true;
			tabFolder.setSelection(1);
			textArea.setText(valueAdvanced.getRawClass());
		}  else if (valueSelection != null){
			table.setSelection(new StructuredSelection(valueSelection.getDescriptor()));
		}
	}
	
	/**
	 * Create the table area

	 * @param tab tab where the controls will be created
	 */
	protected void createTableArea(TabItem tab){
		Composite container = new Composite(tabFolder, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		table = new TableViewer(container, SWT.FULL_SELECTION | SWT.SINGLE);
		table.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		table.setContentProvider(new ListContentProvider());
		tab.setControl(container);
		table.setInput(tableInput);
		table.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				if(selection.getFirstElement() != null){
					CustomizerWidgetsDescriptor selCustomizer = (CustomizerWidgetsDescriptor)selection.getFirstElement();
					valueSelection = new ChartCustomizerDefinition(selCustomizer, definitionKey);
					validate();
				}	
			}
		});
	}
	
	/**
	 * Create the classname area
	 * 
	 * @param tab where the controls will be created
	 */
	protected void createAdvancedArea(TabItem tab){
		Composite container = new Composite(tabFolder, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		new Label(container, SWT.NONE).setText("Customizer Class");
		
		textArea = new Text(container, SWT.BORDER);
		textArea.addModifyListener(textModifyListener);
		textArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button browseButton = new Button(container, SWT.PUSH);
		browseButton.setText("...");
		browseButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Class<?>> acceptedClasses = new ArrayList<Class<?>>();
				acceptedClasses.add(JRChartCustomizer.class);
				String classname = ClassTypeCellEditor.getJavaClassDialog(getShell(), acceptedClasses);
				if (classname != null){
					textArea.setText(classname);
				}
			}
			
		});
		tab.setControl(container);
	}
	
	/**
	 * Set dialog message depending on which tab is select
	 */
	protected void setTabMessage(){
		if (isAdvacedMode){
			setMessage("Select a JRChartCustomizer class");
		} else {
			setMessage("Select a customizer among the ones defined in the preferences");
		}
	}

	/**
	 * Validate the input depending on which tab is select
	 */
	protected void validate(){
		if (isAdvacedMode){
			String text = textArea.getText();
			if (text.trim().isEmpty()){
				setErrorMessage("The classname can not be empty");
				return;
			} else {
				try{
					JRClassLoader.loadClassForName(text);
				}catch (Exception ex){
					setErrorMessage(null);
					setMessage("The selected class cannot be found", IMessageProvider.WARNING);
					return;
				}
			}
		} else {
			if (table.getSelection() == null){
				setErrorMessage("You need to select a Chart Customizer from the table");
				return;
			} 
		}
		setErrorMessage(null);
		setTabMessage();
	}
	
	/**
	 * Return the final {@link ChartCustomizerDefinition}
	 * 
	 * @return a not null {@link ChartCustomizerDefinition}
	 */
	public ChartCustomizerDefinition getResult(){
		if (!isAdvacedMode){
			return valueSelection;
		} else {
			return valueAdvanced;
		}
	}

}
