package com.jaspersoft.studio.repository.wizzard;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.internal.core.BinaryType;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.internal.Workbench;

import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDataSource;

public class JDBCDatasourcePage extends ADatasourcePage {

	private Text driverClassTxt;
	private Text jdbcURLTxt;
	private Text usernameTxt;
	private Text passwordTxt;

	protected JDBCDatasourcePage() {
		super("jdbcdatasourceeditor");
		setTitle("JDBC Datasource");
		setDescription("Creates a JDBC datasource.");
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();
		value.setPropertyValue(MJDBCDataSource.PROPERTY_DRIVERCLASS, driverClassTxt.getText());
		value.setPropertyValue(MJDBCDataSource.PROPERTY_JDBC_URL, jdbcURLTxt.getText());
		value.setPropertyValue(MJDBCDataSource.PROPERTY_USERNAME, usernameTxt.getText());
		value.setPropertyValue(MJDBCDataSource.PROPERTY_PASSWORD, passwordTxt.getText());
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {

		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("Driver Class:");

		driverClassTxt = new Text(parent, SWT.BORDER);
		driverClassTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("JDBC URL:");

		Composite c = new Composite(parent, SWT.NONE);
		c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		c.setLayout(layout);

		jdbcURLTxt = new Text(c, SWT.BORDER);
		jdbcURLTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Button copyButton = new Button(c, SWT.PUSH);
		// copyButton.setText("Br&owse ...");
		// copyButton.addSelectionListener(new SelectionListener() {
		//
		// public void widgetSelected(SelectionEvent e) {
		// IProject project; // currently selected project
		// IEditorPart editorPart = Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		// if (editorPart != null) {
		// IFileEditorInput input = (IFileEditorInput) editorPart.getEditorInput();
		// IFile file = input.getFile();
		// IProject activeProject = file.getProject();
		// if (activeProject instanceof JavaProject) {
		// IType myInterface = ((JavaProject) activeProject).findType("MyInterface", "name.seller.rich");
		//
		// // get the sub types from the interface's type hierarchy
		// ITypeHierarchy hierarchy = myInterface.newTypeHierarchy(new NullProgressMonitor());
		//
		// IType[] subTypes = hierarchy.getAllSubtypes(myInterface);
		//
		// SelectionDialog dialog = JavaUI.createTypeDialog(shell, new ProgressMonitorDialog(shell), searchScope,
		// IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES, false);
		// dialog.setTitle("Open Type");
		// dialog.setMessage("Enter the name prefix or pattern (?, *, or camel case)");
		// if (dialog.open() == Window.OK) {
		// if (dialog.getResult() != null && dialog.getResult().length > 0) {
		// BinaryType bt = (BinaryType) dialog.getResult()[0];
		//
		// return bt.getFullyQualifiedName();
		// }
		// }
		// }
		// }
		//
		// }
		//
		// public void widgetDefaultSelected(SelectionEvent e) {
		// }
		// });

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("Username:");

		usernameTxt = new Text(parent, SWT.BORDER);
		usernameTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("Password:");

		passwordTxt = new Text(parent, SWT.PASSWORD | SWT.BORDER);
		passwordTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	}

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			String dsName = (String) value.getPropertyValue(MJDBCDataSource.PROPERTY_DRIVERCLASS);
			if (dsName == null)
				dsName = "";
			driverClassTxt.setText(dsName);

			dsName = (String) value.getPropertyValue(MJDBCDataSource.PROPERTY_JDBC_URL);
			if (dsName == null)
				dsName = "";
			jdbcURLTxt.setText(dsName);

			dsName = (String) value.getPropertyValue(MJDBCDataSource.PROPERTY_USERNAME);
			if (dsName == null)
				dsName = "";
			usernameTxt.setText(dsName);

			dsName = (String) value.getPropertyValue(MJDBCDataSource.PROPERTY_PASSWORD);
			if (dsName == null)
				dsName = "";
			passwordTxt.setText(dsName);
		}
	}

}
