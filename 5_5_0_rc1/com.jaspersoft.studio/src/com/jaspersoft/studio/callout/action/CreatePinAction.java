/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.callout.action;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.callout.CalloutEditPart;
import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.callout.pin.command.CreatePinCommand;

public class CreatePinAction extends SelectionAction {
	public static String ID = "com.jaspersoft.studio.callout.action.CreatePinAction";

	public CreatePinAction(IWorkbenchPart part) {
		super(part);
		setId(ID);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = createCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	@Override
	public void run() {
		execute(createCommand(getSelectedObjects()));
	}

	private Command createCommand(List<?> selectedObjects) {
		if (selectedObjects.isEmpty() || selectedObjects.size() != 1)
			return null;
		Object obj = selectedObjects.get(0);
		if (obj instanceof MCallout || obj instanceof CalloutEditPart) {
			if (obj instanceof CalloutEditPart)
				obj = ((CalloutEditPart) obj).getModel();

			MCallout mcallout = (MCallout) obj;
			Rectangle location = new Rectangle();
			location.x = 20 + (Integer) mcallout.getPropertyValue(JRDesignElement.PROPERTY_X);
			location.y = -24 + (Integer) mcallout.getPropertyValue(JRDesignElement.PROPERTY_Y);

			return new CreatePinCommand(mcallout, location);
		}
		return null;
	}
}
