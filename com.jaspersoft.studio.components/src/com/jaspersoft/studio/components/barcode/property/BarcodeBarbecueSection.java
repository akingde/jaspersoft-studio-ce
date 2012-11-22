/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class BarcodeBarbecueSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, "Barbecue",
				false, 2);

		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_BAR_WIDTH);

		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_BAR_HEIGTH);
		createWidget4Property(
				group,
				StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION);
		createWidget4Property(group, StandardBarbecueComponent.PROPERTY_TYPE);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_CHECKSUM_REQUIRED, false)
				.getControl().setLayoutData(gd);

		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_DRAW_TEXT, false)
				.getControl().setLayoutData(gd);

		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_ROTATION);

	}
}
