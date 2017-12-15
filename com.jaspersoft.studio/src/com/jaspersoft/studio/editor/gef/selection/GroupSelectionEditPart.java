/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.selection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.jface.viewers.StructuredSelection;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.JSSCompoundResizeTracker;

/**
 * Edit part used to show in the editor the group selection feedback
 */
public class GroupSelectionEditPart extends AbstractGraphicalEditPart {

	/**
	 * Id of the key binding for this action
	 */
	public static final String BINDING_KEY_ID = "com.jaspersoft.studio.editor.groupselection";
	
	/**
	 * Resize handle for the group selection
	 */
	private List<ColoredSquareHandles> selectionHandles = null;
	
	/**
	 * The selection manager
	 */
	private JSelectionManager selectionManager;
	
	/**
	 * The zoom manager
	 */
	private ZoomManager zoomManager;
	
	/**
	 * Figure shown for this edit part
	 */
	private class GroupSelectionFigure extends RectangleFigure {
		
		/**
		 * Create the figure with no fill
		 */
		public GroupSelectionFigure() {
			setFill(false);
		}
		
		/**
		 * Remove this figure from this parent
		 */
		public void clear() {
			if (getParent() != null) {
				if (getParent().getChildren().contains(this)) {
					getParent().remove(this);
				}
			}
		}
		
		/**
		 * The bounds are recalculated on validate but the figure is not shown if it wasn't already
		 * visible
		 */
		@Override
		public void validate() {
			updateBounds(false);
		}
		
	}
	
	public GroupSelectionEditPart(JSelectionManager selectionManager, ZoomManager zoomManager) {
		this.selectionManager = selectionManager;
		this.zoomManager = zoomManager;
		createEditPolicies();
	}
	
	@Override
	protected IFigure createFigure() {
		return new GroupSelectionFigure();
	}

	/**
	 * Create the edit policies to resize this figure and show the correct fidback.
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ResizableEditPolicy() {

			@Override
			protected IFigure getFeedbackLayer() {
				StructuredSelection sel = (StructuredSelection)selectionManager.getSelection();
				IFigure layer = LayerManager.Helper.find((EditPart)sel.getFirstElement()).getLayer(LayerConstants.HANDLE_LAYER);
				return layer;
			}
			
			@Override
			protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {

				IFigure feedback = getDragSourceFeedbackFigure();

				PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
				getHostFigure().translateToAbsolute(rect);
				rect.translate(request.getMoveDelta());
				rect.resize(request.getSizeDelta());

				feedback.translateToRelative(rect);

				feedback.setBounds(rect);
			}
			
			protected Command getResizeCommand(ChangeBoundsRequest request) {
				return new Command() {
					
					private int oldWidth;
					
					private int oldHeight;
					
					
					@Override
					public void execute() {
						if (figure != null) {
							PrecisionRectangle rect = new PrecisionRectangle(figure.getBounds().getCopy());
							oldWidth = figure.getBounds().width;
							oldHeight = figure.getBounds().height;
							rect.translate(request.getMoveDelta());
							rect.resize(request.getSizeDelta());
							figure.setBounds(rect);
						}
					}
					
					@Override
					public void undo() {
						if (figure != null) {
							PrecisionRectangle rect = new PrecisionRectangle(figure.getBounds().getCopy());
							rect.setWidth(oldWidth);
							rect.setHeight(oldHeight);
							figure.setBounds(rect);
						}
					}
					
				};
			}
		});

	}
	
	/**
	 * Hide the current group feedback selection figure.
	 */
	public void clear() {
		((GroupSelectionFigure)getFigure()).clear();
		removeSelectionHandles();
	}
	
	/**
	 * The edit part is active when the figure is visible
	 */
	@Override
	public boolean isActive() {
		return (figure != null) && (figure.getParent() != null);
	}

	/**
	 * Returns a resize tracker for the given direction to be used by a resize handle.
	 * 
	 * @param direction
	 *          the resize direction for the {@link JSSCompoundResizeTracker}.
	 * @return a new {@link GroupResizeTracker}
	 */
	protected ResizeTracker getResizeTracker(int direction) {
		return new GroupResizeTracker(this, direction);
	}
	
	/**
	 * Remove the resize handles from the group feedback figure
	 */
	protected void removeSelectionHandles() {
		if (selectionHandles == null)
			return;
		for (ColoredSquareHandles handle : selectionHandles) {
			IFigure parent = handle.getParent();
			if (parent != null && parent.getChildren().contains(handle)) {
				parent.remove(handle);
			}
		}
		selectionHandles = null;
	}
	
	/**
	 * Create the selection handles from the group feedback figure
	 * 
	 * @param parent parent where the handles are created
	 */
	public void createSelectionHandles(IFigure parent) {
		removeSelectionHandles();
		selectionHandles = new ArrayList<ColoredSquareHandles>();
		createResizeHandle(selectionHandles, PositionConstants.NORTH);
		createResizeHandle(selectionHandles, PositionConstants.EAST);
		createResizeHandle(selectionHandles, PositionConstants.SOUTH);
		createResizeHandle(selectionHandles, PositionConstants.WEST);
		createResizeHandle(selectionHandles, PositionConstants.SOUTH_EAST);
		createResizeHandle(selectionHandles, PositionConstants.SOUTH_WEST);
		createResizeHandle(selectionHandles, PositionConstants.NORTH_WEST);
		createResizeHandle(selectionHandles, PositionConstants.NORTH_EAST);
		
		for (ColoredSquareHandles handle : selectionHandles) {
			parent.add(handle);
		}
	}
	
	/**
	 * Create a single resize handle figure 
	 * 
	 * @param handles the list of handles where the new one will be stored
	 * @param direction the direction handled by this new handle
	 */
	protected void createResizeHandle(List<ColoredSquareHandles> handles, int direction) {
		//the handle of the group have a fixed color
		ColoredSquareHandles handle = new ColoredSquareHandles(this, direction) {
			 
			protected Color[] getFillColorAwt(){
				return JSS_FOCUSED_COLOR;
			}
			
		};
		handle.setDragTracker(getResizeTracker(direction));
		handle.setCursor(Cursors.getDirectionalCursor(direction, getFigure().isMirrored()));
		handles.add(handle);
	}

	/**
	 * Return the selection manager
	 * 
	 * @return a not null {@link JSelectionManager}
	 */
	protected JSelectionManager getSelectionManager() {
		return selectionManager;
	}
	
	/**
	 * Update the bounds of the figure
	 * 
	 * @param createFigure if false the bound are update only
	 * if the figure is visible otherwise if the figure was not visible
	 * it is created and then the bounds cre updated
	 */
	public void updateBounds(boolean createFigure) {
		if (isActive() || createFigure) {
			getFigure().setBounds(calculateBounds());
		}
	}
	
	/**
	 * Calculate the bounds of the group feedback figure. It will be the minimum rectangle to enclose all
	 * the selected element
	 * 
	 * @return a not null rectangle
	 */
	protected Rectangle calculateBounds() {
		StructuredSelection selection = (StructuredSelection)selectionManager.getSelection();
		if (selection != null && !selection.isEmpty()) {
			List<?> listSelection = selection.toList();
			
			Integer x = null;
			Integer y = null;
			Integer xe = null;
			Integer ye = null;
			boolean partFound = false;
			for(Object part : listSelection) {
				if (part instanceof FigureEditPart) {
					partFound = true;
 					FigureEditPart figure = (FigureEditPart)part;
					Rectangle bounds = figure.getFigure().getBounds();
					if (x == null || bounds.x < x) {
						x = bounds.x;
					}
					if (y == null || bounds.y < y) {
						y = bounds.y;
					}
					if (xe == null || bounds.x + bounds.width > xe) {
						xe = (bounds.x + bounds.width) - 1;
					}
					if (ye == null || bounds.y + bounds.height > ye) {
						ye = (bounds.y + bounds.height) - 1;
					}
				}
			}
			if (partFound) {
				double zoomFactor = zoomManager.getZoom();
				x = (int)Math.round(x*zoomFactor);
				y = (int)Math.round(y*zoomFactor);
				xe = (int)Math.round(xe*zoomFactor);
				ye = (int)Math.round(ye*zoomFactor);
				return new Rectangle(x, y, xe - x, ye - y);
			}
		}
		return new Rectangle();
	}
}
