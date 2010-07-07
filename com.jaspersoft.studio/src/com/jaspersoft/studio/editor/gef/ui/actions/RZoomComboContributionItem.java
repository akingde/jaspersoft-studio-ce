/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.ui.actions;

import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IPartService;

// TODO: Auto-generated Javadoc
/**
 * The Class RZoomComboContributionItem.
 * 
 * @author Chicu Veaceslav
 */
public class RZoomComboContributionItem extends ZoomComboContributionItem {
	
	/** The combo. */
	private Combo combo;

	/**
	 * Instantiates a new r zoom combo contribution item.
	 * 
	 * @param partService
	 *          the part service
	 */
	public RZoomComboContributionItem(IPartService partService) {
		super(partService);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.actions.ZoomComboContributionItem#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createControl(Composite parent) {
		combo = (Combo) super.createControl(parent);
		return combo;
	}

	/**
	 * Sets the enabled.
	 * 
	 * @param enabled
	 *          the new enabled
	 */
	public void setEnabled(boolean enabled) {
		if (combo != null)
			combo.setEnabled(enabled);
	}

}
