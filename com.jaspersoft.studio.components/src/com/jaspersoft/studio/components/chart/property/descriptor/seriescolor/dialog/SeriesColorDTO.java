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
package com.jaspersoft.studio.components.chart.property.descriptor.seriescolor.dialog;

import java.awt.Color;

/*
 * @author Chicu Veaceslav
 * 
 */
public class SeriesColorDTO {

	public SeriesColorDTO(Color value) {
		super();
		this.value = value;
	}

	public Color getValue() {
		return value;
	}

	public void setValue(Color value) {
		this.value = value;
	}

	private Color value;

}
