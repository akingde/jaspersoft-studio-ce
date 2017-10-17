/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.xlsx;

import com.jaspersoft.studio.data.xls.XLSCreator;

/**
 * Creator to build a JSS XLSX data adapter from the xml definition of an iReport XLSX 
 * data adapter. The XLS and XLSX are identical as XML definition, so this creator redefine
 * only the id
 * 
 * @author Orlandin Marco
 */
public class XLSXCreator extends XLSCreator {

	@Override
	public String getID() {
		return "com.jaspersoft.ireport.designer.connection.JRXlsxDataSourceConnection";
	}
}
