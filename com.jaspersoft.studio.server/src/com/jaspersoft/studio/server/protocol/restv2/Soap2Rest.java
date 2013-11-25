package com.jaspersoft.studio.server.protocol.restv2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientAwsDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientBeanDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientCustomDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientDataType;
import com.jaspersoft.jasperserver.dto.resources.ClientDataType.TypeOfDataType;
import com.jaspersoft.jasperserver.dto.resources.ClientJdbcDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientJndiJdbcDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientMondrianConnection;
import com.jaspersoft.jasperserver.dto.resources.ClientProperty;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.resources.ClientVirtualDataSource;
import com.jaspersoft.jasperserver.dto.resources.ClientXmlaConnection;

public class Soap2Rest {

	public static ClientResource<?> getResource(RestV2Connection rc, ResourceDescriptor rd) throws ParseException {
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
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_OLAP_XMLA_CONNECTION))
			getXmlaConnection(rc, (ClientXmlaConnection) cr, rd);
		else if (rd.getWsType().equals(ResourceDescriptor.TYPE_OLAP_MONDRIAN_CONNECTION))
			getMondrianConnection(rc, (ClientMondrianConnection) cr, rd);
		return cr;
	}

	private static void getMondrianConnection(RestV2Connection rc, ClientMondrianConnection cr, ResourceDescriptor rd) {
		// cr.setDataSource(dataSource);
		// cr.setSchema(schema);
	}

	private static void getXmlaConnection(RestV2Connection rc, ClientXmlaConnection cr, ResourceDescriptor rd) {
		cr.setUrl(DiffFields.getSoapValue(rd, DiffFields.URL));
		cr.setDataSource(DiffFields.getSoapValue(rd, DiffFields.DATASOURCE));
		cr.setCatalog(DiffFields.getSoapValue(rd, DiffFields.CATALOG));
		cr.setUsername(DiffFields.getSoapValue(rd, DiffFields.USERNAME));
		cr.setPassword(DiffFields.getSoapValue(rd, DiffFields.PASSWORD));
	}

	private static void getVirtualDataSource(RestV2Connection rc, ClientVirtualDataSource cr, ResourceDescriptor rd) {

	}

	private static void getAWSDataSource(RestV2Connection rc, ClientAwsDataSource cr, ResourceDescriptor rd) {
		cr.setAccessKey(DiffFields.getSoapValue(rd, DiffFields.ACCESSKEY));
		cr.setSecretKey(DiffFields.getSoapValue(rd, DiffFields.SECRETKEY));
		cr.setRoleArn(DiffFields.getSoapValue(rd, DiffFields.ROLEARN));
		cr.setRegion(DiffFields.getSoapValue(rd, DiffFields.REGION));
		cr.setDbName(DiffFields.getSoapValue(rd, DiffFields.DBNAME));
		cr.setDbInstanceIdentifier(DiffFields.getSoapValue(rd, DiffFields.DBINSTANCEIDENTIFIER));
		cr.setDbService(DiffFields.getSoapValue(rd, DiffFields.DBSERVICE));
		cr.setTimezone(DiffFields.getSoapValue(rd, DiffFields.TIMEZONE));
	}

	private static void getCustomDataSource(RestV2Connection rc, ClientCustomDataSource cr, ResourceDescriptor rd) {
		cr.setServiceClass(rd.getServiceClass());
		cr.setDataSourceName(DiffFields.getSoapValue(rd, DiffFields.DATASOURCENAME));
		Map<String, String> map = rd.getPropertyMap();
		List<ClientProperty> props = new ArrayList<ClientProperty>();
		for (String key : map.keySet())
			props.add(new ClientProperty(key, map.get(key)));
		cr.setProperties(props);
	}

	private static void getJndiDataSource(RestV2Connection rc, ClientJndiJdbcDataSource cr, ResourceDescriptor rd) {
		cr.setJndiName(rd.getJndiName());
		cr.setTimezone(DiffFields.getSoapValue(rd, DiffFields.TIMEZONE));
	}

	private static void getBeanDataSource(RestV2Connection rc, ClientBeanDataSource cr, ResourceDescriptor rd) {
		cr.setBeanName(rd.getBeanName());
		cr.setBeanMethod(rd.getBeanMethod());
	}

	private static void getJdbcDataSource(RestV2Connection rc, ClientJdbcDataSource cr, ResourceDescriptor rd) {
		cr.setDriverClass(rd.getDriverClass());
		cr.setPassword(rd.getPassword());
		cr.setUsername(rd.getUsername());
		cr.setConnectionUrl(rd.getConnectionUrl());
		cr.setTimezone(DiffFields.getSoapValue(rd, DiffFields.TIMEZONE));
	}

	private static void getDataType(RestV2Connection rc, ClientDataType cr, ResourceDescriptor rd) {
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

	private static void getImage(RestV2Connection rc, ClientResource<?> cr, ResourceDescriptor rd) {

	}

}
