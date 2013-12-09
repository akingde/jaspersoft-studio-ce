package com.jaspersoft.studio.data.cassandra.cql3.server;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.data.cassandra.cql3.CassandraCQL3IconDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceCustom;

public class MRDatasourceCassandraCQL3 extends MRDatasourceCustom {

	public static final String HOSTNAME = "hostname";
	public static final String PORT = "port";
	public static final String KEYSPACE = "keyspace";
	public static final String CASSANDRAVERSION = "cassandraVersion";
	public static final String CLUSTERNAME = "clustername";
	public static final String ISFRAMED = "isFramed";

	public static final String CUSTOM_CLASS = "com.jaspersoft.cassandra.cql3.jasperserver.CassandraDataSourceService";
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MRDatasourceCassandraCQL3(ANode parent, ResourceDescriptor rd, int index) {
		super(parent, rd, index);
	}

	private static IIconDescriptor iconDescriptor;

	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CassandraCQL3IconDescriptor("cassandracql3"); //$NON-NLS-1$
		return iconDescriptor;
	}

	@Override
	public IIconDescriptor getThisIconDescriptor() {
		return getIconDescriptor();
	}

	public static ResourceDescriptor createDescriptor(ANode parent) {
		ResourceDescriptor rd = MRDatasourceCustom.createDescriptor(parent);
		ResourceProperty rp = new ResourceProperty(
				MRDatasourceCustom.PROP_DATASOURCE_CUSTOM_PROPERTY_MAP);
		List<ResourceProperty> props = new ArrayList<ResourceProperty>();
		props.add(new ResourceProperty(HOSTNAME,"localhost"));
		props.add(new ResourceProperty(PORT, "9160"));
		props.add(new ResourceProperty(KEYSPACE, "keyspace"));
		props.add(new ResourceProperty(CASSANDRAVERSION, "1.2"));
		props.add(new ResourceProperty(CLUSTERNAME, "cluster"));
		props.add(new ResourceProperty(ISFRAMED, "false"));
		rp.setProperties(props);
		rd.getProperties().add(rp);
		rp = new ResourceProperty(
				MRDatasourceCustom.PROP_DATASOURCE_CUSTOM_SERVICE_CLASS,
				CUSTOM_CLASS);
		rd.getProperties().add(rp);
		return rd;
	}
}
