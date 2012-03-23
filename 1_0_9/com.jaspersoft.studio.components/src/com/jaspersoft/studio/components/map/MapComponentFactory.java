/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.map;

import java.util.List;

import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.components.map.figure.MapFigure;
import com.jaspersoft.studio.components.map.model.MMap;
import com.jaspersoft.studio.components.map.model.command.CreateMapCommand;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.plugin.IComponentFactory;
import com.jaspersoft.studio.plugin.IPaletteContributor;
import com.jaspersoft.studio.plugin.PaletteContributor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class MapComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignComponentElement
				&& ((JRDesignComponentElement) jrObject).getComponent() instanceof MapComponent) {
			return new MMap(parent, (JRDesignComponentElement) jrObject,
					newIndex);
		}
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MMap)
			return new MapFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {

		return null;
	}

	public IPaletteContributor getPaletteEntries() {
		PaletteContributor pc = new PaletteContributor();
		pc.add(MMap.class);
		return pc;
	}

	public Command getCreateCommand(ANode parent, ANode child,
			Rectangle location, int newIndex) {
		if (child instanceof MMap) {
			if (parent instanceof MElementGroup)
				return new CreateMapCommand((MElementGroup) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MBand)
				return new CreateMapCommand((MBand) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateMapCommand((MFrame) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MReport)
				return new CreateMapCommand(parent, (MGraphicElement) child,
						location, newIndex);
			if (parent instanceof IGroupElement) {
				return new CreateMapCommand(parent, (MGraphicElement) child,
						location, newIndex);
			}
		}
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		return null;
	}

	public List<String> getActionsID() {
		return null;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		return null;
	}

	public AbstractVisualEditor getEditor(Object node,
			JasperReportsConfiguration jrContext) {
		return null;
	}

}
