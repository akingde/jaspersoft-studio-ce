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
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TPropertyLabelProvider extends LabelProvider implements ITableLabelProvider {

	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		PropertyDTO dto = (PropertyDTO) element;
		switch (columnIndex) {
		case 0:
			return dto.getProperty();
		case 1:
			if (dto.getValue() instanceof JRExpression)
				return ((JRExpression) dto.getValue()).getText();
			if (dto.getValue() != null)
				return dto.getValue().toString();
		}
		return ""; //$NON-NLS-1$
	}
}
