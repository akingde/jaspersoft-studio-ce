/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.dialog.ArrayDialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

/**
 * Property description for an array element type
 * 
 * @author Orlandin Marco
 */
public class ArrayPropertyDescription extends AbstractExpressionPropertyDescription<String> {

	/**
	 * The inner type of the array
	 */
	private ItemPropertyDescription<?> innerType;
	
	public ArrayPropertyDescription() {
		super();
	}
	
	public ArrayPropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	/**
	 * Create the control 
	 */
	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lazyCreateExpressionControl(wiProp, cmp);
		
		cmp.getSecondContainer().setLayout(WidgetFactory.getNoPadLayout(2));
		final Text simpleControl =  new Text(cmp.getSecondContainer(), SWT.BORDER);
		simpleControl.setEnabled(false);
		cmp.getSecondContainer().setData(simpleControl);
		cmp.setSimpleControlToHighlight(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);

		createToolbarButton(cmp.getSecondContainer(), wiProp);
		
		cmp.switchToFirstContainer();
		return cmp;
	}
	
	/**
	 * Create the toolbutton where to open the array dialog
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
				try{
					ArrayDialog dialog = new ArrayDialog(UIUtils.getShell(), wiProp.getStaticValue(), innerType);
					if (dialog.open() == IDialogConstants.OK_ID) {
						wiProp.setValue(dialog.getInnerValues(), null);
					}
				} finally {
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
	
	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			lazyCreateExpressionControl(wip, cmp);
			Text expressionControl = (Text) cmp.getFirstContainer().getData();
			super.update(expressionControl, wip);
			cmp.switchToFirstContainer();
		} else {
			Text txtValue = (Text)cmp.getSecondContainer().getData();
			String txt;
			if (wip.getStaticValue() != null){
				txt = wip.getStaticValue();
			} else if (wip.getFallbackValue() != null){
				txt = Misc.nvl(wip.getFallbackValue().toString());
			} else {
				txt = "";
			}
		
			ObjectMapper mapper = new ObjectMapper();
			txtValue.setText("Values: 0");
			if (txt != null && !txt.isEmpty()) {
				try {
					String[] stringValues = mapper.readValue(txt, String[].class);
					txtValue.setText("Values: " + stringValues.length);
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
			
			txtValue.setToolTipText(getToolTip());
			cmp.switchToSecondContainer();
		}
	}
	
	@Override
	public ItemPropertyDescription<String> clone(){
		ArrayPropertyDescription result = new ArrayPropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.fallbackValue = fallbackValue;
		result.innerType = innerType.clone();
		return result;
	}
	
	public void setInnterType(ItemPropertyDescription<?> innerType) {
		this.innerType = innerType;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		ArrayPropertyDescription fileDesc = new ArrayPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		fileDesc.setjConfig(jConfig);
		fileDesc.setReadOnly(cpd.isReadOnly());
		fileDesc.setFallbackValue(cpd.getFallbackValue());
		return fileDesc;
	}

}
