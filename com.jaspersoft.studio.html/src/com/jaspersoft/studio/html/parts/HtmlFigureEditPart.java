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
package com.jaspersoft.studio.html.parts;

import net.sf.jasperreports.components.html.HtmlComponent;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.html.model.MHtml;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;

public class HtmlFigureEditPart extends FigureEditPart {

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			JRExpressionEditor wizard = new JRExpressionEditor();
			MHtml m = (MHtml) getModel();
			wizard.setValue((MExpression) m.getPropertyValue(HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION));
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				SetValueCommand cmd = new SetValueCommand();
				cmd.setTarget((IPropertySource) getModel());
				cmd.setPropertyId(HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION);
				cmd.setPropertyValue(wizard.getValue());
				getViewer().getEditDomain().getCommandStack().execute(cmd);
			}
		} else
			super.performRequest(req);
	}
}
