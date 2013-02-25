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
package com.jaspersoft.studio.server.action.server;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.MServers;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.wizard.ServerProfileWizard;
import com.jaspersoft.studio.server.wizard.ServerProfileWizardDialog;

public class CreateServerAction extends Action implements ICheatSheetAction {
	public static final String ID = "createServerAction"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public CreateServerAction() {
		this(null);
	}

	public CreateServerAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText(Messages.CreateServerAction_title);
		setDescription(Messages.CreateServerAction_desc);
		setToolTipText(Messages.CreateServerAction_desc);
		setImageDescriptor(
				Activator.getDefault().getImageDescriptor("icons/server--plus.png")); //$NON-NLS-1$
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled();
	}

	@Override
	public void run() {
		MRoot root = (MRoot) treeViewer.getInput();
		List<INode> lst = root.getChildren();
		for (INode n : lst) {
			if (n instanceof MServers) {
				ServerProfile srv = new ServerProfile();
				srv.setName(Messages.CreateServerAction_name);
				srv.setUrl("http://localhost:8080/jasperserver/services/repository"); //$NON-NLS-1$
				srv.setUser("username"); //$NON-NLS-1$
				ServerProfileWizard wizard = new ServerProfileWizard(
						new MServerProfile(null, srv));
				ServerProfileWizardDialog dialog = new ServerProfileWizardDialog(
						Display.getDefault().getActiveShell(), wizard);
				wizard.bindTestButton(dialog);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					mservprof = wizard.getServerProfile();
					MServerProfile newprofile = new MServerProfile(
							(MServers) n, mservprof.getValue());
					for (INode cn : mservprof.getChildren())
						newprofile.addChild((ANode) cn);
					newprofile.setWsClient(mservprof.getWsClient());
					ServerManager.addServerProfile(newprofile);

					EditServerAction.fillServerProfile(newprofile, treeViewer);
				}
				break;
			}
		}

	}

	private MServerProfile mservprof;

	public MServerProfile getNewServer() {
		return mservprof;
	}

	public void run(String[] params, ICheatSheetManager manager) {
		run();
		notifyResult(true);
	}

}
