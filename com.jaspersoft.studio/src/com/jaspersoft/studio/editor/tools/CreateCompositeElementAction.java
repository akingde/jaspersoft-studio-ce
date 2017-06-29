/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.tools.wizards.CompositeElementDefinitionWizard;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDatasetContainer;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Action to create a new composite element starting from the selected elements
 * 
 * @author Orlandin Marco
 * 
 */
public class CreateCompositeElementAction extends ACachedSelectionAction {
  
  public static final String ID = "CreateCompositeElementAction"; //$NON-NLS-1$
	
	public CreateCompositeElementAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}
	
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateToolAction_actionName);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/custom-tool.png")); //$NON-NLS-1$
		setEnabled(false);
	}

	private boolean hasDataset(List<?> elements){
		for(Object obj : elements){
			if (obj instanceof IDatasetContainer) return true;
			ANode node = (ANode)obj;
			boolean checkContent = hasDataset(node.getChildren());
			if (checkContent) return true;
		}
		return false;
	}
	
	/**
	 * The action is available if there are at least one graphical element selected but no
	 * elements that uses datasets, since the composite elements support only base elements
	 */
	@Override
	protected boolean calculateEnabled() {
		List<Object> mGraphElements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		if (mGraphElements.isEmpty()) return false;
		return !hasDataset(mGraphElements);
	}
	
	@Override
	public void run() {
		List<Object> mGraphElements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), new CompositeElementDefinitionWizard(mGraphElements));
		if (dialog.open() == Dialog.OK){
			UIUtils.showInformation(Messages.CreateToolAction_successMessage);
		}
	}

}
