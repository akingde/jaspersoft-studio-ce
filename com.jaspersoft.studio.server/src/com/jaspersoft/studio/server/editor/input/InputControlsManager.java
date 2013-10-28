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

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.Argument;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.editor.input.lov.ListOfValuesInput;
import com.jaspersoft.studio.server.editor.input.query.QueryInput;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;
import com.jaspersoft.studio.utils.Misc;

public class InputControlsManager {
	private HashMap<String, List<String>> cascadingDepMap = new HashMap<String, List<String>>();
	private List<ResourceDescriptor> inputcontrols;
	private Map<String, Object> defaults;
	private IConnection wsclient;
	private String reportUnit;

	public InputControlsManager(String reportUnit) {
		this.reportUnit = reportUnit;
	}

	public IConnection getWsClient() {
		return wsclient;
	}

	public void setWsclient(IConnection wsclient) {
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

	public List<ResourceDescriptor> getInputControls(List<ResourceDescriptor> list, IConnection cl) throws Exception {
		this.wsclient = cl;
		inputcontrols = new java.util.ArrayList<ResourceDescriptor>();
		for (ResourceDescriptor sub_rd : list) {
			String wsType = sub_rd.getWsType();
			if (wsType.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
				inputcontrols.add(sub_rd);
			else if (wsType.equals(ResourceDescriptor.TYPE_DATASOURCE))
				dsUri = sub_rd.getReferenceUri();
			else if (SelectorDatasource.isDatasource(sub_rd))
				dsUri = sub_rd.getUriString();
		}
		for (int i = 0; i < inputcontrols.size(); ++i) {
			ResourceDescriptor ic = inputcontrols.get(i);
			if (isICQuery(ic)) {
				inputcontrols.remove(ic);

				String dsUriQuery = getDataSourceQueryURI(dsUri, ic);
				ic.setResourceProperty(ResourceDescriptor.PROP_QUERY_DATA, null);
				// Ask to add values to the control....
				List<Argument> args = new ArrayList<Argument>();
				args.add(new Argument(Argument.IC_GET_QUERY_DATA, dsUriQuery));
				ic = cl.get(new NullProgressMonitor(), ic, null, args);

				inputcontrols.add(i, ic);
				cascadingDependencies(ic);
			}
		}
		return inputcontrols;
	}

	private String getDataSourceQueryURI(String dsUri, ResourceDescriptor ic) {
		String dsUriQuery = null;
		// reset query data...
		// Look if this query has a specific datasource...
		for (int k = 0; dsUriQuery == null && k < ic.getChildren().size(); ++k) {
			ResourceDescriptor sub_ic = (ResourceDescriptor) ic.getChildren().get(k);
			if (isRDQuery(sub_ic))
				for (int k2 = 0; k2 < sub_ic.getChildren().size(); ++k2) {
					ResourceDescriptor sub_sub_ic = (ResourceDescriptor) sub_ic.getChildren().get(k2);
					if (SelectorDatasource.isDatasource(sub_sub_ic)) {
						dsUriQuery = sub_sub_ic.getUriString();
						break;
					}
				}
		}
		if (dsUriQuery == null)
			dsUriQuery = dsUri;
		return dsUriQuery;
	}

	private void cascadingDependencies(ResourceDescriptor ic) {
		List<ResourceDescriptor> children = ic.getChildren();
		for (ResourceDescriptor sub_ic : children) {
			if (!isRDQuery(sub_ic))
				continue;
			String queryString = sub_ic.getSql();
			String lang = sub_ic.getResourceProperty(ResourceDescriptor.PROP_QUERY_LANGUAGE).getValue();
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
	private String dsUri;

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
				if (cascadingDepMap.get(icName) == null || !cascadingDepMap.get(icName).contains(updateICName)) {
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

	private void updateControl(final IDataInput ic, Map<String, Object> parameters) throws Exception {
		PResourceDescriptor presd = (PResourceDescriptor) ic.getParameter();
		List<Argument> args = new ArrayList<Argument>();

		args.add(new Argument(Argument.IC_GET_QUERY_DATA, getDataSourceQueryURI(dsUri, presd.getResourceDescriptor())));
		args.add(new Argument(Argument.RU_REF_URI, WSClientHelper.getReportUnitUri(reportUnit)));

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
		presd.setResourceDescriptor(getWsClient().get(new NullProgressMonitor(), rd, null, args));
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
		return ic.getResourcePropertyValue(ResourceDescriptor.PROP_INPUTCONTROL_IS_VISIBLE) == null || ic.getResourcePropertyValue(ResourceDescriptor.PROP_INPUTCONTROL_IS_VISIBLE).equals("true");
	}

	protected boolean isRDQuery(ResourceDescriptor sub_ic) {
		return sub_ic.getWsType().equals(ResourceDescriptor.TYPE_QUERY);
	}

	public static boolean isICQuery(ResourceDescriptor ic) {
		return ic.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY || ic.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY_RADIO
				|| ic.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY || ic.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX;
	}

	public static boolean isICListOfValues(ResourceDescriptor ic) {
		return ic.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES || ic.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES_RADIO
				|| ic.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES || ic.getControlType() == ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX;
	}
}
