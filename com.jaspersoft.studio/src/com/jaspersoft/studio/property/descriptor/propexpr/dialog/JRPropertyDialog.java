/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr.dialog;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.descriptor.properties.dialog.PropertiesList;
import com.jaspersoft.studio.property.descriptor.properties.dialog.PropertyDTO;
import com.jaspersoft.studio.property.infoList.SelectableComposite;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;

public class JRPropertyDialog extends Dialog {
	private PropertyDTO value;
	private Composite vexp;
	private Composite vcmp;
	private StackLayout stackLayout;
	private Text tvalue;
	private Button buseexpr;
	private WTextExpression evalue;
	private Combo cprop;

	protected JRPropertyDialog(Shell parentShell) {
		super(parentShell);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Properties Dialog");
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout(2, false));
		Label label = new Label(composite, SWT.NONE);
		label.setText("Property Name");

		cprop = new Combo(composite, SWT.BORDER);
		cprop.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		cprop.setItems(PropertiesList.getSortedProperitesNames());
		cprop.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String newtext = cprop.getText();
				value.setProperty(newtext);
				PropertyDTO dto = PropertiesList.getDTO(value.getProperty());
				if (dto != null) {
					value.setValue(dto.getDefValue());
					tvalue.setText((String) dto.getDefValue());
					buseexpr.setSelection(false);
				}
			}
		});

		buseexpr = new Button(composite, SWT.CHECK);
		buseexpr.setText("Use An Expression");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		buseexpr.setLayoutData(gd);

		final Composite cmp = new Composite(composite, SWT.NONE);
		stackLayout = new StackLayout();
		cmp.setLayout(stackLayout);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		vcmp = createValueControl(cmp);

		vexp = createValueExpressionControl(cmp);

		stackLayout.topControl = vcmp;
		buseexpr.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				stackLayout.topControl = buseexpr.getSelection() ? vexp : vcmp;
				cmp.layout();
				if (buseexpr.getSelection())
					value.setValue(evalue.getExpression());
				else
					value.setValue(tvalue.getText());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}
		});
		fillValue(value);
		createSpecialProperties(composite);
		return composite;
	}
	
	private SelectableComposite createSpecialProperties(Composite cmp){
		/*
		 * FIXME add the rapid selection component for properties
		List<ElementDescription> items = new ArrayList<ElementDescription>();
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb \n ccccc \n ddddddd"));
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		for(int i=0;i<200;i++)
			items.add(new ElementDescription("aaaaa", "bbbbbbbbb"));
		
		Label titleLabel = new Label(cmp, SWT.NONE);
		titleLabel.setText("Special Properties:");
		titleLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));
		SelectableComposite comp = new SelectableComposite(cmp, items);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		data.heightHint = 200;
		comp.setLayoutData(data);
		comp.SetDoubleClickListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedProp = ((ElementDescription)e.data).getName();
				cprop.setText(selectedProp);
			}
		});
		return comp;*/
		return null;
	}

	private Composite createValueExpressionControl(Composite cmp) {
		Composite composite = new Composite(cmp, SWT.NONE);
		composite.setLayout(new GridLayout());

		Label label = new Label(composite, SWT.NONE);
		label.setText("Value Expression");

		evalue = new WTextExpression(composite, SWT.NONE, 1);
		evalue.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		evalue.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				value.setValue(evalue.getExpression());
			}
		});

		return composite;
	}

	private Composite createValueControl(Composite cmp) {
		Composite composite = new Composite(cmp, SWT.NONE);
		composite.setLayout(new GridLayout());

		Label label = new Label(composite, SWT.NONE);
		label.setText("Value");

		tvalue = new Text(composite, SWT.BORDER);
		tvalue.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		tvalue.setText("< type value here >");
		tvalue.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				value.setValue(tvalue.getText());
			}
		});
		return composite;
	}

	public void setValue(PropertyDTO value) {
		this.value = value;
	}

	private void fillValue(PropertyDTO value) {
		evalue.setExpressionContext(ModelUtils.getElementExpressionContext(null, value.getPnode()));
		cprop.setText(Misc.nvl(value.getProperty()));
		if (value.getValue() != null) {
			if (value.getValue() instanceof JRExpression) {
				buseexpr.setSelection(true);
				evalue.setExpression((JRDesignExpression) value.getValue());
			} else {
				buseexpr.setSelection(false);
				tvalue.setText(Misc.nvl((String) value.getValue()));
			}
		}
	}

}
