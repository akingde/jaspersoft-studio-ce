/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.UUID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ImageUtils;

public class SPBooleanToggle extends ASPropertyWidget<CheckBoxPropertyDescriptor> {
	private ToolItem cmb3Bool;

	private Composite parent;
	
	private String imageId = null;

	public SPBooleanToggle(Composite parent, AbstractSection section, CheckBoxPropertyDescriptor pDescriptor, Image image) {
		super(parent, section, pDescriptor);
		if (image != null) {
			cmb3Bool.setImage(image);
			imageId = UUID.randomUUID().toString();
			Image cachedImage = ResourceManager.getImage(imageId);
			if (cachedImage == null || cachedImage.isDisposed()) {
				ResourceManager.addImage(imageId, image);
			}
		}
	}

	@Override
	public Control getControl() {
		return parent;
	}

	@Override
	public Control getControlToBorder() {
		return parent;
	}

	protected PaintListener getPaintControlListener() {
		return DefaultWidgetsHighlighters.getWidgetForType(ToolItem.class);
	}

	public void createComponent(Composite parent) {
		this.parent = parent;
		cmb3Bool = new ToolItem((ToolBar) parent, SWT.CHECK);
		cmb3Bool.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				section.changeProperty(pDescriptor.getId(), new Boolean(cmb3Bool.getSelection()));
			}
		});
		cmb3Bool.setToolTipText(pDescriptor.getDescription());
	}
	
	@Override
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		if (imageId != null) {
			if (elementValue == null) {
				Image grayImage = ResourceManager.getImage(imageId+"greyScale");
				if (grayImage == null) {
					Image originalImage = ResourceManager.getImage(imageId);
					ImageData grayData = ImageUtils.createGrayImage(originalImage);
					if (grayData != null) {
						grayImage = new Image(originalImage.getDevice(), grayData);
						ResourceManager.addImage(imageId+"greyScale", grayImage);
					}
				}
				if (grayImage != null) {
					cmb3Bool.setImage(grayImage);
				}
			} else {
				cmb3Bool.setImage(ResourceManager.getImage(imageId));
			}
		}
		if (elementValue == null) {
			cmb3Bool.setToolTipText(Messages.common_inherited_attribute + pDescriptor.getDescription());
		} else {
			cmb3Bool.setToolTipText(pDescriptor.getDescription());
		}
		setData(pnode, resolvedValue);
	}

	public void setData(APropertyNode pnode, Object b) {
		createContextualMenu(pnode);
		cmb3Bool.setEnabled(pnode.isEditable());
		boolean v = false;
		if (b != null)
			v = (Boolean) b;
		cmb3Bool.setSelection(v);
	}
}
