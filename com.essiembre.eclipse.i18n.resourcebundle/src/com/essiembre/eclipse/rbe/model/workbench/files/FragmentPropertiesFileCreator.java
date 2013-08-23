/*
 * Copyright (C) 2007  Uwe Voigt
 * 
 * This file is part of Essiembre ResourceBundle Editor.
 * 
 * Essiembre ResourceBundle Editor is free software; you can redistribute it 
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * Essiembre ResourceBundle Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Essiembre ResourceBundle Editor; if not, write to the 
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, 
 * Boston, MA  02111-1307  USA
 */
package com.essiembre.eclipse.rbe.model.workbench.files;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import com.essiembre.eclipse.rbe.model.workbench.RBEPreferences;
import com.essiembre.eclipse.rbe.ui.editor.resources.PDEUtils;

/**
 * This is a property file creator for files within a fragment project contributing
 * translations.
 *
 * @author Uwe Voigt (http://sourceforge.net/users/uwe_ewald/)
 */
public class FragmentPropertiesFileCreator extends PropertiesFileCreator {
	private final IProject fragment;
    private final String targetDir;
    private final String baseFileName;
	private final String extension;
    
	/**
	 * Creates an instance.
	 * 
	 * @param fragment the fragment project
	 * @param targetDir the target directory
	 * @param baseFileName the base bundle name
	 * @param extension the file extension
	 */
	public FragmentPropertiesFileCreator(IProject fragment, String targetDir, String baseFileName, String extension) {
		super();
		this.fragment = fragment;
		this.targetDir = targetDir;
		this.baseFileName = baseFileName;
		this.extension = extension;
	}

	/* (non-Javadoc)
	 * @see com.essiembre.eclipse.rbe.model.workbench.files.PropertiesFileCreator#buildFilePath(java.util.Locale)
	 */
	protected IPath buildFilePath(final Locale locale) throws CoreException {
		/*
		 * Check where to create the file
		 */
		IProject project = null;
		if (!shouldFileBeCreatedInFragment(fragment)) {
			project = PDEUtils.getFragmentHost(fragment);
		}
		if (project == null) {
			project = fragment;
		}
		/*
		 * create the resource parent paths if necessary 
		 */
		IResource resource = project.findMember(targetDir);
		if (resource == null || !resource.exists()) {
			final IPath path = new Path(targetDir);			
			final List paths = new ArrayList();
			IPath parent = path;
			do {
				paths.add(parent);
				parent = parent.uptoSegment(parent.segmentCount() - 1);
				resource = project.findMember(parent);
			} while (resource == null || !resource.exists());
			for (int i = paths.size() - 1; i >= 0; i--) {
				project.getFolder(((IPath) paths.get(i))).create(true, true, null);
			}
		}

		/*
		 * build the resource path according to the requested language
		 */
		IPath filePath = new Path(baseFileName);
		if (locale != null) {
			filePath = new Path(filePath.toString() + '_' + locale.toString());
			filePath = filePath.addFileExtension(extension);
		}
        return project.getFullPath().append(targetDir).append(filePath);
	}
	
	/**
	 * Ask the user where to create the new file 
	 * the fragment or the host plugin.
	 * @return Whether the user decided to create the file in the fragment.
	 */
	public static boolean shouldFileBeCreatedInFragment(IProject fragment) {
		if (PDEUtils.getFragmentHost(fragment) == null) {
			return true; // there is no host plugin, can not create something there
		}
		if (RBEPreferences.getLoadOnlyFragmentResources())
			return true;
		// TODO externalize/translate this messages
		MessageDialog dialog = new MessageDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
				"File creation", null, // accept
				"Resources where loaded from both the host and the fragment plugin. " +
				"Where do you want to create the new bundle?", 
				MessageDialog.QUESTION, 
				new String[] {"Fragment", "Host plugin"}, 0); // Fragment is the default
		int result = dialog.open();
		return result == 0;
	}
	

}
