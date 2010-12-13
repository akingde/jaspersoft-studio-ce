/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.outline.editpolicy;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;

/**
 * The Class JDContainerEditPolicy.
 * 
 * @author Chicu Veaceslav
 */
public class JDContainerEditPolicy extends ContainerEditPolicy {

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ContainerEditPolicy#getOrphanChildrenCommand(org.eclipse.gef.requests.GroupRequest)
	 */
	public Command getOrphanChildrenCommand(GroupRequest request) {
		List<?> parts = request.getEditParts();
		CompoundCommand result = new CompoundCommand("orphans"); //$NON-NLS-1$
		for (int i = 0; i < parts.size(); i++) {
			result.add(OutlineTreeEditPartFactory.getOrphanCommand((ANode) getHost().getModel(), (ANode) ((EditPart) parts
					.get(i)).getModel()));

		}
		return result.unwrap();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.ContainerEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
//		Command createCommand = OutlineTreeEditPartFactory.getCreateCommand((ANode) getHost().getModel(), (ANode) request
//				.getNewObject(), request.getLocation(), -1);
//		return createCommand;
		return null;
	}

}
