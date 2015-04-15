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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRReport;

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
	
	private MReportPart part;
	
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
		this.part = part;
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
	 * Return the loaded jasperdesign. If it is currently
	 * loading then it wait until the load is complete
	 * 
	 * @return The jasperdesign or null if the jasper design
	 * can not be found
	 */
	public JRReport getJasperDesign(){
		String location = ReportThumbnailsManager.getLocation(part);
		return ReportThumbnailsManager.getJasperDesign(location);
	}
}
