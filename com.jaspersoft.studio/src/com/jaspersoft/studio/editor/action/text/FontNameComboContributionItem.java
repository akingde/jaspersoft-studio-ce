/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.text;

import net.sf.jasperreports.engine.design.JRDesignStyle;

import com.jaspersoft.studio.preferences.fonts.utils.FontUtils;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Zoom contribution item
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class FontNameComboContributionItem extends APropertyComboContributionItem {
	public static final String ID = "com.jaspersoft.studio.editor.action.text.fontname";

	/**
	 * Constructs the action by specifying the report viewer to associate with the item.
	 * 
	 * @param viewer
	 *          the report viewer
	 */
	public FontNameComboContributionItem() {
		super(ID);
	}

	@Override
	protected void setComboItems() {
		combo.setItems(FontUtils.stringToItems(ModelUtils.getFontNames(model.getJasperConfiguration())));
	}

	@Override
	protected Object getDefaultValue() {
		return "";
	}

	protected Object getPropertyName() {
		return JRDesignStyle.PROPERTY_FONT_NAME;
	}

}
