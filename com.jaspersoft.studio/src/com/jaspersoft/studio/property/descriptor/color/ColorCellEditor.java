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
package com.jaspersoft.studio.property.descriptor.color;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

public class ColorCellEditor extends DialogCellEditor {

	/**
	 * Gap between between image and text in pixels.
	 */
	private static final int GAP = 6;

	/**
	 * The composite widget containing the color and RGB label widgets
	 */
	private Composite composite;

	/**
	 * The label widget showing the RGB values.
	 */
	private Label rgbLabel;

	/**
	 * Internal class for laying out this cell editor.
	 */
	private class ColorCellLayout extends Layout {
		public Point computeSize(Composite editor, int wHint, int hHint, boolean force) {
			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
				return new Point(wHint, hHint);
			}
			Point rgbSize = rgbLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
			return new Point(GAP + rgbSize.x, rgbSize.y);
		}

		public void layout(Composite editor, boolean force) {
			Rectangle bounds = editor.getClientArea();
			Point rgbSize = rgbLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
			int ty = (bounds.height - rgbSize.y) / 2;
			if (ty < 0) {
				ty = 0;
			}
			rgbLabel.setBounds(GAP - 1, ty, bounds.width - GAP, bounds.height);
		}
	}

	/**
	 * Creates a new color cell editor parented under the given control. The cell editor value is black (
	 * <code>RGB(0,0,0)</code>) initially, and has no validator.
	 * 
	 * @param parent
	 *          the parent control
	 */
	public ColorCellEditor(Composite parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Creates a new color cell editor parented under the given control. The cell editor value is black (
	 * <code>RGB(0,0,0)</code>) initially, and has no validator.
	 * 
	 * @param parent
	 *          the parent control
	 * @param style
	 *          the style bits
	 */
	public ColorCellEditor(Composite parent, int style) {
		super(parent, style);
		doSetValue(new RGB(0, 0, 0));
	}

	@Override
	protected Control createContents(Composite cell) {
		Color bg = cell.getBackground();
		composite = new Composite(cell, getStyle());
		composite.setBackground(bg);
		composite.setLayout(new ColorCellLayout());
		rgbLabel = new Label(composite, SWT.LEFT);
		rgbLabel.setBackground(bg);
		rgbLabel.setFont(cell.getFont());
		return composite;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		ColorDialog dialog = new ColorDialog(cellEditorWindow.getShell());
		Object value = getValue();
		if (value != null) {
			dialog.setRGB((RGB) value);
		}
		value = dialog.open();
		return dialog.getRGB();
	}

	@Override
	protected void updateContents(Object value) {
		RGB rgb = (RGB) value;
		// XXX: We don't have a value the first time this method is called".
		if (rgb == null) {
			rgbLabel.setText(""); //$NON-NLS-1$
			// rgb = new RGB(0, 0, 0);
		} else
			rgbLabel.setText("RGB (" + rgb.red + "," + rgb.green + "," + rgb.blue + ")");//$NON-NLS-4$//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$
	}
}
