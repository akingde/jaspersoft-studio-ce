/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.editor.tree;


/*
 * The Class AContainerTreeEditPart.
 */
public class ChartThemeContainerTreeEditPart extends ChartThemeTreeEditPart {

	/**
	 * Creates and installs pertinent EditPolicies.
	 */
	protected void createEditPolicies() {
		super.createEditPolicies();
		// installEditPolicy(EditPolicy.CONTAINER_ROLE, new
		// JDStyleContainerEditPolicy());
		// installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE, new
		// JDStyleTreeContainerEditPolicy());
		// // If this editpart is the contents of the viewer, then it is not
		// deletable!
		// if (getParent() instanceof RootEditPart)
		// installEditPolicy(EditPolicy.COMPONENT_ROLE, new
		// RootComponentEditPolicy());
	}
}
