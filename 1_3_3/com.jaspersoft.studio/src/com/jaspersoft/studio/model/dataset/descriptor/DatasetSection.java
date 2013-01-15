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
package com.jaspersoft.studio.model.dataset.descriptor;

import net.sf.jasperreports.engine.design.JRDesignElementDataset;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPIncrementType;
import com.jaspersoft.studio.property.section.widgets.SPResetType;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		IPropertyDescriptor pd = getPropertyDesriptor(JRDesignElementDataset.PROPERTY_INCREMENT_TYPE);
		IPropertyDescriptor gpd = getPropertyDesriptor(JRDesignElementDataset.PROPERTY_INCREMENT_GROUP);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		widgets.put(pd.getId(), new SPIncrementType(parent, this, pd, gpd));

		createWidget4Property(parent, JRDesignElementDataset.PROPERTY_INCREMENT_WHEN_EXPRESSION);

		pd = getPropertyDesriptor(JRDesignElementDataset.PROPERTY_RESET_TYPE);
		gpd = getPropertyDesriptor(JRDesignElementDataset.PROPERTY_RESET_GROUP);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		widgets.put(pd.getId(), new SPResetType(parent, this, pd, gpd));

		Composite group = getWidgetFactory().createSection(parent, "Dataset Run", true, 2, 2);

		createWidget4Property(group, JRDesignElementDataset.PROPERTY_DATASET_RUN);
	}
}
