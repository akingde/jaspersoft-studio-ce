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
package com.jaspersoft.studio.editor.gef.decorator.error;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.messages.Messages;

public class ShowErrorsAction extends Action {
	public final static String ID = "com.jaspersoft.editor.gef.decorator.error.ShowErrorsAction"; //$NON-NLS-1$
	private GraphicalViewer diagramViewer;

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public ShowErrorsAction(GraphicalViewer diagramViewer) {
		super(Messages.ShowErrorsAction_title, AS_CHECK_BOX);
		this.diagramViewer = diagramViewer;
		setToolTipText(Messages.ShowErrorsAction_tooltip);
		setId(ID);
		setActionDefinitionId(ID);
		setChecked(isChecked());
	}

	/**
	 * @see org.eclipse.jface.action.IAction#isChecked()
	 */
	public boolean isChecked() {
		Boolean val = (Boolean) diagramViewer.getProperty(ID);
		if (val != null)
			return val.booleanValue();
		return false;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		boolean val = !isChecked();
		diagramViewer.setProperty(ID, new Boolean(val));
		diagramViewer.getContents().refresh();
	}

}
