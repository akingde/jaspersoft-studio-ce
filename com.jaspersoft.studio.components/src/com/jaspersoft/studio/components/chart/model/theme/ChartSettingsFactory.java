package com.jaspersoft.studio.components.chart.model.theme;

import net.sf.jasperreports.chartthemes.simple.ChartThemeSettings;

import com.jaspersoft.studio.model.MRoot;

public class ChartSettingsFactory {
	public static MRoot createModel(ChartThemeSettings cts) {
		MRoot root = new MRoot(null, null);
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
