/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter;

import java.util.List;

import com.jaspersoft.studio.preferences.editor.properties.ExportedStudioPropertiesHandler;

/**
 * Interface implemented by an {@link IExportedResourceHandler} to specify it 
 * is used to export a specific set of JSS preference properties. This is 
 * used by the generic {@link ExportedStudioPropertiesHandler} to know which
 * properties should not be exported, since they are handled by a specific 
 * exporter
 * 
 * @author Orlandin Marco
 *
 */
public interface IPropertyCustomExporter {

	/**
	 * Return the list of properties handled by this exporter, they 
	 * will not exported then by the generic {@link ExportedStudioPropertiesHandler}
	 * 
	 * @return a not null list of strings
	 */
	public List<String> getHandledProperties();
	
}
