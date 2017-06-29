/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.selector;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class SelectServerWizard extends Wizard {
	private MServerProfile value;

	protected SelectServerWizard() {
		super();
		setWindowTitle("JasperReports Servers");
		setNeedsProgressMonitor(true);
	}

	class Page extends WizardPage {

		protected Page() {
			super("serverpage");
			setTitle("Select a JasperReports Server");
			setDescription(
					"There is no JasperReports Server connection for this report. Please select one to continue.");
		}

		@Override
		public void createControl(Composite parent) {
			Composite cmp = new Composite(parent, SWT.NONE);
			cmp.setLayout(new GridLayout());
			setControl(cmp);

			final TableViewer tv = new TableViewer(cmp,
					SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
			GridData gd = new GridData(GridData.FILL_BOTH);
			gd.widthHint = 300;
			gd.heightHint = 300;
			tv.getTable().setLayoutData(gd);
			tv.getTable().setHeaderVisible(false);
			tv.getTable().setLinesVisible(false);
			tv.setContentProvider(ArrayContentProvider.getInstance());
			tv.setLabelProvider(new ReportTreeLabelProvider());
			ColumnViewerToolTipSupport.enableFor(tv, ToolTip.NO_RECREATE);

			tv.setInput(ServerManager.getServerProfiles());

			tv.addSelectionChangedListener(new ISelectionChangedListener() {

				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					value = (MServerProfile) ((StructuredSelection) tv.getSelection()).getFirstElement();
				}
			});
			tv.getTable().select(0);
			value = (MServerProfile) ((StructuredSelection) tv.getSelection()).getFirstElement();
		}

	}

	@Override
	public void addPages() {
		addPage(new Page());
	}

	public MServerProfile getValue() {
		return value;
	}

	@Override
	public boolean performFinish() {
		try {
			if (value != null)
				getContainer().run(false, true, new IRunnableWithProgress() {

					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						monitor.beginTask("Testing connection", IProgressMonitor.UNKNOWN);
						try {
							IConnection c = value.getWsClient(monitor);
							if (c == null)
								throw new Exception("Could not connect to this server");
						} catch (Exception e) {
							throw new InvocationTargetException(e);
						}
					}
				});
		} catch (InvocationTargetException e) {
			UIUtils.showError(e.getCause());
			return false;
		} catch (InterruptedException e) {
			UIUtils.showError(e);
			return false;
		}
		return true;
	}
}
