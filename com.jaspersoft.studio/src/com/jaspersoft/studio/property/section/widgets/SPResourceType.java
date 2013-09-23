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
package com.jaspersoft.studio.property.section.widgets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.SelectionHelper;

public class SPResourceType extends SPText {

	private Button btn;

	public SPResourceType(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public void setReadOnly(boolean readonly) {
		super.setReadOnly(readonly);
		btn.setEnabled(!readonly);
	}
	
	private String removeLocale(String fileName){
		for (Locale loc : Locale.getAvailableLocales()){
			if (fileName.endsWith("_"+loc.toString())) {
				return fileName.substring(0, fileName.length() - loc.toString().length() -1);
			}
		}
		return fileName;
	}
	
	private int preventNesting(IClasspathEntry entry, List<IClasspathEntry> classpath){
	  if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE){
	  	IPath entryPath = entry.getPath();
	    for (int j = 0; j < classpath.size(); j++){
	            IClasspathEntry otherEntry = classpath.get(j);
	            if (entry != otherEntry && otherEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE){
	                    if (entryPath.isPrefixOf(otherEntry.getPath())){
	                    	return j;
	                    }
	            }
	    }
	  }
	  return -1;
	}
	
	private void addSourceFolder(IPath folder){
		IJavaProject openProject = SelectionHelper.getJavaProjectFromCurrentJRXMLEditor();
	
		try {
			IClasspathEntry[] entries = openProject.getRawClasspath();
			List<IClasspathEntry> entriesArray = new ArrayList<IClasspathEntry>(Arrays.asList(entries));
			IClasspathEntry srcEntry= JavaCore.newSourceEntry(folder, null);
	
			int nestedResource = preventNesting(srcEntry, entriesArray);
			while (nestedResource != -1){
				entriesArray.remove(nestedResource);
				nestedResource = preventNesting(srcEntry, entriesArray);
			}
			
			entriesArray.add(JavaCore.newSourceEntry(srcEntry.getPath()));			
			IClasspathEntry[] newEntries =entriesArray.toArray(new IClasspathEntry[entriesArray.size()]);
			
			openProject.setRawClasspath(newEntries, null);
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
	}
	
	private boolean hasEntry(IPath path){
		IJavaProject openProject = SelectionHelper.getJavaProjectFromCurrentJRXMLEditor();
		try {
			IClasspathEntry[] entries = openProject.getRawClasspath();
			for(IClasspathEntry entry : entries){
				 if (entry.getPath().equals(path)) return true;
				 else if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE && entry.getPath().isPrefixOf(path)){
             	return true;
         }
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return false;
	}

	protected void createComponent(Composite parent) {
		super.createComponent(parent);

		btn = section.getWidgetFactory().createButton(parent, "...", SWT.PUSH);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilteredResourcesSelectionDialog dialog = new FilteredResourcesSelectionDialog(ftext.getShell(), false,
						ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
				dialog.setTitle(Messages.ResourceCellEditor_open_resource);
				dialog.setInitialPattern("*.properties"); //$NON-NLS-1$
				if (dialog.open() == Window.OK) {
					IFile file = (IFile) dialog.getFirstResult();
			
					if (file != null){
						String fileName = file.getProjectRelativePath().toOSString().trim();
						int propertiesIndex = fileName.toLowerCase().lastIndexOf(".properties");
						if (propertiesIndex != -1) fileName = fileName.substring(0, propertiesIndex);
						fileName = removeLocale(fileName);
						handleTextChanged(section, pDescriptor.getId(), fileName);
						IPath path = file.getParent().getFullPath();
						if (!hasEntry(path)){
							boolean answerYes = UIUtils.showConfirmation("Add to Classpath", "To make the resource visible its containing folder must be added to the classpath, would you to do it now?");
							if (answerYes) addSourceFolder(path);
						}
					}
				}
			}
		});
	}

	protected void handleTextChanged(final AbstractSection section, final Object property, String text) {
		if (text != null && text.trim().isEmpty())
			text = null;
		section.changeProperty(property, text);
	}

}
