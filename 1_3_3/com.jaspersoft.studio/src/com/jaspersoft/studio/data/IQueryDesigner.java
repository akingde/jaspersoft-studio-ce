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
package com.jaspersoft.studio.data;

import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;

public interface IQueryDesigner {

	public Control getControl();

	public Control createControl(Composite parent);

	public Control getToolbarControl();

	public Control createToolbar(Composite parent);

	public void dispose();

	public void setQuery(JasperDesign jDesign, JRDataset jDataset);

	public String getQuery();

	public void setDataAdapter(DataAdapterDescriptor da);

	public void setParentContainer(DataQueryAdapters dataQueryAdapters);

	public void setFields(List<JRDesignField> fields);

	public void setParameters(List<JRDesignParameter> fields);

}
