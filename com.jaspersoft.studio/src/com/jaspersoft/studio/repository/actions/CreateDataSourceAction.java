package com.jaspersoft.studio.repository.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.IDatasource;
import com.jaspersoft.studio.model.datasource.empty.MEmptyDataSource;
import com.jaspersoft.studio.model.datasource.empty.MEmptyDatasources;
import com.jaspersoft.studio.model.datasource.file.MFileDataSource;
import com.jaspersoft.studio.model.datasource.file.MFileDatasources;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDataSource;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDatasources;
import com.jaspersoft.studio.model.datasource.xml.MXMLDataSource;
import com.jaspersoft.studio.model.datasource.xml.MXMLDatasources;
import com.jaspersoft.studio.repository.RepositoryManager;
import com.jaspersoft.studio.repository.wizzard.DatasourceEditor;

public class CreateDataSourceAction extends Action {
	public static final String ID = "createdatasourceaction";
	private TreeViewer treeViewer;

	public CreateDataSourceAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText("Create ...");
		setDescription("Create Datasource");
		setToolTipText("Creates datasource");
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/data_source_add.gif"));
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/data_source_add.gif"));

	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		return firstElement != null && firstElement instanceof IDatasource;
	}

	@Override
	public void run() {
		Object obj = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		if (obj instanceof ANode) {
			ANode parent = (ANode) obj;
			AMDatasource value = null;
			if (parent instanceof MJDBCDatasources) {
				value = new MJDBCDataSource(null, -1);
			} else if (parent instanceof MXMLDatasources) {
				value = new MXMLDataSource(null, -1);
			} else if (parent instanceof MFileDatasources) {
				value = new MFileDataSource(null, -1);
			} else if (parent instanceof MEmptyDatasources) {
				value = new MEmptyDataSource(null, -1);
			}
			value.initProperties();

			if (value != null) {
				DatasourceEditor wizard = new DatasourceEditor();
				wizard.setValue(value);
				WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					AMDatasource n = wizard.getValue();
					n.setParent(parent, -1);
					// create physical file
					value = n;
					RepositoryManager.saveResource(value);
				}
				treeViewer.refresh(true);
				treeViewer.setSelection(new StructuredSelection(value));
			}
		}

	}
}
