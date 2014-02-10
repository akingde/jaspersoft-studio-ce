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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.olap4j.CellSetAxis;

import com.jaspersoft.studio.data.internal.olap.JROlapHierarchy;
import com.jaspersoft.studio.data.internal.olap.JROlapMemberTuple;
import com.jaspersoft.studio.data.internal.olap.JROlapResultAxis;

import mondrian.olap.Axis;
import mondrian.olap.Hierarchy;
import mondrian.olap.Position;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRMondrianAxis.java 5879 2013-01-07 20:35:36Z teodord $
 */
public class JRMondrianAxis implements JROlapResultAxis
{
	
	private List<JRMondrianTuple> tuples;
	private List<JRMondrianHierarchy> hierarchies;
	private JRMondrianHierarchy[] hierarchyArray = null;
	private String axisName = "";
	
	public JRMondrianAxis(CellSetAxis axis,
			List<org.olap4j.metadata.Hierarchy> axisHierarchies,
			JRMondrianFactory factory)
	{
		this.hierarchies = new ArrayList<JRMondrianHierarchy>();
		this.tuples = new ArrayList<JRMondrianTuple>();
		List<org.olap4j.Position> positions = axis.getPositions();
		
		for (Iterator<org.olap4j.Position> it = positions.iterator(); it.hasNext(); )
		{
			tuples.add(new JRMondrianTuple(it.next(), factory));
		}
		
		this.hierarchies = new ArrayList<JRMondrianHierarchy>();
		for (Iterator<org.olap4j.metadata.Hierarchy> it = axisHierarchies.iterator(); it.hasNext(); )
		{
			hierarchies.add(new JRMondrianHierarchy(it.next()));
		}
	}
	
	public JRMondrianAxis(Axis axis, Hierarchy[] axisHierarchies, JRMondrianFactory factory)
	{
		axisName = "";
		List<Position> positions = axis.getPositions();
		this.tuples = new ArrayList<JRMondrianTuple>();
		
		for (Iterator<Position> it = positions.iterator(); it.hasNext(); )
		{
			Position position = it.next();
			tuples.add(new JRMondrianTuple(position, factory));
		}
		
		this.hierarchies = new ArrayList<JRMondrianHierarchy>();
		for (int i = 0; i < axisHierarchies.length; i++)
		{
			hierarchies.add(new JRMondrianHierarchy(axisHierarchies[i]));
		}
	}
	
	public String getAxisName()
	{
		return axisName;
	}

	public JROlapHierarchy[] getHierarchiesOnAxis()
	{
		return ensureHierarchyArray();
	}

/*	public JROlapHierarchy[] getHierarchiesOnAxis()
	{
		return hierarchyArray;
	}
*/
	public JROlapMemberTuple getTuple(int index)
	{
		if (index < 0 || index >= tuples.size())
		{
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + tuples.size());
		}
		
		return tuples.get(index);
	}

	public int getTupleCount()
	{
		return tuples.size();
	}

	protected JRMondrianHierarchy[] ensureHierarchyArray()
	{
		if (hierarchyArray == null)
		{
			hierarchyArray = new JRMondrianHierarchy[hierarchies.size()];
			hierarchyArray = hierarchies.toArray(hierarchyArray);
		}
		return hierarchyArray;
	}
/*
	public void addHierarchy(JRMondrianHierarchy hierarchy)
	{
		hierarchies.add(hierarchy);
		resetHierarchyArray();
	}
	
	public void addTuple(JRMondrianTuple tuple)
	{
		tuples.add(tuple);
		
		copyLevelInfo(tuple);
	}

	protected void copyLevelInfo(JRMondrianTuple tuple)
	{
		JROlapMember[] members = tuple.getMembers();
		int idx = 0;
		for (Iterator<JRMondrianHierarchy> it = hierarchies.iterator(); it.hasNext() && idx < members.length; ++idx)
		{
			JRMondrianHierarchy hierarchy = it.next();
			JROlapMember member = members[idx];
			if (hierarchy.getDimensionName().equals(member.getDimensionName()))
			{
				hierarchy.setLevel(member.getLevelName(), member.getDepth());
			}
		}
		
	}

	protected void resetHierarchyArray()
	{
		hierarchyArray = null;
	}

*/}
