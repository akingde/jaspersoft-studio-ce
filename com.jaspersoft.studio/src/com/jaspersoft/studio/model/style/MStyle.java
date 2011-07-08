/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.style;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseParagraph;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.jface.IntegerCellEditorValidator;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.model.text.MParagraph;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.box.BoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.pattern.PatternPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.pen.PenPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;
import com.jaspersoft.studio.utils.ModelUtils;
/*
 * The Class MStyle.
 * 
 * @author Chicu Veaceslav
 */
public class MStyle extends APropertyNode implements ICopyable, IPastable, IContainerEditPart {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("style"); //$NON-NLS-1$
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
	public String getDisplayText() {
		return ((JRStyle) getValue()).getName();
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

	private static final String LINE_PEN = "LinePen"; //$NON-NLS-1$
	private static final String LINE_BOX = "LineBox"; //$NON-NLS-1$

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		if (styleD != null && getValue() instanceof JRDesignStyle) {
			JRDesignStyle jrElement = (JRDesignStyle) getValue();
			JRStyle[] styles = getJasperDesign().getStyles();
			String[] items = new String[styles.length];
			if (items.length > 0) {
				items[0] = jrElement.getStyleNameReference() != null ? jrElement.getStyleNameReference() : ""; //$NON-NLS-1$
				for (int j = 0; j < styles.length; j++) {
					if (jrElement != styles[j])
						items[j] = styles[j].getName();
				}
			}
			styleD.setItems(items);
		}
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {

		styleD = new RWComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_PARENT_STYLE, Messages.common_parent_style,
				new String[] { "" }, //$NON-NLS-1$
				NullEnum.NULL);
		styleD.setDescription(Messages.MStyle_parent_style_description);
		desc.add(styleD);

		TextPropertyDescriptor nameD = new TextPropertyDescriptor(JRDesignStyle.PROPERTY_NAME, Messages.common_name);
		nameD.setDescription(Messages.MStyle_name_description);
		desc.add(nameD);

		PenPropertyDescriptor linePenD = new PenPropertyDescriptor(LINE_PEN, Messages.common_line_pen);
		linePenD.setDescription(Messages.MStyle_line_pen_description);
		desc.add(linePenD);

		BoxPropertyDescriptor lineBoxD = new BoxPropertyDescriptor(LINE_BOX, Messages.common_line_box);
		lineBoxD.setDescription(Messages.MStyle_line_box_description);
		desc.add(lineBoxD);
		lineBoxD.setCategory(Messages.common_graphic);

		ColorPropertyDescriptor forecolorD = new ColorPropertyDescriptor(JRDesignStyle.PROPERTY_FORECOLOR,
				Messages.common_forecolor, NullEnum.INHERITED);
		forecolorD.setDescription(Messages.MStyle_forecolor_description);
		desc.add(forecolorD);

		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRDesignStyle.PROPERTY_BACKCOLOR,
				Messages.common_backcolor, NullEnum.INHERITED);
		backcolorD.setDescription(Messages.MStyle_backcolor_description);
		desc.add(backcolorD);

		IntegerPropertyDescriptor radiusD = new IntegerPropertyDescriptor(JRDesignStyle.PROPERTY_RADIUS,
				Messages.common_radius);
		radiusD.setDescription(Messages.MStyle_radius_description);
		desc.add(radiusD);

		ComboBoxPropertyDescriptor fillD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_FILL,
				Messages.common_fill, EnumHelper.getEnumNames(FillEnum.values(), NullEnum.INHERITED));
		fillD.setDescription(Messages.MStyle_fill_description);
		desc.add(fillD);

		ComboBoxPropertyDescriptor scaleD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_SCALE_IMAGE,
				Messages.MStyle_scale, EnumHelper.getEnumNames(ScaleImageEnum.values(), NullEnum.INHERITED));
		scaleD.setDescription(Messages.MStyle_scale_description);
		desc.add(scaleD);

		ComboBoxPropertyDescriptor halignD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_HORIZONTAL_ALIGNMENT,
				Messages.common_horizontal_alignment, EnumHelper.getEnumNames(HorizontalAlignEnum.values(), NullEnum.INHERITED));
		halignD.setDescription(Messages.MStyle_horizontal_alignment_description);
		desc.add(halignD);

		ComboBoxPropertyDescriptor valignD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_VERTICAL_ALIGNMENT,
				Messages.common_vertical_alignment, EnumHelper.getEnumNames(VerticalAlignEnum.values(), NullEnum.INHERITED));
		valignD.setDescription(Messages.MStyle_vertical_alignment_description);
		desc.add(valignD);

		ComboBoxPropertyDescriptor rotationD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_ROTATION,
				Messages.common_rotation, EnumHelper.getEnumNames(RotationEnum.values(), NullEnum.INHERITED));
		rotationD.setDescription(Messages.MStyle_rotation_description);
		desc.add(rotationD);

		ComboBoxPropertyDescriptor modeD = new ComboBoxPropertyDescriptor(JRDesignStyle.PROPERTY_MODE,
				Messages.MStyle_mode, EnumHelper.getEnumNames(ModeEnum.values(), NullEnum.INHERITED));
		modeD.setDescription(Messages.MStyle_mode_description);
		desc.add(modeD);

		CheckBoxPropertyDescriptor blankWhenNullD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL,
				Messages.common_blank_when_null, NullEnum.INHERITED);
		blankWhenNullD.setDescription(Messages.MStyle_blank_when_null_description);
		desc.add(blankWhenNullD);

		CheckBoxPropertyDescriptor strikeThroughD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_STRIKE_THROUGH,
				Messages.common_strike_trough, NullEnum.INHERITED);
		strikeThroughD.setDescription(Messages.MStyle_strike_trough_description);
		desc.add(strikeThroughD);

		CheckBoxPropertyDescriptor underlineD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_UNDERLINE,
				Messages.common_underline, NullEnum.INHERITED);
		underlineD.setDescription(Messages.MStyle_underline_description);
		desc.add(underlineD);

		CheckBoxPropertyDescriptor italicD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_ITALIC,
				Messages.common_italic, NullEnum.INHERITED);
		italicD.setDescription(Messages.MStyle_italic_description);
		desc.add(italicD);

		CheckBoxPropertyDescriptor boldD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_BOLD,
				Messages.common_bold, NullEnum.INHERITED);
		boldD.setDescription(Messages.MStyle_bold_description);
		desc.add(boldD);

		CheckBoxPropertyDescriptor defaultD = new CheckBoxPropertyDescriptor(JRDesignStyle.PROPERTY_DEFAULT,
				Messages.MStyle_default_style);
		defaultD.setDescription(Messages.MStyle_default_style_description);
		desc.add(defaultD);

		RWComboBoxPropertyDescriptor markupD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MARKUP,
				Messages.MStyle_markup, ModelUtils.getMarkups(), NullEnum.INHERITED);
		markupD.setDescription(Messages.MStyle_markup_description);
		desc.add(markupD);

		RWComboBoxPropertyDescriptor fontNameD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FONT_NAME,
				Messages.common_font_name, ModelUtils.getFontNames(), NullEnum.INHERITED);
		fontNameD.setDescription(Messages.MStyle_font_name_description);
		desc.add(fontNameD);

		RWComboBoxPropertyDescriptor fontSizeD = new RWComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_FONT_SIZE,
				Messages.common_font_size, ModelUtils.getFontSizes(), NullEnum.INHERITED);
		fontSizeD.setDescription(Messages.MStyle_font_size_description);
		fontSizeD.setValidator(new IntegerCellEditorValidator());
		desc.add(fontSizeD);

		PatternPropertyDescriptor patternD = new PatternPropertyDescriptor(JRDesignStyle.PROPERTY_PATTERN,
				Messages.common_pattern);
		patternD.setDescription(Messages.MStyle_pattern_description);
		desc.add(patternD);

		JRPropertyDescriptor paragraph = new JRPropertyDescriptor("paragraph", "Paragraph");
		desc.add(paragraph);

		paragraph.setCategory(Messages.common_text);
		styleD.setCategory(Messages.MStyle_Style_properties);
		nameD.setCategory(Messages.MStyle_Style_properties);
		defaultD.setCategory(Messages.MStyle_Style_properties);

		forecolorD.setCategory(Messages.MStyle_common_category);
		backcolorD.setCategory(Messages.MStyle_common_category);
		modeD.setCategory(Messages.MStyle_common_category);

		linePenD.setCategory(Messages.common_graphic);

		radiusD.setCategory(Messages.common_graphic);
		scaleD.setCategory(Messages.common_graphic);
		fillD.setCategory(Messages.common_graphic);

		patternD.setCategory(Messages.common_text);
		blankWhenNullD.setCategory(Messages.common_text);
		rotationD.setCategory(Messages.common_text);
		markupD.setCategory(Messages.common_text);
		halignD.setCategory(Messages.common_text);
		valignD.setCategory(Messages.common_text);

		fontNameD.setCategory(Messages.MStyle_text_font_category);
		fontSizeD.setCategory(Messages.MStyle_text_font_category);
		boldD.setCategory(Messages.MStyle_text_font_category);
		italicD.setCategory(Messages.MStyle_text_font_category);
		underlineD.setCategory(Messages.MStyle_text_font_category);
		strikeThroughD.setCategory(Messages.MStyle_text_font_category);

		defaultsMap.put(JRDesignStyle.PROPERTY_FORECOLOR, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_BACKCOLOR, null);

		defaultsMap.put(JRDesignStyle.PROPERTY_FILL, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_SCALE_IMAGE, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_HORIZONTAL_ALIGNMENT, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_VERTICAL_ALIGNMENT, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_ROTATION, null);
		defaultsMap.put(JRDesignStyle.PROPERTY_MODE, EnumHelper.getValue(ModeEnum.OPAQUE, 1, true));

		defaultsMap.put(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_STRIKE_THROUGH, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_UNDERLINE, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_ITALIC, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_BOLD, Boolean.FALSE);
		defaultsMap.put(JRDesignStyle.PROPERTY_FONT_NAME, "SansSerif"); //$NON-NLS-1$
		defaultsMap.put(JRDesignStyle.PROPERTY_FONT_SIZE, "10"); //$NON-NLS-1$
	}

	private MLinePen linePen;
	private MLineBox lineBox;
	private RWComboBoxPropertyDescriptor styleD;
	private MParagraph mParagraph;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		if (getValue() == null)
			return null;
		if (getValue() instanceof JRDesignStyle) {
			JRDesignStyle jrstyle = (JRDesignStyle) getValue();
			if (id.equals(JRDesignStyle.PROPERTY_NAME))
				return jrstyle.getName();
			if (id.equals(JRDesignStyle.PROPERTY_DEFAULT))
				return new Boolean(jrstyle.isDefault());
			if (id.equals(JRDesignStyle.PROPERTY_PARENT_STYLE)) {
				if (jrstyle.getStyleNameReference() != null)
					return jrstyle.getStyleNameReference();
				if (jrstyle.getStyle() != null)
					return jrstyle.getStyle().getName();
				return ""; //$NON-NLS-1$
			}
			if (id.equals("paragraph")) {
				if (mParagraph == null) {
					mParagraph = new MParagraph((JRBaseParagraph) jrstyle.getParagraph());
					setChildListener(mParagraph);
				}
				return mParagraph;
			}
		}

		JRBaseStyle jrstyle = (JRBaseStyle) getValue();
		if (id.equals(LINE_PEN)) {
			if (linePen == null) {
				linePen = new MLinePen(jrstyle.getLinePen());
				setChildListener(linePen);
			}
			return linePen;
		}
		if (id.equals(LINE_BOX)) {
			if (lineBox == null) {
				lineBox = new MLineBox(jrstyle.getLineBox());
				setChildListener(lineBox);
			}
			return lineBox;
		}
		if (id.equals(JRBaseStyle.PROPERTY_PATTERN))
			return jrstyle.getOwnPattern();
		if (id.equals(JRBaseStyle.PROPERTY_RADIUS))
			return jrstyle.getOwnRadius();
		if (id.equals(JRBaseStyle.PROPERTY_MARKUP))
			return jrstyle.getOwnMarkup();
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
		if (id.equals(JRDesignStyle.PROPERTY_FONT_NAME))
			return jrstyle.getOwnFontName();
		if (id.equals(JRDesignStyle.PROPERTY_FONT_SIZE))
			return jrstyle.getOwnFontSize() != null ? jrstyle.getOwnFontSize().toString() : ""; //$NON-NLS-1$

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		if (!isEditable())
			return;
		if (getValue() instanceof JRDesignStyle) {
			JRDesignStyle jrstyle = (JRDesignStyle) getValue();
			if (id.equals(JRDesignStyle.PROPERTY_NAME))
				jrstyle.setName((String) value);
			if (id.equals(JRDesignStyle.PROPERTY_PATTERN))
				jrstyle.setPattern((String) value);
			else if (id.equals(JRDesignStyle.PROPERTY_DEFAULT)) {
				jrstyle.setDefault(((Boolean) value).booleanValue());
				getJasperDesign().resetDefaultStyle();
			} else if (id.equals(JRDesignStyle.PROPERTY_PARENT_STYLE)) {
				if (!value.equals("")) { //$NON-NLS-1$
					JRStyle style = (JRStyle) getJasperDesign().getStylesMap().get(value);
					if (style != null) {
						jrstyle.setParentStyle(style);
						jrstyle.setParentStyleNameReference(null);
					} else {
						jrstyle.setParentStyleNameReference((String) value);
						jrstyle.setParentStyle(null);
					}
				}
			}
		}
		JRBaseStyle jrstyle = (JRBaseStyle) getValue();
		if (id.equals(JRDesignStyle.PROPERTY_RADIUS))
			jrstyle.setRadius((Integer) value);
		else if (id.equals(JRBaseStyle.PROPERTY_MARKUP))
			jrstyle.setMarkup((String) value);
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
		else if (id.equals(JRDesignStyle.PROPERTY_FONT_NAME))
			jrstyle.setFontName((String) value);
		else if (id.equals(JRDesignStyle.PROPERTY_FONT_SIZE))
			jrstyle.setFontSize(new Integer((String) value));
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
		jrDesignStyle.setName(ModelUtils.getDefaultName(jrDesign.getStylesMap(), "Style")); //$NON-NLS-1$
		return jrDesignStyle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
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
		evt = new PropertyChangeEvent(getValue(), evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
		super.propertyChange(evt);
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MStyles)
			return true;
		return false;
	}

}
