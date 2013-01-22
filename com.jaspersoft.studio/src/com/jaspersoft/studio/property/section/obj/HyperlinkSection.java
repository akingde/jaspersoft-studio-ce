/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
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
		createWidget4Property(parent, JRDesignHyperlink.PROPERTY_HYPERLINK_TARGET).getControl().setLayoutData(gd);
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
