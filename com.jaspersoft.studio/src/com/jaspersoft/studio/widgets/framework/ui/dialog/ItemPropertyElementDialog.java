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
import org.eclipse.swt.graphics.Point;
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
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
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
	
	private IPropertyEditor dialogPropertyEditor = new PropertyEditorAdapter() {
		
		@Override
		public JRExpression getPropertyValueExpression(String propertyName) {
			return getExpressionValue();
		}
		
		@Override
		public String getPropertyValue(String propertyName) {
			return getStaticValue();
		}
	};

	public ItemPropertyElementDialog(Shell parentShell, String staticValue, JRExpression expressionValue, ItemPropertyDescription<?> ipDesc) {
		super(parentShell);
		this.staticValue = staticValue;
		this.expressionValue = expressionValue != null ? (JRExpression)expressionValue.clone() : null;
		setSaveSettings(false);
		this.ipDesc = ipDesc.clone();
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
		editorComposite.setLayout(WidgetFactory.getNoPadLayout(1));
		//Need a second composite to force the control to not grow on all the visible space
		Composite editorControlComposite = new Composite(editorComposite, SWT.NONE);
		editorControlComposite.setLayout(WidgetFactory.getNoPadLayout(1));
		editorControlComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		propertyValue = ipDesc.createControl(this, editorControlComposite);
		
		//Use as default width a static value, compute the height of the main control basing
		//assuming as its width the same of the dialog and use the result to calculate the height
		//to he control height is added a padding of 200 because the dialog has also the title and
		//buttons area that require space
		Point controlSize = editorControlComposite.computeSize(500, SWT.DEFAULT);
		setDefaultSize(500, controlSize.y + 200);
		
		expressionComposite = new Composite(stackComposite, SWT.NONE);
		expressionComposite.setLayout(WidgetFactory.getNoPadLayout(1));
		propertyValueExpression = new WTextExpression(expressionComposite, SWT.NONE);
		propertyValueExpression.setExpressionContext(this.expContext);
		propertyValueExpression.setLayoutData(new GridData(GridData.FILL_BOTH));

		setValue(staticValue, expressionValue);
		addListeners();

		return dialogArea;
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
		if (useExpressionCheckbox.getSelection()){
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

	/**
	 * This dialog has is own controls for the expression, so it will force the expression mode to
	 * false for the {@link ItemPropertyDescription}
	 */
	@Override
	public boolean isExpressionMode() {
		return false;
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

	@Override
	public IPropertyEditor getPropertyEditor() {
		return dialogPropertyEditor;
	}

	@Override
	public Object getFallbackValue() {
		return ipDesc.getFallbackValue();
	}
}
