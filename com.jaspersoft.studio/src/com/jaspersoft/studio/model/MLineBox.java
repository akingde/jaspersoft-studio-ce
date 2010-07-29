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
		IntegerPropertyDescriptor paddingD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_PADDING, "Padding");
		paddingD.setDescription("The amount of empty space between the border and the element's content.");
		desc.add(paddingD);

		IntegerPropertyDescriptor paddingLeftD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_LEFT_PADDING,
				"Left Padding");
		paddingLeftD.setDescription("The amount of empty space between the left border and the element's content.");
		desc.add(paddingLeftD);

		IntegerPropertyDescriptor paddingRightD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_RIGHT_PADDING,
				"Right Padding");
		paddingRightD.setDescription("The amount of empty space between the right border and the element's content.");
		desc.add(paddingRightD);

		IntegerPropertyDescriptor paddingTopD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_TOP_PADDING,
				"Top Padding");
		paddingTopD.setDescription("The amount of empty space between the top border and the element's content.");
		desc.add(paddingTopD);

		IntegerPropertyDescriptor paddingBottomD = new IntegerPropertyDescriptor(JRBaseLineBox.PROPERTY_BOTTOM_PADDING,
				"Bottom Padding");
		paddingBottomD.setDescription("The amount of empty space between the bottom border and the element's content.");
		desc.add(paddingBottomD);

		paddingD.setCategory("Padding");
		paddingBottomD.setCategory("Padding");
		paddingTopD.setCategory("Padding");
		paddingLeftD.setCategory("Padding");
		paddingRightD.setCategory("Padding");
		// --------------------------------------------------------------------------------------------------------------
		// pen
		PenPropertyDescriptor linePenD = new PenPropertyDescriptor(LINE_PEN, "Line Pen");
		linePenD.setDescription("Groups the properties of the pen used to draw lines or borders.");
		desc.add(linePenD);

		PenPropertyDescriptor linePenTopD = new PenPropertyDescriptor(LINE_PEN_TOP, "Line Pen Top");
		linePenTopD.setDescription("Groups the properties of the pen used to draw lines or borders.");
		desc.add(linePenTopD);

		PenPropertyDescriptor linePenBottomD = new PenPropertyDescriptor(LINE_PEN_BOTTOM, "Line Pen Bottom");
		linePenBottomD.setDescription("Groups the properties of the pen used to draw lines or borders.");
		desc.add(linePenBottomD);

		PenPropertyDescriptor linePenLeftD = new PenPropertyDescriptor(LINE_PEN_LEFT, "Line Pen Left");
		linePenLeftD.setDescription("Groups the properties of the pen used to draw lines or borders.");
		desc.add(linePenLeftD);

		PenPropertyDescriptor linePenRightD = new PenPropertyDescriptor(LINE_PEN_RIGHT, "Line Pen Right");
		linePenRightD.setDescription("Groups the properties of the pen used to draw lines or borders.");
		desc.add(linePenRightD);
	}

	public static final String LINE_PEN = "LinePen";
	public static final String LINE_PEN_TOP = "LinePen_TOP";
	public static final String LINE_PEN_BOTTOM = "LinePen_BOTTOM";
	public static final String LINE_PEN_LEFT = "LinePen_LEFT";
	public static final String LINE_PEN_RIGHT = "LinePen_RIGHT";

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
	@Override
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
	@Override
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

	@Override
	public String getDisplayText() {
		return null;
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

}
