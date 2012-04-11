/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.crosstab.model.cell;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ModeEnum;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.crosstab.CrosstabManager;
import com.jaspersoft.studio.components.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.ILineBox;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.box.BoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;

public class MCell extends APropertyNode implements IGraphicElement, IPastable,
		IPastableGraphic, IContainer, IContainerEditPart, ILineBox,
		IGraphicElementContainer {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CrosstabNodeIconDescriptor("cell"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MCell() {
		super();
	}

	public MCell(ANode parent, JRCellContents jfRield, String name) {
		super(parent, -1);
		setValue(jfRield);
		setName(name);
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *            the parent
	 * @param jfRield
	 *            the jf rield
	 * @param newIndex
	 *            the new index
	 */
	public MCell(ANode parent, JRCellContents jfRield, String name, int index) {
		super(parent, index);
		setValue(jfRield);
		setName(name);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
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
				items[0] = jrElement.getStyleNameReference() != null ? jrElement
						.getStyleNameReference() : ""; //$NON-NLS-1$
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
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		ComboBoxPropertyDescriptor opaqueD = new ComboBoxPropertyDescriptor(
				JRBaseStyle.PROPERTY_MODE, Messages.MCell_opaque,
				EnumHelper.getEnumNames(ModeEnum.values(), NullEnum.NOTNULL));
		opaqueD.setDescription(Messages.MCell_opaque_description);
		desc.add(opaqueD);

		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(
				JRBaseStyle.PROPERTY_BACKCOLOR, Messages.MCell_backcolor,
				NullEnum.INHERITED);
		backcolorD.setDescription(Messages.MCell_backcolor_description);
		desc.add(backcolorD);

		styleD = new RWComboBoxPropertyDescriptor(
				JRDesignCellContents.PROPERTY_STYLE,
				Messages.MCell_parent_style, new String[] { "" }, //$NON-NLS-1$
				NullEnum.NULL);
		styleD.setDescription(Messages.MCell_parent_style_description);
		desc.add(styleD);

		IntegerPropertyDescriptor wD = new IntegerPropertyDescriptor(
				JRDesignCrosstabCell.PROPERTY_WIDTH, Messages.common_width);
		desc.add(wD);

		IntegerPropertyDescriptor hD = new IntegerPropertyDescriptor(
				JRDesignCrosstabCell.PROPERTY_HEIGHT, Messages.common_height);
		desc.add(hD);

		BoxPropertyDescriptor lineBoxD = new BoxPropertyDescriptor(LINE_BOX,
				Messages.MCell_line_box);
		lineBoxD.setDescription(Messages.MCell_line_box_description);
		desc.add(lineBoxD);

		defaultsMap.put(JRBaseStyle.PROPERTY_MODE,
				EnumHelper.getValue(ModeEnum.OPAQUE, 1, false));
		defaultsMap.put(JRBaseStyle.PROPERTY_BACKCOLOR, null);
		defaultsMap.put(JRDesignCellContents.PROPERTY_STYLE, null);
	}

	public static final String LINE_BOX = "LineBox"; //$NON-NLS-1$
	private MLineBox lineBox;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignCellContents jrElement = (JRDesignCellContents) getValue();
		if (jrElement != null) {
			if (id.equals(JRBaseStyle.PROPERTY_MODE))
				return EnumHelper.getValue(jrElement.getModeValue(), 1, false);
			if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR))
				return Colors.getSWTRGB4AWTGBColor(jrElement.getBackcolor());
			if (id.equals(JRDesignCellContents.PROPERTY_STYLE)) {
				if (jrElement.getStyleNameReference() != null)
					return jrElement.getStyleNameReference();
				if (jrElement.getStyle() != null)
					return jrElement.getStyle().getName();
				return ""; //$NON-NLS-1$
			}
			if (id.equals(JRDesignCrosstabCell.PROPERTY_WIDTH))
				return jrElement.getWidth();
			if (id.equals(JRDesignCrosstabCell.PROPERTY_HEIGHT))
				return jrElement.getHeight();
			if (id.equals(LINE_BOX)) {
				JRBoxContainer jrGraphicElement = (JRBoxContainer) getValue();
				if (lineBox == null) {
					lineBox = new MLineBox(jrGraphicElement.getLineBox());
					lineBox.getPropertyChangeSupport()
							.addPropertyChangeListener(this);
				}
				return lineBox;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignCellContents jrElement = (JRDesignCellContents) getValue();
		if (jrElement != null) {
			if (id.equals(JRBaseStyle.PROPERTY_MODE))
				jrElement.setMode((ModeEnum) EnumHelper.getSetValue(
						ModeEnum.values(), value, 1, false));
			else if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR)) {
				jrElement.setBackcolor(Colors.getAWT4SWTRGBColor((RGB) value));
			} else if (id.equals(JRDesignCellContents.PROPERTY_STYLE)) {
				if (!value.equals("")) { //$NON-NLS-1$
					JRStyle style = (JRStyle) getJasperDesign().getStylesMap()
							.get(value);
					if (style != null) {
						jrElement.setStyle(style);
						jrElement.setStyleNameReference(null);
					} else {
						jrElement.setStyleNameReference((String) value);
						jrElement.setStyle(null);
					}
				}
			} else if (id.equals(JRDesignCrosstabCell.PROPERTY_WIDTH)) {
				MCrosstab cross = getMCrosstab();

				cross.getCrosstabManager().setWidth(jrElement, (Integer) value);

				cross.getCrosstabManager().refresh();
				getPropertyChangeSupport().firePropertyChange(
						new PropertyChangeEvent(this,
								JRDesignCrosstabCell.PROPERTY_WIDTH, null,
								value));

			} else if (id.equals(JRDesignCrosstabCell.PROPERTY_HEIGHT)) {
				MCrosstab cross = getMCrosstab();

				cross.getCrosstabManager()
						.setHeight(jrElement, (Integer) value);

				cross.getCrosstabManager().refresh();
				getPropertyChangeSupport().firePropertyChange(
						new PropertyChangeEvent(this,
								JRDesignCrosstabCell.PROPERTY_HEIGHT, null,
								value));

			}

		}
	}

	public int getDefaultWidth() {
		return 20;
	}

	public int getDefaultHeight() {
		return 20;
	}

	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}

	public MCrosstab getMCrosstab() {
		INode node = getParent();
		while (node != null) {
			if (node instanceof MCrosstab) {
				return (MCrosstab) node;
			}
			node = node.getParent();
		}
		return null;
	}

	public Rectangle getBounds() {
		int w = 0;
		int h = 0;
		Rectangle rCellBounds = new Rectangle();
		Rectangle rect = null;
		JRDesignCellContents c = null;
		if (getValue() != null) {
			c = (JRDesignCellContents) getValue();
			w = CrosstabManager.getHW(c.getWidth(), 60);
			h = CrosstabManager.getHW(c.getHeight(), 20);
		}

		MCrosstab mc = getMCrosstab();
		if (mc != null) {
			if (c != null)
				rCellBounds = mc.getCrosstabManager().getBounds(c);
			Rectangle b = mc.getBounds();
			return new Rectangle(b.x + rCellBounds.x, b.y + rCellBounds.y, w, h);
		}

		return rect;
	}

	public JRBoxContainer getBoxContainer() {
		return (JRBoxContainer) getValue();
	}

	public int getTopPadding() {
		JRDesignCellContents c = null;
		if (getValue() != null) {
			c = (JRDesignCellContents) getValue();
			return c.getLineBox().getTopPadding();
		}
		return 0;
	}

	public int getLeftPadding() {
		JRDesignCellContents c = null;
		if (getValue() != null) {
			c = (JRDesignCellContents) getValue();
			return c.getLineBox().getLeftPadding();
		}
		return 0;
	}

	public MCrosstab getCrosstab() {
		INode node = this;
		while (node != null && node.getParent() != null
				&& !(node instanceof MCrosstab) && !(node instanceof MRoot)) {
			node = node.getParent();
		}
		if (node instanceof MCrosstab)
			return (MCrosstab) node;
		return null;
	}
}
