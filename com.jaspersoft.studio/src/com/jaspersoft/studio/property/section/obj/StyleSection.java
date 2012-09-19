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
package com.jaspersoft.studio.property.section.obj;

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

public class StyleSection extends AbstractRealValueSection {

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		if (!(getElement() instanceof MConditionalStyle))
			createWidget4Property(parent, JRDesignStyle.PROPERTY_NAME);

		createWidget4Property(parent, JRDesignStyle.PROPERTY_PARENT_STYLE);

		createWidget4Property(parent, JRDesignStyle.PROPERTY_BLANK_WHEN_NULL);

		if (!(getElement() instanceof MConditionalStyle))
			createWidget4Property(parent, JRDesignStyle.PROPERTY_DEFAULT);

		createWidget4Property(parent, JRDesignStyle.PROPERTY_PATTERN);

		createWidget4Property(parent, JRBaseStyle.PROPERTY_FILL);
		createWidget4Property(parent, JRBaseStyle.PROPERTY_RADIUS);
		createWidget4Property(parent, JRBaseStyle.PROPERTY_SCALE_IMAGE);
	}
}
