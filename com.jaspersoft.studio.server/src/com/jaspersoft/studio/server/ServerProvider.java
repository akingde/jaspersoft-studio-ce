/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.server.action.CreateServerAction;
import com.jaspersoft.studio.server.action.DeleteServerAction;
import com.jaspersoft.studio.server.action.DuplicateServerAction;
import com.jaspersoft.studio.server.action.EditServerAction;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.MServers;
import com.jaspersoft.studio.utils.UIUtils;

public class ServerProvider implements IRepositoryViewProvider {
	private CreateServerAction createServerAction;
	private EditServerAction editServerAction;
	private DeleteServerAction deleteServerAction;
	private DuplicateServerAction duplicateServerAction;

	public Action[] getActions(TreeViewer treeViewer) {
		createActions(treeViewer);
		return new Action[] { createServerAction };
	}

	private void createActions(TreeViewer treeViewer) {
		if (createServerAction == null)
			createServerAction = new CreateServerAction(treeViewer);
		if (editServerAction == null)
			editServerAction = new EditServerAction(treeViewer);
		if (deleteServerAction == null)
			deleteServerAction = new DeleteServerAction(treeViewer);
		if (duplicateServerAction == null)
			duplicateServerAction = new DuplicateServerAction(treeViewer);
	}

	public List<IAction> fillContextMenu(TreeViewer treeViewer, ANode node) {
		createActions(treeViewer);
		List<IAction> lst = new ArrayList<IAction>();
		if (node instanceof MServers) {
			if (createServerAction.isEnabled())
				lst.add(createServerAction);
		} else if (node instanceof MServerProfile) {
			if (editServerAction.isEnabled())
				lst.add(editServerAction);
			if (duplicateServerAction.isEnabled())
				lst.add(duplicateServerAction);

			if (deleteServerAction.isEnabled())
				lst.add(deleteServerAction);
		}
		return lst;
	}

	public void hookKeyEvent(KeyEvent event) {
		if (event.character == SWT.DEL && event.stateMask == 0
				&& deleteServerAction.isEnabled()) {
			deleteServerAction.run();
		}
	}

	public void doubleClick(TreeViewer treeViewer) {
		editServerAction.run();
	}

	public ANode getNode(ANode root) {
		MServers servers = new MServers(root);

		ServerManager.loadServerProfiles(servers);

		// get from somewere the nodes
		// ServerProfile sp = new ServerProfile();
		// sp.setName("Server 1");
		// sp.setUrl("http://localhost:9090/jasperserver-pro/services/repository");
		// sp.setUser("jasperadmin|organization_1");
		// sp.setPass("jasperadmin");
		// MServerProfile srv = new MServerProfile(servers, sp);
		//
		// try {
		// listFolder(srv, connect(sp), "/");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// ServerProfile sp1 = new ServerProfile();
		// sp1.setName("Server2");
		// new MServerProfile(servers, sp1);
		// ServerProfile sp2 = new ServerProfile();
		// sp2.setName("Server3");
		// new MServerProfile(servers, sp2);

		return servers;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		ServerManager.getPropertyChangeSupport().addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		ServerManager.getPropertyChangeSupport().removePropertyChangeListener(
				pcl);
	}

	public void handleTreeEvent(TreeExpansionEvent event) {
		if (event.getElement() instanceof MServerProfile) {

		} else if (event.getElement() instanceof MResource) {
			lazyLoadResource(event);
		}

	}

	private void lazyLoadResource(final TreeExpansionEvent event) {
		// Display.getDefault().asyncExec(new Runnable() {
		//
		// public void run() {
		// MResource r = (MResource) event.getElement();
		// for (INode cr : r.getChildren()) {
		// if (!(cr instanceof MFolder)
		// && !(cr instanceof MReportUnit)
		// && cr instanceof MResource) {
		// MResource res = (MResource) cr;
		// try {
		// cr.setValue(WSClientHelper.getResource(res,
		// res.getValue()));
		// } catch (Exception e) {
		// UIUtils.showError(e);
		// }
		// }
		// }
		//
		// }
		// });

	}
}
