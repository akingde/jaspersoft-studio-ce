package com.jaspersoft.studio.server.publish;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class FindResources {
	public void find(final MReportUnit mrunit,
			final JasperReportsConfiguration jrConfig, final JasperDesign jd) {
		Job job = new Job("Analyze ReportUnit Resources To Publish") {
			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				try {
					find(monitor, mrunit, jrConfig, jd);
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

	private void find(IProgressMonitor monitor, MReportUnit mrunit,
			JasperReportsConfiguration jrConfig, JasperDesign jd)
			throws Exception {
		List<AFileResource> fres = new ArrayList<AFileResource>();
		INode n = mrunit.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile server = (MServerProfile) n;
			ServerProfile srvrd = server.getValue();
			String version = JRXmlWriterHelper.LAST_VERSION;
			version = srvrd.getJrVersion();

			Set<String> fileset = new HashSet<String>();

			JrxmlPublishContributor jpc = new JrxmlPublishContributor();
			jpc.publishJrxml(mrunit, monitor, jd, fileset,
					(IFile) jrConfig.get(IEditorContributor.KEY_FILE), version,
					jrConfig);
		}
//		jrConfig.put(JrxmlPublishAction.KEY_PUBLISH2JSS_DATA, fres);
	}
}
