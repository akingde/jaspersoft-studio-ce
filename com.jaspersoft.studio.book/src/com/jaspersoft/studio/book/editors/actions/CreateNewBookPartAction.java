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
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignPart;

import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.book.JRBookActivator;
import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.book.model.commands.CreatePartCommand;
import com.jaspersoft.studio.editor.outline.actions.ACreateAndSelectAction;

/**
 * Add to the section a book part from a file in the filesystem
 * 
 * @author Marco
 *
 */
public class CreateNewBookPartAction extends ACreateAndSelectAction {

	public static final String ID = "create_book_part"; //$NON-NLS-1$
	
	public CreateNewBookPartAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateNewBookPartAction_actionTitle);
		setToolTipText(Messages.CreateNewBookPartAction_actionTooltip);
		setId(ID);
		setImageDescriptor(JRBookActivator.getDefault().getImageDescriptor("/icons/add.png")); //$NON-NLS-1$
		setEnabled(false);
	}
	
	@Override
	protected boolean calculateEnabled() {
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if(obj instanceof AbstractEditPart && ((AbstractEditPart)obj).getModel() instanceof MReportPartContainer) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void run() {
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if(obj instanceof AbstractEditPart) {
				Object model = ((AbstractEditPart)obj).getModel();
				if (model instanceof MReportPartContainer) {
					MReportPartContainer container = (MReportPartContainer) model;
					List<String> reportFiles = selectReportFiles();
					if(!reportFiles.isEmpty()){
						for(String f : reportFiles){
							JRDesignPart newBookPart = MReportPart.createJRElement(new JRDesignExpression("\""+f+"\""));
							getCommandStack().execute(new CreatePartCommand(container, newBookPart, -1));
						}
					}
				}
			}
		}
	}
	
	/**
	 * Open the file dialog for jrxml files and return 
	 * the absolute path of each selected file
	 * 
	 * @return not null list of the selected files absolute path
	 */
	private List<String> selectReportFiles(){
		FileDialog fd = new FileDialog(UIUtils.getShell(), SWT.OPEN | SWT.MULTI);
		fd.setText(Messages.common_open);
		String[] filterExt = { "*.jrxml" }; //$NON-NLS-1$ 
		fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		List<String> files = new ArrayList<String>();
		if (selected != null) {
			String[] fileNames = fd.getFileNames();
			File parentFolder = new File(selected).getParentFile();
			for(String fileName : fileNames){
				File actualFile = new File(parentFolder, fileName);
				selected = actualFile.getAbsolutePath();
				if (actualFile.isFile()){
					files.add(selected);
				}
			}
		}
		return files;
	}

}
