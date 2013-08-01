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
package com.jaspersoft.studio.components.chart.property.section;

import net.sf.jasperreports.components.spiderchart.StandardChartSettings;
import net.sf.jasperreports.engine.design.JRDesignChart;

import com.jaspersoft.studio.components.chartspider.model.MSpiderChart;
import com.jaspersoft.studio.property.section.graphic.BookmarkSection;

/**
 * 
 * This class extends the bookmark section to provide the correct property ids 
 * for the chart elements
 * 
 * @author Orlandin Marco
 *
 */
public class ChartBookmarkSection extends BookmarkSection {
	
	@Override
	public String getAnchorNameProperty(){
		if (getElement() instanceof MSpiderChart) return StandardChartSettings.PROPERTY_ANCHOR_NAME_EXPRESSION;
		else return JRDesignChart.PROPERTY_ANCHOR_NAME_EXPRESSION;
	}
	
	@Override
	public String getBookmarkLevelProperty(){
		if (getElement() instanceof MSpiderChart) return StandardChartSettings.PROPERTY_BOOKMARK_LEVEL;
		return JRDesignChart.PROPERTY_BOOKMARK_LEVEL;
	}
	
}
