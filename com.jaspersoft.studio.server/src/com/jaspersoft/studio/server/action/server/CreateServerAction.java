/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.action.server;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
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
import com.jaspersoft.studio.statistics.UsageStatisticsIDs;
import com.jaspersoft.studio.utils.BrandingInfo;

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
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/server--plus.png")); //$NON-NLS-1$
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
				srv.setName(getJRSProposedName());
				srv.setUrl(getJRSProposedURL());
				srv.setUser("username"); //$NON-NLS-1$
				srv.setSupportsDateRanges(true);
				ServerProfileWizard wizard = new ServerProfileWizard(new MServerProfile(null, srv));
				ServerProfileWizardDialog dialog = new ServerProfileWizardDialog(UIUtils.getShell(), wizard);
				wizard.bindTestButton(dialog);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					mservprof = wizard.getServerProfile();
					MServers rootServers =  (MServers)n;
					MServerProfile newprofile = new MServerProfile(rootServers, mservprof.getValue());
					newprofile.setWsClient(mservprof.getWsClient());
					ServerManager.addServerProfile(newprofile);
					EditServerAction.fillServerProfile(newprofile, treeViewer);
					JaspersoftStudioPlugin.getInstance().getUsageManager().audit(UsageStatisticsIDs.SERVER_CREATION, UsageStatisticsIDs.CATEGORY_SERVER);
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

	private String getJRSProposedURL() {
		if (BrandingInfo.isProfessionalEdition()) {
			return "http://localhost:8080/jasperserver-pro/"; //$NON-NLS-1$
		} else {
			return "http://localhost:8080/jasperserver/"; //$NON-NLS-1$
		}
	}

	private String getJRSProposedName() {
		if (BrandingInfo.isProfessionalEdition()) {
			return Messages.CreateServerAction_name + " Pro"; //$NON-NLS-1$
		} else {
			return Messages.CreateServerAction_name;
		}
	}

}
