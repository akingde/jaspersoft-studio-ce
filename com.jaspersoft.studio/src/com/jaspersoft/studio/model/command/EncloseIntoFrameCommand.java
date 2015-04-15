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
package com.jaspersoft.studio.model.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.frame.MFrame;

/**
 * 
 * Command to encapsulate elements with the same parent inside a containing frame.
 * The new frame will have the size to contains perfectly all the elements. The commands
 * can be undone an redone
 * 
 * @author Orlandin Marco
 *
 */
public class EncloseIntoFrameCommand extends Command {

	/**
	 * The list of the nodes to enclose into a frame
	 */
	private List<APropertyNode> nodeList = new ArrayList<APropertyNode>();
	
	/**
	 * Used for the undo, list of the element moved into the frame with the position that they
	 * have before the command was executed
	 */
	private List<NodePreviousPosition> previousPositions = new ArrayList<NodePreviousPosition>();
	
	/**
	 * The frame where the elements will be placed
	 */
	private JRDesignFrame containerFrame;
	
	/**
	 * Parent node where the frame will be created
	 */
	private ANode parent;
	
	/**
	 * Container to store the information on the previous
	 * position of a moved node. All the fields are keep 
	 * private since the class is used only here and so 
	 * the fields can be accessed directly
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class NodePreviousPosition{
		
		/**
		 * The node index when it was on its original parent
		 */
		private int nodeIndex;
		
		/**
		 * The node original parent
		 */
		private ANode nodeParent;
		
		/**
		 * The moved node
		 */
		private ANode movedNode;
		
		/**
		 * The x position of the node when it was on the original parent. This
		 * is necessary because when the node is moved inside the frame it must
		 * have the x and y coordinates changed, because in JSS the coordinates
		 * are relative to the parent
		 */
		private int oldX;
		
		/**
		 * The y position of the node when it was on the original parent. 
		 */
		private int oldY;
		
		public NodePreviousPosition(int nodeIndex, ANode nodeParent, ANode movedNode){
			this.nodeIndex = nodeIndex;
			this.nodeParent = nodeParent;
			this.movedNode = movedNode;
			JRDesignElement elementNode = (JRDesignElement)movedNode.getValue();
			oldX = elementNode.getX();
			oldY = elementNode.getY();
		}
		
	}
	
	/**
	 * Create the command, the nodes that will be moved must be added with
	 * the addNode command. 
	 * 
	 * @param nodesParent parent where the frame will be placed. Must be also
	 * the parent of the added nodes before they are moved
	 */
	public EncloseIntoFrameCommand(ANode nodesParent) {
		this.parent = nodesParent;
	}
	
	@Override
	public void execute() {
		previousPositions.clear();
		containerFrame = new JRDesignFrame();
		Rectangle frameSize = getFrameSize();
		addChild(parent, containerFrame);
		//The nodes must be ordered by their position, this because if the command is undone we 
		//can move the nodes in the same order to have their original position restored. This because
		//inserting a node into a list dosen't influence the positions of the ones above it.
		Collections.sort(nodeList, new Comparator<APropertyNode>() {

			@Override
			public int compare(APropertyNode o1, APropertyNode o2) {
				int index1 = o1.getParent().getChildren().indexOf(o1);
				int index2 = o2.getParent().getChildren().indexOf(o2);
				return index1 - index2;
			}
		});
		//Store the position informations of the ordered node
		for(APropertyNode node : nodeList){
			ANode nodeParent = node.getParent();
			previousPositions.add(new NodePreviousPosition(nodeParent.getChildren().indexOf(node), nodeParent, node));
		}
		//Move the nodes
		for(APropertyNode node : nodeList){
			ANode nodeParent = node.getParent();
			JRDesignElement movedElement = (JRDesignElement)node.getValue();
			movedElement.setX(movedElement.getX()-frameSize.x);
			movedElement.setY(movedElement.getY()-frameSize.y);
			removeChild(nodeParent, movedElement);
			addChild(containerFrame, (JRDesignElement)node.getValue());
		}
		containerFrame.setX(frameSize.x);
		containerFrame.setY(frameSize.y);
		containerFrame.setWidth(frameSize.width);
		containerFrame.setHeight(frameSize.height);
	}
	
	@Override
	public void redo() {
		//The nodes are already in the right order, need only to move them
		containerFrame = new JRDesignFrame();
		Rectangle frameSize = getFrameSize();
		addChild(parent, containerFrame);
		for(APropertyNode node : nodeList){
			JRDesignElement movedElement = (JRDesignElement)node.getValue();
			movedElement.setX(movedElement.getX()-frameSize.x);
			movedElement.setY(movedElement.getY()-frameSize.y);
			removeChild(parent, movedElement);
			addChild(containerFrame, (JRDesignElement)node.getValue());
		}
		containerFrame.setX(frameSize.x);
		containerFrame.setY(frameSize.y);
		containerFrame.setWidth(frameSize.width);
		containerFrame.setHeight(frameSize.height);
	}
	
	@Override
	public void undo() {
		for(NodePreviousPosition node : previousPositions){
			JRDesignElement movedElement = (JRDesignElement)node.movedNode.getValue();
			movedElement.setX(node.oldX);
			movedElement.setY(node.oldY);
			addChild(node.nodeParent, movedElement, node.nodeIndex);
		}
		removeChild(parent, containerFrame);
		containerFrame = null;
	}
	
	/**
	 * Return the perfect size of the frame to enclose all the nodes
	 * 
	 * @return the size of the frame
	 */
	private Rectangle getFrameSize(){
		JRDesignElement firstElement = (JRDesignElement)nodeList.get(0).getValue();
		int leftUpperY = firstElement.getY();
		int leftUpperX = firstElement.getX();
		int rightLowerY = firstElement.getY()+firstElement.getHeight();
		int rightLowerX = firstElement.getX()+firstElement.getWidth();
		for(APropertyNode node : nodeList){
			JRDesignElement element = (JRDesignElement)node.getValue();
			if (element.getY()<leftUpperY) leftUpperY = element.getY();
			if (element.getX()<leftUpperX) leftUpperX = element.getX();
			if ((element.getY()+element.getHeight())>rightLowerY) rightLowerY = (element.getY()+element.getHeight());
			if ((element.getX()+element.getWidth())>rightLowerX) rightLowerX = (element.getX()+element.getWidth());
		}
		return new Rectangle(leftUpperX, leftUpperY, rightLowerX-leftUpperX, rightLowerY-leftUpperY);
	}
	
	/**
	 * Add a child to a jr object, after the other children. The parent must be
	 * a JRDesignFrame or a JRDesignElementGroup. This is done since there
	 * is no common interface to manipulate the children of a jr container
	 * 
	 * @param jrParent the parent, it must be a JRDesignFrame or a JRDesignElementGroup
	 * @param child the child to add
	 */
	private void addChild(Object jrParent, JRDesignElement child){
		if (jrParent instanceof JRDesignFrame){
			((JRDesignFrame)jrParent).addElement(child);
		} else {
			((JRDesignElementGroup)jrParent).addElement(child);
		}
	}
	
	
	/**
	 * Add a child to a jr object into a specific index. The parent must be
	 * a Frame model or and IGroupElement model. This is done since there
	 * is no common interface to manipulate the children of a container
	 * 
	 * @param parent the parent, it must be a MFrame or a IGroupElement
	 * @param child the child to add
	 * @param index the position of the child
	 */
	private void addChild(ANode parent, JRDesignElement child, int index){
		if (parent instanceof MFrame){
			((JRDesignFrame)parent.getValue()).addElement(index, child);
		} else if (parent instanceof IGroupElement){
			((JRDesignElementGroup)((IGroupElement)parent).getJRElementGroup()).addElement(index, child);
		}
	}
	
	/**
	 * Add a child to a jr object, after the other children. The parent must be
	 * a Frame model or and IGroupElement model. This is done since there
	 * is no common interface to manipulate the children of a container
	 * 
	 * @param parent the parent, it must be a MFrame or a IGroupElement
	 * @param child the child to add
	 */
	private void addChild(ANode parent, JRDesignElement child){
		if (parent instanceof MFrame){
			((JRDesignFrame)parent.getValue()).addElement(child);
		} else if (parent instanceof IGroupElement){
			((JRDesignElementGroup)((IGroupElement)parent).getJRElementGroup()).addElement(child);
		}
	}
	
	/**
	 * Remove a child from a jr object. The parent must be
	 * a Frame model or and IGroupElement model. This is done since there
	 * is no common interface to manipulate the children of a container
	 * 
	 * @param parent the parent, it must be a MFrame or a IGroupElement
	 * @param child the child to remove
	 */
	private void removeChild(ANode parent, JRDesignElement child){
		if (parent instanceof MFrame){
			((JRDesignFrame)parent.getValue()).removeElement(child);
		} else if (parent instanceof IGroupElement){
			((JRDesignElementGroup)((IGroupElement)parent).getJRElementGroup()).removeElement(child);
		}
	}
	
	@Override
	public boolean canExecute() {
		return !nodeList.isEmpty();
	}
	
	@Override
	public boolean canUndo() {
		return containerFrame != null;
	}
	
	/**
	 * Add a node to move into the frame. The node must have the same 
	 * parent of the one used to create the command. Also the node must
	 * not be added before
	 * 
	 * @param node one of the node to move inside the frame
	 * @return true if the node was valid to be moved, false otherwise
	 */
	public boolean addNode(APropertyNode node){
		if (parent == node.getParent() && !nodeList.contains(node)){
			nodeList.add(node);
			return true;
		} else {
			return false;
		}
	}
}
