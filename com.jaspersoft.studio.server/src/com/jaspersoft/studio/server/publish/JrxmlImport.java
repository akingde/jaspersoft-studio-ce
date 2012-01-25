package com.jaspersoft.studio.server.publish;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
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
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.plugin.AContributorAction;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.wizard.resource.page.SelectorJrxml;
import com.jaspersoft.studio.utils.FileUtils;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtils;

public class JrxmlImport extends AContributorAction {
	private static final String ID = "PUBLISHJRXML";

	public JrxmlImport() {
		super();
		setId(ID);
		setText("Publish Report to JasperServer");
		setToolTipText("Publish Report to JasperServer");
		ImageDescriptor icon16 = Activator
				.getImageDescriptor("icons/server--upload.png");
		setImageDescriptor(icon16);
		setDisabledImageDescriptor(icon16);
	}

	@Override
	public void run() {

		try {
			Shell shell = Display.getDefault().getActiveShell();
			final JasperDesign jd = getJasperDesign();
			Publish2ServerWizard wizard = new Publish2ServerWizard(jd);
			WizardDialog dialog = new WizardDialog(shell, wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				final MReportUnit mrunit = wizard.getReportUnit();
				SelectorJrxml.replaceMainReport(mrunit, getMainReport(jd));
				final IFile file = (IFile) jrConfig
						.get(IEditorContributor.KEY_FILE);
				mrunit.setFile(file.getRawLocation().toFile());
				Job job = new Job("Publish Report Unit") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						try {

							publish(mrunit, monitor, file, jd);
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
		} catch (JRException e) {
			UIUtils.showError(e);
		}
	}

	protected JasperDesign getJasperDesign() throws JRException {
		return ModelUtils.copyJasperDesign(jrConfig.getJasperDesign());
	}

	private ResourceDescriptor getMainReport(JasperDesign jd) {
		ResourceDescriptor mainr = new ResourceDescriptor();
		mainr.setName(jd.getName());
		mainr.setLabel(jd.getName());
		mainr.setWsType(ResourceDescriptor.TYPE_JRXML);
		mainr.setIsNew(true);
		mainr.setMainReport(true);
		mainr.setIsReference(false);
		mainr.setHasData(true);
		return mainr;
	}

	protected void publish(final MReportUnit mrunit, IProgressMonitor monitor,
			IFile file, JasperDesign jd) throws Exception {
		String version = JRXmlWriterHelper.LAST_VERSION;
		mrunit.setValue(WSClientHelper.saveResource(mrunit, monitor, false));
		INode n = mrunit.getRoot();
		if (n != null && n instanceof MServerProfile)
			version = ((MServerProfile) n).getValue().getJrVersion();

		Set<String> fileset = new HashSet<String>();
		publishJrxml(mrunit, monitor, jd, fileset, file, version);

		saveJRXML(monitor, mrunit, jd, version);
	}

	public void publishJrxml(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file, String version)
			throws Exception {
		List<JRDesignElement> elements = ModelUtils.getAllElements(jasper);
		for (JRDesignElement ele : elements) {
			if (ele instanceof JRDesignImage)
				img.publish(jasper, ele, mrunit, monitor, fileset, file);
			else if (ele instanceof JRDesignSubreport) {
				AFileResource fres = srp.publish(jasper, ele, mrunit, monitor,
						fileset, file);
				if (fres == null)
					continue;
				JasperDesign jrd = JRXmlLoader.load(fres.getFile());
				if (jrd != null) {
					IFile[] fs = root.findFilesForLocationURI(fres.getFile()
							.toURI());
					if (fs != null && fs.length > 0) {
						publishJrxml(mrunit, monitor, jrd, fileset, fs[0],
								version);
						saveJRXML(monitor, fres, jrd, version);
					}
				}
			}
		}
		for (JRReportTemplate rt : jasper.getTemplatesList()) {
			style.publish(jasper, rt, mrunit, monitor, fileset, file);
		}
	}

	protected void saveJRXML(IProgressMonitor monitor, AFileResource fres,
			JasperDesign jrd, String version) throws IOException, Exception {
		File f = FileUtils.createTempFile("jrsres", ".jrxml");
		FileUtils.writeFile(f, JRXmlWriterHelper.writeReport(jrd, version));
		fres.setFile(f);
		WSClientHelper.saveResource(fres, monitor, false);
	}

	private IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	private ImpStyleTemplate style = new ImpStyleTemplate();
	private ImpImage img = new ImpImage();
	private ImpSubreport srp = new ImpSubreport();
}
