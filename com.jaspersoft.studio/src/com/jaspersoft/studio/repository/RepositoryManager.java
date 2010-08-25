package com.jaspersoft.studio.repository;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.ReportPreviewUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.MDatasources;
import com.jaspersoft.studio.model.datasource.empty.MEmptyDatasources;
import com.jaspersoft.studio.model.datasource.file.MFileDatasources;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDataSource;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDatasources;
import com.jaspersoft.studio.model.datasource.xml.MXMLDatasources;

public class RepositoryManager {
	public static PropertyChangeSupport getPropertyChangeSupport() {
		return propertyChangeSupport;
	}

	private static MRoot rootNode;
	private static MDatasources datasources;
	private static PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(new RepositoryManager());

	public static ANode getResources() {
		if (rootNode == null) {
			rootNode = new MRoot(null, null);
			getDatasources(rootNode);
		}
		return rootNode;
	}

	public static String checkDataSourceName(String name) {
		// int i = 1;
		// List<AMDatasource> l = getDatasources();
		// for (AMDatasource d : l) {
		// String nameProp = (String) d.getPropertyValue(AMDatasource.PROPERTY_NAME);
		// if (nameProp != null && name.equals(nameProp)) {
		// i++;
		// }
		// }
		return name;
	}

	public static List<AMDatasource> getDatasources() {
		// maybe cache the list?
		List<AMDatasource> list = new ArrayList<AMDatasource>();
		for (INode n : getDatasources((MRoot) getResources()).getChildren()) {
			for (INode md : n.getChildren()) {
				if (md instanceof AMDatasource) {
					list.add((AMDatasource) md);
				}
			}
		}

		// iterate structure get all the datasources;

		return list;
	}

	public static void saveResource(AMDatasource d) {
		propertyChangeSupport.firePropertyChange(new PropertyChangeEvent(d, "DATASOURCE", null, d));
		// save to disc
	}

	private static MDatasources getDatasources(MRoot root) {
		if (datasources == null) {
			datasources = new MDatasources(root);
			MJDBCDatasources jdbcds = new MJDBCDatasources(datasources);
			MJDBCDataSource ds = new MJDBCDataSource(jdbcds, -1);
			ds.setPropertyValue(AMDatasource.PROPERTY_NAME, "Simple datasource");

			new MEmptyDatasources(datasources);

			new MXMLDatasources(datasources);
			new MFileDatasources(datasources);

			// new MODADatasources(datasources);
			// new MDTPDatasources(datasources);
		}
		return datasources;
	}

	public static Connection establishConnection(MJDBCDataSource d, IEditorPart editorPart)
			throws ClassNotFoundException, SQLException {
		if (editorPart != null) {
			IFileEditorInput input = (IFileEditorInput) editorPart.getEditorInput();
			IFile file = input.getFile();
			IProject activeProject = file.getProject();

			ClassLoader cl = ReportPreviewUtil.createProjectClassLoader(activeProject);
			cl.loadClass((String) d.getPropertyValue(MJDBCDataSource.PROPERTY_DRIVERCLASS));

			String url = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_JDBC_URL);
			String user = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_USERNAME);
			String pass = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_PASSWORD);

			return DriverManager.getConnection(url, user, pass);
		}
		return null;
	}
}
