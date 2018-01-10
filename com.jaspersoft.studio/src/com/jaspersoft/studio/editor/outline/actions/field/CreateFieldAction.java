/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions.field;

import java.util.List;
import java.util.Map;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.editor.gef.util.CreateRequestUtil;
import com.jaspersoft.studio.editor.outline.actions.ACreateAndSelectAction;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;

/*
 * The Class CreateFieldAction.
 */
public class CreateFieldAction extends ACreateAndSelectAction {

	/** The Constant ID. */
	public static final String ID = "create_field"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public CreateFieldAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
		setCreationFactory(new JDPaletteCreationFactory(MField.class));
	}

	@Override
	protected boolean calculateEnabled() {
		if (!checkSingleSelectedObject(MFields.class) && !checkSingleSelectedObject(MField.class)
				&& !ShowFieldsTreeAction.isFieldsTree(getJrConfig())) {
			return false;
		}
		// we don't know when fields tree change, we could listen for preference change,
		// or in case we decide to store a property in dataset, we can optimize a bit
		// that
		fresh = false;
		return super.calculateEnabled();
	}

	@Override
	protected boolean setExtendedData(Map<Object, Object> map, List<?> objects) {
		if (objects.size() == 1) {
			EditPart part = (EditPart) objects.get(0);
			if (part.getModel() instanceof MField) {
				MField selectedField = (MField) part.getModel();
				ANode parent = selectedField.getParent();
				if (parent != null && parent.getChildren() != null) {
					int index = parent.getChildren().indexOf(selectedField);
					map.put(CreateRequestUtil.NEWINDEX, index + 1);
				}
			}
		}
		return true;
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateFieldAction_create_field);
		setToolTipText(Messages.CreateFieldAction_create_field_tool_tip);
		setId(CreateFieldAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}

}
