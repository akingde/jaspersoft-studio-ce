/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.action.copy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRCloneable;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.Clipboard;

import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.MGraphicElement;

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
			Collection<?> bList = (Collection<?>) obj;
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
		for (ANode node : list.keySet()) {
			CompoundCommand cmd = new CompoundCommand();
			// create new Node put, clone into it
			try {
				Object value = node.getValue();
				if (value instanceof JRCloneable) {
					ANode n = node.getClass().newInstance();
					Rectangle rect = null;
					n.setValue(((JRCloneable) value).clone());
					if (node.isCut() && node.getParent() != null) {
						Command cmdd = OutlineTreeEditPartFactory.getDeleteCommand((ANode) node.getParent(), node);
						if (cmd != null)
							cmd.add(cmdd);
					} else if (n instanceof MGraphicElement) {
						MGraphicElement mge = (MGraphicElement) n;
						JRDesignElement de = (JRDesignElement) mge.getValue();
						if (parent == node.getParent()) {
							de.setX(de.getX() + 5);
							de.setY(de.getY() + 5);
						}
						rect = mge.getBounds();
						rect.setLocation(de.getX(), de.getY());
					}
					// create command
					Command cmdc = OutlineTreeEditPartFactory.getCreateCommand((ANode) parent, n, rect, -1);
					if (cmdc != null)
						cmd.add(cmdc);

					if (!cmd.isEmpty())
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
			if (cmd != null)
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
