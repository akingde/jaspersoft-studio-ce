package com.jaspersoft.studio.server.publish.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.publish.ResourcePublishMethod;

public class ResourceToFolderAction extends Action {
	private MResource mres;
	private TableViewer tableViewer;

	public ResourceToFolderAction(TableViewer tableViewer) {
		super();
		setText("Save To Folder");
		this.tableViewer = tableViewer;
	}

	public boolean calculateEnabled(MResource mres) {
		this.mres = mres;
		return mres.getPublishOptions().getPublishMethod() != ResourcePublishMethod.RESOURCE;
	}

	@Override
	public void run() {
		MServerProfile msp = ServerManager.getMServerProfileCopy((MServerProfile) mres.getRoot());
		RepositoryDialog rd = new RepositoryDialog(Display.getDefault().getActiveShell(), msp) {
			@Override
			public boolean isResourceCompatible(MResource r) {
				return r.getValue().getWsType().equals(ResourceDescriptor.TYPE_FOLDER);
			}
		};
		if (rd.open() == Dialog.OK) {
			MResource rs = rd.getResource();
			mres.getPublishOptions().setReferencedResource(rs.getValue());
			mres.getPublishOptions().setPublishMethod(ResourcePublishMethod.RESOURCE);
		}
		tableViewer.refresh();
	}
}
