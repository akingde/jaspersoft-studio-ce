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
package com.jaspersoft.studio.data.sample;

import java.util.Vector;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
*
* @author gtoffoli
*/
public class SampleJRDataSourceFactory {
	
	// This is the method to call to get the datasource.
	// The method must be static.....    
	public JRDataSource createDatasource()
	{
		javax.swing.table.DefaultTableModel tm = new javax.swing.table.DefaultTableModel(4,2);
       
        PersonBean person = new PersonBean();
        person.setFirstName("Giulio");
        person.setLastName("Toffoli");
        person.setEmail("gt@businesslogic.it");
        tm.setValueAt(person, 0, 0);
        tm.setValueAt("Test value row 1 col 1", 0, 1);
       
        person = new PersonBean();
        person.setFirstName("Teodor");
        person.setLastName("Danciu");
        person.setEmail("teodor@hotmail.com");
        tm.setValueAt(person, 1, 0);
        tm.setValueAt("Test value row 2 col 1", 1, 1);
       
        person = new PersonBean();
        person.setFirstName("Mario");
        person.setLastName("Rossi");
        person.setEmail("mario@rossi.org");
        tm.setValueAt(person, 2, 0);
        tm.setValueAt("Test value row 3 col 1", 2, 1);
       
        person = new PersonBean();
        person.setFirstName("Jennifer");
        person.setLastName("Lopez");
        person.setEmail("lopez@jennifer.com");
        tm.setValueAt(person, 3, 0);
        tm.setValueAt("Test value row 4 col 1", 3, 1);
       
        return new JRTableModelDataSource(tm);
    }
	
	public JRDataSource createBeanCollectionDatasource()
    {
        return new JRBeanCollectionDataSource(createBeanCollection());
    }    
    
    public static Vector<?> createBeanCollection()
    {
    	java.util.Vector<PersonBean> coll = new java.util.Vector<PersonBean>();
       
        PersonBean person = new PersonBean();
        person.setFirstName("Giulio");
        person.setLastName("Toffoli");
        person.setEmail("gt@businesslogic.it");
        coll.add(person);
       
        person = new PersonBean();
        person.setFirstName("Teodor");
        person.setLastName("Danciu");
        person.setEmail("teodor@hotmail.com");
        coll.add(person);
       
        person = new PersonBean();
        person.setFirstName("Mario");
        person.setLastName("Rossi");
        person.setEmail("mario@rossi.org");
        coll.add(person);
       
        person = new PersonBean();
        person.setFirstName("Jennifer");
        person.setLastName("Lopez");
        person.setEmail("lopez@jennifer.com");
        coll.add(person);
   
        return coll;
    }
}
