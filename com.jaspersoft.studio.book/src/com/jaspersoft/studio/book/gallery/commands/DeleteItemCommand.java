/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.commands;

import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.design.JRDesignSection;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.jaspersoft.studio.book.gallery.controls.GalleryComposite;
import com.jaspersoft.studio.book.gallery.interfaces.IGalleryElement;
import com.jaspersoft.studio.book.model.MReportPart;

/**
 * Delete an element from the gallery
 * 
 * @author Orlandin Marco
 *
 */
public class DeleteItemCommand extends AbstractOperation {
	
	/**
	 * The gallery
	 */
	private GalleryComposite container;
	
	/**
	 * The index of the element to remove
	 */
	private int index;
	
	/**
	 * The delete element
	 */
	private IGalleryElement deletedElement = null;
	
	private JRDesignPart part;

	private JRDesignSection jrsection;

	
	
	/**
	 * Create the command 
	 * 
	 * @param container The gallery
	 * @param elementToDelete The element to remove
	 */
	public DeleteItemCommand(GalleryComposite container, IGalleryElement elementToDelete){
		super("Delete Item");
		this.container = container;
		this.index = container.getIndexOf(elementToDelete);
		MReportPart mpart = (MReportPart) elementToDelete.getData();
		this.part = mpart.getValue();
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		deletedElement = container.removeItem(index);
		JRDesignSection jrsection = container.getPartsContainer().getSection();
		if(jrsection!=null){
			jrsection.removePart(part);
		}
		return Status.OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return execute(monitor, info);
	}

	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		container.createItem(deletedElement, index);
		if(jrsection!=null) {
			jrsection.addPart(index, part);
		}
		deletedElement = null;
		return Status.OK_STATUS;
	}
}
