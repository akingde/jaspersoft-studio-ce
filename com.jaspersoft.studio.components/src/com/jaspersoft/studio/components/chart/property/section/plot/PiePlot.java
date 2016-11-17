/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.plot;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

import net.sf.jasperreports.charts.design.JRDesignPiePlot;

public class PiePlot extends AbstractRealValueSection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent = getWidgetFactory().createSection(parent, "Labels",true, 2, 3);
		
		createWidget4Property(parent,JRDesignPiePlot.PROPERTY_SHOW_LABELS);
		createWidget4Property(parent, JRDesignPiePlot.PROPERTY_CIRCULAR);
		createWidget4Property(parent,JRDesignPiePlot.PROPERTY_LABEL_FORMAT);
		createWidget4Property(parent, JRDesignPiePlot.PROPERTY_LEGEND_LABEL_FORMAT);

		parent = getWidgetFactory().createSectionTitle(parent, Messages.common_item_label,true, 4, 2);
		createWidget4Property(parent, JRDesignPiePlot.PROPERTY_ITEM_LABEL, false);
	}

}
