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
package com.jaspersoft.studio.components.crosstab.model.rowgroup.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.components.crosstab.model.dialog.ApplyCrosstabStyleAction;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroups;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.outline.actions.ACreateAction;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;

import net.sf.jasperreports.engine.design.JRDesignStyle;
/*
 * The Class CreateGroupAction.
 */
public class CreateRowGroupAction extends ACreateAction {

	/** The Constant ID. */
	public static final String ID = "create_rowgroup"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public CreateRowGroupAction(IWorkbenchPart part) {
		super(part);
		setCreationFactory(new JDPaletteCreationFactory(MRowGroup.class));
	}
	
	@Override
	public void run() {
		super.run();
		MCrosstab crosstab = null;
		Object selected = getSelectedObjects().get(0);
		if (selected instanceof EditPart) {
			EditPart part = (EditPart) selected;
			if (part.getModel() instanceof INode) crosstab = getCrosstab((INode)part.getModel());
		} 
		if (crosstab != null){
			ApplyCrosstabStyleAction applyStyle = new ApplyCrosstabStyleAction(new ArrayList<JRDesignStyle>(), crosstab.getValue());
			applyStyle.rebuildStylesFromCrosstab();
			applyStyle.applayStyle(crosstab.getJasperDesign());
			
			JSSCompoundCommand relayoutContentCommands = new JSSCompoundCommand(crosstab);
			createLayoutCommand(crosstab, relayoutContentCommands);
			execute(relayoutContentCommands);
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

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateRowGroupAction_create_row_group);
		setToolTipText(Messages.CreateRowGroupAction_create_row_group_tool_tip);
		setId(CreateRowGroupAction.ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/add-crosstabrows-16.png"));
		setDisabledImageDescriptor(Activator.getDefault().getImageDescriptor("icons/add-crosstabrows-16.png"));
		setEnabled(false);
	}
	
	@Override
	public boolean isEnabled() {
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(MCrosstab.class);
		if (elements.size() == 1) return true;
		elements = editor.getSelectionCache().getSelectionModelForType(MRowGroups.class);
		if (elements.size() == 1) return true;
		return false;
	}

	/**
	 * Create a command to layout the current node if it is a cell, otherwise it 
	 * will search recursively a cell in every child of the node
	 */
	public void createLayoutCommand(INode node, JSSCompoundCommand c){
		if (node == null) return;
		if (node instanceof MCell && node.getValue() != null){
			Command cmd = LayoutManager.createRelayoutCommand((ANode)node);
			if (cmd != null) c.add(cmd);
		} else {
			for(INode child : node.getChildren()){
				createLayoutCommand(child, c);
			}
		}
	}

}
