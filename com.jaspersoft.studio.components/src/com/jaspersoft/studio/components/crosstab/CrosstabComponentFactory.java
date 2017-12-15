/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.components.crosstab.action.EditCrosstabStyleAction;
import com.jaspersoft.studio.components.crosstab.action.RemoveCrosstabStylesAction;
import com.jaspersoft.studio.components.crosstab.editor.CrosstabEditor;
import com.jaspersoft.studio.components.crosstab.figure.CellFigure;
import com.jaspersoft.studio.components.crosstab.figure.CrosstabFigure;
import com.jaspersoft.studio.components.crosstab.figure.EmptyCellFigure;
import com.jaspersoft.studio.components.crosstab.figure.WhenNoDataCellFigure;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.components.crosstab.model.cell.MColumnGroupHeaderCell;
import com.jaspersoft.studio.components.crosstab.model.cell.MColumnGroupTotalCell;
import com.jaspersoft.studio.components.crosstab.model.cell.MGroupCell;
import com.jaspersoft.studio.components.crosstab.model.cell.MRowGroupHeaderCell;
import com.jaspersoft.studio.components.crosstab.model.cell.MRowGroupTotalCell;
import com.jaspersoft.studio.components.crosstab.model.cell.action.CreateColumnCrosstabHeaderAction;
import com.jaspersoft.studio.components.crosstab.model.cell.command.CreateColumnCrosstabHeaderCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.CreateCrosstabElement4ObjectCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.CreateElementCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.CreateElementGroupCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.DeleteColumnCrosstabHeaderCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.DeleteElementCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.DeleteElementGroupCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.OrphanElementCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.OrphanElementGroupCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.ReorderElementCommand;
import com.jaspersoft.studio.components.crosstab.model.cell.command.ReorderElementGroupCommand;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnCrosstabHeaderCell;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroups;
import com.jaspersoft.studio.components.crosstab.model.columngroup.action.CreateColumnGroupAction;
import com.jaspersoft.studio.components.crosstab.model.columngroup.command.CreateColumnCommand;
import com.jaspersoft.studio.components.crosstab.model.columngroup.command.DeleteColumnGroupCommand;
import com.jaspersoft.studio.components.crosstab.model.columngroup.command.ReorderColumnGroupCommand;
import com.jaspersoft.studio.components.crosstab.model.crosstab.command.CreateCrosstabCommand;
import com.jaspersoft.studio.components.crosstab.model.crosstab.command.DeleteCrosstabCommand;
import com.jaspersoft.studio.components.crosstab.model.header.MCrosstabHeader;
import com.jaspersoft.studio.components.crosstab.model.header.MCrosstabHeaderCell;
import com.jaspersoft.studio.components.crosstab.model.header.action.CreateCrosstabHeaderAction;
import com.jaspersoft.studio.components.crosstab.model.header.command.CreateCrosstabHeaderCommand;
import com.jaspersoft.studio.components.crosstab.model.header.command.DeleteCrosstabHeaderCommand;
import com.jaspersoft.studio.components.crosstab.model.measure.MMeasure;
import com.jaspersoft.studio.components.crosstab.model.measure.MMeasures;
import com.jaspersoft.studio.components.crosstab.model.measure.action.CreateMeasureAction;
import com.jaspersoft.studio.components.crosstab.model.measure.command.CreateMeasureCommand;
import com.jaspersoft.studio.components.crosstab.model.measure.command.DeleteMeasureCommand;
import com.jaspersoft.studio.components.crosstab.model.measure.command.ReorderMeasureCommand;
import com.jaspersoft.studio.components.crosstab.model.nodata.MCrosstabWhenNoData;
import com.jaspersoft.studio.components.crosstab.model.nodata.MCrosstabWhenNoDataCell;
import com.jaspersoft.studio.components.crosstab.model.nodata.action.CreateCrosstabWhenNoDataAction;
import com.jaspersoft.studio.components.crosstab.model.nodata.command.CreateCrosstabWhenNoDataCommand;
import com.jaspersoft.studio.components.crosstab.model.nodata.command.DeleteCrosstabWhenNoDataCommand;
import com.jaspersoft.studio.components.crosstab.model.parameter.MCrosstabParameter;
import com.jaspersoft.studio.components.crosstab.model.parameter.MCrosstabParameters;
import com.jaspersoft.studio.components.crosstab.model.parameter.action.CreateCrosstabParameterAction;
import com.jaspersoft.studio.components.crosstab.model.parameter.command.CreateParameterCommand;
import com.jaspersoft.studio.components.crosstab.model.parameter.command.DeleteParameterCommand;
import com.jaspersoft.studio.components.crosstab.model.parameter.command.ReorderParameterCommand;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroups;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.action.CreateRowGroupAction;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.command.CreateRowCommand;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.command.DeleteRowGroupCommand;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.command.ReorderRowGroupCommand;
import com.jaspersoft.studio.components.crosstab.model.title.MTitle;
import com.jaspersoft.studio.components.crosstab.model.title.MTitleCell;
import com.jaspersoft.studio.components.crosstab.model.title.action.CreateCrosstabTitleAction;
import com.jaspersoft.studio.components.crosstab.model.title.command.CreateTitleCellCommand;
import com.jaspersoft.studio.components.crosstab.model.title.command.DeleteTitleCommand;
import com.jaspersoft.studio.components.crosstab.part.CrosstabCellEditPart;
import com.jaspersoft.studio.components.crosstab.part.CrosstabEditPart;
import com.jaspersoft.studio.components.crosstab.part.CrosstabHeaderEditPart;
import com.jaspersoft.studio.components.crosstab.part.CrosstabPageEditPart;
import com.jaspersoft.studio.components.crosstab.part.CrosstabTitleCellEditPart;
import com.jaspersoft.studio.components.crosstab.part.CrosstabTitleEditPart;
import com.jaspersoft.studio.components.crosstab.part.CrosstabWhenNoDataEditPart;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.editor.outline.part.OpenableContainerTreeEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.editor.tools.MCompositeElement;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElementContainer;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MPage;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.model.image.MImage;
import com.jaspersoft.studio.model.image.command.CreateImageCommand;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.model.parameter.MParameterSystem;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.model.variable.MVariableSystem;
import com.jaspersoft.studio.plugin.IComponentFactory;
import com.jaspersoft.studio.plugin.IPaletteContributor;
import com.jaspersoft.studio.plugin.PaletteContributor;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.JRCrosstabParameter;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabDataset;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabParameter;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.Pair;

public class CrosstabComponentFactory implements IComponentFactory {
	
	private static CrosstabComponentFactory inst;
	
	private static List<Class<?>> knownClasses;
	
	static {
		knownClasses = new ArrayList<Class<?>>(8);
		knownClasses.add(MCrosstab.class);
		knownClasses.add(MColumnGroup.class);
		knownClasses.add(MRowGroup.class);
		knownClasses.add(MColumnCrosstabHeaderCell.class);
		knownClasses.add(MCrosstabHeaderCell.class);
		knownClasses.add(MCrosstabWhenNoDataCell.class);
		knownClasses.add(MTitleCell.class);
		knownClasses.add(MCrosstabHeaderCell.class);
		knownClasses.add(MCrosstabParameter.class);
	}

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignCrosstab) {
			JRDesignCrosstab ct = (JRDesignCrosstab) jrObject;
			ct.preprocess();
			CrosstabManager ctManager = new CrosstabManager(ct, parent.getJasperDesign());
			MCrosstab mCrosstab = new MCrosstab(parent, ct, newIndex, ctManager);
			MCrosstabParameters mp = new MCrosstabParameters(mCrosstab, ct, JRDesignCrosstab.PROPERTY_PARAMETERS);
			if (ct.getParameters() != null){
				for (JRCrosstabParameter jrParameter : ct.getParameters()){
					if (jrParameter.isSystemDefined()){
						 new MParameterSystem(mp, (JRDesignCrosstabParameter)jrParameter, -1);
					} else {
						new MCrosstabParameter(mp, (JRDesignCrosstabParameter)jrParameter, -1);
					}
				}
			}
				
			// ---------------------------------
			createCellNodes(ct, mCrosstab);
			return mCrosstab;
		}
		
		if (jrObject instanceof JRCrosstabRowGroup){
			return createRowGroup(parent, (JRCrosstabRowGroup) jrObject, newIndex);
		}
		
		if (jrObject instanceof JRCrosstabColumnGroup){
			return createColumnGroup(parent, (JRCrosstabColumnGroup) jrObject, newIndex);
		}
		
		if (jrObject instanceof JRCrosstabMeasure){
			return new MMeasure(parent, (JRCrosstabMeasure) jrObject, newIndex);
		}
		
		if (jrObject instanceof JRDesignCrosstabParameter){
			return new MCrosstabParameter(parent, (JRDesignCrosstabParameter)jrObject, newIndex);
		}
		return null;
	}
	
	public static void createSubeditor(MCrosstab mc){
		ANode parent = mc.getParent();
		JasperDesign jd = mc.getJasperDesign();
		JRDesignCrosstab st = mc.getValue();
		
		ReportFactory.createStyles(parent.getJasperConfiguration(), jd, parent, 0);
		DSListener dslistner = new DSListener(parent, jd, st);
		setDataset(parent, jd, st, dslistner);
		MCallout.createCallouts(mc);
		((JRDesignCrosstabDataset) st.getDataset()).getEventSupport().addPropertyChangeListener(dslistner);
	}
	
	protected static void setDataset(ANode parent, final JasperDesign jd, JRDesignCrosstab st, DSListener dslistner) {
		for (INode n : parent.getChildren())
			if (n instanceof MDataset)
				parent.removeChild((ANode) n);
		JRDesignCrosstabDataset d = (JRDesignCrosstabDataset) st.getDataset();
		JRDesignDatasetRun dr = (JRDesignDatasetRun) d.getDatasetRun();
		JRDesignDataset dataset = null;
		if (dr != null) {
			dr.getEventSupport().removePropertyChangeListener(dslistner);
			String dbname = dr.getDatasetName();
			if (dbname != null)
				dataset = (JRDesignDataset) jd.getDatasetMap().get(dbname);
			else
				dataset = (JRDesignDataset) jd.getMainDataset();
		} else
			dataset = (JRDesignDataset) jd.getMainDataset();
		if (dataset != null) {
			MDataset nDataset = new MDataset(parent, dataset, 1);
			ReportFactory.createDataset(nDataset, dataset, true);
		}
		if (dr != null)
			dr.getEventSupport().addPropertyChangeListener(dslistner);

	}

	private ANode createColumnGroup(ANode mcg, JRCrosstabColumnGroup p, int newIndex) {
		MColumnGroup rg = new MColumnGroup(mcg, p, newIndex);

		createColumnGroupCells(rg, p);
		return rg;
	}

	public static void createColumnGroupCells(MColumnGroup rg, JRCrosstabColumnGroup p) {
		MCell mc = new MColumnGroupHeaderCell(rg, p.getHeader(), p.getName());
		ReportFactory.createElementsForBand(mc, p.getHeader().getChildren());

		mc = new MColumnCrosstabHeaderCell(rg, p.getCrosstabHeader(), -1);
		if (p.getCrosstabHeader() != null)
			ReportFactory.createElementsForBand(mc, p.getCrosstabHeader().getChildren());

		if (p.getTotalPositionValue() != null &&  !p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.NONE)) {
			JRDesignCrosstab crosstab = rg.getMCrosstab().getValue();
			mc = new MColumnGroupTotalCell(rg, p.getTotalHeader(), p.getName());
			
			
			// I need to add the extra cells...
			Pair<String,String> cellKey = new Pair<String,String>(null, p.getName());
			JRDesignCrosstabCell totalCell = (JRDesignCrosstabCell)crosstab.getCellsMap().get(cellKey);
			if (totalCell == null) {
				totalCell = new JRDesignCrosstabCell();
				totalCell.setColumnTotalGroup(p.getName());
				try {
					crosstab.addCell(totalCell);
					cellKey = new Pair<String,String>(null, null);
					JRCrosstabCell detailCell = crosstab.getCellsMap().get(cellKey);
					totalCell.setHeight(20);
					if (detailCell == null) {
						totalCell.setWidth(60);
					} else {
						totalCell.setHeight(detailCell.getHeight());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// for each column, we need to add the total...
			List<JRCrosstabRowGroup> rows = crosstab.getRowGroupsList();
			if (rows != null) {
				for (JRCrosstabRowGroup row : rows) {
					cellKey = new Pair<String,String>(row.getName(), p.getName());
					JRDesignCrosstabCell cell = (JRDesignCrosstabCell)crosstab.getCellsMap().get(cellKey);
					if (cell == null) {
						cell = new JRDesignCrosstabCell();
						cell.setColumnTotalGroup(p.getName());
						cell.setRowTotalGroup(row.getName());
						try {
							int height = 20;
							for(JRCrosstabCell rowCell : crosstab.getCellsList()) {
								if (ModelUtils.safeEquals(row.getName(), ((JRDesignCrosstabCell)rowCell).getRowTotalGroup())) {
									height = rowCell.getHeight();
									break;
								}
							}
							crosstab.addCell(cell);
							cell.setHeight(height);
							cell.setWidth(60);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					
				}
			}
			ReportFactory.createElementsForBand(mc, p.getTotalHeader().getChildren());
		}

	}

	private ANode createRowGroup(ANode mrg, JRCrosstabRowGroup p, int newIndex) {
		MRowGroup rg = new MRowGroup(mrg, p, newIndex);

		createRowGroupCells(rg, p);
		return rg;
	}

	public static void createRowGroupCells(MRowGroup rg, JRCrosstabRowGroup p) {
		MCell mc = new MRowGroupHeaderCell(rg, p.getHeader(), p.getName());
		ReportFactory.createElementsForBand(mc, p.getHeader().getChildren());

		if (p.getTotalPositionValue() != null && !p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.NONE)) {
			JRDesignCrosstab crosstab = rg.getMCrosstab().getValue();
			JRCrosstabRowGroup lastGroup = null; 
			if (!crosstab.getRowGroupsList().isEmpty()){
				lastGroup = crosstab.getRowGroupsList().get(crosstab.getRowGroupsList().size()-1);
			}
			
			mc = new MRowGroupTotalCell(rg, p.getTotalHeader(), p.getName());
			
			Pair<String,String> cellKey = new Pair<String,String>(p.getName(), null);
			JRDesignCrosstabCell totalCell = (JRDesignCrosstabCell)crosstab.getCellsMap().get(cellKey);
			if (totalCell == null) {
				totalCell = new JRDesignCrosstabCell();
				totalCell.setRowTotalGroup(p.getName());
				try {
					crosstab.addCell(totalCell);
					totalCell.setHeight(20);
					if (lastGroup != null){
						Pair<String, String> key = new Pair<String,String>(lastGroup.getName(), totalCell.getColumnTotalGroup());
						JRCrosstabCell cell = crosstab.getCellsMap().get(key);
						totalCell.setWidth(cell.getWidth());
					} else {
						totalCell.setWidth(p.getWidth());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
			JRCrosstabColumnGroup[] columns = crosstab.getColumnGroups();
			if (columns != null) {
				for(JRCrosstabColumnGroup column : columns) {
					cellKey = new Pair<String,String>(p.getName(), column.getName());
					JRDesignCrosstabCell cell = (JRDesignCrosstabCell)crosstab.getCellsMap().get(cellKey);
					if (cell == null) {
						cell = new JRDesignCrosstabCell();
						cell.setRowTotalGroup(p.getName());
						cell.setColumnTotalGroup(column.getName());
						try {
							crosstab.addCell(cell);
							cell.setHeight(20);
							
							if (lastGroup != null){
								Pair<String, String> key = new Pair<String,String>(lastGroup.getName(), column.getName());
								JRCrosstabCell otherCell = crosstab.getCellsMap().get(key);
								cell.setWidth(otherCell.getWidth());
							} else {
								cell.setWidth(p.getWidth());
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			ReportFactory.createElementsForBand(mc, p.getTotalHeader().getChildren());
		}
	}

	public static void createCellNodes(JRDesignCrosstab ct, MCrosstab mCrosstab) {
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
		
		if (ct.getTitleCell() == null) {
			new MTitle(mCrosstab, -1);
		} else {
			MTitleCell mc = new MTitleCell(mCrosstab, ct.getTitleCell(), -1);
			ReportFactory.createElementsForBand(mc, ct.getTitleCell().getCellContents().getChildren());
		}
		if (ct.getHeaderCell() == null) {
			new MCrosstabHeader(mCrosstab, -1);
		} else {
			MCrosstabHeaderCell mc = new MCrosstabHeaderCell(mCrosstab, ct.getHeaderCell(), -1);
			ReportFactory.createElementsForBand(mc, ct.getHeaderCell().getChildren());
		}

		for (JRCrosstabCell c : ct.getCellsList()) {
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
				MCell mc = new MGroupCell(mCrosstab, c);
				ReportFactory.createElementsForBand(mc, c.getContents().getChildren());
			}
		}
		if (ct.getWhenNoDataCell() == null) {
			new MCrosstabWhenNoData(mCrosstab, -1);
		} else {
			MCrosstabWhenNoDataCell mc = new MCrosstabWhenNoDataCell(mCrosstab, ct.getWhenNoDataCell(), -1);
			ReportFactory.createElementsForBand(mc, ct.getWhenNoDataCell().getChildren());
		}

		if (mCrosstab.getCrosstabManager() != null)
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
		for(INode node : new ArrayList<INode>(mCrosstab.getChildren())){
			if (!(node instanceof MCrosstabParameters)) deleteChildren(node);
		}
	}
	
	protected static void deleteChildren(INode currentNode){
		for(INode node : new ArrayList<INode>(currentNode.getChildren())){
			deleteChildren(node);
		}
		ANode aNode = (ANode) currentNode;
		aNode.setParent(null, -1);
		//It is important to set the value to null to remove any old property change listener
		//set by this node
		aNode.setValue(null);
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

	public IPaletteContributor getPaletteEntries() {
		PaletteContributor pc = new PaletteContributor();
		// pc.add(IPaletteContributor.KEY_COMMON_CONTAINER, MCrosstab.class);
		pc.add(MCrosstab.class);
		return pc;
	}

	public Command getCreateCommand(ANode parent, ANode child, Rectangle location, int newIndex) {
		
		//Check to avoid that dataset objects are dragged inside the crosstab
		boolean isDatasetType = (child instanceof MVariableSystem) || (child instanceof MField) || ((child instanceof MParameterSystem) &&  !(child instanceof MCrosstabParameter));
		if (isDatasetType){
			//It is a dataset object, check if the target is the crosstab
			ANode currentParent = parent;
			while(currentParent != null){
				if (currentParent instanceof MCrosstab) return UnexecutableCommand.INSTANCE;
				else currentParent = currentParent.getParent();
			}
		}
		
		if (parent instanceof MPage) {
			for (INode c : parent.getChildren()) {
				if (c instanceof MCrosstab) {
					parent = (ANode) c;
					break;
				}
			}
		}
		
		//Avoid to generate create command in the main editor
		if (parent instanceof MCrosstab && !(parent.getParent() instanceof MPage)){
			ANode ancestor = parent.getParent();
			Class<? extends ILayout> ancestorLayout = LayoutManager.getContainerLayout(ancestor);
			if (!(ancestor instanceof MCrosstab) && !(FreeLayout.class.equals(ancestorLayout))) {
				return OutlineTreeEditPartFactory.getCreateCommand(ancestor, child, location, newIndex);
			}
			return UnexecutableCommand.INSTANCE;
		}
		
		
		if (child instanceof MStyle && (child.getValue() != null && parent instanceof MCell)) {
			SetValueCommand cmd = new SetValueCommand();
			cmd.setTarget((MCell) parent);
			cmd.setPropertyId(JRDesignCellContents.PROPERTY_STYLE);
			JRStyle style = (JRStyle) child.getValue();
			cmd.setPropertyValue(style.getName());
			return cmd;
		}
		if (child instanceof MStyle && (child.getValue() != null && parent instanceof MCrosstab)) {
			SetValueCommand cmd = new SetValueCommand();
			cmd.setTarget((MCrosstab) parent);
			cmd.setPropertyId(JRDesignElement.PROPERTY_PARENT_STYLE);
			JRStyle style = (JRStyle) child.getValue();
			cmd.setPropertyValue(style.getName());
			return cmd;
		}
		if (child instanceof MField && (child.getValue() != null && parent instanceof MCell))
			return new CreateCrosstabElement4ObjectCommand(child, (MCell) parent, location, newIndex);
		if (child instanceof MParameterSystem && (child.getValue() != null && parent instanceof MCell))
			return new CreateCrosstabElement4ObjectCommand(child, (MCell) parent, location, newIndex);
		if (child instanceof MVariableSystem && (child.getValue() != null && parent instanceof MCell))
			return new CreateCrosstabElement4ObjectCommand(child, (MCell) parent, location, newIndex);

		if (child instanceof MParameter) {
			if (parent instanceof MCrosstabParameters)
				return new CreateParameterCommand((MCrosstabParameters) parent, (MParameter) child, newIndex);
		}
		if (child instanceof MMeasure) {
			if (parent instanceof MCell)
				return UnexecutableCommand.INSTANCE;
			if (parent instanceof MCrosstab)
				return new CreateMeasureCommand((MCrosstab) parent, (MMeasure) child, newIndex);
			if (parent instanceof MMeasures)
				return new CreateMeasureCommand((MMeasures) parent, (MMeasure) child, newIndex);
		}
		if (child instanceof MColumnGroup) {
			if (parent instanceof MCell || parent instanceof MColumnGroup)
				return UnexecutableCommand.INSTANCE;
			if (parent instanceof MCrosstab)
				return new CreateColumnCommand((MCrosstab) parent, (MColumnGroup) child, newIndex);
			if (parent instanceof MColumnGroups)
				return new CreateColumnCommand((MColumnGroups) parent, (MColumnGroup) child, newIndex);
		}
		if (child instanceof MRowGroup) {
			if (parent instanceof MCell || parent instanceof MRowGroup)
				return UnexecutableCommand.INSTANCE;
			if (parent instanceof MCrosstab)
				return new CreateRowCommand((MCrosstab) parent, (MRowGroup) child, newIndex);
			if (parent instanceof MRowGroups)
				return new CreateRowCommand((MRowGroups) parent, (MRowGroup) child, newIndex);
		}
		if (child instanceof MColumnCrosstabHeaderCell && ((MColumnCrosstabHeaderCell) child).getValue() == null) {
			if (parent instanceof MColumnCrosstabHeaderCell)
				return new CreateColumnCrosstabHeaderCommand((MColumnGroup) parent.getParent(), (MColumnCrosstabHeaderCell) child);
		}
		if (child instanceof MCrosstabHeaderCell) {
			if (parent instanceof MCrosstabHeader) {
				MCrosstab crosstab = ((MCrosstabHeader) parent).getCrosstab();
				if (crosstab != null)
					return new CreateCrosstabHeaderCommand(crosstab, null);
			}
		}
		if (child instanceof MCrosstabWhenNoDataCell) {
			if (parent instanceof MCrosstabWhenNoData) {
				MCrosstab crosstab = ((MCrosstabWhenNoData) parent).getCrosstab();
				if (crosstab != null)
					return new CreateCrosstabWhenNoDataCommand(crosstab, null);
			}
		}
		if (child instanceof MTitleCell) {
			if (parent instanceof MTitle) {
				MCrosstab crosstab = ((MTitle) parent).getCrosstab();
				if (crosstab != null)
					return new CreateTitleCellCommand(crosstab, null);
			}
		}
		//If it is a custom tool require the command to the toolmanger
		if (child instanceof MCompositeElement){
			return CompositeElementManager.INSTANCE.getCommand(parent, (MCompositeElement)child, location, newIndex);
		}  
		if (child instanceof MImage && parent instanceof MCell)
			return new CreateImageCommand((MCell)parent, (MImage)child, location, newIndex);
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new CreateElementCommand((MCell) parent, (MGraphicElement) child, location, newIndex);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new CreateElementGroupCommand((MCell) parent, (MElementGroup) child, newIndex);
		if (child instanceof MCrosstab) {
			if (parent instanceof MElementGroup)
				return new CreateCrosstabCommand((MElementGroup) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MBand)
				return new CreateCrosstabCommand((MBand) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MFrame)
				return new CreateCrosstabCommand((MFrame) parent, (MGraphicElement) child, location, newIndex);
			if (parent instanceof MReport)
				return new CreateCrosstabCommand(parent, (MGraphicElement) child, location, newIndex);

			if (parent instanceof IGroupElement && parent instanceof IGraphicElementContainer) {
				return new CreateCrosstabCommand(parent, (MGraphicElement) child, location, newIndex);
			}
		}
		if (parent instanceof MCrosstab && child instanceof MGraphicElement) {
			if (location == null)
				return null;
			MCrosstab mt = (MCrosstab) parent;
			CrosstabCell ccell = mt.getCrosstabManager().getCell(new Point(location.x, location.y));
			if (ccell == null)
				return null;
			JRDesignCellContents cell = ccell.cell;
			if (cell != null) {
				Rectangle r = mt.getCrosstabManager().getCellBounds(new CrosstabCell(cell));
				location = location.setLocation(location.x - r.x, location.y - r.y);

				if (cell != null) {
					MCell mcell = (MCell) ModelUtils.getNode(cell, mt);
					return new CreateElementCommand(mcell, (MGraphicElement) child, location, newIndex);
				}
			}
		}
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		if (child instanceof MCrosstab)
			return new DeleteCrosstabCommand((MCrosstab)child);
		if (parent instanceof MPage)
			parent = child.getParent();
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
		if (child instanceof MColumnCrosstabHeaderCell) {
			if (parent instanceof MColumnGroup && ((MColumnCrosstabHeaderCell) child).getValue() != null)
				return new DeleteColumnCrosstabHeaderCommand((MColumnGroup) parent, (MColumnCrosstabHeaderCell) child);
			return null;
		}
		if (child instanceof MCrosstabHeaderCell && ((MCrosstabHeaderCell) child).getValue() != null) {
			if (parent instanceof MCrosstab)
				return new DeleteCrosstabHeaderCommand((MCrosstab) parent, (MCrosstabHeaderCell) child);
		}
		if (child instanceof MCrosstabWhenNoDataCell && ((MCrosstabWhenNoDataCell) child).getValue() != null) {
			if (parent instanceof MCrosstab)
				return new DeleteCrosstabWhenNoDataCommand((MCrosstab) parent, (MCrosstabWhenNoDataCell) child);
		}
		if (child instanceof MTitleCell && ((MTitleCell) child).getValue() != null) {
			if (parent instanceof MCrosstab)
				return new DeleteTitleCommand((MCrosstab) parent, (MTitleCell) child);
		}
		if (child instanceof MCell) {
			if (parent instanceof MColumnGroup) {
				MColumnGroup colgroup = (MColumnGroup) parent;
				return new DeleteColumnGroupCommand(colgroup.getMCrosstab(), colgroup);
			}
			if (parent instanceof MRowGroup) {
				MRowGroup rowgroup = (MRowGroup) parent;
				return new DeleteRowGroupCommand(rowgroup.getMCrosstab(), rowgroup);
			}
		}

		if (child instanceof MGraphicElement && parent instanceof MCell && child.getValue() != null)
			return new DeleteElementCommand((MCell) parent, (MGraphicElement) child);
		if (child instanceof MElementGroup && parent instanceof MCell && child.getValue() != null)
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
		lst.add(new EditCrosstabStyleAction(part));
		lst.add(new RemoveCrosstabStylesAction(part));
		return lst;
	}

	public List<String> getActionsID() {
		List<String> lst = new ArrayList<String>();
		lst.add(CreateMeasureAction.ID);
		lst.add(CreateColumnGroupAction.ID);
		lst.add(CreateRowGroupAction.ID);
		lst.add(CreateCrosstabHeaderAction.ID);
		lst.add(CreateColumnCrosstabHeaderAction.ID);
		lst.add(CreateCrosstabWhenNoDataAction.ID);
		lst.add(CreateCrosstabTitleAction.ID);
		lst.add(CreateCrosstabParameterAction.ID);
		lst.add(EditCrosstabStyleAction.ID);
		lst.add(RemoveCrosstabStylesAction.ID);
		return lst;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MCrosstab)
			return new CrosstabFigure((MCrosstab) node);
		if (node instanceof MCrosstabHeader || node instanceof MCrosstabWhenNoData || node instanceof MTitle)
			return new EmptyCellFigure();
		if (node instanceof MCrosstabWhenNoDataCell)
			return new WhenNoDataCellFigure();
		if (node instanceof MTitleCell)
			return new CellFigure();
		if (node instanceof MColumnCrosstabHeaderCell)
			return new CellFigure();
		if (node instanceof MCell)
			return new CellFigure();
		return null;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof MRoot) {
			ANode n = ModelUtils.getFirstChild((MRoot) model);
			if (n != null && n instanceof MPage) {
				for (INode child : n.getChildren()) {
					if (child instanceof MCrosstab)
						return new CrosstabPageEditPart();
				}
			}
		}
		if (model instanceof MCrosstab)
			return new CrosstabEditPart();
		if (model instanceof MCrosstabWhenNoDataCell)
			return new CrosstabWhenNoDataEditPart();
		if (model instanceof MTitleCell)
			return new CrosstabTitleCellEditPart();
		if (model instanceof MCell)
			return new CrosstabCellEditPart();
		if (model instanceof MCrosstabHeader)
			return new CrosstabHeaderEditPart();
		if (model instanceof MCrosstabWhenNoData)
			return new CrosstabWhenNoDataEditPart();
		if (model instanceof MTitle)
			return new CrosstabTitleEditPart();
		return null;
	}
	
	@Override
	public EditPart createTreeEditPart(EditPart context, Object model) {
		if (model instanceof MCrosstab)
			return new OpenableContainerTreeEditPart();
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		if (child instanceof MGraphicElement && parent instanceof MCell)
			return new OrphanElementCommand((MCell) parent, (MGraphicElement) child);
		if (child instanceof MElementGroup && parent instanceof MCell)
			return new OrphanElementGroupCommand((MCell) parent, (MElementGroup) child);
		return null;
	}

	public AbstractVisualEditor getEditor(Object node, JasperReportsConfiguration jrContext) {
		if (node instanceof JRDesignCrosstab)
			return new CrosstabEditor(jrContext);
		return null;
	}

	public ExpressionContext getElementExpressionContext(Object jrObject) {
		// FIXME - Implement this method.
		return null;
	}

	@Override
	public Command getStretchToContent(ANode node) {
		if (node instanceof MCell) {
			MCell model = (MCell) node;
			if (model.getMCrosstab() == null)
				return null;
			Dimension d = model.getMCrosstab().getCrosstabManager().getCellPackSize(new CrosstabCell(model.getValue()));
			if (d != null && d.height > 0 && d.width > 0) {
				JSSCompoundCommand c = new JSSCompoundCommand("Resize to container", model);

				SetValueCommand cmd = new SetValueCommand();

				cmd.setTarget(model);
				cmd.setPropertyId(DesignCell.PROPERTY_HEIGHT);
				cmd.setPropertyValue(d.height);
				c.add(cmd);

				cmd = new SetValueCommand();
				cmd.setTarget(model);
				cmd.setPropertyId(StandardColumn.PROPERTY_WIDTH);
				cmd.setPropertyValue(d.width);
				c.add(cmd);

				return c;
			}
		}
		return null;
	}

	public static CrosstabComponentFactory INST() {
		if (inst == null)
			inst = new CrosstabComponentFactory();
		return inst;
	}
	
	@Override
	public List<Class<?>> getKnownClasses() {
		return knownClasses;
	}

}
