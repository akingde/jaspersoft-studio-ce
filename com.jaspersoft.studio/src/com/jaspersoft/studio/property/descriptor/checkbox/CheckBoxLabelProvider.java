/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.checkbox;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.NullEnum;

/*
 * @author Chicu Veaceslav
 * 
 */
public class CheckBoxLabelProvider extends LabelProvider {
	private NullEnum canBeNull;
	private boolean showImage = true;

	public CheckBoxLabelProvider(NullEnum canBeNull) {
		this(canBeNull, true);
	}

	public CheckBoxLabelProvider(NullEnum canBeNull, boolean showImage) {
		super();
		this.canBeNull = canBeNull;
		this.showImage = showImage;
	}

	@Override
	public Image getImage(Object element) {
		if (!showImage)
			return null;
		if (element == null || element instanceof Boolean) {
			return getCellEditorImage((Boolean) element);
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		return element == null || !(element instanceof Boolean) ? canBeNull.getName() : element.toString(); // $NON-NLS-1$
	}

	/**
	 * To be used by LabelProviders that whant to display a checked/unchecked icon
	 * for the CheckboxCellEditor that does not have a Control.
	 * 
	 * @param cellModifier
	 *            The ICellModifier for the CellEditor to provide the value
	 * @param element
	 *            The current element
	 * @param property
	 *            The property the cellModifier should return the value from
	 */
	public Image getCellEditorImage(ICellModifier cellModifier, Object element, String property) {
		Boolean value = (Boolean) cellModifier.getValue(element, property);
		return getCellEditorImage(value);
	}

	/**
	 * returns an checked checkbox image if value if true and an unchecked checkbox
	 * image if false
	 * 
	 * @param value
	 *            the value to get the cooresponding image for
	 * @param disabled
	 *            determines if the image should be disabled or not
	 * @return an checked checkbox image if value if true and an unchecked checkbox
	 *         image if false
	 * 
	 */
	public Image getCellEditorImage(Boolean value) {
		Image image = null;
		if (value == null)
			image = JaspersoftStudioPlugin.getInstance().getImage("icons/CheckboxCellEditorHelper-null.16x16.png"); //$NON-NLS-1$
		else if (value.booleanValue())
			image = JaspersoftStudioPlugin.getInstance().getImage("icons/CheckboxCellEditorHelper-checked.16x16.png"); //$NON-NLS-1$
		else
			image = JaspersoftStudioPlugin.getInstance().getImage("icons/CheckboxCellEditorHelper-unchecked.16x16.png"); //$NON-NLS-1$
		return image;
	}
}
