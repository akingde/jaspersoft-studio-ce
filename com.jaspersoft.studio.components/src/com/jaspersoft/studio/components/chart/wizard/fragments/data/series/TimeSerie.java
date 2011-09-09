package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRTimeSeries;
import net.sf.jasperreports.charts.design.JRDesignTimeSeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class TimeSerie implements ISeriesFactory<JRTimeSeries> {

	public Object createSerie() {
		JRDesignTimeSeries f = new JRDesignTimeSeries();
		f.setSeriesExpression(new JRDesignExpression());
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
