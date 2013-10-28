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
package com.jaspersoft.studio.server.action.resource;

import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;

public class PasteResourceAsLinkAction extends PasteResourceAction {
	public static final String PASTE_ASLINK = "PASTEASLINK";

	public PasteResourceAsLinkAction(TreeViewer treeViewer) {
		super(treeViewer);
		setId(PASTE_ASLINK);
		setText("Paste As Link");
		setToolTipText("Paste As Link");
	}

	@Override
	public boolean isEnabled() {
		ANode n = getSelected();
		if (n instanceof MReportUnit) {
			if (super.isEnabled() && contents != null && contents instanceof List<?>) {
				List<?> list = (List<?>) contents;
				for (Object obj : list)
					if (obj instanceof MResource && obj instanceof ICopyable) {
						ICopyable c = (ICopyable) obj;
						if (c.isCopyable2(n))
							if (((MResource) obj).isCut())
								return false;
							else
								return true;

					}
			}
		}
		return false;
	}

	protected ResourceDescriptor doPasteIntoReportUnit(ANode parent, MResource m, ResourceDescriptor prd, ResourceDescriptor origin) {
		if (m.isCut())
			return doPasteIntoReportUnit(parent, m, prd, origin);

		ResourceDescriptor runit = (ResourceDescriptor) parent.getValue();
		ResourceDescriptor norigin = new ResourceDescriptor();
		norigin.setName(origin.getName());
		norigin.setLabel(origin.getLabel());
		norigin.setDescription(origin.getDescription());
		norigin.setIsNew(true);
		norigin.setIsReference(true);
		norigin.setReferenceUri(origin.getUriString());
		norigin.setParentFolder(runit.getParentFolder() + "/" + runit.getName() + "_files");
		if (ResourceFactory.isFileResourceType(origin))
			norigin.setWsType(ResourceDescriptor.TYPE_REFERENCE);
		else
			norigin.setWsType(origin.getWsType());
		norigin.setUriString(runit.getParentFolder() + "/" + runit.getName() + "_files/" + prd.getName());

		return norigin;
	}
}
