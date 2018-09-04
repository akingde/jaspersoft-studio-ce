/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.input;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.editor.preview.input.ADataInput;
import com.jaspersoft.studio.editor.preview.input.IDataInput;
import com.jaspersoft.studio.server.editor.input.lov.ListOfValuesInput;
import com.jaspersoft.studio.server.editor.input.query.QueryInput;
import com.jaspersoft.studio.server.protocol.IConnection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class InputControlsManager {
	private List<ResourceDescriptor> inputcontrols;
	private Map<String, Object> defaults;
	private ResourceDescriptor rdrepunit;
	private IConnection wsclient;

	public InputControlsManager() {
	}

	public IConnection getWsClient() {
		return wsclient;
	}

	public void setWsclient(IConnection wsclient) {
		this.wsclient = wsclient;
	}

	public ResourceDescriptor getReportUnit() {
		return rdrepunit;
	}

	public Map<String, Object> getParameters() {
		return defaults;
	}

	public void getDefaults() {
		defaults = new HashMap<>();
		if (rdrepunit.getWsType().equals("ReportOptionsResource")) {
			ResourceProperty rp = rdrepunit.getResourceProperty("PROP_VALUES");

			List<ResourceProperty> list = rp.getProperties();
			if (list != null)
				for (ResourceProperty li : list) {
					if (!li.getProperties().isEmpty()) {
						List<String> listVal = new ArrayList<>();
						for (Object sli : li.getProperties())
							listVal.add(((ResourceProperty) sli).getValue());
						defaults.put(li.getName(), listVal);
					} else
						defaults.put(li.getName(), Misc.nvl(li.getValue()));
				}
		}
	}

	public List<ResourceDescriptor> getInputControls() {
		return inputcontrols;
	}

	public void initInputControls(ResourceDescriptor rdrepunit) {
		// if (rdrepunit.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT))
		this.rdrepunit = rdrepunit;
		inputcontrols = new ArrayList<>();
		if (rdrepunit != null)
			for (ResourceDescriptor sub_rd : rdrepunit.getChildren()) {
				String wsType = sub_rd.getWsType();
				if (wsType.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
					inputcontrols.add(sub_rd);
			}
	}

	private List<IDataInput> icontrols = new ArrayList<>();

	public List<IDataInput> getControls() {
		return icontrols;
	}

	private PropertyChangeListener propChangeListener = new PropertyChangeListener() {
		private int started = 0;
		private boolean ended = true;
		private IDataInput control;

		public void propertyChange(PropertyChangeEvent evt) {
			final Object source = evt.getSource();
			if (source instanceof IDataInput) {
				control = (IDataInput) source;
				doCascade();
			}
		}

		protected void doCascade() {
			started++;
			if (ended) {
				ended = false;
				Job job = new Job("Update Cascading Input Controls") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("Update Controls", IProgressMonitor.UNKNOWN);

						Map<IDataInput, Map<String, Object>> toUpd = new HashMap<>();
						actionPerformed(control, toUpd);
						try {
							updateControls(toUpd, monitor);
							ended = true;
							started--;
							if (started > 0) {
								started = 0;
								doCascade();
							}
						} catch (Exception e) {
							UIUtils.showError(e);
						} finally {
							monitor.done();
						}
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

	public void actionPerformed(IDataInput ic, Map<IDataInput, Map<String, Object>> controls) {
		String icName = ((PResourceDescriptor) ic.getParameter()).getResourceDescriptor().getName();
		// get the first input control having this param in the list...
		for (IDataInput icToUpdate : icontrols) {
			if (icToUpdate == ic || controls.containsKey(icToUpdate))
				continue;
			ResourceDescriptor rd = ((PResourceDescriptor) icToUpdate.getParameter()).getResourceDescriptor();
			List<String> parametersICs = rd.getMasterInputControls();
			if (parametersICs == null || !parametersICs.contains(icName))
				continue;

			Map<String, Object> parameters = new HashMap<>();
			Map<String, Object> prms = getParameters();
			for (String paramName : parametersICs)
				parameters.put(paramName, prms.get(paramName));
			controls.put(icToUpdate, parameters);
			actionPerformed(icToUpdate, controls);
			break;
		}
	}

	private void updateControls(final Map<IDataInput, Map<String, Object>> controls, IProgressMonitor monitor)
			throws Exception {
		List<ResourceDescriptor> rds = new ArrayList<>();
		for (IDataInput ic : controls.keySet()) {
			ResourceDescriptor rd = ((PResourceDescriptor) ic.getParameter()).getResourceDescriptor();
			rd.setIcValues(controls.get(ic));
			rds.add(rd);
		}
		List<ResourceDescriptor> newRds = wsclient.cascadeInputControls(rdrepunit, rds, monitor);
		for (IDataInput ic : controls.keySet()) {
			ResourceDescriptor rd = ((PResourceDescriptor) ic.getParameter()).getResourceDescriptor();
			for (ResourceDescriptor r : newRds) {
				if (r != rd && r.getName().equals(rd.getName())) {
					// ok, replacing values
					rd.setListOfValues(r.getListOfValues());
					rd.setQueryData(r.getQueryData());
					rd.setValue(r.getValue());
				}
			}
		}
		UIUtils.getDisplay().syncExec(() -> {
			for (IDataInput ic : controls.keySet()) {
				if (ic instanceof QueryInput)
					((QueryInput) ic).fillTable();
				else if (ic instanceof ListOfValuesInput)
					((ListOfValuesInput) ic).fillTable();
				ic.updateInput();
			}
		});
	}

	public List<ResourceDescriptor> getICValues() {
		List<ResourceDescriptor> rds = new ArrayList<>();
		for (IDataInput ic : icontrols) {
			ResourceDescriptor rd = ((PResourceDescriptor) ic.getParameter()).getResourceDescriptor();
			rd.setIcValues(((ADataInput) ic).getParams());
			rds.add(rd);
		}
		return rds;
	}

	public boolean isAnyVisible() {
		for (ResourceDescriptor ic : inputcontrols) {
			if (isICVisible(ic))
				return true;
		}
		return false;
	}

	public static boolean isICSingle(ResourceDescriptor ic) {
		return ic.getControlType() == ResourceDescriptor.IC_TYPE_BOOLEAN
				|| ic.getControlType() == ResourceDescriptor.IC_TYPE_SINGLE_VALUE;
	}

	protected static boolean isICVisible(ResourceDescriptor ic) {
		return ic.getResourcePropertyValue(ResourceDescriptor.PROP_INPUTCONTROL_IS_VISIBLE) == null
				|| ic.getResourcePropertyValue(ResourceDescriptor.PROP_INPUTCONTROL_IS_VISIBLE).equals("true");
	}

	public static boolean isRDQuery(ResourceDescriptor sub_ic) {
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
