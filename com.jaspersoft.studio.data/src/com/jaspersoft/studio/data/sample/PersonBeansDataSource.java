/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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
