/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.javascript;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;

/**
 * 
 * Command executed to compile a custom visualization component and 
 * generate a javascript with inside all the component informations
 * 
 * @author Orlandin Marco
 *
 */
public class BuildComponentHandler implements IHandler {

	/**
	 * Return the selected project
	 * 
	 * @return the selected project if it is a project with the nature of a custom visualization 
	 * component, null otherwise
	 */
	private IProject getSelectedProject(){
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null && activeWorkbenchWindow.getActivePage() != null) {
		 ISelection selection = activeWorkbenchWindow.getActivePage().getSelection();
			if (selection instanceof IStructuredSelection) {
				for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
					Object element = it.next();
					IProject project = null;
					if (element instanceof IProject) {
						project = (IProject) element;
					} else if (element instanceof IAdaptable) {
						project = (IProject) ((IAdaptable) element).getAdapter(IProject.class);
					}
					try {
						if (project.hasNature(CustomComponentNature.NATURE_ID)) return project;
						else return null;
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Read a resource from the current plugin and save it with a specific
	 * name inside the temp folder. Then return a file reference to the new
	 * created file. If a file was already define inside the temp folder with
	 * the same name, then it is returned without recreate it
	 * 
	 * @param path the path of the resource inside the plugin
	 * @param fileName the name of the file that will be created on the temp folder
	 * @return a file reference to the resource or null if it can't be found
	 */
	private File fetchResource(String path, String fileName){
		try{
			URL url = getClass().getClassLoader().getResource(path);
			InputStream is = url.openStream();   
			String tempDir = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
			File tempFile = new File(tempDir, fileName);
			if (!tempFile.exists()){
				FileOutputStream outputStream = new FileOutputStream(tempFile);
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = is.read(bytes)) != -1) {
						outputStream.write(bytes, 0, read);
				}
				outputStream.close();
			}
			return tempFile;
		} catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Return the correct classpath separator for the current operative 
	 * system
	 * 
	 * @return the ";" if the current operative system is windows, ":" 
	 * otherwise
	 */
	private String getOsDependendSeprator(){
		if (Util.isWin32() || Util.isWindows()) return ";"; //$NON-NLS-1$
		else return ":"; //$NON-NLS-1$
	}
	
	/**
	 * Generate the command to create the custom visualization component.
	 * 
	 * @param compiler path to the compiler jar
	 * @param rhino path to the rhino jar
	 * @param rJs path to the r.js script 
	 * @param buildFile path to the build.js script
	 * @return a command to executed inside the runtime
	 */
	private String generateCommand(File compiler, File rhino, File rJs, File buildFile){
		String command = "java -classpath \""+rhino.getAbsolutePath() + "\"" +  getOsDependendSeprator(); //$NON-NLS-1$
		command += "\""+compiler.getAbsolutePath() + "\" org.mozilla.javascript.tools.shell.Main \""+ rJs.getAbsolutePath()+"\" -o \""+buildFile.getAbsolutePath()+"\""; //$NON-NLS-1$ //$NON-NLS-2$
		return command;
	}
	
	/**
	 * Setup the environment required to executed the command to compile the custom visualization
	 * component project and the execute it. The result of the command is print inside a separate
	 * dialog
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IProject project = getSelectedProject();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		File projectFolder = new File(workspace.getRoot().getLocation().toFile(), project.getName());
		File buildFile = new File(projectFolder, "build.js"); //$NON-NLS-1$
		//Check if the build file is mandatory
		if (buildFile.exists()){
			//Export the resources necessary for the compilation outside the JSS plugin
			File compiler = fetchResource("com/jaspersoft/studio/javascript/resources/compiler.jar", "compiler.jar"); //$NON-NLS-1$ //$NON-NLS-2$
			File rhino = fetchResource("com/jaspersoft/studio/javascript/resources/js.jar", "js.jar"); //$NON-NLS-1$ //$NON-NLS-2$
			File rJs = fetchResource("com/jaspersoft/studio/javascript/resources/r.js", "r.js"); //$NON-NLS-1$ //$NON-NLS-2$
			String command = generateCommand(compiler, rhino, rJs, buildFile);
			//System.out.println(command);
			try {
				//Run the compilation process and print the output inside a dialog
				Process proc = Runtime.getRuntime().exec(command, null, projectFolder);
				CompileDialog dialog = new CompileDialog(UIUtils.getShell(), proc);
				if (dialog.open() == IDialogConstants.OK_ID){
					try {
						project.refreshLocal(IProject.DEPTH_ONE, new NullProgressMonitor());
					} catch (CoreException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			MessageDialog.openError(UIUtils.getShell(), Messages.BuildComponentHandler_errorTitle, Messages.BuildComponentHandler_errorMessage);
		}
		return null;
	}
	
	//NOT USED METHODS

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}
	
}
