
/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/package com.jaspersoft.studio.book.gallery.implementations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.jaspersoft.studio.book.gallery.interfaces.IElementOpener;
import com.jaspersoft.studio.book.gallery.interfaces.IGalleryElement;
import com.jaspersoft.studio.book.messages.Messages;

/**
 * Opener to allow to add elements to a gallery by choosing them
 * from a file dialog
 * 
 * @author Orlandin Marco
 *
 */
public class PageOpener implements IElementOpener {

	/**
	 * Action on the contextual menu
	 */
	@Override
	public String getActionText() {
		return Messages.PageOpener_actionName;
	}

	/**
	 * Open a file dialog and allow to choose any number of jrxml files
	 * and create the gallery elements for them.
	 */
	@Override
	public IGalleryElement[] openResources() {
		FileDialog fd = new FileDialog(UIUtils.getShell(), SWT.OPEN | SWT.MULTI);
		fd.setText(Messages.PageOpener_dialogTitle);
		String[] filterExt = { "*.jrxml" }; //$NON-NLS-1$ 
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		List<IGalleryElement> elementsToCreate = new ArrayList<IGalleryElement>();
		if (selected != null) {
			String[] fileNames = fd.getFileNames();
			File parentFolder = new File(selected).getParentFile();
			for(String fileName : fileNames){
				File actualFile = new File(parentFolder, fileName);
				selected = actualFile.getAbsolutePath();
				if (actualFile.isFile()){
					PageElement selectedContainer = new PageElement(selected);
					elementsToCreate.add(selectedContainer);
				}
			}
		}
		return elementsToCreate.toArray(new IGalleryElement[elementsToCreate.size()]);
	}

}
