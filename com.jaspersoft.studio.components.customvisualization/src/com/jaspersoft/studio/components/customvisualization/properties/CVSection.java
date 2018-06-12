/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPEvaluationTime;

import net.sf.jasperreports.customvisualization.design.CVDesignComponent;

/**
 * Properties section for the main details of the Custom Visualization Component
 * element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class CVSection extends AbstractSection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout(2, false);
		layout.horizontalSpacing = 0;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.verticalSpacing = 0;
		container.setLayout(layout);

		IPropertyDescriptor pd = getPropertyDesriptor(CVDesignComponent.PROPERTY_EVALUATION_TIME);
		IPropertyDescriptor gpd = getPropertyDesriptor(CVDesignComponent.PROPERTY_EVALUATION_GROUP);
		getWidgetFactory().createCLabel(container, pd.getDisplayName());
		SPEvaluationTime eval = new SPEvaluationTime(container, this, pd, gpd);
		eval.getControl().setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false));
		widgets.put(pd.getId(), eval);

		ASPropertyWidget<?> control = createWidget4Property(container, CVDesignComponent.PROPERTY_ON_ERROR_TYPE);
		control.getControl().setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false));
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(CVDesignComponent.PROPERTY_EVALUATION_TIME, Messages.CVSection_EvalTime);
		addProvidedProperties(CVDesignComponent.PROPERTY_EVALUATION_GROUP, Messages.CVSection_EvalGroup);
		addProvidedProperties(CVDesignComponent.PROPERTY_PROCESSING_CLASS, Messages.CVSection_ProcessingClass);
		addProvidedProperties(CVDesignComponent.PROPERTY_ON_ERROR_TYPE, Messages.CVSection_OnErrorType);
	}
}
