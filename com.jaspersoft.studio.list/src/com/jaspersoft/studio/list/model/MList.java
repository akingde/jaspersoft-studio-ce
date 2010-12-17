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
package com.jaspersoft.studio.list.model;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.PrintOrderEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.list.ListNodeIconDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.model.MDatasetRun;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.ReportFactory;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MList extends MGraphicElement implements IPastable, IPastableGraphic, IContainer, IContainerEditPart,
		IGroupElement, ICopyable {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ListNodeIconDescriptor("list"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m list.
	 */
	public MList() {
		super();
	}

	/**
	 * Instantiates a new m list.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrList
	 *          the jr line
	 * @param newIndex
	 *          the new index
	 */
	public MList(ANode parent, JRDesignComponentElement jrList, int newIndex) {
		super(parent, newIndex);
		setValue(jrList);
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
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor stretchTypeD = new ComboBoxPropertyDescriptor(
				StandardListComponent.PROPERTY_PRINT_ORDER, Messages.MList_print_order, EnumHelper.getEnumNames(
						PrintOrderEnum.values(), NullEnum.NOTNULL));
		stretchTypeD.setDescription(Messages.MList_print_order_description);
		desc.add(stretchTypeD);
		stretchTypeD.setCategory(Messages.MList_properties_category);

		CheckBoxPropertyDescriptor ignoreWidthD = new CheckBoxPropertyDescriptor(
				StandardListComponent.PROPERTY_IGNORE_WIDTH, Messages.MList_ignore_width);
		ignoreWidthD.setDescription(Messages.MList_ignore_width_description);
		desc.add(ignoreWidthD);
		ignoreWidthD.setCategory(Messages.MList_properties_category);

		IntegerPropertyDescriptor heightD = new IntegerPropertyDescriptor(PREFIX + DesignListContents.PROPERTY_HEIGHT,
				Messages.MList_cell_height);
		heightD.setCategory(Messages.MList_properties_category);
		heightD.setDescription(Messages.MList_cell_height_description);
		desc.add(heightD);

		IntegerPropertyDescriptor widthD = new IntegerPropertyDescriptor(PREFIX + DesignListContents.PROPERTY_WIDTH,
				Messages.MList_cell_width);
		widthD.setCategory(Messages.MList_properties_category);
		widthD.setDescription(Messages.MList_cell_width_description);
		desc.add(widthD);

		JRPropertyDescriptor datasetRunD = new JRPropertyDescriptor(PREFIX + "DATASET_RUN", Messages.MList_dataset_run); //$NON-NLS-1$
		datasetRunD.setDescription(Messages.MList_dataset_run_description);
		datasetRunD.setCategory(Messages.MList_properties_category);
		desc.add(datasetRunD);

		defaultsMap.put(StandardListComponent.PROPERTY_IGNORE_WIDTH, new Boolean(false));
		defaultsMap.put(StandardListComponent.PROPERTY_PRINT_ORDER, PrintOrderEnum.HORIZONTAL);
	}

	private static final String PREFIX = "CONTENTS."; //$NON-NLS-1$

	private MDatasetRun mDatasetRun;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardListComponent jrList = (StandardListComponent) jrElement.getComponent();

		if (id.equals(StandardListComponent.PROPERTY_IGNORE_WIDTH))
			return jrList.getIgnoreWidth();
		if (id.equals(StandardListComponent.PROPERTY_PRINT_ORDER))
			return EnumHelper.getValue(jrList.getPrintOrderValue(), 1, false);

		if (id.equals(PREFIX + DesignListContents.PROPERTY_HEIGHT))
			return jrList.getContents().getHeight();
		if (id.equals(PREFIX + DesignListContents.PROPERTY_WIDTH))
			return jrList.getContents().getWidth();

		if (id.equals(PREFIX + "DATASET_RUN")) { //$NON-NLS-1$
			if (mDatasetRun == null) {
				JRDatasetRun j = jrList.getDatasetRun();
				if (j == null)
					j = new JRDesignDatasetRun();
				mDatasetRun = new MDatasetRun(j, getJasperDesign());
				setChildListener(mDatasetRun);
			}
			return mDatasetRun;

		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardListComponent jrList = (StandardListComponent) jrElement.getComponent();
		// JRDesignDatasetRun jrDataSetRun = (JRDesignDatasetRun) jrList.getDatasetRun();

		if (id.equals(StandardListComponent.PROPERTY_IGNORE_WIDTH))
			jrList.setIgnoreWidth((Boolean) value);
		else if (id.equals(StandardListComponent.PROPERTY_PRINT_ORDER))
			jrList.setPrintOrderValue((PrintOrderEnum) EnumHelper.getSetValue(PrintOrderEnum.values(), value, 1, false));
		else if (id.equals(PREFIX + DesignListContents.PROPERTY_HEIGHT))
			((DesignListContents) jrList.getContents()).setHeight((Integer) value);
		else if (id.equals(PREFIX + DesignListContents.PROPERTY_WIDTH))
			((DesignListContents) jrList.getContents()).setWidth((Integer) value);

		else
			super.setPropertyValue(id, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGraphicElement#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		StandardListComponent component = new StandardListComponent();
		component.setContents(new DesignListContents());
		el.setComponent(component);

		return el;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

	public JRElementGroup getJRElementGroup() {
		return getJRElementGroup(getValue());
	}

	private JRElementGroup getJRElementGroup(Object value) {
		JRElementGroup res = null;
		if (value != null) {
			JRDesignComponentElement jrElement = (JRDesignComponentElement) value;
			StandardListComponent jrList = (StandardListComponent) jrElement.getComponent();
			res = jrList.getContents();
		}
		return res;
	}

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			if (getJRElementGroup() instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) getJRElementGroup()).getEventSupport().removePropertyChangeListener(this);
		} else if (value != null) {
			JRElementGroup elementGroup = getJRElementGroup(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) elementGroup).getEventSupport().addPropertyChangeListener(this);
			super.setValue(value);
			return;
		}
		super.setValue(value);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRDesignElementGroup.PROPERTY_CHILDREN)) {
			if (evt.getSource() == getJRElementGroup()) {
				if (evt.getOldValue() == null && evt.getNewValue() != null) {
					int newIndex = -1;
					if (evt instanceof CollectionElementAddedEvent) {
						newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
					}
					// add the node to this parent
					ANode n = ReportFactory.createNode(this, evt.getNewValue(), newIndex);
					if (evt.getNewValue() instanceof JRElementGroup) {
						JRElementGroup jrFrame = (JRElementGroup) evt.getNewValue();
						ReportFactory.createElementsForBand(n, jrFrame.getChildren());
					}
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
		}
		PropertyChangeEvent newEvent = evt;
		if (!(evt.getSource() instanceof ANode))
			newEvent = new PropertyChangeEvent(this, evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
		getPropertyChangeSupport().firePropertyChange(newEvent);
	}

}
