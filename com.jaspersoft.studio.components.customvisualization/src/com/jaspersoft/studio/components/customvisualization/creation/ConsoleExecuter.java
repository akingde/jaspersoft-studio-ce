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
import java.io.FileReader;
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

import com.ibm.icu.text.MessageFormat;
import com.jaspersoft.studio.components.customvisualization.CustomVisualizationActivator;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.preferences.util.PreferencesUtils;

/**
 * Compile a list of build.js and print the output of the compilation
 * on a console. The operation can be aborted since it is inside an eclipse job
 * 
 * @author Orlandin Marco
 *
 */
public class ConsoleExecuter {
	
	/**
	 * name of the build file
	 */
	private static final String BUILD_FILE_NAME = "build.js"; //$NON-NLS-1$
	
	/**
	 * Name of the output console
	 */
	private static final String CONSOLE_NAME = "Custom Visualization Component Console"; //$NON-NLS-1$
	
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
	private Job readOutputJob = new Job("Compiling job") { //$NON-NLS-1$
		
		
		private boolean checkJavaVersion(MessageConsoleStream outputStream, IProgressMonitor monitor){
			String checkVersionCommand = getJavaCommand() + " -version"; //$NON-NLS-1$
			try {
				Process process = Runtime.getRuntime().exec(checkVersionCommand);
				outputStream.println(Messages.ConsoleExecuter_checkVersionStart);
				
				//BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				
				// read the output from the command
				String s = null;
				/*try{	
					while ((s = stdInput.readLine()) != null) {
						String line = s.toLowerCase().trim();
						if (line.startsWith("java version")){
							int start = line.indexOf("\"");
							int end = start + line.lastIndexOf("\"");
							String version = line.substring(start, end);
							if (version.startsWith("1.7")) return true;
							else {
								outputStream.println("You need a Java 1.7 to compile this component, you can both:");
								outputStream.println("-Remove your current java version and install a java 1.7");
								outputStream.println("-Set the Jaspersoft Studio Property "+CustomVisualizationActivator.JAVA_PATH_PROPERTY+" with the path of the bin folder of a java 1.7");
							}
						}
					}
				} catch(Exception ex){
					outputStream.println("Error checking your java version");
					outputStream.println(ex.getMessage());
				}*/
	
				// read any errors from the attempted command
				try{
					while ((s = stdError.readLine()) != null) {
						String line = s.toLowerCase().trim();
						if (line.startsWith("java version")){ //$NON-NLS-1$
							int start = line.indexOf("\"")+1; //$NON-NLS-1$
							int end = line.lastIndexOf("\""); //$NON-NLS-1$
							String version = line.substring(start, end);
							if (version.startsWith("1.7")) { //$NON-NLS-1$
								outputStream.println(Messages.ConsoleExecuter_checkSuccess);
								return true;
							} else {
								outputStream.println(MessageFormat.format(Messages.ConsoleExecuter_checkWrongVersion1, new Object[]{version}));
								outputStream.println(Messages.ConsoleExecuter_checkWrongVersion2);
								outputStream.println(MessageFormat.format(Messages.ConsoleExecuter_checkWrongVersion3, new Object[]{CustomVisualizationActivator.JAVA_PATH_PROPERTY}));
							}
						}
					}
				} catch (Exception ex){
					outputStream.println(ex.getMessage());
				}
			} catch (IOException e) {
				outputStream.println(MessageFormat.format(Messages.ConsoleExecuter_checkBadCommand1, new Object[]{checkVersionCommand}));
				String aaa = Messages.ConsoleExecuter_checkBadCommand2;
				outputStream.println(MessageFormat.format(aaa, new Object[]{CustomVisualizationActivator.JAVA_PATH_PROPERTY}));
				e.printStackTrace();
			}
			return false;
		} 
		
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			//Create the console
			MessageConsole console = getCleanConsole();
			MessageConsoleStream outputStream = console.newMessageStream();
			for(IFile fileToCompile : filesToCompile){
				if (monitor.isCanceled()) return Status.CANCEL_STATUS;
				if (fileToCompile.exists()){
					try{
						monitor.beginTask("Compiling "+fileToCompile.getName(), 10); //$NON-NLS-1$
						
						if (checkJavaVersion(outputStream, monitor)){
							
							File projectFolder = fileToCompile.getParent().getLocation().toFile();
							
							//Remove the old file, if the name is found inside the build.js and if it is present
							String filename = getOutputFilename(projectFolder);
							if (filename != null){
								File oldFile = new File(projectFolder, filename);
								if (oldFile.exists() && oldFile.delete()) {
									fileToCompile.getParent().refreshLocal(IProject.DEPTH_ONE, new NullProgressMonitor());
								}
							}
							
							Process process = Runtime.getRuntime().exec(command, null, projectFolder);
							outputStream.println(Messages.CompileDialog_startCompilation);
							BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
							BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
							
							// read the output from the command
							String s = null;
							int work = 0;
							try{	
								while ((s = stdInput.readLine()) != null) {
									outputStream.println(s);
									work++;
									monitor.worked(work);
								}
							} catch(Exception ex){
								outputStream.println(ex.getMessage());
							}
				
							// read any errors from the attempted command
							try{
								while ((s = stdError.readLine()) != null) {
									outputStream.println(s);
									work++;
									monitor.worked(work);
								}
							} catch (Exception ex){
								outputStream.println(ex.getMessage());
							}
							outputStream.println(Messages.CompileDialog_endCompilation);
							fileToCompile.getParent().refreshLocal(IProject.DEPTH_ONE, new NullProgressMonitor());
						}
						monitor.done();
					} catch(Exception ex){
						ex.printStackTrace();
						outputStream.println(ex.getMessage());
					}
				} else {
					outputStream.println(Messages.BuildComponentHandler_errorMessage);
				}
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Status.OK_STATUS;
		}
	};
	
	/**
	 * Read the build.js to find the output file name inside the out section
	 * 
	 * @param projectFolder the folder where the build.js is contained
	 * @return the output filename if it can be found inside the build.js, if something
	 * goes wrong return null
	 */
	private String getOutputFilename(File projectFolder){
		BufferedReader br = null;
		String result = null;
		try{
			String sCurrentLine;
			br = new BufferedReader(new FileReader(new File(projectFolder, BUILD_FILE_NAME)));
			while ((sCurrentLine = br.readLine()) != null && result == null) {
				String trimmed = sCurrentLine.trim();
				if (trimmed.toLowerCase().startsWith("out:")){ //$NON-NLS-1$
					String fileName = trimmed.substring(4);
					int firstQuote = fileName.indexOf("\""); //$NON-NLS-1$
					if (firstQuote == -1) break;
					int secondQuote = fileName.indexOf("\"", firstQuote+1); //$NON-NLS-1$
					if (secondQuote == -1) break;
					result = fileName.substring(firstQuote+1, secondQuote);
				}
			}
		} catch (Exception ex){
		}
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private String getJavaCommand(){
		String javaPath = PreferencesUtils.getJasperReportsProperty(CustomVisualizationActivator.JAVA_PATH_PROPERTY);
		if (javaPath == null || javaPath.trim().isEmpty()) return "java"; //$NON-NLS-1$
		if (!javaPath.endsWith(File.pathSeparator)) javaPath+=File.separator;
		return javaPath+"java"; //$NON-NLS-1$
	}
	
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
				getJavaCommand(),
				"-classpath", //$NON-NLS-1$
				rhino.toString() + File.pathSeparator + compiler.toString(),
				"org.mozilla.javascript.tools.shell.Main", //$NON-NLS-1$
				rJs.toString(),
				"-o", //$NON-NLS-1$
				BUILD_FILE_NAME};
				
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
