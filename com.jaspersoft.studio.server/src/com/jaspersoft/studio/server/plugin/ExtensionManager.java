/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExtensionManager {
	private List<IResourceFactory> resources = new ArrayList<IResourceFactory>();

	private List<IPublishContributor> publisher = new ArrayList<IPublishContributor>();

	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(
						"com.jaspersoft.studio.server", "resources"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IResourceFactory)
					resources.add((IResourceFactory) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}

		config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"com.jaspersoft.studio.server", "publisher"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IPublishContributor)
					publisher.add((IPublishContributor) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void publishJrxml(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file,
			String version, JasperReportsConfiguration jrConfig)
			throws Exception {
		for (IPublishContributor r : publisher)
			r.publishJrxml(mrunit, monitor, jasper, fileset, file, version,
					jrConfig);
	}

	public void publishParameters(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, JasperReportsConfiguration jrConfig)
			throws Exception {
		for (IPublishContributor r : publisher)
			r.publishParameters(mrunit, monitor, jasper, jrConfig);
	}

	public MResource getResource(ANode parent, ResourceDescriptor resource,
			int index) {
		for (IResourceFactory r : resources) {
			MResource mr = r.getResource(parent, resource, index);
			if (mr != null)
				return mr;
		}
		return null;
	}

	public IWizardPage[] getResourcePage(ANode parent, MResource resource) {
		for (IResourceFactory r : resources) {
			IWizardPage[] mr = r.getResourcePage(parent, resource);
			if (mr != null)
				return mr;
		}
		return null;
	}

	public ANode createNewResource(MRoot root, ANode parent) {
		for (IResourceFactory r : resources)
			r.createNewResource(root, parent);
		return null;
	}

	public ANode createNewDatasource(MRoot root, ANode parent) {
		for (IResourceFactory r : resources)
			r.createNewDatasource(root, parent);
		return null;
	}
}
