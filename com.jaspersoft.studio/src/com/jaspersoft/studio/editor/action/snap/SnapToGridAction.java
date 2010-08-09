package com.jaspersoft.studio.editor.action.snap;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.jface.action.Action;

public class SnapToGridAction extends Action {
	public static String ID = "ID_SNAP_TO_GRID";
	private GraphicalViewer diagramViewer;

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public SnapToGridAction(GraphicalViewer diagramViewer) {
		super("Snap To Grid", AS_CHECK_BOX);
		this.diagramViewer = diagramViewer;
		setToolTipText("Snap To Grid");
		setId(ID);
		setActionDefinitionId(ID);
		setChecked(isChecked());
	}

	/**
	 * @see org.eclipse.jface.action.IAction#isChecked()
	 */
	public boolean isChecked() {
		Boolean val = (Boolean) diagramViewer.getProperty(SnapToGrid.PROPERTY_GRID_ENABLED);
		if (val != null)
			return val.booleanValue();
		return false;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		boolean val = !isChecked();
		diagramViewer.setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, new Boolean(val));
	}
}