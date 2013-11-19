/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.jdt.internal.ui.javaeditor.JarEntryEditorInput;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.outline.editpolicy.ElementEditPolicy;
import com.jaspersoft.studio.editor.outline.editpolicy.ElementTreeEditPolicy;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.utils.SelectionHelper;

/*
 * The Class ATreeEditPart.
 */
public class TreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {
	
	private IResource associatedFile;
	
	@Override
	protected void addChild(EditPart child, int index) {
		if (child != null)
			super.addChild(child, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#activate()
	 */
	@Override
	public void activate() {
		super.activate();
		if (getModel() != null)
			((ANode) getModel()).getPropertyChangeSupport().addPropertyChangeListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#deactivate()
	 */
	@Override
	public void deactivate() {
		if (getModel() != null)
			((ANode) getModel()).getPropertyChangeSupport().removePropertyChangeListener(this);
		super.deactivate();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ElementTreeEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {
		if (getWidget() instanceof Tree)
			return;
		TreeItem item = (TreeItem) getWidget();
		ANode node = (ANode) getModel();
		if (node != null) {
			if (node.getImagePath() != null) {
				Image image = JaspersoftStudioPlugin.getInstance().getImage(node.getImagePath());
				if (image != null) {
					if (node.getBackground() != null)
						image.setBackground(node.getBackground());
					else {
						if (item != null && item.getParent() != null && item.getParent().getBackground() != null)
							image.setBackground(item.getParent().getBackground());
					}
					setWidgetImage(image);
				}
			}
			if (item != null) {
				if (node.getBackground() != null)
					item.setBackground(node.getBackground());
				if (node.getForeground() != null)
					item.setForeground(node.getForeground());
			}
			String displayText = node.getDisplayText();
			if (displayText != null) {
				displayText = displayText.replaceAll("(\\r|\\n)+", " ");
				if (displayText.length() > 30)
					displayText = displayText.substring(0, 30) + " ..."; //$NON-NLS-1$
				setWidgetText(displayText);
			} else
				setWidgetText("Unknown");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
	 */
	@Override
	protected List<?> getModelChildren() {
		List<Object> list = new ArrayList<Object>();
		if (getModel() != null)
			for (INode node : ((ANode) getModel()).getChildren()) {
				list.add(node);
			}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}
	
	@Override
	public Object getAdapter(Class key) {
		if (key == IResource.class || key == IFile.class) {
			if (associatedFile == null) {
				associatedFile = getAssociatedFile();
			}
			return associatedFile;
		}
		return super.getAdapter(key);
	}
	
	/**
	 * Returns the file associated.
	 * <p>
	 * Given the current edit part belonging to the active JRXML editor (report designer) the related file is returned.
	 * 
	 * @return the associated file resource
	 */
	public IResource getAssociatedFile() {
		IEditorInput edinput = null;
		if (getViewer() != null && getViewer().getEditDomain() instanceof DefaultEditDomain) {
			IEditorPart ip = ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			edinput = ip.getEditorInput();
		} else {
			IEditorPart ep = SelectionHelper.getActiveJRXMLEditor();
			if (ep != null)
				edinput = ep.getEditorInput();
		}
		if (edinput != null)
			if (edinput instanceof IFileEditorInput)
				return ((IFileEditorInput) edinput).getFile();
			else if (edinput instanceof JarEntryEditorInput)
				return null;
		return null;
	}

}
