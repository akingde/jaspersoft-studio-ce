package com.jaspersoft.studio.editor.action.snap;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.Action;

public class ShowGridAction extends Action {

	private GraphicalViewer diagramViewer;

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public ShowGridAction(GraphicalViewer diagramViewer) {
		super("Show grid", AS_CHECK_BOX);
		this.diagramViewer = diagramViewer;
		setToolTipText("Show grid");
		setId(GEFActionConstants.TOGGLE_GRID_VISIBILITY);
		setActionDefinitionId(GEFActionConstants.TOGGLE_GRID_VISIBILITY);
		setChecked(isChecked());
	}

	/**
	 * @see org.eclipse.jface.action.IAction#isChecked()
	 */
	public boolean isChecked() {
		// Boolean val = (Boolean)diagramViewer.getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
		Boolean val = (Boolean) diagramViewer.getProperty(SnapToGrid.PROPERTY_GRID_VISIBLE);
		if (val != null)
			return val.booleanValue();
		return false;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		boolean val = !isChecked();
		diagramViewer.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE, new Boolean(val));
		// diagramViewer.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, new Boolean(val));
	}

}
