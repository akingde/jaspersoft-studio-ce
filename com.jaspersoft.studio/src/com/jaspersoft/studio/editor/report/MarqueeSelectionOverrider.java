/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.util.EditPartUtilities;
import org.eclipse.jface.viewers.StructuredSelection;

import com.jaspersoft.studio.editor.gef.parts.band.BandEditPart;
import com.jaspersoft.studio.editor.java2d.ISelectionOverrider;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.preferences.bindings.BindingsPreferencePersistence;
import com.jaspersoft.studio.utils.compatibility.FigureUtilities;

/**
 * This selection overrider allow to override the current selection and instead
 * select the elements touched by a virtual rectangle containing all the previously selected
 * elements and the last selected element
 * 
 * @author Orlandin Marco
 *
 */
public class MarqueeSelectionOverrider implements ISelectionOverrider {
	
	/**
	 * Id of the key binding for this action
	 */
	private static final String BINDING_KEY_ID = "com.jaspersoft.studio.editor.MarqueeSelectionOverrider";
	
	public MarqueeSelectionOverrider(){
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
	 */
	@SuppressWarnings("unchecked")
	protected Collection<EditPart> calculatePrimaryMarqueeSelectedEditParts(GraphicalViewer viewer, Rectangle selectionRectangle, boolean allowNotFullyClipped) {
		
		Collection<EditPart> editPartsToProcess = new HashSet<EditPart>();
		editPartsToProcess.addAll(EditPartUtilities.getAllChildren((GraphicalEditPart) viewer.getRootEditPart()));
		
		Collection<EditPart> marqueeSelectedEditParts = new ArrayList<EditPart>();
		for (Iterator<EditPart> iterator = editPartsToProcess.iterator(); iterator.hasNext();) {
			GraphicalEditPart editPart = (GraphicalEditPart) iterator.next();
			//The page and the bands are not valid selectable items, so the isMarqueeSelectable retrun false for every element that it isn't an
			//MGraphical element
			if (isMarqueeSelectable(editPart, allowNotFullyClipped) && isPrimaryMarqueeSelectedEditPart(editPart, selectionRectangle)) {
				marqueeSelectedEditParts.add(editPart);
			}
		}
		return marqueeSelectedEditParts;
	}
	
	/**
	 * Decides whether the given edit part may potentially be included in the current marquee selection.
	 * 
	 * @param editPart
	 *          the {@link EditPart} of interest
	 * @param allowNotFullyClipped true allow the selection even of the part not currently visible if they are inside the marquee area
	 * @return <code>true</code> if the given edit part may be included into the marquee selection, <code>false</code>
	 *         otherwise
	 */
	protected boolean isMarqueeSelectable(GraphicalEditPart editPart, boolean allowNotFullyClipped) {
		if (editPart.isSelectable() && editPart.getModel() instanceof MGraphicElement){
			if (allowNotFullyClipped) return true;
			else return FigureUtilities.isNotFullyClipped(editPart.getFigure());
		}
		return false;
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
	 */
	private boolean isPrimaryMarqueeSelectedEditPart(GraphicalEditPart editPart, Rectangle selectionRectangle) {
		// figure bounds are used to determine if edit part is included in
		// selection
		IFigure figure = editPart.getFigure();
		Rectangle r = figure.getBounds().getCopy();
		figure.translateToAbsolute(r);
		return selectionRectangle.contains(r);
	}

	/**
	 * Calculate a rectangle containing all the previously selected parts and the last selected part
	 * an will select also all the other graphical parts touched by this triangle
	 */
	public boolean overriddenSelection(EditPart selectedPart, List<?> previousSelection, GraphicalViewer currentViewer){
		if (selectedPart != null && BindingsPreferencePersistence.isPressed(BINDING_KEY_ID)){
				List<GraphicalEditPart> graphicalParts = new ArrayList<GraphicalEditPart>();
				for(Object currentPart : previousSelection){
					if (currentPart instanceof GraphicalEditPart && !(currentPart instanceof BandEditPart)){
						graphicalParts.add((GraphicalEditPart)currentPart);
					}
				}
				if (selectedPart instanceof GraphicalEditPart && !(selectedPart instanceof BandEditPart)){
					graphicalParts.add((GraphicalEditPart)selectedPart);
				}
				//Compute the rectangle that contains all the parts
				if (graphicalParts.size() > 1){
					//the rectangle start to the size of the first figure
					IFigure figure = graphicalParts.get(0).getFigure();
					Rectangle containerRectangle = figure.getBounds().getCopy();
					figure.translateToAbsolute(containerRectangle);
					for(GraphicalEditPart graphicPart : graphicalParts){
						figure = graphicPart.getFigure();
						Rectangle r = figure.getBounds().getCopy();
						figure.translateToAbsolute(r);
						//Check if is right
						if (r.x + r.width > containerRectangle.x+containerRectangle.width){
							//Enlarge right edge
							int delta = r.x + r.width - containerRectangle.x - containerRectangle.width;
							containerRectangle.width += delta;
						}
						//check if is left
						if (r.x < containerRectangle.x){
							//move left edge
							int delta = containerRectangle.x - r.x;
							containerRectangle.x = r.x;
							containerRectangle.width += delta;
						}
						//check if is bottom
						if (r.y + r.height > containerRectangle.y + containerRectangle.height){
							//Enlarge bottom edge
							int delta = r.y + r.height - containerRectangle.y - containerRectangle.height;
							containerRectangle.height += delta;
						}
						//check if is top
						if (r.y < containerRectangle.y){
							//move top edge
							int delta = containerRectangle.y - r.y;
							containerRectangle.y = r.y;
							containerRectangle.height += delta;
						}
					}
					//Select the part in the container even if not fully clipped
					Collection<EditPart> partsToSelect = calculatePrimaryMarqueeSelectedEditParts(currentViewer, containerRectangle, true);
					currentViewer.setSelection(new StructuredSelection(new ArrayList<EditPart>(partsToSelect)));
					return true;
				}
		}
		return false;
	}
}
