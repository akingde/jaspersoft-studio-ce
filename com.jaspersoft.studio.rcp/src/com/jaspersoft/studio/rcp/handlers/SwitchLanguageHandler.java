/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.rcp.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.text.MessageFormat;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.handlers.RestartWorkbenchHandler;
import org.eclipse.ui.menus.UIElement;
import org.eclipse.ui.services.ISourceProviderService;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

/**
 * 
 * Action called when the the user select a new language
 * 
 * @author Orlandin Marco
 * 
 */
@SuppressWarnings("restriction")
public class SwitchLanguageHandler extends AbstractHandler implements IElementUpdater {

	private static final String PROP_EXIT_CODE = "eclipse.exitcode"; //$NON-NLS-1$

	private static final String PROP_EXIT_DATA = "eclipse.exitdata"; //$NON-NLS-1$
	
	private static final String CMD_NL = "-nl"; //$NON-NLS-1$
	
	private static final String PROP_VM = "eclipse.vm"; //$NON-NLS-1$

	private static final String PROP_VMARGS = "eclipse.vmargs"; //$NON-NLS-1$

	private static final String PROP_COMMANDS = "eclipse.commands"; //$NON-NLS-1$

	private static final String NEW_LINE = "\n"; //$NON-NLS-1$
	
	private static final String CMD_VMARGS = "-vmargs"; //$NON-NLS-1$

	/**
	 * Execute the command, read the regional code from the parameter passed by
	 * the plugin file and call the method to write the regional code to the
	 * configuration. If the configuration is modified than call a restart
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String locale = event
				.getParameter("com.jaspersoft.studio.switchlanguage.locale"); //$NON-NLS-1$
		boolean needToRestart = ConfigurationManager.changeLocale(locale);
		if (needToRestart) {
			MessageDialog dialog = new MessageDialog(UIUtils.getShell(),
					Messages.SwitchLanguageHandler_restartTitle, null,
					Messages.SwitchLanguageHandler_restartMessage,
					MessageDialog.QUESTION, new String[] { Messages.common_yes,
							Messages.common_no }, 1);
			int selection = dialog.open();
			if (selection == 0) {
				// Some OS (linux\mac) dosen't reload the configuration file
				// after the restart. So when eclipse is
				// re-launched it is done with the -nl parameter to the new
				// locale. Essentially it's like it is launched
				// from command line with the explicit nl parameter
				System.setProperty(PROP_EXIT_DATA, buildCommandLine(locale));
				System.setProperty(PROP_EXIT_CODE,
						IApplication.EXIT_RELAUNCH.toString());
				return new RestartWorkbenchHandler().execute(event);
			} else {
				// Request an update of the locale provider and force the update
				// of the menu item, in this way the language
				// menu is show updated even without a restart
				IWorkbenchWindow window = HandlerUtil
						.getActiveWorkbenchWindow(event);
				ISourceProviderService service = (ISourceProviderService) window
						.getService(ISourceProviderService.class);
				LocaleSourceProvider sessionSourceProvider = (LocaleSourceProvider) service
						.getSourceProvider(LocaleSourceProvider.ACTUAL_LOCALE);
				sessionSourceProvider.forceRefreshLocale();
				ICommandService commandService = (ICommandService) window
						.getService(ICommandService.class);
				commandService.refreshElements(
						"com.jaspersoft.studio.switchlanguage.command", null);
			}
		}
		return null;
	}

	/**
	 * Take the actual language code and if it is the same of the updated
	 * element that the element is marked as checked
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateElement(UIElement element, Map parameters) {
		element.setChecked(LocaleSourceProvider.getLocale().equals(
				parameters.get("com.jaspersoft.studio.switchlanguage.locale")));
	}
	
	/**
	 * Generate a starting parameter by reading the old parameters and changing the nl value or adding it if not present.
	 * It's equivalent to launch the application with an -nl followed by the regional code arguments
	 * 
	 * @param nl
	 *          the regional code
	 * @return the full arguments line used to restart the application
	 */

	private String buildCommandLine(String locale) {
		String property = System.getProperty(PROP_VM);
		StringBuffer result = new StringBuffer(512);

		if (property != null) {
			result.append(property);
			result.append(NEW_LINE);
		}


		// append the vmargs and commands. Assume that these already end in \n
		String vmargs = System.getProperty(PROP_VMARGS);
		//if (vmargs != null) {
			//result.append(vmargs);
		//}

		// append the rest of the args, replacing or adding -data as required
		property = System.getProperty(PROP_COMMANDS);
		if (property != null) {// find the index of the arg to replace its value
			int cmd_nl_pos = property.lastIndexOf(CMD_NL);
			if (cmd_nl_pos != -1) {
				cmd_nl_pos += CMD_NL.length() + 1;
				result.append(property.substring(0, cmd_nl_pos));
				result.append(locale);
				result.append(property.substring(property.indexOf('\n', cmd_nl_pos)));
			} else {
				result.append(CMD_NL);
				result.append(NEW_LINE);
				result.append(locale);
				result.append(NEW_LINE);
				result.append(property);
			}
		}
		// put the vmargs back at the very end (the eclipse.commands property
		// already contains the -vm arg)
		if (vmargs != null) {
			result.append(CMD_VMARGS);
			result.append(NEW_LINE);
			result.append(vmargs);
		}

		return result.toString();
	}
	
	@SuppressWarnings("unused")
	private String buildCommandLineFromIni() {
		File configurationFile = ConfigurationManager.getApplicationConfigurationFile();
		BufferedReader in = null; 
		BufferedWriter out = null;
		StringBuffer result = new StringBuffer(512);
		try {
			in = new BufferedReader(new FileReader(configurationFile));
			String line = in.readLine();
			while (line != null) {
				result.append(line);
				result.append(NEW_LINE);
				line = in.readLine();
			}
		} catch (Exception ex){
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(ex);
			// Configuration file not found, show an error message
			MessageDialog.openWarning(UIUtils.getShell(),
					Messages.SwitchLanguageHandler_errorTitle,
					MessageFormat.format(
							Messages.SwitchLanguageHandler_errorMessage,
							new Object[] { configurationFile.getAbsolutePath() }));
		} finally {
			FileUtils.closeStream(in);
			FileUtils.closeStream(out);
		}
		return result.toString();
	}
}
