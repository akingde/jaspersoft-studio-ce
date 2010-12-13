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
package com.jaspersoft.studio.model.sortfield;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.SortOrderEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MField.
 * 
 * @author Chicu Veaceslav
 */
public class MSortField extends APropertyNode implements ICopyable {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("sortfield"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MSortField() {
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
	public MSortField(ANode parent, JRDesignSortField jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRDesignSortField) getValue()).getName();
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
	private RComboBoxPropertyDescriptor nameD;

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
		if (nameD != null) {
			JRDesignDataset jrDataset = getDataSet();
			JRField[] fields = jrDataset.getFields();
			String[] items = new String[fields.length];
			for (int j = 0; j < fields.length; j++) {
				items[j] = fields[j].getName();
			}
			nameD.setItems(items);
		}
	}

	private JRDesignDataset dataset;

	protected JRDesignDataset getDataSet() {
		if (dataset != null)
			return dataset;
		ANode n = (ANode) getParent();
		while (true) {
			if (n == null)
				break;
			else if (n instanceof MDataset) {
				dataset = (JRDesignDataset) ((MDataset) n).getValue();
				break;
			}
			n = (ANode) n.getParent();
		}
		if (dataset == null)
			dataset = getJasperDesign().getMainDesignDataset();
		return dataset;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {

		nameD = new RComboBoxPropertyDescriptor(JRDesignSortField.PROPERTY_NAME, Messages.MSortField_name, new String[] { "" }); //$NON-NLS-2$
		nameD.setDescription(Messages.MSortField_name_description);
		desc.add(nameD);

		ComboBoxPropertyDescriptor orderD = new ComboBoxPropertyDescriptor(JRDesignSortField.PROPERTY_ORDER, Messages.MSortField_order,
				EnumHelper.getEnumNames(SortOrderEnum.values(), NullEnum.NOTNULL));
		orderD.setDescription(Messages.MSortField_order_description);
		desc.add(orderD);

		defaultsMap.put(JRDesignSortField.PROPERTY_ORDER, EnumHelper.getValue(SortOrderEnum.ASCENDING, 1, false));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignSortField jrField = (JRDesignSortField) getValue();
		if (id.equals(JRDesignSortField.PROPERTY_NAME))
			return jrField.getName();
		if (id.equals(JRDesignSortField.PROPERTY_ORDER))
			return EnumHelper.getValue(jrField.getOrderValue(), 1, false);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignSortField jrField = (JRDesignSortField) getValue();
		if (id.equals(JRDesignSortField.PROPERTY_NAME)) {
			if (!value.equals("")) { //$NON-NLS-1$
				jrField.setName((String) value);
			}
		} else if (id.equals(JasperDesign.PROPERTY_ORIENTATION))
			jrField.setOrder((SortOrderEnum) EnumHelper.getSetValue(SortOrderEnum.values(), value, 1, false));
	}

	/**
	 * Creates the jr field.
	 * 
	 * @param jrDataset
	 *          the jr dataset
	 * @return the jR design field
	 */
	public static JRDesignSortField createJRSortField(JRDesignDataset jrDataset) {
		JRDesignSortField jrDesignField = new JRDesignSortField();
		return jrDesignField;
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MSortFields)
			return true;
		return false;
	}

}
