/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.toolbar;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.services.IDisposable;

import com.jaspersoft.studio.data.widget.DatasourceComboItem;
import com.jaspersoft.studio.editor.preview.PreviewJRPrint;
import com.jaspersoft.studio.editor.preview.actions.SwitchViewsAction;
import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.editor.preview.view.report.swt.action.AReportAction;

public class TopToolBarManagerJRPrint {
	protected PreviewJRPrint container;

	public TopToolBarManagerJRPrint(PreviewJRPrint container, Composite parent) {
		this.container = container;
		createToolBar(parent);
	}

	protected IToolBarManager tbManager;
	protected SwitchViewsAction pvModeAction;
	private ToolBar topToolBar;

	private void createToolBar(Composite parent) {
		Composite prmtbar = new Composite(parent, SWT.NONE);
		prmtbar.setLayout(new GridLayout());
		prmtbar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		topToolBar = new ToolBar(prmtbar, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		tbManager = new ToolBarManager(topToolBar);

		fillToolbar(tbManager);

		refreshToolbar();

	}

	protected void fillToolbar(IToolBarManager tbManager) {
		removeAll();

		if (pvModeAction == null)
			pvModeAction = new SwitchViewsAction(container.getRightContainer(), "Java", true);
		tbManager.add(pvModeAction);

		tbManager.add(new Separator());
	}

	public void removeAll() {
		for (IContributionItem it : tbManager.getItems()) {
			if (it instanceof ActionContributionItem && ((ActionContributionItem) it).getAction() instanceof IDisposable)
				((IDisposable) ((ActionContributionItem) it).getAction()).dispose();
		}
		tbManager.removeAll();
	}

	public void refreshToolbar() {
		tbManager.update(true);
		topToolBar.pack();
	}

	public void contributeItems(APreview contributor) {
		fillToolbar(tbManager);
		contributor.contribute2ToolBar(tbManager);
		refreshToolbar();
	}

	public void setFocus() {
	}

	public void setEnabled(boolean enabled) {
		for (IContributionItem ti : tbManager.getItems()) {
			if (ti instanceof ToolItem)
				((ToolItem) ti).setEnabled(enabled);
			else if (ti instanceof ActionContributionItem) {
				IAction action = ((ActionContributionItem) ti).getAction();
				if (action instanceof AReportAction && enabled)
					action.setEnabled(((AReportAction) action).isActionEnabled());
				else
					action.setEnabled(enabled);
			} else if (ti instanceof DatasourceComboItem)
				((DatasourceComboItem) ti).setEnabled(enabled);
		}
		refreshToolbar();
	}
}
