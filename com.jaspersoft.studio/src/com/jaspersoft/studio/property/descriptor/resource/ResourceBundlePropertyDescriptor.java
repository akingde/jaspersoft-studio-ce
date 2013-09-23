/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPResourceType;

public class ResourceBundlePropertyDescriptor extends NTextPropertyDescriptor {

	public ResourceBundlePropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new ResourceCellEditor(parent) {
			@Override
			protected String convertFile2Value(IFile f) {
				return ResourceBundlePropertyDescriptor.this.convertFile2Value(f);
			}
		};
		editor.setValidator(NResourceCellEditorValidator.instance());
		setValidator(NResourceCellEditorValidator.instance());
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		ASPropertyWidget textWidget = new SPResourceType(parent, section, this) {
			@Override
			protected String convertFile2Value(IFile f) {
				return ResourceBundlePropertyDescriptor.this.convertFile2Value(f);
			}
		};
		textWidget.setReadOnly(readOnly);
		return textWidget;
	}

	private String convertFile2Value(IFile f) {
		String val = f.getProjectRelativePath().toOSString();
		val = val.replaceAll(".properties$", ""); //$NON-NLS-1$ //$NON-NLS-2$
		for (Locale l : Locale.getAvailableLocales()) {
			if (val.endsWith("_" + l.toString())) { //$NON-NLS-1$
				val = val.replaceAll("_" + l.toString() + "$", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				break;
			}
		}
		try {
			IPath path = f.getParent().getFullPath();
			if (!hasEntry(path, f)
					&& UIUtils.showConfirmation("Add to Classpath", Messages.ResourceBundlePropertyDescriptor_warning)) {
				addSourceFolder(path, f);
				val = val.substring(val.lastIndexOf("/") + 1);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return val;
	}

	private void addSourceFolder(IPath folder, IFile f) throws JavaModelException {
		IJavaProject openProject = JavaCore.create(f.getProject());

		IClasspathEntry[] entries = openProject.getRawClasspath();
		List<IClasspathEntry> entriesArray = new ArrayList<IClasspathEntry>(Arrays.asList(entries));
		IClasspathEntry srcEntry = JavaCore.newSourceEntry(folder, null);

		int nestedResource = preventNesting(srcEntry, entriesArray);
		while (nestedResource != -1) {
			entriesArray.remove(nestedResource);
			nestedResource = preventNesting(srcEntry, entriesArray);
		}

		entriesArray.add(JavaCore.newSourceEntry(srcEntry.getPath()));
		IClasspathEntry[] newEntries = entriesArray.toArray(new IClasspathEntry[entriesArray.size()]);

		openProject.setRawClasspath(newEntries, null);
	}

	private int preventNesting(IClasspathEntry entry, List<IClasspathEntry> classpath) {
		if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
			IPath entryPath = entry.getPath();
			for (int j = 0; j < classpath.size(); j++) {
				IClasspathEntry otherEntry = classpath.get(j);
				if (entry != otherEntry && otherEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE
						&& entryPath.isPrefixOf(otherEntry.getPath()))
					return j;
			}
		}
		return -1;
	}

	private boolean hasEntry(IPath path, IFile f) throws CoreException {
		if (f.getProject().getNature(JavaCore.NATURE_ID) == null)
			return true;
		IJavaProject openProject = JavaCore.create(f.getProject());
		IClasspathEntry[] entries = openProject.getRawClasspath();
		for (IClasspathEntry entry : entries) {
			if (entry.getPath().equals(path))
				return true;
			else if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE && entry.getPath().isPrefixOf(path)) {
				return true;
			}
		}
		return false;
	}
}
