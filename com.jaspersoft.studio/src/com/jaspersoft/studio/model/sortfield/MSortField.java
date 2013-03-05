/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
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
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPToolBarEnum;
import com.jaspersoft.studio.utils.ModelUtils;

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
	private static RComboBoxPropertyDescriptor nameD;

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
	private static JSSEnumPropertyDescriptor typeD;
	private static JSSEnumPropertyDescriptor orderD;

	protected JRDesignDataset getDataSet() {
		if (dataset != null)
			return dataset;
		return ModelUtils.getDataset(this);
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

		typeD = new JSSEnumPropertyDescriptor(JRDesignSortField.PROPERTY_TYPE, "Type", SortFieldTypeEnum.class,
				NullEnum.NOTNULL) {
			public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
				Image[] images = new Image[] {
						JaspersoftStudioPlugin.getInstance().getImage("icons/resources/fields-sort-16.png"),
						JaspersoftStudioPlugin.getInstance().getImage("icons/resources/variables-sort-16.png") };
				return new SPToolBarEnum(parent, section, this, images, false);
			}
		};
		typeD.setDescription("Sort field type");
		desc.add(typeD);

		orderD = new JSSEnumPropertyDescriptor(JRDesignSortField.PROPERTY_ORDER, Messages.common_order,
				SortOrderEnum.class, NullEnum.NOTNULL) {
			public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
				Image[] images = new Image[] {
						JaspersoftStudioPlugin.getInstance().getImage("icons/resources/sort-number-column.png"),
						JaspersoftStudioPlugin.getInstance().getImage("icons/resources/sort-number-descending.png") };
				return new SPToolBarEnum(parent, section, this, images, false);
			}
		};
		orderD.setDescription(Messages.MSortField_order_description);
		desc.add(orderD);

		defaultsMap.put(JRDesignSortField.PROPERTY_ORDER, typeD.getEnumValue(SortOrderEnum.ASCENDING));
		defaultsMap.put(JRDesignSortField.PROPERTY_TYPE, orderD.getEnumValue(SortFieldTypeEnum.FIELD));

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#sortField");
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
			return orderD.getEnumValue(jrField.getOrderValue());
		if (id.equals(JRDesignSortField.PROPERTY_TYPE))
			return typeD.getEnumValue(jrField.getType());
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
				JRDesignDataset d = ModelUtils.getDataset(this);
				if (d != null) {
					// d.getSortFieldsMap().remove(jrField);
					// d.getSortFieldsMap().put(jrField.getName(), jrField);
				}
			}
		} else if (id.equals(JRDesignSortField.PROPERTY_ORDER))
			jrField.setOrder((SortOrderEnum) orderD.getEnumValue(value));
		else if (id.equals(JRDesignSortField.PROPERTY_TYPE)) {
			SortFieldTypeEnum type = (SortFieldTypeEnum) typeD.getEnumValue(value);
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
