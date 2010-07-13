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

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGenericElement;
import net.sf.jasperreports.engine.design.JRDesignGraphicElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MGeneric.
 */
public class MGraphicElement extends APropertyNode implements IGraphicElement {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("generic");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m generic.
	 */
	public MGraphicElement() {
		super();
	}

	/**
	 * Instantiates a new m generic.
	 * 
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public MGraphicElement(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	/**
	 * Instantiates a new m generic.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrLine
	 *          the jr line
	 * @param newIndex
	 *          the new index
	 */
	public MGraphicElement(ANode parent, JRDesignElement jrLine, int newIndex) {
		super(parent, newIndex);
		setValue(jrLine);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
		if (getValue() != null && getValue() instanceof JRDesignGraphicElement)
			((JRBasePen) ((JRDesignGraphicElement) getValue()).getLinePen()).getEventSupport().removePropertyChangeListener(
					this);
		else if (value != null && value instanceof JRDesignGraphicElement)
			((JRBasePen) ((JRDesignGraphicElement) value).getLinePen()).getEventSupport().addPropertyChangeListener(this);
		super.setValue(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getDefaultHeight()
	 */
	@Override
	public int getDefaultHeight() {
		return 30;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getDefaultWidth()
	 */
	@Override
	public int getDefaultWidth() {
		return 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return new JRDesignGenericElement(jasperDesign);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		JRElement jr = (JRElement) getValue();
		INode node = getParent();
		while (node != null) {
			if (node instanceof IGraphicElement) {
				Rectangle b = ((IGraphicElement) node).getBounds();
				return new Rectangle(b.x + jr.getX(), b.y + jr.getY(), jr.getWidth(), jr.getHeight());
			}
			node = node.getParent();
		}
		return new Rectangle(0, 0, jr.getWidth(), jr.getHeight());
	}

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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	protected void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {

		NTextPropertyDescriptor keyD = new NTextPropertyDescriptor(JRDesignElement.PROPERTY_KEY, "Key");
		keyD
				.setDescription("This attribute is used to identify band elements by their \"key\" when wanting to alter their settings at runtime. The key value must be unique for elements within report band.");
		desc.add(keyD);

		// bounds
		IntegerPropertyDescriptor heightD = new IntegerPropertyDescriptor(JRDesignElement.PROPERTY_HEIGHT, "Height");
		heightD.setCategory("Size");
		heightD.setDescription("Height of the object.");
		desc.add(heightD);

		IntegerPropertyDescriptor widthD = new IntegerPropertyDescriptor(JRBaseElement.PROPERTY_WIDTH, "Width");
		widthD.setCategory("Size");
		widthD.setDescription("Width of the object.");
		desc.add(widthD);

		IntegerPropertyDescriptor xD = new IntegerPropertyDescriptor(JRBaseElement.PROPERTY_X, "Left");
		xD.setCategory("Location");
		xD.setDescription("Specifies the x coordinate for the object within the band.");
		desc.add(xD);

		IntegerPropertyDescriptor yD = new IntegerPropertyDescriptor(JRDesignElement.PROPERTY_Y, "Top");
		yD.setCategory("Location");
		yD.setDescription("Specifies the y coordinate for the object within the band.");
		desc.add(yD);
		// colors
		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRBaseStyle.PROPERTY_BACKCOLOR, "Backcolor",
				NullEnum.INHERITED);
		backcolorD
				.setDescription("Back color to use when drawing the object. Hexadecimal formatted values preceded by the # character or decimal values are accepted along with the following predefined color values: black, blue, cyan, darkGray, gray, green, lightGray, magenta, orange, pink, red, yellow, white.");
		desc.add(backcolorD);

		ColorPropertyDescriptor forecolorD = new ColorPropertyDescriptor(JRBaseStyle.PROPERTY_FORECOLOR, "Forecolor",
				NullEnum.INHERITED);
		forecolorD
				.setDescription("Fore color to use when drawing the object. Hexadecimal formatted values preceded by the # character or decimal values are accepted along with the following predefined color values: black, blue, cyan, darkGray, gray, green, lightGray, magenta, orange, pink, red, yellow, white.");
		desc.add(forecolorD);
		// opacity
		ComboBoxPropertyDescriptor opaqueD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MODE, "Opaque", EnumHelper
				.getEnumNames(ModeEnum.values(), NullEnum.INHERITED));
		opaqueD.setDescription("Specifies whether the background of an object is transparent or opaque.");
		desc.add(opaqueD);

		ComboBoxPropertyDescriptor positionTypeD = new ComboBoxPropertyDescriptor(JRDesignElement.PROPERTY_POSITION_TYPE,
				"Position Type", EnumHelper.getEnumNames(PositionTypeEnum.values(), NullEnum.NOTNULL));
		positionTypeD.setDescription("Specifies the object position when the report section is affected by stretch.");
		desc.add(positionTypeD);

		ComboBoxPropertyDescriptor stretchTypeD = new ComboBoxPropertyDescriptor(JRDesignElement.PROPERTY_STRETCH_TYPE,
				"Stretch Type", EnumHelper.getEnumNames(StretchTypeEnum.values(), NullEnum.NOTNULL));
		stretchTypeD
				.setDescription("Specifies the graphic element stretch behavior when the report section is affected by stretch.");
		desc.add(stretchTypeD);

		CheckBoxPropertyDescriptor printRVAlueD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES, "Print Repeated Values");
		printRVAlueD
				.setDescription("This flag allows suppressing the repeating values for the dynamic elements such as text fields and image fields and to fully customize the behavior of the static elements like rectangles, lines, ellipses and static texts.");
		desc.add(printRVAlueD);

		CheckBoxPropertyDescriptor rmLineWBlankD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK, "Remove Line When Blank");
		rmLineWBlankD
				.setDescription("Collapses the band if the element is not printing and no other element is occupying the same horizontal space.");
		desc.add(rmLineWBlankD);

		CheckBoxPropertyDescriptor printInFirstWholeBandD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND, "Print In First Whole Band");
		printInFirstWholeBandD
				.setDescription("The element gets printed in the first band of a new page or column that is not an overflow from a previous page or column.");
		desc.add(printInFirstWholeBandD);

		CheckBoxPropertyDescriptor printWhenDetailOverflowsD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS, "Print When Detail Overflows");
		printWhenDetailOverflowsD
				.setDescription("The element will be printed when the band overflows to a new page or a new column.");
		desc.add(printWhenDetailOverflowsD);
		
		forecolorD.setCategory("Graphic");
		backcolorD.setCategory("Graphic");
		opaqueD.setCategory("Graphic");
		

		defaultsMap.put(JRBaseStyle.PROPERTY_FORECOLOR, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_BACKCOLOR, null);

		defaultsMap.put(JRBaseStyle.PROPERTY_MODE, EnumHelper.getValue(ModeEnum.OPAQUE, 1, true));
		defaultsMap.put(JRDesignElement.PROPERTY_POSITION_TYPE, EnumHelper.getValue(PositionTypeEnum.FIX_RELATIVE_TO_TOP,
				1, false));
		defaultsMap.put(JRDesignElement.PROPERTY_STRETCH_TYPE, EnumHelper.getValue(StretchTypeEnum.NO_STRETCH, 0, false));
		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES, Boolean.TRUE);
		defaultsMap.put(JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK, Boolean.FALSE);
		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND, Boolean.FALSE);
		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS, Boolean.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignElement jrElement = (JRDesignElement) getValue();
		if (id.equals(JRDesignElement.PROPERTY_KEY))
			return jrElement.getKey();
		if (id.equals(JRDesignElement.PROPERTY_HEIGHT))
			return new Integer(jrElement.getHeight());
		if (id.equals(JRDesignElement.PROPERTY_WIDTH))
			return new Integer(jrElement.getWidth());
		if (id.equals(JRDesignElement.PROPERTY_X))
			return new Integer(jrElement.getX());
		if (id.equals(JRDesignElement.PROPERTY_Y))
			return new Integer(jrElement.getY());
		// colors
		if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnBackcolor());
		if (id.equals(JRBaseStyle.PROPERTY_FORECOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnForecolor());
		// opacity
		if (id.equals(JRBaseStyle.PROPERTY_MODE))
			return EnumHelper.getValue(jrElement.getOwnModeValue(), 1, true);
		if (id.equals(JRDesignElement.PROPERTY_POSITION_TYPE))
			return EnumHelper.getValue(jrElement.getPositionTypeValue(), 1, false);
		if (id.equals(JRDesignElement.PROPERTY_STRETCH_TYPE))
			return EnumHelper.getValue(jrElement.getStretchTypeValue(), 0, false);

		if (id.equals(JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES))
			return new Boolean(jrElement.isPrintRepeatedValues());
		if (id.equals(JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK))
			return new Boolean(jrElement.isRemoveLineWhenBlank());
		if (id.equals(JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND))
			return new Boolean(jrElement.isPrintInFirstWholeBand());
		if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS))
			return new Boolean(jrElement.isPrintWhenDetailOverflows());

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignElement jrElement = (JRDesignElement) getValue();
		if (id.equals(JRDesignElement.PROPERTY_KEY))
			jrElement.setKey((String) value);
		else if (id.equals(JRDesignElement.PROPERTY_HEIGHT))
			jrElement.setHeight(((Integer) value).intValue());
		else if (id.equals(JRDesignElement.PROPERTY_WIDTH))
			jrElement.setWidth(((Integer) value).intValue());
		else if (id.equals(JRDesignElement.PROPERTY_X))
			jrElement.setX(((Integer) value).intValue());
		else if (id.equals(JRDesignElement.PROPERTY_Y))
			jrElement.setY(((Integer) value).intValue());
		else
		// colors
		if (id.equals(JRBaseStyle.PROPERTY_FORECOLOR)) {
			jrElement.setForecolor(Colors.getAWT4SWTRGBColor((RGB) value));
		} else if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR)) {
			jrElement.setBackcolor(Colors.getAWT4SWTRGBColor((RGB) value));
		} else
		// opacity
		if (id.equals(JRBaseStyle.PROPERTY_MODE))
			jrElement.setMode((ModeEnum) EnumHelper.getSetValue(ModeEnum.values(), value, 1, true));
		else if (id.equals(JRDesignElement.PROPERTY_POSITION_TYPE))
			jrElement.setPositionType((PositionTypeEnum) EnumHelper.getSetValue(PositionTypeEnum.values(), value, 1, false));
		else if (id.equals(JRDesignElement.PROPERTY_STRETCH_TYPE))
			jrElement.setStretchType((StretchTypeEnum) EnumHelper.getSetValue(StretchTypeEnum.values(), value, 0, false));

		else if (id.equals(JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES))
			jrElement.setPrintRepeatedValues(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK))
			jrElement.setRemoveLineWhenBlank(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND))
			jrElement.setPrintInFirstWholeBand(((Boolean) value).booleanValue());
		else if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS))
			jrElement.setPrintWhenDetailOverflows(((Boolean) value).booleanValue());
	}
}
