/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.alignment;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

/**
 * Toolitem controls to align the selected elements to the bottom
 * 
 * @author Orlandin Marco
 *
 */
public class BottomAlignmentContributionItem extends AbstractAlignmentContributionItem{
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);

		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setToolTipText(Messages.Align2BorderAction_align_to_bottom_tool_tip);
		button.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/align-band-bottom.gif"));
		button.setData(WIDGET_DATA_KEY, new Integer(PositionConstants.BOTTOM));
		button.addSelectionListener(alignButtonPressed);
		
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem alignToBottom = new ToolItem(parent, SWT.PUSH);
		alignToBottom.setToolTipText(Messages.Align2BorderAction_align_to_bottom_tool_tip);
		alignToBottom.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/align-band-bottom.gif"));
		alignToBottom.setData(WIDGET_DATA_KEY, new Integer(PositionConstants.BOTTOM));
		alignToBottom.addSelectionListener(alignButtonPressed);
		getToolItems().add(alignToBottom);
		
		return true;
	}
}
