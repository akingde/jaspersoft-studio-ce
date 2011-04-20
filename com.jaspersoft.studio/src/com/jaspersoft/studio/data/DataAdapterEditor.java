/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
/*
 * A IReportConnectionEditor class provides a complete custom GUI for customizing a target IReportConnection.<br>
 * Each IReportConnectionEditor should inherit from the java.awt.Component class so it can be instantiated inside an AWT
 * dialog or panel.<br>
 * Each IReportConnectionEditor should have a null constructor.<br>
 * 
 * @author gtoffoli
 */
public interface DataAdapterEditor {

	/**
	 * Set the DataAdapter to edit. Actually it is a copy of the original DataAdapter. It can be modifed by
	 * the user interface.<br>
	 * <br>
	 * 
	 * The copy of an DataAdapter is done instancing a new class of the same type and loading the properties stored
	 * by the first object
	 * 
	 * @param dataAdapter
	 *          DataAdapter to edit
	 */
	public void setDataAdapter(DataAdapter dataAdapter);

	/**
	 * This method is called when the user completes to edit the datasource or when a datasource test is required.
	 * 
	 * @return IReportConnection modified. IT can be the same instance get in input with setIReportConnection or a new
	 *         one.
	 */
	public DataAdapter getDataAdapter();
	
	/**
	 * This method allows to provide a UI component to edit the data adapter. The WizardPage reference is convenient
	 * for calling specific methods from WizardPage class like setMessage() method but this is not mandatory.
	 * @param parent
	 * @param style
	 * @param wizardPage can be null
	 * @return composite
	 */
	public Composite getComposite(Composite parent, int style, WizardPage wizardPage);
	
	/**
	 * This method returns the help context ID for the composite returned by getComposite()
	 * 
	 * @return String context ID, i.e:
	 *  
	 * As possible default, the context id "com.jaspersoft.studio.doc.dataAdapters_wizard_list" can be return.
	 * 
	 */
	public String getHelpContextId();
}
