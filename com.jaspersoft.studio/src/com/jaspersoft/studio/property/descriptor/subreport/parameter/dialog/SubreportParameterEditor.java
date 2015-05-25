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
package com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;

import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterEditor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterPage;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.ExtensionLoader;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class SubreportParameterEditor extends ParameterEditor {
	
	/**
	 * The loaded report for the inspect of the parameters
	 */
	private JRReport subreportDesign;
	
	/**
	 * True if the report is currently under loading, false otherwise
	 */
	private boolean isLoading = false;
	
	/**
	 * The jasper design of the current report
	 */
	private JasperDesign jd;

	public SubreportParameterEditor(MSubreport subReport) {
		super();
		this.jd = subReport.getJasperDesign();
		preloadReport(subReport);
	}
	
	@Override
	protected ParameterPage getEditingPage() {
		SubreportParameterPage page = new SubreportParameterPage(this);
		page.setJasperDesign(jd);
		return page;
	}
	
	/**
	 * Set the value of the loading flag. This method is
	 * thread safe
	 * 
	 * @param value the new loading status
	 */
	private synchronized void setLoading(boolean value){
		isLoading = value;
	}
	
	/**
	 * Check if the report is currently under loading. This
	 * method is thread safe
	 * 
	 * @return true if the design is currently under loading,
	 * false otherwise
	 */
	private synchronized boolean isLoading(){
		return isLoading;
	}
	
	/**
	 * Return the loaded jasperdesign. If it is currently
	 * loading then it wait until the load is complete
	 * 
	 * @return The jasperdesign or null if the jasper design
	 * can not be found
	 */
	public JRReport getJasperDesign(){
		while(isLoading()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return subreportDesign;
	}
	
	/**
	 * Run the thread that pre-load the target report
	 * 
	 * @param subreport part containing the expression of the target report
	 * to preload
	 */
	private void preloadReport(final MSubreport subreport) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				setLoading(true);
				try {
					ExtensionLoader.waitIfLoading();
					Object reportFileName = null;
					JRDesignSubreport jrSubreport = subreport.getValue();
					JasperReportsConfiguration context = subreport.getJasperConfiguration();
					if (jrSubreport != null) {
						JRExpression subreportExp = jrSubreport.getExpression();
						if (subreportExp != null) {
							JRDesignDataset dataset = (JRDesignDataset)subreport.getJasperDesign().getMainDataset();
							reportFileName = ExpressionUtil.cachedExpressionEvaluation(subreportExp, context, dataset);
						}
					}

					// Report not found
					if (reportFileName != null){
						if (reportFileName instanceof File) {
							reportFileName = ((File) reportFileName).toURI().toString();
						} else if (!(reportFileName instanceof String)) {
							return; // We only understand string paths...
						}
						String location = (String) reportFileName;
						File f = findFile(location, context);
						if ((f == null || !f.exists()) && location.toLowerCase().endsWith(".jasper")) {
							// check for a jrxml...
							location = location.substring(0, location.length() - ".jasper".length()) + ".jrxml";
							f = findFile(location, context);
						}
						if (f.getName().toLowerCase().endsWith(".jasper")){
							subreportDesign = (JRReport) JRLoader.loadObject(f);
						} else {
							subreportDesign = (JRReport) JRXmlLoader.load(f);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					subreportDesign = null;
				}
				setLoading(false);
			}
		});
		t.start();
	}
	
	/**
	 * Convenient method to look for a file.
	 * Find the file with an euristic approach....
     * Attention... this could be slow in very very large environments..
	 * Search is performed in the following way:
	 * 
	 * 1. Check if location can be found on the file system (assuming location an absolute path)
	 * 2. Check if the location can be loaded from the classpath (assuming a file in a folder present in the classpath of the report)
	 * 3. Check if the file can be found relatively to the current report file (pointed by this context)
	 * 4. Check if the file can be found relatively to the current project (to which the file at point 3 belongs to)
	 * 
	 * @param location
	 * @param context
	 * @return
	 */
	public static File findFile(String location, JasperReportsConfiguration context)
	{
		// Check if the location is an absolute path...
		File f = new File(location);
		if (f.exists()) return f;
		
		
		// Check if the file can be found in the classpath...
		URL url = context.getClassLoader().getResource(location);
		
		if (url != null)
		{
			try {
				f = new File( url.toURI());
				if (f.exists()) return f;
			} catch (URISyntaxException e) {
			}
		}
		
		// We check locally to the current file directory... maybe we are more lucky...
		IFile file = (IFile)context.get(FileUtils.KEY_FILE);
		if (file != null)
		{
			// Located the parent...
			f = new File(file.getParent().getLocationURI());
			// ... try to build the full path...
			f = new File(f, location);
			if (f.exists()) return f;
			
			// Finally we look inside this project...
			// We check locally to the current file directory... maybe we are more lucky...
			f = new File(file.getProject().getLocationURI());
			// ... try to build the full path...
			f = new File(f, location);
			if (f.exists()) return f;
		}
		
		return null;
	}
	
}
