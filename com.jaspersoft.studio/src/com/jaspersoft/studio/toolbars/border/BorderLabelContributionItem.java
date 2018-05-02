/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.border;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;


/**
 * Label for the {@link BorderContributionItem}. This component should. It should also be placed along
 * with the {@link BorderContributionItem} because the toolbar is unable to set the height (handled natively
 * by the os) without a static element. In this way the this label will provide the height for the toolbar.
 * 
 * @author Orlandin Marco
 *
 */
public class BorderLabelContributionItem extends CommonToolbarHandler {
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		// FIXME - Temporary solution when solving Bugzilla #44189
		// It appears that in Windows with Eclipse Mars it's not correctly
		// Creating a possible ToolItem separator containing a composite/label.
		ToolItem tiBorderLbl = new ToolItem(parent,SWT.PUSH);
		tiBorderLbl.setText(Messages.ATableComboContribution_presets_label);
		getToolItems().add(tiBorderLbl);
		
		return true;
	}
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		List<Object> selection = getSelectionForType(MGraphicElementLineBox.class);
		return !selection.isEmpty();
	}
}
