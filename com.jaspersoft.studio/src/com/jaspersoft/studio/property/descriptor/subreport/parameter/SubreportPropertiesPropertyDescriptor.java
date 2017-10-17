/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.subreport.parameter;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPSubreportParametersButton;

public class SubreportPropertiesPropertyDescriptor extends NTextPropertyDescriptor implements IPropertyDescriptorWidget {

	public SubreportPropertiesPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		cellEditor = new SubreportPropertiesCellEditor(parent);
		cellEditor.init(msubreport);
		return cellEditor;
	}

	private MSubreport msubreport;
	private SubreportPropertiesCellEditor cellEditor;

	public void init(MSubreport msubreport) {
		this.msubreport = msubreport;
		if (cellEditor != null)
			cellEditor.init(msubreport);
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet())
			return super.getLabelProvider();
		return new SubreportPropertiesLabelProvider();
	}

	@Override
	public ASPropertyWidget<SubreportPropertiesPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPSubreportParametersButton<SubreportPropertiesPropertyDescriptor>(parent, section, this, getDisplayName());
	}
}
