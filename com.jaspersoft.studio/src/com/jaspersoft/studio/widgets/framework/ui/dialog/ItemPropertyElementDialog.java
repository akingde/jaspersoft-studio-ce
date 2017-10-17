/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui.dialog;

import java.util.HashMap;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.ItemPropertyLayoutData;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationTitleAreaDialog;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Dialog used inside a {@link WItemProperty} when the button to open the advance dialog
 * is pressed.  
 * 
 * @author Orlandin Marco
 */
public class ItemPropertyElementDialog extends PersistentLocationTitleAreaDialog {
	
	/**
	 * The {@link ItemPropertyDescription} that define the widget created inside
	 */
	protected ItemPropertyDescription<?> ipDesc;
	
	/**
	 * The current static value
	 */
	private String staticValue;
	
	/**
	 * The current expression value
	 */
	private JRExpression expressionValue;
	
	/**
	 * The {@link WItemProperty} used to build the widget
	 */
	private WItemProperty itemProperty;
	
	/**
	 * The context for the expression editor
	 */
	protected ExpressionContext context;
	
	/**
	 * Flag updated with the value of the checkbox, to force if it is an expression or not
	 */
	private boolean isExpressionMode = false;
	
	/**
	 * Flag used when the dialog is forced in expression mode. When this is set the dialog show only the expression field and not
	 * the simple editor
	 */
	private boolean forceExpressionMode = false;
	
	/**
	 * Hashmap used to store properties temporary properties that are not related to the edited property
	 * This is used since widgets can access directly to the {@link IPropertyEditor} and so the single
	 * widget inside this dialog can change many properties, but in the end only one is returned
	 */
	protected HashMap<String, String> customPropertiesMap = new HashMap<String, String>();
	
	/**
	 * Editor used to store the value from the widget inside the field of this dialog
	 */
	private IPropertyEditor dialogPropertyEditor = new PropertyEditorAdapter() {
		
		public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
			//Avoid to set both the fields so switching back from expression to value will keep 
			//the value of the other one; this happen only inside the dialog, when it is closed
			//only one of the two value is keep (the selected one)
			if (propertyName.equals(itemProperty.getPropertyName())){
				if (isExpressionMode()){
					expressionValue = valueExpression;
				} else {
					staticValue = value;
				}
			} else {
				customPropertiesMap.put(propertyName, value);
			}
		};
		
		@Override
		public JRExpression getPropertyValueExpression(String propertyName) {
			return getExpressionValue();
		}
		
		@Override
		public String getPropertyValue(String propertyName) {
			if (!propertyName.equals(itemProperty.getPropertyName())){
				return customPropertiesMap.get(propertyName);
			}
			return getStaticValue();
		}
	};

	/**
	 * Create the dialog
	 * 
	 * @param parentShell the shell for the dialog
	 * @param ipDesc the {@link ItemPropertyDescription} that define the type of the edited property, 
	 * it is cloned to be used inside this dialog, must be not null
	 * @param itemProperty the {@link WItemProperty}, it is used to retrieve the original value of the property, 
	 * both expression and static value and to know if it is in expression mode or not. Also the expression context 
	 * is read from this parameter. Must be not null
	 * 
	 */
	public ItemPropertyElementDialog(Shell parentShell, ItemPropertyDescription<?> ipDesc, WItemProperty itemProperty) {
		super(parentShell);
		this.staticValue = itemProperty.getStaticValue();
		JRExpression expressionValue = itemProperty.getExpressionValue();
		this.expressionValue = expressionValue != null ? (JRExpression)expressionValue.clone() : null;
		this.context = itemProperty.getExpressionContext();
		this.ipDesc = ipDesc.clone();
		//The initial status of the expression mode flag is retrieved from the WItemProperty
		this.isExpressionMode = itemProperty.isExpressionMode();
		setSaveSettings(false);
	}
	
	/**
	 * Create the dialog
	 * 
	 * @param parentShell the shell for the dialog
	 * @param ipDesc the {@link ItemPropertyDescription} that define the type of the edited property, 
	 * it is cloned to be used inside this dialog, must be not null
	 * @param staticValue the initial static value of the property, can be null
	 * @param expressionValue the initial expression value of the property, can be null
	 * @param context the {@link ExpressionContext} for the expression editor, must be not null
	 */
	public ItemPropertyElementDialog(Shell parentShell, ItemPropertyDescription<?> ipDesc, String staticValue, JRExpression expressionValue, ExpressionContext context) {
		super(parentShell);
		this.staticValue = staticValue;
		this.expressionValue = expressionValue != null ? (JRExpression)expressionValue.clone() : null;
		this.context = context;
		this.ipDesc = ipDesc.clone();
		//The initial status of the expression mode flag is retrieved from the presence or not of the expression
		this.isExpressionMode = expressionValue != null;
		setSaveSettings(false);
	}
	
	/**
	 * Set the flag used when the dialog is forced in expression mode. When this is set the dialog show only the expression field and not
	 * the simple editor. This must be called before the dialog is shown with the open method, otherwise this will not have
	 * effect
	 * 
	 * @param value true to show only the expression area, false otherwise
	 */
	public void setForceExpressionMode(boolean value){
		if (dialogArea == null){
			forceExpressionMode = value;
		} else {
			JaspersoftStudioPlugin.getInstance().logWarning( "The enforce expression method must be called before the dialog is opened");
		}
	}
	
	/**
	 * Return if the dialog is in expression mode or not
	 * 
	 * @return true if it is in expression mode, false otherwise
	 */
	public boolean isExpressionMode(){
		return forceExpressionMode ? true : isExpressionMode;
	}
	
	/**
	 * Set the title of the dialog
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.ItemPropertyElementDialog_shellTitle);
	}
	
	/**
	 * Create the {@link WItemProperty} used to create the editor widget. The {@link WItemProperty} build inside here
	 * should always return the value of the field isExpressionMode (updated by the checkbox) to decide if it is in expression
	 * mode or not, since it is decided trough that value
	 * 
	 * @param parent the parent of the created {@link WItemProperty} control
	 * @param idDesc the {@link ItemPropertyDescription} used inside the created {@link WItemProperty} the build the widget
	 * @param editor the {@link IPropertyEditor} used inside the created {@link WItemProperty}
	 * @return a not null {@link WItemProperty}
	 */
	protected WItemProperty createProperty(Composite parent, ItemPropertyDescription<?> idDesc, IPropertyEditor editor){
		return new WItemProperty(parent, SWT.NONE, ipDesc, editor){
			@Override
			public boolean isExpressionMode() {
				return ItemPropertyElementDialog.this.isExpressionMode();
			}
			
			/**
			 * The edit button in the dialog open the expression editor
			 */
			@Override
			protected void handleEditButton() {
				if(!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
					JRExpressionEditor wizard = new JRExpressionEditor();
					wizard.setValue((JRDesignExpression)getExpressionValue());
					wizard.setExpressionContext(getExpressionContext());
					WizardDialog dialog = ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(getShell(), wizard);
					if (dialog.open() == Dialog.OK) {
						JRDesignExpression value = wizard.getValue();
						setValue(null, value);
					}
				}
			}
		};
	}
	
	/**
	 * Created the area with the checkbox to seitch between expression and simple mode
	 */
	protected void createExpressionCheckbox(Composite dialogArea){
		Button useExpressionCheckbox = new Button(dialogArea, SWT.CHECK);
		useExpressionCheckbox.setText(Messages.ItemPropertyElementDialog_2);
		useExpressionCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		useExpressionCheckbox.setSelection(isExpressionMode());
		useExpressionCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isExpressionMode = ((Button)e.widget).getSelection();
				itemProperty.updateWidget();
			}
		});
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(NLS.bind(Messages.ItemPropertyElementDialog_0, ipDesc.getName() != null ? ipDesc.getName() : "")); // $NON-NLS-2$ //$NON-NLS-1$
		setMessage(ipDesc.getDescription());
		Composite dialogArea = new Composite(parent, SWT.NONE);
		dialogArea.setLayoutData(new GridData(GridData.FILL_BOTH));
		dialogArea.setLayout(new GridLayout(1, false));
		createMessageArea(dialogArea);
		
		if (!forceExpressionMode) createExpressionCheckbox(dialogArea);

		itemProperty = createProperty(dialogArea, ipDesc, dialogPropertyEditor);
		itemProperty.setLayoutData(new GridData(GridData.FILL_BOTH));
		ItemPropertyLayoutData contentLayout = new ItemPropertyLayoutData();
		contentLayout.expressionFillVertical = true;
		contentLayout.buttonVisibleSimpleMode = false;
		itemProperty.setContentLayoutData(contentLayout);
		itemProperty.setExpressionContext(context);
		
		//Use as default width a static value, compute the height of the main control basing
		//assuming as its width the same of the dialog and use the result to calculate the height
		//to he control height is added a padding of 250 because the dialog has also the title and
		//buttons area that require space
		Point controlSize = itemProperty.computeSize(450, SWT.DEFAULT);
		setDefaultSize(450, Math.max(controlSize.y + 250, 300));
	
		itemProperty.updateWidget();
		
		return dialogArea;
	}

	/**
	 * Create an additional message area before the normal composite used for editing 
	 * the property value with the widget or the expression.
	 * 
	 * @param parent the parent composite
	 */
	protected void createMessageArea(Composite parent) {
		
	}
	
	/**
	 * Return the current expression value
	 * 
	 * @return a {@link JRExpression} can be null
	 */
	public JRExpression getExpressionValue(){
		return expressionValue;
	};
	
	/**
	 * Return the current static value
	 * 
	 * @return a string, can be null
	 */
	public String getStaticValue(){
		return staticValue;
	}
	
	/**
	 * When the dialog is closed if the flag to force the expression was enabled it erase the 
	 * static value, otherwise it erase the expression value
	 */
	@Override
	public boolean close() {
		if (isExpressionMode()){
			staticValue = null;
			//if the user deosn't set an expression create it anyway
			if (expressionValue == null){
				expressionValue = new JRDesignExpression();
			}
		} else {
			expressionValue = null;
		}
		return super.close();
	}
	
	@Override
	protected boolean isResizable() {
		return true;
	}
}
