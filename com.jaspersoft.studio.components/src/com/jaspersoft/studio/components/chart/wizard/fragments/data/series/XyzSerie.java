package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.List;

import net.sf.jasperreports.charts.JRXyzSeries;
import net.sf.jasperreports.charts.design.JRDesignXyzSeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class XyzSerie implements ISeriesFactory<JRXyzSeries> {

	public Object createSerie() {
		JRDesignXyzSeries f = new JRDesignXyzSeries();
		f.setSeriesExpression(new JRDesignExpression());
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
		this.vlist = vlist;
	}
}
