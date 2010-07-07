package com.jaspersoft.studio.property.descriptor.classname;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.core.BinaryType;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;

public class ClassTypeCellEditor extends DialogCellEditor {

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
	private Label textLabel;

	/**
	 * Internal class for laying out this cell editor.
	 */
	private class CellLayout extends Layout {
		public Point computeSize(Composite editor, int wHint, int hHint, boolean force) {
			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
				return new Point(wHint, hHint);
			}
			Point rgbSize = textLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
			return new Point(GAP + rgbSize.x, rgbSize.y);
		}

		public void layout(Composite editor, boolean force) {
			Rectangle bounds = editor.getClientArea();
			Point rgbSize = textLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
			int ty = (bounds.height - rgbSize.y) / 2;
			if (ty < 0) {
				ty = 0;
			}
			textLabel.setBounds(GAP - 1, ty, bounds.width - GAP, bounds.height);
		}
	}

	/**
	 * Creates a new color cell editor parented under the given control. The cell editor value is black (
	 * <code>RGB(0,0,0)</code>) initially, and has no validator.
	 * 
	 * @param parent
	 *          the parent control
	 */
	public ClassTypeCellEditor(Composite parent) {
		this(parent, SWT.NONE);
		setValue("");
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
	public ClassTypeCellEditor(Composite parent, int style) {
		super(parent, style);
		doSetValue("");
	}

	@Override
	protected Control createContents(Composite cell) {
		Color bg = cell.getBackground();
		composite = new Composite(cell, getStyle());
		composite.setBackground(bg);
		composite.setLayout(new CellLayout());
		textLabel = new Label(composite, SWT.LEFT);
		textLabel.setBackground(bg);
		textLabel.setFont(cell.getFont());
		return composite;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		Shell shell = cellEditorWindow.getShell();
		try {
			SelectionDialog dialog = JavaUI.createTypeDialog(shell, new ProgressMonitorDialog(shell), SearchEngine
					.createWorkspaceScope(), IJavaElementSearchConstants.CONSIDER_ALL_TYPES, true);
			if (dialog.open() == Window.OK) {
				if (dialog.getResult() != null && dialog.getResult().length > 0) {
					BinaryType bt = (BinaryType) dialog.getResult()[0];
					return bt.getFullyQualifiedName();
				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void updateContents(Object value) {
		String jrQuery = (String) value;
		// XXX: We don't have a value the first time this method is called".
		if (jrQuery == null) {
			jrQuery = "";
		}

		textLabel.setText(jrQuery);
	}

}
