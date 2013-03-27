/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chart.model.theme.strokes;

import net.sf.jasperreports.chartthemes.simple.ColorProvider;
import net.sf.jasperreports.chartthemes.simple.GradientPaintProvider;

import org.eclipse.jface.viewers.LabelProvider;

/*
 * @author Chicu Veaceslav
 * 
 */
public class StrokesLabelProvider extends LabelProvider {

	public StrokesLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element == null)
			return "";
		if (element instanceof ColorProvider)
			return "[Color: " + ((ColorProvider) element).getColor() + "]";
		if (element instanceof GradientPaintProvider)
			return "[Color: " + ((GradientPaintProvider) element).getColor1() + ", " + ((GradientPaintProvider) element).getColor2() + "]";
		return element.toString();
	}

}
