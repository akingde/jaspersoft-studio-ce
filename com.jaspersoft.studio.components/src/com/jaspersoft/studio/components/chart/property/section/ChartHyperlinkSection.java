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
package com.jaspersoft.studio.components.chart.property.section;

import net.sf.jasperreports.charts.design.JRDesignCategorySeries;
import net.sf.jasperreports.charts.design.JRDesignGanttSeries;
import net.sf.jasperreports.charts.design.JRDesignHighLowDataset;
import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.charts.design.JRDesignPieSeries;
import net.sf.jasperreports.charts.design.JRDesignTimePeriodSeries;
import net.sf.jasperreports.charts.design.JRDesignTimeSeries;
import net.sf.jasperreports.charts.design.JRDesignXySeries;
import net.sf.jasperreports.charts.design.JRDesignXyzSeries;

import com.jaspersoft.studio.components.chart.model.dataset.MChartHighLowDataset;
import com.jaspersoft.studio.components.chart.model.dataset.MChartPieDataset;
import com.jaspersoft.studio.components.chart.model.series.category.MCategorySeries;
import com.jaspersoft.studio.components.chart.model.series.gantt.MGanttSeries;
import com.jaspersoft.studio.components.chart.model.series.pie.MPieSeries;
import com.jaspersoft.studio.components.chart.model.series.time.MTimeSeries;
import com.jaspersoft.studio.components.chart.model.series.timeperiod.MTimePeriodSeries;
import com.jaspersoft.studio.components.chart.model.series.xyseries.MXYSeries;
import com.jaspersoft.studio.components.chart.model.series.xyzseries.MXYZSeries;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.obj.HyperlinkSection;

public class ChartHyperlinkSection extends HyperlinkSection {
	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MChartHighLowDataset)
			return (APropertyNode) md
					.getPropertyValue(JRDesignHighLowDataset.PROPERTY_ITEM_HYPERLINK);
		if (md instanceof MChartPieDataset)
			return (APropertyNode) md
					.getPropertyValue(JRDesignPieDataset.PROPERTY_OTHER_SECTION_HYPERLINK);
		if (md instanceof MCategorySeries)
			return (APropertyNode) md
					.getPropertyValue(JRDesignCategorySeries.PROPERTY_ITEM_HYPERLINK);
		if (md instanceof MGanttSeries)
			return (APropertyNode) md
					.getPropertyValue(JRDesignGanttSeries.PROPERTY_ITEM_HYPERLINK);
		if (md instanceof MPieSeries)
			return (APropertyNode) md
					.getPropertyValue(JRDesignPieSeries.PROPERTY_SECTION_HYPERLINK);
		if (md instanceof MTimeSeries)
			return (APropertyNode) md
					.getPropertyValue(JRDesignTimeSeries.PROPERTY_ITEM_HYPERLINK);
		if (md instanceof MTimePeriodSeries)
			return (APropertyNode) md
					.getPropertyValue(JRDesignTimePeriodSeries.PROPERTY_ITEM_HYPERLINK);
		if (md instanceof MXYSeries)
			return (APropertyNode) md
					.getPropertyValue(JRDesignXySeries.PROPERTY_ITEM_HYPERLINK);
		if (md instanceof MXYZSeries)
			return (APropertyNode) md
					.getPropertyValue(JRDesignXyzSeries.PROPERTY_ITEM_HYPERLINK);
		return md;
	}
}
