/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.outline;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.INode;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportTreeLabelProvider.
 * 
 * @author Chicu Veaceslav
 */
public class ReportTreeLabelProvider extends CellLabelProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
	 */
	@Override
	public String getToolTipText(Object element) {
		return "tooltip";
	}

	/*
	 * @see ILabelProvider#getImage(Object)
	 */
	/**
	 * Gets the image.
	 * 
	 * @param element
	 *          the element
	 * @return the image
	 */
	public Image getImage(Object element) {
		return JaspersoftStudioPlugin.getImage(((INode) element).getImagePath());
	}

	/*
	 * @see ILabelProvider#getText(Object)
	 */
	/**
	 * Gets the text.
	 * 
	 * @param element
	 *          the element
	 * @return the text
	 */
	public String getText(Object element) {
		if (element instanceof INode)
			return ((INode) element).getDisplayText();
		return "UNKNOWN ELEMENT";
	}

	/**
	 * Gets the foreground.
	 * 
	 * @param element
	 *          the element
	 * @return the foreground
	 */
	private Color getForeground(Object element) {
		if (element instanceof INode)
			return ((INode) element).getForeground();
		return null;
	}

	/**
	 * Gets the background.
	 * 
	 * @param element
	 *          the element
	 * @return the background
	 */
	private Color getBackground(Object element) {
		if (element instanceof INode)
			return ((INode) element).getBackground();
		return null;
	}

	/**
	 * Gets the font.
	 * 
	 * @param element
	 *          the element
	 * @return the font
	 */
	private Font getFont(Object element) {
		if (element instanceof INode)
			return ((INode) element).getFont();
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellLabelProvider#update(org.eclipse.jface.viewers.ViewerCell)
	 */
	@Override
	public void update(ViewerCell cell) {
		try {
			Object element = cell.getElement();
			cell.setText(getText(element));
			Image image = getImage(element);
			cell.setImage(image);
			cell.setBackground(getBackground(element));
			cell.setForeground(getForeground(element));
			cell.setFont(getFont(element));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
