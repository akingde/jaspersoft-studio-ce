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
import java.util.List;

import mondrian.olap.Axis;
import mondrian.olap.AxisOrdinal;
import mondrian.olap.Cell;
import mondrian.olap.Query;
import mondrian.olap.Result;

import org.olap4j.CellSet;
import org.olap4j.CellSetAxis;

import com.jaspersoft.studio.data.internal.olap.JROlapCell;
import com.jaspersoft.studio.data.internal.olap.JROlapResult;
import com.jaspersoft.studio.data.internal.olap.JROlapResultAxis;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRMondrianResult.java 5879 2013-01-07 20:35:36Z teodord $
 */
public class JRMondrianResult implements JROlapResult
{
	
	private Result result = null;
	private CellSet cellSet = null;
	private JRMondrianAxis[] axes;

	public JRMondrianResult(CellSet cSet) {
		this.cellSet = cSet;
		
		JRMondrianFactory factory = new JRMondrianFactory();

		List<CellSetAxis> resultAxes = cellSet.getAxes();
		axes = new JRMondrianAxis[cellSet.getAxes().size()];
		for (int i = 0; i < cellSet.getAxes().size(); i++)
		{
			// AxisOrdinal ordinal = AxisOrdinal.StandardAxisOrdinal.forLogicalOrdinal(i);
			axes[i] = new JRMondrianAxis(cellSet.getAxes().get(i),
					cellSet.getAxes().get(i).getAxisMetaData().getHierarchies(),
					factory);
		}
	}

	public JRMondrianResult(Result result)
	{
		this.result = result;

		JRMondrianFactory factory = new JRMondrianFactory();

		Query query = result.getQuery();
		Axis[] resultAxes = result.getAxes();
		axes = new JRMondrianAxis[resultAxes.length];
		for (int i = 0; i < resultAxes.length; i++)
		{
			AxisOrdinal ordinal = AxisOrdinal.StandardAxisOrdinal.forLogicalOrdinal(i);
			axes[i] = new JRMondrianAxis(resultAxes[i], query.getMdxHierarchiesOnAxis(ordinal), factory);
		}
	}

	public JROlapResultAxis[] getAxes()
	{
		return axes;
	}

	public JROlapCell getCell(int[] axisPositions)
	{
		if (result != null) {
			Cell dataCell = result.getCell(axisPositions);
			return new JRMondrianCell(dataCell);
		} else {
			List<Integer> positions = new ArrayList<Integer>(axisPositions.length);
			for (int index = 0; index < axisPositions.length; index++)
		    {
				positions.add(axisPositions[index]);
		    } 
			org.olap4j.Cell dataCell = cellSet.getCell(positions);
			return new JRMondrianCell(dataCell);
		}
	}
}
