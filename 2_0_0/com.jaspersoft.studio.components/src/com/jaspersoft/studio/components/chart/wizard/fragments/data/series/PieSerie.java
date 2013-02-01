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
