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
package com.jaspersoft.studio.table.model.column;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.ILineBox;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.box.BoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.table.model.MTable;
import com.jaspersoft.studio.table.util.TableColumnNumerator;

public class MCell extends MColumn implements IGraphicElement, IPastableGraphic, ILineBox {

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
	public MCell(ANode parent, StandardBaseColumn column, Cell cell, String name, int index) {
		super(parent, column, name, index);
		this.cell = (DesignCell) cell;
	}

	private DesignCell cell;

	public DesignCell getCell() {
		return cell;
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
		JasperDesign jasperDesign = getJasperDesign();
		if (jasperDesign != null) {
			if (styleD != null && cell != null) {
				JRStyle[] styles = jasperDesign.getStyles();
				String[] items = new String[styles.length + 1];
				items[0] = cell.getStyleNameReference() != null ? cell.getStyleNameReference() : "";
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
		super.createPropertyDescriptors(desc, defaultsMap);

		styleD = new RWComboBoxPropertyDescriptor(DesignCell.PROPERTY_STYLE, "Parent Style", new String[] { "" },
				NullEnum.NULL);
		styleD.setDescription("Name of the report level style to use as base style (see <style> element).");
		desc.add(styleD);

		IntegerPropertyDescriptor hD = new IntegerPropertyDescriptor(DesignCell.PROPERTY_HEIGHT, "Height");
		desc.add(hD);

		BoxPropertyDescriptor lineBoxD = new BoxPropertyDescriptor(LINE_BOX, "Line Box");
		lineBoxD.setDescription("Groups the properties of the pen used to draw lines or borders.");
		desc.add(lineBoxD);

		styleD.setCategory("Cell Properties");
		hD.setCategory("Cell Properties");
		lineBoxD.setCategory("Cell Properties");

		defaultsMap.put(DesignCell.PROPERTY_STYLE, null);
	}

	public static final String LINE_BOX = "LineBox";
	private MLineBox lineBox;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		if (cell != null) {
			if (id.equals(JRDesignCellContents.PROPERTY_STYLE)) {
				if (cell.getStyleNameReference() != null)
					return cell.getStyleNameReference();
				if (cell.getStyle() != null)
					return cell.getStyle().getName();
				return "";
			}

			if (id.equals(DesignCell.PROPERTY_HEIGHT))
				return cell.getHeight();
			if (id.equals(LINE_BOX)) {
				JRBoxContainer jrGraphicElement = (JRBoxContainer) cell;
				if (lineBox == null) {
					lineBox = new MLineBox(jrGraphicElement.getLineBox());
					lineBox.getPropertyChangeSupport().addPropertyChangeListener(this);
				}
				return lineBox;
			}
		}
		return super.getPropertyValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		if (cell != null) {
			if (id.equals(JRDesignCellContents.PROPERTY_STYLE)) {
				if (!value.equals("")) {
					JRStyle style = (JRStyle) getJasperDesign().getStylesMap().get(value);
					if (style != null) {
						cell.setStyle(style);
						cell.setStyleNameReference(null);
					} else {
						cell.setStyleNameReference((String) value);
						cell.setStyle(null);
					}
				}
			} else if (id.equals(DesignCell.PROPERTY_HEIGHT)) {
				MTable mtable = getMTable();

				mtable.getTableManager().setHeight(cell, (Integer) value, (StandardBaseColumn) getValue());

				mtable.getTableManager().refresh();
				TableColumnNumerator.renumerateColumnNames(mtable);
				getPropertyChangeSupport().firePropertyChange(
						new PropertyChangeEvent(this, DesignCell.PROPERTY_HEIGHT, null, value));
			}
		}
		super.setPropertyValue(id, value);
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

	public MTable getMTable() {
		INode node = getParent();
		while (node != null) {
			if (node instanceof MTable) {
				return (MTable) node;
			}
			node = node.getParent();
		}
		return null;
	}

	@Override
	public Color getForeground() {
		return ColorConstants.black;
	}

	public Rectangle getBounds() {
		int w = 0;
		int h = 0;
		Rectangle rCellBounds = new Rectangle();
		Rectangle rect = null;
		StandardBaseColumn c = null;
		if (getValue() != null) {
			c = (StandardBaseColumn) getValue();

			w = c.getWidth();
			if (cell != null)
				h = cell.getHeight();
		}

		MTable mc = getMTable();
		if (mc != null) {
			if (c != null)
				rCellBounds = mc.getTableManager().getBounds(w, cell, c);
			Rectangle b = mc.getBounds();
			return new Rectangle(b.x + rCellBounds.x, b.y + rCellBounds.y, w, h);
		}

		return rect;
	}

	public JRBoxContainer getBoxContainer() {
		return cell;
	}
}