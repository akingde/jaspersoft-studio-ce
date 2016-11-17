/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section.plot;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;

public class Pie3dPlot extends AbstractRealValueSection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		createWidget4Property(parent, JRDesignPie3DPlot.PROPERTY_SHOW_LABELS);
		createWidget4Property(parent, JRDesignPie3DPlot.PROPERTY_CIRCULAR);
 
		createWidget4Property(parent, JRDesignPie3DPlot.PROPERTY_LABEL_FORMAT);
		createWidget4Property(parent, JRDesignPie3DPlot.PROPERTY_LEGEND_LABEL_FORMAT);
		createWidget4Property(parent, JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR);

		parent = getWidgetFactory().createSectionTitle(parent, Messages.common_item_label,true, 4, 2);
		createWidget4Property(parent,JRDesignPie3DPlot.PROPERTY_ITEM_LABEL, false);
	}

}
