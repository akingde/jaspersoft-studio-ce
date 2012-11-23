/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
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
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.size;

import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

public class MatchSizeAction extends MatchHeightAction {
	public static final String ID = GEFActionConstants.MATCH_HEIGHT + GEFActionConstants.MATCH_WIDTH;
	public MatchSizeAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.MatchSizeAction_match_size);
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/eclipse/match-size.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/eclipse/disabled/match-size.gif")); //$NON-NLS-1$
		setToolTipText(Messages.MatchSizeAction_match_size_tool_tip);
		setId(ID);
	}

	@Override
	protected double getPreciseWidthDelta(PrecisionRectangle precisePartBounds, PrecisionRectangle precisePrimaryBounds) {
		return precisePrimaryBounds.preciseWidth() - precisePartBounds.preciseWidth();
	}
}
