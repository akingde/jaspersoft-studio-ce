/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.charts.ChartThemeBundle;

/**
 * Utility class used to return the list of chart them available on the current {@link JasperReportsConfiguration}
 */
public class ChartHelper {
	public static String[] getChartThemesNull(JasperReportsConfiguration jConfig) {
		String[] ct = getChartThemes(jConfig);
		String[] ctn = new String[ct.length + 1];
		ctn[0] = "";
		System.arraycopy(ct, 0, ctn, 1, ct.length);
		return ctn;
	}

	public static String[] getChartThemes(JasperReportsConfiguration jConfig) {
		try {
			List<ChartThemeBundle> tbundles = jConfig.getExtensions(ChartThemeBundle.class);
			Set<String> tset = new HashSet<String>();
			for (ChartThemeBundle ctb : tbundles) {
				String[] themeNames = ctb.getChartThemeNames();
				for (String theme : themeNames)
					tset.add(theme);
			}
			String[] themes = tset.toArray(new String[tset.size()]);
			Arrays.sort(themes);
			return themes;
		} catch (Exception e) {
			e.printStackTrace();
			return new String[0];
		} 
	}
}
