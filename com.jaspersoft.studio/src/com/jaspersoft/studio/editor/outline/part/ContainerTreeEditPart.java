/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.part;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.editor.outline.editpolicy.CloseSubeditorDeletePolicy;
import com.jaspersoft.studio.editor.outline.editpolicy.JDContainerEditPolicy;
import com.jaspersoft.studio.editor.outline.editpolicy.JDTreeContainerEditPolicy;
import com.jaspersoft.studio.editor.report.EditorContributor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.utils.SelectionHelper;

/*
 * The Class AContainerTreeEditPart.
 */
public class ContainerTreeEditPart extends TreeEditPart {

	/**
	 * Creates and installs pertinent EditPolicies.
	 */
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new JDContainerEditPolicy());
		installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE, new JDTreeContainerEditPolicy());
		//Overwrite the policy that handle the delete, so i have a custom delete command to close the subeditor
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new CloseSubeditorDeletePolicy());
		// If this editpart is the contents of the viewer, then it is not deletable!
		if (getParent() instanceof RootEditPart)
			installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
	}

	@Override
	public boolean understandsRequest(Request req) {
		if (RequestConstants.REQ_ALIGN.equals(req.getType()) && getModel() instanceof MGraphicElement){
			return true;
		}
		if (RequestConstants.REQ_OPEN.equals(req.getType()) && getModel() instanceof MConditionalStyle){
			return true;
		}
		return super.understandsRequest(req);
	}
	
	//These two methods allow to show a visual feedback on the main editor, if available
	
	@Override
	public void showTargetFeedback(Request request) {
		super.showTargetFeedback(request);
		showTargetFeedbackOnEditor(request);
	}
	
	@Override
	public void eraseTargetFeedback(Request request) {
		super.eraseTargetFeedback(request);
		eraseTargetFeedbackOnEditor(request);
	}
	
	/**
	 * If the node is an MConditionalStyle open the editor to edit its expression
	 */
	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType()) && getModel() instanceof MConditionalStyle) {
			MConditionalStyle cStyle = (MConditionalStyle)getModel();
			JRDesignConditionalStyle jrCStyle = (JRDesignConditionalStyle)cStyle.getValue();
			if(!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
				JRExpressionEditor wizard = new JRExpressionEditor();
				wizard.setValue((JRDesignExpression)jrCStyle.getConditionExpression());
				wizard.setExpressionContext(ExpressionEditorSupportUtil.getReportExtendedExpressionContext());
				WizardDialog dialog = ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(UIUtils.getShell(), wizard);
				if (dialog.open() == Dialog.OK) {
					IEditorPart editor = SelectionHelper.getActiveJRXMLEditor();
					EditorContributor provider = (EditorContributor) editor.getAdapter(EditorContributor.class);
					if (provider != null){
						CommandStack stack = provider.getEditDomain().getCommandStack();
						SetValueCommand setExpCommand = new SetValueCommand(Messages.MConditionalStyle_conditional_expression);
						setExpCommand.setTarget(cStyle);
						setExpCommand.setPropertyId(JRDesignConditionalStyle.PROPERTY_CONDITION_EXPRESSION);
						setExpCommand.setPropertyValue(wizard.getValue());
						stack.execute(setExpCommand);
					} else {
						jrCStyle.setConditionExpression(wizard.getValue());
					}
				}
			}
		}
		super.performRequest(req);
	}
}
