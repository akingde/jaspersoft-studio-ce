/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySource2;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.help.HelpPrefixBuilder;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.property.ElementLabelProvider;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignElement;

public abstract class APropertyNode extends ANode implements IPropertySource, IPropertySource2 {
	
	/**
	 * Static default map used to keep the defaults value of every implementation of a property node.
	 */
	private static HashMap<Class<? extends APropertyNode>, Map<String, DefaultValue>> defaultsMap = 
														new HashMap<Class<? extends APropertyNode>, Map<String,DefaultValue>>();
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String PROPERTY_MAP = "PROPERTY_MAP"; //$NON-NLS-1$
	
	public APropertyNode() {
		super();
	}

	public APropertyNode(ANode parent, int newIndex) {
		super(parent, newIndex);
		
	}

	public boolean isPropertyResettable(Object id) {
		return true;
	}

	/**
	 * Return the default map of this node. First is chekced if it is already available
	 * in the cache map, in that case is returned otherwise it is created, stored and returned
	 * 
	 * @return a map of the default value, could be null
	 */
	public Map<String, DefaultValue> getDefaultsMap(){
		Map<String, DefaultValue> result = defaultsMap.get(this.getClass());
		if (result == null){
			result = createDefaultsMap();
			defaultsMap.put(this.getClass(), result);
		}
		return result;
	}
	
	protected Map<String, DefaultValue> createDefaultsMap(){
		return new HashMap<String, DefaultValue>();
	}

	public abstract void setDescriptors(IPropertyDescriptor[] descriptors1);

	public abstract IPropertyDescriptor[] getDescriptors();

	/**
	 * Return the actual value of an attribute, so the value that the system is using, not considering if it's inherited
	 * or of the element
	 * 
	 * @param id
	 *          of the attribute
	 * @return the attribute value.
	 */
	public Object getPropertyActualValue(Object id) {
		return getPropertyValue(id);
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public abstract void createPropertyDescriptors(List<IPropertyDescriptor> desc);

	/**
	 * @param descriptors
	 */
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		// Property descriptors that involve the use of an expression
		// should have an expression context.
		// Most of the times the right context can be get using directly the node information.
		// Sometimes the context must be customized (i.e: dataset run related expressions).
		// In this case the clients should override this method, but also be sure to call
		// the superclass one first in order not to break the expression context setting of
		// other property descriptors.
		try {
			ExpressionContext elementExpressionContext = getExpressionContext();
			for (IPropertyDescriptor desc : descriptors) {
				if (desc instanceof IExpressionContextSetter) {
					((IExpressionContextSetter) desc).setExpressionContext(elementExpressionContext);
				}
			}
		} catch (Exception ex) {
			// Unable to get a valid context expression, a default one will be used.
			// Maybe we should log for debug purpose.
		}
	}

	/**
	 * Tries to get a proper expression context for the current node.
	 * First step is trying to get the expression context through a valid adapter from the node object.
	 * <p>
	 * 
	 * NOTE: subclasses that override this method <em>SHOULD NOT</em> call superclass implementation,
	 * since it can lead to StackOverflowError exceptions.
	 *  
	 * @return a valid expression context, <code>null</code> otherwise
	 * @see #getAdapter(Class)
	 * @see ExpressionContext
	 */
	public ExpressionContext getExpressionContext() {
		JRDesignElement designEl = null;
		if (this.getValue() instanceof JRDesignElement) {
			designEl = (JRDesignElement) this.getValue();
		}
		ExpressionContext elementExpressionContext = (ExpressionContext) this.getAdapter(ExpressionContext.class);
		if (elementExpressionContext == null) {
			elementExpressionContext = ModelUtils.getElementExpressionContext(designEl, this);
		}
		return elementExpressionContext;
	}

	public IPropertyDescriptor getPropertyDescriptor(Object id) {
		IPropertyDescriptor[] descriptors = getPropertyDescriptors();
		for (IPropertyDescriptor pd : descriptors)
			if (pd.getId().equals(id))
				return pd;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// if we cache sections ... we have to return descriptors always
		// if (getValue() == null)
		// return new IPropertyDescriptor[0];
		IPropertyDescriptor[] descriptors = getDescriptors();
		if (descriptors == null) {
			List<IPropertyDescriptor> desc = new ArrayList<IPropertyDescriptor>();

			createPropertyDescriptors(desc);

			descriptors = desc.toArray(new IPropertyDescriptor[desc.size()]);
			setDescriptors(descriptors);
		}
		postDescriptors(descriptors);
		return descriptors;
	}

	protected void setHelpPrefix(List<IPropertyDescriptor> desc, String prefix) {
		for (IPropertyDescriptor pd : desc)
			if (pd instanceof IHelp && ((IHelp) pd).getHelpReference() == null)
				((IHelp) pd).setHelpRefBuilder(new HelpPrefixBuilder(prefix, pd));
	}

	/**
	 * Return a list of the attribute descriptor that depends from a style
	 * 
	 * @return Hashmap where the key is the attribute id, and the value it's the attribute itself
	 */
	public HashMap<String, Object> getStylesDescriptors() {
		return new HashMap<String, Object>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
	 */
	public boolean isPropertySet(Object id) {
		try {
			Object def = getPropertyDefaultValue((String) id);
			Object propertyValue = getPropertyValue(id);
			if ((def != null && !def.equals(propertyValue)) || (def == null && propertyValue != null))
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * @param id
	 * @return default value
	 */
	public Object getPropertyDefaultValue(String id) throws Exception {
		Map<String, DefaultValue> defaultsMap = getDefaultsMap();
		if (defaultsMap != null && defaultsMap.containsKey(id))
			return defaultsMap.get(id).getValue();
		throw new Exception("Key not found"); //$NON-NLS-1$
	}

	public void initProperties() {
		IPropertyDescriptor[] pd = getPropertyDescriptors();
		for (int i = 0; i < pd.length; i++) {
			try {
				Object o = getPropertyDefaultValue((String) pd[i].getId());
				setPropertyValue(pd[i].getId(), o);
			} catch (Exception e) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	public void resetPropertyValue(Object id) {
		try {
			setPropertyValue(id, getPropertyDefaultValue((String) id));
		} catch (Exception e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	public Object getEditableValue() {
		return this;
	}

	/**
	 * Returns a custom title that should be shown in the property sheets page when the node is selected.<br>
	 * Actually this method returns <code>null</code>, so the standard behavior provided by the contributed label provider
	 * {@link ElementLabelProvider} is used.
	 * <p>
	 * 
	 * Nodes (sub-classes) that want to provide a different behavior should override this method and provide a meaningful
	 * human-readable text.
	 * 
	 * @return a custom title
	 * 
	 * @see ElementLabelProvider#getText(Object)
	 */
	public String getCustomPropertyTitle() {
		return null;
	}
}
