/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.rcp;

import java.util.Arrays;

import net.sf.jasperreports.eclipse.builder.JasperReportsNature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.ui.IStartup;

import com.jaspersoft.studio.rcp.messages.Messages;

/**
 *
 */
public class Startup implements IStartup {

	public void earlyStartup() {
		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject("MyReports"); //$NON-NLS-1$
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
	}

}
