/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.beans.PropertyChangeEvent;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.outline.part.TreeEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * A generic sort action for the element in the outline view, this is implemented 
 * for example to sort the parameters and the variables. The property is set
 * at eclipse workspace level
 * 
 * @author Orlandin Marco
 */
public abstract class AbstractSortAction extends ACachedSelectionAction {

	/**
	 * Build the action as a checkbox action
	 * 
	 * @param part
	 */
	public AbstractSortAction(IWorkbenchPart part) {
		super(part, IAction.AS_CHECK_BOX);
	}
	
	/**
	 * Build the command to apply a sort on the current node. The node is used only on the refresh because
	 * the sorting is handled by the {@link TreeEditPart}, so this set the property and trigger a refresh
	 * 
	 * @param selectedNode the slected node
	 * @return a not null command
	 */
	protected Command generateCommand(ANode selectedNode){
		Command cmd = new Command() {
			
			private boolean oldValue;
			
			@Override
			public void execute() {
				IFile reportFile = selectedNode.getJasperConfiguration().getAssociatedReportFile();
				try {
					String property = reportFile.getPersistentProperty(new QualifiedName(JaspersoftStudioPlugin.getUniqueIdentifier(), getPersistentPropertyName()));
					oldValue = Boolean.parseBoolean(property);
					reportFile.setPersistentProperty(new QualifiedName(JaspersoftStudioPlugin.getUniqueIdentifier(), getPersistentPropertyName()), Boolean.toString(!oldValue));
					selectedNode.propertyChange(new PropertyChangeEvent(selectedNode, MGraphicElement.FORCE_GRAPHICAL_REFRESH, oldValue, !oldValue));
					update();
				} catch (Exception e) {
					e.printStackTrace();
					oldValue = false;
				}		
			}
			
			@Override
			public void undo() {
				IFile reportFile = selectedNode.getJasperConfiguration().getAssociatedReportFile();
				try {
					reportFile.setPersistentProperty(new QualifiedName(JaspersoftStudioPlugin.getUniqueIdentifier(), getPersistentPropertyName()), Boolean.toString(oldValue));
					selectedNode.propertyChange(new PropertyChangeEvent(selectedNode, MGraphicElement.FORCE_GRAPHICAL_REFRESH, !oldValue, oldValue));
					update();
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}

		};
		return cmd;
	}
	
	@Override
	public boolean isDirty() {
		return fresh;
	}
	
	/**
	 * This is a dynamic action since it must update the check box
	 */
	@Override
	public boolean isDynamic() {
		return true;
	}
	
	/**
	 * Return if the flag to sort the element is set to true or false for a specific property
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the file
	 * @param propertyName the name of the property, must be not null
	 * @return true if the property to sort is set to true, false in any other case
	 */
	protected static boolean areElementSorted(JasperReportsConfiguration jConfig, String propertyName){
		if (jConfig != null){
			IFile reportFile = jConfig.getAssociatedReportFile();
			try {
				String property = reportFile.getPersistentProperty(new QualifiedName(JaspersoftStudioPlugin.getUniqueIdentifier(), propertyName));
				return Boolean.parseBoolean(property);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Return the name of the property set on the file resource
	 * 
	 * @return a not null string
	 */
	protected abstract String getPersistentPropertyName();
}
