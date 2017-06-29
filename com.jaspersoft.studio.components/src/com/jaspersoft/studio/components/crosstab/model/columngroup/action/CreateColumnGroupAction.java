/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.columngroup.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.CrosstabUtil;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.wizard.CrosstabGroupWizard;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroups;
import com.jaspersoft.studio.components.crosstab.model.columngroup.command.CreateColumnCommand;
import com.jaspersoft.studio.components.crosstab.model.crosstab.command.LazyCrosstabLayoutCommand;
import com.jaspersoft.studio.components.crosstab.model.dialog.ApplyCrosstabStyleAction;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabBucket;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Class to create a new column group. It open a wizard to define the group name and
 * expression and if it is closed with Finish the group is created and the crosstab
 * re-layout.
 * 
 * @author Orlandin Marco
 */
public class CreateColumnGroupAction extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "create_crosstab_columngroup"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public CreateColumnGroupAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.CreateColumnGroupAction_create_column_group);
		setToolTipText(Messages.CreateColumnGroupAction_create_column_group_tool_tip);
		setId(CreateColumnGroupAction.ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/add-crosstabcolumns-16.png"));
		setDisabledImageDescriptor(Activator.getDefault().getImageDescriptor("icons/add-crosstabcolumns-16.png"));
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
			CrosstabGroupWizard crosstabGroupWizard = new CrosstabGroupWizard(crosstab);
			WizardDialog dialog = new WizardDialog(UIUtils.getShell(), crosstabGroupWizard);
			if (dialog.open() == IDialogConstants.OK_ID){
				JSSCompoundCommand cmd = new JSSCompoundCommand("Create Column Group", null);
				JRDesignCrosstabColumnGroup jrGroup = CrosstabUtil.createColumnGroup(crosstab.getJasperDesign(), crosstab.getValue(),
																					 crosstabGroupWizard.getGroupName(),
																					 CrosstabTotalPositionEnum.END);
				JRDesignCrosstabBucket bucket = (JRDesignCrosstabBucket)jrGroup.getBucket();
				bucket.setExpression(new JRDesignExpression(crosstabGroupWizard.getGroupExpression()));
				bucket.setValueClassName(crosstabGroupWizard.getGroupValueClass());
				cmd.add(new CreateColumnCommand(crosstab, jrGroup, -1));
				cmd.setReferenceNodeIfNull(crosstab);
				cmd.add(new LazyCrosstabLayoutCommand(crosstab));
				execute(cmd);
				
				JasperDesign jd = crosstab.getJasperDesign();
				ApplyCrosstabStyleAction applyStyle = new ApplyCrosstabStyleAction(new ArrayList<JRDesignStyle>(), crosstab.getValue());
				applyStyle.rebuildStylesFromCrosstab(jd);
				applyStyle.applayStyle(crosstab.getJasperDesign());
			}
		}
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
	public boolean isEnabled() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MCrosstab.class);
		if (elements.size() == 1) return true;
		elements = editor.getSelectionCache().getSelectionModelForType(MColumnGroups.class);
		if (elements.size() == 1) return true;
		return false;
	}
}
