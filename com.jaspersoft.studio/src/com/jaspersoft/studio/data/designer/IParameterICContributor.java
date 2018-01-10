/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.designer;

import org.eclipse.swt.widgets.Composite;

import net.sf.jasperreports.engine.design.JRDesignParameter;

public interface IParameterICContributor {
	public void createUI(Composite parent, JRDesignParameter prm, AQueryDesigner designer);

	public void refresh(JRDesignParameter prm);
}
