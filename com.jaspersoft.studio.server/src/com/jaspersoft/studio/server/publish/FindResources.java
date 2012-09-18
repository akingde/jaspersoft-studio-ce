package com.jaspersoft.studio.server.publish;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.action.JrxmlPublishAction;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class FindResources {
	public void find(final MReportUnit mrunit,
			final JasperReportsConfiguration jrConfig, final JasperDesign jd) {
		Job job = new Job(Messages.FindResources_jobname) {
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

	public boolean find(IProgressMonitor monitor, MReportUnit mrunit,
			JasperReportsConfiguration jrConfig, JasperDesign jd)
			throws Exception {
		jrConfig.put(JrxmlPublishAction.KEY_PUBLISH2JSS_DATA,
				new ArrayList<AFileResource>());

		String version = ServerManager.getVersion(mrunit);
		HashSet<String> fileset = new HashSet<String>();
		IFile file = (IFile) jrConfig.get(IEditorContributor.KEY_FILE);

		mrunit.removeChildren();

		new JrxmlPublishContributor().publishJrxml(mrunit, monitor, jd,
				fileset, file, version, jrConfig);

		Object r = jrConfig.get(JrxmlPublishAction.KEY_PUBLISH2JSS_DATA);
		return r != null && r instanceof List && ((List<?>) r).size() > 0;
	}

}
