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
package com.jaspersoft.studio.rcp.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.internal.handlers.RestartWorkbenchHandler;
import org.eclipse.ui.menus.UIElement;
import org.eclipse.ui.services.ISourceProviderService;

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
	
	/**
	 * Execute the command, read the regional code from the paramenter passed by the plugin file and
	 * call the method to write the regional code to the configuration. If the configuration is modified
	 * than call a restart
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String locale = event.getParameter("com.jaspersoft.studio.switchlanguage.locale"); //$NON-NLS-1$
		boolean needToRestart = changeLocale(locale);
		if (needToRestart) {
			MessageDialog dialog = new MessageDialog(UIUtils.getShell(), Messages.SwitchLanguageHandler_restartTitle, null,
					Messages.SwitchLanguageHandler_restartMessage, MessageDialog.QUESTION, new String[] { Messages.common_yes , Messages.common_no}, 1); 
			int selection = dialog.open();
			if (selection == 0)
				return new RestartWorkbenchHandler().execute(event);
			else {
				//Request an update of the locale provider and force the update of the menu item, in this way the language
				//menu is show updated even without a restart
				IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
				ISourceProviderService service = (ISourceProviderService) window.getService(ISourceProviderService.class);
				LocaleSourceProvider sessionSourceProvider = (LocaleSourceProvider) service.getSourceProvider(LocaleSourceProvider.ACTUAL_LOCALE);
				sessionSourceProvider.forceRefreshLocale();
				ICommandService commandService = (ICommandService)window.getService(ICommandService.class); 
				commandService.refreshElements("com.jaspersoft.studio.switchlanguage.command", null);
			}
		}
		return null;
	}
	
	/**
	 * close the passed BufferedReader
	 * 
	 * @param reader BufferedReader to close
	 */
	private static void closeStream(BufferedReader reader){
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Take the actual language code and if it is the same of the updated 
	 * element thant the element is marked as checked
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateElement(UIElement element, Map parameters) {
		 element.setChecked(LocaleSourceProvider.getLocale().equals(parameters.get("com.jaspersoft.studio.switchlanguage.locale")));
	}
	

	/**
	 * Read the configuration file of the application and rewerite it with a new regional code
	 * if the code is changed then it is also requested a platform restart.
	 * The regional code will be set at the place of the old code if found, otherwise before the 
	 * first parameter found between -clean, -vm, -vmargs. If none of this parameters are found then it is set at the end of the file
	 * 
	 * @param locale
	 * @return
	 */
	private static boolean changeLocale(String locale) {
		URL location = null;
		String path = ConfigurationPathProvider.getPath();
		try {
			location = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		boolean fileChanged = false;
		try {
			String fileName = location.getFile();
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			BufferedWriter out = null;
			try {
				String line = in.readLine();
				List<String> configLines = new ArrayList<String>();
				int localePosition = -1;
				int lineNumber = 0;
				while(line !=null){
					if (line.equals("-nl")) localePosition = lineNumber+1; //$NON-NLS-1$
					else if (localePosition == -1 && (line.equals("-vmargs") || line.equals("-clean") || line.equals("-vm"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						configLines.add("-nl"); //$NON-NLS-1$
						configLines.add(""); //$NON-NLS-1$
						localePosition = lineNumber+1;
					}
					configLines.add(line);
					lineNumber++;
					line = in.readLine();
				}
				if (localePosition != -1) {
					if (configLines.get(localePosition).equals(locale)){
						closeStream(in);
						//The file has already the right regional code, there is no need to restart eclipse
						return false;
					} else  configLines.set(localePosition, locale);
				}
				else {
					configLines.add("-nl"); //$NON-NLS-1$
					configLines.add(locale);
				}
				//Keep the old file as backup
				File file = new File(fileName);
				fileName += ".bak"; //$NON-NLS-1$
				File backupFile = new File(fileName);
				if (backupFile.exists()) backupFile.delete();
				file.renameTo(backupFile);
				out = new BufferedWriter(new FileWriter(location.getFile()));
				int writtenLines = 1;
				for(String outLine : configLines) { 
						out.write(outLine);
						if (writtenLines < configLines.size()) out.newLine();
						writtenLines++;
				} 
				out.flush();
			} finally {
				closeStream(in);
				if (out != null) {
					try {
						out.close();
						fileChanged = true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			//Configuration file not found, show an error message
			MessageDialog.openWarning(UIUtils.getShell(), Messages.SwitchLanguageHandler_errorTitle,  MessageFormat.format(Messages.SwitchLanguageHandler_errorMessage, new Object[]{path}));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileChanged;
	}
}
