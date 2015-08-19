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
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.command.CreateBandDetailCommand;

/**
 * Action used to add a new detail band to the report
 * 
 * @author Orlandin Marco
 *
 */
public class CreateDetailBandAction extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "create_detail_band"; //$NON-NLS-1$

	/**
	 * Construct the action
	 * 
	 * @param part the current editor
	 */
	public CreateDetailBandAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	/**
	 * The action is enabled only if there is a single band selected and that band
	 * is a detail
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MBand.class);
		return (elements.size() == 1 && ((MBand)elements.get(0)).getBandType() == BandTypeEnum.DETAIL);
	}

	/**
	 * Return the command to add a new detail band
	 */
	@Override
	public Command createCommand() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MBand.class);
		return new CreateBandDetailCommand((MBand)elements.get(0), new MBand());
	}
	
	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateDetailBandAction_actionName);
		setToolTipText(Messages.CreateDetailBandAction_actionTooltip);
		setId(CreateDetailBandAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}
}
