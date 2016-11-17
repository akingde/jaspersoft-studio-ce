/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;

/**
 * Class to evaluate the nature of a project and check if it is a Custom Visualization Project or 
 * if the selected file is a build.js file
 * 
 * @author Orlandin Marco
 *
 */
public class CompileAllTester extends PropertyTester {

	/**
	 * Check if a project is a Custom Visualization project or a build.js file
	 * 
	 * @param receiver an IProject, if the parameter has a different type
	 * the method return false
	 * @return true if the receiver is an IProject with a Custom Visualization project nature
	 */
	public static boolean evaluateElementNature(Object receiver){
		try{
			if (receiver instanceof IFolder){
				IFolder container = (IFolder)receiver;
				for (IResource resource : container.members()){
					boolean value = evaluateElementNature(resource);
					if (value) return true;
				}
			} else if (receiver instanceof IProject){
				IProject container = (IProject)receiver;
				for (IResource resource : container.members()){
					boolean value = evaluateElementNature(resource);
					if (value) return true;
				}
			} else if (receiver instanceof IFile){
				return ((IFile)receiver).getName().equals("build.js");
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
	  return false;
	}
	

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof Collection){
			Collection<?> selection = (Collection<?>) receiver;
			for (Iterator<?> it = selection.iterator(); it.hasNext();) {
				Object element = it.next();
				if (element instanceof IContainer && evaluateElementNature(element)){
					 return true;
				}
			}
			return false;
		} else {
			return (receiver instanceof IContainer && evaluateElementNature(receiver));
		}

	}

}
