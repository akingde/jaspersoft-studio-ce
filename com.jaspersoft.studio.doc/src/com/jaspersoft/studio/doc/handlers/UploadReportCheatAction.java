package com.jaspersoft.studio.doc.handlers;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;

import com.jaspersoft.studio.doc.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.publish.wizard.Publish2ServerWizard;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * This class start the upload of a report into an instance of JasperReport Server
 * 
 * @author Orlandin Marco
 *
 */
public class UploadReportCheatAction extends Action {
	
	private ANode cloneServer(ANode server, ANode parent){
		MServerProfile fakeServer = new MServerProfile(parent, (ServerProfile)server.getValue());
		for(INode element : server.getChildren())
			fakeServer.addChild((ANode)element);
		return fakeServer;
	}
	
	@Override
	public void run() {
		ANode rootElement = HandlersUtil.getRootElement();
		if (rootElement != null){
			JasperDesign design = rootElement.getJasperDesign();
			JasperReportsConfiguration config = rootElement.getJasperConfiguration();
			ANode servers = HandlersUtil.getServers();
			if (servers != null && servers.getChildren().size()>0){
				//Create a fake root to show only the server connection in the list
				MRoot fakeRoot = new MRoot(null,design);
				for(INode server : servers.getChildren())
					cloneServer((ANode)server, fakeRoot);
				Publish2ServerWizard wizard = new Publish2ServerWizard(fakeRoot , design, config,1);
				WizardDialog dialogToOpen = new WizardDialog(UIUtils.getShell(), wizard);
				dialogToOpen.create();
				dialogToOpen.open();
			} else MessageDialog.openWarning(UIUtils.getShell(), Messages.UploadReportCheatAction_no_server_title, Messages.UploadReportCheatAction_no_server_warning);
		} else MessageDialog.openWarning(UIUtils.getShell(), Messages.UploadReportCheatAction_warning_title, Messages.UploadReportCheatAction_warning_message);
	}
	
}
