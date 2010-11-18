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
package com.jaspersoft.studio.crosstab.model;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.crosstabs.base.JRBaseCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.RunDirectionEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.crosstab.CrosstabComponentFactory;
import com.jaspersoft.studio.crosstab.CrosstabManager;
import com.jaspersoft.studio.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.crosstab.model.header.MCrosstabHeader;
import com.jaspersoft.studio.crosstab.model.nodata.MCrosstabWhenNoData;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MCrosstab extends MGraphicElement implements IContainer, IContainerEditPart, IGroupElement {
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CrosstabNodeIconDescriptor("crosstab");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m chart.
	 */
	public MCrosstab() {
		super();
	}

	public MCrosstab(ANode parent, int newIndex, CrosstabManager ctManager) {
		super(parent, newIndex);
		this.ctManager = ctManager;
	}

	private CrosstabManager ctManager;

	public CrosstabManager getCrosstabManager() {
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
	public MCrosstab(ANode parent, JRCrosstab jrCrosstab, int newIndex, CrosstabManager ctManager) {
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

		ComboBoxPropertyDescriptor runDirectionD = new ComboBoxPropertyDescriptor(JRBaseCrosstab.PROPERTY_RUN_DIRECTION,
				"Run Direction", EnumHelper.getEnumNames(RunDirectionEnum.values(), NullEnum.NOTNULL));
		runDirectionD.setDescription("Run direction.");
		desc.add(runDirectionD);

		JRExpressionPropertyDescriptor paramMapExprD = new JRExpressionPropertyDescriptor(
				JRDesignCrosstab.PROPERTY_PARAMETERS_MAP_EXPRESSION, "Parameters Map Expression");
		paramMapExprD.setDescription("Parameters map expression.");
		desc.add(paramMapExprD);

		CheckBoxPropertyDescriptor repeatColumnHeadersD = new CheckBoxPropertyDescriptor(
				JRDesignCrosstab.PROPERTY_REPEAT_COLUMN_HEADERS, "Repeat Column Headers", NullEnum.NOTNULL);
		repeatColumnHeadersD.setDescription("Repeat column headers.");
		desc.add(repeatColumnHeadersD);

		CheckBoxPropertyDescriptor repeatRowHeadersD = new CheckBoxPropertyDescriptor(
				JRDesignCrosstab.PROPERTY_REPEAT_ROW_HEADERS, "Repeat Row Headers", NullEnum.NOTNULL);
		repeatRowHeadersD.setDescription("Repeat row headers.");
		desc.add(repeatRowHeadersD);

		CheckBoxPropertyDescriptor ignoreWidthD = new CheckBoxPropertyDescriptor(JRDesignCrosstab.PROPERTY_IGNORE_WIDTH,
				"Ignore Width", NullEnum.NULL);
		ignoreWidthD.setDescription("Ignore width.");
		desc.add(ignoreWidthD);

		IntegerPropertyDescriptor columnBreakOffsetD = new IntegerPropertyDescriptor(
				JRDesignCrosstab.PROPERTY_COLUMN_BREAK_OFFSET, "Column Break Offset");
		columnBreakOffsetD.setDescription("Column break offset");
		desc.add(columnBreakOffsetD);

		JRPropertyDescriptor datasetD = new JRPropertyDescriptor(JRDesignCrosstab.PROPERTY_DATASET, "Dataset");
		datasetD.setDescription("Dataset.");
		desc.add(datasetD);

		datasetD.setCategory("Crosstab Properties");
		repeatColumnHeadersD.setCategory("Crosstab Properties");
		repeatRowHeadersD.setCategory("Crosstab Properties");
		ignoreWidthD.setCategory("Crosstab Properties");
		columnBreakOffsetD.setCategory("Crosstab Properties");
		runDirectionD.setCategory("Crosstab Properties");
		paramMapExprD.setCategory("Crosstab Properties");

	}

	@Override
	public void setGroupItems(String[] items) {
		if (mCrosstabDataset != null)
			mCrosstabDataset.setGroupItems(items);
	}

	private MCrosstabDataset mCrosstabDataset;

	private MExpression prmMapExpr;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignCrosstab jrElement = (JRDesignCrosstab) getValue();

		if (id.equals(JRDesignCrosstab.PROPERTY_REPEAT_COLUMN_HEADERS))
			return jrElement.isRepeatColumnHeaders();
		if (id.equals(JRDesignCrosstab.PROPERTY_REPEAT_ROW_HEADERS))
			return jrElement.isRepeatRowHeaders();
		if (id.equals(JRDesignCrosstab.PROPERTY_IGNORE_WIDTH))
			return jrElement.getIgnoreWidth();
		if (id.equals(JRDesignCrosstab.PROPERTY_COLUMN_BREAK_OFFSET))
			return jrElement.getColumnBreakOffset();
		if (id.equals(JRBaseCrosstab.PROPERTY_RUN_DIRECTION))
			return EnumHelper.getValue(jrElement.getRunDirectionValue(), 0, false);
		if (id.equals(JRDesignCrosstab.PROPERTY_PARAMETERS_MAP_EXPRESSION)) {
			if (prmMapExpr == null)
				prmMapExpr = new MExpression(jrElement.getParametersMapExpression());
			return prmMapExpr;
		}

		if (id.equals(JRDesignCrosstab.PROPERTY_DATASET)) {
			if (mCrosstabDataset == null)
				mCrosstabDataset = new MCrosstabDataset(jrElement.getDataset(), getJasperDesign());
			return mCrosstabDataset;
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignCrosstab jrElement = (JRDesignCrosstab) getValue();

		if (id.equals(JRDesignCrosstab.PROPERTY_REPEAT_COLUMN_HEADERS))
			jrElement.setRepeatColumnHeaders((Boolean) value);
		else if (id.equals(JRDesignCrosstab.PROPERTY_REPEAT_ROW_HEADERS))
			jrElement.setRepeatRowHeaders((Boolean) value);
		else if (id.equals(JRDesignCrosstab.PROPERTY_IGNORE_WIDTH))
			jrElement.setIgnoreWidth((Boolean) value);
		else if (id.equals(JRDesignCrosstab.PROPERTY_COLUMN_BREAK_OFFSET))
			jrElement.setColumnBreakOffset((Integer) value);
		if (id.equals(JRBaseCrosstab.PROPERTY_RUN_DIRECTION))
			jrElement.setRunDirection((RunDirectionEnum) EnumHelper.getSetValue(RunDirectionEnum.values(), value, 0, false));
		else if (id.equals(JRDesignCrosstab.PROPERTY_PARAMETERS_MAP_EXPRESSION)) {
			if (value instanceof MExpression) {
				prmMapExpr = (MExpression) value;
				JRExpression expression = (JRExpression) prmMapExpr.getValue();
				jrElement.setParametersMapExpression(expression);
			}
		} else
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
		JRDesignElement jrDesignElement = new JRDesignCrosstab(jasperDesign);
		return jrDesignElement;
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
		JRCrosstab oldObject = (JRCrosstab) getValue();
		JRCrosstab newObject = (JRCrosstab) value;

		if (oldObject != null) {
			JRCellContents headerCell = oldObject.getHeaderCell();
			if (headerCell != null)
				((JRDesignCellContents) headerCell).getEventSupport().removePropertyChangeListener(this);
		}
		if (newObject != null) {

			JRCellContents headerCell = newObject.getHeaderCell();
			if (headerCell != null)
				((JRDesignCellContents) headerCell).getEventSupport().addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRDesignCrosstab.PROPERTY_HEADER_CELL)) {
			if (evt.getSource() == getValue()) {
				if (evt.getOldValue() != null && evt.getNewValue() == null) {
					List<INode> child = this.getChildren();
					for (INode n : child) {
						if (n instanceof MCrosstabHeader)
							((MCrosstabHeader) n).setValue(null);
					}
				} else {
					// add the node to this parent
					List<INode> child = this.getChildren();
					for (INode n : child) {
						if (n instanceof MCrosstabHeader) {
							((MCrosstabHeader) n).setValue(evt.getNewValue());
							break;
						}
					}
				}
			}
		} else if (evt.getPropertyName().equals(JRDesignCrosstab.PROPERTY_WHEN_NO_DATA_CELL)) {
			if (evt.getSource() == getValue()) {
				if (evt.getOldValue() != null && evt.getNewValue() == null) {
					List<INode> child = this.getChildren();
					for (INode n : child) {
						if (n instanceof MCrosstabWhenNoData)
							((MCrosstabWhenNoData) n).setValue(null);
					}
				} else {
					// add the node to this parent
					List<INode> child = this.getChildren();
					for (INode n : child) {
						if (n instanceof MCrosstabWhenNoData) {
							((MCrosstabWhenNoData) n).setValue(evt.getNewValue());
							break;
						}
					}
				}
			}
		} else if (evt.getPropertyName().equals(JRDesignCrosstab.PROPERTY_CELLS)) {
			if (evt.getSource() == getValue() && getValue() != null && !flagRefreshCells) {
				flagRefreshCells = true;
				final CrosstabComponentFactory crosstabComponentFactory = new CrosstabComponentFactory();
				crosstabComponentFactory.deleteCellNodes(MCrosstab.this);
				Display.getCurrent().asyncExec(new Runnable() {
					public void run() {
						crosstabComponentFactory.createCellNodes((JRDesignCrosstab) getValue(), MCrosstab.this);
						flagRefreshCells = false;
						MCrosstab.super.propertyChange(evt);
					}
				});
			}
		}
		super.propertyChange(evt);
	}

	private boolean flagRefreshCells = false;

	public JRElementGroup getJRElementGroup() {
		// TODO Auto-generated method stub
		return null;
	}

}
