/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.plugin;

import java.util.Set;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.server.model.AMJrxmlContainer;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Interface used to contribute resources during publishing of jrxml to jasper
 * reports server
 * 
 * @author Veaceslav Chicu (schicu@tibco.com)
 * 
 */
public interface IPublishContributor {
	/**
	 * Initialization the publisher
	 * 
	 * @param jrConfig
	 *            context
	 * @param version
	 *            jrxml version
	 */
	public void init(JasperReportsConfiguration jrConfig);

	/**
	 * this is the place to setup more properties or contribute some resources
	 * to the publishing of jrxml to jasperreports server
	 * 
	 * @param mrunit
	 *            report unit to which resources are added
	 * @param monitor
	 *            progress monitor, please check isCanceled and interrupt as
	 *            soon as possible
	 * @param jasper
	 *            jasper reports that will be published to the server, you can
	 *            modify it, it's a copy of the original jasper report
	 * @param fileset
	 *            set of files, if file is already there you should not process
	 *            it, this help to break loops
	 * @param file
	 *            jarper reports file
	 * @param version
	 *            jasper reports version to save
	 * @throws Exception
	 */
	public void publishJrxml(AMJrxmlContainer mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file, String version)
			throws Exception;

	/**
	 * if a component has some resources to contribute, this is the place to do
	 * it, this method is called for every component inside the report
	 * 
	 * @param mrunit
	 *            report unit to which resources are added
	 * @param monitor
	 *            progress monitor, please check isCanceled and interrupt as
	 *            soon as possible
	 * @param jasper
	 *            jasper reports that will be published to the server, you can
	 *            modify it, it's a copy of the original jasper report
	 * @param fileset
	 *            set of files, if file is already there you should not process
	 *            it, this help to break loops
	 * @param file
	 *            jarper reports file
	 * @param element
	 *            current jasper reports component
	 * @param version
	 *            jasper reports version to save
	 * @throws Exception
	 */
	public void publishComponent(AMJrxmlContainer mrunit,
			IProgressMonitor monitor, JasperDesign jasper, Set<String> fileset,
			IFile file, JRDesignElement element, String version)
			throws Exception;

	/**
	 * this is the place to contribute input controls, or add some custom
	 * properties
	 * 
	 * @param mrunit
	 *            report unit to which resources are added
	 * @param monitor
	 *            progress monitor, please check isCanceled and interrupt as
	 *            soon as possible
	 * @param jasper
	 *            jasper reports that will be published to the server, you can
	 *            modify it, it's a copy of the original jasper report
	 * @throws Exception
	 */
	public void publishParameters(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper) throws Exception;
}
