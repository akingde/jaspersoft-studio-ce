/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.report.CachedSelectionProvider;
import com.jaspersoft.studio.editor.report.CommonSelectionCacheProvider;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

import net.sf.jasperreports.engine.JRChild;

/**
 * 
 * Abstract class to implement to define a selection action that caches
 * the command and the selection set to provide better performance
 *
 */
public abstract class ACachedSelectionAction extends SetWorkbenchAction {

	protected boolean fresh = false;
	protected boolean freshChecked = false;
	
	/**
	 * The cached command
	 */
	protected Command command;
	
	protected boolean ischecked = false;
	
	/**
	 * Editor that provide the common selection provider, class that cache the actual
	 * selection and all the request for a subselection and stuff
	 */
	protected CachedSelectionProvider editor = null;
	
	public ACachedSelectionAction(IWorkbenchPart part) {
		super(part);
		editor = (CachedSelectionProvider) part;
	}

	public ACachedSelectionAction(IWorkbenchPart part, int style) {
		super(part, style);
		editor = (CachedSelectionProvider) part;
	}

	@Override
	protected void handleSelectionChanged() {
		fresh = false;
		freshChecked = false;
		if (editor != null){
			editor.getSelectionCache().selectionChanged(getSelection());
		}
		super.handleSelectionChanged();
	}

	@Override
	public void run() {
		//If the command was not created yet refresh it
		if (!fresh)
			command = createCommand();
		fresh = true;
		if (fresh && command != null){
			execute(command);
		}
	}

	@Override
	protected boolean calculateEnabled() {
		if (!fresh)
			command = createCommand();
		fresh = true;
		return command != null && command.canExecute();
	}

	protected Command createCommand() {
		List<Object> mGraphElements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		for(Object obj : mGraphElements){
			JSSCompoundCommand cmd = new JSSCompoundCommand((ANode)obj);
			cmd.add(new SetValueCommand());
			return cmd;
		}
		return null;
	}
	
	/**
	 * Verifies that there is only one EditPart selected
	 * referring to a model object of the allowed class types.
	 * 
	 * @param classes the allowed type(s) for the model object 
	 * @return <code>true</code> the single model object is instance of
	 * 					one of the allowed types,<code>false</code> otherwise
	 */
	public boolean checkSingleSelectedObject(Class<?>...classes) {
		if (getSelectedObjects().size() != 1) return false;
		CommonSelectionCacheProvider cache = editor.getSelectionCache();
		for (Class<?> clazz : classes) {
			if (!cache.getSelectionModelForType(clazz).isEmpty())
				return true;
		}
		return false;
	}
	
	/**
	 * Verifies that all the currently selected objects are EditParts
	 * referring to model objects of the allowed class types.
	 * 
	 * @param classes the allowed type(s) for the model objects 
	 * @return <code>true</code> all model objects are instances of 
	 * 					one of the allowed types,<code>false</code> otherwise
	 */
	public boolean checkAllSelectedObjects(Class<?> searchedClass){
		List<Object> elements = editor.getSelectionCache().getSelectionModelForType(searchedClass);
		return (!elements.isEmpty() && elements.size() == getSelectedObjects().size());
	}
	
	@Override
	public void setWorkbenchPart(IWorkbenchPart part) {
		super.setWorkbenchPart(part);
		if (part instanceof CachedSelectionProvider){
			fresh = false;
			editor = (CachedSelectionProvider) part;
		}
	}
	
	/**
	 * Set the selection on the last children of the parent of the current selected part
	 */
	protected void setSelectionOnLastSibling(){
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if (obj instanceof EditPart) {
				EditPart editPart = (EditPart) obj;
				EditPart parentPart = editPart.getParent();
				List<?> children = parentPart.getChildren();
				if (children != null && !children.isEmpty()) {
					int last = children.size() - 1;
					StructuredSelection newselection = new StructuredSelection(children.get(last));
					setSelection(newselection);
					getWorkbenchPart().getSite().getSelectionProvider().setSelection(newselection);
				}
			}
		}
	}
	
	/**
	 * Set the selection on a specified children of the parent 
	 * of the selected editpart
	 * 
	 * @param the value of the model node of the edit part to select
	 */
	protected void setSelectionOnSiblingElement(JRChild element){
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if (obj instanceof EditPart) {
				EditPart editPart = (EditPart) obj;
				EditPart parentPart = editPart.getParent();
				List<?> children = parentPart.getChildren();
				if (children != null) {
					for(Object child : children){
						EditPart childPart = (EditPart)child;
						if (childPart.getModel() != null && ((INode)childPart.getModel()).getValue() == element){
							StructuredSelection newselection = new StructuredSelection(childPart);
							setSelection(newselection);
							getWorkbenchPart().getSite().getSelectionProvider().setSelection(newselection);
							break;
						}
					}
				}
			}
		}
	}
}
