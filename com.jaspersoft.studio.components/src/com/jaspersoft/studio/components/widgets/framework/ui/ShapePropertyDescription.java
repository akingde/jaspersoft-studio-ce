/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets.framework.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.widgets.framework.ui.dialogs.ShapeDefinitionWizard;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;

import net.sf.jasperreports.chartcustomizers.utils.ShapeDefinition;

/**
 * Property description to define a shape for a chart. The shape can be configured from
 * a sperated dialog opened trough a button
 * 
 * @author Orlandin Marco
 *
 */
public class ShapePropertyDescription extends TextPropertyDescription<String> {

	public ShapePropertyDescription() {
	}
	
	public ShapePropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control expressionControl = super.createControl(wiProp, cmp.getFirstContainer());
		cmp.getFirstContainer().setData(expressionControl);

		cmp.getSecondContainer().setLayout(WidgetFactory.getNoPadLayout(2));
		final Text simpleControl = new Text(cmp.getSecondContainer(), SWT.BORDER);
		cmp.getSecondContainer().setData(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);
		simpleControl.setEnabled(false);
		
		simpleControl.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (UIUtil.isMacAndEclipse4()) {
					if (((Text) e.getSource()).isDisposed())
						return;
					wiProp.updateWidget();
				}
			}

		});
		
		//Create the button to open the wizard to define the shape information
		Button openWizardButton = new Button(cmp.getSecondContainer(), SWT.PUSH);
		openWizardButton.setText("...");
		openWizardButton.setEnabled(!isReadOnly());
		GridData buttonData = new GridData();
		buttonData.verticalAlignment = SWT.CENTER;
		buttonData.grabExcessVerticalSpace = true;
		openWizardButton.setLayoutData(buttonData);
		openWizardButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String currentValue = wiProp.getStaticValue();
				ShapeDefinition currentDefinition = ShapeDefinition.decode(currentValue);
				ShapeDefinitionWizard wizard = new ShapeDefinitionWizard(currentDefinition);
				WizardDialog dialog = new WizardDialog(simpleControl.getShell(), wizard);
				if (dialog.open() == Dialog.OK){
					wiProp.setValue(wizard.getEncodedShapeDefinition(), null);
				}
			}
		});
		
		setupContextMenu(simpleControl, wiProp);
		setupContextMenu(textExpression, wiProp);
		cmp.switchToFirstContainer();
		return cmp;
	}
	
	/**
	 * Set the value inside the correct control, if the editor is in 
	 * expression mode or not
	 */
	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			Text expressionControl = (Text) cmp.getFirstContainer().getData();
			super.update(expressionControl, wip);
			cmp.switchToFirstContainer();
		} else {
			boolean isFallback = false;
			Text simpleControl = (Text)cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v == null && wip.getFallbackValue() != null){
				v = wip.getFallbackValue().toString();
				isFallback = true;
			}
			ShapeDefinition definition = ShapeDefinition.decode(v);
			if (definition != null){
				simpleControl.setText(Misc.nvl(definition.getShape().toString()));	
			} else {
				//if the shape can not be decoded show this message
				simpleControl.setText("No shape defined");
			}
			changeFallbackForeground(isFallback, simpleControl);
			cmp.switchToSecondContainer();
		}
	}

	@Override
	public ShapePropertyDescription clone(){
		ShapePropertyDescription result = new ShapePropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		ShapePropertyDescription result = new ShapePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(cpd.getFallbackValue());
		return result;
	}

}
