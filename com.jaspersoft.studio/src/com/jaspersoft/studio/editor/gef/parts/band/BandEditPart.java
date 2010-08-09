/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts.band;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;

import com.jaspersoft.studio.editor.gef.figures.BandFigure;
import com.jaspersoft.studio.editor.gef.figures.PageFigure;
import com.jaspersoft.studio.editor.gef.parts.AJDEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.BandContainerEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.BandMoveEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * BandEditPart creates the figure for the band. The figure is actually just the bottom border of the band. This allows
 * to drag this border to resize the band. The PageEditPart sets a specific contraint for the BandEditPart elements in
 * order to make them move only vertically. The BandMoveEditPolicy is responsable for the feedback when the band is
 * dragged.
 * 
 * @author Chicu Veaceslav, Giulio Toffoli
 * 
 */
public class BandEditPart extends AJDEditPart implements PropertyChangeListener {

	/**
	 * Gets the band.
	 * 
	 * @return the band
	 */
	public JRDesignBand getBand() {
		return (JRDesignBand) ((MBand) getModel()).getValue();
	}

	/**
	 * Gets the jasper design.
	 * 
	 * @return the jasper design
	 */
	public JasperDesign getJasperDesign() {
		try {
			return ((MBand) getModel()).getJasperDesign();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		BandFigure rect = new BandFigure();
		rect.setForegroundColor(ColorConstants.blue);
		rect.setToolTip(new Label(((MBand) getModel()).getToolTip()));
		setupBandFigure(rect);
		return rect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new BandMoveEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new BandContainerEditPolicy());
	}

	/**
	 * Class name.
	 * 
	 * @param cls
	 *          the cls
	 * @return the string
	 */
	public static String className(Object cls) {
		String className = cls.getClass().getName();
		return className.substring(className.lastIndexOf('.') + 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	public void refreshVisuals() {
		IFigure rect = getFigure();

		setupBandFigure(rect);
		rect.repaint();
	}

	private void setupBandFigure(IFigure rect) {
		JRDesignBand jrBand = getBand();
		Rectangle bounds = ((MBand) getModel()).getBounds();
		int width = getJasperDesign().getPageWidth() + 1;
		int height = jrBand != null ? jrBand.getHeight() : 0;
		rect.setBounds(new Rectangle(PageFigure.PAGE_BORDER.left, bounds.y + PageFigure.PAGE_BORDER.top, width, height));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}

}
