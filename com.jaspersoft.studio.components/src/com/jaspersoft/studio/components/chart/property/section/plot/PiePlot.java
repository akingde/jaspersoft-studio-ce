/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chart.property.section.plot;

import net.sf.jasperreports.charts.design.JRDesignPiePlot;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class PiePlot extends APlot {

	@Override
	public void createControls(AbstractSection section, Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		section.createWidget4Property(parent,
				JRDesignPiePlot.PROPERTY_SHOW_LABELS);

		section.createWidget4Property(parent, JRDesignPiePlot.PROPERTY_CIRCULAR);

		section.createWidget4Property(parent,
				JRDesignPiePlot.PROPERTY_LABEL_FORMAT);

		section.createWidget4Property(parent,
				JRDesignPiePlot.PROPERTY_LEGEND_LABEL_FORMAT);

		parent = section.getWidgetFactory().createSection(parent, "Item Label",
				true, 4, 2);

		section.createWidget4Property(parent,
				JRDesignPiePlot.PROPERTY_ITEM_LABEL, false);
	}

}
