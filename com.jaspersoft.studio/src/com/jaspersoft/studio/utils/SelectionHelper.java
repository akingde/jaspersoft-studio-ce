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
package com.jaspersoft.studio.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;

public class SelectionHelper {

	public static EditPart getEditPart(JRDesignElement jrElement) {
		ANode node = getNode(jrElement);
		if (node != null && node.getFigureEditPart() != null) {
			return node.getFigureEditPart();
		}
		return null;
	}

	public static ANode getNode(JRDesignElement jrElement) {
		IWorkbenchWindow activeWorkbenchWindow = JaspersoftStudioPlugin.getInstance().getWorkbench()
				.getActiveWorkbenchWindow();
		JrxmlEditor jrxmlEditor = (JrxmlEditor) activeWorkbenchWindow.getActivePage().getActiveEditor();
		MRoot root = (MRoot) jrxmlEditor.getModel();
		ANode node = ((MReport) root.getChildren().get(0)).getNode(jrElement);
		return node;
	}

	public static boolean isSelected(JRDesignElement jrElement) {
		EditPart ep = getEditPart(jrElement);
		if (ep != null) {
			ISelection sel = ep.getViewer().getSelection();
			if (sel instanceof StructuredSelection) {
				for (Object o : ((StructuredSelection) sel).toList()) {
					if (o instanceof EditPart && ((EditPart) o) == ep)
						return true;
				}
			}
		}
		return false;
	}

	public static void setSelection(JRDesignElement jrElement, boolean add) {
		EditPart ep = getEditPart(jrElement);
		if (ep != null) {
			ISelection sel = ep.getViewer().getSelection();
			List<Object> s = new ArrayList<Object>();
			s.add(ep);
			if (add) {
				if (sel instanceof StructuredSelection) {
					for (Object o : ((StructuredSelection) sel).toList()) {
						s.add(o);
					}
				}
			}
			ep.getViewer().select(ep);
			ep.getViewer().reveal(ep);
		}

	}

}
