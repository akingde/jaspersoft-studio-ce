/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.outline;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

/*
 * The Class ReportTreeContetProvider.
 * 
 * @author Chicu Veaceslav
 */
public class ReportTreeContetProvider implements ITreeContentProvider, PropertyChangeListener {

	/** The EMPT y_ array. */
	protected static Object[] EMPTY_ARRAY = new Object[0];

	/** The viewer. */
	protected TreeViewer viewer;

	/*
	 * @see ITreeContentProvider#getChildren(Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof INode) {
			INode node = (INode) parentElement;
			List<INode> children = node.getChildren();
			if (!children.isEmpty())
				return children.toArray();
		}
		return EMPTY_ARRAY;
	}

	/*
	 * @see ITreeContentProvider#getParent(Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (element instanceof INode)
			return ((INode) element).getParent();
		return null;
	}

	/*
	 * @see ITreeContentProvider#hasChildren(Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		if (element == null)
			return false;
		return getChildren(element).length > 0;
	}

	/*
	 * @see IStructuredContentProvider#getElements(Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.
	 * Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface
	 * .viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TreeViewer) viewer;
		if (oldInput != null && newInput instanceof ANode) {
			PropertyChangeSupport pChangeSource = ((ANode) oldInput).getPropertyChangeSupport();
			pChangeSource.removePropertyChangeListener(this);
			eventSources.remove(pChangeSource);
		}
		if (newInput instanceof ANode) {
			PropertyChangeSupport pChangeSource = ((ANode) newInput).getPropertyChangeSupport();
			pChangeSource.addPropertyChangeListener(this);
			eventSources.add(pChangeSource);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		viewer.refresh(true);
	}

	/** The event sources. */
	private Set<PropertyChangeSupport> eventSources = new HashSet<>();

	/*
	 * @see IContentProvider#dispose()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
		for (PropertyChangeSupport eventSource : eventSources)
			eventSource.removePropertyChangeListener(this);
	}

}
