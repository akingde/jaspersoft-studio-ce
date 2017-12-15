/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.editor.table;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;

import com.jaspersoft.studio.messages.Messages;

public abstract class TreeFieldEditor extends FieldEditor {

	/**
	 * The tree widget; <code>null</code> if none (before creation or after disposal).
	 */
	protected TreeViewer tree;

	public Tree getTable() {
		return tree.getTree();
	}

	/**
	 * The button box containing the Add, Remove, Up, and Down buttons; <code>null</code> if none (before creation or
	 * after disposal).
	 */
	private Composite buttonBox;

	/**
	 * The Add button.
	 */
	protected Button addButton;

	/**
	 * The Duplicate button.
	 */
	protected Button duplicateButton;

	/**
	 * The Remove button.
	 */
	protected Button removeButton;

	/**
	 * The Up button.
	 */
	protected Button upButton;

	/**
	 * The Down button.
	 */
	protected Button downButton;

	/**
	 * The selection listener.
	 */
	protected SelectionListener selectionListener;

	/**
	 * Creates a new table field editor
	 */
	protected TreeFieldEditor() {
	}

	/**
	 * Creates a table field editor.
	 * 
	 * @param name
	 *          the name of the preference this field editor works on
	 * @param labelText
	 *          the label text of the field editor
	 * @param columnNames
	 *          the names of columns
	 * @param columnWidths
	 *          the widths of columns
	 * @param parent
	 *          the parent of the field editor's control
	 * 
	 */
	protected TreeFieldEditor(String name, String labelText, Composite parent) {
		init(name, labelText);
		createControl(parent);
	}

	/**
	 * Creates the Add, Remove, Up, and Down button in the given button box.
	 * 
	 * @param box
	 *          the box for the buttons
	 */
	protected void createButtons(Composite box) {
		addButton = createPushButton(box, Messages.common_add);
		duplicateButton = createPushButton(box, Messages.common_duplicate);
		removeButton = createPushButton(box, Messages.common_delete);
		upButton = createPushButton(box, Messages.common_up);
		downButton = createPushButton(box, Messages.common_down);
	}

	/**
	 * Return the Add button.
	 * 
	 * @return the button
	 */
	protected Button getAddButton() {
		return addButton;
	}

	/**
	 * Return the Duplicate button.
	 * 
	 * @return the button
	 */
	protected Button getDuplicateButton() {
		return duplicateButton;
	}

	/**
	 * Return the Remove button.
	 * 
	 * @return the button
	 */
	protected Button getRemoveButton() {
		return removeButton;
	}

	/**
	 * Return the Up button.
	 * 
	 * @return the button
	 */
	protected Button getUpButton() {
		return upButton;
	}

	/**
	 * Return the Down button.
	 * 
	 * @return the button
	 */
	protected Button getDownButton() {
		return downButton;
	}

	/**
	 * Helper method to create a push button.
	 * 
	 * @param parent
	 *          the parent control
	 * @param key
	 *          the resource name used to supply the button's label text
	 * @return Button
	 */
	protected Button createPushButton(Composite parent, String key) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText(key);
		button.setFont(parent.getFont());
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		int widthHint = convertHorizontalDLUsToPixels(button, IDialogConstants.BUTTON_WIDTH);
		data.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);
		button.setLayoutData(data);
		button.addSelectionListener(getSelectionListener());
		return button;
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void adjustForNumColumns(int numColumns) {
		Control control = getLabelControl();
		((GridData) control.getLayoutData()).horizontalSpan = numColumns;
		((GridData) tree.getTree().getLayoutData()).horizontalSpan = numColumns - 1;
	}

	/**
	 * Creates a selection listener.
	 */
	public void createSelectionListener() {
		selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Widget widget = event.widget;
				if (widget == addButton) {
					addPressed();
				} else if (widget == duplicateButton) {
					duplicatePressed();
				} else if (widget == removeButton) {
					removePressed();
				} else if (widget == upButton) {
					upPressed();
				} else if (widget == downButton) {
					downPressed();
				} else if (widget == tree.getTree()) {
					selectionChanged();
				}
			}
		};
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		Control control = getLabelControl(parent);
		GridData gd = new GridData();
		gd.horizontalSpan = numColumns;
		control.setLayoutData(gd);

		Composite composite = new Composite(parent, SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		gridData.minimumWidth = 500;
		gridData.heightHint = 500;
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(2, false));

		tree = getTreeControl(composite);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = numColumns - 1;
		gd.grabExcessHorizontalSpace = true;
		tree.getTree().setLayoutData(gd);

		buttonBox = getButtonBoxControl(composite);
		gd = new GridData();
		gd.verticalAlignment = GridData.BEGINNING;
		buttonBox.setLayoutData(gd);
	}

	/**
	 * Returns this field editor's button box containing the Add, Remove, Up, and Down button.
	 * 
	 * @param parent
	 *          the parent control
	 * @return the button box
	 */
	public Composite getButtonBoxControl(Composite parent) {
		if (buttonBox == null) {
			buttonBox = new Composite(parent, SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			buttonBox.setLayout(layout);
			createButtons(buttonBox);
			buttonBox.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					addButton = null;
					duplicateButton = null;
					removeButton = null;
					upButton = null;
					downButton = null;
					buttonBox = null;
				}
			});

		} else {
			checkParent(buttonBox, parent);
		}

		selectionChanged();
		return buttonBox;
	}
	
	public TreeViewer getTreeControl(Composite parent) {
		return getTreeControl(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	}

	/**
	 * Returns this field editor's table control.
	 * 
	 * @param parent
	 *          the parent control
	 * @return the table control
	 */
	public TreeViewer getTreeControl(Composite parent, int style) {
		if (tree == null) {
			tree = new TreeViewer(parent, style);
			setupTree(tree);
			ColumnViewerToolTipSupport.enableFor(tree, ToolTip.NO_RECREATE);
			tree.getTree().addSelectionListener(getSelectionListener());
			tree.getTree().addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					tree = null;
				}
			});
			tree.addDoubleClickListener(new IDoubleClickListener() {

				@Override
				public void doubleClick(DoubleClickEvent event) {
					handleTableDoubleClick();
				}
			});
		}
		return tree;
	}

	protected abstract void setupTree(TreeViewer tree);

	protected void handleTableDoubleClick() {
	}

	protected boolean isFieldEditable(int col, int row) {
		return true;
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	public int getNumberOfControls() {
		return 2;
	}

	/**
	 * Returns this field editor's selection listener. The listener is created if necessary.
	 * 
	 * @return the selection listener
	 */
	protected SelectionListener getSelectionListener() {
		if (selectionListener == null) {
			createSelectionListener();
		}
		return selectionListener;
	}

	/**
	 * Returns this field editor's shell.
	 * <p>
	 * This method is internal to the framework; subclassers should not call this method.
	 * </p>
	 * 
	 * @return the shell
	 */
	protected Shell getShell() {
		if (addButton == null) {
			return null;
		}
		return addButton.getShell();
	}

	/**
	 * Notifies that the Add button has been pressed.
	 */
	protected abstract void addPressed();

	/**
	 * Notifies that the Add button has been pressed.
	 */
	protected abstract void duplicatePressed();

	/**
	 * Notifies that the Remove button has been pressed.
	 */
	protected abstract void removePressed();

	/**
	 * Notifies that the Up button has been pressed.
	 */
	protected abstract void upPressed();

	/**
	 * Notifies that the Down button has been pressed.
	 */
	protected abstract void downPressed();

	/**
	 * Invoked when the selection in the list has changed.
	 * 
	 * <p>
	 * The default implementation of this method utilizes the selection index and the size of the list to toggle the
	 * enabled state of the up, down and remove buttons.
	 * </p>
	 * 
	 * <p>
	 * Subclasses may override.
	 * </p>
	 * 
	 */
	protected void selectionChanged() {
		StructuredSelection sel = (StructuredSelection) tree.getSelection();
		if (duplicateButton != null)
			duplicateButton.setEnabled(!sel.isEmpty());
		if (removeButton != null)
			removeButton.setEnabled(!sel.isEmpty() && isRemovable(sel));
		if (upButton != null)
			upButton.setEnabled(!sel.isEmpty() && isSortable(sel) && canGoUp(sel));
		if (downButton != null)
			downButton.setEnabled(!sel.isEmpty() && isSortable(sel) && canGoDown(sel));
	}

	protected boolean isRemovable(StructuredSelection sel) {
		return true;
	}

	protected boolean canGoUp(StructuredSelection sel) {
		return true;
	}

	protected boolean canGoDown(StructuredSelection sel) {
		return true;
	}

	protected boolean isSortable(StructuredSelection sel) {
		return true;
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	public void setFocus() {
		if (tree != null)
			tree.getTree().setFocus();
	}

	/*
	 * @see FieldEditor.setEnabled(boolean,Composite).
	 */
	public void setEnabled(boolean enabled, Composite parent) {
		super.setEnabled(enabled, parent);
		getTreeControl(parent).getTree().setEnabled(enabled);
		if (addButton != null)
			addButton.setEnabled(enabled);
		if (duplicateButton != null)
			duplicateButton.setEnabled(enabled);
		if (removeButton != null)
			removeButton.setEnabled(enabled);
		if (upButton != null)
			upButton.setEnabled(enabled);
		if (downButton != null)
			downButton.setEnabled(enabled);
	}

}
