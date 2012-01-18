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
package com.jaspersoft.studio.server.editor.input;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.utils.UIUtils;

public class PResourceDescriptor implements IParameter {
	private ResourceDescriptor p;
	private InputControlsManager icm;

	public PResourceDescriptor(ResourceDescriptor p, InputControlsManager icm) {
		this.p = p;
		this.icm = icm;
	}

	public void setResourceDescriptor(ResourceDescriptor p) {
		this.p = p;
	}

	public InputControlsManager getInputControlManager() {
		return icm;
	}

	public String getName() {
		return p.getName();
	}

	public String getLabel() {
		return p.getLabel();
	}

	public boolean isMandatory() {
		return p.isMandatory();
	}

	public boolean isReadOnly() {
		return p.isReadOnly();
	}

	public Class<?> getValueClass() {
		try {
			return getValueClass(p);
		} catch (Exception e) {
			UIUtils.showError(e);
		}
		return null;
	}

	public ResourceDescriptor getResourceDescriptor() {
		return p;
	}

	public WSClient getWsClient() {
		return icm.getWsClient();
	}

	public String getDescription() {
		return p.getDescription();
	}

	private Class<?> getValueClass(ResourceDescriptor rd) throws Exception {
		if (rd.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_VALUE) {
			ResourceDescriptor rdtype = (ResourceDescriptor) rd.getChildren()
					.get(0);
			if (rdtype.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE)) {
				ResourceDescriptor tmpRd = new ResourceDescriptor();
				tmpRd.setUriString(rdtype.getReferenceUri());
				rdtype = getWsClient().get(tmpRd, null);
			}
			if (rdtype != null) {
				if (rdtype.getDataType() == ResourceDescriptor.DT_TYPE_DATE)
					return Date.class;
				if (rdtype.getDataType() == ResourceDescriptor.DT_TYPE_DATE)
					return Timestamp.class;
				if (rdtype.getDataType() == ResourceDescriptor.DT_TYPE_TEXT)
					return String.class;
				if (rdtype.getDataType() == ResourceDescriptor.DT_TYPE_NUMBER)
					return BigDecimal.class;
			}
		} else if (rd.getControlType() == ResourceDescriptor.IC_TYPE_BOOLEAN) {
			return java.lang.Boolean.class;
		} else if (InputControlsManager.isICListOfValues(rd)) {
			return List.class;
		} else if (InputControlsManager.isICQuery(rd)) {
			return ResourceDescriptor.class;
		}
		return rd.getClass();
	}

	public boolean isStrictMin() {
		return p.isStrictMin();
	}

	public String getMinValue() {
		return p.getMinValue();
	}

	public boolean isStrictMax() {
		return p.isStrictMax();
	}

	public String getMaxValue() {
		return p.getMaxValue();
	}

	public String getPattern() {
		return p.getPattern();
	}

}
