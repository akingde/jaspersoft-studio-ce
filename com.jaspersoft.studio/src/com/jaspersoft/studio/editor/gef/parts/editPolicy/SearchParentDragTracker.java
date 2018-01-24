/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.DragEditPartsTracker;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.JSSScalableFreeformRootEditPart;
import com.jaspersoft.studio.editor.gef.parts.ReportPageEditPart;
import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IDesignDragable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.preferences.bindings.BindingsPreferencePersistence;

/**
 * This drag tracker redefine the behavior when an element is moved or resized. Usually an element can not be moved over
 * other edit part, and can not be resized to overlap them. This drag tracker change this behavior taking as the target
 * edit part an IContainer, that could be the actual target or the container of the actual target
 * 
 * By default if the selected elements had different parents the drag operation doesn't change their parent. Otherwise if
 * the parent is the same then the selected elements will be moved to the destination
 * 
 * It allow an hotkey to drag the selection only vertically or horizontally
 * 
 * It allow an hotkey to force the dragged to use the target parent even if the source selection comes from different parents
 * 
 * The hotkeys can be combined and configured
 * 
 * @author Orlandin Marco
 * 
 */
public class SearchParentDragTracker extends DragEditPartsTracker {
	
	/** defines the range where autoscroll is active inside a viewer */
	private static final int DEFAULT_EXPOSE_THRESHOLD = 50;
	
	/**
	 * Id of the key binding action to enable the straight movement of the element
	 */
	private static final String STRAIGHT_DRAG_KEY_ID = "com.jaspersoft.studio.editor.straightmovment";
	
	/**
	 * Id of the key binding action to disable the snap of the moved element
	 */
	private static final String NO_SNAPPING_DRAG_KEY_ID = "com.jaspersoft.studio.editor.straightmovment.nosnapping";
	
	/**
	 * This is the modifier key (A button) that is used to modify the drag and drop
	 * operation behavior. When this key is pressed during a drag and drop the children will
	 * never change parent
	 */
	protected static final String MOVE_CHILD_KEY_ID = "com.jaspersoft.studio.editor.enforcechangeparent";
	
	/**
	 * This is the modifier key (A button) that is used to modify the drag and drop
	 * operation behavior. When this key is pressed during a drag and drop the children will
	 * never change parent
	 */
	protected static final String KEEP_PARENT_KEY_ID = "com.jaspersoft.studio.editor.enforcekeepparent";

	/**
	 * This variable contains all the hierarchy of the elements dragged, to avoid that an element is placed inside
	 * one of his descendant. To avoid excessive calculations this variable is initialized ad the drag start and 
	 * clear at the drag end
	 */
	private HashSet<INode> selectionHierarchy = null; 
	
	/**
	 * Zoom manager of the current viewer
	 */
	private ZoomManager zoomManager;
	
	/**
	 * Flag used to know if the current drag operation contain elements with a different parent or 
	 * the parent of an element is a frame. When this flag is enabled the parent of the moved elements
	 * never change
	 */
	private boolean keepParentDrag = false;
	
	/**
	 * An implementation of {@link org.eclipse.gef.AutoexposeHelper} that performs autoscrolling of a
	 * <code>Viewport</code> figure. This helper is for use with graphical editparts that contain a viewport figure.
	 * Autoscroll will occur when the detect location is near the viewport edges. It will continue for as long as the
	 * location continues to meet these criteria.
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class DragAutoExpose implements AutoexposeHelper {

		/**
		 * Viewport of the scrolled area
		 */
		private Viewport port;

		/** the last time an auto expose was performed */
		private long lastStepTime = 0;

		/** The insets for this helper. */
		private int threshold;

		/**
		 * Create the class
		 * 
		 * @param port
		 *          the viewport figure
		 * @param threshold
		 *          if the location is inside the area between the viewport edges and the threshold then view will be
		 *          autoscrolled. Other than this the threshold is used to calculate how much the screen is scrolled, infact
		 *          it is scrolled twice the threshold in the correct direction
		 */
		public DragAutoExpose(Viewport port, int threshold) {
			this.port = port;
			this.threshold = threshold;
		}

		/**
		 * Build the class with a default threshold of 50
		 * 
		 * @param port
		 */
		@SuppressWarnings("unused")
		public DragAutoExpose(Viewport port) {
			this(port, DEFAULT_EXPOSE_THRESHOLD);
		}

		/**
		 * Returns <code>true</code> if the given point is inside the viewport, but near its edge, so if the autoscroll
		 * action is needed
		 * 
		 * @param where
		 *          the mouse's current location in the viewer
		 * @return true if the autoexpose action should be done because the location is too near to the viewports edge,
		 *         otherwise true
		 */
		@Override
		public boolean detect(Point where) {
			lastStepTime = 0;
			Rectangle rect = Rectangle.SINGLETON;
			port.getClientArea(rect);
			port.translateToParent(rect);
			port.translateToAbsolute(rect);
			boolean needChange = false;
			if (where.x >= rect.width - threshold)
				needChange = true;
			if (where.y >= rect.height - threshold)
				needChange = true;
			if (where.x < threshold)
				needChange = true;
			if (where.y < threshold)
				needChange = true;
			return needChange;
		}

		/**
		 * Check if the autoscroll action should be take from a time point o view. This is done to avoid the the autoscroll
		 * is too fast so between every autoscroll a little lag is introduced
		 * 
		 * @return true if the last autoscroll was done too few ago and we need to wait, false otherwise
		 */
		private boolean needToWait() {
			// set scroll offset (speed factor)
			int scrollOffset = 0;

			// calculate time based scroll offset
			if (lastStepTime == 0)
				lastStepTime = System.currentTimeMillis();

			long difference = System.currentTimeMillis() - lastStepTime;

			if (difference > 0) {
				scrollOffset = ((int) difference / 3);
				lastStepTime = System.currentTimeMillis();
			}

			if (scrollOffset == 0)
				return true;
			return false;
		}

		/**
		 * Returns <code>true</code> if the given point is outside the viewport or near its edge. Scrolls the viewport by a
		 * calculated (time based) amount in the current direction. To avoid infinite autoscroll a there is a bound, calculated 
		 * on the dragged element size
		 * 
		 * @param where
		 *          the current location of the mouse in the viewer
		 * 
		 * @return a hint indicating whether this helper should continue to be invoked
		 */
		@Override
		public boolean step(Point where) {
			Rectangle rect = Rectangle.SINGLETON;
			port.getClientArea(rect);
			port.translateToParent(rect);
			port.translateToAbsolute(rect);
			Point loc = port.getViewLocation();
			Point targetloc = port.getViewLocation();
			//Rectangle dragSize = ((AJDEditPart) getSourceEditPart()).getFigure().getBounds();
			//The autoscroll bounds are calculated on the size of the element dragged
			//int elementSizeOffset = Math.abs((dragSize.x + dragSize.y)*2);
			if (needToWait())
				return true;
			//System.out.println("where: "+where);
			if (where.x >= rect.width - threshold)
				targetloc.x += threshold * 2;
			if (where.y >= rect.height - threshold)
				targetloc.y += threshold * 2;
			if (where.x < threshold)
				targetloc.x -= threshold * 2;
			//System.out.println(where.y + "<"+ threshold +"&&"+ (loc.y + elementSizeOffset) +">0");
			if (where.y < threshold)
				targetloc.y -= threshold * 2;
			
			if (!loc.equals(targetloc)) {
				port.setViewLocation(targetloc);
				updateTargetUnderMouse();
				return true;
			}
			return false;
		}

	}
	
	/**
	 * Enumeration to store the mouse movement main direction, it can be
	 * horizontal or vertical or undefined if was not still defined
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private enum MOUSE_DIRECTION {HORIZONTAL, VERTICAL, UNDEFINED};
	
	/**
	 * variable to store the mouse direction during the drag
	 */
	private MOUSE_DIRECTION firstMovment = MOUSE_DIRECTION.UNDEFINED;
	
	public SearchParentDragTracker(EditPart sourceEditPart) {
		super(sourceEditPart);
	}
	
	/**
	 * Handle the drag in progress event, it is different from the default one because it check if the AutoxposeHelper is
	 * set, and if it isn't it create a new one using the viewport of the active window. Then it call the default
	 * handleDragInProgress
	 */
	protected boolean handleDragInProgress() {
		/*
		 * If the drag tracker has not an autoexpose helper it will be set. Since we are doing this in the drag in progress
		 * we can take it from the active window, that should be the report editor
		 */
		if (getAutoexposeHelper() == null) {
			// JrxmlEditor editor =
			// (JrxmlEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getPart();
			// editor.getReportContainer().getMainEditor().getEditor().getViewport();
			org.eclipse.draw2d.Viewport port = ((FigureCanvas) getCurrentViewer().getControl()).getViewport();
			setAutoexposeHelper(new DragAutoExpose(port, 50));
		}
		if (!isInState(STATE_TERMINAL)){
			Command command = getCurrentCommand();
			if (command == null || !command.canExecute()){
				eraseSourceFeedback();
			}
		}
		return super.handleDragInProgress();
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#getDebugName()
	 */
	protected String getDebugName() {
		return "SameParentDragTracker:" + getCommandName();
	}

	/**
	 * Take an edit part and search its container. Check also the actual selected elements to avoid
	 * that the selected parent is the moved element. In this case the grandparent is searched (this is 
	 * a particular case where a frame is dragged on one of its children, so the destination will be the 
	 * dragged frame itself, becoming source and target of the selection)
	 * 
	 * @param child
	 * @return the container of the child, could be null
	 */
	private EditPart searchContainer(EditPart child) {
		if (child != null) {
			ANode parentModel = ((ANode) child.getModel()).getParent();
			if (parentModel == null)
				return null;
			//If the drop is outside the page search the band on the y axis or the neares one if
			//the position is above or under the bands
			if (child instanceof ReportPageEditPart){
				ReportPageEditPart rootPart = (ReportPageEditPart)child;
				ChangeBoundsRequest req = (ChangeBoundsRequest)getTargetRequest();
				FigureEditPart lastBand = null;
				FigureEditPart firstBand = null;
				for(Object element : rootPart.getChildren()){
					if (element instanceof BandEditPart){
						FigureEditPart actualChildPart = (FigureEditPart) element;
						if (firstBand == null) firstBand = actualChildPart;
						lastBand = actualChildPart;
						Rectangle bounds = actualChildPart.getFigure().getBounds();
						if (bounds.y <= req.getLocation().y && req.getLocation().y <= bounds.y + bounds.height){
							return actualChildPart;
						}
					}
				}
				if (firstBand != null && req.getLocation().y < firstBand.getFigure().getBounds().y){
					return firstBand;
				}
				if (lastBand != null){
					Rectangle bounds = lastBand.getFigure().getBounds();
					if (req.getLocation().y >  bounds.y + bounds.height ){
						return lastBand;
					}
				}
				return null;
			}
			//I search the first container of the target element that it's not in the exclusion set
			if(selectionHierarchy!=null) {
				while (selectionHierarchy.contains(parentModel)){
					parentModel = parentModel.getParent();
					if (parentModel == null)
						return null;
				}
			}
			// This use the model for the search because every EditPart in the report has the same father.
			for (Object actualChild : child.getParent().getChildren()) {
				EditPart actualChildPart = (EditPart) actualChild;
				if (parentModel == actualChildPart.getModel()) return actualChildPart;
			}
		}
		return null;
	}
	
	/**
	 * Take an edit part and search its parent
	 * 
	 * @param child the child
	 * @return the container of the child, could be null
	 */
	private EditPart getParent(EditPart child) {
		if (child != null) {
			ANode parentModel = ((ANode) child.getModel()).getParent();
			if (parentModel == null)
				return null;
			// This use the model for the search because every EditPart in the report has the same father.
			for (Object actualChild : child.getParent().getChildren()) {
				EditPart actualChildPart = (EditPart) actualChild;
				if (parentModel == actualChildPart.getModel()) return actualChildPart;
			}
		}
		return null;
	}
	
	
	/**
	 * Build an set of invalid target for the drop. The invalid target are all the elements 
	 * in the dragged selection plus their descendants. It also initialize the flag that identify
	 * if this is a multi parent drag or not. This flag is used also if the parent of one of the dragged
	 * elements is a frame, since with it the same behavior of the multi drag is applied 
	 * 
	 * @return an hash set with the models of the invalid target elements for a drop operation
	 */
	private HashSet<INode> getSelectionDesendent(){
		HashSet<INode> result = new HashSet<INode>();
		INode lastParent = null;
		for (Object part : getCurrentViewer().getSelectedEditParts()){
			if (part instanceof EditPart){
				EditPart ep = (EditPart)part;
				INode model = (INode) ep.getModel();
				INode currentParent = model.getParent();
				if (!keepParentDrag){
					//Optimization: ignore the check if i've already found a behavior changer
					if (currentParent instanceof MFrame){
						keepParentDrag = true;
					} else if (lastParent == null){
						lastParent = currentParent;
					} else if (currentParent != lastParent){
						keepParentDrag = true;
					}
				}
				result.add(model);
				if (model instanceof IContainer) getSelectionDesendentRecursive(model.getChildren(), result);
			}
		}
		return result;
	}
	
	/**
	 * Support method for getSelectionDesendent, do the recursion on the container. Check a list of children 
	 * and if one of them is an IContainer also its children are checked
	 * 
	 * @param children children to check
	 * @param foundedElements an hash set with the models of the invalid target elements for a drop operation
	 */
	private void getSelectionDesendentRecursive(List<INode> children, HashSet<INode> foundedElements){
		for(INode child : children){
			foundedElements.add(child);
			if (child instanceof IContainer) getSelectionDesendentRecursive(child.getChildren(), foundedElements);
		}
	}
	
	/**
	 * Recursive method that take a frame and the element actually in the selection,
	 * then search in this selection for descendants of the frame an the found one 
	 * are set to null
	 * 
	 * @param parent Frame for which the descendant are searched
	 * @param actualSelection the element actually selected
	 */
	private void removeNestedElements(MFrame parent, List<EditPart> actualSelection){
		for(int i=0; i<actualSelection.size(); i++){
			EditPart element = actualSelection.get(i);
			if (element != null){
				ANode model = (ANode) element.getModel() ;
				if (model.getParent() == parent) {
					//If a descendant is a frame then a recursive call is done
					if (model instanceof MFrame) removeNestedElements((MFrame)model, actualSelection);
					actualSelection.set(i, null);
				}
			}
		}
	}

	/**
	 * This method return the elements affected by the drag operation. The overridden version
	 * exclude from this elements the one that are children or descendant of a frames actually selected.
	 * This avoid that the move operations are done on the frame and also on his descendants, that may 
	 * create error like the change of parent or a wrong position for the children of the frame 
	 * It also consider the MgraphicalElements since they are the only handled by this tracker
	 */
	@SuppressWarnings("rawtypes")
	protected List createOperationSet() {
		ArrayList<EditPart> selectedElements = new  ArrayList<EditPart>();
		//Need to copy the list because the one from getCurrentViewer().getSelectedEditParts() is not editable
		for (Object part : getCurrentViewer().getSelectedEditParts()){
			if (((EditPart)part).getModel() instanceof IDesignDragable) selectedElements.add((EditPart)part);
		}
		//The result of the following for is an array where all the element not null are valid for the selection
		for(EditPart element : selectedElements){
			if (element != null && element.getModel() instanceof MFrame) 
				removeNestedElements((MFrame)element.getModel(),selectedElements);
		}
		//Rebuild an array removing the null element
		ArrayList<EditPart> result = new ArrayList<EditPart>();
		for(EditPart element : selectedElements){
			if (element != null) result.add(element);
		}
		return result;
	}
	
	/**
	 * When the drag start the exclusion set based on the selected element is build
	 */
	protected boolean handleDragStarted() {
		selectionHierarchy = getSelectionDesendent();
		return super.handleDragStarted();
	};
	
	/**
	 * Search a valid node that has as ancestor a node to disable the 
	 * editor refresh. The node is searched between the selection set
	 * 
	 * @return the node if it is found, null otherwise
	 */
	private ANode getLockableNode(){
		for(Object part : getOperationSet()){
			if (part instanceof EditPart){
				EditPart ePart = (EditPart)part;
				if (ePart.getModel() instanceof ANode){
					ANode mainNode = JSSCompoundCommand.getMainNode((ANode)ePart.getModel());
					if (mainNode != null) return mainNode;
				}
			}
		}
		return null;
	}
	
	/**
	 * When the command from a drag operation is returned it is checked if it a compoundcommand
	 * but not JSSCompoundCommand. If this condition it is true then it is converted into
	 * a JSSCompoundCommand to improve the performance during the execution
	 */
	@Override	
	protected Command getCurrentCommand() {
		Command command = super.getCurrentCommand();
		if (!(command instanceof JSSCompoundCommand)){
			if (command instanceof CompoundCommand){
				CompoundCommand cc = (CompoundCommand)command;
				JSSCompoundCommand jsscc = new JSSCompoundCommand(cc,getLockableNode()) ;
				command = jsscc;
			} else {
				JSSCompoundCommand jsscc = new JSSCompoundCommand(getLockableNode());
				jsscc.add(command);
				command = jsscc;
			}
		}
		return command;
	}
	
	/**
	 * Create the command for the drag and drop operation. If the 
	 * drag modifier key is pressed then the operation is considered a drag
	 * where every element change parent so the move commands are generated
	 * by the standard command. Otherwise if there is a multidrag  in progress or the modifier 
	 * to never change the perent is active the command are generated in a way 
	 * to dosen't change the parent of the element
	 */
	protected Command getCommand() {
		boolean useOldBheavior = BindingsPreferencePersistence.isPressed(MOVE_CHILD_KEY_ID);
		if (useOldBheavior){
			return super.getCommand();
		} else { 
			boolean forceKeepParent = BindingsPreferencePersistence.isPressed(KEEP_PARENT_KEY_ID);
			if (keepParentDrag || forceKeepParent){
				return getKeepParentCommand();
			} else {
				return super.getCommand();
			}
		}
	}
	
	/**
	 * Create a command where the elements are always moved in the bounds of their parent, so
	 * it is always a move children operation. This means that the selected elements doesn't change 
	 * parent.
	 */
	private Command getKeepParentCommand(){
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel("Drag Object Tracker");//$NON-NLS-1$

		Iterator<?> iter = getOperationSet().iterator();
		ChangeBoundsRequest request = (ChangeBoundsRequest)getTargetRequest();
		request.setType(REQ_MOVE_CHILDREN);
		while (iter.hasNext()) {
			EditPart editPart = (EditPart) iter.next();
			request.setEditParts(editPart);
			EditPart parent = getParent(editPart);
			if (parent != null){
				command.add(parent.getCommand(request));
			}
		}
		return command.unwrap();
	}
	
	/**
	 * Avoid to show the target feedback when the elements are
	 * not going to change the parent
	 */
	@Override
	protected void showTargetFeedback() {
		Command command = getCurrentCommand();
		if (command != null && command.canExecute()){
			boolean useOldBheavior = BindingsPreferencePersistence.isPressed(MOVE_CHILD_KEY_ID);
			boolean forceKeepParent = BindingsPreferencePersistence.isPressed(KEEP_PARENT_KEY_ID);
			if (!(forceKeepParent || keepParentDrag) || useOldBheavior){
				super.showTargetFeedback();
			}
		}
	}
	
	
	/**
	 * Don't show the feedback if the command can`t be executed
	 */
	@Override
	protected void showSourceFeedback() {
		Command command = getCurrentCommand();
		if (command != null && command.canExecute()){
			super.showSourceFeedback();
		}
	}
	
	/**
	 * When the drag is done the exclusion set is cleared
	 */
	@Override
	protected void performDrag() {
		super.performDrag();
		selectionHierarchy = null;
		//At the end of the drag operation the mouse direction reset
		firstMovment = MOUSE_DIRECTION.UNDEFINED;
		//reset the multiparent flag
		keepParentDrag = false;
	}
	
	/**
	 * Called to get the destination edit part during a drag and drop, if the destination its not a container the it
	 * parent its taken
	 */
	protected EditPart getTargetEditPart() {
		EditPart target = super.getTargetEditPart();
		return getTargetEditPart(target);
	}
	
	/**
	 * If the passed part is not a container search the fisrst valid
	 * part for the drop operation and the return it
	 */
	protected EditPart getTargetEditPart(EditPart target) {
		EditPart parent = null;
		if (!(target instanceof IContainer) || 
				(selectionHierarchy != null && selectionHierarchy.contains(target.getModel()))){
			//If the target of the operation is not a Container or it is into an exclusion set, then the most
			//near valid parent of the target is searched
			parent = searchContainer(target);
		}
		EditPart result= parent != null ? parent : target;
		return result;
	}

	/**
	 * Return the current level of zoom
	 */
	protected double getZoom(){
		if (zoomManager == null){
			EditPartViewer viewer = getCurrentViewer();
			zoomManager = (ZoomManager)viewer.getProperty(ZoomManager.class.toString());
			if (zoomManager == null){
				//fallback
				zoomManager = ((JSSScalableFreeformRootEditPart) viewer.getRootEditPart()).getZoomManager();
			}
		}
		return zoomManager != null ? zoomManager.getZoom() : 1d;
	}
	
	/**
	 * When the request is created it check the bounds to avoid the drag 
	 * of the item too far from the current page
	 */
	@Override
	protected Request getTargetRequest() {
		ChangeBoundsRequest request = (ChangeBoundsRequest)super.getTargetRequest();
		if (request.getEditParts() != null && request.getEditParts().size() > 0){
			ANode node = null;
			for(Object part : request.getEditParts()){
				node = (ANode)((EditPart)part).getModel();
				break;
			}
			org.eclipse.swt.graphics.Point maximumSize = node.getAvailableSize();
			double zoom = 1/getZoom();
			double moveDelta_x = request.getMoveDelta().x*zoom;
			double moveDelta_y = request.getMoveDelta().y*zoom;
			if (moveDelta_x != 0 || moveDelta_y != 0){
				for(Object part : request.getEditParts()){
					IDesignDragable gElement = (IDesignDragable)((EditPart)part).getModel();
					Rectangle bounds = gElement.getJRBounds();
					
					double newX = bounds.x+moveDelta_x;
					if (newX >= 0){
						if (Math.abs(bounds.width)+newX>maximumSize.x){
							double delta = (maximumSize.x - bounds.width - bounds.x)/zoom;
							request.getMoveDelta().setX((int)Math.round(delta));
						}
					} else {
						if (Math.abs(newX)>maximumSize.x){
							double delta = (-maximumSize.x - bounds.x)/zoom;
							request.getMoveDelta().setX((int)Math.round(delta));
						}
					}
					
					double newY = bounds.y + moveDelta_y;
					if (newY > 0){
						if (Math.abs(bounds.height)+newY>maximumSize.y){
							double delta = (maximumSize.y - bounds.height - bounds.y)/zoom;
							request.getMoveDelta().setY((int)Math.round(delta));
						}
					} else {
						if (Math.abs(newY)>maximumSize.y){
							double delta = (-maximumSize.y - bounds.y)/zoom;
							request.getMoveDelta().setY((int)Math.round(delta));
						}
					}
				}
			}
		}
		return request;
	}
	
	/**
	 * The JSS Drag Tracker doesn't support cloning
	 */
	@Override
	protected boolean isCloneActive() {
		return false;
	}
	
	/**
	 * Calls {@link #repairStartLocation()} in case auto scroll is being
	 * performed. Updates the request with the current
	 * {@link AbstractTool#getOperationSet() operation set}, move delta,
	 * location and type.
	 * 
	 * Modify the drag behavior when the shift key is pressed. when it is pressed if the mouse
	 * if moved over an offset (actually ten pixel) in horizontal or in vertical then the element
	 * will be moved only on the horizontal or vertical axis, with all the snap deactivated 
	 */
	@Override
	protected void updateTargetRequest() {
		repairStartLocation();
		ChangeBoundsRequest request = (ChangeBoundsRequest) getTargetRequest();
		request.setEditParts(getOperationSet());
		Dimension delta = getDragMoveDelta();

		request.setSnapToEnabled(!BindingsPreferencePersistence.isPressed(NO_SNAPPING_DRAG_KEY_ID));
		request.setConstrainedMove(BindingsPreferencePersistence.isPressed(STRAIGHT_DRAG_KEY_ID));
		
		if (request.isConstrainedMove()) {
			int x = Math.abs(delta.width);
			int y = Math.abs(delta.height);
			//check if the offset threshold is passed
			if (x>10 && x>y && firstMovment == MOUSE_DIRECTION.UNDEFINED) firstMovment = MOUSE_DIRECTION.HORIZONTAL;
			if (y>10 && y>x && firstMovment == MOUSE_DIRECTION.UNDEFINED) firstMovment = MOUSE_DIRECTION.VERTICAL;
			if (firstMovment != MOUSE_DIRECTION.UNDEFINED){
				if (firstMovment == MOUSE_DIRECTION.VERTICAL) delta.width = 0;
				else delta.height = 0;
			}
		} else {
			//Shift was released so use the default snap hander and reset the mouse direction
			firstMovment = MOUSE_DIRECTION.UNDEFINED;
		}

		Point moveDelta = new Point(delta.width, delta.height);
		request.getExtendedData().clear();
		request.setMoveDelta(moveDelta);
		snapPoint(request);

		request.setLocation(getLocation());
		request.setType(getCommandName());
	}
	
	/**
	 * The update of the target under the mouse is done using 
	 * the getTargetEditPart from this class
	 */
	protected boolean updateTargetUnderMouse() {
		if (!isTargetLocked()) {
			EditPart editPart  = getCurrentViewer().findObjectAtExcluding(getLocation(), getExclusionSet(), getTargetingConditional());
			if (editPart != null){
				editPart = getTargetEditPart(editPart);
			}
			boolean changed = super.getTargetEditPart() != editPart;
			setTargetEditPart(editPart);
			return changed;
		}
		return false;
	}
};
