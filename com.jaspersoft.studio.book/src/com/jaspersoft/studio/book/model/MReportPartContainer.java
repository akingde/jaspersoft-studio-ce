/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JROrigin;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.JRSection;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.type.BandTypeEnum;

public class MReportPartContainer extends APropertyNode {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	public static final String PROPERTY_CONTAINER_TYPE = "property_partcontainer_type";
	
	public static final String JSSPROPERTY_GROUPLABEL_PREFIX = "com.jaspersoft.studio.book.group.";
	public static final String JSSPROPERTY_GROUPLABEL_HEADER_POSTFIX = ".header";
	public static final String JSSPROPERTY_GROUPLABEL_FOOTER_POSTFIX = ".footer";
	
	
	// The icon descriptor
	private static IIconDescriptor iconDescriptor;
	// Array of property descriptors
	private static IPropertyDescriptor[] descriptors;
	
	public MReportPartContainer(ANode parent, JRSection jrsection, int newIndex){
		super(parent,newIndex);
		setValue(jrsection);
	}
	
	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("reportpartcontainer"); //$NON-NLS-1$
		return iconDescriptor;
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignSection jrsection = getValue();
		if(jrsection!=null){
			if(JRDesignSection.PROPERTY_PARTS.equals(id)){
				return jrsection.getPartsList();
			}
			if(PROPERTY_CONTAINER_TYPE.equals(id)){
				JROrigin origin = jrsection.getOrigin();
				if(origin!=null){
					// Valid return values should be only:
					//	- GROUP_HEADER
					// 	- GROUP_FOOTER
					//	- DETAIL
					return origin.getBandTypeValue();
				}
				else {
					return BandTypeEnum.UNKNOWN;
				}
			}
		}
		return null;
	}
	
	@Override
	public JRDesignSection getValue() {
		return (JRDesignSection) super.getValue();
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignSection jrsection = getValue();
		if(jrsection!=null){
			if(JRDesignSection.PROPERTY_PARTS.equals(id)){
				// Remove all old parts
				JRPart[] parts = jrsection.getParts();
				for(JRPart p : parts){
					jrsection.removePart(p);
				}
				// Add new ones
				List<JRPart> newParts = (List<JRPart>) value;
				for(JRPart p : newParts){
					jrsection.addPart(p);
				}
			}
		}
	}

	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public boolean isDetail() {
		BandTypeEnum type = (BandTypeEnum) getPropertyValue(PROPERTY_CONTAINER_TYPE);
		return BandTypeEnum.DETAIL.equals(type);
	}
	
	/**
	 * We want to display a meaningful name for this part.
	 * The name of the group is more than logical, but a better value for the label.
     * We use a custom properties for this
	 */
	@Override
	public String getDisplayText() {
		if(isDetail()){
			return "Content";
		}
		return "<UNDEFINED>";
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors=descriptors1;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRDesignSection.PROPERTY_PARTS)){
			if (evt.getNewValue() == null && evt.getOldValue() != null){
				for(INode node : getChildren()){
					if (node.getValue() == evt.getOldValue()){
						removeChild((ANode)node);
						break;
					}
				}
			}
			if (evt.getNewValue() != null && evt.getOldValue() == null){
				JRDesignSection jrsection = getValue();
				int partIndex = jrsection.getPartsList().indexOf(evt.getNewValue());
				new MReportPart(this, (JRPart)evt.getNewValue(), partIndex);
			}
		}
		super.propertyChange(evt);
	}

	@Override
	public Object getAdapter(Class adapter) {
		if(ExpressionContext.class.equals(adapter)){
			return ExpressionEditorSupportUtil.getReportExpressionContext();
		}
		return super.getAdapter(adapter);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		//this node has no proprerties
	}
}
