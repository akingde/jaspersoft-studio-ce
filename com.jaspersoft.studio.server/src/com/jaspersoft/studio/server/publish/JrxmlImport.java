package com.jaspersoft.studio.server.publish;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class JrxmlImport extends Action {
	private static final String ID = "PUBLISHJRXML";
	private JasperDesign jDesign;

	public JrxmlImport() {
		super();
		setId(ID);
		setText("Publish Report to JasperServer");
		setToolTipText("Publish Report to JasperServer");
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
	}

	public void setJasperDesign(JasperDesign jDesign) {
		this.jDesign = jDesign;
	}

	@Override
	public void run() {
		MServerProfile server = null;
		String prop = jDesign.getProperty(JrxmlExporter.PROP_SERVERURL);
		if (prop == null) {
			// ask for server name
		}
		prop = jDesign.getProperty(JrxmlExporter.PROP_REPORTUNIT);
		if (prop == null) {
			// ok, we don't know reportunit, so ask
			// if new create one
		}

		// put report in the unit
		// put images
		// put subreports
		// create input controls
	}

}
