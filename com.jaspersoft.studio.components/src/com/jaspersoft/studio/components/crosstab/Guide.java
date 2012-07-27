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
package com.jaspersoft.studio.components.crosstab;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.crosstabs.JRCellContents;

public class Guide {
	private int y;
	private List<CrosstabCell> prev = new ArrayList<CrosstabCell>();
	private List<CrosstabCell> next = new ArrayList<CrosstabCell>();

	public Guide(int y) {
		this.y = y;
	}

	public void setNext(List<CrosstabCell> next) {
		this.next = next;
	}

	public void setPrev(List<CrosstabCell> prev) {
		this.prev = prev;
	}

	public List<CrosstabCell> getPrev() {
		return prev;
	}

	public List<CrosstabCell> getNext() {
		return next;
	}

	public void addWest(CrosstabCell cell) {
		cell.setEast(this);
		prev.add(cell);
	}

	public void addEast(CrosstabCell cell) {
		cell.setWest(this);
		next.add(cell);
	}

	public void addNorth(CrosstabCell cell) {
		cell.setSouth(this);
		prev.add(cell);
	}

	public void addSouth(CrosstabCell cell) {
		cell.setNorth(this);
		next.add(cell);
	}

	public void mirror() {
		switchCells(next);
		switchCells(prev);

		List<CrosstabCell> tmp = next;
		next = prev;
		prev = tmp;
	}

	private void switchCells(List<CrosstabCell> cols) {
		for (CrosstabCell cc : cols) {
			Guide n = cc.getNorth();
			Guide s = cc.getSouth();
			cc.setSouth(n);
			cc.setNorth(s);
		}
	}

	public int getY() {
		return y;
	}

	public void setY(Guide north, JRCellContents c) {
		if (c != null)
			y = Math.max(y, north.getY() + c.getHeight());
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		StringBuffer res = new StringBuffer();
		res.append("y: " + y + " NORTH");
		for (CrosstabCell cc : prev)
			res.append(cc.toString());
		res.append("\nSOUTH");
		for (CrosstabCell cc : next)
			res.append(cc.toString());
		return res.toString();
	}

}
