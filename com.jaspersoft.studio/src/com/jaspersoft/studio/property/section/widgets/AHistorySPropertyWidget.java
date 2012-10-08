/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;

public abstract class AHistorySPropertyWidget extends ASPropertyWidget {
	public static final String HIST_PREFIX = "asproperty.";
	protected AutoCompleteField autocomplete;

	public AHistorySPropertyWidget(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	protected abstract Text getTextControl();

	protected Object getHistoryKey() {
		return HIST_PREFIX + pDescriptor.getId();
	}

	@Override
	protected void handleFocusLost() {
		if (autocomplete != null) {
			autocomplete.setProposals(InputHistoryCache.get(null));
			InputHistoryCache.put(getHistoryKey(), getTextControl().getText());
		}
		super.handleFocusLost();
	}

	@Override
	protected void handleFocusGained() {
		if (autocomplete != null)
			autocomplete.setProposals(InputHistoryCache.get(getHistoryKey()));
		super.handleFocusGained();
	}

}
