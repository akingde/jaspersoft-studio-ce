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
