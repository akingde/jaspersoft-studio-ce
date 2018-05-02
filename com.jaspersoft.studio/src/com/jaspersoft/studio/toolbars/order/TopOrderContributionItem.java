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
 * Toolitem to send an element to the top
 * 
 * @author Orlandin Marco
 *
 */
public class TopOrderContributionItem extends AbstractOrderContributionItem {
	
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		ToolItem moveButton = new ToolItem(buttons, SWT.PUSH);
		moveButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/bring_to_front.gif")); //$NON-NLS-1$
		moveButton.setData(WIDGET_DATA_KEY, ORDER_TYPE.TOP);
		moveButton.setToolTipText(Messages.BringToFrontAction_bring_to_front_tool_tip);
		moveButton.addSelectionListener(pushButtonPressed);
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem moveTop = new ToolItem(parent, SWT.PUSH);
		moveTop.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/bring_to_front.gif")); //$NON-NLS-1$
		moveTop.setData(WIDGET_DATA_KEY, ORDER_TYPE.TOP);
		moveTop.setToolTipText(Messages.BringToFrontAction_bring_to_front_tool_tip);
		moveTop.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveTop);
		
		return true;
	}
}
