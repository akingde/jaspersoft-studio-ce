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

import net.sf.jasperreports.charts.JRXyzSeries;
import net.sf.jasperreports.charts.design.JRDesignXyzSeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class XyzSerie implements ISeriesFactory<JRXyzSeries> {

	public JRDesignXyzSeries createSerie() {
		return createSerie(new JRDesignExpression("\"SERIE 1\""));
	}

	@Override
	public JRDesignXyzSeries createSerie(JRDesignExpression expr) {
		JRDesignXyzSeries f = new JRDesignXyzSeries();
		f.setSeriesExpression(expr);
		f.setXValueExpression(new JRDesignExpression("new Double(0)"));
		f.setYValueExpression(new JRDesignExpression("new Double(0)"));
		f.setZValueExpression(new JRDesignExpression("new Double(0)"));
		return f;
	}

	public String getColumnText(Object element, int columnIndex) {
		JRXyzSeries dcs = (JRXyzSeries) element;
		switch (columnIndex) {
		case 0:
			if (dcs.getSeriesExpression() != null
					&& dcs.getSeriesExpression().getText() != null)
				return dcs.getSeriesExpression().getText();
		}
		return ""; //$NON-NLS-1$
	}

	public Object getValue(Object element, String property) {
		JRXyzSeries prop = (JRXyzSeries) element;
		if ("NAME".equals(property)) { //$NON-NLS-1$
			return prop.getSeriesExpression();
		}
		return ""; //$NON-NLS-1$
	}

	public void modify(Object element, String property, Object value) {
		TableItem tableItem = (TableItem) element;
		JRDesignXyzSeries data = (JRDesignXyzSeries) tableItem.getData();
		if ("NAME".equals(property) && value instanceof JRExpression) {//$NON-NLS-1$
			data.setSeriesExpression((JRExpression) value);
		}

	}

	private List<JRXyzSeries> vlist;

	public List<JRXyzSeries> getList() {
		return vlist;
	}

	public void setList(List<JRXyzSeries> vlist) {
		this.vlist = new ArrayList<JRXyzSeries>(vlist);
	}
}
