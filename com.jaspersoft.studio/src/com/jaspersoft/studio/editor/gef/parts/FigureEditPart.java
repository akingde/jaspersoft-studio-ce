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
package com.jaspersoft.studio.editor.gef.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.util.IPropertyChangeListener;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.figures.FigureFactory;
import com.jaspersoft.studio.editor.gef.figures.PageFigure;
import com.jaspersoft.studio.editor.gef.figures.borders.CornerBorder;
import com.jaspersoft.studio.editor.gef.figures.borders.ElementLineBorder;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.preferences.PreferenceConstants;

/**
 * The Class FigureEditPart.
 */
public class FigureEditPart extends AJDEditPart implements PropertyChangeListener {

	private DrawVisitor drawVisitor;
	private PreferenceListener preferenceListener;

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(PreferenceConstants.P_ELEMENT_DESIGN_BORDER_STYLE))
				setPrefsBorder(getFigure());
		}
	}

	@Override
	public void activate() {
		super.activate();
		preferenceListener = new PreferenceListener();
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(preferenceListener);
	}

	@Override
	public void deactivate() {
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().removePropertyChangeListener(preferenceListener);
		super.deactivate();
	}

	@Override
	public void setModel(Object model) {
		super.setModel(model);
		if (model != null && model instanceof ANode)
			drawVisitor = new DrawVisitor(((ANode) model).getJasperDesign(), null);
		else
			drawVisitor = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		ANode model = (ANode) getModel();
		IFigure rect = FigureFactory.createFigure(model);
		setPrefsBorder(rect);
		setupFigure(rect);

		return rect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#refreshVisuals()
	 */
	@Override
	public void refreshVisuals() {
		Shape rect = (Shape) getFigure();
		setupFigure(rect);
		rect.repaint();
	}

	private void setPrefsBorder(IFigure rect) {
		String pref = Platform.getPreferencesService().getString(JaspersoftStudioPlugin.getUniqueIdentifier(),
				PreferenceConstants.P_ELEMENT_DESIGN_BORDER_STYLE, "rectangle", null); //$NON-NLS-1$

		if (pref.equals("rectangle")) //$NON-NLS-1$
			rect.setBorder(new ElementLineBorder(ColorConstants.black));
		else
			rect.setBorder(new CornerBorder(ColorConstants.black, 5));
	}

	/**
	 * Sets the up figure.
	 * 
	 * @param rect
	 *          the new up figure
	 */
	private void setupFigure(IFigure rect) {
		ANode model = (ANode) getModel();
		if (model instanceof IGraphicElement && model.getValue() != null) {
			Rectangle bounds = ((IGraphicElement) model).getBounds();
			int x = bounds.x + PageFigure.PAGE_BORDER.left;
			int y = bounds.y + PageFigure.PAGE_BORDER.top;
			if (model.getValue() instanceof JRDesignElement) {
				JRDesignElement jrElement = (JRDesignElement) model.getValue();
				if (rect instanceof ComponentFigure && drawVisitor != null) {
					ComponentFigure f = (ComponentFigure) rect;
					f.setLocation(new Point(x, y));
					f.setJRElement(jrElement, drawVisitor);
				} else
					rect.setBounds(new Rectangle(x, y, jrElement.getWidth(), jrElement.getHeight()));
			} else {
				rect.setBounds(new Rectangle(x, y, bounds.width, bounds.height));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
		refreshC((ANode) getModel());
	}

	/**
	 * Refresh c.
	 * 
	 * @param n
	 *          the n
	 */
	private void refreshC(ANode n) {
		if (n.getChildren() != null)
			for (INode node : n.getChildren()) {
				EditPart ep = (EditPart) getViewer().getEditPartRegistry().get(node);
				if (ep instanceof FigureEditPart)
					((FigureEditPart) ep).refreshVisuals();
				refreshC((ANode) node);
			}
	}

}
