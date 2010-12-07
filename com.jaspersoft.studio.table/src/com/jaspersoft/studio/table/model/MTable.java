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

import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MDatasetRun;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.table.TableManager;
import com.jaspersoft.studio.table.TableNodeIconDescriptor;

public class MTable extends MGraphicElement implements IContainer, IContainerEditPart, IGroupElement {
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
	 *          the parent
	 * @param jrCrosstab
	 *          the jr chart
	 * @param newIndex
	 *          the new index
	 */
	public MTable(ANode parent, JRDesignComponentElement jrCrosstab, int newIndex, TableManager ctManager) {
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

		JRPropertyDescriptor datasetRunD = new JRPropertyDescriptor(StandardTable.PROPERTY_DATASET_RUN, Messages.MTable_dataset_run);
		datasetRunD.setDescription(Messages.MTable_dataset_run_description);
		datasetRunD.setCategory(Messages.MTable_properties_category);
		desc.add(datasetRunD);

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

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// JRDesignCrosstab jrElement = (JRDesignCrosstab) getValue();
		//
		// if (id.equals(JRDesignCrosstab.PROPERTY_REPEAT_COLUMN_HEADERS))
		// jrElement.setRepeatColumnHeaders((Boolean) value);
		// else if (id.equals(JRDesignCrosstab.PROPERTY_REPEAT_ROW_HEADERS))
		// jrElement.setRepeatRowHeaders((Boolean) value);
		// else if (id.equals(JRDesignCrosstab.PROPERTY_IGNORE_WIDTH))
		// jrElement.setIgnoreWidth((Boolean) value);
		// else if (id.equals(JRDesignCrosstab.PROPERTY_COLUMN_BREAK_OFFSET))
		// jrElement.setColumnBreakOffset((Integer) value);
		// if (id.equals(JRBaseCrosstab.PROPERTY_RUN_DIRECTION)) {
		// jrElement.setRunDirection((RunDirectionEnum) EnumHelper.getSetValue(RunDirectionEnum.values(), value, 0, false));
		//
		// getTableManager().refresh();
		// getPropertyChangeSupport().firePropertyChange(
		// new PropertyChangeEvent(this, JRBaseCrosstab.PROPERTY_RUN_DIRECTION, null, value));
		// } else if (id.equals(JRDesignCrosstab.PROPERTY_PARAMETERS_MAP_EXPRESSION)) {
		// if (value instanceof MExpression) {
		// prmMapExpr = (MExpression) value;
		// JRExpression expression = (JRExpression) prmMapExpr.getValue();
		// jrElement.setParametersMapExpression(expression);
		// }
		// } else
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

	public JRDesignElement createJRElement(JasperDesign jasperDesign, byte chartType) {
		// JRDesignElement jrDesignElement = new JRDesignCrosstab(jasperDesign);
		// return jrDesignElement;
		return null;
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

	@Override
	public void setValue(Object value) {
		// JRCrosstab oldObject = (JRCrosstab) getValue();
		// JRCrosstab newObject = (JRCrosstab) value;
		//
		// if (oldObject != null) {
		// JRCellContents headerCell = oldObject.getHeaderCell();
		// if (headerCell != null)
		// ((JRDesignCellContents) headerCell).getEventSupport().removePropertyChangeListener(this);
		// }
		// if (newObject != null) {
		//
		// JRCellContents headerCell = newObject.getHeaderCell();
		// if (headerCell != null)
		// ((JRDesignCellContents) headerCell).getEventSupport().addPropertyChangeListener(this);
		// }
		super.setValue(value);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		// if (evt.getPropertyName().equals(JRDesignCrosstab.PROPERTY_HEADER_CELL)) {
		// if (evt.getSource() == getValue()) {
		// if (evt.getOldValue() != null && evt.getNewValue() == null) {
		// List<INode> child = this.getChildren();
		// for (INode n : child) {
		// if (n instanceof MCrosstabHeader)
		// ((MCrosstabHeader) n).setValue(null);
		// }
		// } else {
		// // add the node to this parent
		// List<INode> child = this.getChildren();
		// for (INode n : child) {
		// if (n instanceof MCrosstabHeader) {
		// ((MCrosstabHeader) n).setValue(evt.getNewValue());
		// break;
		// }
		// }
		// }
		// }
		// } else if (evt.getPropertyName().equals(JRDesignCrosstab.PROPERTY_WHEN_NO_DATA_CELL)) {
		// if (evt.getSource() == getValue()) {
		// if (evt.getOldValue() != null && evt.getNewValue() == null) {
		// List<INode> child = this.getChildren();
		// for (INode n : child) {
		// if (n instanceof MCrosstabWhenNoData)
		// ((MCrosstabWhenNoData) n).setValue(null);
		// }
		// } else {
		// // add the node to this parent
		// List<INode> child = this.getChildren();
		// for (INode n : child) {
		// if (n instanceof MCrosstabWhenNoData) {
		// ((MCrosstabWhenNoData) n).setValue(evt.getNewValue());
		// break;
		// }
		// }
		// }
		// }
		// } else if (evt.getPropertyName().equals(JRDesignCrosstab.PROPERTY_CELLS)) {
		// if (evt.getSource() == getValue() && getValue() != null && !flagRefreshCells) {
		// flagRefreshCells = true;
		// final CrosstabComponentFactory crosstabComponentFactory = new CrosstabComponentFactory();
		// crosstabComponentFactory.deleteCellNodes(MTable.this);
		// Display.getCurrent().asyncExec(new Runnable() {
		// public void run() {
		// crosstabComponentFactory.createCellNodes((JRDesignCrosstab) getValue(), MTable.this);
		// flagRefreshCells = false;
		// MTable.super.propertyChange(evt);
		// }
		// });
		// }
		// }
		super.propertyChange(evt);
	}

	private boolean flagRefreshCells = false;

	public JRElementGroup getJRElementGroup() {
		return null;
	}

}
