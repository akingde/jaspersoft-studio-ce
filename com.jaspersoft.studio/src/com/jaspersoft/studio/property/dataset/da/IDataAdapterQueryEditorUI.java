/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.da;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.design.JRDesignDataset;

public interface IDataAdapterQueryEditorUI {
	public boolean isForDataAdapter(DataAdapter da);

	public Composite create(Composite parent, DataAdapter da, JRDesignDataset dataset,
			JasperReportsConfiguration jConfig);
}
