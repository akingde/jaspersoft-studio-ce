package com.jaspersoft.studio.editor.preview.input;

public interface IParameter {
	public String getName();

	public String getLabel();

	public String getDescription();

	public Class<?> getValueClass();

	public boolean isMandatory();

	public boolean isReadOnly();

}
