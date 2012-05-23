/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.querydesigner.sql;

//import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
//import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
//import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.designer.QueryDesigner;

public class SQLQueryDesigner extends QueryDesigner {
	public SQLQueryDesigner() {
		super();
	}

	private Composite composite;

	@Override
	public Control getControl() {
		// if (sqleditor == null)
		return super.getControl();
		// return composite;
	}

	@Override
	public Control createControl(Composite parent) {
		IEditorPart actEditor = JaspersoftStudioPlugin.getInstance()
				.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		if (actEditor != null) {
			// super.createControl(parent);
			composite = new Composite(parent, SWT.NONE);
			composite.setLayoutData(new GridData(GridData.FILL_BOTH));
			FillLayout layout = new FillLayout(SWT.HORIZONTAL);
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			layout.spacing = 0;
			composite.setLayout(layout);

			// try {
			// SQLBuilder sqlbuilder = new SQLBuilder();
			// String driverURL =
			// "jdbc:derby:/home/slavic/mydb;create=true";
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

			// sqleditor = new SQLEditor();
			// sqleditor.init((IEditorSite) actEditor.getSite(),
			// createSQLEditorInput(""));
			// sqleditor.createPartControl(composite);
			// editor.setInput(new SQLBuilderEditorInput(cp,
			// StatementHelper.STATEMENT_TYPE_SELECT));

			// } catch (PartInitException e) {
			// e.printStackTrace();
			// }
			// catch (ParseException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			return composite;
		} else
			return super.createControl(parent);
	}

	@Override
	public void dispose() {
		// sqleditor.dispose();
		super.dispose();
	}

	// private SQLEditor sqleditor;

	@Override
	public void setQuery(JasperDesign jDesign, JRDataset jDataset) {
		// if (sqleditor != null) {
		// sqleditor.setInput(createSQLEditorInput(query));
		// this.query = query;
		// } else
		super.setQuery(jDesign, jDataset);
	}

	// private QDSQLEditorInput createSQLEditorInput(String query) {
	// QDSQLEditorInput input = new QDSQLEditorInput(query);
	// input.setConnectionInfo(new SQLEditorConnectionInfo(
	// new DatabaseVendorDefinitionId("hsql")));
	// return input;
	// }

	@Override
	public String getQuery() {
		// if (sqleditor != null) {
		// query = sqleditor.getText();
		// return query;
		// } else
		return super.getQuery();
	}
}
