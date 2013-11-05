package com.jaspersoft.studio.data.hbase;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.hadoop.hbase.adapter.HBaseDataAdapter;
import com.jaspersoft.hadoop.hbase.adapter.HBaseDataAdapterImpl;
import com.jaspersoft.hadoop.hbase.adapter.HBaseDataAdapterService;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HBaseDataAdapterFactory implements DataAdapterFactory {
	public HBaseDataAdapterFactory() {
	}

	@Override
	public DataAdapterDescriptor createDataAdapter() {
		return new HBaseDataAdapterDescriptor();
	}

	@Override
	public String getDataAdapterClassName() {
		return HBaseDataAdapterImpl.class.getName();
	}

	@Override
	public String getLabel() {
		return "HBase Connection";
	}

	@Override
	public String getDescription() {
		return "HBase Connection";
	}

	@Override
	public Image getIcon(int size) {
		if (size == 16)
			return Activator.getDefault().getImage("icons/HBase_Logo.png"); //$NON-NLS-1$ 
		return null;
	}

	public DataAdapterService createDataAdapterService(DataAdapter dataAdapter) {
		if (dataAdapter instanceof HBaseDataAdapter)
			return new HBaseDataAdapterService(JasperReportsConfiguration.getDefaultJRConfig(), (HBaseDataAdapter) dataAdapter);
		return null;
	}

	@Override
	public IDataAdapterCreator iReportConverter() {
		return null;
	}
}