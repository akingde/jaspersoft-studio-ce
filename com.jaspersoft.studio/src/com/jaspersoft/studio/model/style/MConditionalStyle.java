/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.style;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

/*
 * The Class MConditionalStyle.
 * 
 * @author Chicu Veaceslav
 */
public class MConditionalStyle extends MStyle implements IPropertySource {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;
	
	private static IPropertyDescriptor[] descriptors;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("style"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m conditional style.
	 */
	public MConditionalStyle() {
		super();
	}

	/**
	 * Instantiates a new m conditional style.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrstyle
	 *          the jrstyle
	 * @param newIndex
	 *          the new index
	 */
	public MConditionalStyle(ANode parent, JRDesignConditionalStyle jrstyle, int newIndex) {
		super(parent, newIndex);
		setValue(jrstyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		JRExpression conditionExpression = ((JRDesignConditionalStyle) getValue()).getConditionExpression();
		if (conditionExpression != null)
			return conditionExpression.getText();
		return "<NO CONDITION SET>";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	@Override
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

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		Set<IPropertyDescriptor> toRemove = new HashSet<IPropertyDescriptor>();
		// remove name, defaults
		for (IPropertyDescriptor d : desc) {
			if (d.getId().equals(JRDesignStyle.PROPERTY_NAME))
				toRemove.add(d);
			else if (d.getId().equals(JRDesignStyle.PROPERTY_DEFAULT))
				toRemove.add(d);
		}
		desc.removeAll(toRemove);

		JRExpressionPropertyDescriptor conditionalExpressionD = new JRExpressionPropertyDescriptor(
				JRDesignConditionalStyle.PROPERTY_CONDITION_EXPRESSION, Messages.MConditionalStyle_conditional_expression);
		conditionalExpressionD.setCategory(Messages.MConditionalStyle_properties_category);
		conditionalExpressionD.setDescription(Messages.MConditionalStyle_conditional_expression_description);
		desc.add(conditionalExpressionD);

	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		for(IPropertyDescriptor desc : descriptors){
			if(JRDesignConditionalStyle.PROPERTY_CONDITION_EXPRESSION.equals(desc.getId())){
				// fix the conditional expression context
				((JRExpressionPropertyDescriptor) desc).setExpressionContext(
						ExpressionEditorSupportUtil.getReportExtendedExpressionContext());
				return;
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignConditionalStyle jrstyle = (JRDesignConditionalStyle) getValue();
		if (id.equals(JRDesignConditionalStyle.PROPERTY_CONDITION_EXPRESSION))
			return ExprUtil.getExpression(jrstyle.getConditionExpression());
		return super.getPropertyValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		if (isEditable()) {
			JRDesignConditionalStyle jrstyle = (JRDesignConditionalStyle) getValue();
			if (id.equals(JRDesignConditionalStyle.PROPERTY_CONDITION_EXPRESSION)){
				jrstyle.setConditionExpression(ExprUtil.setValues(jrstyle.getConditionExpression(), value));
			} else if (id.equals(JRDesignStyle.PROPERTY_PARENT_STYLE)){
				//Needed special code to set the parent style of a conditional style, since the method are not inerithed
				//from the same class of the standard style
				if (!Misc.isNullOrEmpty((String) value)) {
					JRStyle style = (JRStyle) getJasperDesign().getStylesMap().get(value);
					if (style != null) {
						jrstyle.setParentStyle(style);
					} else {
						//Set the external style as parent style if existing, to resolve JR resolving problem at design time
						jrstyle.setParentStyle(ExternalStylesManager.getExternalStyle((String) value, getJasperConfiguration()));
					}
				} else {
					//remove the style
					jrstyle.setParentStyle(null);
				}
			}		
			else
				super.setPropertyValue(id, value);
		}
	}

	/**
	 * The attribute of the conditional styles are never shown at design time
	 */
	@Override
	protected boolean isDependencyStyle(){
		return false;
	}
	
	/**
	 * Creates the jr style.
	 * 
	 * @return the jR design conditional style
	 */
	public static JRDesignConditionalStyle createJRStyle() {
		JRDesignConditionalStyle jrDesignConditionalStyle = new JRDesignConditionalStyle();
		return jrDesignConditionalStyle;
	}

	@Override
	public ICopyable.RESULT isCopyable2(Object parent) {
		//Check if the destination node is a style of a report
		if (parent instanceof MStyle){
			MStyle parentStyle = (MStyle)parent;
			if (parentStyle.getParent() instanceof MStyles){
				return ICopyable.RESULT.COPYABLE;
			}
		}
		return ICopyable.RESULT.NOT_COPYABLE;
	}
}
