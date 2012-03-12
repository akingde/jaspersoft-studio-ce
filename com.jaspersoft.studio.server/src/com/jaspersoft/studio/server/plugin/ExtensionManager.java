/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
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

	public MResource getResource(ANode parent, ResourceDescriptor resource,
			int index) {
		for (IResourceFactory r : resources) {
			MResource mr = r.getResource(parent, resource, index);
			if (mr != null)
				return mr;
		}
		return null;
	}

	public IWizardPage getResourcePage(ANode parent, MResource resource) {
		for (IResourceFactory r : resources) {
			IWizardPage mr = r.getResourcePage(parent, resource);
			if (mr != null)
				return mr;
		}
		return null;
	}

	public ANode createNewResource(MRoot root, ANode parent) {
		for (IResourceFactory r : resources) {
			ANode mr = r.createNewResource(root, parent);
			if (mr != null)
				return mr;
		}
		return null;
	}

}
