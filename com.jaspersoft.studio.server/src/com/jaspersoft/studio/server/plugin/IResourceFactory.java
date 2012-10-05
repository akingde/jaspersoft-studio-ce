package com.jaspersoft.studio.server.plugin;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.server.model.MResource;

public interface IResourceFactory {
	public MResource getResource(ANode parent, ResourceDescriptor resource,
			int index);

	public IWizardPage getResourcePage(ANode parent, MResource resource);

	public ANode createNewResource(MRoot root, ANode parent);
}
