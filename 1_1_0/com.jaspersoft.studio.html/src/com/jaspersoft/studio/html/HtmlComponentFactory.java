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

package com.jaspersoft.studio.html;

import java.util.List;

import net.sf.jasperreports.components.html.HtmlComponent;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.html.command.CreateHtmlCommand;
import com.jaspersoft.studio.html.figure.HtmlFigure;
import com.jaspersoft.studio.html.model.MHtml;
import com.jaspersoft.studio.html.parts.HtmlFigureEditPart;
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

public class HtmlComponentFactory implements IComponentFactory {
	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignComponentElement) {
			if (((JRDesignComponentElement) jrObject).getComponent() instanceof HtmlComponent) {
				return new MHtml(parent, (JRDesignComponentElement) jrObject,
						newIndex);
			}
		}
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MHtml)
			return new HtmlFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {

		return null;
	}

	public IPaletteContributor getPaletteEntries() {
		PaletteContributor pc = new PaletteContributor();
		pc.add(MHtml.class);
		return pc;
	}

	public Command getCreateCommand(ANode parent, ANode child,
			Rectangle location, int newIndex) {
		if (child instanceof MHtml) {
			if (parent instanceof MElementGroup)
				return new CreateHtmlCommand((MElementGroup) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MBand)
				return new CreateHtmlCommand((MBand) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateHtmlCommand((MFrame) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MReport)
				return new CreateHtmlCommand(parent, (MGraphicElement) child,
						location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateHtmlCommand(parent, (MGraphicElement) child,
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
		if (model instanceof MHtml)
			return new HtmlFigureEditPart();
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		return null;
	}

	public AbstractVisualEditor getEditor(Object node,
			JasperReportsConfiguration jrContext) {
		return null;
	}

	public ExpressionContext getElementExpressionContext(Object jrObject) {
		// The HTML design component element has no direct
		// dataset information associated. 
		// Therefore this situation must be handled somehow by
		// the caller method(s).
		return null;
	}

}
