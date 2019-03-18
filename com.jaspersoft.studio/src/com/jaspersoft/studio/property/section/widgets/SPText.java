/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.contexts.IContextActivation;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;

import net.sf.jasperreports.eclipse.util.Misc;

public class SPText<T extends IPropertyDescriptor> extends AHistorySPropertyWidget<T> {
	
	protected class CustomText extends Text {
		
		public CustomText(Composite parent, int style) {
			super(parent, style);
		}

		@Override
		public Point computeSize(int wHint, int hHint, boolean changed) {
			return computeTextSize(wHint, hHint, changed);
		}
		
		public Point standardComputeSize(int wHint, int hHint, boolean changed) {
			return super.computeSize(wHint, hHint, changed);
		}
		
		@Override
		protected void checkSubclass() {
		}
		
		/**
		 * Assuring that the width has an hint in case of grid layout, doing this will force the
		 * sptext to not grow too much depending on the text content 
		 */
		@Override
		public void setLayoutData(Object layoutData) {
			Object newData = layoutData;
			if (newData instanceof GridData) {
				GridData newGridData = (GridData)newData;
				if (newGridData.grabExcessHorizontalSpace && newGridData.horizontalAlignment == SWT.FILL && newGridData.widthHint == SWT.DEFAULT) {
					int w = getCharWidth(this) * 15;
					if (w > 50) w = 50;
					newGridData.widthHint = w;
				}
			}
			super.setLayoutData(newData);
		}
		
	}
	
	protected CustomText ftext;
	protected APropertyNode pnode;
	protected String savedValue;
	// Flag used to overcome the problem of focus events in Mac OS X
	// - JSS Bugzilla 42999
	// - Eclipse Bug 383750
	// It makes sense only on E4 platform and Mac OS X operating systems.
	// DO NOT USE THIS FLAG FOR OTHER PURPOSES.
	private boolean editHappened = false;
	protected IContextActivation context;
	/**
	 * Flag used to avoid that the handletextchanged is called twice when CR is pressed (because the CR made the control
	 * to loose the focus)
	 */
	protected boolean disableFocusLost = false;

	public SPText(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return ftext;
	}

	@Override
	protected Text getTextControl() {
		return ftext;
	}
	
	protected int getStyle() {
		int style = SWT.NONE;
		if (pDescriptor instanceof JSSTextPropertyDescriptor)
			style = ((JSSTextPropertyDescriptor) pDescriptor).getStyle();
		return style;
	}
	
	protected Point computeTextSize(int wHint, int hHint, boolean changed) {
		return ftext.standardComputeSize(wHint, hHint, changed);
	}

	protected void createComponent(Composite parent) {
		ftext = new CustomText(parent, getStyle());
		autocomplete = new CustomAutoCompleteField(ftext, new TextContentAdapter(), InputHistoryCache.get(getHistoryKey()));
		if (UIUtil.isMacAndEclipse4()) {
			ftext.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					editHappened = true;
				}
			});
		}
		ftext.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR) {
					disableFocusLost = true;
					handleTextChanged(section, pDescriptor.getId(), ftext.getText());
					disableFocusLost = false;
				}
				if (e.keyCode == SWT.ESC) {
					if (!autocomplete.isPopupJustClosed()) {
						autocomplete.setEnabled(false);
						ftext.setText(savedValue);
						autocomplete.setEnabled(true);
					}
				}
				autocomplete.resetPopupJustClosed();
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		ftext.setToolTipText(pDescriptor.getDescription());

		setWidth(parent, 15);
	}

	private void setWidth(Composite parent, int chars) {
		int w = getCharWidth(ftext) * chars;
		if (w > 50) w = 50;
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			rd.width = w;
			ftext.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData rd = new GridData(GridData.FILL_HORIZONTAL);
			rd.minimumWidth = w;
			rd.widthHint = w;
			ftext.setLayoutData(rd);
		}
	}

	@Override
	protected void handleFocusLost() {
		String currentValue = getCurrentValue();
		if (UIUtil.isMacAndEclipse4() && !editHappened) {
			ftext.setText(Misc.nvl(currentValue));
		}
		if (!disableFocusLost) {
			if (!(currentValue != null && currentValue.equals(ftext.getText())))
				handleTextChanged(section, pDescriptor.getId(), ftext.getText());
			super.handleFocusLost();
		}
		if (UIUtil.isMacAndEclipse4()) {
			editHappened = false;
		}
	}

	protected String getCurrentValue() {
		Object v = section.getElement().getPropertyValue(pDescriptor.getId());
		if (v instanceof String)
			return (String) v;
		return null;
	}

	protected void handleTextChanged(final AbstractSection section, final Object property, String text) {
		section.changeProperty(property, text);
	}

	@Override
	public void setData(APropertyNode pnode, Object b) {
		createContextualMenu(pnode);
		this.pnode = pnode;
		ftext.setEnabled(pnode.isEditable());
		if (b != null) {
			savedValue = b.toString();
			int oldpos = ftext.getLocation().x;
			ftext.setText(b.toString());
			if (b.toString().length() >= oldpos)
				ftext.setSelection(oldpos, oldpos);
		} else {
			savedValue = "";
			ftext.setText("");
		}
	}

}
