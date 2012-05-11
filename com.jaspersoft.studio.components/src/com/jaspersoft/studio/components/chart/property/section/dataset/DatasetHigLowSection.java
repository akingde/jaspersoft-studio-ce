/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chart.property.section.dataset;

import net.sf.jasperreports.charts.design.JRDesignHighLowDataset;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.dataset.descriptor.DatasetSection;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetHigLowSection extends DatasetSection {

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION);
		createWidget4Property(parent,
				JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION);
	}

}
