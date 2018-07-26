/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.callout.pin.IPinContainer;
import com.jaspersoft.studio.components.crosstab.CrosstabComponentFactory;
import com.jaspersoft.studio.components.crosstab.CrosstabManager;
import com.jaspersoft.studio.components.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.header.MCrosstabHeader;
import com.jaspersoft.studio.components.crosstab.model.header.MCrosstabHeaderCell;
import com.jaspersoft.studio.components.crosstab.model.nodata.MCrosstabWhenNoData;
import com.jaspersoft.studio.components.crosstab.model.nodata.MCrosstabWhenNoDataCell;
import com.jaspersoft.studio.components.crosstab.model.title.MTitle;
import com.jaspersoft.studio.components.crosstab.model.title.MTitleCell;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.VerticalRowLayout;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.dataset.descriptor.DatasetRunPropertyDescriptor;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSComboPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.crosstabs.CrosstabColumnCell;
import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.crosstabs.base.JRBaseCrosstab;
import net.sf.jasperreports.crosstabs.design.DesignCrosstabColumnCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabDataset;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.RunDirectionEnum;

public class MCrosstab extends MGraphicElementLineBox
		implements IContainer, IContainerEditPart, IGroupElement, IContainerLayout, IDatasetContainer, IPinContainer {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String UPDATE_CROSSTAB_MODEL = "updateCrosstabModel";

	private boolean flagRefreshCells = false;

	private static NamedEnumPropertyDescriptor<RunDirectionEnum> runDirectionD;

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private static IPropertyDescriptor[] descriptors;

	private CrosstabManager ctManager;

	private MCrosstabDataset mCrosstabDataset;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CrosstabNodeIconDescriptor("crosstab"); //$NON-NLS-1$
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

	public CrosstabManager getCrosstabManager() {
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
	public MCrosstab(ANode parent, JRCrosstab jrCrosstab, int newIndex, CrosstabManager ctManager) {
		super(parent, newIndex);
		setValue(jrCrosstab);
		this.ctManager = ctManager;
	}

	@Override
	public JRDesignCrosstab getValue() {
		return (JRDesignCrosstab) super.getValue();
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		runDirectionD = new NamedEnumPropertyDescriptor<RunDirectionEnum>(JRBaseCrosstab.PROPERTY_RUN_DIRECTION,
				Messages.MCrosstab_run_direction, RunDirectionEnum.LTR, NullEnum.NOTNULL);
		runDirectionD.setDescription(Messages.MCrosstab_run_direction_description);
		desc.add(runDirectionD);

		JRExpressionPropertyDescriptor paramMapExprD = new JRExpressionPropertyDescriptor(
				JRDesignCrosstab.PROPERTY_PARAMETERS_MAP_EXPRESSION, Messages.MCrosstab_parameter_map_expression);
		paramMapExprD.setDescription(Messages.MCrosstab_parameter_map_expression_description);
		paramMapExprD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#parametersMapExpression")); //$NON-NLS-1$
		desc.add(paramMapExprD);

		CheckBoxPropertyDescriptor repeatColumnHeadersD = new CheckBoxPropertyDescriptor(
				JRDesignCrosstab.PROPERTY_REPEAT_COLUMN_HEADERS, Messages.MCrosstab_repeat_column_headers,
				NullEnum.NOTNULL);
		repeatColumnHeadersD.setDescription(Messages.MCrosstab_repeat_column_headers_description);
		desc.add(repeatColumnHeadersD);

		CheckBoxPropertyDescriptor repeatRowHeadersD = new CheckBoxPropertyDescriptor(
				JRDesignCrosstab.PROPERTY_REPEAT_ROW_HEADERS, Messages.MCrosstab_repeat_row_headers, NullEnum.NOTNULL);
		repeatRowHeadersD.setDescription(Messages.MCrosstab_repeat_row_headers_description);
		desc.add(repeatRowHeadersD);

		CheckBoxPropertyDescriptor ignoreWidthD = new CheckBoxPropertyDescriptor(JRDesignCrosstab.PROPERTY_IGNORE_WIDTH,
				Messages.MCrosstab_ignore_witdh, NullEnum.NULL);
		ignoreWidthD.setDescription(Messages.MCrosstab_ignore_witdh_description);
		desc.add(ignoreWidthD);

		IntegerPropertyDescriptor columnBreakOffsetD = new IntegerPropertyDescriptor(
				JRDesignCrosstab.PROPERTY_COLUMN_BREAK_OFFSET, Messages.MCrosstab_column_break_offset);
		columnBreakOffsetD.setDescription(Messages.MCrosstab_column_break_offset_description);
		desc.add(columnBreakOffsetD);

		DatasetRunPropertyDescriptor datasetD = new DatasetRunPropertyDescriptor(JRDesignCrosstab.PROPERTY_DATASET,
				Messages.MCrosstab_dataset);
		datasetD.setDescription(Messages.MCrosstab_dataset_description);
		desc.add(datasetD);

		JSSComboPropertyDescriptor horizongalPositionD = new JSSComboPropertyDescriptor(
				JRBaseCrosstab.PROPERTY_HORIZONTAL_POSITION, Messages.MCrosstab_horizontalposition,
				HorizontalPositionUtil.getItems());
		horizongalPositionD.setDescription(Messages.MCrosstab_horizontalposition);
		desc.add(horizongalPositionD);

		CheckBoxPropertyDescriptor columnsFillDescriptor = new CheckBoxPropertyDescriptor(
				MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, Messages.MCrosstab_columnFitName);
		columnsFillDescriptor.setDescription(Messages.MCrosstab_columnFitDescription);
		desc.add(columnsFillDescriptor);

		horizongalPositionD.setCategory(Messages.MCrosstab_crosstab_properties_category);
		datasetD.setCategory(Messages.MCrosstab_crosstab_properties_category);
		repeatColumnHeadersD.setCategory(Messages.MCrosstab_crosstab_properties_category);
		repeatRowHeadersD.setCategory(Messages.MCrosstab_crosstab_properties_category);
		ignoreWidthD.setCategory(Messages.MCrosstab_crosstab_properties_category);
		columnBreakOffsetD.setCategory(Messages.MCrosstab_crosstab_properties_category);
		runDirectionD.setCategory(Messages.MCrosstab_crosstab_properties_category);
		paramMapExprD.setCategory(Messages.MCrosstab_crosstab_properties_category);
		columnsFillDescriptor.setCategory(Messages.MCrosstab_crosstab_properties_category);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#crosstab"); //$NON-NLS-1$
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();

		defaultsMap.put(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, new DefaultValue(Boolean.FALSE, false));

		return defaultsMap;
	}

	@Override
	public void setGroupItems(String[] items) {
		if (mCrosstabDataset != null)
			mCrosstabDataset.setGroupItems(items);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignCrosstab jrElement = (JRDesignCrosstab) getValue();

		if (id.equals(JRDesignCrosstab.PROPERTY_REPEAT_COLUMN_HEADERS))
			return jrElement.isRepeatColumnHeaders();
		if (id.equals(JRBaseCrosstab.PROPERTY_HORIZONTAL_POSITION))
			return HorizontalPositionUtil.getPos4TextPosition(jrElement.getHorizontalPosition());
		if (id.equals(JRDesignCrosstab.PROPERTY_REPEAT_ROW_HEADERS))
			return jrElement.isRepeatRowHeaders();
		if (id.equals(JRDesignCrosstab.PROPERTY_IGNORE_WIDTH))
			return jrElement.getIgnoreWidth();
		if (id.equals(JRDesignCrosstab.PROPERTY_COLUMN_BREAK_OFFSET))
			return jrElement.getColumnBreakOffset();
		if (id.equals(JRBaseCrosstab.PROPERTY_RUN_DIRECTION))
			return runDirectionD.getIntValue(jrElement.getRunDirectionValue());
		if (id.equals(JRDesignCrosstab.PROPERTY_PARAMETERS_MAP_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getParametersMapExpression());

		if (id.equals(JRDesignCrosstab.PROPERTY_DATASET)) {
			if (mCrosstabDataset == null) {
				mCrosstabDataset = new MCrosstabDataset(jrElement.getDataset(), getJasperDesign());
				setChildListener(mCrosstabDataset);
			}
			return mCrosstabDataset;
		}

		if (id.equals(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL)) {
			return hasColumnsAutoresizeProportional();
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
		else if (id.equals(JRBaseCrosstab.PROPERTY_HORIZONTAL_POSITION))
			jrElement.setHorizontalPosition(HorizontalPositionUtil.getTextPosition4Pos((Integer) value));
		else if (id.equals(JRDesignCrosstab.PROPERTY_IGNORE_WIDTH))
			jrElement.setIgnoreWidth((Boolean) value);
		else if (id.equals(JRDesignCrosstab.PROPERTY_COLUMN_BREAK_OFFSET))
			jrElement.setColumnBreakOffset((Integer) value);
		if (id.equals(JRBaseCrosstab.PROPERTY_RUN_DIRECTION)) {
			jrElement.setRunDirection(runDirectionD.getEnumValue(value));

			getCrosstabManager().refresh();
			getPropertyChangeSupport().firePropertyChange(
					new PropertyChangeEvent(this, JRBaseCrosstab.PROPERTY_RUN_DIRECTION, null, value));
		} else if (id.equals(JRDesignCrosstab.PROPERTY_PARAMETERS_MAP_EXPRESSION)) {
			jrElement.setParametersMapExpression(ExprUtil.setValues(jrElement.getParametersMapExpression(), value));
		} else if (id.equals(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL)) {
			Object oldValue = getValue().getPropertiesMap()
					.getProperty(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL);
			Object newValue = null;
			if (value == null || !Boolean.parseBoolean(value.toString())) {
				getValue().getPropertiesMap().removeProperty(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL);
			} else {
				getValue().getPropertiesMap().setProperty(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL,
						value.toString());
				newValue = value;
			}
			propertyChange(
					new PropertyChangeEvent(this, MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, oldValue, newValue));
		} else if (id.equals(MGraphicElement.PROPERTY_ELEMENT_NAME)) {
			super.setPropertyValue(id, value);
			// fire the event to update the editor name, because the property of the name
			// could be changed
			firePropertyChange(
					new PropertyChangeEvent(getValue(), ReportContainer.RENAME_EDITOR_PROPERTY, false, true));
		} else
			super.setPropertyValue(id, value);
	}

	@Override
	public int getDefaultHeight() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_HEIGHT);
		return defaultValue != null ? (Integer) defaultValue : 200;
	}

	@Override
	public int getDefaultWidth() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_WIDTH);
		return defaultValue != null ? (Integer) defaultValue : 200;
	}

	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignCrosstab jrDesignElement = new JRDesignCrosstab(jasperDesign);
		JRDesignCrosstabDataset dataset = new JRDesignCrosstabDataset();
		dataset.setDatasetRun(new JRDesignDatasetRun());
		jrDesignElement.setDataset(dataset);

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), jrDesignElement);
		}
		jrDesignElement.getPropertiesMap().setProperty(ILayout.KEY, VerticalRowLayout.class.getName());
		return jrDesignElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		String name = getElementNameProperty();
		return getIconDescriptor().getTitle() + " " + Misc.nvl(name); //$NON-NLS-1$
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
			DesignCrosstabColumnCell tcc = (DesignCrosstabColumnCell) oldObject.getTitleCell();
			if (tcc != null) {
				tcc.getEventSupport().removePropertyChangeListener(this);
				JRCellContents titleCell = tcc.getCellContents();
				if (titleCell != null)
					((JRDesignCellContents) titleCell).getEventSupport().removePropertyChangeListener(this);
			}
			JRCellContents headerCell = oldObject.getHeaderCell();
			if (headerCell != null)
				((JRDesignCellContents) headerCell).getEventSupport().removePropertyChangeListener(this);
			JRCellContents wndCell = oldObject.getWhenNoDataCell();
			if (wndCell != null)
				((JRDesignCellContents) wndCell).getEventSupport().removePropertyChangeListener(this);
		}
		if (newObject != null) {
			DesignCrosstabColumnCell tcc = (DesignCrosstabColumnCell) newObject.getTitleCell();
			if (tcc != null) {
				tcc.getEventSupport().addPropertyChangeListener(this);
				JRCellContents titleCell = tcc.getCellContents();
				if (titleCell != null)
					((JRDesignCellContents) titleCell).getEventSupport().addPropertyChangeListener(this);
			}
			JRCellContents headerCell = newObject.getHeaderCell();
			if (headerCell != null)
				((JRDesignCellContents) headerCell).getEventSupport().addPropertyChangeListener(this);
			JRCellContents wndCell = newObject.getWhenNoDataCell();
			if (wndCell != null)
				((JRDesignCellContents) wndCell).getEventSupport().addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		if (getParent() == null || flagRefreshCells)
			return;

		String pname = evt.getPropertyName();
		Object newValue = evt.getNewValue();
		Object oldValue = evt.getOldValue();
		if (pname.equals(JRDesignElement.PROPERTY_WIDTH) || pname.equals(JRDesignElement.PROPERTY_HEIGHT)) {
			getValue().preprocess();
			getCrosstabManager().init(getValue());
		} else if (pname.equals(JRDesignCrosstab.PROPERTY_HEADER_CELL)) {
			if (evt.getSource() == getValue()) {
				if (oldValue != null && newValue == null) {
					List<INode> child = this.getChildren();
					for (int i = 0; i < child.size(); i++) {
						INode n = child.get(i);
						if (n instanceof MCrosstabHeaderCell) {
							removeChild((ANode) n);
							new MCrosstabHeader(this, i);
							break;
						}
					}
				} else {
					// add the node to this parent
					List<INode> child = this.getChildren();
					for (int i = 0; i < child.size(); i++) {
						INode n = child.get(i);
						if (n instanceof MCrosstabHeader) {
							removeChild((ANode) n);
							new MCrosstabHeaderCell(this, (JRCellContents) newValue, i);
							break;
						}
					}
				}
			}
			getCrosstabManager().refresh();
		} else if (getParent() instanceof MPage) {
			if (pname.equals(JRDesignCrosstab.PROPERTY_TITLE_CELL)) {
				if (evt.getSource() == getValue()) {
					if (oldValue != null && newValue == null) {
						List<INode> child = this.getChildren();
						for (int i = 0; i < child.size(); i++) {
							INode n = child.get(i);
							if (n instanceof MTitleCell) {
								removeChild((ANode) n);
								new MTitle(this, i);
								break;
							}
						}
					} else {
						// add the node to this parent
						List<INode> child = this.getChildren();
						for (int i = 0; i < child.size(); i++) {
							INode n = child.get(i);
							if (n instanceof MTitle) {
								removeChild((ANode) n);
								new MTitleCell(this, (CrosstabColumnCell) newValue, i);
								break;
							}
						}
					}
				}
				getCrosstabManager().refresh();
			} else if (pname.equals(JRDesignCrosstab.PROPERTY_WHEN_NO_DATA_CELL)) {
				if (evt.getSource() == getValue()) {
					if (oldValue != null && newValue == null) {
						List<INode> child = this.getChildren();
						for (int i = 0; i < child.size(); i++) {
							INode n = child.get(i);
							if (n instanceof MCrosstabWhenNoDataCell) {
								removeChild((ANode) n);
								new MCrosstabWhenNoData(this, i);
								break;
							}
						}
					} else {
						// add the node to this parent
						List<INode> child = this.getChildren();
						for (int i = 0; i < child.size(); i++) {
							INode n = child.get(i);
							if (n instanceof MCrosstabWhenNoData) {
								removeChild((ANode) n);
								new MCrosstabWhenNoDataCell(this, (JRCellContents) newValue, i);
								break;
							}
						}
					}
				}
				getCrosstabManager().refresh();
			} else if (pname.equals(JRDesignCrosstab.PROPERTY_CELLS)
					|| pname.equals(JRDesignCrosstab.PROPERTY_ROW_GROUPS)
					|| pname.equals(JRDesignCrosstab.PROPERTY_COLUMN_GROUPS)) {
				MCrosstab.super.propertyChange(evt);
				getCrosstabManager().refresh();
			} else if (pname.equals(JRDesignCrosstab.PROPERTY_MEASURES)) {
				getCrosstabManager().refresh();
			}
		}
		if (evt.getPropertyName().equals(UPDATE_CROSSTAB_MODEL)) {
			getValue().preprocess();
			CrosstabComponentFactory.deleteCellNodes(this);
			CrosstabComponentFactory.createCellNodes(getValue(), this);
			getCrosstabManager().refresh();
			setChangedProperty(true, evt);
		}
		if (getCrosstabManager() != null) {
			if (hasColumnsAutoresizeProportional() && isColumnsResizeEvent(evt)) {
				String oldAutoresizeValue = getValue().getPropertiesMap()
						.getProperty(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL);
				getValue().getPropertiesMap().removeProperty(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL);
				getCrosstabManager().fillSpace(getValue().getWidth(), true);
				getValue().getPropertiesMap().setProperty(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL,
						oldAutoresizeValue);
			}
		}
		super.propertyChange(evt);
	}

	public JRElementGroup getJRElementGroup() {
		return null;
	}

	@Override
	public JRPropertiesHolder[] getPropertyHolder() {
		return new JRPropertiesHolder[] { getValue() };
	}

	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRBaseStyle.PROPERTY_BACKCOLOR);
		result.add(JRDesignCellContents.PROPERTY_STYLE);
		result.add(JRBaseStyle.PROPERTY_MODE);
		result.add(JRDesignCellContents.PROPERTY_STYLE_NAME_REFERENCE);
		result.add(JRDesignCrosstabCell.PROPERTY_WIDTH);
		result.add(JRDesignCrosstabCell.PROPERTY_HEIGHT);
		result.add(JRDesignElement.PROPERTY_ELEMENT_GROUP);
		result.add(JRDesignCrosstab.PROPERTY_COLUMN_GROUPS);
		result.add(JRDesignCrosstab.PROPERTY_ROW_GROUPS);
		result.add(JRDesignCrosstab.PROPERTY_MEASURES);
		result.add(JRDesignCrosstab.PROPERTY_TITLE_CELL);
		result.add(JRDesignCrosstab.PROPERTY_HEADER_CELL);
		result.add(JRDesignCrosstab.PROPERTY_CELLS);
		result.add(JRDesignCrosstabGroup.PROPERTY_TOTAL_POSITION);
		return result;
	}

	@Override
	public List<MDatasetRun> getDatasetRunList() {
		List<MDatasetRun> datasetList = new ArrayList<MDatasetRun>();
		MCrosstabDataset crosstabDataset = (MCrosstabDataset) getPropertyValue(JRDesignCrosstab.PROPERTY_DATASET);
		datasetList.add((MDatasetRun) crosstabDataset.getPropertyValue(JRDesignElementDataset.PROPERTY_DATASET_RUN));
		return datasetList;
	}

	private void fillUsedStyles(List<INode> children, Map<String, List<ANode>> map) {
		for (INode node : children) {
			if (node instanceof ANode) {
				mergeElementStyle(map, ((ANode) node).getUsedStyles());
			}
		}
	}

	@Override
	public Map<String, List<ANode>> getUsedStyles() {
		Map<String, List<ANode>> result = super.getUsedStyles();
		fillUsedStyles(getChildren(), result);
		return result;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRDesignCrosstab jrSource = (JRDesignCrosstab) getValue();
		if (jrSource != null) {
			JRDesignCrosstab jrTarget = (JRDesignCrosstab) target;
			jrTarget.setRepeatColumnHeaders(jrSource.isRepeatColumnHeaders());
			jrTarget.setRepeatRowHeaders(jrSource.isRepeatRowHeaders());
			jrTarget.setHorizontalPosition(jrSource.getHorizontalPosition());
			jrTarget.setIgnoreWidth(jrSource.getIgnoreWidth());
			jrTarget.setColumnBreakOffset(jrSource.getColumnBreakOffset());
			jrTarget.setRunDirection(jrSource.getRunDirectionValue());
		}
	}

	@Override
	public boolean showChildren() {
		return getParent() instanceof MPage;
	}

	@Override
	public void createSubeditor() {
		CrosstabComponentFactory.createSubeditor(this);
	}

	@Override
	public ExpressionContext getExpressionContext() {
		return new ExpressionContext(getValue(), getJasperConfiguration());
	}

	@Override
	public ILayout getDefaultLayout() {
		return LayoutManager.getLayout(FreeLayout.class.getName());
	}

	/**
	 * Check if in the current crosstab is set the flag to autoresize the columns
	 * taking the space from the next one when it is drag and dropped
	 * 
	 * @return true if the resize of a column should take the space from the next
	 *         one, false otherwise
	 */
	public boolean hasColumnsAutoresizeProportional() {
		if (getValue() != null) {
			JRPropertiesMap map = getValue().getPropertiesMap();
			Object value = map.getProperty(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL);
			if (value != null) {
				return Boolean.parseBoolean(value.toString());
			}
		}
		return false;
	}

	/**
	 * Check if the passed event is a resize event of the column or of the crosstab
	 * 
	 * @param evt
	 *            a not null event
	 * @return true if the event has changed the width of the crosstab or of
	 *         something inside, or if it is the change of the column enablement
	 *         status
	 */
	private boolean isColumnsResizeEvent(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRDesignElement.PROPERTY_WIDTH)) {
			return true;
		}
		if (evt.getPropertyName().equals(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL)) {
			return true;
		}
		return false;
	}
}
