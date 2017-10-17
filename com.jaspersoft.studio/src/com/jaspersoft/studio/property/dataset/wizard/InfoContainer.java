/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.wizard;

import java.util.List;

import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.dataset.MDatasetRun;

/**
	 * Inner class used to store some data about a dataset run
	 * 
	 * @author Orlandin Marco
	 *
	 */
	public class InfoContainer{
		
		/**
		 * a Dataset run reference
		 */
		private MDatasetRun datasetRun;
		
		/**
		 * List of parameters missing from the referenced dataset run
		 */
		private List<String> missingParameters;
		
		/**
		 * Reference to the element from where the referenced dataset run is taken
		 */
		private IDatasetContainer container;
		
		/**
		 * Create the container 
		 * 
		 * @param missingParameters List of parameters missing from the referenced dataset run
		 * @param container Reference to the element from where the referenced dataset run is taken
		 * @param datasetRun a Dataset run reference
		 */
		public InfoContainer(List<String> missingParameters, IDatasetContainer container, MDatasetRun datasetRun){
			this.missingParameters = missingParameters;
			this.container = container;
			this.datasetRun = datasetRun;
		}
		
		/**
		 * Return the referenced dataset run
		 * 
		 * @return an MDataset run or null
		 */
		public MDatasetRun getRun(){
			return datasetRun;
		}
		
		/**
		 * Return the parameters missing from the dataset run
		 * 
		 * @return list of string containing the name of the parameters missing inside the dataset run or null
		 */
		public List<String> getMissingParameters(){
			return missingParameters;
		}
		
		/**
		 * Return the element that contains the referenced dataset run
		 * 
		 * @return an IDatasetContainer (probably it will be a node) or null
		 */
		public IDatasetContainer getContainer(){
			return container;
		}
	}
