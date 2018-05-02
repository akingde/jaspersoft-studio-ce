/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.size;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.size.Size2BorderAction;
import com.jaspersoft.studio.messages.Messages;

/**
 * Toolitem to fit the width of the selected elements to the one of the parent
 * 
 * @author Orlandin Marco
 *
 */
public class FitWidthSizeContributionItem extends AbstractSizeContributionItem {
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		ToolItem sizeButton = new ToolItem(buttons, SWT.PUSH);
		sizeButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/size_to_control_width.gif"));
		sizeButton.setData(WIDGET_DATA_KEY, Size2BorderAction.WIDTH);
		sizeButton.setToolTipText(Messages.Size2BorderAction_fit_width_tool_tip);
		sizeButton.addSelectionListener(pushButtonPressed);
		
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem sizeButtonWidth = new ToolItem(parent, SWT.PUSH);;
		sizeButtonWidth.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/size_to_control_width.gif"));
		sizeButtonWidth.setData(WIDGET_DATA_KEY,Size2BorderAction.WIDTH);
		sizeButtonWidth.setToolTipText(Messages.Size2BorderAction_fit_width_tool_tip);
		sizeButtonWidth.addSelectionListener(pushButtonPressed);
		getToolItems().add(sizeButtonWidth);
		return true;
	}
	
}
