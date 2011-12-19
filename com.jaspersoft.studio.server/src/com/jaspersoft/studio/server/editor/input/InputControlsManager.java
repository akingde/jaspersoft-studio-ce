package com.jaspersoft.studio.server.editor.input;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.input.lov.ListOfValuesInput;
import com.jaspersoft.studio.server.editor.input.query.QueryInput;
import com.jaspersoft.studio.server.wizard.resource.page.SelectorDatasource;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtils;

public class InputControlsManager {
	private HashMap<String, List<String>> cascadingDepMap = new HashMap<String, List<String>>();
	private List<ResourceDescriptor> inputcontrols;
	private Map<String, Object> defaults;
	private WSClient wsclient;
	private String reportUnit;

	public InputControlsManager(String reportUnit) {
		this.reportUnit = reportUnit;
	}

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
				cascadingDependencies(ic);
			}
		}
		return inputcontrols;
	}

	private void cascadingDependencies(ResourceDescriptor ic) {
		List<ResourceDescriptor> children = ic.getChildren();
		for (ResourceDescriptor sub_ic : children) {
			if (!isRDQuery(sub_ic))
				continue;
			String queryString = sub_ic.getSql();
			String lang = sub_ic.getResourceProperty(
					ResourceDescriptor.PROP_QUERY_LANGUAGE).getValue();
			if (queryString != null && !queryString.isEmpty()) {
				List<String> parameters = new ArrayList<String>();
				JRDesignQuery query = new JRDesignQuery();
				query.setText(queryString);
				if (lang != null)
					query.setLanguage(lang);
				for (JRQueryChunk chunk : query.getChunks()) {
					switch (chunk.getType()) {
					case JRQueryChunk.TYPE_TEXT:
						break;
					case JRQueryChunk.TYPE_PARAMETER_CLAUSE:
					case JRQueryChunk.TYPE_PARAMETER:
						String paramName = chunk.getText();
						if (!parameters.contains(paramName))
							parameters.add(paramName);
						break;
					case JRQueryChunk.TYPE_CLAUSE_TOKENS:
						String[] tokens = chunk.getTokens();
						if (tokens.length > 2) {
							for (String t : tokens) {
								t = t.trim();
								if (!parameters.contains(t))
									parameters.add(t);
							}
						}
						break;
					}
				}
				if (!parameters.isEmpty())
					cascadingDepMap.put(ic.getName(), parameters);
			}
			break;
		}
	}

	private List<IDataInput> icontrols = new ArrayList<IDataInput>();

	public List<IDataInput> getControls() {
		return icontrols;
	}

	private PropertyChangeListener propChangeListener = new PropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent evt) {
			final Object source = evt.getSource();
			if (source instanceof IDataInput) {
				Job job = new Job("Update Cascading Input Controls") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						IDataInput control = (IDataInput) source;
						actionPerformed(control, new HashSet<IDataInput>());
						return Status.OK_STATUS;
					}

				};
				job.setSystem(true);
				job.setUser(false);
				job.schedule();
			}
		}
	};

	public PropertyChangeListener getPropertyChangeListener() {
		return propChangeListener;
	}

	public void actionPerformed(IDataInput ic, Set<IDataInput> controls) {
		try {
			String updateICName = ic.getParameter().getName();
			// get the first input control having this param in the list...
			for (IDataInput icToUpdate : icontrols) {
				if (icToUpdate == ic || controls.contains(icToUpdate))
					continue;
				String icName = icToUpdate.getParameter().getName();
				if (cascadingDepMap.get(icName) == null
						|| !cascadingDepMap.get(icName).contains(updateICName)) {
					continue;
				}

				Map<String, Object> parameters = new HashMap<String, Object>();
				List<String> parametersICs = cascadingDepMap.get(icName);
				for (String paramName : parametersICs) {
					Object value = getParameters().get(paramName);
					parameters.put(paramName, value);
				}
				updateControl(icToUpdate, parameters);
				controls.add(icToUpdate);
				actionPerformed(icToUpdate, controls);
				break;
			}
		} catch (Exception ex) {
			UIUtils.showError(ex);
		}
	}

	private void updateControl(final IDataInput ic,
			Map<String, Object> parameters) throws Exception {
		List<Argument> args = new ArrayList<Argument>();
		args.add(new Argument(Argument.IC_GET_QUERY_DATA, ""));
		args.add(new Argument(Argument.RU_REF_URI, WSClientHelper
				.getReportUnitUri(reportUnit)));

		PResourceDescriptor presd = (PResourceDescriptor) ic.getParameter();
		ResourceDescriptor rd = presd.getResourceDescriptor();
		rd.getParameters().clear();
		rd.setResourceProperty(ResourceDescriptor.PROP_QUERY_DATA, null);

		for (String key : parameters.keySet()) {
			Object value = parameters.get(key);
			if (value != null)
				if (value instanceof Collection) {
					for (String item : ((Collection<String>) value)) {
						ListItem l = new ListItem(key, item);
						l.setIsListItem(true);
						rd.getParameters().add(l);
					}
				} else {
					rd.getParameters().add(new ListItem(key, value));
				}
		}
		presd.setResourceDescriptor(getWsClient().get(rd, null, args));
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				if (ic instanceof QueryInput)
					((QueryInput) ic).fillTable();
				else if (ic instanceof ListOfValuesInput)
					((ListOfValuesInput) ic).fillTable();
			}
		});

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
