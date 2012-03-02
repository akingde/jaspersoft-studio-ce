package com.jaspersoft.studio.data;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;

public abstract class AQueryDesigner implements IQueryDesigner {
	private DataQueryAdapters parent;

	public AQueryDesigner() {
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
