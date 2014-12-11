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
package com.jaspersoft.studio.components.customvisualization.creation.wizard;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.wizard.project.ProjectUtil;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.customvisualization.creation.CustomComponentNature;
import com.jaspersoft.studio.components.customvisualization.creation.ModuleDefinition;
import com.jaspersoft.studio.components.customvisualization.creation.ModuleManager;
import com.jaspersoft.studio.components.customvisualization.creation.VelocityLibrary;
import com.jaspersoft.studio.components.customvisualization.creation.VelocityShimLibrary;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.utils.VelocityUtils;
import com.jaspersoft.studio.wizards.JSSWizard;

/**
 * Wizard to create a custom visualization component project
 * 
 * @author Orlandin Marco
 *
 */
public class CustomVisualizationComponentWizard extends JSSWizard implements INewWizard {
	
	/**
	 * Key to write or read the selected module in the first step from the wizard settings
	 */
	protected static final String SELECTED_MODULE_KEY = "selectedModule";
	
	/**
	 * Engine to fill the build.js template
	 */
	private VelocityEngine ve = VelocityUtils.getConfiguredVelocityEngine();
	
	/**
	 * Path of the build.js template
	 */
	private static final String BUILD_FILE = "com/jaspersoft/studio/components/customvisualization/creation/resources/build.vm"; //$NON-NLS-1$
	
	/**
	 * Page to select the javascript module used by the project
	 */
	private CustomVisualizationComponentTablePage page0;
	
	/**
	 * Page to get a summary of all the libraries used by the project
	 */
	private CustomVisualizationComponentSummaryPage page1;
	
	/**
	 * Page to review the licenses of all the libraries used by the project
	 */
	private CustomVisualizationComponentLicensePage page2;
	
	
	public CustomVisualizationComponentWizard(){
		super();
		setWindowTitle(Messages.CustomVisualizationComponentWizard_title);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		page0 = new CustomVisualizationComponentTablePage();
		addPage(page0);
		page1 = new CustomVisualizationComponentSummaryPage();
		addPage(page1);
		page2 = new CustomVisualizationComponentLicensePage();
		addPage(page2);
	}

	/**
	 * Create the project inside the workspace and all it's content
	 */
	@Override
	public boolean performFinish() {
		IProgressMonitor monitor = new NullProgressMonitor();
		String projectName = page0.getProjectName();
		ModuleDefinition selected = page0.getSelectedModule();
		boolean result = createProject(projectName, monitor);
		if (result){
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject project = root.getProject(projectName);
			File dest = new File(root.getRawLocation().toFile(), projectName);
			List<VelocityLibrary> libraries = new ArrayList<VelocityLibrary>();
			List<VelocityShimLibrary> shimLibraries = new ArrayList<VelocityShimLibrary>();
			
			try{
				//Add the main module and all it's dependencies
				addModule(selected, shimLibraries, libraries, dest);
				for(ModuleDefinition requiredLibrary : selected.getRequiredLibraries()){
					addModule(requiredLibrary, shimLibraries, libraries, dest);
				}
		
				generateCSS(project, monitor, selected);
				generateRender(project, monitor, selected);
				libraries.add(new VelocityLibrary(projectName, projectName));
				generateBuildFile(project, monitor, libraries, shimLibraries, projectName);
				try {
					project.refreshLocal(IProject.DEPTH_INFINITE, new NullProgressMonitor());
				} catch (CoreException e) {
					e.printStackTrace();
				}
			} catch(FileNotFoundException ex){
				MessageDialog.openError(UIUtils.getShell(), Messages.CustomVisualizationComponentWizard_errorTitle, ex.getMessage());
				return false;
			}
		}
		return result;
	}			
	
	/**
	 * Add a module to the project, it's library is added on the project folder and
	 * are created the informations to have it added to the build.js file
	 * 
	 * @param module the module to add
	 * @param shimmedList the list of library to added to the shim list
	 * @param librariesList the list of library to add to the path list
	 * @param projectFolder the project folder
	 * @throws FileNotFoundException throw the exception if the library file can not be found
	 */
	private void addModule(ModuleDefinition module, List<VelocityShimLibrary> shimmedList, List<VelocityLibrary> librariesList, File projectFolder) throws FileNotFoundException{
		File resourceFile = ModuleManager.getLibraryFile(module);
		if (resourceFile != null && resourceFile.exists()){
			String fileName = module.getFilename();
			File workspaceCopy = new File(projectFolder, fileName);
			try {
				FileUtils.copyFile(resourceFile, workspaceCopy);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//ADD THE LIBRARY TO THE LIBRARIES LIST
			librariesList.add(new VelocityLibrary(module.getVariableName(), fileName.substring(0,fileName.length()-3)));
			//CHECK IF THE MODULE MUST BE SHIMMED
			if (module.isNeedShim()){
				String dependencies = ""; //$NON-NLS-1$
				for(int i=0; i< module.getShimDependencies().size(); i++){
					dependencies+= "'"+ module.getShimDependencies().get(i) +"'"; //$NON-NLS-1$ //$NON-NLS-2$
					if (i < (module.getShimDependencies().size()-1)){
						dependencies+=","; //$NON-NLS-1$
					}
				}
				VelocityShimLibrary shimLibrary = new VelocityShimLibrary(module.getVariableName(), module.getShimExportName(), dependencies);
				shimmedList.add(shimLibrary);
			}
		}  else {
			String errorMessage = MessageFormat.format(Messages.CustomVisualizationComponentWizard_errorDescription, new Object[]{module.getLibraryURL()});
			throw new FileNotFoundException(errorMessage);
		}
	}
	
	/**
	 * Generate for the new custom visualization component project the css file
	 * 
	 * @param container the container where the fill will be placed
	 * @param monitor the monitor to execute the operation 
	 * @param library the module selected by the user in the wizard page
	 */ 
	private void generateCSS(IProject container, IProgressMonitor monitor, ModuleDefinition library){
		String cssContent = library.getCssResource();
		if (cssContent != null){
			createFile(container.getName()+".css", container, cssContent, monitor); //$NON-NLS-1$
		}
	}
	
	/**
	 * Generate for the new custom visualization component project the render file
	 * 
	 * @param container the container where the fill will be placed
	 * @param monitor the monitor to execute the operation 
	 * @param library the module selected by the user in the wizard page
	 */
	private void generateRender(IProject container, IProgressMonitor monitor, ModuleDefinition library){
		String renderContent = library.getRenderResource();
		if (renderContent != null){
			createFile(container.getName()+".js", container, renderContent, monitor); //$NON-NLS-1$
		}
	}
	
	/**
	 * Generate the build.js file using the template mixed with the 
	 * data provided during the wizard
	 * 
	 * @param container the container where the final build.js will be placed
	 * @param monitor monitor to execute the operation
	 * @param libraries the list of javascript libraries to include inside the build file
	 * @param shimLibraries the list of javascript shimmed libraries to include inside the build file
	 * @param modulename the name of the folder where the project is contained, that it is used as module name
	 */
	private void generateBuildFile(IProject container, IProgressMonitor monitor, List<VelocityLibrary> libraries, List<VelocityShimLibrary> shimLibraries, String modulename) {
		VelocityContext functionContext = new VelocityContext();
		functionContext.put("libraries", libraries); //$NON-NLS-1$
		functionContext.put("hasShim", shimLibraries.size()>0); //$NON-NLS-1$
		functionContext.put("shimlibraries", shimLibraries); //$NON-NLS-1$
		functionContext.put("modulename", modulename); //$NON-NLS-1$
		//functionContext.put("includes", includes); //$NON-NLS-1$
		
		Template functionTemplate = ve.getTemplate(BUILD_FILE);
		StringWriter fsw = new StringWriter();
		functionTemplate.merge(functionContext, fsw);

		createFile("build.js", container, fsw.toString(), monitor); //$NON-NLS-1$
	}
	
	/**
	 * Add a textual file to the project
	 * 
	 * @param name the name of the file
	 * @param container the container of the file
	 * @param content the textual content of the file
	 * @param progressMonitor a progress monitor
	 * @return the added file
	 */
	public static IFile createFile(String name, IContainer container, String content, IProgressMonitor progressMonitor) {
		final IFile file = container.getFile(new Path(name));
		try {
			final InputStream stream = new ByteArrayInputStream(content.getBytes(file.getCharset()));
			if (file.exists()) {
				file.setContents(stream, true, true, progressMonitor);
			}
			else {
				file.create(stream, true, progressMonitor);
			}
			stream.close();
		}
		catch (final Exception e) {
			JaspersoftStudioPlugin.getInstance().logError(e);
		}
		progressMonitor.worked(1);

		return file;
	}
	
	/**
	 * Create an empty project inside the workspace with the nature of a custom visualization
	 * component
	 * 
	 * @param projectName the name of the project
	 * @param monitor monitor to execute the operation
	 * @return true if the project was created correctly, false otherwise
	 */
	private boolean createProject(String projectName, IProgressMonitor monitor){
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		try {
			if (!project.exists()) {
				project.create(monitor);
				project.open(monitor);
	
				ProjectUtil.addNature(project, CustomComponentNature.NATURE_ID, monitor);
				
				//IFolder folder = project.getFolder(PreferenceConstants.getPreferenceStore().getString(PreferenceConstants.SRCBIN_BINNAME));
				//folder.create(IResource.FORCE | IResource.DERIVED, true, monitor);
				//folder.setDerived(true, monitor);

				project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				IProjectDescription description = project.getDescription();
				description.setName(Messages.CustomVisualizationComponentWizard_projectDescription);
				project.setDescription(description, monitor);
				return true;
			}
		} catch (CoreException e) {
			e.printStackTrace();
		} 
		return false;
	}
}
