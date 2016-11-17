/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;



/**
 * Class to evaluate the nature of a project and check if it is a Custom Visualization Project or 
 * if the selected file is a build.js file
 * 
 * @author Orlandin Marco
 *
 */
public class CompileFileTester extends PropertyTester {

	/**
	 * Check if a project is a Custom Visualization project or a build.js file
	 * 
	 * @param receiver an IProject, if the parameter has a different type
	 * the method return false
	 * @return true if the receiver is an IProject with a Custom Visualization project nature
	 */
	public static boolean evaluateElementNature(Object receiver){
		if (receiver instanceof IFile){
			IFile file = (IFile)receiver;
			return file.getName().equals("build.js");
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
