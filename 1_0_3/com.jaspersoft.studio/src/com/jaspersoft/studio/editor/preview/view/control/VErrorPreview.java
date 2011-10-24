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
package com.jaspersoft.studio.editor.preview.view.control;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.utils.ErrorUtil;

public class VErrorPreview extends APreview {

	private static final String NL = System.getProperty("line.separator");
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat();

	public VErrorPreview(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	private Text txterror;

	@Override
	public Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setBackground(parent.getBackground());

		txterror = new Text(composite, SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL);
		txterror.setLayoutData(new GridData(GridData.FILL_BOTH));
		txterror.setBackground(composite.getBackground());

		return composite;
	}

	public void setError(String msg, Throwable t) {
		String text = "";
		if (msg != null)
			text += "message " + NL;
		;
		if (t != null)
			text += ErrorUtil.getStackTrace(t);
		txterror.setText(text);
	}

	public void addError(Throwable t) {
		txterror.setText(txterror.getText() + SIMPLE_DATE_FORMAT.format(new Date()) + " - " + t.getMessage() + NL
				+ ErrorUtil.getStackTrace(t) + NL);
	}

	public void addMessage(String msg) {
		msg = SIMPLE_DATE_FORMAT.format(new Date()) + " - " + msg + NL;
		txterror.setText(txterror.getText() + msg);
	}

	public void clearMessages() {
		txterror.setText("");
	}

	public void setMessage(String msg) {
		txterror.setText(msg);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
