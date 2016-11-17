/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.style.MStyleTemplateReference;

/**
 * Paste the elements in the clipboard
 */
public class PasteAction extends ACachedSelectionAction {

	public PasteAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		super.init();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText(Messages.common_paste);
		setId(ActionFactory.PASTE.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		setEnabled(false);
	}

	/**
	 * If possible it avoid to generate the command to calculate the enablement
	 * state of the action, to speedup 
	 */
	@Override
	protected boolean calculateEnabled() {
		Object obj = Clipboard.getDefault().getContents();
		if (obj instanceof PastableElements) {
			PastableElements pastableElements = (PastableElements)obj;
			Collection<?> selectedObjects = getSelectedObjects();
			for(Object element : selectedObjects){
				if (element instanceof EditPart) {
					Object modelObj = ((EditPart) element).getModel();
					if  (modelObj instanceof MReport || modelObj instanceof MPage){
						return true;
					} else if (modelObj instanceof ANode){
						if (getParent2Paste((ANode)modelObj, pastableElements.getCopiedElements()) != null){
							return true;
						}
					}
				}
			}
		} else if (obj instanceof AbstractPastableObject){
			command = ((AbstractPastableObject)obj).getPasteCommand(getSelectedObjects());
			return command != null && command.canExecute();
		}
		return false;
	}
	
	/**
	 * Check if content can be pasted on the passed node, if it is not like this
	 * start to go up in the hierarchy. If it comes to a band model with null value return null
	 * 
	 * @param n node from where the search of a node where the elements can be pasted start
	 * @return the node where the elements can be pasted or null if the elements can't be at all
	 */
	private IPastable getParent2Paste(ANode n, Collection<ICopyable> copiedElements) {
		if (!n.isEditable()){
			return null;
		}
		while (n != null) {
			if (n instanceof IPastable) {
				if (n instanceof MBand && n.getValue() == null){
					return null;
				}  else {
					boolean allPastable = true;
					for(ICopyable copyable : copiedElements){
						ICopyable.RESULT result = copyable.isCopyable2(n);
						if (result == ICopyable.RESULT.CHECK_PARENT){
							allPastable = false;
							break;
						} else if (result == ICopyable.RESULT.NOT_COPYABLE){
							return null;
						}
					}
					if (allPastable) return (IPastable) n;
				}
			} else if (n instanceof MStyleTemplateReference){
				return null;
			} 
			n = (ANode) n.getParent();
		}
		return null;
	}

	@Override
	public void run() {
		Object obj = Clipboard.getDefault().getContents();
		command = ((AbstractPastableObject)obj).getPasteCommand(getSelectedObjects());
		execute(command);
		// Select the pasted edit part
		GraphicalViewer viewer = (GraphicalViewer) getWorkbenchPart().getAdapter(GraphicalViewer.class);
		if (viewer != null && command instanceof PasteCommand) {
			PasteCommand standardPasteCommand = (PasteCommand)command;
			viewer.setSelection(new StructuredSelection(getSelectableEditParts(viewer, standardPasteCommand.getPasteParent(),	standardPasteCommand.getCreatedNodesNumber())));
		}
	}

	/**
	 * Return a list of the edit part created because the past operation
	 * 
	 * @param viewer
	 * @param pasteParent
	 *          parent of the pasted elements
	 * @param createdElements
	 *          number of pasted elements
	 * @return the editpart created for the paste operation, so they can be selected
	 */
	@SuppressWarnings("rawtypes")
	private List<EditPart> getSelectableEditParts(GraphicalViewer viewer, IPastable pasteParent, int createdElements) {
		List<EditPart> selectableChildren = new ArrayList<EditPart>();
		if (!(pasteParent instanceof ANode)){
			return selectableChildren;
		}
		ANode parentModel = (ANode) pasteParent;
		HashSet<INode> pastedModels = new HashSet<INode>();
		int elementsToInsert = createdElements;
		List<INode> childrens = parentModel.getChildren();
		// the list children can be empty in specific use case
		// for example when the elements were pasted into a node
		// that is not expanded in the main editor (i.e: list component)
		if(childrens.size()>=elementsToInsert) {
			while (elementsToInsert > 0) {
				pastedModels.add(childrens.get(childrens.size() - elementsToInsert));
				elementsToInsert--;
			}
		}

		List children = viewer.getContents().getChildren();
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child instanceof EditPart) {
				EditPart part = (EditPart) child;
				if (pastedModels.contains(part.getModel()) && part.isSelectable())
					selectableChildren.add(part);
			}
		}
		return selectableChildren;
	}

}
