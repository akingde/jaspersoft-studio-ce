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
package com.jaspersoft.studio.components.customvisualization.creation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import com.jaspersoft.studio.components.customvisualization.messages.Messages;

/**
 * Compile a list of build.js and print the output of the compilation
 * on a console. The operation can be aborted since it is inside an eclipse job
 * 
 * @author Orlandin Marco
 *
 */
public class ConsoleExecuter {
	
	/**
	 * Name of the output console
	 */
	private static final String CONSOLE_NAME = "Custom Visualization Component Console";
	
	/**
	 * Not null list of the file to compile
	 */
	private List<IFile> filesToCompile;
	
	/**
	 * Command to compile the build.js in the current working directory. The command
	 * is the same for each file and so it is initialized in the constructor and it
	 * is reused for each compilation.
	 */
	private String[] command;
	
	/**
	 * The current active page
	 */
	private IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	
	/**
	 * Job to execute the compile command for each file and print the output
	 * inside a console. The job can be cancelled but the current compilation
	 * must be finished before it stops
	 */
	private Job readOutputJob = new Job("Compiling job") {
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			//Create the console
			MessageConsole console = getCleanConsole();
			MessageConsoleStream outputSteam = console.newMessageStream();
			for(IFile fileToCompile : filesToCompile){
				if (monitor.isCanceled()) return Status.CANCEL_STATUS;
				if (fileToCompile.exists()){
					try{
						monitor.beginTask("Compiling "+fileToCompile.getName(), 10);
						File projectFolder = fileToCompile.getParent().getLocation().toFile();
						Process process = Runtime.getRuntime().exec(command, null, projectFolder);
						outputSteam.println(Messages.CompileDialog_startCompilation);
						BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
						BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
						
						// read the output from the command
						String s = null;
						int work = 0;
						try{	
							while ((s = stdInput.readLine()) != null) {
								outputSteam.println(s);
								work++;
								monitor.worked(work);
							}
						} catch(Exception ex){
							outputSteam.println(ex.getMessage());
						}
			
						// read any errors from the attempted command
						try{
							while ((s = stdError.readLine()) != null) {
								outputSteam.println(s);
								work++;
								monitor.worked(work);
							}
						} catch (Exception ex){
							outputSteam.println(ex.getMessage());
						}
						outputSteam.println(Messages.CompileDialog_endCompilation);
						fileToCompile.getParent().refreshLocal(IProject.DEPTH_ONE, new NullProgressMonitor());
						monitor.done();
					} catch(Exception ex){
						ex.printStackTrace();
						outputSteam.println(ex.getMessage());
					}
				} else {
					outputSteam.println(Messages.BuildComponentHandler_errorMessage);
				}
			}
			try {
				outputSteam.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Status.OK_STATUS;
		}
	};
	
	/**
	 * Create the class and start the compilation process, showing 
	 * the output into a console view
	 * 
	 * @param filesToCompile the build.js files that need to be compiled
	 */
	public ConsoleExecuter(List<IFile> filesToCompile){
		this.filesToCompile = filesToCompile;
		//Export the resources necessary for the compilation outside the JSS plugin
		File compiler = fetchResource("com/jaspersoft/studio/components/customvisualization/creation/resources/compiler.jar", "compiler.jar"); //$NON-NLS-1$ //$NON-NLS-2$
		File rhino = fetchResource("com/jaspersoft/studio/components/customvisualization/creation/resources/js.jar", "js.jar"); //$NON-NLS-1$ //$NON-NLS-2$
		File rJs = fetchResource("com/jaspersoft/studio/components/customvisualization/creation/resources/r.js", "r.js"); //$NON-NLS-1$ //$NON-NLS-2$
		command = new String[] {
				"java",
				"-classpath",
				rhino.toString() + File.pathSeparator + compiler.toString(),
				"org.mozilla.javascript.tools.shell.Main",
				rJs.toString(),
				"-o",
				"build.js"};
				
		readOutputJob.setPriority(Job.SHORT);
		readOutputJob.schedule(); // start as soon as possible
	}

	/**
	 * Return a clean console where the output of the compilation can be written
	 * 
	 * @return a not null console
	 */
	private MessageConsole getCleanConsole() {
		final MessageConsole myConsole = findConsole(CONSOLE_NAME);

		// Try to show the console
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				try {
					String id = IConsoleConstants.ID_CONSOLE_VIEW;
					IConsoleView view = (IConsoleView) page.showView(id);
					view.display(myConsole);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		myConsole.clearConsole();
		return myConsole;
	}
	
	/**
	 * Search an output console with a specific name.
	 * If the console can't be found a new one is created. 
	 * 
	 * @param name name of the console
	 * @return A not null console with a specific name
	 */
  public static MessageConsole findConsole(String name) {
    ConsolePlugin plugin = ConsolePlugin.getDefault();
    IConsoleManager conMan = plugin.getConsoleManager();
    IConsole[] existing = conMan.getConsoles();
    for (int i = 0; i < existing.length; i++)
       if (name.equals(existing[i].getName()))
          return (MessageConsole) existing[i];
    //no console found, so create a new one
    MessageConsole myConsole = new MessageConsole(name, null);
    conMan.addConsoles(new IConsole[]{myConsole});
    return myConsole;
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
	 * 
	 * REMOVED, USING array of arguments to exec instead. For some reason this methos was failing
	 * on OSX.
	 * 
	 * Generate the command to create the custom visualization component.
	 * 
	 * @param compiler path to the compiler jar
	 * @param rhino path to the rhino jar
	 * @param rJs path to the r.js script 
	 * @param buildFile path to the build.js script
	 * @return a command to executed inside the runtime

	private String generateCommand(File compiler, File rhino, File rJs, File buildFile){
		
		StringBuilder command = new StringBuilder();
		
		command.append("java -classpath "); //$NON-NLS-1$
		command.append("\"").append(rhino.getAbsolutePath()); //$NON-NLS-1$
		command.append(File.pathSeparator);
		command.append(compiler.getAbsolutePath()); //$NON-NLS-1$
		command.append("\"");
		command.append(" "); //$NON-NLS-1$
		command.append("org.mozilla.javascript.tools.shell.Main"); //$NON-NLS-1$
		command.append(" "); //$NON-NLS-1$
		command.append("\"").append(rJs.getAbsolutePath()).append("\""); //$NON-NLS-1$
		command.append(" -o "); //$NON-NLS-1$
		command.append(" "); //$NON-NLS-1$
		command.append("\"").append(buildFile.getAbsolutePath()).append("\""); //$NON-NLS-1$
		
		return command.toString();
	}
	
	*/
}
