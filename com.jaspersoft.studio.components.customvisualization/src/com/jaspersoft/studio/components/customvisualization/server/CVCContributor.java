/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.server;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.server.model.AMJrxmlContainer;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.plugin.IPublishContributor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.customvisualization.design.CVDesignComponent;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

public class CVCContributor implements IPublishContributor {

	@Override
	public void publishJrxml(AMJrxmlContainer mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file, String version)
			throws Exception {
	}

	@Override
	public void publishComponent(AMJrxmlContainer mrunit,
			IProgressMonitor monitor, JasperDesign jasper, Set<String> fileset,
			IFile file, JRDesignElement element, String version)
			throws Exception {
		if (!(mrunit instanceof MReportUnit))
			return;
		if (!(element instanceof JRDesignComponentElement))
			return;
		JRDesignComponentElement jrElement = (JRDesignComponentElement) element;
		Component cmp = jrElement.getComponent();
		if (cmp instanceof CVDesignComponent) {
			CVDesignComponent cvComp = (CVDesignComponent) cmp;
			for (ItemProperty p : cvComp.getItemProperties()) {
				if (p.getName().equalsIgnoreCase("css")) {
					impcss.publish(jasper, p, (MReportUnit) mrunit, monitor,
							fileset, file);
				} else if (p.getName().equalsIgnoreCase("script")) {
					impjs.publish(jasper, p, (MReportUnit) mrunit, monitor,
							fileset, file);
				}
			}
		}
	}

	@Override
	public void publishParameters(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper) throws Exception {
	}

	private ImpCSS impcss;
	private ImpJS impjs;

	@Override
	public void init(JasperReportsConfiguration jrConfig) {
		impcss = new ImpCSS(jrConfig);
		impjs = new ImpJS(jrConfig);
	}
}
