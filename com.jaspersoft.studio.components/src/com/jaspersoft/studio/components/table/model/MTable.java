/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.callout.pin.IPinContainer;
import com.jaspersoft.studio.components.section.name.NameSection;
import com.jaspersoft.studio.components.table.TableComponentFactory;
import com.jaspersoft.studio.components.table.TableDatasetRunProperyDescriptor;
import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.TableNodeIconDescriptor;
import com.jaspersoft.studio.components.table.TableSetValueCommandProvider;
import com.jaspersoft.studio.components.table.descriptor.FillContentPropertyDescriptor;
import com.jaspersoft.studio.components.table.descriptor.NextColumnPropertyDescriptor;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.VerticalRowLayout;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.IGraphicalPropertiesHandler;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.ISetValueCommandProvider;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.ColumnGroup;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.WhenNoDataTypeTableEnum;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;

public class MTable extends MGraphicElement implements IContainer, IContainerEditPart, IGroupElement, IContainerLayout, IDatasetContainer, IPinContainer{

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/**
	 * The property used to know if the resize of the column
	 * take the space from the following one is it is a standard resize
	 */
	public static final String PROPERTY_COLUMNS_AUTORESIZE_NEXT = "com.jaspersoft.studio.components.autoresize.next"; //$NON-NLS-1$

	/**
	 * The property used to know if the columns of the table are automatically resize if necessary to fit the table area, the width is distributed
	 * proportionally to the columns initial size
	 */
	public static final String PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL = "com.jaspersoft.studio.components.autoresize.proportional"; //$NON-NLS-1$
	
	private static IIconDescriptor iconDescriptor;
	
	private static IPropertyDescriptor[] descriptors;

	private static NamedEnumPropertyDescriptor<WhenNoDataTypeTableEnum> whennodataD;
	
	private TableManager ctManager;
	
	private MDatasetRun mDatasetRun;

	/**
	 * The dataset where the group listener was placed last
	 */
	private JRDesignDataset datasetWithListener = null;

	/**
	 * Listener put on the current dataset to refresh the group node on the
	 * tables when a group is added on removed on his dataset
	 */
	private PropertyChangeListener datasetGroupListener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(JRDesignDataset.PROPERTY_GROUPS)) {
				// this need to be done only inside the table editor
				if (evt.getNewValue() != null && evt.getOldValue() == null && getChildren().size() > 0) {
					MTableDetail detailNode = null;
					for (INode child : getChildren()) {
						if (detailNode == null && child instanceof MTableDetail) {
							detailNode = (MTableDetail) child;
							break;
						}
					}
					int detailIndex = getChildren().indexOf(detailNode);
					JRDesignGroup jrGroup = (JRDesignGroup) evt.getNewValue();
					MTableGroupHeader newHeader = new MTableGroupHeader(
							MTable.this, (JRDesignComponentElement) getValue(),
							jrGroup, ""); //$NON-NLS-1$
					addChild(newHeader, detailIndex);
					detailIndex += 2;
					MTableGroupFooter newFooter = new MTableGroupFooter(
							MTable.this, (JRDesignComponentElement) getValue(),
							jrGroup, ""); //$NON-NLS-1$
					addChild(newFooter, detailIndex);
					List<BaseColumn> columns = getStandardTable().getColumns();
					for (int i = 0; i < columns.size(); i++) {
						BaseColumn bc = columns.get(i);
						TableComponentFactory.createCellGroupHeader(newHeader,
								bc, i + 1, jrGroup.getName(), i);
						TableComponentFactory.createCellGroupFooter(newFooter,
								bc, i + 1, jrGroup.getName(), i);
					}
				} else if (evt.getNewValue() == null && evt.getOldValue() != null) {
					JRDesignGroup jrGroup = (JRDesignGroup) evt.getOldValue();
					deleteGroup(jrGroup.getName());
				}
				// Run an event on the table to force a grapghical refresh of
				// the columnss
				setChangedProperty(true);
				MTable.this.propertyChange(new PropertyChangeEvent(getValue(),
						StandardTable.PROPERTY_COLUMNS, null, null));
			}
		}
	};

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

	public TableManager getTableManager() {
		return ctManager;
	}

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
	 * 
	 * @param parent
	 *            the parent
	 * @param jrTable
	 *            the jr chart
	 * @param newIndex
	 *            the new index
	 */
	public MTable(ANode parent, JRDesignComponentElement jrTable, int newIndex,
			TableManager ctManager) {
		super(parent, newIndex);
		setValue(jrTable);
		this.ctManager = ctManager;
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

		TableDatasetRunProperyDescriptor datasetRunD = new TableDatasetRunProperyDescriptor(StandardTable.PROPERTY_DATASET_RUN,Messages.MTable_dataset_run, false);
		datasetRunD.setDescription(Messages.MTable_dataset_run_description);
		datasetRunD.setCategory(Messages.MTable_table_properties_category);
		desc.add(datasetRunD);

		whennodataD = new NamedEnumPropertyDescriptor<WhenNoDataTypeTableEnum>(
				StandardTable.PROPERTY_WHEN_NO_DATA_TYPE,
				Messages.MTable_whennodatalabel, WhenNoDataTypeTableEnum.BLANK,
				NullEnum.NULL);
		whennodataD.setDescription(Messages.MTable_whennodatadescription);
		desc.add(whennodataD);
		whennodataD.setCategory(Messages.MTable_table_properties_category);
		
		
		NextColumnPropertyDescriptor columnsIncreaseDescriptor = new NextColumnPropertyDescriptor(Messages.MTable_autoresizeNext);
		columnsIncreaseDescriptor.setDescription(Messages.MTable_autoresizeNextDescription);
		desc.add(columnsIncreaseDescriptor);
		columnsIncreaseDescriptor.setCategory(Messages.MTable_table_properties_category);
		
		FillContentPropertyDescriptor columnsFillDescriptor = new FillContentPropertyDescriptor(Messages.MTable_propertyForceFill);
		columnsFillDescriptor.setDescription(Messages.MTable_propertyForceFillDescription);
		desc.add(columnsFillDescriptor);
		columnsFillDescriptor.setCategory(Messages.MTable_table_properties_category);
		
		setHelpPrefix(desc,
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#table"); //$NON-NLS-1$
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		int whenNoDataValue = NamedEnumPropertyDescriptor.getIntValue(WhenNoDataTypeTableEnum.BLANK, NullEnum.NULL, WhenNoDataTypeTableEnum.BLANK);
		defaultsMap.put(StandardTable.PROPERTY_WHEN_NO_DATA_TYPE, new DefaultValue(whenNoDataValue, true));
		defaultsMap.put(PROPERTY_COLUMNS_AUTORESIZE_NEXT, new DefaultValue(Boolean.FALSE, false));
		defaultsMap.put(PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, new DefaultValue(Boolean.FALSE, false));
		
		return defaultsMap;
	}

	@Override
	public void setGroupItems(String[] items) {
		super.setGroupItems(items);
	}

	@Override
	public Object getPropertyValue(Object id) {
		StandardTable jrTable = getStandardTable();

		if (id.equals(StandardTable.PROPERTY_DATASET_RUN)) {
			JRDatasetRun j = jrTable.getDatasetRun();
			if (j == null)
				j = new JRDesignDatasetRun();
			if (mDatasetRun != null)
				mDatasetRun.setValue(j);
			else {
				mDatasetRun = new MDatasetRun(j, getJasperDesign());
				mDatasetRun.setJasperConfiguration(getJasperConfiguration());
				setChildListener(mDatasetRun);
			}
			return mDatasetRun;
		}
		if (id.equals(StandardTable.PROPERTY_WHEN_NO_DATA_TYPE)){
			return whennodataD.getIntValue(jrTable.getWhenNoDataType());
		}
		if (id.equals(PROPERTY_COLUMNS_AUTORESIZE_NEXT)){
			return hasColumnsAutoresizeNext();
		}
		if (id.equals(PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL)){
			return hasColumnsAutoresizeProportional();
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		StandardTable jrTable = getStandardTable();

		if (id.equals(StandardTable.PROPERTY_WHEN_NO_DATA_TYPE))
			jrTable.setWhenNoDataType((WhenNoDataTypeTableEnum) whennodataD
					.getEnumValue(value));
		else if (id.equals(StandardTable.PROPERTY_DATASET_RUN)) {
			MDatasetRun mdr = (MDatasetRun) value;
			JRDesignDatasetRun dr = (JRDesignDatasetRun) mdr.getValue();
			if (dr.getDatasetName() != null)
				jrTable.setDatasetRun(dr);
			else
				jrTable.setDatasetRun(null);
		} else if (id.equals(PROPERTY_COLUMNS_AUTORESIZE_NEXT)){
			Object oldValue = getValue().getPropertiesMap().getProperty(PROPERTY_COLUMNS_AUTORESIZE_NEXT);
			Object newValue = null;
			if (value == null || !Boolean.parseBoolean(value.toString())){
				getValue().getPropertiesMap().removeProperty(PROPERTY_COLUMNS_AUTORESIZE_NEXT);
			} else {
				getValue().getPropertiesMap().setProperty(PROPERTY_COLUMNS_AUTORESIZE_NEXT, value.toString());
				newValue = value;
			}
			//Triggering the event will refresh the UI
			propertyChange(new PropertyChangeEvent(this, PROPERTY_COLUMNS_AUTORESIZE_NEXT, oldValue, newValue));
		} else if (id.equals(PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL)){
			Object oldValue = getValue().getPropertiesMap().getProperty(PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL);
			Object newValue = null;
			if (value == null || !Boolean.parseBoolean(value.toString())){
				getValue().getPropertiesMap().removeProperty(PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL);
			} else {
				getValue().getPropertiesMap().setProperty(PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, value.toString());
				newValue = value;
			}
			//Triggering the event will refresh the UI
			propertyChange(new PropertyChangeEvent(this, PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, oldValue, newValue));
		} else if (id.equals(PROPERTY_MAP) || id.equals(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS)) {
			super.setPropertyValue(id, value);
			//fire the event to update the editor name, because the property of the name could be changed
			firePropertyChange(new PropertyChangeEvent(getValue(), ReportContainer.RENAME_EDITOR_PROPERTY, false, true));
		}
		else super.setPropertyValue(id, value);
	}

	/**
	 * Check if in the current table is set the flag to autoresize the columns taking the 
	 * space from the next one when it is drag and dropped
	 * 
	 * @return true if the resize of a column should take the space from the next one, false otherwise
	 */
	public boolean hasColumnsAutoresizeNext(){
		if (getValue() != null){
			JRPropertiesMap map = getValue().getPropertiesMap();
			Object value = map.getProperty(PROPERTY_COLUMNS_AUTORESIZE_NEXT);
			if (value != null){
				return Boolean.parseBoolean(value.toString());
			}
		}
		return false;
	}
	
	/**
	 * Check if in the current table is set the flag to autoresize the columns taking the 
	 * space from the next one when it is drag and dropped
	 * 
	 * @return true if the resize of a column should take the space from the next one, false otherwise
	 */
	public boolean hasColumnsAutoresizeProportional(){
		if (getValue() != null){
			JRPropertiesMap map = getValue().getPropertiesMap();
			Object value = map.getProperty(PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL);
			if (value != null){
				return Boolean.parseBoolean(value.toString());
			}
		}
		return false;
	}
	
	public StandardTable getStandardTable() {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardTable jrTable = (StandardTable) jrElement.getComponent();
		return jrTable;
	}

	@Override
	public int getDefaultHeight() {
		Object defaultValue = DefaultManager.INSTANCE
				.getDefaultPropertiesValue(this.getClass(),
						JRDesignElement.PROPERTY_HEIGHT);
		return defaultValue != null ? (Integer) defaultValue : 200;
	}

	@Override
	public int getDefaultWidth() {
		Object defaultValue = DefaultManager.INSTANCE
				.getDefaultPropertiesValue(this.getClass(),
						JRDesignElement.PROPERTY_WIDTH);
		return defaultValue != null ? (Integer) defaultValue : 200;
	}

	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement jrElement = new JRDesignComponentElement(jasperDesign);
		StandardTable component = new StandardTable();

		((JRDesignComponentElement) jrElement).setComponent(component);
		((JRDesignComponentElement) jrElement)
				.setComponentKey(new ComponentKey(
						"http://jasperreports.sourceforge.net/jasperreports/components", "jr", "table")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();
		component.setDatasetRun(datasetRun);

		DefaultManager.INSTANCE.applyDefault(this.getClass(), jrElement);
		jrElement.getPropertiesMap().setProperty(ILayout.KEY,
				VerticalRowLayout.class.getName());

		return jrElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		String name = getPropertiesMap().getProperty(
				NameSection.getNamePropertyId(this));
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

	public JRElementGroup getJRElementGroup() {
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (evt.getPropertyName().equals(StandardTable.PROPERTY_DATASET_RUN)) {
			addDatasetGroupListener();
		} else if (evt.getPropertyName().equals(MGraphicElement.FORCE_GRAPHICAL_REFRESH)) {
			ANode parent = getParent();
			IGraphicalPropertiesHandler upperGrahpicHandler = null;
			while (parent != null) {
				if (parent instanceof IGraphicalPropertiesHandler) {
					upperGrahpicHandler = (IGraphicalPropertiesHandler) parent;
				}
				parent = parent.getParent();
			}
			if (upperGrahpicHandler != null) {
				APropertyNode node = (APropertyNode) upperGrahpicHandler;
				((JRChangeEventsSupport)node.getValue()).getEventSupport().firePropertyChange(MGraphicElement.FORCE_GRAPHICAL_REFRESH, null, null);
			}
		}

		if (getTableManager() != null){
			getTableManager().update();
		}
	
		if (!(evt.getPropertyName().equals(StandardColumn.PROPERTY_TABLE_FOOTER) ||
				evt.getPropertyName().equals(StandardColumn.PROPERTY_TABLE_HEADER) ||
				evt.getPropertyName().equals(StandardColumn.PROPERTY_COLUMN_HEADER) ||
				evt.getPropertyName().equals(StandardColumn.PROPERTY_COLUMN_FOOTER) ||
				evt.getPropertyName().equals(StandardColumn.PROPERTY_GROUP_HEADERS) ||
				evt.getPropertyName().equals(StandardColumn.PROPERTY_GROUP_FOOTERS))){
			super.propertyChange(evt);
		} else {
			if (hasChangedProperty()){
				HashSet<String> graphicalProperties = getGraphicalProperties();
				if (graphicalProperties.contains(evt.getPropertyName())) {
					setChangedProperty(true);
				}
			}
		}
	}
	
	@Override
	public JRPropertiesHolder[] getPropertyHolder() {
		return new JRPropertiesHolder[] { getValue() };
	}

	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> properties = super.generateGraphicalProperties();
		properties.add(DesignCell.PROPERTY_DEFAULT_STYLE_PROVIDER);
		properties.add(DesignCell.PROPERTY_STYLE);
		properties.add(DesignCell.PROPERTY_STYLE_NAME_REFERENCE);
		properties.add(DesignCell.PROPERTY_ROW_SPAN);
		properties.add(DesignCell.PROPERTY_HEIGHT);
		properties.add(JRDesignElement.PROPERTY_ELEMENT_GROUP);
		properties.add(MColumn.PROPERTY_NAME);
		properties.add(StandardBaseColumn.PROPERTY_WIDTH);
		properties.add(StandardBaseColumn.PROPERTY_TABLE_HEADER);
		properties.add(StandardBaseColumn.PROPERTY_TABLE_FOOTER);
		properties.add(StandardBaseColumn.PROPERTY_COLUMN_HEADER);
		properties.add(StandardBaseColumn.PROPERTY_COLUMN_FOOTER);
		properties.add(StandardBaseColumn.PROPERTY_GROUP_HEADERS);
		properties.add(StandardBaseColumn.PROPERTY_GROUP_FOOTERS);
		properties.add(StandardTable.PROPERTY_COLUMNS);
		return properties;
	}

	@Override
	public List<MDatasetRun> getDatasetRunList() {
		List<MDatasetRun> datasetList = new ArrayList<MDatasetRun>();
		datasetList
				.add((MDatasetRun) getPropertyValue(StandardTable.PROPERTY_DATASET_RUN));
		return datasetList;
	}

	private void fillUsedStyles(List<INode> children, HashMap<String, List<ANode>> map) {
		for (INode node : children) {
			if (node instanceof ANode) {
				mergeElementStyle(map, ((ANode) node).getUsedStyles());
			}
		}
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		addDatasetGroupListener();
	}

	/**
	 * Delete a group node from the table, both header and footer if present
	 * 
	 * @param groupName
	 *            the name of the group
	 */
	private void deleteGroup(String groupName) {
		//Delete the model nodes
		MTableGroupFooter footer = null;
		MTableGroupHeader header = null;
		for (INode child : getChildren()) {
			if (child instanceof MTableGroupHeader) {
				MTableGroupHeader groupHeader = (MTableGroupHeader) child;
				if (groupHeader.getJrDesignGroup().getName().equals(groupName)) {
					header = groupHeader;
				}
			} else if (child instanceof MTableGroupFooter) {
				MTableGroupFooter groupFooter = (MTableGroupFooter) child;
				if (groupFooter.getJrDesignGroup().getName().equals(groupName)) {
					footer = groupFooter;
				}
			}
			if (footer != null && header != null)
				break;
		}
		if (footer != null)
			removeChild(footer);
		if (header != null)
			removeChild(header);
		
		//Delete the JR values
		List<BaseColumn> allColumns = getAllColumns(getStandardTable().getColumns());
		for(BaseColumn col : allColumns){
			StandardBaseColumn baseCol = (StandardBaseColumn)col;
			baseCol.setGroupFooter(groupName, null);
			baseCol.setGroupHeader(groupName, null);
		}
	}

	/**
	 * Add the dataset group listener to the current table dataset, but before
	 * remove the old one if present
	 */
	private void addDatasetGroupListener() {
		//First remove the old listeners
		if (datasetWithListener != null) {
			datasetWithListener.getEventSupport().removePropertyChangeListener(datasetGroupListener);
			datasetWithListener = null;
		}
		//If there is a value into the table lets add the new one
		if (getValue() != null){
			JRDatasetRun datasetRun = getStandardTable().getDatasetRun();
			JasperDesign design = getJasperDesign();
			if (design != null) {
				JRDesignDataset dataset = (JRDesignDataset) design.getDatasetMap().get(datasetRun.getDatasetName());
				datasetWithListener = dataset;
				if (dataset != null) {
					dataset.getEventSupport().addPropertyChangeListener(datasetGroupListener);
				}
			}
		}
	}

	@Override
	public HashMap<String, List<ANode>> getUsedStyles() {
		HashMap<String, List<ANode>> result = super.getUsedStyles();
		fillUsedStyles(getChildren(), result);
		return result;
	}
	
	@Override
	public boolean showChildren() {
		return getParent() instanceof MPage;
	}

	@Override
	public void createSubeditor() {
		TableComponentFactory.createSubeditor(this);
	}
	
	@Override
	public ILayout getDefaultLayout() {
		return LayoutManager.getLayout(FreeLayout.class.getName());
	}
	
	/**
	 * Return a list of every columns in the table, considering also the
	 * ColumnGroup
	 * 
	 * @param cols the current set of columns, it is a recursive method
	 * @return the list of columns contained in the passed parameter (considering
	 * also the subcolumns contained by the columns groups)
	 */
	protected List<BaseColumn> getAllColumns(List<BaseColumn> cols) {
		List<BaseColumn> lst = new ArrayList<BaseColumn>();
		for (BaseColumn bc : cols) {
			if (bc instanceof ColumnGroup){
				lst.addAll(getAllColumns(((ColumnGroup) bc).getColumns()));
			} 
			lst.add(bc);
		}
		return lst;
	}
	
	/**
	 * This type of node return a custom set value command provider that will allow to 
	 * generate command that will check if the table has the autoresize and if the changed property
	 * need to trigger its refresh
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == ISetValueCommandProvider.class) return TableSetValueCommandProvider.INSTANCE;
		else return super.getAdapter(adapter);
	}
}
