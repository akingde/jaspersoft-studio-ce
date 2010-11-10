/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.action.copy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRCloneable;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IPastable;

public class PasteCommand extends Command {
	private Map<ANode, Command> list = new HashMap<ANode, Command>();
	private IPastable parent;

	public PasteCommand(IPastable parent) {
		super();
		this.parent = parent;
	}

	@Override
	public boolean canExecute() {
		Object obj = Clipboard.getDefault().getContents();
		if (obj == null)
			return false;
		if (obj instanceof Collection<?>) {
			Collection bList = (Collection) obj;
			if (bList.isEmpty())
				return false;
			for (Iterator<?> it = bList.iterator(); it.hasNext();) {
				ANode node = (ANode) it.next();
				if (isPastableNode(node)) {
					list.put(node, null);
				}
			}
		} else if (obj instanceof ANode)
			if (isPastableNode(obj))
				list.put((ANode) obj, null);

		return !list.isEmpty();
	}

	@Override
	public void execute() {
		if (!canExecute())
			return;

		Iterator<ANode> it = list.keySet().iterator();
		while (it.hasNext()) {
			ANode node = it.next();
			// create new Node put, clone into it
			try {
				Object value = node.getValue();
				if (value instanceof JRCloneable) {
					ANode n = node.getClass().newInstance();

					n.setValue(((JRCloneable) value).clone());
					// create command
					Command cmd = OutlineTreeEditPartFactory.getCreateCommand((ANode) parent, n, (Point) null, -1);
					list.put(node, cmd);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		redo();
	}

	@Override
	public void redo() {
		Iterator<Command> it = list.values().iterator();
		while (it.hasNext()) {
			Command cmd = it.next();
			cmd.execute();
		}
	}

	@Override
	public boolean canUndo() {
		return !(list.isEmpty());
	}

	@Override
	public void undo() {
		Iterator<Command> it = list.values().iterator();
		while (it.hasNext()) {
			Command cmd = it.next();
			cmd.undo();
		}
	}

	public boolean isPastableNode(Object node) {
		if (node instanceof ICopyable && ((ICopyable) node).isCopyable2(parent))
			return true;
		return false;
	}
}
