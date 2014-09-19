package com.jaspersoft.studio.hibernate3.classpath;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

public class Hibernate3ClasspathContainerInitializer extends
		ClasspathContainerInitializer {

	@Override
	public void initialize(IPath containerPath, IJavaProject project)
			throws CoreException {
		Hibernate3ClasspathContainer container = new Hibernate3ClasspathContainer(containerPath);
		JavaCore.setClasspathContainer(containerPath,new IJavaProject[] { project },
				new IClasspathContainer[] { container }, null);
	}

}
