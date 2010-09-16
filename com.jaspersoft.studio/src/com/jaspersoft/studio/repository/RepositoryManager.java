/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.repository;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import net.sf.jasperreports.eclipse.builder.JasperReportsBuilder;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.MDatasources;
import com.jaspersoft.studio.model.datasource.empty.MEmptyDataSource;
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
		Preferences prefs = new InstanceScope().getNode(JaspersoftStudioPlugin.getUniqueIdentifier());
		try {
			String strDS = "<datasources>";
			for (AMDatasource ds : getDatasources()) {
				try {
					strDS += ds.getToString();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} catch (TransformerException e) {
					e.printStackTrace();
				}
			}
			prefs.put("REPOSITORYDATASOURCES", strDS + "</datasources>");

			prefs.flush();
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	private static MDatasources getDatasources(MRoot root) {
		if (datasources == null) {
			datasources = new MDatasources(root);
			MJDBCDatasources jdbcds = new MJDBCDatasources(datasources);
			MEmptyDatasources emptyds = new MEmptyDatasources(datasources);
			MXMLDatasources xmlds = new MXMLDatasources(datasources);
			MFileDatasources fileds = new MFileDatasources(datasources);

			// new MODADatasources(datasources);
			// new MDTPDatasources(datasources);
			Preferences prefs = new InstanceScope().getNode(JaspersoftStudioPlugin.getUniqueIdentifier());

			String dts = prefs.get("REPOSITORYDATASOURCES", "");
			System.out.println(dts);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			try {
				builder = factory.newDocumentBuilder();
				InputSource is = new InputSource(new StringReader(dts));
				Document doc = builder.parse(is);
				NodeList nl = doc.getElementsByTagName("DATASOURCE");
				for (int i = 0; i < nl.getLength(); i++) {
					Node n = nl.item(i);
					NamedNodeMap nnm = n.getAttributes();
					Node ntype = nnm.getNamedItem("type");
					String type = ntype.getNodeValue();
					AMDatasource m = null;
					if (type.equals(MJDBCDataSource.class.getName()))
						m = new MJDBCDataSource(jdbcds, -1);
					if (type.equals(MEmptyDataSource.class.getName()))
						m = new MEmptyDataSource(emptyds, -1);
					if (type.equals(MFileDataSource.class.getName()))
						m = new MFileDataSource(fileds, -1);
					if (type.equals(MXMLDataSource.class.getName()))
						m = new MXMLDataSource(xmlds, -1);

					if (m != null)
						fillProperties(n, m);
				}

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return datasources;
	}

	private static void fillProperties(Node n, AMDatasource m) {
		NodeList props = n.getChildNodes();
		for (int j = 0; j < props.getLength(); j++) {
			Node cn = props.item(j);
			if (cn.getNodeType() == Node.ELEMENT_NODE) {
				String nodeName = cn.getNodeName();
				Object nodeValue = null;

				NodeList fstNmElmnt = (NodeList) cn.getChildNodes();
				for (int k = 0; k < fstNmElmnt.getLength(); k++) {
					Node node = fstNmElmnt.item(k);
					nodeValue = node.getNodeValue();
				}

				m.setPropertyValue(nodeName, nodeValue);
			}
		}
	}

	private static Map<String, Driver> drivers = new java.util.Hashtable<String, Driver>();

	public static void closeConnection(MJDBCDataSource d) {
		Connection connection = (Connection) d.getPropertyValue(MJDBCDataSource.PROPERTY_CONNECTION);
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection establishConnection(MJDBCDataSource d, IEditorPart editorPart, IProgressMonitor monitor)
			throws Exception {
		Connection connection = (Connection) d.getPropertyValue(MJDBCDataSource.PROPERTY_CONNECTION);
		if (connection == null || connection.isClosed()) {
			String drvClass = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_DRIVERCLASS);
			String url = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_JDBC_URL);
			String user = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_USERNAME);
			String pass = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_PASSWORD);
			String jars = (String) d.getPropertyValue(MJDBCDataSource.PROPERTY_JAR);
			if (drvClass != null && !drvClass.trim().equals("")) {
				Driver driver = drivers.get(drvClass);
				if (driver == null)
					driver = loadJDBCDriver(editorPart, monitor, drvClass, jars);
				Properties info = new Properties();
				if (user != null)
					info.put("user", user);
				if (pass != null)
					info.put("password", pass);
				try {
					connection = driver.connect(url, info);
				} catch (NoClassDefFoundError e) {
					driver = loadJDBCDriver(editorPart, monitor, drvClass, jars);
					connection = driver.connect(url, info);
				}
			}
			d.setPropertyValue(MJDBCDataSource.PROPERTY_CONNECTION, connection);
		}
		return connection;
	}

	private static Driver loadJDBCDriver(IEditorPart editorPart, IProgressMonitor monitor, String drvClass, String jars)
			throws MalformedURLException, CoreException, JavaModelException, ClassNotFoundException, Exception,
			InstantiationException, IllegalAccessException {
		Driver driver;
		ClassLoader loader = null;
		Class<?> cl = null;
		if (jars != null && !jars.trim().equals("")) {
			List<URL> urls = new ArrayList<URL>();
			StringTokenizer st = new StringTokenizer(jars, ";");
			while (st.hasMoreElements()) {
				String jar = st.nextToken();
				java.io.File jarFiles = new java.io.File(jar);
				if (jarFiles.exists())
					urls.add(jarFiles.toURI().toURL());
			}
			if (urls.size() > 0)
				loader = new URLClassLoader(urls.toArray(new URL[urls.size()]));
		}
		if (loader == null) {
			if (editorPart != null) {
				IFileEditorInput input = (IFileEditorInput) editorPart.getEditorInput();
				IFile file = input.getFile();
				loader = getClassLoader4Project(monitor, file.getProject());
			} else { // take all projects
				IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
				for (IProject project : projects) {
					loader = getClassLoader4Project(monitor, project);
					try {
						cl = loader.loadClass(drvClass);
						break;
					} catch (ClassNotFoundException cnfe) {
						cnfe.printStackTrace();
					}
				}
			}
		}
		if (cl == null)
			try {
				cl = loader.loadClass(drvClass);
			} catch (ClassNotFoundException cnfe) {
				cl = Class.forName(drvClass);
			}
		if (!Driver.class.isAssignableFrom(cl))
			throw new Exception("Connection failed. The specified driver class does not implement the "
					+ Driver.class.getName() + " interface.");
		driver = (Driver) cl.newInstance();
		drivers.put(drvClass, driver);
		return driver;
	}

	private static ClassLoader getClassLoader4Project(IProgressMonitor monitor, IProject activeProject)
			throws CoreException, JavaModelException {
		// loader = ReportPreviewUtil.createProjectClassLoader(activeProject);
		if (activeProject.hasNature(JasperReportsBuilder.JAVA_NATURE)) {
			IJavaProject javaProject = JavaCore.create(activeProject);
			javaProject.open(monitor);
			return new JavaProjectClassLoader(javaProject);
		}
		return null;
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
