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
package com.jaspersoft.studio.components.map.property;

import net.sf.jasperreports.components.map.StandardMapComponent;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPEvaluationTime;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class MapSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		IPropertyDescriptor pd = getPropertyDesriptor(StandardMapComponent.PROPERTY_EVALUATION_TIME);
		IPropertyDescriptor gpd = getPropertyDesriptor(StandardMapComponent.PROPERTY_EVALUATION_GROUP);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		widgets.put(pd.getId(), new SPEvaluationTime(parent, this, pd, gpd));

		createWidget4Property(parent,
				StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION);

		createWidget4Property(parent,
				StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION);

		createWidget4Property(parent,
				StandardMapComponent.PROPERTY_ZOOM_EXPRESSION);

		createWidget4Property(parent,
				StandardMapComponent.PROPERTY_LANGUAGE_EXPRESSION);

		createWidget4Property(parent, StandardMapComponent.PROPERTY_MAP_TYPE);

		createWidget4Property(parent, StandardMapComponent.PROPERTY_MAP_SCALE);

		createWidget4Property(parent, StandardMapComponent.PROPERTY_IMAGE_TYPE);

	}
	
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(StandardMapComponent.PROPERTY_EVALUATION_TIME, Messages.MMap_evaluation_time);
		addProvidedProperties(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION, Messages.MMap_latitude);
		addProvidedProperties(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION, Messages.MMap_longitude);
		addProvidedProperties(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION, Messages.MMap_zoom);
		addProvidedProperties(StandardMapComponent.PROPERTY_LANGUAGE_EXPRESSION, Messages.MMap_languageExpressionTitle);
		addProvidedProperties(StandardMapComponent.PROPERTY_MAP_TYPE, Messages.MMap_mapTypeTitle);
		addProvidedProperties(StandardMapComponent.PROPERTY_MAP_SCALE, Messages.MMap_mapScaleTitle);
		addProvidedProperties(StandardMapComponent.PROPERTY_IMAGE_TYPE, Messages.MMap_imageTypeTitle);
	}
	
}
