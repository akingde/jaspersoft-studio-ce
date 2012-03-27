package com.jaspersoft.studio.data.designer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtils;

public class QueryStatus {
	private String msg;
	private Throwable t;
	private ToolItem msgItem;
	private ToolBar toolBar;

	public QueryStatus(Composite composite) {
		createStatusBar(composite);
	}

	public void showError(final Throwable t) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				msgItem.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/obj16/error_tsk.gif"));
				setMessage(t, t.getMessage(), true);
			}
		});
	}

	public void showWarning(final String msg) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				msgItem.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/obj16/warn_tsk.gif"));
				setMessage(null, msg, true);
			}
		});
	}

	public void showInfo(final String msg) {
		Display.getDefault().syncExec(new Runnable() {

			public void run() {
				msgItem.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/obj16/info_tsk.gif"));
				setMessage(null, msg, false);
			}
		});
	}

	protected void setMessage(final Throwable t, String message, boolean enabled) {
		this.t = t;
		msg = message;
		msgItem.setText(Misc.nvl(msg));
		msgItem.setEnabled(enabled);
		toolBar.setVisible(msg != null && !msg.trim().isEmpty());
	}

	protected void createStatusBar(final Composite comp) {
		toolBar = new ToolBar(comp, SWT.FLAT | SWT.WRAP | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));

		msgItem = new ToolItem(toolBar, SWT.PUSH);
		showInfo("Build a query for your report.");
		msgItem.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (t != null)
					UIUtils.showError(t);
				else {
					UIUtils.showWarning(msg);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}

}
