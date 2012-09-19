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

package com.jaspersoft.studio.property.section.text;

import net.sf.jasperreports.engine.base.JRBaseParagraph;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MParagraph;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ParagraphSection extends AbstractRealValueSection {
	

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, "Paragraph", false, 2);

		createWidget4Property(parent, JRBaseParagraph.PROPERTY_LINE_SPACING);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_LINE_SPACING_SIZE);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_FIRST_LINE_INDENT);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_LEFT_INDENT);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_RIGHT_INDENT);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_SPACING_BEFORE);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_SPACING_AFTER);
		createWidget4Property(parent, JRBaseParagraph.PROPERTY_TAB_STOP_WIDTH);
	}

	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MTextElement) {
			MParagraph paragraph = (MParagraph) md.getPropertyValue("paragraph");
			return paragraph;
		}
		return md;
	}

}
