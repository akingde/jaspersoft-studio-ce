/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.io.Serializable;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.customadapters.JSSCastorUtil;
import com.jaspersoft.studio.data.ui.DefaultDataAdapterEditor;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JasperReportsContext;

/*
 * 
 * @author gtoffoli
 */
public abstract class DataAdapterDescriptor implements IIconDescriptor, Serializable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	protected transient DataAdapter dataAdapter;

	/**
	 *
	 */
	public abstract DataAdapter getDataAdapter();
	
	/**
	 * Get a data adapter using a specific {@link JasperReportsConfiguration} to load it.
	 * The default implementation, to be backward compatible simple call the default getDataAdapter(),
	 * but can be overridden by the descriptor of the data adapters that need a specific context to be 
	 * loaded
	 */
	public DataAdapter getDataAdapter(JasperReportsConfiguration jConfig) {
		return getDataAdapter();
	}

	/**
	 * FIXME consider remove
	 */
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.dataAdapter = dataAdapter;
	}

	String name;

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * FIXME consider remove
	 */
	public String getName() {
		if (name != null)
			return name;
		return getDataAdapter() == null ? null : getDataAdapter().getName();
	}

	/**
	 * Coming from the IIconDescriptor interface. Just returns the name of this data adapter
	 */
	public String getTitle() {
		if (getDataAdapter() == null || getDataAdapter().getName() == null || getDataAdapter().getName().length() == 0) {
			return "<unnamed>";
		}
		return getDataAdapter().getName();
	}

	/**
	 * Coming from the IIconDescriptor interface. Return null;
	 */
	public String getDescription() {
		return null;
	}

	/**
	 * Coming from the IIconDescriptor interface. Return null;
	 */
	public String getToolTip() {
		return null;
	}

	/**
	 * Return an Image. By default this method returns a simple database icon
	 */
	public Image getIcon(int size) {
		return JaspersoftStudioPlugin.getInstance().getImage("icons/database.png");
	}

	/**
	 * It just write a portion of XML to persist the data adapter it is used by the DataAdapterManager and can be used to
	 * export this data adapter too. FIXME consider remove
	 */
	public final String toXml(JasperReportsContext jrContext) {
		JasperReportsConfiguration jConfig = (JasperReportsConfiguration)jrContext;
		return JSSCastorUtil.getInstance(jConfig).writeToString(getDataAdapter(jConfig));
	}

	@Override
	public String toString() {
		return getTitle();
	}

	/**
	 * This method is used to provide to the datasources window the GUI to configure this kind of component.
	 * 
	 */
	public DataAdapterEditor getEditor() {
		// return new BasicDataAdapterEditor();
		return new DefaultDataAdapterEditor();
	}

	/**
	 * Gets the icon16.
	 * 
	 * @return the icon16
	 */
	public ImageDescriptor getIcon16() {
		Image icon = getIcon(16);
		if (icon != null)
			return ImageDescriptor.createFromImage(icon);
		return JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/database.png");
	}

	/**
	 * Gets the icon32.
	 * 
	 * @return the icon32
	 */
	public ImageDescriptor getIcon32() {
		Image icon = getIcon(32);
		if (icon != null)
			return ImageDescriptor.createFromImage(icon);
		return JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/database.png");
	}

	/**
	 * Verifies if the Data Adapter is supposed to support a generic <i>Test</i> operation.
	 * <p>
	 * This kind of information can be useful when exposed in the UI.
	 * 
	 * @return <code>true</code> if the data adapter supports Test, <code>false</code> otherwise
	 */
	public boolean doSupportTest() {
		return true;
	}

	/**
	 * Data Adapters can instruct UI wich languages to show when adapter is selected or if data adapter is appropriate for
	 * a language.
	 * 
	 * @return null or * means any language, in case data adapter dictate the language, first language from the list will
	 *         be used
	 */
	public String[] getLanguages() {
		return null;
	}

	/**
	 * This list will help building language list to filter data adapters or languages
	 * 
	 * @return null - nothing to exclude or array of languages to exclude
	 */
	public String[] getExcludedLanguages() {
		return null;
	}
}
