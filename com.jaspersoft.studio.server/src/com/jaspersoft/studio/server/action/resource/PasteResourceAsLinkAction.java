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

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.protocol.IConnection;

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

	@Override
	protected void saveToReportUnit(IProgressMonitor monitor, ANode parent, IConnection ws, ResourceDescriptor origin) throws IOException, Exception {
		ResourceDescriptor prd = (ResourceDescriptor) parent.getValue();
		ResourceDescriptor rd = null;
		rd = new ResourceDescriptor();
		rd.setName(origin.getName());
		rd.setLabel(origin.getLabel());
		rd.setDescription(origin.getDescription());
		rd.setIsNew(true);
		rd.setIsReference(true);
		rd.setReferenceUri(origin.getUriString());
		rd.setParentFolder(prd.getParentFolder() + "/" + prd.getName() + "_files");
		if (ResourceFactory.isFileResourceType(origin))
			rd.setWsType(ResourceDescriptor.TYPE_REFERENCE);
		else
			rd.setWsType(origin.getWsType());
		rd.setUriString(prd.getParentFolder() + "/" + prd.getName() + "_files/" + prd.getName());

		prd.getChildren().add(rd);
		ws.addOrModifyResource(monitor, prd, null);
	}

}
