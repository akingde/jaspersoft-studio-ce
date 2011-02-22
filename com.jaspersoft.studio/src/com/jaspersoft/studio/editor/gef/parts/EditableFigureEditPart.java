/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;

import com.jaspersoft.studio.editor.IJROBjectEditor;
import com.jaspersoft.studio.model.ANode;

public class EditableFigureEditPart extends FigureEditPart {
	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			Object value = ((ANode) getModel()).getValue();
			IEditorPart editorPart = ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			openEditor(value, editorPart);
		}
		super.performRequest(req);
	}

	public static void openEditor(final Object val, IEditorPart editor) {
		if (editor.getEditorSite() instanceof MultiPageEditorSite) {
			final MultiPageEditorPart mpep = ((MultiPageEditorSite) editor.getEditorSite()).getMultiPageEditor();
			if (mpep instanceof IJROBjectEditor)
				doOpenEditor(val, (IJROBjectEditor) mpep);
		} else {
			editor = Workbench.getInstance().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editor instanceof IJROBjectEditor)
				doOpenEditor(val, (IJROBjectEditor) editor);
		}
	}

	public static void doOpenEditor(final Object val, final IJROBjectEditor editor) {
		Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
				((IJROBjectEditor) editor).openEditor(val);
			}
		});
	}
}
