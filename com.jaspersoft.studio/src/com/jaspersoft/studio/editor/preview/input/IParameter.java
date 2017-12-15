/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

public interface IParameter {
	public String getName();

	public String getLabel();

	public String getDescription();

	public Class<?> getValueClass();

	public boolean isMandatory();

	public boolean isReadOnly();

	public boolean isStrictMin();

	public String getMinValue();

	public boolean isStrictMax();

	public String getMaxValue();

	public String getPattern();

}
