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
