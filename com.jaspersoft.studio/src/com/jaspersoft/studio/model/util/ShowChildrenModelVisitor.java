/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.util;

import com.jaspersoft.studio.model.INode;

/**
 * Visitor used to search the children inside a model element. This visitor
 * will skip the children of elements that have the flag showChildren set to false
 * 
 * @author Orlandin Marco
 */
public abstract class ShowChildrenModelVisitor<T> extends ModelVisitor<T>{

	public ShowChildrenModelVisitor(INode model) {
		super(model);
	}

	public void iterate(INode node) {
		if (node != null && node.getChildren() != null && node.showChildren()){
			for (INode n : node.getChildren()) {
				if (visit(n)) {
					iterate(n);
					postChildIteration(n);
				}
			}
		}
	}
}
