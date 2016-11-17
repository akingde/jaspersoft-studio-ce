/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.band.MBandGroupHeader;
import com.jaspersoft.studio.model.band.command.CreateBandGroupHeaderCommand;

/**
 * Action used to add a new group header band to the report
 * 
 * @author Orlandin Marco
 * 
 */
public class CreateGroupHeaderAction extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "create_group_header_band"; //$NON-NLS-1$

	/**
	 * Construct the action
	 * 
	 * @param part
	 *          the current editor
	 */
	public CreateGroupHeaderAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	/**
	 * The action is enabled on the elements inside a detail band
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MBandGroupHeader.class);
		return elements.size() == 1;
	}

	/**
	 * Return the command to add a new detail band
	 */
	@Override
	public Command createCommand() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MBandGroupHeader.class);
		if (elements.size() == 1){
			MBandGroupHeader headerNode = (MBandGroupHeader)elements.get(0);
			return new CreateBandGroupHeaderCommand(headerNode);
		}
		return null;
	}
	
	@Override
	protected void execute(Command command) {
		super.execute(command);
		CreateBandGroupHeaderCommand bandCommand = (CreateBandGroupHeaderCommand)command;
		//set the selection on the new band
		setSelectionOnSiblingElement(bandCommand.getCreatedElement());
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateGroupHeaderAction_actionName);
		setToolTipText(Messages.CreateGroupHeaderAction_actionTooltip);
		setId(CreateGroupHeaderAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}
}
