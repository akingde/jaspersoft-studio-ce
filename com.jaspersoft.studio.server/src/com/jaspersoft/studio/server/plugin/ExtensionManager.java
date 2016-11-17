/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.plugin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.editor.input.IInputControls;
import com.jaspersoft.studio.server.model.AMJrxmlContainer;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.restv2.ARestV2Connection;
import com.jaspersoft.studio.server.protocol.restv2.WsTypes;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExtensionManager {
	private List<IResourceFactory> resources = new ArrayList<IResourceFactory>();
	private List<IConnection> protocols = new ArrayList<IConnection>();

	private List<IInputControls> inputcontrol = new ArrayList<IInputControls>();

	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("com.jaspersoft.studio.server", "resources"); //$NON-NLS-1$ //$NON-NLS-2$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IResourceFactory)
					resources.add((IResourceFactory) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
		config = Platform.getExtensionRegistry().getConfigurationElementsFor("com.jaspersoft.studio.server", //$NON-NLS-1$
				"protocols"); //$NON-NLS-1$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IConnection)
					protocols.add((IConnection) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
		config = Platform.getExtensionRegistry().getConfigurationElementsFor("com.jaspersoft.studio.server", //$NON-NLS-1$
				"inputcontrol"); //$NON-NLS-1$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IInputControls)
					inputcontrol.add((IInputControls) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private Map<JasperReportsConfiguration, List<IPublishContributor>> publisher = new HashMap<JasperReportsConfiguration, List<IPublishContributor>>();

	public List<IPublishContributor> getPublisher(JasperReportsConfiguration jrConfig) {
		List<IPublishContributor> p = publisher.get(jrConfig);
		if (p == null) {
			IConfigurationElement[] config = Platform.getExtensionRegistry()
					.getConfigurationElementsFor("com.jaspersoft.studio.server", "publisher"); //$NON-NLS-1$ //$NON-NLS-2$
			p = new ArrayList<IPublishContributor>();
			for (IConfigurationElement e : config) {
				try {
					Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
					if (o instanceof IPublishContributor) {
						((IPublishContributor) o).init(jrConfig);
						p.add((IPublishContributor) o);
					}

				} catch (CoreException ex) {
					System.out.println(ex.getMessage());
				}
			}
			publisher.put(jrConfig, p);
		}
		return p;
	}

	public void publishJrxml(JasperReportsConfiguration jrConfig, AMJrxmlContainer mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file, String version) throws Exception {
		for (IPublishContributor r : getPublisher(jrConfig))
			r.publishJrxml(mrunit, monitor, jasper, fileset, file, version);
	}

	public void publishComponent(JasperReportsConfiguration jrConfig, AMJrxmlContainer mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file, JRDesignElement ele, String version)
			throws Exception {
		for (IPublishContributor r : getPublisher(jrConfig))
			r.publishComponent(mrunit, monitor, jasper, fileset, file, ele, version);
	}

	public void publishParameters(JasperReportsConfiguration jrConfig, MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper) throws Exception {
		for (IPublishContributor r : getPublisher(jrConfig))
			r.publishParameters(mrunit, monitor, jasper);
	}

	public AMResource getResource(ANode parent, ResourceDescriptor resource, int index) {
		for (IResourceFactory r : resources) {
			AMResource mr = r.getResource(parent, resource, index);
			if (mr != null)
				return mr;
		}
		return null;
	}

	public IWizardPage[] getResourcePage(ANode parent, AMResource resource) {
		for (IResourceFactory r : resources) {
			IWizardPage[] mr = r.getResourcePage(parent, resource);
			if (mr != null)
				return mr;
		}
		return null;
	}

	public List<IConnection> getProtocols() {
		List<IConnection> cons = new ArrayList<IConnection>();
		for (IConnection p : protocols) {
			try {
				if (p == null)
					continue;
				cons.add(p.getClass().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return cons;
	}

	public ANode createNewResource(ANode root, ANode parent) {
		for (IResourceFactory r : resources)
			r.createNewResource(root, parent);
		return null;
	}

	public ANode createNewDatasource(ANode root, ANode parent) {
		for (IResourceFactory r : resources)
			r.createNewDatasource(root, parent);
		return null;
	}

	public void initWsTypes(WsTypes wsType) {
		for (IResourceFactory r : resources)
			r.initWsTypes(wsType);
	}

	public void initContainers(Set<Class<? extends ClientResource<?>>> containers) {
		for (IResourceFactory r : resources)
			r.initContainers(containers);
	}

	public ResourceDescriptor getRD(ARestV2Connection rc, ClientResource<?> cr, ResourceDescriptor rd)
			throws ParseException {
		for (IResourceFactory r : resources) {
			ResourceDescriptor nrd = r.getRD(rc, cr, rd);
			if (nrd != null)
				return nrd;
		}
		return null;
	}

	public ClientResource<?> getResource(ARestV2Connection rc, ClientResource<?> cr, ResourceDescriptor rd)
			throws ParseException {
		for (IResourceFactory r : resources) {
			ClientResource<?> nrd = r.getResource(rc, cr, rd);
			if (nrd != null)
				return nrd;
		}
		return null;
	}

	public List<IInputControls> getInstance() {
		List<IInputControls> controls = new ArrayList<IInputControls>();
		for (IInputControls r : inputcontrol)
			controls.add(r.getInstance());
		return controls;
	}

}
