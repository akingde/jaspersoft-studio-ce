/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.parameter;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

import net.sf.jasperreports.crosstabs.JRCrosstabParameter;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabParameter;
import net.sf.jasperreports.engine.design.JRDesignParameter;

public class MCrosstabParameter extends MParameter {

	private static final long serialVersionUID = 8799510130465402431L;
	
	//Must use its own descriptors since they are different from the ones of the superclass
	
	private static IPropertyDescriptor[] descriptors;

	/**
	 * Create a parameter model node for a JRDesignCrosstabParameter
	 * 
	 * @param parent the parent
	 * @param jrParameter a JRDesignCrosstabParameter
	 * @param newIndex the new index of the node
	 */
	public MCrosstabParameter(ANode parent, JRDesignCrosstabParameter jrParameter, int newIndex) {
		super(parent, jrParameter, newIndex);
	}
	
	/**
	 * Instantiates a new m parameter.
	 */
	public MCrosstabParameter() {
		super();
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignCrosstabParameter parameter = (JRDesignCrosstabParameter)getValue();
		if (id.equals(JRDesignCrosstabParameter.PROPERTY_VALUE_EXPRESSION)){
			return ExprUtil.getExpression(parameter.getExpression());
		}
		return super.getPropertyValue(id);
	}
	
	@Override
	public void setPropertyValue(Object id, Object value) {
		if (!isEditable())
			return;
	
		JRDesignCrosstabParameter parameter = (JRDesignCrosstabParameter)getValue();
		
		if (id.equals(JRDesignParameter.PROPERTY_NAME)) {
			if (!value.equals("")) { //$NON-NLS-1$
				JRDesignCrosstab crosstab = getMCrosstab().getValue();
				JRCrosstabParameter[] parameters = crosstab.getParameters();
				for (JRCrosstabParameter p : parameters) {
					if (p == parameter)
						continue;
					if (p.getName().equals(value)) {
						// warn?
						return;
					}
				}
				parameter.setName((String) value);
			}
		} else if (id.equals(JRDesignCrosstabParameter.PROPERTY_VALUE_EXPRESSION)){
			parameter.setExpression(ExprUtil.setValues(parameter.getExpression(), value));
		}
		else super.setPropertyValue(id, value);
	}
	
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);
		
		JRExpressionPropertyDescriptor valueExprD = new JRExpressionPropertyDescriptor(JRDesignCrosstabParameter.PROPERTY_VALUE_EXPRESSION, 
																					   Messages.MCrosstabParameter_valueLabel);
		valueExprD.setDescription(Messages.MCrosstabParameter_valueDescription);
		desc.add(valueExprD);
	}
	
	@Override
	public ExpressionContext getExpressionContext() {
		MCrosstab crosstab = getMCrosstab();
		if (crosstab != null){
			return new ExpressionContext(crosstab.getValue(), crosstab.getJasperConfiguration());
		}
		return null;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//Override the method for the name, to update the parameters map
		if (JRDesignParameter.PROPERTY_NAME.equals(evt.getPropertyName())) {
			MCrosstab crosstab = getMCrosstab();
			if (crosstab != null){
				JRDesignCrosstabParameter jrParameter = (JRDesignCrosstabParameter) getValue();
				JRDesignCrosstab jrCrosstab = crosstab.getValue();
				jrCrosstab.getParametersMap().remove(evt.getOldValue());
				jrCrosstab.getParametersMap().put(jrParameter.getName(), jrParameter);
				//Since this event will not arrive to the designer to refresh it then force manually with a new event
				jrParameter.getEventSupport().firePropertyChange(MGraphicElement.FORCE_GRAPHICAL_REFRESH, null, null);
			}
		} else super.propertyChange(evt);
	}
	
	/**
	 * Return the parent crosstab node if available
	 * 
	 * @return the parent MCrosstab or null if it can't be found
	 */
	public MCrosstab getMCrosstab(){
		if (getParent() != null){
			return (MCrosstab) getParent().getParent();
		}
		return null;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}
	
	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}
}
