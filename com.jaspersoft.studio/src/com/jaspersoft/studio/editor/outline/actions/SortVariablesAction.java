/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.variable.MVariables;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Action to sort the variables on the outline
 *  
 * @author Orlandin Marco
 *
 */
public class SortVariablesAction extends AbstractFilePropertyAction {
	
	/** 
	 * The Constant ID. 
	 */
	public static final String ID = "sort_variables"; //$NON-NLS-1$
	
	/**
	 * The id of the property set on the eclipse file resource
	 */
	private static final String SORT_PROPERTY_NAME = "com.jaspersoft.studio.sortVariables";  //$NON-NLS-1$
	
	public SortVariablesAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.SortVariablesAction_common_sortalphabetically);
		setToolTipText(Messages.SortVariablesAction_2);
		setId(ID);
		setEnabled(false);
	}
	
	/**
	 * Utility  method to check if the variable are sorted by name or not
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the report
	 * @return true if they are sorted, false in any other case
	 */
	public static boolean areVariablesSorted(JasperReportsConfiguration jConfig){
		return isPropertySet(jConfig, SORT_PROPERTY_NAME);
	}
	
	/**
	 * Return the name of the property set on the eclipse file
	 */
	@Override
	protected String getPersistentPropertyName() {
		return SORT_PROPERTY_NAME;
	}
	
	@Override
	protected Command createCommand() {
		List<Object> selection = editor.getSelectionCache().getSelectionModelForType(MVariables.class);
		if (selection.size() == 1){
			final MVariables selectedVariables = (MVariables)selection.get(0);
			return generateCommand(selectedVariables);
		}
		return null;
	}
	
	/**
	 * Method used to see if the action has the checkbox present or not. In this case it check for 
	 * the presence of the property
	 */
	@Override
	public boolean isChecked() {
		List<Object> selection = editor.getSelectionCache().getSelectionModelForType(MVariables.class);
		if (selection.size() == 1){
			MVariables selectedVariables = (MVariables)selection.get(0);
			return isPropertySet(selectedVariables.getJasperConfiguration(), getPersistentPropertyName());
		}
		return false;
	}
}
