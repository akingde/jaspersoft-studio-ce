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

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.editor.preview.PreviewJRPrint;
import com.jaspersoft.studio.editor.preview.actions.ViewExecutionInfoAction;
import com.jaspersoft.studio.editor.preview.actions.ViewParametersAction;
import com.jaspersoft.studio.editor.preview.actions.ViewReportParametersAction;
import com.jaspersoft.studio.editor.preview.actions.ViewSortFieldsAction;
import com.jaspersoft.studio.swt.toolbar.ToolItemContribution;

public class LeftToolBarManager extends ATopToolBarManager {

	// private final class PinAction extends Action {
	// private PinAction() {
	// super();
	// setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseicons/icon_con_pin.png"));
	// setToolTipText("Pin Parameters Panel");
	// }
	//
	// @Override
	// public void run() {
	// container.setHideParameters(!container.isHideParameters());
	// }
	// }

	public LeftToolBarManager(PreviewJRPrint container, Composite parent) {
		super(container, parent);
	}

	private Label label;
	private Composite prmtbar;
	private ViewParametersAction vprmAction;
	private ViewReportParametersAction vprmrepAction;
	private ViewSortFieldsAction vsortAction;
	private ViewExecutionInfoAction vexecAction;

	@Override
	protected void createToolBar(Composite parent) {
		prmtbar = new Composite(parent, SWT.NONE);
		prmtbar.setLayout(new GridLayout(2, false));
		prmtbar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		label = new Label(prmtbar, SWT.NONE);
		label.setText("Parameters");
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		topToolBar = new ToolBar(prmtbar, SWT.FLAT | SWT.WRAP | SWT.RIGHT);

		tbManager = new ToolBarManager(topToolBar);

		fillToolbar(tbManager);

		refreshToolbar();
	}

	protected void fillToolbar(IToolBarManager tbManager) {
		PreviewContainer pvcont = (PreviewContainer) container;
		if (vprmAction == null)
			vprmAction = new ViewParametersAction(pvcont.getLeftContainer());
		tbManager.add(vprmAction);
		if (vprmrepAction == null)
			vprmrepAction = new ViewReportParametersAction(pvcont.getLeftContainer());
		tbManager.add(vprmrepAction);
		if (vsortAction == null)
			vsortAction = new ViewSortFieldsAction(pvcont.getLeftContainer());
		tbManager.add(vsortAction);
		if (vexecAction == null)
			vexecAction = new ViewExecutionInfoAction(pvcont);
		tbManager.add(vexecAction);

		ToolItemContribution titem = new ToolItemContribution("id", SWT.CHECK);
		tbManager.add(titem);
		
		tbManager.update(true);
		
		final ToolItem item = titem.getToolItem();
		item.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				container.setHideParameters(!container.isHideParameters());
			}
		});
		item.setImage(JaspersoftStudioPlugin.getImage("icons/eclipseicons/pin.png"));
		item.setToolTipText("Pin Parameters Panel");
		item.setSelection(container.isHideParameters());
	}

	public void setLabelText(String key) {
		label.setText(key);
		prmtbar.layout();
	}

}
