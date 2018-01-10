/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.dataset;

import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.dataset.MElementDataset;

public class MChartDataset extends MElementDataset {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MChartDataset(ANode parent, JRChartDataset value,
			JasperDesign jasperDesign) {
		super(parent, value, jasperDesign);
	}

}
