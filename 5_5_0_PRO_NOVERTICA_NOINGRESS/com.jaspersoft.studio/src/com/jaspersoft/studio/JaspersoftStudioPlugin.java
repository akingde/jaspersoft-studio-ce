/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

import org.osgi.framework.BundleContext;

import com.jaspersoft.studio.editor.gef.decorator.DecoratorManager;
import com.jaspersoft.studio.editor.preview.input.ext.InputControlTypeManager;
import com.jaspersoft.studio.editor.toolitems.ToolItemsManager;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.preferences.GlobalPreferencePage;
import com.jaspersoft.studio.property.PostSetValueManager;
import com.jaspersoft.studio.utils.BrandingInfo;
import com.jaspersoft.studio.utils.jasper.DriversManager;

/*
 * The main plugin class to be used in the desktop.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * 
 * @version $Id: JasperCompileManager.java 1229 2006-04-19 13:27:35 +0300 (Wed, 19 Apr 2006) teodord $
 */
public class JaspersoftStudioPlugin extends AbstractJRUIPlugin {

	public static final String PLUGIN_ID = "com.jaspersoft.studio"; //$NON-NLS-1$
	public static final String COMPONENTS_ID = "com.jaspersoft.studio.components"; //$NON-NLS-1$
	
	// The shared instance.
	/** The plugin. */
	private static JaspersoftStudioPlugin plugin;

	/**
	 * Gets the single instance of JaspersoftStudioPlugin.
	 * 
	 * @return the plugin instance singleton.
	 */
	public static JaspersoftStudioPlugin getInstance() {
		return plugin; // plugin cannot be null, Eclipse takes care to instance it
		// at startup.
	}

	public static final String ICONS_RESOURCES_REFRESH_16_PNG = "icons/resources/refresh-16.png";

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		getExtensionManager();
		// Sets the branding information
		BrandingInfo info = new BrandingInfo();
		info.setProductName("Jaspersoft Studio plug-in");
		info.setProductVersion(getBundle().getVersion().toString());
		info.setProductMainBundleID(PLUGIN_ID);
		setBrandingInformation(info);
	}

	/**
	 * The constructor.
	 */
	public JaspersoftStudioPlugin() {
		plugin = this;
	}

	/**
	 * This method is called when the plug-in is stopped.
	 * 
	 * @param context
	 *          the context
	 * @throws Exception
	 *           the exception
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		plugin = null;
	}

	private static ExtensionManager extensionManager;

	public static ExtensionManager getExtensionManager() {
		if (extensionManager == null) {
			extensionManager = new ExtensionManager();
			extensionManager.init();
		}
		return extensionManager;
	}

	private static DecoratorManager decoratorManager;

	public static DecoratorManager getDecoratorManager() {
		if (decoratorManager == null) {
			decoratorManager = new DecoratorManager();
			decoratorManager.init();
		}
		return decoratorManager;
	}

	private static ToolItemsManager toolItemsManager;

	public static ToolItemsManager getToolItemsManager() {
		if (toolItemsManager == null) {
			toolItemsManager = new ToolItemsManager();
			toolItemsManager.init();
		}
		return toolItemsManager;
	}

	private static DriversManager driversManager;

	public static DriversManager getDriversManager() {
		if (driversManager == null) {
			driversManager = new DriversManager();
			driversManager.init();
		}
		return driversManager;
	}

	private static PostSetValueManager postSetValueManager;

	public static PostSetValueManager getPostSetValueManager() {
		if (postSetValueManager == null) {
			postSetValueManager = new PostSetValueManager();
			postSetValueManager.init();
		}
		return postSetValueManager;
	}

	private static InputControlTypeManager inputControlTypeManager;

	public static InputControlTypeManager getInputControlTypeManager() {
		if (inputControlTypeManager == null) {
			inputControlTypeManager = new InputControlTypeManager();
			inputControlTypeManager.init();
		}
		return inputControlTypeManager;
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

	/**
	 * @return the unique plug-in identifier
	 */
	public static String getUniqueIdentifier() {
		return PLUGIN_ID;
	}
	
	/**
	 * Sets the branding information that will helps identify the
	 * product, for example in case of debug, diagnostics or statistics.
	 * 
	 * @param info branding information
	 */
	public void setBrandingInformation(BrandingInfo info){
		getPreferenceStore().putValue(BrandingInfo.BRANDING_PRODUCT_NAME, info.getProductName());
		getPreferenceStore().putValue(BrandingInfo.BRANDING_PRODUCT_VERSION, info.getProductVersion());
		getPreferenceStore().putValue(BrandingInfo.BRANDING_PRODUCT_MAINBUNDLE, info.getProductMainBundleID());
	}

	/**
	 * @return the branding information that identify the currently running product (plugin/product)
	 * 
	 */
	public BrandingInfo getBrandingInformation(){
		BrandingInfo info = new BrandingInfo();
		info.setProductName(getPreferenceStore().getString(BrandingInfo.BRANDING_PRODUCT_NAME));
		info.setProductVersion(getPreferenceStore().getString(BrandingInfo.BRANDING_PRODUCT_VERSION));
		info.setProductMainBundleID(getPreferenceStore().getString(BrandingInfo.BRANDING_PRODUCT_MAINBUNDLE));
		return info;
	}
	
	/**
	 * @return <code>true</code> if we should use the Eclipse Secure Storage feature,<code>false</code> otherwise
	 */
	public static boolean shouldUseSecureStorage(){ 
		return getInstance().getPreferenceStore().getBoolean(GlobalPreferencePage.JSS_USE_SECURE_STORAGE);
	}
	
}
