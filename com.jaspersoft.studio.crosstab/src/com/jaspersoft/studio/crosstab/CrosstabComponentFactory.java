/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.JRCrosstabParameter;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.IComponentFactory;
import com.jaspersoft.studio.crosstab.editor.CrosstabEditor;
import com.jaspersoft.studio.crosstab.figure.CellFigure;
import com.jaspersoft.studio.crosstab.figure.CrosstabFigure;
import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.crosstab.model.MCrosstab;
import com.jaspersoft.studio.crosstab.model.cell.MCell;
import com.jaspersoft.studio.crosstab.model.cell.command.CreateElementCommand;
import com.jaspersoft.studio.crosstab.model.cell.command.CreateElementGroupCommand;
import com.jaspersoft.studio.crosstab.model.cell.command.DeleteElementCommand;
import com.jaspersoft.studio.crosstab.model.cell.command.DeleteElementGroupCommand;
import com.jaspersoft.studio.crosstab.model.cell.command.OrphanElementCommand;
import com.jaspersoft.studio.crosstab.model.cell.command.OrphanElementGroupCommand;
import com.jaspersoft.studio.crosstab.model.cell.command.ReorderElementCommand;
import com.jaspersoft.studio.crosstab.model.cell.command.ReorderElementGroupCommand;
import com.jaspersoft.studio.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.crosstab.model.columngroup.MColumnGroups;
import com.jaspersoft.studio.crosstab.model.columngroup.action.CreateColumnGroupAction;
import com.jaspersoft.studio.crosstab.model.columngroup.command.CreateColumnGroupCommand;
import com.jaspersoft.studio.crosstab.model.columngroup.command.DeleteColumnGroupCommand;
import com.jaspersoft.studio.crosstab.model.columngroup.command.ReorderColumnGroupCommand;
import com.jaspersoft.studio.crosstab.model.crosstab.command.CreateCrosstabCommand;
import com.jaspersoft.studio.crosstab.model.header.MCrosstabHeader;
import com.jaspersoft.studio.crosstab.model.header.MCrosstabHeaderCell;
import com.jaspersoft.studio.crosstab.model.header.action.CreateCrosstabHeaderAction;
import com.jaspersoft.studio.crosstab.model.header.command.CreateCrosstabHeaderCommand;
import com.jaspersoft.studio.crosstab.model.header.command.DeleteCrosstabHeaderCommand;
import com.jaspersoft.studio.crosstab.model.measure.MMeasure;
import com.jaspersoft.studio.crosstab.model.measure.MMeasures;
import com.jaspersoft.studio.crosstab.model.measure.action.CreateMeasureAction;
import com.jaspersoft.studio.crosstab.model.measure.command.CreateMeasureCommand;
import com.jaspersoft.studio.crosstab.model.measure.command.DeleteMeasureCommand;
import com.jaspersoft.studio.crosstab.model.measure.command.ReorderMeasureCommand;
import com.jaspersoft.studio.crosstab.model.nodata.MCrosstabWhenNoData;
import com.jaspersoft.studio.crosstab.model.nodata.MCrosstabWhenNoDataCell;
import com.jaspersoft.studio.crosstab.model.nodata.action.CreateCrosstabWhenNoDataAction;
import com.jaspersoft.studio.crosstab.model.nodata.command.CreateCrosstabWhenNoDataCommand;
import com.jaspersoft.studio.crosstab.model.nodata.command.DeleteCrosstabWhenNoDataCommand;
import com.jaspersoft.studio.crosstab.model.parameter.MCrosstabParameters;
import com.jaspersoft.studio.crosstab.model.parameter.command.CreateParameterCommand;
import com.jaspersoft.studio.crosstab.model.parameter.command.DeleteParameterCommand;
import com.jaspersoft.studio.crosstab.model.parameter.command.ReorderParameterCommand;
import com.jaspersoft.studio.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.crosstab.model.rowgroup.MRowGroups;
import com.jaspersoft.studio.crosstab.model.rowgroup.action.CreateRowGroupAction;
import com.jaspersoft.studio.crosstab.model.rowgroup.command.CreateRowGroupCommand;
import com.jaspersoft.studio.crosstab.model.rowgroup.command.DeleteRowGroupCommand;
import com.jaspersoft.studio.crosstab.model.rowgroup.command.ReorderRowGroupCommand;
import com.jaspersoft.studio.crosstab.part.CrosstabCellEditPart;
import com.jaspersoft.studio.crosstab.part.CrosstabEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.util.ModelUtil;
import com.jaspersoft.studio.model.util.ReportFactory;

public class CrosstabComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignCrosstab) {
			JRDesignCrosstab ct = (JRDesignCrosstab) jrObject;
			ct.preprocess();
			CrosstabManager ctManager = new CrosstabManager(ct);
			MCrosstab mCrosstab = new MCrosstab(parent, ct, newIndex, ctManager);
			if (parent instanceof MPage) {
				MCrosstabParameters mp = new MCrosstabParameters(mCrosstab, ct, JRDesignCrosstab.PROPERTY_PARAMETERS);
				if (ct.getParameters() != null)
					for (JRCrosstabParameter p : ct.getParameters())
						ReportFactory.createNode(mp, p, -1);

				MRowGroups mrg = new MRowGroups(mCrosstab, ct, JRDesignCrosstab.PROPERTY_ROW_GROUPS);
				if (ct.getRowGroups() != null)
					for (JRCrosstabRowGroup p : ct.getRowGroups())
						ReportFactory.createNode(mrg, p, -1);

				MColumnGroups mcg = new MColumnGroups(mCrosstab, ct, JRDesignCrosstab.PROPERTY_COLUMN_GROUPS);
				if (ct.getColumnGroups() != null)
					for (JRCrosstabColumnGroup p : ct.getColumnGroups())
						ReportFactory.createNode(mcg, p, -1);

				MMeasures mm = new MMeasures(mCrosstab, ct, JRDesignCrosstab.PROPERTY_MEASURES);
				if (ct.getMeasures() != null)
					for (JRCrosstabMeasure p : ct.getMeasures())
						ReportFactory.createNode(mm, p, -1);
				// ---------------------------------
				createCellNodes(ct, mCrosstab);
			}
			return mCrosstab;
		}
		if (jrObject instanceof JRCrosstabRowGroup) {

			return createRowGroup(parent, (JRCrosstabRowGroup) jrObject, newIndex);
		}
		if (jrObject instanceof JRCrosstabColumnGroup) {
			return createColumnGroup(parent, (JRCrosstabColumnGroup) jrObject, newIndex);
		}
		if (jrObject instanceof JRCrosstabMeasure) {
			return new MMeasure(parent, (JRCrosstabMeasure) jrObject, newIndex);
		}
		return null;
	}

	private ANode createColumnGroup(ANode mcg, JRCrosstabColumnGroup p, int newIndex) {
		MColumnGroup rg = new MColumnGroup(mcg, p, newIndex);

		createColumnGroupCells(rg, p);
		return rg;
	}

	public static void createColumnGroupCells(MColumnGroup rg, JRCrosstabColumnGroup p) {
		MCell mc = new MCell(rg, p.getHeader(), Messages.CrosstabComponentFactory_header + p.getName());
		ReportFactory.createElementsForBand(mc, p.getHeader().getChildren());

		if (!p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.NONE)) {
			mc = new MCell(rg, p.getTotalHeader(), Messages.CrosstabComponentFactory_total + p.getName());
			ReportFactory.createElementsForBand(mc, p.getTotalHeader().getChildren());
		}
	}

	private ANode createRowGroup(ANode mrg, JRCrosstabRowGroup p, int newIndex) {
		MRowGroup rg = new MRowGroup(mrg, p, newIndex);

		createRowGroupCells(rg, p);
		return rg;
	}

	public static void createRowGroupCells(MRowGroup rg, JRCrosstabRowGroup p) {
		MCell mc = new MCell(rg, p.getHeader(), Messages.CrosstabComponentFactory_header + p.getName());
		ReportFactory.createElementsForBand(mc, p.getHeader().getChildren());

		if (!p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.NONE)) {
			mc = new MCell(rg, p.getTotalHeader(), Messages.CrosstabComponentFactory_total + p.getName());
			ReportFactory.createElementsForBand(mc, p.getTotalHeader().getChildren());
		}
	}

	public static void createCellNodes(JRDesignCrosstab ct, MCrosstab mCrosstab) {
		if (ct.getHeaderCell() == null) {
			new MCrosstabHeader(mCrosstab, -1);
		} else {
			MCrosstabHeaderCell mc = new MCrosstabHeaderCell(mCrosstab, ct.getHeaderCell(), -1);
			ReportFactory.createElementsForBand(mc, ct.getHeaderCell().getChildren());
		}

		for (Iterator<?> it = ct.getCellsList().iterator(); it.hasNext();) {
			JRDesignCrosstabCell c = (JRDesignCrosstabCell) it.next();

			boolean hide = false;
			String colname = c.getColumnTotalGroup();
			if (colname == null)
				colname = Messages.CrosstabComponentFactory_detail;
			else
				hide = isColumnGroupTotal(ct, colname);

			String rowname = c.getRowTotalGroup();
			if (rowname == null)
				rowname = Messages.CrosstabComponentFactory_detail;
			else
				hide = hide || isRowGroupTotal(ct, rowname);

			if (!hide) {
				MCell mc = new MCell(mCrosstab, c.getContents(), colname + "/" + rowname); //$NON-NLS-1$
				ReportFactory.createElementsForBand(mc, c.getContents().getChildren());
			}
		}
		if (ct.getWhenNoDataCell() == null) {
			new MCrosstabWhenNoData(mCrosstab, -1);
		} else {
			MCrosstabWhenNoDataCell mc = new MCrosstabWhenNoDataCell(mCrosstab, ct.getWhenNoDataCell(), -1);
			ReportFactory.createElementsForBand(mc, ct.getWhenNoDataCell().getChildren());
		}
		mCrosstab.getCrosstabManager().refresh();
	}

	private static boolean isRowGroupTotal(JRDesignCrosstab ct, String rowname) {
		for (Object obj : ct.getRowGroupsList()) {
			JRCrosstabRowGroup rg = (JRCrosstabRowGroup) obj;
			if (rg.getName().equals(rowname))
				return rg.getTotalPositionValue().equals(CrosstabTotalPositionEnum.NONE);
		}
		return false;
	}

	private static boolean isColumnGroupTotal(JRDesignCrosstab ct, String colname) {
		for (Object obj : ct.getColumnGroupsList()) {
			JRCrosstabColumnGroup rg = (JRCrosstabColumnGroup) obj;
			if (rg.getName().equals(colname))
				return rg.getTotalPositionValue().equals(CrosstabTotalPositionEnum.NONE);
		}
		return false;
	}

	public static void deleteCellNodes(MCrosstab mCrosstab) {
		List<INode> nodes = new ArrayList<INode>();
		for (INode n : mCrosstab.getChildren()) {
			if (n instanceof MCell || n instanceof MCrosstabWhenNoData || n instanceof MCrosstabHeader)
				nodes.add(n);
		}
		mCrosstab.removeChildren(nodes);
		mCrosstab.getCrosstabManager().refresh();
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MCrosstab)
			return new CrosstabFigure();
		if (node instanceof MCell)
			return new CellFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {
		if (jrObject instanceof JRCrosstab) {
			// JRCrosstab ct = (JRCrosstab) jrObject;
			List<Object> lst = new ArrayList<Object>();

			// lst.add(ct.getParameters());
			// lst.add(ct.getRowGroups());
			// lst.add(ct.getColumnGroups());
			// lst.add(ct.getMeasures());

			// lst.add(Arrays.asList(ct.getHeaderCell()));

			return lst;
		}
		return null;
	}

	public List<Class<?>> getPaletteEntries() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MCrosstab.class);
		return list;
	}

	public Command getCreateCommand(ANode parent, ANode child, Rectangle location, int newIndex) {
		if (child instanceof MParameter) {
			if (parent instanceof MCrosstabParameters)
				return new CreateParameterCommand((MCrosstabParameters) parent, (MParameter) child, newIndex);
		}
		if (child instanceof MMeasure) {
			if (parent instanceof MMeasures)
				return new CreateMeasureCommand((MMeasures) parent, (MMeasure) child, newIndex);
		}
		if (child instanceof MColumnGroup) {
			if (parent instanceof MColumnGroups)
				return new CreateColumnGroupCommand((MColumnGroups) parent, (MColumnGroup) child, newIndex);
		}
		if (child instanceof MRowGroup) {
			if (parent instanceof MRowGroups)
				return new CreateRowGroupCommand((MRowGroups) parent, (MRowGroup) child, newIndex);
		}
		if (child instanceof MCrosstabHeader) {
			if (parent instanceof MCrosstabHeader)
				return new CreateCrosstabHeaderCommand((MCrosstab) parent.getParent(), (MCrosstabHeader) child);
		}
		if (child instanceof MCrosstabWhenNoData) {
			if (parent instanceof MCrosstabWhenNoData)
				return new CreateCrosstabWhenNoDataCommand((MCrosstab) parent.getParent(), (MCrosstabWhenNoData) child);
		}
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new CreateElementCommand((MCell) parent, (MGraphicElement) child, newIndex);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new CreateElementGroupCommand((MCell) parent, (MElementGroup) child, newIndex);
		if (child instanceof MCrosstab) {
			if (parent instanceof MElementGroup)
				return new CreateCrosstabCommand((MElementGroup) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MBand)
				return new CreateCrosstabCommand((MBand) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateCrosstabCommand((MFrame) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MReport)
				return new CreateCrosstabCommand(parent, (MGraphicElement) child, location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateCrosstabCommand(parent, (MGraphicElement) child, location, newIndex);
			}
		}
		if (parent instanceof MCrosstab && child instanceof MGraphicElement) {
			MCrosstab mt = (MCrosstab) parent;
			final JRDesignCellContents cell = mt.getCrosstabManager().getCell(new Point(location.x, location.y));
			if (cell != null) {
				Rectangle r = mt.getCrosstabManager().getCellBounds(cell);
				location = location.setLocation(location.x - r.x, location.y - r.y);

				if (cell != null) {
					MCell mcell = (MCell) ModelUtil.getNode(cell, mt);
					return new CreateElementCommand(mcell, (MGraphicElement) child, location);
				}
			}
		}
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		if (child instanceof MParameter) {
			if (parent instanceof MCrosstabParameters)
				return new DeleteParameterCommand((MCrosstabParameters) parent, (MParameter) child);
		}
		if (child instanceof MMeasure) {
			if (parent instanceof MMeasures)
				return new DeleteMeasureCommand((MMeasures) parent, (MMeasure) child);
		}
		if (child instanceof MColumnGroup) {
			if (parent instanceof MColumnGroups)
				return new DeleteColumnGroupCommand((MColumnGroups) parent, (MColumnGroup) child);
		}
		if (child instanceof MRowGroup) {
			if (parent instanceof MRowGroups)
				return new DeleteRowGroupCommand((MRowGroups) parent, (MRowGroup) child);
		}
		if (child instanceof MCrosstabHeaderCell && ((MCrosstabHeaderCell) child).getValue() != null) {
			if (parent instanceof MCrosstab)
				return new DeleteCrosstabHeaderCommand((MCrosstab) parent, (MCrosstabHeaderCell) child);
		}
		if (child instanceof MCrosstabWhenNoDataCell && ((MCrosstabWhenNoDataCell) child).getValue() != null) {
			if (parent instanceof MCrosstab)
				return new DeleteCrosstabWhenNoDataCommand((MCrosstab) parent, (MCrosstabWhenNoDataCell) child);
		}
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new DeleteElementCommand((MCell) parent, (MGraphicElement) child);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new DeleteElementGroupCommand((MCell) parent, (MElementGroup) child);
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		if (child instanceof MParameter) {
			if (parent instanceof MCrosstabParameters)
				return new ReorderParameterCommand((MParameter) child, (MCrosstabParameters) parent, newIndex);
		}
		if (child instanceof MMeasure) {
			if (parent instanceof MMeasures)
				return new ReorderMeasureCommand((MMeasure) child, (MMeasures) parent, newIndex);
		}
		if (child instanceof MColumnGroup && child.getValue() != null) {
			if (parent instanceof MColumnGroups)
				return new ReorderColumnGroupCommand((MColumnGroup) child, (MColumnGroups) parent, newIndex);
		}
		if (child instanceof MRowGroup && child.getValue() != null) {
			if (parent instanceof MRowGroups)
				return new ReorderRowGroupCommand((MRowGroup) child, (MRowGroups) parent, newIndex);
		}
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new ReorderElementCommand((MGraphicElement) child, (MCell) parent, newIndex);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new ReorderElementGroupCommand((MElementGroup) child, (MCell) parent, newIndex);
		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		List<Action> lst = new ArrayList<Action>();
		lst.add(new CreateMeasureAction(part));
		lst.add(new CreateColumnGroupAction(part));
		lst.add(new CreateRowGroupAction(part));
		lst.add(new CreateCrosstabHeaderAction(part));
		lst.add(new CreateCrosstabWhenNoDataAction(part));
		return lst;
	}

	public List<String> getActionsID() {
		List<String> lst = new ArrayList<String>();
		lst.add(CreateMeasureAction.ID);
		lst.add(CreateColumnGroupAction.ID);
		lst.add(CreateRowGroupAction.ID);
		lst.add(CreateCrosstabHeaderAction.ID);
		lst.add(CreateCrosstabWhenNoDataAction.ID);
		return lst;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof MCrosstab)
			return new CrosstabEditPart();
		if (model instanceof MCell)
			return new CrosstabCellEditPart();
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new OrphanElementCommand((MCell) parent, (MGraphicElement) child);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new OrphanElementGroupCommand((MCell) parent, (MElementGroup) child);
		return null;
	}

	public AbstractVisualEditor getEditor(Object node) {
		if (node instanceof JRDesignCrosstab)
			return new CrosstabEditor();
		return null;
	}

}
