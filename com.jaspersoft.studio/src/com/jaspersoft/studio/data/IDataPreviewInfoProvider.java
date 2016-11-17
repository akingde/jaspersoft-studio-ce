/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * This interface should be implemented by those classes that 
 * are supposed to provide information to deal with data preview.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface IDataPreviewInfoProvider {

	/**
	 * @return the jasper reports configuration
	 */
	JasperReportsConfiguration getJasperReportsConfig();
	
	/**
	 * @return the data adapter currently set
	 */
	DataAdapterDescriptor getDataAdapterDescriptor();

	/**
	 * @return the design dataset
	 */
	JRDesignDataset getDesignDataset();
	
	/**
	 * @return the list of fields currently selected for preview
	 */
	List<JRDesignField> getFieldsForPreview();
	
}
