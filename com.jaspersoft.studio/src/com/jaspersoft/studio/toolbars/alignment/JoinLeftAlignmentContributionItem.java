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
 * Toolitem to join horizontally the selected elements
 * 
 * @author Orlandin Marco
 *
 */
public class JoinLeftAlignmentContributionItem extends AbstractAlignmentContributionItem{
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setToolTipText(Messages.JoinLeftAction_actionDescription);
		button.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/joinleft.png"));
		button.setData(WIDGET_DATA_KEY, JOIN_DIRECTION.LEFT);
		button.addSelectionListener(joinButtonPressed);
		
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem joinLeft = new ToolItem(parent, SWT.PUSH);
		joinLeft.setToolTipText(Messages.JoinLeftAction_actionDescription);
		joinLeft.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/joinleft.png"));
		joinLeft.setData(WIDGET_DATA_KEY, JOIN_DIRECTION.LEFT);
		joinLeft.addSelectionListener(joinButtonPressed);
		getToolItems().add(joinLeft);
		
		return true;
	}
}
