/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.part;

import org.eclipse.gef.EditPolicy;

import com.jaspersoft.studio.editor.outline.editpolicy.JDTreeContainerEditPolicy;

/**
 * Node used for those node that are not containers but want however offers creation action.
 * For example a parameter, variable or field allow to create a node of the same type on the same
 * level after them
 */
public class DatasetElementsTreeEditPart extends TreeEditPart{

	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE, new JDTreeContainerEditPolicy());
	}
	
}
