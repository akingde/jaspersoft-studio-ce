/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
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

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * @author Chicu Veaceslav (schicu@jaspersoft.com)
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

	private String[] fonts;

	@Override
	protected void setComboItems() {
		JasperReportsConfiguration jConfig = model.getJasperConfiguration();
		if (jConfig != null) {
			String[] newfonts = jConfig.getFontList();
			if (newfonts != fonts) {
				fonts = newfonts;
				combo.setItems(fonts);
			}

		}
	}

	@Override
	protected Object getDefaultValue() {
		return "";
	}

	protected Object getPropertyName() {
		return JRDesignStyle.PROPERTY_FONT_NAME;
	}

}
