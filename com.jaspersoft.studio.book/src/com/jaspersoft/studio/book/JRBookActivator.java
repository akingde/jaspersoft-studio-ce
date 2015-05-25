package com.jaspersoft.studio.book;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class JRBookActivator extends AbstractJRUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.jaspersoft.studio.book"; //$NON-NLS-1$

	// The shared instance
	private static JRBookActivator plugin;
	
	/**
	 * The constructor
	 */
	public JRBookActivator() {
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
	public static JRBookActivator getDefault() {
		return plugin;
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

	@Override
	protected void postStartOperations() {
		Job lookForBookReports = new Job("Book reports search") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					BookUtils.scanWSForBookReports();
				} catch (CoreException e) {
					JRBookActivator.getDefault().logError(e);
					return Status.CANCEL_STATUS;
				}
				return Status.OK_STATUS;
			}
		};
		lookForBookReports.schedule();
	}
}
