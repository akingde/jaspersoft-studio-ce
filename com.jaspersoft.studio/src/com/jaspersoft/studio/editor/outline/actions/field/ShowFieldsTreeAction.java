/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions.field;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.outline.actions.AbstractFilePropertyAction;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Action to sort the fields on the outline
 * 
 * @author Veaceslav Chicu
 *
 */
public class ShowFieldsTreeAction extends AbstractFilePropertyAction {

	/**
	 * The Constant ID.
	 */
	public static final String ID = "showfieldstree"; //$NON-NLS-1$

	/**
	 * The id of the property set on the eclipse file resource
	 */
	private static final String TREE_PROPERTY_NAME = "com.jaspersoft.studio.showFieldsTree"; //$NON-NLS-1$

	public ShowFieldsTreeAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text.
	 */
	@Override
	protected void init() {
		super.init();
		setText("Show Fields Tree");
		setToolTipText("Show fields as a tree");
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/transparent_icon.png"));
		setEnabled(false);
	}

	/**
	 * Utility method to check if the fields are sorted by name or not
	 * 
	 * @param jConfig
	 *            the {@link JasperReportsConfiguration} of the report
	 * @return true if they are sorted, false in any other case
	 */
	public static boolean isFieldsTree(JasperReportsConfiguration jConfig) {
		return isPropertySet(jConfig, TREE_PROPERTY_NAME);
	}

	/**
	 * Return the name of the property set on the eclipse file
	 */
	@Override
	protected String getPersistentPropertyName() {
		return TREE_PROPERTY_NAME;
	}

	@Override
	protected Command createCommand() {
		List<Object> selection = editor.getSelectionCache().getSelectionModelForType(MFields.class);
		if (selection.size() == 1 && selection.get(0).getClass().equals(MFields.class)) {
			MFields fields = (MFields) selection.get(0);
			return generateCommand(fields);
		}
		return null;
	}

	/**
	 * Method used to see if the action has the checkbox present or not. In this
	 * case it check for the presence of the property
	 */
	@Override
	public boolean isChecked() {
		List<Object> selection = editor.getSelectionCache().getSelectionModelForType(MFields.class);
		if (selection.size() == 1) {
			MFields selectedVariables = (MFields) selection.get(0);
			return isPropertySet(selectedVariables.getJasperConfiguration(), getPersistentPropertyName());
		}
		return false;
	}
}
