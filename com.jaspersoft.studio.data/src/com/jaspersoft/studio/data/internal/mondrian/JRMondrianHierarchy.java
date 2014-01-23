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

import org.olap4j.metadata.NamedList;

import com.jaspersoft.studio.data.internal.olap.JROlapHierarchy;
import com.jaspersoft.studio.data.internal.olap.JROlapHierarchyLevel;

import mondrian.olap.Hierarchy;
import mondrian.olap.Level;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRMondrianHierarchy.java 5879 2013-01-07 20:35:36Z teodord $
 */
public class JRMondrianHierarchy implements JROlapHierarchy
{
	
	private final Hierarchy hierarchy;
	private final org.olap4j.metadata.Hierarchy olap4jhierarchy;
	private final JRMondrianLevel[] levels;
	private String uniqueName = null;
	
	public JRMondrianHierarchy(String name) {
		uniqueName = name;
		levels = new JRMondrianLevel[0];
		hierarchy = null;
		this.olap4jhierarchy = null;
	}
	
	public JRMondrianHierarchy(org.olap4j.metadata.Hierarchy olap4jhierarchy)
	{
		this.hierarchy = null;
		this.olap4jhierarchy = olap4jhierarchy;
		
		if (olap4jhierarchy == null)
		{
			levels = new JRMondrianLevel[0];
		}
		else
		{
			NamedList<org.olap4j.metadata.Level> hierarchyLevels = olap4jhierarchy.getLevels();
			levels = new JRMondrianLevel[hierarchyLevels.size()];
			for (int i = 0; i < hierarchyLevels.size(); i++)
			{
				levels[i] = new JRMondrianLevel(hierarchyLevels.get(i));
			}
		}
	}
	
	public JRMondrianHierarchy(Hierarchy hierarchy)
	{
		this.hierarchy = hierarchy;
		this.olap4jhierarchy = null;
		
		if (hierarchy == null)
		{
			levels = new JRMondrianLevel[0];
		}
		else
		{
			Level[] hierarchyLevels = hierarchy.getLevels();
			levels = new JRMondrianLevel[hierarchyLevels.length];
			for (int i = 0; i < hierarchyLevels.length; i++)
			{
				levels[i] = new JRMondrianLevel(hierarchyLevels[i]);
			}
		}
	}

	public String getDimensionName()
	{
		if (hierarchy != null) {
			return hierarchy.getDimension().getName();
		} else if (olap4jhierarchy != null) {
			return olap4jhierarchy.getDimension().getName();
		} else {
			return uniqueName;
		}
	}

	public JROlapHierarchyLevel[] getLevels()
	{
		return levels;
	}

	// MPenningroth 21-April-2009 deal with case when dimension is <dimension>.<hierarchy> form
	public String getHierarchyUniqueName()
	{
		if (hierarchy != null) {
			return hierarchy.getUniqueName();
		} else if (olap4jhierarchy != null) {
			return olap4jhierarchy.getUniqueName();
		} else {
			return uniqueName;
		}
	}

	public boolean matchDimensionName(String dimensionNameToTest) {
		if (dimensionNameToTest.equals(getDimensionName()) || dimensionNameToTest.equals(getHierarchyUniqueName())) {
			return true;
		} else {
	
			// MPenningroth 21-April-2009 deal with case when dimension is <dimension>.<hierarchy> form
			String hierName = "[" + dimensionNameToTest + "]";
			return hierName.equals(getHierarchyUniqueName());
		}
	}
}
