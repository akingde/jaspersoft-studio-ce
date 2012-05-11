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

import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

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

		Section sectioncmp = section.getWidgetFactory().createSection(
				parent,
				ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
						| ExpandableComposite.EXPANDED);
		sectioncmp.setText("Item Label");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		sectioncmp.setLayoutData(gd);

		parent = section.getWidgetFactory().createComposite(sectioncmp);
		parent.setLayout(new GridLayout(4, false));

		sectioncmp.setClient(parent);

		section.createWidget4Property(parent,
				JRDesignPie3DPlot.PROPERTY_ITEM_LABEL, false);
	}

}
