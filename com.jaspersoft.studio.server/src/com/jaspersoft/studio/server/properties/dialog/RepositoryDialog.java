/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.properties.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.server.ServerProvider;
import com.jaspersoft.studio.server.model.MResource;

public abstract class RepositoryDialog extends Dialog {

	public RepositoryDialog(Shell parentShell, INode root) {
		super(parentShell);
		this.root = root;
	}
	
	@Override
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(newShellStyle | SWT.SHELL_TRIM);
	}

	private INode root;

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("JasperServer Repository Browser");
	}

	private MResource resource;
	private Text tname;
	private Text ttype;

	public MResource getResource() {
		return resource;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		composite.setLayout(new GridLayout(2, false));

		final TreeViewer treeViewer = new TreeViewer(composite, SWT.SINGLE
				| SWT.BORDER);
		GridData gd = new GridData(SWT.FILL,SWT.FILL,true,true);
		gd.horizontalSpan = 2;
		gd.minimumHeight = 300;
		gd.minimumWidth = 400;
		treeViewer.getTree().setLayoutData(gd);
		treeViewer.setContentProvider(new ReportTreeContetProvider());
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());
		treeViewer.setInput(root);
		ColumnViewerToolTipSupport.enableFor(treeViewer);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				TreeSelection ts = (TreeSelection) event.getSelection();
				Object obj = ts.getFirstElement();
				if (obj instanceof MResource
						&& isResourceCompatible((MResource) obj))
					setResource((MResource) obj);
			}
		});
		treeViewer.addTreeListener(new ITreeViewerListener() {

			private ServerProvider serverProvider;

			public void treeExpanded(TreeExpansionEvent event) {
				if (serverProvider == null)
					serverProvider = new ServerProvider();
				serverProvider.handleTreeEvent(event);
			}

			public void treeCollapsed(TreeExpansionEvent event) {

			}
		});

		new Label(composite, SWT.NONE).setText("Resource Name");

		tname = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE).setText("Resource Type");

		ttype = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		return composite;
	}

	public abstract boolean isResourceCompatible(MResource r);

	private void setResource(MResource res) {
		this.resource = res;
		tname.setText(res.getValue().getUriString());
		ttype.setText(res.getValue().getWsType());
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}
}
