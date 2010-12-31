/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.util;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * The Interface IIconDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public interface IIconDescriptor {

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle();

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription();

	/**
	 * Gets the tool tip.
	 * 
	 * @return the tool tip
	 */
	public String getToolTip();

	/**
	 * Gets the icon16 path.
	 * 
	 * @return the icon16 path
	 */
	public String getIcon16Path();

	/**
	 * Gets the icon32 path.
	 * 
	 * @return the icon32 path
	 */
	public String getIcon32Path();

	/**
	 * Gets the icon16.
	 * 
	 * @return the icon16
	 */
	public ImageDescriptor getIcon16();

	/**
	 * Gets the icon32.
	 * 
	 * @return the icon32
	 */
	public ImageDescriptor getIcon32();

}
