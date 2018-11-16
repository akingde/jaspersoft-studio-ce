/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.list.model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.callout.pin.IPinContainer;
import com.jaspersoft.studio.components.list.ListComponentFactory;
import com.jaspersoft.studio.components.list.ListNodeIconDescriptor;
import com.jaspersoft.studio.components.list.messages.Messages;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.dataset.MDatasetRun;
import com.jaspersoft.studio.model.dataset.descriptor.DatasetRunPropertyDescriptor;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.PrintOrderEnum;

public class MList extends MGraphicElement implements IPastable, IPastableGraphic, IContainerLayout, IContainer,
		IContainerEditPart, IGroupElement, IGraphicElementContainer, ICopyable, IDatasetContainer, IPinContainer {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private static IPropertyDescriptor[] descriptors;

	private static NamedEnumPropertyDescriptor<PrintOrderEnum> printOrderD;

	public static final String PREFIX = "CONTENTS."; //$NON-NLS-1$

	private MDatasetRun mDatasetRun;

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
	 *            the parent
	 * @param jrList
	 *            the jr line
	 * @param newIndex
	 *            the new index
	 */
	public MList(ANode parent, JRDesignComponentElement jrList, int newIndex) {
		super(parent, newIndex);
		setValue(jrList);
	}

	@Override
	public void register() {
		super.register();
		if (getValue() != null) {
			INode troot = getRoot();
			if (troot != null && troot instanceof ANode) {
				((ANode) troot).register(getList(), this);
				((ANode) troot).register(getJRElementGroup(), this);
			}
		}
	}

	@Override
	public void unregister() {
		super.unregister();
		if (getValue() != null) {
			INode troot = getRoot();
			if (troot != null && troot instanceof ANode) {
				((ANode) troot).unregister(getList());
				((ANode) troot).unregister(getJRElementGroup());
			}
		}
	}

	@Override
	public JRDesignComponentElement getValue() {
		return (JRDesignComponentElement) super.getValue();
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

		printOrderD = new NamedEnumPropertyDescriptor<PrintOrderEnum>(StandardListComponent.PROPERTY_PRINT_ORDER,
				Messages.MList_print_order, PrintOrderEnum.HORIZONTAL, NullEnum.NOTNULL);
		printOrderD.setDescription(Messages.MList_print_order_description);
		desc.add(printOrderD);
		printOrderD.setCategory(Messages.MList_list_properties_category);
		printOrderD.setHelpRefBuilder(
				new HelpReferenceBuilder("net.sf.jasperreports.doc/docs/components.schema.reference.html#_printOrder"));

		CheckBoxPropertyDescriptor ignoreWidthD = new CheckBoxPropertyDescriptor(
				StandardListComponent.PROPERTY_IGNORE_WIDTH, Messages.MList_ignore_width, NullEnum.NULL);
		ignoreWidthD.setDescription(Messages.MList_ignore_width_description);
		desc.add(ignoreWidthD);
		ignoreWidthD.setCategory(Messages.MList_list_properties_category);
		ignoreWidthD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#_ignoreWidth"));

		DatasetRunPropertyDescriptor datasetRunD = new DatasetRunPropertyDescriptor(
				PREFIX + StandardListComponent.PROPERTY_DATASET_RUN, Messages.MList_dataset_run, false); // $NON-NLS-1$
		datasetRunD.setDescription(Messages.MList_dataset_run_description);
		datasetRunD.setCategory(Messages.MList_list_properties_category);
		desc.add(datasetRunD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/components.schema.reference.html#list");

		PixelPropertyDescriptor heightD = new PixelPropertyDescriptor(PREFIX + DesignListContents.PROPERTY_HEIGHT,
				Messages.MList_cell_height);
		heightD.setCategory(Messages.MList_list_properties_category);
		heightD.setDescription(Messages.MList_cell_height_description);
		desc.add(heightD);
		heightD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#listContents_height"));

		ListSizePropertyDescriptor cellSizeDescriptor = new ListSizePropertyDescriptor();
		cellSizeDescriptor.setCategory(Messages.MList_list_properties_category);
		cellSizeDescriptor.setDescription(Messages.MList_cell_height_description);
		desc.add(cellSizeDescriptor);

		PixelPropertyDescriptor widthD = new PixelPropertyDescriptor(PREFIX + DesignListContents.PROPERTY_WIDTH,
				Messages.MList_cell_width);
		widthD.setCategory(Messages.MList_list_properties_category);
		widthD.setDescription(Messages.MList_cell_width_description);
		desc.add(widthD);
		widthD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#listContents_width"));
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();

		defaultsMap.put(StandardListComponent.PROPERTY_IGNORE_WIDTH, new DefaultValue(false, false));

		int printOrderValue = NamedEnumPropertyDescriptor.getIntValue(PrintOrderEnum.HORIZONTAL, NullEnum.NOTNULL,
				PrintOrderEnum.HORIZONTAL);
		defaultsMap.put(StandardListComponent.PROPERTY_PRINT_ORDER, new DefaultValue(printOrderValue, false));

		return defaultsMap;
	}

	@Override
	public Object getPropertyValue(Object id) {
		StandardListComponent jrList = getList();

		if (id.equals(StandardListComponent.PROPERTY_IGNORE_WIDTH))
			return jrList.getIgnoreWidth();
		if (id.equals(StandardListComponent.PROPERTY_PRINT_ORDER))
			return printOrderD.getIntValue(jrList.getPrintOrderValue());

		if (id.equals(PREFIX + DesignListContents.PROPERTY_HEIGHT))
			return jrList.getContents().getHeight();
		if (id.equals(PREFIX + DesignListContents.PROPERTY_WIDTH))
			return jrList.getContents().getWidth();

		if (id.equals(PREFIX + StandardListComponent.PROPERTY_DATASET_RUN)) { // $NON-NLS-1$
			JRDatasetRun j = jrList.getDatasetRun();
			if (j == null) {
				j = new JRDesignDatasetRun();
			}
			if (mDatasetRun != null)
				mDatasetRun.setValue(j);
			else {
				mDatasetRun = new MDatasetRun(j, getJasperDesign());
				mDatasetRun.setJasperConfiguration(getJasperConfiguration());
				setChildListener(mDatasetRun);
			}
			return mDatasetRun;
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		StandardListComponent jrList = getList();
		if (id.equals(StandardListComponent.PROPERTY_IGNORE_WIDTH))
			jrList.setIgnoreWidth((Boolean) value);
		else if (id.equals(StandardListComponent.PROPERTY_PRINT_ORDER))
			jrList.setPrintOrderValue(printOrderD.getEnumValue(value));
		else if (id.equals(PREFIX + DesignListContents.PROPERTY_HEIGHT))
			((DesignListContents) jrList.getContents()).setHeight((Integer) value);
		else if (id.equals(PREFIX + DesignListContents.PROPERTY_WIDTH))
			((DesignListContents) jrList.getContents()).setWidth((Integer) value);
		else if (id.equals(PREFIX + JRDesignElementDataset.PROPERTY_DATASET_RUN)) {
			if (value == null) {
				jrList.setDatasetRun(null);
			} else {
				MDatasetRun mdr = (MDatasetRun) value;
				JRDesignDatasetRun dr = (JRDesignDatasetRun) mdr.getValue();
				if (dr.getDatasetName() != null)
					jrList.setDatasetRun(dr);
				else
					jrList.setDatasetRun(null);
			}
		} else if (id.equals(MGraphicElement.PROPERTY_ELEMENT_NAME)) {
			super.setPropertyValue(id, value);
			// fire the event to update the editor name, because the property of the name
			// could be changed
			firePropertyChange(
					new PropertyChangeEvent(getValue(), ReportContainer.RENAME_EDITOR_PROPERTY, false, true));
		} else
			super.setPropertyValue(id, value);
	}

	public Dimension getContainerSize() {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		StandardListComponent jrList = (StandardListComponent) jrElement.getComponent();
		// ListContents can have null dimensions, let's provide a default
		return new Dimension(Misc.nvl(jrList.getContents().getWidth(), 0),
				Misc.nvl(jrList.getContents().getHeight(), 0));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGraphicElement#createJRElement(net.sf.
	 * jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignComponentElement component = new JRDesignComponentElement();
		component.setHeight(getDefaultHeight());
		component.setWidth(getDefaultWidth());
		StandardListComponent componentImpl = new StandardListComponent();
		DesignListContents contents = new DesignListContents();
		contents.setHeight(100);
		contents.setWidth((Integer) 100);
		componentImpl.setContents(contents);

		component.setComponent(componentImpl);
		ComponentKey componentKey = new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components",
				"jr", "list");
		component.setComponentKey(componentKey);

		JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();
		componentImpl.setDatasetRun(datasetRun);

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), component);
		}

		return component;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		String name = getElementNameProperty();
		return getIconDescriptor().getTitle() + " " + Misc.nvl(name);
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
		return getJRElementGroup((JRDesignComponentElement) getValue());
	}

	private JRElementGroup getJRElementGroup(JRDesignComponentElement value) {
		JRElementGroup res = null;
		if (value != null) {
			JRDesignComponentElement jrElement = value;
			StandardListComponent jrList = (StandardListComponent) jrElement.getComponent();
			res = jrList.getContents();
		}
		return res;
	}

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			JRDesignComponentElement jrcomp = (JRDesignComponentElement) getValue();
			JRElementGroup elementGroup = getJRElementGroup(jrcomp);
			if (elementGroup instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) elementGroup).getEventSupport().removePropertyChangeListener(this);
		}
		if (value != null) {
			JRDesignComponentElement jrcomp = (JRDesignComponentElement) value;
			JRElementGroup elementGroup = getJRElementGroup(jrcomp);
			if (elementGroup instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) elementGroup).getEventSupport().addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// check if it is already marked to be refreshed, in this case skip the check of
		// the maps
		if (!hasChangedProperty()) {
			HashSet<String> graphicalProperties = getGraphicalProperties();
			if (graphicalProperties.contains(evt.getPropertyName())) {
				setChangedProperty(true, evt);
			}
		}
		if (evt.getSource() == getValue()) {
			if (evt.getPropertyName().equals(JRDesignElement.PROPERTY_HEIGHT)) {
				JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
				StandardListComponent jrList = (StandardListComponent) jrElement.getComponent();
				((DesignListContents) jrList.getContents()).setHeight((Integer) evt.getNewValue());
			} else if (evt.getPropertyName().equals(JRDesignElement.PROPERTY_WIDTH)) {
				JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
				StandardListComponent jrList = (StandardListComponent) jrElement.getComponent();
				((DesignListContents) jrList.getContents()).setWidth((Integer) evt.getNewValue());
			}
		}

		if (evt.getPropertyName().equals(JRDesignElementGroup.PROPERTY_CHILDREN) && getParent() instanceof MPage) {
			// Add the children at the model only if the list is opened into a separate
			// editor
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
						if (n.getValue() == evt.getOldValue()) {
							n.setValue(evt.getNewValue());
						}
					}
				}
			}
		}
		super.propertyChange(evt);
	}

	@Override
	public Dimension getSize() {
		JRDesignComponentElement v = getValue();
		return new Dimension(v.getWidth(), v.getHeight());
	}

	public StandardListComponent getList() {
		JRDesignComponentElement jrElement = getValue();
		StandardListComponent jrList = (StandardListComponent) jrElement.getComponent();
		return jrList;
	}

	@Override
	public JRPropertiesHolder[] getPropertyHolder() {
		return new JRPropertiesHolder[] { getValue() };
	}

	@Override
	public List<MDatasetRun> getDatasetRunList() {
		List<MDatasetRun> datasetList = new ArrayList<MDatasetRun>();
		datasetList.add((MDatasetRun) getPropertyValue(PREFIX + StandardListComponent.PROPERTY_DATASET_RUN));
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
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> properties = super.generateGraphicalProperties();
		properties.add(JRDesignElementGroup.PROPERTY_CHILDREN);
		properties.add(JRDesignElement.PROPERTY_ELEMENT_GROUP);
		properties.add(JRDesignElement.PROPERTY_PARENT_STYLE_NAME_REFERENCE);
		properties.add(PREFIX + DesignListContents.PROPERTY_WIDTH);
		properties.add(PREFIX + DesignListContents.PROPERTY_HEIGHT);
		return properties;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		StandardListComponent jrSourceList = getList();
		JRDesignComponentElement jrTargetElement = (JRDesignComponentElement) target;
		StandardListComponent jrTargetList = (StandardListComponent) jrTargetElement.getComponent();

		jrTargetList.setIgnoreWidth(jrSourceList.getIgnoreWidth());
		jrTargetList.setPrintOrderValue(jrSourceList.getPrintOrderValue());
		((DesignListContents) jrTargetList.getContents()).setHeight(jrSourceList.getContents().getHeight());
		((DesignListContents) jrTargetList.getContents()).setWidth(jrSourceList.getContents().getWidth());

	}

	@Override
	public boolean showChildren() {
		return getParent() instanceof MPage;
	}

	@Override
	public void createSubeditor() {
		ListComponentFactory.createSubeditor(this);
	}

	@Override
	public ILayout getDefaultLayout() {
		return LayoutManager.getLayout(FreeLayout.class.getName());
	}

	/**
	 * When the style changes a refresh is sent not only to the current node, but
	 * also to the node that are listening on the same JR element. This is done to
	 * propagate the change to every editor where the element is displayed
	 */
	@Override
	public void setStyleChangedProperty() {
		super.setStyleChangedProperty();
		getValue().getEventSupport().firePropertyChange(MGraphicElement.FORCE_GRAPHICAL_REFRESH, null, null);
	}

	// Stuff for the padding, the list is not a line box, but it could support
	// padding trough styles. For
	// this reason this calls try to resolve the style and return the padding
	// provided by the style.

	@Override
	public Integer getTopPadding() {
		JRStyle style = getActualStyle();
		if (style != null) {
			return style.getLineBox().getTopPadding();
		}
		return 0;
	}

	@Override
	public Integer getLeftPadding() {
		JRStyle style = getActualStyle();
		if (style != null) {
			return style.getLineBox().getLeftPadding();
		}
		return 0;
	}

	@Override
	public Integer getRightPadding() {
		JRStyle style = getActualStyle();
		if (style != null) {
			return style.getLineBox().getRightPadding();
		}
		return 0;
	}

	@Override
	public Integer getBottomPadding() {
		JRStyle style = getActualStyle();
		if (style != null) {
			return style.getLineBox().getBottomPadding();
		}
		return 0;
	}

	@Override
	public Integer getPadding() {
		JRStyle style = getActualStyle();
		if (style != null) {
			return style.getLineBox().getPadding();
		}
		return 0;
	}
}
