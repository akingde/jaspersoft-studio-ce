/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.toolbar;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.widget.DatasourceComboItem;
import com.jaspersoft.studio.data.widget.IDataAdapterRunnable;
import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.actions.RunStopAction;
import com.jaspersoft.studio.editor.preview.actions.ViewExecutionInfoAction;
import com.jaspersoft.studio.editor.preview.actions.ViewParametersAction;
import com.jaspersoft.studio.editor.preview.actions.ViewReportParametersAction;
import com.jaspersoft.studio.editor.preview.actions.ViewSortFieldsAction;

public class LeftToolBarManager {
	private PreviewContainer container;

	public LeftToolBarManager(PreviewContainer container, Composite parent) {
		this.container = container;
		createToolBar(parent);
	}

	private IToolBarManager tbManager;
	private RunStopAction reloadAction;
	private DatasourceComboItem dataSourceWidget;
	private ToolBar toolBar;
	private Label label;
	private Composite prmtbar;
	private ViewParametersAction vprmAction;
	private ViewReportParametersAction vprmrepAction;
	private ViewSortFieldsAction vsortAction;
	private ViewExecutionInfoAction vexecAction;

	private void createToolBar(Composite parent) {
		prmtbar = new Composite(parent, SWT.NONE);
		prmtbar.setLayout(new GridLayout(2, false));
		prmtbar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(prmtbar, SWT.NONE);
		label.setText("Parameters");
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		toolBar = new ToolBar(prmtbar, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		// toolBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END));

		tbManager = new ToolBarManager(toolBar);

		fillToolbar(tbManager);

		refreshToolbar();
	}

	private void fillToolbar(IToolBarManager tbManager) {
		tbManager.removeAll();
		if (vprmAction == null)
			vprmAction = new ViewParametersAction(container.getLeftContainer());
		tbManager.add(vprmAction);
		if (vprmrepAction == null)
			vprmrepAction = new ViewReportParametersAction(container.getLeftContainer());
		tbManager.add(vprmrepAction);
		if (vsortAction == null)
			vsortAction = new ViewSortFieldsAction(container.getLeftContainer());
		tbManager.add(vsortAction);
		if (vexecAction == null)
			vexecAction = new ViewExecutionInfoAction(container.getLeftContainer());
		tbManager.add(vexecAction);

		tbManager.add(new Separator());

		if (dataSourceWidget == null)
			dataSourceWidget = new DatasourceComboItem((IDataAdapterRunnable) container);
		tbManager.add(dataSourceWidget);

		if (reloadAction == null)
			reloadAction = new RunStopAction((IDataAdapterRunnable) container);
		tbManager.add(reloadAction);
	}

	public void setLabelText(String key) {
		label.setText(key);
		prmtbar.layout();
	}

	public void refreshToolbar() {
		tbManager.update(true);

		toolBar.pack();
	}

	public void setEnabled(boolean enabled) {
		for (IContributionItem ti : tbManager.getItems()) {
			if (ti instanceof ToolItem)
				((ToolItem) ti).setEnabled(enabled);
			else if (ti instanceof ActionContributionItem)
				((ActionContributionItem) ti).getAction().setEnabled(enabled);
			else if (ti instanceof DatasourceComboItem)
				((DatasourceComboItem) ti).setEnabled(enabled);
		}
		tbManager.update(true);
	}

	public DatasourceComboItem getDataSourceWidget() {
		return dataSourceWidget;
	}

	public void setDataAdapters(String strda) {
		dataSourceWidget.setSelected(DataAdapterManager.findDataAdapter(strda));
	}
}
