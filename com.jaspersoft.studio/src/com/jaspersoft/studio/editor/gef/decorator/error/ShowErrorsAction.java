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
package com.jaspersoft.studio.editor.gef.decorator.error;

import org.eclipse.gef.GraphicalViewer;

import com.jaspersoft.studio.editor.action.snap.ACheckResourcePrefAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Action used to show or hide the error decorator, that provide a visual warning on the
 * elements with a validation error and a tooltip on the same elements with an error message
 * 
 * @author Orlandin Marco
 *
 */
public class ShowErrorsAction extends ACheckResourcePrefAction {
	
	public final static String ID = "com.jaspersoft.editor.gef.decorator.error.ShowErrorsAction"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param diagramViewer the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 * @param jrContext the context of the report opened in the editor 
	 */
	public ShowErrorsAction(GraphicalViewer diagramViewer, JasperReportsConfiguration jrContext) {
		super(Messages.ShowErrorsAction_title, jrContext);
		setToolTipText(Messages.ShowErrorsAction_tooltip);
		setId(ID);
	}

	@Override
	protected String getProperty() {
		return ID;
	}
}
