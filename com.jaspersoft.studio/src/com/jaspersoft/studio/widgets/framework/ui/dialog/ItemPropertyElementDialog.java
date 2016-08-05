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
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.menu.IMenuProvider;
import com.jaspersoft.studio.widgets.framework.ui.menu.StandardContextualMenu;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationTitleAreaDialog;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ItemPropertyElementDialog extends PersistentLocationTitleAreaDialog implements IExpressionContextSetter, IWItemProperty {

	private Button useExpressionCheckbox;
	private Control propertyValue;
	private WTextExpression propertyValueExpression;
	private ExpressionContext expContext;
	private Composite dialogArea;
	private ItemPropertyDescription<?> ipDesc;
	private StackLayout layout;
	private Composite stackComposite;
	private Composite editorComposite;
	private Composite expressionComposite;
	
	private String staticValue;
	
	private JRExpression expressionValue;
	
	private boolean refresh = false;

	public ItemPropertyElementDialog(Shell parentShell, String staticValue, JRExpression expressionValue, ItemPropertyDescription<?> ipDesc) {
		super(parentShell);
		this.staticValue = staticValue;
		this.expressionValue = expressionValue != null ? (JRExpression)expressionValue.clone() : null;
		setDefaultSize(500, 210);
		setSaveSettings(false);
		this.ipDesc = ipDesc.clone(new PropertyEditorAdapter() {
			
			@Override
			public JRExpression getPropertyValueExpression(String propertyName) {
				return getExpressionValue();
			}
			
			@Override
			public String getPropertyValue(String propertyName) {
				return getStaticValue();
			}
		});
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(NLS.bind(Messages.ItemPropertyElementDialog_0, ipDesc.getName() != null ? ipDesc.getName() : "")); // $NON-NLS-2$
		setMessage(ipDesc.getDescription());
		dialogArea = new Composite(parent, SWT.NONE);
		dialogArea.setLayoutData(new GridData(GridData.FILL_BOTH));
		dialogArea.setLayout(new GridLayout(1, false));

		useExpressionCheckbox = new Button(dialogArea, SWT.CHECK);
		useExpressionCheckbox.setText(Messages.ItemPropertyElementDialog_2);
		useExpressionCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		stackComposite = new Composite(dialogArea, SWT.NONE);
		layout = new StackLayout();
		stackComposite.setLayout(layout);
		stackComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		editorComposite = new Composite(stackComposite, SWT.NONE);
		editorComposite.setLayout(getNoPadLayout(1));
		//Need a second composite to force the control to not grow on all the visible space
		Composite editorControlComposite = new Composite(editorComposite, SWT.NONE);
		editorControlComposite.setLayout(getNoPadLayout(1));
		editorControlComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		propertyValue = ipDesc.createControl(this, editorControlComposite);
		
		expressionComposite = new Composite(stackComposite, SWT.NONE);
		expressionComposite.setLayout(getNoPadLayout(1));
		propertyValueExpression = new WTextExpression(expressionComposite, SWT.NONE);
		propertyValueExpression.setExpressionContext(this.expContext);
		propertyValueExpression.setLayoutData(new GridData(GridData.FILL_BOTH));

		setValue(staticValue, expressionValue);
		addListeners();

		return dialogArea;
	}
	
	protected GridLayout getNoPadLayout(int colNumber){
		GridLayout result = new GridLayout(colNumber, false);
		result.horizontalSpacing = 0;
		result.verticalSpacing = 0;
		result.marginWidth = 0;
		result.marginHeight = 0;
		return result;
	}
		
	private void addListeners() {
		propertyValueExpression.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				if (isRefresh())
					return;
				setRefresh(true);
				try{
					expressionValue = event.modifiedExpression;
				} finally {
					setRefresh(false);
				}
			}
		});
		
		useExpressionCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isRefresh())
					return;
				if (useExpressionCheckbox.getSelection()){
					layout.topControl = expressionComposite;
					useExpressionCheckbox.setSelection(true);
					propertyValueExpression.setFocus();
				} else {
					useExpressionCheckbox.setSelection(false);
					updateWidget();
					layout.topControl = editorComposite;
					propertyValue.setFocus();
				}
				stackComposite.layout(true);
			}
		});
	}

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

	@Override
	public void setRefresh(boolean refreshing) {
		this.refresh = refreshing;
	}

	@Override
	public boolean isRefresh() {
		return refresh;
	}

	public void setValue(String staticValue, JRExpression expressionValue) {
		if (isRefresh())
			return;
		setRefresh(true);
		try {
			if (expressionValue != null){
				this.expressionValue = expressionValue;
			}
			if (staticValue != null){
				this.staticValue = staticValue;
			}
			if (expressionValue != null) {
				layout.topControl = expressionComposite;
				propertyValueExpression.setExpression((JRDesignExpression)expressionValue);
				useExpressionCheckbox.setSelection(true);
				propertyValueExpression.setFocus();
			} else {
				useExpressionCheckbox.setSelection(false);
				ipDesc.update(propertyValue,  this);
				layout.topControl = editorComposite;
				propertyValue.setFocus();
			}
			stackComposite.layout(true);
		} finally {
			setRefresh(false);
		}
	}

	@Override
	public Control getControl() {
		return propertyValue;
	}
	
	public JRExpression getExpressionValue(){
		return expressionValue;
	};
	
	public String getStaticValue(){
		return staticValue;
	}

	@Override
	public String getPropertyName() {
		return ipDesc.getName();
	}
	
	@Override
	public boolean close() {
		if (isExpressionMode()){
			staticValue = null;
		} else {
			expressionValue = null;
		}
		return super.close();
	}

	@Override
	public boolean isExpressionMode() {
		return useExpressionCheckbox.getSelection();
	}
	
	@Override
	public IMenuProvider getContextualMenuProvider() {
		return StandardContextualMenu.INSTANCE;
	}

	@Override
	public void updateWidget() {
		setRefresh(true);
		try{
			ipDesc.update(propertyValue, this);
		} finally {
			setRefresh(false);
		}
	}

}
