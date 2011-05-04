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
package com.jaspersoft.studio.data.jrdsprovider;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JasperReport;

import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.jface.dialogs.DataAdapterErrorDialog;

public class JrdsProviderDataAdapter extends DataAdapter {

	private ClassLoader JRDSProviderClassLoader = null;
	private JRDataSourceProvider dsp;
	private JRDataSource ds;
	private String jrdsProviderClassNameKey = "JRDataSourceProvider";
	private String jrdsProviderClassName = "";

	public DataAdapterEditor getEditor() {
		return new JrdsProviderDataAdapterEditor();
	}

	public Map<String, String> getProperties() {
		
		Map<String, String> map = super.getProperties();
		map.put(jrdsProviderClassNameKey, getJRDSProviderClassName());
		
		return map;
	}

	public void loadProperties(Map<String, String> map) {
		
		setJRDSProviderClassName(map.get(jrdsProviderClassNameKey));
	}

	public JRDataSource getJRDataSource() {
		return this.getJRDataSource(null);
	}
	
	public JRDataSource getJRDataSource(JasperReport jasper) {
		
		if (ds != null) {
			String message = "This data source is already in use by another filling process!!";
			DataAdapterErrorDialog.showErrorDialog( Display.getCurrent().getActiveShell(), message, null);
			System.out.println(message);
			return null;
		}
		
		try {
			ds = getDataSourceProvider().create(jasper);
		} catch (Exception e) {
			String message = "Problems occurred creating the new data source!!";
			DataAdapterErrorDialog.showErrorDialog( Display.getCurrent().getActiveShell(), message, e);
			System.out.println(message);
			e.printStackTrace();
		}
		
		return ds;
	}
	
	private JRDataSourceProvider getDataSourceProvider() {
		
		if (dsp == null && (jrdsProviderClassName != null && jrdsProviderClassName.length() > 0) ) {
			try {
				
				dsp = (JRDataSourceProvider)(Class.forName( jrdsProviderClassName ).newInstance());
			
			} catch (NoClassDefFoundError e) {
				System.out.println("No class definition found error!!\nCheck your classpath! " + e.getMessage());
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found error!!\nCheck your classpath! " + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dsp;
	}

	public void test() throws Exception {
		
		Class testDsp = Class.forName( jrdsProviderClassName );
		
		if (!JRDataSourceProvider.class.isAssignableFrom(testDsp)) {
			throw new Exception(jrdsProviderClassName + " is not a subclass of net.sf.jasperreports.engine.JRDataSourceProvider");
		}
	}
	
	public ClassLoader getJRDSProviderClassLoader() {
		return getJRDSProviderClassLoader(false);
	}

	public ClassLoader getJRDSProviderClassLoader(boolean reload) {
		
		if (JRDSProviderClassLoader == null || reload) {
			
			List<URL> urls = new ArrayList<URL>();
			List<String> classpathPaths = new ArrayList<String>();
			//classpathPaths.add(getJRDSProviderClassUrl());
			for (String cp : classpathPaths)
			{
				File f = new File(cp);
				if (f.exists())
				{
					try {
						urls.add( f.toURI().toURL());
					} catch (MalformedURLException e) {
						//e.printStackTrace();
						// We don't care if the entry cannot be found.
					}
				}
			}
			
			JRDSProviderClassLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]));
		}
		
		return JRDSProviderClassLoader;
	}

	
	/*
	 * GETTERS AND SETTERS
	 */
	public String getJRDSProviderClassName() {
		return jrdsProviderClassName;
	}

	public void setJRDSProviderClassName(String jrdsProviderClassName) {
		this.jrdsProviderClassName = jrdsProviderClassName;
	}
}
