package com.jaspersoft.studio.editor.gef.parts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.preferences.DesignerPreferencePage;

public abstract class PrefFigureEditPart extends FigureEditPart implements IPrefEditPart {
	@Override
	protected IFigure createFigure() {
		figure = super.createFigure();
		setMarginColor();
		return figure;
	}

	@Override
	protected void handlePreferenceChanged(org.eclipse.jface.util.PropertyChangeEvent event) {
		// if (event.getProperty().equals(
		// DesignerPreferencePage.P_SHOW_REPORT_BAND_NAMES)) {
		// setBandNameShowing(getFigure());
		// } else
		if (event.getProperty().equals(DesignerPreferencePage.P_CONTAINER_MARGIN_COLOR)) {
			setMarginColor();
		} else
			super.handlePreferenceChanged(event);
	}

	private Color marginColor;

	@Override
	public Color getMarginColor() {
		return marginColor;
	}

	protected void setMarginColor() {
		if (jConfig == null)
			jConfig = getModel().getJasperConfiguration();
		String mcolor = jConfig.getProperty(DesignerPreferencePage.P_CONTAINER_MARGIN_COLOR, "");
		marginColor = SWTResourceManager.getColor(StringConverter.asRGB(mcolor, IPrefEditPart.DEFAULT_MARGINCOLOR));
		if (figure != null) {
			setupMarginColor();
			refreshVisuals();
		}
	}

	protected void setupMarginColor() {
	}
}
