/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.classname;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

/**
 * A custom cell editor that allows to pickup the class name in different ways:
 * <ul>
 * <li>via a type search dialog opened via button</li>
 * <li>among a predefined set of default classes</li>
 * <li>writing the full class name directly</li>
 * </ul>
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ClassTypeComboCellEditor extends CellEditor {

	public static String[] DEFAULT_ITEMS = new String[] { "java.lang.Integer", "java.lang.Long", "java.lang.Double",
			"java.lang.String", "java.lang.Short", "java.lang.Boolean", "java.lang.Float", "java.math.BigDecimal",
			"java.sql.Time", "java.sql.Timestamp", "java.sql.Date", "java.util.Date" };
	private Button button;
	private CCombo combo;
	private Composite editor;
	private List<Class<?>> classes;
	private Object value;

	static {
		Arrays.sort(DEFAULT_ITEMS);
	}

	public ClassTypeComboCellEditor(Composite parent) {
		super(parent);
	}

	@Override
	protected Control createControl(Composite parent) {

		editor = new Composite(parent, getStyle());
		editor.setLayout(new DialogCellLayout());

		combo = new CCombo(editor, SWT.NONE);
		combo.setItems(DEFAULT_ITEMS);
		combo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.character == '\u001b') { // Escape character
					fireCancelEditor();
				} else if (e.character == '\r') { // Return key
					String newValue = combo.getText();
					applyValueAndDeactivate(newValue);
				}
			}
		});
		combo.addTraverseListener(new TraverseListener() {
			@Override
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_ESCAPE || e.detail == SWT.TRAVERSE_RETURN) {
					e.doit = false;
				}
			}
		});
		final FocusAdapter focusListener = new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent event) {
				String newValue = combo.getText();
				if (newValue != null) {
					boolean newValidState = isCorrect(newValue);
					setValueValid(newValidState);
					if (newValidState) {
						markDirty();
						doSetValue(newValue);
					}
					if (!button.isFocusControl()) {
						fireApplyEditorValue();
						deactivate();
					}
				}
			}
		};
		combo.addFocusListener(focusListener);

		button = new Button(editor, SWT.PUSH);
		button.setText("...");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo.removeFocusListener(focusListener);
				Object newValue = ClassTypeCellEditor.getJavaClassDialog(UIUtils.getShell(), classes);
				combo.addFocusListener(focusListener);
				applyValueAndDeactivate(newValue);
			}
		});

		return editor;
	}

	/*
	 * Applies the new chosen value and deactivates the cell editor.
	 */
	private void applyValueAndDeactivate(Object newValue) {
		if (newValue != null) {
			boolean newValidState = isCorrect(newValue);
			setValueValid(newValidState);
			if (newValidState) {
				markDirty();
				doSetValue(newValue);
			} else {
				setErrorMessage(MessageFormat.format(getErrorMessage(), new Object[] { newValue.toString() }));
			}
			fireApplyEditorValue();
			deactivate();
		} else {
			deactivate();
		}
	}

	@Override
	protected void doSetFocus() {
		combo.setFocus();
	}

	@Override
	protected Object doGetValue() {
		return this.value;
	}

	@Override
	protected void doSetValue(Object value) {
		this.value = value;
		updateContents(value);
	}

	/**
	 * Updates the cell editor contents.
	 * 
	 * @param value
	 *          the new value
	 */
	protected void updateContents(Object value) {
		combo.setText(Misc.nvl(value, ""));
	}

	/**
	 * Sets a list of allowed classes for the search scope of the class dialog.
	 * 
	 * @param classes
	 *          list of classes for the search scope
	 */
	public void setClasses(List<Class<?>> classes) {
		this.classes = classes;
	}

	/**
	 * Internal class for laying out the dialog.
	 */
	private class DialogCellLayout extends Layout {
		public void layout(Composite editor, boolean force) {
			Rectangle bounds = editor.getClientArea();
			Point size = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
			if (combo != null) {
				combo.setBounds(0, 0, bounds.width - size.x, bounds.height);
			}
			button.setBounds(bounds.width - size.x, 0, size.x, bounds.height);
		}

		public Point computeSize(Composite editor, int wHint, int hHint, boolean force) {
			if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT) {
				return new Point(wHint, hHint);
			}
			Point contentsSize = combo.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
			Point buttonSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
			// Just return the button width to ensure the button is not clipped
			// if the label is long.
			// The label will just use whatever extra width there is
			Point result = new Point(buttonSize.x, Math.max(contentsSize.y, buttonSize.y));
			return result;
		}
	}
}
