/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFieldsContainer;
import com.jaspersoft.studio.model.field.command.CreateFieldsContainerCommand;

/*
 * The Class CreateFieldAction.
 */
public class CreateFieldsContainerAction extends ACreateAndSelectAction {

	/** The Constant ID. */
	public static final String ID = "create_fieldscontainer"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public CreateFieldsContainerAction(IWorkbenchPart part) {
		super(part);
		setCreationFactory(new JDPaletteCreationFactory(MFieldsContainer.class));
	}

	@Override
	protected boolean calculateEnabled() {
		if (checkAllSelectedObjects(MField.class, MFieldsContainer.class))
			return super.calculateEnabled();
		return false;
	}

	@Override
	public Command createCommand() {
		List<Object> objects = getSelectedObjects();
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;
		if (objects.size() > 1) {
			List<ANode> nodes = new ArrayList<ANode>();
			for (Object obj : objects)
				if (obj instanceof EditPart)
					nodes.add((ANode) ((EditPart) obj).getModel());
			return new CreateFieldsContainerCommand(nodes);
		}
		return super.createCommand();
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText("Create Fields Group");
		setToolTipText("Put fields under same group.");
		setId(CreateFieldsContainerAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}

}
