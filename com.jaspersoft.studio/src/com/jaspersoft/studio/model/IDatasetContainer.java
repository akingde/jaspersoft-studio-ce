/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.List;

import com.jaspersoft.studio.model.dataset.MDatasetRun;

/**
 * Interface to specify if an element keep inside a dataset run (like table or crosstab)
 */
public interface IDatasetContainer {
	/**
	 * Return the dataset run of the element
	 */
	public List<MDatasetRun> getDatasetRunList();
}
