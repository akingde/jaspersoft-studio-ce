/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.imp;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.data.designer.ICQuery;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.ic.ICParameterContributor;
import com.jaspersoft.studio.server.ic.ICTypes;
import com.jaspersoft.studio.server.model.MDataType;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.preferences.JRSPreferencesPage;
import com.jaspersoft.studio.server.publish.OverwriteEnum;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.server.publish.ResourcePublishMethod;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.types.date.DateRange;
import net.sf.jasperreports.types.date.TimestampRange;

public class ImpInputControls {
	protected JasperReportsConfiguration jrConfig;

	public ImpInputControls(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

	public void publish(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper,
			JasperReportsConfiguration jrConfig) throws Exception {
		ResourceDescriptor runit = mrunit.getValue();
		for (JRParameter p : jasper.getParametersList()) {
			if (p.isSystemDefined() || !p.isForPrompting())
				continue;
			ResourceDescriptor rd = MInputControl.createDescriptor(mrunit);
			rd.setName(IDStringValidator.safeChar(Misc.nvl(p.getName())));
			rd.setLabel(p.getName());
			rd.setDescription(p.getDescription());
			rd.setVisible(true);
			rd.setReadOnly(false);
			rd.setMandatory(false);
			rd.setResourceProperty(ResourceDescriptor.PROP_INPUTCONTROL_TYPE, ResourceDescriptor.IC_TYPE_SINGLE_VALUE);
			rd.setParentFolder(runit.getUriString() + MReportUnit.RU_SUFFIX);
			rd.setUriString(runit.getUriString() + MReportUnit.RU_SUFFIX + "/" + rd.getName());

			MInputControl mres = (MInputControl) ResourceFactory.getResource(mrunit, rd, -1);
			String v = p.getPropertiesMap().getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_TYPE);
			Class<?> pClazz = p.getValueClass();
			if (!Misc.isNullOrEmpty(v)) {
				if (v.equals(ICTypes.BOOLEAN.getValue()))
					rd.setControlType(ResourceDescriptor.IC_TYPE_BOOLEAN);
				else if (v.equals(ICTypes.VALUE.getValue())) {
					rd.setControlType(ResourceDescriptor.IC_TYPE_SINGLE_VALUE);
					if (String.class.isAssignableFrom(pClazz))
						addType(rd, mres, ResourceDescriptor.DT_TYPE_TEXT);
					else if (Time.class.isAssignableFrom(pClazz))
						addType(rd, mres, (byte) 5);
					else if (Timestamp.class.isAssignableFrom(pClazz) || TimestampRange.class.isAssignableFrom(pClazz))
						addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE_TIME);
					else if (Date.class.isAssignableFrom(pClazz) || DateRange.class.isAssignableFrom(pClazz))
						addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE);
					else if (Number.class.isAssignableFrom(pClazz))
						addType(rd, mres, ResourceDescriptor.DT_TYPE_NUMBER);
				} else {
					String qvalue = p.getPropertiesMap()
							.getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_VALUE);
					if (Collection.class.isAssignableFrom(pClazz) || pClazz.isArray()) {
						if (v.equals(ICTypes.SINGLE_LOV.getValue()) || v.equals(ICTypes.SINGLE_LOV_RADIO.getValue()))
							v = ICTypes.MULTI_LOV.getValue();
						else if (v.equals(ICTypes.SINGLE_QUERY.getValue())
								|| v.equals(ICTypes.SINGLE_QUERY_RADIO.getValue()))
							v = ICTypes.MULTI_QUERY.getValue();
					} else {
						if (v.equals(ICTypes.MULTI_LOV.getValue()) || v.equals(ICTypes.MULTI_LOV_CHECKBOX.getValue()))
							v = ICTypes.SINGLE_LOV.getValue();
						else if (v.equals(ICTypes.MULTI_QUERY.getValue())
								|| v.equals(ICTypes.MULTI_QUERY_CHECKBOX.getValue()))
							v = ICTypes.SINGLE_QUERY.getValue();
					}

					if (v.equals(ICTypes.SINGLE_LOV.getValue())) {
						rd.setControlType(ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES);
						setupLOV(rd, qvalue);
					} else if (v.equals(ICTypes.SINGLE_LOV_RADIO.getValue())) {
						rd.setControlType(ResourceDescriptor.IC_TYPE_SINGLE_SELECT_LIST_OF_VALUES_RADIO);
						setupLOV(rd, qvalue);
					} else if (v.equals(ICTypes.MULTI_LOV.getValue())) {
						rd.setControlType(ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES);
						setupLOV(rd, qvalue);
					} else if (v.equals(ICTypes.MULTI_LOV_CHECKBOX.getValue())) {
						rd.setControlType(ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES_CHECKBOX);
						setupLOV(rd, qvalue);
					} else if (v.equals(ICTypes.SINGLE_QUERY.getValue())) {
						rd.setControlType(ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY);
						setupQuery(rd, qvalue, p, jasper);
					} else if (v.equals(ICTypes.SINGLE_QUERY_RADIO.getValue())) {
						rd.setControlType(ResourceDescriptor.IC_TYPE_SINGLE_SELECT_QUERY_RADIO);
						setupQuery(rd, qvalue, p, jasper);
					} else if (v.equals(ICTypes.MULTI_QUERY.getValue())) {
						rd.setControlType(ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY);
						setupQuery(rd, qvalue, p, jasper);
					} else if (v.equals(ICTypes.MULTI_QUERY_CHECKBOX.getValue())) {
						rd.setControlType(ResourceDescriptor.IC_TYPE_MULTI_SELECT_QUERY_CHECKBOX);
						setupQuery(rd, qvalue, p, jasper);
					}
				}
			} else {
				if (Boolean.class.isAssignableFrom(pClazz)) {
					rd.setControlType(ResourceDescriptor.IC_TYPE_BOOLEAN);
				} else if (String.class.isAssignableFrom(pClazz)) {
					addType(rd, mres, ResourceDescriptor.DT_TYPE_TEXT);
				} else if (Time.class.isAssignableFrom(pClazz)) {
					addType(rd, mres, (byte) 5);
				} else if (Timestamp.class.isAssignableFrom(pClazz) || TimestampRange.class.isAssignableFrom(pClazz)) {
					addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE_TIME);
				} else if (Date.class.isAssignableFrom(pClazz) || DateRange.class.isAssignableFrom(pClazz)) {
					addType(rd, mres, ResourceDescriptor.DT_TYPE_DATE);
				} else if (Number.class.isAssignableFrom(pClazz)) {
					addType(rd, mres, ResourceDescriptor.DT_TYPE_NUMBER);
				} else if (Collection.class.isAssignableFrom(pClazz) || pClazz.isArray()) {
					rd.setControlType(ResourceDescriptor.IC_TYPE_MULTI_SELECT_LIST_OF_VALUES);

					ResourceDescriptor dt = new ResourceDescriptor();
					dt.setWsType(ResourceDescriptor.TYPE_LOV);
					dt.setName("lov_" + rd.getName());
					dt.setLabel("lov_" + rd.getName());
					dt.setIsNew(true);
					dt.setParentFolder(rd.getUriString() + MReportUnit.RU_SUFFIX);
					dt.setUriString(dt.getParentFolder() + "/" + rd.getName());
					// here maybe we could evaluate default parameter value to generate lov?
					dt.setListOfValues(new ArrayList<ListItem>());
					rd.getChildren().add(dt);
				} else {
					mrunit.removeChild(mres);
					continue;
				}
			}

			PublishOptions popt = AImpObject.createOptions(jrConfig, null);
			String b = jrConfig.getProperty(JRSPreferencesPage.PUBLISH_REPORT_OVERRIDEBYDEFAULT, "true");
			if (b.equals("true") && rd.getIsNew())
				popt.setOverwrite(OverwriteEnum.OVERWRITE);
			mres.setPublishOptions(popt);
			PublishUtil.loadPreferences(monitor, (IFile) jrConfig.get(FileUtils.KEY_FILE), mres);
			v = p.getPropertiesMap().getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_PATH);
			if (!Misc.isNullOrEmpty(v)) {
				rd.setUriString(v);
				popt.setPublishMethod(ResourcePublishMethod.REFERENCE);
				popt.setReferencedResource(rd);
				popt.setOverwrite(OverwriteEnum.IGNORE);
			} else {
				v = p.getPropertiesMap().getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_LABEL);
				if (!Misc.isNullOrEmpty(v))
					rd.setLabel(v);
			}
			PublishUtil.getResources(mrunit, monitor, jrConfig).add(mres);
		}
	}

	public void setupQuery(ResourceDescriptor rd, String qv, JRParameter p, JasperDesign jd) {
		ResourceDescriptor dataType = new ResourceDescriptor();
		dataType.setWsType(ResourceDescriptor.TYPE_QUERY);
		dataType.setName("query_" + p.getName());
		dataType.setLabel("query_" + p.getName());
		dataType.setIsNew(true);
		dataType.setParentFolder(rd.getUriString() + MReportUnit.RU_SUFFIX);
		dataType.setUriString(dataType.getParentFolder() + "/" + dataType.getName());
		if (jd.getQuery() != null)
			dataType.setResourceProperty("PROP_QUERY_LANGUAGE", jd.getQuery().getLanguage());
		if (qv != null)
			try {
				ICQuery value = new ObjectMapper().readValue(qv, ICQuery.class);
				if (value != null) {
					rd.setQueryValueColumn(value.valueField);
					if (value.columns != null)
						rd.setQueryVisibleColumns(value.columns.toArray(new String[value.columns.size()]));
					dataType.setSql(value.query);
					if (value.language != null)
						dataType.setResourceProperty("PROP_QUERY_LANGUAGE", value.language);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		String v = p.getPropertiesMap().getProperty(ICParameterContributor.PROPERTY_JS_INPUTCONTROL_DATASOURCE);
		if (!Misc.isNullOrEmpty(v)) {
			ResourceDescriptor ds = new ResourceDescriptor();
			ds.setWsType(ResourceDescriptor.TYPE_DATASOURCE);
			ds.setReferenceUri(v);
			ds.setIsReference(true);
			dataType.getChildren().add(ds);
		}
		rd.getChildren().add(dataType);
	}

	public void setupLOV(ResourceDescriptor rd, String qv) {
		rd.getChildren().clear();

		ResourceDescriptor dt = new ResourceDescriptor();
		dt.setWsType(ResourceDescriptor.TYPE_LOV);
		dt.setName("lov_" + rd.getName());
		dt.setLabel("lov_" + rd.getName());
		dt.setIsNew(true);
		dt.setParentFolder(rd.getUriString() + MReportUnit.RU_SUFFIX);
		dt.setUriString(dt.getParentFolder() + "/" + rd.getName());
		List<ListItem> values = new ArrayList<>();
		if (qv != null)
			try {
				values = new ObjectMapper().readValue(qv, new TypeReference<List<ListItem>>() {
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (values == null)
			values = new ArrayList<>();

		dt.setListOfValues(values);
		rd.getChildren().add(dt);
	}

	public static ResourceDescriptor addType(ResourceDescriptor rd, MInputControl mres, byte type) {
		ResourceDescriptor rdtype = MDataType.createDescriptor(mres);
		String name = "myDatatype";
		rdtype.setName(name);
		rdtype.setLabel(name);
		rdtype.setIsNew(true);
		rdtype.setDataType(type);
		rdtype.setIsReference(false);
		rdtype.setParentFolder(rd.getUriString() + MReportUnit.RU_SUFFIX);
		rdtype.setUriString(rdtype.getParentFolder() + "/" + name);

		rd.getChildren().add(rdtype);
		return rdtype;
	}
}
