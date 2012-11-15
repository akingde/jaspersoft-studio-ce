/*******************************************************************************
 * ---------------------------------------------------------------------
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 * ---------------------------------------------------------------------
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.tools.DragEditPartsTracker;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;

/**
 * This drag tracker redefine the behavior when an element is moved or resized. Usually an element can not be moved 
 * over other edit part, and can not be resized to overlap them. This dragtracker change this behavior taking as the target edit part
 * an icontainer, that could be the actual target or the container of the actual target
 * @author Orlandin Marco
 *
 */
public class SearchParentDragTracker extends DragEditPartsTracker{
	
	public SearchParentDragTracker(EditPart sourceEditPart) {
		super(sourceEditPart);
	}
	
	/**
	 * Take an edit part and search it's container
	 * @param child
	 * @return the container of the child, could be null
	 */
	private EditPart searchParent(EditPart child){
		if (child != null){
			//This use the model for the search because every EditPart in the report has the same father.
			Object parentModel = ((ANode)child.getModel()).getParent();
			for(Object actualChild: child.getParent().getChildren()){
				EditPart actualChildPart = (EditPart) actualChild;
				if (parentModel == actualChildPart.getModel()){
					return actualChildPart;
				}
			}
		}
		return null;
	}
	
	/**
	 * Called to get the destination edit part during a drag and drop, if the destination its not
	 * a container the it parent is taken
	 */
	protected EditPart getTargetEditPart() {
		EditPart target = super.getTargetEditPart();
		EditPart parent = null;
		if (!(target instanceof IContainer))
			parent = searchParent(target);
		return parent != null ? parent : target;
	}

};
