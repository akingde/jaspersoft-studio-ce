package com.jaspersoft.studio.repository;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.MDatasources;
import com.jaspersoft.studio.model.datasource.empty.MEmptyDatasources;
import com.jaspersoft.studio.model.datasource.file.MFileDataSource;
import com.jaspersoft.studio.model.datasource.file.MFileDatasources;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDataSource;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDatasources;
import com.jaspersoft.studio.model.datasource.xml.MXMLDataSource;
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

	private static Map<String, Driver> drivers = new java.util.Hashtable<String, Driver>();

	public static Connection establishConnection(MJDBCDataSource d, IEditorPart editorPart) throws Exception {
		Connection connection = (Connection) d.getPropertyValue(MJDBCDataSource.PROPERTY_CONNECTION);
		if (connection == null || connection.isClosed()) {
			// if (editorPart != null) {
			String drvClass = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_DRIVERCLASS);
			String url = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_JDBC_URL);
			String user = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_USERNAME);
			String pass = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_PASSWORD);
			String jars = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_JAR);
			if (jars != null && !jars.trim().equals("")) {
				// IFileEditorInput input = (IFileEditorInput) editorPart.getEditorInput();
				// IFile file = input.getFile();
				// IProject activeProject = file.getProject();
				//
				// IJavaProject javaProject = null;
				//
				// if (activeProject.hasNature(JasperReportsBuilder.JAVA_NATURE)) {
				// javaProject = JavaCore.create(activeProject);
				// }
				Driver driver = drivers.get(drvClass);
				if (driver == null) {
					List<URL> urls = new ArrayList<URL>();
					StringTokenizer st = new StringTokenizer(jars, ";");
					while (st.hasMoreElements()) {
						String jar = st.nextToken();
						java.io.File jarFiles = new java.io.File(jar);
						if (!jarFiles.exists())
							throw new ClassNotFoundException(jar + " not found");

						urls.add(jarFiles.toURI().toURL());
					}
					ClassLoader loader = new URLClassLoader(urls.toArray(new URL[urls.size()]));
					Class<?> cl = loader.loadClass(drvClass);
					if (!Driver.class.isAssignableFrom(cl))
						throw new Exception("Connection failed. The specified driver class does not implement the "
								+ Driver.class.getName() + " interface.");
					driver = (Driver) cl.newInstance();
					drivers.put(drvClass, driver);
				}
				Properties info = new Properties();
				if (user != null)
					info.put("user", user);
				if (pass != null)
					info.put("password", pass);
				connection = driver.connect(url, info);
			} else {
				Class.forName(drvClass);
				DriverManager.getConnection(url, user, pass);
			}

			d.setPropertyValue(MJDBCDataSource.PROPERTY_CONNECTION, connection);
			// }
		}
		return connection;
	}

	public static JRDataSource createFileDataSource(InputStream io, MFileDataSource datasource) {
		JRCsvDataSource jrds = new JRCsvDataSource(io);
		String p = (String) datasource.getPropertyValue(MFileDataSource.PROPERTY_RECORDDELIMITER);
		String crlf = p.replace("\\r", "\r").replace("\\n", "\n").replace("\\t", "\t");
		jrds.setRecordDelimiter(crlf);

		char c = (Character) datasource.getPropertyValue(MFileDataSource.PROPERTY_COLUMNDELIMITER);
		jrds.setFieldDelimiter(c);

		boolean b = (Boolean) datasource.getPropertyValue(MFileDataSource.PROPERTY_FIRSTROWASHEADER);
		jrds.setUseFirstRowAsHeader(b);

		p = (String) datasource.getPropertyValue(MFileDataSource.PROPERTY_COLUMNNAMES);
		if (p != null && !p.trim().equals("")) {
			p = p.replaceAll(" ", "");
			StringTokenizer st = new StringTokenizer(p, ",");
			List<String> cols = new ArrayList<String>();
			while (st.hasMoreTokens()) {
				String nextToken = st.nextToken();
				nextToken = nextToken.replace('"', ' ').trim();
				cols.add(nextToken);
			}
			jrds.setColumnNames(cols.toArray(new String[cols.size()]));
			// .setColumnNames(new String[] { "city", "id", "name", "address", "state" });
		}
		return jrds;
	}

	public static JRDataSource createXMLDataSource(InputStream io, MXMLDataSource datasource) throws JRException {
		JRXmlDataSource jrds = null;
		String select = (String) datasource.getPropertyValue(MXMLDataSource.PROPERTY_XPATHSELECT);
		if (select != null && !select.trim().endsWith(""))
			jrds = new JRXmlDataSource(io, select);
		else
			jrds = new JRXmlDataSource(io);
		return jrds;
	}
}
