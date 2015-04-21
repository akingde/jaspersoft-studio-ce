/*******************************************************************************
 * Copyright (C) 2005 - 2015 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.classname;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.viewers.DialogCellEditor;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.utils.Misc;

/**
 * A custom cell editor that allows to pickup the class name in different ways:
 * <ul>
 * 	<li>via a type search dialog opened via button</li>
 * 	<li>among a predefined set of default classes</li>
 *  <li>writing the full class name directly</li>
 * </ul>
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ComboClassTypeCellEditor extends DialogCellEditor {
	
	private static String[] DEFAULT_ITEMS;
	private CCombo combo;
	private int selection;
	private List<Class<?>> classes;
	private String[] items;

	static {
		DEFAULT_ITEMS = new String[] {
			"java.lang.Integer", "java.lang.Long", "java.lang.Double", "java.lang.String",
			"java.lang.Short", "java.lang.Boolean", "java.lang.Float", "java.math.BigDecimal",
			"java.sql.Time", "java.sql.Timestamp", "java.sql.Date"
		};
		Arrays.sort(DEFAULT_ITEMS);
	}
	
	public ComboClassTypeCellEditor(Composite parent) {
		super(parent,SWT.NONE);
		this.items = DEFAULT_ITEMS;
	}
	
	@Override
	protected Control createContents(Composite cell) {
		combo = new CCombo(cell, SWT.NONE);
		combo.setFont(cell.getFont());
		combo.setBackground(cell.getBackground());
		combo.setItems(DEFAULT_ITEMS);
		combo.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent event) {
				selection = combo.getSelectionIndex();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				applyEditorValueAndDeactivate();			
			}
		});
		combo.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				keyReleaseOccured(e);
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
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent event) {
				String newValue = combo.getText();
				if (newValue != null) {
					boolean newValidState = isCorrect(newValue);
					if (newValidState) {
						markDirty();
						doSetValue(newValue);
					}
					fireApplyEditorValue();
				}
			}
		});
		
		return combo;
	}
	
	/**
	 * Sets a new bunch of items for the combo box.
	 * 
	 * @param newItems the new items to be set
	 */
	public void setItems(String[] newItems) {
		this.items = newItems;
		combo.removeAll();
		combo.setItems(newItems);
	}
	
	public void setClasses(List<Class<?>> classes) {
		this.classes = classes;
	}
		
	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		String result = ClassTypeCellEditor.getJavaClassDialog(UIUtils.getShell(), classes);
		return result;
	}
	
	@Override
	protected void updateContents(Object value) {
		combo.setText(Misc.nvl(value,""));
	}
	
	@Override
	protected void fireApplyEditorValue() {
		if(combo.isFocusControl()) {
			return;
		}
		super.fireApplyEditorValue();
	}

	/*
	 * Applies the currently selected value and deactivates the cell editor
	 */
	private void applyEditorValueAndDeactivate() {
		// must set the selection before getting value
		selection = combo.getSelectionIndex();
		Object newValue = doGetValue();
		markDirty();
		boolean isValid = isCorrect(newValue);
		setValueValid(isValid);

		if (!isValid) {
			// Only format if the 'index' is valid
			if (items.length > 0 && selection >= 0 && selection < items.length) {
				// try to insert the current value into the error message.
				setErrorMessage(MessageFormat.format(getErrorMessage(), new Object[] { items[selection] }));
			} else {
				// Since we don't have a valid index, assume we're using an 'edit'
				// combo so format using its text value
				setErrorMessage(MessageFormat.format(getErrorMessage(), new Object[] { combo.getText() }));
			}
		}
		
		fireApplyEditorValue();
		deactivate();
	}
	
}
