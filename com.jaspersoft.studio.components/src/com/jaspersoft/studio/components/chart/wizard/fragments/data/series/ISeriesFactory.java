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
package com.jaspersoft.studio.components.chart.wizard.fragments.data.series;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignExpression;

public interface ISeriesFactory<T> {
	public Object createSerie();

	public Object createSerie(JRDesignExpression expr);

	public String getColumnText(Object element, int columnIndex);

	public Object getValue(Object element, String property);

	public void modify(Object element, String property, Object value);

	public List<T> getList();

	public void setList(List<T> vlist);
}
