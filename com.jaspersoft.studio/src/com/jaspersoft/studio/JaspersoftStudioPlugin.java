/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio;

import java.io.PrintStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.progress.UIJob;
import org.osgi.framework.BundleContext;

import com.jaspersoft.studio.data.defaults.DefaultDAManager;
import com.jaspersoft.studio.editor.gef.decorator.DecoratorManager;
import com.jaspersoft.studio.editor.gef.ui.actions.EditorSettingsContributorManager;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.preview.input.ext.InputControlTypeManager;
import com.jaspersoft.studio.editor.toolitems.ToolItemsManager;
import com.jaspersoft.studio.jasper.ComponentConverterManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.preferences.GlobalPreferencePage;
import com.jaspersoft.studio.preferences.util.PreferencesUtils;
import com.jaspersoft.studio.property.PostSetValueManager;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.property.section.report.util.PHolderUtil;
import com.jaspersoft.studio.statistics.UsageManager;
import com.jaspersoft.studio.utils.BrandingInfo;
import com.jaspersoft.studio.utils.jasper.DriversManager;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.category.ReportTemplatesWizardPage;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;
import net.sf.jasperreports.eclipse.builder.JasperReportsNature;
import net.sf.jasperreports.eclipse.builder.jdt.JDTUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.wizard.project.ProjectUtil;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;

/*
 * The main plugin class to be used in the desktop.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * 
 * @version $Id: JasperCompileManager.java 1229 2006-04-19 13:27:35 +0300 (Wed, 19 Apr 2006) teodord $
 */
public class JaspersoftStudioPlugin extends AbstractJRUIPlugin {

	public static final String ICONS_RESOURCES_REFRESH_16_PNG = "icons/resources/refresh-16.png"; //$NON-NLS-1$

	public static final String PLUGIN_ID = "com.jaspersoft.studio"; //$NON-NLS-1$

	public static final String COMPONENTS_ID = "com.jaspersoft.studio.components"; //$NON-NLS-1$

	// The shared instance.

	private static ExtensionManager extensionManager;

	private static ComponentConverterManager converterManager;

	/**
	 * The current instance of the plugin.
	 */
	private static JaspersoftStudioPlugin plugin;

	/**
	 * The update manager used to handle the usage statistics for the current instance
	 */
	private UsageManager manager;

	/**
	 * Gets the single instance of JaspersoftStudioPlugin.
	 * 
	 * @return the plugin instance singleton.
	 */
	public static JaspersoftStudioPlugin getInstance() {
		// Plugin cannot be null, Eclipse takes care to instance it at startup.
		return plugin;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * The constructor.
	 */
	public JaspersoftStudioPlugin() {
		plugin = this;
	}

	/**
	 * Return the usage manager defined in the current instance
	 * 
	 * @return a not null usage manager
	 */
	public synchronized UsageManager getUsageManager() {
		if (manager == null) {
			manager = new UsageManager();
		}
		return manager;
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
		// Must stop the manager of the statistics before to set the plugin instance to null
		// since the usage manager uses the plugin instance to write on the logger
		if (manager != null) {
			manager.stop();
		}
		plugin = null;
		super.stop(context);
	}

	@Override
	protected void postStartOperations() {
		super.postStartOperations();

		DataQueryAdapters.initMetadata();
		PHolderUtil.initMetadata();
		LayoutManager.initMetadata();

		// Sets the branding information
		BrandingInfo info = new BrandingInfo();
		info.setProductName(Messages.JaspersoftStudioPlugin_BrandingInfoJSSPlugin);
		info.setProductVersion(getBundle().getVersion().toString());
		info.setProductMainBundleID(PLUGIN_ID);
		setBrandingInformation(info);
		logInfo(NLS.bind(Messages.JaspersoftStudioPlugin_StartingJSSBundleMsg, info.getProductVersion()));

		// Some property checks
		JasperReportsConfiguration c = JasperReportsConfiguration.getDefaultInstance();
		String key = "net.sf.jasperreports.default.font.name"; //$NON-NLS-1$
		JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).setProperty(key, c.getProperty(key));
		key = "net.sf.jasperreports.default.font.size"; //$NON-NLS-1$
		JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance()).setProperty(key, c.getProperty(key));

		// Fix some JasperReports properties installation related.
		// See Bugzilla #42275.
		String defJRProperty = DefaultJasperReportsContext.getInstance()
				.getProperty("net.sf.jasperreports.compiler.classpath"); //$NON-NLS-1$
		PreferencesUtils.storeJasperReportsProperty("net.sf.jasperreports.compiler.classpath", Misc.nvl(defJRProperty)); //$NON-NLS-1$
		defJRProperty = DefaultJasperReportsContext.getInstance().getProperty("net.sf.jasperreports.compiler.temp.dir"); //$NON-NLS-1$
		PreferencesUtils.storeJasperReportsProperty("net.sf.jasperreports.compiler.temp.dir", Misc.nvl(defJRProperty)); //$NON-NLS-1$

		// Initialize the extension manager
		getExtensionManager();

		// Pre-cache template images
		Job precacheImagesJob = new Job(Messages.JaspersoftStudioPlugin_CachingTemplateImagesJob) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				ReportTemplatesWizardPage.templateImagesPreCache();
				return Status.OK_STATUS;
			}
		};
		precacheImagesJob.setPriority(Job.LONG);
		precacheImagesJob.schedule();

		// Force the initialization of some JR extensions
		// Job extensionsPreloadingJob = new Job(Messages.JaspersoftStudioPlugin_CachingJRExtensionsJob) {
		// @Override
		// protected IStatus run(IProgressMonitor monitor) {
		// ExtensionLoader.initializeJRExtensions(monitor);
		// return Status.OK_STATUS;
		// }
		// };
		// extensionsPreloadingJob.setPriority(Job.LONG);
		// extensionsPreloadingJob.schedule();

		// JSS console activation (if requested)
		if (getInstance().getPreferenceStore().getBoolean(GlobalPreferencePage.JSS_ENABLE_INTERNAL_CONSOLE)) {
			UIJob j = new UIJob(Messages.JaspersoftStudioPlugin_InstallingJSSConsoleJob) {
				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					try {
						installJSSConsole();
						return Status.OK_STATUS;
					} catch (Exception e) {
						// something went wrong while trying to re-assign the standard output and error streams.
						return new Status(IStatus.ERROR, PLUGIN_ID, Messages.JaspersoftStudioPlugin_ConsoleInstallationError, e);
					}
				}
			};
			j.schedule();
		}
		// Start the usage statistics plugin, among the other operations it will
		// check for new versions
		getUsageManager().start();

		// Sanity checks for Java Compiling settings:
		// possible issues when bundling a JDK 1.8 in 3.8.2 platform
		if (isRCP()) {
			JDTUtils.forceWorkspaceCompilerSettings(JavaCore.VERSION_1_6);
			try {
				IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
				for (IProject prj : projects) {
					if (ProjectUtil.isOpen(prj) && prj.hasNature(JasperReportsNature.NATURE_ID)) {
						JDTUtils.forceJRProjectCompilerSettings(JavaCore.create(prj), JavaCore.VERSION_1_6);
					}
				}
			} catch (CoreException e) {
				logError(e);
			}
		}
	}

	public static ExtensionManager getExtensionManager() {
		if (extensionManager == null) {
			extensionManager = new ExtensionManager();
			extensionManager.init();
		}
		return extensionManager;
	}

	public static ComponentConverterManager getComponentConverterManager() {
		if (converterManager == null) {
			converterManager = new ComponentConverterManager();
			converterManager.init();
		}
		return converterManager;
	}

	private static DecoratorManager decoratorManager;

	public static DecoratorManager getDecoratorManager() {
		if (decoratorManager == null) {
			decoratorManager = new DecoratorManager();
			decoratorManager.init();
		}
		return decoratorManager;
	}

	private static EditorSettingsContributorManager editorSettingsManager;

	public static EditorSettingsContributorManager getEditorSettingsManager() {
		if (editorSettingsManager == null) {
			editorSettingsManager = new EditorSettingsContributorManager();
			editorSettingsManager.init();
		}
		return editorSettingsManager;
	}

	private static DefaultDAManager daManager;

	public static DefaultDAManager getDefaultDAManager() {
		if (daManager == null) {
			daManager = new DefaultDAManager();
			daManager.init();
		}
		return daManager;
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
	 * Sets the branding information that will helps identify the product, for example in case of debug, diagnostics or
	 * statistics.
	 * 
	 * @param info
	 *          branding information
	 */
	public void setBrandingInformation(BrandingInfo info) {
		getPreferenceStore().putValue(BrandingInfo.BRANDING_PRODUCT_NAME, info.getProductName());
		getPreferenceStore().putValue(BrandingInfo.BRANDING_PRODUCT_VERSION, info.getProductVersion());
		getPreferenceStore().putValue(BrandingInfo.BRANDING_PRODUCT_MAINBUNDLE, info.getProductMainBundleID());
	}

	/**
	 * @return the branding information that identify the currently running product (plugin/product)
	 * 
	 */
	public BrandingInfo getBrandingInformation() {
		BrandingInfo info = new BrandingInfo();
		info.setProductName(getPreferenceStore().getString(BrandingInfo.BRANDING_PRODUCT_NAME));
		info.setProductVersion(getPreferenceStore().getString(BrandingInfo.BRANDING_PRODUCT_VERSION));
		info.setProductMainBundleID(getPreferenceStore().getString(BrandingInfo.BRANDING_PRODUCT_MAINBUNDLE));
		return info;
	}

	/**
	 * @return <code>true</code> if we should use the Eclipse Secure Storage feature,<code>false</code> otherwise
	 */
	public static boolean shouldUseSecureStorage() {
		return getInstance().getPreferenceStore().getBoolean(GlobalPreferencePage.JSS_USE_SECURE_STORAGE);
	}

	/**
	 * Creates an additional Console for the Console view. Once installed, all the messages printed on the System.out and
	 * System.err streams will be redirected here.
	 */
	private static void installJSSConsole() {
		MessageConsole jssConsole = new MessageConsole(Messages.JaspersoftStudioPlugin_JSSConsoleTitle, null);
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { jssConsole });
		MessageConsoleStream consoleStream = jssConsole.newMessageStream();
		PrintStream pstream = new PrintStream(consoleStream);
		System.setOut(pstream);
		System.setErr(pstream);
	}

	/**
	 * Check if the running JSS is a RCP or plugin version. This is done looking for the plugins com.jaspersoft.studio.rcp
	 * or com.jaspersoft.studio.pro.rcp that are available only on the RCP version
	 * 
	 * @return true if the current running JSS is an RCP version, false otherwise
	 */
	public static boolean isRCP() {
		boolean isRCP = Platform.getBundle("com.jaspersoft.studio.rcp") != null; //$NON-NLS-1$
		if (isRCP)
			return true;
		// check if it can be a pro version
		return Platform.getBundle("com.jaspersoft.studio.pro.rcp") != null; //$NON-NLS-1$
	}
}
