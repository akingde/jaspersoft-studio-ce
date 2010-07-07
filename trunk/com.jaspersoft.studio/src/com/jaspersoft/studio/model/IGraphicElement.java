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
package com.jaspersoft.studio.model;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGraphicElement.
 * 
 * @author Chicu Veaceslav
 */
public interface IGraphicElement {
	
	/**
	 * Gets the bounds.
	 * 
	 * @return the bounds
	 */
	public Rectangle getBounds();

	/**
	 * Gets the default width.
	 * 
	 * @return the default width
	 */
	public int getDefaultWidth();

	/**
	 * Gets the default height.
	 * 
	 * @return the default height
	 */
	public int getDefaultHeight();

	/**
	 * Creates the jr element.
	 * 
	 * @param jasperDesign
	 *          the jasper design
	 * @return the jR design element
	 */
	public abstract JRDesignElement createJRElement(JasperDesign jasperDesign);
}
