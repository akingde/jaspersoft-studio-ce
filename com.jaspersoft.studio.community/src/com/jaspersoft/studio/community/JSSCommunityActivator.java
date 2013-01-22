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
package com.jaspersoft.studio.community;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.osgi.framework.BundleContext;

import com.jaspersoft.studio.community.utils.CommunityUser;
import com.jaspersoft.studio.utils.UIUtils;

/**
 * Activator class for the <code>com.jaspersoft.studio.community</code> plug-in.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JSSCommunityActivator extends AbstractJRUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.jaspersoft.studio.community"; //$NON-NLS-1$
	// The shared instance
	private static JSSCommunityActivator plugin;
	
	/**
	 * The constructor
	 */
	public JSSCommunityActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static JSSCommunityActivator getDefault() {
		return plugin;
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

	/**
	 * Save the Jaspersoft Community site credentials.
	 * <p>
	 * 
	 * They can be used for example to submit new issue to the tracker via REST
	 * API.
	 * 
	 * @param user
	 *            the community user
	 */
	public void storeCommunityUserInformation(CommunityUser user){
		ISecurePreferences secPrefs = SecurePreferencesFactory.getDefault();
		ISecurePreferences node = secPrefs.node(CommunityConstants.SECURE_PREFSTORE_PATHNAME);
		try {
			node.put("username", user.getUsername(), true);
			node.put("password", user.getPassword(), true);
		}
		catch (StorageException ex){
			logError("Unable to store the Jaspersoft Community credentials on the secure preferences store.", ex);
			UIUtils.showError(ex);
		}
	}
	
	/**
	 * @return the community user information if they exists in the secure
	 *         preferences store, <code>null</code> otherwise
	 */
	public CommunityUser getCommunityUserInformation() {
		 ISecurePreferences preferences = SecurePreferencesFactory.getDefault();
         if (preferences.nodeExists(CommunityConstants.SECURE_PREFSTORE_PATHNAME)) {
             ISecurePreferences node = preferences.node(CommunityConstants.SECURE_PREFSTORE_PATHNAME);
             try {
                 String username = node.get("username", "n/a");
                 String password = node.get("password", "n/a");
                 if(username.equals("n/a") || password.equals("n/a")){
                	 return null;
                 }
                 return new CommunityUser(username,password);
             } catch (StorageException ex) {
            	 logError("A problem occurred while trying to get the stored community user information.", ex);
            	 UIUtils.showError(ex);	 
             }
         }
         return null;
	}
}
