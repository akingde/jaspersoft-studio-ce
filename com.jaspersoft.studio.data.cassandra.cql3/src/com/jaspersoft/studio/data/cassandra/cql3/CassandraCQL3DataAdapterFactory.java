package com.jaspersoft.studio.data.cassandra.cql3;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.cassandra.cql3.adapter.CassandraCQL3DataAdapter;
import com.jaspersoft.cassandra.cql3.adapter.CassandraCQL3DataAdapterImpl;
import com.jaspersoft.cassandra.cql3.adapter.CassandraCQL3DataAdapterService;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;
import com.jaspersoft.studio.data.cassandra.cql3.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * @author Raul Gallegos
 * 
 */
public class CassandraCQL3DataAdapterFactory implements DataAdapterFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#createDataAdapter()
	 */
	public DataAdapterDescriptor createDataAdapter() {
		CassandraCQL3DataAdapterDescriptor descriptor = new CassandraCQL3DataAdapterDescriptor();
		descriptor.getDataAdapter().setHostname("localhost"); //$NON-NLS-1$
		descriptor.getDataAdapter().setPort(9160);		
		descriptor.getDataAdapter().setKeyspace("keyspace"); //$NON-NLS-1$
		descriptor.getDataAdapter().setCassandraVersion("1.2"); //$NON-NLS-1$
		descriptor.getDataAdapter().setClustername("cluster"); //$NON-NLS-1$
		return descriptor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.data.DataAdapterFactory#getDataAdapterClassName()
	 */
	public String getDataAdapterClassName() {
		return CassandraCQL3DataAdapterImpl.class.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getDescription()
	 */
	public String getLabel() {
		return Messages.CassandraCQL3DataAdapterFactory_Label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getDescription()
	 */
	public String getDescription() {
		return Messages.CassandraCQL3DataAdapterFactory_Description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.DataAdapterFactory#getIcon(int)
	 */
	public Image getIcon(int size) {
		if (size == 16) {
			return Activator.getDefault().getImage("icons/cassandracql3.png"); //$NON-NLS-1$
		}
		return null;
	}

	public DataAdapterService createDataAdapterService(DataAdapter dataAdapter) {
		if (dataAdapter instanceof CassandraCQL3DataAdapter)
			return new CassandraCQL3DataAdapterService(JasperReportsConfiguration.getDefaultJRConfig(), (CassandraCQL3DataAdapter) dataAdapter);
		return null;
	}

	@Override
	public IDataAdapterCreator iReportConverter() {
		return null;
	}
}
