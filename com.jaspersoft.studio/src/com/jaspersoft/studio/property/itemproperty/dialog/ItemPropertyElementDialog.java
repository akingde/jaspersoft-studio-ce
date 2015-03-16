/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;

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
import com.jaspersoft.studio.property.itemproperty.ItemPropertyLabelProvider;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.IWItemProperty;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

/**
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ItemPropertyElementDialog extends ATitledDialog implements IExpressionContextSetter, IWItemProperty {

	private Button useExpressionCheckbox;
	private Control propertyValue;
	private WTextExpression propertyValueExpression;
	private ExpressionContext expContext;
	private StandardItemProperty pname;
	private Composite dialogArea;
	private ItemPropertyDescription<?> ipDesc;
	private ADescriptor descriptor;

	public ItemPropertyElementDialog(Shell parentShell, StandardItemProperty pname, ADescriptor descriptor) {
		super(parentShell);
		this.descriptor = descriptor;
		this.pname = (StandardItemProperty) pname.clone();
		ipDesc = descriptor.getDescription(pname.getName());
		setTitle(NLS.bind(Messages.ItemPropertyElementDialog_0, pname != null ? pname.getName() : "")); //$NON-NLS-2$
		setDescription(ipDesc.getDescription());
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		dialogArea = (Composite) super.createDialogArea(parent);
		dialogArea.setLayout(new GridLayout(1, false));

		useExpressionCheckbox = new Button(dialogArea, SWT.CHECK);
		useExpressionCheckbox.setText(Messages.ItemPropertyElementDialog_2);
		useExpressionCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		cmp = new Composite(dialogArea, SWT.NONE);
		layout = new StackLayout();
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		propertyValue = ipDesc.createControl(this, cmp);

		propertyValueExpression = new WTextExpression(cmp, SWT.NONE);
		propertyValueExpression.setExpressionContext(this.expContext);

		setValue(pname);
		addListeners();

		return dialogArea;
	}

	private boolean refresh = false;

	private void addListeners() {
		propertyValueExpression.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				pname.setValueExpression(event.modifiedExpression);
			}
		});
		useExpressionCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (refresh)
					return;
				if (useExpressionCheckbox.getSelection())
					pname.setValueExpression(new JRDesignExpression());
				else
					pname.setValueExpression(null);
				setValue(pname);
			}
		});
	}

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

	@Override
	public boolean isRefresh() {
		return refresh;
	}

	@Override
	public StandardItemProperty getValue() {
		return pname;
	}

	@Override
	public void setValue(StandardItemProperty exp) {
		if (refresh)
			return;
		refresh = true;
		try {
			if (pname.getValueExpression() != null) {
				layout.topControl = propertyValueExpression;
				useExpressionCheckbox.setSelection(true);
				propertyValueExpression.setExpression((JRDesignExpression) pname.getValueExpression());
				propertyValueExpression.setFocus();
			} else {
				useExpressionCheckbox.setSelection(false);
				layout.topControl = propertyValue;

				ipDesc.setValue(propertyValue, this);
				propertyValueExpression.setExpression(null);
				propertyValue.setFocus();
			}

			UIUtils.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					cmp.layout(true);
				}
			});
		} finally {
			refresh = false;
		}
	}

	private ItemPropertyLabelProvider lprovider = new ItemPropertyLabelProvider(descriptor);
	private StackLayout layout;
	private Composite cmp;

	@Override
	public ItemPropertyLabelProvider getLabelProvider() {
		return lprovider;
	}

	@Override
	public Control getControl() {
		return propertyValue;
	}

}
