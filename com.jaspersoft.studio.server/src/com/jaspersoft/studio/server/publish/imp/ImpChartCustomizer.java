/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.imp;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MJar;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.NamedChartCustomizer;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Class used to search and propose for the upload also the jar of the
 * customizers. It will search its jar and if it is found it propose to upload
 * that jar
 * 
 * @author Orlandin Marco
 *
 */
public class ImpChartCustomizer extends AImpObject {

	/**
	 * The set of classname of the customizers that are embedded
	 */
	private HashSet<String> embeddedCustomizers;

	public ImpChartCustomizer(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
		// Initialize the embedded customizers informations
		initializeEmbeddedCustmizers();
	}

	/**
	 * Build the list of the customizers embedded in JR that doesn't need to be
	 * uploaded
	 */
	protected void initializeEmbeddedCustmizers() {
		embeddedCustomizers = new HashSet<>();
	}

	public File publish(JasperDesign jd, String className, MReportUnit mrunit, IProgressMonitor monitor,
			Set<String> fileset, IFile file, String version) throws Exception {
		AFileResource fres = findFile(mrunit, monitor, jd, fileset, file, className, version);
		return fres != null ? fres.getFile() : null;
	}

	@Override
	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MJar.createDescriptor(mrunit);
	}

	@Override
	public AFileResource publish(JasperDesign jd, JRDesignElement img, MReportUnit mrunit, IProgressMonitor monitor,
			Set<String> fileset, IFile file) throws Exception {
		return null;
	}

	@Override
	protected JRDesignExpression getExpression(JRDesignElement img) {
		return null;
	}

	/**
	 * Add to the server exported resources a jar containing the class whose name is
	 * passed as parameter. If the class is not provided from a local jar or for any
	 * reason this jar can not be found then this doesn't do anything
	 * 
	 * @param className
	 *            the name of the class searched among the jar
	 */
	protected AFileResource findFile(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jd, Set<String> fileset,
			IFile reportFile, String className, String version) {
		try {
			if (!embeddedCustomizers.contains(className)) {
				// custom customizer, load its class and search for the jar
				Class<?> customizerClass = jrConfig.getClassLoader().loadClass(className);
				String str = findPathJar(customizerClass);
				File f = findFile(reportFile, str);
				if (f != null && f.exists()) {
					if (!fileset.contains(str)) {
						PublishOptions popt = createOptions(jrConfig, str);
						fileset.add(str);
						return addResource(monitor, mrunit, fileset, f, popt);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * If the provided class has been loaded from a jar file that is on the local
	 * file system, will find the absolute path to that jar file.
	 * 
	 * @param customizerClass
	 *            The jar file that contained the class file that represents this
	 *            class will be found.
	 * @return the absolute path of the jar or null if it can't be found
	 */
	protected String findPathJar(Class<?> customizerClass) throws IllegalStateException {
		String classFileName = '/' + customizerClass.getName().replace('.', '/') + ".class";
		String uri = customizerClass.getResource(classFileName).toString();
		if (!uri.startsWith("jar:file:")) {
			// class is not from a file jar, return null
			return null;
		}

		int idx = uri.indexOf('!');
		try {
			if (idx != -1) {
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
	 * @param chart
	 *            the uploaded chart
	 * @return a not null list of String representing the customizer classes loaded
	 *         by a {@link ProxyChartCustomizer} from the properties of the element
	 */
	public List<String> getSubCustmizersClass(JRChart chart) {
		List<String> result = new ArrayList<>();
		List<JRPropertiesUtil.PropertySuffix> properties = JRPropertiesUtil.getProperties(chart.getPropertiesMap(),
				NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX);
		// We look for all the class properties...
		for (JRPropertiesUtil.PropertySuffix prop : properties) {
			String customizerClass = prop.getValue();
			result.add(customizerClass);
		}
		return result;
	}

}
