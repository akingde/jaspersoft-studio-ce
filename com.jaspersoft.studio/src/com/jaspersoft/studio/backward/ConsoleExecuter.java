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
package com.jaspersoft.studio.backward;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.eclipse.classpath.container.JRClasspathContainer;
import net.sf.jasperreports.eclipse.classpath.container.JRDependenciesClasspathContainer;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.util.Util;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import com.jaspersoft.studio.messages.Messages;

/**
 * Compile a jrxml file from command line using a specific version of JasperReport.
 * The file must be in the workspace to allow to get its classpath from the project
 * and use it when compiling
 * 
 * @author Orlandin Marco
 *
 */
public class ConsoleExecuter {
	
	/**
	 * Name of the output console
	 */
	private static final String CONSOLE_NAME = "JRConsole Console"; //$NON-NLS-1$
	
	/**
	 * Command to compile the jrxml file
	 */
	private String[] command;
	
	/**
	 * The file that will be compiled
	 */
	private IFile fileToCompile;
	
	/**
	 * Folder where is located the jar of jasperreports that will be used for the compilation
	 */
	private String jrFolder;
	
	/**
	 * The current active page
	 */
	private IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	
	/**
	 * Contains the id of the container that shoulden't be added to the classpath when compiling
	 */
	private static final HashSet<String> classpathExclusionSet = new HashSet<String>();
	
	static{
		classpathExclusionSet.clear();
		classpathExclusionSet.add(JRClasspathContainer.ID.toString());
		classpathExclusionSet.add(JRDependenciesClasspathContainer.ID.toString());
	}
	
	/**
	 * Job to execute the compile command The job can be cancelled but the 
	 * current compilation must be finished before it stops
	 */
	private Job readOutputJob = new Job("Compiling job") { //$NON-NLS-1$
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			//Create the console
			MessageConsole console = getCleanConsole();
			MessageConsoleStream outputStream = console.newMessageStream();
			if (monitor.isCanceled()) return Status.CANCEL_STATUS;
			if (fileToCompile.exists()){
				try{
						monitor.beginTask("Compiling "+fileToCompile.getName(), 10); //$NON-NLS-1$
						File projectFolder = fileToCompile.getParent().getLocation().toFile();									
						
						Process process = Runtime.getRuntime().exec(command, null, projectFolder);
						outputStream.println(Messages.ConsoleExecuter_startCompilation);
						String fullCommand = ""; //$NON-NLS-1$
						for(String str : command){
							fullCommand+=str+" "; //$NON-NLS-1$
						}
						outputStream.println(fullCommand);
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
						outputStream.println(Messages.ConsoleExecuter_endCompilation);
						fileToCompile.getParent().refreshLocal(IProject.DEPTH_ONE, new NullProgressMonitor());
						monitor.done();
					} catch(Exception ex){
						ex.printStackTrace();
						outputStream.println(ex.getMessage());
					}
				} else {
					outputStream.println(Messages.ConsoleExecuter_fileNotFound);
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
	 * Read name of the generated .jasper file
	 * 
	 * @param fileToCompile the jrxml file that will be compiled, must be not null
	 * @return the name of the jrxml file but with extension .jasper
	 */
	private String getOutputFilename(IFile fileToCompile){
		String fileName = fileToCompile.getName();
		if (fileName.endsWith(".jrxml")) return fileName.substring(0, fileName.length()-6).concat(".jasper"); //$NON-NLS-1$ //$NON-NLS-2$
		else return fileName.concat(".jasper"); //$NON-NLS-1$
	}
	
	/**
	 * Return the command to invoke the jvm. Now it is 
	 * static but can be overridden to return a specific version
	 * of the JVM using it's absolute path
	 * 
	 * @return the command to call the jvm
	 */
	protected String getJavaCommand(){
		return "java"; //$NON-NLS-1$
	}
	
	/**
	 * Return the project of the file to compile
	 * 
	 * @param fileToCompile the jrxml that will be compiled, must be not null
	 * @return the project where the jrxml is contained
	 */
	private IProject getProject(IFile fileToCompile){
		return fileToCompile.getProject();
	}
	
	/**
	 * Get all the resolved classpath entries for a specific project. The entries 
	 * with ID JRClasspathContainer.ID and JavaRuntime.JRE_CONTAINER are not resolved
	 * or included in the result. At also add the source and output folder provided with the 
	 * project
	 * 
	 * @param project the project where the file to compile is contained, must be not null
	 * @return a not null list of string that contains the classpath to include in the compilation project
	 */
	private List<String> getClasspaths(IProject project) {
		IJavaProject jprj = JavaCore.create(project);
		List<String> classpath = new ArrayList<String>();
		IWorkspaceRoot wsRoot = project.getWorkspace().getRoot();
		if (jprj != null) {
			try{
				IClasspathEntry[] entries = jprj.getRawClasspath();
				
				//Add the default output folder if any
				IPath defaultLocationPath = jprj.getOutputLocation();
  			if(defaultLocationPath!=null) {
    			IFolder entryOutputFolder = wsRoot.getFolder(defaultLocationPath);
    			classpath.add(entryOutputFolder.getLocation().toOSString() + File.separator);
  			}
				
				for (IClasspathEntry en : entries) {
					if (en.getEntryKind() == IClasspathEntry.CPE_CONTAINER){
						String containerPath = en.getPath().toString();
						//Don't add the eclipse runtime and the classpath extension defined in the exclusion list
						if (!containerPath.startsWith(JavaRuntime.JRE_CONTAINER) && !classpathExclusionSet.contains(containerPath)){
							addEntries(JavaCore.getClasspathContainer(en.getPath(), jprj).getClasspathEntries(), classpath, jprj);
						}
					}	else if (en.getEntryKind() == IClasspathEntry.CPE_PROJECT){
						classpath.add(wsRoot.findMember(en.getPath()).getLocation().toOSString() + File.separator); 
					} else if(en.getEntryKind() == IClasspathEntry.CPE_SOURCE && en.getContentKind() == IPackageFragmentRoot.K_SOURCE){
							//check if is a source folder and if it has a custom output folder to add them also to the classpath
	      			IPath entryOutputLocation = en.getOutputLocation();
	      			if(entryOutputLocation!=null) {
	        			IFolder entryOutputFolder = wsRoot.getFolder(entryOutputLocation);
	        			classpath.add(entryOutputFolder.getLocation().toOSString() + File.separator);
	      			}
					} else {
						//It is a jar check if it is internal to the workspace of external
						IPath location = wsRoot.getFile(en.getPath()).getLocation();
						if (location == null){
							//The location could not be resolved from the root of the workspace, it is external
							classpath.add(en.getPath().toOSString());
						} else {
							//The location has been resolved from the root of the workspace, it is internal
							classpath.add(location.toOSString());
						}
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return classpath;
	}
	
	/**
	 * Add an array of classpath entry to the result set. If an entry is a classpath container 
	 * then it is resolved and the resulting entries are added recursively
	 * 
	 * @param entries the entries to add
	 * @param classpath the current result
	 * @param jprj the current java project
	 */
	private void addEntries(IClasspathEntry[] entries, List<String> classpath, IJavaProject jprj){
		IWorkspace workspace = ResourcesPlugin.getWorkspace(); 
		for(IClasspathEntry en : entries){
			if (en.getEntryKind() == IClasspathEntry.CPE_CONTAINER){
				try{
					addEntries(JavaCore.getClasspathContainer(en.getPath(), jprj).getClasspathEntries(), classpath, jprj);
				} catch(Exception ex){
					ex.printStackTrace();
				}
			} else if (en.getEntryKind() == IClasspathEntry.CPE_PROJECT || en.getEntryKind() == IClasspathEntry.CPE_SOURCE){
				classpath.add(workspace.getRoot().findMember(en.getPath()).getLocation().toOSString()+File.separator+"*"); //$NON-NLS-1$
			} else {	
				classpath.add(en.getPath().toOSString());
			}
		}
	}
	
	/**
	 * Return the full classpath to use in the compilation command. 
	 * It include the folder where the compiled file is located, the folder
	 * where the JR used the for the compilation is located plug almost all
	 * the classpath of the project where the compiled file is located
	 * 
	 * @param fileToCompile the file to compile
	 * @return a not null classpath parameter
	 */
	private String getClasspath(IFile fileToCompile){
		//classpath entry for the jr compiler
		String classPath = jrFolder + File.separator + "*"; //$NON-NLS-1$
		IProject project = getProject(fileToCompile);
		if (project != null){
			String separator = ";"; //$NON-NLS-1$
			if (Util.isMac()) separator = ":"; //$NON-NLS-1$
			//Add the project folder to the workspace, actually not used
			/*IWorkspace workspace = ResourcesPlugin.getWorkspace();  
			File projectFile = workspace.getRoot().findMember(project.getFullPath()).getLocation().toFile();
			if (projectFile.exists()){
				classPath+=separator+projectFile.getAbsolutePath() + File.separator + "*"; 
			}*/
			for(String entry : getClasspaths(project)){
				classPath+= separator + entry;
			}
		}
		return classPath;
	}
	
	/**
	 * Create the class and start the compilation process, showing 
	 * the output into a console view
	 * 
	 * @param fileToCompile the jrxml that will be compiled
	 * @param jrFolder folder where the JasperReport used for the compilation
	 * is located. The folder must contains also the JRToolKit.jar file. This jar
	 * is placed automatically when the backward wizard is used to download an older
	 * version of JR,
	 */
	public ConsoleExecuter(IFile fileToCompile, String jrFolder){
		this.fileToCompile = fileToCompile;		
		this.jrFolder = jrFolder;
		List<String> commandSegments = new ArrayList<String>();
		commandSegments.add(getJavaCommand());
		commandSegments.add("-cp"); //$NON-NLS-1$
		commandSegments.add(getClasspath(fileToCompile));

		commandSegments.add("com.jaspersoft.jasperreports.toolkit.JRCompiler"); //$NON-NLS-1$
		commandSegments.add(fileToCompile.getLocation().toOSString());
		
		String destinationPath = JRBackwardManager.INSTANCE.getDestinationFolder();
		//Check the destination and compute the destination file name
		if (destinationPath != null){
			//Make sure that the destination is valid
			File destination = new File(destinationPath);
			destination.mkdirs();
	  	if (!destinationPath.endsWith(File.separator)) destinationPath+=File.separator;
	  	String fileName = getOutputFilename(fileToCompile);
	  	destinationPath+=fileName;
	  	File checkExistence = new File(destinationPath);
	  	if (checkExistence.exists()) checkExistence.delete();
	  	commandSegments.add(destinationPath);
		} else {
			//Remove the old file, if the name is found inside the build.js and if it is present
			String filename = getOutputFilename(fileToCompile);
			if (filename != null){
				try{
					for(IResource res : fileToCompile.getParent().members()){
						if (res.getName().equals(filename)){
							res.delete(true, new NullProgressMonitor());
							fileToCompile.getParent().refreshLocal(IProject.DEPTH_ONE, new NullProgressMonitor());
						}
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		
		command = commandSegments.toArray(new String[commandSegments.size()]);
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
}
