/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics;

import java.net.URL;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.PlatformUI;

/**
 * A field editor for a boolean type preference. It support url reference
 * in the label, that will be opened with the default browser
 * 
 * @author Orlandin Marco 
 */
public class BooleanLinkFieldEditor extends FieldEditor {

	/**
	 * The previously selected, or "before", value.
	 */
	private boolean wasSelected;

	/**
	 * The checkbox control
	 */
	private Button checkBox = null;
	
	/**
	 * The label control
	 */
	private Link linkLabel = null;
	
	/**
	 * The grid data of the container that contains both the checkbox and the label
	 */
	private GridData containerGridData = null;

	/**
	 * Creates a boolean field editor 
	 * 
	 * @param name  the name of the preference this field editor works on
	 * @param label the label text of the field editor, can contains an <a href=".."> tag to provide and url reference
	 * @param parent the parent of the field editor's control
	 */
	public BooleanLinkFieldEditor(String name, String label, Composite parent) {
		init(name, label);
		createControl(parent);
	}

	/**
	 * Create both the checkbox and the label, set their layout data and
	 * initialize the text area
	 * 
	 */
	@Override
	protected void doFillIntoGrid(Composite parent, int numColumns) {
		GridData checkBoxData = new GridData();
		checkBoxData.verticalAlignment = SWT.TOP;
		checkBox = getChangeControl(parent);
		checkBox.setLayoutData(checkBoxData);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 500;
		getLinkControl(parent).setLayoutData(gd);
		String text = getLabelText();
		if (text != null){
			linkLabel.setText(text);
		}
	}

	/**
	 * Returns the control responsible for displaying this field editor's label.
	 * This method can be used to set a tooltip for a
	 * <code>BooleanFieldEditor</code>. Note that the normal pattern of
	 * <code>getLabelControl(parent).setToolTipText(tooltipText)</code> does not
	 * work for boolean field editors, as it can lead to duplicate text (see bug
	 * 259952).
	 * 
	 * @param parent
	 *            the parent composite
	 * @return the control responsible for displaying the label
	 * 
	 * @since 3.5
	 */
	public Control getDescriptionControl(Composite parent) {
		return getLinkControl(parent);
	}

	/**
	 * Returns the checkbox button for this field editor.
	 * 
	 * @param parent The Composite to create the receiver in.
	 * 
	 * @return the checkbox button
	 */
	protected Button getChangeControl(Composite parent) {
		if (checkBox == null) {
			checkBox = new Button(parent, SWT.CHECK);
			checkBox.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					boolean isSelected = checkBox.getSelection();
					valueChanged(wasSelected, isSelected);
					wasSelected = isSelected;
				}
			});
			checkBox.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					checkBox = null;
				}
			});
		} else {
			checkParent(checkBox, parent);
		}
		return checkBox;
	}
	
	/**
	 * Returns the link label for this field editor.
	 * 
	 * @param parent The Composite to create the receiver in.
	 * 
	 * @return the link label
	 */
	protected Link getLinkControl(Composite parent){
		if (linkLabel == null) {
			linkLabel = new Link(parent, SWT.WRAP);
			linkLabel.setFont(parent.getFont());
			linkLabel.addSelectionListener(new SelectionAdapter() {
				 @Override
	        public void widgetSelected(SelectionEvent e) {
		        try {
		        	PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(e.text));
		        } catch (Exception ex) {
		        	ex.printStackTrace();
		        } 
	        }
			});
			linkLabel.addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent event) {
					linkLabel = null;
				}
			});
		} else {
			checkParent(linkLabel, parent);
		}
		return linkLabel;
	}

	/**
	 * Informs this field editor's listener, if it has one, about a change to
	 * the value (<code>VALUE</code> property) provided that the old and new
	 * values are different.
	 * 
	 * @param oldValue the old value
	 * @param newValue the new value
	 */
	protected void valueChanged(boolean oldValue, boolean newValue) {
		setPresentsDefaultValue(false);
		if (oldValue != newValue) {
			fireStateChanged(VALUE, oldValue, newValue);
		}
	}

	/**
	 * Return the number number of control provided by this field editor.
	 * Since all the controls are contained into a single composite created
	 * by the editor itself, the number of controls is one 
	 */
	@Override
	public int getNumberOfControls() {
		return 1;
	}

	/**
	 * Adjust the layout data of the main container of this editor
	 * to its parent. Since there is only one controls it fills all
	 * the available horizontal area
	 */
	@Override
	protected void adjustForNumColumns(int numColumns) {
		containerGridData.horizontalSpan = numColumns;
	}
	
	/**
	 * Create the main container that will contains both
	 * the checkbox and the link controls.
	 */
	@Override
  protected void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		containerGridData = new GridData(GridData.FILL_HORIZONTAL);
		container.setLayoutData(containerGridData);
    GridLayout containerLayout = new GridLayout(2, false);
    containerLayout.marginWidth = 0;
    containerLayout.marginHeight = 0;
    container.setLayout(containerLayout);
    doFillIntoGrid(container, containerLayout.numColumns);
	}
	
	@Override
	protected void doLoad() {
		if (checkBox != null) {
			boolean value = getPreferenceStore().getBoolean(getPreferenceName());
			checkBox.setSelection(value);
			wasSelected = value;
		}
	}

	@Override
	protected void doLoadDefault() {
		if (checkBox != null) {
			boolean value = getPreferenceStore().getDefaultBoolean(getPreferenceName());
			checkBox.setSelection(value);
			wasSelected = value;
		}
	}

	@Override
	protected void doStore() {
		getPreferenceStore().setValue(getPreferenceName(), checkBox.getSelection());
	}
	
	@Override
	public void setFocus() {
		if (checkBox != null) {
			checkBox.setFocus();
		}
	}

	@Override
	public void setLabelText(String text) {
		super.setLabelText(text);
		if (linkLabel != null) {
			linkLabel.setText(text);
		}
	}
	
	@Override
	public void setEnabled(boolean enabled, Composite parent) {
		getLinkControl(parent).setEnabled(enabled);
		getChangeControl(parent).setEnabled(enabled);
	}
}
