/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.text;

import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Zoom contribution item
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class FontSizeComboContributionItem extends APropertyComboContributionItem {
	public static final String ID = "com.jaspersoft.studio.editor.action.text.fontsize";

	/**
	 * Constructs the action by specifying the report viewer to associate with the item.
	 * 
	 * @param viewer
	 *          the report viewer
	 */
	public FontSizeComboContributionItem() {
		super(ID);
	}

	@Override
	protected Control createControl(Composite parent) {
		Control ctrl = super.createControl(parent);
		ctrl.setSize(70, ctrl.getSize().y);
		return ctrl;
	}

	@Override
	protected void setComboItems() {
		combo.setItems(ModelUtils.getFontSizes());
	}

	@Override
	protected Object getDefaultValue() {
		return new Integer(10);
	}

	protected Object getPropertyName() {
		return JRDesignStyle.PROPERTY_FONT_SIZE;
	}

}
