/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.jface.dialogs.StyleTemplateSelectionDialog;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.model.style.command.CreateStyleTemplateCommand;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;

/*
 * The Class CreateStyleTemplateAction.
 */
public class CreateStyleTemplateAction extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "create_style_template"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public CreateStyleTemplateAction(IWorkbenchPart part) {
		super(part);
	}
	
	@Override
	protected boolean calculateEnabled() {
		return checkSingleSelectedObject(MStyles.class);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateStyleTemplateAction_create_style_template);
		setToolTipText(Messages.CreateStyleTemplateAction_create_style_template_tool_tip);
		setId(CreateStyleTemplateAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}
	
	@Override
	public void run() {
		MStyles node = (MStyles)editor.getSelectionCache().getSelectionModelForType(MStyles.class).get(0);
		JasperReportsConfiguration jConfig = node.getJasperConfiguration();
		StyleTemplateSelectionDialog fsd = new StyleTemplateSelectionDialog(UIUtils.getShell());
		fsd.configureDialog(jConfig);
		if (fsd.open() == Dialog.OK) { 
			JRDesignReportTemplate jrTemplate = MStyleTemplate.createJRTemplate();
			jrTemplate.setSourceExpression(fsd.getFileExpression());
			IFile project = (IFile) jConfig.get(FileUtils.KEY_FILE);
			String location = ExternalStylesManager.evaluateStyleExpression(jrTemplate, project, jConfig);
			if (location != null && (!fsd.isValidationAllowed() || ExternalStylesManager.validateTemplate(jConfig, location))){
				//Check if the template is valid and add it only in that case
				CreateStyleTemplateCommand command = new CreateStyleTemplateCommand(node, jrTemplate, 0);
				execute(command);
				jConfig.refreshCachedStyles();
			} else {
				UIUtils.showWarning("The selected resource is not a valid template style");
			}
		}
		
		
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if (obj instanceof EditPart) {
				EditPart editPart = (EditPart) obj;
				List<?> children = editPart.getParent().getChildren();
				if (children != null && !children.isEmpty()) {
					int last = 0;
					StructuredSelection newselection = new StructuredSelection(children.get(last));
					setSelection(newselection);
					getWorkbenchPart().getSite().getSelectionProvider().setSelection(newselection);
				}
			}
		}
	}
}
