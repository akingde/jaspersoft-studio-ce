/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.order;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

/**
 * Toolitem to move an element forward
 * 
 * @author Orlandin Marco
 *
 */
public class ForwardOrderContributionItem extends AbstractOrderContributionItem{
	
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		ToolItem moveButton = new ToolItem(buttons, SWT.PUSH);
		moveButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/bring_forward.gif")); //$NON-NLS-1$
		moveButton.setData(WIDGET_DATA_KEY, ORDER_TYPE.FORWARD);
		moveButton.setToolTipText(Messages.BringForwardAction_bring_forward_tool_tip);
		moveButton.addSelectionListener(pushButtonPressed);
		
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem moveForward = new ToolItem(parent, SWT.PUSH);
		moveForward.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/bring_forward.gif")); //$NON-NLS-1$
		moveForward.setData(WIDGET_DATA_KEY, ORDER_TYPE.FORWARD);
		moveForward.setToolTipText(Messages.BringForwardAction_bring_forward_tool_tip);
		moveForward.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveForward);
		
		return true;
	}
}
