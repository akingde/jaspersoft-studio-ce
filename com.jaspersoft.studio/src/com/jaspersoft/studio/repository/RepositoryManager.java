package com.jaspersoft.studio.repository;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.sf.jasperreports.eclipse.builder.JasperReportsBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

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

	public static Connection establishConnection(MJDBCDataSource d, IEditorPart editorPart)
			throws ClassNotFoundException, SQLException, CoreException, MalformedURLException {
		if (editorPart != null) {
			IFileEditorInput input = (IFileEditorInput) editorPart.getEditorInput();
			IFile file = input.getFile();
			IProject activeProject = file.getProject();

			IJavaProject javaProject = null;

			if (activeProject.hasNature(JasperReportsBuilder.JAVA_NATURE)) {
				javaProject = JavaCore.create(activeProject);
			}

			// ClassLoader cl = ReportPreviewUtil.createProjectClassLoader(activeProject);

			// ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();
			//
			// try {
			// ClassLoader projectClassLoader = ReportPreviewUtil.createProjectClassLoader(activeProject);
			// if (projectClassLoader != null) {
			// Thread.currentThread().setContextClassLoader(projectClassLoader);
			// }
			//
			// Class.forName("org.hsqldb.jdbcDriver", true, projectClassLoader);
			// } catch (Exception e) {
			// e.printStackTrace();
			//
			// } finally {
			// Thread.currentThread().setContextClassLoader(oldClassLoader);
			// }

			String driver = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_DRIVERCLASS);
			String jars = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_JAR);

			List<URL> urls = new ArrayList<URL>();
			// IClasspathEntry[] entries = javaProject.getRawClasspath();
			// for (int i = 0; i < entries.length; i++) {
			// IClasspathEntry entry = entries[i];
			// switch (entry.getEntryKind()) {
			// case IClasspathEntry.CPE_LIBRARSY: {
			// try {
			// JavaCore.
			// urls.add(entry.getPath().toFile().toURI().toURL());
			// } catch (MalformedURLException e) {
			// e.printStackTrace();
			// }
			// break;
			// }
			// default: {
			// }
			// }
			// }

			// // Unfortunately nested jars are busted, so this wont work.
			// urls.add(DynamicDriverManager.class.getResource(
			// "chaperon.jar" // i.e. dynamicdriver/chaperon.jar
			// ));
			// java.io.File chaperonJar = new java.io.File(
			// "/home/slavic/tmp/backupjasper/runtime-EclipseApplication/test/lib/hsqldb-1.8.0-10.jar");
			java.io.File jarFiles = new java.io.File(jars);
			if (!jarFiles.exists()) {
				throw new ClassNotFoundException(" not found");
			}
			urls.add(jarFiles.toURI().toURL());

			// urls.addAll(locations);
			// Use bootclass loader as parent - don't use application classes.
			ClassLoader loader = java.net.URLClassLoader.newInstance(urls.toArray(new URL[urls.size()]), null);
			Thread.currentThread().setContextClassLoader(loader);

			Class cl = Class.forName("org.hsqldb.jdbcDriver", true, loader);
			try {
				Object obj = cl.newInstance();
				DriverManager.registerDriver((Driver) obj);

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String url = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_JDBC_URL);
			String user = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_USERNAME);
			String pass = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_PASSWORD);

			return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost", user, pass);

		}
		return null;
	}

	public static JRDataSource createFileDataSource(InputStream io, MFileDataSource datasource) {
		JRCsvDataSource jrds = new JRCsvDataSource(io);
		String p = (String) datasource.getPropertyValue(MFileDataSource.PROPERTY_RECORDDELIMITER);
		jrds.setRecordDelimiter(p.replace("\\\\", "\\"));

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
