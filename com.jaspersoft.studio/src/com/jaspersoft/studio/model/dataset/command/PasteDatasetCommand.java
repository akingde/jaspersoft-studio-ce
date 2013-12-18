/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.model.dataset.command;

import java.util.Collection;
import java.util.HashMap;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.Clipboard;

import com.jaspersoft.studio.editor.action.copy.PasteCommand;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.dataset.MDataset;

/**
 * Command to copy and paste dataset elements
 * 
 * @author Orlandin Marco
 *
 */
public class PasteDatasetCommand extends PasteCommand {

	private JasperDesign destination;
	
	public PasteDatasetCommand(JasperDesign parent) {
		super(null);
		this.destination = parent;
	}

	/**
	 * Create list of all the MDataset Elements inside the clipboard
	 */
	@Override
	public boolean canExecute() {
		if (list == null) {
			Object obj = Clipboard.getDefault().getContents();
			if (obj == null)
				return false;
			list = new HashMap<ANode, Command>();
			if (obj instanceof Collection<?>) {
				Collection<?> bList = (Collection<?>) obj;
				if (bList.isEmpty())
					return false;
				for (Object node : bList) {
					if (node instanceof MDataset)
						list.put((ANode)node, null);
				}
			} else if (obj instanceof MDataset)
				list.put((ANode) obj, null);
		}
		return !list.isEmpty();
	}
	
	/**
	 * Create the commands to paste
	 */
	@Override
	public void execute() {
		if (list == null && !canExecute()) return;
		createdNodes = 0;
		for (ANode node : list.keySet()) {
			CompoundCommand cmd = new CompoundCommand();
			if (node.isCut() && node.getParent() != null) {
					Command cmdd = OutlineTreeEditPartFactory.getDeleteCommand((ANode) node.getParent(), node);
					if (cmd != null)
						cmd.add(cmdd);
			} 
			// create command
			Command cmdc = new CopyDatasetCommand((MDataset)node, destination);
			cmd.add(cmdc);
			createdNodes++;
			list.put(node, cmd);
		}
		redo();
	}

	public boolean isPastableNode(Object node) {
		return node instanceof MDataset;
	}

}
