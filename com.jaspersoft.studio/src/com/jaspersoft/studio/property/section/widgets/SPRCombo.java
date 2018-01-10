/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class SPRCombo extends ASPropertyWidget<RComboBoxPropertyDescriptor> {

	private Combo combo;

	private APropertyNode pnode;
	
	private Object b;

	private boolean refreshing = false;

	public SPRCombo(Composite parent, AbstractSection section, RComboBoxPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return combo;
	}

	protected void createComponent(Composite parent) {
		combo = section.getWidgetFactory().createCombo(parent, SWT.READ_ONLY);

		combo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!refreshing)
					changeProperty(section, combo.getItem(combo.getSelectionIndex()));
			}
		});
		combo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (!refreshing)
					changeProperty(section, combo.getText());
			}
		});
		combo.setToolTipText(pDescriptor.getDescription());
	}

	protected void changeProperty(AbstractSection section, Object value) {
		section.changeProperty(pDescriptor.getId(), value);
	}

	public void refresh() {
		UIUtils.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				setData(pnode, b);
			}
		});
	}

	public void setData(APropertyNode pnode, Object b) {
		createContextualMenu(pnode);
		if (pnode != null && pnode.getDescriptors() != null)
			// I see sometimes pdescriptor is not the same, because in some cases we have pdescriptor static or not,
			// better is to setup the right one, if we want to get items from there
			for (IPropertyDescriptor pd : pnode.getDescriptors()) {
				if (pDescriptor.getId().equals(pd.getId()) && pd instanceof RComboBoxPropertyDescriptor)
					pDescriptor = (RComboBoxPropertyDescriptor) pd;
			}
		this.pnode = pnode;
		this.b = b;
		refreshing = true;
		combo.setItems(pDescriptor.getItems());
		String str = (String) b;
		String[] items = combo.getItems();
		int selection = -1;
		for (int i = 0; i < items.length; i++) {
			if (items[i].equals(str)) {
				selection = i;
				break;
			}
		}
		if (b != null) {
			if (selection == -1 && pDescriptor.getItems().length > 0) {
				str = Misc.nvl(str);
				int oldpos = str.length();
				// If the value of the element is not in the combo then it is added in the selections
				// list in the first position, then selected
				List<String> newItems = new ArrayList<String>();
				newItems.add(str);
				newItems.addAll(Arrays.asList(items));
				combo.setItems(newItems.toArray(new String[newItems.size()]));
				if (newItems.size() > 0)
					combo.select(0);
				combo.setSelection(new Point(oldpos, oldpos));
			} else {
				combo.select(selection);
			}
		}
		refreshing = false;
	}
}
