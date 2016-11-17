/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.toolbar;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.PreviewJRPrint;
import com.jaspersoft.studio.editor.preview.actions.SwitchViewsAction;
import com.jaspersoft.studio.editor.preview.view.AViewsFactory;

public class TopToolBarManagerJRPrint extends ATopToolBarManager {

	public TopToolBarManagerJRPrint(PreviewJRPrint container, Composite parent) {
		super(container, parent);

	}

	protected SwitchViewsAction pvModeAction;

	public IContributionItem[] getContributions() {
		if (tbManager == null)
			return new IContributionItem[0];
		return tbManager.getItems();
	}

	protected void fillToolbar(IToolBarManager tbManager) {
		if (pvModeAction == null) {
			AViewsFactory viewFactory = container.getViewFactory();
			pvModeAction = new SwitchViewsAction(container.getRightContainer(), viewFactory.getLabel(container
					.getDefaultViewerKey()), true, viewFactory);
		}
		tbManager.add(pvModeAction);

		tbManager.add(new Separator());
	}

	/**
	 * Set the text of the action
	 */
	public void setActionText(String text) {
		pvModeAction.setText(text);
	}
}
