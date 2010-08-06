/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.action.snap;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.Action;

public class SnapToGuidesAction extends Action {
	public static final String ID = "PROPERTY_SNAP_TO_GUIDES_ENABLED";
	private GraphicalViewer diagramViewer;

	public SnapToGuidesAction(GraphicalViewer diagramViewer) {
		super("Show Grid", AS_CHECK_BOX);
		this.diagramViewer = diagramViewer;
		setText("Show Grid");
		setToolTipText("Show Grid");
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
