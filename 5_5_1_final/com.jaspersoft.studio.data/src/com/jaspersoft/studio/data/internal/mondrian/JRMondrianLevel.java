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

import com.jaspersoft.studio.data.internal.olap.JROlapHierarchyLevel;

import mondrian.olap.Level;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRMondrianLevel.java 5879 2013-01-07 20:35:36Z teodord $
 */
public class JRMondrianLevel implements JROlapHierarchyLevel
{

	private Level level = null;
	private org.olap4j.metadata.Level olap4jLevel = null;
	
	public JRMondrianLevel(Level level)
	{
		this.level = level;
		this.olap4jLevel = null;
	}
	
	public JRMondrianLevel(org.olap4j.metadata.Level olap4jLevel)
	{
		this.level = null;
		this.olap4jLevel = olap4jLevel;
	}

	public int getDepth()
	{
		if (level != null) {
			return level.getDepth();
		} else {
			return olap4jLevel.getDepth();
		}
	}

	public String getName()
	{
		if (level != null) {
			return level.getName();
		} else {
			return olap4jLevel.getName();
		}
	}

}
