/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ModeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;

public class MCell extends APropertyNode implements /* IGraphicElement, */IPastable, IPastableGraphic, IContainer,
		IContainerEditPart {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CrosstabNodeIconDescriptor("cell");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MCell() {
		super();
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MCell(ANode parent, JRCellContents jfRield, String name) {
		super(parent, -1);
		setValue(jfRield);
		this.name = name;
	}

	private String name;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return name;
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

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		// initialize style
		JasperDesign jasperDesign = getJasperDesign();
		if (jasperDesign != null) {
			if (styleD != null) {
				JRDesignCellContents jrElement = (JRDesignCellContents) getValue();
				JRStyle[] styles = jasperDesign.getStyles();
				String[] items = new String[styles.length + 1];
				items[0] = jrElement.getStyleNameReference() != null ? jrElement.getStyleNameReference() : "";
				for (int j = 0; j < styles.length; j++) {
					items[j + 1] = styles[j].getName();
				}
				styleD.setItems(items);
			}
		}
	}

	private RWComboBoxPropertyDescriptor styleD;

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		ComboBoxPropertyDescriptor opaqueD = new ComboBoxPropertyDescriptor(JRBaseStyle.PROPERTY_MODE, "Opaque",
				EnumHelper.getEnumNames(ModeEnum.values(), NullEnum.NOTNULL));
		opaqueD.setDescription("Specifies whether the background of an object is transparent or opaque.");
		desc.add(opaqueD);

		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRBaseStyle.PROPERTY_BACKCOLOR, "Backcolor",
				NullEnum.INHERITED);
		backcolorD
				.setDescription("Back color to use when drawing the object. Hexadecimal formatted values preceded by the # character or decimal values are accepted along with the following predefined color values: black, blue, cyan, darkGray, gray, green, lightGray, magenta, orange, pink, red, yellow, white.");
		desc.add(backcolorD);

		styleD = new RWComboBoxPropertyDescriptor(JRDesignCellContents.PROPERTY_STYLE, "Parent Style", new String[] { "" },
				NullEnum.NULL);
		styleD.setDescription("Name of the report level style to use as base style (see <style> element).");
		desc.add(styleD);

		defaultsMap.put(JRBaseStyle.PROPERTY_MODE, EnumHelper.getValue(ModeEnum.OPAQUE, 1, false));
		defaultsMap.put(JRBaseStyle.PROPERTY_BACKCOLOR, null);
		defaultsMap.put(JRDesignCellContents.PROPERTY_STYLE, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignCellContents jrElement = (JRDesignCellContents) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_MODE))
			return EnumHelper.getValue(jrElement.getModeValue(), 1, false);
		if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getBackcolor());
		if (id.equals(JRDesignCellContents.PROPERTY_STYLE)) {
			if (jrElement.getStyleNameReference() != null)
				return jrElement.getStyleNameReference();
			if (jrElement.getStyle() != null)
				return jrElement.getStyle().getName();
			return "";
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignCellContents jrElement = (JRDesignCellContents) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_MODE))
			jrElement.setMode((ModeEnum) EnumHelper.getSetValue(ModeEnum.values(), value, 1, false));
		else if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR)) {
			jrElement.setBackcolor(Colors.getAWT4SWTRGBColor((RGB) value));
		} else if (id.equals(JRDesignCellContents.PROPERTY_STYLE)) {
			if (!value.equals("")) {
				JRStyle style = (JRStyle) getJasperDesign().getStylesMap().get(value);
				if (style != null) {
					jrElement.setStyle(style);
					jrElement.setStyleNameReference(null);
				} else {
					jrElement.setStyleNameReference((String) value);
					jrElement.setStyle(null);
				}
			}
		}
	}

	// public Rectangle getBounds() {
	// Rectangle bounds = ((MCrosstab) getParent()).getBounds();
	// // JRDesignCellContents cell = (JRDesignCellContents) getValue();
	// // cell.getBackcolor();
	// return bounds;
	// }

	public int getDefaultWidth() {
		return 20;
	}

	public int getDefaultHeight() {
		return 20;
	}

	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}
}