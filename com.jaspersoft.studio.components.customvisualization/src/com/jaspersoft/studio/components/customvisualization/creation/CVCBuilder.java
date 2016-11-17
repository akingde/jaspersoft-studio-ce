/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

import net.sf.jasperreports.eclipse.builder.Markers;

public class CVCBuilder extends IncrementalProjectBuilder {
	public static final String BUILDER_ID = "com.jaspersoft.studio.cvc.builder"; //$NON-NLS-1$

	class DeltaVisitor implements IResourceDeltaVisitor {
		private IProgressMonitor monitor;

		public DeltaVisitor(IProgressMonitor monitor) {
			super();
			this.monitor = monitor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse
		 * .core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			if (monitor.isCanceled())
				return false;
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
			case IResourceDelta.REMOVED:
			case IResourceDelta.CHANGED:
				compile(delta.getResource(), monitor);
				break;
			}
			// return true to continue visiting children.
			return true;
		}
	}

	class ResourceVisitor implements IResourceVisitor {
		private IProgressMonitor monitor;

		public ResourceVisitor(IProgressMonitor monitor) {
			super();
			this.monitor = monitor;
		}

		public boolean visit(IResource resource) throws CoreException {
			if (monitor.isCanceled())
				return false;
			compile(resource, monitor);
			return true;
		}
	}

	class CleanResourceVisitor implements IResourceVisitor {
		private IProgressMonitor monitor;

		public CleanResourceVisitor(IProgressMonitor monitor) {
			super();
			this.monitor = monitor;
		}

		public boolean visit(IResource resource) throws CoreException {
			if (monitor.isCanceled())
				return false;
			if (resource.exists()) {
				if (resource.getName().equals(ConsoleExecuter.BUILD_FILE_NAME))
					Markers.deleteMarkers(resource);
				else if (resource.getName().equals("min.js"))
					resource.delete(false, SubMonitor.convert(monitor));
			}
			return true;
		}
	}

	@Override
	protected void clean(IProgressMonitor monitor) throws CoreException {
		IProject currentProject = getProject();
		if (currentProject == null || !currentProject.isAccessible())
			return;
		monitor.subTask("Cleaning");
		long stime = System.currentTimeMillis();
		getProject().accept(new CleanResourceVisitor(monitor));
		long etime = System.currentTimeMillis();
		System.out.println("Cleaned in " + (etime - stime) + " ms");
	}

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		IProject currentProject = getProject();
		if (currentProject == null)
			return new IProject[0];
		switch (kind) {
		case FULL_BUILD:
			fullBuild(monitor);
			break;
		case CLEAN_BUILD:
			clean(monitor);
			break;
		default:
			IResourceDelta delta = getDelta(getProject());
			if (delta == null)
				fullBuild(monitor);
			else
				incrementalBuild(delta, monitor);
		}
		return new IProject[0];
	}

	protected void fullBuild(final IProgressMonitor monitor) throws CoreException {
		long stime = System.currentTimeMillis();
		getProject().accept(new ResourceVisitor(monitor));
		long etime = System.currentTimeMillis();
		System.out.println("Full Build in " + (etime - stime) + " ms");
	}

	protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException {
		delta.accept(new DeltaVisitor(monitor));
	}

	private IFile compile(IResource resource, IProgressMonitor monitor) {
		if (resource instanceof IFile && resource.getName().equals(ConsoleExecuter.BUILD_FILE_NAME)) {
			List<IFile> files = new ArrayList<IFile>();
			files.add((IFile) resource);
			new ConsoleExecuter(files);
		}
		return null;
	}
}
