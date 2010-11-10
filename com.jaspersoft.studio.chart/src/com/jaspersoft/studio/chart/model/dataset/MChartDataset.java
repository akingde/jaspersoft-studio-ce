package com.jaspersoft.studio.chart.model.dataset;

import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.dataset.MElementDataset;

public class MChartDataset extends MElementDataset {

	public MChartDataset(ANode parent, JRChartDataset value, JasperDesign jasperDesign) {
		super(parent, value, jasperDesign);
	}

}
