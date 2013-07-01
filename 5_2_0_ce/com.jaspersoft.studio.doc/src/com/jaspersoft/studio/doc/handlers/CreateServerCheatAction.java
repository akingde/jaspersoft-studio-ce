package com.jaspersoft.studio.doc.handlers;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.action.server.EditServerAction;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.MServers;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.wizard.ServerProfileWizard;
import com.jaspersoft.studio.server.wizard.ServerProfileWizardDialog;

public class CreateServerCheatAction extends AsyncAction {
	
	private ServerProfileWizard wizardDialog;
	
	private INode selectedElement;

	@Override
	protected void loadDialog() {
		TreeViewer treeViewer = HandlersUtil.getRepositoryView().getTreeViewer();
		MRoot root = (MRoot) treeViewer.getInput();
		List<INode> lst = root.getChildren();
		for (INode n : lst) {
			if (n instanceof MServers) {
				ServerProfile srv = new ServerProfile();
				srv.setName("JasperReports Server");
				srv.setUrl("http://localhost:8080/jasperserver/services/repository");
				srv.setUser("username");
				ServerProfileWizard wizard = new ServerProfileWizard(
						new MServerProfile(null, srv));
				ServerProfileWizardDialog dialog = new ServerProfileWizardDialog(
						Display.getDefault().getActiveShell(), wizard);
				wizard.bindTestButton(dialog);			
				dialog.create();
				dialogToOpen = dialog;
				wizardDialog = wizard;
				selectedElement = n;
				break;
			}
		}
	}
	
	public void run() {
		TreeViewer treeViewer = HandlersUtil.getRepositoryView().getTreeViewer();
		MRoot root = (MRoot) treeViewer.getInput();
		List<INode> lst = root.getChildren();
		for (INode n : lst) {
			if (n instanceof MServers) {
				ServerProfile srv = new ServerProfile();
				srv.setName("JasperReports Server");
				srv.setUrl("http://localhost:8080/jasperserver/services/repository");
				srv.setUser("username");
				ServerProfileWizard wizard = new ServerProfileWizard(
						new MServerProfile(null, srv));
				ServerProfileWizardDialog dialog = new ServerProfileWizardDialog(
						Display.getDefault().getActiveShell(), wizard);
				wizard.bindTestButton(dialog);			
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					MServerProfile mservprof = wizard.getServerProfile();
					MServerProfile newprofile = new MServerProfile(
							(MServers) n, mservprof.getValue());
					for (INode cn : mservprof.getChildren())
						newprofile.addChild((ANode) cn);
					newprofile.setWsClient(mservprof.getWsClient());
					ServerManager.addServerProfile(newprofile);
					EditServerAction.fillServerProfile(newprofile, treeViewer);
				}
			}
		}
	}
	
	@Override
	public void doAction() {
		TreeViewer treeViewer = HandlersUtil.getRepositoryView().getTreeViewer();
		if (dialogToOpen.open() == Dialog.OK) {
			MServerProfile mservprof = wizardDialog.getServerProfile();
			MServerProfile newprofile = new MServerProfile(
					(MServers) selectedElement, mservprof.getValue());
			for (INode cn : mservprof.getChildren())
				newprofile.addChild((ANode) cn);
			newprofile.setWsClient(mservprof.getWsClient());
			ServerManager.addServerProfile(newprofile);
			EditServerAction.fillServerProfile(newprofile, treeViewer);
		}
	}
}
