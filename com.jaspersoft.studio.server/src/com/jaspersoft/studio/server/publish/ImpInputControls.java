/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.publish;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.MDataType;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.action.JrxmlPublishAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImpInputControls {
	private JasperReportsConfiguration jrConfig;

	public ImpInputControls(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

	public void publish(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, JasperReportsConfiguration jrConfig)
			throws Exception {
		for (JRParameter p : jasper.getParametersList()) {
			if (p.isSystemDefined() || !p.isForPrompting())
				continue;
			ResourceDescriptor runit = mrunit.getValue();

			ResourceDescriptor rd = MInputControl.createDescriptor(mrunit);
			rd.setName(p.getName());
			rd.setLabel(p.getName());
			rd.setDescription(p.getDescription());
			rd.setVisible(true);
			rd.setReadOnly(false);
			rd.setMandatory(false);
			rd.setResourceProperty(ResourceDescriptor.PROP_INPUTCONTROL_TYPE,
					ResourceDescriptor.IC_TYPE_SINGLE_VALUE);
			rd.setParentFolder(runit.getUriString() + "_files");
			rd.setUriString(runit.getUriString() + "_files/" + rd.getName());

			MInputControl mres = (MInputControl) ResourceFactory.getResource(
					mrunit, rd, -1);

			if (Boolean.class.isAssignableFrom(p.getValueClass())) {
				rd.setControlType(ResourceDescriptor.IC_TYPE_BOOLEAN);
			} else if (String.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_TEXT);
			} else if (Timestamp.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE_TIME);
			} else if (Date.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE);
			} else if (Number.class.isAssignableFrom(p.getValueClass())) {
				addType(rd, mres, ResourceDescriptor.DT_TYPE_NUMBER);
			} else
				return;

			mres.setPublishOptions(new PublishOptions());

			JrxmlPublishAction.getResources(jrConfig).add(mres);
		}
	}

	protected static void addType(ResourceDescriptor rd, MInputControl mres,
			byte type) {
		ResourceDescriptor rdtype = MDataType.createDescriptor(mres);
		String name = "myDatatype";
		rdtype.setName(name);
		rdtype.setLabel(name);
		rdtype.setIsNew(true);
		rdtype.setDataType(type);
		rdtype.setUriString(String.format("%s/%s/%s", rd.getParentFolder(),
				rd.getName(), name));

		rd.getChildren().add(rdtype);
	}
}
