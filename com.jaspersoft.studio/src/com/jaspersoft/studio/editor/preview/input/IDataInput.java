/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.PreviewContainer;

public interface IDataInput {
	public boolean isForType(Class<?> valueClass);

	public IDataInput getInstance();

	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params);

	public IParameter getParameter();

	public boolean isLabeled();

	public void updateModel(Object value);

	public void updateInput();

	public void addChangeListener(PropertyChangeListener listener);

	public void removeChangeListener(PropertyChangeListener listener);

	public void dispose();

	public boolean isDirty();

	public boolean isRemoved();

	public void setDirty(boolean dirty);

	public void setPcontainer(PreviewContainer pcontainer);

	/**
	 * Method that should called when one of the parameter changed value to
	 * allow the other parameters to update their value. This is used because
	 * some parameters could depend from the others
	 * 
	 * @param evt
	 *            the event contains the name of the parameter that changed
	 *            value
	 */
	public void parameterChanged(PropertyChangeEvent evt);

}
