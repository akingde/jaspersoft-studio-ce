/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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

import net.sf.jasperreports.charts.JRXySeries;
import net.sf.jasperreports.charts.design.JRDesignXySeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class XySerie implements ISeriesFactory<JRXySeries> {

	public JRDesignXySeries createSerie() {
		return createSerie(new JRDesignExpression("\"SERIE 1\""), null);
	}

	@Override
	public JRDesignXySeries createSerie(JRDesignExpression expr, JRXySeries prev) {
		JRDesignXySeries f = new JRDesignXySeries();
		f.setAutoSort(true);
		f.setSeriesExpression(expr);
		if (prev == null) {
			f.setXValueExpression(new JRDesignExpression("0"));
			f.setYValueExpression(new JRDesignExpression("0"));
		} else {
			f.setXValueExpression(prev.getXValueExpression());
			f.setYValueExpression(prev.getYValueExpression());
			f.setLabelExpression(prev.getLabelExpression());
		}
		return f;
	}

	public String getColumnText(Object element, int columnIndex) {
		JRXySeries dcs = (JRXySeries) element;
		switch (columnIndex) {
		case 0:
			if (dcs.getSeriesExpression() != null
					&& dcs.getSeriesExpression().getText() != null)
				return dcs.getSeriesExpression().getText();
		}
		return ""; //$NON-NLS-1$
	}

	public Object getValue(JRXySeries element, String property) {
		JRXySeries prop = (JRXySeries) element;
		if ("NAME".equals(property)) { //$NON-NLS-1$
			return prop.getSeriesExpression();
		}
		return ""; //$NON-NLS-1$
	}

	public void modify(JRXySeries element, String property, Object value) {
		JRDesignXySeries data = (JRDesignXySeries) element;
		if ("NAME".equals(property) && value instanceof JRExpression) //$NON-NLS-1$
			data.setSeriesExpression((JRExpression) value);
	}

	private List<JRXySeries> vlist;

	public List<JRXySeries> getList() {
		return vlist;
	}

	public void setList(List<JRXySeries> vlist) {
		this.vlist = new ArrayList<JRXySeries>(vlist);
	}
}
