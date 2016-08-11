/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jface.dialogs.FileSelectionDialog;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class FilePropertyDescription extends TextPropertyDescription<String> {

	private boolean refreshing = false;

	public FilePropertyDescription() {
		super();
	}
	
	public FilePropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	@Override
	public void handleEdit(Control txt, IWItemProperty wProp) {
		if (refreshing)
			return;
		super.handleEdit(txt, wProp);
	}

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		cmp.setLayout(layout);
		GridData cmpData = new GridData(GridData.FILL_HORIZONTAL);
		cmp.setLayoutData(cmpData);

		super.createControl(wiProp, cmp);

		if (!isReadOnly()) {
			ToolBar toolBar = new ToolBar(cmp, SWT.NONE);
			ToolItem b = new ToolItem(toolBar, SWT.FLAT);
			b.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/obj16/fldr_obj.gif"));
			b.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					refreshing = true;
					wiProp.setRefresh(true);
					try {
						FileSelectionDialog fsd = new FileSelectionDialog(UIUtils.getShell());
						fsd.configureDialog(jConfig);
						if (fsd.open() == Dialog.OK) {
							JRDesignExpression exp = fsd.getFileExpression();
							textExpression.setText(exp.getText());

							wiProp.setValue(null, exp);
						}
					} finally {
						wiProp.setRefresh(false);
						refreshing = false;
					}
				}

			});
		}
		return cmp;
	}
	
	@Override
	public void update(Control c, IWItemProperty wip) {
		//Update the textual control, that is the first child of the returned composite
		super.update(((Composite)c).getChildren()[0], wip);
	}
	
	@Override
	public ItemPropertyDescription<String> clone(){
		FilePropertyDescription result = new FilePropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		FilePropertyDescription fileDesc = new FilePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		fileDesc.setjConfig(jConfig);
		fileDesc.setReadOnly(cpd.isReadOnly());
		return fileDesc;
	}

}
