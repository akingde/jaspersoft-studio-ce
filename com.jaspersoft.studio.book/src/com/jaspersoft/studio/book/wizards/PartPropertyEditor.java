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

import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterEditor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterPage;
import com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog.SubreportParameterPage;

/**
 * Wizard to add, remove or edit the parameters from an
 * MReportPart
 * 
 * @author Orlandin Marco
 *
 */
public class PartPropertyEditor extends ParameterEditor {
	
	private class PartPropertyPage extends SubreportParameterPage{
		
		@Override
		public String getTitle() {
			return Messages.PartPropertyEditor_pageTitle;
		}
		
		@Override
		public String getDescription() {
			return Messages.PartPropertyEditor_pageDescription;
		}
	}
	
	@Override
	protected ParameterPage getEditingPage() {
		return new PartPropertyPage();
	}
	
	@Override
	public String getWindowTitle() {
		return Messages.PartPropertyEditor_wizardTitle;
	}
}
