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
 * Move an element of the gallery to the end of another gallery
 * 
 * @author Orlandin Marco
 *
 */
public class MoveElementToEnd extends AbstractOperation {
	
	/**
	 * Element to move
	 */
	private IGalleryElement elementMoved;
	
	/**
	 * Gallery source
	 */
	private GalleryComposite source;
	
	/**
	 * Gallery target
	 */
	private GalleryComposite target;
	
	/**
	 * Element that was to the left of the moved element before the command was be executed
	 */
	private IGalleryElement oldPreviousItem = null;
	
	private JRDesignPart part;
	
	/**
	 * Create the command 
	 * 
	 * @param previousItem Element that will be to the left of the moved element when the command will be executed or null if
	 * the moved element should be moved on the first position
	 * @param elementMoved Element to move
	 * @param container the gallery that contains the elements
	 */
	public MoveElementToEnd(IGalleryElement elementMoved, GalleryComposite source, GalleryComposite target){
		super("Move Element");
		this.source = source;
		this.target = target;
		this.elementMoved = elementMoved;
		MReportPart mpart = (MReportPart) elementMoved.getData();
		this.part = mpart.getValue();
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		int oldIndex = source.getIndexOf(elementMoved);
		//Store the old item
		if (oldIndex == 0) {
			oldPreviousItem = null;
		} else {
			oldPreviousItem = source.getElementAt(oldIndex-1);
		}
		
		//Delete from the old position
		source.removeItem(oldIndex);
		JRDesignSection jrsectionSource = source.getPartsContainer().getSection();
		if(jrsectionSource!=null){
			jrsectionSource.removePart(oldIndex);
		}
		
		//create on the new position
		target.createItem(elementMoved, -1);
		JRDesignSection jrsectionTarget = target.getPartsContainer().getSection();
		if(jrsectionTarget!=null){
			jrsectionTarget.addPart(part);
		}
		return Status.OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return execute(monitor, info);
	}

	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		//Delete from the new position
		target.removeItem(target.getContentSize()-1);
		
		//create on the old position
		int newIndex = 0;
		if (oldPreviousItem != null){
			newIndex = source.getIndexOf(oldPreviousItem)+1;
		}
		source.createItem(elementMoved, newIndex);
		return Status.OK_STATUS;
	}
}
