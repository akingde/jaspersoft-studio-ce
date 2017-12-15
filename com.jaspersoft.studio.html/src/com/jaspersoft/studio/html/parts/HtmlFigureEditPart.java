/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.html.parts;

import net.sf.jasperreports.components.html.HtmlComponent;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.html.model.MHtml;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.utils.ModelUtils;

public class HtmlFigureEditPart extends FigureEditPart {

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			if(!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
				JRExpressionEditor wizard = new JRExpressionEditor();
				MHtml m = (MHtml) getModel();
				wizard.setValue((JRDesignExpression) m
						.getPropertyValue(HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION));
				ExpressionContext ec=ModelUtils.getElementExpressionContext((JRDesignElement)m.getValue(), m);
				wizard.setExpressionContext(ec);
				WizardDialog dialog = ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(Display.getDefault()
						.getActiveShell(), wizard);
				if (dialog.open() == Dialog.OK) {
					SetValueCommand cmd = new SetValueCommand();
					cmd.setTarget((IPropertySource) getModel());
					cmd.setPropertyId(HtmlComponent.PROPERTY_HTMLCONTENT_EXPRESSION);
					cmd.setPropertyValue(wizard.getValue());
					getViewer().getEditDomain().getCommandStack().execute(cmd);
				}
			}
		} else
			super.performRequest(req);
	}
}
