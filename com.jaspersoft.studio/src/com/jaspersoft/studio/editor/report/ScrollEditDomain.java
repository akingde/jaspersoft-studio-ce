/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.report;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.java2d.J2DScrollingGraphicalViewer;

/**
 * Edit domain that allow to scroll the editor with the mouse movement 
 * after the mouse central button has been pressed (easy scroll mode)
 * 
 * @author Orlandin Marco
 *
 */
public class ScrollEditDomain extends DefaultEditDomain {

	/**
	 * Cursor shown when the easy scroll mode is enabled but the user is not scrolling outside the threshold
	 * This resource is disposed when the application is closed by the resource manager
	 */
	private static final Cursor CURSOR_CENTER = ResourceManager.getCursor(JaspersoftStudioPlugin.class, "/icons/cursors/center.png");

	/**
	 * Cursor shown when the easy scroll mode is enabled and it the user is scrolling up.
	 * This resource is disposed when the application is closed by the resource manager
	 */
	private static final Cursor CURSOR_UP = ResourceManager.getCursor(JaspersoftStudioPlugin.class, "/icons/cursors/up.png");

	/**
	 * Cursor shown when the easy scroll mode is enabled and it the user is scrolling down.
	 * This resource is disposed when the application is closed by the resource manager
	 */
	private static final Cursor CURSOR_DOWN = ResourceManager.getCursor(JaspersoftStudioPlugin.class, "/icons/cursors/down.png");
	
	/**
	 * Cursor shown when the easy scroll mode is enabled and it the user is scrolling left.
	 * This resource is disposed when the application is closed by the resource manager
	 */
	private static final Cursor CURSOR_LEFT = ResourceManager.getCursor(JaspersoftStudioPlugin.class, "/icons/cursors/left.png");

	/**
	 * Cursor shown when the easy scroll mode is enabled and it the user is scrolling right.
	 * This resource is disposed when the application is closed by the resource manager
	 */
	private static final Cursor CURSOR_RIGHT = ResourceManager.getCursor(JaspersoftStudioPlugin.class, "/icons/cursors/right.png");

	/**
	 * Threshold form where the user pressed the central mouse button. While the cursor
	 * is moved inside the threshold from that point the editor is not scrolled
	 */
	private static final int VERTICAL_THRESHOLD = 100;
	
	/**
	 * Threshold form where the user pressed the central mouse button. While the cursor
	 * is moved inside the threshold from that point the editor is not scrolled
	 */
	private static final int HORIZONTAL_THRESHOLD = 100;

	/**
	 * Flag to check if the easy scrolling mode is enabled or not
	 */
	private boolean easyScrollEnabled = false;

	/**
	 * Point where the central mouse button was pressed when the editor
	 * is in easy scroll mode. Null in the other cases
	 */
	private Point clickPoint = null;

	/**
	 * Current mouse position
	 */
	private Point currentMouseLocation;

	/**
	 * Thread that scroll the editor when it is in easy 
	 * scroll mode. We can not relay only on the mouse movement since when it is outside
	 * the threshold area the editor must be scrolled even if the mouse if freeze. It is also
	 * used to know if there is already an instance of this thread running. When it is null 
	 * there is no instance otherwise there is already an instance. 
	 */
	private Thread scrollThread = null;

	/**
	 * Runnable executed by the thread
	 */
	private Runnable scrollRunnbable = new Runnable() {

		/**
		 * If the editor is in easy scroll mode it check if and in which direction it must
		 * be scrolled and eventually update the icon. There could be only one instance of 
		 * this at a time
		 */
		@Override
		public void run() {
			Display disp = UIUtils.getDisplay();
			//avoid concurrent access to the thread field
			synchronized (ScrollEditDomain.this) {
				try {
					FigureCanvas canvas = ((J2DScrollingGraphicalViewer) getViewer()).getFigureCanvas();
					Viewport port = canvas.getViewport();
					//While the editor is in easy scroll mode
					while (clickPoint != null) {
						if (currentMouseLocation.y < clickPoint.y - VERTICAL_THRESHOLD) {
							int amount = (clickPoint.y - VERTICAL_THRESHOLD) - currentMouseLocation.y;
							Point currentPoint = port.getViewLocation();
							scrollTo(disp, canvas, currentPoint.x, Math.max(0, currentPoint.y - amount));
							setCursor(disp, CURSOR_UP);
						} else if (currentMouseLocation.y > clickPoint.y + VERTICAL_THRESHOLD) {
							int amount = currentMouseLocation.y - (clickPoint.y + VERTICAL_THRESHOLD);
							Point currentPoint = port.getViewLocation();
							scrollTo(disp, canvas, currentPoint.x, Math.max(0, currentPoint.y + amount));
							setCursor(disp, CURSOR_DOWN);
						} else if (currentMouseLocation.x < clickPoint.x - HORIZONTAL_THRESHOLD){
							int amount = (clickPoint.x - HORIZONTAL_THRESHOLD) - currentMouseLocation.x;
							Point currentPoint = port.getViewLocation();
							scrollTo(disp, canvas,  Math.max(0, currentPoint.x - amount), currentPoint.y);
							setCursor(disp, CURSOR_LEFT);
						} else if (currentMouseLocation.x > clickPoint.x + HORIZONTAL_THRESHOLD) {
							int amount = currentMouseLocation.x - (clickPoint.x + HORIZONTAL_THRESHOLD);
							Point currentPoint = port.getViewLocation();
							scrollTo(disp, canvas,  Math.max(0, currentPoint.x + amount), currentPoint.y);
							setCursor(disp, CURSOR_RIGHT);
						} else {
							setCursor(disp, CURSOR_CENTER);
						}
						Thread.sleep(10);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					//since the operation is ended reset the thread
					scrollThread = null;
					setCursor(disp, null);
				}
			}
		}
	};

	/**
	 * Constructs a ScrollEditDomain with the specified IEditorPart
	 * 
	 * @param editorPart an AbstractVisualEditor, must be not null
	 */
	public ScrollEditDomain(AbstractVisualEditor editorPart) {
		super(editorPart);
		Assert.isNotNull(editorPart);
	}

	/**
	 * Return the viewer of the current editor part
	 * 
	 * @return a not null graphical viewer
	 */
	protected GraphicalViewer getViewer() {
		return ((AbstractVisualEditor) getEditorPart()).getGraphicalViewer();
	}

	/**
	 * Update the easy scroll state
	 * 
	 * @param value the easy scroll state, true if it is enabled, false otherwise
	 * @param event a mouse event that could have change the scroll event, can be null if value
	 * is false
	 */
	private void setMouseScroll(boolean value, MouseEvent event) {
		if (value == easyScrollEnabled)
			return;
		easyScrollEnabled = value;
		if (easyScrollEnabled) {
			//The easy scroll was enable, start the thread. Since the thread
			//is completely synchronized it is not possible to start a new instance while it 
			//is still running
			clickPoint = new Point(event.x, event.y);
			currentMouseLocation = new Point(event.x, event.y);
			//avoid concurrent access to the thread field
			synchronized (ScrollEditDomain.this) {
				if (scrollThread == null) {
					scrollThread = new Thread(scrollRunnbable);
					scrollThread.start();
				}
			}
		} else {
			//easy scroll disabled
			clickPoint = null;
		}
	}
	
	/**
	 * Check if the button pressed is the middle one and enable or
	 * disable the easy scroll
	 */
	@Override
	public void mouseDown(MouseEvent mouseEvent, EditPartViewer viewer) {
		if (mouseEvent.button == 2) {
			setMouseScroll(!easyScrollEnabled, mouseEvent);
		} else {
			setMouseScroll(false, mouseEvent);
			super.mouseDown(mouseEvent, viewer);
		}
	};

	/**
	 * A keypress stop the method easyscroll
	 */
	@Override
	public void keyDown(KeyEvent keyEvent, EditPartViewer viewer) {
		if (easyScrollEnabled) {
			setMouseScroll(false, null);
		} 
		super.keyDown(keyEvent, viewer);
	}
	
	/**
	 * If the easy scroll is enabled store the last position
	 */
	@Override
	public void mouseMove(MouseEvent mouseEvent, EditPartViewer viewer) {
		if (easyScrollEnabled) {
			currentMouseLocation = new Point(mouseEvent.x, mouseEvent.y);
		}
		super.mouseMove(mouseEvent, viewer);
	}

	//UI METHODS TO SET THE CURSOR OR CHANGE THE SCROLL
	
	private void setCursor(Display disp, final Cursor cursor) {
		//This check avoid to do operation if the application is closed when the thread is running
		if (!disp.isDisposed()){
			disp.syncExec(new Runnable() {
	
				@Override
				public void run() {
					if (cursor == null || !cursor.isDisposed()) getViewer().setCursor(cursor);
				}
			});
		}
	}

	private void scrollTo(Display disp, final FigureCanvas canvas, final int x, final int y) {
		//This check avoid to do operation if the application is closed when the thread is running
		if (!disp.isDisposed()){
			disp.syncExec(new Runnable() {
	
				@Override
				public void run() {
					if (!canvas.isDisposed()) canvas.scrollSmoothTo(x, y);
				}
			});
		}
	}

}
