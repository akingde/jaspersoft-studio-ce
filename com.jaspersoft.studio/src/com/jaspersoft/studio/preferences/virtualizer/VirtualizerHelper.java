/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.preferences.virtualizer;

import java.io.File;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.fill.JRGzipVirtualizer;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VirtualizerHelper {
	public static void setVirtualizer(JasperDesign jd, JasperReportsConfiguration jContext, Map<String, Object> parammap) {
		if (jContext.getPropertyBoolean(VirtualizerPreferencePage.JSS_VIRTUALIZER_USE, false)) {
			List<JRParameter> params = jd.getParametersList();
			for (JRParameter p : params) {
				if (p.getValueClassName().equals(JRVirtualizer.class.getName())) {
					parammap.put(p.getName(), createVirtualizer(jContext));
				}
			}
		}
	}

	private static JRVirtualizer createVirtualizer(JasperReportsConfiguration jContext) {
		JRVirtualizer v = null;
		String vtype = jContext.getProperty(VirtualizerPreferencePage.JSS_VIRTUALIZER_TYPE);
		int maxSize = jContext.getPropertyInteger(VirtualizerPreferencePage.JSS_VIRTUALIZER_MAX_SIZE, 100);

		if (vtype != null) {
			if (vtype.equals(JRFileVirtualizer.class.getName())) {
				v = new JRFileVirtualizer(maxSize);
			} else if (vtype.equals(JRGzipVirtualizer.class.getName())) {
				v = new JRGzipVirtualizer(maxSize);
			} else if (vtype.equals(JRSwapFileVirtualizer.class.getName())) {
				int blockSize = jContext.getPropertyInteger(VirtualizerPreferencePage.JSS_VIRTUALIZER_BLOCK_SIZE, 100);
				int minGrowCount = jContext.getPropertyInteger(VirtualizerPreferencePage.JSS_VIRTUALIZER_MIN_GROW_COUNT, 100);
				String directory = jContext.getProperty(VirtualizerPreferencePage.JSS_VIRTUALIZER_TMP);
				if (directory != null && !directory.trim().equals("")) {
					// check if exists
					File f = new File(directory);
					if (!f.exists())
						directory = null;
				}
				if (directory == null || directory.trim().equals("")) {
					IWorkspace workspace = ResourcesPlugin.getWorkspace();
					directory = workspace.getRoot().getLocation().toOSString();
				}

				v = new JRSwapFileVirtualizer(maxSize, new JRSwapFile(directory, blockSize, minGrowCount), false);
			}
		}
		return v;
	}
}
