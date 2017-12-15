/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.TableNodeIconDescriptor;
import com.jaspersoft.studio.components.table.TableSetValueCommandProvider;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.MTableGroupFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupHeader;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.util.TableColumnNumerator;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.VerticalRowLayout;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.ISetValueCommandProvider;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.propexpr.JPropertyExpressionsDescriptor;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignPropertyExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;

public class MColumn extends APropertyNode
		implements IPastable, IContainer, IContainerLayout, IGraphicElement, IContainerEditPart {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static IIconDescriptor iconDescriptor;

	public static String PROPERTY_NAME = "NAME";

	public static String COLUMN_NAME = "com.jaspersoft.studio.components.table.model.column.name";

	private static IPropertyDescriptor[] descriptors;

	private JRDesignGroup jrGroup;

	/**
	 * Last valid value for the table containing this node, this reference
	 * because it could be need for some undo operations (SetValueCommand) even
	 * after this node was detached from the table
	 */
	private MTable containerTable;

	/**
	 * Last valid value for the section containing this node, this reference
	 * because it could be need for some undo operations (SetValueCommand) even
	 * after this node was detached from the table
	 */
	protected AMCollection containerSection;

	private List<ANode> list;

	private String name;

	private String grName;

	private int type = TableUtil.TABLE_HEADER;

	/**
	 * Instantiates a new m field.
	 */
	public MColumn() {
		super();
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
	public MColumn(ANode parent, StandardBaseColumn column, String name, int index) {
		super(parent, index);
		setValue(column);
		this.name = name;
		List<ANode> n = getAMCollection();
		if (n != null && !n.isEmpty()) {
			ANode aNode = n.get(n.size() - 1);
			type = TableColumnSize.getType(aNode.getClass());
			if (aNode instanceof MTableGroupHeader) {
				jrGroup = ((MTableGroupHeader) aNode).getJrDesignGroup();
				grName = jrGroup.getName();
			}
			if (aNode instanceof MTableGroupFooter) {
				jrGroup = ((MTableGroupFooter) aNode).getJrDesignGroup();
				grName = jrGroup.getName();
			}
		}
	}

	/**
	 * Store the table and section ancestor of this node. When the parent is set
	 * to null the old value are maintained to allow the undo operation to be
	 * executed on this node
	 */
	@Override
	public void setParent(ANode newparent, int newIndex) {
		super.setParent(newparent, newIndex);
		ANode node = getParent();
		while (node != null) {
			if (node instanceof MTable) {
				containerTable = (MTable) node;
				break;
			} else if (node instanceof AMCollection) {
				containerSection = (AMCollection) node;
			}
			node = node.getParent();
		}
		// When the node is removed (because maybe a cell was deleted) the value
		// is not
		// set to null, in this case remove this listener from the node
		if (newparent == null && getValue() != null) {
			getValue().getEventSupport().removePropertyChangeListener(this);
		}
	}

	public JRDesignGroup getJrGroup() {
		return jrGroup;
	}

	public MColumn getNorth() {
		ANode mparent = getParent();
		if (TableManager.isBottomOfTable(type)) {
			if (this instanceof MColumnGroup || this instanceof MColumnGroupCell)
				return (MColumn) getChildren().get(0);

			MTable mtable = getMTable();
			List<ANode> amCollection = getAMCollection();
			int index = mtable.getChildren().indexOf(amCollection.get(amCollection.size() - 1));
			AMCollection newmc = (AMCollection) mtable.getChildren().get(index - 1);
			return (MColumn) newmc.getChildren().get(0);
		} else if (mparent instanceof MColumnGroup || mparent instanceof MColumnGroupCell)
			return (MColumn) mparent;

		MTable mtable = getMTable();
		int index = mtable.getChildren().indexOf(mparent);
		if (index > 0) {
			AMCollection newmc = (AMCollection) mtable.getChildren().get(index - 1);
			return getBottomColumn(newmc.getChildren());
		} else
			return null;
	}

	private MColumn getBottomColumn(List<INode> newmc) {
		for (INode col : newmc) {
			if (col instanceof MColumnGroup || col instanceof MColumnGroupCell)
				col = getBottomColumn(col.getChildren());
			if (col instanceof MColumn)
				return (MColumn) col;
		}
		return null;
	}

	public int getType() {
		return type;
	}

	public String getGrName() {
		return Misc.nvl(grName);
	}

	@Override
	public StandardBaseColumn getValue() {
		return (StandardBaseColumn) super.getValue();
	}

	@Override
	public Color getForeground() {
		return ColorConstants.lightGray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return getName();
	}

	public String getName() {
		if (name == null)
			return getValue().getPropertiesMap().getProperty(COLUMN_NAME);
		return name;
	}

	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		getValue().getPropertiesMap().setProperty(COLUMN_NAME, name);
		getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, name); // $NON-NLS-1$
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
		String tt = "";// getValue().getUUID().toString() + "\n";
		List<ANode> nodes = getAMCollection();
		for (int i = nodes.size() - 1; i >= 0; i--)
			tt += nodes.get(i).getDisplayText() + "\n";
		tt += "\t" + getIconDescriptor().getToolTip() + ": " + getDisplayText();
		return tt;
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
		JRExpressionPropertyDescriptor printWhenExprD = new JRExpressionPropertyDescriptor(
				StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.MColumn_print_when_expression);
		printWhenExprD.setDescription(Messages.MColumn_print_when_expression_description);
		desc.add(printWhenExprD);

		PixelPropertyDescriptor wD = new PixelPropertyDescriptor(StandardBaseColumn.PROPERTY_WIDTH,
				Messages.MColumn_column_width);
		desc.add(wD);

		JPropertyExpressionsDescriptor propertiesD = new JPropertyExpressionsDescriptor(
				JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS,
				com.jaspersoft.studio.messages.Messages.MGraphicElement_property_expressions);
		propertiesD.setDescription(
				com.jaspersoft.studio.messages.Messages.MGraphicElement_property_expressions_description);
		desc.add(propertiesD);

		printWhenExprD.setCategory(Messages.MColumn_column_properties_category);
		wD.setCategory(Messages.MColumn_column_properties_category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		StandardBaseColumn jrElement = getValue();
		if (id.equals(StandardBaseColumn.PROPERTY_WIDTH)) {
			return jrElement.getWidth();
		}
		if (id.equals(StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION)) {
			return ExprUtil.getExpression(jrElement.getPrintWhenExpression());
		}
		if (id.equals(DesignCell.PROPERTY_HEIGHT)) {
			return getMTable().getTableManager().getYhcolumn(type, grName, jrElement).height;
		}
		if (id.equals(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS)) {
			JRPropertyExpression[] propertyExpressions = jrElement.getPropertyExpressions();
			if (propertyExpressions != null) {
				propertyExpressions = propertyExpressions.clone();
			}

			JRPropertiesMap propertiesMap = jrElement.getPropertiesMap();
			if (propertiesMap != null) {
				propertiesMap = propertiesMap.cloneProperties();
			}

			return new PropertyExpressionsDTO(propertyExpressions, propertiesMap, getValue(),
					ModelUtils.getExpressionContext(this));
		}
		if (id.equals(MGraphicElement.PROPERTY_MAP)) {
			JRPropertiesMap propertiesMap = jrElement.getPropertiesMap();
			if (propertiesMap != null) {
				propertiesMap = propertiesMap.cloneProperties();
			}
			return propertiesMap;
		}
		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		StandardBaseColumn jrElement = getValue();

		if (id.equals(StandardBaseColumn.PROPERTY_WIDTH)) {
			// the editing of a table column width is a complex operation, that
			// could in most case require the adjustemnt of the width of
			// other columns. Setting it directly could be risky. Also add here
			// the logic to set the other columns and not in an appropriate
			// command could bring problems in the undo of the operation. For
			// this reason the direct set of this property is discouraged
			// and it is suggested to used the command SetColumnWidthCommand,
			// that handle all the necessary operations
			throw new UnsupportedOperationException(
					"Column Width shouldn't be set directly, use the appropriate command SetColumnWidthCommand");
		} else if (id.equals(DesignCell.PROPERTY_HEIGHT)) {
			Integer height = (Integer) value;
			if (containerSection != null && height.intValue() >= 0) {

				@SuppressWarnings("unchecked")
				Class<AMCollection> classType = (Class<AMCollection>) containerSection.getClass();
				String grName = null;
				if (containerSection instanceof MTableGroupHeader) {
					grName = ((MTableGroupHeader) containerSection).getJrDesignGroup().getName();
				} else if (containerSection instanceof MTableGroupFooter) {
					grName = ((MTableGroupFooter) containerSection).getJrDesignGroup().getName();
				}

				containerTable.getTableManager().setHeight(null, height, jrElement, TableColumnSize.getType(classType),
						grName);
				containerTable.getTableManager().update();

				getPropertyChangeSupport()
						.firePropertyChange(new PropertyChangeEvent(this, DesignCell.PROPERTY_HEIGHT, null, value));
			}
		} else if (id.equals(StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION)) {
			jrElement.setPrintWhenExpression(ExprUtil.setValues(jrElement.getPrintWhenExpression(), value, null));
		} else if (id.equals(MGraphicElement.PROPERTY_MAP)) {
			JRPropertiesMap v = (JRPropertiesMap) value;
			String[] names = jrElement.getPropertiesMap().getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				jrElement.getPropertiesMap().removeProperty(names[i]);
			}
			names = v.getPropertyNames();
			for (int i = 0; i < names.length; i++)
				jrElement.getPropertiesMap().setProperty(names[i], v.getProperty(names[i]));
			this.getPropertyChangeSupport().firePropertyChange(MGraphicElement.PROPERTY_MAP, false, true);
		} else if (id.equals(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS)) {
			if (value instanceof PropertyExpressionsDTO) {
				PropertyExpressionsDTO dto = (PropertyExpressionsDTO) value;
				JRPropertyExpression[] expr = jrElement.getPropertyExpressions();
				// Remove the old expression properties if any
				if (expr != null) {
					for (JRPropertyExpression ex : expr)
						jrElement.removePropertyExpression(ex);
				}
				// Add the new expression properties
				for (PropertyExpressionDTO p : dto.getProperties()) {
					if (p.isExpression()) {
						JRDesignPropertyExpression newExp = new JRDesignPropertyExpression();
						newExp.setName(p.getName());
						newExp.setValueExpression(p.getValueAsExpression());
						jrElement.addPropertyExpression(newExp);
					}
				}
				// now change properties, first remove the old ones if any
				String[] names = jrElement.getPropertiesMap().getPropertyNames();
				for (int i = 0; i < names.length; i++) {
					jrElement.getPropertiesMap().removeProperty(names[i]);
				}
				// now add the new properties
				for (PropertyExpressionDTO p : dto.getProperties()) {
					if (!p.isExpression()) {
						jrElement.getPropertiesMap().setProperty(p.getName(), p.getValue());
					}
				}
				this.getPropertyChangeSupport().firePropertyChange(MGraphicElement.PROPERTY_MAP, false, true);
			}
		}
	}

	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}

	public List<ANode> getAMCollection() {
		if (list == null) {
			list = new ArrayList<ANode>();
			ANode node = getParent();
			while (node != null) {
				list.add(node);
				if (node instanceof AMCollection)
					return list;
				node = node.getParent();
			}
		}
		return list;
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		final AMCollection section = getSection();
		if (section != null) {
			if (evt.getPropertyName().equals(section.getCellEvent())) {
				if (evt.getSource() == this.getValue()) {

					final StandardBaseColumn bc = (StandardBaseColumn) evt.getSource();

					final ANode parent = (ANode) getParent();
					final MColumn child = this;
					final int newIndex = parent.getChildren().indexOf(this);

					parent.removeChild(child);

					section.createColumn(parent, bc, 122, newIndex);

					MTable mtable = (MTable) section.getParent();
					if (mtable == null) {
						if (evt.getSource() instanceof JRPropertyChangeSupport) {
							((JRPropertyChangeSupport) evt.getSource()).removePropertyChangeListener(child);
						} else if (evt.getSource() instanceof JRChangeEventsSupport) {
							((JRChangeEventsSupport) evt.getSource()).getEventSupport()
									.removePropertyChangeListener(child);
						}
					} else {
						mtable.getTableManager().refresh();
						TableColumnNumerator.renumerateColumnNames(mtable);
						parent.propertyChange(evt);
					}
				}
			}
		}
		super.propertyChange(evt);
	}

	public AMCollection getSection() {
		return containerSection;
	}

	public MTable getMTable() {
		return containerTable;
	}

	public Rectangle getBounds() {
		StandardBaseColumn c = getValue();
		MTable mc = getMTable();
		if (mc != null && c != null)
			return mc.getTableManager().getBounds(c, type, grName);
		return null;
	}

	public int getDefaultWidth() {
		return 0;
	}

	public int getDefaultHeight() {
		return 0;
	}

	@Override
	public JRPropertiesHolder[] getPropertyHolder() {
		return new JRPropertiesHolder[] { getValue(), getMTable().getValue() };
	}

	public MColumn getNextColumn() {
		ANode parent = getParent();
		ANode child = this;
		// If the resized cell is a group cell go up to find the
		// next to the group
		while (parent instanceof MColumnGroupCell) {
			child = parent;
			parent = parent.getParent();
		}
		int index = parent.getChildren().indexOf(child);
		if (index < parent.getChildren().size() - 1) {
			return (MColumn) parent.getChildren().get(index + 1);
		} else {
			if (parent instanceof MColumnGroup) {
				return ((MColumnGroup) parent).getNextColumn();
			}
		}
		return null;
	}

	@Override
	public ILayout getDefaultLayout() {
		return LayoutManager.getLayout(VerticalRowLayout.class.getName());
	}

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new TableNodeIconDescriptor("tablecell"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Return the table model that contains this column
	 * 
	 * @return the table model that contains this column or null if this column
	 *         is not in hierarchy with a table node
	 */
	public MTable getTable() {
		INode node = this;
		while (node != null && node.getParent() != null && !(node instanceof MTable) && !(node instanceof MRoot)) {
			node = node.getParent();
		}
		if (node instanceof MTable)
			return (MTable) node;
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (ISetValueCommandProvider.class.equals(adapter)) {
			// This type of node return a custom set value command provider that
			// will allow to
			// generate command that will check if the table has the auto-resize
			// and if the changed property
			// need to trigger its refresh
			return TableSetValueCommandProvider.INSTANCE;
		} else if (ExpressionContext.class.equals(adapter)) {
			return getExpressionContext();
		} else {
			return super.getAdapter(adapter);
		}
	}

	public ExpressionContext getExpressionContext() {
		if (containerTable != null) {
			return containerTable.getExpressionContext();
		} else {
			return null;
		}
	}

}
