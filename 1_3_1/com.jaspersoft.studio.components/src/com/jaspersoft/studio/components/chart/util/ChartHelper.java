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
package com.jaspersoft.studio.components.chart.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.charts.ChartThemeBundle;
import net.sf.jasperreports.extensions.ExtensionsEnvironment;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.utils.SelectionHelper;

public class ChartHelper {
	public static String[] getChartThemesNull() {
		String[] ct = getChartThemes();
		String[] ctn = new String[ct.length + 1];
		ctn[0] = "";
		System.arraycopy(ct, 0, ctn, 1, ct.length);
		return ctn;
	}

	public static String[] getChartThemes() {
		ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
		try {
			IEditorPart ep = SelectionHelper.getActiveJRXMLEditor();
			IFile file = ((IFileEditorInput) ep.getEditorInput()).getFile();

			SelectionHelper.setClassLoader(file, null);

			List<ChartThemeBundle> tbundles = ExtensionsEnvironment
					.getExtensionsRegistry().getExtensions(
							ChartThemeBundle.class);
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
		} finally {
			Thread.currentThread().setContextClassLoader(oldCL);
		}
	}
}
