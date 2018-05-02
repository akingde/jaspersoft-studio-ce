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
 * Toolitem to send an element to the bottom
 * 
 * @author Orlandin Marco
 *
 */
public class BottomOrderContributionItem extends AbstractOrderContributionItem {
	
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
	
		ToolItem moveButton = new ToolItem(buttons, SWT.PUSH);
		moveButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/send_to_back.gif")); //$NON-NLS-1$
		moveButton.setData(WIDGET_DATA_KEY, ORDER_TYPE.BOTTOM);
		moveButton.setToolTipText(Messages.BringToBackAction_send_to_back_tool_tip);
		moveButton.addSelectionListener(pushButtonPressed);

		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem moveBottom = new ToolItem(parent, SWT.PUSH);
		moveBottom.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/send_to_back.gif")); //$NON-NLS-1$
		moveBottom.setData(WIDGET_DATA_KEY, ORDER_TYPE.BOTTOM);
		moveBottom.setToolTipText(Messages.BringToBackAction_send_to_back_tool_tip);
		moveBottom.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveBottom);
		
		return true;
	}
}
