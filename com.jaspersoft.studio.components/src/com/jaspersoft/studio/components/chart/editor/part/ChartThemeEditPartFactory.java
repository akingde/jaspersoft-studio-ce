/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.editor.part;

import org.eclipse.gef.EditPart;

import com.jaspersoft.studio.components.chart.model.theme.MChartThemeSettings;
import com.jaspersoft.studio.editor.AEditPartFactory;
import com.jaspersoft.studio.model.MRoot;

/*
 * A factory for creating JasperDesignEditPart objects.
 * 
 * @author Chicu Veaceslav
 */
public class ChartThemeEditPartFactory extends AEditPartFactory {

	@Override
	protected EditPart createEditPart(Object model) {
		EditPart editPart = null;
		if (model instanceof MRoot || model instanceof MChartThemeSettings)
			editPart = new ChartThemeEditPart();
		return editPart;
	}
}
