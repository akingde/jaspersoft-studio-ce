/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background.action;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.background.MBackgrounImage;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Action to enable or disable the fit width flag on the background element.
 * This is a checkbox action, so the status is shown directly on the menu
 * 
 * @author Orlandin Marco
 * 
 */
public class BackgroundKeepRatioAction extends ACachedSelectionAction {
  
  public static final String ID = "BackgroundKeepRatio"; //$NON-NLS-1$
  
	public BackgroundKeepRatioAction(IWorkbenchPart part) {
		super(part, IAction.AS_CHECK_BOX);
		setLazyEnablementCalculation(true);
	}
	
	@Override
	protected void init() {
		super.init();
		setText(Messages.MBackgrounImage_labelKeepRatio);
		setId(ID);
		setEnabled(false);
	}

	@Override
	protected Command createCommand() {
		List<Object> background = editor.getSelectionCache().getSelectionModelForType(MBackgrounImage.class);
		if (background.isEmpty()) return null;
		MBackgrounImage backgroundModel = (MBackgrounImage)background.get(0);
		
		boolean isKeepRatio = backgroundModel.isKeepRatio();
		
		SetValueCommand command = new SetValueCommand();
		command.setTarget(backgroundModel);
		command.setPropertyId(MBackgrounImage.PROPERTY_KEEP_RATIO);
		command.setPropertyValue(!isKeepRatio);
		
		return command;
	}
	
	@Override
	public void run() {
		super.run();
		//Since the command change without a selection we need to refresh it manually
		fresh = false;
		calculateEnabled();
		//allow to refresh the check state of the command after the command was executed
		firePropertyChange(new PropertyChangeEvent(this, IAction.CHECKED, false, true));
	}
	
	/**
	 * Since this is a checkbox this one return the current if the action is actually 
	 * checked (if the image must keep it's ratio) or not.
	 */
	@Override
	public boolean isChecked() {
		List<Object> background = editor.getSelectionCache().getSelectionModelForType(MBackgrounImage.class);
		if (background.isEmpty()) return false;
		MBackgrounImage backgroundModel = (MBackgrounImage)background.get(0);
		return backgroundModel.isKeepRatio();
	}
}

