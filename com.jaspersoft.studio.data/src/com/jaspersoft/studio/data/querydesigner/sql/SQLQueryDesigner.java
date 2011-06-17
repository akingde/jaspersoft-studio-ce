package com.jaspersoft.studio.data.querydesigner.sql;

import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.QueryDesigner;

public class SQLQueryDesigner extends QueryDesigner {
	private Composite composite;

	@Override
	public Control getControl() {
		return composite;
	}

	@Override
	public Control createControl(Composite parent) {
		// super.createControl(parent);
		composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		FillLayout layout = new FillLayout(SWT.HORIZONTAL);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.spacing = 0;
		composite.setLayout(layout);

		try {
			// SQLBuilder sqlbuilder = new SQLBuilder();
			// String driverURL = "jdbc:derby:/home/slavic/mydb;create=true";
			// String driverClass = "org.apache.derby.jdbc.EmbeddedDriver";
			// String jarList =
			// "/home/slavic/eclipse3/eclipse/plugins/org.apache.derby_10.5.1.1_201005192117.jar";
			//
			// Properties bprop = new Properties();
			// bprop.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST,
			// jarList);
			// bprop.setProperty(
			// IJDBCConnectionProfileConstants.DRIVER_CLASS_PROP_ID,
			// driverClass);
			// bprop.setProperty(IJDBCConnectionProfileConstants.URL_PROP_ID,
			// driverURL);
			// // bprop.setProperty(
			// // IJDBCConnectionProfileConstants.DATABASE_NAME_PROP_ID,
			// // "SAMPLE");
			// bprop.setProperty(IJDBCConnectionProfileConstants.USERNAME_PROP_ID,
			// "");
			// bprop.setProperty(IJDBCConnectionProfileConstants.PASSWORD_PROP_ID,
			// "");
			// bprop.setProperty(
			// IJDBCConnectionProfileConstants.DATABASE_VENDOR_PROP_ID,
			// "Derby");
			// bprop.setProperty(
			// IJDBCConnectionProfileConstants.DATABASE_VERSION_PROP_ID,
			// "10.1");
			// bprop.setProperty(
			// IJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID,
			// String.valueOf(true));
			//
			// IConnectionProfile cp = ProfileManager
			// .getInstance()
			// .createTransientProfile(
			// "org.eclipse.datatools.connectivity.db.derby.embedded.connectionProfile",
			// bprop);

			// IConnectionProfile cp = ProfileManager.getInstance()
			// .getProfileByName("TESTDERBY");
			// IStatus status = cp.connect();
			// if (status.isOK()) {

			// sqlbuilder.setInput(new SQLBuilderEditorInput(cp,
			// StatementHelper.STATEMENT_TYPE_SELECT));
			// sqlbuilder.createClient(composite);
			IWorkbenchPage activePage = JaspersoftStudioPlugin.getInstance()
					.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			sqleditor = new SQLEditor();
			if (activePage != null && activePage.getActiveEditor() != null)
				sqleditor.init((IEditorSite) activePage.getActiveEditor()
						.getSite(), createSQLEditorInput(""));
			else {
				// sqleditor.
				// sqleditor.init(null, createSQLEditorInput(""));
			}
			// editor.setInput(new SQLBuilderEditorInput(cp,
			// StatementHelper.STATEMENT_TYPE_SELECT));
			sqleditor.createPartControl(composite);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		// catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return composite;
	}

	private String query;
	private SQLEditor sqleditor;

	@Override
	public void setQuery(String query) {
		sqleditor.setInput(createSQLEditorInput(query));
		this.query = query;
	}

	private QDSQLEditorInput createSQLEditorInput(String query) {
		QDSQLEditorInput input = new QDSQLEditorInput(query);
		input.setConnectionInfo(new SQLEditorConnectionInfo(
				new DatabaseVendorDefinitionId("hsql")));
		return input;
	}

	@Override
	public String getQuery() {
		query = sqleditor.getText();
		return query;
	}
}
