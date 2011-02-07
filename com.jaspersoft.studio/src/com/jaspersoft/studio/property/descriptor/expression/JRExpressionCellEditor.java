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
		wizard.setValue((MExpression) getValue());
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
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new JRExpressionLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
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
			Display.getCurrent().asyncExec(new Runnable() {

				public void run() {
					// m.setPropertyValue(JRDesignExpression.PROPERTY_TEXT, text.getText());
					e.setText(text.getText());
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
