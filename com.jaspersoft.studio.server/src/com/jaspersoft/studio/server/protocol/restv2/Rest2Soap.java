package com.jaspersoft.studio.server.protocol.restv2;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientAwsDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientBeanDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientCustomDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientDataType;
import com.jaspersoft.jasperserver.dto.resources.ClientFile;
import com.jaspersoft.jasperserver.dto.resources.ClientInputControl;
import com.jaspersoft.jasperserver.dto.resources.ClientJdbcDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientJndiJdbcDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientListOfValues;
import com.jaspersoft.jasperserver.dto.resources.ClientMondrianConnection;
import com.jaspersoft.jasperserver.dto.resources.ClientProperty;
import com.jaspersoft.jasperserver.dto.resources.ClientReference;
import com.jaspersoft.jasperserver.dto.resources.ClientReferenceableInputControl;
import com.jaspersoft.jasperserver.dto.resources.ClientReportUnit;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.jasperserver.dto.resources.ClientVirtualDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientXmlaConnection;
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

		else if (cr instanceof ClientXmlaConnection)
			getXmlaConnection(rc, (ClientXmlaConnection) cr, rd);
		else if (cr instanceof ClientMondrianConnection)
			getMondrianConnection(rc, (ClientMondrianConnection) cr, rd);

		else if (cr instanceof ClientListOfValues)
			getLOVConnection(rc, (ClientListOfValues) cr, rd);
		else if (cr instanceof ClientReportUnit)
			getReportUnit(rc, (ClientReportUnit) cr, rd);
		else if (cr instanceof ClientInputControl)
			getInputControl(rc, (ClientInputControl) cr, rd);
		else if (cr instanceof ClientFile)
			getFile(rc, (ClientFile) cr, rd);
		// else if (cr instanceof ClientReportOptions)
		// getReportOptions(rc, (ClientReportOptions) cr, rd);

		return rd;
	}

	// private static void getReportOptions(RestV2Connection rc,
	// ClientReportOptions cr, ResourceDescriptor rd) throws ParseException {
	// cr.getType();
	// cr.getContent();
	// }

	private static void getFile(RestV2Connection rc, ClientFile cr, ResourceDescriptor rd) throws ParseException {
		cr.getType();
		cr.getContent();
	}

	private static void getLOVConnection(RestV2Connection rc, ClientListOfValues cr, ResourceDescriptor rd) throws ParseException {
		// for (ClientListOfValuesItem sds : cr.getItems())
		// getRD(rc, sds, new ResourceDescriptor());
	}

	private static void getMondrianConnection(RestV2Connection rc, ClientMondrianConnection cr, ResourceDescriptor rd) throws ParseException {
		cr.getDataSource();
		cr.getSchema();
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
			for (ClientProperty cp : cr.getProperties()) {
				map.put(cp.getKey(), cp.getValue());
			}
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
		// for (ClientSubDataSourceReference sds : cr.getSubDataSources())
		// getRD(rc, sds, new ResourceDescriptor());
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
		cr.isAlwaysPromptControls();
		cr.getQuery();

		// rd.getChildren().add(cr.getJrxml());

		for (ClientReferenceableInputControl cric : cr.getInputControls()) {
			if (cric instanceof ClientInputControl)
				rd.getChildren().add(getRD(rc, (ClientInputControl) cric, new ResourceDescriptor()));
			// else if (cric instanceof ClientReferenceableInputControl)
			// rd.getChildren().add(getRD(rc, (ClientReferenceableInputControl) cric,
			// new ResourceDescriptor()));
		}
		cr.getInputControlRenderingView();
		cr.getFiles();
		cr.getReportRenderingView();
	}

	private static void getReference(RestV2Connection rc, ClientReference cr, ResourceDescriptor rd) {

	}

	private static void getInputControl(RestV2Connection rc, ClientInputControl cr, ResourceDescriptor rd) {

	}

}
