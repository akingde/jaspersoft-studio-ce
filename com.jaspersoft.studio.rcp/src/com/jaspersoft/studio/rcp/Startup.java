/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.rcp;

import java.util.Arrays;

import net.sf.jasperreports.eclipse.builder.JasperReportsNature;
import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.ui.IStartup;

import com.jaspersoft.studio.rcp.heartbeat.Heartbeat;
import com.jaspersoft.studio.rcp.messages.Messages;

/**
 *
 */
public class Startup implements IStartup {

	public void earlyStartup() {
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(FileUtils.DEFAULT_PROJECT); //$NON-NLS-1$
		IProgressMonitor monitor = new NullProgressMonitor();
		try {
			if (!project.exists()) {
				project.create(monitor);
			}
			project.open(monitor);

			IProjectDescription description = project.getDescription();
			description.setName(Messages.Startup_jss_project);
			String[] ids = description.getNatureIds();
			if (!Arrays.asList(ids).contains(JavaCore.NATURE_ID)) {
				String[] newIds = new String[ids.length + 1];
				System.arraycopy(ids, 0, newIds, 0, ids.length);
				newIds[newIds.length - 1] = JavaCore.NATURE_ID;
				description.setNatureIds(newIds);

				project.setDescription(description, monitor);

				ICommand java = description.newCommand();
				java.setBuilderName(JavaCore.BUILDER_ID);

				description.setBuildSpec(new ICommand[] { java });

				project.setDescription(description, monitor);

				IJavaProject javaproj = JavaCore.create(project);
				javaproj.setOutputLocation(project.getFullPath(), monitor);
				IClasspathEntry srcEntry = JavaCore.newSourceEntry(project
						.getFullPath());
				IClasspathEntry containerEntry = JavaCore
						.newContainerEntry(new Path(JavaRuntime.JRE_CONTAINER));
				javaproj.setRawClasspath(new IClasspathEntry[] {
						containerEntry, srcEntry }, monitor);
			}
			ids = description.getNatureIds();
			if (!Arrays.asList(ids).contains(JasperReportsNature.NATURE_ID)) {
				String[] newIds = new String[ids.length + 1];
				System.arraycopy(ids, 0, newIds, 0, ids.length);
				newIds[newIds.length - 1] = JasperReportsNature.NATURE_ID;
				description.setNatureIds(newIds);
				project.setDescription(description, monitor);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		} finally {
			monitor.done();
		}

		String devmode = System.getProperty("devmode");
		if(devmode==null || !devmode.equals("true")){
			Job job = new Job("Check New Version") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {

					Heartbeat.run();
					return Status.OK_STATUS;
				}

			};
			job.setSystem(true);
			job.schedule();
		}
		
	}

}
