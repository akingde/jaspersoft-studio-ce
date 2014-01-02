package com.jaspersoft.studio.server.protocol.restv2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
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
import com.jaspersoft.jasperserver.dto.resources.ClientProperty;
import com.jaspersoft.jasperserver.dto.resources.ClientQuery;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableFile;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableInputControl;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableListOfValues;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableQuery;
import com.jaspersoft.jasperserver.dto.resources.ClientReportUnit;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientSubDataSourceReference;
import com.jaspersoft.jasperserver.dto.resources.ClientVirtualDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientXmlaConnection;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;
import com.jaspersoft.studio.utils.Misc;

public class Soap2Rest {

	public static ClientResource<?> getResource(ARestV2Connection rc, ResourceDescriptor rd) throws ParseException {
		ClientResource<?> cr = WsTypes.INST().createResource(rd);
		cr.setCreationDate(rc.timestamp2str(rd.getCreationDate()));
		cr.setLabel(rd.getLabel());
		cr.setDescription(rd.getDescription());
		cr.setUri(rd.getUriString());
		cr.setVersion(rd.getVersion());
		cr.setUpdateDate(DiffFields.getSoapValue(rd, DiffFields.UPDATEDATE));
		cr.setPermissionMask(DiffFields.getSoapValueInteger(rd, DiffFields.PERMISSIONMASK));

		if (rd.getWsType().equals(ResourceDescriptor.TYPE_IMAGE))
			getImage(rc, cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DATA_TYPE))
			getDataType(rc, (ClientDataType) cr, rd);

		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_ADHOC_DATA_VIEW))
			getAdhocDataView(rc, (ClientAdhocDataView) cr, rd);

		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE_JDBC))
			getJdbcDataSource(rc, (ClientJdbcDataSource) cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE_BEAN))
			getBeanDataSource(rc, (ClientBeanDataSource) cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE_JNDI))
			getJndiDataSource(rc, (ClientJndiJdbcDataSource) cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE_CUSTOM))
			getCustomDataSource(rc, (ClientCustomDataSource) cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE_AWS))
			getAWSDataSource(rc, (ClientAwsDataSource) cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_DATASOURCE_VIRTUAL))
			getVirtualDataSource(rc, (ClientVirtualDataSource) cr, rd);

		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_QUERY))
			getQuery(rc, (ClientQuery) cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
			getInputControl(rc, (ClientInputControl) cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_LOV))
			getLOV(rc, (ClientListOfValues) cr, rd);

		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_OLAP_XMLA_CONNECTION))
			getXmlaConnection(rc, (ClientXmlaConnection) cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_OLAP_MONDRIAN_CONNECTION))
			getMondrianConnection(rc, (ClientMondrianConnection) cr, rd);

		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_REPORTUNIT))
			getReportUnit(rc, (ClientReportUnit) cr, rd);

		else
			Activator.getExtManager().getResource(rc, cr, rd);
		return cr;
	}

	private static void getAdhocDataView(ARestV2Connection rc, ClientAdhocDataView cr, ResourceDescriptor rd) throws ParseException {
		List<ResourceDescriptor> children = rd.getChildren();
		for (ResourceDescriptor r : children)
			if (SelectorDatasource.isDatasource(r))
				cr.setDataSource((ClientReferenceableDataSource) getResource(rc, r));
	}

	private static void getLOV(ARestV2Connection rc, ClientListOfValues cr, ResourceDescriptor rd) {
		List<ClientListOfValuesItem> lovs = new ArrayList<ClientListOfValuesItem>();
		if (rd.getListOfValues() != null)
			for (ListItem l : (List<ListItem>) rd.getListOfValues())
				lovs.add(new ClientListOfValuesItem(l.getLabel(), (String) l.getValue()));
		cr.setItems(lovs);
	}

	private static void getMondrianConnection(ARestV2Connection rc, ClientMondrianConnection cr, ResourceDescriptor rd) throws ParseException {
		for (ResourceDescriptor r : (List<ResourceDescriptor>) rd.getChildren()) {
			if (SelectorDatasource.isDatasource(r))
				cr.setDataSource((ClientReferenceableDataSource) getResource(rc, r));
			else if (r.getWsType().equals(ResourceDescriptor.TYPE_MONDRIAN_SCHEMA))
				cr.setSchema((ClientReferenceableFile) getResource(rc, r));
		}
	}

	private static void getXmlaConnection(ARestV2Connection rc, ClientXmlaConnection cr, ResourceDescriptor rd) {
		cr.setUrl(DiffFields.getSoapValue(rd, DiffFields.URL));
		cr.setDataSource(DiffFields.getSoapValue(rd, DiffFields.DATASOURCE));
		cr.setCatalog(DiffFields.getSoapValue(rd, DiffFields.CATALOG));
		cr.setUsername(DiffFields.getSoapValue(rd, DiffFields.USERNAME));
		cr.setPassword(DiffFields.getSoapValue(rd, DiffFields.PASSWORD));
	}

	private static void getVirtualDataSource(ARestV2Connection rc, ClientVirtualDataSource cr, ResourceDescriptor rd) {
		List<ClientSubDataSourceReference> ds = new ArrayList<ClientSubDataSourceReference>();
		for (ResourceDescriptor r : (List<ResourceDescriptor>) rd.getChildren()) {
			ClientSubDataSourceReference d = new ClientSubDataSourceReference();
			d.setId(r.getResourcePropertyValue("PROP_DATASOURCE_SUB_DS_ID"));
			d.setUri(r.getReferenceUri());
			ds.add(d);
		}
		cr.setSubDataSources(ds);
	}

	private static void getAWSDataSource(ARestV2Connection rc, ClientAwsDataSource cr, ResourceDescriptor rd) {
		cr.setAccessKey(DiffFields.getSoapValue(rd, DiffFields.ACCESSKEY));
		cr.setSecretKey(DiffFields.getSoapValue(rd, DiffFields.SECRETKEY));
		cr.setRoleArn(DiffFields.getSoapValue(rd, DiffFields.ROLEARN));
		cr.setRegion(DiffFields.getSoapValue(rd, DiffFields.REGION));
		cr.setDbName(DiffFields.getSoapValue(rd, DiffFields.DBNAME));
		cr.setDbInstanceIdentifier(DiffFields.getSoapValue(rd, DiffFields.DBINSTANCEIDENTIFIER));
		cr.setDbService(DiffFields.getSoapValue(rd, DiffFields.DBSERVICE));
		cr.setTimezone(DiffFields.getSoapValue(rd, DiffFields.TIMEZONE));
	}

	private static void getCustomDataSource(ARestV2Connection rc, ClientCustomDataSource cr, ResourceDescriptor rd) {
		cr.setServiceClass(rd.getServiceClass());
		cr.setDataSourceName(DiffFields.getSoapValue(rd, DiffFields.DATASOURCENAME));
		Map<String, String> map = rd.getPropertyMap();
		List<ClientProperty> props = new ArrayList<ClientProperty>();
		for (String key : map.keySet()) {
			if (key.equals("password") && Misc.isNullOrEmpty(map.get(key)))
				continue;
			props.add(new ClientProperty(key, map.get(key)));
		}
		cr.setProperties(props);
	}

	private static void getJndiDataSource(ARestV2Connection rc, ClientJndiJdbcDataSource cr, ResourceDescriptor rd) {
		cr.setJndiName(rd.getJndiName());
		cr.setTimezone(DiffFields.getSoapValue(rd, DiffFields.TIMEZONE));
	}

	private static void getBeanDataSource(ARestV2Connection rc, ClientBeanDataSource cr, ResourceDescriptor rd) {
		cr.setBeanName(rd.getBeanName());
		cr.setBeanMethod(rd.getBeanMethod());
	}

	private static void getJdbcDataSource(ARestV2Connection rc, ClientJdbcDataSource cr, ResourceDescriptor rd) {
		cr.setDriverClass(rd.getDriverClass());
		cr.setPassword(rd.getPassword());
		cr.setUsername(rd.getUsername());
		cr.setConnectionUrl(rd.getConnectionUrl());
		cr.setTimezone(DiffFields.getSoapValue(rd, DiffFields.TIMEZONE));
	}

	private static void getDataType(ARestV2Connection rc, ClientDataType cr, ResourceDescriptor rd) {
		switch (rd.getDataType()) {
		case 1:
			cr.setType(TypeOfDataType.text);
			break;
		case 2:
			cr.setType(TypeOfDataType.number);
			break;
		case 3:
			cr.setType(TypeOfDataType.date);
			break;
		case 4:
			cr.setType(TypeOfDataType.datetime);
			break;
		case 5:
			cr.setType(TypeOfDataType.time);
			break;
		}
		cr.setPattern(rd.getPattern());
		cr.setMaxValue(rd.getMaxValue());
		cr.setStrictMax(rd.isStrictMax());
		cr.setMinValue(rd.getMinValue());
		cr.setStrictMin(rd.isStrictMin());
		cr.setMaxLength(DiffFields.getSoapValueInteger(rd, DiffFields.MAXLENGHT));
	}

	private static void getQuery(ARestV2Connection rc, ClientQuery cr, ResourceDescriptor rd) throws ParseException {
		cr.setValue(rd.getSql());
		cr.setLanguage(rd.getResourcePropertyValue(ResourceDescriptor.PROP_QUERY_LANGUAGE));
		List<ResourceDescriptor> children = rd.getChildren();
		for (ResourceDescriptor r : children)
			if (SelectorDatasource.isDatasource(r))
				cr.setDataSource((ClientReferenceableDataSource) getResource(rc, r));
	}

	private static void getImage(ARestV2Connection rc, ClientResource<?> cr, ResourceDescriptor rd) {

	}

	private static void getInputControl(ARestV2Connection rc, ClientInputControl cr, ResourceDescriptor rd) throws ParseException {
		cr.setType(rd.getControlType());
		cr.setValueColumn(rd.getQueryValueColumn());
		if (rd.getQueryVisibleColumns() != null)
			cr.setVisibleColumns(Arrays.asList(rd.getQueryVisibleColumns()));
		List<ResourceDescriptor> children = rd.getChildren();
		for (ResourceDescriptor r : children) {
			if (r.getWsType().equals(ResourceDescriptor.TYPE_LOV))
				cr.setListOfValues((ClientReferenceableListOfValues) getResource(rc, r));
			else if (r.getWsType().equals(ResourceDescriptor.TYPE_QUERY))
				cr.setQuery((ClientReferenceableQuery) getResource(rc, r));
		}
	}

	private static void getReportUnit(ARestV2Connection rc, ClientReportUnit cr, ResourceDescriptor rd) throws ParseException {
		cr.setAlwaysPromptControls(rd.getResourcePropertyValueAsBoolean(ResourceDescriptor.PROP_RU_ALWAYS_PROPMT_CONTROLS));
		cr.setInputControlRenderingView(rd.getResourcePropertyValue(ResourceDescriptor.PROP_RU_INPUTCONTROL_RENDERING_VIEW));
		cr.setReportRenderingView(rd.getResourcePropertyValue(ResourceDescriptor.PROP_RU_REPORT_RENDERING_VIEW));
		switch (rd.getResourcePropertyValueAsInteger(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT)) {
		case (int) ResourceDescriptor.RU_CONTROLS_LAYOUT_POPUP_SCREEN:
			cr.setControlsLayout(ControlsLayoutType.popupScreen);
			break;
		case (int) ResourceDescriptor.RU_CONTROLS_LAYOUT_SEPARATE_PAGE:
			cr.setControlsLayout(ControlsLayoutType.separatePage);
			break;
		case (int) ResourceDescriptor.RU_CONTROLS_LAYOUT_TOP_OF_PAGE:
			cr.setControlsLayout(ControlsLayoutType.topOfPage);
			break;
		case 4:
			cr.setControlsLayout(ControlsLayoutType.inPage);
			break;
		}
		List<ResourceDescriptor> children = rd.getChildren();
		for (ResourceDescriptor r : children) {
			if (SelectorDatasource.isDatasource(r))
				cr.setDataSource((ClientReferenceableDataSource) getResource(rc, r));
			else if (r.getWsType().equals(ResourceDescriptor.TYPE_QUERY))
				cr.setQuery((ClientReferenceableQuery) getResource(rc, r));
			else if (r.getWsType().equals(ResourceDescriptor.TYPE_JRXML))
				cr.setJrxml((ClientReferenceableFile) getResource(rc, r));
			else if (r.getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL))
				cr.getInputControls().add((ClientReferenceableInputControl) getResource(rc, r));
			else {
				ClientFile res = (ClientFile) getResource(rc, r);
				cr.getFiles().put(r.getLabel(), res);
			}
		}

	}

}
