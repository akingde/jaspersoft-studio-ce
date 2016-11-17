/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.parameter.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import com.jaspersoft.studio.model.dataset.MDatasetRun;

/**
 * Wizard with a single page where the user can select and provide an expression for the parameters
 * of a dataset run. The parameters that can be selected are only the ones already provided by the dataset referenced
 * by the dataset run
 *
 */
public class ComboParameterEditor extends ParameterEditor{

	private MDatasetRun datasetRun;
	
	private JRParameter[] datasetRunParameters;

	public ComboParameterEditor(MDatasetRun datasetRun){
		this.datasetRun = datasetRun;
		datasetRunParameters = getDatasetRunParameters();
	}

	private JRParameter[] getDatasetRunParameters(){
		JRParameter[] datasetParameters;
		//get the dataset referenced by the dataset run
		JRDataset dataset = datasetRun.getJasperDesign().getDatasetMap().get(datasetRun.getPropertyValue(JRDesignDatasetRun.PROPERTY_DATASET_NAME));
		if (dataset != null) {
			List<JRParameter> userParameters = new ArrayList<JRParameter>();
			//flag to add or not the system parameters
			boolean addSystemParameters = true;
			for(JRParameter param : dataset.getParameters()){
				if (!param.isSystemDefined() || addSystemParameters) userParameters.add(param);
			}
			//the original dataset parameters are cached
			datasetParameters = userParameters.toArray(new JRParameter[userParameters.size()]);
		}
		else datasetParameters = new JRDesignParameter[0];
		return datasetParameters;
	}
	
	@Override
	protected ParameterPage getEditingPage() {
		return new ComboParametersPage(datasetRunParameters);
	}

}
