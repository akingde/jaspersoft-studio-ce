/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Command used when an element is pasted to set a position for it. The new position will be under the copied element if 
 * there is space, or on its right otherwise. If there is no space on the right it will be placed under the copied element.
 * This is done only if the new parent is the same of the original parent
 */
public class FixPositionCommand extends Command{
	
	/**
	 * The pasted element
	 */
	private MGraphicElement mge;
	
	/**
	 * The parent of the copied element
	 */
	private ANode originalParent;
	
	/**
	 * The parent of the pasted element
	 */
	private ANode newParent;
	
	/**
	 * Store the band height of the pasted element if it is pasted into a band,
	 * so it can reset its height on undo if it was resized because of the placing of the element
	 */
	private Integer oldBandHeight = null;

	/**
	 * Store the band of the pasted element if it is pasted into a band,
	 * so it can reset its height on undo if it was resized because of the placing of the element
	 */
	private JRDesignBand parentBand = null;
	
	/**
	 *	The {@link JasperReportsConfiguration} of the report 
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * Create the command 
	 * 
	 * @param mge The pasted element
	 * @param originalParent The parent of the copied element
	 * @param newParent The parent of the pasted element
	 */
	public FixPositionCommand(MGraphicElement mge, ANode originalParent, ANode newParent){
		this.mge = mge;
		this.newParent = newParent;
		this.originalParent = originalParent;
		this.jConfig = mge.getJasperConfiguration();
	}
	
	@Override
	public void execute() {
		JRDesignElement de = (JRDesignElement) mge.getValue();
		if (originalParent == newParent || originalParent == null) {
			int xOffset = 5;
			int yOffset = 5;
			if (newParent.getValue() instanceof JRBand){
				JRBand band = (JRBand)newParent.getValue();
				Boolean resizeBandOnPaste = jConfig.getPropertyBoolean(DesignerPreferencePage.P_RESIZE_ON_PASTE, true);
				boolean fixXaxes = false;
				int height = de.getY() + yOffset+ de.getHeight();
				if (band.getHeight() < height && !resizeBandOnPaste) {
					fixXaxes = true;
					yOffset = 0;
				}
				Rectangle startingLocation = new Rectangle(de.getX() + xOffset, de.getY() + yOffset, de.getWidth(), de.getHeight());
				JRElement overlappingElement = getPerfectlyOverlappingChildren(de, band, startingLocation);
				while(overlappingElement != null){
					xOffset = overlappingElement.getX() + 5 - de.getX();
					if (!fixXaxes) {
						int lastValidYOffset = yOffset;
						yOffset = overlappingElement.getY() + 5 - de.getY();		
						height = de.getY() + yOffset+ de.getHeight();
						if (band.getHeight() < height && !resizeBandOnPaste) {
							fixXaxes = true;
							yOffset = lastValidYOffset;
						}
					}
					startingLocation = new Rectangle(de.getX() + xOffset, de.getY() + yOffset, de.getWidth(), de.getHeight());
					overlappingElement = getPerfectlyOverlappingChildren(de, band, startingLocation);
				}
				
				//check if the band need and could be resized
				height = de.getY() + yOffset+ de.getHeight();
				if (band.getHeight() < height) {
					int maxBandHeight = ModelUtils.getMaxBandHeight((JRDesignBand)band, jConfig.getJasperDesign());
					if (maxBandHeight >= height) {
						parentBand = (JRDesignBand)band;
						oldBandHeight = band.getHeight();
						parentBand.setHeight(height);
					}
				}
			}
			de.setX(de.getX() + xOffset);
			de.setY(de.getY() + yOffset);
		}
	}
	
	/**
	 * Restore the band size if a band was resized
	 */
	@Override
	public void undo() {
		if (parentBand != null && parentBand.getHeight() != oldBandHeight){
			parentBand.setHeight(oldBandHeight);
		}
	}
	
	/**
	 * Check if into a specific position of a band there is a node perfectly overlapping another node
	 * 
	 * @param de the node to check
	 * @param band the band where the node is placed
	 * @param newLocation the location where the node should be
	 * @return an element that perfectly overlap  the passed node if it was in the newLocation position,
	 * or null otherwise
	 */
	private JRElement getPerfectlyOverlappingChildren(JRElement de, JRBand band, Rectangle newLocation){
		for(JRChild child : band.getChildren()){
			if (child != de && child instanceof JRElement){
				JRElement jrChild = (JRElement)child;
				Rectangle position = new Rectangle(jrChild.getX(), jrChild.getY(), jrChild.getWidth(), jrChild.getHeight());
				if (position.equals(newLocation)){
					return jrChild;
				}
			}
		}
		return null;
	}
	
	/**
	 * Return the minimum location there to place an element at the right of its current position without intersect
	 * other node. This will move the node on the x axis until it found a free space or it doesn't get out of the page
	 * 
	 * @param band the band where the element is placed
	 * @param de the moved element
	 * @param jd the {@link JasperDesign} of the report
	 * @return a location where the element doesn't intersect other elements or null if there is not a valid position
	 * inside the page
	 */
	@SuppressWarnings("unused")
	private Rectangle getOverlappingChildren(JRBand band, JRElement de, JasperDesign jd){
		int availablePageSpace = jd.getPageWidth() - jd.getRightMargin();
		Rectangle newLocation = new Rectangle(de.getX() + de.getWidth(), de.getY(), de.getWidth(), de.getHeight());
		if (newLocation.x + newLocation.width > availablePageSpace){
			return null;
		} else {
			for(JRChild child : band.getChildren()){
				if (child != de && child instanceof JRElement){
					JRElement jrChild = (JRElement)child;
					Rectangle position = new Rectangle(jrChild.getX(), jrChild.getY(), jrChild.getWidth(), jrChild.getHeight());
					if (position.intersects(newLocation)){
						if (jrChild.getX() + jrChild.getWidth() >= newLocation.x + newLocation.width){
							newLocation.setX(jrChild.getX() + jrChild.getWidth());
						}
					}
				}
				if (newLocation.x + newLocation.width > availablePageSpace){
					return null;
				}
			}
			return newLocation;
		}
	}
}
