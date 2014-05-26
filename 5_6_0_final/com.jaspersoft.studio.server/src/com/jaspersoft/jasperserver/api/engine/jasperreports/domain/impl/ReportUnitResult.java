/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperserver.api.engine.jasperreports.domain.impl;

import java.util.Date;

import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.ReportContext;
import net.sf.jasperreports.web.servlets.JasperPrintAccessor;
import net.sf.jasperreports.web.servlets.SimpleJasperPrintAccessor;

import com.jaspersoft.jasperserver.api.engine.common.domain.ReportResult;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: AbstractAttributedObject.java 2140 2006-02-21 06:41:21Z tony $
 */
public class ReportUnitResult implements ReportResult
{
	
	private String requestId;
	private String reportUnitURI;
	private JasperPrintAccessor jasperPrintAccessor;
	private JRVirtualizer virtualizer = null;
	private Date dataTimestamp;
	private ReportContext reportContext;
	private boolean paginated = true;//default

	public ReportUnitResult(
			String reportUnitURI,
			JRVirtualizer virtualizer
			)
	{
		this.reportUnitURI = reportUnitURI;
		this.virtualizer = virtualizer;
	}

	public ReportUnitResult(
			String reportUnitURI,
			JasperPrint jasperPrint,
			JRVirtualizer virtualizer
			)
	{
		this(reportUnitURI, virtualizer);
		this.jasperPrintAccessor = new SimpleJasperPrintAccessor(jasperPrint);
	}

	public ReportUnitResult(
			String reportUnitURI,
			JasperPrintAccessor jasperPrintAccessor,
			JRVirtualizer virtualizer
			)
	{
		this(reportUnitURI, virtualizer);
		this.jasperPrintAccessor = jasperPrintAccessor;
	}
	
	/**
	 * @deprecated use {@link #ReportUnitResult(String, JasperPrint, JRVirtualizer)}
	 * to include the report unit URI as well
	 */
	public ReportUnitResult(
		JasperPrint jasperPrint,
		JRVirtualizer virtualizer
		)
	{
		this(null, jasperPrint, virtualizer);
	}
	
	/**
	 * 
	 * @see #getJasperPrintAccessor()
	 */
	public JasperPrint getJasperPrint()
	{
		// this will wait for the report to finish for async fills
		return jasperPrintAccessor.getFinalJasperPrint();
	}
	
	public JasperPrintAccessor getJasperPrintAccessor()
	{
		return jasperPrintAccessor;
	}
	
	public void setJasperPrintAccessor(JasperPrintAccessor jasperPrintAccessor)
	{
		this.jasperPrintAccessor = jasperPrintAccessor;
	}

	
	/**
	 * 
	 */
	public JRVirtualizer getVirtualizer()
	{
		return virtualizer;
	}

	/**
	 * Returns the URI of the report unit for which this result was created.
	 * 
	 * @return the report unit URI
	 */
	public String getReportUnitURI()
	{
		return reportUnitURI;
	}

	public Date getDataTimestamp() {
		return dataTimestamp;
	}

	public void setDataTimestamp(Date dataTimestamp) {
		this.dataTimestamp = dataTimestamp;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public ReportContext getReportContext() {
		return reportContext;
	}

	public void setReportContext(ReportContext reportContext) {
		this.reportContext = reportContext;
	}

	public boolean isPaginated() {
		return paginated;
	}

	public void setPaginated(boolean paginated) {
		this.paginated = paginated;
	}
	
}
