/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.widgets.framework.ui.providers.BaseLabelProvider;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;

/*
 * @author Chicu Veaceslav
 */
public class ItemPropertyBaseLabelProvider extends BaseLabelProvider {

	@Override
	public String getText(Object element) {
		return getTextLabel(element);
	}

	protected String getTextLabel(Object element) {
		if (element != null && element instanceof ItemProperty) {
			ItemProperty ip = (ItemProperty) element;
			if (ip.getValueExpression() != null)
				return Misc.nvl(ip.getValueExpression().getText());
			else return Misc.nvl(ip.getValue());
		}
		return ""; //$NON-NLS-1$
	}
	
	public String getTextLabel(JRExpression expressionValue, String staticValue) {
		if (expressionValue != null)
			return Misc.nvl(expressionValue.getText());
		return Misc.nvl(staticValue);
	}


	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return super.getBackground(element);
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
