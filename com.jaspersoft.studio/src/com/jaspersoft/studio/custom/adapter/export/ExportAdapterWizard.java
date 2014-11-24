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
package com.jaspersoft.studio.custom.adapter.export;

import java.io.File;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.pde.internal.core.bundle.BundlePluginModel;
import org.eclipse.pde.internal.ui.wizards.exports.BaseExportWizardPage;
import org.eclipse.pde.internal.ui.wizards.exports.PluginExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.internal.ide.actions.BuildUtilities;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.translation.JarFileUtils;

/**
 * Wizard to export the data adapter plugin as a jar. It uses 
 * a custom store for the setting and a custom page
 * 
 * @author Orlandin Marco
 *
 */
@SuppressWarnings("restriction")
public class ExportAdapterWizard extends PluginExportWizard {
	
	/**
	 * Page where the destination of the jar can be chosen
	 */
	private ExportAdapterWizardPage fPage;
	
	/**
	 * The settings store id
	 */
	private static final String STORE_SECTION = "AdapterExportWizard"; //$NON-NLS-1$
	
	public ExportAdapterWizard(IWorkbench workbench, IStructuredSelection selection){
		super();
		init(workbench, selection);
	}
	
	/**
	 * Create a custom page instead of the standard one
	 */
	protected BaseExportWizardPage createPage1() {
		fPage = new ExportAdapterWizardPage(getSelection());
		return fPage;
	}
	
	/**
	 * Return the settings store id
	 */
	protected String getSettingsSectionName() {
		return STORE_SECTION;
	}
	
	/**
	 * Generate the qualifier for a fragment using the eclipse style. 
	 * So a concatenation of the actuals year,month,day,hour and minute
	 * 
	 * @return an eclipse style qualifier
	 */
	public static String generateQualifier(){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm"); //$NON-NLS-1$
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/**
	 * Return the composed message of all the errors to show it on the final dialog
	 * 
	 * @param singleErrors a not null list of errors project by project
	 * @return a message to show to the user
	 */
	private String getErrorMessage(List<String> singleErrors){
		String message = Messages.ExportAdapterWizard_projectErrors;
		for(String error : singleErrors){
			message += error + "\n"; //$NON-NLS-1$
		}
		return message;
	}
	
	/**
	 * Return the composed message of all the successes to show it on the final dialog
	 * 
	 * @param singleErrors a not null list of successful operation project by project
	 * @return a message to show to the user
	 */
	private String getSuccessMessage(List<String> singleSuccesses){
		String message = Messages.ExportAdapterWizard_projectSucceses;
		for(String success : singleSuccesses){
			message += success + "\n"; //$NON-NLS-1$
		}
		return message;
	}
	
	/**
	 * Build a project and return if it has error or not
	 * 
	 * @param project project to compile
	 * @return true if the project has not compilation error, false otherwise
	 */
	private boolean buildProject(IProject project){
		//BuildAction buildAction = new BuildAction(UIUtils.getShellProvider(), IncrementalProjectBuilder.FULL_BUILD);
		//buildAction.selectionChanged(new StructuredSelection(project));
		//buildAction.run();
		//The following is essentially the body of a BuildAction but executed outsie a separated job
		try {
			ArrayList<IProject> projects = new ArrayList<IProject>();
			projects.add(project);
			BuildUtilities.saveEditors(projects);
			List<IBuildConfiguration> buildConfiguration = new ArrayList<IBuildConfiguration>();
			buildConfiguration.add(project.getActiveBuildConfig());
			IBuildConfiguration[] configs = buildConfiguration.toArray(new IBuildConfiguration[buildConfiguration.size()]);
			ResourcesPlugin.getWorkspace().build(configs, IncrementalProjectBuilder.FULL_BUILD, true, new NullProgressMonitor());
		} catch (CoreException e1) {
			e1.printStackTrace();
			return false;
		}
		try {
			IMarker[] markers = project.findMarkers(IJavaModelMarker.JAVA_MODEL_PROBLEM_MARKER, true, IResource.DEPTH_INFINITE);
			for (IMarker marker: markers)
			{
			    int severity = marker.getAttribute(IMarker.SEVERITY, Integer.MAX_VALUE);
			    if (severity == IMarker.SEVERITY_ERROR){
			       return false;
			    }
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Generate the jar for the selected projects. 
	 */
	@Override
	public boolean performFinish() {
		fPage.saveSettings(getDialogSettings());
		IWorkspace workspace = ResourcesPlugin.getWorkspace();  
		//get location of workspace (java.io.File)  
		File workspaceDirectory = workspace.getRoot().getLocation().toFile();
		List<String> successfullyExported = new ArrayList<String>();
		List<String> notExported = new ArrayList<String>();
	  for(Object item : fPage.getSelectedItems()){
  		if (item instanceof BundlePluginModel){
  			BundlePluginModel plugin = (BundlePluginModel)item;
  			IProject project = fPage.getProjectForModel(plugin);
  			if (project != null){
    			File sourceFile = new File(workspaceDirectory, project.getFullPath().toOSString());
    			File manifestFile = new File(sourceFile, "META-INF\\MANIFEST.MF"); //$NON-NLS-1$
    		  String manifestContent = JarFileUtils.deserializeString(manifestFile);
    		  
    		  String version = "_"+plugin.getBundleDescription().getVersion().toString().replace("qualifier", generateQualifier()); //$NON-NLS-1$ //$NON-NLS-2$
    		  String fileName =  plugin.getBundleDescription().getSymbolicName() + version + ".jar"; //$NON-NLS-1$
    		  File destinationFile = new File(fPage.getDestination(), fileName);
    		  if (destinationFile.exists()) destinationFile.delete();
    		  try {
    				project.build(IncrementalProjectBuilder.CLEAN_BUILD, new NullProgressMonitor());
    				//project.build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
    		  	boolean compilationSuccess = buildProject(project);
    		  	if (compilationSuccess){
      				JarFileUtils.createPluginJar(fPage.getDestination(), sourceFile, fileName, manifestContent);
      				successfullyExported.add(MessageFormat.format(Messages.ExportAdapterWizard_projectSuccess, new Object[]{project.getName(), new File(fPage.getDestination(), fileName).toString()}));
    		  	} else {
      				notExported.add(MessageFormat.format(Messages.ExportAdapterWizard_projectError, new Object[]{project.getName()}));
    		  	}
    			} catch (Exception e) {
    				notExported.add(MessageFormat.format(Messages.ExportAdapterWizard_projectError, new Object[]{project.getName()}));
    				e.printStackTrace();
    			}
  			}
  		}
	  }  		
	  if (successfullyExported.isEmpty()){
			MessageDialog.openError(UIUtils.getShell(), Messages.ExportAdapterWizard_allError, getErrorMessage(notExported));
		} else if (notExported.size() == 0){
			MessageDialog.openInformation(UIUtils.getShell(), Messages.ExportAdapterWizard_allSuccess, getSuccessMessage(successfullyExported));
		} else {
			MessageDialog.openWarning(UIUtils.getShell(), Messages.ExportAdapterWizard_someErrors, getSuccessMessage(successfullyExported) + "\n" + getErrorMessage(notExported)); //$NON-NLS-2$
		}
		return true;
	}

}
