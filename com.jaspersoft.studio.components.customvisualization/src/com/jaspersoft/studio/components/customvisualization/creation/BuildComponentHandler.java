/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * Command executed to compile one or more custom visualization component and 
 * generate a javascript for each of them with inside all the component informations
 * 
 * @author Orlandin Marco
 *
 */
public class BuildComponentHandler implements IHandler {

	/**
	 * Get the selected resources and return the list of build.js files 
	 * among the selected resources. Containers and sub-containers
	 * are searched to find every build.js. The returned files are unique
	 * 
	 * @return a not null list of unique IFile pointing to the found build.js files
	 */
	private List<IFile> getSelectedProjects(){
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (activeWorkbenchWindow != null && activeWorkbenchWindow.getActivePage() != null) {
		 ISelection selection = activeWorkbenchWindow.getActivePage().getSelection();
			if (selection instanceof IStructuredSelection) {
				HashSet<IFile> alreadyAdded = new HashSet<IFile>();
				for (Iterator<?> it = ((IStructuredSelection) selection).iterator(); it.hasNext();) {
					Object element = it.next();
					recursiveSearch(element, alreadyAdded);
				}
				return new ArrayList<IFile>(alreadyAdded);
			}
		}
		return new ArrayList<IFile>();
	}
	
	/**
	 * Check the element, if it is a file with name build.js is 
	 * added to the found files (if it wasen't inside before)
	 * 
	 * Otherwise if it is a folder or a project it is searched between
	 * its content
	 * 
	 * @param element the current elmenet
	 * @param paths container for the unique build.js found
	 */
	private void recursiveSearch(Object element, HashSet<IFile> paths){
		if (element instanceof IFile){
			IFile file = (IFile)element;
			if (file.getName().equals("build.js")){
				paths.add(file);
			}
		} else if (element instanceof IFolder){
			try{
				for(IResource resource : ((IFolder)element).members()){
					recursiveSearch(resource, paths);
				}
			} catch(Exception ex){
				ex.printStackTrace();
			}
		} else if (element instanceof IProject){
			try{
				for(IResource resource : ((IProject)element).members()){
					recursiveSearch(resource, paths);
				}
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Compile the selected build.js inside a job with the output on a console
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		new ConsoleExecuter(getSelectedProjects()); 
		return null;
	}
	
	//NOT USED METHODS

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}
	
}
