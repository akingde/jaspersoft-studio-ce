/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts;

import java.lang.reflect.Field;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.swt.widgets.Display;


/**
 * Custom ZoomManager that allows to specify the style of the zoom location.
 * The current styles allow to zoom on the center of the editor or zoom keeping
 * centered the current mouse position
 * 
 * @author Orlandin Marco
 *
 */
public class JSSZoomManager extends ZoomManager{

	/**
	 * The supported styles
	 * 
	 * @author Orlandin Marco
	 *
	 */
	public enum ZOOM_TYPE{KEEP_CENTER, CENTER_TO_MOUSE};
	
	/**
	 * To zoom to the mouse position this zoom manager need some special code.
	 * There are two ways to execute this codes, one require reflection the other
	 * not but does some useless calculations. 
	 * This class if necessary will try to use the reflections methods, and if something
	 * goes wrong will fallback to the other one. This field is the one that is set trought
	 * the reflection
	 */
	protected Field privateStringField = null;
	
	/**
	 * The zoom style
	 */
	protected ZOOM_TYPE zoomType = ZOOM_TYPE.KEEP_CENTER;
	
	/**
	 * The edit part where this manager is placed
	 */
	private EditPart parent;
	
	/**
	 * Create the new zoom manager
	 *  
	 * @param pane The ScalableFigure associated with this ZoomManager
	 * @param viewport The Viewport associated with this ZoomManager
	 * @param parent The edit part associated with this ZoomManager, must be not null
	 * @param style the style of the zoom manager. Using it as ZOOM_TYPE.KEEP_CENTER is 
	 * the same to use the standard ZoomManager. Must be not null
	 */
	public JSSZoomManager(ScalableFigure pane, Viewport viewport, EditPart parent, ZOOM_TYPE style) {
		super(pane, viewport);
		Assert.isNotNull(parent);
		Assert.isNotNull(style);
		this.parent = parent;
		this.zoomType = style;
		try {
			privateStringField = ZoomManager.class.getDeclaredField("zoom");
			privateStringField.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
			privateStringField = null;
		}
	}
	
	/**
	 * Create the zoom manager, its behavior will be the same of 
	 * the standard ZoomManager
	 * 
	 * @param pane The ScalableFigure associated with this ZoomManager
	 * @param viewport The Viewport associated with this ZoomManager
	 */
	public JSSZoomManager(ScalableFigure pane, Viewport viewport){
		super(pane, viewport);
	}
	
	/**
	 * Set trough the reflection the variable field zoom of the superclass
	 * 
	 * @param zoom the value to set
	 * @return true if the field was set without errors, false otherwise
	 */
	protected boolean forceSetZoom(Double zoom){
		if (privateStringField != null){
			try {
				privateStringField.set(this, zoom);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Calculate the point where to zoom basing on the zoom style
	 * 
	 * @return the zoom point or null if the style is not valid
	 */
	protected Point calculateZoomPoint(){
		Point p1 = null;
		switch (zoomType) {
		case KEEP_CENTER:
			p1 = getViewport().getClientArea().getCenter();
			break;
		case CENTER_TO_MOUSE:
			Display display = UIUtils.getDisplay();
			org.eclipse.swt.graphics.Point point = parent.getViewer().getControl().toControl(display.getCursorLocation());
			p1 = new Point(point.x, point.y);
			getScalableFigure().translateToRelative(p1);
			break;
		}
		return p1;
	}
	
	/**
	 * Do the custom zoom without depending on reflection
	 * 
	 * @param zoom the new zoom level
	 */
	protected void noReflectionPrimSetZoom(double zoom){
		Point p1 = calculateZoomPoint();
		if (p1 != null){
			Point p2 = p1.getCopy();
			Point p = getViewport().getViewLocation();
			double prevZoom = getZoom();

			super.primSetZoom(zoom);

			p2.scale(zoom / prevZoom);
			Dimension dif = p2.getDifference(p1);
			p.x += dif.width;
			p.y += dif.height;
			forceSetViewLocation(p);
		}
	}
	
	/**
	 * Set the current zoom type. This ZoomManager must be build with
	 * the constructor with four parameters to use this
	 * 
	 * @param style the zoom type, must be not null
	 */
	public void setZoomType(ZOOM_TYPE style){
		Assert.isNotNull(parent, "Invalid ZoomManager instance: the parent part is null");
		Assert.isNotNull(style);
	}
	
	/**
	 * Simply call the setViewLocation of the superclass, since this class override it in some 
	 * cases this is another point to access that method
	 * 
	 * @param p The new location for the Viewport's view.
	 */
	public void forceSetViewLocation(Point p){
		super.setViewLocation(p);
	}

	/**
	 * Override the standard setViewLocation and does nothing 
	 * when the zoom style is different from ZOOM_TYPE.KEEP_CENTER.
	 * This is done to break the original call stack when using a custom
	 * location for the zoom
	 */
	@Override
	public void setViewLocation(Point p) {
		if (zoomType.equals(ZOOM_TYPE.KEEP_CENTER)){
			//standard behavior
			super.setViewLocation(p);
		}
	};
	
	/**
	 * If the zoom type is KEEP_CENTER it execute the superclass
	 * primSetZoom.
	 * Otherwise it will try to set the current zoom position first
	 * trough the reflection and if something goes wrong with a safer
	 * Method
	 *  
	 * @param zoom the new zoom level
	 */
	@Override
	protected void primSetZoom(double zoom) {
		if (zoomType.equals(ZOOM_TYPE.KEEP_CENTER)){
			//standard behavior
			super.primSetZoom(zoom);
		} else {
			if (privateStringField != null){
				Point p1 = calculateZoomPoint();
				if (p1 != null){
					Point p2 = p1.getCopy();
					Point p = getViewport().getViewLocation();
					double prevZoom = getZoom();
					boolean result = forceSetZoom(zoom);
					if (!result){
						noReflectionPrimSetZoom(zoom);
					} else {
						getScalableFigure().setScale(zoom);
						fireZoomChanged();
						getViewport().validate();
			
						p2.scale(zoom / prevZoom);
						Dimension dif = p2.getDifference(p1);
						p.x += dif.width;
						p.y += dif.height;
						forceSetViewLocation(p);
					}
				}
			} else {
				noReflectionPrimSetZoom(zoom);
			}
		}	
	}
}
