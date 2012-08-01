/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.jface.util.IPropertyChangeListener;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.figures.FigureFactory;
import com.jaspersoft.studio.editor.gef.figures.ReportPageFigure;
import com.jaspersoft.studio.editor.gef.figures.borders.CornerBorder;
import com.jaspersoft.studio.editor.gef.figures.borders.ElementLineBorder;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.FigureSelectionEditPolicy;
import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;

/*
 * The Class FigureEditPart.
 */
public class FigureEditPart extends AJDEditPart implements PropertyChangeListener, IRulerUpdatable {

	protected DrawVisitor drawVisitor;

	public DrawVisitor getDrawVisitor() {
		return drawVisitor;
	}

	private PreferenceListener preferenceListener;

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(DesignerPreferencePage.P_ELEMENT_DESIGN_BORDER_STYLE))
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

	public void setDrawVisitor(DrawVisitor drawVisitor) {
		this.drawVisitor = drawVisitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {
		ANode model = getModel();
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
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new FigureSelectionEditPolicy());
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
		rect.invalidate();
		rect.repaint();
	}

	public void setPrefsBorder(IFigure rect) {
		String pref = Platform.getPreferencesService().getString(JaspersoftStudioPlugin.getUniqueIdentifier(),
				DesignerPreferencePage.P_ELEMENT_DESIGN_BORDER_STYLE, "rectangle", null); //$NON-NLS-1$

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
	protected void setupFigure(IFigure rect) {
		ANode model = getModel();
		rect.setToolTip(new Label(model.getToolTip()));
		if (model instanceof IGraphicElement && model.getValue() != null) {
			Rectangle bounds = ((IGraphicElement) model).getBounds();
			int x = bounds.x + ReportPageFigure.PAGE_BORDER.left;
			int y = bounds.y + ReportPageFigure.PAGE_BORDER.top;
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
		if (rect instanceof ComponentFigure)
			JaspersoftStudioPlugin.getDecoratorManager().setupFigure((ComponentFigure) rect, this);
	}

	@Override
	public ANode getModel() {
		return (ANode) super.getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
		refreshC(getModel());
		refreshVisuals();
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

	public void updateRulers() {
		ANode model = getModel().getParent();
		if (model instanceof IGraphicElement && model.getValue() != null) {
			Rectangle bounds = ((IGraphicElement) model).getBounds();
			int x = bounds.x + ReportPageFigure.PAGE_BORDER.left;
			int y = bounds.y + ReportPageFigure.PAGE_BORDER.top;

			getViewer().setProperty(ReportRuler.PROPERTY_HOFFSET, x);
			getViewer().setProperty(ReportRuler.PROPERTY_VOFFSET, y);
			getViewer().setProperty(ReportRuler.PROPERTY_HEND, bounds.width); //$NON-NLS-1$
			getViewer().setProperty(ReportRuler.PROPERTY_VEND, bounds.height);//$NON-NLS-1$

			getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ORIGIN, new Point(x, y));
		}
	}

}