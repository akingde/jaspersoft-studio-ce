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
package com.jaspersoft.studio.data.sql;

import java.lang.reflect.InvocationTargetException;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceUtil;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledTextDropTargetEffect;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.PluginTransfer;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterDescriptor;
import com.jaspersoft.studio.data.querydesigner.sql.SimpleSQLQueryDesigner;
import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.data.sql.model.query.MGroupBy;
import com.jaspersoft.studio.data.sql.model.query.MHaving;
import com.jaspersoft.studio.data.sql.model.query.MWhere;
import com.jaspersoft.studio.data.sql.model.query.from.MFrom;
import com.jaspersoft.studio.data.sql.model.query.orderby.MOrderBy;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.ui.DBMetadata;
import com.jaspersoft.studio.data.sql.ui.SQLQueryOutline;
import com.jaspersoft.studio.data.sql.ui.gef.SQLQueryDiagram;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.swt.widgets.CSashForm;

public class SQLQueryDesigner extends SimpleSQLQueryDesigner {
	private SashForm sf;
	private DBMetadata dbMetadata;
	private SQLQueryOutline outline;
	private SQLQueryDiagram diagram;
	private MRoot root;

	public SQLQueryDesigner() {
		super();
		refreshViewer();
	}

	@Override
	public Control getControl() {
		return sf;
	}

	@Override
	public Control createControl(Composite parent) {
		sf = new CSashForm(parent, SWT.HORIZONTAL);
		sf.setLayoutData(new GridData(GridData.FILL_BOTH));
		sf.setLayout(new GridLayout());

		dbMetadata = new DBMetadata(this);
		Control c = dbMetadata.createControl(sf);
		c.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		tabFolder = new CTabFolder(sf, SWT.FLAT | SWT.BOTTOM);
		tabFolder.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		createSource(tabFolder);
		createOutline(tabFolder);
		createDiagram(tabFolder);

		tabFolder.setSelection(0);
		tabFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (tabFolder.getSelectionIndex()) {
				case 1:
					showWarning();
					outline.scheduleRefresh();
					break;
				case 2:
					showWarning();
					diagram.scheduleRefresh();
					break;
				}
			}
		});

		sf.setWeights(new int[] { 250, 750 });
		return sf;
	}

	private boolean warned = false;

	private void showWarning() {
		if (!warned && isQueryModelEmpty()) {
			UIUtils.showWarning("Attention, SQL Query Builder will overwrite the existing query!");
			warned = true;
		}
	}

	protected void createDiagram(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Diagram");

		diagram = new SQLQueryDiagram(this);
		bptab.setControl(diagram.createDiagram(tabFolder));
	}

	protected void createOutline(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Outline");

		outline = new SQLQueryOutline(this);
		bptab.setControl(outline.createOutline(tabFolder));
	}

	private void createSource(CTabFolder tabFolder) {
		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText("Text");

		super.createControl(tabFolder);
		control.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bptab.setControl(control);

		DropTarget target = new DropTarget(control, DND.DROP_MOVE | DND.DROP_COPY);
		target.setTransfer(new Transfer[] { NodeTransfer.getInstance(), PluginTransfer.getInstance() });
		target.addDropListener(new StyledTextDropTargetEffect(control) {
			@Override
			public void drop(DropTargetEvent event) {
				Object obj = event.data;
				if (obj.getClass().isArray()) {
					Object[] arr = (Object[]) obj;
					if (arr.length > 0)
						obj = arr[0];
				}
				if (obj instanceof AMSQLObject) {
					StringBuffer oldText = new StringBuffer(control.getText());

					oldText.insert(control.getCaretOffset(), " " + ((AMSQLObject) obj).toSQLString() + " ");
					control.setText(oldText.toString());
				}
			}
		});
	}

	public void refreshQuery() {
		if (root != null) {
			if (!isQueryModelEmpty()) {
				updateQueryText(QueryWriter.writeQuery(root));
				if (tabFolder.getSelectionIndex() == 2)
					diagram.scheduleRefresh();
			}
		}
	}

	protected boolean isQueryModelEmpty() {
		boolean update = true;
		for (INode c : root.getChildren())
			if (!c.getChildren().isEmpty()) {
				update = false;
				break;
			}
		return update;
	}

	private DataAdapterDescriptor da;

	@Override
	public void setDataAdapter(final DataAdapterDescriptor da) {
		if (this.da == da)
			return;
		this.da = da;
		super.setDataAdapter(da);
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				updateMetadata();
			}
		});
	}

	private Thread runningthread;
	private IProgressMonitor runningmonitor;
	private CTabFolder tabFolder;

	public void updateMetadata() {
		if (da instanceof JDBCDataAdapterDescriptor)
			try {
				container.run(true, true, new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						if (runningthread != null) {
							runningmonitor.setCanceled(true);
							if (runningthread != null)
								runningthread.join();
						}
						runningmonitor = monitor;
						runningthread = Thread.currentThread();
						try {
							monitor.beginTask("Reading metadata", IProgressMonitor.UNKNOWN);
							DataAdapterService das = DataAdapterServiceUtil.getInstance(jConfig).getService(da.getDataAdapter());
							dbMetadata.updateUI(da, das, monitor);
						} finally {
							monitor.done();
							runningthread = null;
							runningmonitor = null;
						}
					}
				});
			} catch (InvocationTargetException ex) {
				container.getQueryStatus().showError(ex.getTargetException());
				runningthread = null;
				runningmonitor = null;
			} catch (InterruptedException ex) {
				container.getQueryStatus().showError(ex);
				runningthread = null;
				runningmonitor = null;
			}
	}

	public SQLQueryOutline getOutline() {
		return outline;
	}

	public SQLQueryDiagram getDiagram() {
		return diagram;
	}

	@Override
	public void dispose() {
		if (outline != null)
			outline.dispose();
		if (diagram != null)
			diagram.dispose();
		super.dispose();
	}

	public DBMetadata getDbMetadata() {
		return dbMetadata;
	}

	public MRoot getRoot() {
		return root;
	}

	protected void refreshViewer() {
		if (root != null)
			root.removeChildren();
		else
			root = new MRoot(null, getjDataset());
		new MSelect(root);
		new MFrom(root);
		new MWhere(root);
		new MGroupBy(root);
		new MHaving(root);
		new MOrderBy(root);
	}
}
