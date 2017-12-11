/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
 package com.jaspersoft.studio.book.sections;

import java.text.MessageFormat;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.book.model.MGroupReportPartContainer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

public class PartContainerSection extends AbstractSection {
	
	private ASPropertyWidget<?> nameWidget;
	
	private Composite container;
	
	private MGroupReportPartContainer groupContainer;
	
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		
		super.createControls(parent, tabbedPropertySheetPage);
		
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.heightHint = 100;
		data.minimumHeight = 100;
		container.setLayoutData(data);
		
		JSSTextPropertyDescriptor nameD = new JSSTextPropertyDescriptor(JRDesignGroup.PROPERTY_NAME, Messages.common_name);
		nameD.setDescription(Messages.MGroup_name_description);

		JRExpressionPropertyDescriptor expressionD = new JRExpressionPropertyDescriptor(JRDesignGroup.PROPERTY_EXPRESSION,Messages.common_expression);
		expressionD.setDescription(Messages.MGroup_expression_description);

		nameWidget = createWidget(container, JRDesignGroup.PROPERTY_NAME, true, nameD);
		nameWidget.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		ASPropertyWidget<?> expressionWidget = createWidget(container, JRDesignGroup.PROPERTY_EXPRESSION, true, expressionD);
		expressionWidget.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignGroup.PROPERTY_NAME, Messages.common_name);
		addProvidedProperties(JRDesignGroup.PROPERTY_EXPRESSION, Messages.common_expression);
	}

	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		groupContainer = (MGroupReportPartContainer) md;
		JRDesignGroup group = groupContainer.getJrgroup();
		MGroup groupModel = new MGroup();
		groupModel.setValue(group);
		groupModel.setJasperConfiguration(groupContainer.getJasperConfiguration());
		return groupModel;
	}
	
	/**
	 * Check if the property changed is the name and in this case check that the 
	 * new name is different from any existing group. If it is different the change
	 * is done, otherwise a warning message is shown and the original name is 
	 * restored
	 */
	@Override
	public boolean changeProperty(Object property, Object newValue) {
		if (JRDesignGroup.PROPERTY_NAME.equals(property)){
			JasperDesign jd = getElement().getJasperConfiguration().getJasperDesign();
			String oldName = getElement().getPropertyValue(JRDesignGroup.PROPERTY_NAME).toString();
			//If the new name is equals to the actual one the there is no need to change
			if (oldName.equals(newValue)) return true;
			if (jd != null && jd.getGroupsMap().get(newValue) != null) {
				nameWidget.setData(getElement(), oldName);
				String message = MessageFormat.format(Messages.GroupSection_SameNameErrorMsg, new Object[] { newValue });
				MessageDialog dialog = new MessageDialog(UIUtils.getShell(), Messages.GroupSection_SameNameErrorTitle, null,
						message, MessageDialog.WARNING, new String[] { "Ok"}, 0); //$NON-NLS-1$
				dialog.open();
				return false;
			}
		}
		super.changePropertyOn(property, newValue, groupContainer);
		return true;
	}
}
