/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data;

import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.data.designer.AQueryDesignerContainer;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public interface IQueryDesigner {

	public Control getControl();

	public Control createControl(Composite parent);

	public Control getToolbarControl();

	public Control createToolbar(Composite parent);

	public void dispose();

	public void setQuery(JasperDesign jDesign, JRDataset jDataset, JasperReportsConfiguration jConfig);

	public String getQuery();

	public void setDataAdapter(DataAdapterDescriptor da);

	public void setParentContainer(AQueryDesignerContainer dataQueryAdapters);

	public void setFields(List<JRDesignField> fields);

	public void setParameters(List<JRParameter> fields);

	public String getContextHelpId();

	public void setJasperConfiguration(JasperReportsConfiguration jConfig);

}
