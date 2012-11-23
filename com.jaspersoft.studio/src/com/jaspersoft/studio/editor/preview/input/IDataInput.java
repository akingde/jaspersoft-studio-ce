/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.beans.PropertyChangeListener;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;

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

	public void setDirty(boolean dirty);

}
