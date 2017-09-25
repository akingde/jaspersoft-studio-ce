/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.commands;

import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;

/** 
 * Set the size or position of an element. The command
 * can be executed only if the operation is allowed by
 * the layout of the parent. It layout also the container
 * of the element when the operation is executed or undone
 */
public class SetPageConstraintCommand extends Command {

	/** The new bounds. */
	private Rectangle newBounds;

	/** The old bounds. */
	private Rectangle oldBounds;

	/** The jr element. */
	private JRDesignElement jrElement;

	/** The parent bounds. */
	private Rectangle parentBounds;
	protected JRElementGroup jrGroup;
	
	/**
	 * The parent of the element
	 */
	private ANode parent;

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
		if (child.getValue() instanceof JRDesignElement) {
			jrElement = (JRDesignElement) child.getValue();
			newBounds = constraint;
			parentBounds = ((IGraphicElement) child).getBounds();
			parent = child.getParent();
			this.parent = parent;
			if (parent instanceof IGroupElement)
				jrGroup = ((IGroupElement) parent).getJRElementGroup();
			else if (parent.getValue() instanceof JRElementGroup)
				jrGroup = (JRElementGroup) parent.getValue();
		}
	}

	public void setContext2(ANode parent, ANode child, Rectangle constraint) {
		if (child.getValue() instanceof JRDesignElement) {
			jrElement = (JRDesignElement) child.getValue();
			newBounds = constraint;
			parentBounds = ((IGraphicElement) child).getBounds();
			if (parent instanceof IGroupElement)
				jrGroup = ((IGroupElement) parent).getJRElementGroup();
			else if (parent.getValue() instanceof JRElementGroup)
				jrGroup = (JRElementGroup) parent.getValue();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrElement != null) {
			oldBounds = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(), jrElement.getHeight());
			// check position,
			// if top-left corner outside the bottom bar bands, move to bottom band
			// if bottom-left corner outside the top bar, move to top band
			int y = jrElement.getY() + newBounds.y - parentBounds.y;

			int x = jrElement.getX() + newBounds.x - parentBounds.x;

			jrElement.setX(x);
			jrElement.setY(y);
			jrElement.setWidth(newBounds.width);
			jrElement.setHeight(newBounds.height);

			layoutChildAndParent(parent);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (jrElement != null) {
			jrElement.setWidth(oldBounds.width);
			jrElement.setHeight(oldBounds.height);
			jrElement.setX(oldBounds.x);
			jrElement.setY(oldBounds.y);
			layoutChildAndParent(parent);
		}
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
	
	/**
	 * Return if the operation is allowed by the layout of the current parent
	 * 
	 * @param oldBounds the old bounds of the element
	 * @param newBounds the new bounds of the element
	 * @return true if the operation is allowed, false otherwise
	 */
	private boolean isOperationAllowed(Rectangle oldBounds, Rectangle newBounds){
		JRPropertiesMap newMap = LayoutManager.getPropertyMap(parent);
		if (newMap != null){
			 String parentLayout = newMap.getProperty(ILayout.KEY);
			if (parentLayout != null){
				ILayout layout = LayoutManager.getLayout(parentLayout);
				return layout.allowChildBoundChange(getNodeForElement(parent), oldBounds, newBounds);
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
		if (oldBounds == null && jrElement != null){
			oldBounds = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(), jrElement.getHeight());
		}
		return isOperationAllowed(oldBounds, newBounds);
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
}
