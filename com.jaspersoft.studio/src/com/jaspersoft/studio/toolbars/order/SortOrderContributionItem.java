/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.order;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Toolitem sort some elements by name, work on parameters, fields and variables
 * 
 * @author Orlandin Marco
 *
 */
public class SortOrderContributionItem extends AbstractOrderContributionItem {
	
	private ToolItem sortElement;
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
	
		sortElement = new ToolItem(buttons, SWT.PUSH);
		sortElement.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/sort-alpha-asc.png")); //$NON-NLS-1$
		sortElement.addSelectionListener(pushButtonPressed);
		setSortButtonEnablement();

		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		sortElement = new ToolItem(parent, SWT.PUSH);
		sortElement.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/eclipseapps/elcl16/sort-alpha-asc.png")); //$NON-NLS-1$
		sortElement.addSelectionListener(pushButtonPressed);
		setSortButtonEnablement();
		
		return true;
	}
	
	private boolean setSortButtonEnablement() {
		setSortElementStatus(false, Messages.OrderContributionItem_disabledtooltip, null);
		if (areParameters()) {
			setSortElementStatus(true, Messages.OrderContributionItem_paramTooltip, ORDER_TYPE.SORT_PARAMETERS);
		} else {
			if (areVariables()) {
				setSortElementStatus(true, Messages.OrderContributionItem_varTooltip, ORDER_TYPE.SORT_VARIABLES);
			} else {
				if (areFields()) {
					setSortElementStatus(true, Messages.OrderContributionItem_fieldstooltip, ORDER_TYPE.SORT_FIELDS);
				} else {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean isVisible() {
		if (!checkWidgetVisible()) return false;
		
		List<Object> selection = getSelectionForType(MGraphicElement.class);
		if (selection.size() == 0){
			return setSortButtonEnablement();
		} else {
			setSortElementStatus(false, Messages.OrderContributionItem_disabledtooltip, null);
		}
		return true;
	}
	
	private void setSortElementStatus(boolean enablement, String tooltip, ORDER_TYPE type) {
		if (sortElement != null && !sortElement.isDisposed()) {
			sortElement.setEnabled(enablement);
			sortElement.setData(WIDGET_DATA_KEY, type);
			sortElement.setToolTipText(tooltip);
		}
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (sortElement != null){
			sortElement.dispose();
			sortElement = null;
		}
	}
}
