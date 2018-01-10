/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme;

import net.sf.jasperreports.chartthemes.simple.ChartThemeSettings;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ChartSettingsFactory {
	public static MRoot createModel(ChartThemeSettings cts, JasperReportsConfiguration jrContext) {
		JasperDesign jd = new JasperDesign();
		jd.setJasperReportsContext(jrContext);
		MRoot root = new MRoot(null, jd);
		MChartThemeSettings n = new MChartThemeSettings(root, cts);
		new MChartSettings(n, cts.getChartSettings());
		new MTitleSettings(n, cts.getTitleSettings(), "Title");
		new MTitleSettings(n, cts.getSubtitleSettings(), "Subtitle");
		new MLegendSettings(n, cts.getLegendSettings());
		new MPlotSettings(n, cts.getPlotSettings());
		new MAxisSettings(n, cts.getDomainAxisSettings(), "Domain Axis");
		new MAxisSettings(n, cts.getRangeAxisSettings(), "Range Axis");
		return root;
	}
}
