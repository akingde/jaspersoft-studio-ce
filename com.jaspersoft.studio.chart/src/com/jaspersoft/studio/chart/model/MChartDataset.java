package com.jaspersoft.studio.chart.model;

import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.dataset.MElementDataset;

public class MChartDataset extends MElementDataset {

	public MChartDataset(JRChartDataset value, JasperDesign jasperDesign) {
		super(value, jasperDesign);
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

	public String getDisplayText() {
		return null;
	}

}
