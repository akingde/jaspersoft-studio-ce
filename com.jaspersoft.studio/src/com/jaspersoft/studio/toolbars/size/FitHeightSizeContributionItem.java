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
 * Toolitem to fit the height of the selected elements to the one of the parent
 * 
 * @author Orlandin Marco
 *
 */
public class FitHeightSizeContributionItem extends AbstractSizeContributionItem {
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		ToolItem sizeButton = new ToolItem(buttons, SWT.PUSH);
		sizeButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/size_to_control_height.gif"));
		sizeButton.setData(WIDGET_DATA_KEY, Size2BorderAction.HEIGHT);
		sizeButton.setToolTipText(Messages.Size2BorderAction_fit_height_tool_tip);
		sizeButton.addSelectionListener(pushButtonPressed);
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem sizeButtonHeight = new ToolItem(parent, SWT.PUSH);
		sizeButtonHeight.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/size_to_control_height.gif"));
		sizeButtonHeight.setData(WIDGET_DATA_KEY,Size2BorderAction.HEIGHT);
		sizeButtonHeight.setToolTipText(Messages.Size2BorderAction_fit_height_tool_tip);
		sizeButtonHeight.addSelectionListener(pushButtonPressed);
		getToolItems().add(sizeButtonHeight);
		
		return true;
	}
	
}
