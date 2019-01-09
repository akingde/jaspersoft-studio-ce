/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.svgimporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.svgimporter.InkscapeMessageDialog;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.PreferencesUtils;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Pair;

/**
 * Attemp to use Inkscape by command line to convert a PDF into an SVG format.
 * If the inkscape executable is not found into a default location it will show a dialog
 * with information
 * 
 * @author Orlandin Marco
 *
 */
public class ConsolePdfConverter {

	/**
	 * Name of the output console
	 */
	private static final String CONSOLE_NAME = "PDF Converter Console"; //$NON-NLS-1$
	
	/**
	 * Property ID to set an inkscape path manually in the preferences
	 */
	public static final String INKSCAPE_PATH_PROPERTY = "com.jaspersoft.studio.imports.pdf.inkscapepath"; //$NON-NLS-1$

	/**
	 * Not null list of the PDF files to convert
	 */
	private List<File> filesToConvert;

	/**
	 * The result list of the files converted into SVG
	 */
	private List<File> convertedSVG = new ArrayList<>();
	
	/**
	 * Create the class and start the conversion process, showing the output
	 * into a console view
	 * 
	 * @param filesToCompile
	 *            the PDF files that need to be converted
	 */
	public ConsolePdfConverter(List<File> filesToConvert) {
		this.filesToConvert = filesToConvert;
		doConversion(new NullProgressMonitor());
	}

	/**
	 * Job to execute the converted command for each file and print the output
	 * inside a console. The job can be cancelled but the current conversion
	 * must be finished before it stops. Actually this is not used
	 * 
	 * @param jobChangeListener listener used to notify the advance of the job
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
	
	/**
	 * Check if a string is an inkscape version number. An inkscape
	 * version number are numbers separated by the dot charachter.
	 * 
	 * @param value the strign to check, must be not null
	 * @return true if the string is an inkscape version number, false otherwise
	 */
	private boolean isVersion(String value) {
		String[] numbers = value.split("\\."); //$NON-NLS-1$
		for(String number : numbers) {
			if (!StringUtils.isNumeric(number)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if inkscape is present on the system looking for the command for the current system
	 * and trying to call it with his version
	 * 
	 * @param outputStream not null stream where the output of the console command is printed
	 * @return true if inkscape was found, false otherwise
	 */
	private boolean checkInkspace(MessageConsoleStream outputStream) {
		Pair<String,File> command = getInkscapeCommand();

		String[] checkVersionCommand = {command.getKey(), "-V"}; //$NON-NLS-1$
		outputStream.println(MessageFormat.format(Messages.ConsolePdfConverter_consoleChecking, new Object[] {command.getKey()}));
		BufferedReader stdInput = null;
		try {
			Process process = Runtime.getRuntime().exec(checkVersionCommand, null, null);
			stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			// read the output from the command
			String s = null;
			try {
				while ((s = stdInput.readLine()) != null) {
					int inkscapePosition = s.toLowerCase().indexOf("inkscape"); //$NON-NLS-1$
					if (inkscapePosition != -1) { //$NON-NLS-1$
						//check if it is followed by a version number
						s = s.substring(inkscapePosition + 9);
						String[] splitString = s.split(" "); //$NON-NLS-1$
						if (splitString.length > 0 && isVersion(splitString[0])) {
							return true;
						}
					}
				}
			} catch (Exception ex) {
				outputStream.println(ex.getMessage());
				JaspersoftStudioPlugin.getInstance().logError(ex);
				ex.printStackTrace();
			}
		} catch (IOException ex) {
			outputStream.println(MessageFormat.format(Messages.ConsolePdfConverter_consoleError,new Object[] {command.getKey() }));
			JaspersoftStudioPlugin.getInstance().logError(ex);
			ex.printStackTrace();
		} finally {
			FileUtils.closeStream(stdInput);
		}
		return false;
	}
	
	/**
	 * Do the conversion of the file using inkscape, but only if inkscape is found, otherwise it
	 * show an informative message dialog. The process is synchronous
	 * 
	 * @param monitor a monitor to interrupt the process
	 * @return return the result
	 */
	private IStatus doConversion(IProgressMonitor monitor) {
		// Create the console
		convertedSVG.clear();
		IStatus result = Status.OK_STATUS;
		MessageConsole console = getCleanConsole();
		MessageConsoleStream outputStream = console.newMessageStream();
		if (checkInkspace(outputStream)) {
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
						outputStream.println(MessageFormat.format(Messages.ConsolePdfConverter_consoleConversion,new Object[] {fileToCompile.getName()}));
						String absolutePath = fileToCompile.getAbsolutePath();
						String outName = getOutputFilename(fileToCompile);
						String[] command = new String[] {inkscapeCommand.getKey(), "-l", outName, absolutePath}; //$NON-NLS-1$
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
							outputStream.println(MessageFormat.format(Messages.ConsolePdfConverter_consoleDone,new Object[] {outFile.getAbsolutePath()}));
						} else {
							outputStream.println(Messages.ConsolePdfConverter_consoleFail);
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
	
	/**
	 * Return the command to run inkscape in console 
	 * 
	 * @return a pair where the first is the command to run inkscape in console, the file should
	 * be the execution folder, but in many case it is not used
	 */
	private Pair<String, File> getInkscapeCommand() {
		String inkscapePath = PreferencesUtils.getJasperReportsProperty(INKSCAPE_PATH_PROPERTY);
		if (inkscapePath != null && !inkscapePath.trim().isEmpty()) {
			if (!inkscapePath.endsWith(File.pathSeparator))
				inkscapePath += File.separator;
			if (Util.isMac()) {
				return new Pair<String,File>(inkscapePath + "inkscape", null); //$NON-NLS-1$
			} else {
				return new Pair<String,File>(inkscapePath + "inkscape", null); //$NON-NLS-1$
			}
		}
		Pair<String, File> command = new Pair<String, File>("inkscape", null); //$NON-NLS-1$
		if (Util.isWindows()) {
			String winCommand = getWindowsDir();
			if (winCommand != null) {
				File windowsInkscapeFile = new File(winCommand);
				command = new Pair<String, File>(windowsInkscapeFile.getAbsolutePath(), windowsInkscapeFile.getParentFile());
			}
		} else if (Util.isMac()) {
			command = new Pair<String, File>("/Applications/Inkscape.app/Contents/Resources/bin/inkscape", null); //$NON-NLS-1$
		}
		return command;
	}
	
	/**
	 * Look for inkscape in the default windows dir, if one of them is found the it is returned
	 * 
	 * @return the default executable position where inkscape is placed in windows or null if it can't be found
	 */
	private String getWindowsDir() {
		File windowsDefaultPath = new File("C:\\Program Files\\Inkscape\\inkscape.exe"); //$NON-NLS-1$
		if (!windowsDefaultPath.exists()) {
			windowsDefaultPath = new File("C:\\Program Files (x86)\\Inkscape\\inkscape.exe"); //$NON-NLS-1$
			if (!windowsDefaultPath.exists()) {
				return null;
			}
		}
		return FilenameUtils.removeExtension(windowsDefaultPath.getAbsolutePath());
	}

	/**
	 * Read the PDF file name to find the output file name and place it into the current system temp folder
	 * 
	 * @param targetFile the not null file to convert
	 * @return the path where the new SVG can be saved
	 */
	private String getOutputFilename(File targetFile) {
		String tempDir = System.getProperty("java.io.tmpdir"); //$NON-NLS-1$
		String fileNameWithOutExt = FilenameUtils.removeExtension(targetFile.getName());
		File tempFile = new File(tempDir, fileNameWithOutExt + ".svg"); //$NON-NLS-1$
		int counter = 1;
		while(tempFile.exists()) {
			tempFile = new File(tempDir, fileNameWithOutExt + counter + ".svg"); //$NON-NLS-1$
			counter++;
		}
		return tempFile.getAbsolutePath();
	}

	/**
	 * Return a clean console where the output of the compilation can be written
	 * 
	 * @return a not null console
	 */
	private MessageConsole getCleanConsole() {
		final MessageConsole myConsole = findConsole(CONSOLE_NAME);
		//force the opening of the console
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(IConsoleConstants.ID_CONSOLE_VIEW);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
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
	 * Return the list of the converted files
	 * 
	 * @return a not null list of file
	 */
	public List<File> getConvertedFiles(){
		return convertedSVG;
	}
}
