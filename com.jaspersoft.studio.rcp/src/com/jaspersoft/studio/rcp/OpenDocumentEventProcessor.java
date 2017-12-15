/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.rcp;

import java.util.ArrayList;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.utils.UIUtil;

/**
 * @author gtoffoli
 * 
 */

public class OpenDocumentEventProcessor implements Listener {
	private ArrayList<String> filesToOpen = new ArrayList<String>(1);

	public void handleEvent(Event event) {
		
		if (event.text != null)
			filesToOpen.add(event.text);
	}

	public void openFiles() {
		
		if (filesToOpen.isEmpty())
			return;

		String[] filePaths = filesToOpen
				.toArray(new String[filesToOpen.size()]);
		filesToOpen.clear();

		
		for (String path : filePaths)
		{
			System.out.println("Processing " + path);
			if (UIUtil.EditorExtension.hasValidExtension(path.toLowerCase()))
			{
				
				java.io.File file = new java.io.File(path);
				if (!file.exists()) continue;
				
					IFileStore fileStore =  EFS.getLocalFileSystem().getStore(new Path(file.getParent()));
					fileStore =  fileStore.getChild(file.getName());
					IFileInfo fetchInfo = fileStore.fetchInfo();
					if (!fetchInfo.isDirectory() && fetchInfo.exists()) {
						
						IWorkbenchWindow window=PlatformUI.getWorkbench().getActiveWorkbenchWindow();
						IWorkbenchPage page = window.getActivePage();
						
						
						try {
							IDE.openEditorOnFileStore(page, fileStore);
						} catch (PartInitException e) {
							
						}
					}				
				
//				IFile fileToBeOpened = (IFile) EFS.getLocalFileSystem().getStore(new Path(path));
//				IEditorInput editorInput = new FileEditorInput(fileToBeOpened);
//				
//				try {
//					IDE.openEditor(page, fileToBeOpened);
//					
//					IEditorPart  part = page.openEditor(editorInput, "com.jaspersoft.studio.editor.JrxmlEditor");
//					System.out.println(part);
//					
//				} catch (PartInitException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		}
	}
}
