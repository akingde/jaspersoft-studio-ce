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
package com.jaspersoft.studio.editor.gef.parts.text;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignTextField;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.text.MTextField;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * The Class FigureEditPart.
 */
public class TextFieldFigureEditPart extends FigureEditPart {

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			UIUtils.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					if(!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
						JRExpressionEditor wizard = new JRExpressionEditor();
						MTextField m = (MTextField) getModel();
						wizard.setValue((JRDesignExpression) m.getPropertyValue(JRDesignTextField.PROPERTY_EXPRESSION));
						ExpressionContext ec = ModelUtils.getElementExpressionContext((JRDesignTextField) m.getValue(), m);
						wizard.setExpressionContext(ec);
	
						WizardDialog dialog = ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(Display.getDefault().getActiveShell(), wizard);
						if (dialog.open() == Dialog.OK) {
							SetValueCommand cmd = new SetValueCommand();
							cmd.setTarget((IPropertySource) getModel());
							cmd.setPropertyId(JRDesignTextField.PROPERTY_EXPRESSION);
							cmd.setPropertyValue(wizard.getValue());
							getViewer().getEditDomain().getCommandStack().execute(cmd);
						}
					}
				}
			});
		} else
			super.performRequest(req);
	}

}
