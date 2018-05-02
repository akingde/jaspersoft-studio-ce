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
 * Toolitem to move an element backward
 * 
 * @author Orlandin Marco
 *
 */
public class BackwardOrderContributionItem extends AbstractOrderContributionItem{
	
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		ToolItem moveButton = new ToolItem(buttons, SWT.PUSH);
		moveButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/send_backward.gif")); //$NON-NLS-1$
		moveButton.setData(WIDGET_DATA_KEY, ORDER_TYPE.BACKWARD);
		moveButton.setToolTipText(Messages.BringBackwardAction_send_backward_tool_tip);
		moveButton.addSelectionListener(pushButtonPressed);
		
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem moveBackward = new ToolItem(parent, SWT.PUSH);
		moveBackward.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/send_backward.gif")); //$NON-NLS-1$
		moveBackward.setData(WIDGET_DATA_KEY, ORDER_TYPE.BACKWARD);
		moveBackward.setToolTipText(Messages.BringBackwardAction_send_backward_tool_tip);
		moveBackward.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveBackward);
		
		return true;
	}
}
