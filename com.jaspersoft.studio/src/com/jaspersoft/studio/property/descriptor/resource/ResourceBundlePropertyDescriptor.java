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
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPResourceType;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.wizards.ResourceBundleFilterDialog;

/**
 * 
 * Widget descriptor with a button to select a properties resource file and a text area where the path of the selected
 * resource is shown. From the selected filename is calculated the bundle base name. 
 * Other than this if the parent folder of the file is not in the classpath it is proposed to the user to add it automatically
 * 
 * @author Orlandin Marco & Slavic
 *
 */
public class ResourceBundlePropertyDescriptor extends NTextPropertyDescriptor {

	/**
	 * This class extends the original widget to select a resource to be used 
	 * only with resource bundle. For example the selection of the file is limited
	 * to the files with .properties extension and contained in the actually opened
	 * project or in one of its dependences
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class SPBundleType extends SPResourceType {
		
		public SPBundleType(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
			super(parent, section, pDescriptor);
		}

		@Override
		protected String convertFile2Value(IFile f) {
			return ResourceBundlePropertyDescriptor.this.convertFile2Value(f);
		}
		
		@Override
		protected SelectionAdapter buttonPressed(){
			return new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					IJavaProject openProject = SelectionHelper.getJavaProjectFromCurrentJRXMLEditor();
					if (openProject != null){
						ResourceBundleFilterDialog dialog = new ResourceBundleFilterDialog(ftext.getShell(), false, openProject);
						dialog.setTitle(Messages.ResourceCellEditor_open_resource);
						if (dialog.open() == Window.OK) {
							IFile file = (IFile) dialog.getFirstResult();
							if (file != null){
								String fileName = convertFile2Value(file);
								handleTextChanged(section, pDescriptor.getId(), fileName);
								IPath path = file.getParent().getFullPath();
								if (!hasEntry(path, openProject)){
									boolean answerYes = UIUtils.showConfirmation(Messages.SPResourceType_messageTitle, Messages.SPResourceType_messageDescription);
									if (answerYes) addSourceFolder(path, openProject);
								}
							}
						}
					}
				}
			};
		}
	};
	
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
		ASPropertyWidget textWidget = new SPBundleType(parent, section, this);
		textWidget.setReadOnly(readOnly);
		return textWidget;
	}

	private String convertFile2Value(IFile f) {
		String fileName = f.getProjectRelativePath().toOSString().trim();
		int propertiesIndex = fileName.toLowerCase().lastIndexOf(".properties"); //$NON-NLS-1$
		if (propertiesIndex != -1) fileName = fileName.substring(0, propertiesIndex);
		fileName = removeLocale(fileName);
		return fileName;
	}

	/**
	 * Add a path to the classpath of the project
	 * 
	 * @param folder the path to include
	 * @param openProject the project
	 */
	private void addSourceFolder(IPath folder, IJavaProject openProject){
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
			IClasspathEntry[] newEntries = entriesArray.toArray(new IClasspathEntry[entriesArray.size()]);
			
			openProject.setRawClasspath(newEntries, null);
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Check if one of the resources already in the classpath is included also in the one we want to add
	 * 
	 * @param entry resource to add
	 * @param classpath actual resources in the classpath
	 * @return index of the resource of classpath that is already included as subfolder in entry or -1 if none
	 */
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

	
	/**
	 * Check if the filename has a locale as terminal part, in this case the locale is removed
	 * to get a base name
	 * 
	 * @param fileName original filename
	 * @return filename without locale (if the filename had not a locale this is equal to the filename)
	 */
	private String removeLocale(String fileName){
		for (Locale loc : Locale.getAvailableLocales()){
			if (fileName.endsWith("_"+loc.toString())) { //$NON-NLS-1$
				return fileName.substring(0, fileName.length() - loc.toString().length() -1);
			}
		}
		return fileName;
	}
	
	
	/**
	 * Check if a path is already inside the classpath of the project. It is 
	 * also consider if the path is a subfolder of a folder already in the classpath
	 * 
	 * @param path the path
	 * @param openProject the project
	 * @return true if the path is already included (directly or indirectly) in the classpath, otherwise false
	 */
	private boolean hasEntry(IPath path, IJavaProject openProject){
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
}
