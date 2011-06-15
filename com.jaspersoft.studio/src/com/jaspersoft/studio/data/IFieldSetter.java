package com.jaspersoft.studio.data;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignField;

public interface IFieldSetter {
	public void setFields(List<JRDesignField> fields);
}
