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
package com.jaspersoft.studio.book.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.part.PartComponent;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.parts.subreport.SubreportPartComponent;

import org.eclipse.swt.SWT;

import com.jaspersoft.studio.book.ReportThumbnailsManager;
import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ComboInputParameterDialog;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ComboParametersPage;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.InputParameterDialog;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterEditor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterPage;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.ExtensionLoader;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Wizard to add, remove or edit the parameters from an
 * MReportPart. It inspect the target jasper design to found
 * a list of the provided parameters. The report is loaded when
 * the wizard is opened and it is loaded in background. Doing this
 * if the user will use the add or edit option the inspect will be 
 * faster since the loading operation was already started before
 * 
 * @author Orlandin Marco
 *
 */
public class PartPropertyEditor extends ParameterEditor {
	
	/**
	 * The loaded report for the inspect of the parameters
	 */
	private JRReport partReport;
	
	/**
	 * True if the report is currently under loading, false otherwise
	 */
	private boolean isLoading = false;
	
	/**
	 * Page where the user can add parameters
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class PartPropertyPage extends ComboParametersPage{
		
		public PartPropertyPage() {
			super(null);
		}
		
		/**
		 * Return the input of the combo, a list of the parameter name of the original dataset
		 * not already used by other parameters
		 * 
		 * @return the list of string displayed in the combo
		 */
		protected List<String> createNameComboInput(){
			List<String> result = new ArrayList<String>();
			HashSet<String> usedParams = new HashSet<String>();
			for(GenericJSSParameter param : values){
					usedParams.add(param.getName());
			}
			if (getJasperDesign() != null){
				for (JRParameter param : getJasperDesign().getParameters()){
					if (!usedParams.contains(param.getName())){
							if (param.getName() != null) result.add(param.getName());
					}
				}
				Collections.sort(result);
			}
			return result;
		}
		
		/**
		 * Return an editable combo control
		 */
		@Override
		protected InputParameterDialog getEditDialog(GenericJSSParameter editedParameter) {
			return new ComboInputParameterDialog(getShell(), createNameComboInput(), editedParameter, SWT.DROP_DOWN);
		}

		@Override
		public String getTitle() {
			return Messages.PartPropertyEditor_pageTitle;
		}
		
		@Override
		public String getDescription() {
			return Messages.PartPropertyEditor_pageDescription;
		}
	}
	
	/**
	 * Create the class and start the preload of the jasperdesign
	 * 
	 * @param part edited report part
	 */
	public PartPropertyEditor(MReportPart part) {
		super();
		preloadReport(part);
	}
	
	@Override
	protected ParameterPage getEditingPage() {
		return new PartPropertyPage();
	}
	
	@Override
	public String getWindowTitle() {
		return Messages.PartPropertyEditor_wizardTitle;
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
		return partReport;
	}
	
	/**
	 * Run the thread that pre-load the target report
	 * 
	 * @param part part containing the expression of the target report
	 * to preload
	 */
	private void preloadReport(final MReportPart part) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				setLoading(true);
				ExtensionLoader.waitIfLoading();
				try {
					Object reportFileName = null;
					JRDesignPart jrDesignPart = part.getValue();
					JasperReportsConfiguration context = part.getJasperConfiguration();
					if (jrDesignPart != null) {
						PartComponent partComponent = jrDesignPart
								.getComponent();
						if (partComponent instanceof SubreportPartComponent) {
							JRExpression subreportExp = ((SubreportPartComponent) partComponent)
									.getExpression();
							if (subreportExp != null) {
								JRDesignDataset dataset = (JRDesignDataset)part.getJasperDesign().getMainDataset();
								reportFileName = ExpressionUtil.cachedExpressionEvaluation(subreportExp, context, dataset);
							}
						}
					}

					// Report not found
					if (reportFileName == null) return;

					if (reportFileName instanceof File) {
						reportFileName = ((File) reportFileName).toURI().toString();
					} else if (!(reportFileName instanceof String)) {
						return; // We only understand string paths...
					}
					String location = (String) reportFileName;
					File f = ReportThumbnailsManager.findFile(location, context);
					if ((f == null || !f.exists()) && location.toLowerCase().endsWith(".jasper")) {
						// check for a jrxml...
						location = location.substring(0, location.length() - ".jasper".length()) + ".jrxml";
						f = ReportThumbnailsManager.findFile(location, context);
					}
					if (f.getName().toLowerCase().endsWith(".jasper")){
						partReport = (JRReport) JRLoader.loadObject(f);
					} else {
						partReport = (JRReport) JRXmlLoader.load(f);
					}
					
				} catch (Exception ex) {
					ex.printStackTrace();
					partReport = null;
				}
				setLoading(false);
			}
		});
		t.start();
	}
}
