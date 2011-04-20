/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.action.copy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;

public class CutCommand extends Command {
	private List<ANode> list = new ArrayList<ANode>();

	public boolean addElement(ANode node) {
		if (!list.contains(node)) {
			return list.add(node);
		}
		return false;
	}

	public boolean isCopyableNode(ANode node) {
		if (node instanceof ICopyable)
			return true;
		return false;
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	@Override
	public void execute() {
		if (canExecute()) {
			for (ANode n : list)
				n.setCut(true);
			Clipboard.getDefault().setContents(list);
		}
	}

	@Override
	public boolean canExecute() {
		if (list == null || list.isEmpty())
			return false;
		Iterator<ANode> it = list.iterator();
		while (it.hasNext()) {
			if (!isCopyableNode(it.next()))
				return false;
		}
		return true;

	}

}
