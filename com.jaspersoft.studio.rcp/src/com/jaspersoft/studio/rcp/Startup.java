/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.rcp;

import java.util.List;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.wizard.project.ProjectUtil;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.rcp.messages.Messages;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 *
 */
public class Startup implements IStartup {

	private void testRefreshCommand() {
		ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		service.addExecutionListener(new IExecutionListener() {

			@Override
			public void postExecuteSuccess(String commandId, Object returnValue) {
				if (org.eclipse.ui.IWorkbenchCommandConstants.FILE_REFRESH.equals(commandId)) {
					List<JrxmlEditor> editors = SelectionHelper.getOpenedEditors();
					for(JrxmlEditor editor : editors){
						JasperReportsConfiguration jConfig = editor.getJrContext(null);
						if (jConfig != null) jConfig.refreshClasspath();
					}
				}
			}

			@Override
			public void preExecute(String commandId, final ExecutionEvent event) {

			}

			@Override
			public void notHandled(String commandId,
					NotHandledException exception) {
				
			}

			@Override
			public void postExecuteFailure(String commandId,
					ExecutionException exception) {
				
			}

		});
	}

	public void earlyStartup() {
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(FileUtils.DEFAULT_PROJECT); //$NON-NLS-1$
		IProgressMonitor monitor = new NullProgressMonitor();
		boolean defaultPrjCreatedOnce = JaspersoftStudioPlugin.getInstance().getPreferenceStore().getBoolean(FileUtils.DEFAULT_PROJECT_PROPERTY);
		try {
			if (!project.exists() && !defaultPrjCreatedOnce) {
				project.create(monitor);
				project.open(monitor);
				ProjectUtil.createJRProject(monitor, project);
				IProjectDescription description = project.getDescription();
				description.setName(Messages.Startup_jss_project);
				project.setDescription(description, monitor);
			}
			JaspersoftStudioPlugin.getInstance().getPreferenceStore().setValue(FileUtils.DEFAULT_PROJECT_PROPERTY, true);
			IEditorRegistry registry = PlatformUI.getWorkbench().getEditorRegistry();
			registry.setDefaultEditor("*.properties", "com.essiembre.rbe.eclipse.editor.ResourceBundleEditor");
			testRefreshCommand();
		} catch (CoreException e) {
			e.printStackTrace();
		} finally {
			monitor.done();
		}
	}

}
