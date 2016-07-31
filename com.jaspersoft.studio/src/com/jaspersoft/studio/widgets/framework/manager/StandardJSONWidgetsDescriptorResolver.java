/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import java.io.InputStream;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.repo.RepositoryUtil;

/**
 * Default resolver to load a {@link WidgetsDescriptor} from a JSON file
 * 
 * @author Orlandin Marco
 *
 */
public class StandardJSONWidgetsDescriptorResolver implements IWidgetsDescriptorResolver {

	/**
	 * The class that should be deserialized from the JSON
	 */
	private Class<?> classToResolve;
	
	/**
	 * Create the resolver
	 * 
	 * @param classToResolve The class that should be deserialized from the JSON
	 */
	public StandardJSONWidgetsDescriptorResolver(Class<?> classToResolve) {
		this.classToResolve = classToResolve;
	}
	
	@Override
	public WidgetsDescriptor loadDescriptor(JasperReportsConfiguration jConfig, String URL) {
		ObjectMapper mapper = new ObjectMapper();
		InputStream in = null;
		WidgetsDescriptor result = null;
		try {
			in = RepositoryUtil.getInstance(jConfig).getInputStreamFromLocation(URL);
			mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			result = (WidgetsDescriptor)mapper.readValue(in, classToResolve);
		} catch (Exception e) {
			JaspersoftStudioPlugin.getInstance().logError(e);
		} finally {
			FileUtils.closeStream(in);
		}
		return result;
	}

}
