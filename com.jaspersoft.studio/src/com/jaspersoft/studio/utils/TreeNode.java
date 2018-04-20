/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Generic structure representing a tree node.
 * It also allows iteration over its children nodes.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class TreeNode<T> implements Iterable<TreeNode<T>> {

	private T data;
	private List<TreeNode<T>> children;
	private TreeNode<T> parent;

	
	/**
	 * Returns an iterator over the children nodes
	 */
	@Override
	public Iterator<TreeNode<T>> iterator() {
		if(children == null) {
			return Collections.<TreeNode<T>>emptyList().iterator();
		}
		else {
			return children.iterator();
		}
	}

	/**
	 * @return the data item wrapped by the node
	 */
	public T getData() {
		return data;
	}

	/**
	 * Sets the data to store into the wrapper node.
	 * 
	 * @param data the data item
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the list of children for this nodes
	 */
	public List<TreeNode<T>> getChildren() {
		if(children==null){
			children = new ArrayList<>();
		}
		return children;
	}
	
	/**
	 * @return the parent node for this one
	 */
	public TreeNode<T> getParent() {
		return parent;
	}

	/**
	 * Sets the parent node for this one.
	 * 
	 * @param parent the parent node
	 */
	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}
	
	/**
	 * @return <code>true</code> if the node has children,
	 * 			<code>false</code> otherwise
	 */
	public boolean hasChildren() {
		return !getChildren().isEmpty();
	}

}
