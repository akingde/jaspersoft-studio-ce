package com.jaspersoft.studio.editor.action.snap;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.action.Action;

public class SnapToGuidesAction extends Action {
	public static final String ID = "PROPERTY_SNAP_TO_GUIDES_ENABLED";
	private GraphicalViewer diagramViewer;

	public SnapToGuidesAction(GraphicalViewer diagramViewer) {
		super("Show Grid", AS_CHECK_BOX);
		this.diagramViewer = diagramViewer;
		setText("Show Grid");
		setToolTipText("Show Grid");
		setId(ID);
		setActionDefinitionId(ID);
		setChecked(isChecked());
	}

	/**
	 * @see org.eclipse.jface.action.IAction#isChecked()
	 */
	public boolean isChecked() {
		Boolean val = (Boolean) this.diagramViewer.getProperty(SnapToGuidesAction.ID);
		if (val != null)
			return val.booleanValue();
		return false;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		this.diagramViewer.setProperty(SnapToGuidesAction.ID, new Boolean(!isChecked()));
	}
}
