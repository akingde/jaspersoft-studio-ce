/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.StructuredSelection;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.selection.SelectElementCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Implementation of the paste definition for the editor graphical
 * objects. This can be used to copy\paste graphical objects. If one ore
 * more containers are selected it paste the elements on that containers. If
 * the root node is selected it paste the elements on the same parent they have
 * when they were copied. If this is a copy operation and the elements selected
 * are the same that were copied than they are pasted on the same parents of the
 * original copy
 * 
 * @author Orlandin Marco
 *
 */
public class PastableElements extends AbstractPastableObject {

	/**
	 * Define if the past action is done for a copy or a cut
	 */
	public enum ACTION_TYPE{COPY, CUT};

	/**
	 * Create a map of the parents of each copied node. This is done
	 * because with the cut action the nodes are removed and could be impossible
	 * to recover the current parent, so the parent is saved when the node 
	 * is copied
	 */
	private HashMap<ICopyable, ANode> parentsMap;
	
	/**
	 * Define if the paste is done because a cut or a copy
	 */
	private ACTION_TYPE actionType;
	
	/**
	 * The JasperReportConfiguration of the copied elements
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * Create an instance of the class
	 * 
	 * @param list not null list of the elements that are object of the copy
	 * @param parentsMap map of the parents of every copied node, must be not null
	 * @param actionType enum used to know if the current set to paste comes from a copy or a cut
	 */
	public PastableElements(List<ICopyable> list, HashMap<ICopyable, ANode> parentsMap, ACTION_TYPE actionType) {
		super(list);
		this.parentsMap = parentsMap;
		this.actionType = actionType;
		//Initialize the JasperReportsConfiguration
		for(ICopyable node : list){
			if (node instanceof ANode){
				jConfig = ((ANode)node).getJasperConfiguration();
			}
		}
	}
	
	/**
	 * Check if the copied set need do be pasted on the same parents each elements
	 * had when it was copied or on the selected containers. The element maintains the 
	 * same container if they are pasted on the same report and if the node where they are
	 * pasted is an MReport or an MPage or if the current selected elements are the same elements
	 * that were coped
	 * 
	 * @param selectedObjects the current selection set
	 * @return true if the elements pasted must maintain the same parent of the originals, 
	 * false if they must be copied all in the selected parents
	 */
	public boolean doSpecialPaste(Collection<?> selectedObjects){
		//Check if the elements where target of the copy and the copied one are
		//in the same report, by checking the JasperReportConfiguration
		for (Object selection : selectedObjects) {
			if (selection instanceof EditPart) {
				Object model = ((EditPart)selection).getModel();
				if (model instanceof ANode){
					JasperReportsConfiguration nodeConfig = ((ANode)model).getJasperConfiguration();
					if (nodeConfig != null && nodeConfig != jConfig){
						return false;
					}
					break;
				}
			}
		}
		if (selectedObjects.size() == 1){
			Object firstElement = selectedObjects.iterator().next();
			if (firstElement instanceof EditPart) {
				Object modelObj = ((EditPart) firstElement).getModel();
				boolean result =(modelObj instanceof MReport || modelObj instanceof MPage);
				if (result) return true;
			}
		} 
		if (actionType == ACTION_TYPE.COPY && selectedObjects.size() == list.size()){
			for (Object selection : selectedObjects) {
				if (selection instanceof EditPart) {
					Object model = ((EditPart)selection).getModel();
					if (!list.contains(model)) return false;
				} else {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public Command getPasteCommand(Collection<?> targets) {
		return createCommand(targets);
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
	 * Get the list of node to paste. This nodes are the list 
	 * in the clipboard minus all the nested nodes of an already
	 * copied item
	 * 
	 * @return a not null list of element to paste
	 */
	private List<ICopyable> getNotNestedNodes(){
		HashSet<INode> nodesInHierarchy = new HashSet<INode>();
		for(ICopyable node :list){
			if (node instanceof INode){
				addChildren((INode)node, nodesInHierarchy);
			}
		}
		List<ICopyable> result = new ArrayList<ICopyable>();
		for(ICopyable node :list){
			if (!nodesInHierarchy.contains(node)){
				result.add(node);
			}
		}
		return result;
	}
	
	/**
	 * Create the command to pasted the copied elements, check if the pasted
	 * elements must maintains the same parent of the original or not before,
	 * and in case create a different command
	 * 
	 * @param selectedObjects the list of object selected for the paste
	 */
	protected Command createCommand(Collection<?> selectedObjects) {
		if (doSpecialPaste(selectedObjects)){
			CompoundCommand copyAndSelectElementsCommand = new CompoundCommand();
			JSSCompoundCommand copyElementsCommand = new JSSCompoundCommand(null);
			copyAndSelectElementsCommand.add(copyElementsCommand);
			List<JRChild> elementsToSelect = new ArrayList<JRChild>();
			for(ICopyable node : getNotNestedNodes()){
				ANode parent = parentsMap.get(node);
				copyElementsCommand.setReferenceNodeIfNull(parent);
				if (parent != null && node instanceof ANode){
					PasteElementCommand pasteCommand = new PasteElementCommand(parent, (ANode)node);
					copyElementsCommand.add(pasteCommand);
					if (pasteCommand.getPastedJRElement() instanceof JRDesignElement){
						elementsToSelect.add((JRDesignElement)pasteCommand.getPastedJRElement());
					}
				}
			}
			//Add the command to select the new elements
			SelectElementCommand selectPastedCommands = new SelectElementCommand(elementsToSelect);
			copyAndSelectElementsCommand.add(selectPastedCommands);
			return copyAndSelectElementsCommand;
		} else {
			for (Object selection : selectedObjects) {
				Command cmd = getPasteComand(selection);
				if (cmd != null)
					return cmd;
				if (selection instanceof StructuredSelection) {
					StructuredSelection s = (StructuredSelection) selection;
					for (Iterator<?> it = s.iterator(); it.hasNext();) {
						Object o = it.next();
						cmd = getPasteComand(o);
						if (cmd != null)
							return cmd;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Check if content can be pasted on the passed node, if it is not like this
	 * start to go up in the hierarchy. If it comes to a band model with null value return null
	 * 
	 * @param n node from where the search of a node where the elemens can be pasted start
	 * @return the node where the elements can be pasted or null if the elements can't be at all
	 */
	private IPastable getParent2Paste(ANode n) {
		while (n != null) {
			if (n instanceof IPastable) {
				if (n instanceof MBand && n.getValue() == null){
					return null;
				} else {
					boolean allPastable = true;
					for(ICopyable copyable : list){
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
			}
			n = (ANode) n.getParent();
		}
		return null;
	}
	
	private Command getPasteComand(Object selection) {
		if (selection instanceof EditPart) {
			Object modelObj = ((EditPart) selection).getModel();
			if (modelObj instanceof ANode) {
				IPastable past = getParent2Paste((ANode) modelObj);
				if (past != null) {
					return new PasteCommand(past, getNotNestedNodes());
				}
			}
		} else if (selection instanceof ANode) {
			IPastable past = getParent2Paste((ANode) selection);
			if (past != null) {
				return new PasteCommand(past, getNotNestedNodes());
			}
		}
		return null;
	}
}
