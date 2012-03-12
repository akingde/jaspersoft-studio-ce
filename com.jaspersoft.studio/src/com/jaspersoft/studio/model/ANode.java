/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;

import org.eclipse.gef.EditPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/*
 * The Class ANode.
 * 
 * @author Chicu Veaceslav
 */
public abstract class ANode implements INode, Serializable {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/** The parent. */
	private ANode parent;

	/** The children. */
	private List<INode> children = new ArrayList<INode>();

	/** The value. */
	private Object value;

	/** The property change support. */
	private PropertyChangeSupport propertyChangeSupport;

	/**
	 * Instantiates a new a node.
	 */
	public ANode() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	public String getToolTip() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getFont()
	 */
	public Font getFont() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getForeground()
	 */
	public Color getForeground() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getBackground()
	 */
	public Color getBackground() {
		return null;
	}

	/**
	 * Instantiates a new a node.
	 * 
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ANode(ANode parent, int newIndex) {
		if (parent != null)
			setParent(parent, newIndex);
	}

	public ANode(ANode parent, Object value, int newIndex) {
		if (parent != null)
			setParent(parent, newIndex);
		setValue(value);
	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null) {
			iconDescriptor = new NodeIconDescriptor("report"); //$NON-NLS-1$
		}
		return iconDescriptor;
	}

	/**
	 * Gets the root.
	 * 
	 * @return the root
	 */
	public INode getRoot() {
		INode node = this;
		while (!(node instanceof MReport) && !(node instanceof MRoot)) {
			if (node == null || node.getParent() == null)
				return this;
			node = node.getParent();
		}
		return node;
	}

	/**
	 * Sets the parent.
	 * 
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public void setParent(ANode parent, int newIndex) {
		if (parent == null) {
			unregister();
			getPropertyChangeSupport().removePropertyChangeListener(parent);
			if (this.parent != null && this.parent.getChildren() != null) {
				this.parent.getChildren().remove(this);
			}
			this.parent = null;
			unsetDependents();
		} else {
			register();
			this.parent = parent;
			if (newIndex >= 0 && newIndex < parent.getChildren().size())
				parent.getChildren().add(newIndex, this);
			else
				parent.getChildren().add(this);
			getPropertyChangeSupport().addPropertyChangeListener(parent);
		}
	}

	Set<ANode> dependents;

	public void setChildListener(ANode child) {
		if (child != null)
			child.getPropertyChangeSupport().addPropertyChangeListener(this);
		if (dependents == null)
			dependents = new HashSet<ANode>();
		dependents.add(child);
	}

	public void unsetChildListener(ANode child) {
		if (child != null)
			child.getPropertyChangeSupport().removePropertyChangeListener(this);
		if (dependents != null)
			dependents.remove(child);
	}

	public void unsetDependents() {
		if (dependents != null) {
			for (ANode n : dependents)
				n.getPropertyChangeSupport().removePropertyChangeListener(this);
			dependents.clear();
		}
	}

	/**
	 * Adds the child.
	 * 
	 * @param child
	 *          the child
	 */
	public void addChild(ANode child) {
		child.setParent(this, -1);
	}

	public void addChild(ANode child, int index) {
		child.setParent(this, index);
	}

	public boolean hasParent(ANode node) {
		if (parent == null)
			return false;
		return parent.equals(node) || parent.hasParent(node);
	}

	public ANode[] flatten() {
		ArrayList<ANode> result = new ArrayList<ANode>();
		doFlatten(this, result);
		return (ANode[]) result.toArray(new ANode[result.size()]);
	}

	private void doFlatten(ANode node, ArrayList<ANode> all) {
		// add the gadget and its children to the list
		all.add(node);
		List<INode> children = node.getChildren();
		for (INode n : children) {
			doFlatten((ANode) n, all);
		}
	}

	/**
	 * Removes the child.
	 * 
	 * @param child
	 *          the child
	 */
	public void removeChild(ANode child) {
		child.setParent(null, -1);
	}

	/**
	 * Removes the children.
	 */
	public void removeChildren() {
		removeChildren(getChildren());
	}

	/**
	 * Removes the children.
	 */
	public void removeChildren(List<INode> children) {
		Object[] array = children.toArray();
		for (int i = 0; i < array.length; i++)
			removeChild((ANode) array[i]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getPropertyChangeSupport()
	 */
	public PropertyChangeSupport getPropertyChangeSupport() {
		if (propertyChangeSupport == null)
			propertyChangeSupport = new PropertyChangeSupport(this);
		return propertyChangeSupport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRDesignElementGroup.PROPERTY_CHILDREN)) {
			if (evt.getSource() == getValue()) {
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
		// PropertyChangeEvent newEvent = evt;
		// if (evt.getSource() instanceof ANode) {
		// ANode enode = (ANode) evt.getSource();
		// if (dependents.contains(enode)) {
		// newEvent = new PropertyChangeEvent(this, evt.getPropertyName(), evt.getOldValue(),
		// evt.getNewValue());
		// }
		// } else {
		// newEvent = new PropertyChangeEvent(evt.getSource(), evt.getPropertyName(), evt.getOldValue(),
		// evt.getNewValue());
		// }
		getPropertyChangeSupport().firePropertyChange(evt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getChildren()
	 */
	public List<INode> getChildren() {
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getParent()
	 */
	public ANode getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getValue()
	 */
	public Object getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#setValue(java.lang.Object)
	 */
	public void setValue(Object value) {
		unregister();
		if (this.value != null && this.value instanceof JRChangeEventsSupport) {
			((JRChangeEventsSupport) this.value).getEventSupport().removePropertyChangeListener(this);
		}
		if (value != null) {
			this.value = value;
			if (this.value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) this.value).getEventSupport().addPropertyChangeListener(this);
			register();
			return;
		}
		this.value = value;
	}

	public void register() {
		INode root = getRoot();
		if (root instanceof MReport) {
			MReport mRoot = (MReport) root;
			if (mRoot != null)
				mRoot.register(this);
		}
	}

	public void unregister() {
		INode root = getRoot();
		if (root instanceof MReport) {
			MReport mRoot = (MReport) getRoot();
			if (mRoot != null)
				mRoot.unregister(this);
		}
	}

	public EditPart getFigureEditPart() {
		for (Object o : propertyChangeSupport.getPropertyChangeListeners()) {
			if (o instanceof FigureEditPart)
				return (EditPart) o;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getJasperDesign()
	 */
	public JasperDesign getJasperDesign() {
		if (getRoot().getValue() instanceof JasperDesign)
			return (JasperDesign) getRoot().getValue();
		return null;
	}

	private JasperReportsConfiguration jConfig;

	public void setJasperConfiguration(JasperReportsConfiguration jConfig) {
		this.jConfig = jConfig;
	}

	public JasperReportsConfiguration getJasperConfiguration() {
		if (jConfig != null)
			return jConfig;
		return parent.getJasperConfiguration();
	}

	public int findElement(Object obj) {
		if (obj == null)
			return -1;
		List<INode> children2 = getChildren();
		for (int i = 0; i < children2.size(); i++) {
			if (children2.get(i).getValue() == obj)
				return i;
		}
		return -1;
	}

	private boolean cut = false;

	public boolean isCut() {
		return cut;
	}

	public void setCut(boolean cut) {
		this.cut = cut;
	}

	public ANode clone() {
		try {
			ANode clone = (ANode) super.clone();
			if (getChildren() != null) {
				clone.removeChildren();
				for (INode n : getChildren()) {
					clone.addChild(((ANode) n).clone());
				}
			}
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new JRRuntimeException(e);
		}
	}
}
