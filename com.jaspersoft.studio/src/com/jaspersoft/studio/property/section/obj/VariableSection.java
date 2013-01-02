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
package com.jaspersoft.studio.property.section.obj;

import net.sf.jasperreports.engine.design.JRDesignVariable;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPIncrementType;
import com.jaspersoft.studio.property.section.widgets.SPResetType;

public class VariableSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(3, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignVariable.PROPERTY_CALCULATION).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignVariable.PROPERTY_EXPRESSION).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignVariable.PROPERTY_INITIAL_VALUE_EXPRESSION).getControl().setLayoutData(gd);

		IPropertyDescriptor pd = getPropertyDesriptor(JRDesignVariable.PROPERTY_INCREMENT_TYPE);
		IPropertyDescriptor gpd = getPropertyDesriptor(JRDesignVariable.PROPERTY_INCREMENT_GROUP);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		SPIncrementType winctype = new SPIncrementType(parent, this, pd, gpd);
		gd = new GridData();
		gd.horizontalSpan = 2;
		winctype.getControl().setLayoutData(gd);
		widgets.put(pd.getId(), winctype);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(parent, JRDesignVariable.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME).getControl().setLayoutData(
				gd);

		pd = getPropertyDesriptor(JRDesignVariable.PROPERTY_RESET_TYPE);
		gpd = getPropertyDesriptor(JRDesignVariable.PROPERTY_RESET_GROUP);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		SPResetType wrestype = new SPResetType(parent, this, pd, gpd);
		gd = new GridData();
		gd.horizontalSpan = 2;
		wrestype.getControl().setLayoutData(gd);
		widgets.put(pd.getId(), wrestype);
	}
}
