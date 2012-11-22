/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRTimeSeries;
import net.sf.jasperreports.charts.design.JRDesignTimeSeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class TimeSerie implements ISeriesFactory<JRTimeSeries> {

	public JRDesignTimeSeries createSerie() {
		return createSerie(new JRDesignExpression("\"SERIE 1\""));
	}

	@Override
	public JRDesignTimeSeries createSerie(JRDesignExpression expr) {
		JRDesignTimeSeries f = new JRDesignTimeSeries();
		f.setSeriesExpression(expr);
		f.setTimePeriodExpression(new JRDesignExpression("new Double(0)"));
		f.setValueExpression(new JRDesignExpression("new Double(0)"));
		return f;
	}

	public String getColumnText(Object element, int columnIndex) {
		JRTimeSeries dcs = (JRTimeSeries) element;
		switch (columnIndex) {
		case 0:
			if (dcs.getSeriesExpression() != null
					&& dcs.getSeriesExpression().getText() != null)
				return dcs.getSeriesExpression().getText();
		}
		return ""; //$NON-NLS-1$
	}

	public Object getValue(Object element, String property) {
		JRTimeSeries prop = (JRTimeSeries) element;
		if ("NAME".equals(property)) { //$NON-NLS-1$
			return prop.getSeriesExpression();
		}
		return ""; //$NON-NLS-1$
	}

	public void modify(Object element, String property, Object value) {
		TableItem tableItem = (TableItem) element;
		JRDesignTimeSeries data = (JRDesignTimeSeries) tableItem.getData();
		if ("NAME".equals(property) && value instanceof JRExpression) {//$NON-NLS-1$
			data.setSeriesExpression((JRExpression) value);
		}

	}

	private List<JRTimeSeries> vlist;

	public List<JRTimeSeries> getList() {
		return vlist;
	}

	public void setList(List<JRTimeSeries> vlist) {
		this.vlist = new ArrayList<JRTimeSeries>(vlist);
	}
}
