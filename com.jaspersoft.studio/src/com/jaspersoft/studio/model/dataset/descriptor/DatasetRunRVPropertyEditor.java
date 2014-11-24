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
package com.jaspersoft.studio.model.dataset.descriptor;

import java.util.List;

import net.sf.jasperreports.engine.ReturnValue;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.property.descriptor.returnvalue.ReturnValueContainer;

/**
 * Wizard to edit the return parameters of a dataset run
 * 
 * @author Orlandin Marco
 *
 */
public class DatasetRunRVPropertyEditor extends Wizard {
	
	/**
	 * The edit dataset run
	 */
	private MDatasetRun mdatasetrun;
	
	/**
	 * The page where the user can edit\add\delete the parameters
	 */
	private DatasetRunRVPropertyPage page0;
	
	/**
	 * Create the wizard to edit the return parameters of a dataset run
	 *  
	 * @param mdatasetrun the edit dataset run
	 */
	public DatasetRunRVPropertyEditor(MDatasetRun mdatasetrun) {
		super();
		setWindowTitle(Messages.common_properties);
		setNeedsProgressMonitor(false);
		this.mdatasetrun = mdatasetrun;
	}
	
	/**
	 * Return the list of parameters after the change made from the user
	 * 
	 * @return a not null list of return parameters to set inside the dataset run
	 */
	public List<ReturnValue> getValue() {
		return ReturnValueContainer.convertToDatasetRun(page0.getValue());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addPages() {
		page0 = new DatasetRunRVPropertyPage(mdatasetrun);
		List<ReturnValue> returnValues = (List<ReturnValue>)mdatasetrun.getPropertyValue(JRDesignDatasetRun.PROPERTY_RETURN_VALUES);
		page0.setValue(ReturnValueContainer.convertFromDatasetRunReturn(returnValues));
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}