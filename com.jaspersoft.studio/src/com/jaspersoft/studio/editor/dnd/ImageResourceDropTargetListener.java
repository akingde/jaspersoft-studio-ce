/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.dnd;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.part.ResourceTransfer;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.image.MImage;
import com.jaspersoft.studio.model.image.command.CreateImageCommand;
import com.jaspersoft.studio.repository.IRepositoryViewProvider;
import com.jaspersoft.studio.utils.ImageUtils;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Implementation of a drop target listener that is supposed to handle the dropping of images inside
 * {@link EditPartViewer}s.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ImageResourceDropTargetListener extends AbstractTransferDropTargetListener {

	private SimpleImageCreationFactory factory = new SimpleImageCreationFactory();

	public ImageResourceDropTargetListener(EditPartViewer viewer, Transfer xfer) {
		super(viewer, xfer);
	}

	public ImageResourceDropTargetListener(EditPartViewer viewer) {
		super(viewer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#updateTargetRequest()
	 */
	@Override
	protected void updateTargetRequest() {
		((CreateRequest) getTargetRequest()).setLocation(getDropLocation());
	}

	@Override
	public boolean isEnabled(DropTargetEvent event) {
		if (event.data != null) {
			// check if it can be an image
			return isDroppedDataAnImage(event);
		}
		return super.isEnabled(event);
	}

	/*
	 * Verifies if the object being dropped is a valid image resource.
	 */
	private boolean isDroppedDataAnImage(DropTargetEvent event) {
		if (ResourceTransfer.getInstance().isSupportedType(event.currentDataType)) {
			if (event.data instanceof IResource[]) {
				// Dropping an image resource from inside workspace
				IResource imgResource = ((IResource[]) event.data)[0];
				String extension = imgResource.getProjectRelativePath().getFileExtension();
				return ImageUtils.hasValidFileImageExtension(extension) || extension.equalsIgnoreCase("svg");
			}
		} else if (FileTransfer.getInstance().isSupportedType(event.currentDataType)) {
			// Dropping an image resource from outside workspace
			if (event.data instanceof String[]) {
				String filepath = ((String[]) event.data)[0];
				if (filepath != null) {
					int lastIndexOfDot = filepath.lastIndexOf(".");
					if (lastIndexOfDot != -1) {
						String extension = filepath.substring(lastIndexOfDot + 1);
						return ImageUtils.hasValidFileImageExtension(extension) || extension.equalsIgnoreCase("svg");
					}
				}
			}
		} else if (ImageURLTransfer.getInstance().isSupportedType(event.currentDataType)) {
			// Dropping an image dropped from a contributed view (i.e: repository view)
			return (event.data instanceof String);
		}

		return false;
	}

	@Override
	protected void handleDrop() {
		updateTargetRequest();
		updateTargetEditPart();

		if (getTargetEditPart() != null) {
			Command command = getCommand();
			if (command instanceof CreateImageCommand && command.canExecute()) {
				try {
					command = setImageExpression((CreateImageCommand) command);
					getViewer().getEditDomain().getCommandStack().execute(command);
				} catch (InterruptedException e) {
					getCurrentEvent().detail = DND.DROP_NONE;
				}
			} else
				getCurrentEvent().detail = DND.DROP_NONE;
		} else
			getCurrentEvent().detail = DND.DROP_NONE;
	}

	/*
	 * Updates the image creation command with the valid expression for the newly created image element.
	 */
	private Command setImageExpression(CreateImageCommand command) throws InterruptedException {
		if (ResourceTransfer.getInstance().isSupportedType(getCurrentEvent().currentDataType)) {
			// Dropping an image resource from inside workspace
			IResource imgResource = ((IResource[]) getCurrentEvent().data)[0];

			// Starting from JR 6.2.2 the file name is supported even to load SVG files.
			// In previous versions the user should relay on an expression like:
			// "net.sf.jasperreports.renderers.BatikRenderer.getInstanceFromLocation($P{JASPER_REPORTS_CONTEXT}, <file
			// name>)";
			String expression = "\"" + imgResource.getProjectRelativePath() + "\"";

			command.setImageExpression(new JRDesignExpression(expression));
		} else if (FileTransfer.getInstance().isSupportedType(getCurrentEvent().currentDataType)) {
			// Dropping an image resource from outside workspace
			String filepath = ((String[]) getCurrentEvent().data)[0];
			if (filepath != null) {
				String expression = "\"" + filepath + "\"";
				command.setImageExpression(new JRDesignExpression(expression));
			}
		} else if (ImageURLTransfer.getInstance().isSupportedType(getCurrentEvent().currentDataType)) {
			// Dropping an image dropped from a contributed view (i.e: repository view)
			String filepath = (String) getCurrentEvent().data;
			if (filepath != null) {
				String expr = filepath;
				command.setImageExpression(new JRDesignExpression("\"" + expr.trim() + "\""));
				int indx = filepath.lastIndexOf("\n");
				if (indx > 0) {
					expr = filepath.substring(indx).trim();
					command.setImageExpression(new JRDesignExpression("\"" + expr.trim() + "\""));

					String srv = filepath.substring(0, indx);
					ISelection s = getViewer().getSelection();
					if (s instanceof StructuredSelection) {
						Object obj = ((StructuredSelection) s).getFirstElement();
						if (obj instanceof EditPart)
							obj = ((EditPart) obj).getModel();
						if (obj instanceof APropertyNode) {
							INode r = ((APropertyNode) obj).getRoot();
							CompoundCommand cc = new CompoundCommand(command.getLabel());
							if (r != null)
								for (IRepositoryViewProvider rvp : JaspersoftStudioPlugin.getInstance().getExtensionManager()
										.getRepositoryProviders()) {
									List<Command> c = rvp.dropResource(srv, r);
									if (!Misc.isNullOrEmpty(c))
										for (Command item : c)
											cc.add(item);
								}
							if (!cc.isEmpty()) {
								cc.add(command);
								return cc;
							}
						}
					}
				}
			}
		}
		return command;
	}

	@Override
	protected Request createTargetRequest() {
		CreateRequest request = new CreateRequest();
		request.setFactory(factory);
		return request;
	}

	private class SimpleImageCreationFactory implements CreationFactory {
		public Object getNewObject() {
			return new MImage();
		}

		public Object getObjectType() {
			return MImage.class;
		}
	}
}
