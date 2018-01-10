/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.customadapters;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterServiceFactoryImpl;
import com.jaspersoft.studio.data.adapter.IDataAdapterCreator;
import com.jaspersoft.studio.data.customadapters.ui.AdapterWidgetsDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterContributorFactory;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceFactory;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.ParameterContributorContext;

/**
 * Factory for a configurable data adapter
 * 
 * @author Orlandin Marco
 */
public class ConfigurableDataAdapterFactory implements DataAdapterFactory {
	
	/**
	 * The data adapter definition
	 */
	private AdapterWidgetsDescriptor descriptor;
	
	/**
	 * Build the factory
	 * 
	 * @param descriptor the data adapter definition, must be not null
	 */
	public ConfigurableDataAdapterFactory(AdapterWidgetsDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	/**
	 * Return the {@link DataAdapterDescriptor} for a configurable Data Adapter
	 */
	@Override
	public DataAdapterDescriptor createDataAdapter() {
		return new ConfigurableDataAdapterDescriptor(descriptor);
	}

	/**
	 * Retunr the class of the data adapter
	 */
	@Override
	public String getDataAdapterClassName() {
		return descriptor.getAdapterClass();
	}

	/**
	 * Return the data adapter service, first the service is found among the JasperReports contribution and if it 
	 * can't be found the a default dummy service is returned, since this method should not return null+
	 */
	@SuppressWarnings("deprecation")
	@Override
	public DataAdapterService createDataAdapterService(JasperReportsContext jasperReportsContext, DataAdapter dataAdapter) {
		//Search among the JR Extensions
		List<DataAdapterContributorFactory> bundles = jasperReportsContext.getExtensions(DataAdapterContributorFactory.class);
		ParameterContributorContext paramContribContext = new ParameterContributorContext(jasperReportsContext, null, null);
		for (Iterator<DataAdapterContributorFactory> it = bundles.iterator(); it.hasNext();)
		{
			DataAdapterContributorFactory factory = it.next();
			DataAdapterService service = factory.getDataAdapterService(paramContribContext, dataAdapter);
			if (service != null)
			{
				return service;
			}
		}
		//Look in the data adapter service factory, DataAdapterServiceFactory is deprecated but somw old data adapters may still use it
		List<DataAdapterServiceFactory> oldBundles = jasperReportsContext.getExtensions(DataAdapterServiceFactory.class);
		for (Iterator<DataAdapterServiceFactory> it = oldBundles.iterator(); it.hasNext();)
		{
			DataAdapterServiceFactory factory = it.next();
			if (!(factory instanceof DataAdapterServiceFactoryImpl)) {
				DataAdapterService service = factory.getDataAdapterService(jasperReportsContext, dataAdapter);
				if (service != null)
				{
					return service;
				}
			}
		}
		
		//fallback returning a default DataAdapterService
		return new DataAdapterService() {
			
			@Override
			public void dispose() {
			}
			
			@Override
			public void contributeParameters(Map<String, Object> parameters) throws JRException {			
			}
			
			@Override
			public void test() throws JRException {
				testExistence((JasperReportsConfiguration)jasperReportsContext);
			}
		};
	}

	@Override
	public String getLabel() {
		return descriptor.getLabel();
	}

	@Override
	public String getDescription() {
		return descriptor.getDescription();
	}
	
	/**
	 * Resize an {@link ImageData} to a specific size
	 */
	private ImageData resizeImage(ImageData sourceImageData, int x, int y, int width, int height){
		if (sourceImageData.width == width && sourceImageData.height == height) return sourceImageData;
		Image sourceImage = new Image(Display.getCurrent(), sourceImageData);
	    Image scaledImage = new Image(Display.getCurrent(), width, height);
		GC gc = new GC(scaledImage);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(sourceImage, 0, 0, sourceImage.getBounds().width, sourceImage.getBounds().height, 0, 0, width, height);
	    gc.dispose();
	    ImageData croppedImageData = scaledImage.getImageData();
	    
	    scaledImage.dispose();
	    sourceImage.dispose();
	    return croppedImageData;
	}

	/**
	 * Return the icon of the data adapter scaled to a specific size, if the icon is not 
	 * defined it return null. The image one build is cached in the {@link ResourceManager}
	 */
	@Override
	public Image getIcon(int size) {
		if (descriptor.getIconPath() != null) {
			String key = "DA_IMAGE_" + descriptor.getIconPath() + size;
			Image image = ResourceManager.getImage(key);
			if (image == null) {
				ImageDescriptor desc = ResourceManager.getImageDescriptor(descriptor.getIconPath());
				if (desc != null) {
					ImageData data = desc.getImageData();
					if (data != null) {
						data = resizeImage(data, 0, 0, size, size);
						image = new Image(UIUtils.getDisplay(), data);
						ResourceManager.addImage(key, image);
					}
				}
			}  
			return image;
		}
		return null;
	}
	
	/**
	 * Test if the class of the data adapter can be resolved using the classloader
	 * of the passed {@link JasperReportsConfiguration} and if the class has a 0
	 * parameters constructor
	 * 
	 * @param jConfig a not null {@link JasperReportsConfiguration}
	 * @throws JRException throw the exception if the data adapter can not be resolved or instantiated 
	 */
	public void testExistence(JasperReportsConfiguration jConfig) throws JRException{
		try {
			Class<?> clazz = jConfig.getClassLoader().loadClass(descriptor.getAdapterClass());
			Constructor<?> ctor = clazz.getConstructor();
			@SuppressWarnings("unused")
			DataAdapter da = (DataAdapter)ctor.newInstance();
		} catch (Exception ex) {
			throw new JRException("Unable to instantiate the data adapter" , ex);
		}
	}


	@Override
	public IDataAdapterCreator iReportConverter() {
		return null;
	}

	@Override
	public boolean isDeprecated() {
		return false;
	}
}
