/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.osgi.framework.BundleContext;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.property.dataset.fields.table.widget.WJRProperty;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.ic.ICParameterContributor;
import com.jaspersoft.studio.server.ic.ResourcePropertyDescription;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.plugin.ExtensionManager;
import com.jaspersoft.studio.server.utils.HttpUtils;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractJRUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.jaspersoft.studio.server"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	public static final String ICPATH = "icpath"; //$NON-NLS-1$
	public static final String DSPATH = "dspath"; //$NON-NLS-1$
	public static final String RSPATH = "rspath"; //$NON-NLS-1$
	public static final String RUPATH = "rupath"; //$NON-NLS-1$
	public static final String REPPATH = "reppath"; //$NON-NLS-1$

	public static final String SERVER_CATEGORY = "com.jaspersoft.studio.jrs.category:JasperReports.server";//$NON-NLS-1$

	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
	 * BundleContext )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		HttpUtils.patchUri("lowMask", "L_DASH");
		HttpUtils.patchUri("highMask", "H_DASH");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
	 * BundleContext )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	@Override
	protected void postStartOperations() {
		super.postStartOperations();

		ICParameterContributor.initMetadata();
		AExporter.initMetadata();
		WJRProperty.addCallback(Activator.ICPATH, c -> new ResourcePropertyDescription(c.getPropertyName(),
				c.getLabel(), c.getDescription(), false, null, c.getValue()) {
			@Override
			protected boolean isResourceCompatible(AMResource r) {
				return r.getValue().getWsType().equals(ResourceDescriptor.TYPE_INPUT_CONTROL);
			}
		});

		WJRProperty.addCallback(Activator.DSPATH, c -> new ResourcePropertyDescription(c.getPropertyName(),
				c.getLabel(), c.getDescription(), false, null, c.getValue()) {
			@Override
			protected boolean isResourceCompatible(AMResource r) {
				return SelectorDatasource.isDatasource(r.getValue());
			}
		});

		WJRProperty.addCallback(Activator.RUPATH, c -> new ResourcePropertyDescription(c.getPropertyName(),
				c.getLabel(), c.getDescription(), false, null, c.getValue()) {
			@Override
			protected boolean isResourceCompatible(AMResource r) {
				return r instanceof MReportUnit;
			}
		});

		WJRProperty.addCallback(Activator.RSPATH, c -> new ResourcePropertyDescription(c.getPropertyName(),
				c.getLabel(), c.getDescription(), false, null, c.getValue()));

		WJRProperty.addCallback(Activator.REPPATH, c -> new ResourcePropertyDescription(c.getPropertyName(),
				c.getLabel(), c.getDescription(), false, null, c.getValue()) {
			@Override
			protected boolean isResourceCompatible(AMResource r) {
				return r instanceof MJrxml;
			}
		});

		Job initParametersJob = new Job("Init JRS built-in parameters") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					JRSBuiltInParameterProvider.init();
				} catch (Exception ex) {
					logError(ex);
					return Status.CANCEL_STATUS;
				}
				return Status.OK_STATUS;
			}
		};
		initParametersJob.setPriority(Job.LONG);
		initParametersJob.schedule(5000);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	private static ExtensionManager extensionManager;

	public static ExtensionManager getExtManager() {
		if (extensionManager == null) {
			extensionManager = new ExtensionManager();
			extensionManager.init();
		}
		return extensionManager;
	}

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

}
