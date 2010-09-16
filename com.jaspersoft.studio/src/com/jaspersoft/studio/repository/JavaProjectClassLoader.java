package com.jaspersoft.studio.repository;

import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

public class JavaProjectClassLoader extends ClassLoader {
	private IJavaProject javaProject;

	public JavaProjectClassLoader(IJavaProject project) {
		super();
		if (project == null || !project.exists() || !project.isOpen())
			throw new IllegalArgumentException("Invalid javaProject");
		this.javaProject = project;
	}

	public JavaProjectClassLoader(IJavaProject project, ClassLoader classLoader) {
		super(classLoader);
		if (project == null || !project.exists() || !project.isOpen())
			throw new IllegalArgumentException("Invalid javaProject");
		this.javaProject = project;
	}

	@Override
	protected Class findClass(String className) throws ClassNotFoundException {
		IClassFile classFile = null;
		try {
			IType findType = javaProject.findType(className);
			if (findType == null)
				throw new ClassNotFoundException(className);
			final IPackageFragment fragment = findType.getPackageFragment();
			classFile = fragment.getClassFile(className.substring(className.lastIndexOf('.') + 1) + ".class");
			final byte[] buff = classFile.getBytes();
			if (buff == null)
				throw new ClassNotFoundException(className);
			return defineClass(className, buff, 0, buff.length);
		} catch (JavaModelException e) {
			throw new ClassNotFoundException(className);
		}
	}
}