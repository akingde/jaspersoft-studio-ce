/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.alignment;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

/**
 * Toolitem to join vertically the selected elements
 * 
 * @author Orlandin Marco
 *
 */
public class JoinRightAlignmentContributionItem extends AbstractAlignmentContributionItem{
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setToolTipText(Messages.JoinRightAction_actionDescription);
		button.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/joinright.png"));
		button.setData(WIDGET_DATA_KEY, JOIN_DIRECTION.RIGHT);
		button.addSelectionListener(joinButtonPressed);
		
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem joinRight = new ToolItem(parent, SWT.PUSH);
		joinRight.setToolTipText(Messages.JoinRightAction_actionDescription);
		joinRight.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/joinright.png"));
		joinRight.setData(WIDGET_DATA_KEY, JOIN_DIRECTION.RIGHT);
		joinRight.addSelectionListener(joinButtonPressed);
		getToolItems().add(joinRight);
		
		return true;
	}
}
