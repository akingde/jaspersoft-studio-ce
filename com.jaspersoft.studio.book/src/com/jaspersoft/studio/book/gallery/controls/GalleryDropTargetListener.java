/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.controls;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.nebula.widgets.gallery.Gallery;
import org.eclipse.nebula.widgets.gallery.GalleryItem;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ResourceTransfer;

import com.jaspersoft.studio.book.gallery.commands.CompoundOperation;
import com.jaspersoft.studio.book.gallery.commands.CreateElementAfterCommand;
import com.jaspersoft.studio.book.gallery.commands.CreateElementCommand;
import com.jaspersoft.studio.book.gallery.commands.MoveElementAfter;
import com.jaspersoft.studio.book.gallery.commands.MoveElementToEnd;
import com.jaspersoft.studio.book.gallery.implementations.PageElement;
import com.jaspersoft.studio.book.gallery.interfaces.IGalleryElement;

/**
 * Drop listener when something of a supported type (so a resource or an element from another gallery)
 *  is dropped inside a gallery
 * 
 * @author Orlandin Marco
 *
 */
public class GalleryDropTargetListener implements DropTargetListener {
	
	/**
	 * The gallery where the user actually is with the mouse pointer
	 */
	private GalleryComposite lastTargetGallery = null;

	/**
	 * Execute the drop operation
	 */
	@Override
	public void drop(DropTargetEvent event) {
		DropLocation currentDrop = new DropLocation();
		for(TransferData eventData : event.dataTypes){
			if (ResourceTransfer.getInstance().isSupportedType(eventData)){
				//Was dropped a resource from the outside
				getExternalDropLocation(event.x, event.y, currentDrop);
				parseExternalResources((IResource[])event.data, currentDrop);
				currentDrop.getTargetGallery().clearDropEffect(true);
				break;
			} else if (GalleryTransfer.getInstance().isSupportedType(eventData)) {
				//Was dropped an element dragged from another gallery
				DropLocation location = new DropLocation();
				GalleryComposite sourceGallery = GalleryTransfer.getInstance().getSourceGallery();
				location.setSourceGallery(sourceGallery);
				if (!sourceGallery.isSelectionEmpty()) {
					getDropLocation(event.x, event.y, location);
					if (location.isValid()) {
						GalleryItem movedItem = location.getSourceGallery().getGallery().getSelection()[0];
						if (location.isPlacedAfter()) {
							placeAfter(location.getItem(), movedItem, location);
						} else {
							placeBefore(location.getItem(), movedItem, location);
						}
					} else if (location.getTargetGallery() != null && location.getTargetGallery().isEmpty()){
						GalleryItem movedItem = location.getSourceGallery().getGallery().getSelection()[0];
						MoveElementToEnd moveAfterCommand = new MoveElementToEnd((IGalleryElement)movedItem.getData(), location.getSourceGallery(), location.getTargetGallery());
						location.getTargetGallery().executeCommand(moveAfterCommand);
					}
					location.getTargetGallery().clearDropEffect(true);
				}
				break;
			}
		}
		lastTargetGallery = null;
	}
	
	/**
	 * Update the drag feedback and request the repaint if it is different from
	 * the last one
	 */
	@Override
	public void dragOver(DropTargetEvent event) {
		DropLocation currentDrop = new DropLocation();
		for(TransferData eventData : event.dataTypes){
			if (ResourceTransfer.getInstance().isSupportedType(eventData)){
				//Dragging an element from outside
				getExternalDropLocation(event.x, event.y, currentDrop);
				break;
			} else if (GalleryTransfer.getInstance().isSupportedType(eventData)) {
				//Dragging an element from another gallery
				currentDrop.setSourceGallery(GalleryTransfer.getInstance().getSourceGallery());
				getDropLocation(event.x, event.y, currentDrop);
				break;
			}	
		}
		if (currentDrop.isValid() && !hasAlreadyEffect(currentDrop)) {
			GalleryComposite target = currentDrop.getTargetGallery();
			DropEffectLocation lastDropEffect = target.getDropTargetEffect();
			lastDropEffect.setItem(currentDrop.getItem());
			lastDropEffect.setPlaceAfer(currentDrop.isPlacedAfter());
			target.getGallery().redraw();
		}
	}
	
	/**
	 * Check if the target gallery has already a drop feedback effect painted
	 * in the right position. This is done to avoid to repaint a drop feedback
	 * effect when it's already there and save time
	 * 
	 * @param dropLocation the location where the element will eventually be dropped
	 * @return true if the gallary has already a drop feedback in the right position and 
	 * there is no need to repaint it, false otherwise
	 */
	private boolean hasAlreadyEffect(DropLocation dropLocation){
		GalleryComposite target = dropLocation.getTargetGallery();
		DropEffectLocation lastDropEffect = target.getDropTargetEffect();
		return (lastDropEffect.getItem() == dropLocation.getItem() && lastDropEffect.isPlacedAfter() == dropLocation.isPlacedAfter());
	}


	/**
	 * When leaving a component if the last gallery where the mouse pointer
	 * was has a drop feedback effect then it is removed
	 */
	@Override
	public void dragLeave(DropTargetEvent event) {
		if (lastTargetGallery != null){
			lastTargetGallery.clearDropEffect(false);
		}
	}

	/**
	 * Called when the drop operation is supported from a component where
	 * the mouse is entering. If the control is a Gallery then it's reference is saved
	 * as the last gallery where the user was
	 */
	@Override
	public void dragEnter(DropTargetEvent event) {
		Display currentDisplay = UIUtils.getDisplay();
		Control hoveredControl = currentDisplay.getCursorControl();
		if (hoveredControl.getParent() instanceof GalleryComposite) {
			lastTargetGallery = (GalleryComposite) hoveredControl.getParent();
		}
	}
	
	/**
	 * Calculate the current drop location on the gallery basing on the mouse pointer
	 * position. all the informations are saved inside the currentLocation parameter.
	 * Use the isValid on the modified currentLocation to know if the drop position is valid
	 * An invalid position it is because it is outside the gallery or consecutive to the current
	 * position of the dragged element. This is use to calculate the drop position when the moved
	 * element is dragged from a gallery to another
	 * 
	 * @param currentX current x position of the mouse
	 * @param currentY current y position of the mouse
	 * @param currentLocation container where the target location will be saved
	 */
	private void getDropLocation(int currentX, int currentY, DropLocation currentLocation) {
		currentLocation.setItem(null);
		if (lastTargetGallery != null && currentLocation.getSourceGallery() != null && !currentLocation.getSourceGallery().isSelectionEmpty()) {
			currentLocation.setTargetGallery(lastTargetGallery);
			Display currentDisplay = UIUtils.getDisplay();
			Point pt = currentDisplay.map(null, currentLocation.getTargetGallery(), currentX, currentY);
			GalleryItem currentItem = currentLocation.getTargetGallery().getGallery().getItem(pt);
			GalleryItem movedItem = currentLocation.getSourceGallery().getGallery().getSelection()[0];
			if (currentItem == null) {
				int multiplier = 1;
				Gallery targetGallery = currentLocation.getTargetGallery().getGallery();
				while (multiplier < 10) {
					Point ptLeft = new Point(pt.x - (5 * multiplier), pt.y);
					Point ptRight = new Point(pt.x + (5 * multiplier), pt.y);

					GalleryItem itemLeft = targetGallery.getItem(ptLeft);

					if (itemLeft != null) {
						currentLocation.setItem(itemLeft);
						currentLocation.setPlaceAfer(true);
						break;
					}

					GalleryItem itemRight = targetGallery.getItem(ptRight);
					
					if (itemRight != null) {
						currentLocation.setItem(itemRight);
						currentLocation.setPlaceAfer(false);
						break;
					}
					multiplier++;
				}
			} else if (currentItem != movedItem) {
				Rectangle elementBounds = currentItem.getBounds();
				int halfWidth = elementBounds.width / 2;
				Rectangle splitLeft = new Rectangle(elementBounds.x, elementBounds.y, halfWidth, elementBounds.height);
				if (splitLeft.contains(pt)) {
					currentLocation.setItem(currentItem);
					currentLocation.setPlaceAfer(false);
				} else {
					currentLocation.setItem(currentItem);
					currentLocation.setPlaceAfer(true);
				}
			}
			
			
			if (currentLocation.isValid()){
				GalleryItem galleryRoot = currentLocation.getTargetGallery().getGallery().getItem(0);
				if (currentLocation.getItem() == movedItem){
					currentLocation.setItem(null);
				} else if (currentLocation.isPlacedAfter()){
					int index = galleryRoot.indexOf(currentLocation.getItem());
					if (index != galleryRoot.getItemCount()-1){
						GalleryItem nextItem = galleryRoot.getItem(index+1);
						if (nextItem == movedItem) currentLocation.setItem(null);
					}
				} else {
					int index = galleryRoot.indexOf(currentLocation.getItem());
					if (index != 0){
						GalleryItem previousItem = galleryRoot.getItem(index-1);
						if (previousItem == movedItem) currentLocation.setItem(null);
					}
				}
			}
		}
	}
	
	/**
	 * Calculate the current drop location on the gallery basing on the mouse pointer
	 * position. all the informations are saved inside the currentLocation parameter.
	 * Use the isValid on the modified currentLocation to know if the drop position is valid
	 * An invalid position it is because it is outside the gallery or consecutive to the current
	 * position of the dragged element. This is used when the moved element is a resource
	 * from an external view dragged inside the gallery
	 * 
	 * @param currentX current x position of the mouse
	 * @param currentY current y position of the mouse
	 * @param currentLocation container where the target location will be saved
	 */
	private void getExternalDropLocation(int currentX, int currentY, DropLocation currentLocation) {
		currentLocation.setItem(null);
		if (lastTargetGallery != null) {
			currentLocation.setTargetGallery(lastTargetGallery);
			Display currentDisplay = UIUtils.getDisplay();
			Point pt = currentDisplay.map(null, currentLocation.getTargetGallery(), currentX, currentY);
			GalleryItem currentItem = currentLocation.getTargetGallery().getGallery().getItem(pt);
			if (currentItem == null) {
				int multiplier = 1;
				Gallery targetGallery = currentLocation.getTargetGallery().getGallery();
				while (multiplier < 10) {
					Point ptLeft = new Point(pt.x - (5 * multiplier), pt.y);
					Point ptRight = new Point(pt.x + (5 * multiplier), pt.y);

					GalleryItem itemLeft = targetGallery.getItem(ptLeft);

					if (itemLeft != null) {
						currentLocation.setItem(itemLeft);
						currentLocation.setPlaceAfer(true);
						break;
					}

					GalleryItem itemRight = targetGallery.getItem(ptRight);
					
					if (itemRight != null) {
						currentLocation.setItem(itemRight);
						currentLocation.setPlaceAfer(false);
						break;
					}
					multiplier++;
				}
			} else {
				Rectangle elementBounds = currentItem.getBounds();
				int halfWidth = elementBounds.width / 2;
				Rectangle splitLeft = new Rectangle(elementBounds.x, elementBounds.y, halfWidth, elementBounds.height);
				if (splitLeft.contains(pt)) {
					currentLocation.setItem(currentItem);
					currentLocation.setPlaceAfer(false);
				} else {
					currentLocation.setItem(currentItem);
					currentLocation.setPlaceAfer(true);
				}
			}
			
			if (currentLocation.getItem() == null && !lastTargetGallery.isEmpty()){
				GalleryItem rootItem = lastTargetGallery.getGallery().getItem(0);
				currentLocation.setItem(rootItem.getItem(rootItem.getItemCount()-1));
			}
		}
	}
	
	/**
	 * Parse a list of external resources and if they are report then
	 * they are added inside the gallery in the specified drop location
	 * 
	 * @param input the resources to add
	 * @param drop the gallery and it's location where the valid resources should be placed
	 */
	private void parseExternalResources(IResource[] input, DropLocation drop){
		List<IGalleryElement> readElements = new ArrayList<IGalleryElement>(); 
		//Search the jrxml files
		for(IResource resource : input){
			if (resource instanceof IFile){
				IFile file = (IFile)resource;
				if (file.getName().toLowerCase().endsWith(".jrxml")){
					readElements.add(new PageElement(file.getRawLocation().makeAbsolute().toPortableString()));
				}
			}
		}
		if (drop.getTargetGallery().isEmpty()){
			//If the target gallery is empty add the elements to the end
			CompoundOperation cc = new CompoundOperation("Move Elements");
			for(IGalleryElement readElement : readElements){
				CreateElementCommand createCommand = new  CreateElementCommand(drop.getTargetGallery(), readElement);
				cc.add(createCommand);;
			}
			drop.getTargetGallery().executeCommand(cc);
		} else if (readElements.size()>0 && drop.isValid()){
			//otherwise add the elments to the specified location
			IGalleryElement firstElement = readElements.get(0);
			CompoundOperation cc = new CompoundOperation("Move Elements");
			IGalleryElement selectedNode = (IGalleryElement)drop.getItem().getData();
			if (drop.isPlacedAfter()){
				CreateElementAfterCommand firstCommand = new CreateElementAfterCommand(drop.getTargetGallery(), firstElement, selectedNode);
				cc.add(firstCommand);
			} else {
				IGalleryElement previousNode = null;
				int selectedNodeIndex  = drop.getTargetGallery().getIndexOf(selectedNode);
				if (selectedNodeIndex > 0){
					previousNode = drop.getTargetGallery().getElementAt(selectedNodeIndex-1);
				}
				CreateElementAfterCommand firstCommand = new CreateElementAfterCommand(drop.getTargetGallery(), firstElement, previousNode);
				cc.add(firstCommand);
			}
			IGalleryElement afterNode = firstElement;
			for(int i=1; i<readElements.size(); i++){
				IGalleryElement currentNode = readElements.get(i);
				CreateElementAfterCommand firstCommand = new CreateElementAfterCommand(drop.getTargetGallery(), currentNode, afterNode);
				cc.add(firstCommand);
				afterNode = currentNode;
			}
			drop.getTargetGallery().executeCommand(cc);
		}
	}


	/**
	 * Move an element after another one and return the result of the operation.
	 * The movement is done with commands, so it can be undone
	 * 
	 * @param afterItem element after the one the moved item should be placed
	 * @param movedItem the moved element
	 * @return true if the move operation can be done, false otherwise
	 */
	private boolean placeAfter(GalleryItem afterItem, GalleryItem movedItem, DropLocation location){
			if (afterItem == movedItem) return false;
			MoveElementAfter moveAfterCommand = new MoveElementAfter((IGalleryElement)afterItem.getData(), (IGalleryElement)movedItem.getData(), location.getSourceGallery(), location.getTargetGallery());
			location.getTargetGallery().executeCommand(moveAfterCommand);
			return true;
	}
	
	/**
	 * Move an element before another one and return the result of the operation.
	 * The movement is done with commands, so it can be undone
	 * 
	 * @param beforeItem element before the one the moved item should be placed
	 * @param movedItem the moved element
	 * @return true if the move operation can be done, false otherwise
	 */
	private boolean placeBefore(GalleryItem beforeItem, GalleryItem movedItem, DropLocation location){
		if (beforeItem == movedItem) return false;
		IGalleryElement beforeItemContainer = (IGalleryElement)beforeItem.getData();
		int beforeItemIndex = location.getTargetGallery().getElements().indexOf(beforeItemContainer);
		if (beforeItemIndex == 0){
			MoveElementAfter moveAfterCommand = new MoveElementAfter(null, (IGalleryElement)movedItem.getData(), location.getSourceGallery(), location.getTargetGallery());
			location.getTargetGallery().executeCommand(moveAfterCommand);
		} else {
			beforeItemContainer = location.getTargetGallery().getElements().get(beforeItemIndex-1);
			MoveElementAfter moveAfterCommand = new MoveElementAfter(beforeItemContainer, (IGalleryElement)movedItem.getData(), location.getSourceGallery(), location.getTargetGallery());
			location.getTargetGallery().executeCommand(moveAfterCommand);
		}
		return true;
	}
	
	@Override
	public void dropAccept(DropTargetEvent event) {
	}

	@Override
	public void dragOperationChanged(DropTargetEvent event) {
	}
}

