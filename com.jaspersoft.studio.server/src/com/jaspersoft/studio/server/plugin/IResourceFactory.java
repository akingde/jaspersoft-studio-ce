/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.plugin;

import java.text.ParseException;
import java.util.Set;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.protocol.restv2.ARestV2Connection;
import com.jaspersoft.studio.server.protocol.restv2.WsTypes;

public interface IResourceFactory {
	public AMResource getResource(ANode parent, ResourceDescriptor resource, int index);

	public IWizardPage[] getResourcePage(ANode parent, AMResource resource);

	public ANode createNewResource(ANode root, ANode parent);

	public ANode createNewDatasource(ANode root, ANode parent);

	public void initWsTypes(WsTypes wsType);

	public ResourceDescriptor getRD(ARestV2Connection rc, ClientResource<?> cr, ResourceDescriptor rd) throws ParseException;

	public ClientResource<?> getResource(ARestV2Connection rc, ClientResource<?> cr, ResourceDescriptor rd) throws ParseException;

	public void initContainers(Set<Class<? extends ClientResource<?>>> containers);
}
