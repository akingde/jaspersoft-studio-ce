/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
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
