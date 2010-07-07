/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts.band;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

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

// TODO: Auto-generated Javadoc
/**
 * BandEditPart creates the figure for the band. The figure is actually just the
 * bottom border of the band. This allows to drag this border to resize the
 * band. The PageEditPart sets a specific contraint for the BandEditPart
 * elements in order to make them move only vertically. The BandMoveEditPolicy
 * is responsable for the feedback when the band is dragged.
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

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		/*
		 * RectangleFigure rect = new RectangleFigure() { private Font f = new
		 * Font(null, "Sans Serif", 14, SWT.BOLD);
		 * 
		 * @Override public void paintFigure(Graphics graphics) {
		 * super.paintFigure(graphics); Rectangle r = getBounds();
		 * graphics.setFont(f); graphics.setForegroundColor(ColorConstants.gray);
		 * 
		 * String bandTitle = ModelUtils.nameOf(getBand().getOrigin()); Dimension d
		 * = FigureUtilities.getStringExtents(bandTitle, f); int x = r.x + r.width /
		 * 2 - d.width / 2; int y = r.y + r.height / 2 - d.height / 2;
		 * graphics.drawText(bandTitle, x, y); } }; rect.setLayoutManager(new
		 * XYLayout()); rect.setBackgroundColor(ColorConstants.lightGray);
		 * rect.setForegroundColor(ColorConstants.red); rect.setOpaque(false);
		 * rect.setAlpha(150);
		 * 
		 * rect.setSize(1000, getBand().getHeight());
		 * 
		 * return rect;
		 */

		BandFigure rect = new BandFigure();

		rect.setForegroundColor(ColorConstants.blue);

		JasperDesign jrDesign = getJasperDesign();
		JRDesignBand jrBand = getBand();
		if (jrBand != null && jrBand.getOrigin() != null && jrBand.getOrigin().getBandTypeValue() != null)
			rect.setToolTip(new Label(" " + jrBand.getOrigin().getBandTypeValue().getName() + " "));
		rect.setBounds(new Rectangle(10, 10 + ModelUtils.getBandLocation(jrBand, jrDesign), jrDesign.getPageWidth() + 1,
				jrBand.getHeight()));

		// rect.setBounds(new Rectangle(10, 10 +
		// ModelUtils.getBandLocation(getBand(), getJasperDesign())
		// + getBand().getHeight(), getJasperDesign().getPageWidth() + 1, 2));

		return rect;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new BandMoveEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new BandContainerEditPolicy());

	}

	/**
	 * Prints the edit policy iterator.
	 * 
	 * @param req
	 *          the req
	 * @param ep2
	 *          the ep2
	 */
	public void printEditPolicyIterator(Request req, EditPolicy ep2) {
		req.getType();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#understandsRequest(org.eclipse.gef.Request)
	 */
	public boolean understandsRequest(Request req) {
		EditPolicyIterator iter = getEditPolicyIterator();
		while (iter.hasNext()) {
			EditPolicy ep = iter.next();
			if (ep.understandsRequest(req)) {
				System.out.println(className(this) + "/" + className(ep) + " understands " + className(req) + "/"
						+ req.getType());
				return true;
			}
		}
		System.out.println(className(this) + " has no policy for " + className(req) + "/" + req.getType());
		return false;
	}

	/*
	   *
	   */

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

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List<Object> getModelChildren() {
		List<Object> list = new ArrayList<Object>();
		// for (INode nod : ((MBand) getModel()).getChildren()) {
		// if (nod instanceof IGraphicElement)
		// list.add(nod);
		// }
		return list;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	public void refreshVisuals() {
		IFigure rect = getFigure();

		JRDesignBand jrBand = getBand();
		Rectangle bounds = ((MBand) getModel()).getBounds();

		int width = getJasperDesign().getPageWidth() + 1;
		int height = jrBand != null ? jrBand.getHeight() : 0;
		rect.setBounds(new Rectangle(PageFigure.PAGE_BORDER.left, bounds.y + PageFigure.PAGE_BORDER.top, width, height));
		rect.repaint();
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}

}
