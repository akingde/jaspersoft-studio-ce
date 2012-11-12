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
package com.jaspersoft.studio.editor.part;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;

public class TFItem extends Item {
	private TFContainer parent;
	private Control control; // the tab page

	public TFItem(TFContainer parent, int style) {
		this(parent, style, parent.getItemCount());
	}

	public TFItem(TFContainer parent, int style, int index) {
		super(parent, style);
		this.parent = parent;
		parent.createItem(this, index);
	}

	public Control getControl() {
		checkWidget();
		return control;
	}

	@Override
	public void setText(String string) {
		super.setText(string);
		parent.update(this);
	}

	@Override
	public void setImage(Image image) {
		super.setImage(image);
		parent.update(this);
	}

	public void setControl(Control control) {
		checkWidget();
		if (control != null) {
			if (control.isDisposed())
				SWT.error(SWT.ERROR_INVALID_ARGUMENT);
			if (control.getParent() != parent.getContent())
				SWT.error(SWT.ERROR_INVALID_PARENT);
		}
		if (this.control != null && !this.control.isDisposed())
			this.control.setVisible(false);

		((StackLayout) parent.getContent().getLayout()).topControl = control;

		this.control = control;
		if (this.control != null) {
			int index = parent.indexOf(this);
			if (index == parent.getSelectionIndex()) {
				this.control.setBounds(parent.getContent().getClientArea());
				this.control.setVisible(true);
			} else {
				int selectedIndex = parent.getSelectionIndex();
				Control selectedControl = null;
				if (selectedIndex != -1) {
					selectedControl = parent.getItem(selectedIndex).getControl();
				}
				if (this.control != selectedControl) {
					this.control.setVisible(false);
				}
			}
		}
		parent.getContent().layout(true);
	}
	@Override
	public void dispose() {
		parent.removeItem(this);
		super.dispose();
	}
}
