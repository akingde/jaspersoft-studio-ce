/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.properties.view.validation.ValidationError;
import com.jaspersoft.studio.property.JRPropertySheetEntry;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.eclipse.util.Misc;

/*
 * @author Chicu Veaceslav
 */
public class DescriptorPropertyLabelProvider extends ItemPropertyBaseLabelProvider {
	
	private ADescriptor descriptor;

	public DescriptorPropertyLabelProvider(ADescriptor descriptor) {
		super();
		this.descriptor = descriptor;
	}

	@Override
	protected String getTextLabel(Object element) {
		if (element != null && element instanceof ItemProperty) {
			ItemProperty ip = (ItemProperty) element;
			if (ip.getValueExpression() != null)
				return Misc.nvl(ip.getValueExpression().getText());
			if (descriptor != null) {
				ItemPropertyDescription<?> ipDesc = descriptor.getDescription(ip.getName());
				if (ipDesc != null)
					return Misc.nvl(ip.getValue());
			}
			return Misc.nvl(ip.getValue());
		}
		return ""; //$NON-NLS-1$
	}
	
	@Override
	public Color getBackground(Object element) {
		if (element instanceof JRPropertySheetEntry) {
			JRPropertySheetEntry pse = (JRPropertySheetEntry) element;
			pse.getDescriptor().getId();
			Object[] vals = pse.getValues();
			if (vals.length == 1)
				element = vals[0];

			if (element == null || element instanceof ItemProperty)
				try {
					descriptor.validateItem((ItemProperty) element);
				} catch (ValidationError e) {
					if (e.getProps().contains(pse.getDescriptor().getId())) {
						if (e.isWarning())
							return ValidationError.WARN;
						return ValidationError.ERROR;
					}
				}
		}
		return super.getBackground(element);
	}

	@Override
	public String getToolTipText(Object element) {
		if (element instanceof JRPropertySheetEntry) {
			JRPropertySheetEntry pse = (JRPropertySheetEntry) element;
			pse.getDescriptor().getId();
			Object[] vals = pse.getValues();
			if (vals.length == 1)
				element = vals[0];

			if (element == null || element instanceof ItemProperty)
				try {
					descriptor.validateItem((ItemProperty) element);
				} catch (ValidationError e) {
					if (e.getProps().contains(pse.getDescriptor().getId()))
						return e.getMessage();
				}
		}
		return getText(element);
	}
}
