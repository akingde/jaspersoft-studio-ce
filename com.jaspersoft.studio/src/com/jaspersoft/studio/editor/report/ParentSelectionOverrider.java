/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.report;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.java2d.ISelectionOverrider;
import com.jaspersoft.studio.editor.java2d.JSSScrollingGraphicalViewer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.preferences.bindings.BindingsPreferencePersistence;
import com.jaspersoft.studio.preferences.bindings.JSSKeySequence;
import com.jaspersoft.studio.preferences.bindings.JSSKeyStroke;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;

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
	 * ID of the binding key associated to this action
	 */
	private static final String BINDING_KEY_ID = "com.jaspersoft.studio.editor.ParentSelectionOverrider";
	
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
	public boolean overriddenSelection(EditPart selectedPart, List<?> previousSelection, GraphicalViewer currentViewer){
		if (selectedPart != null && BindingsPreferencePersistence.isPressed(BINDING_KEY_ID)){
			Object lastItem = selectedPart.getModel();
			if (lastItem instanceof ANode){
				ANode currentNode = (ANode)lastItem;
				if (ignoreSelected) currentNode = currentNode.getParent();
				while(currentNode != null){
					if (searchedType.isAssignableFrom(currentNode.getClass())){
						EditPart part = SelectionHelper.getEditPart(currentNode);
						if (part != null){
							part.getViewer().deselectAll();
							if (part.getViewer() instanceof JSSScrollingGraphicalViewer){
								((JSSScrollingGraphicalViewer)part.getViewer()).forceSelect(part);
							} else {
								part.getViewer().select(part);
							}
							return true;
						}
					}
					currentNode = currentNode.getParent();
				}
			}
		} else if (JaspersoftStudioPlugin.getInstance().isTraceEnabled()) {
			StringBuilder message = new StringBuilder("ParentSelectOverrider-");
			message.append("Sequence Expected: ");
			message.append(BindingsPreferencePersistence.getBinding(BINDING_KEY_ID));
			List<JSSKeyStroke> pressedKeys = new ArrayList<JSSKeyStroke>();
			for(int pressedKey : JasperReportsPlugin.getPressedKeys()){
				pressedKeys.add(JSSKeyStroke.getInstance(pressedKey));
			}
			JSSKeySequence keysSequence = JSSKeySequence.getInstance(pressedKeys);
			message.append("\r\n Currently Pressed: ");
			message.append(keysSequence);
			JaspersoftStudioPlugin.getInstance().logTrace(message.toString());
		}
		return false;
	}
}
