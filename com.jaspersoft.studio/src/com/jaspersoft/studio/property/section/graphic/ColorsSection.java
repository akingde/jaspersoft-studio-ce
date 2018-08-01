/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.graphic;

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;

import java.beans.PropertyChangeEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ColorsSection extends AbstractRealValueSection {

	private ExpandableComposite section;
	
	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, Messages.ColorsSection_colorSectionTitle, true, 4);
		section = (ExpandableComposite)parent.getParent();
		//parent.setLayout(new GridLayout(4, false));
		
		createWidget4Property(parent, JRBaseStyle.PROPERTY_FORECOLOR);
		createWidget4Property(parent, JRBaseStyle.PROPERTY_BACKCOLOR);
		
		Composite transparencyComp = new Composite(parent, SWT.NONE);
		GridLayout transparencyLayout = new GridLayout(2,false);
		transparencyLayout.marginWidth = 0;
		transparencyComp.setLayout(transparencyLayout);
		GridData gd = new GridData();
		gd.horizontalSpan = 4;
		transparencyComp.setLayoutData(gd);
		createWidget4Property(transparencyComp, JRBaseStyle.PROPERTY_MODE,false);
	}
	
	@Override
	public void expandForProperty(Object propertyId) {
		if (section != null && !section.isExpanded()) section.setExpanded(true);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!isDisposed()) {
			String n = evt.getPropertyName();
			setRefreshing(true);
			APropertyNode element = getElement();
			if (element != null) {
				element.getPropertyDescriptors();
				for (Object key : widgets.keySet()) {
					if (n.equals(key) || n.equals(JRDesignElement.PROPERTY_PARENT_STYLE) || n.equals(JRDesignElement.PROPERTY_PARENT_STYLE_NAME_REFERENCE)){
						//Use actual and current value to check if a value is inherited or not
						Object currentValue = element.getPropertyActualValue(key);
						Object ownValue = element.getPropertyValue(key);
						widgets.get(key).setData(element, currentValue, ownValue);
					}
				}
			}
			setRefreshing(false);
		}
	}
	
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRBaseStyle.PROPERTY_FORECOLOR, Messages.common_forecolor);
		addProvidedProperties(JRBaseStyle.PROPERTY_BACKCOLOR, Messages.common_backcolor);
		addProvidedProperties(JRBaseStyle.PROPERTY_MODE, Messages.common_mode);
	}

}
