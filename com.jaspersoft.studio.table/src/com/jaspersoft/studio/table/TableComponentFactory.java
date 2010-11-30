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
package com.jaspersoft.studio.table;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.IComponentFactory;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.table.model.MTable;
import com.jaspersoft.studio.table.model.MTableColumnFooter;
import com.jaspersoft.studio.table.model.MTableColumnHeader;
import com.jaspersoft.studio.table.model.MTableDetail;
import com.jaspersoft.studio.table.model.MTableFooter;
import com.jaspersoft.studio.table.model.MTableHeader;

public class TableComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignComponentElement) {
			JRDesignComponentElement tbl = (JRDesignComponentElement) jrObject;
			if (tbl.getComponent() instanceof StandardTable) {
				TableManager tblManager = new TableManager(tbl);
				MTable mt = new MTable(parent, tbl, newIndex, tblManager);
				new MTableHeader(mt, tbl, StandardColumn.PROPERTY_TABLE_HEADER);
				new MTableColumnHeader(mt, tbl, StandardColumn.PROPERTY_COLUMN_HEADER);
				new MTableDetail(mt, tbl, StandardColumn.PROPERTY_DETAIL);
				new MTableColumnFooter(mt, tbl, StandardColumn.PROPERTY_COLUMN_FOOTER);
				new MTableFooter(mt, tbl, StandardColumn.PROPERTY_TABLE_FOOTER);

				return mt;
			}
		}
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MTable)
			return new ComponentFigure();
		// if (node instanceof MCell)
		// return new CellFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {
		// if (jrObject instanceof JRCrosstab) {
		// // JRCrosstab ct = (JRCrosstab) jrObject;
		// List<Object> lst = new ArrayList<Object>();
		//
		// // lst.add(ct.getParameters());
		// // lst.add(ct.getRowGroups());
		// // lst.add(ct.getColumnGroups());
		// // lst.add(ct.getMeasures());
		//
		// // lst.add(Arrays.asList(ct.getHeaderCell()));
		//
		// return lst;
		// }
		return null;
	}

	public List<Class<?>> getPaletteEntries() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MTable.class);
		return list;
	}

	public Command getCreateCommand(ANode parent, ANode child, Point location, int newIndex) {
		// if (child instanceof MParameter) {
		// if (parent instanceof MCrosstabParameters)
		// return new CreateParameterCommand((MCrosstabParameters) parent, (MParameter) child, newIndex);
		// }
		// if (child instanceof MMeasure) {
		// if (parent instanceof MMeasures)
		// return new CreateMeasureCommand((MMeasures) parent, (MMeasure) child, newIndex);
		// }
		// if (child instanceof MColumnGroup) {
		// if (parent instanceof MColumnGroups)
		// return new CreateColumnGroupCommand((MColumnGroups) parent, (MColumnGroup) child, newIndex);
		// }
		// if (child instanceof MRowGroup) {
		// if (parent instanceof MRowGroups)
		// return new CreateRowGroupCommand((MRowGroups) parent, (MRowGroup) child, newIndex);
		// }
		// if (child instanceof MCrosstabHeader) {
		// if (parent instanceof MCrosstabHeader && ((MCrosstabHeader) parent).getValue() == null)
		// return new CreateCrosstabHeaderCommand((MCrosstab) parent.getParent(), (MCrosstabHeader) child);
		// }
		// if (child instanceof MCrosstabWhenNoData) {
		// if (parent instanceof MCrosstabWhenNoData && ((MCrosstabWhenNoData) parent).getValue() == null)
		// return new CreateCrosstabWhenNoDataCommand((MCrosstab) parent.getParent(), (MCrosstabWhenNoData) child);
		// }

		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		// if (child instanceof MParameter) {
		// if (parent instanceof MCrosstabParameters)
		// return new DeleteParameterCommand((MCrosstabParameters) parent, (MParameter) child);
		// }
		// if (child instanceof MMeasure) {
		// if (parent instanceof MMeasures)
		// return new DeleteMeasureCommand((MMeasures) parent, (MMeasure) child);
		// }
		// if (child instanceof MColumnGroup) {
		// if (parent instanceof MColumnGroups)
		// return new DeleteColumnGroupCommand((MColumnGroups) parent, (MColumnGroup) child);
		// }
		// if (child instanceof MRowGroup) {
		// if (parent instanceof MRowGroups)
		// return new DeleteRowGroupCommand((MRowGroups) parent, (MRowGroup) child);
		// }
		// if (child instanceof MCrosstabHeader && ((MCrosstabHeader) child).getValue() != null) {
		// if (parent instanceof MCrosstab)
		// return new DeleteCrosstabHeaderCommand((MCrosstab) parent, (MCrosstabHeader) child);
		// }
		// if (child instanceof MCrosstabWhenNoData && ((MCrosstabWhenNoData) child).getValue() != null) {
		// if (parent instanceof MCrosstab)
		// return new DeleteCrosstabWhenNoDataCommand((MCrosstab) parent, (MCrosstabWhenNoData) child);
		// }
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		// if (child instanceof MParameter) {
		// if (parent instanceof MCrosstabParameters)
		// return new ReorderParameterCommand((MParameter) child, (MCrosstabParameters) parent, newIndex);
		// }
		// if (child instanceof MMeasure) {
		// if (parent instanceof MMeasures)
		// return new ReorderMeasureCommand((MMeasure) child, (MMeasures) parent, newIndex);
		// }
		// if (child instanceof MColumnGroup) {
		// if (parent instanceof MColumnGroups)
		// return new ReorderColumnGroupCommand((MColumnGroup) child, (MColumnGroups) parent, newIndex);
		// }
		// if (child instanceof MRowGroup) {
		// if (parent instanceof MRowGroups)
		// return new ReorderRowGroupCommand((MRowGroup) child, (MRowGroups) parent, newIndex);
		// }
		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		List<Action> lst = new ArrayList<Action>();
		// lst.add(new CreateMeasureAction(part));
		// lst.add(new CreateColumnGroupAction(part));
		// lst.add(new CreateRowGroupAction(part));
		// lst.add(new CreateCrosstabHeaderAction(part));
		// lst.add(new CreateCrosstabWhenNoDataAction(part));
		return lst;
	}

	public List<String> getActionsID() {
		List<String> lst = new ArrayList<String>();
		// lst.add(CreateMeasureAction.ID);
		// lst.add(CreateColumnGroupAction.ID);
		// lst.add(CreateRowGroupAction.ID);
		// lst.add(CreateCrosstabHeaderAction.ID);
		// lst.add(CreateCrosstabWhenNoDataAction.ID);
		return lst;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		// if (model instanceof MCrosstab)
		// return new CrosstabEditPart();
		// if (model instanceof MCell)
		// return new CrosstabCellEditPart();
		return null;
	}

}
