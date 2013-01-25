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
package com.jaspersoft.studio.doc.handlers;

import java.util.Iterator;
import java.util.List;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.repository.RepositoryView;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * Utility class for the cheatsheets actions, it provide various methods to get reference to 
 * the active report
 * 
 * @author Orlandin Marco
 *
 */
public class HandlersUtil {
	
	/**
	 * Return an iterator to the first children of a list
	 * @param children the list of chldren
	 * @return an iterator to the first children, or null if the reference to the list is null
	 */
	private static Iterator getFirstChildrendIterator(List children){
		if (children != null) return children.iterator();
		return null;
	}
	
	/**
	 * Return the root node of the report
	 * 
	 * @return the root node of the report, tipically an MRoot, it can be null
	 */
	public static INode getRootNode(){
		IEditorPart activeJRXMLEditor = SelectionHelper.getActiveJRXMLEditor();
		if (activeJRXMLEditor != null && activeJRXMLEditor instanceof JrxmlEditor) {
			INode root = ((JrxmlEditor) activeJRXMLEditor).getModel();
			return root;
		}
		return null;
	}
	
	/**
	 * Return the root element of the report
	 * 
	 * @return an MReport or an MPage, depends from the context. it can be null
	 */
	public static APropertyNode getRootElement(){
		INode root = getRootNode();
		if (root == null) return null;
		List children = root.getChildren();
	 	Iterator it = getFirstChildrendIterator(children);
	 	while(it != null && it.hasNext()){
	 		INode actualPart = (INode)it.next();
	 		if (actualPart instanceof MReport || actualPart instanceof MPage) return (APropertyNode)actualPart;;
	 		if (actualPart instanceof MRoot) it = getFirstChildrendIterator(actualPart.getChildren());
	 	}
	 	return null;
	}
	
	public static RepositoryView getRepositoryView(){
		return (RepositoryView)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("com.jaspersoft.studio.Repository");
	}
	
}
