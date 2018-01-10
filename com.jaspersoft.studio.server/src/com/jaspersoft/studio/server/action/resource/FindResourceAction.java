/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.action.resource;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.ServerProvider;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.wizard.find.FindResourceJob;

public class FindResourceAction extends Action {
	public static final String ID = "findResourceAction"; //$NON-NLS-1$
	private TreeViewer treeViewer;
	private ServerProvider sp;

	public FindResourceAction(TreeViewer treeViewer, ServerProvider sp) {
		super();
		this.sp = sp;
		setId(ID);
		setText(Messages.FindResourceAction_0);
		setDescription(Messages.FindResourceAction_1);
		setToolTipText(getDescription());
		setImageDescriptor(JaspersoftStudioPlugin.getInstance()
				.getImageDescriptor("icons/find_obj.gif")); //$NON-NLS-1$
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		boolean en = false;
		TreeSelection selection = (TreeSelection) treeViewer.getSelection();
		Object firstElement = selection.getFirstElement();
		if (firstElement != null) {
			MServerProfile msp = null;
			if (firstElement instanceof MServerProfile)
				msp = (MServerProfile) firstElement;
			else if (firstElement instanceof AMResource) {
				INode n = ((AMResource) firstElement).getRoot();
				if (n instanceof MServerProfile)
					msp = (MServerProfile) n;
			}
			try {
				if (msp != null) {
					IConnection c = msp.getWsClient();
					if (c != null)
						en = msp != null
								&& c.isSupported(Feature.SEARCHREPOSITORY);
				}
			} catch (Exception e) {
				en = false;
			}
		}
		setEnabled(en);
		return en;
	}

	@Override
	public void run() {
		FindResourceJob.doFindResource(sp, treeViewer);
	}

}
