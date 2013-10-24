/*
 * Copyright (C) 2007 Alexander Bieber
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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import com.essiembre.eclipse.rbe.ui.editor.resources.NLResourceFactory;
import com.essiembre.eclipse.rbe.ui.editor.resources.PDEUtils;

/**
 * PropertiesFileCreator used when loaded resources from an nl structure in fragments.  
 * 
 * @author Alexander Bieber -- fleque [at] users.sourceforge [dot] net
 * @version $Author: fleque $ $Revision: 1.1 $ $Date: 2007/09/30 14:22:04 $
 */
public class FragmentNLPropertiesFileCreator extends NLPropertiesFileCreator {

	private IProject fragment;
	private String fragmentNlDir;
	private String hostNlDir;
	
	/**
	 * @param nlDir
	 * @param fileName
	 */
	public FragmentNLPropertiesFileCreator(IProject fragment, String fileName) {
		super(NLResourceFactory.lookupNLDir(fragment).toString(), fileName);
		this.fragment = fragment;
		this.fragmentNlDir = NLResourceFactory.lookupNLDir(fragment).getFullPath().toString();
		IProject host = PDEUtils.getFragmentHost(fragment);
		if (host != null) {
			this.hostNlDir = NLResourceFactory.lookupNLDir(host).getFullPath().toString();
		}
	}
	
	@Override
	protected IPath buildFilePath(Locale locale) throws CoreException {
		if (FragmentPropertiesFileCreator.shouldFileBeCreatedInFragment(fragment)) {
			setNlDir(fragmentNlDir);
		} else {
			setNlDir(hostNlDir);
		}
		return super.buildFilePath(locale);
	}

}
