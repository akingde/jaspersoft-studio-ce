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
package com.jaspersoft.studio.components.chartspider.property.section;

import net.sf.jasperreports.components.spiderchart.StandardSpiderPlot;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ChartPlotSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_BACKCOLOR);
		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_BACKGROUND_ALPHA);
		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_FOREGROUND_ALPHA);
		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_AXIS_LINE_COLOR);
		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_AXIS_LINE_WIDTH);

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_LABEL_COLOR);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_LABEL_FONT);

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_LABEL_GAP);

		createWidget4Property(parent,
				StandardSpiderPlot.PROPERTY_MAX_VALUE_EXPRESSION);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_WEB_FILLED);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_TABLE_ORDER);

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_START_ANGLE);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_HEAD_PERCENT);
		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_INTERIOR_GAP);

		createWidget4Property(parent, StandardSpiderPlot.PROPERTY_ROTATION);
	}
	
	/*protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(StandardSpiderPlot.PROPERTY_BACKCOLOR, Messages.common_key);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_BACKGROUND_ALPHA, Messages.common_backcolor);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_FOREGROUND_ALPHA, Messages.common_parent_style);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_AXIS_LINE_COLOR, Messages.MGraphicElement_remove_line_when_blank);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_AXIS_LINE_WIDTH, Messages.MGraphicElement_print_in_first_whole_band);
		
		addProvidedProperties(StandardSpiderPlot.PROPERTY_LABEL_COLOR, Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_LABEL_FONT, Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_LABEL_GAP, Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_MAX_VALUE_EXPRESSION, Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_WEB_FILLED, Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_TABLE_ORDER, Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_START_ANGLE, Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_HEAD_PERCENT, Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_INTERIOR_GAP, Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(StandardSpiderPlot.PROPERTY_ROTATION, Messages.MGraphicElement_print_in_first_whole_band);
	}*/

}
