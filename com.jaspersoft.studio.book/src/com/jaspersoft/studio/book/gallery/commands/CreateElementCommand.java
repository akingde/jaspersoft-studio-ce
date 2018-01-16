/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.commands;

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

import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.design.JRDesignSection;

/**
 * Command to create an element inside the gallery, can be undone
 * 
 * @author Orlandin Marco
 *
 */
public class CreateElementCommand extends AbstractOperation {

	/**
	 * The element to create
	 */
	private IGalleryElement elementToCreate;
	
	/**
	 * The gallery where the element will be created
	 */
	private GalleryComposite container;
	
	/**
	 * The index of the creation, if it's -1 then it is placed in the last position
	 */
	private int index = -1;
	
	private String partPath;
	
	/**
	 * Build the command to create the element to a specific index
	 * 
	 * @param container The gallery where the element will be created
	 * @param elementToCreate The element to create
	 * @param index The index of the creation, if it's -1 then it is placed in the last position
	 */
	public CreateElementCommand(GalleryComposite container, IGalleryElement elementToCreate, int index){
		super("Create Element");
		this.partPath = (String) elementToCreate.getData();
		this.elementToCreate = elementToCreate;
		this.index = index;
		this.container = container;
	}
	
	/**
	 * Build the command to create the element at the end of the gallery
	 * 
	 * @param container The gallery where the element will be created
	 * @param elementToCreate The element to create
	 */
	public CreateElementCommand(GalleryComposite container, IGalleryElement elementToCreate){
		this(container, elementToCreate, -1);
	}

	/**
	 * Create the element inside the gallery
	 */
	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if(index < 0 || index >= container.getContentSize()){
			index = -1;
		}
		IReportPartContainer partsContainer = container.getPartsContainer();
		JRDesignSection jrsection = partsContainer.getSection();
		if(jrsection!=null){
			JRDesignPart part = MReportPart.createJRElement(new JRDesignExpression(partPath));
			if(index==-1){
				jrsection.addPart(part);
			}
			else {
				jrsection.addPart(index,part);
			}
			MReportPart partNode = (MReportPart) ReportFactory.createNode((ANode) partsContainer,part,index);
			elementToCreate = new ReportPartGalleryElement(partNode);
			container.createItem(elementToCreate, index);
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
		if(index < 0 || index > container.getContentSize()){
			index = container.getContentSize()-1;
		}
		container.removeItem(index);
		return Status.OK_STATUS;
	}
	
}
