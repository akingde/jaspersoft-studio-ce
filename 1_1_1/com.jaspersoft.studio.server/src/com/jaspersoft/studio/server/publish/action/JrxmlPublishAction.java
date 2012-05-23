/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.publish.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.plugin.AContributorAction;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.publish.FindReportUnit;
import com.jaspersoft.studio.server.publish.FindResources;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.wizard.Publish2ServerWizard;
import com.jaspersoft.studio.utils.FileUtils;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JrxmlPublishAction extends AContributorAction {
	private static final String ID = "PUBLISHJRXML";
	public static final String KEY_PUBLISH2JSSWIZARD_SILENT = "PUBLISH2JSSWIZARD_SILENT";
	public static final String KEY_PUBLISH2JSS_DATA = "PUBLISH2JSS_DATA";
	private MReportUnit mrunit;

	public JrxmlPublishAction() {
		super(ID, "Publish Report to JasperServer");
		setToolTipText("Publish Report to JasperServer");
		ImageDescriptor icon16 = Activator
				.getImageDescriptor("icons/server--upload.png");
		setImageDescriptor(icon16);
		setDisabledImageDescriptor(icon16);
	}

	@Override
	public void run() {
		try {
			new FindReportUnit().find(this, getJasperDesign());
		} catch (Exception e) {
			UIUtils.showError(e);
		}
	}

	public void publishReportUnit(final ANode node, final JasperDesign jd) {
		boolean silent = jrConfig.get(KEY_PUBLISH2JSSWIZARD_SILENT, false);
		if (node == null || !(node instanceof MReportUnit)) {
			if (!createDialog(node, jd, 1))
				return;
		} else {
			mrunit = (MReportUnit) node;
			if (silent) {
				new FindResources().find(mrunit, jrConfig, jd);
			} else if (!createDialog(node, jd, 2))
				return;
		}
		Job job = new Job("Publish Report Unit") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					publishResources(monitor, jd, node);
					if (monitor.isCanceled())
						return Status.CANCEL_STATUS;
					// clean
					jrConfig.remove(KEY_PUBLISH2JSS_DATA);
					Display.getDefault().syncExec(new Runnable() {

						public void run() {
							postProcessLocal();
						}
					});
				} catch (Exception e) {
					UIUtils.showError(e);
				} finally {
					monitor.done();
				}
				return Status.OK_STATUS;
			}

		};
		job.setPriority(Job.LONG);
		job.schedule();
	}

	private boolean createDialog(ANode n, JasperDesign jd, int page) {
		Shell shell = Display.getDefault().getActiveShell();
		Publish2ServerWizard wizard = new Publish2ServerWizard(n, jd, jrConfig,
				page);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		if (dialog.open() != Dialog.OK)
			return false;
		mrunit = wizard.getReportUnit();
		return true;
	}

	protected JasperDesign getJasperDesign() throws JRException {
		return ModelUtils.copyJasperDesign(jrConfig.getJasperDesign());
	}

	protected IStatus publishResources(IProgressMonitor monitor,
			JasperDesign jd, ANode parent) throws Exception {
		if (mrunit == null || !(mrunit instanceof MReportUnit)) {
			ResourceDescriptor rd = MReportUnit.createDescriptor(parent);
			mrunit = new MReportUnit(parent, rd);
		}
		MJrxml jrxml = new MJrxml(mrunit, getMainReport(jd), 0);
		File file = FileUtils.createTempFile("jrsres", ".jrxml");
		String version = ServerManager.getVersion(mrunit);
		FileUtils.writeFile(file, JRXmlWriterHelper.writeReport(jd, version));
		jrxml.setFile(file);

		mrunit.getValue().getChildren().add(jrxml.getValue());

		mrunit.setValue(save(monitor, mrunit));

		List<MResource> files = jrConfig.get(KEY_PUBLISH2JSS_DATA,
				new ArrayList<MResource>());
		for (MResource f : files) {
			PublishOptions popt = f.getPublishOptions();
			if (popt.isOverwrite()) {
				if (popt.getjExpression() != null)
					popt.getjExpression().setText(popt.getExpression());
				save(monitor, f);
			}
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}

	protected ResourceDescriptor save(IProgressMonitor monitor, MResource f)
			throws Exception {
		try {
			return WSClientHelper.saveResource(f, monitor, false);
		} catch (Exception e) {
			f.getValue().setIsNew(false);
			return WSClientHelper.saveResource(f, monitor, false);
		}
	}

	private ResourceDescriptor getMainReport(JasperDesign jd) {
		ResourceDescriptor mainr = new ResourceDescriptor();
		mainr.setName(jd.getName());
		mainr.setLabel("Main jrxml");
		mainr.setWsType(ResourceDescriptor.TYPE_JRXML);
		mainr.setIsNew(true);
		mainr.setMainReport(true);
		mainr.setIsReference(false);
		mainr.setHasData(true);
		return mainr;
	}

	protected void postProcessLocal() {
		JasperDesign rpt = jrConfig.getJasperDesign();
		if (mrunit != null) {
			ResourceDescriptor runit = mrunit.getValue();
			rpt.setProperty(JrxmlExporter.PROP_REPORTUNIT, runit.getUriString());

			INode n = mrunit.getRoot();
			if (n != null && n instanceof MServerProfile) {
				MServerProfile server = (MServerProfile) n;
				ServerProfile srvrd = server.getValue();
				rpt.setProperty(JrxmlExporter.PROP_SERVERURL, srvrd.getUrl());

			}
		}
	}

	public static List<MResource> getResources(
			JasperReportsConfiguration jrConfig) {
		List<MResource> resources = jrConfig.get(
				JrxmlPublishAction.KEY_PUBLISH2JSS_DATA,
				new ArrayList<MResource>());
		jrConfig.put(JrxmlPublishAction.KEY_PUBLISH2JSS_DATA, resources);

		return resources;
	}

}
