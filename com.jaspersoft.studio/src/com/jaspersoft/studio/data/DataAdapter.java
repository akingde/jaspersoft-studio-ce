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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data;

import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import net.sf.jasperreports.engine.JasperReport;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.ui.DefaultDataAdapterEditor;
import com.jaspersoft.studio.model.util.IIconDescriptor;
/*
 *
 * @author  gtoffoli
 */
public abstract class DataAdapter implements IIconDescriptor {
    
  private String name = "";
  
  /*
   * Return true if this connection is a "Connection" to a database
   * I.E. you can see JDBCConnection
   */
  public boolean isJDBCConnection(){ return false; }
  
  
  /*
   * Return true if this ireport connection can be used using getJRDataSource
   * I.E. you can see JDBCConnection
   */
  public boolean isJRDataSource() { return true; }
  
  /**
   * Coming from the IIconDescriptor interface.
   * Just returns the name of this data adapter
   */
  public String getTitle() {
  	if (getName() == null || getName().length() == 0)
  	{
  		return "<unnamed>";
  	}
		return getName();
	}

  /**
   * Coming from the IIconDescriptor interface.
   * Return null;
   */
	public String getDescription() {
		return null;
	}

	/**
   * Coming from the IIconDescriptor interface.
   * Return null;
   */
	public String getToolTip() {
		return null;
	}

	/**
   * Coming from the IIconDescriptor interface.
   * Return a default dataAdapter icon;
   */
	public ImageDescriptor getIcon16() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(JaspersoftStudioPlugin.getUniqueIdentifier(),"icons/data_source.gif");
	}

	/**
   * Coming from the IIconDescriptor interface.
   * Return a default dataAdapter icon;
   */
	public ImageDescriptor getIcon32() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(JaspersoftStudioPlugin.getUniqueIdentifier(),"icons/resources/dataset-32.png");
	}


	/**
    *  This method return an instanced connection to the database.
    *  If isJDBCConnection() return false => getConnection() return null
    */
  public java.sql.Connection getConnection(){ return null; }

  /**
   *  This method return an instanced JRDataDource to the database.
   *  It just calls getJRDataSource(), but can be redefined in special
   *  connections.
   */
  public net.sf.jasperreports.engine.JRDataSource getJRDataSource(JasperReport jasper) {
  	return getJRDataSource();
  }

  /**
   *  This method return an instanced JRDataDource to the database.
   *  If isJDBCConnection() return true => getJRDataSource() should return false
   */
  public net.sf.jasperreports.engine.JRDataSource getJRDataSource() { return new net.sf.jasperreports.engine.JREmptyDataSource(); }
    
  /** Getter for property name.
   * @return Value of property name.
   *
   */
  public java.lang.String getName() {
  	return name;
  }
    
  /** Setter for property name.
   * @param name New value of property name.
   *
   */
  public void setName(java.lang.String name) {
  	this.name = name;
  }
    
    
  /** All properties of an IReportConnection are stored in a XML file as Pair key/value
   *  This HashMap must contain all the properties that the IReportConnection must save in the
   *  XML.
   *  IReport will store the content of this HashMap in the XML. Please note that all the values
   *  and keys will be casted to String!
   */
  public java.util.Map<String, String> getProperties()
  {
  	return new java.util.HashMap<String, String>();
  }
  
  /** All properties of a IReportConnection are stored in a XML file as Pair key/value
   *  This HashMap contains all the properties found for this IReportConnection in the
   *  XML. You must use this hashMap to initialize all attributes of your IReprotConnection
   */
  public void loadProperties(java.util.Map<String, String> map)
  {
  }
  
  /** 
   *  It just write a portion of XML to persiste the data adapter
   *  it is used by the DataAdapterManager and can be used to export this data adapter too.
   */
  public final String toXml()
  {
    java.util.Map<String,String> hm = this.getProperties();
    StringBuffer xml = new StringBuffer();
    xml.append("\t<dataAdapter name=\""+ this.getName() +"\" class=\"" + this.getClass().getName() +"\">\n");
    java.util.Iterator<String> iterator = hm.keySet().iterator();
    
    while (iterator.hasNext())
    {
      String key = iterator.next();
      if (key == null || hm.get(key) == null) continue;
      xml.append("\t\t<parameter name=\""  +  key + "\"><![CDATA[" + hm.get(key) + "]]></parameter>\n");
    }
    xml.append("\t</dataAdapter>\n");
        
    return xml.toString();
  }    
    
  public String toString()
  {
    return getName();
  }
   
  /**
   * This method is call before the adapter is used and permits to add special parameters to the map
   *
   */
  public Map getSpecialParameters(Map map) throws net.sf.jasperreports.engine.JRException
  {
  	return map;
  }
  
  /**
   * This method is call after the datasource is used to dispose special parameters
   * (i.e. closing an Hibernate session create as parameter with a getSpecialParameters...
   *
   */
  public Map disposeSpecialParameters(Map map)
  {
  	return map;
  }
  
  
  /**
   * This method is used to test the configuration. To throw an exception if the test fails is not mandatory
   * and anyway the exception will be ignored.
   * The method is responsible to show error messages it the test has success or fails!!
   *
   */
  public void test() throws Exception  {
    //JOptionPane.showMessageDialog( Utilities. ,I18n.getString("messages.connectionDialog.connectionTestSuccessful","Connection test successful!"),"",JOptionPane.INFORMATION_MESSAGE);
    return;
  }
  
  
  /**
   * This method is used to provide to the datasources window the GUI to configure this kind of component.
   * 
   */
  public DataAdapterEditor getEditor()
  {
    //return new BasicDataAdapterEditor();
  	return new DefaultDataAdapterEditor();
  }
}

