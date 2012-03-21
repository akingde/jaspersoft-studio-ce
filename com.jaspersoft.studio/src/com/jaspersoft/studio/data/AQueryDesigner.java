package com.jaspersoft.studio.data;

import java.util.List;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;

public abstract class AQueryDesigner implements IQueryDesigner {
	private DataQueryAdapters parent;
	protected JasperDesign jDesign;
	protected JRDesignDataset jDataset;

	public AQueryDesigner() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#setQuery(java.lang.String)
	 */
	public void setQuery(JasperDesign jDesign, JRDataset jDataset) {
		this.jDesign = jDesign;
		this.jDataset = (JRDesignDataset) jDataset;
	}

	public String getQuery() {
		if (jDataset != null)
			return jDataset.getQuery().getText();
		else
			return "";
	}

	public void setParentContainer(DataQueryAdapters parent) {
		this.parent = parent;
	}

	public void setFields(List<JRDesignField> fields) {
		parent.setFields(fields);
	}

	public void setParameters(List<JRDesignParameter> params) {
		parent.setParameters(params);
	}
}
