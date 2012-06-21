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
package com.jaspersoft.studio.components.table.model.column;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.table.TableNodeIconDescriptor;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.util.TableColumnNumerator;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.IntegerPropertyDescriptor;

public class MColumn extends APropertyNode implements IPastable, IContainer,
		IGraphicElement, IContainerEditPart {
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
			iconDescriptor = new TableNodeIconDescriptor("tablecell"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MColumn() {
		super();
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
	public MColumn(ANode parent, StandardBaseColumn column, String name,
			int index) {
		super(parent, index);
		setValue(column);
		this.name = name;
		List<ANode> n = getAMCollection();
		if (n != null && !n.isEmpty()) {
			AMCollection aNode = (AMCollection) n.get(n.size() - 1);
			type = TableColumnSize.getType(aNode.getClass());
		}
	}

	private int type = TableUtil.TABLE_HEADER;

	@Override
	public StandardBaseColumn getValue() {
		return (StandardBaseColumn) super.getValue();
	}

	private String name;

	@Override
	public Color getForeground() {
		return ColorConstants.lightGray;
	}

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
		String oldValue = this.name;
		this.name = name;
		getPropertyChangeSupport().firePropertyChange("NAME", oldValue, name); //$NON-NLS-1$
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
		String tt = "";
		List<ANode> nodes = getAMCollection();
		for (int i = nodes.size() - 1; i >= 0; i--)
			tt += nodes.get(i).getDisplayText() + "\n";
		tt += "\t" + getIconDescriptor().getToolTip() + ": " + getDisplayText();
		return tt;
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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		JRExpressionPropertyDescriptor printWhenExprD = new JRExpressionPropertyDescriptor(
				StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION,
				Messages.MColumn_print_when_expression);
		printWhenExprD
				.setDescription(Messages.MColumn_print_when_expression_description);
		desc.add(printWhenExprD);

		IntegerPropertyDescriptor wD = new IntegerPropertyDescriptor(
				StandardBaseColumn.PROPERTY_WIDTH,
				Messages.MColumn_column_width);
		desc.add(wD);

		printWhenExprD.setCategory(Messages.MColumn_column_properties_category);
		wD.setCategory(Messages.MColumn_column_properties_category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		StandardBaseColumn jrElement = getValue();
		if (id.equals(StandardBaseColumn.PROPERTY_WIDTH))
			return jrElement.getWidth();
		if (id.equals(StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getPrintWhenExpression());
		return null;
	}

	private boolean canSet = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		StandardBaseColumn jrElement = getValue();

		if (id.equals(StandardBaseColumn.PROPERTY_WIDTH)) {
			if ((Integer) value >= 0 && canSet) {
				canSet = false;
				MTable cross = getMTable();

				cross.getTableManager().setWidth(jrElement, (Integer) value);

				cross.getTableManager().refresh();
				getPropertyChangeSupport()
						.firePropertyChange(
								new PropertyChangeEvent(this,
										StandardBaseColumn.PROPERTY_WIDTH,
										null, value));
				canSet = true;
			}
		} else if (id.equals(StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION))
			jrElement.setPrintWhenExpression(ExprUtil.setValues(
					jrElement.getPrintWhenExpression(), value, null));
	}

	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}

	public MTable getMTable() {
		ANode node = getParent();
		while (node != null) {
			if (node instanceof MTable) {
				return (MTable) node;
			}
			node = node.getParent();
		}
		return null;
	}

	private List<ANode> list;

	public List<ANode> getAMCollection() {
		if (list == null) {
			list = new ArrayList<ANode>();
			ANode node = getParent();
			while (node != null) {
				list.add(node);
				if (node instanceof AMCollection)
					return list;
				node = node.getParent();
			}
		}
		return list;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final AMCollection section = getSection();
		if (section != null) {
			if (evt.getPropertyName().equals(section.getCellEvent())) {
				if (evt.getSource() == this.getValue()) {

					final StandardBaseColumn bc = (StandardBaseColumn) evt
							.getSource();

					final ANode parent = (ANode) getParent();
					final MColumn child = this;
					final int newIndex = parent.getChildren().indexOf(this);

					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							parent.removeChild(child);

							section.createColumn(parent, bc, 122, newIndex);

							MTable mtable = (MTable) section.getParent();
							mtable.getTableManager().refresh();
							TableColumnNumerator.renumerateColumnNames(mtable);
							parent.propertyChange(evt);
						}
					});
				}
			}
		}
		super.propertyChange(evt);
	}

	public AMCollection getSection() {
		INode n = getParent();
		while (n != null) {
			if (n instanceof AMCollection)
				return (AMCollection) n;
			n = n.getParent();
		}
		return null;
	}

	public Rectangle getBounds() {
		int w = 0;
		int h = 0;
		Rectangle rCellBounds = new Rectangle();
		Rectangle rect = null;
		StandardBaseColumn c = null;
		if (getValue() != null) {
			c = getValue();

			w = c.getWidth();
		}
		MTable mc = getMTable();
		if (mc != null) {
			if (c != null)
				rCellBounds = mc.getTableManager().getBounds(w, c, type);
			Rectangle b = mc.getBounds();
			return new Rectangle(b.x + rCellBounds.x, b.y + rCellBounds.y, w, h);
		}
		return rect;
	}

	public int getDefaultWidth() {
		return 0;
	}

	public int getDefaultHeight() {
		return 0;
	}

}
