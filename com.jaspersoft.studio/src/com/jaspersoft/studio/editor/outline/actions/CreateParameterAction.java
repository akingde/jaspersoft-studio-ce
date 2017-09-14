/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;
import java.util.Map;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.editor.gef.util.CreateRequestUtil;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameters;

/*
 * The Class CreateParameterAction.
 */
public class CreateParameterAction extends ACreateAndSelectAction {

	/** The Constant ID. */
	public static final String ID = "create_parameter"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public CreateParameterAction(IWorkbenchPart part) {
		super(part);
		setCreationFactory(new JDPaletteCreationFactory(MParameter.class));
	}

	@Override
	protected boolean calculateEnabled() {
		if (!checkSingleSelectedObject(MParameters.class) && !checkSingleSelectedObject(MParameter.class)) {
			return false;
		}
		return super.calculateEnabled();
	}

	protected boolean setExtendedData(Map<Object, Object> map, List<?> objects) {
		if (objects.size() == 1) {
			EditPart part = (EditPart) objects.get(0);
			if (part.getModel() instanceof MParameter) {
				MParameter selectedField = (MParameter) part.getModel();
				ANode parent = selectedField.getParent();
				if (parent != null) {
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
		setText(Messages.CreateParameterAction_create_parameter);
		setToolTipText(Messages.CreateParameterAction_create_parameter_tool_tip);
		setId(CreateParameterAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}

}
