package com.jaspersoft.studio.book.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.book.ReportThumbnailsManager;
import com.jaspersoft.studio.book.dnd.PageEditPartTracker;
import com.jaspersoft.studio.book.editors.figures.BookPagesFigure;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.book.model.commands.CreatePartAfterCommand;
import com.jaspersoft.studio.book.model.commands.RemoveChildrenCommand;
import com.jaspersoft.studio.model.APropertyNode;

public class BookPagesEditPart extends AbstractGraphicalEditPart {
	
	private BookPagesFigure figure = null;
	private PageEditPartTracker dragTracker;
	
	/**
	 * The figure shown when the user drag a report part to move it to a new
	 * book section.
	 * It is created by the showSourceFeedback method, but returned by the
	 * NonResizableEditPolicy installed on the container part.
	 */
	private ImageFigure  sourceFeedbackFigure = null;
	
	private PropertyChangeListener updatePart = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent arg0) {
			figure.updateText();
			refresh();
		}
	};
	
	
	public BookPagesEditPart(){
		dragTracker = new PageEditPartTracker(this);
	}
	
	
	@Override
	public void setModel(Object model) {
		if (getModel() != null){
			MReportPart bookModel = (MReportPart)getModel();
			bookModel.getPropertyChangeSupport().removePropertyChangeListener(updatePart);
		}
		super.setModel(model);
		if (getModel() != null){
			MReportPart bookModel = (MReportPart)getModel();
			bookModel.getPropertyChangeSupport().addPropertyChangeListener(updatePart);
		}
	}
	
	
	@Override
	protected IFigure createFigure() {
		if (figure == null){
			
			Image cachedFigureSwtImage = null;
			if (getModel() != null && ((MReportPart)getModel()).getValue() != null)
			{
				MReportPart bookModel = (MReportPart)getModel();
				cachedFigureSwtImage = ReportThumbnailsManager.popElementImage( bookModel.getValue().getUUID().toString());
			}
			figure = new BookPagesFigure((MReportPart)getModel(), cachedFigureSwtImage);
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
					CompoundCommand cc = new CompoundCommand();
					MReportPartContainer sourceContainer = (MReportPartContainer)child.getParent().getModel();
					MReportPartContainer targetContainer = dragTracker.getContainer().getBookModel();
					MReportPart movedElement = (MReportPart)child.getModel();
					MReportPart afterElement = dragTracker.getAfterPart() != null ? (MReportPart)dragTracker.getAfterPart().getModel() : null;
					
					// Let's keep the original Image, we want to use it for the new figure...
					Image partImage = ((BookPagesFigure)((BookPagesEditPart)child).getFigure()).getThubmnailImage();
					if (partImage != null)
					{
						ReportThumbnailsManager.pushElementImage(movedElement.getValue().getUUID().toString(), partImage);
					}
					
					RemoveChildrenCommand removeCommand = new RemoveChildrenCommand(sourceContainer, movedElement);
					cc.add(removeCommand);
					CreatePartAfterCommand createCommmand = new CreatePartAfterCommand(targetContainer, movedElement.getValue(), afterElement);
					cc.add(createCommmand);
					return cc;
				}
				return null;
				
			}
			
			@Override
			protected Command createAddCommand(EditPart child, EditPart after) {
				return createMoveChildCommand(child, after);
			}
			
		});
		NonResizableEditPolicy selectionPolicy = new NonResizableEditPolicy() {
		
			@Override
			protected IFigure createDragSourceFeedbackFigure() {
			
				if (sourceFeedbackFigure == null)
				{
					sourceFeedbackFigure = new ImageFigure( ((BookPagesFigure)getFigure()).getThubmnailImage());
				}
				// The figure is created in the showSourceFeedback
				addFeedback(sourceFeedbackFigure);
				return sourceFeedbackFigure;
			};
			
			
			/**
			 * We disable firing of commands from this policy which is used only
			 * to show the feedback...
			 */
			@Override
			public Command getCommand(Request request) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		
		selectionPolicy.setDragAllowed(true);
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, selectionPolicy);
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {
			@Override
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				return new RemoveChildrenCommand((MReportPartContainer) ((MReportPart) getHost().getModel()).getParent(), (MReportPart) getHost().getModel());
			}
		});
		
	}
	
	@Override
	public Command getCommand(Request request) {
		if (getParent() == null) return null;
		else return super.getCommand(request);
	}
	
	@Override
	public DragTracker getDragTracker(Request request) {
		return dragTracker;
	}
	
	@Override
	public void showTargetFeedback(Request request) {
		//If the edit part was removed during the drag it's parent will be null
		if (getParent() != null) getParent().showTargetFeedback(request);
	}
	
	@Override
	public void eraseTargetFeedback(Request request) {
		//it the part was removed during the drag it's parent will be null
		if (getParent() != null) getParent().eraseTargetFeedback(request);
	}
	
	@Override
	public void deactivate() {
		if (figure != null)
		{
			figure.dispose();
		}
		if (getModel() != null){
			APropertyNode bookModel = (APropertyNode)getModel();
			bookModel.getPropertyChangeSupport().removePropertyChangeListener(updatePart);
		}
		super.deactivate();
	}
}