/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.customadapters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.customadapters.ui.AdapterWidgetsDescriptor;
import com.jaspersoft.studio.data.customadapters.ui.DataAdapterPanelManager;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.manager.panel.IPanelManager;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.JasperReportsContext;

/**
 * Composite that will build the UI of the configurable data adapter, basing it on 
 * the Configuration file provided
 */
public class ConfigurableDataAdapterComposite extends ADataAdapterComposite {
	
	/**
	 * The configuration definition of the data adapter, which contains the widgets to create
	 */
	private AdapterWidgetsDescriptor descriptor;
	
	/**
	 * The panel manager used to build the widgets
	 */
	private IPanelManager currentPanelManager;
	
	/**
	 * The property editor used to read and write the properties from and to the data adapter object
	 */
	private DataAdapterPropertyEditor dataAdapterEditor = new DataAdapterPropertyEditor();

	/**
	 * Build the composite
	 * 
	 * @param parent parent of the composite
	 * @param style style of this composite
	 * @param jrContext a not null {@link JasperReportsConfiguration},
	 * @param descriptor The configuration definition of the data adapter, which contains the widgets to create, must be not null
	 */
	public ConfigurableDataAdapterComposite(Composite parent, int style, JasperReportsContext jrContext, AdapterWidgetsDescriptor descriptor) {
		super(parent, style, jrContext);
		
		this.descriptor = descriptor;
		setLayout(new GridLayout(1, false));
		
		ScrolledComposite scrolledContainer = new ScrolledComposite(this, SWT.V_SCROLL);
		scrolledContainer.setExpandHorizontal(true);
		scrolledContainer.setExpandVertical(true);
		scrolledContainer.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite dynamicParent = new Composite(scrolledContainer, SWT.NONE);
		scrolledContainer.setContent(dynamicParent);
		
		RGB background = parent.getBackground() != null ? parent.getBackground().getRGB() : null;
		if (background != null) {
			RGB rgb = parent.getBackground().getRGB();
			scrolledContainer.setBackground(ResourceManager.getColor(rgb));
			dynamicParent.setBackground(ResourceManager.getColor(rgb));
		}
			
		//Dispose the old panel manager if present
		if(currentPanelManager != null){
			currentPanelManager.disposeWidgets();
			currentPanelManager = null;
		}	
		
		//read the panel manager from the file and if it is not available use a default one.
		currentPanelManager = descriptor.getPanelManager(dynamicParent);
		if (currentPanelManager == null) {
			currentPanelManager = new DataAdapterPanelManager(dynamicParent, background);
		}
		
		//Create the widgets trough the panel manager
		for(WidgetPropertyDescriptor p : descriptor.getPlainWidgets()) {
			ItemPropertyDescription<?> pDescriptor = WidgetFactory.createItemPropertyDescriptor(descriptor, p, JasperReportsConfiguration.getDefaultInstance());
			currentPanelManager.createWidget(p, pDescriptor, dataAdapterEditor, ExpressionContext.getGlobalContext());
		}
		
		int compositeHeight = dynamicParent.computeSize(500, SWT.DEFAULT).y;
		scrolledContainer.setMinHeight(compositeHeight);
		dynamicParent.layout(true, true);
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		//Update the widgets value
		dataAdapterEditor.setDataAdapter(dataAdapter, pchangesuport);
		if (currentPanelManager != null) currentPanelManager.updateWidgets();
	}

	@Override
	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new ConfigurableDataAdapterDescriptor(descriptor);
		return dataAdapterDesc;
	}


}
