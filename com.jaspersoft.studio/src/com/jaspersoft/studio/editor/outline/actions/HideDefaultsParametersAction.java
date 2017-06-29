/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.parameter.MParameters;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Action to hide the defaults parameters on the outline
 *  
 * @author Orlandin Marco
 *
 */
public class HideDefaultsParametersAction extends AbstractFilePropertyAction {
	
	/** 
	 * The Constant ID. 
	 */
	public static final String ID = "hide_default_parameters"; //$NON-NLS-1$
	
	/**
	 * The id of the property set on the eclipse file resource
	 */
	private static final String HIDE_PROPERTY_NAME = "com.jaspersoft.studio.hideParameters"; 
	
	public HideDefaultsParametersAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text.
	 */
	@Override
	protected void init() {
		super.init();
		setText("Hide Default Parameters");
		setToolTipText("Hide the default parameters");
		setId(ID);
		setEnabled(false);
	}
	
	/**
	 * Utility  method to check if the default parameters are hidden or not
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the report
	 * @return true if they are sorted, false in any other case
	 */
	public static boolean areDefaultParametersHidden(JasperReportsConfiguration jConfig){
		return isPropertySet(jConfig, HIDE_PROPERTY_NAME);
	}
	
	/**
	 * Return the name of the property set on the eclipse file
	 */
	@Override
	protected String getPersistentPropertyName() {
		return HIDE_PROPERTY_NAME;
	}
	
	@Override
	protected Command createCommand() {
		List<Object> selection = editor.getSelectionCache().getSelectionModelForType(MParameters.class);
		if (selection.size() == 1 && selection.get(0).getClass().equals(MParameters.class)){
			final MParameters<?> selectedVariables = (MParameters<?>)selection.get(0);
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
		List<Object> selection = editor.getSelectionCache().getSelectionModelForType(MParameters.class);
		if (selection.size() == 1){
			MParameters<?> selectedVariables = (MParameters<?>)selection.get(0);
			return isPropertySet(selectedVariables.getJasperConfiguration(), getPersistentPropertyName());
		}
		return false;
	}
}
