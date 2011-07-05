/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.outline.actions;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;
/*
 * The Class ACreateAction.
 */
public abstract class ACreateAction extends SelectionAction {

	/** The creation factory. */
	private CreationFactory creationFactory;

	/** The location. */
	private Point location = new Point(0, 0);

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public ACreateAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	/**
	 * Sets the creation factory.
	 * 
	 * @param creationFactory
	 *          the new creation factory
	 */
	public void setCreationFactory(CreationFactory creationFactory) {
		this.creationFactory = creationFactory;
	}

	/**
	 * Returns <code>true</code> if the selected objects can be created. Returns <code>false</code> if there are no
	 * objects selected or the selected objects are not {@link EditPart}s.
	 * 
	 * @return if the command should be enabled
	 */
	@Override
	protected boolean calculateEnabled() {
		Command cmd = createCreateCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	/**
	 * Create a command to create the selected objects.
	 * 
	 * @param objects
	 *          The objects to be deleted.
	 * @return The command to remove the selected objects.
	 */
	public Command createCreateCommand(List<?> objects) {
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		CreateRequest createReq = new CreateRequest(RequestConstants.REQ_CREATE);
		createReq.setLocation(location);
		createReq.setFactory(creationFactory);

		for (int i = 0; i < objects.size(); i++) {
			EditPart object = (EditPart) objects.get(i);
			Command cmd = object.getCommand(createReq);
			if (cmd != null)
				return cmd;
		}

		return null;
	}

	/**
	 * Performs the create action on the selected objects.
	 */
	public void run() {
		execute(createCreateCommand(getSelectedObjects()));
	}

}
