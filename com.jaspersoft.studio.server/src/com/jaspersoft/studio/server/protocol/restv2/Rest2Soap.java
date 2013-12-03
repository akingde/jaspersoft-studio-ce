package com.jaspersoft.studio.server.protocol.restv2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ListItem;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.AbstractClientReportUnit.ControlsLayoutType;
import com.jaspersoft.jasperserver.dto.resources.ClientAwsDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientBeanDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientCustomDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientDataType;
import com.jaspersoft.jasperserver.dto.resources.ClientFile;
import com.jaspersoft.jasperserver.dto.resources.ClientInputControl;
import com.jaspersoft.jasperserver.dto.resources.ClientJdbcDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientJndiJdbcDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientListOfValues;
import com.jaspersoft.jasperserver.dto.resources.ClientListOfValuesItem;
import com.jaspersoft.jasperserver.dto.resources.ClientMondrianConnection;
import com.jaspersoft.jasperserver.dto.resources.ClientProperty;
import com.jaspersoft.jasperserver.dto.resources.ClientQuery;
import com.jaspersoft.jasperserver.dto.resources.ClientReference;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableFile;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableInputControl;
import com.jaspersoft.jasperserver.dto.resources.ClientReportUnit;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.jasperserver.dto.resources.ClientSubDataSourceReference;
import com.jaspersoft.jasperserver.dto.resources.ClientVirtualDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientXmlaConnection;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.utils.RDUtil;

public class Rest2Soap {

	public static ResourceDescriptor getRDLookup(RestV2Connection rc, ClientResourceLookup cr) throws ParseException {
		ResourceDescriptor rd = getRD(rc, cr);
		// rd.setWsType(WsTypes.INST().toSoapType(cr.getResourceType()));
		return rd;
	}

	public static ResourceDescriptor getRD(RestV2Connection rc, ClientResource<?> cr) throws ParseException {
		ResourceDescriptor rd = new ResourceDescriptor();
		getRD(rc, cr, rd);
		return rd;
	}

	public static ResourceDescriptor getRD(RestV2Connection rc, ClientResource<?> cr, ResourceDescriptor rd) throws ParseException {
		rd.setWsType(WsTypes.INST().toSoapType(cr));

		rd.setParentFolder(RDUtil.getParentFolder(cr.getUri()));
		rd.setUriString(cr.getUri());
		rd.setLabel(cr.getLabel());
		rd.setDescription(cr.getDescription());
		rd.setName(RDUtil.getID(cr.getUri()));
		rd.setVersion(cr.getVersion());

		rd.setCreationDate(rc.toTimestamp(cr.getCreationDate()));
		DiffFields.setSoapValue(rd, DiffFields.UPDATEDATE, cr.getUpdateDate());
		DiffFields.setSoapValue(rd, DiffFields.PERMISSIONMASK, cr.getPermissionMask());

		// look recursively
		if (cr instanceof ClientDataType)
			getDataType(rc, (ClientDataType) cr, rd);
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

		else if (cr instanceof ClientQuery)
			getQuery(rc, (ClientQuery) cr, rd);

		else if (cr instanceof ClientXmlaConnection)
			getXmlaConnection(rc, (ClientXmlaConnection) cr, rd);
		else if (cr instanceof ClientMondrianConnection)
			getMondrianConnection(rc, (ClientMondrianConnection) cr, rd);

		else if (cr instanceof ClientListOfValues)
			getLOV(rc, (ClientListOfValues) cr, rd);
		else if (cr instanceof ClientReportUnit)
			getReportUnit(rc, (ClientReportUnit) cr, rd);
		else if (cr instanceof ClientInputControl)
			getInputControl(rc, (ClientInputControl) cr, rd);
		else if (cr instanceof ClientFile)
			getFile(rc, (ClientFile) cr, rd);
		else
			rd = Activator.getExtManager().getRD(rc, cr, rd);

		return rd;
	}

	private static void getQuery(RestV2Connection rc, ClientQuery cr, ResourceDescriptor rd) throws ParseException {
		rd.setResourceProperty(ResourceDescriptor.PROP_QUERY_LANGUAGE, cr.getLanguage());
		rd.setSql(cr.getValue());
		if (cr.getDataSource() != null)
			rd.getChildren().add(getRD(rc, (ClientResource<?>) cr.getDataSource()));
	}

	private static void getFile(RestV2Connection rc, ClientFile cr, ResourceDescriptor rd) throws ParseException {
		cr.getType();
		cr.getContent();
	}

	private static void getLOV(RestV2Connection rc, ClientListOfValues cr, ResourceDescriptor rd) throws ParseException {
		List<ListItem> lovs = new ArrayList<ListItem>();
		for (ClientListOfValuesItem sds : cr.getItems())
			lovs.add(new ListItem(sds.getLabel(), sds.getValue()));
		rd.setListOfValues(lovs);
	}

	private static void getMondrianConnection(RestV2Connection rc, ClientMondrianConnection cr, ResourceDescriptor rd) throws ParseException {
		if (cr.getDataSource() != null)
			rd.getChildren().add(getRD(rc, (ClientResource<?>) cr.getDataSource()));
		if (cr.getSchema() != null)
			rd.getChildren().add(getRD(rc, (ClientResource<?>) cr.getSchema()));
	}

	private static void getXmlaConnection(RestV2Connection rc, ClientXmlaConnection cr, ResourceDescriptor rd) throws ParseException {
		DiffFields.setSoapValue(rd, DiffFields.URL, cr.getUrl());
		DiffFields.setSoapValue(rd, DiffFields.DATASOURCE, cr.getDataSource());
		DiffFields.setSoapValue(rd, DiffFields.CATALOG, cr.getCatalog());
		DiffFields.setSoapValue(rd, DiffFields.USERNAME, cr.getUsername());
		DiffFields.setSoapValue(rd, DiffFields.PASSWORD, cr.getPassword());
	}

	private static void getBeanDataSource(RestV2Connection rc, ClientBeanDataSource cr, ResourceDescriptor rd) throws ParseException {
		rd.setBeanName(cr.getBeanName());
		rd.setBeanMethod(cr.getBeanMethod());
	}

	private static void getCustomDataSource(RestV2Connection rc, ClientCustomDataSource cr, ResourceDescriptor rd) throws ParseException {
		rd.setServiceClass(cr.getServiceClass());
		if (cr.getProperties() != null) {
			Map<String, String> map = new HashMap<String, String>();
			for (ClientProperty cp : cr.getProperties())
				map.put(cp.getKey(), cp.getValue());
			rd.setPropertyMap(map);
		}
		DiffFields.setSoapValue(rd, DiffFields.DATASOURCENAME, cr.getDataSourceName());
	}

	private static void getJdbcDataSource(RestV2Connection rc, ClientJdbcDataSource cr, ResourceDescriptor rd) throws ParseException {
		rd.setDriverClass(cr.getDriverClass());
		rd.setPassword(cr.getPassword());
		rd.setUsername(cr.getUsername());
		rd.setConnectionUrl(cr.getConnectionUrl());
		DiffFields.setSoapValue(rd, DiffFields.TIMEZONE, cr.getTimezone());
	}

	private static void getJndiDataSource(RestV2Connection rc, ClientJndiJdbcDataSource cr, ResourceDescriptor rd) throws ParseException {
		rd.setJndiName(cr.getJndiName());
		DiffFields.setSoapValue(rd, DiffFields.TIMEZONE, cr.getTimezone());
	}

	private static void getAWSDataSource(RestV2Connection rc, ClientAwsDataSource cr, ResourceDescriptor rd) throws ParseException {
		DiffFields.setSoapValue(rd, DiffFields.ACCESSKEY, cr.getAccessKey());
		DiffFields.setSoapValue(rd, DiffFields.SECRETKEY, cr.getSecretKey());
		DiffFields.setSoapValue(rd, DiffFields.ROLEARN, cr.getRoleArn());
		DiffFields.setSoapValue(rd, DiffFields.REGION, cr.getRegion());
		DiffFields.setSoapValue(rd, DiffFields.DBNAME, cr.getDbName());
		DiffFields.setSoapValue(rd, DiffFields.DBINSTANCEIDENTIFIER, cr.getDbInstanceIdentifier());
		DiffFields.setSoapValue(rd, DiffFields.DBSERVICE, cr.getDbService());
		DiffFields.setSoapValue(rd, DiffFields.TIMEZONE, cr.getTimezone());
	}

	private static void getVirtualDataSource(RestV2Connection rc, ClientVirtualDataSource cr, ResourceDescriptor rd) throws ParseException {
		for (ClientSubDataSourceReference sds : cr.getSubDataSources()) {
			ResourceDescriptor r = new ResourceDescriptor();
			r.setName(rd.getName());
			r.setLabel(rd.getLabel());
			r.setIsReference(true);
			r.setReferenceUri(sds.getUri());
			r.setWsType(ResourceDescriptor.TYPE_DATASOURCE);
			r.setIsNew(true);
			r.setResourceProperty("PROP_DATASOURCE_SUB_DS_ID", sds.getId());
			rd.getChildren().add(r);
		}
	}

	private static void getDataType(RestV2Connection rc, ClientDataType cr, ResourceDescriptor rd) throws ParseException {
		rd.setDataType((byte) (cr.getType().ordinal() + 1));
		rd.setPattern(cr.getPattern());
		rd.setMaxValue(cr.getMaxValue());
		rd.setStrictMax(cr.isStrictMax());
		rd.setMinValue(cr.getMinValue());
		rd.setStrictMin(cr.isStrictMin());
		DiffFields.setSoapValue(rd, DiffFields.MAXLENGHT, cr.getMaxLength());
	}

	private static void getReportUnit(RestV2Connection rc, ClientReportUnit cr, ResourceDescriptor rd) throws ParseException {
		rd.setResourceProperty(ResourceDescriptor.PROP_RU_ALWAYS_PROPMT_CONTROLS, cr.isAlwaysPromptControls());
		rd.setResourceProperty(ResourceDescriptor.PROP_RU_INPUTCONTROL_RENDERING_VIEW, cr.getInputControlRenderingView());
		rd.setResourceProperty(ResourceDescriptor.PROP_RU_REPORT_RENDERING_VIEW, cr.getReportRenderingView());
		if (cr.getControlsLayout() == ControlsLayoutType.popupScreen)
			rd.setResourceProperty(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT, ResourceDescriptor.RU_CONTROLS_LAYOUT_POPUP_SCREEN);
		if (cr.getControlsLayout() == ControlsLayoutType.separatePage)
			rd.setResourceProperty(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT, ResourceDescriptor.RU_CONTROLS_LAYOUT_SEPARATE_PAGE);
		if (cr.getControlsLayout() == ControlsLayoutType.topOfPage)
			rd.setResourceProperty(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT, ResourceDescriptor.RU_CONTROLS_LAYOUT_TOP_OF_PAGE);
		if (cr.getControlsLayout() == ControlsLayoutType.inPage)
			rd.setResourceProperty(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT, 4);

		if (cr.getDataSource() != null)
			rd.getChildren().add(getRD(rc, (ClientResource<?>) cr.getDataSource()));
		if (cr.getQuery() != null)
			rd.getChildren().add(getRD(rc, (ClientQuery) cr.getQuery()));
		if (cr.getJrxml() != null)
			rd.getChildren().add(getRD(rc, (ClientResource<?>) cr.getJrxml()));

		if (cr.getInputControls() != null)
			for (ClientReferenceableInputControl cric : cr.getInputControls())
				rd.getChildren().add(getRD(rc, (ClientResource<?>) cric));
		if (cr.getFiles() != null)
			for (ClientReferenceableFile crf : cr.getFiles().values())
				rd.getChildren().add(getRD(rc, (ClientResource<?>) crf));
	}

	private static void getReference(RestV2Connection rc, ClientReference cr, ResourceDescriptor rd) {

	}

	private static void getInputControl(RestV2Connection rc, ClientInputControl cr, ResourceDescriptor rd) throws ParseException {
		if (cr.getListOfValues() != null)
			rd.getChildren().add(getRD(rc, (ClientListOfValues) cr.getListOfValues()));
		if (cr.getQuery() != null)
			rd.getChildren().add(getRD(rc, (ClientQuery) cr.getQuery()));

		rd.setControlType(cr.getType());
		rd.setQueryValueColumn(cr.getValueColumn());
		if (cr.getVisibleColumns() != null)
			rd.setQueryVisibleColumns(cr.getVisibleColumns().toArray(new String[cr.getVisibleColumns().size()]));
	}
}
