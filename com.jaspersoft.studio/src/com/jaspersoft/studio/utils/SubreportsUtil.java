/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.builder.jdt.JDTUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;

public class SubreportsUtil {
	
	private SubreportsUtil() {
	}
	
	public static Map<File, IFile> getSubreportFiles(JasperReportsConfiguration jConfig, IFile file, JasperDesign jd,
			IProgressMonitor monitor) {
		Map<File, IFile> fmap = new HashMap<>();
		try {
			List<JRDesignElement> elements = ModelUtils.getAllElements(jd);
			for (JRDesignElement ele : elements) {
				if (ele instanceof JRDesignSubreport)
					publishSubreport(jConfig, fmap, monitor, file, jd, (JRDesignSubreport) ele);
			}
		} finally {
			jConfig.init(file);
		}
		return fmap;
	}

	private static void publishSubreport(JasperReportsConfiguration jConfig, Map<File, IFile> fmap,
			IProgressMonitor monitor, IFile file, JasperDesign parent, JRDesignSubreport ele) {
		jConfig.init(file);
		String expr = ExpressionUtil.eval(ele.getExpression(), jConfig, parent);
		if (expr == null || expr.isEmpty()) {
			return;
		}
		if (expr.endsWith(FileExtension.PointJASPER)) {
			expr = StringUtils.replaceAllIns(expr, FileExtension.PointJASPER + "$", FileExtension.PointJRXML);
		}
		expr = expr.replaceFirst("repo:", "");
		File f = FileUtils.findFile(file, expr);
		if (f == null) {
			try {
				f = fallbackFindFile(file, expr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (fmap.containsKey(f)) {
			return;
		}
		if (f != null && f.exists()) {
			IFile[] fs = JDTUtils.WS_ROOT.findFilesForLocationURI(f.toURI());
			if (fs != null && fs.length > 0) {
				IFile ifile = fs[0];
				fmap.put(f, ifile);
				try {
					JasperDesign jd = JRXMLUtils.getJasperDesign(jConfig, ifile.getContents(), ifile.getFileExtension());
					if (jd != null) {
						for (JRDesignElement el : ModelUtils.getAllElements(jd)) {
							if (el instanceof JRDesignSubreport)
								publishSubreport(jConfig, fmap, monitor, ifile, jd, (JRDesignSubreport) el);
							if (monitor.isCanceled())
								break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				fmap.put(f, null);
			}
		}
	}

	private static File fallbackFindFile(IFile file, String expression) {
		File f;
		try {
			f = new File(expression);
		} catch (IllegalArgumentException e) {
			f = new File(file.getRawLocationURI().getPath(), expression);
		}
		return f;
	}

}
