/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.HashMap;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;

/**
 * This class is a container for many PaintListener, one for each type of control.
 * Everyone of this paintlistener can be used to create a highlight effect on 
 * the type of control it is associated with
 * 
 * @author Orlandin Marco
 *
 */
public class DefaultWidgetsHighlighters {
	
	/**
	 * Map that bind a control type with its paint listener
	 */
	private static HashMap<Class<?>,PaintListener> widgetsMap = null;
	
	/**
	 * A default paint listener returned when a specific paint listener for a 
	 * control is not found
	 */
	private static PaintListener defaultListener = new PaintListener() {
		@Override
		public void paintControl(PaintEvent e) {
			Rectangle bounds = ((Control)e.getSource()).getBounds();
			e.gc.setForeground(ColorConstants.orange);
			e.gc.setLineWidth(3);
			e.gc.drawRectangle(0,0,bounds.width-5,bounds.height-5);
		}
	};
	
	/**
	 * Create the map with all the predefined paint listener provided
	 */
	private static void initializeMap(){
		widgetsMap = new HashMap<Class<?>, PaintListener>();
		
		widgetsMap.put(ToolItem.class, new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Rectangle bounds = ((Control)e.getSource()).getBounds();
				e.gc.setForeground(ColorConstants.orange);
				e.gc.setLineWidth(3);
				e.gc.drawRectangle(1,1,bounds.width-3,bounds.height-3);
			}
		});
		
		widgetsMap.put(Combo.class, new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Rectangle bounds = ((Control)e.getSource()).getBounds();
				e.gc.setForeground(ColorConstants.orange);
				e.gc.setLineWidth(3);
				e.gc.drawRectangle(1,1,bounds.width-3,bounds.height-3);
			}
		});
		
		widgetsMap.put(Text.class, new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Text textArea = (Text)e.getSource();
				Rectangle bounds = textArea.getBounds();
				if (textArea.getVerticalBar() != null){
					bounds.width = bounds.width - textArea.getVerticalBar().getSize().x;
				}
				e.gc.setForeground(ColorConstants.orange);
				e.gc.setLineWidth(3);
				e.gc.drawRectangle(0,0,bounds.width-5,bounds.height-5);
			}
		});
		
		widgetsMap.put(Composite.class, new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Rectangle bounds = ((Control)e.getSource()).getBounds();
				e.gc.setForeground(ColorConstants.orange);
				e.gc.setLineWidth(3);
				e.gc.drawRectangle(1,1,bounds.width-3,bounds.height-3);
			}
		});
		
		widgetsMap.put(Spinner.class, new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				Spinner spinner = (Spinner)e.getSource();
				Rectangle bounds = spinner.getBounds();
				if (spinner.getVerticalBar() != null){
					bounds.width = bounds.width - spinner.getVerticalBar().getSize().x;
				}
				e.gc.setForeground(ColorConstants.orange);
				e.gc.setLineWidth(3);
				e.gc.drawRectangle(0,0,bounds.width-2,bounds.height-2);
			}
		});
	}
	
	/**
	 * Return a paint listener for the passed control type
	 * 
	 * @param type the type of the control
	 * @return a paint listener for the passed control, may be a specific one 
	 * or the generic paint listener
	 */
	public static PaintListener getWidgetForType(Class<?> type){
		if (widgetsMap == null) initializeMap();
		PaintListener selected =  widgetsMap.get(type);
		
		return selected != null ? selected : defaultListener;
	}

}
