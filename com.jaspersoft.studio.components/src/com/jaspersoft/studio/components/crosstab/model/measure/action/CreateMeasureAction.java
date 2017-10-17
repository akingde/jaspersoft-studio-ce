/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.measure.action;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.wizard.CrosstabMeasureWizard;
import com.jaspersoft.studio.components.crosstab.model.measure.MMeasures;
import com.jaspersoft.studio.components.crosstab.model.measure.command.CreateMeasureCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;
/*
 * The Class CreateGroupAction.
 */
public class CreateMeasureAction extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "create_measure"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public CreateMeasureAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateMeasureAction_create_measure);
		setToolTipText(Messages.CreateMeasureAction_create_measure_tool_tip);
		setId(CreateMeasureAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
	}
	
	@Override
	public boolean isEnabled() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MCrosstab.class);
		if (elements.size() == 1) return true;
		elements = editor.getSelectionCache().getSelectionModelForType(MMeasures.class);
		if (elements.size() == 1) return true;
		return false;
	}
	
	public MCrosstab getCrosstab(INode startNode) {
		INode node = startNode;
		while (node != null && node.getParent() != null
				&& !(node instanceof MCrosstab) && !(node instanceof MRoot)) {
			node = node.getParent();
		}
		if (node instanceof MCrosstab)
			return (MCrosstab) node;
		return null;
	}
	
	@Override
	public void run() {
		MCrosstab crosstab = null;
		Object selected = getSelectedObjects().get(0);
		if (selected instanceof EditPart) {
			EditPart part = (EditPart) selected;
			if (part.getModel() instanceof INode) crosstab = getCrosstab((INode)part.getModel());
		} 
		if (crosstab != null){
			CrosstabMeasureWizard crosstabMeasureWizard = new CrosstabMeasureWizard(crosstab);
			WizardDialog dialog = new WizardDialog(UIUtils.getShell(), crosstabMeasureWizard);
			if (dialog.open() == IDialogConstants.OK_ID){
				JRDesignCrosstabMeasure measure = CreateMeasureCommand.createMesure(crosstab.getValue(), crosstabMeasureWizard.getGroupName());
				measure.setValueClassName(crosstabMeasureWizard.getGroupValueClass());
				measure.setValueExpression(new JRDesignExpression(crosstabMeasureWizard.getGroupExpression()));
				measure.setCalculation(crosstabMeasureWizard.getCalculation());
				CreateMeasureCommand measureCmd = new CreateMeasureCommand(crosstab, measure, -1);
				execute(measureCmd);
			}
		}
	}
}
