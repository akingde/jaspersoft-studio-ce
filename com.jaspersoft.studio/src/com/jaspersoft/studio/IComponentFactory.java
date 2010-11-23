/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.model.ANode;

public interface IComponentFactory {
	public ANode createNode(ANode parent, Object jrObject, int newIndex);

	public List<?> getChildren4Element(Object jrObject);

	public List<Class<?>> getPaletteEntries();

	public IFigure createFigure(final ANode node);
	
	public EditPart createEditPart(EditPart context, Object model);

	public Command getCreateCommand(ANode parent, ANode child, Point location, int newIndex);

	public Command getReorderCommand(ANode parent, ANode child, int newIndex);

	public Command getDeleteCommand(ANode parent, ANode child);

	public List<Action> getActions(WorkbenchPart part);

	public List<String> getActionsID();

}
