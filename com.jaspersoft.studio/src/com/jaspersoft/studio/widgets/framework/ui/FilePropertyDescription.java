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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jface.dialogs.FileSelectionDialog;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class FilePropertyDescription extends TextPropertyDescription<String> {

	public FilePropertyDescription() {
		super();
	}
	
	public FilePropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		cmp.getFirstContainer().setLayout(WidgetFactory.getNoPadLayout(2));
		Control expressionControl = super.createControl(wiProp, cmp.getFirstContainer());
		cmp.getFirstContainer().setData(expressionControl);
		createToolbarButton(cmp.getFirstContainer(), wiProp);
		
		cmp.getSecondContainer().setLayout(WidgetFactory.getNoPadLayout(2));
		final Text simpleControl =  new Text(cmp.getSecondContainer(), SWT.BORDER);
		cmp.getSecondContainer().setData(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);
		simpleControl.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(simpleControl, wiProp);
			}
		});
		createToolbarButton(cmp.getSecondContainer(), wiProp);

		if (isReadOnly()){
			simpleControl.setEnabled(false);
		}
		
		setupContextMenu(simpleControl, wiProp);
		setupContextMenu(textExpression, wiProp);
		cmp.switchToFirstContainer();
		return cmp;
	}
	
	/**
	 * Create the toolbutton where to open the file selection dialog
	 * 
	 * @param parent the parent of the button
	 * @param wiProp the {@link IWItemProperty} to handle the setValue operation if the dialog is closed 
	 * correctly
	 */
	protected void createToolbarButton(Composite parent, final IWItemProperty wiProp){
		ToolBar toolBar = new ToolBar(parent, SWT.NONE);
		ToolItem b = new ToolItem(toolBar, SWT.FLAT);
		b.setImage(getButtonImage());
		b.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				wiProp.setRefresh(true);
				JRDesignExpression exp = null;
				try{
					exp = openSelectionDialog();
				} finally {
					if (exp != null) {
						wiProp.setValue(null, exp);
					}
					wiProp.setRefresh(false);
				}
			}

		});
		GridData data = new GridData();
		data.verticalAlignment = SWT.TOP;
		toolBar.setLayoutData(data);
		if (isReadOnly()){
			toolBar.setEnabled(false);	
		}
	}

	/**
	 * Return the image that will be shown on the button
	 * 
	 * @return an {@link Image}, should be not null
	 */
	protected Image getButtonImage(){
		return JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/obj16/fldr_obj.gif");
	}
	
	/**
	 * Open the file selection dialog
	 * 
	 * @return the expression of the selected file if the dialog was closed with ok, null otherwise
	 */
	protected JRDesignExpression openSelectionDialog(){
		FileSelectionDialog fsd = new FileSelectionDialog(UIUtils.getShell());
		fsd.configureDialog(jConfig);
		if (fsd.open() == Dialog.OK) {
			JRDesignExpression exp = fsd.getFileExpression();
			return exp;
		}
		return null;
	}
	
	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			Text expressionControl = (Text) cmp.getFirstContainer().getData();
			super.update(expressionControl, wip);
			cmp.switchToFirstContainer();
		} else {
			Text simpleControl = (Text)cmp.getSecondContainer().getData();
			super.update(simpleControl, wip);
			cmp.switchToSecondContainer();
		}
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
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		FilePropertyDescription fileDesc = new FilePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		fileDesc.setjConfig(jConfig);
		fileDesc.setReadOnly(cpd.isReadOnly());
		fileDesc.setFallbackValue(cpd.getFallbackValue());
		return fileDesc;
	}

}
