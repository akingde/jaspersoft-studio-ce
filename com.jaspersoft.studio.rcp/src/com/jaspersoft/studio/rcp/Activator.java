/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.rcp;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.equinox.p2.ui.Policy;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.internal.util.PrefUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.jaspersoft.studio.rcp.p2.JSSP2Policy;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractJRUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.jaspersoft.studio.rcp"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	// Stuff for JSS P2 Policy
	private ServiceRegistration<?> p2PolicyRegistration;
	private JSSP2Policy policy;
	private IPropertyChangeListener preferenceListener;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		plugin = this;
		// Register the p2 UI policy
		registerP2Policy(context);
		getPreferenceStore().addPropertyChangeListener(getPreferenceListener());
		Job prefSettings = new Job("Preferences setting"){

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				// FIXME - Temporary workaround for Bugzilla #44286
				// See also Eclipse bug: https://bugs.eclipse.org/bugs/show_bug.cgi?id=475578
				PrefUtil.getAPIPreferenceStore().putValue(IWorkbenchPreferenceConstants.PROMPT_WHEN_SAVEABLE_STILL_OPEN,"false"); //$NON-NLS-1$
				// FIXME - Workaround for Bugzilla #44980
				// We extensively need the support for linked resources in JSS. Just an additional check.
				IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode(ResourcesPlugin.PI_RESOURCES);
				preferences.putBoolean(ResourcesPlugin.PREF_DISABLE_LINKING,false);
				return Status.OK_STATUS;
			}
			
		};
		prefSettings.setPriority(Job.SHORT);
		prefSettings.schedule(5000);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		// Unregister the UI policy
		p2PolicyRegistration.unregister();
		p2PolicyRegistration = null;
		getPreferenceStore().removePropertyChangeListener(preferenceListener);
		preferenceListener = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}
	
	/*
	 * Registers the P2 policy.
	 */
	private void registerP2Policy(BundleContext context) {
		policy = new JSSP2Policy();
		policy.updateForPreferences();
		p2PolicyRegistration = context.registerService(Policy.class.getName(), policy, null);
	}
	
	private IPropertyChangeListener getPreferenceListener() {
		if (preferenceListener == null) {
			preferenceListener = new IPropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent event) {
					policy.updateForPreferences();
				}
			};
		}
		return preferenceListener;
	}

}
