/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package com.jaspersoft.jasperserver.api.engine.common.domain;

import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.web.servlets.JasperPrintAccessor;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportResult.java 30437 2013-03-28 17:54:52Z lchirita $
 */
public interface ReportResult extends Result {
	
	String getRequestId();
	
	JasperPrintAccessor getJasperPrintAccessor();
	
	JRVirtualizer getVirtualizer();

}
