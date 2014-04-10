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
package com.jaspersoft.studio.property.descriptor.checkbox;

import org.eclipse.jface.viewers.LabelProvider;

public class BooleanLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof Boolean)
			return ((Boolean) element).booleanValue() ? "true" : "false";

		return super.getText(element);
	}

}
