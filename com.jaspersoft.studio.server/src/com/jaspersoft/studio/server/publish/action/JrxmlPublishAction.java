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
package com.jaspersoft.studio.server.publish.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
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
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.publish.FindReportUnit;
import com.jaspersoft.studio.server.publish.FindResources;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.wizard.Publish2ServerWizard;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JrxmlPublishAction extends AContributorAction {
	private static final String ID = "PUBLISHJRXML"; //$NON-NLS-1$
	public static final String KEY_PUBLISH2JSSWIZARD_SILENT = "PUBLISH2JSSWIZARD_SILENT"; //$NON-NLS-1$
	public static final String KEY_PUBLISH2JSS_DATA = "PUBLISH2JSS_DATA"; //$NON-NLS-1$
	private MReportUnit mrunit;
	private int startpage = 1;
	private IProgressMonitor monitor;

	public JrxmlPublishAction(int startpage, IProgressMonitor monitor) {
		this();
		this.startpage = startpage;
		this.monitor = monitor;
	}

	public JrxmlPublishAction() {
		super(ID, Messages.JrxmlPublishAction_title);
		setToolTipText(Messages.JrxmlPublishAction_tooltip);
		ImageDescriptor icon16 = 
				Activator.getDefault().getImageDescriptor("icons/server--upload.png"); //$NON-NLS-1$
		setImageDescriptor(icon16);
		setDisabledImageDescriptor(icon16);
	}

	@Override
	public void run() {
		try {
			final JasperDesign jasperDesign = getJasperDesign();
			if (monitor != null)
				new FindReportUnit().find(this, jasperDesign, startpage,
						monitor);
			else {
				Job job = new Job(Messages.FindReportUnit_jobname) {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						new FindReportUnit().find(JrxmlPublishAction.this,
								jasperDesign, startpage, monitor);
						return Status.OK_STATUS;
					}

				};
				job.setPriority(Job.LONG);
				job.schedule();
			}
		} catch (Exception e) {
			UIUtils.showError(e);
		}
	}

	public void publishReportUnit(final ANode node, final JasperDesign jd,
			int startpage, IProgressMonitor monitor) {
		boolean silent = jrConfig.get(KEY_PUBLISH2JSSWIZARD_SILENT, false);
		if (node == null || !(node instanceof MReportUnit)) {
			if (!createDialog(node, jd, 1))
				return;
		} else {
			mrunit = (MReportUnit) node;
			if (silent) {
				new FindResources().find(mrunit, jrConfig, jd);
			} else if (!createDialog(node, jd, startpage))
				return;
		}

		try {
			publishResources(monitor, jd, node);
			if (monitor.isCanceled())
				return;
			UIUtils.showInformation("Your report was published to JasperReports Server with success!\nPlease refresh Repository View to see changes.");

			// clean
			jrConfig.remove(KEY_PUBLISH2JSS_DATA);
			postProcessLocal();
		} catch (Exception e) {
			UIUtils.showError(e);
		}

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
		} else if (mrunit.getParent() == null)
			mrunit.setParent(parent, -1);
		MJrxml jrxml = new MJrxml(mrunit, getMainReport(mrunit, jd), 0);
		File file = FileUtils.createTempFile("jrsres", ".jrxml"); //$NON-NLS-1$ //$NON-NLS-2$
		String version = ServerManager.getVersion(mrunit);

		List<MResource> files = jrConfig.get(KEY_PUBLISH2JSS_DATA,
				new ArrayList<MResource>());
		for (MResource f : files) {
			PublishOptions popt = f.getPublishOptions();
			if (popt.isOverwrite() && popt.getjExpression() != null)
				popt.getjExpression().setText(popt.getExpression());
			if (monitor.isCanceled())
				return Status.CANCEL_STATUS;
		}
		FileUtils.writeFile(file,
				JRXmlWriterHelper.writeReport(null, jd, version));
		jrxml.setFile(file);
		mrunit.setFile(file);
		mrunit.getValue().getChildren().add(jrxml.getValue());
		mrunit.setValue(save(monitor, mrunit));
		jrxml.setValue(save(monitor, jrxml));

		for (MResource f : files) {
			PublishOptions popt = f.getPublishOptions();
			if (popt.isOverwrite())
				save(monitor, f);
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
			if (f.getValue().getIsNew()) {
				f.getValue().setIsNew(false);
				return WSClientHelper.saveResource(f, monitor, false);
			}
			throw e;
		}
	}

	private ResourceDescriptor getMainReport(MReportUnit mrunit, JasperDesign jd) {
		String jrxmln = jd.getProperty(JrxmlExporter.PROP_REPORTRESOURCE);
		if (jrxmln != null) {
			String unit = mrunit.getValue().getUriString() + "_files/";
			if (unit != null && jrxmln.startsWith(unit)
					&& jrxmln.length() > unit.length()
					&& jrxmln.substring(unit.length()).indexOf('/') < 0) {
				MServerProfile sp = (MServerProfile) mrunit.getRoot();
				if (sp != null) {
					ResourceDescriptor rd = new ResourceDescriptor();
					rd.setName(jrxmln.substring(unit.length()));
					rd.setLabel(jrxmln.substring(unit.length()));
					rd.setUriString(jrxmln);
					rd.setParentFolder(unit + "_files/" + rd.getName());
					rd.setIsNew(true);
					rd.setWsType(ResourceDescriptor.TYPE_JRXML);
					rd.setIsReference(false);
					rd.setHasData(true);
					try {
						rd = sp.getWsClient().get(rd, null);
						rd.setHasData(true);
						if (rd != null)
							return rd;
					} catch (Exception e) {
						e.printStackTrace();// maybe ask something?
						return rd;
					}
				}
			}
		}
		ResourceDescriptor mainr = new ResourceDescriptor();
		mainr.setName(Messages.JrxmlPublishAction_defaultresourcename);
		mainr.setLabel(Messages.JrxmlPublishAction_defaultresourcelabel);
		mainr.setWsType(ResourceDescriptor.TYPE_JRXML);
		mainr.setIsNew(true);
		mainr.setMainReport(true);
		mainr.setIsReference(false);
		mainr.setHasData(true);
		return mainr;
	}

	protected void postProcessLocal() {
		JasperDesign rpt = jrConfig.getJasperDesign();
		if (mrunit != null && mrunit.getValue() != null) {
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
