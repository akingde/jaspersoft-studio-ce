package com.jaspersoft.studio.data.sql.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.model.AMSQLObject;
import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlSchema;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.metadata.MTables;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;

public class UsedColumnsDialog extends Dialog {
	private TreeViewer treeViewer;
	private MRoot root;
	private List<MColumn> cols = new ArrayList<MColumn>();
	private List<MSqlTable> tables;
	private List<AMSQLObject> columns;

	public UsedColumnsDialog(Shell parentShell) {
		super(parentShell);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Columns Dialog");
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	public void setRoot(MRoot root) {
		this.root = root;
	}

	public void setSelection(ANode sel) {
		tables = Util.getTables(sel);
		columns = Util.getUsedColumns(sel);
	}

	@Override
	public boolean close() {
		TreeSelection ts = (TreeSelection) treeViewer.getSelection();
		for (Object obj : ts.toList()) {
			if (obj instanceof MColumn)
				cols.add((MColumn) obj);
		}

		return super.close();
	}

	public List<MColumn> getColumns() {
		return cols;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);

		treeViewer = new TreeViewer(cmp, SWT.MULTI | SWT.BORDER);
		treeViewer.setContentProvider(new ReportTreeContetProvider() {
			@Override
			public Object[] getChildren(Object parentElement) {
				if (parentElement instanceof MRoot) {
					List<INode> n = new ArrayList<INode>();
					MRoot p = (MRoot) parentElement;
					for (INode node : p.getChildren())
						if (node instanceof MSqlSchema && getChildrens4Schema((MSqlSchema) node).length > 0)
							n.add(node);
					return n.toArray();
				} else if (parentElement instanceof MSqlSchema)
					return getChildrens4Schema((MSqlSchema) parentElement);
				else if (parentElement instanceof MTables)
					return getChildrens4Tables((MTables) parentElement);
				return super.getChildren(parentElement);
			}

			protected Object[] getChildrens4Schema(MSqlSchema p) {
				if (p.getChildren() != null && p.getChildren().size() > 0) {
					List<INode> n = new ArrayList<INode>();
					for (INode node : p.getChildren())
						if (node instanceof MTables && getChildrens4Tables((MTables) node).length > 0)
							n.add(node);
					return n.toArray();
				}
				return new Object[0];
			}

			protected Object[] getChildrens4Tables(MTables p) {
				if (p.getChildren() != null && p.getChildren().size() > 0) {
					List<INode> n = new ArrayList<INode>();
					for (INode node : p.getChildren())
						if (tables.contains(node))
							n.add(node);
					return n.toArray();
				}
				return new Object[0];
			}

		});
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 400;
		gd.minimumWidth = 400;
		treeViewer.getControl().setLayoutData(gd);
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				TreeSelection ts = (TreeSelection) treeViewer.getSelection();
				Object el = ts.getFirstElement();
				if (el instanceof MColumn)
					okPressed();
				else {
					if (treeViewer.getExpandedState(el))
						treeViewer.collapseToLevel(el, 1);
					else
						treeViewer.expandToLevel(el, 1);
				}
			}
		});
		ColumnViewerToolTipSupport.enableFor(treeViewer);

		treeViewer.setInput(root);

		treeViewer.expandAll();
		return cmp;
	}
}
