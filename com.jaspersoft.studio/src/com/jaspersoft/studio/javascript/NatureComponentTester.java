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
package com.jaspersoft.studio.javascript;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

/**
 * Class to evaluate the nature of a project and check if it is a Custom Visualization Project
 * 
 * @author Orlandin Marco
 *
 */
public class NatureComponentTester extends PropertyTester {

	/**
	 * Check if a project is a Custom Visualization project
	 * 
	 * @param receiver an IProject, if the parameter has a different type
	 * the method return false
	 * @return true if the receiver is an IProject with a Custom Visualization project nature
	 */
	public static boolean evaluateElementNature(Object receiver){
		if (receiver instanceof IProject){
			IProject project = (IProject)receiver;  
		     try {
		    	if (project.isOpen()){
			    	if (project.hasNature(CustomComponentNature.NATURE_ID)) return true;
		    	}
				return false;
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	    return false;
	}
	

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof Collection){
			Collection<?> selection = (Collection<?>) receiver;
			boolean allRight = !selection.isEmpty();
			for (Iterator<?> it = selection.iterator(); it.hasNext() && allRight;) {
				allRight = evaluateElementNature(it.next());
			}
			return allRight;
		} else {
			return evaluateElementNature(receiver);
		}

	}

}