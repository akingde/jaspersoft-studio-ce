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
package com.jaspersoft.studio.editor.action.copy;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.StructuredSelection;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.band.MBand;

/**
 * Implementation of the paste definition for the editor graphical
 * objects. This can be used to copy\paste graphical objects
 * 
 * @author Orlandin Marco
 *
 */
public class PastableElements extends AbstractPastableObject {

	/**
	 * Create an instance of the class
	 * 
	 * @param list not null list of the elements that are object of the copy
	 */
	public PastableElements(List<ICopyable> list) {
		super(list);
	}

	@Override
	public Command getPasteCommand(Collection<?> targets) {
		return createCommand(targets);
	}
	
	protected Command createCommand(Collection<?> selectedObjects) {
		for (Object selection : selectedObjects) {
			Command cmd = getPasteComand(selection);
			if (cmd != null)
				return cmd;
			if (selection instanceof StructuredSelection) {
				StructuredSelection s = (StructuredSelection) selection;
				for (Iterator<?> it = s.iterator(); it.hasNext();) {
					Object o = it.next();
					cmd = getPasteComand(o);
					if (cmd != null)
						return cmd;
				}
			}
		}
		return null;
	}
	
	private IPastable getParent2Paste(ANode n) {
		while (n != null) {
			if (n instanceof IPastable) {
				if (n instanceof MBand && n.getValue() == null)
					return null;
				return (IPastable) n;
			}
			n = (ANode) n.getParent();
		}
		return null;
	}
	
	private Command getPasteComand(Object selection) {
		if (selection instanceof EditPart) {
			Object modelObj = ((EditPart) selection).getModel();
			if (modelObj instanceof ANode) {
				IPastable past = getParent2Paste((ANode) modelObj);
				if (past != null) {
					return new PasteCommand(past);
				}
			}
		} else if (selection instanceof ANode) {
			IPastable past = getParent2Paste((ANode) selection);
			if (past != null) {
				return new PasteCommand(past);
			}
		}
		return null;
	}
}
