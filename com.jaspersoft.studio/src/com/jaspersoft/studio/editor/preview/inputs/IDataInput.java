package com.jaspersoft.studio.editor.preview.inputs;

import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.widgets.Composite;

public interface IDataInput {
	public boolean isForType(Class<?> valueClass);

	public boolean createInput(Composite parent, final JRDesignParameter param, Class<?> valueClass,
			final Map<String, Object> params);

}
