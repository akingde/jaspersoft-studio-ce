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
package com.jaspersoft.studio.property.descriptor.propexpr.dialog;

import java.util.Collections;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.properties.dialog.PropertyDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Dialog that extend the dialog to define a property as key and value.
 * This extension allow to use an expression as value
 * 
 * @author Orlandin Marco
 *
 */
public class JRPropertyExpressionDialog extends JRPropertyDialog 
{
	/**
	 * Checkbutton to choose if to use a textual value or an expression
	 */
	protected Button buseexpr;
	
	/**
	 * Control where the expression can be placed
	 */
	protected WTextExpression evalue;
	
	/**
	 * Container of the expression control
	 */
	protected Composite vexp;
	
	/**
	 * Boolean guard to avoid recursive calls when the text of the value
	 * is modified from the modify listener itself
	 */
	private boolean updating = false;
	
	public JRPropertyExpressionDialog(Shell parentShell) {
		super(parentShell);
	}
	
	/**
	 * The hints are initialized using the type of the actual node
	 */
	@Override
	protected void initializeHints(){
		hints = HintsPropertiesList.getElementProperties(value.getPnode().getValue());
		Collections.sort(hints);
	}
	
	@Override
	protected ModifyListener getModifyListener(){
		return new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String newtext = cprop.getText();
				if (propertiesSuggestions != null) propertiesSuggestions.showOnlyElement(newtext);
				value.setName(newtext);
			}
		};
	}
	
	/**
	 * Create the checkbox
	 */
	protected void createAdditionalControls(Composite parent){
		buseexpr = new Button(parent, SWT.CHECK);
		buseexpr.setText("Use An Expression");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		buseexpr.setLayoutData(gd);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite)super.createDialogArea(parent);
		tvalue.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				synchText();
			}
		});
		vexp = createValueExpressionControl(stackComposite);
		buseexpr.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = buseexpr.getSelection() ? vexp : vcmp;
				stackComposite.layout();
				((PropertyExpressionDTO)value).setExpression(buseexpr.getSelection());
				if (buseexpr.getSelection())
					value.setValue(evalue.getExpression().getText());
				else
					value.setValue(tvalue.getText());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});
		fillValue(value);
		return composite;
	}
	
	/**
	 * Crate the control to insert an expression
	 * 
	 * @param cmp the parent where the control will be placed
	 * @return container of the control
	 */
	private Composite createValueExpressionControl(Composite cmp) {
		Composite composite = new Composite(cmp, SWT.NONE);
		composite.setLayout(new GridLayout());

		Label label = new Label(composite, SWT.NONE);
		label.setText("Value Expression");

		evalue = new WTextExpression(composite, SWT.NONE, 1);
		evalue.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				synchText();	
			}
		});
		evalue.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		evalue.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				value.setValue(evalue.getExpression().getText());
			}
		});

		return composite;
	}
	
	/**
	 * Set the current value insde the value controls
	 * 
	 * @param value value to set
	 */
	private void fillValue(PropertyDTO value) {
		ANode node =  value.getPnode();
		evalue.setExpressionContext(ModelUtils.getElementExpressionContext(null, node));		
		cprop.setText(Misc.nvl(value.getName()));
		buseexpr.setSelection(value.isExpression());
		String text = Misc.nvl((String) value.getValue());
		tvalue.setText(text);
		evalue.setExpression(new JRDesignExpression(text));
	}

	/**
	 * Method called when one of the value controls are modfied
	 * and it synch the value on the other control
	 */
	protected synchronized void synchText(){
		if (!updating){
			updating = true;
			if (buseexpr.getSelection()){
				tvalue.setText(evalue.getText());
			} else {
				evalue.setExpression(new JRDesignExpression(tvalue.getText()));
			}
			updating = false;
		}
	}
	
	/**
	 * Return a value as string, it can handle string
	 * and expressions. If the value can't be converted
	 * it return an empty string
	 */
	@Override
	protected String getValueText(Object value) {
		if(value instanceof String) {
			return (String) value;
		} else if (value instanceof JRExpression){
			return ((JRExpression)value).getText();
		}
		return "";
	}
}
