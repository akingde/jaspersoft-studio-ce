/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import net.sf.jasperreports.eclipse.classpath.JavaProjectClassLoader;

public class JrsClassLoader extends ClassLoader {

	private final class ResourceListener implements IResourceChangeListener {
		@Override
		public void resourceChanged(IResourceChangeEvent event) {
			IResource r = event.getResource();
			if (toRefresh(r))
				refresh();
			else if (r == null && event.getType() == IResourceChangeEvent.POST_CHANGE
					&& event.getDelta().getKind() == IResourceDelta.CHANGED)
				try {
					event.getDelta().accept(new IResourceDeltaVisitor() {

						@Override
						public boolean visit(IResourceDelta delta) throws CoreException {
							IResource res = delta.getResource();
							if (!res.exists())
								return true;
							int type = res.getType();
							if (toRefresh(r))
								refresh();
							if (type == IResource.FOLDER && delta.getKind() == IResourceDelta.REMOVED) {
								res.accept(new IResourceVisitor() {

									@Override
									public boolean visit(IResource resource) throws CoreException {
										if (resource.getType() == IResource.FILE) {
											IFile file = (IFile) resource;
											if (file.getProject().equals(container.getProject())) {

												try {
													URL rurl = file.getLocationURI().toURL();
													for (URL u : urls)
														if (u.equals(rurl)) {
															refresh();
															return false;
														}
												} catch (MalformedURLException e) {
													// ignore exception
												}
											}
										}
										return true;
									}
								});
							}
							return true;
						}
					});
				} catch (CoreException e) {
					e.printStackTrace();
				}
		}

		private boolean toRefresh(IResource r) {
			return r instanceof IProject || (r instanceof IFolder && isFolderOrParent(r))
					|| (r instanceof IFile && (r.getName().endsWith(".jar") || r.getName().endsWith(".zip")
							|| r.getName().endsWith(".properties")));
		}

		private boolean isFolderOrParent(IResource r) {
			IResource c = container;
			do {
				if (c.equals(r))
					return true;
				c = c.getParent();
			} while (c instanceof IFolder);
			return false;
		}
	}

	private IContainer container;
	private URLClassLoader loader;
	private ResourceListener listener;

	public JrsClassLoader(ClassLoader parent, IContainer container) {
		super(parent);
		this.container = container;
		refresh();
		listener = new ResourceListener();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener,
				IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.POST_CHANGE);
	}

	private Set<URL> urls;

	private URL[] buildPaths() {
		urls = new HashSet<>();
		try {
			container.accept(new IResourceVisitor() {

				@Override
				public boolean visit(IResource resource) throws CoreException {
					if (resource instanceof IFile && resource.getFileExtension() != null
							&& (resource.getFileExtension().equals("jar") || resource.getFileExtension().equals("zip")
									|| resource.getFileExtension().equals("properties"))) {
						try {
							urls.add(resource.getLocationURI().toURL());
						} catch (MalformedURLException e) {
							// ignore exception
						}
					}
					return true;
				}
			});
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		return urls.toArray(new URL[urls.size()]);
	}

	public void refresh() {
		disposeClassLoader();
		loader = URLClassLoader.newInstance(buildPaths(), getParent());
	}

	private void disposeClassLoader() {
		if (loader != null) {
			JavaProjectClassLoader.clean(loader);
			loader = null;
		}
	}

	public void dispose() {
		disposeClassLoader();
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
	}

	@Override
	protected URL findResource(String name) {
		if (loader != null)
			return loader.getResource(name);
		return super.findResource(name);
	}

	@Override
	protected Enumeration<URL> findResources(String name) throws IOException {
		if (loader != null)
			return loader.getResources(name);
		return super.findResources(name);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		if (loader != null)
			return loader.loadClass(name);
		throw new ClassNotFoundException(name);
	}
}
