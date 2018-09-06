/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.action.resource;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.jasperserver.dto.authority.ClientRole;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.wizard.exp.ExportMetadataWizard;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Action for importing the selected DataSource in the JRS tree as Data Adapter
 * in JSS.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ExportMetadataAction extends Action {
	public static final String ID = "IMPORT_METADATA_IN_JSS"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public ExportMetadataAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText(Messages.ExportMetadataAction_0);
		setToolTipText(Messages.ExportMetadataAction_1);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(JaspersoftStudioPlugin.PLUGIN_ID,
				"/icons/resources/eclipse/etool16/import_wiz.gif")); //$NON-NLS-1$
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		boolean en = false;
		TreeSelection selection = (TreeSelection) treeViewer.getSelection();
		Object firstElement = selection.getFirstElement();
		if (firstElement != null) {
			MServerProfile msp = null;
			INode n = null;
			if (firstElement instanceof MServerProfile)
				msp = (MServerProfile) firstElement;
			else if (firstElement instanceof AMResource) {
				n = ((AMResource) firstElement).getRoot();
				if (n instanceof MServerProfile)
					msp = (MServerProfile) n;
				if (((AMResource) firstElement).getParent() instanceof MReportUnit) {
					setEnabled(false);
					return false;
				}
			}
			try {
				IConnection c = msp.getWsClient();
				if (c != null) {
					en = msp != null && c.isSupported(Feature.EXPORTMETADATA);
					if (en && c.getServerProfile().getClientUser() != null) {
						for (ClientRole r : c.getServerProfile().getClientUser().getRoleSet()) {
							if (r.getName().equals("ROLE_SUPERUSER")) {
								setEnabled(true);
								return true;
							}
						}
					}
					setEnabled(false);
					return false;
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
		StructuredSelection selection = (StructuredSelection) treeViewer.getSelection();

		ExportMetadataWizard wizard = new ExportMetadataWizard(selection);
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
		}
	}

}
