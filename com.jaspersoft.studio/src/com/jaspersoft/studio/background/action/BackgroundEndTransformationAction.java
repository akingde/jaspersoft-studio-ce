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
package com.jaspersoft.studio.background.action;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.background.MBackgrounImage;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * Action to set the current editor in background not editable mode.
 * 
 * @author Orlandin Marco
 * 
 */
public class BackgroundEndTransformationAction extends ACachedSelectionAction {
  
  public static final String ID = "BackgroundEndTransofrmation"; //$NON-NLS-1$
  
  /**
   * Command used to change the status of the editor editable background flag.
   * It can be undone. 
   * 
   * @author Orlandin Marco
   *
   */
  private class ChangeTransformationCommand extends Command{
  	
  	@Override
  	public void execute() {
  		IEditorPart currentEditor = SelectionHelper.getActiveJRXMLEditor();
  		if (currentEditor instanceof JrxmlEditor){
  			JrxmlEditor editor = (JrxmlEditor) currentEditor;
  			ReportContainer currentContainer =  editor.getReportContainer();
  			currentContainer.setBackgroundImageEditable(!currentContainer.isBackgroundImageEditable());
  			if (currentContainer.isBackgroundImageEditable()) SelectionHelper.setBackgroundSelected();
  			else SelectionHelper.deselectBackground();
  		}
  	}
  	
  	@Override
  	public void undo() {
  		execute();
  	}
  	
  }
  
	public BackgroundEndTransformationAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}
	
	@Override
	protected void init() {
		super.init();
		setText(Messages.MBackgrounImage_labelEndTransformation);
		setId(ID);
		setEnabled(false);
	}

	@Override
	protected Command createCommand() {
		List<Object> background = editor.getSelectionCache().getSelectionModelForType(MBackgrounImage.class);
		if (background.isEmpty()) return null;
		return new ChangeTransformationCommand();
	}
}

