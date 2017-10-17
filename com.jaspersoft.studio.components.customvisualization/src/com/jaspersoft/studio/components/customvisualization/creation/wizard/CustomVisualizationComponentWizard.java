/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation.wizard;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.customvisualization.CustomVisualizationActivator;
import com.jaspersoft.studio.components.customvisualization.creation.CVCNature;
import com.jaspersoft.studio.components.customvisualization.creation.ModuleDefinition;
import com.jaspersoft.studio.components.customvisualization.creation.ModuleManager;
import com.jaspersoft.studio.components.customvisualization.creation.VelocityLibrary;
import com.jaspersoft.studio.components.customvisualization.creation.VelocityShimLibrary;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentPropertyDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentSectionDescriptor;
import com.jaspersoft.studio.utils.VelocityUtils;
import com.jaspersoft.studio.wizards.JSSWizard;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.wizard.project.ProjectUtil;

/**
 * Wizard to create a custom visualization component project
 * 
 * @author Orlandin Marco
 *
 */
public class CustomVisualizationComponentWizard extends JSSWizard implements INewWizard {

	/**
	 * Key to write or read the selected module in the first step from the
	 * wizard settings
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
	private CustomVisualizationComponentListPage page0;

	/**
	 * Page to get a summary of all the libraries used by the project
	 */
	private CustomVisualizationComponentSummaryPage page1;

	/**
	 * Page to review the licenses of all the libraries used by the project
	 */
	private CustomVisualizationComponentLicensePage page2;

	public CustomVisualizationComponentWizard() {
		super();
		setWindowTitle(Messages.CustomVisualizationComponentWizard_title);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		page0 = new CustomVisualizationComponentListPage();
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
		final String projectName = page0.getProjectName();
		final ModuleDefinition selected = page0.getSelectedModule();
		final String mName = page0.getModule();
		final String oldModule = selected.getModuleName();
		try {
			selected.setModuleName(mName);
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Creating CVC Component Project", IProgressMonitor.UNKNOWN);
					try {

						boolean result = createProject(projectName, monitor);
						if (result) {
							IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
							IProject project = root.getProject(projectName);
							File dest = new File(root.getRawLocation().toFile(), projectName);
							List<VelocityLibrary> libraries = new ArrayList<VelocityLibrary>();
							List<VelocityShimLibrary> shimLibraries = new ArrayList<VelocityShimLibrary>();

							try {
								String outputScriptName = projectName + ".min.js";
								// Add the main module and all it's
								// dependencies
								addModule(selected, shimLibraries, libraries, dest);
								for (ModuleDefinition requiredLibrary : selected.getRequiredLibraries()) {
									addModule(requiredLibrary, shimLibraries, libraries, dest);
								}

								String cssFileName = generateCSS(project, monitor, selected);
								String renderFileName = generateRender(project, monitor, selected);

								libraries.add(new VelocityLibrary(mName, removeJsExtension(renderFileName)));
								String buildFile = generateBuildFile(libraries, shimLibraries, mName, outputScriptName);
								createFile("build.js", project, buildFile, monitor); //$NON-NLS-1$
								try {
									createUIFiles(monitor, project, mName, cssFileName, selected.getLibraryURL());
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								// Eventually create a sample for the
								// current project
								createSample(selected, outputScriptName, cssFileName, project, monitor);
								try {
									project.refreshLocal(IProject.DEPTH_INFINITE, monitor);
									project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
									project.refreshLocal(IProject.DEPTH_INFINITE, monitor);
								} catch (CoreException e) {
									e.printStackTrace();
								}
							} catch (FileNotFoundException ex) {
								MessageDialog.openError(UIUtils.getShell(),
										Messages.CustomVisualizationComponentWizard_errorTitle, ex.getMessage());
							}
						}
					} finally {
						monitor.done();
						selected.setModuleName(oldModule);
					}
				}
			});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e.getCause());
		} catch (InterruptedException e) {
			UIUtils.showError(e);
		}
		return true;
	}

	protected void createUIFiles(IProgressMonitor monitor, IProject project, String mName, String cssFName,
			String jsFName) throws IOException {
		if (page0.isCreateUI()) {

			String uiIconPath = page0.getUiIconPath();
			String thName = null;
			if (Misc.isNullOrEmpty(uiIconPath)) {
				URL img = CustomVisualizationActivator.getDefault().getBundle().getResource("icons/blank_a4.png");
				thName = FilenameUtils.getName(img.toString());
				createFile(thName, project, img.openStream(), monitor);
			} else {
				File f = new File(uiIconPath);
				thName = f.getName();
				createFile(thName, project, f.toURI().toURL().openStream(), monitor);
			}
			ComponentDescriptor cd = new ComponentDescriptor();
			cd.setLabel(page0.getUiLabel());
			cd.setDescription(page0.getUiDescription());
			cd.setThumbnail(thName);
			cd.setModule(mName);

			List<ComponentSectionDescriptor> sections = new ArrayList<ComponentSectionDescriptor>();
			ComponentSectionDescriptor csd = new ComponentSectionDescriptor();
			csd.setExpandable(false);
			csd.setName("Script");

			List<ComponentPropertyDescriptor> props = new ArrayList<ComponentPropertyDescriptor>();
			ComponentPropertyDescriptor cpd = new ComponentPropertyDescriptor();
			cpd.setType("TEXT");
			cpd.setName("module");
			cpd.setLabel("Module");
			cpd.setDescription("Module name");
			cpd.setDefaultValue(mName);
			cpd.setReadOnly(true);
			cpd.setMandatory(true);
			props.add(cpd);

			if (cssFName != null) {
				cpd = new ComponentPropertyDescriptor();
				cpd.setType("PATH");
				cpd.setName("css");
				cpd.setLabel("CSS Path");
				cpd.setDescription("CSS Path");
				cpd.setDefaultValue(cssFName);
				cpd.setReadOnly(false);
				cpd.setMandatory(true);
				props.add(cpd);
			}

			cpd = new ComponentPropertyDescriptor();
			cpd.setType("PATH");
			cpd.setName("script");
			cpd.setLabel("Script Path");
			cpd.setDescription("Script path");
			cpd.setDefaultValue(new File(jsFName).getName());
			cpd.setReadOnly(false);
			cpd.setMandatory(true);
			props.add(cpd);

			csd.setProperties(props);

			sections.add(csd);

			cd.setSections(sections);

			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
			createFile(mName + ".json", project, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cd), //$NON-NLS-1$
					monitor);
		}
	}

	/**
	 * Check if the selected module provide some samples, like a sample jrxml or
	 * its resources. In this case it add the jr nature to the project and copy
	 * all the specified resources inside the project
	 * 
	 * @param selectedModule
	 *            the selected module
	 * @param scriptName
	 *            the name of the output script that will be generated when the
	 *            project component is compiled
	 * @param cssName
	 *            name of the css file if any, could be null if no css is
	 *            provided
	 * @param project
	 *            the current project
	 * @param monitor
	 *            monitor to execute the operation
	 */
	private void createSample(ModuleDefinition selectedModule, String scriptName, String cssName, IProject project,
			IProgressMonitor monitor) {
		if (!selectedModule.getSampleResources().isEmpty()) {
			try {
				// It uses the samples, add the jr nature to the project
				if (!ProjectUtil.hasJRNature(monitor, project)) {
					ProjectUtil.createJRProject(monitor, project);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (String resourcePath : selectedModule.getSampleResources()) {
				InputStream resource = selectedModule.getResource(resourcePath);
				if (resource != null) {
					String resourceName = getResourceName(resourcePath);
					if (resourceName.toLowerCase().endsWith(".jrxml")) {
						// It's a jrxml, call the generate method to provide
						// some project dependent informations
						String jrxmlContent = generateJRXML(resourcePath, scriptName, cssName,
								selectedModule.getModuleName());
						createFile(selectedModule.getModuleName() + "_sample.jrxml", project, jrxmlContent, monitor);
					} else {
						// It's another resource file (maybe required from the
						// jrxml), simply create it in the folder
						createFile(resourceName, project, resource, monitor);
					}
				}
			}
		}
	}

	/**
	 * Generate a sample jrxml for the project by starting from an existing
	 * jrxml and doing some substitution. Actually only the script name is write
	 * inside the final sample jrxml
	 * 
	 * @param jrxmlPath
	 *            path to the sample template
	 * @param scriptName
	 *            name of the script to use along with the template to generate
	 *            the sample jrxml
	 * @param cssName
	 *            name of the css file if any, could be null if no css is
	 *            provided
	 * @return the content of the sample jrxml
	 */
	private String generateJRXML(String jrxmlPath, String scriptName, String cssName, String moduleName) {
		VelocityContext functionContext = new VelocityContext();
		functionContext.put("scriptname", scriptName); //$NON-NLS-1$
		functionContext.put("cssname", cssName); //$NON-NLS-1$
		functionContext.put("modulename", moduleName); //$NON-NLS-1$

		Template functionTemplate = ve.getTemplate(jrxmlPath);
		StringWriter fsw = new StringWriter();
		functionTemplate.merge(functionContext, fsw);
		return fsw.toString();
	}

	/**
	 * Return a resource name starting from it's path. To find the resource name
	 * the last / is searched, if not found it will return the path itself,
	 * otherwise the substring after the last /
	 * 
	 * @param resourcePath
	 *            a path of a resource
	 * @return a not null string
	 */
	private String getResourceName(String resourcePath) {
		int slash = resourcePath.lastIndexOf("/");
		if (slash == -1)
			return resourcePath;
		else
			return resourcePath.substring(slash + 1);
	}

	/**
	 * Get a resource name and if it ends with the js extension then the
	 * extension is removed
	 * 
	 * @param source
	 *            the name of the resource
	 * @return the name without the extension if it was a .js, otherwise the
	 *         source
	 */
	private String removeJsExtension(String source) {
		if (source.toLowerCase().endsWith(".js"))
			return source.substring(0, source.length() - 3);
		return source;
	}

	/**
	 * Add a module to the project, it's library is added on the project folder
	 * and are created the informations to have it added to the build.js file
	 * 
	 * @param module
	 *            the module to add
	 * @param shimmedList
	 *            the list of library to added to the shim list
	 * @param librariesList
	 *            the list of library to add to the path list
	 * @param projectFolder
	 *            the project folder
	 * @throws FileNotFoundException
	 *             throw the exception if the library file can not be found
	 */
	private void addModule(ModuleDefinition module, List<VelocityShimLibrary> shimmedList,
			List<VelocityLibrary> librariesList, File projectFolder) throws FileNotFoundException {
		// Check if the name is null because a module could not have a library
		String fileName = module.getLibraryFilename();
		if (fileName != null) {
			File resourceFile = ModuleManager.getLibraryFile(module);
			if (resourceFile != null && resourceFile.exists()) {
				File workspaceCopy = new File(projectFolder, fileName);
				try {
					FileUtils.copyFile(resourceFile, workspaceCopy);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// ADD THE LIBRARY TO THE LIBRARIES LIST
				librariesList.add(new VelocityLibrary(module.getVariableName(), removeJsExtension(fileName)));
				// CHECK IF THE MODULE MUST BE SHIMMED
				if (module.isNeedShim()) {
					String dependencies = ""; //$NON-NLS-1$
					for (int i = 0; i < module.getShimDependencies().size(); i++) {
						dependencies += "'" + module.getShimDependencies().get(i) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
						if (i < (module.getShimDependencies().size() - 1)) {
							dependencies += ","; //$NON-NLS-1$
						}
					}
					VelocityShimLibrary shimLibrary = new VelocityShimLibrary(module.getVariableName(),
							module.getShimExportName(), dependencies);
					shimmedList.add(shimLibrary);
				}

			} else {
				String errorMessage = MessageFormat.format(Messages.CustomVisualizationComponentWizard_errorDescription,
						new Object[] { module.getLibraryURL() });
				throw new FileNotFoundException(errorMessage);
			}
		}
	}

	/**
	 * Generate for the new custom visualization component project the css file
	 * 
	 * @param container
	 *            the container where the fill will be placed
	 * @param monitor
	 *            the monitor to execute the operation
	 * @param library
	 *            the module selected by the user in the wizard page
	 * @return the name of the css file or null if no css is provided by the
	 *         module
	 */
	private String generateCSS(IProject container, IProgressMonitor monitor, ModuleDefinition module) {
		String cssContent = module.getCssResource();
		if (cssContent != null) {
			String cssName = container.getName() + ".css";
			createFile(cssName, container, cssContent, monitor); // $NON-NLS-1$
			return cssName;
		}
		return null;
	}

	/**
	 * Generate for the new custom visualization component project the render
	 * file
	 * 
	 * @param container
	 *            the container where the fill will be placed
	 * @param monitor
	 *            the monitor to execute the operation
	 * @param library
	 *            the module selected by the user in the wizard page
	 */
	private String generateRender(IProject container, IProgressMonitor monitor, ModuleDefinition library) {
		String renderContent = library.getRenderResource();
		if (renderContent != null) {
			String renderFileName = container.getName() + ".js";
			createFile(renderFileName, container, generateJsResource(renderContent, library.getModuleName()), monitor); // $NON-NLS-1$
			return renderFileName;
		}
		return null;
	}

	private String generateJsResource(String content, String moduleName) {
		VelocityContext functionContext = new VelocityContext();
		functionContext.put("modulename", moduleName); //$NON-NLS-1$

		// Template functionTemplate = ve.getTemplate(jrxmlPath);
		StringWriter fsw = new StringWriter();
		Velocity.evaluate(functionContext, fsw, "", content);

		// functionTemplate.merge(functionContext, fsw);
		return fsw.toString();
	}

	/**
	 * Generate the build.js file using the template mixed with the data
	 * provided during the wizard
	 * 
	 * @param libraries
	 *            the list of javascript libraries to include inside the build
	 *            file
	 * @param shimLibraries
	 *            the list of javascript shimmed libraries to include inside the
	 *            build file
	 * @param modulename
	 *            the name of the folder where the project is contained, that it
	 *            is used as module name
	 */
	private String generateBuildFile(List<VelocityLibrary> libraries, List<VelocityShimLibrary> shimLibraries,
			String moduleName, String outputName) {
		VelocityContext functionContext = new VelocityContext();
		functionContext.put("libraries", libraries); //$NON-NLS-1$
		functionContext.put("hasShim", shimLibraries.size() > 0); //$NON-NLS-1$
		functionContext.put("shimlibraries", shimLibraries); //$NON-NLS-1$
		functionContext.put("modulename", moduleName); //$NON-NLS-1$
		functionContext.put("outputname", outputName); //$NON-NLS-1$

		Template functionTemplate = ve.getTemplate(BUILD_FILE);
		StringWriter fsw = new StringWriter();
		functionTemplate.merge(functionContext, fsw);
		return fsw.toString();
	}

	/**
	 * Add a textual file to the project
	 * 
	 * @param name
	 *            the name of the file
	 * @param container
	 *            the container of the file
	 * @param content
	 *            the textual content of the file
	 * @param progressMonitor
	 *            a progress monitor
	 * @return the added file
	 */
	protected static IFile createFile(String name, IContainer container, String content,
			IProgressMonitor progressMonitor) {
		try {
			final InputStream stream = new ByteArrayInputStream(content.getBytes(container.getDefaultCharset(true)));
			return createFile(name, container, stream, progressMonitor);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Add a file to the project
	 * 
	 * @param name
	 *            the name of the file
	 * @param container
	 *            the container of the file
	 * @param stream
	 *            the binary content of the file
	 * @param progressMonitor
	 *            a progress monitor
	 * @return the added file
	 */
	protected static IFile createFile(String name, IContainer container, InputStream stream,
			IProgressMonitor progressMonitor) {
		final IFile file = container.getFile(new Path(name));
		try {
			if (file.exists()) {
				file.setContents(stream, true, true, progressMonitor);
			} else {
				file.create(stream, true, progressMonitor);
			}
			stream.close();
		} catch (final Exception e) {
			JaspersoftStudioPlugin.getInstance().logError(e);
		}
		progressMonitor.worked(1);

		return file;
	}

	/**
	 * Create an empty project inside the workspace with the nature of a custom
	 * visualization component
	 * 
	 * @param projectName
	 *            the name of the project
	 * @param monitor
	 *            monitor to execute the operation
	 * @return true if the project was created correctly, false otherwise
	 */
	private boolean createProject(String projectName, IProgressMonitor monitor) {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		try {
			if (!project.exists()) {
				project.create(monitor);
				project.open(monitor);

				ProjectUtil.addNature(project, CVCNature.NATURE_ID, monitor);

				// IFolder folder =
				// project.getFolder(PreferenceConstants.getPreferenceStore().getString(PreferenceConstants.SRCBIN_BINNAME));
				// folder.create(IResource.FORCE | IResource.DERIVED, true,
				// monitor);
				// folder.setDerived(true, monitor);

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

	/**
	 * Can finish if all the pages are complete or if only the first page is
	 * available and it is complete
	 */
	@Override
	public boolean canFinish() {
		if (!page0.hasLibraryPage())
			return page0.isPageComplete();
		else
			return super.canFinish();
	}
}
