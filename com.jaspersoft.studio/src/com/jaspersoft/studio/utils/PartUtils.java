/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Utility methods to handle the JRPart elements and its content
 * 
 * @author Orlandin Marco
 *
 */
public class PartUtils {

	/**
	 * The current workspace root
	 */
	private static IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	
	/**
	 * Inspect the passed report to search report parts and return a map of the 
	 * subreport used by them. The subreport are explored recursively to find other
	 * resources
	 * 
	 * @param jConfig configuration of the report
	 * @param jd the jasperdesign of the report
	 * @param isRecursive boolean flag to search other subreports 
	 * inside the the first level subreport recursively
	 * @param monitor monitor of the execution
	 * @return
	 */
	public static Map<File, IFile> getSubreportsFromParts(JasperReportsConfiguration jConfig, JasperDesign jd, boolean isRecursive, IProgressMonitor monitor) {
		IFile mainFile = (IFile) jConfig.get(FileUtils.KEY_FILE);
		List<JRPart> elements = ModelUtils.getAllPartElements(jd);
		Map<File, IFile> result = new HashMap<File, IFile>();
		for(JRPart part : elements){
			if (part.getComponent() instanceof StandardSubreportPartComponent){
				StandardSubreportPartComponent component = (StandardSubreportPartComponent)part.getComponent();
				findSubreport(jConfig, result, monitor, mainFile, jd, isRecursive, component);		
			}
		}
		return result;
	}

	/**
	 * Search the subreport inside the current part. It can be set to be recursive
	 * and search the subreports inside the founded subreport
	 * 
	 * @param jConfig the current jasper report configuration
	 * @param fmap map where the result can be saved
	 * @param monitor monitor to execute the operation 
	 * @param file the file of the report to which the part belong
	 * @param parent the jasperdesign of the report
	 * @param isRecursive flag to choose if the method must search subreports recursively
	 * @param ele the part when the subreports are searched at first
	 */
	protected static void findSubreport(JasperReportsConfiguration jConfig, Map<File, IFile> fmap, IProgressMonitor monitor, IFile file, JasperDesign parent, boolean isRecursive, StandardSubreportPartComponent ele) {
		jConfig.init(file);
		String expr = ExpressionUtil.eval(ele.getExpression(), jConfig, parent);
		if (expr == null || expr.isEmpty())
			return;
		if (expr.endsWith(FileExtension.PointJASPER)) expr = expr.replaceAll(FileExtension.PointJASPER + "$", FileExtension.PointJRXML);
		expr = expr.replaceFirst("repo:", "");
		File f = FileUtils.findFile(file, expr);
		if (f == null)
			try {
				try {
					f = new File(expr);
				} catch (IllegalArgumentException e) {
					f = new File(file.getRawLocationURI().getPath(), expr);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		if (fmap.containsKey(f))
			return;
		if (f != null && f.exists()) {
			IFile[] fs = root.findFilesForLocationURI(f.toURI());
			if (fs != null && fs.length > 0) {
				IFile ifile = fs[0];
				fmap.put(f, ifile);
				try {
					JasperDesign jd = JRXMLUtils.getJasperDesign(jConfig, ifile.getContents(), ifile.getFileExtension());
					if (jd != null && isRecursive) {
						for (JRDesignElement el : ModelUtils.getAllElements(jd)) {
							if (el instanceof JRDesignSubreport){
								Map<File, IFile> partial = SubreportsUtil.getSubreportFiles(jConfig, ifile, jd, monitor);
								fmap.putAll(partial);
							}
							if (monitor.isCanceled()) break;
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
}
