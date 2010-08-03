package com.jaspersoft.studio.editor.action.size;

import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.MatchHeightAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

public class MatchSizeAction extends MatchHeightAction {
	public static final String ID = GEFActionConstants.MATCH_HEIGHT + GEFActionConstants.MATCH_WIDTH;
	public MatchSizeAction(IWorkbenchPart part) {
		super(part);
		setText("Match Size");
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/eclipse/match-size.gif"));
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/eclipse/match-size_d.gif"));
		setToolTipText("Match Size");
		setId(ID);
	}

	@Override
	protected double getPreciseWidthDelta(PrecisionRectangle precisePartBounds, PrecisionRectangle precisePrimaryBounds) {
		return precisePrimaryBounds.preciseWidth - precisePartBounds.preciseWidth;
	}
}
