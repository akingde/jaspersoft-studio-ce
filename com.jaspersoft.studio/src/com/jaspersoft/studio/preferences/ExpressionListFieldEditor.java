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
package com.jaspersoft.studio.preferences;

import java.util.ArrayList;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;
import com.jaspersoft.studio.utils.Misc;

/**
 * Field editor for a list of user defined expressions.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ExpressionListFieldEditor extends ListEditor{
	
	public static final String EXPRESSION_SEP="\n";

	public ExpressionListFieldEditor(String name, String labelText,
			Composite parent) {
		super(name, labelText, parent);
	}

	@Override
	protected String createList(String[] items) {
        StringBuffer expressionsBuff = new StringBuffer("");//$NON-NLS-1$

        for (int i = 0; i < items.length; i++) {
        	expressionsBuff.append(items[i]);
        	expressionsBuff.append(EXPRESSION_SEP);
        }
        return expressionsBuff.toString();
	}

	@Override
	protected String getNewInputObject() {
		JRExpressionEditor wizard = new JRExpressionEditor();
		WizardDialog dialog = new WizardDialog(getShell(),wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			JRDesignExpression newExp = wizard.getValue();
			if(newExp!=null &&
					!Misc.nvl(newExp.getText()).equals("")){
				return newExp.getText();
			}
		}
		return null;
	}

	@Override
	protected String[] parseString(String stringList) {
        StringTokenizer st = new StringTokenizer(stringList, EXPRESSION_SEP);//$NON-NLS-1$
        ArrayList<String> v = new ArrayList<String>();
        while (st.hasMoreElements()) {
            v.add((String)st.nextElement());
        }
        return v.toArray(new String[v.size()]);
	}
	
}