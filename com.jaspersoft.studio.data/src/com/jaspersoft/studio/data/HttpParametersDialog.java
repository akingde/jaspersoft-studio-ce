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
package com.jaspersoft.studio.data;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.data.http.HttpLocationParameter;
import net.sf.jasperreports.data.http.RequestMethod;
import net.sf.jasperreports.data.http.StandardHttpDataLocation;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.EditButton;
import com.jaspersoft.studio.swt.widgets.table.IEditElement;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtil;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class HttpParametersDialog extends ATitledDialog {
	private StandardHttpDataLocation dataFile;

	/**
	 * @param parentShell
	 */
	protected HttpParametersDialog(Shell parentShell,
			StandardHttpDataLocation dataFile) {
		super(parentShell);
		setTitle(Messages.HttpParametersDialog_0);
		setDescription("Setup connection options. URL Parameters will be added to the url string.\n1In case you use POST request POST parameters will be added to the request body.");
		this.dataFile = dataFile;
	}

	public StandardHttpDataLocation getDataFile() {
		return dataFile;
	}

	public void setDataFile(StandardHttpDataLocation dataFile) {
		this.dataFile = dataFile;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		new Label(cmp, SWT.NONE).setText(Messages.HttpParametersDialog_1);
		final Text user = new Text(cmp, SWT.BORDER);
		user.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		user.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				dataFile.setUsername(user.getText());
			}
		});

		new Label(cmp, SWT.NONE).setText(Messages.HttpParametersDialog_2);
		final Text password = new Text(cmp, SWT.BORDER | SWT.PASSWORD);
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		password.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				dataFile.setPassword(password.getText());
			}
		});

		user.setText(Misc.nvl(dataFile.getUsername()));
		password.setText(Misc.nvl(dataFile.getPassword()));

		new Label(cmp, SWT.NONE).setText("Request Type");

		final Combo cmb = new Combo(cmp, SWT.READ_ONLY);
		cmb.setItems(new String[] { RequestMethod.GET.name(),
				RequestMethod.POST.name() });
		if (dataFile.getMethod() == RequestMethod.POST)
			cmb.select(1);
		else
			cmb.select(0);
		cmb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (cmb.getSelectionIndex()) {
				case 0:
					dataFile.setMethod(RequestMethod.GET);
				case 1:
					dataFile.setMethod(RequestMethod.POST);
				}
			}
		});

		CTabFolder tabFolder = new CTabFolder(cmp, SWT.FLAT | SWT.TOP);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		tabFolder.setLayoutData(gd);

		if (dataFile.getUrlParameters() == null)
			dataFile.setUrlParameters(new ArrayList<HttpLocationParameter>());
		if (dataFile.getPostParameters() == null)
			dataFile.setPostParameters(new ArrayList<HttpLocationParameter>());

		final TableViewer tv = createParameters(tabFolder, "URL Parameters");
		final TableViewer tvp = createParameters(tabFolder, "POST Parameters");

		tabFolder.setSelection(0);

		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				tv.setInput(dataFile.getUrlParameters());
				tvp.setInput(dataFile.getPostParameters());
			}
		});

		return cmp;
	}

	protected class PEditDialog extends Dialog {

		private String pname;
		private String pvalue;

		protected PEditDialog(Shell parentShell, HttpLocationParameter hlp) {
			super(parentShell);
			this.pname = hlp.getName();
			this.pvalue = hlp.getValue();
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite composite = (Composite) super.createDialogArea(parent);
			composite.setLayout(new GridLayout(2, false));
			Label label = new Label(composite, SWT.NONE);
			label.setText("Name");

			final Text text = new Text(composite, SWT.BORDER);
			text.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
					| GridData.HORIZONTAL_ALIGN_FILL));
			text.setText(Misc.nvl(pname, "net.sf.jasperreports.")); //$NON-NLS-1$
			text.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					pname = text.getText();
				}
			});

			label = new Label(composite, SWT.NONE);
			label.setText("Value");

			final Text tname = new Text(composite, SWT.BORDER);
			tname.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
					| GridData.HORIZONTAL_ALIGN_FILL));
			tname.setText(Misc.nvl(pvalue, "Value"));
			tname.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					pvalue = tname.getText();
				}
			});
			applyDialogFont(composite);
			return composite;
		}

		@Override
		protected boolean isResizable() {
			return true;
		}

		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setSize(500, 200);
			newShell.setText("Parameter Dialog");
		}

		public String getPName() {
			return this.pname;
		}

		public String getPValue() {
			return this.pvalue;
		}

	}

	private TableViewer createParameters(CTabFolder tFolder, String title) {
		CTabItem bptab = new CTabItem(tFolder, SWT.NONE);
		bptab.setText(title);

		Composite cmp = new Composite(tFolder, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		TableViewer tviewer = new TableViewer(cmp, SWT.V_SCROLL | SWT.MULTI
				| SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 500;
		gd.heightHint = 200;
		tviewer.getTable().setLayoutData(gd);
		tviewer.getTable().setHeaderVisible(true);

		TableViewerColumn column = new TableViewerColumn(tviewer, SWT.NONE);
		column.getColumn().setText("Name");
		column.getColumn().setWidth(250);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				return ((HttpLocationParameter) element).getName();
			}

			@Override
			public String getToolTipText(Object element) {
				return ((HttpLocationParameter) element).getName();
			}
		});

		column = new TableViewerColumn(tviewer, SWT.NONE);
		column.getColumn().setText("Value");
		column.getColumn().setWidth(250);
		column.setLabelProvider(new ColumnLabelProvider() {

			public String getText(Object element) {
				return ((HttpLocationParameter) element).getValue();
			}

			@Override
			public String getToolTipText(Object element) {
				return ((HttpLocationParameter) element).getValue();
			}
		});

		tviewer.setContentProvider(new ListContentProvider());
		ColumnViewerToolTipSupport.enableFor(tviewer, ToolTip.NO_RECREATE);
		UIUtil.setViewerCellEditingOnDblClick(tviewer);

		Composite bGroup = new Composite(cmp, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		new NewButton().createNewButtons(bGroup, tviewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				HttpLocationParameter hlp = new HttpLocationParameter("prm",
						"value");
				PEditDialog pd = new PEditDialog(getShell(), hlp);
				if (pd.open() == Dialog.OK) {
					hlp.setName(pd.getPName());
					hlp.setValue(pd.getPValue());
					return hlp;
				}
				return null;
			}

		});

		final EditButton<HttpLocationParameter> bEditPrm = new EditButton<HttpLocationParameter>();
		bEditPrm.createEditButtons(bGroup, tviewer,
				new IEditElement<HttpLocationParameter>() {

					@Override
					public void editElement(List<HttpLocationParameter> input,
							int pos) {
						HttpLocationParameter hlp = input.get(pos);
						PEditDialog pd = new PEditDialog(getShell(), hlp);
						if (pd.open() == Dialog.OK) {
							hlp.setName(pd.getPName());
							hlp.setValue(pd.getPValue());
						}
					}
				});
		tviewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				bEditPrm.push();
			}
		});
		final DeleteButton delb = new DeleteButton();
		delb.createDeleteButton(bGroup, tviewer);

		bptab.setControl(cmp);
		return tviewer;
	}
}
