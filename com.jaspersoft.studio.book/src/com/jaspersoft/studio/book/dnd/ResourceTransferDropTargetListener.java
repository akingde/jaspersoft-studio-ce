package com.jaspersoft.studio.book.dnd;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.part.ResourceTransfer;

import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.book.model.commands.CreatePartAfterCommand;


public class ResourceTransferDropTargetListener extends AbstractTransferDropTargetListener {

	private PageEditPartTracker partTracker;
	
	public ResourceTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer, ResourceTransfer.getInstance());
	}

	@Override
	protected void updateTargetRequest() {
	}
	
	
	@Override
	protected void eraseTargetFeedback() {
		if (partTracker != null) {
			partTracker.eraseTargetFeedback();
			partTracker.setTargetEditPart(null);
		}
		partTracker = null;
	}
	
	@Override
	protected EditPart getTargetEditPart() {
		partTracker = null;
		EditPart hoveredEditPart = super.getTargetEditPart();
		if (hoveredEditPart == null) return null;
		if (hoveredEditPart.getDragTracker(getTargetRequest()) instanceof PageEditPartTracker){
			partTracker = (PageEditPartTracker)hoveredEditPart.getDragTracker(getTargetRequest());
			partTracker.setViewer(getViewer());
			partTracker.updateDropLocation(PageEditPartTracker.getDropLocation(hoveredEditPart, getDropLocation()));
			return partTracker.getContainer();
		}
		return hoveredEditPart;
	}
	
	
	
	@Override
	protected Command getCommand() {
		DropTargetEvent event = getCurrentEvent();
		for(TransferData eventData : event.dataTypes){
			if (ResourceTransfer.getInstance().isSupportedType(eventData)){
				return parseExternalResources((IResource[])event.data);
			}
		}
		return super.getCommand();
	}
	
	
	@Override
	protected Request createTargetRequest() {
		ChangeBoundsRequest request =  new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
		request.setLocation(getDropLocation());
		return request;
	}
	
	/**
	 * Parse a list of external resources and if they are report then
	 * they are added inside the gallery in the specified drop location
	 * 
	 * @param input the resources to add
	 * @param drop the gallery and it's location where the valid resources should be placed
	 */
	private Command parseExternalResources(IResource[] input){
		if (partTracker == null || partTracker.getContainer() == null) return null;
		List<String> readElements = new ArrayList<String>(); 
		//Search the jrxml files
		for(IResource resource : input){
			if (resource instanceof IFile){
				IFile file = (IFile)resource;
				if (file.getName().toLowerCase().endsWith(".jrxml")){
					readElements.add("\""+file.getRawLocation().makeAbsolute().toPortableString()+"\"");
				}
			}
		}
		MReportPartContainer modelContainer = partTracker.getContainer().getBookModel();
		//otherwise add the elments to the specified location
		MReportPart afterPart = partTracker.getAfterPart() != null ? (MReportPart)partTracker.getAfterPart().getModel() : null;
		CompoundCommand cc = new CompoundCommand();
		for(int i = readElements.size()-1; i>-1; i--){
			String readElement = readElements.get(i);
			CreatePartAfterCommand createCommand = new CreatePartAfterCommand(modelContainer, MReportPart.createJRElement(new JRDesignExpression(readElement)), afterPart);
			cc.add(createCommand);
		}
		return cc;
	}

}
