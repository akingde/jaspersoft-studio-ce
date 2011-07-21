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

import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


public class PersonBeansDataSource extends JRAbstractBeanDataSourceProvider {

	public PersonBeansDataSource() {
		super(PersonBean.class);
	}

	public JRDataSource create(JasperReport arg0) throws JRException {
		
		ArrayList<PersonBean> list = new ArrayList<PersonBean>();
		list.add(new PersonBean("Chengan"));
		list.add(new PersonBean("Giulio"));
		list.add(new PersonBean("Slavic"));
		list.add(new PersonBean("Teodor"));
		
		return new JRBeanCollectionDataSource(list);
	}
	
	public void dispose(JRDataSource arg0) throws JRException {
		// nothing to do
	}
}
