/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout.pin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.editor.gef.parts.AJDEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementEditPolicy;

public class PinEditPart extends AJDEditPart implements PropertyChangeListener, NodeEditPart {
	@Override
	public MPin getModel() {
		return (MPin) super.getModel();
	}

	@Override
	protected IFigure createFigure() {
		IFigure fig = new PinFigure();
		fig.setOpaque(false);
		setupFigure(fig);
		m_anchor = new ChopboxAnchor(fig);
		return fig;
	}

	protected void setupFigure(IFigure rect) {
		MPin mpin = getModel();
		Point p = (Point) mpin.getPropertyValue("");
		rect.setLocation(p);
		rect.setSize(16, 16);
	}

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
		IFigure rect = getFigure();
		if (Display.getCurrent() != null) {
			setupFigure(rect);
			rect.invalidate();
			rect.repaint();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
		refreshVisuals();
	}

	private ChopboxAnchor m_anchor;

	@Override
	protected ConnectionEditPart createConnection(Object model) {
		PinConnectorEditPart connectPart = (PinConnectorEditPart) getRoot().getViewer().getEditPartRegistry().get(model);
		if (connectPart == null) {
			connectPart = new PinConnectorEditPart();
			connectPart.setModel(model);
		}
		return connectPart;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getModelSourceConnections() {
		List<MPinConnection> sourceConnections = new ArrayList<MPinConnection>();
		sourceConnections.add(getModel().getSourceConnections());
		return sourceConnections;
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return m_anchor;
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return m_anchor;
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return null;
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return null;
	}
}
