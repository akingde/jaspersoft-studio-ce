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
package com.jaspersoft.studio.editor.gef.parts;

import net.sf.jasperreports.engine.base.JRBaseStaticText;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.model.text.MStaticText;
import com.jaspersoft.studio.editor.gef.parts.directeditor.TextEditManager;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * The Class FigureEditPart.
 */
public class StaticTextFigureEditPart extends FigureEditPart {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new DirectEditPolicy() {

			@Override
			protected void showCurrentEditValue(DirectEditRequest request) {
				getFigure().getUpdateManager().performUpdate();
			}

			@Override
			protected Command getDirectEditCommand(DirectEditRequest request) {
				SetValueCommand cmd = new SetValueCommand();
				cmd.setTarget((IPropertySource) getHost().getModel());
				cmd.setPropertyId(JRBaseStaticText.PROPERTY_TEXT);
				CellEditor cellEditor = request.getCellEditor();
				cmd.setPropertyValue((String) cellEditor.getValue());
				return cmd;
			}
		});
		
	}

	protected DirectEditManager manager;

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_OPEN) {
			if (manager == null) {
//				manager = new DirectEditManager(this, MultiLineTextEditor.class, new LabelCellEditorLocator(getFigure()), null) {
//
//					@Override
//					protected void initCellEditor() {
//						MStaticText model = (MStaticText) getModel();
//						getCellEditor().setValue(model.getPropertyValue(JRBaseStaticText.PROPERTY_TEXT));
//						getCellEditor().getControl().setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
//					}
//				};
				manager = new TextEditManager(this, new LabelCellEditorLocator(getFigure()) );
			}
			manager.show();
		}
	}

}
