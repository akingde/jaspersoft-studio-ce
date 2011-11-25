package com.jaspersoft.studio.server.editor.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.server.wizard.resource.page.SelectorDatasource;
import com.jaspersoft.studio.utils.Misc;

public class InputControlsManager {
	private List<ResourceDescriptor> inputcontrols;
	private Map<String, Object> defaults;
	private WSClient wsclient;

	public WSClient getWsClient() {
		return wsclient;
	}

	public void setWsclient(WSClient wsclient) {
		this.wsclient = wsclient;
	}

	public Map<String, Object> getParameters() {
		return defaults;
	}

	public void getDefaults(ResourceDescriptor rd) {
		defaults = new HashMap<String, Object>();
		if (rd.getWsType().equals("ReportOptionsResource")) {
			ResourceProperty rp = rd.getResourceProperty("PROP_VALUES");

			List<ResourceProperty> list = rp.getProperties();
			if (list != null)
				for (ResourceProperty li : list) {
					if (!li.getProperties().isEmpty()) {
						List<String> listVal = new ArrayList<String>();
						for (Object sli : li.getProperties())
							listVal.add(((ResourceProperty) sli).getValue());
						defaults.put(li.getName(), listVal);
					} else {
						defaults.put(li.getName(), Misc.nvl(li.getValue()));
					}
				}
		}
	}

	public List<ResourceDescriptor> getInputControls() {
		return inputcontrols;
	}

	public List<ResourceDescriptor> getInputControls(
			List<ResourceDescriptor> list, WSClient cl) throws Exception {
		this.wsclient = cl;
		String dsUri = null;

		inputcontrols = new java.util.ArrayList<ResourceDescriptor>();
		for (ResourceDescriptor sub_rd : list) {
			if (sub_rd.getWsType()
					.equals(ResourceDescriptor.TYPE_INPUT_CONTROL)) {
				inputcontrols.add(sub_rd);
			} else if (sub_rd.getWsType().equals(
					ResourceDescriptor.TYPE_DATASOURCE)) {
				dsUri = sub_rd.getReferenceUri();
			}
			// else if (RepositoryFolder.isDataSource(sub_rd)) {
			// dsUri = sub_rd.getUriString();
			// }
		}

		for (int i = 0; i < inputcontrols.size(); ++i) {
			ResourceDescriptor ic = inputcontrols.get(i);
			if (isICQuery(ic)) {
				String dsUriQuery = null;
				inputcontrols.remove(ic);

				// Ask to add values to the control....
				java.util.List<Argument> args = new java.util.ArrayList<Argument>();
				// reset query data...
				// Look if this query has a specific datasource...
				for (int k = 0; dsUriQuery == null
						&& k < ic.getChildren().size(); ++k) {
					ResourceDescriptor sub_ic = (ResourceDescriptor) ic
							.getChildren().get(k);
					if (isRDQuery(sub_ic))
						for (int k2 = 0; k2 < sub_ic.getChildren().size(); ++k2) {
							ResourceDescriptor sub_sub_ic = (ResourceDescriptor) sub_ic
									.getChildren().get(k2);
							if (SelectorDatasource.isDatasource(sub_sub_ic)) {
								dsUriQuery = sub_sub_ic.getUriString();
								break;
							}
						}
				}
				if (dsUriQuery == null)
					dsUriQuery = dsUri;
				ic.setResourceProperty(ResourceDescriptor.PROP_QUERY_DATA, null);

				args.add(new Argument(Argument.IC_GET_QUERY_DATA, dsUriQuery));
				ic = cl.get(ic, null, args);

				inputcontrols.add(i, ic);
			}
		}
		return inputcontrols;
	}

	public boolean isAnyVisible() {
		for (ResourceDescriptor ic : inputcontrols) {
			if (isICVisible(ic))
				return true;
		}
		return false;
	}

	protected boolean isICVisible(ResourceDescriptor ic) {
		return ic
				.getResourcePropertyValue(ResourceDescriptor.PROP_INPUTCONTROL_IS_VISIBLE) == null
				|| ic.getResourcePropertyValue(
						ResourceDescriptor.PROP_INPUTCONTROL_IS_VISIBLE)
						.equals("true");
	}

	protected boolean isRDQuery(ResourceDescriptor sub_ic) {
		return sub_ic.getWsType().equals(ResourceDescriptor.TYPE_QUERY);
	}

	public static boolean isICQuery(ResourceDescriptor ic) {
		return ic.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY
				|| ic.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY_RADIO
				|| ic.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY
				|| ic.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX;
	}

	public static boolean isICListOfValues(ResourceDescriptor ic) {
		return ic.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES
				|| ic.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES_RADIO
				|| ic.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES
				|| ic.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX;
	}
}
