/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.property.infoList.ElementDescription;
import com.jaspersoft.studio.property.infoList.SelectableComposite;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.ItemPropertyLayoutData;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.PersistentLocationTitleAreaDialog;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Dialog that allows editing the information associated to a {@link ItemProperty} element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ItemPropertyDialog extends PersistentLocationTitleAreaDialog {

	/**
	 * The {@link ItemPropertyDescription} that define the widget created inside
	 */
	private ItemPropertyDescription<?> ipDesc;
	
	/**
	 * The current static value
	 */
	private String staticValue;
	
	/**
	 * The current expression value
	 */
	private JRExpression expressionValue;
	
	/**
	 * The name of the property
	 */
	private String propertyName;
	
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
	protected boolean isExpressionMode = false;
	
	/**
	 * The descriptor containing all the properties of the element
	 */
	private ADescriptor descriptor;
	
	/**
	 * Text area to insert the property name of the property
	 */
	private Text propertyNameText;
	
	/**
	 * Area where the controls are created
	 */
	private Composite dialogArea;
	
	/**
	 * Editor used to store the value from the widget inside the field of this dialog
	 */
	private IPropertyEditor internalEditor = new PropertyEditorAdapter() {

		public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
			//Avoid to set both the fields so switching back from expression to value will keep 
			//the value of the other one; this happen only inside the dialog, when it is closed
			//only one of the two value is keep (the selected one)
			if (isExpressionMode){
				expressionValue = valueExpression;
			} else {
				staticValue = value;
			}
			validateDialog();
		};
		
		@Override
		public JRExpression getPropertyValueExpression(String propertyName) {
			return getExpressionValue();
		}

		@Override
		public String getPropertyValue(String propertyName) {
			return getStaticValue();
		}
	};

	public ItemPropertyDialog(Shell parentShell, ItemProperty handledProperty, ADescriptor descriptor, ExpressionContext context) {
		super(parentShell);
		setSaveSettings(false);
		setDefaultSize(450, 350);
		this.context = context;
		if (handledProperty != null){
			staticValue = handledProperty.getValue();
			expressionValue = handledProperty.getValueExpression();
			propertyName = handledProperty.getName();
		} else {
			staticValue = "";
			expressionValue = null;
			propertyName = "";
		}
		this.isExpressionMode = expressionValue != null;
		this.descriptor = descriptor;	
		ItemPropertyDescription<?> ipDesc = descriptor.getDescription(propertyName);
		if (ipDesc == null)
			this.ipDesc = new TextPropertyDescription<String>(propertyName, "", false);
		else
			this.ipDesc = ipDesc.clone();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.ItemPropertyElementDialog_shellTitle);
	}
	
	protected WItemProperty createProperty(Composite parent, ItemPropertyDescription<?> idDesc, IPropertyEditor editor){
		return new WItemProperty(parent, SWT.NONE, ipDesc, editor){
			@Override
			public boolean isExpressionMode() {
				return isExpressionMode;
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
	
	public JRExpression getExpressionValue(){
		return expressionValue;
	};
	
	public String getStaticValue(){
		return staticValue;
	}
	
	public StandardItemProperty getValue(){
		return new StandardItemProperty(propertyName, getStaticValue(), getExpressionValue());
	}
	
	@Override
	public boolean close() {
		descriptor.setOldItemProperty(null);
		if (isExpressionMode){
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
	protected Control createDialogArea(Composite parent) {
		setTitle(Messages.ItemPropertyDialog_EditItemProperty);
		setMessage("Define the value of the new property");
		dialogArea = new Composite(parent, SWT.NONE);
		dialogArea.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		dialogArea.setLayout(layout);
		
		Label lblPropertyName = new Label(dialogArea, SWT.NONE);
		lblPropertyName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		lblPropertyName.setText(Messages.ItemPropertyDialog_PropertyName);
		
		propertyNameText = new Text(dialogArea, SWT.BORDER);
		propertyNameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		propertyNameText.setText(propertyName);

		Button useExpressionCheckbox = new Button(dialogArea, SWT.CHECK);
		useExpressionCheckbox.setText(Messages.ItemPropertyElementDialog_2);
		useExpressionCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		useExpressionCheckbox.setSelection(isExpressionMode);
		useExpressionCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isExpressionMode = ((Button)e.widget).getSelection();
				itemProperty.updateWidget();
				validateDialog();
			}
		});
		
		Label lblPropertyValue = new Label(dialogArea, SWT.NONE);
		lblPropertyValue.setText(Messages.ItemPropertyDialog_PropertyValue);
		lblPropertyValue.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		itemProperty = createProperty(dialogArea, ipDesc, internalEditor);
		itemProperty.setLayoutData(new GridData(GridData.FILL_BOTH));
		ItemPropertyLayoutData contentLayout = new ItemPropertyLayoutData();
		contentLayout.expressionFillVertical = true;
		contentLayout.buttonVisibleSimpleMode = false;
		itemProperty.setContentLayoutData(contentLayout);
		itemProperty.setExpressionContext(context);

		List<ElementDescription> hints = getPropertiesInformation();
		if (!hints.isEmpty()){
			final SelectableComposite infoPanel = new SelectableComposite(dialogArea);
			infoPanel.setItems(hints);
			GridData infoGD = new GridData(SWT.FILL, SWT.FILL, true, true);
			infoGD.heightHint = 200;
			infoGD.verticalIndent = 5;
			infoPanel.setLayoutData(infoGD);
			infoPanel.SetDoubleClickListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					String newname = infoPanel.getSelectedElement().getName();
					ItemPropertyDescription<?> ipDescNew = descriptor.getDescription(newname);
					if (ipDescNew != null) {
						propertyName = newname;
						staticValue = ipDescNew.getDefaultValueString();
						expressionValue = null;
						ItemPropertyDescription<?> ipDesc = descriptor.getDescription(propertyName);
						rebuildWidget(ipDesc);
					}
				}
			});
		}
		
		itemProperty.updateWidget();
		addListeners();
		return dialogArea;
	}
	
	/**
	 * Rebuild the widget that will be used to as value input widget
	 * 
	 * @param ipDesc the {@link ItemPropertyDescription} o the widget, can be null and in this case a 
	 * standard {@link TextPropertyDescription} will be used
	 */
	protected void rebuildWidget(ItemPropertyDescription<?> ipDesc){
		itemProperty.dispose();
		if (ipDesc == null) {
			ItemPropertyDialog.this.ipDesc = new TextPropertyDescription<String>(propertyName, "", false);
		} else {
			ItemPropertyDialog.this.ipDesc = ipDesc.clone();
		}
		itemProperty = createProperty(dialogArea, ItemPropertyDialog.this.ipDesc, internalEditor);
		itemProperty.setLayoutData(new GridData(GridData.FILL_BOTH));
		ItemPropertyLayoutData contentLayout = new ItemPropertyLayoutData();
		contentLayout.expressionFillVertical = true;
		contentLayout.buttonVisibleSimpleMode = false;
		itemProperty.setContentLayoutData(contentLayout);
		itemProperty.setExpressionContext(context);
		dialogArea.layout();
		itemProperty.updateWidget();
	}

	private void addListeners() {
		propertyNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Point p = propertyNameText.getSelection();
				propertyName = propertyNameText.getText();

				ItemPropertyDescription<?> ipDesc = descriptor.getDescription(propertyName);
				rebuildWidget(ipDesc);

				propertyNameText.setSelection(p);
				validateDialog();
			}
		});
	}
	
	/**
	 * Need to do the validation after the contents are created, 
	 * otherwise the button will not be correctly disabled
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control cmp = super.createContents(parent);
		validateDialog();
		return cmp;
	}

	private List<ElementDescription> getPropertiesInformation() {
		List<ElementDescription> descriptions = new ArrayList<ElementDescription>();
		for (ItemPropertyDescription<?> ipd : descriptor.getItemPropertyDescriptors())
			descriptions.add(new ElementDescription(ipd.getName(), ipd.getDescription(), false));
		return descriptions;
	}

	protected void validateDialog() {
		Button ok = getButton(IDialogConstants.OK_ID);
		String str = null;
		try {
			descriptor.validateItem(getValue());
		} catch (Exception e) {
			str = e.getMessage();
		}
		if (Misc.isNullOrEmpty(str)){
			setErrorMessage(null);
			if (ok != null){
				ok.setEnabled(true);
			}
		} else {
			setErrorMessage(str);
			if (ok != null){
				ok.setEnabled(false);
			}
		}
		
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(newShellStyle | SWT.RESIZE | SWT.PRIMARY_MODAL);
		setBlockOnOpen(true);
	}
}
