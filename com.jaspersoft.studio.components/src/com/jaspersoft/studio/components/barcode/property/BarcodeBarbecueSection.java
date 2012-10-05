/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
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