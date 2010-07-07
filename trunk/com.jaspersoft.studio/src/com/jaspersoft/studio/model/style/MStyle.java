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
package com.jaspersoft.studio.model.style;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineSpacingEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.model.ReportFactory;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * The Class MStyle.
 * 
 * @author Chicu Veaceslav
 */
public class MStyle extends APropertyNode {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("style");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m style.
	 */
	public MStyle() {
		super();
	}

	public MStyle(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	/**
	 * Instantiates a new m style.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrstyle
	 *          the jrstyle
	 * @param newIndex
	 *          the new index
	 */
	public MStyle(ANode parent, JRDesignStyle jrstyle, int newIndex) {
		super(parent, newIndex);
		setValue(jrstyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return ((JRStyle) getValue()).getName();
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
		TextPropertyDescriptor nameD = new TextPropertyDescriptor(JRDesignStyle.PROPERTY_NAME, "Name");
		nameD.setDescription("Name of the report style definition.");
		desc.add(nameD);

		ColorPropertyDescriptor forecolorD = new ColorPropertyDescriptor(JRDesignStyle.PROPERTY_FORECOLOR, "Forecolor",
				NullEnum.INHERITED);
		forecolorD.setDescription("Forecolor");
		desc.add(forecolorD);

		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRDesignStyle.PROPERTY_BACKCOLOR, "Backcolor",
				NullEnum.INHERITED);
		backcolorD.setDescription("Backcolor");
		desc.add(backcolorD);

		IntegerPropertyDescriptor radiusD = new IntegerPropertyDescriptor(JRDesignStyle.PROPERTY_RADIUS, "Radius");
		radiusD.setDescription("Radius");
		desc.add(radiusD);

		ComboBoxPropertyDescriptor fillD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_FILL, "Fill", EnumHelper
				.getEnumNames(FillEnum.values(), NullEnum.INHERITED));
		fillD.setDescription("Type of the fill pattern used to fill objects.");
		desc.add(fillD);

		ComboBoxPropertyDescriptor scaleD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_SCALE_IMAGE, "Scale",
				EnumHelper.getEnumNames(ScaleImageEnum.values(), NullEnum.INHERITED));
		scaleD.setDescription("Image displaying type.");
		desc.add(scaleD);

		ComboBoxPropertyDescriptor halignD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_HORIZONTAL_ALIGNMENT,
				"Horizontal alignment", EnumHelper.getEnumNames(HorizontalAlignEnum.values(), NullEnum.INHERITED));
		halignD.setDescription("Horizontal image alignment.");
		desc.add(halignD);

		ComboBoxPropertyDescriptor valignD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_VERTICAL_ALIGNMENT,
				"Vertical alignment", EnumHelper.getEnumNames(VerticalAlignEnum.values(), NullEnum.INHERITED));
		valignD.setDescription("Vertical image alignment.");
		desc.add(valignD);

		ComboBoxPropertyDescriptor rotationD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_ROTATION, "Rotation",
				EnumHelper.getEnumNames(RotationEnum.values(), NullEnum.INHERITED));
		rotationD.setDescription("Type of rotation for the object.");
		desc.add(rotationD);

		ComboBoxPropertyDescriptor lineSpacingD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_LINE_SPACING,
				"Line Spacing", EnumHelper.getEnumNames(LineSpacingEnum.values(), NullEnum.INHERITED));
		lineSpacingD.setDescription("Type of line spacing for the text object.");
		desc.add(lineSpacingD);

		ComboBoxPropertyDescriptor modeD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_MODE, "Mode", EnumHelper
				.getEnumNames(ModeEnum.values(), NullEnum.INHERITED));
		modeD.setDescription("Specifies whether the background of an object is transparent or opaque.");
		desc.add(modeD);

		CheckBoxPropertyDescriptor blankWhenNullD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL,
				"Blank When NULL", NullEnum.INHERITED);
		blankWhenNullD
				.setDescription("Specifies that the text field should display a blank character instead of \"null\" when the text field expression evaluates to null.");
		desc.add(blankWhenNullD);

		CheckBoxPropertyDescriptor strikeThroughD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_STRIKE_THROUGH,
				"Strike Through", NullEnum.INHERITED);
		strikeThroughD.setDescription("Flag indicating if the font is strikethrough.");
		desc.add(strikeThroughD);

		CheckBoxPropertyDescriptor underlineD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_UNDERLINE,
				"Underline", NullEnum.INHERITED);
		underlineD.setDescription("Flag indicating if the font is underlined.");
		desc.add(underlineD);

		CheckBoxPropertyDescriptor italicD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_ITALIC, "Italic",
				NullEnum.INHERITED);
		italicD.setDescription("Flag indicating if the font is italic.");
		desc.add(italicD);

		CheckBoxPropertyDescriptor boldD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_BOLD, "Bold",
				NullEnum.INHERITED);
		boldD.setDescription("Flag indicating if the font is bold.");
		desc.add(boldD);

		CheckBoxPropertyDescriptor defaultD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_DEFAULT,
				"Default Style");
		defaultD
				.setDescription("The default report style is used as base style for all the elements that do not explicitly reference a report style definition.");
		desc.add(defaultD);

		defaultsMap.put(JRDesignStyle.PROPERTY_FORECOLOR, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_BACKCOLOR, null);
		
		defaultsMap.put(JRDesignStyle.PROPERTY_FILL, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_SCALE_IMAGE, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_HORIZONTAL_ALIGNMENT, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_VERTICAL_ALIGNMENT, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_ROTATION, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_LINE_SPACING, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_MODE, EnumHelper.getValue(ModeEnum.OPAQUE, 1, true));

		defaultsMap.put(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_STRIKE_THROUGH, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_UNDERLINE, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_ITALIC, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_BOLD, Boolean.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		if (getValue() == null)
			return null;
		if (getValue() instanceof JRDesignStyle) {
			JRDesignStyle jrstyle = (JRDesignStyle) getValue();
			if (id.equals(JRDesignStyle.PROPERTY_NAME))
				return jrstyle.getName();
			if (id.equals(JRDesignStyle.PROPERTY_DEFAULT))
				return new Boolean(jrstyle.isDefault());
		}
		JRBaseStyle jrstyle = (JRBaseStyle) getValue();
		if (id.equals(JRDesignStyle.PROPERTY_RADIUS))
			return jrstyle.getOwnRadius();
		if (id.equals(JRDesignStyle.PROPERTY_FORECOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrstyle.getOwnForecolor());
		else if (id.equals(JRDesignStyle.PROPERTY_BACKCOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrstyle.getOwnBackcolor());
		if (id.equals(JRDesignStyle.PROPERTY_FILL))
			return EnumHelper.getValue(jrstyle.getOwnFillValue(), 1, true);

		if (id.equals(JRDesignStyle.PROPERTY_SCALE_IMAGE))
			return EnumHelper.getValue(jrstyle.getOwnScaleImageValue(), 1, true);
		if (id.equals(JRDesignStyle.PROPERTY_HORIZONTAL_ALIGNMENT))
			return EnumHelper.getValue(jrstyle.getOwnHorizontalAlignmentValue(), 1, true);
		if (id.equals(JRDesignStyle.PROPERTY_VERTICAL_ALIGNMENT))
			return EnumHelper.getValue(jrstyle.getOwnVerticalAlignmentValue(), 1, true);
		if (id.equals(JRDesignStyle.PROPERTY_ROTATION))
			return EnumHelper.getValue(jrstyle.getOwnRotationValue(), 0, true);
		if (id.equals(JRDesignStyle.PROPERTY_LINE_SPACING))
			return EnumHelper.getValue(jrstyle.getOwnLineSpacingValue(), 0, true);
		if (id.equals(JRDesignStyle.PROPERTY_MODE))
			return EnumHelper.getValue(jrstyle.getOwnModeValue(), 1, true);

		if (id.equals(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL))
			return jrstyle.isOwnBlankWhenNull();
		if (id.equals(JRDesignStyle.PROPERTY_STRIKE_THROUGH))
			return jrstyle.isOwnStrikeThrough();
		if (id.equals(JRDesignStyle.PROPERTY_UNDERLINE))
			return jrstyle.isOwnUnderline();
		if (id.equals(JRDesignStyle.PROPERTY_ITALIC))
			return jrstyle.isOwnItalic();
		if (id.equals(JRDesignStyle.PROPERTY_BOLD))
			return jrstyle.isOwnBold();

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		if (getValue() instanceof JRDesignStyle) {
			JRDesignStyle jrstyle = (JRDesignStyle) getValue();
			if (id.equals(JRDesignStyle.PROPERTY_NAME))
				jrstyle.setName((String) value);
			else if (id.equals(JRDesignStyle.PROPERTY_DEFAULT))
				jrstyle.setDefault(((Boolean) value).booleanValue());
		}
		JRBaseStyle jrstyle = (JRBaseStyle) getValue();
		if (id.equals(JRDesignStyle.PROPERTY_RADIUS))
			jrstyle.setRadius((Integer) value);
		else if (id.equals(JRDesignStyle.PROPERTY_FORECOLOR))
			jrstyle.setForecolor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignStyle.PROPERTY_BACKCOLOR))
			jrstyle.setBackcolor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignStyle.PROPERTY_FILL))
			jrstyle.setFill((FillEnum) EnumHelper.getSetValue(FillEnum.values(), value, 1, true));

		else if (id.equals(JRDesignStyle.PROPERTY_SCALE_IMAGE))
			jrstyle.setScaleImage((ScaleImageEnum) EnumHelper.getSetValue(ScaleImageEnum.values(), value, 1, true));
		else if (id.equals(JRDesignStyle.PROPERTY_HORIZONTAL_ALIGNMENT))
			jrstyle.setHorizontalAlignment((HorizontalAlignEnum) EnumHelper.getSetValue(HorizontalAlignEnum.values(), value,
					1, true));
		else if (id.equals(JRDesignStyle.PROPERTY_VERTICAL_ALIGNMENT))
			jrstyle.setVerticalAlignment((VerticalAlignEnum) EnumHelper.getSetValue(VerticalAlignEnum.values(), value, 1,
					true));
		else if (id.equals(JRDesignStyle.PROPERTY_ROTATION))
			jrstyle.setRotation((RotationEnum) EnumHelper.getSetValue(RotationEnum.values(), value, 0, true));
		else if (id.equals(JRDesignStyle.PROPERTY_LINE_SPACING))
			jrstyle.setLineSpacing((LineSpacingEnum) EnumHelper.getSetValue(LineSpacingEnum.values(), value, 0, true));
		else if (id.equals(JRDesignStyle.PROPERTY_MODE))
			jrstyle.setMode((ModeEnum) EnumHelper.getSetValue(ModeEnum.values(), value, 1, true));

		else if (id.equals(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL))
			jrstyle.setBlankWhenNull((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_STRIKE_THROUGH))
			jrstyle.setStrikeThrough((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_UNDERLINE))
			jrstyle.setUnderline((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_ITALIC))
			jrstyle.setItalic((Boolean) value);
		else if (id.equals(JRDesignStyle.PROPERTY_BOLD))
			jrstyle.setBold((Boolean) value);
	}

	/**
	 * Creates the jr style.
	 * 
	 * @param jrDesign
	 *          the jr design
	 * @return the jR design style
	 */
	public static JRDesignStyle createJRStyle(JasperDesign jrDesign) {
		JRDesignStyle jrDesignStyle = new JRDesignStyle();
		jrDesignStyle.setName(ModelUtils.getDefaultName(jrDesign.getStylesMap(), "Style"));
		return jrDesignStyle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		PropertyChangeEvent newEvent = evt;
		if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_CONDITIONAL_STYLES) && evt.getSource() == getValue()) {
			if (evt.getOldValue() == null && evt.getNewValue() != null) {
				int newIndex = -1;
				if (evt instanceof CollectionElementAddedEvent) {
					newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
				}
				// add the node to this parent
				ReportFactory.createNode(this, evt.getNewValue(), newIndex);
			} else if (evt.getOldValue() != null && evt.getNewValue() == null) {
				// delete
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue()) {
						removeChild((ANode) n);
						break;
					}
				}
			} else {
				// changed
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue())
						n.setValue(evt.getNewValue());
				}
			}
		}
		if (!(evt.getSource() instanceof ANode))
			newEvent = new PropertyChangeEvent(this, evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
		getPropertyChangeSupport().firePropertyChange(newEvent);
	}

}
