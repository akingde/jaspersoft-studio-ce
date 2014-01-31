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
package com.jaspersoft.studio.server.publish.imp;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.types.date.DateRange;
import net.sf.jasperreports.types.date.TimestampRange;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.MDataType;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImpInputControls {
	private JasperReportsConfiguration jrConfig;

	public ImpInputControls(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

	public void publish(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper, JasperReportsConfiguration jrConfig) throws Exception {
		ResourceDescriptor runit = mrunit.getValue();
		for (JRParameter p : jasper.getParametersList()) {
			if (p.isSystemDefined() || !p.isForPrompting())
				continue;

			ResourceDescriptor rd = MInputControl.createDescriptor(mrunit);
			rd.setName(p.getName());
			rd.setLabel(p.getName());
			rd.setDescription(p.getDescription());
			rd.setVisible(true);
			rd.setReadOnly(false);
			rd.setMandatory(false);
			rd.setResourceProperty(ResourceDescriptor.PROP_INPUTCONTROL_TYPE, ResourceDescriptor.IC_TYPE_SINGLE_VALUE);
			rd.setParentFolder(runit.getUriString() + "_files");
			rd.setUriString(runit.getUriString() + "_files/" + rd.getName());

			MInputControl mres = (MInputControl) ResourceFactory.getResource(mrunit, rd, -1);

			if (Boolean.class.isAssignableFrom(p.getValueClass())) {
				rd.setControlType(ResourceDescriptor.IC_TYPE_BOOLEAN);
			} else if (String.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_TEXT);
			} else if (Timestamp.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE_TIME);
			} else if (Date.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE);
			} else if (TimestampRange.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE_TIME);
			} else if (DateRange.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE);
			} else if (Number.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_NUMBER);
			} else {
				mrunit.removeChild(mres);
				continue;
			}

			mres.setPublishOptions(new PublishOptions());

			PublishUtil.getResources(monitor, jrConfig).add(mres);
		}
	}

	protected static void addType(ResourceDescriptor rd, MInputControl mres, byte type) {
		ResourceDescriptor rdtype = MDataType.createDescriptor(mres);
		String name = "myDatatype";
		rdtype.setName(name);
		rdtype.setLabel(name);
		rdtype.setIsNew(true);
		rdtype.setDataType(type);
		rdtype.setIsReference(false);
		rdtype.setUriString(String.format("%s/%s/%s", rd.getParentFolder(), rd.getName() + "_files", name));

		rd.getChildren().add(rdtype);
	}
}
