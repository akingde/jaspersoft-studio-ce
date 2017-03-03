/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.image;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.action.IGlobalAction;
import com.jaspersoft.studio.jface.dialogs.ImageSelectionDialog;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.image.MImage;

public class ChangeImageExpression extends ACachedSelectionAction implements IGlobalAction {

	private MImage imageModel;

	public final static String ID = "ActionImageChangeExpression";

	public ChangeImageExpression(IWorkbenchPart part) {
		super(part);
		setId(ID);
		setText(Messages.ImageContributionItem_actionName);
		setToolTipText(Messages.ImageContributionItem_actionName);
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/blue-folder-open-image.png"));
		loadImageModel();
	}

	private void loadImageModel() {
		imageModel = null;
		List<Object> images = editor.getSelectionCache().getSelectionModelForType(MImage.class);
		if (!images.isEmpty()) {
			imageModel = (MImage) images.get(0);
		}
	}

	@Override
	protected boolean calculateEnabled() {
		return (imageModel != null);
	}

	@Override
	protected void setSelection(ISelection selection) {
		super.setSelection(selection);
		loadImageModel();
	}

	public static void setImageExpression(MImage imageModel) {
		if (imageModel != null) {
			ImageSelectionDialog d = new ImageSelectionDialog(UIUtils.getShell());
			JRDesignExpression expr = (JRDesignExpression) imageModel.getPropertyValue(JRDesignImage.PROPERTY_EXPRESSION);
			if (expr != null)
				d.setFileExpressionText(expr.getText());
			d.configureDialog(imageModel.getJasperConfiguration());
			if (d.open() == Window.OK) {
				JRDesignExpression imageExpression = d.getFileExpression();
				if (imageExpression == null) {
					// No image selected => remove property
					imageModel.setPropertyValue(JRDesignImage.PROPERTY_EXPRESSION, "");
				} else {
					imageModel.setPropertyValue(JRDesignImage.PROPERTY_EXPRESSION, imageExpression.getText());
				}
				JSSCompoundCommand.forceRefreshVisuals(imageModel);
			}
		}
	}

	@Override
	public void run() {
		setImageExpression(imageModel);
	}

}
