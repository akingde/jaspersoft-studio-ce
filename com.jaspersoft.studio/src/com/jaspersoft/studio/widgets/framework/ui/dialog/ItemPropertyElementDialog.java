/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui.dialog;

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

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.ItemPropertyLayoutData;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationTitleAreaDialog;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Dialog used to define the value of a property when the edit button is pressed
 */
public class ItemPropertyElementDialog extends PersistentLocationTitleAreaDialog {
	
	private ItemPropertyDescription<?> ipDesc;
	
	private String staticValue;
	
	private JRExpression expressionValue;
	
	private WItemProperty itemProperty;
	
	protected ExpressionContext context;
	
	protected boolean isExpressionMode = false;
	
	private IPropertyEditor dialogPropertyEditor = new PropertyEditorAdapter() {
		
		public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
			expressionValue = valueExpression;
			staticValue = value;
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

	public ItemPropertyElementDialog(Shell parentShell, ItemPropertyDescription<?> ipDesc, WItemProperty itemProperty) {
		super(parentShell);
		this.staticValue = itemProperty.getStaticValue();
		JRExpression expressionValue = itemProperty.getExpressionValue();
		this.expressionValue = expressionValue != null ? (JRExpression)expressionValue.clone() : null;
		this.context = itemProperty.getExpressionContext();
		this.ipDesc = ipDesc.clone();
		this.isExpressionMode = itemProperty.isExpressionMode();
		setSaveSettings(false);
	}
	
	public ItemPropertyElementDialog(Shell parentShell, ItemPropertyDescription<?> ipDesc, String staticValue, JRExpression expressionValue, ExpressionContext context) {
		super(parentShell);
		this.staticValue = staticValue;
		this.expressionValue = expressionValue != null ? (JRExpression)expressionValue.clone() : null;
		this.context = context;
		this.ipDesc = ipDesc.clone();
		this.isExpressionMode = expressionValue != null;
		setSaveSettings(false);
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
		};
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(NLS.bind(Messages.ItemPropertyElementDialog_0, ipDesc.getName() != null ? ipDesc.getName() : "")); // $NON-NLS-2$ //$NON-NLS-1$
		setMessage(ipDesc.getDescription());
		Composite dialogArea = new Composite(parent, SWT.NONE);
		dialogArea.setLayoutData(new GridData(GridData.FILL_BOTH));
		dialogArea.setLayout(new GridLayout(1, false));

		Button useExpressionCheckbox = new Button(dialogArea, SWT.CHECK);
		useExpressionCheckbox.setText(Messages.ItemPropertyElementDialog_2);
		useExpressionCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		useExpressionCheckbox.setSelection(isExpressionMode);
		useExpressionCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isExpressionMode = ((Button)e.widget).getSelection();
				itemProperty.updateWidget();
			}
		});

		itemProperty = createProperty(dialogArea, ipDesc, dialogPropertyEditor);
		itemProperty.setLayoutData(new GridData(GridData.FILL_BOTH));
		ItemPropertyLayoutData contentLayout = new ItemPropertyLayoutData();
		contentLayout.expressionFillVertical = true;
		itemProperty.setContentLayoutData(contentLayout);
		itemProperty.setExpressionContext(context);
		
		//Use as default width a static value, compute the height of the main control basing
		//assuming as its width the same of the dialog and use the result to calculate the height
		//to he control height is added a padding of 200 because the dialog has also the title and
		//buttons area that require space
		Point controlSize = itemProperty.computeSize(500, SWT.DEFAULT);
		setDefaultSize(500, Math.max(controlSize.y + 200, 300));
	
		itemProperty.updateWidget();
		
		return dialogArea;
	}
	
	
	public JRExpression getExpressionValue(){
		return expressionValue;
	};
	
	public String getStaticValue(){
		return staticValue;
	}
	
	@Override
	public boolean close() {
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
}
