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
package com.jaspersoft.studio.property.descriptor.expression.dialog;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MExpression;

public class JRExpressionEditor extends Wizard {
	private MExpression mExpression;
	private JRExpressionPage page0;

	public MExpression getValue() {
		if (page0 != null)
			return page0.getValue();
		return mExpression;
	}

	public void setValue(MExpression value) {
		if (page0 != null)
			page0.setValue(value);
		this.mExpression = value;
	}

	public JRExpressionEditor() {
		super();
		setWindowTitle(Messages.common_expression_editor);
	}

	@Override
	public void addPages() {
		page0 = new JRExpressionPage("jrquery.editor"); //$NON-NLS-1$
		page0.setValue(mExpression);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
