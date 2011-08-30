package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.List;

public interface ISeriesFactory<T> {
	public Object createSerie();

	public String getColumnText(Object element, int columnIndex);

	public Object getValue(Object element, String property);

	public void modify(Object element, String property, Object value);

	public List<T> getList();

	public void setList(List<T> vlist);
}
