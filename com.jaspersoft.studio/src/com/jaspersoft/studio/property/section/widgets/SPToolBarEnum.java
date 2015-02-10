/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.IEnumDescriptors;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPToolBarEnum<T extends IPropertyDescriptor & IEnumDescriptors> extends ASPropertyWidget<T> {
	private ToolItem[] toolItems;
	private ToolBar toolBar;

	public SPToolBarEnum(Composite parent, AbstractSection section, T pDescriptor, Image[] images, boolean showText) {
		super(parent, section, pDescriptor);
		setupImages(pDescriptor, images, showText);
	}

	public SPToolBarEnum(Composite parent, AbstractSection section, T pDescriptor, Image[] images) {
		this(parent, section, pDescriptor, images, true);
	}

	private void setupImages(T pDescriptor, Image[] images, boolean showText) {
		if (images != null)
			for (int i = 0, j = 0; i < toolItems.length; i++, j++) {
				// if (i == 0 && pDescriptor.getType() != NullEnum.NOTNULL) {
				// j--;
				// continue;
				// }
				if (images.length <= j)
					continue;
				toolItems[i].setImage(images[j]);
				if (!showText)
					toolItems[i].setText("");
			}
	}

	@Override
	public Control getControl() {
		return toolBar;
	}

	protected void createComponent(Composite parent) {
		toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.RIGHT);

		String[] enumItems = pDescriptor.getEnumItems();
		int j = 0;
		if (pDescriptor.getType() != NullEnum.NOTNULL) {
			toolItems = new ToolItem[enumItems.length - 1];
			j++;
		} else {
			toolItems = new ToolItem[enumItems.length];
		}
		for (int i = 0; i < toolItems.length; i++) {
			final int index = i;
			toolItems[i] = new ToolItem(toolBar, SWT.CHECK);
			toolItems[i].setText(enumItems[index + j]);
			toolItems[i].setToolTipText(enumItems[index + j]);
			toolItems[i].addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (refresh)
						return;
					// eventually deselect other selected items. It is possible use SWT.RADIO as toolitem
					// stylebit but this sometimes dosen't highlight the selected item
					for (ToolItem item : toolItems) {
						if (item != e.widget) {
							item.setSelection(false);
						}
					}
					int val = index;
					if (pDescriptor.getType() != NullEnum.NOTNULL)
						val++;
					propertyChange(section, pDescriptor.getId(), toolItems[index].getSelection() ? val : null);
				}
			});
			// bindToHelp(pd, toolItems[i].getControl());
		}
	}

	public void propertyChange(AbstractSection section, Object property, Integer value) {
		section.changeProperty(property, value);
	}

	private boolean refresh = false;

	public void setData(APropertyNode pnode, Object b) {
		refresh = true;
		toolBar.setEnabled(pnode.isEditable());
		int index = 0;
		if (b != null)
			index = ((Number) b).intValue();
		if (pDescriptor.getType() != NullEnum.NOTNULL)
			index--;
		for (int i = 0; i < toolItems.length; i++)
			toolItems[i].setSelection(i == index);
		refresh = false;
	}
}
