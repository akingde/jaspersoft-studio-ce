/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.dnd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.part.ResourceTransfer;

import com.jaspersoft.studio.book.JRBookActivator;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.book.model.commands.CreatePartAfterCommand;
import com.jaspersoft.studio.book.wizards.PageWizard;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;
import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;

/**
 * Class that handle the native swt drag and drop to allow to drag inside 
 * the editor a report from the project explorer
 * 
 * @author Orlandin Marco
 *
 */
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
				return parseExternalResources((IResource[])event.data, null, null, null);
			}
		}
		return super.getCommand();
	}
	
	/**
	 * Generate the command for the drop. It very similar to the command generated during
	 * the drag operation but it allows to set the connection and\or the datasource expressions
	 * 
	 * @param connectionExp the connection expression or null to avoid to set it
	 * @param datasourceExp the datasource expression or null to avoid to set it
	 * @param jrSubreportParameters the parameters for the subreport part
	 * @return the command to create the element and optionally set the expressions
	 */
	protected Command getDropCommand(JRExpression connectionExp, JRExpression datasourceExp, JRSubreportParameter[] jrSubreportParameters) {
		DropTargetEvent event = getCurrentEvent();
		for(TransferData eventData : event.dataTypes){
			if (ResourceTransfer.getInstance().isSupportedType(eventData)){
				return parseExternalResources((IResource[])event.data, connectionExp, datasourceExp, jrSubreportParameters);
			}
		}
		return super.getCommand();
	}
	
	/**
	 * Updates the target Request and target EditPart, and performs the drop. By
	 * default, the drop is performed by asking the target EditPart for a
	 * Command using the target Request. This Command is then executed on the
	 * CommandStack.
	 * <P>
	 * If there is no target EditPart or no executable Command, the event's
	 * <code>detail</code> field is set to <code>DND.DROP_NONE</code>.
	 */
	protected void handleDrop() {
		updateTargetRequest();
		updateTargetEditPart();

		if (getTargetEditPart() != null && hasParts()) {
			PageWizard wizard = new PageWizard();
			WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				Command command = getDropCommand(wizard.getConnectionExpression(), wizard.getDatasourceExpression(), wizard.getParameters());
				if (command != null && command.canExecute()){
					getViewer().getEditDomain().getCommandStack().execute(command);	
				}
			}
			else getCurrentEvent().detail = DND.DROP_NONE;
		} else getCurrentEvent().detail = DND.DROP_NONE;
	}
	
	@Override
	protected Request createTargetRequest() {
		ChangeBoundsRequest request =  new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
		request.setLocation(getDropLocation());
		return request;
	}
	
	/**
	 * Set the connection and datasource expressions on a part, this values are set only
	 * if they are not null
	 * 
	 * @param newPart The part where the expressions should be set, must have a component of 
	 * type StandardSubreportPartComponent
	 * @param connectionExp the connection expression or null to avoid to set it
	 * @param datasourceExp the datasource expression or null to avoid to set it
	 * @param jrSubreportParameters the parameters for the subreport part
	 */
	private void setDataParameters(JRDesignPart newPart, JRExpression connectionExp, JRExpression datasourceExp, JRSubreportParameter[] jrSubreportParameters){
		if (newPart.getComponent() instanceof StandardSubreportPartComponent){
			StandardSubreportPartComponent subPart = (StandardSubreportPartComponent)newPart.getComponent();
			if (connectionExp != null) {
				JRDesignSubreportParameter param = new JRDesignSubreportParameter();
				param.setName(MReportPart.REPORT_CONNECTION_PROPERTY);
				param.setExpression(connectionExp);
				try {
					subPart.addParameter(param);
				} catch (JRException e) {
					JRBookActivator.getDefault().logError(e);
				}
			}
			if (datasourceExp != null){
				JRDesignSubreportParameter param = new JRDesignSubreportParameter();
				param.setName(MReportPart.REPORT_DATASOURCE_PROPERTY);
				param.setExpression(datasourceExp);
				try {
					subPart.addParameter(param);
				} catch (JRException e) {
					JRBookActivator.getDefault().logError(e);
				}
			}
			if (jrSubreportParameters != null) {
				for(JRSubreportParameter param : jrSubreportParameters) {
					try {
						subPart.addParameter(param);
					} catch (JRException e) {
						JRBookActivator.getDefault().logError(e);
					}
				}
			}
		}
	}
	
	/**
	 * Check if between the current dragged items there is at least a jrxml or jasper file
	 * 
	 * @return true if at least a jrxml or jasper file is dragged, false otherwise
	 */
	private boolean hasParts(){
		if (partTracker == null || partTracker.getContainer() == null) return false;
		DropTargetEvent event = getCurrentEvent();
		for(TransferData eventData : event.dataTypes){
			if (ResourceTransfer.getInstance().isSupportedType(eventData)){
				//Search the jrxml files
				for(IResource resource : (IResource[])event.data){
					if (resource instanceof IFile){
						IFile file = (IFile)resource;
						String fileName = file.getName().toLowerCase();
						if (fileName.endsWith(FileExtension.PointJRXML) || fileName.endsWith(FileExtension.PointJASPER)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Parse a list of external resources and if they are report then
	 * they are added inside the gallery in the specified drop location
	 * 
	 * @param input the resources to add
	 * @param connectionExp the connection expression or null to avoid to set it
	 * @param datasourceExp the datasource expression or null to avoid to set it
	 * @param jrSubreportParameters the parameters for the subreport part
	 */
	private Command parseExternalResources(IResource[] input, JRExpression connectionExp, JRExpression datasourceExp, JRSubreportParameter[] jrSubreportParameters){
		if (partTracker == null || partTracker.getContainer() == null) return null;
		List<String> readElements = new ArrayList<String>(); 
		//Search the jrxml files
		for(IResource resource : input){
			if (resource instanceof IFile){
				IFile file = (IFile)resource;
				String fileName = file.getName().toLowerCase();
				if (fileName.endsWith(FileExtension.PointJRXML) || fileName.endsWith(FileExtension.PointJASPER)){
					IPath fileLocation = file.getLocation();
					if(FileExtension.JRXML.equals(fileLocation.getFileExtension())) {
						fileLocation = fileLocation.removeFileExtension();
						fileLocation = fileLocation.addFileExtension(FileExtension.JASPER);
					}
					readElements.add(fileLocation.toPortableString());
				}
			}
		}
		MReportPartContainer modelContainer = partTracker.getContainer().getBookModel();
		//otherwise add the elments to the specified location
		MReportPart afterPart = partTracker.getAfterPart() != null ? (MReportPart)partTracker.getAfterPart().getModel() : null;
		CompoundCommand cc = new CompoundCommand();
		for(int i = readElements.size()-1; i>-1; i--){
			String readElement = readElements.get(i);
			File fileElement = new File(readElement);
			JRDesignExpression partExptession = new JRDesignExpression("\""+fileElement.getName()+"\"");
			JRDesignPart newPart = MReportPart.createJRElement(partExptession);
			setDataParameters(newPart, connectionExp, datasourceExp, jrSubreportParameters);
			CreatePartAfterCommand createCommand = new CreatePartAfterCommand(modelContainer, newPart, afterPart);
			cc.add(createCommand);
		}
		return cc;
	}

}
