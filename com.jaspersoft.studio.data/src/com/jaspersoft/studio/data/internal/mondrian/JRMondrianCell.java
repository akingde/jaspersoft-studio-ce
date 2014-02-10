/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.internal.mondrian;

import com.jaspersoft.studio.data.internal.olap.JROlapCell;

import mondrian.olap.Cell;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRMondrianCell.java 5879 2013-01-07 20:35:36Z teodord $
 */
public class JRMondrianCell implements JROlapCell
{

	private final Cell cell;
	private final org.olap4j.Cell olap4jCell;
	private String formattedValue;
	private Object value;
	private boolean isError = false;
	
	public JRMondrianCell(Cell cell)
	{
		this.cell = cell;
		this.olap4jCell = null;
		this.value = this.cell.getValue();
		this.formattedValue = this.cell.getFormattedValue();
	}
	
	public JRMondrianCell(org.olap4j.Cell olap4jCell)
	{
		this.cell = null;
		this.olap4jCell = olap4jCell;
		this.value = this.olap4jCell.getValue();
		this.formattedValue = this.olap4jCell.getFormattedValue();
	}

	public String getFormattedValue()
	{
		return formattedValue;
	}

	public Object getValue()
	{
		return value;
	}

	public boolean isError()
	{
		return isError;
	}

	public boolean isNull()
	{
		return value == null;
	}

}
