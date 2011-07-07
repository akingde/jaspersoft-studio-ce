package com.jaspersoft.studio.data;

import net.sf.jasperreports.engine.design.JRDesignDataset;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IMappingTool {

	public String getName();

	public Control getControl();

	public Control createControl(Composite parent);

	public void setFields(IFieldSetter fsetter);

	public void setJRDataset(JRDesignDataset dataset);

	public JRDesignDataset getJRDataset();

	public void dispose();
}
