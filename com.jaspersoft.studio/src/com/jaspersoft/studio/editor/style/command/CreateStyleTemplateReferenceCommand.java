/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.style.command;

import java.io.File;

import net.sf.jasperreports.eclipse.messages.Messages;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRTemplateReference;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.model.style.MStyleTemplateReference;
import com.jaspersoft.studio.model.style.MStylesTemplate;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.FilteredHelpDialog;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateStyleTemplateReferenceCommand extends Command {

	private JRTemplateReference jrTemplate;

	private JRSimpleTemplate jrDesign;

	private int index;
	
	/**
	 * The configuration of the actual report
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * Instantiates a new creates the style template command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateStyleTemplateReferenceCommand(MStylesTemplate destNode, MStyleTemplateReference srcNode, int index) {
		super();
		this.jrDesign = (JRSimpleTemplate) destNode.getValue();
		this.jConfig = destNode.getJasperConfiguration();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrTemplate = (JRTemplateReference) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		createObject();
		if (jrTemplate != null) {
			if (index < 0 || index > jrDesign.getIncludedTemplatesList().size())
				jrDesign.addIncludedTemplate(jrTemplate);
			else
				jrDesign.addIncludedTemplate(index, jrTemplate);
		}
	}
	
	/**
	 * Create the container for the selected jrtx file, by selecting it from a chooser dialog.
	 * If the selected file is not valid an error is shown
	 */
	private void createObject() {
		if (jrTemplate == null) {
			FilteredResourcesSelectionDialog fd = new FilteredHelpDialog(Display.getCurrent().getActiveShell(),false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE, ContextHelpIDs.WIZARD_STYLE_TEMPLATE_LOAD);
			fd.setInitialPattern("*.jrtx");//$NON-NLS-1$
			if (fd.open() == Dialog.OK) {
				IFile file = (IFile) fd.getFirstResult();
				File  fileToBeOpened = file.getRawLocation().makeAbsolute().toFile();
				boolean showErrorMessage = false;
				//Check if the file is a valid template before add it to the model
				if (fileToBeOpened != null && fileToBeOpened.exists() && fileToBeOpened.isFile()) {
					try{
						//Try to load the file to see if it is a valid template
						JRXmlTemplateLoader.load(fileToBeOpened);
						this.jrTemplate = MStyleTemplateReference.createJRTemplate();
						jrTemplate.setLocation(getStylePath(file));
					} catch(Exception ex){
						showErrorMessage = true;
					}
				} else {
					showErrorMessage = true;
				}
				if (showErrorMessage){
					MessageDialog.open(MessageDialog.ERROR, Display.getCurrent().getActiveShell(), Messages.UIUtils_ExceptionTitle, Messages.CreateStyleTemplateCommand_loadStyleError, SWT.NONE);
				}
			}
		}
	}

	/**
	 * This method try to return a relative path for the style from the current opened report. If it isn't
	 * Possible to find a relative path then the absolute one is returned
	 * 
	 * @param styleFile the style file resource
	 * @return and absolute or relative path to the style resource
	 */
	private String getStylePath(IFile styleFile){
		IFile reportFile = (IFile) jConfig.get(FileUtils.KEY_FILE);
		if (reportFile != null){
			if (reportFile.getParent().equals(styleFile.getParent())) return styleFile.getName();
			else if (reportFile.getProject().equals(styleFile.getProject())) return styleFile.getProjectRelativePath().toPortableString();
		}
	 return styleFile.getRawLocation().makeAbsolute().toOSString();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrDesign.removeIncludedTemplate(jrTemplate);
	}
}
