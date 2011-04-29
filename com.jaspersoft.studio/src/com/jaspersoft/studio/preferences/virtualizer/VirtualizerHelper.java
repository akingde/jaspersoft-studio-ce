package com.jaspersoft.studio.preferences.virtualizer;

import java.io.File;
import java.util.List;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.fill.JRGzipVirtualizer;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;

import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class VirtualizerHelper {
	@SuppressWarnings("unchecked")
	public static void setVirtualizer(JasperDesign jd, PropertiesHelper ps) {
		if (ps.getBoolean(VirtualizerPreferencePage.JSS_VIRTUALIZER_USE)) {
			List<JRParameter> params = jd.getParametersList();
			for (JRParameter p : params) {
				if (p.getValueClassName().equals(JRVirtualizer.class.getName())) {
					jd.getParametersMap().put(p.getName(), createVirtualizer(ps));
				}
			}
		}
	}

	private static JRVirtualizer createVirtualizer(PropertiesHelper ps) {
		JRVirtualizer v = null;
		String vtype = ps.getString(VirtualizerPreferencePage.JSS_VIRTUALIZER_TYPE);
		int maxSize = ps.getInteger(VirtualizerPreferencePage.JSS_VIRTUALIZER_MAX_SIZE, 100);
		if (vtype != null) {
			if (vtype.equals(JRFileVirtualizer.class.getName())) {
				v = new JRFileVirtualizer(maxSize);
			} else if (vtype.equals(JRGzipVirtualizer.class.getName())) {
				v = new JRGzipVirtualizer(maxSize);
			} else if (vtype.equals(JRSwapFileVirtualizer.class.getName())) {
				int blockSize = ps.getInteger(VirtualizerPreferencePage.JSS_VIRTUALIZER_BLOCK_SIZE, 100);
				int minGrowCount = ps.getInteger(VirtualizerPreferencePage.JSS_VIRTUALIZER_MIN_GROW_COUNT, 100);
				String directory = ps.getString(VirtualizerPreferencePage.JSS_VIRTUALIZER_TMP);
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
