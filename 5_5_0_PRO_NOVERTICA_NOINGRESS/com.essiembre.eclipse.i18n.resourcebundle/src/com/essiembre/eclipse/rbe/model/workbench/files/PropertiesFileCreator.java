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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import com.essiembre.eclipse.rbe.model.bundle.PropertiesGenerator;
import com.essiembre.eclipse.rbe.model.workbench.RBEPreferences;

/**
 * Creates a properties file.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: essiembre $ $Revision: 1.3 $ $Date: 2007/02/05 04:37:31 $
 */
public abstract class PropertiesFileCreator {

    /**
     * Creates a propertiles file.
     * @param locale locale representing properties file
     * @return the properties file
     * @throws CoreException problem creating file
     * @throws IOException problem creating file
     */
    public IFile createPropertiesFile(Locale locale)
            throws CoreException, IOException {
        IPath filePath = buildFilePath(locale);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IFile file = root.getFile(filePath);
        if (file.exists()) {
            //TODO internationalize.
            throw new IOException(
                    "File already exists: " + file.getName()); //$NON-NLS-1$
        }
        String contents = ""; //$NON-NLS-1$
        if (RBEPreferences.getShowGenerator()) {
            contents = PropertiesGenerator.GENERATED_BY;
        }
        InputStream stream = 
            new ByteArrayInputStream(contents.getBytes());
        file.create(stream, true, null);
        stream.close();
        return file;
    }
    
    protected abstract IPath buildFilePath(Locale locale) throws CoreException;
}
