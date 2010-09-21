/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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

			if (findType == null) {
//				return super.findClass(className);
//				Class<?> c = Class.forName(className);
//				if (c != null)
//					return c;
				throw new ClassNotFoundException(className);
			}
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