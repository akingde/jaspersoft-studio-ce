/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.action;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TableViewer;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.restv2.WsTypes;
import com.jaspersoft.studio.server.publish.ResourcePublishMethod;
import com.jaspersoft.studio.server.wizard.find.FindResourceJob;

public class ReferenceResourceAction extends Action {
	private AMResource mres;
	private TableViewer tableViewer;

	public ReferenceResourceAction(TableViewer tableViewer) {
		super();
		setText(Messages.ReferenceResourceAction_0);
		this.tableViewer = tableViewer;
	}

	public boolean calculateEnabled(AMResource mres) {
		this.mres = mres;
		return mres.getPublishOptions().getPublishMethod() != ResourcePublishMethod.REFERENCE;
	}

	@Override
	public void run() {
		MServerProfile msp = ServerManager.getMServerProfileCopy((MServerProfile) mres.getRoot());
		if (mres.isSupported(Feature.SEARCHREPOSITORY)) {
			ResourceDescriptor rd = FindResourceJob.doFindResource(msp, new String[] { WsTypes.INST().toRestType(mres.getValue().getWsType()) }, null);
			if (rd != null) {
				mres.getPublishOptions().setReferencedResource(rd);
				mres.getPublishOptions().setPublishMethod(ResourcePublishMethod.REFERENCE);
			}
		} else {
			RepositoryDialog rd = new RepositoryDialog(UIUtils.getShell(), msp) {
				@Override
				public boolean isResourceCompatible(AMResource r) {
					return r.getValue().getWsType().equals(mres.getValue().getWsType());
				}
			};
			if (rd.open() == Dialog.OK) {
				AMResource rs = rd.getResource();
				mres.getPublishOptions().setReferencedResource(rs.getValue());
				mres.getPublishOptions().setPublishMethod(ResourcePublishMethod.REFERENCE);
			}
		}
		tableViewer.refresh();
	}
}
