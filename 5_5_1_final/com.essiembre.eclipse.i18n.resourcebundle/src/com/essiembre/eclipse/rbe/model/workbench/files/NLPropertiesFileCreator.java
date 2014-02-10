/*
 * Copyright (C) 2003, 2004  Pascal Essiembre, Essiembre Consultant Inc.
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

import java.util.Locale;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * Creates a properties file under an "NL" structure.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: fleque $ $Revision: 1.3 $ $Date: 2007/09/30 14:22:04 $
 */
public class NLPropertiesFileCreator extends PropertiesFileCreator {

    private String nlDir;
    private String fileName;
    
    /**
     * Constructor.
     * @param nlDir NL directory name
     * @param fileName file name
     */
    public NLPropertiesFileCreator(String nlDir, String fileName) {
        super();
        this.nlDir = nlDir;
        this.fileName = fileName;
    }

    /**
     * @return The currently set nlDir.
     */
    protected String getNlDir() {
    	return nlDir;
    }
    /**
     * Set the nlDir.
     * @param nlDir The nlDir to set.
     */
    protected void setNlDir(String nlDir) {
		this.nlDir = nlDir;
	}
    
    /**
     * @throws CoreException
     * @see com.essiembre.eclipse.rbe.model.workbench.files.PropertiesFileCreator#buildFilePath(java.util.Locale)
     */
    protected IPath buildFilePath(Locale locale) throws CoreException {
        String folderPath = ""; //$NON-NLS-1$
        IWorkspaceRoot root = 
                ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(getNlDir());
        IContainer container = (IContainer) resource;

        if (locale != null) {
            if (locale.getLanguage().length() > 0) {
                folderPath += locale.getLanguage() + "/";  //$NON-NLS-1$
                IFolder folder = container.getFolder(
                        new Path(folderPath));
                if (!folder.exists()) {
                    folder.create(true, true, null);
                }
            }
            if (locale.getCountry().length() > 0) {
                folderPath += locale.getCountry() + "/";  //$NON-NLS-1$
                IFolder folder = container.getFolder(
                        new Path(folderPath));
                if (!folder.exists()) {
                    folder.create(true, true, null);
                }
            }
            if (locale.getVariant().length() > 0) {
                folderPath += locale.getVariant() + "/";  //$NON-NLS-1$
                IFolder folder = container.getFolder(
                        new Path(folderPath));
                if (!folder.exists()) {
                    folder.create(true, true, null);
                }
            }
            folderPath = getNlDir() + "/" + folderPath; //$NON-NLS-1$
        } else {
            folderPath = getNlDir().substring(
                    0, getNlDir().length() - "/nl".length()) //$NON-NLS-1$
                  + "/" + folderPath; //$NON-NLS-1$
        }
        return new Path(folderPath + fileName);
    }
}
