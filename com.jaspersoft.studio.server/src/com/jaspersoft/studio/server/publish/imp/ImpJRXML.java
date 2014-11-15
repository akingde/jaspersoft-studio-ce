/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.imp;

import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImpJRXML {
	private JasperReportsConfiguration jrConfig;

	public ImpJRXML(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

	public MJrxml publish(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, JasperReportsConfiguration jrConfig)
			throws Exception {
		ResourceDescriptor runit = mrunit.getValue();

		ResourceDescriptor rd = MJrxml.createDescriptor(mrunit);
		rd.setName(IDStringValidator.safeChar(Misc.nvl(jasper.getName())));
		rd.setLabel(jasper.getName());
		rd.setVisible(true);
		rd.setReadOnly(false);
		rd.setMandatory(false);
		rd.setResourceProperty(ResourceDescriptor.PROP_INPUTCONTROL_TYPE,
				ResourceDescriptor.IC_TYPE_SINGLE_VALUE);
		rd.setParentFolder(runit.getUriString() + "_files");
		rd.setUriString(runit.getUriString() + "_files/" + rd.getName());

		MJrxml mres = (MJrxml) ResourceFactory.getResource(mrunit, rd, -1);

		mres.setPublishOptions(new PublishOptions());

		PublishUtil.getResources(mrunit, monitor, jrConfig).add(mres);
		return mres;
	}
}
