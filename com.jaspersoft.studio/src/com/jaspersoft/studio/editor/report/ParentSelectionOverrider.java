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
package com.jaspersoft.studio.editor.report;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.SWT;

import com.jaspersoft.studio.editor.java2d.ISelectionOverrider;
import com.jaspersoft.studio.model.ANode;

/**
 * This selection overrider allow to override the current selection and instead
 * select the container parent of the element when the selection is done by pressing 
 * the SHIFT key. It allow also to specify the type of the parent searched, so the hierarchy
 * can be climbed up until the parent is found. For example inside the table it is searched
 * always the parent cell
 * 
 * @author Orlandin Marco
 *
 */
public class ParentSelectionOverrider implements ISelectionOverrider {
	
	/**
	 * The type of the searched parent
	 */
	private Class<?> searchedType;
	
	/**
	 * If the research start from the selected node or from it's parent
	 */
	private boolean ignoreSelected = false;
	
	public ParentSelectionOverrider(Class<?> searchedType, boolean ignoreSelected){
		this.searchedType = searchedType;
		this.ignoreSelected = ignoreSelected;
	}
	
	/**
	 * From the selected element will search a node of a specific type, if it is found
	 * then that node will be selected, overriding the current selection, otherwise 
	 * the selection will be not overridden
	 */
	public boolean overriddenSelection(EditPart selectedPart, GraphicalViewer currentViewer){
		if (selectedPart != null && JasperReportsPlugin.isPressed(SWT.SHIFT)){
			Object lastItem = selectedPart.getModel();
			if (lastItem instanceof ANode){
				ANode currentNode = (ANode)lastItem;
				if (ignoreSelected) currentNode = currentNode.getParent();
				while(currentNode != null){
					if (searchedType.isAssignableFrom(currentNode.getClass())){
						EditPart part = currentNode.getFigureEditPart();
						if (part != null){
							part.getViewer().deselectAll();
							part.getViewer().select(part);
							return true;
						}
					}
					currentNode = currentNode.getParent();
				}
			}
		}
		return false;
	}
}