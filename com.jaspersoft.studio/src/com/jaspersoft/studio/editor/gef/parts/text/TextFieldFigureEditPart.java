/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.text;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.window.Window;

import com.jaspersoft.studio.editor.expression.EditElementExpressionCommand;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.text.MTextField;

import net.sf.jasperreports.engine.design.JRDesignTextField;

/*
 * The Class FigureEditPart.
 */
public class TextFieldFigureEditPart extends FigureEditPart {

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			Command cmd = null;
			MTextField textfield = (MTextField) getModel();
			cmd = new EditElementExpressionCommand(textfield, JRDesignTextField.PROPERTY_EXPRESSION) {
				@Override
				public boolean canExecute() {
					return super.canExecute() && this.showDialog()==Window.OK;
				}
			};
			getViewer().getEditDomain().getCommandStack().execute(cmd);
		} else
			super.performRequest(req);
	}

}
