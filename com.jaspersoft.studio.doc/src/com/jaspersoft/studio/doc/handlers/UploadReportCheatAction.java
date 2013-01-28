package com.jaspersoft.studio.doc.handlers;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;

import com.jaspersoft.studio.doc.messages.Messages;
import com.jaspersoft.studio.model.ANode;
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
	
	@Override
	public void run() {
		ANode rootElement = HandlersUtil.getRootElement();
		if (rootElement != null){
			JasperDesign design = rootElement.getJasperDesign();
			JasperReportsConfiguration config = rootElement.getJasperConfiguration();
			Publish2ServerWizard wizard = new Publish2ServerWizard(null, design, config,1);
			WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
			dialog.create();
			dialog.open();
		} else MessageDialog.openWarning(UIUtils.getShell(), Messages.UploadReportCheatAction_warning_title, Messages.UploadReportCheatAction_warning_message);
	};
	
}
