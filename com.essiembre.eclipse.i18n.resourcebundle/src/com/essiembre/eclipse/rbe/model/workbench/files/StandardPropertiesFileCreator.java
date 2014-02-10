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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * Creates a standard properties file.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: essiembre $ $Revision: 1.1 $ $Date: 2005/07/30 22:10:55 $
 */
public class StandardPropertiesFileCreator extends PropertiesFileCreator {

    private String dir;
    private String baseFileName;
    private String extension;
    
    /**
     * Constructor.
     * @param dir directory in wich to create the file
     * @param baseFileName base name of file to create
     * @param extension file extension
     */
    public StandardPropertiesFileCreator(
            String dir, String baseFileName, String extension) {
        super();
        this.dir = dir;
        this.baseFileName = baseFileName;
        this.extension = extension;
    }

    /**
     * @see com.essiembre.eclipse.rbe.model.workbench.files.PropertiesFileCreator#buildFilePath(java.util.Locale)
     */
    protected IPath buildFilePath(Locale locale) {
        
        IPath path = new Path(dir);
        path = path.append(baseFileName);
        if (locale != null) {
            path = new Path(
                    path.toString() + "_" + locale.toString()); //$NON-NLS-1$
        }
        return path.addFileExtension(extension);
    }


}
