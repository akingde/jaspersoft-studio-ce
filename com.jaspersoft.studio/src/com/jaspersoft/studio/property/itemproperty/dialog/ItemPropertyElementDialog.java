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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.Misc;

/**
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ItemPropertyElementDialog extends ATitledDialog implements IExpressionContextSetter {

	private Button useExpressionCheckbox;
	private Text propertyValue;
	private WTextExpression propertyValueExpression;
	private ExpressionContext expContext;
	private StandardItemProperty pname;
	private Composite dialogArea;

	public ItemPropertyElementDialog(Shell parentShell, StandardItemProperty pname) {
		super(parentShell);
		this.pname = (StandardItemProperty) pname.clone();
		setTitle(NLS.bind("Enter value for the {0}", pname != null ? pname.getName() : ""));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		dialogArea = (Composite) super.createDialogArea(parent);
		dialogArea.setLayout(new GridLayout(1, false));

		useExpressionCheckbox = new Button(dialogArea, SWT.CHECK);
		useExpressionCheckbox.setText("Use Expression");
		useExpressionCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		propertyValue = new Text(dialogArea, SWT.BORDER);
		GridData gd_propertyValue = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd_propertyValue.widthHint = 400;
		propertyValue.setLayoutData(gd_propertyValue);

		propertyValueExpression = new WTextExpression(dialogArea, SWT.NONE);
		GridData gd_propertyValueExpression = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd_propertyValueExpression.heightHint = 50;
		gd_propertyValueExpression.widthHint = 400;
		propertyValueExpression.setLayoutData(gd_propertyValueExpression);
		propertyValueExpression.setExpressionContext(this.expContext);

		initWidgets();
		addListeners();

		return dialogArea;
	}

	private void initWidgets() {
		if (pname.getValueExpression() != null) {
			useExpressionCheckbox.setSelection(true);
			propertyValueExpression.setExpression((JRDesignExpression) pname.getValueExpression());
			propertyValue.setVisible(false);
			propertyValue.setEnabled(false);
			((GridData) propertyValue.getLayoutData()).exclude = true;
			propertyValueExpression.setFocus();
		} else {
			useExpressionCheckbox.setSelection(false);
			propertyValue.setText(Misc.nvl(pname.getValue()));
			propertyValue.setToolTipText(Misc.nvl(pname.getValue()));
			propertyValueExpression.setVisible(false);
			propertyValueExpression.setEnabled(false);
			propertyValueExpression.setExpression(null);
			((GridData) propertyValueExpression.getLayoutData()).exclude = true;
			propertyValue.setFocus();
		}
	}

	private void addListeners() {
		propertyValue.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				pname.setValue(propertyValue.getText());
				propertyValue.setToolTipText(propertyValue.getText());
			}
		});
		propertyValueExpression.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				pname.setValueExpression(event.modifiedExpression);
			}
		});
		useExpressionCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (useExpressionCheckbox.getSelection()) {
					// hide normal textbox
					propertyValue.setText(""); //$NON-NLS-1$
					propertyValue.setVisible(false);
					propertyValue.setEnabled(false);
					((GridData) propertyValue.getLayoutData()).exclude = true;
					// and show expression widget
					propertyValueExpression.setVisible(true);
					propertyValueExpression.setEnabled(true);
					((GridData) propertyValueExpression.getLayoutData()).exclude = false;
				} else {
					// hide the expression widget
					propertyValueExpression.setVisible(false);
					propertyValueExpression.setEnabled(false);
					propertyValueExpression.setExpression(null);
					((GridData) propertyValueExpression.getLayoutData()).exclude = true;
					// and show the normal textbox
					propertyValue.setText(""); //$NON-NLS-1$
					propertyValue.setVisible(true);
					propertyValue.setEnabled(true);
					((GridData) propertyValue.getLayoutData()).exclude = false;
				}
				UIUtils.relayoutDialog(getShell(), defwidth, defheight);
			}
		});
	}

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}

	public StandardItemProperty getElementName() {
		return this.pname;
	}

}
