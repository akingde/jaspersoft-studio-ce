/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Similar to an ACreateAction, but uses a {@link CompoundCommand} instead
 * of a {@link JSSCompoundCommand}, to allow to have the edit part immediatly
 * after each command and not at the end
 */
public abstract class ACreateAndSelectAction extends ACreateAction {

	public ACreateAndSelectAction(IWorkbenchPart part) {
		super(part);
	}

	protected JasperReportsConfiguration getJrConfig() {
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if (obj instanceof EditPart) {
				EditPart editPart = (EditPart) obj;
				if (editPart.getModel() instanceof ANode)
					return ((ANode) editPart.getModel()).getJasperConfiguration();
			}
		}
		return null;
	}

	@Override
	public Command createCommand() {
		List<Object> objects = getSelectedObjects();
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		CreateRequest createReq = getCreateRequest(objects);
		if (createReq == null){
			return null;
		}

		CompoundCommand jssCcmd = new CompoundCommand();		
		for (int i = 0; i < objects.size(); i++) {
			Object obj = objects.get(i);
			if (obj instanceof EditPart) {
				EditPart object = (EditPart) obj;
				//Set the node if necessary to disable the refresh
				//jssCcmd.setReferenceNodeIfNull(object.getModel());	
				Command cmd = object.getCommand(createReq);
				if (cmd != null) {
					jssCcmd.add(cmd);
				}
			}
		}
		if(!jssCcmd.isEmpty()) {
			return jssCcmd;
		}
		else {
			return null;
		}
	}
	
	@Override
	public void run() {
		super.run();
	}
}
