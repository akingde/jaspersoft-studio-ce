/*
 * iReport - Visual Designer for JasperReports. Copyright (C) 2002 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.compatibility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.xml.JRXmlBaseWriter;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.compatibility.dialog.VersionDialog;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * 
 * @author gtoffoli
 */
public class JRXmlWriterHelper {

	private static final Map<String, Class<? extends JRXmlWriter>> writers = new HashMap<String, Class<? extends JRXmlWriter>>();

	static {
		for (Field f : JRConstants.class.getFields()) {
			if (f.getName().startsWith("VERSION_"))
				try {
					writers.put((String) f.get(null), JRXmlWriter.class);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
		}
	}

	public static String[][] getVersions() {
		List<String> sl = new ArrayList<String>(writers.keySet());
		Collections.sort(sl);
		Collections.reverse(sl);
		String[][] r = new String[sl.size() + 1][2];
		r[0] = new String[] { "Last Version", "last" };
		int i = 1;
		for (String key : sl) {
			r[i][0] = "JasperReports " + key.replace('_', '.');
			r[i][1] = key;
			i++;
		}
		return r;
	}

	public static String writeReport(JasperReportsConfiguration jrContext, JRReport report, IFile file, boolean showDialog)
			throws Exception {

		return writeReport(jrContext, report, file.getCharset(true), getVersion(file, jrContext, showDialog));
	}

	public static String writeReport(JasperReportsContext jrContext, JRReport report, String version) throws Exception {
		return writeReport(jrContext, report, fixencoding("UTF-8"), version);
	}

	public static String writeReport(JasperReportsContext jrContext, JRReport report, String encoding, String version)
			throws Exception {
		encoding = fixencoding(encoding);
		if (!writers.containsKey(version))
			version = LAST_VERSION;
		if (jrContext == null)
			jrContext = DefaultJasperReportsContext.getInstance();
		jrContext.removeProperty(JRXmlBaseWriter.PROPERTY_REPORT_VERSION);
		if (writers.containsKey(version))
			jrContext.setProperty(JRXmlBaseWriter.PROPERTY_REPORT_VERSION, version);

		// jrContext.setProperty("net.sf.jasperreports.components.table.version", version);
		String xml = new JRXmlWriter(jrContext).write(report, encoding);
		xml = xml.replaceFirst(
				"<jasperReport ", "<!-- Created with Jaspersoft Studio version " + version + "-->\n<jasperReport "); //$NON-NLS-1$ //$NON-NLS-2$
		return xml;
	}

	public static String fixencoding(String encoding) {
		return "UTF-8";
		// String tmp = EncodingMap.getJava2IANAMapping(encoding);
		// if (tmp != null)
		// return tmp;
		// tmp = EncodingMap.getJava2IANAMapping(encoding.toUpperCase());
		// if (tmp != null)
		// return tmp;
		// tmp = EncodingMap.getJava2IANAMapping(encoding.toLowerCase());
		// if (tmp != null)
		// return tmp;
		// return encoding;
	}

	public static String getVersion(IResource resource, JasperReportsConfiguration jContext, boolean showDialog) {
		String version = jContext.getProperty(StudioPreferencePage.JSS_COMPATIBILITY_VERSION, LAST_VERSION);
		if (showDialog && jContext.getPropertyBoolean(StudioPreferencePage.JSS_COMPATIBILITY_SHOW_DIALOG, false)) {
			VersionDialog dialog = new VersionDialog(Display.getDefault().getActiveShell(), version, resource.getProject());
			if (dialog.open() == Dialog.OK) {
				version = dialog.getVersion();
			}
		}
		return version;
	}

	public static final String LAST_VERSION = "last";
}
