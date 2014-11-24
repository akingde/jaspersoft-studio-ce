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

import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterEditor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterPage;

public class SubreportParameterEditor extends ParameterEditor {
	
	private JasperDesign jd;

	public SubreportParameterEditor(JasperDesign jd) {
		super();
		this.jd = jd;
	}
	
	@Override
	protected ParameterPage getEditingPage() {
		SubreportParameterPage page = new SubreportParameterPage();
		page.setJasperDesign(jd);
		return page;
	}
}
