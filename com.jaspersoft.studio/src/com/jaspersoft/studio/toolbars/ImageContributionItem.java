/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.image.ChangeImageExpression;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.image.MImage;

/**
 * Create the toolbar button to change the image expression
 * 
 * @author Orlandin Marco
 *
 */
public class ImageContributionItem extends CommonToolbarHandler{
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			List<Object> selection = getSelectionForType(MImage.class);
			if (selection.size() != 1)
				return;
			ChangeImageExpression.setImageExpression((MImage)selection.get(0));
		}
	};
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		ToolItem changeImage = new ToolItem(buttons, SWT.PUSH);
		changeImage.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/blue-folder-open-image.png")); //$NON-NLS-1$
		changeImage.setToolTipText(Messages.ImageContributionItem_actionName);
		changeImage.addSelectionListener(pushButtonPressed);
		
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		ToolItem changeImage = new ToolItem(parent, SWT.PUSH);
		changeImage.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/blue-folder-open-image.png")); //$NON-NLS-1$
		changeImage.setToolTipText(Messages.ImageContributionItem_actionName);
		changeImage.addSelectionListener(pushButtonPressed);
		getToolItems().add(changeImage);
		return true;
	}
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		List<Object> selection = getSelectionForType(MImage.class);
		return selection.size() == 1;
	}
}
