package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.List;

import net.sf.jasperreports.charts.JRXySeries;
import net.sf.jasperreports.charts.design.JRDesignXySeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class XySerie implements ISeriesFactory<JRXySeries> {

	public Object createSerie() {
		JRDesignXySeries f = new JRDesignXySeries();
		f.setSeriesExpression(new JRDesignExpression());
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

	public Object getValue(Object element, String property) {
		JRXySeries prop = (JRXySeries) element;
		if ("NAME".equals(property)) { //$NON-NLS-1$
			return prop.getSeriesExpression();
		}
		return ""; //$NON-NLS-1$
	}

	public void modify(Object element, String property, Object value) {
		TableItem tableItem = (TableItem) element;
		JRDesignXySeries data = (JRDesignXySeries) tableItem.getData();
		if ("NAME".equals(property) && value instanceof JRExpression) {//$NON-NLS-1$
			data.setSeriesExpression((JRExpression) value);
		}

	}

	private List<JRXySeries> vlist;

	public List<JRXySeries> getList() {
		return vlist;
	}

	public void setList(List<JRXySeries> vlist) {
		this.vlist = vlist;
	}
}
