package com.jaspersoft.studio.data;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public interface IMappingTool {

	public String getName();

	public Control getControl();

	public Control createControl(Composite parent);

	public List<JRDesignField> getFields();

	public void setJRDataset(JRDesignDataset dataset);

	public JRDesignDataset getJRDataset();
}
