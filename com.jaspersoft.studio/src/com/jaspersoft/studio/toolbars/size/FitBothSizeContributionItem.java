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
 * Toolitem to fit the size of the selected elements to the one of the parent
 * 
 * @author Orlandin Marco
 *
 */
public class FitBothSizeContributionItem extends AbstractSizeContributionItem{
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		ToolItem sizeButton = new ToolItem(buttons, SWT.PUSH);
		sizeButton.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/size_to_control.gif"));
		sizeButton.setData(WIDGET_DATA_KEY, Size2BorderAction.BOTH);
		sizeButton.setToolTipText(Messages.Size2BorderAction_fit_both_tool_tip);
		sizeButton.addSelectionListener(pushButtonPressed);
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem sizeButtonBoth = new ToolItem(parent, SWT.PUSH);;
		sizeButtonBoth.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/size_to_control.gif"));
		sizeButtonBoth.setData(WIDGET_DATA_KEY,Size2BorderAction.BOTH);
		sizeButtonBoth.setToolTipText(Messages.Size2BorderAction_fit_both_tool_tip);
		sizeButtonBoth.addSelectionListener(pushButtonPressed);
		getToolItems().add(sizeButtonBoth);
		return true;
	}
	
}
