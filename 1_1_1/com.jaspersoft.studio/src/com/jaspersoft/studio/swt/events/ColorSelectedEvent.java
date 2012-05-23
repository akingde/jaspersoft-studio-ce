/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.swt.events;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Widget;

import com.jaspersoft.studio.swt.widgets.WColorPicker;

/**
 * The event that should be notified/received when dealing with color selection widgets, like for example the {@link WColorPicker}.
 * 
 * @author mrabbi
 * 
 * @see ColorSelectionListener
 * @see WColorPicker
 *
 */
public class ColorSelectedEvent extends TypedEvent{

	private static final long serialVersionUID = 1849191962501917969L;
	public RGB selectedColor=null;

	public ColorSelectedEvent(Widget widget){
		super(widget);
	}
	
}
