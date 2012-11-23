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
package com.jaspersoft.studio.editor.action.snap;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.messages.Messages;

public class SnapToGuidesAction extends Action {
	public static final String ID = "PROPERTY_SNAP_TO_GUIDES_ENABLED"; //$NON-NLS-1$
	private GraphicalViewer diagramViewer;

	public SnapToGuidesAction(GraphicalViewer diagramViewer) {
		super(Messages.common_snap_to_guides, AS_CHECK_BOX);
		this.diagramViewer = diagramViewer;
		setText(Messages.common_snap_to_guides);
		setToolTipText(Messages.SnapToGuidesAction_show_grid_tool_tip);
		setId(ID);
		setActionDefinitionId(ID);
		setChecked(isChecked());
	}

	/**
	 * @see org.eclipse.jface.action.IAction#isChecked()
	 */
	public boolean isChecked() {
		Boolean val = (Boolean) this.diagramViewer.getProperty(SnapToGuidesAction.ID);
		if (val != null)
			return val.booleanValue();
		return false;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		this.diagramViewer.setProperty(SnapToGuidesAction.ID, new Boolean(!isChecked()));
	}
}
