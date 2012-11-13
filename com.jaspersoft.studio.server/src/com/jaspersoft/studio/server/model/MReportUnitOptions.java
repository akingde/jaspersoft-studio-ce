/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.model;

import java.util.ArrayList;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;

public class MReportUnitOptions extends MResource {
	private static final String PROP_OPTIONS_NAME = "PROP_OPTIONS_NAME";
	private static final String PROP_VALUES = "PROP_VALUES";
	public static final String REPORT_OPTIONS_RESOURCE = "ReportOptionsResource";
	public static final String PROP_RU_URI = "PROP_RU_URI";
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MReportUnitOptions(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	private static IIconDescriptor iconDescriptor;

	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("reportunitoptions"); //$NON-NLS-1$
		return iconDescriptor;
	}

	@Override
	public void setParent(ANode parent, int newIndex) {
		if (parent instanceof MReportUnit)
			parent = parent.getParent();
		super.setParent(parent, newIndex);
	}

	@Override
	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	public static ResourceDescriptor createDescriptor(MReportUnit parent) {
		ResourceDescriptor rd = MResource.createDescriptor(parent);
		ResourceProperty rp = new ResourceProperty(PROP_RU_URI, parent
				.getValue().getUriString());
		rd.getProperties().add(rp);

		rp = new ResourceProperty(PROP_OPTIONS_NAME, rd.getName());
		rd.getProperties().add(rp);

		rp = new ResourceProperty(PROP_VALUES);
		rp.setProperties(new ArrayList<ResourceProperty>());
		rd.getProperties().add(rp);

		rd.setParentFolder(parent.getValue().getParentFolder());
		rd.setWsType(REPORT_OPTIONS_RESOURCE);
		return rd;
	}
}
