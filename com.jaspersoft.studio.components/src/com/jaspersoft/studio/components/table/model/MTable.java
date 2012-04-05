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
package com.jaspersoft.studio.components.table.model;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.WhenNoDataTypeTableEnum;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.TableNodeIconDescriptor;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;

public class MTable extends MGraphicElement implements IContainer,
		IContainerEditPart, IGroupElement {
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
			iconDescriptor = new TableNodeIconDescriptor("table"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m chart.
	 */
	public MTable() {
		super();
	}

	public MTable(ANode parent, int newIndex, TableManager ctManager) {
		super(parent, newIndex);
		this.ctManager = ctManager;
	}

	private TableManager ctManager;

	public TableManager getTableManager() {
		return ctManager;
	}

	/**
	 * Instantiates a new m chart.
	 * 
	 * @param parent
	 *            the parent
	 * @param jrCrosstab
	 *            the jr chart
	 * @param newIndex
	 *            the new index
	 */
	public MTable(ANode parent, JRDesignComponentElement jrCrosstab,
			int newIndex, TableManager ctManager) {
		super(parent, newIndex);
		setValue(jrCrosstab);
		this.ctManager = ctManager;
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
		super.createPropertyDescriptors(desc, defaultsMap);

		JRPropertyDescriptor datasetRunD = new JRPropertyDescriptor(
				StandardTable.PROPERTY_DATASET_RUN, Messages.MTable_dataset_run);
		datasetRunD.setDescription(Messages.MTable_dataset_run_description);
		datasetRunD.setCategory(Messages.MTable_table_properties_category);
		desc.add(datasetRunD);

		ComboBoxPropertyDescriptor whennodataD = new ComboBoxPropertyDescriptor(
				StandardTable.PROPERTY_WHEN_NO_DATA_TYPE,
				Messages.MTable_whennodatalabel, EnumHelper.getEnumNames(
						WhenNoDataTypeTableEnum.values(), NullEnum.NULL));
		whennodataD.setDescription(Messages.MTable_whennodatadescription);
		desc.add(whennodataD);
		whennodataD.setCategory(Messages.MTable_table_properties_category);

		defaultsMap.put(StandardTable.PROPERTY_WHEN_NO_DATA_TYPE,
				EnumHelper.getValue(WhenNoDataTypeTableEnum.BLANK, 1, true));
	}

	private MDatasetRun mDatasetRun;

	@Override
	public void setGroupItems(String[] items) {
		// if (mCrosstabDataset != null)
		// mCrosstabDataset.setGroupItems(items);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardTable jrTable = (StandardTable) jrElement.getComponent();

		if (id.equals(StandardTable.PROPERTY_DATASET_RUN)) {
			if (mDatasetRun == null) {
				JRDatasetRun j = jrTable.getDatasetRun();
				if (j == null)
					j = new JRDesignDatasetRun();
				mDatasetRun = new MDatasetRun(j, getJasperDesign());
			}
			return mDatasetRun;

		}
		if (id.equals(StandardTable.PROPERTY_WHEN_NO_DATA_TYPE))
			return EnumHelper.getValue(jrTable.getWhenNoDataType(), 1, true);

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardTable jrTable = (StandardTable) jrElement.getComponent();

		if (id.equals(StandardTable.PROPERTY_WHEN_NO_DATA_TYPE))
			jrTable.setWhenNoDataType((WhenNoDataTypeTableEnum) EnumHelper
					.getSetValue(WhenNoDataTypeTableEnum.values(), value, 1,
							true));
		else if (id.equals(StandardTable.PROPERTY_DATASET_RUN)) {
			MDatasetRun mdr = (MDatasetRun) value;
			JRDesignDatasetRun dr = (JRDesignDatasetRun) mdr.getValue();
			if (dr.getDatasetName() != null)
				jrTable.setDatasetRun(dr);
			else
				jrTable.setDatasetRun(null);
		}
		super.setPropertyValue(id, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultHeight()
	 */
	@Override
	public int getDefaultHeight() {
		return 200;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultWidth()
	 */
	@Override
	public int getDefaultWidth() {
		return 200;
	}

	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement jrElement = new JRDesignComponentElement();
		StandardTable component = new StandardTable();

		// jrElement.setKey((String) wizardDescriptor.getProperty("basename"));
		((JRDesignComponentElement) jrElement).setComponent(component);
		((JRDesignComponentElement) jrElement)
				.setComponentKey(new ComponentKey(
						"http://jasperreports.sourceforge.net/jasperreports/components", "jr", "table")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		// JRDesignDataset newDataset = new JRDesignDataset(false);
		//		String name = "Table Dataset "; //$NON-NLS-1$
		// for (int i = 1;; i++) {
		// if (!jasperDesign.getDatasetMap().containsKey(name + i)) {
		// newDataset.setName(name + i);
		// break;
		// }
		// }
		//
		JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();
		//
		// datasetRun.setDatasetName(newDataset.getName());
		// JRDesignExpression exp = new JRDesignExpression();
		//		exp.setValueClassName("net.sf.jasperreports.engine.JRDataSource");// NOI18N //$NON-NLS-1$
		//		exp.setText("new net.sf.jasperreports.engine.JREmptyDataSource(1)");// NOI18N //$NON-NLS-1$
		//
		// datasetRun.setDataSourceExpression(exp);
		component.setDatasetRun(datasetRun);
		return jrElement;
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
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (getTableManager() != null)
			getTableManager().refresh();
		super.propertyChange(evt);
	}
}
