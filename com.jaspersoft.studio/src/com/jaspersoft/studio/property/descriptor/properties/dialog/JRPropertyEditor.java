/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRPropertiesMap;

public class JRPropertyEditor extends Wizard {
	private JRPropertiesMap value;
	private JRPropertyPage page0;

	public JRPropertiesMap getValue() {
		if (page0 != null)
			return page0.getValue();
		return value;
	}

	public void setValue(JRPropertiesMap value) {
		if (page0 != null)
			page0.setValue(value);
		this.value = value;
	}

	private JasperReportsConfiguration jConfig;
	private Object jrElement;

	public JRPropertyEditor(JasperReportsConfiguration jConfig, Object jrElement) {
		super();
		setWindowTitle(Messages.common_properties);
		setNeedsProgressMonitor(false);
		this.jConfig = jConfig;
		this.jrElement = jrElement;
	}

	@Override
	public void addPages() {
		page0 = new JRPropertyPage("jrproperties", jConfig, jrElement); //$NON-NLS-1$
		page0.setValue(value);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
