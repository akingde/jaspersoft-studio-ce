/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorLauncher;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.JRXMLUtils;

import net.sf.jasperreports.eclipse.builder.JSSReportContext;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;

/**
 * Editor launcher for .jasper files.<br>
 * It performs check before opening the correct editor (i.e. JRXML or Book Editor one).
 * We provide a conversion if needed or just in case open the original corresponding JRXML.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JasperFileEditorLauncher implements IEditorLauncher {

	@Override
	public void open(IPath file) {
		IFile jasperFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(file);
		if(jasperFile==null){
			try {
				jasperFile = FileUtils.getInProjectFile(file.toFile().toURI(), new NullProgressMonitor());
			} catch (Exception e) {
				UIUtils.showError(Messages.JasperFileEditorLauncher_ErrorExternalJasperFile,e);
			}
		}
    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IFile jrxmlFile = silentlyConvertJasperFile(jasperFile, new NullProgressMonitor());
		try {
			IDE.openEditor(page, jrxmlFile);
		} catch (PartInitException e) {
			UIUtils.showError(Messages.JasperFileEditorLauncher_ErrorWorkspaceJasperFile,e);
		}
	}

	/*
	 * Given a .jasper file it returns the corresponding the .jrxml or if 
	 * requested it creates/overwrites using the .jasper as input source.
	 */
	private IFile silentlyConvertJasperFile(IFile jasperFile, IProgressMonitor monitor) {
		JSSReportContext jrContext = JSSReportContext.getNewConfig(jasperFile);
		// Check for existing corresponding .jrxml file
		IPath tmpPath = jasperFile.getFullPath().removeFileExtension();
		tmpPath = tmpPath.addFileExtension(FileExtension.JRXML);
		IFile jrxmlFile = ResourcesPlugin.getWorkspace().getRoot().getFile(tmpPath);
		if(jrxmlFile.exists()){
			boolean overwriteAnswer = MessageDialog.openQuestion(
					UIUtils.getShell(), Messages.AbstractJRXMLEditor_ConversionTitle, 
					Messages.AbstractJRXMLEditor_ConversionMessage);
			if(overwriteAnswer){
				try {
					InputStream contents = jasperFile.getContents();
					InputStream jrxmlInStream = JRXMLUtils.getJRXMLInputStream(jrContext, contents, FileExtension.JASPER, FileUtils.UTF8_ENCODING, JRXmlWriterHelper.LAST_VERSION);
					jrxmlFile.setContents(jrxmlInStream, IFile.FORCE, monitor);
					jrxmlFile.getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
				} catch (CoreException e) {
					JaspersoftStudioPlugin.getInstance().logError(e);
				} catch (JRException e) {
					JaspersoftStudioPlugin.getInstance().logError(e);
				}
			}
		}
		else {
			// Open .jrxml file corresponding to the .jasper
			try {
				InputStream contents = jasperFile.getContents();
				InputStream jrxmlInStream = JRXMLUtils.getJRXMLInputStream(jrContext, contents, FileExtension.JASPER, FileUtils.UTF8_ENCODING, JRXmlWriterHelper.LAST_VERSION);
				jrxmlFile.create(jrxmlInStream, IFile.FORCE, monitor);
			} catch (CoreException e) {
				JaspersoftStudioPlugin.getInstance().logError(e);
			} catch (JRException e) {
				JaspersoftStudioPlugin.getInstance().logError(e);
			}
		}
		return jrxmlFile;
	}

}
