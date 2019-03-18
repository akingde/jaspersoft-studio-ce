/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.layout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

/**
 * Layout for the case when we have a label and a control, when computing the size the control 
 * has no bounds but it can have a size greater than the client area of the container. 
 * If the client area has not yet been defined the control will use a default width
 * (defaultControlWidth), lower than the column width. But it is allowed to grow until
 * the column with but not above it. This is done to avoid a forced resizing of the column
 * because of a widget with too much text
 */
public class ResizableControlLayout extends Layout {

	private int defaultControlWidth;
	
	public ResizableControlLayout(int defaultControlWidth) {
		super();
		this.defaultControlWidth = defaultControlWidth;
	}
	
	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
		Rectangle clientArea = composite.getClientArea();
		Control label = composite.getChildren()[0];
		Control control = composite.getChildren()[1];
		Point sizeLabel = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		Point sizeControl = control.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		int width = defaultControlWidth + sizeLabel.x + 8;
		if (clientArea.width != 0 && sizeControl.x > defaultControlWidth) {
			width = Math.min(clientArea.width, sizeControl.x) + sizeLabel.x + 8;
		}
		return new Point(width, Math.max(sizeLabel.y, sizeControl.y));
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		Control label = composite.getChildren()[0];
		Point labelSize = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		label.setSize(labelSize.x, labelSize.y);
		label.setBounds(0, 0, labelSize.x, labelSize.y);
		int startX = labelSize.x + 8;
		Control control = composite.getChildren()[1];
		Point size = control.computeSize(SWT.DEFAULT,SWT.DEFAULT);
		if (composite.getClientArea().width !=0 && size.x > composite.getClientArea().width - startX) {
			control.setBounds(startX, 0, composite.getClientArea().width - startX, size.y);
		} else {
			control.setBounds(startX, 0, size.x, size.y);
		}
	}

}
