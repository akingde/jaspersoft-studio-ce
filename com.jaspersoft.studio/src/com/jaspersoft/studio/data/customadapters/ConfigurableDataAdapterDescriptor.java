/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.customadapters;

import java.lang.reflect.Constructor;
import java.util.List;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.customadapters.ui.AdapterWidgetsDescriptor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

/**
 * Data Adapters descriptor for the Configurable data adapters
 */
public class ConfigurableDataAdapterDescriptor extends DataAdapterDescriptor implements IFieldsProvider {

	private static final long serialVersionUID = 4348212451493471479L;
	
	/**
	 * Definition of the data adapter
	 */
	private AdapterWidgetsDescriptor descriptor;
	
	/**
	 * Create the descriptor
	 * 
	 * @param descriptor definition of the data adapter, must be not null
	 */
	public ConfigurableDataAdapterDescriptor(AdapterWidgetsDescriptor descriptor) {
		this.descriptor = descriptor;
	}
	
	/**
	 * Build the data adapter with the class loader of the current thread
	 */
	@Override
	public DataAdapter getDataAdapter() {
		if (dataAdapter == null) {
			try {
				Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(descriptor.getAdapterClass());
				Constructor<?> ctor = clazz.getConstructor();
				dataAdapter = (DataAdapter)ctor.newInstance();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return dataAdapter;
	
	}
	
	/**
	 * Build the data adapter with the class loader of the passed {@link JasperReportsConfiguration}
	 */
	@Override
	public DataAdapter getDataAdapter(JasperReportsConfiguration jConfig) {
		if (dataAdapter == null) {
			try {
				Class<?> clazz = jConfig.getClassLoader().loadClass(descriptor.getAdapterClass());
				Constructor<?> ctor = clazz.getConstructor();
				dataAdapter = (DataAdapter)ctor.newInstance();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return dataAdapter;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new ConfigurableDataAdapterEditor(descriptor);
	}

	/**
	 * Support the get field operation if in the descriptor is present and {@link IAdapterPropertyHandler} that
	 * is also a {@link IFieldsProvider} and support the operation
	 */
	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		IAdapterPropertyHandler setter = descriptor.getAdapterPropertyHandler(jConfig);
		if (setter != null && setter instanceof IFieldsProvider) {
			return ((IFieldsProvider)setter).supportsGetFieldsOperation(jConfig);
		}
		return false;
	}

	/**
	 * Support the get field operation if in the descriptor is present and {@link IAdapterPropertyHandler} that
	 * is also a {@link IFieldsProvider} and support the operation
	 */
	@Override
	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig, JRDataset jDataset) throws JRException, UnsupportedOperationException {
		IAdapterPropertyHandler setter = descriptor.getAdapterPropertyHandler(jConfig);
		if (setter != null && setter instanceof IFieldsProvider) {
			return ((IFieldsProvider)setter).getFields(con, jConfig, jDataset);
		}
		return null;
	}
}
