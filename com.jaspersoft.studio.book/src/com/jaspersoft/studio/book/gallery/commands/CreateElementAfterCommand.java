/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.commands;

import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.design.JRDesignSection;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.jaspersoft.studio.book.editors.ReportPartGalleryElement;
import com.jaspersoft.studio.book.gallery.controls.GalleryComposite;
import com.jaspersoft.studio.book.gallery.interfaces.IGalleryElement;
import com.jaspersoft.studio.book.model.IReportPartContainer;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.util.ReportFactory;

/**
 * Command to create an element inside the gallery after another element, can be undone
 * 
 * @author Orlandin Marco
 *
 */
public class CreateElementAfterCommand extends AbstractOperation {

	/**
	 * The element to create
	 */
	private IGalleryElement elementToCreate;
	
	/**
	 * The gallery where the element will be created
	 */
	private GalleryComposite container;
	
	/**
	 * The element after witch the new element will be created
	 */
	private IGalleryElement afterElement;

	private String partPath;
	
	/**
	 * Build the command 
	 * 
	 * @param container The gallery where the element will be created
	 * @param elementToCreate The element to create
	 * @param afterElement The element after witch the new element will be created. If it is null the new element
	 * will be created at the start of the gallery
	 */
	public CreateElementAfterCommand(GalleryComposite container, IGalleryElement elementToCreate, IGalleryElement afterElement){
		super("Create Element Command");
		this.partPath = (String) elementToCreate.getData();
		this.elementToCreate = elementToCreate;
		this.afterElement = afterElement;
		this.container = container;
	}
	
	/**
	 * Create the element inside the gallery
	 */
	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		int chosenIndex = -1;
		if (afterElement == null){
			chosenIndex = 0;
		} else {
			int afterIndex = container.getIndexOf(afterElement);
			if (afterIndex == container.getContentSize()-1){
				chosenIndex = -1;
			} else {
				chosenIndex = afterIndex+1;
			}
		}
		IReportPartContainer partsContainer = container.getPartsContainer();
		JRDesignSection jrsection = partsContainer.getSection();
		if(jrsection!=null){
			JRDesignPart part = MReportPart.createJRElement(new JRDesignExpression(partPath));
			if(chosenIndex!=-1){
				jrsection.addPart(chosenIndex,part);
			}
			else {
				jrsection.addPart(part);
			}
			MReportPart partNode = (MReportPart) ReportFactory.createNode((ANode) partsContainer,part,chosenIndex);
			elementToCreate = new ReportPartGalleryElement(partNode);
			container.createItem(elementToCreate, chosenIndex);
		}
		return Status.OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return execute(monitor, info);
	}

	/**
	 * Remove the created element from it's position
	 */
	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		int createdIndex = container.getIndexOf(elementToCreate);
		container.removeItem(createdIndex);
		return Status.OK_STATUS;
	}
	
}
