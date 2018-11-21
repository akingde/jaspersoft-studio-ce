/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.repo.RepositoryContext;
import net.sf.jasperreports.repo.RepositoryUtil;
import net.sf.jasperreports.repo.SimpleRepositoryContext;
import net.sf.jasperreports.repo.SimpleRepositoryResourceContext;

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
			IFile file = (IFile) jConfig.get(FileUtils.KEY_FILE);
			if (file != null) {
				String parentPath = file.getParent().getLocation().toFile().getAbsolutePath();
				SimpleRepositoryResourceContext context = SimpleRepositoryResourceContext.of(parentPath);
				RepositoryContext repoContext = SimpleRepositoryContext.of(jConfig, context);
				in = RepositoryUtil.getInstance(repoContext).getInputStreamFromLocation(URL);
			} else {
				in = RepositoryUtil.getInstance(jConfig).getInputStreamFromLocation(URL);
			}
			mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			result = (WidgetsDescriptor)mapper.readValue(in, classToResolve);
		} catch (Exception e) {
			JaspersoftStudioPlugin.getInstance().logError(e);
		} finally {
			FileUtils.closeStream(in);
		}
		return result;
	}
	
	/**
	 * Create the key for a specific definition location information
	 * 
	 * @param jConfig the current {@link JasperReportsConfiguration}
	 * @param url the url of the loaded location
	 * @return an unique key for the resource
	 */
	@Override
	public String getKey(JasperReportsConfiguration jConfig, String URL) {
		IFile project = (IFile) jConfig.get(FileUtils.KEY_FILE);
		String projectPath = project.getLocation().toPortableString();
		String key = projectPath + URL;		
		return key;
	}

	@Override
	public boolean unloadOnConfigurationDispose() {
		return true;
	}

}
