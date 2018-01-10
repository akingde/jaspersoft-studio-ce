/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;

import com.jaspersoft.studio.book.editors.figures.BookReportFigure;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;

public class BookReportEditPart extends AbstractGraphicalEditPart {
	
	private BookReportFigure figure = null;
	
	private PropertyChangeListener updatePart = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			//need to refresh when a group is changed
			refresh();
		}
	};
	
	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		((Figure)getFigure()).repaint();
	}
	
	@Override
	public void setModel(Object model) {
		if (getModel() != null){
			MReport bookModel = (MReport)getModel();
			bookModel.getPropertyChangeSupport().removePropertyChangeListener(updatePart);
		}
		super.setModel(model);
		if (getModel() != null){
			MReport bookModel = (MReport)getModel();
			bookModel.getPropertyChangeSupport().addPropertyChangeListener(updatePart);
		}
	}
	
	@Override
	protected IFigure createFigure() {
		if (figure == null) {
			figure =  new BookReportFigure();
		}
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		NonResizableEditPolicy selectionPolicy = new NonResizableEditPolicy(){
			protected void showSelection() {};
		};
		selectionPolicy.setDragAllowed(false);
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, selectionPolicy);
	}
	
	@Override
	protected List<Object> getModelChildren() {
		List<Object> list = new ArrayList<Object>();
		for (INode node : getBookModel().getChildren()) {
			if (node instanceof MReportPartContainer) {
				list.add(node);
			}
		}
		return list;
	}
	
	protected MReport getBookModel(){
		return (MReport)getModel();
	}
	
	@Override
	public void deactivate() {
		if (getModel() != null){
			APropertyNode bookModel = (APropertyNode)getModel();
			bookModel.getPropertyChangeSupport().removePropertyChangeListener(updatePart);
		}
		super.deactivate();
	}
}
