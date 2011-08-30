package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.List;

import net.sf.jasperreports.charts.JRTimePeriodSeries;
import net.sf.jasperreports.charts.design.JRDesignTimePeriodSeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class TimePeriodSerie implements ISeriesFactory<JRTimePeriodSeries> {

	public Object createSerie() {
		JRDesignTimePeriodSeries f = new JRDesignTimePeriodSeries();
		f.setSeriesExpression(new JRDesignExpression());
		return f;
	}

	public String getColumnText(Object element, int columnIndex) {
		JRTimePeriodSeries dcs = (JRTimePeriodSeries) element;
		switch (columnIndex) {
		case 0:
			if (dcs.getSeriesExpression() != null
					&& dcs.getSeriesExpression().getText() != null)
				return dcs.getSeriesExpression().getText();
		}
		return ""; //$NON-NLS-1$
	}

	public Object getValue(Object element, String property) {
		JRTimePeriodSeries prop = (JRTimePeriodSeries) element;
		if ("NAME".equals(property)) { //$NON-NLS-1$
			return prop.getSeriesExpression();
		}
		return ""; //$NON-NLS-1$
	}

	public void modify(Object element, String property, Object value) {
		TableItem tableItem = (TableItem) element;
		JRDesignTimePeriodSeries data = (JRDesignTimePeriodSeries) tableItem
				.getData();
		if ("NAME".equals(property) && value instanceof JRExpression) {//$NON-NLS-1$
			data.setSeriesExpression((JRExpression) value);
		}

	}

	private List<JRTimePeriodSeries> vlist;

	public List<JRTimePeriodSeries> getList() {
		return vlist;
	}

	public void setList(List<JRTimePeriodSeries> vlist) {
		this.vlist = vlist;
	}
}
