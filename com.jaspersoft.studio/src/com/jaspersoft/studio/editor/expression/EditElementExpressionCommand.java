/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.expression;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRCloneUtils;

/**
 * Command that allows to edit the expression associated to an element. Supported types are
 *  {@link JRDesignTextField}, {@link JRParameter} and {@link JRVariable} elements.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class EditElementExpressionCommand extends Command {

	private JasperDesign jasperDesign;
	private APropertyNode editedElement;
	private String propertyName;
	private JRDesignExpression originalExpression;
	private JRDesignExpression newExpression;
	private boolean isExpressionChanged;
	
	public EditElementExpressionCommand(APropertyNode textField, String propertyName) {
		this.editedElement = textField;
		this.jasperDesign = this.editedElement.getJasperDesign();
		this.propertyName = propertyName;
		this.originalExpression = (JRDesignExpression) this.editedElement.getPropertyValue(propertyName);
		this.isExpressionChanged = false;
	}
	
	@Override
	public boolean canExecute() {
		return (editedElement!=null && jasperDesign!=null);
	}
	
	@Override
	public void execute() {
		editedElement.setPropertyValue(propertyName, newExpression);
	}
	
	public int showDialog() {
		if(!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
			JRExpressionEditor wizard = new JRExpressionEditor();
			wizard.setValue(JRCloneUtils.nullSafeClone(originalExpression));
			ExpressionContext ec = null;
			if (editedElement.getValue() instanceof JRDesignElement) {
				ec = ModelUtils.getElementExpressionContext((JRDesignElement) editedElement.getValue(), editedElement);
			} else if (editedElement instanceof MParameter) {
				ec = ((MParameter)editedElement).getExpressionContext();
			} else if (editedElement instanceof MVariable) {
				ec = ((MVariable)editedElement).getExpressionContext();
			} else {
				ec = ExpressionContext.getGlobalContext();
			}
			wizard.setExpressionContext(ec);

			WizardDialog dialog = ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(UIUtils.getShell(), wizard);
			if (dialog.open() == Dialog.OK) {
				isExpressionChanged = true;
				newExpression=wizard.getValue();
				return Window.OK;
			}
		}
		isExpressionChanged=false;
		return Window.CANCEL;
	}
	
	public boolean canRedo() {
		return isExpressionChanged;
	}
	
	@Override
	public boolean canUndo() {
		// we can not simply rely on the original and new expression
		// values, because null is a good one
		return isExpressionChanged;
	}
	
	@Override
	public void undo() {
		editedElement.setPropertyValue(propertyName, originalExpression);
	}
	
	@Override
	public String getLabel() {
		return "Change Text Field Expression";
	}
}
