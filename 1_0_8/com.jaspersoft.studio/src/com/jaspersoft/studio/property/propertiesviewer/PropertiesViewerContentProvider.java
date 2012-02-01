/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.propertiesviewer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * The content provider for the tree viewer of the {@link TreePropertiesViewerPanel} widget.
 * The flat list of input nodes is used to create the tree structure. 
 * The attributes <code>category</code> of the {@link IPropertiesViewerNode} is used to 
 * build the hierarchy.
 * Nodes with this attribute set to <code>null</code> are roots, otherwise it represents
 * the reference id to the parent of the current node. 
 *  
 * @author mrabbi
 *
 */
public class PropertiesViewerContentProvider<T extends IPropertiesViewerNode> implements ITreeContentProvider {
	
	/* The flat list of nodes used to build the tree */
	private List<T> nodes = new ArrayList<T>();
	
	public PropertiesViewerContentProvider(List<T> allNodes){
		super();
		Assert.isNotNull(allNodes);
		nodes.addAll(allNodes);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {

		// This should never be true....
		if (!(parentElement instanceof IPropertiesViewerNode) || parentElement == null)
			return null;

		IPropertiesViewerNode parent = (IPropertiesViewerNode) parentElement;

		List<IPropertiesViewerNode> children = new ArrayList<IPropertiesViewerNode>();

		// we have to look for all the nodes having this node as parent...
		for (IPropertiesViewerNode node : nodes) {
			if (node.getCategory() != null && parent.getId().equals(node.getCategory())) {
				children.add(node);
			}
		}

		return children.toArray();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
    public Object[] getElements(Object inputElement){

		// Look for all the nodes with category set to null
		List<IPropertiesViewerNode> children = new ArrayList<IPropertiesViewerNode>();

		for (IPropertiesViewerNode node : nodes) {
			if (node.getCategory() == null) {
				children.add(node);
			}
		}

		return children.toArray();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(Object element) {
    	// This should never be true....
    	if (!(element instanceof IPropertiesViewerNode)) return null;
    	
    	// Let's find the parent node, which is the node having
    	// as ID the category of this node
    	IPropertiesViewerNode node = (IPropertiesViewerNode)element;   	
		if (node.getCategory() == null) {
			// This is a root node...
			return null;
		}
    	
		for (IPropertiesViewerNode parent : nodes) {
			if (parent.getId().equals(node.getCategory())) {
				return parent;
			}
		}
    	
    	return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }
	
    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
    }
}
