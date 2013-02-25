/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.wizard.pages;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.DataAdapterFactory;

public class LLabelProvider extends LabelProvider {
	@Override
	public Image getImage(Object element) {
		if (element instanceof DataAdapterFactory)
			return ((DataAdapterFactory) element).getIcon(16);
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof DataAdapterFactory)
			return ((DataAdapterFactory) element).getLabel();
		return super.getText(element);
	}
}
