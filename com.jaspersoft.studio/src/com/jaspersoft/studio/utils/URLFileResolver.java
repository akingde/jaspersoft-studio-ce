/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.io.File;
import java.net.URL;
import java.util.List;

import net.sf.jasperreports.engine.util.SimpleFileResolver;

/**
 * Extends the default SimpleFileResolver by adding 
 * the decode of the path as url, when the simple file
 * resolver can't resolve it
 * 
 * @author Orlandin Marco
 *
 */
public class URLFileResolver extends SimpleFileResolver {

	public URLFileResolver(File parentFolder) {
		super(parentFolder);
	}
	
	public URLFileResolver(List<File> parentFolders) {
		super(parentFolders);
	}

	@Override
	public File resolveFile(String fileName) {
		File fileToBeOpened = super.resolveFile(fileName);
		//The simple file resolver can't resolve the filename, try to check if it's an url
		if (fileToBeOpened == null){
			try {
				URL fileURL = new URL(fileName);
				File f = new File(fileURL.toURI());
				if (f.exists()) fileToBeOpened = f;
			} catch (Exception e) {}
		}
		return fileToBeOpened;
	}
	
	
}
