/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.gef.figures.APageFigure;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * The Class MReport.
 * 
 * @author Chicu Veaceslav
 */
public class MPage extends MLockableRefresh implements IGraphicElement, IContainerEditPart {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static IPropertyDescriptor[] descriptors;

	private Map<Object, ANode> obj2Node = new HashMap<Object, ANode>();

	private ANode realParent;

	@Override
	public INode getRoot() {
		return this;
	}

	@Override
	public void register(Object key, ANode value) {
		if (key != null) {
			obj2Node.put(key, value);
		}
	}
	
	@Override
	public void unregister(Object key) {
		if (key != null) {
			obj2Node.remove(key);
		}
	}

	public ANode getNode(Object obj) {
		return obj2Node.get(obj);
	}

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("report"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m report.
	 * 
	 * @param parent
	 *            the parent
	 * @param jd
	 *            the jd
	 */
	public MPage(ANode parent, JasperDesign jd) {
		super(parent, -1);
		setValue(jd);
	}

	private MDataset getDataset(JasperDesign jrDesign) {
		MDataset mDataset = new MDataset(getReport(), (JRDesignDataset) jrDesign.getMainDataset());
		mDataset.setJasperConfiguration(getJasperConfiguration());
		return mDataset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	@Override
	public Object getEditableValue() {
		return this;
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JasperDesign jrDesign = (JasperDesign) getValue();
		if (id.equals(MGraphicElement.PROPERTY_MAP)) {
			// to avoid duplication I remove it first
			return (JRPropertiesMap) jrDesign.getPropertiesMap().cloneProperties();
		} else if (id.equals(JasperDesign.PROPERTY_MAIN_DATASET)) {
			return getDataset(jrDesign);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.
	 * Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JasperDesign jrDesign = (JasperDesign) getValue();
		if (id.equals(MGraphicElement.PROPERTY_MAP)) {
			JRPropertiesMap v = (JRPropertiesMap) value;
			String[] names = jrDesign.getPropertiesMap().getPropertyNames();
			for (int i = 0; i < names.length; i++)
				jrDesign.getPropertiesMap().removeProperty(names[i]);
			names = v.getPropertyNames();

			for (String str : v.getPropertyNames())
				jrDesign.setProperty(str, v.getProperty(str));
			this.getPropertyChangeSupport().firePropertyChange(MGraphicElement.PROPERTY_MAP, false, true);
		}
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
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JasperDesign) getValue()).getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getDefaultHeight()
	 */
	public int getDefaultHeight() {
		return 800;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getDefaultWidth()
	 */
	public int getDefaultWidth() {
		return 800;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#createJRElement(net.sf.
	 * jasperreports.engine.design.JasperDesign)
	 */
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.IGraphicElement#getBounds()
	 */
	public Rectangle getBounds() {
		return new Rectangle(APageFigure.PAGE_BORDER.left, APageFigure.PAGE_BORDER.top, 300, 400);
	}

	/**
	 * Set the real parent of the child of the page, this is used when the page is
	 * created to open a subeditor. In that case the classic chain of parents is not
	 * valid anymore since a separate editor has a new root separated from the rest
	 * of the report. So this field is provided to keep a reference to the real
	 * parent of the element opened in the editor
	 * 
	 * @param realParent
	 *            real parent of the element opened in the editor
	 */
	public void setRealParent(ANode realParent) {
		this.realParent = realParent;
	}

	/**
	 * Get the real parent of the child of the page, this is used when the page is
	 * created to open a subeditor. In that case the classic chain of parents is not
	 * valid anymore since a separate editor has a new root separated from the rest
	 * of the report. So this field is provided to keep a reference to the real
	 * parent of the element opened in the editor
	 * 
	 * @param realParent
	 *            real parent of the element opened in the editor
	 */
	public ANode getRealParent() {
		return realParent;
	}

	/**
	 * The Page return the list of styles of the children of the real parent of the
	 * page, among to the list of the styles of the current node
	 */
	@Override
	public Map<String, List<ANode>> getUsedStyles() {
		Map<String, List<ANode>> result = super.getUsedStyles();
		for (INode child : getRealParent().getChildren()) {
			if (child instanceof ANode) {
				mergeElementStyle(result, ((ANode) child).getUsedStyles());
			}
		}
		for (INode child : getChildren()) {
			if (child instanceof ANode) {
				mergeElementStyle(result, ((ANode) child).getUsedStyles());
			}
		}
		return result;
	}

	/**
	 * Search the report node going up in hierarchy
	 * 
	 * @return an MReport node or null if it can't be found
	 */
	protected MReport getReport() {
		ANode parent = getRealParent();
		while ((parent != null) && !(parent instanceof MReport)) {
			if (parent instanceof MPage) {
				parent = ((MPage) parent).getRealParent();
			} else {
				parent = parent.getParent();
			}
		}
		return (MReport) parent;
	}

	/**
	 * Create or remove a lock for the refresh. It will apply the lock also on the
	 * report, to assure to avoid the refresh also on the main editor
	 * 
	 * @param ignore
	 *            true if the refresh should be ignored, false otherwise
	 * @param caller
	 *            who wants to put or to remove its previous lock
	 */
	public void setIgnoreEvents(boolean ignore, Object caller) {
		super.setIgnoreEvents(ignore, caller);
		MReport parentReport = getReport();
		if (parentReport != null) {
			parentReport.setIgnoreEvents(ignore, caller);
		}
	}

}
