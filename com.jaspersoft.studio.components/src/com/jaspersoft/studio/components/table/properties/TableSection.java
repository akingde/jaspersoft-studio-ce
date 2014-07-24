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
package com.jaspersoft.studio.components.table.properties;

import net.sf.jasperreports.components.headertoolbar.HeaderToolbarElement;
import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPText;

public class TableSection extends AbstractSection {
	private JSSTextPropertyDescriptor pd;

	public TableSection() {
		super();
		pd = new JSSTextPropertyDescriptor(MGraphicElement.PROPERTY_MAP, "Name");
		pd.setDescription("Table Name");
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(MGraphicElement.PROPERTY_MAP, "Name");
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		if (getElement().getValue() == null)
			return;

		parent = getWidgetFactory().createSection(parent, "Table", false, 2);

		getWidgetFactory().createCLabel(parent, "Name", SWT.RIGHT);

		widgets.put(pd.getId(), new SPText(parent, this, pd) {
			@Override
			protected String getCurrentValue() {
				JRPropertiesMap pmap = (JRPropertiesMap) section.getElement().getPropertyValue(pDescriptor.getId());
				return (String) pmap.getProperty(HeaderToolbarElement.PROPERTY_TABLE_NAME);
			}

			protected void handleTextChanged(final AbstractSection section, final Object property, String text) {
				JRPropertiesMap pmap = (JRPropertiesMap) pnode.getPropertyValue(MGraphicElement.PROPERTY_MAP);
				pmap = (JRPropertiesMap) pmap.clone();
				pmap.setProperty(HeaderToolbarElement.PROPERTY_TABLE_NAME, text);
				section.changeProperty(MGraphicElement.PROPERTY_MAP, pmap);
			}

			@Override
			public void setData(APropertyNode pnode, Object b) {
				if (b instanceof JRPropertiesMap) {
					JRPropertiesMap map = (JRPropertiesMap) b;
					String name = map.getProperty(HeaderToolbarElement.PROPERTY_TABLE_NAME);
					super.setData(pnode, name);
				}
			}
		});
	}
}
