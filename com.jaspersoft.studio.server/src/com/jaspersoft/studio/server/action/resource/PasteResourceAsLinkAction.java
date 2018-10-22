/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.action.resource;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;

public class PasteResourceAsLinkAction extends PasteResourceAction {
	public static final String PASTE_ASLINK = "PASTEASLINK"; //$NON-NLS-1$

	public PasteResourceAsLinkAction(TreeViewer treeViewer) {
		super(treeViewer);
		setId(PASTE_ASLINK);
		setText(Messages.PasteResourceAsLinkAction_1);
		setToolTipText(Messages.PasteResourceAsLinkAction_2);
	}

	@Override
	public boolean isEnabled() {
		boolean res = false;
		ANode n = getSelected();
		if (n instanceof MReportUnit) {
			res = super.isEnabled();
			if (res && contents != null && contents instanceof List<?>) {
				List<?> list = (List<?>) contents;
				for (Object obj : list)
					if (obj instanceof AMResource && obj instanceof ICopyable) {
						ICopyable c = (ICopyable) obj;
						if (c.isCopyable2(n) == ICopyable.RESULT.COPYABLE) {
							if (((AMResource) obj).isCut())
								res = false;
							else
								res = true;
							break;
						} else
							return false;
					}
			}
		}
		return res;
	}

	@Override
	protected void saveToReportUnit(IProgressMonitor monitor, AMResource parent, IConnection ws,
			ResourceDescriptor origin, boolean doSave) throws Exception {
		ResourceDescriptor prd = parent.getValue();
		ResourceDescriptor rd = null;
		rd = new ResourceDescriptor();
		rd.setName(origin.getName());
		rd.setLabel(origin.getLabel());
		rd.setDescription(origin.getDescription());
		rd.setIsNew(true);
		rd.setIsReference(true);
		if (parent.getWsClient().isSupported(Feature.SEARCHREPOSITORY)) {
			rd.setParentFolder(prd.getParentFolder() + "/" + prd.getName() + "_files");
			rd.setUriString(origin.getUriString());
			rd.setWsType(origin.getWsType());
		} else {
			rd.setReferenceUri(origin.getUriString());
			rd.setParentFolder(prd.getParentFolder() + "/" + prd.getName() + "_files"); //$NON-NLS-1$ //$NON-NLS-2$
			if (ResourceFactory.isFileResourceType(origin))
				rd.setWsType(ResourceDescriptor.TYPE_REFERENCE);
			else
				rd.setWsType(origin.getWsType());
			rd.setUriString(prd.getParentFolder() + "/" + prd.getName() + "_files/" + prd.getName()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		prd.getChildren().add(rd);
		if (doSave)
			ws.addOrModifyResource(monitor, prd, null);
	}

}
