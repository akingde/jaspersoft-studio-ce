package com.jaspersoft.studio.server.editor.input;

import java.sql.Date;
import java.sql.Timestamp;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.editor.preview.input.IParameter;
import com.jaspersoft.studio.utils.UIUtils;

public class PResourceDescriptor implements IParameter {
	private ResourceDescriptor p;
	private WSClient client;

	public PResourceDescriptor(ResourceDescriptor p, WSClient client) {
		this.p = p;
	}

	public String getName() {
		return p.getLabel();
	}

	public Class<?> getValueClass() {
		try {
			return getXXX(p);
		} catch (Exception e) {
			UIUtils.showError(e);
		}
		return null;
	}

	public String getDescription() {
		return p.getDescription();
	}

	private Class<?> getXXX(ResourceDescriptor rd) throws Exception {
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

			// BasicInputControl bic = null;
			//
			// if (rd.getControlType() ==
			// rd.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES ||
			// rd.getControlType() ==
			// rd.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES_RADIO)
			// {
			// bic = new SingleSelectInputControl();
			// }
			// else if (rd.getControlType() ==
			// rd.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES ||
			// rd.getControlType() ==
			// rd.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX)
			// {
			// bic = new MultiSelectInputControl();
			// }
			//
			//
			// ResourceDescriptor rd2 =
			// (ResourceDescriptor)rd.getChildren().get(0);
			// List items = null;
			//
			// if (rd2.getWsType().equals(ResourceDescriptor.TYPE_REFERENCE))
			// {
			// ResourceDescriptor tmpRd = new ResourceDescriptor();
			// tmpRd.setUriString(rd2.getReferenceUri());
			// try {
			// tmpRd = this.getServer().getWSClient().get(tmpRd, null);
			// items = tmpRd.getListOfValues();
			// } catch (Exception ex)
			// {
			// JOptionPane.showMessageDialog(this,JasperServerManager.getFormattedString("messages.error.3",
			// "Error:\n {0}", new Object[] {ex.getMessage()}));
			// ex.printStackTrace();
			// }
			// }
			// else
			// {
			// items =
			// ((ResourceDescriptor)rd.getChildren().get(0)).getListOfValues();
			// }
			// bic.setInputControl(rd, items);
			// if (defaultValue != null) bic.setDefaultValue(defaultValue);
			// inputControls.add(bic);
		} else if (InputControlsManager.isICQuery(rd)) {

			// BasicInputControl bic = null;
			//
			// if (rd.getControlType() == rd.IC_TYPE_SINGLE_SELECT_QUERY)
			// {
			// bic = new MultiColumnListInputControl();
			// }
			// else if (rd.getControlType() ==
			// rd.IC_TYPE_SINGLE_SELECT_QUERY_RADIO)
			// {
			// bic = new SingleSelectInputControl();
			// }
			// else if (rd.getControlType() == rd.IC_TYPE_MULTI_SELECT_QUERY ||
			// rd.getControlType() == rd.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX)
			// {
			// bic = new MultiSelectInputControl();
			// }
			//
			// ResourceDescriptor rd2 =
			// (ResourceDescriptor)rd.getChildren().get(0);
			// List items = null;
			//
			// bic.setInputControl(rd,rd.getQueryData());
			// if (defaultValue != null) bic.setDefaultValue(defaultValue);
			// inputControls.add(bic);
		}
		return null;
	}
}
