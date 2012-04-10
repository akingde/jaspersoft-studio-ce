package com.jaspersoft.studio.editor.report;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;

public class TFItem extends Item {
	TFContainer parent;
	Control control; // the tab page

	public TFItem(TFContainer parent, int style) {
		this(parent, style, parent.getItemCount());
	}

	public TFItem(TFContainer parent, int style, int index) {
		super(parent, style);
		parent.createItem(this, index);
	}

	public Control getControl() {
		checkWidget();
		return control;
	}

	public void setControl(Control control) {
		checkWidget();
		if (control != null) {
			if (control.isDisposed())
				SWT.error(SWT.ERROR_INVALID_ARGUMENT);
			if (control.getParent() != parent)
				SWT.error(SWT.ERROR_INVALID_PARENT);
		}
		if (this.control != null && !this.control.isDisposed()) {
			this.control.setVisible(false);
		}
		this.control = control;
		if (this.control != null) {
			int index = parent.indexOf(this);
			if (index == parent.getSelectionIndex()) {
				this.control.setBounds(parent.getClientArea());
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
	}
}
