package com.jaspersoft.studio.components.chart.property.section.plot;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.property.section.AbstractSection;

public abstract class APlot {

	public APlot(Composite parent, AbstractSection section) {
		createComponent(parent, section);
	}

	protected abstract void createComponent(Composite parent,
			AbstractSection section);

	public abstract void setData(MChartPlot mplot);
}
