/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty;

import net.sf.jasperreports.components.map.ItemProperty;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.utils.Misc;

/*
 * @author Chicu Veaceslav
 */
public class ItemPropertyLabelProvider extends ColumnLabelProvider implements ITableLabelProvider {
	private ADescriptor descriptor;

	public ItemPropertyLabelProvider(ADescriptor descriptor) {
		super();
		this.descriptor = descriptor;
	}

	@Override
	public String getText(Object element) {
		return getTextLabel(element);
	}

	private String getTextLabel(Object element) {
		if (element != null && element instanceof ItemProperty) {
			ItemProperty ip = (ItemProperty) element;
			if (ip.getValueExpression() != null)
				return Misc.nvl(ip.getValueExpression().getText());
			if (descriptor != null) {
				ItemPropertyDescription<?> ipDesc = descriptor.getDescription(ip.getName());
				if (ipDesc != null)
					return ipDesc.toSimpleString(Misc.nvl(ip.getValue()));
			}
			return Misc.nvl(ip.getValue());
		}
		return ""; //$NON-NLS-1$
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (element instanceof ItemProperty && columnIndex == 1)
			return getImageIcon(element);
		return null;
	}

	private Image getImageIcon(Object element) {
		if (element != null && element instanceof ItemProperty) {
			ItemProperty ip = (ItemProperty) element;
			if (ip.getValueExpression() != null)
				return JaspersoftStudioPlugin.getInstance().getImage("icons/functions_icon.png");
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof ItemProperty)
			switch (columnIndex) {
			case 0:
				return ((ItemProperty) element).getName();
			case 1:
				return getTextLabel(element);
			}
		return null;
	}

	@Override
	public void update(ViewerCell cell) {
		super.update(cell);
	}

	@Override
	public String getToolTipText(Object element) {
		return getText(element);
	}

	@Override
	public Point getToolTipShift(Object object) {
		return new Point(5, 5);
	}

	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		return 100; // msec
	}

	@Override
	public int getToolTipTimeDisplayed(Object object) {
		return 5000; // msec
	}
}
