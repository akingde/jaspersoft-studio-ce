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
package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGraphicElement;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.pen.PenPropertyDescriptor;

/**
 * The Class MGeneric.
 */
public abstract class MGraphicElementLinePen extends MGraphicElement implements IGraphicElement {

	public static final String LINE_PEN = "LinePen"; //$NON-NLS-1$

	public MGraphicElementLinePen() {
		super();
	}

	public MGraphicElementLinePen(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	public MGraphicElementLinePen(ANode parent, JRDesignElement jrLine, int newIndex) {
		super(parent, jrLine, newIndex);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		PenPropertyDescriptor linePenD = new PenPropertyDescriptor(LINE_PEN, Messages.MGraphicElementLinePen_line_pen);
		linePenD.setDescription(Messages.MGraphicElementLinePen_line_pen_description);
		desc.add(linePenD);
		linePenD.setCategory(Messages.MGraphicElementLinePen_graphic_category);
	}

	private MLinePen linePen;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		// pen
		if (id.equals(LINE_PEN)) {
			JRDesignGraphicElement jrGraphicElement = (JRDesignGraphicElement) getValue();
			if (linePen == null) {
				linePen = new MLinePen(jrGraphicElement.getLinePen());
				linePen.getPropertyChangeSupport().addPropertyChangeListener(this);
			}
			return linePen;
		}
		return super.getPropertyValue(id);
	}

}
