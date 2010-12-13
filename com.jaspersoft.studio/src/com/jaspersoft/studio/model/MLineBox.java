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

import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.base.JRBaseLineBox;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.pen.PenPropertyDescriptor;

public class MLineBox extends APropertyNode implements IPropertySource {

	public MLineBox(JRLineBox lineBox) {
		super();
		setValue(lineBox);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		IntegerPropertyDescriptor paddingD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_PADDING, Messages.MLineBox_padding);
		paddingD.setDescription(Messages.MLineBox_padding_description);
		desc.add(paddingD);

		IntegerPropertyDescriptor paddingLeftD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_LEFT_PADDING,
				Messages.MLineBox_left_padding);
		paddingLeftD.setDescription(Messages.MLineBox_left_padding_description);
		desc.add(paddingLeftD);

		IntegerPropertyDescriptor paddingRightD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_RIGHT_PADDING,
				Messages.MLineBox_right_padding);
		paddingRightD.setDescription(Messages.MLineBox_right_padding_description);
		desc.add(paddingRightD);

		IntegerPropertyDescriptor paddingTopD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_TOP_PADDING,
				Messages.MLineBox_top_padding);
		paddingTopD.setDescription(Messages.MLineBox_top_padding_description);
		desc.add(paddingTopD);

		IntegerPropertyDescriptor paddingBottomD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_BOTTOM_PADDING,
				Messages.MLineBox_bottom_padding);
		paddingBottomD.setDescription(Messages.MLineBox_bottom_padding_description);
		desc.add(paddingBottomD);

		paddingD.setCategory(Messages.MLineBox_padding_category);
		paddingBottomD.setCategory(Messages.MLineBox_padding_category);
		paddingTopD.setCategory(Messages.MLineBox_padding_category);
		paddingLeftD.setCategory(Messages.MLineBox_padding_category);
		paddingRightD.setCategory(Messages.MLineBox_padding_category);
		// --------------------------------------------------------------------------------------------------------------
		// pen
		PenPropertyDescriptor linePenD = new PenPropertyDescriptor(LINE_PEN, Messages.MLineBox_line_pen);
		linePenD.setDescription(Messages.MLineBox_line_pen_description);
		desc.add(linePenD);

		PenPropertyDescriptor linePenTopD = new PenPropertyDescriptor(LINE_PEN_TOP, Messages.MLineBox_line_pen_top);
		linePenTopD.setDescription(Messages.MLineBox_line_pen_top_description);
		desc.add(linePenTopD);

		PenPropertyDescriptor linePenBottomD = new PenPropertyDescriptor(LINE_PEN_BOTTOM, Messages.MLineBox_line_pen_bottom);
		linePenBottomD.setDescription(Messages.MLineBox_line_pen_bottom_description);
		desc.add(linePenBottomD);

		PenPropertyDescriptor linePenLeftD = new PenPropertyDescriptor(LINE_PEN_LEFT, Messages.MLineBox_line_pen_left);
		linePenLeftD.setDescription(Messages.MLineBox_line_pen_left_description);
		desc.add(linePenLeftD);

		PenPropertyDescriptor linePenRightD = new PenPropertyDescriptor(LINE_PEN_RIGHT, Messages.MLineBox_line_pen_right);
		linePenRightD.setDescription(Messages.MLineBox_line_pen_right_description);
		desc.add(linePenRightD);
	}

	public static final String LINE_PEN = "LinePen"; //$NON-NLS-1$
	public static final String LINE_PEN_TOP = "LinePen_TOP"; //$NON-NLS-1$
	public static final String LINE_PEN_BOTTOM = "LinePen_BOTTOM"; //$NON-NLS-1$
	public static final String LINE_PEN_LEFT = "LinePen_LEFT"; //$NON-NLS-1$
	public static final String LINE_PEN_RIGHT = "LinePen_RIGHT"; //$NON-NLS-1$

	private MLinePen linePen;
	private MLinePen linePenTop;
	private MLinePen linePenBottom;
	private MLinePen linePenLeft;
	private MLinePen linePenRight;

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		// pen
		JRLineBox lineBox = (JRLineBox) getValue();
		if (lineBox != null) {
			if (id.equals(JRBaseLineBox.PROPERTY_PADDING))
				return lineBox.getOwnPadding();
			if (id.equals(JRBaseLineBox.PROPERTY_LEFT_PADDING))
				return lineBox.getOwnLeftPadding();
			if (id.equals(JRBaseLineBox.PROPERTY_RIGHT_PADDING))
				return lineBox.getOwnRightPadding();
			if (id.equals(JRBaseLineBox.PROPERTY_TOP_PADDING))
				return lineBox.getOwnTopPadding();
			if (id.equals(JRBaseLineBox.PROPERTY_BOTTOM_PADDING))
				return lineBox.getOwnBottomPadding();
			// ----------------------------------------------------
			if (id.equals(LINE_PEN)) {
				if (linePen == null) {
					linePen = new MLinePen(lineBox.getPen());
					linePen.getPropertyChangeSupport().addPropertyChangeListener(this);
				}
				return linePen;
			}
			if (id.equals(LINE_PEN_TOP)) {
				if (linePenTop == null) {
					linePenTop = new MLinePen(lineBox.getTopPen());
					linePenTop.getPropertyChangeSupport().addPropertyChangeListener(this);
				}
				return linePenTop;
			}
			if (id.equals(LINE_PEN_BOTTOM)) {
				if (linePenBottom == null) {
					linePenBottom = new MLinePen(lineBox.getBottomPen());
					linePenBottom.getPropertyChangeSupport().addPropertyChangeListener(this);
				}
				return linePenBottom;
			}
			if (id.equals(LINE_PEN_LEFT)) {
				if (linePenLeft == null) {
					linePenLeft = new MLinePen(lineBox.getLeftPen());
					linePenLeft.getPropertyChangeSupport().addPropertyChangeListener(this);
				}
				return linePenLeft;
			}
			if (id.equals(LINE_PEN_RIGHT)) {
				if (linePenRight == null) {
					linePenRight = new MLinePen(lineBox.getRightPen());
					linePenRight.getPropertyChangeSupport().addPropertyChangeListener(this);
				}
				return linePenRight;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRLineBox lineBox = (JRLineBox) getValue();
		if (lineBox != null) {
			if (id.equals(JRBaseLineBox.PROPERTY_PADDING))
				lineBox.setPadding((Integer) value);
			else if (id.equals(JRBaseLineBox.PROPERTY_TOP_PADDING))
				lineBox.setTopPadding((Integer) value);
			else if (id.equals(JRBaseLineBox.PROPERTY_BOTTOM_PADDING))
				lineBox.setBottomPadding((Integer) value);
			else if (id.equals(JRBaseLineBox.PROPERTY_LEFT_PADDING))
				lineBox.setLeftPadding((Integer) value);
			else if (id.equals(JRBaseLineBox.PROPERTY_RIGHT_PADDING))
				lineBox.setRightPadding((Integer) value);
			// --------------------------------------------
		}
	}

	public String getDisplayText() {
		return null;
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

}
