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
package com.jaspersoft.studio.server.publish.imp;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Bundle;

import com.jaspersoft.jasperreports.chartcustomizers.ProxyChartCustomizer;
import com.jaspersoft.jasperreports.chartcustomizers.utils.ChartCustomizerUtils;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MJar;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Class used to search and propose for the upload also the jar of the customizers. First 
 * look for the embedded customizers, included the {@link ProxyChartCustomizer}. If the current
 * customizer is one of these check if the version of the server is 6.3.1 or greater. If so it 
 * doesn't do anything (because since 6.3.1 the customizer jar is shipped with JR), 
 * otherwise propose to upload the customizer library. Otherwise if the customizer
 * is not of the ones emebedded it will search its jar and if it is found it propose to upload that
 * jar
 * 
 * @author Orlandin Marco
 *
 */
public class ImpChartCustomizer extends AImpObject {
	
	/**
	 * The minimum version of the server where the customizer jar is embedded
	 */
	private static final String EMBEDDED_JAR_MIN_VERSION = "6.3.1";
	
	/**
	 * The set of classname of the customizers that are embedded
	 */
	private HashSet<String> embeddedCustomizers;
	
	/**
	 * The path of the jar of the embedded customizer
	 */
	private File customizerJar = null;
	
	public ImpChartCustomizer(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
		
		//Initialize the emebedded customizers informations
		embeddedCustomizers = new HashSet<String>();
		Bundle bundle = JasperReportsPlugin.getDefault().getBundle();
		URL fileURL = bundle.getEntry("lib/jasperreports_chartcustomizers.jar");
		try{
			if (fileURL != null){			
				customizerJar = new File(FileLocator.getBundleFile(bundle), fileURL.getFile());
				for(Class<?> customizerClass : ChartCustomizerUtils.getEmbeddedCustomizers()){
					embeddedCustomizers.add(customizerClass.getName());
				}
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public File publish(JasperDesign jd, String className, MReportUnit mrunit, IProgressMonitor monitor, Set<String> fileset, IFile file, String version)  throws Exception {
		AFileResource fres = findFile(mrunit, monitor, jd, fileset, file, className, version);
		return fres != null ? fres.getFile() : null;
	}

	@Override
	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MJar.createDescriptor(mrunit);
	}

	@Override
	public AFileResource publish(JasperDesign jd, JRDesignElement img,
			MReportUnit mrunit, IProgressMonitor monitor, Set<String> fileset,
			IFile file) throws Exception {
		return null;
	}

	@Override
	protected JRDesignExpression getExpression(JRDesignElement img) {
		return null;
	}
	
	/**
	 * Add to the server exported resources a jar containing the class whose name is passed as parameter.
	 * If the class is not provided from a local jar or for any reason this jar can not be found then this
	 * doesn't do anything
	 * 
	 * @param className the name of the class searched among the jar
	 */
	protected AFileResource findFile(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jd, Set<String> fileset, IFile reportFile, String className, String version) {
		try{
			if (embeddedCustomizers.contains(className)){
				//Check if the JR on the servers need the adapter jar
				boolean needToAddJar = FileUtils.versionCompare(EMBEDDED_JAR_MIN_VERSION, version) > 0;
				if (needToAddJar && customizerJar != null && customizerJar.exists()) {
					//need to commit also the customizer jar
					if (!fileset.contains(customizerJar.getAbsolutePath())){
						PublishOptions popt = createOptions(jrConfig, customizerJar.getAbsolutePath());
						fileset.add(customizerJar.getAbsolutePath());
						return addResource(monitor, mrunit, fileset, customizerJar, popt);	
					}
				}
			} else {	
				//custom customizer, load its class and search for the jar
				Class<?> customizerClass = jrConfig.getClassLoader().loadClass(className);
				String str = findPathJar(customizerClass);
				File f = findFile(reportFile, str);
				if (f != null && f.exists()) {
					if (!fileset.contains(str)){
						PublishOptions popt = createOptions(jrConfig, str);
						fileset.add(str);
						return addResource(monitor, mrunit, fileset, f, popt);	
					}
				}
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * If the provided class has been loaded from a jar file that is on the local file system, will find the absolute path to that jar file.
	 * 
	 * @param customizerClass The jar file that contained the class file that represents this class will be found. 
	 * @return the absolute path of the jar or null if it can't be found
	 */
	protected String findPathJar(Class<?> customizerClass) throws IllegalStateException {
	    String classFileName = '/' + customizerClass.getName().replace('.', '/') + ".class";
	    String uri = customizerClass.getResource(classFileName).toString();
	    if (!uri.startsWith("jar:file:")) {
	    	//class is not from a file jar, return null
	    	return null;
	    }

	    int idx = uri.indexOf('!');
	    try {
	    	if (idx != -1){
	    		return URLDecoder.decode(uri.substring("jar:file:".length(), idx), Charset.defaultCharset().name());
	    	} else {
	    		return URLDecoder.decode(uri.substring("jar:file:".length()), Charset.defaultCharset().name());
	    	}
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * Return the list of the defined customizers in the properties of an element
	 * 
	 * @param chart the uploaded chart
	 * @return a not null list of String representing the customizer classes loaded by a {@link ProxyChartCustomizer} from the properties
	 * of the element
	 */
	public List<String> getSubCustmizersClass(JRChart chart){
		List<String> result = new ArrayList<String>();
		List<JRPropertiesUtil.PropertySuffix> properties = JRPropertiesUtil.getProperties(chart.getPropertiesMap(), ProxyChartCustomizer.CUSTOMIZER_ATTRIBUTE_PREFIX + 
																														ProxyChartCustomizer.CUSTOMIZER_ATTRIBUTE_SEPARATOR);
		// We look for all the class properties...
		for (JRPropertiesUtil.PropertySuffix prop : properties) {
			//Escape the separator char
			Pattern escaper = Pattern.compile("([^a-zA-z0-9])");
			String splitPattern =  escaper.matcher(ProxyChartCustomizer.CUSTOMIZER_ATTRIBUTE_SEPARATOR).replaceAll("\\\\$1");
			String[] parts = prop.getSuffix().split(splitPattern);
			if (parts.length == 2 && parts[1].equals(ProxyChartCustomizer.CUSTOMIZER_CLASS_ATTRIUBUTE)) {
				String customizerClass = prop.getValue();
				result.add(customizerClass);
			}
		}
		return result;
	}

}
