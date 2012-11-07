/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRPieSeries;
import net.sf.jasperreports.charts.design.JRDesignPieSeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class PieSerie implements ISeriesFactory<JRPieSeries> {

	public JRDesignPieSeries createSerie() {
		return createSerie(new JRDesignExpression("\"SERIE 1\""));
	}

	@Override
	public JRDesignPieSeries createSerie(JRDesignExpression expr) {
		JRDesignPieSeries f = new JRDesignPieSeries();
		f.setKeyExpression(expr);
		f.setValueExpression(new JRDesignExpression("new Double(0)"));
		return f;
	}

	public String getColumnText(Object element, int columnIndex) {
		JRPieSeries dcs = (JRPieSeries) element;
		switch (columnIndex) {
		case 0:
			if (dcs.getKeyExpression() != null
					&& dcs.getKeyExpression().getText() != null)
				return dcs.getKeyExpression().getText();
		}
		return ""; //$NON-NLS-1$
	}

	public Object getValue(Object element, String property) {
		JRDesignPieSeries prop = (JRDesignPieSeries) element;
		if ("NAME".equals(property)) { //$NON-NLS-1$
			return prop.getKeyExpression();
		}
		return ""; //$NON-NLS-1$
	}

	public void modify(Object element, String property, Object value) {
		TableItem tableItem = (TableItem) element;
		JRDesignPieSeries data = (JRDesignPieSeries) tableItem.getData();
		if ("NAME".equals(property) && value instanceof JRExpression) {//$NON-NLS-1$
			data.setKeyExpression((JRExpression) value);
		}

	}

	private List<JRPieSeries> vlist;

	public List<JRPieSeries> getList() {
		return vlist;
	}

	public void setList(List<JRPieSeries> vlist) {
		this.vlist = new ArrayList<JRPieSeries>(vlist);
	}
}
