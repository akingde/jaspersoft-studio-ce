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
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
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

import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MGeneric.
 */
public class MGraphicElement extends APropertyNode implements IGraphicElement, ICopyable {
	private ReportRulerGuide verticalGuide, horizontalGuide;

	public ReportRulerGuide getVerticalGuide() {
		return verticalGuide;
	}

	public void setVerticalGuide(ReportRulerGuide verticalGuide) {
		this.verticalGuide = verticalGuide;
	}

	public ReportRulerGuide getHorizontalGuide() {
		return horizontalGuide;
	}

	public void setHorizontalGuide(ReportRulerGuide horizontalGuide) {
		this.horizontalGuide = horizontalGuide;
	}

	@Override
	public void setParent(ANode parent, int newIndex) {
		if (parent instanceof MGraphicElement) {
			MGraphicElement p = (MGraphicElement) parent;
			if (p.getVerticalGuide() != null)
				p.getVerticalGuide().detachPart(p);
			if (p.getHorizontalGuide() != null)
				p.getHorizontalGuide().detachPart(p);
		}
		super.setParent(parent, newIndex);
	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("generic"); //$NON-NLS-1$
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
	public int getDefaultHeight() {
		return 30;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getDefaultWidth()
	 */
	public int getDefaultWidth() {
		return 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return new JRDesignGenericElement(jasperDesign);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
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
	public Rectangle getBounds() {
		JRElement jr = (JRElement) getValue();
		INode node = getParent();
		while (node != null) {
			if (node instanceof IGraphicElement) {
				Rectangle b = ((IGraphicElement) node).getBounds();
				if (node instanceof IGraphicElementContainer) {
					int x = ((IGraphicElementContainer) node).getLeftPadding();
					int y = ((IGraphicElementContainer) node).getTopPadding();

					b.setLocation(b.x + x, b.y + y);
				}
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

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		// initialize style
		JasperDesign jasperDesign = getJasperDesign();
		if (jasperDesign != null) {
			if (styleD != null) {
				JRDesignElement jrElement = (JRDesignElement) getValue();
				JRStyle[] styles = jasperDesign.getStyles();
				String[] items = new String[styles.length + 1];
				items[0] = jrElement.getStyleNameReference() != null ? jrElement.getStyleNameReference() : ""; //$NON-NLS-1$
				for (int j = 0; j < styles.length; j++) {
					items[j + 1] = styles[j].getName();
				}
				styleD.setItems(items);
			}
			// initialize groups
			JRGroup[] groups = jasperDesign.getGroups();
			String[] items = new String[groups.length + 1];
			items[0] = ""; //$NON-NLS-1$
			for (int j = 0; j < groups.length; j++) {
				items[j + 1] = groups[j].getName();
			}
			setGroupItems(items);
		}
	}

	protected void setGroupItems(String[] items) {
		if (groupChangesD != null)
			groupChangesD.setItems(items);
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		styleD = new RWComboBoxPropertyDescriptor(JRDesignElement.PROPERTY_PARENT_STYLE,
				Messages.MGraphicElement_parent_style, new String[] { "" }, NullEnum.NULL); //$NON-NLS-1$
		styleD.setDescription(Messages.MGraphicElement_parent_style_description);
		desc.add(styleD);

		groupChangesD = new RComboBoxPropertyDescriptor(JRDesignElement.PROPERTY_PRINT_WHEN_GROUP_CHANGES,
				Messages.MGraphicElement_print_when_group_changes, new String[] { "" }); //$NON-NLS-2$
		groupChangesD.setDescription(Messages.MGraphicElement_print_when_group_changes_description);
		desc.add(groupChangesD);

		NTextPropertyDescriptor keyD = new NTextPropertyDescriptor(JRDesignElement.PROPERTY_KEY,
				Messages.MGraphicElement_key);
		keyD.setDescription(Messages.MGraphicElement_key_description);
		desc.add(keyD);

		// bounds
		IntegerPropertyDescriptor heightD = new IntegerPropertyDescriptor(JRDesignElement.PROPERTY_HEIGHT,
				Messages.MGraphicElement_height);
		heightD.setCategory(Messages.MGraphicElement_size_category);
		heightD.setDescription(Messages.MGraphicElement_height_description);
		desc.add(heightD);

		IntegerPropertyDescriptor widthD = new IntegerPropertyDescriptor(JRBaseElement.PROPERTY_WIDTH,
				Messages.MGraphicElement_width);
		widthD.setCategory(Messages.MGraphicElement_size_category);
		widthD.setDescription(Messages.MGraphicElement_width_description);
		desc.add(widthD);

		IntegerPropertyDescriptor xD = new IntegerPropertyDescriptor(JRBaseElement.PROPERTY_X,
				Messages.MGraphicElement_left);
		xD.setCategory(Messages.MGraphicElement_location_category);
		xD.setDescription(Messages.MGraphicElement_left_description);
		desc.add(xD);

		IntegerPropertyDescriptor yD = new IntegerPropertyDescriptor(JRDesignElement.PROPERTY_Y,
				Messages.MGraphicElement_top);
		yD.setCategory(Messages.MGraphicElement_location_category);
		yD.setDescription(Messages.MGraphicElement_top_description);
		desc.add(yD);
		// colors
		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRBaseStyle.PROPERTY_BACKCOLOR,
				Messages.MGraphicElement_backcolor, NullEnum.INHERITED);
		backcolorD.setDescription(Messages.MGraphicElement_backcolor_description);
		desc.add(backcolorD);

		ColorPropertyDescriptor forecolorD = new ColorPropertyDescriptor(JRBaseStyle.PROPERTY_FORECOLOR,
				Messages.MGraphicElement_forecolor, NullEnum.INHERITED);
		forecolorD.setDescription(Messages.MGraphicElement_forecolor_description);
		desc.add(forecolorD);
		// opacity
		ComboBoxPropertyDescriptor opaqueD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MODE,
				Messages.MGraphicElement_opaque, EnumHelper.getEnumNames(ModeEnum.values(), NullEnum.INHERITED));
		opaqueD.setDescription(Messages.MGraphicElement_opaque_description);
		desc.add(opaqueD);

		ComboBoxPropertyDescriptor positionTypeD = new ComboBoxPropertyDescriptor(JRDesignElement.PROPERTY_POSITION_TYPE,
				Messages.MGraphicElement_position_type, EnumHelper.getEnumNames(PositionTypeEnum.values(), NullEnum.NOTNULL));
		positionTypeD.setDescription(Messages.MGraphicElement_position_type_description);
		desc.add(positionTypeD);
		positionTypeD.setCategory(Messages.MGraphicElement_location_category);

		ComboBoxPropertyDescriptor stretchTypeD = new ComboBoxPropertyDescriptor(JRDesignElement.PROPERTY_STRETCH_TYPE,
				Messages.MGraphicElement_stretch_type, EnumHelper.getEnumNames(StretchTypeEnum.values(), NullEnum.NOTNULL));
		stretchTypeD.setCategory(Messages.MGraphicElement_size_category);
		stretchTypeD.setDescription(Messages.MGraphicElement_stretch_type_description);
		desc.add(stretchTypeD);

		CheckBoxPropertyDescriptor printRVAlueD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES, Messages.MGraphicElement_print_repeated_values);
		printRVAlueD.setDescription(Messages.MGraphicElement_print_repeated_values_description);
		desc.add(printRVAlueD);

		CheckBoxPropertyDescriptor rmLineWBlankD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK, Messages.MGraphicElement_remove_line_when_blank);
		rmLineWBlankD.setDescription(Messages.MGraphicElement_remove_line_when_blank_description);
		desc.add(rmLineWBlankD);

		CheckBoxPropertyDescriptor printInFirstWholeBandD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND, Messages.MGraphicElement_print_in_first_whole_band);
		printInFirstWholeBandD.setDescription(Messages.MGraphicElement_print_in_first_whole_band_description);
		desc.add(printInFirstWholeBandD);

		CheckBoxPropertyDescriptor printWhenDetailOverflowsD = new CheckBoxPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS, Messages.MGraphicElement_print_when_detail_overflows);
		printWhenDetailOverflowsD.setDescription(Messages.MGraphicElement_print_when_detail_overflows_desription);
		desc.add(printWhenDetailOverflowsD);

		JRExpressionPropertyDescriptor printWhenExprD = new JRExpressionPropertyDescriptor(
				JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.MGraphicElement_print_when_expression);
		printWhenExprD.setDescription(Messages.MGraphicElement_print_when_expression_description);
		desc.add(printWhenExprD);

		JPropertiesPropertyDescriptor propertiesD = new JPropertiesPropertyDescriptor(
				JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS, Messages.MGraphicElement_property_expressions);
		propertiesD.setDescription(Messages.MGraphicElement_property_expressions_description);
		desc.add(propertiesD);

		forecolorD.setCategory(Messages.MGraphicElement_graphic_category);
		backcolorD.setCategory(Messages.MGraphicElement_graphic_category);
		opaqueD.setCategory(Messages.MGraphicElement_graphic_category);

		defaultsMap.put(JRDesignElement.PROPERTY_PARENT_STYLE, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_FORECOLOR, null);
		defaultsMap.put(JRBaseStyle.PROPERTY_BACKCOLOR, null);

		defaultsMap.put(JRBaseStyle.PROPERTY_MODE, EnumHelper.getValue(ModeEnum.OPAQUE, 1, true));
		defaultsMap.put(JRDesignElement.PROPERTY_POSITION_TYPE,
				EnumHelper.getValue(PositionTypeEnum.FIX_RELATIVE_TO_TOP, 1, false));
		defaultsMap.put(JRDesignElement.PROPERTY_STRETCH_TYPE, EnumHelper.getValue(StretchTypeEnum.NO_STRETCH, 0, false));
		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES, Boolean.TRUE);
		defaultsMap.put(JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK, Boolean.FALSE);
		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND, Boolean.FALSE);
		defaultsMap.put(JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS, Boolean.FALSE);
	}

	private MExpression mExpression;
	private RWComboBoxPropertyDescriptor styleD;
	private RComboBoxPropertyDescriptor groupChangesD;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignElement jrElement = (JRDesignElement) getValue();
		if (id.equals(JRDesignElement.PROPERTY_KEY))
			return jrElement.getKey();
		if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION)) {
			if (mExpression == null) {
				mExpression = new MExpression(jrElement.getPrintWhenExpression());
				setChildListener(mExpression);
			}
			return mExpression;
		}
		if (id.equals(JRDesignElement.PROPERTY_PARENT_STYLE)) {
			if (jrElement.getStyleNameReference() != null)
				return jrElement.getStyleNameReference();
			if (jrElement.getStyle() != null)
				return jrElement.getStyle().getName();
			return ""; //$NON-NLS-1$
		}
		if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_GROUP_CHANGES)) {
			if (jrElement.getPrintWhenGroupChanges() != null)
				return jrElement.getPrintWhenGroupChanges().getName();
			return ""; //$NON-NLS-1$
		}
		if (id.equals(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS)) {
			// FIXME: jrElement.getPropertyExpression(); same field
			return jrElement.getPropertiesMap();
		}
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
	public void setPropertyValue(Object id, Object value) {
		JRDesignElement jrElement = (JRDesignElement) getValue();
		if (id.equals(JRDesignElement.PROPERTY_KEY))
			jrElement.setKey((String) value);
		else if (id.equals(JRDesignElement.PROPERTY_PARENT_STYLE)) {
			if (!value.equals("")) { //$NON-NLS-1$
				JRStyle style = (JRStyle) getJasperDesign().getStylesMap().get(value);
				if (style != null) {
					jrElement.setStyle(style);
					jrElement.setStyleNameReference(null);
				} else {
					jrElement.setStyleNameReference((String) value);
					jrElement.setStyle(null);
				}
			}
		} else if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION)) {
			if (value instanceof MExpression) {
				mExpression = (MExpression) value;
				setChildListener(mExpression);
				JRExpression expression = (JRExpression) mExpression.getValue();
				jrElement.setPrintWhenExpression(expression);
			}
		} else if (id.equals(JRDesignElement.PROPERTY_PRINT_WHEN_GROUP_CHANGES)) {
			if (!value.equals("")) { //$NON-NLS-1$
				JRGroup group = (JRGroup) getJasperDesign().getGroupsMap().get(value);
				jrElement.setPrintWhenGroupChanges(group);
			}
		} else if (id.equals(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS)) {
			JRPropertiesMap v = (JRPropertiesMap) value;
			JRPropertiesMap propertiesMap = jrElement.getPropertiesMap();
			String[] names = propertiesMap.getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				propertiesMap.removeProperty(names[i]);
			}
			names = v.getPropertyNames();
			for (int i = 0; i < names.length; i++)
				propertiesMap.setProperty(names[i], v.getProperty(names[i]));
		} else if (id.equals(JRDesignElement.PROPERTY_HEIGHT))
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

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MElementGroup || parent instanceof IPastableGraphic)
			return true;
		return false;
	}
}
