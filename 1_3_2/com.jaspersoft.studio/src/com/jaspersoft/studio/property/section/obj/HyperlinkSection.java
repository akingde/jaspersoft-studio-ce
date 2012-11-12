/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section.obj;

import net.sf.jasperreports.engine.design.JRDesignHyperlink;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class HyperlinkSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(3, false));

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_LINK_TARGET).getControl().setLayoutData(gd);
		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_LINK_TYPE).getControl().setLayoutData(gd);

		getWidgetFactory().createCLabel(parent, "Anchor");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION, false).getControl()
				.setLayoutData(gd);

		getWidgetFactory().createCLabel(parent, "Page");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION, false).getControl()
				.setLayoutData(gd);

		getWidgetFactory().createCLabel(parent, "Reference");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION, false).getControl()
				.setLayoutData(gd);

		getWidgetFactory().createCLabel(parent, "Tooltip");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION, false).getControl()
				.setLayoutData(gd);

		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS);
	}

}
