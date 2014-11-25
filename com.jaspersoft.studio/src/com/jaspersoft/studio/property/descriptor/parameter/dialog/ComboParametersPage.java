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
package com.jaspersoft.studio.property.descriptor.parameter.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.engine.JRParameter;

/**
 * Page used to add a parameter to a dataset run. The parameter can only be choose between
 * the parameters already defined for dataset referenced by the dataset run. This avoid to have
 * a dataset run parameter with a value of a not existing parameter 
 * 
 * @author Orlandin Marco
 *
 */
public class ComboParametersPage extends ParameterPage {
	
	/**
	 * List of the parameter defined on the dataset
	 */
	protected JRParameter[] availableParameters;

	/**
	 * Build the page to edit the parameters
	 * 
	 * @param availableParameters a not null list of the parameters
	 * available on the dataset referenced by the dataset run
	 */
	public ComboParametersPage(JRParameter[] availableParameters){
		super();
		this.availableParameters = availableParameters;
	}
	
	/**
	 * Return the input of the combo, a list of the parameter name of the original dataset
	 * not already used by other parameters of the dataset run, plus a void element on the top
	 * of the list to remove a parameter from a dataset run
	 * 
	 * @return the list of string displayed in the combo
	 */
	protected List<String> createNameComboInput(){
		List<String> result = new ArrayList<String>();
		HashSet<String> usedParams = new HashSet<String>();
		for(GenericJSSParameter param : values){
				usedParams.add(param.getName());
		}
		for (JRParameter param : availableParameters){
			if (!usedParams.contains(param.getName())){
					if (param.getName() != null) result.add(param.getName());
			}
		}
		Collections.sort(result);
		return result;
	}

	@Override
	protected InputParameterDialog getEditDialog(GenericJSSParameter editedParameter) {
		return new ComboInputParameterDialog(getShell(), createNameComboInput(), editedParameter);
	}
}
