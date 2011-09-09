package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRPieSeries;
import net.sf.jasperreports.charts.design.JRDesignPieSeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class PieSerie implements ISeriesFactory<JRPieSeries> {

	public Object createSerie() {
		JRDesignPieSeries f = new JRDesignPieSeries();
		f.setKeyExpression(new JRDesignExpression());
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
