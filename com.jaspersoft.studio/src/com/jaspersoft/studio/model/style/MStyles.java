/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.beans.PropertyChangeEvent;
import java.util.List;

import net.sf.jasperreports.engine.JRConditionalStyle;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.model.util.ReportFactory;

/*
 * The Class MStyles.
 * 
 * @author Chicu Veaceslav
 */
public class MStyles extends ANode implements IPastable, IContainerEditPart {
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
			iconDescriptor = new NodeIconDescriptor("styles"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m styles.
	 * 
	 * @param parent
	 *          the parent
	 */
	public MStyles(ANode parent) {
		this(parent, -1);
	}

	public MStyles(ANode parent, int index) {
		super(parent, index);
		setValue(getJasperDesign());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
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
		return getIconDescriptor().getToolTip();
	}
	
	private JRStyle getDefaultStyle(List<INode> children){
		for(INode node : children){
			if (node instanceof MStyleTemplate){
				JRStyle result = getDefaultStyle(node.getChildren());
				if (result != null) return result;
			} else if (node instanceof MStyle){
				JRStyle style = (JRStyle)node.getValue();
				if (style.isDefault()) return style;
			}
		}
		return null;
	}
	
	public void updateDefaulStyle(){
		JasperDesign jd = getJasperDesign();
		if (jd != null){
			JRStyle oldDefault = jd.getDefaultStyle();
			JRStyle newDefault = getDefaultStyle(getChildren());
			if (oldDefault != newDefault){
				//remove the flag from the old style, but only if it is an internal style
				if (oldDefault != null && jd.getStylesList().contains(oldDefault)){
					((JRDesignStyle)oldDefault).setDefault(false);
				}
				jd.setDefaultStyle(newDefault);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.ANode#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JasperDesign.PROPERTY_STYLES) || evt.getPropertyName().equals(JasperDesign.PROPERTY_TEMPLATES) && evt.getSource() == getValue()) {
			if (evt.getOldValue() == null && evt.getNewValue() != null) {
				int newIndex = -1;
				if (evt instanceof CollectionElementAddedEvent) {
					newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
				}
				if (evt.getNewValue() instanceof JRDesignReportTemplate)
					newIndex = 0;
				// add the node to this parent
				ANode style = ReportFactory.createNode(this, evt.getNewValue(), newIndex);
				if (evt.getNewValue() instanceof JRDesignStyle) {
					JRDesignStyle jrStyle = (JRDesignStyle) evt.getNewValue();
					for (JRConditionalStyle it : jrStyle.getConditionalStyleList()) {
						ReportFactory.createNode(style, it, -1);
					}
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
			super.propertyChange(evt);
		} else if (evt.getPropertyName().equals(JRDesignStyle.PROPERTY_DEFAULT) || evt.getPropertyName().equals(JasperDesign.PROPERTY_TEMPLATES)){
			//A style default flag has been changed or a external style has been added removed, need to update the default styles
			updateDefaulStyle();
		}
	}

}
