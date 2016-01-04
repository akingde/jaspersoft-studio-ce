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
package com.jaspersoft.studio.components.crosstab.model.crosstab.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

/**
 * Crearte and execute a layout command on the whole crosstab. The command
 * to layout the crosstab is created and executed at execution time.
 * 
 * 
 * @author Orlandin Marco
 */
public class LazyCrosstabLayoutCommand extends Command{
	
	/**
	 * The inner layout command
	 */
	private JSSCompoundCommand cmd = null;
	
	/**
	 * The container to layout
	 */
	private ANode container;
	
	/**
	 * Create the command
	 * 
	 * @param container the container to layout, if 
	 * null the command can`t not be executed
	 */
	public LazyCrosstabLayoutCommand(ANode container) {
		this.container = container;
	}
	
	@Override
	public boolean canExecute() {
		return container != null;
	}

	@Override
	public void execute() {
		cmd = new JSSCompoundCommand(container);
		createLayoutCommand(container, cmd);
		if (cmd != null){
			cmd.execute();
		}
	}

	@Override
	public void undo() {
		if (cmd != null){
			cmd.undo();
		}
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
