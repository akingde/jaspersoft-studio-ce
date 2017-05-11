/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.jasperserver.dto.reports.inputcontrols.ReportInputControl;
import com.jaspersoft.jasperserver.dto.resources.AbstractClientJdbcDataSource;
import com.jaspersoft.jasperserver.dto.resources.AbstractClientReportUnit;
import com.jaspersoft.jasperserver.dto.resources.AbstractClientReportUnit.ControlsLayoutType;
import com.jaspersoft.jasperserver.dto.resources.ClientAdhocDataView;
import com.jaspersoft.jasperserver.dto.resources.ClientAwsDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientBeanDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientCustomDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientDataType;
import com.jaspersoft.jasperserver.dto.resources.ClientDataType.TypeOfDataType;
import com.jaspersoft.jasperserver.dto.resources.ClientFile;
import com.jaspersoft.jasperserver.dto.resources.ClientInputControl;
import com.jaspersoft.jasperserver.dto.resources.ClientJdbcDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientJndiJdbcDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientListOfValues;
import com.jaspersoft.jasperserver.dto.resources.ClientListOfValuesItem;
import com.jaspersoft.jasperserver.dto.resources.ClientMondrianConnection;
import com.jaspersoft.jasperserver.dto.resources.ClientMondrianXmlaDefinition;
import com.jaspersoft.jasperserver.dto.resources.ClientOlapUnit;
import com.jaspersoft.jasperserver.dto.resources.ClientProperty;
import com.jaspersoft.jasperserver.dto.resources.ClientQuery;
import com.jaspersoft.jasperserver.dto.resources.ClientReference;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableFile;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableInputControl;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.jasperserver.dto.resources.ClientSecureMondrianConnection;
import com.jaspersoft.jasperserver.dto.resources.ClientSubDataSourceReference;
import com.jaspersoft.jasperserver.dto.resources.ClientVirtualDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientXmlaConnection;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceCustom;
import com.jaspersoft.studio.server.model.datasource.filter.DatasourcesAllFilter;
import com.jaspersoft.studio.server.utils.RDUtil;

import net.sf.jasperreports.eclipse.util.Misc;

public class Rest2Soap {

	public static ResourceDescriptor getRDLookup(ARestV2Connection rc, ClientResourceLookup cr) throws ParseException {
		ResourceDescriptor rd = getRD(rc, cr);
		// rd.setWsType(WsTypes.INST().toSoapType(cr.getResourceType()));
		return rd;
	}

	public static ResourceDescriptor getRD(ARestV2Connection rc, ClientResource<?> cr) throws ParseException {
		ResourceDescriptor rd = new ResourceDescriptor();
		getRD(rc, cr, rd);
		return rd;
	}

	public static ResourceDescriptor getRDContainer(ARestV2Connection rc, ClientResource<?> cr) throws ParseException {
		ResourceDescriptor rd = new ResourceDescriptor();
		getRD(rc, cr, rd);
		if (!(RDUtil.getParentFolder(cr.getUri()).endsWith("_files"))) {
			rd.setIsReference(true);
			rd.setReferenceUri(cr.getUri());
		}
		return rd;
	}

	public static ResourceDescriptor getDataSource(ARestV2Connection rc, ClientReferenceableDataSource crds)
			throws ParseException {
		if (crds instanceof ClientReference) {
			ResourceDescriptor rd = new ResourceDescriptor();
			rd.setReferenceUri(crds.getUri());
			rd.setIsReference(true);
			rd.setWsType(ResourceDescriptor.TYPE_REFERENCE);
			return rd;
		}
		return getRDContainer(rc, (ClientResource<?>) crds);
	}

	public static ResourceDescriptor getRD(ARestV2Connection rc, ClientResource<?> cr, ResourceDescriptor rd)
			throws ParseException {
		rd.getChildren().clear();
		rd.setWsType(WsTypes.INST().toSoapType(cr));
		rd.setIsNew(false);

		rd.setParentFolder(RDUtil.getParentFolder(cr.getUri()));
		rd.setUriString(cr.getUri());
		rd.setLabel(cr.getLabel());
		rd.setDescription(cr.getDescription());
		rd.setName(RDUtil.getID(cr.getUri()));
		rd.setVersion(Misc.nvl(cr.getVersion(), 0));

		rd.setCreationDate(rc.toTimestamp(cr.getCreationDate()));
		DiffFields.setSoapValue(rd, DiffFields.UPDATEDATE, cr.getUpdateDate());
		rd.setPermissionMask(Misc.nvl(cr.getPermissionMask(), 0));

		// look recursively
		if (cr instanceof ClientDataType)
			getDataType(rc, (ClientDataType) cr, rd);

		else if (cr instanceof ClientAdhocDataView)
			getAdhocDataView(rc, (ClientAdhocDataView) cr, rd);

		else if (cr instanceof ClientJdbcDataSource)
			getJdbcDataSource(rc, (ClientJdbcDataSource) cr, rd);
		else if (cr instanceof ClientJndiJdbcDataSource)
			getJndiDataSource(rc, (ClientJndiJdbcDataSource) cr, rd);
		else if (cr instanceof ClientAwsDataSource)
			getAWSDataSource(rc, (ClientAwsDataSource) cr, rd);
		else if (cr instanceof ClientVirtualDataSource)
			getVirtualDataSource(rc, (ClientVirtualDataSource) cr, rd);
		else if (cr instanceof ClientCustomDataSource)
			getCustomDataSource(rc, (ClientCustomDataSource) cr, rd);
		else if (cr instanceof ClientBeanDataSource)
			getBeanDataSource(rc, (ClientBeanDataSource) cr, rd);
		else if (cr instanceof ClientOlapUnit)
			getOlapUnit(rc, (ClientOlapUnit) cr, rd);

		else if (cr instanceof ClientQuery)
			getQuery(rc, (ClientQuery) cr, rd);

		else if (cr instanceof ClientXmlaConnection)
			getXmlaConnection(rc, (ClientXmlaConnection) cr, rd);
		else if (cr instanceof ClientMondrianConnection)
			getMondrianConnection(rc, (ClientMondrianConnection) cr, rd);
		else if (cr instanceof ClientSecureMondrianConnection)
			getSecureMondrianConnection(rc, (ClientSecureMondrianConnection) cr, rd);
		else if (cr instanceof ClientMondrianXmlaDefinition)
			getMondrianXmlaDefinition(rc, (ClientMondrianXmlaDefinition) cr, rd);

		else if (cr instanceof ClientListOfValues)
			getLOV(rc, (ClientListOfValues) cr, rd);
		else if (cr instanceof AbstractClientReportUnit)
			getReportUnit(rc, (AbstractClientReportUnit<?>) cr, rd);
		else if (cr instanceof ClientInputControl)
			getInputControl(rc, (ClientInputControl) cr, rd);
		else if (cr instanceof ClientFile)
			getFile(rc, (ClientFile) cr, rd);
		else
			rd = Misc.nvl(Activator.getExtManager().getRD(rc, cr, rd), rd);

		return rd;
	}

	private static void getAdhocDataView(ARestV2Connection rc, ClientAdhocDataView cr, ResourceDescriptor rd)
			throws ParseException {
		if (cr.getDataSource() != null) {
			ResourceDescriptor r = getDataSource(rc, cr.getDataSource());
			setupReference(rd, r);
			rd.getChildren().add(r);
		}
	}

	private static void getQuery(ARestV2Connection rc, ClientQuery cr, ResourceDescriptor rd) throws ParseException {
		rd.setResourceProperty(ResourceDescriptor.PROP_QUERY_LANGUAGE, cr.getLanguage());
		rd.setSql(cr.getValue());
		if (cr.getDataSource() != null) {
			ResourceDescriptor r = getDataSource(rc, cr.getDataSource());
			setupReference(rd, r);
			rd.getChildren().add(r);
		}
	}

	private static void getFile(ARestV2Connection rc, ClientFile cr, ResourceDescriptor rd) throws ParseException {
		// if (cr.getContent() != null)
		// rd.setData(cr.getContent().getBytes());
	}

	private static void getLOV(ARestV2Connection rc, ClientListOfValues cr, ResourceDescriptor rd)
			throws ParseException {
		List<ListItem> lovs = new ArrayList<ListItem>();
		if (cr.getItems() != null)
			for (ClientListOfValuesItem sds : cr.getItems())
				lovs.add(new ListItem(sds.getLabel(), sds.getValue()));
		rd.setListOfValues(lovs);
	}

	private static void getMondrianConnection(ARestV2Connection rc, ClientMondrianConnection cr, ResourceDescriptor rd)
			throws ParseException {
		if (cr.getDataSource() != null) {
			ResourceDescriptor r = getDataSource(rc, cr.getDataSource());
			setupReference(rd, r);
			rd.getChildren().add(r);
		}
		if (cr.getSchema() != null) {
			ResourceDescriptor r = getRDContainer(rc, (ClientResource<?>) cr.getSchema());
			setupReference(rd, r);
			rd.getChildren().add(r);
		}
	}

	private static void getSecureMondrianConnection(ARestV2Connection rc, ClientSecureMondrianConnection cr,
			ResourceDescriptor rd) throws ParseException {
		if (cr.getDataSource() != null) {
			ResourceDescriptor r = getDataSource(rc, cr.getDataSource());
			rd.getChildren().add(r);
		}
		if (cr.getSchema() != null) {
			ResourceDescriptor r = getRDContainer(rc, (ClientResource<?>) cr.getSchema());
			rd.getChildren().add(r);
		}
		if (cr.getAccessGrants() != null) {
			for (ClientReferenceableFile crf : cr.getAccessGrants()) {
				ResourceDescriptor ard = getRDContainer(rc, (ClientResource<?>) crf);
				ard.setWsType(ResourceDescriptor.TYPE_ACCESS_GRANT_SCHEMA);
				setupReference(rd, ard);
				rd.getChildren().add(ard);
			}
		}
	}

	private static void getMondrianXmlaDefinition(ARestV2Connection rc, ClientMondrianXmlaDefinition cr,
			ResourceDescriptor rd) throws ParseException {
		rd.setResourceProperty(ResourceDescriptor.PROP_XMLA_CATALOG, cr.getCatalog());
		if (cr.getMondrianConnection() != null) {
			ResourceDescriptor r = getRDContainer(rc, (ClientResource<?>) cr.getMondrianConnection());
			setupReference(rd, r);
			rd.getChildren().add(r);
		}
	}

	private static void getXmlaConnection(ARestV2Connection rc, ClientXmlaConnection cr, ResourceDescriptor rd)
			throws ParseException {
		rd.setResourceProperty(ResourceDescriptor.PROP_XMLA_URI, Misc.nvl(cr.getUrl()));
		rd.setResourceProperty(ResourceDescriptor.PROP_XMLA_DATASOURCE, Misc.nvl(cr.getDataSource()));
		rd.setResourceProperty(ResourceDescriptor.PROP_XMLA_CATALOG, Misc.nvl(cr.getCatalog()));
		rd.setResourceProperty(ResourceDescriptor.PROP_XMLA_USERNAME, Misc.nvl(cr.getUsername()));
		rd.setResourceProperty(ResourceDescriptor.PROP_XMLA_PASSWORD, Misc.nvl(cr.getPassword()));
	}

	private static void getBeanDataSource(ARestV2Connection rc, ClientBeanDataSource cr, ResourceDescriptor rd)
			throws ParseException {
		rd.setBeanName(cr.getBeanName());
		rd.setBeanMethod(cr.getBeanMethod());
	}

	private static void getOlapUnit(ARestV2Connection rc, ClientOlapUnit cr, ResourceDescriptor rd)
			throws ParseException {
		rd.setSql(cr.getMdxQuery());
		rd.setResourceProperty(ResourceDescriptor.PROP_QUERY_LANGUAGE, "mdx");
		if (cr.getOlapConnection() != null) {
			ResourceDescriptor r = getRDContainer(rc, (ClientResource<?>) cr.getOlapConnection());
			setupReference(rd, r);
			rd.getChildren().add(r);
		}
	}

	private static void getCustomDataSource(ARestV2Connection rc, ClientCustomDataSource cr, ResourceDescriptor rd)
			throws ParseException {
		rd.setServiceClass(cr.getServiceClass());
		if (cr.getProperties() != null) {
			ResourceProperty rp = new ResourceProperty(MRDatasourceCustom.PROP_DATASOURCE_CUSTOM_PROPERTY_MAP);
			List<ResourceProperty> props = new ArrayList<ResourceProperty>();
			for (ClientProperty cp : cr.getProperties())
				props.add(new ResourceProperty(cp.getKey(), cp.getValue()));
			rp.setProperties(props);
			rd.setResourceProperty(rp);
		}
		DiffFields.setSoapValue(rd, DiffFields.DATASOURCENAME, cr.getDataSourceName());
	}

	public static void getJdbcDataSource(ARestV2Connection rc, AbstractClientJdbcDataSource<?> cr,
			ResourceDescriptor rd) throws ParseException {
		rd.setDriverClass(cr.getDriverClass());
		rd.setPassword(cr.getPassword());
		rd.setUsername(cr.getUsername());
		rd.setConnectionUrl(cr.getConnectionUrl());
		DiffFields.setSoapValue(rd, DiffFields.TIMEZONE, cr.getTimezone());
	}

	private static void getJndiDataSource(ARestV2Connection rc, ClientJndiJdbcDataSource cr, ResourceDescriptor rd)
			throws ParseException {
		rd.setJndiName(cr.getJndiName());
		DiffFields.setSoapValue(rd, DiffFields.TIMEZONE, cr.getTimezone());
	}

	private static void getAWSDataSource(ARestV2Connection rc, ClientAwsDataSource cr, ResourceDescriptor rd)
			throws ParseException {
		getJdbcDataSource(rc, cr, rd);
		DiffFields.setSoapValue(rd, DiffFields.ACCESSKEY, cr.getAccessKey());
		DiffFields.setSoapValue(rd, DiffFields.SECRETKEY, cr.getSecretKey());
		DiffFields.setSoapValue(rd, DiffFields.ROLEARN, cr.getRoleArn());
		DiffFields.setSoapValue(rd, DiffFields.REGION, cr.getRegion());
		DiffFields.setSoapValue(rd, DiffFields.DBNAME, cr.getDbName());
		DiffFields.setSoapValue(rd, DiffFields.DBINSTANCEIDENTIFIER, cr.getDbInstanceIdentifier());
		DiffFields.setSoapValue(rd, DiffFields.DBSERVICE, cr.getDbService());
		DiffFields.setSoapValue(rd, DiffFields.TIMEZONE, cr.getTimezone());
	}

	private static void getVirtualDataSource(ARestV2Connection rc, ClientVirtualDataSource cr, ResourceDescriptor rd)
			throws ParseException {
		if (cr.getSubDataSources() != null)
			for (ClientSubDataSourceReference sds : cr.getSubDataSources()) {
				ResourceDescriptor r = new ResourceDescriptor();
				r.setName(sds.getId());
				r.setLabel(sds.getId());
				r.setIsReference(true);
				r.setReferenceUri(sds.getUri());
				r.setWsType(ResourceDescriptor.TYPE_DATASOURCE);
				r.setIsNew(true);
				r.setResourceProperty("PROP_DATASOURCE_SUB_DS_ID", sds.getId());
				r.setUriString(sds.getUri());
				setupReference(rd, r);
				rd.getChildren().add(r);
			}
	}

	private static void getDataType(ARestV2Connection rc, ClientDataType cr, ResourceDescriptor rd)
			throws ParseException {
		if (cr.getType().equals(TypeOfDataType.text))
			rd.setDataType(ResourceDescriptor.DT_TYPE_TEXT);
		else if (cr.getType().equals(TypeOfDataType.number))
			rd.setDataType(ResourceDescriptor.DT_TYPE_NUMBER);
		else if (cr.getType().equals(TypeOfDataType.date))
			rd.setDataType(ResourceDescriptor.DT_TYPE_DATE);
		else if (cr.getType().equals(TypeOfDataType.datetime))
			rd.setDataType(ResourceDescriptor.DT_TYPE_DATE_TIME);
		else if (cr.getType().equals(TypeOfDataType.time))
			rd.setDataType((byte) 5);

		// rd.setDataType((byte) (cr.getType().ordinal() + 1));
		rd.setPattern(cr.getPattern());
		rd.setMaxValue(cr.getMaxValue());
		rd.setStrictMax(cr.isStrictMax());
		rd.setMinValue(cr.getMinValue());
		rd.setStrictMin(cr.isStrictMin());
		DiffFields.setSoapValue(rd, DiffFields.MAXLENGHT, Misc.nvl(cr.getMaxLength(), new Integer(0)));
	}

	private static void getReportUnit(ARestV2Connection rc, AbstractClientReportUnit<?> cr, ResourceDescriptor rd)
			throws ParseException {
		rd.setResourceProperty(ResourceDescriptor.PROP_RU_ALWAYS_PROPMT_CONTROLS, cr.isAlwaysPromptControls());
		rd.setResourceProperty(ResourceDescriptor.PROP_RU_INPUTCONTROL_RENDERING_VIEW,
				cr.getInputControlRenderingView());
		rd.setResourceProperty(ResourceDescriptor.PROP_RU_REPORT_RENDERING_VIEW, cr.getReportRenderingView());
		if (cr.getControlsLayout() == ControlsLayoutType.popupScreen)
			rd.setResourceProperty(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT,
					ResourceDescriptor.RU_CONTROLS_LAYOUT_POPUP_SCREEN);
		if (cr.getControlsLayout() == ControlsLayoutType.separatePage)
			rd.setResourceProperty(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT,
					ResourceDescriptor.RU_CONTROLS_LAYOUT_SEPARATE_PAGE);
		if (cr.getControlsLayout() == ControlsLayoutType.topOfPage)
			rd.setResourceProperty(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT,
					ResourceDescriptor.RU_CONTROLS_LAYOUT_TOP_OF_PAGE);
		if (cr.getControlsLayout() == ControlsLayoutType.inPage)
			rd.setResourceProperty(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT, 4);

		rd.getChildren().clear();

		if (cr.getDataSource() != null) {
			ResourceDescriptor dataSource = getDataSource(rc, cr.getDataSource());
			setupReference(rd, dataSource);
			rd.getChildren().add(dataSource);
		}
		if (cr.getQuery() != null) {
			ResourceDescriptor rdContainer = getRDContainer(rc, (ClientQuery) cr.getQuery());
			setupReference(rd, rdContainer);
			rd.getChildren().add(rdContainer);
		}
		if (cr.getJrxml() != null) {
			ResourceDescriptor mjrxml = getRDContainer(rc, (ClientResource<?>) cr.getJrxml());
			mjrxml.setMainReport(true);
			setupReference(rd, mjrxml);
			rd.getChildren().add(mjrxml);
		}

		if (cr.getInputControls() != null)
			for (ClientReferenceableInputControl cric : cr.getInputControls()) {
				ResourceDescriptor r = getRDContainer(rc, (ClientResource<?>) cric);
				setupReference(rd, r);
				rd.getChildren().add(r);
			}
		if (cr.getFiles() != null)
			for (String key : cr.getFiles().keySet()) {
				ClientReferenceableFile crf = cr.getFiles().get(key);
				ResourceDescriptor r = getRDContainer(rc, (ClientResource<?>) crf);
				r.setName(key);
				setupReference(rd, r);
				rd.getChildren().add(r);
			}
		Collections.sort(rd.getChildren(), new Comparator<ResourceDescriptor>() {

			@Override
			public int compare(ResourceDescriptor arg0, ResourceDescriptor arg1) {
				if (arg0.getLabel() == arg1.getLabel())
					return 0;
				if (arg0.getLabel() == null)
					return -1;
				if (arg1.getLabel() == null)
					return 1;
				String wsType0 = arg0.getWsType();
				String wsType1 = arg1.getWsType();
				if (wsType0.equals(wsType1)) {
					if (wsType0.equals(ResourceDescriptor.TYPE_JRXML)) {
						if (arg0.isMainReport())
							return -1;
						if (arg1.isMainReport())
							return 1;
						return arg0.getLabel().compareToIgnoreCase(arg1.getLabel());
					} else if (wsType0.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
						// ignore input controls
						return 0;
					return arg0.getLabel().compareToIgnoreCase(arg1.getLabel());
				}
				if (DatasourcesAllFilter.getTypes().contains(wsType0))
					return -1;
				if (DatasourcesAllFilter.getTypes().contains(wsType1))
					return 1;
				if (wsType0.equals(ResourceDescriptor.TYPE_JRXML))
					return -1;
				if (wsType1.equals(ResourceDescriptor.TYPE_JRXML))
					return 1;
				if (wsType0.equals(ResourceDescriptor.TYPE_QUERY))
					return -1;
				if (wsType1.equals(ResourceDescriptor.TYPE_QUERY))
					return 1;
				if (wsType0.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
					return -1;
				if (wsType1.equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
					return 1;

				return wsType0.compareTo(wsType1);
			}
		});
	}

	private static void setupReference(ResourceDescriptor parent, ResourceDescriptor rd) {
		if (rd != null && rd.getParentFolder() != null) {
			if (!rd.getParentFolder().equals(parent.getUriString() + "_files"))
				rd.setIsReference(true);
		}
	}

	private static void getInputControl(ARestV2Connection rc, ClientInputControl cr, ResourceDescriptor rd)
			throws ParseException {
		rd.setMandatory(cr.isMandatory());
		rd.setReadOnly(cr.isReadOnly());
		rd.setVisible(cr.isVisible());
		if (cr.getListOfValues() != null) {
			ResourceDescriptor r = getRDContainer(rc, (ClientListOfValues) cr.getListOfValues());
			setupReference(rd, r);
			rd.getChildren().add(r);
		}
		if (cr.getQuery() != null) {
			ResourceDescriptor r = getRDContainer(rc, (ClientQuery) cr.getQuery());
			setupReference(rd, r);
			rd.getChildren().add(r);
		}
		if (cr.getDataType() != null) {
			ResourceDescriptor r = getRDContainer(rc, (ClientDataType) cr.getDataType());
			setupReference(rd, r);
			rd.getChildren().add(r);
		}

		rd.setControlType(cr.getType());
		rd.setQueryValueColumn(cr.getValueColumn());
		if (cr.getVisibleColumns() != null)
			rd.setQueryVisibleColumns(cr.getVisibleColumns().toArray(new String[cr.getVisibleColumns().size()]));
	}

	public static ResourceDescriptor getInputControl(ARestV2Connection rc, ReportInputControl cr, ResourceDescriptor rd)
			throws ParseException {
		rd.setName(cr.getId());
		rd.setUriString(cr.getUri().replaceFirst("repo:", ""));
		rd.setLabel(cr.getLabel());
		rd.setDescription(cr.getDescription());
		rd.setWsType(ResourceDescriptor.TYPE_INPUT_CONTROL);
		rd.setValue(cr);
		rd.setMasterInputControls(cr.getMasterDependencies());
		rd.setMandatory(cr.getMandatory());
		rd.setReadOnly(cr.getReadOnly());
		rd.setVisible(cr.getVisible());
		String rtype = cr.getType();
		if (rtype.equals("bool"))
			rd.setControlType((byte) 1);
		else if (rtype.equals("singleValue"))
			rd.setControlType((byte) 2);
		else if (rtype.equals("singleValueNumber")) {
			rd.setControlType((byte) 2);
			ResourceDescriptor r = new ResourceDescriptor();
			r.setWsType(ResourceDescriptor.TYPE_DATA_TYPE);
			r.setDataType(ResourceDescriptor.DT_TYPE_NUMBER);
			setupReference(rd, r);
			rd.getChildren().add(r);
		} else if (rtype.equals("singleValueDate")) {
			rd.setControlType((byte) 2);
			ResourceDescriptor r = new ResourceDescriptor();
			r.setWsType(ResourceDescriptor.TYPE_DATA_TYPE);
			r.setDataType(ResourceDescriptor.DT_TYPE_DATE);
			setupReference(rd, r);
			rd.getChildren().add(r);
		} else if (rtype.equals("singleValueText")) {
			rd.setControlType((byte) 2);
			ResourceDescriptor r = new ResourceDescriptor();
			r.setWsType(ResourceDescriptor.TYPE_DATA_TYPE);
			r.setDataType(ResourceDescriptor.DT_TYPE_TEXT);
			setupReference(rd, r);
			rd.getChildren().add(r);
		} else if (rtype.equals("singleValueDatetime")) {
			rd.setControlType((byte) 2);
			ResourceDescriptor r = new ResourceDescriptor();
			r.setWsType(ResourceDescriptor.TYPE_DATA_TYPE);
			r.setDataType(ResourceDescriptor.DT_TYPE_DATE_TIME);
			setupReference(rd, r);
			rd.getChildren().add(r);
		} else if (rtype.equals("singleSelect"))
			rd.setControlType((byte) 3);
		else if (rtype.equals("singleSelect"))
			rd.setControlType((byte) 4);
		else if (rtype.equals("multiValue"))
			rd.setControlType((byte) 5);
		else if (rtype.equals("multiSelect"))
			rd.setControlType((byte) 6);
		else if (rtype.equals("multiSelect"))
			rd.setControlType((byte) 7);
		else if (rtype.equals("multiSelect"))
			rd.setControlType((byte) 8);
		else if (rtype.equals("singleSelectRadio"))
			rd.setControlType((byte) 9);
		else if (rtype.equals("multiSelectCheckbox"))
			rd.setControlType((byte) 10);
		else if (rtype.equals("multiSelectCheckbox"))
			rd.setControlType((byte) 11);

		return rd;
	}

}
