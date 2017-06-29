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
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.command.CreateBandGroupFooterCommand;

/**
 * Action used to add a new group footer band to the report
 * 
 * @author Orlandin Marco
 * 
 */
public class CreateGroupFooterAction extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "create_group_footer_band"; //$NON-NLS-1$

	/**
	 * Construct the action
	 * 
	 * @param part
	 *          the current editor
	 */
	public CreateGroupFooterAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	/**
	 * The action is enabled on the group footer elements
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MBandGroupFooter.class);
		return elements.size() == 1;
	}

	/**
	 * Return the command to add a new grouop footer band band
	 */
	@Override
	public Command createCommand() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MBandGroupFooter.class);
		if (elements.size() == 1){
			MBandGroupFooter footerNode = (MBandGroupFooter)elements.get(0);
			return new CreateBandGroupFooterCommand(footerNode);
		}
		return null;
	}
	
	@Override
	protected void execute(Command command) {
		super.execute(command);
		CreateBandGroupFooterCommand bandCommand = (CreateBandGroupFooterCommand)command;
		//set the selection on the new band
		setSelectionOnSiblingElement(bandCommand.getCreatedElement());
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateGroupFooterAction_actionName);
		setToolTipText(Messages.CreateGroupFooterAction_1);
		setId(CreateGroupFooterAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}
}
