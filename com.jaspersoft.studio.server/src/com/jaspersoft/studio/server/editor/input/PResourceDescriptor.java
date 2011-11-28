package com.jaspersoft.studio.server.editor.input;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.utils.UIUtils;

public class PResourceDescriptor implements IParameter {
	private ResourceDescriptor p;
	private WSClient client;

	public PResourceDescriptor(ResourceDescriptor p, WSClient client) {
		this.p = p;
		this.client = client;
	}

	public String getName() {
		return p.getLabel();
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
		return client;
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
				rdtype = client.get(tmpRd, null);
			}
			if (rdtype != null) {
				if (rdtype.getDataType() == ResourceDescriptor.DT_TYPE_DATE)
					return Date.class;
				if (rdtype.getDataType() == ResourceDescriptor.DT_TYPE_DATE)
					return Timestamp.class;
				if (rdtype.getDataType() == ResourceDescriptor.DT_TYPE_TEXT)
					return String.class;
				if (rdtype.getDataType() == ResourceDescriptor.DT_TYPE_NUMBER)
					return Number.class;
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
}
