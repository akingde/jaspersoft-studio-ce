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
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import com.ibm.icu.text.MessageFormat;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.customvisualization.CustomVisualizationActivator;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.preferences.util.PreferencesUtils;

/**
 * Compile a list of build.js and print the output of the compilation on a
 * console. The operation can be aborted since it is inside an eclipse job
 * 
 * @author Orlandin Marco
 *
 */
public class ConsoleExecuter {

	/**
	 * The minimum java version required for the compiling
	 */
	private static final String MINIMUM_JAVA_VERSION = "1.7";//$NON-NLS-1$

	/**
	 * name of the build file
	 */
	public static final String BUILD_FILE_NAME = "build.js"; //$NON-NLS-1$

	/**
	 * Name of the output console
	 */
	private static final String CONSOLE_NAME = "Custom Visualization Component Console"; //$NON-NLS-1$

	/**
	 * Not null list of the file to compile
	 */
	private List<IFile> filesToCompile;

	/**
	 * Command to compile the build.js in the current working directory. The
	 * command is the same for each file and so it is initialized in the
	 * constructor and it is reused for each compilation.
	 */
	private String[] command;

	private IWorkbenchPage getActivePage() {
		IWorkbench w = PlatformUI.getWorkbench();
		IWorkbenchWindow aww = w.getActiveWorkbenchWindow();
		if (aww == null && w.getWorkbenchWindowCount() > 0) {
			aww = w.getWorkbenchWindows()[0];
			return aww.getActivePage();
		}
		return null;
	}

	/**
	 * Job to execute the compile command for each file and print the output
	 * inside a console. The job can be cancelled but the current compilation
	 * must be finished before it stops
	 */
	private Job readOutputJob = new Job("Compiling job") { //$NON-NLS-1$

		private boolean checkJavaVersion(MessageConsoleStream outputStream, IProgressMonitor monitor) {
			String javaCommand = getJavaCommand();
			String[] checkVersionCommand = {javaCommand, "-version"}; //$NON-NLS-1$
			try {
				Process process = Runtime.getRuntime().exec(checkVersionCommand);
				outputStream.println(Messages.ConsoleExecuter_checkVersionStart);
				outputStream.println(javaCommand + " -version");

				// BufferedReader stdInput = new BufferedReader(new
				// InputStreamReader(process.getInputStream()));
				BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

				// read the output from the command
				String s = null;
				try {
					while ((s = stdError.readLine()) != null) {
						String line = s.toLowerCase().trim();
						if (line.startsWith("java version")) { //$NON-NLS-1$
							int start = line.indexOf("\"") + 1; //$NON-NLS-1$
							int end = line.lastIndexOf("\""); //$NON-NLS-1$
							// Convert the last number into a version number
							String version = line.substring(start, end).replaceAll("_", ".");
							if (versionCompare(version, MINIMUM_JAVA_VERSION) >= 0) {
								outputStream.println(Messages.ConsoleExecuter_checkSuccess);
								return true;
							} else {
								outputStream.println(MessageFormat.format(Messages.ConsoleExecuter_checkWrongVersion1,
										new Object[] { version }));
								outputStream.println(Messages.ConsoleExecuter_checkWrongVersion2);
								outputStream.println(MessageFormat.format(Messages.ConsoleExecuter_checkWrongVersion3,
										new Object[] { CustomVisualizationActivator.JAVA_PATH_PROPERTY }));
							}
						}
					}
				} catch (Exception ex) {
					outputStream.println(ex.getMessage());
				}
			} catch (IOException e) {
				outputStream.println(MessageFormat.format(Messages.ConsoleExecuter_checkBadCommand1,
						new Object[] { javaCommand + " -version" }));
				String aaa = Messages.ConsoleExecuter_checkBadCommand2;
				outputStream.println(
						MessageFormat.format(aaa, new Object[] { CustomVisualizationActivator.JAVA_PATH_PROPERTY }));
				e.printStackTrace();
			}
			return false;
		}

		/**
		 * Pad the array passed as parameter to the length specified as second
		 * parameter. The padding elements have the value zero
		 * 
		 * @param currentNumber
		 *            a not null array with size lesser then newSize
		 * @param newSize
		 *            the new size of the array
		 * @return a not null array with size new size
		 */
		private String[] versionPadding(String[] currentNumber, int newSize) {
			Assert.isTrue(currentNumber.length < newSize);
			String[] equalLenghtVals2 = new String[newSize];
			for (int i = 0; i < newSize; i++) {
				if (i < currentNumber.length) {
					equalLenghtVals2[i] = currentNumber[i];
				} else {
					equalLenghtVals2[i] = "0";
				}
			}
			return equalLenghtVals2;
		}

		/**
		 * Compares two version strings.
		 * 
		 * Use this instead of String.compareTo() for a non-lexicographical
		 * comparison that works for version strings. e.g.
		 * "1.10".compareTo("1.6"). The number of components of the two version
		 * is uniformed to have an one to one comparison (this allow to
		 * recognize that 10.0 and 10.0.0 are the same version)
		 *
		 * 
		 * @param str1
		 *            a string of ordinal numbers separated by decimal points.
		 * @param str2
		 *            a string of ordinal numbers separated by decimal points.
		 * @return The result is a negative integer if str1 is _numerically_
		 *         less than str2. The result is a positive integer if str1 is
		 *         _numerically_ greater than str2. The result is zero if the
		 *         strings are _numerically_ equal.
		 */
		public Integer versionCompare(String str1, String str2) {
			String[] vals1 = str1.split("\\.");
			String[] vals2 = str2.split("\\.");

			// Make sure that the two string have the same number of components
			if (vals2.length < vals1.length) {
				vals2 = versionPadding(vals2, vals1.length);
			} else if (vals2.length > vals1.length) {
				vals1 = versionPadding(vals1, vals2.length);
			}

			int i = 0;
			// set index to first non-equal ordinal or length of shortest
			// version string
			while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
				i++;
			}
			// compare first non-equal ordinal number
			if (i < vals1.length && i < vals2.length) {
				int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
				return Integer.signum(diff);
			}
			// the strings are equal or one string is a substring of the other
			// e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
			else {
				return Integer.signum(vals1.length - vals2.length);
			}
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			// Create the console
			MessageConsole console = getCleanConsole();
			MessageConsoleStream outputStream = console.newMessageStream();
			for (IFile fileToCompile : filesToCompile) {
				if (monitor.isCanceled())
					return Status.CANCEL_STATUS;
				if (fileToCompile.exists()) {
					try {
						monitor.beginTask("Compiling " + fileToCompile.getName(), 10); //$NON-NLS-1$

						if (checkJavaVersion(outputStream, monitor)) {

							File projectFolder = fileToCompile.getParent().getLocation().toFile();

							// Remove the old file, if the name is found inside
							// the build.js and if it is present
							String filename = getOutputFilename(projectFolder);
							if (filename != null) {
								File oldFile = new File(projectFolder, filename);
								if (oldFile.exists() && oldFile.delete()) {
									fileToCompile.getParent().refreshLocal(IProject.DEPTH_ONE,
											new NullProgressMonitor());
								}
							}

							Process process = Runtime.getRuntime().exec(command, null, projectFolder);
							outputStream.println(Messages.CompileDialog_startCompilation);
							BufferedReader stdInput = new BufferedReader(
									new InputStreamReader(process.getInputStream()));
							BufferedReader stdError = new BufferedReader(
									new InputStreamReader(process.getErrorStream()));

							// read the output from the command
							String s = null;
							int work = 0;
							try {
								while ((s = stdInput.readLine()) != null) {
									outputStream.println(s);
									work++;
									monitor.worked(work);
								}
							} catch (Exception ex) {
								outputStream.println(ex.getMessage());
							}

							// read any errors from the attempted command
							try {
								while ((s = stdError.readLine()) != null) {
									outputStream.println(s);
									work++;
									monitor.worked(work);
								}
							} catch (Exception ex) {
								outputStream.println(ex.getMessage());
							}
							outputStream.println(Messages.CompileDialog_endCompilation);
							fileToCompile.getParent().refreshLocal(IProject.DEPTH_ONE, new NullProgressMonitor());
						}
						monitor.done();
					} catch (Exception ex) {
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
	 * @param projectFolder
	 *            the folder where the build.js is contained
	 * @return the output filename if it can be found inside the build.js, if
	 *         something goes wrong return null
	 */
	private String getOutputFilename(File projectFolder) {
		BufferedReader br = null;
		String result = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(new File(projectFolder, BUILD_FILE_NAME)));
			while ((sCurrentLine = br.readLine()) != null && result == null) {
				String trimmed = sCurrentLine.trim();
				if (trimmed.toLowerCase().startsWith("out:")) { //$NON-NLS-1$
					String fileName = trimmed.substring(4);
					int firstQuote = fileName.indexOf("\""); //$NON-NLS-1$
					if (firstQuote == -1)
						break;
					int secondQuote = fileName.indexOf("\"", firstQuote + 1); //$NON-NLS-1$
					if (secondQuote == -1)
						break;
					result = fileName.substring(firstQuote + 1, secondQuote);
				}
			}
		} catch (Exception ex) {
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

	/**
	 * Return the string to execute a Java command. First the path is looked in
	 * the property
	 * com.jaspersoft.studio.components.customvisualization.javapath, if found
	 * it is used. If not and the current version is an RCP it is used the java
	 * version embedded with studio and used to run it. Otherwise will be
	 * returned the standard Java command that relay on the environment
	 * variables.
	 * 
	 * 
	 * @return a not null string to call the JVM
	 */
	private String getJavaCommand() {
		String javaPath = PreferencesUtils.getJasperReportsProperty(CustomVisualizationActivator.JAVA_PATH_PROPERTY);
		if (javaPath != null && !javaPath.trim().isEmpty()) {
			if (!javaPath.endsWith(File.pathSeparator))
				javaPath += File.separator;
			return javaPath + "java"; //$NON-NLS-1$
		} else {
			boolean isRCP = JaspersoftStudioPlugin.isRCP();
			if (isRCP) {
				javaPath = System.getProperties().getProperty("java.home");
				if (javaPath != null && !javaPath.trim().isEmpty()) {
					javaPath += File.separator + "bin" + File.separator + "java";//$NON-NLS-1$ //$NON-NLS-2$
					return javaPath;
				}
			}
		}
		return "java";//$NON-NLS-1$
	}

	/**
	 * Create the class and start the compilation process, showing the output
	 * into a console view
	 * 
	 * @param filesToCompile
	 *            the build.js files that need to be compiled
	 */
	public ConsoleExecuter(List<IFile> filesToCompile) {
		this.filesToCompile = filesToCompile;
		// Export the resources necessary for the compilation outside the JSS
		// plugin
		File compiler = fetchResource(
				"com/jaspersoft/studio/components/customvisualization/creation/resources/compiler.jar", "compiler.jar"); //$NON-NLS-1$ //$NON-NLS-2$
		File rhino = fetchResource("com/jaspersoft/studio/components/customvisualization/creation/resources/js.jar", //$NON-NLS-1$
				"js.jar"); //$NON-NLS-1$
		File rJs = fetchResource("com/jaspersoft/studio/components/customvisualization/creation/resources/r.js", //$NON-NLS-1$
				"r.js"); //$NON-NLS-1$

		command = new String[] { getJavaCommand(), "-classpath", //$NON-NLS-1$
				rhino.toString() + File.pathSeparator + compiler.toString(), "org.mozilla.javascript.tools.shell.Main", //$NON-NLS-1$
				rJs.toString(), "-o", //$NON-NLS-1$
				BUILD_FILE_NAME };

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
					IWorkbenchPage activePage = getActivePage();
					if (activePage != null) {
						IConsoleView view = (IConsoleView) activePage.showView(id);
						view.display(myConsole);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		myConsole.clearConsole();
		return myConsole;
	}

	/**
	 * Search an output console with a specific name. If the console can't be
	 * found a new one is created.
	 * 
	 * @param name
	 *            name of the console
	 * @return A not null console with a specific name
	 */
	public static MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		// no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}

	/**
	 * Read a resource from the current plugin and save it with a specific name
	 * inside the temp folder. Then return a file reference to the new created
	 * file. If a file was already define inside the temp folder with the same
	 * name, then it is returned without recreate it
	 * 
	 * @param path
	 *            the path of the resource inside the plugin
	 * @param fileName
	 *            the name of the file that will be created on the temp folder
	 * @return a file reference to the resource or null if it can't be found
	 */
	private File fetchResource(String path, String fileName) {
		try {
			URL url = getClass().getClassLoader().getResource(path);
			InputStream is = url.openStream();
			String tempDir = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
			File tempFile = new File(tempDir, fileName);
			if (!tempFile.exists()) {
				FileOutputStream outputStream = new FileOutputStream(tempFile);
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				outputStream.close();
			}
			return tempFile;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * REMOVED, USING array of arguments to exec instead. For some reason this
	 * methos was failing on OSX.
	 * 
	 * Generate the command to create the custom visualization component.
	 * 
	 * @param compiler
	 *            path to the compiler jar
	 * @param rhino
	 *            path to the rhino jar
	 * @param rJs
	 *            path to the r.js script
	 * @param buildFile
	 *            path to the build.js script
	 * @return a command to executed inside the runtime
	 * 
	 *         private String generateCommand(File compiler, File rhino, File
	 *         rJs, File buildFile){
	 * 
	 *         StringBuilder command = new StringBuilder();
	 * 
	 *         command.append("java -classpath "); //$NON-NLS-1$
	 *         command.append("\"").append(rhino.getAbsolutePath());
	 *         //$NON-NLS-1$ command.append(File.pathSeparator);
	 *         command.append(compiler.getAbsolutePath()); //$NON-NLS-1$
	 *         command.append("\""); command.append(" "); //$NON-NLS-1$
	 *         command.append("org.mozilla.javascript.tools.shell.Main");
	 *         //$NON-NLS-1$ command.append(" "); //$NON-NLS-1$
	 *         command.append("\"").append(rJs.getAbsolutePath()).append("\"");
	 *         //$NON-NLS-1$ command.append(" -o "); //$NON-NLS-1$
	 *         command.append(" "); //$NON-NLS-1$
	 *         command.append("\"").append(buildFile.getAbsolutePath()).append(
	 *         "\""); //$NON-NLS-1$
	 * 
	 *         return command.toString(); }
	 * 
	 */
}
