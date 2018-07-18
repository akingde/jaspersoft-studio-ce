/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.UUID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.IEnumDescriptors;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ImageUtils;

public class SPToolBarEnum<T extends IPropertyDescriptor & IEnumDescriptors> extends ASPropertyWidget<T> {
	
	private ToolItem[] toolItems;
	
	private ToolBar toolBar;
	
	private boolean refresh = false;
	
	private static final String IMAGE_KEY = "imageKey";
	
	private static final String GREY_IMAGE_ID_KEY = "greyImageId";

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
				if (images.length <= j)
					continue;
				toolItems[i].setImage(images[j]);
				if (!showText) {
					toolItems[i].setData(IMAGE_KEY, images[i]);
					toolItems[i].setData(GREY_IMAGE_ID_KEY, UUID.randomUUID().toString() + "greyScale");
					toolItems[i].setText("");
				}
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
	
	@Override
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		for (int i = 0; i < toolItems.length; i++) {
			ToolItem currentItem = toolItems[i];
			Image originalImage = (Image)currentItem.getData(IMAGE_KEY);
			if (originalImage != null) {
				if (elementValue == null) {
					String grayImageKey = (String) currentItem.getData(GREY_IMAGE_ID_KEY);
					Image grayImage = ResourceManager.getImage(grayImageKey);
					if (grayImage == null) {
						ImageData grayData = ImageUtils.createGrayImage(originalImage);
						if (grayData != null) {
							grayImage = new Image(originalImage.getDevice(), grayData);
							ResourceManager.addImage(grayImageKey, grayImage);
						}
					}
					if (grayImage != null) {
						currentItem.setImage(grayImage);
					}
				} else {
					currentItem.setImage(originalImage);
				}
			}
			if (elementValue == null) {
				currentItem.setToolTipText(Messages.common_inherited_attribute + pDescriptor.getDescription());
				if (getLabel() != null) {
					getLabel().setToolTipText(Messages.common_inherited_attribute + pDescriptor.getDescription());
				}
			} else {
				currentItem.setToolTipText(pDescriptor.getDescription());
				if (getLabel() != null) {
					getLabel().setToolTipText(pDescriptor.getDescription());
				}
			}
		}
		setData(pnode, resolvedValue);
	}

	public void setData(APropertyNode pnode, Object b) {
		createContextualMenu(pnode);
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
