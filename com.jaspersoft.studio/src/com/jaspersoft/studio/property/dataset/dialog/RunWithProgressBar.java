/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.dialog;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ProgressBar;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class RunWithProgressBar implements IRunnableContext {

	private ProgressBar pb;

	public RunWithProgressBar(Composite comp) {
		pb = new ProgressBar(comp, SWT.HORIZONTAL | SWT.INDETERMINATE);
		// pb.setMinimum(0);
		// pb.setMaximum(100);
		// pb.setSelection(50);
		// pb.setBounds(10, 10, 200, 30);
		GridData gd = new GridData();
		gd.widthHint = 100;
		gd.heightHint = 20;
		pb.setLayoutData(gd);
		pb.setVisible(false);
	}

	public ProgressBar getProgressBar() {
		return pb;
	}

	public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable)
			throws InvocationTargetException, InterruptedException {
		IProgressMonitor monitor = getProgressMonitor(cancelable);
		if (pb != null && !pb.isDisposed())
			ModalContext.run(runnable, true, monitor, UIUtils.getDisplay());
	}

	public IProgressMonitor getProgressMonitor(boolean cancelable) {
		return new ProgressBarMonitor(pb, cancelable);
	}

	class ProgressBarMonitor implements IProgressMonitor {
		private ProgressBar progressBar;
		private boolean cancelled;
		private boolean cancelable;
		private Button bCancel;

		/**
		 * Create a new ProgressBarMonitor.
		 * 
		 * @param progressBar
		 *            the ProgressBar control
		 */
		public ProgressBarMonitor(ProgressBar progressBar, boolean cancelable) {
			this.progressBar = progressBar;
			this.cancelable = cancelable;
		}

		public void beginTask(String name, int totalWork) {
			cancelled = false;
			if (!progressBar.isDisposed()) {
				progressBar.setSelection(0);
				progressBar.setMinimum(0);
				progressBar.setMaximum(totalWork);
				progressBar.setVisible(true);
			}

			if (cancelable && (bCancel == null || bCancel.isDisposed())) {
				Composite cmp = progressBar.getParent();
				for (Control c : cmp.getChildren())
					if (c instanceof Button)
						c.dispose();

				bCancel = new Button(cmp, SWT.FLAT | SWT.PUSH);
				bCancel.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/delete_style.gif"));
				bCancel.addSelectionListener(new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						setCanceled(true);
					}
				});
				cmp.layout();
			}
		}

		public void setTaskName(String name) {
			// nothing to do
		}

		public void subTask(String name) {
			// nothing to do
		}

		public void internalWorked(double work) {
		}

		public void worked(int work) {
			if (!progressBar.isDisposed()) {
				progressBar.setSelection(progressBar.getSelection() + work);
			}
		}

		public boolean isCanceled() {
			return cancelled;
		}

		public void setCanceled(boolean value) {
			cancelled = value;
		}

		public void done() {
			if (bCancel != null && !bCancel.isDisposed()) {
				bCancel.dispose();
				bCancel = null;
			}
			if (!progressBar.isDisposed()) {
				progressBar.setVisible(false);
			}
		}
	}
}
