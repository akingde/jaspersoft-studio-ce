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
package com.jaspersoft.studio.table.model;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.table.TableNodeIconDescriptor;

public class MColumn extends APropertyNode implements IPastable, IContainer, IContainerEditPart {

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
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MColumn(ANode parent, StandardBaseColumn column, String name) {
		super(parent, -1);
		setValue(column);
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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		JRExpressionPropertyDescriptor printWhenExprD = new JRExpressionPropertyDescriptor(
				StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.MColumn_print_when_expression);
		printWhenExprD
				.setDescription(Messages.MColumn_print_when_expression_description);
		desc.add(printWhenExprD);

		IntegerPropertyDescriptor wD = new IntegerPropertyDescriptor(StandardBaseColumn.PROPERTY_WIDTH, Messages.MColumn_column_width);
		desc.add(wD);

		printWhenExprD.setCategory(Messages.MColumn_properties_category);
		wD.setCategory(Messages.MColumn_properties_category);
	}

	private MExpression mExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		StandardBaseColumn jrElement = (StandardBaseColumn) getValue();
		if (id.equals(StandardBaseColumn.PROPERTY_WIDTH))
			return jrElement.getWidth();
		if (id.equals(StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION)) {
			if (mExpression == null)
				mExpression = new MExpression(jrElement.getPrintWhenExpression());
			return mExpression;
		}
		return null;
	}

	private boolean canSet = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		StandardBaseColumn jrElement = (StandardBaseColumn) getValue();

		if (id.equals(StandardBaseColumn.PROPERTY_WIDTH)) {
			if ((Integer) value >= 0 && canSet) {
				canSet = false;
				MTable cross = getMTable();

				cross.getTableManager().setWidth(jrElement, (Integer) value);

				cross.getTableManager().refresh();
				getPropertyChangeSupport().firePropertyChange(
						new PropertyChangeEvent(this, StandardBaseColumn.PROPERTY_WIDTH, null, value));
				canSet = true;
			}
		} else if (id.equals(StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION)) {
			if (value instanceof MExpression) {
				mExpression = (MExpression) value;
				JRExpression expression = (JRExpression) mExpression.getValue();
				jrElement.setPrintWhenExpression(expression);
			}
		}
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

}