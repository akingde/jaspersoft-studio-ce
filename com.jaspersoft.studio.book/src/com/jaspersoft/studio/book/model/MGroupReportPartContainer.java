/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.model;

import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.book.descriptors.GroupNameValidator;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSValidatedTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRSection;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.type.BandTypeEnum;

/**
 * Extension of an {@link MReportPartContainer}, used for report group. 
 * Differently from a {@link MReportPartContainer} this provide properties
 * and methods to configure the group
 */
public class MGroupReportPartContainer extends MReportPartContainer {
	
	private static final long serialVersionUID = 6026733285849479681L;
	
	private static GroupNameValidator validator;
	
	private JRDesignGroup jrgroup;
	
	public MGroupReportPartContainer(ANode parent, JRSection jrsection, int newIndex) {
		super(parent, jrsection, newIndex);
	}

	@Override
	public Object getPropertyActualValue(Object id) {
		if(JRDesignGroup.PROPERTY_NAME.equals(id) && getJrgroup() != null){
			return getJrgroup().getName();
		} else if (JRDesignGroup.PROPERTY_EXPRESSION.equals(id) && getJrgroup() != null){
			return getJrgroup().getExpression();
		} else return super.getPropertyActualValue(id);
	}
	
	@Override
	public void setPropertyValue(Object id, Object value) {
		if(JRDesignGroup.PROPERTY_NAME.equals(id) && getJrgroup() != null){
			getJrgroup().setName((String)value);
			this.getPropertyChangeSupport().firePropertyChange(JRDesignGroup.PROPERTY_NAME, false, true);
		} else if (JRDesignGroup.PROPERTY_EXPRESSION.equals(id) && getJrgroup() != null){
			getJrgroup().setExpression((JRExpression)value);
			this.getPropertyChangeSupport().firePropertyChange(JRDesignGroup.PROPERTY_EXPRESSION, false, true);
		} else super.setPropertyValue(id, value);
	}
	

	public boolean isGroupHeader() {
		BandTypeEnum type = (BandTypeEnum) getPropertyValue(PROPERTY_CONTAINER_TYPE);
		return BandTypeEnum.GROUP_HEADER.equals(type) && getJrgroup() !=null;
	}
	
	public boolean isGroupFooter() {
		BandTypeEnum type = (BandTypeEnum) getPropertyValue(PROPERTY_CONTAINER_TYPE);
		return BandTypeEnum.GROUP_FOOTER.equals(type) && getJrgroup() !=null;
	}
	
	public void setJRGroup(JRGroup group){
		this.jrgroup = (JRDesignGroup)group;
	}
	
	public JRDesignGroup getJrgroup() {
		return jrgroup;
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
		else if(isGroupFooter()){
			String grpName = getJrgroup() .getName();
			return ModelUtils.getReportPropertyValue(
					getJasperDesign(), 
					JSSPROPERTY_GROUPLABEL_PREFIX+grpName+JSSPROPERTY_GROUPLABEL_FOOTER_POSTFIX,
					getJrgroup() .getName());
		}
		else if(isGroupHeader()){
			String grpName = getJrgroup() .getName();
			return ModelUtils.getReportPropertyValue(
					getJasperDesign(), 
					JSSPROPERTY_GROUPLABEL_PREFIX+grpName+JSSPROPERTY_GROUPLABEL_HEADER_POSTFIX,
					getJrgroup() .getName());			
		}
		return "<UNDEFINED>";
	}
	
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		validator = new GroupNameValidator();
		validator.setTargetNode(this);
		
		if(!isDetail()) {
			JSSTextPropertyDescriptor nameD = new JSSValidatedTextPropertyDescriptor(JRDesignGroup.PROPERTY_NAME, Messages.common_name, validator);
			nameD.setDescription(Messages.MGroup_name_description);
			desc.add(nameD);
	
			JRExpressionPropertyDescriptor expressionD = new JRExpressionPropertyDescriptor(JRDesignGroup.PROPERTY_EXPRESSION,Messages.common_expression);
			expressionD.setDescription(Messages.MGroup_expression_description);
			desc.add(expressionD);
		}
	}
	
	/**
	 * Update the reference into the static validator when the actual group is 
	 * edited
	 */
	public void updateValidator(){
		validator.setTargetNode(this);
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		//Set into the validator the actual reference
		updateValidator();
	}
}
