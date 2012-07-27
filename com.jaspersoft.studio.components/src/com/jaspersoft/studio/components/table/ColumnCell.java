/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.table;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.util.TableUtil;

import org.eclipse.draw2d.geometry.Rectangle;

public class ColumnCell {
	public int type;
	public String grName;
	public BaseColumn column;
	public DesignCell cell;

	public ColumnCell(int type, String grName, BaseColumn column) {
		super();
		this.type = type;
		this.grName = grName;
		this.column = column;
	}

	public void setCell(DesignCell cell) {
		this.cell = cell;
	}

	private Guide north;
	private Guide south;
	private Guide west;
	private Guide east;

	public Guide getWest() {
		return west;
	}

	public void setWest(Guide west) {
		this.west = west;
	}

	public Guide getEast() {
		return east;
	}

	public void setEast(Guide east) {
		this.east = east;
	}

	public Guide getNorth() {
		return north;
	}

	public void setNorth(Guide north) {
		this.north = north;
	}

	public Guide getSouth() {
		return south;
	}

	public void setSouth(Guide south) {
		this.south = south;
	}

	public Rectangle getBounds() {
		int w = east.getY() - west.getY();
		if (south.getY() < north.getY()) {
			Guide tmp = north;
			north = south;
			south = tmp;
		}
		int h = south.getY() - north.getY();
		if (north == null || south == null)
			return new Rectangle(0, 0, w, 10);
		return new Rectangle(west.getY(), north.getY(), w, h);
	}

	@Override
	public String toString() {
		Cell c = TableUtil.getCell(column, type, grName);

		String ctype = column instanceof StandardColumnGroup ? "GR" : "COL";

		String str = "[" + type + ":" + ctype + ":" + hashCode() + "]";
		if (c != null)
			str += "[h:" + c.getHeight() + "]";
		else
			str += "[++++]";
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ColumnCell) {
			ColumnCell toObj = (ColumnCell) obj;
			return this.type == toObj.type && grName.equals(toObj.grName)
					&& this.column == toObj.column;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return grName.hashCode() ^ type ^ column.hashCode();
	}
}
