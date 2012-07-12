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
package com.jaspersoft.studio.components.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.component.Component;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.components.table.editor.TableEditor;
import com.jaspersoft.studio.components.table.figure.CellFigure;
import com.jaspersoft.studio.components.table.figure.EmptyCellFigure;
import com.jaspersoft.studio.components.table.figure.TableFigure;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.MTableColumnFooter;
import com.jaspersoft.studio.components.table.model.MTableColumnHeader;
import com.jaspersoft.studio.components.table.model.MTableDetail;
import com.jaspersoft.studio.components.table.model.MTableFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupHeader;
import com.jaspersoft.studio.components.table.model.MTableHeader;
import com.jaspersoft.studio.components.table.model.cell.command.CreateE4ObjectCommand;
import com.jaspersoft.studio.components.table.model.cell.command.CreateElementCommand;
import com.jaspersoft.studio.components.table.model.cell.command.CreateElementGroupCommand;
import com.jaspersoft.studio.components.table.model.cell.command.DeleteElementCommand;
import com.jaspersoft.studio.components.table.model.cell.command.DeleteElementGroupCommand;
import com.jaspersoft.studio.components.table.model.cell.command.OrphanElementCommand;
import com.jaspersoft.studio.components.table.model.cell.command.OrphanElementGroupCommand;
import com.jaspersoft.studio.components.table.model.cell.command.ReorderElementCommand;
import com.jaspersoft.studio.components.table.model.cell.command.ReorderElementGroupCommand;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnAction;
import com.jaspersoft.studio.components.table.model.column.action.CreateColumnCellAction;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnCellCommand;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnCommand;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnFromGroupCommand;
import com.jaspersoft.studio.components.table.model.column.command.DeleteColumnCellCommand;
import com.jaspersoft.studio.components.table.model.column.command.DeleteColumnCommand;
import com.jaspersoft.studio.components.table.model.column.command.DeleteColumnFromGroupCommand;
import com.jaspersoft.studio.components.table.model.column.command.OrphanColumn4GroupCommand;
import com.jaspersoft.studio.components.table.model.column.command.OrphanColumnCommand;
import com.jaspersoft.studio.components.table.model.column.command.ReorderColumnCommand;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.model.columngroup.action.CreateColumnGroupAction;
import com.jaspersoft.studio.components.table.model.columngroup.action.UnGroupColumnsAction;
import com.jaspersoft.studio.components.table.model.columngroup.command.CreateColumnGroupCommand;
import com.jaspersoft.studio.components.table.model.columngroup.command.CreateColumnGroupFromGroupCommand;
import com.jaspersoft.studio.components.table.model.columngroup.command.ReorderColumnGroupCommand;
import com.jaspersoft.studio.components.table.model.table.command.CreateTableCommand;
import com.jaspersoft.studio.components.table.part.TableCellEditPart;
import com.jaspersoft.studio.components.table.part.TableEditPart;
import com.jaspersoft.studio.components.table.part.TablePageEditPart;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MCollection;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.model.util.ModelVisitor;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.model.variable.MVariableSystem;
import com.jaspersoft.studio.plugin.IComponentFactory;
import com.jaspersoft.studio.plugin.IPaletteContributor;
import com.jaspersoft.studio.plugin.PaletteContributor;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class TableComponentFactory implements IComponentFactory {

	public ANode createNode(final ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignComponentElement) {
			JRDesignComponentElement tbl = (JRDesignComponentElement) jrObject;
			if (tbl.getComponent() instanceof StandardTable) {
				JasperDesign jasperDesign = parent.getJasperDesign();
				TableManager tblManager = new TableManager(tbl, jasperDesign);
				MTable mt = new MTable(parent, tbl, newIndex, tblManager);
				if (parent instanceof MPage) {
					createTable(mt);

					// listen for datasets
					final JasperDesign jd = mt.getJasperDesign();
					final MTable finaltable = mt;
					final PropertyChangeListener listener = new PropertyChangeListener() {

						public void propertyChange(PropertyChangeEvent evt) {
							Display.getDefault().asyncExec(new Runnable() {

								public void run() {
									refreshTable(finaltable);
								}
							});

						}
					};
					PropertyChangeListener dlistener = new PropertyChangeListener() {

						public void propertyChange(PropertyChangeEvent evt) {
							listenDatasets(jd, listener);
						}
					};
					jd.getEventSupport().addPropertyChangeListener(
							JasperDesign.PROPERTY_DATASETS, dlistener);

					listenDatasets(jd, listener);

					final StandardTable st = TableManager.getTable(mt);
					DSListener dslistner = new DSListener(parent, jd, st);
					setDataset(parent, jd, st, dslistner);

					st.getEventSupport().addPropertyChangeListener(dslistner);
				}
				return mt;
			}
		}
		return null;
	}

	class DSListener implements PropertyChangeListener {
		private ANode parent;
		private JasperDesign jd;
		private StandardTable st;

		public DSListener(ANode parent, JasperDesign jd, StandardTable st) {
			this.parent = parent;
			this.jd = jd;
			this.st = st;
		}

		public void propertyChange(PropertyChangeEvent evt) {
			setDataset(parent, jd, st, this);
		}
	};

	public void setDataset(ANode parent, final JasperDesign jd,
			StandardTable st, DSListener dslistner) {
		for (INode n : parent.getChildren())
			if (n instanceof MDataset)
				parent.removeChild((ANode) n);
		JRDesignDatasetRun dr = (JRDesignDatasetRun) st.getDatasetRun();
		if (dr != null) {
			dr.getEventSupport().removePropertyChangeListener(dslistner);
			String dbname = dr.getDatasetName();
			JRDesignDataset dataset;
			if (dbname != null)
				dataset = (JRDesignDataset) jd.getDatasetMap().get(dbname);
			else
				dataset = (JRDesignDataset) jd.getMainDataset();
			if (dataset != null) {
				MDataset nDataset = new MDataset(parent, dataset, 0);
				ReportFactory.createDataset(nDataset, dataset, false);
			}

			dr.getEventSupport().addPropertyChangeListener(dslistner);
		}
	}

	private static void listenDatasets(final JasperDesign jd,
			final PropertyChangeListener listener) {
		for (Object jddt : jd.getDatasetsList()) {
			((JRDesignDataset) jddt).getEventSupport()
					.removePropertyChangeListener(
							JRDesignDataset.PROPERTY_GROUPS, listener);
			((JRDesignDataset) jddt).getEventSupport()
					.addPropertyChangeListener(JRDesignDataset.PROPERTY_GROUPS,
							listener);
		}
	}

	public static void refreshTable(MTable mt) {
		mt.removeChildren();
		createTable(mt);
	}

	public static ANode createTable(MTable mt) {
		JRDesignComponentElement tbl = (JRDesignComponentElement) mt.getValue();
		StandardTable table = (StandardTable) tbl.getComponent();
		MTableHeader mth = new MTableHeader(mt, tbl,
				StandardColumn.PROPERTY_TABLE_HEADER);
		MTableColumnHeader mch = new MTableColumnHeader(mt, tbl,
				StandardColumn.PROPERTY_COLUMN_HEADER);

		List<?> groupsList = TableUtil
				.getGroupList(table, mt.getJasperDesign());
		List<MTableGroupHeader> grHeaders = new ArrayList<MTableGroupHeader>();
		List<MTableGroupFooter> grFooters = new ArrayList<MTableGroupFooter>();

		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				grHeaders.add(new MTableGroupHeader(mt, tbl, jrGroup, ""));
			}

		MTableDetail mtd = new MTableDetail(mt, tbl,
				StandardColumn.PROPERTY_DETAIL);

		if (groupsList != null)
			for (ListIterator<?> it = groupsList
					.listIterator(groupsList.size()); it.hasPrevious();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.previous();
				grFooters.add(new MTableGroupFooter(mt, tbl, jrGroup, ""));
			}

		MTableColumnFooter mtcf = new MTableColumnFooter(mt, tbl,
				StandardColumn.PROPERTY_COLUMN_FOOTER);
		MTableFooter mtf = new MTableFooter(mt, tbl,
				StandardColumn.PROPERTY_TABLE_FOOTER);

		createColumns(mt, table.getColumns(), mth, mch, mtd, mtcf, mtf,
				grHeaders, grFooters);

		return mt;
	}

	public static void createColumns(ANode parent, List<BaseColumn> columns,
			MTableHeader mth, MTableColumnHeader mch, MTableDetail mtd,
			MTableColumnFooter mcf, MTableFooter mtf,
			List<MTableGroupHeader> grHeaders, List<MTableGroupFooter> grFooter) {
		for (int i = 0; i < columns.size(); i++) {
			BaseColumn bc = columns.get(i);
			createCellTableHeader(mth, bc, i + 1, i);

			createCellColumnHeader(mch, bc, i + 1, i);

			for (MTableGroupHeader mtgh : grHeaders)
				createCellGroupHeader(mtgh, bc, i + 1, mtgh.getJrDesignGroup()
						.getName(), i);

			createCellDetail(mtd, bc, i + 1, i);

			for (MTableGroupFooter mtgh : grFooter)
				createCellGroupFooter(mtgh, bc, i + 1, mtgh.getJrDesignGroup()
						.getName(), i);

			createCellColumnFooter(mcf, bc, i + 1, i);

			createCellTableFooter(mtf, bc, i + 1, i);
		}
	}

	public static int createCellGroupHeader(ANode mth, BaseColumn bc, int i,
			String grName, int index) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg,
					(DesignCell) scg.getGroupHeader(grName), i, index);
			for (int j = 0; j < scg.getColumns().size(); j++)
				i = createCellGroupHeader(mcg, scg.getColumns().get(j), i,
						grName, j);
		} else {
			createColumnCell(mth, bc, i, bc.getGroupHeader(grName), index);
			return ++i;
		}
		return i;
	}

	public static int createCellGroupFooter(ANode mth, BaseColumn bc, int i,
			String grName, int index) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg,
					(DesignCell) scg.getGroupFooter(grName), i, index);
			for (int j = 0; j < scg.getColumns().size(); j++)
				i = createCellGroupFooter(mcg, scg.getColumns().get(j), i,
						grName, j);
		} else {
			createColumnCell(mth, bc, i, bc.getGroupFooter(grName), index);
			return ++i;
		}
		return i;
	}

	public static int createCellDetail(ANode mth, BaseColumn bc, int i,
			int index) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			for (BaseColumn bcg : scg.getColumns())
				i = createCellDetail(mth, bcg, i, index);
		} else {
			createColumnCell(mth, bc, i, ((StandardColumn) bc).getDetailCell(),
					index);
			return ++i;
		}
		return i;
	}

	public static int createCellColumnHeader(ANode mth, BaseColumn bc, int i,
			int index) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg,
					(DesignCell) scg.getColumnHeader(), i, index);
			for (int j = 0; j < scg.getColumns().size(); j++)
				i = createCellColumnHeader(mcg, scg.getColumns().get(j), i, j);
		} else {
			createColumnCell(mth, bc, i, bc.getColumnHeader(), index);
			return ++i;
		}
		return i;
	}

	public static int createCellColumnFooter(ANode mth, BaseColumn bc, int i,
			int index) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg,
					(DesignCell) scg.getColumnFooter(), i, index);
			for (int j = 0; j < scg.getColumns().size(); j++)
				i = createCellColumnFooter(mcg, scg.getColumns().get(j), i, j);
		} else {
			createColumnCell(mth, bc, i, bc.getColumnFooter(), index);
			return ++i;
		}
		return i;
	}

	public static int createCellTableHeader(ANode mth, BaseColumn bc, int i,
			int index) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg,
					(DesignCell) scg.getTableHeader(), i, index);
			for (int j = 0; j < scg.getColumns().size(); j++)
				i = createCellTableHeader(mcg, scg.getColumns().get(j), i, j);
		} else {
			createColumnCell(mth, bc, i, bc.getTableHeader(), index);
			return ++i;
		}
		return i;
	}

	public static int createCellTableFooter(ANode mth, BaseColumn bc, int i,
			int index) {
		if (bc instanceof StandardColumnGroup) {
			StandardColumnGroup scg = (StandardColumnGroup) bc;
			MColumn mcg = getColumnGroup(mth, scg,
					(DesignCell) scg.getTableFooter(), i, index);
			for (int j = 0; j < scg.getColumns().size(); j++)
				i = createCellTableFooter(mcg, scg.getColumns().get(j), i, j);
		} else {
			createColumnCell(mth, bc, i, bc.getTableFooter(), index);
			return ++i;
		}
		return i;
	}

	public static ANode createColumnCell(ANode parent, BaseColumn bc, int i,
			Cell grHeader, int index) {
		String name = Messages.common_column + i;
		if (grHeader != null) {
			MCell mc = new MCell(parent, (StandardBaseColumn) bc,
					(DesignCell) grHeader, name, index);
			ReportFactory.createElementsForBand(mc, grHeader.getChildren());
			return mc;
		}
		return new MColumn(parent, (StandardBaseColumn) bc, name, index);
	}

	public static MColumn getColumnGroup(ANode mth, StandardColumnGroup scg,
			DesignCell cell, int i, int index) {
		String name = Messages.common_columns
				+ " " + i + "-" + (i + scg.getColumns().size() - 1); //$NON-NLS-1$ //$NON-NLS-2$
		MColumn mcg = null;
		if (cell != null) {
			mcg = new MColumnGroupCell(mth, scg, cell, name, index);
			ReportFactory.createElementsForBand(mcg, cell.getChildren());
		} else
			mcg = new MColumnGroup(mth, scg, name, index);
		return mcg;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MTable)
			return new TableFigure();
		if (node instanceof MCell)
			return new CellFigure();

		if (node instanceof MColumn && !(node instanceof MCell))
			return new EmptyCellFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {
		return null;
	}

	public IPaletteContributor getPaletteEntries() {
		PaletteContributor pc = new PaletteContributor();
		// pc.add(IPaletteContributor.KEY_COMMON_CONTAINER, MTable.class);
		pc.add(MTable.class);
		return pc;
	}

	public Command getCreateCommand(ANode parent, ANode child,
			Rectangle location, int newIndex) {
		if (child instanceof MField
				&& (child.getValue() != null && parent instanceof MCell))
			return new CreateE4ObjectCommand(child, (MCell) parent, location,
					newIndex);
		if (child instanceof MParameterSystem
				&& (child.getValue() != null && parent instanceof MCell))
			return new CreateE4ObjectCommand(child, (MCell) parent, location,
					newIndex);
		if (child instanceof MVariableSystem
				&& (child.getValue() != null && parent instanceof MCell))
			return new CreateE4ObjectCommand(child, (MCell) parent, location,
					newIndex);
		// } else {
		// if (child instanceof MStyle) {
		// if (parent instanceof MGraphicElement
		// && child.getValue() != null
		// && !(parent instanceof IContainer)) {
		// SetValueCommand cmd = new SetValueCommand();
		// cmd.setTarget((IPropertySource) parent);
		// cmd.setPropertyId(JRDesignElement.PROPERTY_PARENT_STYLE);
		// JRStyle style = (JRStyle) child.getValue();
		// cmd.setPropertyValue(style.getName());
		// return cmd;
		// }
		// if (parent instanceof MReport && location != null) {
		// MGraphicElement element = ModelUtils.getElement4Point(
		// parent, new Point(location.x, location.y));
		// if (element != null) {
		// SetValueCommand cmd = new SetValueCommand();
		// cmd.setTarget(element);
		// cmd.setPropertyId(JRDesignElement.PROPERTY_PARENT_STYLE);
		// JRStyle style = (JRStyle) child.getValue();
		// cmd.setPropertyValue(style.getName());
		// return cmd;
		// }
		// }
		// }
		// }

		if (child instanceof MCell) {
			if (parent instanceof MColumnGroup && !(parent instanceof MCell))
				return new CreateColumnCellCommand(
						((MColumn) parent).getSection(), (MColumn) parent);
			if (parent instanceof MColumn && !(parent instanceof MCell))
				return new CreateColumnCellCommand(
						((MColumn) parent).getSection(), (MColumn) parent);

		} else if (child instanceof MColumnGroup) {
			if (parent instanceof AMCollection)
				return new CreateColumnGroupCommand((AMCollection) parent,
						(MColumnGroup) child, newIndex);

			if (parent instanceof MColumnGroupCell)
				return new CreateColumnGroupFromGroupCommand(
						(MColumnGroupCell) parent, (MColumnGroup) child,
						newIndex);
			if (parent instanceof MColumnGroup)
				return new CreateColumnGroupFromGroupCommand(
						(MColumnGroup) parent, (MColumnGroup) child, newIndex);

			if (parent.getParent() instanceof MColumnGroupCell)
				return new CreateColumnGroupFromGroupCommand(
						(MColumnGroupCell) parent.getParent(),
						(MColumnGroup) child, newIndex);
			if (parent.getParent() instanceof MColumnGroup)
				return new CreateColumnGroupFromGroupCommand(
						(MColumnGroup) parent.getParent(),
						(MColumnGroup) child, newIndex);

			if (parent instanceof MColumn)
				return new CreateColumnGroupCommand((MColumn) parent,
						(MColumnGroup) child, newIndex);

		} else if (child instanceof MColumn) {
			if (parent instanceof MTableGroupHeader)
				return new CreateColumnCommand((MTableGroupHeader) parent,
						(MColumn) child, newIndex);
			if (parent instanceof MTableGroupFooter)
				return new CreateColumnCommand((MTableGroupFooter) parent,
						(MColumn) child, newIndex);
			if (parent instanceof AMCollection)
				return new CreateColumnCommand((AMCollection) parent,
						(MColumn) child, newIndex);

			if (parent instanceof MColumnGroupCell)
				return new CreateColumnFromGroupCommand(
						(MColumnGroupCell) parent, (MColumn) child, newIndex);
			if (parent instanceof MColumnGroup)
				return new CreateColumnFromGroupCommand((MColumnGroup) parent,
						(MColumn) child, newIndex);

			if (parent.getParent() instanceof MColumnGroupCell)
				return new CreateColumnFromGroupCommand(
						(MColumnGroupCell) parent.getParent(), (MColumn) child,
						newIndex);
			if (parent.getParent() instanceof MColumnGroup)
				return new CreateColumnFromGroupCommand(
						(MColumnGroup) parent.getParent(), (MColumn) child,
						newIndex);

			if (parent instanceof MColumn
					&& ((MColumn) parent).getMTable() != null)
				return new CreateColumnCommand((MColumn) parent,
						(MColumn) child, newIndex);
		}
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new CreateElementCommand((MCell) parent,
					(MGraphicElement) child, location, newIndex);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new CreateElementGroupCommand((MCell) parent,
					(MElementGroup) child, newIndex);
		if (child instanceof MTable) {
			if (parent instanceof MElementGroup)
				return new CreateTableCommand((MElementGroup) parent,
						(MGraphicElement) child, location, newIndex);
			if (parent instanceof MBand)
				return new CreateTableCommand((MBand) parent,
						(MGraphicElement) child, location, newIndex);
			if (parent instanceof MFrame)
				return new CreateTableCommand((MFrame) parent,
						(MGraphicElement) child, location, newIndex);
			if (parent instanceof MReport)
				return new CreateTableCommand(parent, (MGraphicElement) child,
						location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateTableCommand(parent, (MGraphicElement) child,
						location, newIndex);
			}
		}
		if (parent instanceof MTable && child instanceof MGraphicElement) {
			MTable mt = (MTable) parent;
			final Cell cell = mt.getTableManager().getCell(
					new Point(location.x, location.y));
			Rectangle r = mt.getTableManager().getCellBounds(cell);
			location = location.setLocation(location.x - r.x, location.y - r.y);

			if (cell != null) {
				ModelVisitor mv = new ModelVisitor(parent) {
					@Override
					public boolean visit(INode n) {
						if (n instanceof MCell && ((MCell) n).getCell() == cell)
							setObject(n);
						return true;
					}
				};
				MCell mcell = (MCell) mv.getObject();
				return new CreateElementCommand(mcell, (MGraphicElement) child,
						location, newIndex);
			}
		}
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		if (child instanceof MCell) {
			if (parent instanceof MColumnGroupCell)
				return new DeleteColumnCellCommand((ANode) parent.getParent(),
						(MCell) child);
			if (parent instanceof MColumnGroup)
				return new DeleteColumnCellCommand((ANode) parent.getParent(),
						(MCell) child);
			if (parent instanceof AMCollection)
				return new DeleteColumnCellCommand((ANode) parent,
						(MCell) child);
		} else if (child instanceof MColumn) {
			if (parent instanceof MTableGroupHeader)
				return new DeleteColumnCommand((MTableGroupHeader) parent,
						(MColumn) child);
			if (parent instanceof MTableGroupFooter)
				return new DeleteColumnCommand((MTableGroupFooter) parent,
						(MColumn) child);
			if (parent instanceof AMCollection)
				return new DeleteColumnCommand((AMCollection) parent,
						(MColumn) child);

			if (parent instanceof MColumnGroup)
				return new DeleteColumnFromGroupCommand((MColumnGroup) parent,
						(MColumn) child);
			if (parent instanceof MColumnGroupCell)
				return new DeleteColumnFromGroupCommand(
						(MColumnGroupCell) parent, (MColumn) child);
		}
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new DeleteElementCommand((MCell) parent,
					(MGraphicElement) child);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new DeleteElementGroupCommand((MCell) parent,
					(MElementGroup) child);
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		if (child instanceof MColumn) {
			if (parent instanceof MCollection)
				return new ReorderColumnCommand((MColumn) child,
						(MTable) parent.getParent(), newIndex);
			if (parent instanceof MColumnGroupCell)
				return new ReorderColumnGroupCommand((MColumn) child,
						(MColumnGroupCell) parent, newIndex);
			if (parent instanceof MColumnGroup)
				return new ReorderColumnGroupCommand((MColumn) child,
						(MColumnGroup) parent, newIndex);

		}
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new ReorderElementCommand((MGraphicElement) child,
					(MCell) parent, newIndex);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new ReorderElementGroupCommand((MElementGroup) child,
					(MCell) parent, newIndex);
		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		List<Action> lst = new ArrayList<Action>();
		// lst.add(new CreateColumnAction(part));
		// lst.add(new CreateColumnGroupAction(part));
		// lst.add(new CreateColumnCellAction(part));
		return lst;
	}

	public List<String> getActionsID() {
		List<String> lst = new ArrayList<String>();
		lst.add(CreateColumnAction.ID);
		lst.add(CreateColumnGroupAction.ID);
		lst.add(UnGroupColumnsAction.ID);
		lst.add(CreateColumnCellAction.ID);
		return lst;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof MRoot) {
			ANode n = ModelUtils.getFirstChild((MRoot) model);
			if (n != null && n instanceof MPage) {
				for (INode child : n.getChildren()) {
					if (child instanceof MTable)
						return new TablePageEditPart();
				}
			}
		}
		if (model instanceof MTable)
			return new TableEditPart();
		if (model instanceof MCell)
			return new TableCellEditPart();
		else if (model instanceof MColumn)
			return new TableCellEditPart();
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		if (child instanceof MColumn) {
			if (parent instanceof AMCollection)
				return new OrphanColumnCommand((AMCollection) parent,
						(MColumn) child);
			if (parent instanceof MColumnGroupCell)
				return new OrphanColumn4GroupCommand((MColumnGroupCell) parent,
						(MColumn) child);
			if (parent instanceof MColumnGroup)
				return new OrphanColumn4GroupCommand((MColumnGroup) parent,
						(MColumn) child);
		}
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new OrphanElementCommand((MCell) parent,
					(MGraphicElement) child);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new OrphanElementGroupCommand((MCell) parent,
					(MElementGroup) child);
		return null;
	}

	public AbstractVisualEditor getEditor(Object node,
			JasperReportsConfiguration jrContext) {
		if (node != null && node instanceof JRDesignComponentElement) {
			Component component = ((JRDesignComponentElement) node)
					.getComponent();
			if (component != null && component instanceof StandardTable)
				return new TableEditor(jrContext);
		}
		return null;
	}

	public ExpressionContext getElementExpressionContext(Object jrObject) {
		if (jrObject instanceof MTable
				&& ((MTable) jrObject).getValue() instanceof JRDesignComponentElement) {
			MTable mtable = (MTable) jrObject;
			StandardTable table = (StandardTable) ((JRDesignComponentElement) mtable
					.getValue()).getComponent();
			JRDesignDataset designDS = ModelUtils
					.getDesignDatasetForDatasetRun(mtable
							.getJasperConfiguration().getJasperDesign(), table
							.getDatasetRun());
			return new ExpressionContext(designDS,
					mtable.getJasperConfiguration());
		}
		return null;
	}
}
