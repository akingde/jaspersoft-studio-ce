package com.jaspersoft.studio.server.plugin;

import java.util.Set;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public interface IPublishContributor {
	public void publishJrxml(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file,
			String version, JasperReportsConfiguration jrConfig)
			throws Exception;

}
