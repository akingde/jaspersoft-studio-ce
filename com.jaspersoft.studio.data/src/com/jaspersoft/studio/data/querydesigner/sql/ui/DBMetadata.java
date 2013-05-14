package com.jaspersoft.studio.data.querydesigner.sql.ui;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.querydesigner.sql.model.MDBObjects;
import com.jaspersoft.studio.data.querydesigner.sql.model.MFunction;
import com.jaspersoft.studio.data.querydesigner.sql.model.MProcedure;
import com.jaspersoft.studio.data.querydesigner.sql.model.MSqlTable;
import com.jaspersoft.studio.data.querydesigner.sql.model.MView;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;

public class DBMetadata {
	private TreeViewer domTreeViewer;
	private MRoot root;

	public DBMetadata() {
		// TODO Auto-generated constructor stub
	}

	public Control createControl(Composite parent) {
		domTreeViewer = new TreeViewer(parent, SWT.SINGLE | SWT.BORDER);
		domTreeViewer.setContentProvider(new ReportTreeContetProvider());
		domTreeViewer.setLabelProvider(new ReportTreeLabelProvider());

		ColumnViewerToolTipSupport.enableFor(domTreeViewer);

		root = new MRoot(null, null);
		updateUI(root, null);

		return domTreeViewer.getControl();
	}

	public void updateUI(Connection c) {
		root.removeChildren();
		try {
			DatabaseMetaData meta = c.getMetaData();
			ResultSet schemas = meta.getSchemas();
			while (schemas.next()) {
				String tableSchema = schemas.getString(1); // "TABLE_SCHEM"
				MDBObjects msch = new MDBObjects(root, tableSchema, "icons/database.png");

				String tableCatalog = null;
				if (meta.supportsCatalogsInTableDefinitions())
					tableCatalog = schemas.getString(2); // "TABLE_CATALOG"

				try {
					MDBObjects mtbl = new MDBObjects(msch, "Tables", "icons/table.png");
					ResultSet rs = meta.getTables(tableCatalog, tableSchema, "%", new String[] { "TABLE" });
					while (rs.next()) {
						new MSqlTable(mtbl, rs.getString(3), rs);
					}
				} catch (Exception e) {
				}
				try {
					MDBObjects mview = new MDBObjects(msch, "Views", "icons/table.png");
					ResultSet rs = meta.getTables(tableCatalog, tableSchema, "%", new String[] { "VIEW" });
					while (rs.next()) {
						new MView(mview, rs.getString(3), rs);
					}
				} catch (Exception e) {
				}
				try {
					ResultSet rs = meta.getProcedures(tableCatalog, tableSchema, "%");
					MDBObjects mprocs = new MDBObjects(msch, "Procedures", "icons/function.png");
					while (rs.next()) {
						new MProcedure(mprocs, rs.getString(3), rs);
					}
				} catch (Exception e) {
				}
				try {
					ResultSet rs = meta.getFunctions(tableCatalog, tableSchema, "%");
					MDBObjects mfunct = new MDBObjects(msch, "Functions", "icons/function.png");
					while (rs.next()) {
						new MFunction(mfunct, rs.getString(3), rs);
					}
				} catch (Exception e) {
				}

				System.out.println(meta.getSQLKeywords());
				System.out.println(meta.getSQLKeywords());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		updateUI(root, c);
	}

	protected void updateUI(final MRoot root, Connection c) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				domTreeViewer.setInput(root);
			}
		});
	}
}
