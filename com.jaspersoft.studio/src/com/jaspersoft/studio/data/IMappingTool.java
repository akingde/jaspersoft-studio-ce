/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import net.sf.jasperreports.engine.design.JRDesignDataset;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;

public interface IMappingTool {

	public String getName();

	public Control getControl();

	public Control createControl(Composite parent);

	public void setFields(IFieldSetter fsetter);

	public void setJRDataset(JRDesignDataset dataset);

	public JRDesignDataset getJRDataset();

	public void dispose();

	public void setParentContainer(DataQueryAdapters dataQueryAdapters);
}
