/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Framework widget to acquire a class name. There is a combo with some suggestion and a button used to open a 
 * class selection dialog
 * 
 * @author Orlandin Marco
 *
 */
public class ClassItemPropertyDescription extends ComboItemPropertyDescription<String> {

	public ClassItemPropertyDescription() {
		super();
	}

	public ClassItemPropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue, String[] values) {
		super(name, label, description, mandatory, defaultValue, values);
	}

	public ClassItemPropertyDescription(String name, String label, String description, boolean mandatory, String[] values) {
		super(name, label, description, mandatory, values);
	}
	
	public void handleEdit(Control txt, IWItemProperty wProp) {
		if (txt instanceof Combo) {
			//set the value inside the combo
			wProp.setValue(((Combo)txt).getText(), null);
		} else {
			//it is an expression
			super.handleEdit(txt, wProp);
		}
	}
	
	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lazyCreateExpressionControl(wiProp, cmp);

		cmp.getSecondContainer().setLayout(WidgetFactory.getNoPadLayout(2));
		final Combo simpleControl = createComboControl(cmp.getSecondContainer());
		cmp.getSecondContainer().setData(simpleControl);
		cmp.setSimpleControlToHighlight(simpleControl);
		
		GridData comboData = new GridData(GridData.FILL_HORIZONTAL);
		comboData.verticalAlignment = SWT.CENTER;
		comboData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(comboData);
		
		simpleControl.setItems(convert2Value(keyValues));
		simpleControl.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				Point p = simpleControl.getSelection();
				handleEdit(simpleControl, wiProp);
				simpleControl.setSelection(p);
			}
		});
		
		final Button classDialogButton = new Button(cmp.getSecondContainer(), SWT.PUSH);
		classDialogButton.setText("...");
		classDialogButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String classname = ClassTypeCellEditor.getJavaClassDialog(classDialogButton.getShell(), null);
				if (classname != null){
					//this will trigger the modify listneer
					simpleControl.setText(classname);
				}
			}
		});
		classDialogButton.setLayoutData(new GridData(GridData.FILL_VERTICAL));		

		if (isReadOnly()){
			simpleControl.setEnabled(false);
			classDialogButton.setEnabled(false);
		} else {
			setupContextMenu(simpleControl, wiProp);
		}
		
		cmp.switchToSecondContainer();
		return cmp;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		if (cpd.getComboOptions() != null) {
			String[][] opts = cpd.getComboOptions();
			String classes[] = new String[opts.length];
			for (int i = 0; i < opts.length; i++) {
				classes[i] = opts[i][0];
			}
			ClassItemPropertyDescription result = new ClassItemPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), classes);
			result.setReadOnly(cpd.isReadOnly());
			result.setFallbackValue(cpd.getFallbackValue());
			return result;
		}
		return null;
	}
}
