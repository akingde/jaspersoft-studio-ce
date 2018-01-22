/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.returnvalue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.events.ChangeEvent;
import com.jaspersoft.studio.swt.events.ChangeListener;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.ListOrderButtons;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Wizard page to show the return values of a component and to interact with them
 * 
 * @author Orlandin Marco
 * 
 */
public abstract class RVPropertyPage extends JSSHelpWizardPage {

	/**
	 * Suggested width int for the page
	 */
	public final static int WIDTH_HINT = 730;

	/**
	 * The actual values shown inside the dialog
	 */
	protected List<ReturnValueContainer> values;

	/**
	 * The table where the value are shown
	 */
	protected Table table;

	/**
	 * The viewer for the table
	 */
	protected TableViewer tableViewer;

	/**
	 * The button to add a return value
	 */
	private Button addButton;

	/**
	 * The button to edit the return value selected in the table
	 */
	private Button editButton;

	/**
	 * The button to delete the return value selected in the table
	 */
	private Button deleteButton;

	/**
	 * The list of toVariables currently used
	 */
	protected String[] toVariables;
	
	/**
	 * The list of fromVariables currently used
	 */
	private String[] fromVariables;

	/**
	 * The design where the dataset of the current report
	 */
	protected JasperDesign design;
	
	/**
	 * A list of modify listener that are called when the values in the table changes
	 */
	private List<ModifyListener> returnValuesModified = new ArrayList<ModifyListener>();

	/**
	 * Text provider for the column of the table
	 */
	private final class TLabelProvider extends LabelProvider implements ITableLabelProvider {

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			ReturnValueContainer val = (ReturnValueContainer) element;
			switch (columnIndex) {
			case 0:
				return val.getFromVariable();
			case 1:
				return val.getToVariable();
			case 2:
				return val.getCalculation().getName();
			case 3:
				return Misc.nvl(val.getIncrementerFactoryClassName());
			}
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Create the class
	 * 
	 * @param pageName
	 *          name of the page
	 */
	protected RVPropertyPage(String pageName, JasperDesign design) {
		super(pageName);
		this.design = design;
	}

	/**
	 * Create the buttons to add, edit, delete or remove an element, plus the buttons to move the selected element up or
	 * down
	 * 
	 * @param bGroup
	 *          container composite of the buttons
	 */
	private void createButtons(Composite bGroup) {

		// CREATE THE ADD BUTTON

		addButton = new Button(bGroup, SWT.PUSH);
		addButton.setText(Messages.common_add);
		addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				openAddDialog();
			}
		});

		// CREATE THE EDIT BUTTON

		editButton = new Button(bGroup, SWT.PUSH);
		editButton.setText(Messages.common_edit);
		editButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		editButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
				if (selection.size() > 0) {
					ReturnValueContainer selectedValue = (ReturnValueContainer) selection.getFirstElement();
					openEditDialog(selectedValue);
				}
			}
		});

		editButton.setEnabled(false);

		// CREATE THE DELETE BUTTON

		deleteButton = new Button(bGroup, SWT.PUSH);
		deleteButton.setText(Messages.common_delete);
		deleteButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		deleteButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
				if (selection.size() > 0) {
					ReturnValueContainer selectedValue = (ReturnValueContainer) selection.getFirstElement();
					int index = values.indexOf(selectedValue);
					values.remove(index);
					tableViewer.refresh();
					toVariables = null;
					updateButtonsStatus();
					callModifyListeners();
				}
			}
		});

		deleteButton.setEnabled(false);

		// CREATE THE MOVMENT BUTTON

		ListOrderButtons upDownButtons = new ListOrderButtons();
		upDownButtons.createOrderButtons(bGroup, tableViewer);
		upDownButtons.addChangeListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event) {
				callModifyListeners();
			}
		});

		// ADD THE LISTENER TO ENABLE OR DISABLE THE EDIT BUTTON

		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection) tableViewer.getSelection();
				boolean enabledStatus = selection != null && selection.size() > 0;
				editButton.setEnabled(enabledStatus);
				deleteButton.setEnabled(enabledStatus);
			}
		});

		updateButtonsStatus();
	}
	
	protected void openAddDialog(){
		ReturnValueContainer container = new ReturnValueContainer();
		InputReturnValueDialog inputDialog = new InputReturnValueDialog(getShell(), container, getToVariablesNames());
		if (inputDialog.open() == Dialog.OK) {
			addElemenet(container);
		}
	}
	
	protected void addElemenet(ReturnValueContainer container){
		values.add(container);
		tableViewer.refresh();
		toVariables = null;
		fromVariables = null;
		updateButtonsStatus();
		callModifyListeners();
	}
	
	protected void openEditDialog(ReturnValueContainer edited){
		ReturnValueContainer result = edited.clone();
		String[] toVariables = getVariablesPlusElement(getToVariablesNames(), edited.getToVariable());
		InputReturnValueDialog inputDialog = new InputReturnValueDialog(getShell(), result, toVariables);
		if (inputDialog.open() == Dialog.OK) {
			editElement(edited, result);
		}
	}

	/**
	 * Open the dialog to edit an existing element and replace it when the dialog is closed with the ok button
	 * 
	 * @param edited
	 *          the element to edit
	 */
	protected void editElement(ReturnValueContainer edited, ReturnValueContainer newValue) {
		int index = values.indexOf(edited);
		values.set(index, newValue);
		tableViewer.refresh();
		toVariables = null;
		fromVariables = null;
		updateButtonsStatus();
		callModifyListeners();
	}

	/**
	 * Update the status of the buttons enabling or disabling them if there are input errors
	 */
	protected void updateButtonsStatus() {
		if (addButton != null) {
			if (getToVariablesNames().length == 0) {
				addButton.setEnabled(false);
				setErrorMessage(Messages.RVPropertyPage_error_message_report_variables_all_used);
			} else {
				addButton.setEnabled(true);
				setErrorMessage(null);
			}
		}
	}

	/**
	 * Create the table control and attach to it the viewer
	 * 
	 * @param composite
	 *          parent of the table
	 */
	private void buildTable(Composite composite) {
		table = new Table(composite, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL);
		table.setHeaderVisible(true);

		tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ListContentProvider());
		tableViewer.setLabelProvider(new TLabelProvider());
		attachCellEditors(tableViewer, table);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(15, 55, true));
		tlayout.addColumnData(new ColumnWeightData(15, 55, true));
		tlayout.addColumnData(new ColumnWeightData(15, 55, true));
		tlayout.addColumnData(new ColumnWeightData(55, 100, true));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[4];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.RVPropertyPage_subreport_variable);

		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.RVPropertyPage_to_variable);

		column[2] = new TableColumn(table, SWT.NONE);
		column[2].setText(Messages.RVPropertyPage_calculation_type);

		column[3] = new TableColumn(table, SWT.NONE);
		column[3].setText(Messages.RVPropertyPage_incrementer_factory_class);

		fillTable();
		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();
	}

	/**
	 * Attach to the table the cell editors to directly edit the return parameter value without enter inside the edit
	 * dialog
	 * 
	 * @param viewer
	 *          viewer of the table
	 * @param parent
	 *          container for the cell editor controls
	 */
	private void attachCellEditors(final TableViewer viewer, Composite parent) {

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				int selectedIndex = table.getSelectionIndex();
				if (selectedIndex != -1) {
					ReturnValueContainer selectedElement = values.get(selectedIndex);
					openEditDialog(selectedElement);
				}
			}
		});

		viewer.setColumnProperties(new String[] { "FROMVARIABLE", "TOVARIABLE", "CALCULATIONTYPE",
				"INCREMENTERFACTORYCLASS" });
	}

	/**
	 * Return an hashset of the variable names that are already used as to variables
	 * 
	 * @return not null hashset of variables already used as a to variable
	 */
	protected HashSet<String> getAlreadyUsedToVariables() {
		HashSet<String> result = new HashSet<String>();
		for (ReturnValueContainer value : values) {
			result.add(value.getToVariable());
		}
		return result;
	}

	/**
	 * Return an hashset of the variable names that are already used as from variables
	 * 
	 * @return not null hashset of variables already used as a from variable
	 */
	private HashSet<String> getAlreadyUsedFromVariables() {
		HashSet<String> result = new HashSet<String>();
		for (ReturnValueContainer value : values) {
			result.add(value.getFromVariable());
		}
		return result;
	}
	
	/**
	 * Get an array of all the variables that can be used as to variable. These are variables defined for the element on
	 * its dataset that aren't already used as to variable
	 * 
	 * @return a not null array of variable names that can be used as to variables
	 */
	protected String[] getToVariablesNames() {
		if (toVariables == null) {
			List<String> res = new ArrayList<String>();
			HashSet<String> usedVariables = getAlreadyUsedToVariables();
			JRVariable[] vlist = getMainDatasetVariables();
			for (JRVariable o : vlist) {
				JRDesignVariable jdVar = (JRDesignVariable) o;
				if (!jdVar.isSystemDefined() && !usedVariables.contains(jdVar.getName()))
					res.add(jdVar.getName());
			}
			return res.toArray(new String[res.size()]);
		}
		return toVariables;
	}
	
	/**
	 * Get an array of all the variables that can be used as to variable. These are variables defined for the element on
	 * its dataset that aren't already used as to variable
	 * 
	 * @return a not null array of variable names that can be used as to variables
	 */
	protected String[] getFromVariablesNames() {
		if (fromVariables == null) {
			List<String> res = new ArrayList<String>();
			HashSet<String> usedVariables = getAlreadyUsedFromVariables();
			JRVariable[] vlist = getDatasetVariables();
			for (JRVariable o : vlist) {
				JRDesignVariable jdVar = (JRDesignVariable) o;
				if (!jdVar.isSystemDefined() && !usedVariables.contains(jdVar.getName()))
					res.add(jdVar.getName());
			}
			return res.toArray(new String[res.size()]);
		}
		return fromVariables;
	}

	/**
	 * add the parameter to the top of the variable names. Used when edit a
	 * return parameter to provide in the list of the to variables also the one already used by the edited element
	 * 
	 * @param elementToAdd
	 *          element to add to the available variables array
	 * @return not null array of variable names that can be used as to variables plus a static value added to the top
	 */
	protected String[] getVariablesPlusElement(String[] variables, String elementToAdd) {
		String[] result = new String[variables.length + 1];
		result[0] = elementToAdd;
		for (int i = 1; i < result.length; i++) {
			result[i] = variables[i - 1];
		}
		return result;
	}

	/**
	 * Call all the modify listeners defined
	 */
	private void callModifyListeners() {
		Event e = new Event();
		e.data = values;
		e.widget = table;
		ModifyEvent modEvent = new ModifyEvent(e);
		for (ModifyListener listener : returnValuesModified) {
			listener.modifyText(modEvent);
		}
	}

	/**
	 * When a return parameter is edited directly from the table then it is validated to allow to exit the dialog only if
	 * there aren't error. The only checked error is that more then one return values uses the same to variable
	 */
	protected boolean validate() {
		// validate toVariable is unique
		List<String> lto = new ArrayList<String>();
		for (ReturnValueContainer d : values)
			lto.add(d.getToVariable());
		int size = lto.size();
		int setSize = new HashSet<String>(lto).size();
		if (size != setSize) {
			setErrorMessage(Messages.RVPropertyPage_error_message_return_variables_contain_duplicate_tovariable_values);
			setPageComplete(false);
			return false;
		} else {
			setErrorMessage(null);
			setPageComplete(true);
			return true;
		}
	}

	/**
	 * Set the input of the table with the current values
	 */
	protected void fillTable() {
		tableViewer.setInput(values);
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_RETURN_VALUE;
	}

	/**
	 * Create the controls of the page
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		setControl(composite);

		buildTable(composite);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 300;
		gd.minimumWidth = 500;
		gd.widthHint = 500;
		table.setLayoutData(gd);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		createButtons(bGroup);
	}

	/**
	 * Set the values showed inside the control when it is created. The list of value is cloned so it isn't modified
	 * directly. If the value is null an empty list is used
	 * 
	 * @param value
	 *          the list to show in the dialog when it is created
	 */
	public void setValue(List<ReturnValueContainer> value) {
		if (value == null) {
			this.values = new ArrayList<ReturnValueContainer>();
		} else {
			this.values = new ArrayList<ReturnValueContainer>(value);
		}
		if (table != null) {
			fillTable();
		}
		updateButtonsStatus();
	}

	/**
	 * Return the list actual list of value as it is shown inside the table
	 * 
	 * @return a not null list of value. If the wizard ends correctly it can be used to update the return values on the
	 *         element
	 */
	public List<ReturnValueContainer> getValue() {
		return values;
	}

	/**
	 * Add a modify listener that will be colled when a return value is added, edited or deleted
	 * 
	 * @param listener
	 *          the listener to call
	 */
	public void addModifyListener(ModifyListener listener) {
		returnValuesModified.add(listener);
	}

	/**
	 * Return the list of variables provided by the element trough it's dataset
	 * 
	 * @return a not null list of variable
	 */
	public abstract JRVariable[] getDatasetVariables();

	/**
	 * Return the list of variables provided by the main dataset
	 * 
	 * @return a not null list of variable
	 */
	protected JRVariable[] getMainDatasetVariables() {
		if (design == null)
			return new JRVariable[0];
		JRDataset dataset = design.getMainDataset();
		if (dataset != null)
			return dataset.getVariables();
		return new JRVariable[0];
	}
}
