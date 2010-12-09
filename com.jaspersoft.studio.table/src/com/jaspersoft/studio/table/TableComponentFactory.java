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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.IComponentFactory;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ReportFactory;
import com.jaspersoft.studio.table.model.MCell;
import com.jaspersoft.studio.table.model.MColumn;
import com.jaspersoft.studio.table.model.MColumnGroup;
import com.jaspersoft.studio.table.model.MColumnGroupCell;
import com.jaspersoft.studio.table.model.MTable;
import com.jaspersoft.studio.table.model.MTableColumnFooter;
import com.jaspersoft.studio.table.model.MTableColumnHeader;
import com.jaspersoft.studio.table.model.MTableDetail;
import com.jaspersoft.studio.table.model.MTableFooter;
import com.jaspersoft.studio.table.model.MTableGroupFooter;
import com.jaspersoft.studio.table.model.MTableGroupHeader;
import com.jaspersoft.studio.table.model.MTableHeader;
import com.jaspersoft.studio.table.part.TableCellEditPart;
import com.jaspersoft.studio.table.part.TableEditPart;

public class TableComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignComponentElement) {
			JRDesignComponentElement tbl = (JRDesignComponentElement) jrObject;
			if (tbl.getComponent() instanceof StandardTable) {
				StandardTable table = (StandardTable) tbl.getComponent();
				JasperDesign jasperDesign = parent.getJasperDesign();

				TableManager tblManager = new TableManager(tbl, jasperDesign);
				MTable mt = new MTable(parent, tbl, newIndex, tblManager);
				MTableHeader mth = new MTableHeader(mt, tbl, StandardColumn.PROPERTY_TABLE_HEADER);
				MTableColumnHeader mch = new MTableColumnHeader(mt, tbl, StandardColumn.PROPERTY_COLUMN_HEADER);

				List<?> groupsList = tblManager.getGroupList();
				List<MTableGroupHeader> grHeaders = new ArrayList<MTableGroupHeader>();
				List<MTableGroupFooter> grFooters = new ArrayList<MTableGroupFooter>();

				if (groupsList != null)
					for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
						JRDesignGroup jrGroup = (JRDesignGroup) it.next();
						grHeaders.add(new MTableGroupHeader(mt, jrGroup, ""));
					}

				MTableDetail mtd = new MTableDetail(mt, tbl, StandardColumn.PROPERTY_DETAIL);

				if (groupsList != null)
					for (ListIterator<?> it = groupsList.listIterator(groupsList.size()); it.hasPrevious();) {
						JRDesignGroup jrGroup = (JRDesignGroup) it.previous();
						grFooters.add(new MTableGroupFooter(mt, jrGroup, ""));
					}

				MTableColumnFooter mtcf = new MTableColumnFooter(mt, tbl, StandardColumn.PROPERTY_COLUMN_FOOTER);
				MTableFooter mtf = new MTableFooter(mt, tbl, StandardColumn.PROPERTY_TABLE_FOOTER);

				createColumns(mt, table.getColumns(), mth, mch, mtd, mtcf, mtf, grHeaders, grFooters);

				return mt;
			}
		}
		return null;
	}

	public void createColumns(ANode parent, List<BaseColumn> columns, MTableHeader mth, MTableColumnHeader mch,
			MTableDetail mtd, MTableColumnFooter mcf, MTableFooter mtf, List<MTableGroupHeader> grHeaders,
			List<MTableGroupFooter> grFooter) {
		int i = 1;
		for (BaseColumn bc : columns) {
			createCellTableHeader(mth, bc, i);

			createCellColumnHeader(mch, bc, i);

			for (MTableGroupHeader mtgh : grHeaders)
				createCellGroupHeader(mtgh, bc, i, ((JRDesignGroup) mtgh.getValue()).getName());

			createCellDetail(mtd, bc, i);

			for (MTableGroupFooter mtgh : grFooter)
				createCellGroupFooter(mtgh, bc, i, ((JRDesignGroup) mtgh.getValue()).getName());

			createCellColumnFooter(mcf, bc, i);

			createCellTableHeader(mtf, bc, i);

			i++;
		}
	}

	public int createCellGroupHeader(ANode mth, BaseColumn bc, int i, String grName) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg, (DesignCell) scg.getGroupHeader(grName), i);
			for (BaseColumn bcg : scg.getColumns())
				i = createCellGroupHeader(mcg, bcg, i, grName);
		} else {
			if (bc.getGroupHeader(grName) != null) {
				MCell mc = new MCell(mth, (StandardBaseColumn) bc, (DesignCell) bc.getGroupHeader(grName), "Column" + i);
				ReportFactory.createElementsForBand(mc, bc.getGroupHeader(grName).getChildren());
			} else
				new MColumn(mth, (StandardBaseColumn) bc, "Column" + i);
			return ++i;
		}
		return i;
	}

	public int createCellGroupFooter(ANode mth, BaseColumn bc, int i, String grName) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg, (DesignCell) scg.getGroupFooter(grName), i);
			for (BaseColumn bcg : scg.getColumns())
				i = createCellGroupFooter(mcg, bcg, i, grName);
		} else {
			if (bc.getGroupFooter(grName) != null) {
				MCell mc = new MCell(mth, (StandardBaseColumn) bc, (DesignCell) bc.getGroupFooter(grName), "Column" + i);
				ReportFactory.createElementsForBand(mc, bc.getGroupFooter(grName).getChildren());
			} else
				new MColumn(mth, (StandardBaseColumn) bc, "Column" + i);
			return ++i;
		}
		return i;
	}

	public int createCellDetail(ANode mth, BaseColumn bc, int i) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			for (BaseColumn bcg : scg.getColumns())
				i = createCellDetail(mth, bcg, i);
		} else {
			if (((StandardColumn) bc).getDetailCell() != null) {
				MCell mc = new MCell(mth, (StandardBaseColumn) bc, (DesignCell) ((StandardColumn) bc).getDetailCell(), "Column"
						+ i);
				ReportFactory.createElementsForBand(mc, ((StandardColumn) bc).getDetailCell().getChildren());
			} else
				new MColumn(mth, (StandardBaseColumn) bc, "Column" + i);
			return ++i;
		}
		return i;
	}

	public int createCellColumnHeader(ANode mth, BaseColumn bc, int i) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg, (DesignCell) scg.getColumnHeader(), i);
			for (BaseColumn bcg : scg.getColumns())
				i = createCellColumnHeader(mcg, bcg, i);
		} else {
			if (bc.getColumnHeader() != null) {
				MCell mc = new MCell(mth, (StandardBaseColumn) bc, (DesignCell) bc.getColumnHeader(), "Column" + i);
				ReportFactory.createElementsForBand(mc, bc.getColumnHeader().getChildren());
			} else
				new MColumn(mth, (StandardBaseColumn) bc, "Column" + i);
			return ++i;
		}
		return i;
	}

	public int createCellColumnFooter(ANode mth, BaseColumn bc, int i) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg, (DesignCell) scg.getColumnFooter(), i);
			for (BaseColumn bcg : scg.getColumns())
				i = createCellColumnFooter(mcg, bcg, i);
		} else {
			if (bc.getColumnFooter() != null) {
				MCell mc = new MCell(mth, (StandardBaseColumn) bc, (DesignCell) bc.getColumnFooter(), "Column" + i);
				ReportFactory.createElementsForBand(mc, bc.getColumnFooter().getChildren());
			} else
				new MColumn(mth, (StandardBaseColumn) bc, "Column" + i);
			return ++i;
		}
		return i;
	}

	public int createCellTableHeader(ANode mth, BaseColumn bc, int i) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg, (DesignCell) scg.getTableHeader(), i);
			for (BaseColumn bcg : scg.getColumns())
				i = createCellTableHeader(mcg, bcg, i);
		} else {
			if (bc.getTableHeader() != null) {
				MCell mc = new MCell(mth, (StandardBaseColumn) bc, (DesignCell) bc.getTableHeader(), "Column" + i);
				ReportFactory.createElementsForBand(mc, bc.getTableHeader().getChildren());
			} else
				new MColumn(mth, (StandardBaseColumn) bc, "Column" + i);
			return ++i;
		}
		return i;
	}

	public int createCellTableFooter(ANode mth, BaseColumn bc, int i) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg, (DesignCell) scg.getTableFooter(), i);
			for (BaseColumn bcg : scg.getColumns())
				i = createCellTableFooter(mcg, bcg, i);
		} else {
			if (bc.getTableHeader() != null) {
				MCell mc = new MCell(mth, (StandardBaseColumn) bc, (DesignCell) bc.getTableFooter(), "Column" + i);
				ReportFactory.createElementsForBand(mc, bc.getTableFooter().getChildren());
			} else
				new MColumn(mth, (StandardBaseColumn) bc, "Column" + i);
			return ++i;
		}
		return i;
	}

	public MColumn getColumnGroup(ANode mth, StandardColumnGroup scg, DesignCell cell, int i) {
		String name = "Columns " + i + "-" + (i + scg.getColumns().size() - 1);
		MColumn mcg = null;
		if (cell != null) {
			mcg = new MColumnGroupCell(mth, scg, cell, name);
			ReportFactory.createElementsForBand(mcg, cell.getChildren());
		} else
			mcg = new MColumnGroup(mth, scg, name);
		return mcg;
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
		if (model instanceof MTable)
			return new TableEditPart();
		if (model instanceof MCell)
			return new TableCellEditPart();
		return null;
	}

}
