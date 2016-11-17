/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.command.DeleteElementCommand;
import com.jaspersoft.studio.model.style.MStyleTemplate;

/**
 * Cut the selected node. The cut consist of three parts:
 * 
 * 1 - The node is set as cut
 * 2 - The node is added to the clipboard
 * 3 - The node is removed from its actual parent (excluded the nested ones)
 * 
 * @author Orlandin Marco 
 * 
 */
public class CutAction extends ACachedSelectionAction {

	public CutAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		super.init();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText(Messages.common_cut);
		setId(ActionFactory.CUT.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT_DISABLED));
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createCommand());
	}

	/**
	 * Add to an HashSet all the children of the passed node and 
	 * all their descendants recursively
	 * 
	 * @param node the current node
	 * @param children HashSet where the nodes are added
	 */
	private void addChildren(INode node, HashSet<INode> children){
		for(INode child : node.getChildren()){
			addChildren(child, children);
			children.add(child);
		}
	}
	
	/**
	 * Get an HashSet of all the nested nod in the current 
	 * selection 
	 * 
	 * @return a not null HashSet
	 */
	private HashSet<INode> getNotNestedNodes(List<Object> copiedObjects){
		HashSet<INode> nodesInHierarchy = new HashSet<INode>();
		for(Object node : copiedObjects){
			if (node instanceof INode){
				addChildren((INode)node, nodesInHierarchy);
			}
		}
		return nodesInHierarchy;
	}
	
	@Override
	protected boolean calculateEnabled() {
		List<Object> copiableObjects = editor.getSelectionCache().getSelectionModelForType(ICopyable.class);
		ISelection currentSelection = editor.getSelectionCache().getLastRawSelection();
		List<Object> cuttableObjects = new ArrayList<Object>();
		//Search among the copyable objects the ones that can be cut
		for(Object obj : copiableObjects){
			ICopyable copyableObject = (ICopyable)obj;
			if (copyableObject.isCuttable(currentSelection)){
				cuttableObjects.add(copyableObject);
				if (obj instanceof ANode){
					ANode node = (ANode)obj;
					ANode parent = node.getParent();
					if (parent instanceof MStyleTemplate) return false;
				}
			}
		}
		return !cuttableObjects.isEmpty();
	}
	
	
	@Override
	protected Command createCommand() {
		List<Object> copiableObjects = editor.getSelectionCache().getSelectionModelForType(ICopyable.class);
		if (copiableObjects.isEmpty())
			return null;
		JSSCompoundCommand command = new JSSCompoundCommand(null);
		CutCommand cmd = new CutCommand();
		command.add(cmd);
		HashSet<INode> nestedNodes = getNotNestedNodes(copiableObjects);
		ISelection currentSelection = editor.getSelectionCache().getLastRawSelection();
		for (Object it : copiableObjects) {
			ICopyable copyable = (ICopyable)it;
			if  (copyable.isCuttable(currentSelection)){
				cmd.addElement((ICopyable) it);
				if (it instanceof MGraphicElement){
					MGraphicElement node = (MGraphicElement)it;
					command.setReferenceNodeIfNull(node.getRoot());
					//Avoid to delete the nested nodes
					if (!nestedNodes.contains(node)){
						DeleteElementCommand deleteCommand = new DeleteElementCommand(node);
						command.add(deleteCommand);
					}
				}
			}
		}
		return command;
	}

}
