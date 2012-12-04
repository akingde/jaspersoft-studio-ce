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
		return jrConfig.getPropertyBoolean(getProperty());
//		return store.getBoolean(getProperty());
	};

	@Override
	protected void doRun() throws Exception {
		store.setValue(getProperty(), !isChecked());
	}

	protected abstract String getProperty();
}