/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.expression;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.descriptor.ATextDialogRWCellEditor;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;

public class JRExpressionCellEditor extends ATextDialogRWCellEditor {

	public JRExpressionCellEditor(Composite parent) {
		super(parent);
	}

	public JRExpressionCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		JRExpressionEditor wizard = new JRExpressionEditor();
		Object val = getValue();

		if (val == null)
			val = new MExpression(new JRDesignExpression());
		if (val instanceof String) {
			JRDesignExpression jrexpr = new JRDesignExpression();
			jrexpr.setValueClassName(String.class.getName());
			jrexpr.setText((String) val);
			val = new MExpression(jrexpr);
		}
		wizard.setValue((MExpression) val);
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			return wizard.getValue();
		}
		return null;
	}

	private JRExpressionLabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (labelProvider == null)
			labelProvider = new JRExpressionLabelProvider();
		String txt = labelProvider.getText(value);

		if (getDefaultLabel() == null) {
			text.removeModifyListener(getModifyListener());
			text.setText(txt);
			text.addModifyListener(getModifyListener());
		} else
			getDefaultLabel().setText(txt);
	}

	@Override
	protected Object doGetValue() {
		Object val = super.doGetValue();
		if (isDirty() && val instanceof MExpression) {
			final MExpression m = (MExpression) val;
			JRDesignExpression dexpr = (JRDesignExpression) m.getValue();
			if (dexpr == null) {
				dexpr = new JRDesignExpression();
				dexpr.setValueClassName(String.class.getName());
				m.setValue(dexpr);
			}
			final JRDesignExpression e = dexpr;
			final String exprTxt = text.getText();

			Display.getCurrent().asyncExec(new Runnable() {

				public void run() {
					// m.setPropertyValue(JRDesignExpression.PROPERTY_TEXT, text.getText());
					e.setText(exprTxt);
					// doSetValue(exprTxt);
					updateContents(exprTxt);
				}
			});
			return new MExpression(dexpr);
		}
		return val;
	}

	@Override
	protected void doSetValue(Object value) {
		super.doSetValue(value);
		if (value instanceof MExpression) {
			MExpression expression = (MExpression) value;

			text.removeModifyListener(getModifyListener());
			String pvalue = (String) expression.getPropertyValue(JRDesignExpression.PROPERTY_TEXT);
			if (pvalue == null)
				pvalue = ""; //$NON-NLS-1$
			text.setText(pvalue);
			text.addModifyListener(getModifyListener());
		}
	}

}
