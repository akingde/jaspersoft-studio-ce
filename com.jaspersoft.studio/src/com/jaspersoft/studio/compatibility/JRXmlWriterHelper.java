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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.compatibility.dialog.VersionDialog;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

/*
 * 
 * @author gtoffoli
 */
public class JRXmlWriterHelper {

	private static final Map<String, Class<? extends JRXmlWriter>> writers = new HashMap<String, Class<? extends JRXmlWriter>>();

	static {
		writers.put("4_0_1", JRXmlWriter_4_0_1.class);
		writers.put("3_7_4", JRXmlWriter_3_7_4.class);
		writers.put("3_7_3", JRXmlWriter_3_7_3.class);
		// writers.put("3_7_1", JRXmlWriter_3_7_1.class);
		// writers.put("3_6_2", JRXmlWriter_3_6_2.class);
		// writers.put("3_6_1", JRXmlWriter_3_6_1.class);
		// writers.put("3_6_0", JRXmlWriter_3_6_0.class);
		// writers.put("3_5_2", JRXmlWriter_3_5_2.class);
		// writers.put("3_5_1", JRXmlWriter_3_5_1.class);
		// writers.put("3_5_0", JRXmlWriter_3_5_0.class);
		// writers.put("3_1_4", JRXmlWriter_3_1_4.class);
		// writers.put("3_1_3", JRXmlWriter_3_1_3.class);
		// writers.put("3_1_2", JRXmlWriter_3_1_2.class);
		// writers.put("3_1_0", JRXmlWriter_3_1_0.class);
		// writers.put("3_0_1", JRXmlWriter_3_0_1.class);
		// writers.put("3_0_0", JRXmlWriter_3_0_0.class);
		// writers.put("2_0_5", JRXmlWriter_2_0_5.class);
		// writers.put("2_0_4", JRXmlWriter_2_0_4.class);
		// writers.put("2_0_3", JRXmlWriter_2_0_3.class);
		// writers.put("2_0_2", JRXmlWriter_2_0_2.class);
	}

	public static String[][] getVersions() {
		List<String> sl = new ArrayList<String>(writers.keySet());
		Collections.sort(sl);
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

	public static String writeReport(JRReport report, IFile file, boolean showDialog) throws Exception {

		return JRXmlWriterHelper.writeReport(report, file, file.getCharset(true),
				getVersion(file, new PropertiesHelper(file.getProject()), showDialog));
	}

	public static String writeReport(JRReport report, IFile file, String encoding, String version) throws Exception {
		if (writers.containsKey(version)) {
			Class<? extends JRXmlWriter> clazz = writers.get(version);
			if (clazz != null)
				return (String) clazz.getMethod("writeReport", new Class[] { JRReport.class, String.class }).invoke(null,
						new Object[] { report, encoding });
		}
		return JRXmlWriter.writeReport(report, encoding);
	}

	public static String getVersion(IResource resource, PropertiesHelper ph, boolean showDialog) {
		String version = ph.getString(StudioPreferencePage.JSS_COMPATIBILITY_VERSION, "last");
		if (showDialog && ph.getBoolean(StudioPreferencePage.JSS_COMPATIBILITY_SHOW_DIALOG, false)) {
			VersionDialog dialog = new VersionDialog(Display.getDefault().getActiveShell(), version);
			version = dialog.open(resource.getProject());
		}
		return version;
	}

}
