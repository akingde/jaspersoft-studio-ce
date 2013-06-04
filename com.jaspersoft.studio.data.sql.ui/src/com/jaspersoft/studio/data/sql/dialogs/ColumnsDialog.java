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

import com.jaspersoft.studio.data.sql.model.metadata.MColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlSchema;
import com.jaspersoft.studio.data.sql.model.metadata.MTables;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;

public class ColumnsDialog extends Dialog {
	private TreeViewer treeViewer;
	private MRoot root;
	private List<MColumn> cols = new ArrayList<MColumn>();

	public ColumnsDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected boolean isResizable() {
		return true;
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

	public void setRoot(MRoot root) {
		this.root = root;
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
				if (parentElement instanceof MSqlSchema) {
					MSqlSchema p = (MSqlSchema) parentElement;
					if (p.getChildren() != null && p.getChildren().size() > 0) {
						List<INode> n = new ArrayList<INode>();
						for (INode node : p.getChildren()) {
							if (node instanceof MTables)
								n.add(node);
						}
						return n.toArray();
					}
				}
				return super.getChildren(parentElement);
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
		return cmp;
	}
}
