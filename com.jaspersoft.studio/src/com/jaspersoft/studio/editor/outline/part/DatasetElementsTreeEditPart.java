/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.part;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.window.Window;

import com.jaspersoft.studio.editor.expression.EditElementExpressionCommand;
import com.jaspersoft.studio.editor.outline.editpolicy.JDTreeContainerEditPolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.variable.MVariable;

import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;

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
	
	@Override
	public void performRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_OPEN ) {
			Command cmd = null;
			if (getModel() instanceof MParameter) {
				cmd = new EditElementExpressionCommand((MParameter)getModel(), JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION) {
					@Override
					public boolean canExecute() {
						return super.canExecute() && this.showDialog()==Window.OK;
					}
				};	
			} else if (getModel() instanceof MVariable) {
				cmd = new EditElementExpressionCommand((MVariable)getModel(), JRDesignVariable.PROPERTY_EXPRESSION) {
					@Override
					public boolean canExecute() {
						return super.canExecute() && this.showDialog()==Window.OK;
					}
				};		
			}

			if (cmd != null) {
				getViewer().getEditDomain().getCommandStack().execute(cmd);	
			}
		}else super.performRequest(req);
	}
	
	@Override
	public boolean understandsRequest(Request req) {
		if (req.getType() == RequestConstants.REQ_OPEN ) {
			if (getModel() instanceof MParameter || getModel() instanceof MVariable) {
				if (((ANode)getModel()).isEditable()) {
					return true;
				}
			}
		} 
		return super.understandsRequest(req);
	}
	
}
