/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.imports.svg;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.Util;
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
import com.jaspersoft.studio.preferences.util.PreferencesUtils;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Pair;

/**
 * Compile a list of build.js and print the output of the compilation on a
 * console. The operation can be aborted since it is inside an eclipse job
 * 
 * @author Orlandin Marco
 *
 */
public class ConsolePdfConverter {

	/**
	 * Name of the output console
	 */
	private static final String CONSOLE_NAME = "PDF Converter Console"; //$NON-NLS-1$
	
	  public static final String INKSCAPE_PATH_PROPERTY = "com.jaspersoft.studio.imports.pdf.inkscapepath";

	/**
	 * Not null list of the file to compile
	 */
	private List<File> filesToConvert;

	private List<File> convertedSVG = new ArrayList<>();
	
	/**
	 * Create the class and start the compilation process, showing the output
	 * into a console view
	 * 
	 * @param filesToCompile
	 *            the build.js files that need to be compiled
	 */
	public ConsolePdfConverter(List<File> filesToConvert) {
		this.filesToConvert = filesToConvert;
		doConversion(new NullProgressMonitor());
	}

	/**
	 * Job to execute the compile command for each file and print the output
	 * inside a console. The job can be cancelled but the current compilation
	 * must be finished before it stops
	 */
	protected void asyncConvert(IJobChangeListener jobChangeListener) {
		Job readOutputJob = new Job("Compiling job") { //$NON-NLS-1$

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				return doConversion(monitor);
			}
		};
		readOutputJob.setPriority(Job.SHORT);
		readOutputJob.addJobChangeListener(jobChangeListener);
		readOutputJob.schedule(); // start as soon as possible*/
	}
	
	private IWorkbenchPage getActivePage() {
		IWorkbench w = PlatformUI.getWorkbench();
		IWorkbenchWindow aww = w.getActiveWorkbenchWindow();
		if (aww == null && w.getWorkbenchWindowCount() > 0) {
			aww = w.getWorkbenchWindows()[0];
			return aww.getActivePage();
		}
		return null;
	}
	
	private boolean isVersion(String value) {
		String[] numbers = value.split("\\.");
		for(String number : numbers) {
			if (!StringUtils.isNumeric(number)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkInkspace(MessageConsoleStream outputStream, IProgressMonitor monitor) {
		Pair<String,File> command = getInkscapeCommand();

		String[] checkVersionCommand = {command.getKey(), "-V"};
		outputStream.println("Checking for inkscape with command " + command.getKey());
		BufferedReader stdInput = null;
		try {
			Process process = Runtime.getRuntime().exec(checkVersionCommand, null, null);
			stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			// read the output from the command
			String s = null;
			int work = 0;
			try {
				while ((s = stdInput.readLine()) != null) {
					int inkscapePosition = s.toLowerCase().indexOf("inkscape");
					if (inkscapePosition != -1) { //$NON-NLS-1$
						//check if it is followed by a version number
						s = s.substring(inkscapePosition + 9);
						String[] splitString = s.split(" ");
						if (splitString.length > 0 && isVersion(splitString[0])) {
							return true;
						}
					}
					work++;
					monitor.worked(work);
				}
			} catch (Exception ex) {
				outputStream.println(ex.getMessage());
				JaspersoftStudioPlugin.getInstance().logError(ex);
				ex.printStackTrace();
			}
		} catch (IOException ex) {
			outputStream.println(MessageFormat.format("Unable to execute the command {0}",new Object[] {command.getKey() }));
			JaspersoftStudioPlugin.getInstance().logError(ex);
			ex.printStackTrace();
		} finally {
			FileUtils.closeStream(stdInput);
		}
		return false;
	}
	
	private IStatus doConversion(IProgressMonitor monitor) {
		// Create the console
		convertedSVG.clear();
		IStatus result = Status.OK_STATUS;
		MessageConsole console = getCleanConsole();
		MessageConsoleStream outputStream = console.newMessageStream();
		if (checkInkspace(outputStream, monitor)) {
			Pair<String, File> inkscapeCommand = getInkscapeCommand();
			for (File fileToCompile : filesToConvert) {
				if (monitor.isCanceled()) {
					result = Status.CANCEL_STATUS;
					break;
				} else if (fileToCompile.exists()) {
					BufferedReader stdInput = null;
					BufferedReader stdError = null;
					try {
						monitor.beginTask(MessageFormat.format("Converting {0}",new Object[] {fileToCompile.getName()}), 1); //$NON-NLS-1$
						outputStream.println(MessageFormat.format("Converting {0}",new Object[] {fileToCompile.getName()}));
						String absolutePath = fileToCompile.getAbsolutePath();
						String outName = getOutputFilename(fileToCompile);
						String[] command = new String[] {inkscapeCommand.getKey(), "-l", outName, absolutePath};
						Process process = Runtime.getRuntime().exec(command, null, null);
						stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
						stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

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
						File outFile = new File(outName);
						if (outFile.exists()) {
							convertedSVG.add(outFile);
							outputStream.println(MessageFormat.format("PDF converted and stored in {0}",new Object[] {outFile.getAbsolutePath()}));
						} else {
							outputStream.println("Unable to convert the file");
						}
						
						monitor.done();
					} catch (Exception ex) {
						ex.printStackTrace();
						JaspersoftStudioPlugin.getInstance().logError(ex);
						outputStream.println(ex.getMessage());
					} finally {
						FileUtils.closeStream(stdError);
						FileUtils.closeStream(stdInput);
					}
				}
			}
		} else {
			InkscapeMessageDialog showInformationDialog = new InkscapeMessageDialog(UIUtils.getShell());
			if (showInformationDialog.open() == MessageDialog.OK) {
				result = doConversion(monitor);
			} else {
				result = Status.CANCEL_STATUS;
			}
		}
		FileUtils.closeStream(outputStream);
		return result;
	}
	
	private Pair<String, File> getInkscapeCommand() {
		String inkscapePath = PreferencesUtils.getJasperReportsProperty(INKSCAPE_PATH_PROPERTY);
		if (inkscapePath != null && !inkscapePath.trim().isEmpty()) {
			if (!inkscapePath.endsWith(File.pathSeparator))
				inkscapePath += File.separator;
			if (Util.isMac()) {
				return new Pair<String,File>(inkscapePath + "inkscape-bin", null); //$NON-NLS-1$
			} else {
				return new Pair<String,File>(inkscapePath + "inkscape", null); //$NON-NLS-1$
			}
		}
		Pair<String, File> command = new Pair<String, File>("inkscape", null);
		if (Util.isWindows()) {
			String winCommand = getWindowsDir();
			if (winCommand != null) {
				File windowsInkscapeFile = new File(winCommand);
				command = new Pair<String, File>(windowsInkscapeFile.getAbsolutePath(), windowsInkscapeFile.getParentFile());
			}
		} else if (Util.isMac()) {
			command = new Pair<String, File>("/Applications/Inkscape.app/Contents/Resources/bin/inkscape-bin", null);
		}
		return command;
	}

	/**
	 * Read the build.js to find the output file name inside the out section
	 * 
	 * @param projectFolder
	 *            the folder where the build.js is contained
	 * @return the output filename if it can be found inside the build.js, if
	 *         something goes wrong return null
	 */
	private String getOutputFilename(File targetFile) {
		String tempDir = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
		String fileNameWithOutExt = FilenameUtils.removeExtension(targetFile.getName());
		File tempFile = new File(tempDir, fileNameWithOutExt + ".svg");
		int counter = 1;
		while(tempFile.exists()) {
			tempFile = new File(tempDir, fileNameWithOutExt + counter + ".svg");
			counter++;
		}
		return tempFile.getAbsolutePath();
	}
	
	private String getWindowsDir() {
		File windowsDefaultPath = new File("C:\\Program Files\\Inkscape\\inkscape.exe");
		if (!windowsDefaultPath.exists()) {
			windowsDefaultPath = new File("C:\\Program Files (x86)\\Inkscape\\inkscape.exe");
			if (!windowsDefaultPath.exists()) {
				return null;
			}
		}
		return FilenameUtils.removeExtension(windowsDefaultPath.getAbsolutePath());
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

	public List<File> getConvertedFiles(){
		return convertedSVG;
	}
}
