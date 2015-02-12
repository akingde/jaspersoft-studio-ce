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
package com.jaspersoft.studio.components.table.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.components.table.editor.TableEditor;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 *
 */
public class SelectAllElementsAction extends SelectionAction {

	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.components.table.action.SelectAllElementsAction"; //$NON-NLS-1$

	public SelectAllElementsAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.SelectAllElementsAction_1);
		setId(ID);
	}

	/**
	 * The action is enable only if enabled if and only if the first element of
	 * the selection is a TableEditPart with inside an MTable
	 */
	@Override
	protected boolean calculateEnabled() {
		List<?> tables = getSelectedObjects();
		for (Object obj : tables) {
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();
			if (obj instanceof MTable || obj instanceof MColumn
					|| obj instanceof AMCollection)
				return true;
		}
		return false;
	}

	private StandardTable getTable() {
		List<?> tables = getSelectedObjects();
		for (Object obj : tables) {
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();
			if (obj instanceof MTable)
				return ((MTable) obj).getStandardTable();
			if (obj instanceof MColumn)
				return ((MColumn) obj).getMTable().getStandardTable();
			if (obj instanceof AMCollection)
				return ((AMCollection) obj).getStandardTable();
		}
		return null;
	}

	/**
	 * Execute the action
	 */
	@Override
	public void run() {
		TableEditor part = (TableEditor) getWorkbenchPart();
		StandardTable tbl = getTable();
		List<JRDesignElement> tblels = ModelUtils.getTableElements(tbl);
		List<EditPart> parts = new ArrayList<EditPart>();
		Map<?, ?> map = part.getGraphicalViewer().getEditPartRegistry();
		for (Object obj : map.keySet()) {
			if (obj instanceof ANode
					&& tblels.contains(((ANode) obj).getValue())) {
				EditPart ep = (EditPart) map.get(obj);
				if (ep != null)
					parts.add(ep);
			}
		}
		StructuredSelection s = new StructuredSelection(parts);

		part.getGraphicalViewer().setSelection(s);
	}

}
