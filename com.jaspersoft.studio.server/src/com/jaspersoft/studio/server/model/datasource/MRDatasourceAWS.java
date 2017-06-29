/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model.datasource;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.ServerIconDescriptor;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.protocol.restv2.DiffFields;

import net.sf.jasperreports.engine.JRConstants;

public class MRDatasourceAWS extends AMRDatasource {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String TYPE_AWS = "aws";

	public static final String PROP_DATASOURCE_AWS_ACCESS_KEY = DiffFields.ACCESSKEY;
	public static final String PROP_DATASOURCE_AWS_SECRET_KEY = DiffFields.SECRETKEY;
	public static final String PROP_DATASOURCE_AWS_ROLE_ARN = DiffFields.ROLEARN;
	public static final String PROP_DATASOURCE_AWS_REGION = DiffFields.REGION;
	public static final String PROP_DATASOURCE_AWS_DB_NAME = DiffFields.DBNAME;
	public static final String PROP_DATASOURCE_AWS_DB_SERVICE = DiffFields.DBSERVICE;
	public static final String PROP_DATASOURCE_AWS_DB_INSTANCE_IDENTIFIER = DiffFields.DBINSTANCEIDENTIFIER;

	public MRDatasourceAWS(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	private static IIconDescriptor iconDescriptor;

	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ServerIconDescriptor("datasource-aws"); //$NON-NLS-1$
		return iconDescriptor;
	}

	@Override
	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	public static ResourceDescriptor createDescriptor(ANode parent) {
		ResourceDescriptor rd = AMResource.createDescriptor(parent);
		rd.setWsType(MRDatasourceAWS.TYPE_AWS);
		rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_REGION, "");
		rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_DB_NAME, "");
		rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_DB_SERVICE, "");
		rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_DB_INSTANCE_IDENTIFIER, "");
		return rd;
	}
}
