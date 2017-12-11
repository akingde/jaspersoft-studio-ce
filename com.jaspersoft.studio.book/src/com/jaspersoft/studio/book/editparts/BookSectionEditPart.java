/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.book.ReportThumbnailsManager;
import com.jaspersoft.studio.book.dnd.PageEditPartTracker;
import com.jaspersoft.studio.book.editors.figures.BookPagesFigure;
import com.jaspersoft.studio.book.editors.figures.BookSectionFigure;
import com.jaspersoft.studio.book.model.MGroupReportPartContainer;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.book.model.commands.CreatePartAfterCommand;
import com.jaspersoft.studio.book.model.commands.CreatePartCommand;
import com.jaspersoft.studio.book.model.commands.RemoveChildrenCommand;
import com.jaspersoft.studio.book.model.commands.RemoveSectionCommand;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;

public class BookSectionEditPart extends AbstractGraphicalEditPart {
	
	private BookSectionFigure figure = null;
	
	private PageEditPartTracker dragTracker;
	
	private PropertyChangeListener updatePart = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			figure.repaint();
			refresh();
		}
	};
	
	public BookSectionEditPart(){
		dragTracker = new PageEditPartTracker(this);
	}
	
	@Override
	public void setModel(Object model) {
		if (getModel() != null){
			MReportPartContainer bookModel = (MReportPartContainer)getModel();
			bookModel.getPropertyChangeSupport().removePropertyChangeListener(updatePart);
		}
		super.setModel(model);
		if (getModel() != null){
			MReportPartContainer bookModel = (MReportPartContainer)getModel();
			bookModel.getPropertyChangeSupport().addPropertyChangeListener(updatePart);
		}
	}
	
	@Override
	protected IFigure createFigure() {
		if (figure == null){
			figure = new BookSectionFigure(this);
		}
		return figure;
	}
	
	

	@Override
	protected void createEditPolicies() {
		
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new OrderedLayoutEditPolicy() {
			
			@Override
			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
			
			@Override
			protected EditPart getInsertionReference(Request request) {
				return null;
			}
			
			@Override
			protected Command createMoveChildCommand(EditPart child, EditPart after) {
				if (dragTracker.getContainer() != null){
					if (dragTracker.getContainer().getChildren().isEmpty()){
						CompoundCommand cc = new CompoundCommand();
						MReportPartContainer container = dragTracker.getContainer().getBookModel();
						
						MReportPart partToCreate = (MReportPart) child.getModel();
						MReportPartContainer sourceContainer = (MReportPartContainer)child.getParent().getModel();
						MReportPart movedPart = (MReportPart)child.getModel();
						
						// Let's keep the original Image, we want to use it for the new figure...
						Image partImage = ((BookPagesFigure)((BookPagesEditPart)child).getFigure()).getThubmnailImage();
						if (partImage != null)
						{
							ReportThumbnailsManager.pushElementImage(partToCreate.getValue().getUUID().toString(), partImage);
						}
						
						RemoveChildrenCommand removeCommand = new RemoveChildrenCommand(sourceContainer, movedPart);
						cc.add(removeCommand);
						
						CreatePartCommand createCommand = new CreatePartCommand(container, partToCreate.getValue());
						cc.add(createCommand);
						return cc;
					} else {
						
						MReportPartContainer sourceContainer = (MReportPartContainer)child.getParent().getModel();
						MReportPartContainer targetContainer = dragTracker.getContainer().getBookModel();
						
						MReportPart movedPart = (MReportPart)child.getModel();
						MReportPart afterElement = dragTracker.getAfterPart() != null ? (MReportPart)dragTracker.getAfterPart().getModel() : null;
						
						CompoundCommand cc = new CompoundCommand();
						
						// Let's keep the original Image, we want to use it for the new figure...
						Image partImage = ((BookPagesFigure)((BookPagesEditPart)child).getFigure()).getThubmnailImage();
						if (partImage != null)
						{
							ReportThumbnailsManager.pushElementImage(movedPart.getValue().getUUID().toString(), partImage);
						}
						
						
						RemoveChildrenCommand removeCommand = new RemoveChildrenCommand(sourceContainer, movedPart);
						cc.add(removeCommand);
						
						CreatePartAfterCommand createCommand = new CreatePartAfterCommand(targetContainer, movedPart.getValue(), afterElement);
						cc.add(createCommand);
						
						
						
						return cc;
					}
				}
				return null;
			}
			
			@Override
			protected Command createAddCommand(EditPart child, EditPart after) {
				return createMoveChildCommand(child, after);
			}
			
		});
		NonResizableEditPolicy selectionPolicy = new NonResizableEditPolicy(){
			protected void showSelection() {}

			@Override
			public Command getCommand(Request request) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
		selectionPolicy.setDragAllowed(false);
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, selectionPolicy);
		installEditPolicy(EditPolicy.COMPONENT_ROLE,new ComponentEditPolicy() {
			@Override
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				if (getModel() instanceof MGroupReportPartContainer)
					return new RemoveSectionCommand((MGroupReportPartContainer)getBookModel());
				return UnexecutableCommand.INSTANCE;
			}
		});

	}

	@Override
	protected List<Object> getModelChildren() {
		List<Object> list = new ArrayList<Object>();
		for (INode node : getBookModel().getChildren()) {
			if (node instanceof MReportPart) {
				list.add(node);
			}
		}
		return list;
	}
	
	@Override
	public DragTracker getDragTracker(Request request) {
		return dragTracker;
	}
	
	private Point getLocationFromRequest(Request request) {
		return ((DropRequest)request).getLocation();
	}
	
	
	protected Integer getFeedbackIndexFor(Request request) {
		List<?> children = getChildren();
		if (children.isEmpty())
			return -1;
			
		Transposer transposer = new Transposer();
		Point pt = transposer.t(getLocationFromRequest(request));
		int multiplier = 1;
		while (multiplier < 10) {
			Point ptLeft = new PrecisionPoint(pt.x - (5 * multiplier), pt.y);
			Point ptRight = new PrecisionPoint(pt.x + (5 * multiplier), pt.y);

			EditPart itemLeft = getViewer().findObjectAt(ptLeft);

			if (itemLeft != null) {
				return getChildren().indexOf(itemLeft);
			}

			EditPart itemRight = getViewer().findObjectAt(ptRight);
			
			if (itemRight != null) {
				int rightItemIndex = getChildren().indexOf(itemRight);
				if (rightItemIndex == 0) return -1;
				else return rightItemIndex-1;
			}
			multiplier++;
		}
		return null;
	}
	

	public void showTargetFeedback(EditPart afterPart) {
		if (!figure.hasFeedback() || figure.afterPart() != afterPart){
			figure.drawFeedback(afterPart);
			figure.erase();
		}
	}
	
	public void eraseTargetFeedback() {
		if (figure.hasFeedback()) {
			figure.clearFeedback();
			figure.erase();
		}
	}
	
	public MReportPartContainer getBookModel(){
		return (MReportPartContainer)getModel();
	}
	
	public boolean isLastPart(EditPart part){
		if (getChildren().isEmpty()) return false;
		else return (getChildren().get(getChildren().size()-1) == part);
	}
	
	public EditPart getFollowingPart(EditPart part){
		int index = getChildren().indexOf(part);
		if (index > -1 && index < (getChildren().size()-1)){
			return (EditPart)getChildren().get(index+1);
		}
		return null;
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
