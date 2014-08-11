/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.band;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.gef.util.EditPartUtilities;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.compatibility.ToolUtilitiesCompatibility;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.compatibility.FigureUtilities;

/**
 * This class override the original MarqueeDragTracker to add the drag selection without selecting the marquee tool.
 * Despite the fact that it share many method with the standard MarqueeDragTracker it's not an extension but a copy.
 * This because MarqueeDragTracker dosen't provide an easy way to change the selection appearance. So this extended copy
 * was done to give this possibility. This class handle a double type of selection, in fact when selecting up to down
 * only elements Completely enclosed in the selection will be selected, otherwise an element will be selected even if
 * it's only touched.
 * 
 * @author Orlandin Marco
 * 
 */
public class SameBandEditPartsTracker extends SelectEditPartTracker {

	private static Color borderColor = null;
	private static Color fillColor = null;

	/**
	 * The Figure painted by the selection
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	class MarqueeRectangleFigure extends Figure {

		private static final int DELAY = 110; // animation delay in millisecond
		private int offset = 0;
		private boolean schedulePaint = true;

		/**
		 * Paint a semi-transparent rectangle
		 */
		protected void paintFigure(Graphics graphics) {
			Rectangle bounds = getBounds().getCopy();
			graphics.translate(getLocation());
			Graphics2D gr = ((J2DGraphics) graphics).getGraphics2D();
			// gr.setColor(new java.awt.Color(168,202,236,128));
			gr.setColor(fillColor);
			gr.fillRect(0, 0, bounds.width - 1, bounds.height - 1);
			gr.setStroke(new BasicStroke(2.0f));
			gr.setColor(borderColor);
			gr.drawRect(0, 0, bounds.width - 1, bounds.height - 1);
			if (schedulePaint) {
				Display.getCurrent().timerExec(DELAY, new Runnable() {
					public void run() {
						offset++;
						if (offset > 5)
							offset = 0;

						schedulePaint = true;
						repaint();
					}
				});
			}

			schedulePaint = false;
		}
	}

	/**
	 * This behavior selects connections that intersect the marquee rectangle.
	 * 
	 * @since 3.7
	 */
	public static final int BEHAVIOR_CONNECTIONS_CONTAINED = new Integer(6).intValue();

	/**
	 * This behavior selects connections that intersect the marquee rectangle.
	 * 
	 * @since 3.1
	 */
	public static final int BEHAVIOR_CONNECTIONS_TOUCHED = new Integer(2).intValue();

	/**
	 * This behavior selects nodes completely encompassed by the marquee rectangle. This is the default behavior for this
	 * tool.
	 * 
	 * @since 3.1
	 */
	public static final int BEHAVIOR_NODES_CONTAINED = new Integer(1).intValue();

	/**
	 * This behavior selects nodes completely encompassed by the marquee rectangle, and all connections between those
	 * nodes.
	 * 
	 * @since 3.7
	 */
	public static final int BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS = new Integer(3).intValue();

	/**
	 * This behavior selects nodes that intersect the marquee rectangle.
	 * 
	 * @since 3.7
	 */
	public static final int BEHAVIOR_NODES_TOUCHED = new Integer(4).intValue();

	/**
	 * This behavior selects nodes that intersect the marquee rectangle.
	 * 
	 * @since 3.7
	 */
	public static final int BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS = new Integer(5).intValue();

	/**
	 * This behavior selects nodes completely encompassed by the marquee rectangle, and all connections between those
	 * nodes.
	 * 
	 * @since 3.1
	 * @deprecated use {@link #BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS} instead.
	 */
	public static final int BEHAVIOR_NODES_AND_CONNECTIONS = BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS;

	static final int DEFAULT_MODE = 0;
	static final int TOGGLE_MODE = 1;
	static final int APPEND_MODE = 2;

	/**
	 * Point from where the drag selection action start
	 */
	private int dragStart = -1;

	public SameBandEditPartsTracker(EditPart owner) {
		super(owner);
		if (borderColor == null) {
			fillColor = new java.awt.Color(168, 202, 236, 128);
			borderColor = new java.awt.Color(0, 50, 200, 128);
		}
	}

	public SameBandEditPartsTracker() {
		this(null);
	}

	/**
	 * When a click is done in a band only that band will be selected and all others elements deselected, when is done out
	 * of the work area all the elements will be deselected
	 */
	@Override
	public void mouseUp(MouseEvent me, EditPartViewer viewer) {

		boolean wasDragging = movedPastThreshold();
		if (me.button == 1 && !wasDragging) {
			EditPart clickedPart = viewer.findObjectAt(new Point(me.x, me.y));
			if (clickedPart instanceof BandEditPart) {
				viewer.select(clickedPart);
			} else
				viewer.deselectAll();
		} else
			super.mouseUp(me, viewer);
	};

	/**
	 * Called when the mouse button is released. Overridden to do nothing, since a drag tracker does not need to unload
	 * when finished.
	 */
	protected void handleFinished() {
		dragStart = -1;
		if (unloadWhenFinished())
			getDomain().loadDefaultTool();
		else
			reactivate();
	}

	private static final Request MARQUEE_REQUEST = new Request(RequestConstants.REQ_SELECTION);

	/**
	 * The property to be used in {@link AbstractTool#setProperties(java.util.Map)} for {@link #setMarqueeBehavior(int)}.
	 */
	public static final Object PROPERTY_MARQUEE_BEHAVIOR = "marqueeBehavior"; //$NON-NLS-1$

	/**
	 * Constant defining the default marquee selection behavior.
	 * 
	 * @since 3.7
	 */
	public static final int DEFAULT_MARQUEE_BEHAVIOR = BEHAVIOR_NODES_CONTAINED;

	private Set<?> allChildren = new HashSet<Object>();
	private int marqueeBehavior = DEFAULT_MARQUEE_BEHAVIOR;
	private Figure marqueeRectangleFigure;
	private int mode;

	private Collection<?> selectedEditParts;

	private Request targetRequest;

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#applyProperty(java.lang.Object, java.lang.Object)
	 */
	protected void applyProperty(Object key, Object value) {
		if (PROPERTY_MARQUEE_BEHAVIOR.equals(key)) {
			if (value instanceof Integer)
				setMarqueeBehavior(((Integer) value).intValue());
			return;
		}
		super.applyProperty(key, value);
	}

	/**
	 * Called from {@link #performMarqueeSelect()} to determine those {@link EditPart}s that are affected by the current
	 * marquee selection. In default and append mode, the edit parts returned here will become selected in the current
	 * viewer's new selection (which is calculated and set in {@link #performMarqueeSelect()}), while in toggle mode their
	 * selection state will be inverted.
	 * 
	 * Calculation is delegated to {@link #calculatePrimaryMarqueeSelectedEditParts()} and
	 * {@link #calculateSecondaryMarqueeSelectedEditParts(Collection)} to compute the set of marquee selected edit parts
	 * in a two step-process, where all directly affected edit parts are determined first, and those indirectly affected
	 * (related connections in case of {@link #BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS}, or
	 * {@link #BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS}) afterwards.
	 * 
	 * Clients may overwrite to customize the calculation of marquee selected edit parts.
	 * 
	 * @return A collection containing all edit parts that should be regarded as being included in the current marquee
	 *         selection, i.e. which should get selected in default or append mode, and whose selection state should get
	 *         inverted in toggle mode.
	 * @since 3.7
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Collection calculateMarqueeSelectedEditParts() {
		Collection marqueeSelectedEditParts = new HashSet();
		marqueeSelectedEditParts.addAll(calculatePrimaryMarqueeSelectedEditParts());
		marqueeSelectedEditParts.addAll(calculateSecondaryMarqueeSelectedEditParts(marqueeSelectedEditParts));
		return marqueeSelectedEditParts;
	}

	/**
	 * Responsible of calculating those edit parts that should be regarded as directly affected by the current marquee
	 * selection. By default, the method calculates which edit parts are potential candidates based on the current marquee
	 * behavior and delegates to {@link #isMarqueeSelectable(GraphicalEditPart)} and
	 * {@link #isPrimaryMarqueeSelectedEditPart(GraphicalEditPart)} to decide whether the candidate is to be included in
	 * the marquee selection.
	 * 
	 * @return A {@link Collection} containing all {@link EditPart}s that should be regarded as being directly affected by
	 *         the current marquee selection.
	 * @since 3.7
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection calculatePrimaryMarqueeSelectedEditParts() {
		Collection editPartsToProcess = new HashSet();
		if (marqueeBehavior != BEHAVIOR_CONNECTIONS_CONTAINED && marqueeBehavior != BEHAVIOR_CONNECTIONS_TOUCHED) {
			// process nodes
			editPartsToProcess.addAll(EditPartUtilities.getAllChildren((GraphicalEditPart) getCurrentViewer().getRootEditPart()));
		}

		if (marqueeBehavior != BEHAVIOR_NODES_CONTAINED && marqueeBehavior != BEHAVIOR_NODES_TOUCHED) {
			// process connections
			editPartsToProcess.addAll(EditPartUtilities.getAllNestedConnectionEditParts((GraphicalEditPart) getCurrentViewer().getRootEditPart()));
		}

		// process all edit parts and determine which are affected by the
		// current marquee selection
		Collection marqueeSelectedEditParts = new ArrayList();
		if (!editPartsToProcess.isEmpty()) {
			EditPart pageEditPart = ToolUtilitiesCompatibility.getPageEditPart((EditPart)editPartsToProcess.iterator().next());
			editPartsToProcess.remove(pageEditPart.getChildren().get(0));
		}
		for (Iterator iterator = editPartsToProcess.iterator(); iterator.hasNext();) {
			GraphicalEditPart editPart = (GraphicalEditPart) iterator.next();
			//The page and the bands are not valid selectable items, so the isMarqueeSelectable retrun false for every element that it isn't an
			//MGraphical element
			if (isMarqueeSelectable(editPart) && isPrimaryMarqueeSelectedEditPart(editPart)) {
				marqueeSelectedEditParts.add(editPart);
			}
		}
		return marqueeSelectedEditParts;
	}

	/**
	 * Responsible of calculating those edit parts that should be regarded as being indirectly affected by the marquee
	 * selection. By default, the method calculates which edit parts are potential candidates based on the current marquee
	 * behavior and delegates to {@link #isMarqueeSelectable(GraphicalEditPart)} and
	 * {@link #isSecondaryMarqueeSelectedEditPart(Collection, EditPart)} to decide whether the candidate is to be included
	 * in the marquee selection.
	 * 
	 * @param directlyMarqueeSelectedEditParts
	 *          A collection containing those {@link EditPart}s that were already identified as being directly affected by
	 *          the marquee selection
	 * @return A {@link Collection} containing all {@link EditPart}s that are indirectly affected by the current marquee
	 *         selection
	 * @since 3.7
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection calculateSecondaryMarqueeSelectedEditParts(Collection directlyMarqueeSelectedEditParts) {

		Collection editPartsToProcess = new HashSet();
		for (Iterator iterator = directlyMarqueeSelectedEditParts.iterator(); iterator.hasNext();) {
			GraphicalEditPart marqueeSelectedEditPart = (GraphicalEditPart) iterator.next();
			editPartsToProcess.addAll(marqueeSelectedEditPart.getSourceConnections());
			editPartsToProcess.addAll(marqueeSelectedEditPart.getTargetConnections());
		}

		// process all edit parts and decide, whether they are indirectly
		// affected by marquee selection
		Collection secondaryMarqueeSelectedEditParts = new HashSet();
		if (!editPartsToProcess.isEmpty()) {
			EditPart pageEditPart = ToolUtilitiesCompatibility.getPageEditPart((EditPart)editPartsToProcess.iterator().next());
			editPartsToProcess.remove(pageEditPart.getChildren().get(0));
		}
		for (Iterator iterator = editPartsToProcess.iterator(); iterator.hasNext();) {
			GraphicalEditPart editPart = (GraphicalEditPart) iterator.next();
			if (isMarqueeSelectable(editPart) && isSecondaryMarqueeSelectedEditPart(directlyMarqueeSelectedEditParts, editPart)) {
				secondaryMarqueeSelectedEditParts.add(editPart);
			}
		}
		return secondaryMarqueeSelectedEditParts;
	}

	protected Request createTargetRequest() {
		return MARQUEE_REQUEST;
	}

	/**
	 * Erases feedback if necessary and puts the tool into the terminal state.
	 */
	public void deactivate() {
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			eraseMarqueeFeedback();
			eraseTargetFeedback();
		}
		super.deactivate();
		allChildren.clear();
		setState(STATE_TERMINAL);
	}

	protected void eraseMarqueeFeedback() {
		if (marqueeRectangleFigure != null) {
			removeFeedback(marqueeRectangleFigure);
			marqueeRectangleFigure = null;
		}
	}

	protected void eraseTargetFeedback() {
		if (selectedEditParts == null)
			return;
		Iterator<?> oldEditParts = selectedEditParts.iterator();
		while (oldEditParts.hasNext()) {
			EditPart editPart = (EditPart) oldEditParts.next();
			editPart.eraseTargetFeedback(getTargetRequest());
		}
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#getCommandName()
	 */
	protected String getCommandName() {
		return REQ_SELECTION;
	}

	/**
	 * Returns the current marquee selection rectangle.
	 * 
	 * @return A {@link Rectangle} representing the current marquee selection.
	 * @since 3.7
	 */
	protected Rectangle getCurrentMarqueeSelectionRectangle() {
		return new Rectangle(getStartLocation(), getLocation());
	}

	/**
	 * Returns the current selection mode, i.e. default, append, or toggle
	 * 
	 * @return on of {@link #DEFAULT_MODE}, {@link #APPEND_MODE}, or {@link #TOGGLE_MODE}
	 * @since 3.7
	 */
	protected int getCurrentSelectionMode() {
		return mode;
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#getDebugName()
	 */
	protected String getDebugName() {
		return "SameBand Marquee: " + marqueeBehavior;//$NON-NLS-1$
	}

	protected IFigure getMarqueeFeedbackFigure() {
		if (marqueeRectangleFigure == null) {
			marqueeRectangleFigure = new MarqueeRectangleFigure();
			addFeedback(marqueeRectangleFigure);
		}
		return marqueeRectangleFigure;
	}

	protected Request getTargetRequest() {
		if (targetRequest == null)
			targetRequest = createTargetRequest();
		return targetRequest;
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleButtonDown(int)
	 */
	protected boolean handleButtonDown(int button) {
		if (!isCurrentViewerGraphical())
			return true;
		if (button != 1) {
			setState(STATE_INVALID);
			handleInvalidInput();
		}
		if (stateTransition(STATE_INITIAL, STATE_DRAG_IN_PROGRESS)) {
			if (getCurrentInput().isModKeyDown(SWT.MOD1))
				setSelectionMode(TOGGLE_MODE);
			else if (getCurrentInput().isShiftKeyDown())
				setSelectionMode(APPEND_MODE);
			else
				setSelectionMode(DEFAULT_MODE);
		}
		return true;
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleButtonUp(int)
	 */
	protected boolean handleButtonUp(int button) {
		if (stateTransition(STATE_DRAG_IN_PROGRESS, STATE_TERMINAL)) {
			eraseTargetFeedback();
			eraseMarqueeFeedback();
			performMarqueeSelect();
		}
		handleFinished();
		return true;
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleDragInProgress()
	 */
	protected boolean handleDragInProgress() {
		if (isInState(STATE_DRAG | STATE_DRAG_IN_PROGRESS)) {
			if (dragStart == -1) {
				dragStart = getLocation().y;
			}
			showMarqueeFeedback();
			eraseTargetFeedback();
			selectedEditParts = calculateMarqueeSelectedEditParts();
			showTargetFeedback();
		}
		return true;
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#handleFocusLost()
	 */
	protected boolean handleFocusLost() {
		if (isInState(STATE_DRAG | STATE_DRAG_IN_PROGRESS)) {
			handleFinished();
			return true;
		}
		return false;
	}

	/**
	 * This method is called when mouse or keyboard input is invalid and erases the feedback.
	 * 
	 * @return <code>true</code>
	 */
	protected boolean handleInvalidInput() {
		eraseTargetFeedback();
		eraseMarqueeFeedback();
		return true;
	}

	/**
	 * Handles high-level processing of a key down event. KeyEvents are forwarded to the current viewer's
	 * {@link KeyHandler}, via {@link KeyHandler#keyPressed(KeyEvent)}.
	 * 
	 * @see AbstractTool#handleKeyDown(KeyEvent)
	 */
	protected boolean handleKeyDown(KeyEvent e) {
		if (super.handleKeyDown(e))
			return true;
		if (getCurrentViewer().getKeyHandler() != null)
			return getCurrentViewer().getKeyHandler().keyPressed(e);
		return false;
	}

	/**
	 * Decides whether the given edit part may potentially be included in the current marquee selection.
	 * 
	 * @param editPart
	 *          the {@link EditPart} of interest
	 * @return <code>true</code> if the given edit part may be included into the marquee selection, <code>false</code>
	 *         otherwise
	 * @since 3.7
	 */
	protected boolean isMarqueeSelectable(GraphicalEditPart editPart) {
		// IMPORTANT: MarqueeSelectionTool is not a TargetingTool, thus the
		// pre-selection does not depend on hit-testing. Therefore, the visible
		// state of the edit part's figure has to be taken into consideration as
		// well.
		return editPart.getTargetEditPart(MARQUEE_REQUEST) == editPart && editPart.isSelectable()
				&& FigureUtilities.isNotFullyClipped(editPart.getFigure()) && editPart.getModel() instanceof MGraphicElement;
	}

	/**
	 * Determines which edit parts are directly affected by the current marquee selection. Calculation is performed by
	 * regarding the current marquee selection rectangle ( {@link #getCurrentMarqueeSelectionRectangle()}), taking into
	 * consideration the current marquee behavior (contained vs. touched) that was provided (
	 * {@link #setMarqueeBehavior(int)} ).
	 * 
	 * @param editPart
	 *          the {@link EditPart} whose state is to be determined
	 * @return <code>true</code> if the {@link EditPart} should be regarded as being included in the current marquee
	 *         selection, <code>false</code> otherwise.
	 * @since 3.7
	 */
	private boolean isPrimaryMarqueeSelectedEditPart(GraphicalEditPart editPart) {
		// figure bounds are used to determine if edit part is included in
		// selection
		IFigure figure = editPart.getFigure();
		Rectangle r = figure.getBounds().getCopy();
		figure.translateToAbsolute(r);

		boolean included = false;
		Rectangle marqueeSelectionRectangle = getCurrentMarqueeSelectionRectangle();
		if (editPart instanceof ConnectionEditPart) {
			if (marqueeBehavior == BEHAVIOR_CONNECTIONS_TOUCHED || marqueeBehavior == BEHAVIOR_CONNECTIONS_CONTAINED) {
				if (marqueeSelectionRectangle.intersects(r)) {
					// children will contain ConnectionEditParts only in case
					// behavior is BEHAVIOR_CONNECTIONS_TOUCHED or
					// BEHAVIOR_CONNECTIONS_CONTAINED
					Rectangle relMarqueeRect = Rectangle.SINGLETON;
					figure.translateToRelative(relMarqueeRect.setBounds(marqueeSelectionRectangle));
					if (marqueeBehavior == BEHAVIOR_CONNECTIONS_TOUCHED) {
						included = ((Connection) figure).getPoints().intersects(relMarqueeRect);
					} else if (marqueeBehavior == BEHAVIOR_CONNECTIONS_CONTAINED) {
						included = relMarqueeRect.contains(((Connection) figure).getPoints().getBounds());
					}
				}
			}
		} else {
			// otherwise children will only be 'node' edit parts
			if (marqueeBehavior == BEHAVIOR_NODES_TOUCHED
					|| marqueeBehavior == BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS) {
				included = marqueeSelectionRectangle.intersects(r);
			} else if (marqueeBehavior == BEHAVIOR_NODES_CONTAINED
					|| marqueeBehavior == BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS) {
				included = marqueeSelectionRectangle.contains(r);
			}
		}
		return included;
	}

	/**
	 * Determines which edit parts are indirectly affected by the current marquee selection through those edit parts being
	 * directly affected. In case of {@link #BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS} or
	 * {@link #BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS} marquee behavior, this method will be used to calculate the
	 * related connections after all respective nodes have been identified as primary selected edit parts.
	 * 
	 * @param directlyMarqueeSelectedEditParts
	 *          A collection of {@link EditPart}s which are regarded to be directly included in the current marquee
	 *          selection.
	 * @param editPart
	 *          the {@link EditPart} of concern
	 * @return <code>true</code> if the {@link EditPart} should be regarded as being included in the current marquee
	 *         selection, <code>false</code> otherwise.
	 * @since 3.7
	 */
	private boolean isSecondaryMarqueeSelectedEditPart(Collection<?> directlyMarqueeSelectedEditParts, EditPart editPart) {
		boolean included = false;
		if (editPart instanceof ConnectionEditPart
				&& (marqueeBehavior == BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS || marqueeBehavior == BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS)) {
			// connections are included, if related nodes are included
			ConnectionEditPart connection = (ConnectionEditPart) editPart;
			GraphicalEditPart source = (GraphicalEditPart) connection.getSource();
			GraphicalEditPart target = (GraphicalEditPart) connection.getTarget();
			boolean sourceIncludedInMarqueeSelection = directlyMarqueeSelectedEditParts.contains(source);
			boolean targetIncludedInMarqueeSelection = directlyMarqueeSelectedEditParts.contains(target);

			if (mode == DEFAULT_MODE) {
				// in default mode, select connection if source and
				// target are included in marqee selection
				included = sourceIncludedInMarqueeSelection && targetIncludedInMarqueeSelection;
			} else if (mode == APPEND_MODE) {
				// in append mode, the current viewer selection is of interest
				// as well, so select connection if not already selected and
				// source and target are already selected or will get selected
				included = connection.getSelected() == EditPart.SELECTED_NONE
						&& (getCurrentViewer().getSelectedEditParts().contains(source) || sourceIncludedInMarqueeSelection)
						&& (getCurrentViewer().getSelectedEditParts().contains(target) || targetIncludedInMarqueeSelection);
			} else if (mode == TOGGLE_MODE) {
				if (connection.getSelected() == EditPart.SELECTED_NONE) {
					// connection is currently deselected, include it in the
					// marquee selection, i.e. select it, if one of
					// source or target will become selected in the new viewer
					// selection
					included = ((source.getSelected() == EditPart.SELECTED_NONE && sourceIncludedInMarqueeSelection) || (source
							.getSelected() != EditPart.SELECTED_NONE && !sourceIncludedInMarqueeSelection))
							&& ((target.getSelected() == EditPart.SELECTED_NONE && targetIncludedInMarqueeSelection) || (target
									.getSelected() != EditPart.SELECTED_NONE && !targetIncludedInMarqueeSelection));
				} else {
					// connection is currently selected, include it in marquee
					// selection, i.e. deselect it, if one of source or target
					// will become deselected in the new viewer selection
					included = (source.getSelected() != EditPart.SELECTED_NONE && sourceIncludedInMarqueeSelection)
							|| (target.getSelected() != EditPart.SELECTED_NONE && targetIncludedInMarqueeSelection);
				}
			}
		}
		return included;
	}

	/**
	 * MarqueeSelectionTool is only interested in GraphicalViewers, not TreeViewers.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#isViewerImportant(org.eclipse.gef.EditPartViewer)
	 */
	protected boolean isViewerImportant(EditPartViewer viewer) {
		return isCurrentViewerGraphical();
	}

	private boolean isCurrentViewerGraphical() {
		return getCurrentViewer() instanceof GraphicalViewer;
	}

	/**
	 * Calculates and sets a new viewer selection based on the current marquee selection.
	 * 
	 * By default, this method delegates to {@link #calculateMarqueeSelectedEditParts()} to obtain the set of edit parts,
	 * which should be regarded as being affected by the current marquee selection. It then calculates a new viewer
	 * selection based on the current selection state of all affected edit parts and the current selection mode of the
	 * tool ( {@link #getCurrentSelectionMode()}), as well as the current selection of the viewer (in case of APPEND
	 * mode), which is then passed to the current viewer.
	 * 
	 * @since 3.7
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void performMarqueeSelect() {

		if (getLocation().y < dragStart) {
			marqueeBehavior = SameBandEditPartsTracker.BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS;
		} else {
			marqueeBehavior = SameBandEditPartsTracker.DEFAULT_MARQUEE_BEHAVIOR;
		}

		// determine which edit parts are affected by the current marquee
		// selection
		Collection marqueeSelectedEditParts = calculateMarqueeSelectedEditParts();

		// calculate nodes/connections that are to be selected/deselected,
		// dependent on the current mode of the tool
		Collection editPartsToSelect = new LinkedHashSet();
		Collection editPartsToDeselect = new HashSet();
		for (Iterator iterator = marqueeSelectedEditParts.iterator(); iterator.hasNext();) {
			EditPart affectedEditPart = (EditPart) iterator.next();
			if (affectedEditPart.getSelected() == EditPart.SELECTED_NONE || getCurrentSelectionMode() != TOGGLE_MODE)
				editPartsToSelect.add(affectedEditPart);
			else
				editPartsToDeselect.add(affectedEditPart);
		}

		// include the current viewer selection, if not in DEFAULT mode.
		if (getCurrentSelectionMode() != DEFAULT_MODE) {
			editPartsToSelect.addAll(getCurrentViewer().getSelectedEditParts());
			editPartsToSelect.removeAll(editPartsToDeselect);
		}

		getCurrentViewer().setSelection(new StructuredSelection(editPartsToSelect.toArray()));
	}

	/**
	 * Sets the type of parts that this tool will select. This method should only be invoked once: when the tool is being
	 * initialized.
	 * 
	 * @param type
	 *          {@link #BEHAVIOR_CONNECTIONS_TOUCHED} or {@link #BEHAVIOR_CONNECTIONS_CONTAINED}
	 *          {@link #BEHAVIOR_NODES_TOUCHED} or {@link #BEHAVIOR_NODES_CONTAINED} or
	 *          {@link #BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS} or
	 *          {@link #BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS}
	 * @since 3.1
	 */
	public void setMarqueeBehavior(int type) {
		if (type != BEHAVIOR_CONNECTIONS_TOUCHED && type != BEHAVIOR_CONNECTIONS_CONTAINED
				&& type != BEHAVIOR_NODES_TOUCHED && type != BEHAVIOR_NODES_TOUCHED_AND_RELATED_CONNECTIONS
				&& type != BEHAVIOR_NODES_CONTAINED && type != BEHAVIOR_NODES_CONTAINED_AND_RELATED_CONNECTIONS)
			throw new IllegalArgumentException("Invalid marquee behaviour specified."); //$NON-NLS-1$
		marqueeBehavior = type;
	}

	private void setSelectionMode(int mode) {
		this.mode = mode;
	}

	/**
	 * Calls {@link #performOpen()} if the double click was with mouse button 1.
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#handleDoubleClick(int)
	 */
	protected boolean handleDoubleClick(int button) {
		if (getSourceEditPart() != null) {
			super.handleDoubleClick(button);
		}
		return true;
	}

	/**
	 * @see org.eclipse.gef.Tool#setViewer(org.eclipse.gef.EditPartViewer)
	 */
	public void setViewer(EditPartViewer viewer) {
		if (viewer == getCurrentViewer())
			return;
		super.setViewer(viewer);
		if (viewer instanceof GraphicalViewer)
			setDefaultCursor(SharedCursors.CROSS);
		else
			setDefaultCursor(SharedCursors.NO);
	}

	private void showMarqueeFeedback() {
		Rectangle rect = getCurrentMarqueeSelectionRectangle().getCopy();
		IFigure marqueeFeedbackFigure = getMarqueeFeedbackFigure();
		marqueeFeedbackFigure.translateToRelative(rect);
		marqueeFeedbackFigure.setBounds(rect);
		marqueeFeedbackFigure.validate();
	}

	protected void showTargetFeedback() {
		for (Iterator<?> itr = selectedEditParts.iterator(); itr.hasNext();) {
			EditPart editPart = (EditPart) itr.next();
			editPart.showTargetFeedback(getTargetRequest());
		}
	}

}
