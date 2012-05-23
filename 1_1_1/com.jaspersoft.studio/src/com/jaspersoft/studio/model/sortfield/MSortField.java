/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.model.sortfield;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSortField;
import net.sf.jasperreports.engine.type.SortFieldTypeEnum;
import net.sf.jasperreports.engine.type.SortOrderEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

/*
 * The Class MField.
 * 
 * @author Chicu Veaceslav
 */
public class MSortField extends APropertyNode implements ICopyable {
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
		if (getValue() != null) {
			JRDesignSortField sortField = (JRDesignSortField) getValue();
			if (sortField.getType().equals(SortFieldTypeEnum.FIELD))
				return MField.getIconDescriptor().getIcon16();
			if (sortField.getType().equals(SortFieldTypeEnum.VARIABLE))
				return MVariable.getIconDescriptor().getIcon16();
		}
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

	private IPropertyDescriptor[] descriptors;
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
		super.postDescriptors(descriptors);
		if (nameD != null) {
			JRDesignDataset jrDataset = getDataSet();
			if (getValue() != null) {
				JRDesignSortField sortField = (JRDesignSortField) getValue();
				String[] items = null;
				if (sortField.getType().equals(SortFieldTypeEnum.FIELD)) {
					JRField[] fields = jrDataset.getFields();
					items = new String[fields.length];
					for (int j = 0; j < fields.length; j++) {
						items[j] = fields[j].getName();
					}
				} else {
					JRVariable[] vars = jrDataset.getVariables();
					items = new String[vars.length];
					for (int j = 0; j < vars.length; j++) {
						items[j] = vars[j].getName();
					}
				}
				nameD.setItems(items);
			}
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
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {

		nameD = new RComboBoxPropertyDescriptor(JRDesignSortField.PROPERTY_NAME, Messages.common_name, new String[] { "" }); //$NON-NLS-1$
		nameD.setDescription(Messages.MSortField_name_description);
		desc.add(nameD);

		ComboBoxPropertyDescriptor typeD = new ComboBoxPropertyDescriptor(JRDesignSortField.PROPERTY_TYPE, "Type",
				EnumHelper.getEnumNames(SortFieldTypeEnum.values(), NullEnum.NOTNULL));
		typeD.setDescription("Sort field type");
		desc.add(typeD);

		ComboBoxPropertyDescriptor orderD = new ComboBoxPropertyDescriptor(JRDesignSortField.PROPERTY_ORDER,
				Messages.common_order, EnumHelper.getEnumNames(SortOrderEnum.values(), NullEnum.NOTNULL));
		orderD.setDescription(Messages.MSortField_order_description);
		desc.add(orderD);

		defaultsMap.put(JRDesignSortField.PROPERTY_ORDER, EnumHelper.getValue(SortOrderEnum.ASCENDING, 1, false));
		defaultsMap.put(JRDesignSortField.PROPERTY_TYPE, EnumHelper.getValue(SortFieldTypeEnum.FIELD, 0, false));
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
		if (id.equals(JRDesignSortField.PROPERTY_TYPE))
			return EnumHelper.getValue(jrField.getType(), 0, false);
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
		} else if (id.equals(JRDesignSortField.PROPERTY_ORDER))
			jrField.setOrder((SortOrderEnum) EnumHelper.getSetValue(SortOrderEnum.values(), value, 1, false));
		else if (id.equals(JRDesignSortField.PROPERTY_TYPE)) {
			SortFieldTypeEnum type = (SortFieldTypeEnum) EnumHelper.getSetValue(SortFieldTypeEnum.values(), value, 0, false);
			if (!type.equals(jrField.getType())) {
				jrField.setType(type);
				JRDesignDataset ds = getDataSet();
				String newName = "";
				if (type.equals(SortFieldTypeEnum.FIELD)) {
					List<JRField> f = ds.getFieldsList();
					if (!f.isEmpty())
						newName = f.get(0).getName();
				} else {
					List<JRVariable> f = ds.getVariablesList();
					if (!f.isEmpty())
						newName = f.get(0).getName();
				}
				jrField.setName(newName);
			}
		}
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
