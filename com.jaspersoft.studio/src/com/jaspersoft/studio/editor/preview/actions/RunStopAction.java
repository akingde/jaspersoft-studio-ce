/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions;

import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.context.AEditorContext;
import com.jaspersoft.studio.editor.preview.IRunReport;
import com.jaspersoft.studio.editor.preview.PreviewJRPrint;
import com.jaspersoft.studio.editor.preview.view.control.IReportRunner;
import com.jaspersoft.studio.editor.preview.view.control.ReportController;
import com.jaspersoft.studio.messages.Messages;

public class RunStopAction extends Action // implements IMenuCreator
{
	public static final String MODERUN_LOCAL = "RUNLOCAL";

	public static final String ID = "PREVIEWRELOADACTION"; //$NON-NLS-1$
	private PreviewJRPrint editor;

	public RunStopAction(PreviewJRPrint editor) {
		super();
		this.editor = editor;
		setId(ID);
		// setMenuCreator(this);
		setDescription(Messages.RunStopAction_runreport_desc);
		setToolTipText(Messages.RunStopAction_runreport_desc);
		ImageDescriptor imgd = JaspersoftStudioPlugin.getInstance()
				.getImageDescriptor("icons/resources/eclipse/start_task.gif");// $NON-NLS-1$
		setImageDescriptor(imgd);
		setDisabledImageDescriptor(imgd);
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNotRunning();
	}

	@Override
	public void run() {
		if (editor instanceof IRunReport)
			((IRunReport) editor).runReport();
	}

	private Menu listMenu;

	public void dispose() {
		if (listMenu != null)
			listMenu.dispose();
	}

	public Menu getMenu(Control parent) {
		if (listMenu != null)
			listMenu.dispose();
		listMenu = new Menu(parent);

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MenuItem menuItem = (MenuItem) e.getSource();
				menuItem.setSelection(true);
				if (editor instanceof IRunReport)
					((IRunReport) editor).setMode((String) menuItem.getData("run.key"));
				run();
			}
		};
		AEditorContext cntx = editor.getJrContext().getEditorContext();
		String defMode = cntx.getDefaultRunMode();
		if (cntx.isAllowOtherRunners()) {
			MenuItem m1 = new MenuItem(listMenu, SWT.PUSH);
			m1.setText("Run Report");
			m1.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/start_task.gif"));
			m1.addSelectionListener(listener);
			m1.setData("run.key", MODERUN_LOCAL);
			setupMenu(m1, defMode);
		}

		for (Map.Entry<String, IReportRunner> entry : ReportController.getRunners().entrySet()) {
			if (entry.getKey().equals(defMode)) {
				MenuItem m1 = new MenuItem(listMenu, SWT.RADIO);
				m1.setText(entry.getValue().getLabel());
				m1.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/start_task.gif"));
				m1.addSelectionListener(listener);
				m1.setData("run.key", entry.getKey());
				setupMenu(m1, defMode);
			}
		}
		if (listMenu.getItemCount() == 1)
			listMenu.getItems()[0].dispose();

		return listMenu;
	}

	private void setupMenu(MenuItem menuItem, String mode) {
		String key = (String) menuItem.getData("run.key");
		if (mode.equals(key)) {
			menuItem.setSelection(true);
			if (editor instanceof IRunReport)
				((IRunReport) editor).setMode(mode);
		}
	}

	public Menu getMenu(Menu parent) {
		return null;
	}
}
