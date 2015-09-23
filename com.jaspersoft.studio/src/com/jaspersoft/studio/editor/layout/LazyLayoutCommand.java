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
package com.jaspersoft.studio.editor.layout;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;

/**
 * Command to layout a container, it encapsulate a layout 
 * command and the difference is that the data used for the layout
 * it is extracted when the command is executed and not when it is 
 * created like a standard layout command
 * 
 * @author Orlandin Marco
 */
public class LazyLayoutCommand extends Command {
	
	/**
	 * The inner layout command
	 */
	private LayoutCommand cmd = null;
	
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
	public LazyLayoutCommand(ANode container) {
		this.container = container;
	}
	
	@Override
	public boolean canExecute() {
		return container != null;
	}

	@Override
	public void execute() {
		cmd = LayoutManager.creteRelayoutCommand(container);
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
}
