/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.snap;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class ACheckResourcePrefAction extends AResourcePreferenceAction {

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public ACheckResourcePrefAction(String text, JasperReportsConfiguration jrConfig) {
		super(text, jrConfig, AS_CHECK_BOX);
	}

	/**
	 * @see org.eclipse.jface.action.IAction#isChecked()
	 */
	public boolean isChecked() {
		String p = getProperty();
		return jrConfig.getPropertyBooleanDef(p, false);
	};

	@Override
	protected void doRun() throws Exception {
		getStore().setValue(getProperty(), Boolean.toString(!isChecked()));
	}

	protected abstract String getProperty();
}
