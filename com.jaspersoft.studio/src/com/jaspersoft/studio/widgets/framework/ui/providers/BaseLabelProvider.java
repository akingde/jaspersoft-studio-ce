/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui.providers;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.widgets.framework.WItemProperty;

/**
 * A dummy label provider for a {@link WItemProperty}
 * 
 * @author Orlandin Marco
 *
 */
public class BaseLabelProvider extends ColumnLabelProvider implements ITableLabelProvider, IColorProvider {

	public static final BaseLabelProvider INSTANCE = new BaseLabelProvider();
	
	@Override
	public String getText(Object element) {
		return getTextLabel(element);
	}

	protected String getTextLabel(Object element) {
		if (element != null) {
			element.toString();
		}
		return ""; //$NON-NLS-1$
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
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element != null){
			return element.toString();
		}
		return "";
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
