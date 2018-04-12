/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Set the size or position of an element. The command
 * can be executed only if the operation is allowed by
 * the layout of the parent. It could also change the 
 * parent of the element. Typically this is used to move
 * the child of the bands. It layout also the container
 * of the element when the operation is executed or undone
 */
public class SetConstraintCommand extends Command {
	
	/**
	 * The parent of the modified node
	 */
	private ANode originalParent;
	
	/**
	 * If different from the original parent this is 
	 * the new parent of the element 
	 */
	private ANode newParent;
	
	/** 
	 * The new bounds. 
	 */
	private Rectangle newBounds;

	/** 
	 * The old bounds of the element 
	 */
	private Rectangle oldBounds;

	/** The old index. */
	private int oldIndex;

	/** The jr element. */
	private JRDesignElement jrElement;

	/** The jr design. */
	private JasperDesign jrDesign;
	
	/** The jr configuration */
	private JasperReportsConfiguration jrConfig;

	/** The parent bounds. */
	private Rectangle parentBounds;
	
	protected JRElementGroup jrGroup;

	/**
	 * The old band height, since the band can be resized if 
	 * the element is too big for it
	 */
	private int oldBandHeight = -1;

	/**
	 * Sets the context.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 * @param constraint
	 *          the constraint
	 */
	public void setContext(ANode parent, ANode child, Rectangle constraint) {
		jrConfig = child.getJasperConfiguration();
		jrDesign = jrConfig.getJasperDesign();
		this.originalParent = child.getParent();
		if (parent instanceof MBand){
			this.newParent = parent;
		} else {
			this.newParent = originalParent;
		}
		if (child.getValue() instanceof JRDesignElement) {
			jrElement = (JRDesignElement) child.getValue();
			oldBounds = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(), jrElement.getHeight());
			newBounds = constraint;
			parentBounds = ((IGraphicElement) child).getBounds();
			if (child instanceof IGroupElement)
				jrGroup = ((IGroupElement) child).getJRElementGroup();
			else if (child.getValue() instanceof JRElementGroup)
				jrGroup = (JRElementGroup) child.getValue();
		}
	}
	
	/**
	 * Return if the operation is allowed by the layout of the current parent
	 * 
	 * @param oldBounds the old bounds of the element
	 * @param newBounds the new bounds of the element
	 * @return true if the operation is allowed, false otherwise
	 */
	private boolean isOperationAllowed(Rectangle oldBounds, Rectangle newBounds){
		JRPropertiesMap newMap = LayoutManager.getPropertyMap(originalParent);
		if (newMap != null && jrElement != null){
			 String parentLayout = newMap.getProperty(ILayout.KEY);
			if (parentLayout != null){
				ILayout layout = LayoutManager.getLayout(parentLayout);
				//calculate the new bounds of the element relative to the parent
				Rectangle relativeNewBounds = new Rectangle(newBounds);
				int x = jrElement.getX() + newBounds.x - parentBounds.x;
				int y = jrElement.getY() + newBounds.y - parentBounds.y;
				relativeNewBounds.setX(x);
				relativeNewBounds.setY(y);
				return layout.allowChildBoundChange(getNodeForElement(originalParent), oldBounds, relativeNewBounds);
			}
		}
		return true;
	}
	
	/**
	 * Return the node associated to the element in the specified parent. It is
	 * not possible to use child.getParent because if the parent change also the node
	 * is changed
	 * 
	 * @param parent the parent
	 * @return the node of the jrElement inside the parent or null if it can't be found
	 */
	private ANode getNodeForElement(ANode parent){
		for(INode child : parent.getChildren()){
			if (child.getValue() == jrElement) return (ANode)child;
		}
		return null;
	}
	
	/**
	 * The command can be executed if the bounds change
	 * is allowed by the layout of the parent
	 */
	@Override
	public boolean canExecute() {
		return isOperationAllowed(oldBounds, newBounds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrElement != null) {
			int x = jrElement.getX() + newBounds.x - parentBounds.x;
			int y = jrElement.getY() + newBounds.y - parentBounds.y;
			if (newParent != originalParent){
				changeParent((JRDesignBand)originalParent.getValue(), (JRDesignBand)newParent.getValue());
			} 
			jrElement.setX(x);
			jrElement.setY(y);
			jrElement.setWidth(newBounds.width);
			jrElement.setHeight(newBounds.height);
			// check position,
			// if top-left corner outside the bottom bar bands, move to bottom band
			// if bottom-left corner outside the top bar, move to top band
			if (newParent != null && newParent.getValue() != null && newParent.getValue() instanceof JRDesignBand){
				JRDesignBand band = (JRDesignBand)newParent.getValue();
				int maxHeight = ModelUtils.getMaxBandHeight(band, jrDesign);
				int elementHeight = jrElement.getHeight() + jrElement.getY() ;
				boolean isBandResizeEnabled = jrConfig.getPropertyBoolean(DesignerPreferencePage.P_RESIZE_CONTAINER, Boolean.TRUE);
				if (maxHeight > 1 && elementHeight > band.getHeight() && isBandResizeEnabled){
					//If the band could increase its size and the element is higher than the band
					//reside the element or the band to fit the element. If the band could not increase
					//leave the element as it is
					oldBandHeight = band.getHeight();
					int elHeight = jrElement.getY() + jrElement.getHeight();
					//The band can not increase above its maximum
					elHeight = Math.min(maxHeight, elHeight);
					if (elHeight > band.getHeight()) {
						band.setHeight(elHeight);
					}
				}
			}
			
			layoutChildAndParent(newParent);
		}
	}
	
	/**
	 * Execute the layout both of the moved element and of its parent
	 * 
	 * @param currentParent the current parent of the element to layout
	 */
	private void layoutChildAndParent(ANode currentParent){
		//layout the children of the element if any
		ANode elementNode = getNodeForElement(currentParent);
		LayoutManager.layoutContainer(elementNode);
		
		//layout the parent
		LayoutManager.layoutContainer(currentParent);
	}

	/**
	 * Change the parent band of an element from one to another
	 * 
	 * @param originalBand the original band where the element is, must be not null
	 * @param destinationBand the destination band where it should be placed
	 */
	private void changeParent(JRDesignBand originalBand, JRDesignBand destinationBand) {
		JRElement[] elements = originalBand.getElements();
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == jrElement) {
				oldIndex = i;
				break;
			}
		}
		originalBand.removeElement(jrElement);
		destinationBand.addElement(jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {	
		if (originalParent != newParent){
			JRDesignBand originalBand = (JRDesignBand)originalParent.getValue();
			JRDesignBand newBand = (JRDesignBand)newParent.getValue();
			newBand.removeElement(jrElement);
			originalBand.addElement(oldIndex, jrElement);
		}
		jrElement.setX(oldBounds.x);
		jrElement.setY(oldBounds.y);
		jrElement.setWidth(oldBounds.width);
		jrElement.setHeight(oldBounds.height);
		if (oldBandHeight != -1){
			JRDesignBand newBand = (JRDesignBand)newParent.getValue();
			newBand.setHeight(oldBandHeight);
		}
		layoutChildAndParent(originalParent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#getLabel()
	 */
	@Override
	public String getLabel() {
		if (oldBounds != null && (oldBounds.x != newBounds.x || oldBounds.y != newBounds.y))
			return "set location"; //$NON-NLS-1$
		return "resize"; //$NON-NLS-1$
	}
}
