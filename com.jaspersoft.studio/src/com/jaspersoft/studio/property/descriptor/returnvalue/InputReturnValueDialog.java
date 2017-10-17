/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.returnvalue;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.core.search.BasicSearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.dialogs.SelectionDialog;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;
import net.sf.jasperreports.engine.fill.JRIncrementerFactory;
import net.sf.jasperreports.engine.type.CalculationEnum;

/**
 * Dialog to provide the configuration of a dataset run return value
 * 
 * @author Orlandin Marco
 *
 */
@SuppressWarnings("restriction")
public class InputReturnValueDialog extends PersistentLocationDialog {

	/**
	 * The textual list of the possible Calculation value
	 */
	private static final String[] calculationValues = EnumHelper.getEnumNames(CalculationEnum.values(), NullEnum.NOTNULL);
	
	/**
	 * Text where the user can insert a valid increment class factory
	 */
	protected Text incrementText;
	
	/**
	 * Combo where the calculation function can be selected
	 */
	protected Combo calculation;
	
	/**
	 * Text area to select the from variable
	 */
	protected Text fromVariable;
	
	/**
	 * Combo to select the to variable from on of the available values
	 */
	protected Combo toVariable;
	
	/**
	 * Container where the values selected from the widget ar stored
	 */
	protected ReturnValueContainer rvContainer;
	
	/**
	 * Possible values for the combo toVariable
	 */
	protected String[] toVariables;
	
	/**
	 * Widget called when a widget is modified to update
	 * the container
	 */
	protected ModifyListener widgetModified = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			updateContainer();
		}
	};
	
	/**
	 * Create the dialog
	 * 
	 * @param parentShell the shell
	 * @param rvContainer a not null container, the widget will be initialized with the content
	 * of this container, useful for edit operations and the output will be stored also inside this value
	 * @param toVariables Possible values for the combo toVariable
	 */
	public InputReturnValueDialog(Shell parentShell, ReturnValueContainer rvContainer, String[] toVariables) {
		super(parentShell);
		Assert.isNotNull(rvContainer);
		this.rvContainer = rvContainer;
		this.toVariables = toVariables;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		if (newShell != null){
			newShell.setText(Messages.InputReturnValueDialog_dialogTitle);
		}
	}
	
	/**
	 * Create the control for the from variable as a text area
	 * 
	 * @param container the parent container, it will have a gridlayout with two 
	 * columns
	 */
	protected void createFromVariable(Composite container){
		Label fromVariableLabel = new Label(container, SWT.NONE);
		fromVariableLabel.setText(Messages.RVPropertyPage_subreport_variable);
		
		fromVariable = new Text(container, SWT.BORDER);
		fromVariable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	/**
	 * Create the control for the to variable as not editable combo
	 * 
	 * @param container the parent container, it will have a gridlayout with two 
	 * columns
	 */
	protected void createToVariable(Composite container){
		Label toVariableLabel = new Label(container, SWT.NONE);
		toVariableLabel.setText(Messages.RVPropertyPage_to_variable);
		
		toVariable = new Combo(container, SWT.READ_ONLY);
		toVariable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		toVariable.setItems(toVariables);
	}
	
	/**
	 * Initialize the from and to control with the passed container and attach
	 * the modify listeners to them
	 */
	protected void initializeVariables(){
		if (rvContainer.getToVariable() != null) {
			int index = ArrayUtils.indexOf(toVariables, rvContainer.getToVariable());
			if (index == ArrayUtils.INDEX_NOT_FOUND) index = 0;
			toVariable.select(index);
		} else {
			toVariable.select(0);
		}
		
		if (rvContainer.getFromVariable() != null) {
			fromVariable.setText(rvContainer.getFromVariable());
		}
		
		toVariable.addModifyListener(widgetModified);
		fromVariable.addModifyListener(widgetModified);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		createFromVariable(container);
		createToVariable(container);
		
		Label calculationLabel = new Label(container, SWT.NONE);
		calculationLabel.setText(Messages.RVPropertyPage_calculation_type);
		
		calculation = new Combo(container, SWT.READ_ONLY);
		calculation.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		calculation.setItems(calculationValues);
		
		Label incrementLabel = new Label(container, SWT.NONE);
		incrementLabel.setText(Messages.RVPropertyPage_incrementer_factory_class);
		
		Composite incrementContainer = new Composite(container, SWT.NONE);
		GridLayout incrementContainerLayout = new GridLayout(2, false);
		incrementContainerLayout.horizontalSpacing = 0;
		incrementContainerLayout.verticalSpacing = 0;
		incrementContainerLayout.marginWidth = 0;
		incrementContainerLayout.marginHeight = 0;
		incrementContainer.setLayout(incrementContainerLayout);
		incrementContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		incrementText = new Text(incrementContainer, SWT.BORDER);
		incrementText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Button incrementButton = new Button(incrementContainer, SWT.NONE);
		incrementButton.setText("..."); //$NON-NLS-1$
		incrementButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SelectionDialog dialog;
				try {
					dialog = JavaUI.createTypeDialog(getShell(), new ProgressMonitorDialog(getShell()), getIncrementContext(), IJavaElementSearchConstants.CONSIDER_ALL_TYPES, false);
					dialog.setTitle(Messages.ClassTypeCellEditor_open_type);
					dialog.setMessage(Messages.ClassTypeCellEditor_dialog_message);
					if (dialog.open() == Window.OK) {
						if (dialog.getResult() != null && dialog.getResult().length > 0) {
							IType bt = (IType) dialog.getResult()[0];
							incrementText.setText(bt.getFullyQualifiedName());
						}
					}
				} catch (JavaModelException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//INITIALIZE THE WIDGET WITH THE CONTENT OF THE CONTAINER IF IT IS VALID
		
		if (rvContainer.getCalculation() != null) {
			int index = ArrayUtils.indexOf(CalculationEnum.values(), rvContainer.getCalculation());
			if (index == ArrayUtils.INDEX_NOT_FOUND) index = 0;
			calculation.select(index);
		} 

		if (rvContainer.getIncrementerFactoryClassName() != null) incrementText.setText(rvContainer.getIncrementerFactoryClassName());
		
		//ADD THE MODIFY LISTENER AT THE END TO AVOID THAT IT'S CALLED DURING THE INITIALIZATION
		
		initializeVariables();
		incrementText.addModifyListener(widgetModified);
		calculation.addModifyListener(widgetModified);
		updateContainer();
		return container;
	}
	
	/**
	 * Save the value from the widget inside the container
	 */
	protected void updateContainer(){
		rvContainer.setCalculation(CalculationEnum.values()[calculation.getSelectionIndex()]);
		rvContainer.setToVariable(toVariable.getText());
		rvContainer.setFromVariable(fromVariable.getText());
		rvContainer.setIncrementerFactoryClassName(incrementText.getText());
		validate();
	}
	
	/**
	 * Create all the controls and validate the content of the dialog 
	 * 
	 * @param parent the parent control
	 * @return return the container control
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		validate();
		return contents;
	}
	
	/**
	 * Check if the content of the widget is valid and enable\disable the ok button.
	 * Essentially the only validity check is a name not empty for the from variable
	 */
	protected void validate(){
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null){
			okButton.setEnabled(fromVariable.getText() != null && !fromVariable.getText().trim().isEmpty());
		}
	}
	
	/**
	 * Get the scope to search in the workspace a class that implements the JRIncrementFactory
	 * 
	 * @return a not null java search scope to fine an implementations of JRIncrementFactory
	 */
	private IJavaSearchScope getIncrementContext(){
		IJavaSearchScope searchScope = SearchEngine.createWorkspaceScope();
		IProject prj = ((IFileEditorInput) SelectionHelper.getActiveJRXMLEditor().getEditorInput()).getFile().getProject();
		if (prj != null) {
			try{
				IJavaProject jprj = JavaCore.create(prj);
				IType t;
				t = jprj.findType(JRIncrementerFactory.class.getName());
				if (t != null){
						searchScope = BasicSearchEngine.createHierarchyScope(t);
				}
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return searchScope;
	}
}
