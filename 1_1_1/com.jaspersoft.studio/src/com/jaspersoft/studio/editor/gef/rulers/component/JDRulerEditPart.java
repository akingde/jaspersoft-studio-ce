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
package com.jaspersoft.studio.editor.gef.rulers.component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.rulers.RulerChangeListener;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.swt.accessibility.AccessibleEvent;

import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;

public class JDRulerEditPart extends AbstractGraphicalEditPart {

	protected GraphicalViewer diagramViewer;
	private AccessibleEditPart accPart;
	private RulerProvider rulerProvider;
	private boolean horizontal;
	private RulerChangeListener listener = new RulerChangeListener.Stub() {

		public void notifyGuideReparented(Object guide) {
			handleGuideReparented(guide);
		}

		public void notifyUnitsChanged(int newUnit) {
			handleUnitsChanged(newUnit);
		}
	};
	private RulerListener rulerLister;

	public JDRulerEditPart(Object model) {
		setModel(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
	 */
	public void activate() {
		rulerLister = new RulerListener();
		((ReportRuler) getRulerProvider().getRuler()).addPropertyChangeListener(rulerLister);
		getRulerProvider().addRulerChangeListener(listener);
		getRulerFigure().setZoomManager(getZoomManager());
		super.activate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		/*
		 * @TODO:Pratik the right way of creating guides and figuring out the target edit part is by installing an edit
		 * policy with container role. talk to randy about how this should work. would isMove() in GuideEditPart's drag
		 * tracker still have to return true all the time?
		 */
		// installEditPolicy(EditPolicy.CONTAINER_ROLE, );
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new RulerSelectionPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		JDRulerFigure ruler = new JDRulerFigure(isHorizontal(), getRulerProvider().getUnit());
		if (ruler.getUnit() == RulerProvider.UNIT_PIXELS)
			ruler.setInterval(100, 10);
		return ruler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
	 */
	public void deactivate() {
		super.deactivate();
		((ReportRuler) getRulerProvider().getRuler()).removePropertyChangeListener(rulerLister);
		getRulerProvider().removeRulerChangeListener(listener);
		rulerProvider = null;
		getRulerFigure().setZoomManager(null);
	}

	protected AccessibleEditPart getAccessibleEditPart() {
		if (accPart == null)
			accPart = new AccessibleGraphicalEditPart() {
				public void getName(AccessibleEvent e) {
					e.result = isHorizontal() ? "Horizontal" : "Vertical"; //$NON-NLS-1$ //$NON-NLS-2$
				}

				public void getDescription(AccessibleEvent e) {
					e.result = "Ruler"; //$NON-NLS-1$
				}
			};
		return accPart;
	}

	/**
	 * Returns the GraphicalViewer associated with the diagram.
	 * 
	 * @return graphical viewer associated with the diagram.
	 */
	protected GraphicalViewer getDiagramViewer() {
		return diagramViewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
	 */
	public DragTracker getDragTracker(Request request) {
		if (request.getType().equals(REQ_SELECTION) && ((SelectionRequest) request).getLastButtonPressed() != 1) {
			return null;
		}
		return new JDRulerDragTracker(this);
	}

	public IFigure getGuideLayer() {
		LayerManager lm = (LayerManager) diagramViewer.getEditPartRegistry().get(LayerManager.ID);
		if (lm != null)
			return lm.getLayer(LayerConstants.GUIDE_LAYER);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	protected List<?> getModelChildren() {
		return getRulerProvider().getGuides();
	}

	protected JDRulerFigure getRulerFigure() {
		return (JDRulerFigure) getFigure();
	}

	public RulerProvider getRulerProvider() {
		return rulerProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPart#getTargetEditPart(org.eclipse.gef.Request)
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request.getType().equals(REQ_MOVE)) {
			return this;
		} else {
			return super.getTargetEditPart(request);
		}
	}

	public ZoomManager getZoomManager() {
		return (ZoomManager) diagramViewer.getProperty(ZoomManager.class.toString());
	}

	public void handleGuideReparented(Object guide) {
		refreshChildren();
		EditPart guidePart = (EditPart) getViewer().getEditPartRegistry().get(guide);
		if (guidePart != null) {
			getViewer().select(guidePart);
		}
	}

	public void handleUnitsChanged(int newUnit) {
		getRulerFigure().setUnit(newUnit);
		if (newUnit == RulerProvider.UNIT_PIXELS)
			getRulerFigure().setInterval(100, 10);
		else
			getRulerFigure().setInterval(0, 0);
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setParent(EditPart parent) {
		super.setParent(parent);
		if (getParent() != null && diagramViewer == null) {
			diagramViewer = (GraphicalViewer) getViewer().getProperty(GraphicalViewer.class.toString());
			RulerProvider hProvider = (RulerProvider) diagramViewer.getProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER);
			if (hProvider != null && hProvider.getRuler() == getModel()) {
				rulerProvider = hProvider;
				horizontal = true;
			} else {
				rulerProvider = (RulerProvider) diagramViewer.getProperty(RulerProvider.PROPERTY_VERTICAL_RULER);
			}
		}
	}

	private final class RulerListener implements PropertyChangeListener {

		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(ReportRuler.PROPERTY_HOFFSET)) {
				((JDRulerFigure) getFigure()).setHoffset((Integer) evt.getNewValue());
			} else if (evt.getPropertyName().equals(ReportRuler.PROPERTY_VOFFSET)) {
				((JDRulerFigure) getFigure()).setVoffset((Integer) evt.getNewValue());
			} else if (evt.getPropertyName().equals(ReportRuler.PROPERTY_HEND)) {
				((JDRulerFigure) getFigure()).setHend((Integer) evt.getNewValue());
			} else if (evt.getPropertyName().equals(ReportRuler.PROPERTY_VEND)) {
				((JDRulerFigure) getFigure()).setVend((Integer) evt.getNewValue());
			}
		}
	}

	public static class RulerSelectionPolicy extends SelectionEditPolicy {
		protected void hideFocus() {
			((JDRulerFigure) getHostFigure()).setDrawFocus(false);
		}

		protected void hideSelection() {
			((JDRulerFigure) getHostFigure()).setDrawFocus(false);
		}

		protected void showFocus() {
			((JDRulerFigure) getHostFigure()).setDrawFocus(true);
		}

		protected void showSelection() {
			((JDRulerFigure) getHostFigure()).setDrawFocus(true);
		}
	}

}
