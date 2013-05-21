package com.jaspersoft.studio.data.sql.ui;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.PluginTransfer;

import com.jaspersoft.studio.data.sql.model.MColumn;
import com.jaspersoft.studio.data.sql.model.MDBObjects;
import com.jaspersoft.studio.data.sql.model.MFunction;
import com.jaspersoft.studio.data.sql.model.MProcedure;
import com.jaspersoft.studio.data.sql.model.MSqlTable;
import com.jaspersoft.studio.data.sql.model.MView;
import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;

public class DBMetadata {
	private TreeViewer treeViewer;
	private MRoot root;

	public DBMetadata() {
	}

	public Control createControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.SINGLE | SWT.BORDER);
		treeViewer.setContentProvider(new ReportTreeContetProvider());
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());

		ColumnViewerToolTipSupport.enableFor(treeViewer);

		int ops = DND.DROP_COPY | DND.DROP_MOVE;
		Transfer[] transfers = new Transfer[] { NodeTransfer.getInstance(), PluginTransfer.getInstance() };
		treeViewer.addDragSupport(ops, transfers, new NodeDragListener(treeViewer) {
			@Override
			public void dragStart(DragSourceEvent event) {
				TreeSelection s = (TreeSelection) treeViewer.getSelection();
				for (TreePath tp : s.getPaths()) {
					if (!(tp.getLastSegment() instanceof IDragable)) {
						event.doit = false;
						return;
					}
				}
				event.doit = !s.isEmpty();
			}

			@Override
			public void dragFinished(DragSourceEvent event) {
				treeViewer.refresh(true);
				if (!event.doit)
					return;
			}
		});
		MenuManager menuMgr = new MenuManager();
		Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
		menuMgr.add(new Action("&Refresh") {
			@Override
			public void run() {
				updateUI(das);
			}
		});
		treeViewer.getControl().setMenu(menu);

		root = new MRoot(null, null);
		updateUI(root, null);

		return treeViewer.getControl();
	}

	private DataAdapterService das;

	public void updateUI(final DataAdapterService das) {
		this.das = das;
		root.removeChildren();
		final Connection c = getConnection(das);
		if (c != null)
			try {
				DatabaseMetaData meta = c.getMetaData();
				ResultSet schemas = meta.getSchemas();
				while (schemas.next()) {
					String tableSchema = schemas.getString("TABLE_SCHEM");
					MDBObjects msch = new MDBObjects(root, tableSchema, "icons/database.png");

					String tableCatalog = null;
					if (meta.supportsCatalogsInTableDefinitions())
						tableCatalog = schemas.getString("TABLE_CATALOG");

					try {
						MDBObjects mtbl = new MDBObjects(msch, "Tables", "icons/table.png");
						ResultSet rs = meta.getTables(tableCatalog, tableSchema, "%", new String[] { "TABLE" });
						while (rs.next())
							new MSqlTable(mtbl, rs.getString("TABLE_NAME"), rs);

						for (INode n : mtbl.getChildren()) {
							rs = meta.getColumns(tableCatalog, tableSchema, (String) n.getValue(), "%");
							while (rs.next())
								new MColumn((ANode) n, rs.getString("COLUMN_NAME"), rs);
						}
					} catch (Exception e) {
					}
					try {
						MDBObjects mview = new MDBObjects(msch, "Views", "icons/table.png");
						ResultSet rs = meta.getTables(tableCatalog, tableSchema, "%", new String[] { "VIEW" });
						while (rs.next())
							new MView(mview, rs.getString("TABLE_NAME"), rs);
						for (INode n : mview.getChildren()) {
							rs = meta.getColumns(tableCatalog, tableSchema, (String) n.getValue(), "%");
							while (rs.next())
								new MColumn((ANode) n, rs.getString("COLUMN_NAME"), rs);
						}
					} catch (Exception e) {
					}
					try {
						ResultSet rs = meta.getProcedures(tableCatalog, tableSchema, "%");
						MDBObjects mprocs = new MDBObjects(msch, "Procedures", "icons/function.png");
						while (rs.next())
							new MProcedure(mprocs, rs.getString("PROCEDURE_NAME"), rs);
					} catch (Exception e) {
					}
					try {
						ResultSet rs = meta.getFunctions(tableCatalog, tableSchema, "%");
						MDBObjects mfunct = new MDBObjects(msch, "Functions", "icons/function.png");
						while (rs.next())
							new MFunction(mfunct, rs.getString("FUNCTION_NAME"), rs);
					} catch (Exception e) {
					}

					System.out.println(meta.getSQLKeywords());
					System.out.println(meta.getSQLKeywords());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				updateUI(root, c);
			}
		});

	}

	public Connection getConnection(final DataAdapterService das) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		try {
			if (das != null)
				das.contributeParameters(parameters);
		} catch (JRException e1) {
			e1.printStackTrace();
		}

		final Connection c = (Connection) parameters.get(JRParameter.REPORT_CONNECTION);
		return c;
	}

	protected void updateUI(final MRoot root, Connection c) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				treeViewer.setInput(root);
			}
		});
	}
}
