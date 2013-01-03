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
package com.jaspersoft.studio.components.chart.property.section.plot;

import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class Pie3dPlot extends APlot {

	@Override
	public void createControls(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		section.createWidget4Property(parent,
				JRDesignPie3DPlot.PROPERTY_SHOW_LABELS);

		section.createWidget4Property(parent,
				JRDesignPie3DPlot.PROPERTY_CIRCULAR);

		section.createWidget4Property(parent,
				JRDesignPie3DPlot.PROPERTY_LABEL_FORMAT);

		section.createWidget4Property(parent,
				JRDesignPie3DPlot.PROPERTY_LEGEND_LABEL_FORMAT);

		section.createWidget4Property(parent,
				JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR);

		parent = section.getWidgetFactory().createSection(parent, "Item Label",
				true, 4, 2);

		section.createWidget4Property(parent,
				JRDesignPie3DPlot.PROPERTY_ITEM_LABEL, false);
	}

}
