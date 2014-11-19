package com.jaspersoft.studio.book.editparts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.OrderedLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.book.ReportThumbnailsManager;
import com.jaspersoft.studio.book.dnd.PageEditPartTracker;
import com.jaspersoft.studio.book.editors.figures.BookPagesFigure;
import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.book.model.commands.CreatePartAfterCommand;
import com.jaspersoft.studio.book.model.commands.RemoveChildrenCommand;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

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
	
	/**
	 * Called on double click on the edit part. It try to resolve the expression
	 * of the page part and if the resource reference is found it is opened inside
	 * the editor
	 */
	@Override
	public void performRequest(Request req) {
	    if(req.getType() == RequestConstants.REQ_OPEN) {
	    	MReportPart part = (MReportPart)getModel();
	    	JRExpression expression = (JRExpression)part.getPropertyValue(MReportPart.COMPONENT_EXPRESSION);
	    	JasperReportsConfiguration jConfig = part.getJasperConfiguration();
	    	String path = ExpressionUtil.cachedExpressionEvaluationString(expression, jConfig);
			if (path == null) return;
	    	File file = getResource(path,jConfig);
			if (file != null && file.exists() && file.isFile()) {
				IFileStore fileStore = EFS.getLocalFileSystem().getStore(file.toURI());
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditorOnFileStore(page, fileStore);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			} else {
				MessageDialog.openError(UIUtils.getShell(), Messages.BookPagesEditPart_errorTitle, Messages.BookPagesEditPart_errorDescription);			
			}	
	    }
	}
	
	/**
	 * Given a report (jasper or jrxml), the file is loaded if exists.
	 * 
	 * @param lovation - A file location.
	 * @param context - Can be null, but in this case don't expect images to be resolved...or properly previewed.
	 * @return the file element of the resource
	 *
	 */
	private File getResource(String location, JasperReportsConfiguration context) {
		
		File f = ReportThumbnailsManager.findFile(location, context);
		
		if (location.toLowerCase().endsWith(".jasper")) //$NON-NLS-1$
		{
			// check for a jrxml...
			 location = location.substring(0, location.length() - ".jasper".length()) + ".jrxml"; //$NON-NLS-1$ //$NON-NLS-2$
			 File jrxmFile = ReportThumbnailsManager.findFile(location, context);
			 if (jrxmFile != null && jrxmFile.exists()) return f = jrxmFile;
		}
		
		return f;
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