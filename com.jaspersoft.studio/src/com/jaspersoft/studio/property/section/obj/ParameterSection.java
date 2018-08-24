/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.obj;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.EnumSet;

import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionContext.Visibility;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

public class ParameterSection extends AbstractSection {

	private Composite cmp;
	private Composite cmpP;

	private Composite rootComposite;

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent.setLayout(new GridLayout(1, false));

		rootComposite = getWidgetFactory().createComposite(parent);
		GridLayout rootLayout = new GridLayout(2, false);
		// rootLayout.horizontalSpacing = 0;
		rootLayout.marginHeight = 0;
		rootLayout.marginWidth = 0;
		// rootLayout.verticalSpacing=0;
		rootComposite.setLayout(rootLayout);
		GridData rootData = new GridData(GridData.FILL_HORIZONTAL);
		rootComposite.setLayoutData(rootData);

		cmp = getWidgetFactory().createComposite(rootComposite);
		GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		cmp.setLayout(layout);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(cmp, JRDesignParameter.PROPERTY_NESTED_TYPE_NAME).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(rootComposite, JRDesignParameter.PROPERTY_DESCRIPTION).getControl().setLayoutData(gd);

		cmpP = getWidgetFactory().createComposite(rootComposite);
		layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		cmpP.setLayout(layout);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		cmpP.setLayoutData(gd);

		createWidget4Property(cmpP, JRDesignParameter.PROPERTY_FOR_PROMPTING, false);

		createWidget4Property(rootComposite, JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION);
		createWidget4Property(rootComposite, JRDesignParameter.PROPERTY_EVALUATION_TIME);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignParameter.PROPERTY_NESTED_TYPE_NAME, Messages.MParameter_nested_type_name);
		addProvidedProperties(JRDesignParameter.PROPERTY_DESCRIPTION, Messages.common_description);
		addProvidedProperties(JRDesignParameter.PROPERTY_FOR_PROMPTING, Messages.MParameter_is_for_prompting);
		addProvidedProperties(JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION,
				Messages.MParameter_default_value_expression);
		addProvidedProperties(JRDesignParameter.PROPERTY_EVALUATION_TIME, Messages.common_evaluation_time);
	}

	/**
	 * Show or hide the composite with the nested class type
	 */
	private void setCompVisible(Composite c, boolean visible) {
		c.setVisible(visible);
		((GridData) c.getLayoutData()).exclude = !visible;
		((GridData)rootComposite.getLayoutData()).minimumHeight = visible ? 150 : SWT.DEFAULT;
		//layout the section
		rootComposite.getParent().getParent().layout(true, true);
	}

	/**
	 * Take a qualified class name and return true if it represent a class subtype of Collection, false otherwise
	 */
	private boolean isClassCollection(String className) {
		try {
			return Collection.class.isAssignableFrom(Class.forName(className));
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	/**
	 * When the data is refreshed is checked if the class name is a collection, in this case the controls to define the
	 * nested type are shown, otherwise they are hidden.
	 */
	@Override
	public void refresh() {
		setRefreshing(true);
		APropertyNode element = getElement();
		if (element != null) {
			element.getPropertyDescriptors();
			for (Object key : widgets.keySet()) {
				if (key.equals(JRDesignParameter.PROPERTY_NESTED_TYPE_NAME)) {
					Object value = element.getPropertyValue(JRDesignVariable.PROPERTY_VALUE_CLASS_NAME);
					String type = value != null ? value.toString() : null;
					setCompVisible(cmp, isClassCollection(type));
				} else if (key.equals(JRDesignParameter.PROPERTY_FOR_PROMPTING)) {
					JasperDesign jd = element.getJasperDesign();
					setCompVisible(cmpP,
							jd != null && jd.getMainDesignDataset().getParametersList().contains(element.getValue()));
				}
				widgets.get(key).setData(element, element.getPropertyValue(key));
				if (key.equals(JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION)) {
					// fix the visibilities mask: allows only PARAMETERS
					ExpressionContext expContext = ((WTextExpression) ((SPExpression) widgets.get(key)).getControl())
							.getExpressionContext();
					if (expContext != null) {
						expContext.setVisibilities(EnumSet.of(Visibility.SHOW_PARAMETERS));
					}
				}
			}
		}
		setRefreshing(false);
	}

	/**
	 * Check if it is changed the type, if it is a collection show the additional field, otherwise hide it
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		APropertyNode element = getElement();
		if (element != null && evt.getPropertyName().equals(JRDesignVariable.PROPERTY_VALUE_CLASS_NAME)) {
			Object value = element.getPropertyValue(JRDesignVariable.PROPERTY_VALUE_CLASS_NAME);
			String type = value != null ? value.toString() : null;
			setCompVisible(cmp, isClassCollection(type));
		}
		super.propertyChange(evt);
	}

	@Override
	public boolean hasDynamicContent() {
		return true;
	}
}
