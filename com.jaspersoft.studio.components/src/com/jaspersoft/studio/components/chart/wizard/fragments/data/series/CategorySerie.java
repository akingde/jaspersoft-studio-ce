package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRCategorySeries;
import net.sf.jasperreports.charts.design.JRDesignCategorySeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.widgets.TableItem;

public class CategorySerie implements ISeriesFactory<JRCategorySeries> {

	public Object createSerie() {
		JRDesignCategorySeries f = new JRDesignCategorySeries();
		f.setSeriesExpression(new JRDesignExpression());
		return f;
	}

	public String getColumnText(Object element, int columnIndex) {
		JRCategorySeries dcs = (JRCategorySeries) element;
		switch (columnIndex) {
		case 0:
			if (dcs.getSeriesExpression() != null
					&& dcs.getSeriesExpression().getText() != null)
				return dcs.getSeriesExpression().getText();
		}
		return ""; //$NON-NLS-1$
	}

	public Object getValue(Object element, String property) {
		JRCategorySeries prop = (JRCategorySeries) element;
		if ("NAME".equals(property)) { //$NON-NLS-1$
			return prop.getSeriesExpression();
		}
		return ""; //$NON-NLS-1$
	}

	public void modify(Object element, String property, Object value) {
		TableItem tableItem = (TableItem) element;
		JRDesignCategorySeries data = (JRDesignCategorySeries) tableItem
				.getData();
		if ("NAME".equals(property) && value instanceof JRExpression) {//$NON-NLS-1$
			data.setSeriesExpression((JRExpression) value);
		}

	}

	private List<JRCategorySeries> vlist;

	public List<JRCategorySeries> getList() {
		return vlist;
	}

	public void setList(List<JRCategorySeries> vlist) {
		this.vlist = new ArrayList<JRCategorySeries>(vlist);
	}
}
