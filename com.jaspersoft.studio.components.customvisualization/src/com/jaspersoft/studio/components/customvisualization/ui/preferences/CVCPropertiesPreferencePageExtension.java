/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui.preferences;

import java.util.Properties;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

import net.sf.jasperreports.customvisualization.export.CVElementPhantomJSImageDataProvider;
import net.sf.jasperreports.eclipse.preferences.IPreferenceExtendablePage;
import net.sf.jasperreports.eclipse.preferences.IPreferencePageExtension;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Extension of the properties preference page to allow to set automatically the phantomjs property fallback
 * value
 * 
 * @author Orlandin Marco
 *
 */
public class CVCPropertiesPreferencePageExtension implements IPreferencePageExtension {
	
	public static final String PHANTOMJS_DEFAULT_VALUE = "phantomjs";

	@Override
	public void createContributedFields(Composite parent, IPreferenceExtendablePage page) {
	}

	@Override
	public void performApply() {
	}

	@Override
	public void performCancel() {
	}

	@Override
	public void performDefaults() {
	}

	@Override
	public void initDefaultProperties(IPreferenceStore store) {
		try { 
			Properties props = FileUtils.load(store.getDefaultString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
			String phantomJSPath = props.getProperty(CVElementPhantomJSImageDataProvider.PROPERTY_PHANTOMJS_EXECUTABLE_PATH);
			if (phantomJSPath == null || phantomJSPath.isEmpty()) {
				props.put(CVElementPhantomJSImageDataProvider.PROPERTY_PHANTOMJS_EXECUTABLE_PATH, PHANTOMJS_DEFAULT_VALUE);
				store.setDefault(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES, FileUtils.getPropertyAsString(props));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(ex);
		}
	}

}
