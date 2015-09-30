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
package com.jaspersoft.studio.editor.gef.commands;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;

/**
 * Command to move and element, it is similar to SetConstraintCommand but
 * with this one the parent of the element doesn't change
 * 
 * @author Orlandin Marco
 *
 */
public class SetPositionCommand extends Command {

	/** The new bounds. */
	protected Rectangle newBounds;

	/** The old bounds. */
	protected Rectangle oldBounds;

	/** The jr element. */
	protected JRDesignElement jrElement;

	/** The parent bounds. */
	protected Rectangle parentBounds;
	
	protected ANode child;

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
	public void setContext(ANode child, Rectangle constraint) {
		this.child = child;
		if (child.getValue() instanceof JRDesignElement) {
			jrElement = (JRDesignElement) child.getValue();
			newBounds = constraint;
			parentBounds = ((IGraphicElement) child).getBounds();
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
			
			layoutChildAndParent();
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
			
			layoutChildAndParent();
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
	 * Execute the layout both of the moved element and of its parent
	 * 
	 */
	protected void layoutChildAndParent(){
		//layout the children of the element if any
		LayoutManager.layoutContainer(child);
		
		//layout the parent
		LayoutManager.layoutContainer(child.getParent());
	}
	
	/**
	 * The command can be executed if the bounds change
	 * is allowed by the layout of the parent
	 */
	@Override
	public boolean canExecute() {
		return isOperationAllowed(oldBounds, newBounds);
	}
	
	/**
	 * Return if the operation is allowed by the layout of the current parent
	 * 
	 * @param oldBounds the old bounds of the element
	 * @param newBounds the new bounds of the element
	 * @return true if the operation is allowed, false otherwise
	 */
	protected boolean isOperationAllowed(Rectangle oldBounds, Rectangle newBounds){
		JRPropertiesMap newMap = LayoutManager.getPropertyMap(child.getParent());
		if (newMap != null){
			 String parentLayout = newMap.getProperty(ILayout.KEY);
			if (parentLayout != null){
				ILayout layout = LayoutManager.getLayout(parentLayout);
				return layout.allowChildBoundChange(child, oldBounds, newBounds);
			}
		}
		return true;
	}
}
