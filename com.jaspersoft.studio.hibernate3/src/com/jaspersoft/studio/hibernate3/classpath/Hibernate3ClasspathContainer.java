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

public class Hibernate3ClasspathContainer implements IClasspathContainer {

	public static final IPath ID = new Path("com.jaspersoft.studio.data.hibernate.HIBERNATE_3_2_0_GA_CONTAINER");
	// path to the container
	private IPath path;
	
	public Hibernate3ClasspathContainer(IPath path) {
		this.path = path;
	}

	@Override
	public IClasspathEntry[] getClasspathEntries() {
		List<IClasspathEntry> hibernateItems = new ArrayList<IClasspathEntry>();
		Bundle bundle = Hibernate3Activator.getDefault().getBundle();
		Enumeration<URL> urls = bundle.findEntries("lib/", "*.jar", false);
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
		return "Hibernate 3.2.0.GA Library";
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
