/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.crosstab.CrosstabCell;
import com.jaspersoft.studio.components.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.VerticalRowLayout;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGraphicalPropertiesHandler;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.ILineBox;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MLineBox;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.box.BoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.OpaqueModePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseLineBox;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.ModeEnum;

public class MCell extends APropertyNode implements IGraphicElement, IPastable, IContainerLayout, IPastableGraphic,
		IContainer, IContainerEditPart, ILineBox, IGroupElement, IGraphicElementContainer, IGraphicalPropertiesHandler {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	public static final String LINE_BOX = "LineBox"; //$NON-NLS-1$

	private static OpaqueModePropertyDescriptor opaqueD;

	private static RWComboBoxPropertyDescriptor styleD;

	private static IPropertyDescriptor[] descriptors;

	private MLineBox lineBox;

	private String name;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CrosstabNodeIconDescriptor("cell"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MCell() {
		super();
	}

	public MCell(ANode parent, JRCellContents jfRield, String name) {
		super(parent, -1);
		setValue(jfRield);
		setName(name);
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *            the parent
	 * @param jfRield
	 *            the jf rield
	 * @param newIndex
	 *            the new index
	 */
	public MCell(ANode parent, JRCellContents jfRield, String name, int index) {
		super(parent, index);
		setValue(jfRield);
		setName(name);
	}

	@Override
	public JRDesignCellContents getValue() {
		return (JRDesignCellContents) super.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return getIconDescriptor().getToolTip() + ": " + getDisplayText();
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		// initialize style
		JasperDesign jasperDesign = getJasperDesign();
		if (jasperDesign != null) {
			if (styleD != null) {
				JRDesignCellContents jrElement = (JRDesignCellContents) getValue();
				JRStyle[] styles = jasperDesign.getStyles();
				String[] items = new String[styles.length + 1];
				items[0] = jrElement.getStyleNameReference() != null ? jrElement.getStyleNameReference() : ""; //$NON-NLS-1$
				for (int j = 0; j < styles.length; j++) {
					items[j + 1] = styles[j].getName();
				}
				styleD.setItems(items);
			}
		}
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		opaqueD = new OpaqueModePropertyDescriptor(JRBaseStyle.PROPERTY_MODE, Messages.MCell_transparent,
				NullEnum.NOTNULL);
		opaqueD.setDescription(Messages.MCell_transparent_description);
		desc.add(opaqueD);

		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRBaseStyle.PROPERTY_BACKCOLOR,
				Messages.MCell_backcolor, NullEnum.INHERITED);
		backcolorD.setDescription(Messages.MCell_backcolor_description);
		desc.add(backcolorD);

		styleD = new RWComboBoxPropertyDescriptor(JRDesignCellContents.PROPERTY_STYLE, Messages.MCell_parent_style,
				new String[] { "" }, //$NON-NLS-1$
				NullEnum.NULL);
		styleD.setDescription(Messages.MCell_parent_style_description);
		desc.add(styleD);

		PixelPropertyDescriptor wD = new PixelPropertyDescriptor(JRDesignCrosstabCell.PROPERTY_WIDTH,
				Messages.common_width);
		desc.add(wD);

		PixelPropertyDescriptor hD = new PixelPropertyDescriptor(JRDesignCrosstabCell.PROPERTY_HEIGHT,
				Messages.common_height);
		desc.add(hD);

		BoxPropertyDescriptor lineBoxD = new BoxPropertyDescriptor(LINE_BOX, Messages.MCell_line_box);
		lineBoxD.setDescription(Messages.MCell_line_box_description);
		desc.add(lineBoxD);

		JPropertiesPropertyDescriptor propertiesMapD = new JPropertiesPropertyDescriptor(MGraphicElement.PROPERTY_MAP,
				com.jaspersoft.studio.messages.Messages.common_properties, getJasperConfiguration(), getValue());
		propertiesMapD.setDescription(com.jaspersoft.studio.messages.Messages.common_properties);
		desc.add(propertiesMapD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#crosstabCell");
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();

		defaultsMap.put(JRBaseStyle.PROPERTY_MODE, new DefaultValue(ModeEnum.OPAQUE, false));
		defaultsMap.put(JRBaseStyle.PROPERTY_BACKCOLOR, new DefaultValue(null, true));
		defaultsMap.put(JRDesignCellContents.PROPERTY_STYLE, new DefaultValue(null, true));

		return defaultsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignCellContents jrElement = getValue();
		if (jrElement != null) {
			if (id.equals(JRBaseStyle.PROPERTY_MODE))
				return opaqueD.getIntValue(jrElement.getModeValue());
			if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR))
				return Colors.getSWTRGB4AWTGBColor(jrElement.getBackcolor());
			if (id.equals(JRDesignCellContents.PROPERTY_STYLE)) {
				if (jrElement.getStyleNameReference() != null)
					return jrElement.getStyleNameReference();
				if (jrElement.getStyle() != null)
					return jrElement.getStyle().getName();
				return ""; //$NON-NLS-1$
			}
			if (id.equals(JRDesignCrosstabCell.PROPERTY_WIDTH))
				return jrElement.getWidth();
			if (id.equals(JRDesignCrosstabCell.PROPERTY_HEIGHT))
				return jrElement.getHeight();
			if (id.equals(LINE_BOX)) {
				JRBoxContainer jrGraphicElement = (JRBoxContainer) getValue();
				if (lineBox == null) {
					lineBox = new MLineBox(jrGraphicElement.getLineBox(), this);
					lineBox.getPropertyChangeSupport().addPropertyChangeListener(this);
				}
				return lineBox;
			}
			if (id.equals(MGraphicElement.PROPERTY_MAP)) {
				// return a copy of the map
				return getPropertiesMapClone(jrElement);
			}
		}
		return null;
	}

	protected JRPropertiesMap getPropertiesMapClone(JRDesignCellContents element) {
		JRPropertiesMap propertiesMap = element.getPropertiesMap();
		if (propertiesMap != null)
			propertiesMap = propertiesMap.cloneProperties();
		return propertiesMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignCellContents jrElement = getValue();
		if (jrElement != null) {
			if (id.equals(JRBaseStyle.PROPERTY_MODE))
				jrElement.setMode((ModeEnum) opaqueD.getEnumValue(value));
			else if (id.equals(JRBaseStyle.PROPERTY_BACKCOLOR)) {
				jrElement.setBackcolor(Colors.getAWT4SWTRGBColor((AlfaRGB) value));
			} else if (id.equals(JRDesignCellContents.PROPERTY_STYLE)) {
				if (!value.equals("")) { //$NON-NLS-1$
					JRStyle style = (JRStyle) getJasperDesign().getStylesMap().get(value);
					if (style != null) {
						jrElement.setStyle(style);
						jrElement.setStyleNameReference(null);
					} else {
						jrElement.setStyleNameReference((String) value);
						jrElement.setStyle(null);
					}
				}
			} else if (id.equals(JRDesignCrosstabCell.PROPERTY_WIDTH)) {
				MCrosstab cross = getMCrosstab();
				if (cross != null) {
					cross.getCrosstabManager().setWidth(jrElement, (Integer) value);

					cross.getCrosstabManager().refresh();
					getPropertyChangeSupport().firePropertyChange(
							new PropertyChangeEvent(this, JRDesignCrosstabCell.PROPERTY_WIDTH, null, value));
				}
			} else if (id.equals(JRDesignCrosstabCell.PROPERTY_HEIGHT)) {
				MCrosstab cross = getMCrosstab();
				if (cross != null) {
					cross.getCrosstabManager().setHeight(jrElement, (Integer) value);

					cross.getCrosstabManager().refresh();
					getPropertyChangeSupport().firePropertyChange(
							new PropertyChangeEvent(this, JRDesignCrosstabCell.PROPERTY_HEIGHT, null, value));
				}
			} else if (id.equals(MGraphicElement.PROPERTY_MAP)) {
				JRPropertiesMap originalMap = jrElement.getPropertiesMap().cloneProperties();
				JRPropertiesMap v = (JRPropertiesMap) value;
				String[] names = jrElement.getPropertiesMap().getPropertyNames();
				// clear the old map
				for (int i = 0; i < names.length; i++) {
					jrElement.getPropertiesMap().removeProperty(names[i]);
				}
				// set the new properties
				names = v.getPropertyNames();
				for (int i = 0; i < names.length; i++) {
					jrElement.getPropertiesMap().setProperty(names[i], v.getProperty(names[i]));
				}
				// really important to trigger the property with source the JR object and not
				// the node
				// using the node could cause problem with the refresh of the advanced
				// properties view
				this.getPropertyChangeSupport().firePropertyChange(
						new PropertyChangeEvent(jrElement, PROPERTY_MAP, originalMap, jrElement.getPropertiesMap()));
			}

		}
	}

	public int getDefaultWidth() {
		return 20;
	}

	public int getDefaultHeight() {
		return 20;
	}

	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}

	public Rectangle getBounds() {
		MCrosstab mc = getMCrosstab();
		if (mc != null)
			return mc.getCrosstabManager().getBounds(new CrosstabCell(getValue()));
		return null;
	}

	public JRBoxContainer getBoxContainer() {
		return (JRBoxContainer) getValue();
	}

	public Integer getTopPadding() {
		JRDesignCellContents c = null;
		if (getValue() != null) {
			c = (JRDesignCellContents) getValue();
			return c.getLineBox().getTopPadding();
		}
		return 0;
	}

	public Integer getLeftPadding() {
		JRDesignCellContents c = null;
		if (getValue() != null) {
			c = (JRDesignCellContents) getValue();
			return c.getLineBox().getLeftPadding();
		}
		return 0;
	}

	public Integer getBottomPadding() {
		JRDesignCellContents c = null;
		if (getValue() != null) {
			c = (JRDesignCellContents) getValue();
			return c.getLineBox().getBottomPadding();
		}
		return 0;
	}

	public Integer getRightPadding() {
		JRDesignCellContents c = null;
		if (getValue() != null) {
			c = (JRDesignCellContents) getValue();
			return c.getLineBox().getRightPadding();
		}
		return 0;
	}

	public Integer getPadding() {
		JRDesignCellContents c = null;
		if (getValue() != null) {
			c = (JRDesignCellContents) getValue();
			return c.getLineBox().getPadding();
		}
		return 0;
	}

	public MCrosstab getCrosstab() {
		INode node = this;
		while (node != null && node.getParent() != null && !(node instanceof MCrosstab) && !(node instanceof MRoot)) {
			node = node.getParent();
		}
		if (node instanceof MCrosstab)
			return (MCrosstab) node;
		return null;
	}

	public MCrosstab getMCrosstab() {
		return getCrosstab();
	}

	@Override
	public Dimension getSize() {
		JRDesignCellContents v = getValue();
		return new Dimension(v.getWidth(), v.getHeight());
	}

	@Override
	public JRElementGroup getJRElementGroup() {
		return getValue();
	}

	@Override
	public JRPropertiesHolder[] getPropertyHolder() {
		return new JRPropertiesHolder[] { getValue(), getMCrosstab().getValue() };
	}

	/**
	 * Flag changed when some property that has graphical impact on the element is
	 * changed. This is used to redraw the elemnt only when something graphical is
	 * changed isndie it, all the other times can just be copied
	 */
	private boolean visualPropertyChanged = true;

	/**
	 * True if some graphical property is changed for the element, false otherwise
	 */
	@Override
	public boolean hasChangedProperty() {
		synchronized (this) {
			return visualPropertyChanged;
		}
	}

	/**
	 * When the style changes a refresh is sent not only to the current node, but
	 * also to the node that are listening on the same JR element. This is done to
	 * propagate the change to every editor where the element is displayed
	 */
	@Override
	public void setStyleChangedProperty() {
		// Performance improvement, avoid to send the event more than one time
		// for each editor
		HashSet<ANode> refreshedParents = new HashSet<ANode>();
		for (PropertyChangeListener listener : getValue().getEventSupport().getPropertyChangeListeners()) {
			if (listener instanceof MCell) {
				MCell listenerCell = (MCell) listener;
				MCrosstab crosstab = listenerCell.getMCrosstab();
				if (crosstab != null) {
					ANode tableParent = crosstab.getParent();
					if (tableParent != null && !refreshedParents.contains(tableParent)) {
						refreshedParents.add(tableParent);
						listenerCell.setChangedProperty(true);

					}
				}
			}
		}
	}

	/**
	 * Set the actual state of the property change flag
	 */
	@Override
	public void setChangedProperty(boolean value) {
		synchronized (this) {
			if (value) {
				ANode parent = getParent();
				while (parent != null) {
					if (parent instanceof IGraphicalPropertiesHandler) {
						IGraphicalPropertiesHandler handler = (IGraphicalPropertiesHandler) parent;
						handler.setChangedProperty(true);
						// We can exit the cycle since the setChangedProperty on
						// the parent
						// will propagate the
						// refresh on the upper levels
						break;
					} else {
						parent = parent.getParent();
					}
				}
			}
			visualPropertyChanged = value;
		}
	}

	private static HashSet<String> cachedGraphicalProperties = null;

	/**
	 * Return the graphical properties for an MGraphicalElement
	 */
	@Override
	public HashSet<String> getGraphicalProperties() {
		if (cachedGraphicalProperties == null) {
			cachedGraphicalProperties = new HashSet<String>();
			cachedGraphicalProperties.add(JRBaseStyle.PROPERTY_MODE);
			cachedGraphicalProperties.add(JRBaseStyle.PROPERTY_BACKCOLOR);
			cachedGraphicalProperties.add(JRDesignCellContents.PROPERTY_STYLE);
			cachedGraphicalProperties.add(JRDesignCrosstabCell.PROPERTY_WIDTH);
			cachedGraphicalProperties.add(JRDesignCrosstabCell.PROPERTY_HEIGHT);
			cachedGraphicalProperties.add(JRBaseLineBox.PROPERTY_BOTTOM_PADDING);
			cachedGraphicalProperties.add(JRBaseLineBox.PROPERTY_LEFT_PADDING);
			cachedGraphicalProperties.add(JRBaseLineBox.PROPERTY_PADDING);
			cachedGraphicalProperties.add(JRBaseLineBox.PROPERTY_RIGHT_PADDING);
			cachedGraphicalProperties.add(JRBaseLineBox.PROPERTY_TOP_PADDING);
			cachedGraphicalProperties.add(JRBasePen.PROPERTY_LINE_COLOR);
			cachedGraphicalProperties.add(JRBasePen.PROPERTY_LINE_STYLE);
			cachedGraphicalProperties.add(JRBasePen.PROPERTY_LINE_WIDTH);
		}
		return cachedGraphicalProperties;
	}

	@Override
	public Map<String, List<ANode>> getUsedStyles() {
		JRDesignCellContents jrElement = getValue();
		Map<String, List<ANode>> result = super.getUsedStyles();
		if (jrElement != null && jrElement.getStyle() != null) {
			addElementStyle(jrElement.getStyle(), result);
		}
		for (INode node : getChildren()) {
			if (node instanceof ANode) {
				mergeElementStyle(result, ((ANode) node).getUsedStyles());
			}
		}
		return result;
	}

	@Override
	public void setStyle(JRStyle style) {
		JRDesignCellContents jrElement = getValue();
		if (jrElement != null) {
			jrElement.setStyle(style);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		HashSet<String> graphicalProperties = getGraphicalProperties();
		if (graphicalProperties.contains(evt.getPropertyName())) {
			setChangedProperty(true);
		}
		super.propertyChange(evt);
	}

	@Override
	public ILayout getDefaultLayout() {
		return LayoutManager.getLayout(VerticalRowLayout.class.getName());
	}

	/**
	 * Override this to update the node name on some events (group name changed for
	 * example). By default this doesn't to anything, trigger only an event to force
	 * the UI to refresh, so it can be called by the override of this method
	 */
	public void updateName() {
		if (getValue() != null) {
			propertyChange(new PropertyChangeEvent(getValue(), MGraphicElement.FORCE_GRAPHICAL_REFRESH, null, null));
		}
	}

	/**
	 * Style descriptor used by the inheritance view section
	 */
	@Override
	public HashMap<String, Object> getStylesDescriptors() {
		HashMap<String, Object> result = super.getStylesDescriptors();
		if (getValue() == null)
			return result;
		MLineBox element = (MLineBox) getPropertyValue(LINE_BOX);
		result.put(LINE_BOX, element);
		return result;
	}

	@Override
	public Rectangle getAbsoluteBounds() {
		MCrosstab crosstabModel = getCrosstab();
		Rectangle crosstabLocationLocation = crosstabModel.getAbsoluteBounds();
		Rectangle cellBounds = getBounds();
		int x = cellBounds.x + crosstabLocationLocation.x;
		int y = cellBounds.y + crosstabLocationLocation.y;
		return new Rectangle(x, y, cellBounds.width, cellBounds.height);
	}
}
