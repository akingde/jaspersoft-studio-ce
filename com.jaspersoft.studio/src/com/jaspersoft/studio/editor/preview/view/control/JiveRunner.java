/*******************************************************************************
 * Copyright (C) 2010 - 2018. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.core.resources.IFile;

import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.jive.Context;
import com.jaspersoft.studio.editor.preview.jive.JettyUtil;
import com.jaspersoft.studio.editor.preview.view.report.html.ABrowserViewer;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JasperReport;

public class JiveRunner implements IReportRunner {
	public static final String ID = "RUNJIVE";

	@Override
	public String getID() {
		return ID;
	}

	public String getLabel() {
		return "Run Interactive Report (Jive)";
	}

	public void run(final PreviewContainer pcontainer, final IFile file, final JasperReport jasperReport,
			JasperReportsConfiguration jrContext, Map<String, Object> jasperParameters) {
		JettyUtil.startJetty(file.getProject(), jrContext);
		UIUtils.getDisplay().syncExec(() -> {
			try {
				Map<String, Object> prm = new HashMap<>();

				prm.put(JettyUtil.PRM_JRPARAMETERS, jasperParameters);
				prm.put(JettyUtil.PRM_JASPERREPORT, jasperReport);

				UUID randomUUID = UUID.randomUUID();
				Context.putContext(randomUUID.toString(), prm);

				String url = JettyUtil.getURL(file, randomUUID.toString(), jrContext);
				ABrowserViewer jiveViewer = pcontainer.getBrowserViewer();
				jiveViewer.setURL(url);
				pcontainer.getRightContainer().switchView(null, jiveViewer);

			} catch (Throwable e) {
				UIUtils.showError(e);
			}
		});
	}
}
