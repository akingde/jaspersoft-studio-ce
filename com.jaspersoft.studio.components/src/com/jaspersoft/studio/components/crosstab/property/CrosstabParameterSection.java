/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.property;

import java.util.EnumSet;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionContext.Visibility;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabParameter;

/**
 * Crosstab parameter section used to input the expression value for a crosstab parameter
 * 
 * @author Orlandin Marco
 *
 */
public class CrosstabParameterSection extends AbstractSection {
	
	private Composite rootComposite;
	
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent.setLayout(new GridLayout(1, false));
		
		rootComposite = getWidgetFactory().createComposite(parent);
		GridLayout rootLayout = new GridLayout(2,false);
		rootLayout.marginHeight=0;
		rootLayout.marginWidth=0;
		rootComposite.setLayout(rootLayout);
		GridData rootData = new GridData(GridData.FILL_BOTH);
		rootComposite.setLayoutData(rootData);
		createWidget4Property(rootComposite, JRDesignCrosstabParameter.PROPERTY_VALUE_EXPRESSION);
	}

	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignCrosstabParameter.PROPERTY_VALUE_EXPRESSION,  Messages.MCrosstabParameter_valueLabel);
	}
	
	@Override
	public void refresh() {
		super.refresh();
		setRefreshing(true);
		APropertyNode element = getElement();
		if (element != null) {
			String key = JRDesignCrosstabParameter.PROPERTY_VALUE_EXPRESSION;
			// fix the visibilities mask: allows only PARAMETERS
			ExpressionContext expContext = ((WTextExpression) ((SPExpression) widgets.get(key)).getControl()).getExpressionContext();
			if(expContext!=null){
				expContext.setVisibilities(EnumSet.of(Visibility.SHOW_PARAMETERS));
			}
		}
		setRefreshing(false);
	}
}
