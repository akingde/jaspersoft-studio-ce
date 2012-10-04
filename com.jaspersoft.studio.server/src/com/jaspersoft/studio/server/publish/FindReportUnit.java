package com.jaspersoft.studio.server.publish;

import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MDummy;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.publish.action.JrxmlPublishAction;
import com.jaspersoft.studio.utils.UIUtils;

public class FindReportUnit {
	public void find(final JrxmlPublishAction action, final JasperDesign jd,
			final int startpage, final IProgressMonitor monitor) {
		monitor.subTask(Messages.FindReportUnit_jobname);
		try {
			final ANode node = find(monitor, jd);
			Display.getDefault().syncExec(new Runnable() {

				public void run() {
					action.publishReportUnit(node, jd, startpage, monitor);
				}
			});
		} catch (Exception e) {
			UIUtils.showError(e);
		} finally {
			monitor.done();
		}
	}

	private ANode find(IProgressMonitor monitor, JasperDesign jd) {
		final MRoot root = new MRoot(null, null);
		ANode sp = null;
		List<ServerProfile> servers = ServerManager.getServerList();
		for (ServerProfile s : servers) {
			sp = new MServerProfile(root, s);
			new MDummy(sp);
			try {
				WSClientHelper.connect((MServerProfile) sp, monitor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		final String prop = jd.getProperty(JrxmlExporter.PROP_SERVERURL);
		if (prop != null) {
			final MServerProfile mserv = (MServerProfile) new ModelVisitor<MServerProfile>(
					root) {

				@Override
				public boolean visit(INode n) {
					if (n instanceof MServerProfile
							&& ((MServerProfile) n).getValue().getUrl()
									.equals(prop)) {
						setObject((MServerProfile) n);
						return false;
					}
					return false;
				}
			}.getObject();

			sp = findReportUnit(mserv, monitor, jd);
		}

		return sp;
	}

	protected ANode findReportUnit(ANode sp, IProgressMonitor monitor,
			JasperDesign jd) {
		try {
			MServerProfile mserv = (MServerProfile) sp;
			WSClientHelper.connect(mserv, monitor);
			if (sp != null && sp instanceof MServerProfile) {
				String prunit = jd.getProperty(JrxmlExporter.PROP_REPORTUNIT);
				if (prunit != null) {
					WSClientHelper.connectGetData(mserv, monitor);
					// We can try to locate a previous existing Report Unit.
					// If not possible we will popup the selection tree as usual.
					MResource selectedRepoUnit = WSClientHelper.findSelected(mserv.getChildren(),
							monitor, prunit, mserv.getWsClient());
					if(selectedRepoUnit!=null){
						sp=selectedRepoUnit;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sp;
	}
}
