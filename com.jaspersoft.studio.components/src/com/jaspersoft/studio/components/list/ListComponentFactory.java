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
package com.jaspersoft.studio.components.list;

import java.util.List;

import net.sf.jasperreports.components.list.StandardListComponent;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.components.list.editor.ListEditor;
import com.jaspersoft.studio.components.list.figure.ListFigure;
import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.components.list.model.command.CreateListCommand;
import com.jaspersoft.studio.components.list.part.ListEditPart;
import com.jaspersoft.studio.components.list.part.ListPageEditPart;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.plugin.IComponentFactory;
import com.jaspersoft.studio.plugin.IPaletteContributor;
import com.jaspersoft.studio.plugin.PaletteContributor;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ListComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignComponentElement
				&& ((JRDesignComponentElement) jrObject).getComponent() instanceof StandardListComponent) {
			StandardListComponent list = (StandardListComponent) ((JRDesignComponentElement) jrObject)
					.getComponent();
			MList mlist = new MList(parent,
					(JRDesignComponentElement) jrObject, newIndex);
			if (mlist.getParent() instanceof MPage) {
				ReportFactory.createElementsForBand(mlist, list.getContents()
						.getChildren());
			}
			return mlist;
		}
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MList)
			return new ListFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {
		// if (jrObject instanceof JRComponentElement
		// && ((JRDesignComponentElement) jrObject).getComponent() instanceof
		// StandardListComponent) {
		// StandardListComponent slc = (StandardListComponent)
		// ((JRDesignComponentElement) jrObject).getComponent();
		// ListContents lc = slc.getContents();
		// if (lc != null)
		// return lc.getChildren();
		// }
		return null;
	}

	public IPaletteContributor getPaletteEntries() {
		PaletteContributor pc = new PaletteContributor();
		// pc.add(IPaletteContributor.KEY_COMMON_CONTAINER, MList.class);
		pc.add(MList.class);
		return pc;
	}

	public Command getCreateCommand(ANode parent, ANode child,
			Rectangle location, int newIndex) {
		if (child instanceof MList) {
			if (parent instanceof MElementGroup)
				return new CreateListCommand((MElementGroup) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MBand)
				return new CreateListCommand((MBand) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateListCommand((MFrame) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MReport)
				return new CreateListCommand(parent, (MGraphicElement) child,
						location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateListCommand(parent, (MGraphicElement) child,
						location, newIndex);
			}
		}
		if (child instanceof MGraphicElement && child.getValue() != null)
			if (parent instanceof MList)
				return new com.jaspersoft.studio.components.list.commands.element.CreateElementCommand(
						(MList) parent, (MGraphicElement) child, location,
						newIndex);

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
		if (model instanceof MRoot) {
			ANode n = ModelUtils.getFirstChild((MRoot) model);
			if (n != null && n instanceof MPage) {
				n = ModelUtils.getFirstChild(n);
				if (n != null && n instanceof MList)
					return new ListPageEditPart();
			}
		}
		if (model instanceof MList)
			return new ListEditPart();
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		return null;
	}

	public AbstractVisualEditor getEditor(Object node,
			JasperReportsConfiguration jrContext) {
		if (node != null && node instanceof JRDesignComponentElement) {
			Component component = ((JRDesignComponentElement) node)
					.getComponent();
			if (component != null && component instanceof StandardListComponent)
				return new ListEditor(jrContext);
		}
		return null;
	}

	public ExpressionContext getElementExpressionContext(Object jrObject) {
		if (jrObject instanceof MList
				&& ((MList) jrObject).getValue() instanceof JRDesignComponentElement) {
			MList mlist = (MList) jrObject;
			StandardListComponent listComponent = (StandardListComponent) mlist
					.getValue().getComponent();
			JRDesignDataset designDS = ModelUtils
					.getDesignDatasetForDatasetRun(mlist
							.getJasperConfiguration().getJasperDesign(),
							listComponent.getDatasetRun());
			return new ExpressionContext(designDS,
					mlist.getJasperConfiguration());
		}

		return null;
	}
}
