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
package com.jaspersoft.studio.hibernate3.classpath;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.osgi.framework.Bundle;

import com.jaspersoft.studio.hibernate3.Hibernate3Activator;
import com.jaspersoft.studio.hibernate3.messages.Messages;

public class Hibernate3ClasspathContainer implements IClasspathContainer {

	public static final IPath ID = new Path("com.jaspersoft.studio.data.hibernate.HIBERNATE_3_2_0_GA_CONTAINER"); //$NON-NLS-1$
	// path to the container
	private IPath path;
	
	public Hibernate3ClasspathContainer(IPath path) {
		this.path = path;
	}

	@Override
	public IClasspathEntry[] getClasspathEntries() {
		List<IClasspathEntry> hibernateItems = new ArrayList<IClasspathEntry>();
		Bundle bundle = Hibernate3Activator.getDefault().getBundle();
		Enumeration<URL> urls = bundle.findEntries("lib/", "*.jar", false); //$NON-NLS-1$ //$NON-NLS-2$
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			try {
				URL fileURL = FileLocator.toFileURL(url);
				URI uri = new URI(
						fileURL.getProtocol(), fileURL.getUserInfo(), fileURL.getHost(), 
						fileURL.getPort(), fileURL.getPath(), fileURL.getQuery(), null);
				Path binpath = new Path(new File(uri).getAbsolutePath());
				Path srcpath = binpath;
				hibernateItems.add(JavaCore.newLibraryEntry(binpath, srcpath, new Path("/"))); //$NON-NLS-1$
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return hibernateItems.toArray(new IClasspathEntry[hibernateItems.size()]);
	}

	@Override
	public String getDescription() {
		return Messages.Hibernate3ClasspathContainer_Description;
	}

	@Override
	public int getKind() {
		return IClasspathContainer.K_APPLICATION;
	}

	@Override
	public IPath getPath() {
		return path;
	}

}
