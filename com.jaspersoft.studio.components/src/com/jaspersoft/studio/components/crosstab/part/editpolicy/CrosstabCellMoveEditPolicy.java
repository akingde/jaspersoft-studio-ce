/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.part.editpolicy;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.editor.gef.parts.editPolicy.AContainerMoveEditPolicy;

/*
 * The Class BandMoveEditPolicy.
 * 
 * @author Chicu Veaceslav
 */
public class CrosstabCellMoveEditPolicy extends AContainerMoveEditPolicy {

	protected Command getResizeCommand(ChangeBoundsRequest request) {
		return CreateResize.createResizeCommand(request,
				(GraphicalEditPart) getHost());
	}

}
