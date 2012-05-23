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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.preview.view.APreview;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class VSimpleErrorPreview extends APreview {

	public VSimpleErrorPreview(Composite parent, PropertiesHelper ph) {
		super(parent, ph);
	}

	private Text tmessage;

	@Override
	public Control createControl(final Composite parent) {
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		container.setLayout(layout);

		createMessages(container);

		return container;
	}

	public void setFocus() {
		container.setFocus();
	}

	protected void createMessages(Composite composite) {
		Label lbl = new Label(composite, SWT.PUSH);
		lbl.setText("The document is empty.");
		GridData layoutData = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);

		GC gc = new GC(lbl);
		FontMetrics fontMetrics = gc.getFontMetrics();
		int h = fontMetrics.getHeight();
		gc.dispose();
		layoutData.heightHint = h * 2 + 50;
		lbl.setLayoutData(layoutData);

		// tmessage = new Text(composite, SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		// tmessage.setText("EmptyDocument");
		// tmessage.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	public void setMessage(String msg) {
		tmessage.setText(msg);
	}

	public void addMessage(String msg) {
		tmessage.setText(tmessage.getText() + msg + "\n");
	}

	private Composite container;

	public void clear() {
		tmessage.setText("");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
