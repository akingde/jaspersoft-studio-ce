/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.xls;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.action.xls.XLSAction;
import com.jaspersoft.studio.editor.action.xls.XLSActionList;
import com.jaspersoft.studio.editor.action.xls.XLSPathDataAction;
import com.jaspersoft.studio.editor.gef.decorator.chainable.ChainableDecorator;
import com.jaspersoft.studio.editor.gef.decorator.chainable.ChainableElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.java2d.J2DGraphics;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicalPropertiesHandler;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.SelectionHelper;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsAbstractMetadataExporter;

/**
 * Define the action related to the XLS export, it extends a TextElementDecorator to print the textual tag on the
 * elements. It also show a global feedback to show the possible breakpoints
 * 
 * @author Orlandin Marco
 * 
 */
public class XLSElementDecorator extends ChainableElementDecorator {
	
	/**
	 * Dashed line style, used to paint the global feedback
	 */
	 private Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{3}, 0);
	 
	 /**
	  * Solid line style, used to paint the global feedback
	  */
	 private Stroke solid = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
    
	 /**
	  * Color red, used to paint the global feedback
	  */
	 private Color red =  new Color(255, 0, 0, 128);
	 
	 /**
	  * Color blue, used to paint the global feedback
	  */
	 private Color blue =  new Color(0, 0, 255, 128);

	/**
	 * The XSL contributor for the text decoration
	 */
	private XLSDecorator decorator = new XLSDecorator();

	private List<String> actionIDs;

	/**
	 * Add or remove the XSL contributor from the text element decorator
	 */
	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		super.setupFigure(fig, editPart);
		ChainableDecorator dec = getDecorator(fig);
		dec.removeDecorator(decorator);
		if (editPart.getjConfig().getPropertyBooleanDef(ShowXLSTagsAction.ID, false))
			dec.addDecorator(decorator);
	}

	private void registerFit(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new XLSAction(part, XLSAction.FIT_ROW_ID, "true", Messages.XLSElementDecorator_fitRowAction); //$NON-NLS-1$
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part, XLSAction.FIT_COL_ID, "true", Messages.XLSElementDecorator_fitColAction); //$NON-NLS-1$
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSActionList(part, "XLS_Fit_None", new String[] { XLSAction.FIT_ROW_ID, XLSAction.FIT_COL_ID }, //$NON-NLS-1$
				new String[] { null, null }, Messages.XLSElementDecorator_nonAction);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	private void registerAutoFilter(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new XLSAction(part, XLSAction.AUTOFILTER_ID.concat(XLSDecorator.START), XLSAction.AUTOFILTER_ID,
				XLSDecorator.START, //$NON-NLS-1$ //$NON-NLS-2$
				Messages.XLSElementDecorator_startAction);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part, XLSAction.AUTOFILTER_ID.concat(XLSDecorator.END), XLSAction.AUTOFILTER_ID,
				XLSDecorator.END, Messages.XLSElementDecorator_endAction); //$NON-NLS-1$ //$NON-NLS-2$
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part,
				XLSAction.AUTOFILTER_ID.concat("none"), XLSAction.AUTOFILTER_ID, null, Messages.XLSElementDecorator_nonAction); //$NON-NLS-1$ //$NON-NLS-2$
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	private void registerBreak(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new XLSAction(part, XLSAction.BREAK_AFTER_ROW_ID, XLSAction.BREAK_AFTER_ROW_ID, "true", //$NON-NLS-1$
				Messages.XLSElementDecorator_breakAfterAction, new String[] { XLSAction.BREAK_BEFORE_ROW_ID });
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part, XLSAction.BREAK_BEFORE_ROW_ID, XLSAction.BREAK_BEFORE_ROW_ID, "true", //$NON-NLS-1$
				Messages.XLSElementDecorator_breakBeforeAction, new String[] { XLSAction.BREAK_AFTER_ROW_ID });
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSActionList(part, "XSL_Break_None", new String[] { XLSAction.BREAK_AFTER_ROW_ID, //$NON-NLS-1$
				XLSAction.BREAK_BEFORE_ROW_ID }, new String[] { null, null }, Messages.XLSElementDecorator_nonAction); //$NON-NLS-1$
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	private void registerCellProperties(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new XLSAction(part, XLSAction.CELL_HIDDEN_ID, XLSAction.CELL_HIDDEN_ID,
				"true", Messages.XLSElementDecorator_hiddenCellAction); //$NON-NLS-1$
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part, XLSAction.CELL_LOCKED_ID, XLSAction.CELL_LOCKED_ID,
				"true", Messages.XLSElementDecorator_lockCellAction); //$NON-NLS-1$

		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSActionList(part,
				"XSL_Cell_None", //$NON-NLS-1$
				new String[] { XLSAction.CELL_HIDDEN_ID, XLSAction.CELL_LOCKED_ID }, new String[] { null, null },
				Messages.XLSElementDecorator_nonAction); //$NON-NLS-1$
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	private void registerFreezeProperties(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {

		IAction action = new XLSAction(part, XLSAction.FREEZE_COL_ID.concat("Left"), XLSAction.FREEZE_COL_ID, "Left", //$NON-NLS-1$ //$NON-NLS-2$
				Messages.XLSElementDecorator_leftAction);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(
				part,
				XLSAction.FREEZE_COL_ID.concat("Right"), XLSAction.FREEZE_COL_ID, "Right", Messages.XLSElementDecorator_rightAction); //$NON-NLS-1$ //$NON-NLS-2$
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part,
				XLSAction.FREEZE_COL_ID.concat("None"), XLSAction.FREEZE_COL_ID, null, Messages.XLSElementDecorator_nonAction); //$NON-NLS-1$ //$NON-NLS-2$
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part,
				XLSAction.FREEZE_ROW_ID.concat("Top"), XLSAction.FREEZE_ROW_ID, "Top", Messages.XLSElementDecorator_topAction); //$NON-NLS-1$ //$NON-NLS-2$
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(
				part,
				XLSAction.FREEZE_ROW_ID.concat("Bottom"), XLSAction.FREEZE_ROW_ID, "Bottom", Messages.XLSElementDecorator_bottomAction); //$NON-NLS-1$ //$NON-NLS-2$
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part,
				XLSAction.FREEZE_ROW_ID.concat("None"), XLSAction.FREEZE_ROW_ID, null, Messages.XLSElementDecorator_nonAction); //$NON-NLS-1$ //$NON-NLS-2$
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	public void registerActions(ActionRegistry registry, List<String> selectionActions, IWorkbenchPart part) {
		registerFit(registry, part, selectionActions);
		registerAutoFilter(registry, part, selectionActions);
		registerBreak(registry, part, selectionActions);
		registerCellProperties(registry, part, selectionActions);
		registerFreezeProperties(registry, part, selectionActions);

		IAction action = new XLSPathDataAction(part);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			AbstractVisualEditor part) {
		gviewer.setProperty(ShowXLSTagsAction.ID, true);
		IAction action = new ShowXLSTagsAction(gviewer, part.getJrContext());
		registry.registerAction(action);
		action = new ShowXLSBreakAction(gviewer, part.getJrContext());
		registry.registerAction(action);
		registerActions(registry, selectionActions, part);
	}

	public void fillContextMenu(ActionRegistry registry, IMenuManager menu, IStructuredSelection sel) {
		MenuManager submenu = new MenuManager(Messages.XLSElementDecorator_xlsTagsMenu);
		MenuManager fitMenu = new MenuManager(Messages.XLSElementDecorator_fitMenu);
		MenuManager autoFilterMenu = new MenuManager(Messages.XLSElementDecorator_autoFilterMenu);
		MenuManager breakMenu = new MenuManager(Messages.XLSElementDecorator_breakMenu);
		MenuManager freezeMenu = new MenuManager(Messages.XLSElementDecorator_freezeMenu);
		MenuManager freezeRowMenu = new MenuManager(Messages.XLSElementDecorator_rowsMenu);
		MenuManager freezeColMenu = new MenuManager(Messages.XLSElementDecorator_columnsMenu);
		MenuManager propertiesMenu = new MenuManager(Messages.XLSElementDecorator_cellPropertiesMenu);

		submenu.add(fitMenu);
		submenu.add(autoFilterMenu);
		submenu.add(breakMenu);
		submenu.add(propertiesMenu);
		freezeMenu.add(freezeRowMenu);
		freezeMenu.add(freezeColMenu);
		submenu.add(freezeMenu);

		IAction action;
		// Adding actions for the Fit
		action = registry.getAction(XLSAction.FIT_ROW_ID);
		fitMenu.add(action);
		action = registry.getAction(XLSAction.FIT_COL_ID);
		fitMenu.add(action);
		action = registry.getAction("XLS_Fit_None"); //$NON-NLS-1$
		fitMenu.add(action);

		// Adding actions for the autofilter
		action = registry.getAction(XLSAction.AUTOFILTER_ID.concat(XLSDecorator.START)); //$NON-NLS-1$
		autoFilterMenu.add(action);
		action = registry.getAction(XLSAction.AUTOFILTER_ID.concat(XLSDecorator.END)); //$NON-NLS-1$
		autoFilterMenu.add(action);
		action = registry.getAction(XLSAction.AUTOFILTER_ID.concat("none")); //$NON-NLS-1$
		autoFilterMenu.add(action);

		// Adding actions for the break
		action = registry.getAction(XLSAction.BREAK_BEFORE_ROW_ID);
		breakMenu.add(action);
		action = registry.getAction(XLSAction.BREAK_AFTER_ROW_ID);
		breakMenu.add(action);
		action = registry.getAction("XSL_Break_None"); //$NON-NLS-1$
		breakMenu.add(action);

		// Adding actions for the cell properties
		action = registry.getAction(XLSAction.CELL_HIDDEN_ID);
		propertiesMenu.add(action);
		action = registry.getAction(XLSAction.CELL_LOCKED_ID);
		propertiesMenu.add(action);
		action = registry.getAction("XSL_Cell_None"); //$NON-NLS-1$
		propertiesMenu.add(action);

		// Adding the freeze properties
		action = registry.getAction(XLSAction.FREEZE_ROW_ID.concat("Top")); //$NON-NLS-1$
		freezeRowMenu.add(action);
		action = registry.getAction(XLSAction.FREEZE_ROW_ID.concat("Bottom")); //$NON-NLS-1$
		freezeRowMenu.add(action);
		action = registry.getAction(XLSAction.FREEZE_ROW_ID.concat("None")); //$NON-NLS-1$
		freezeRowMenu.add(action);

		action = registry.getAction(XLSAction.FREEZE_COL_ID.concat("Left")); //$NON-NLS-1$
		freezeColMenu.add(action);
		action = registry.getAction(XLSAction.FREEZE_COL_ID.concat("Right")); //$NON-NLS-1$
		freezeColMenu.add(action);
		action = registry.getAction(XLSAction.FREEZE_COL_ID.concat("None")); //$NON-NLS-1$
		freezeColMenu.add(action);

		action = registry.getAction(JRXlsAbstractMetadataExporter.PROPERTY_COLUMN_NAME);
		submenu.add(action);

		menu.add(submenu);
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		if (sel.getFirstElement() instanceof EditPart) {
			EditPart ep = (EditPart) sel.getFirstElement();
			if (!(ep.getModel() instanceof MGraphicElement))
				return;
		}
		fillContextMenu(registry, menu, sel);
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { new RetargetAction(ShowXLSTagsAction.ID, Messages.XLSElementDecorator_showXLSTagsLabel, IAction.AS_CHECK_BOX),
															new RetargetAction(ShowXLSBreakAction.ID, Messages.ShowXLSBreakAction_name, IAction.AS_CHECK_BOX) };
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowXLSTagsAction.ID));
		menuManager.add(registry.getAction(ShowXLSBreakAction.ID));
	}

	@Override
	public List<String> getActionIDs() {
		if (actionIDs == null) {
			actionIDs = new ArrayList<String>(2);
			actionIDs.add(ShowXLSTagsAction.ID);
			actionIDs.add(ShowXLSBreakAction.ID);
		}
		return actionIDs;
	}
	
	/*
	  CODE NO MORE USED, SHOW THE BREAKS ONLY ON TABLE COLUMNS
	 
	 private int isBreakingColumn(FigureEditPart tablePart, FigureEditPart otherElement, int tableWidth) {
		Rectangle elementBounds = otherElement.getFigure().getBounds();
		Rectangle tableBounds= tablePart.getFigure().getBounds();
		if (elementBounds.x > tableBounds.x && elementBounds.x < tableBounds.x + tableWidth &&
				(elementBounds.y + elementBounds.height < tableBounds.y || elementBounds.y > tableBounds.y + tableBounds.height)) {
			JRDesignComponentElement compElement = (JRDesignComponentElement)((ANode)tablePart.getModel()).getValue();
			StandardTable table = (StandardTable)compElement.getComponent();
			List<BaseColumn> columns = table.getColumns();
			int currentWidth = tableBounds.x;
			for(BaseColumn column : columns) {
				if (elementBounds.x > currentWidth && elementBounds.x < currentWidth + column.getWidth()) {
					return currentWidth;
				}
				currentWidth += column.getWidth();
			}	
		}
		
		
		return -1;
	}
	
	private int getTableWidth(FigureEditPart tablePart) {
		JRDesignComponentElement compElement = (JRDesignComponentElement)((ANode)tablePart.getModel()).getValue();
		StandardTable table = (StandardTable)compElement.getComponent();
		List<BaseColumn> columns = table.getColumns();
		int width = 0;
		for(BaseColumn column : columns) {
			width += column.getWidth();
		}
		return width;
	}

	
	public void paintGlobal2(Graphics g, IFigure figure, JasperReportsConfiguration jConfig) {
		if (jConfig.getPropertyBooleanDef(ShowXLSBreakAction.ID, false)) {
			List<FigureEditPart> tableParts = new ArrayList<>();
			List<FigureEditPart> elementParts = new ArrayList<FigureEditPart>();
			List<JRDesignElement> jrElements = ModelUtils.getAllElements(jConfig.getJasperDesign());
		for (JRDesignElement element : jrElements) {
				ANode node = SelectionHelper.getNode(element);
				if (element instanceof JRDesignComponentElement) {
					JRDesignComponentElement compElement = (JRDesignComponentElement)element;
					if (compElement.getComponent() instanceof StandardTable) {
						FigureEditPart tablePart = (FigureEditPart)SelectionHelper.getEditPart(element);
						if (tablePart != null) {
							tableParts.add(tablePart);
						}
					}
				} else if (element instanceof JRDesignElement) {
					EditPart elementPart = SelectionHelper.getEditPart(element);
					if (elementPart != null && elementPart instanceof FigureEditPart) {
						elementParts.add((FigureEditPart)elementPart);
					}
				}
			}
			if (!tableParts.isEmpty()) {
				Color oldColor = g.getBackgroundColor();
				int oldAlpha = g.getAlpha();
				g.setBackgroundColor(ColorConstants.red);
				g.setAlpha(128);
				for(FigureEditPart tablePart : tableParts) {
					int tableWidth = getTableWidth(tablePart);
					Rectangle tableBounds = tablePart.getFigure().getBounds();
					for(FigureEditPart elementPart : elementParts) {
						Rectangle elementBounds = elementPart.getFigure().getBounds();
						int breakingPoint = isBreakingColumn(tablePart, elementPart, tableWidth);
						if (breakingPoint != -1) {
							if (breakingPoint > tableBounds.width) {
								breakingPoint = tableBounds.width + tableBounds.x - 2;
							}
							if (elementBounds.y < tableBounds.y) {
								//the element is above the table
								Rectangle rectangle = new Rectangle(elementBounds.x - 3, elementBounds.y, 3, tableBounds.y - elementBounds.y);
								g.fillRectangle(rectangle);
								rectangle = new Rectangle(breakingPoint, tableBounds.y - 2, elementBounds.x - breakingPoint, 2);
								g.fillRectangle(rectangle);
							} else {
								//the element is under the table
								int elementHeight = elementBounds.y + elementBounds.height;
								int tableHeight = tableBounds.y + tableBounds.height;
								Rectangle rectangle = new Rectangle(elementBounds.x - 3, tableHeight, 3, elementHeight - tableHeight);
								g.fillRectangle(rectangle);
								rectangle = new Rectangle(breakingPoint, tableHeight, elementBounds.x - breakingPoint, 2);
								g.fillRectangle(rectangle);
							}

						}
					}
				}
				g.setBackgroundColor(oldColor);
				g.setAlpha(oldAlpha);
			}
		}	
	}*/
	
	/*private Color generateColor(int x) {
    int red = ((x + (x % 2)) * 100) % 256;
    int green = ((x + (x % 3)) * 100) % 256;
    int blue = ((x + (x % 5)) * 100) % 256;
    return ResourceManager.getColor(red, green, blue);
	}*/
	
	/**
	 * Search the page root node associated to the passed {@link JasperReportsConfiguration}
	 * 
	 * @param jConfig a not null {@link JasperReportsConfiguration}
	 * @return the node of the page, can be null if there are no band on the report or if the editor is closed
	 */
	private ANode getPageRoot(JasperReportsConfiguration jConfig) {
		JasperDesign jd = jConfig.getJasperDesign();
		for(JRBand band : jd.getAllBands()) {
			ANode node = SelectionHelper.getNode(band);
			if (node != null) {
				return node.getParent();
			}
		}
		return null;
	}
	
	/**
	 * Iterate recursively all the node of the reports and collect the graphical elements
	 * that can break a column, keeping track of their position and size 
	 * 
	 * @param currentNode the current node in the recursion
	 * @param positions the current list of the found graphical elements
	 */
	private void collectChildren(INode currentNode, List<Rectangle> positions) {
		if (currentNode != null) {
			for(INode child : currentNode.getChildren()) {
				if (child instanceof IGraphicalPropertiesHandler) {
					IGraphicalPropertiesHandler handler = (IGraphicalPropertiesHandler)child;
					Rectangle bounds = handler.getAbsoluteBounds();
					
					if (child.getValue() instanceof JRDesignComponentElement) {
						JRDesignComponentElement compElement = (JRDesignComponentElement)child.getValue();
						if (compElement.getComponent() instanceof StandardTable) {
							StandardTable table = (StandardTable)compElement.getComponent();
							int currentColumnWidth = 0;
							for(BaseColumn column : table.getColumns()) {
								positions.add(new Rectangle(bounds.x + currentColumnWidth, bounds.y, column.getWidth(), bounds.height));
								currentColumnWidth += column.getWidth();
							}
						}
					} else {
						positions.add(new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height));
						positions.add(new Rectangle(bounds.x+bounds.width, bounds.y, bounds.width, bounds.height));
					}
				}
				collectChildren(child, positions);
			}
		}
	}
	
	@Override
	public void paintGlobal(Graphics g, IFigure figure, JasperReportsConfiguration jConfig) {
		if (jConfig.getPropertyBooleanDef(ShowXLSBreakAction.ID, false)) {
			List<Rectangle> positions = new ArrayList<>();
			ANode root = getPageRoot(jConfig);
			collectChildren(root, positions);
			
			
			if (!positions.isEmpty()) {
				JasperDesign jd = jConfig.getJasperDesign();
				//search the minimum rectangle that enclose the page and all the elements
				int dxOffset = 10;
				int dyOffset = 10;
				int minX = 0;
				int minY = 0;	
				int maxX = jConfig.getJasperDesign().getPageWidth();
				int maxY = jd.getTopMargin() + jd.getBottomMargin();;
				for(JRBand band : jd.getAllBands()) {
					if (band != null) {
						maxY += band.getHeight();
					}
				}
				for(Rectangle position : positions) {
					if (position.x < minX) {
						minX = position.x;
					}
					if (position.x > maxX) {
						maxX = position.x;
					}
					if (position.y < minY) {
						minY = position.y;
					}
					if (position.y > maxY) {
						maxY = position.y;
					}
				}

				//Aggregate the node that have the same X coordinate
				positions.sort(new Comparator<Rectangle>() {
					@Override
					public int compare(Rectangle o1, Rectangle o2) {
						return o1.x - o2.x;
					}
				});
				List<List<Rectangle>> aggregatedPositions = new ArrayList<>();
				Rectangle previousValue = null;
				for(Rectangle position : positions) {
					if (previousValue == null || previousValue.x != position.x) {
						List<Rectangle> subList = new ArrayList<>();
						subList.add(position);
						aggregatedPositions.add(subList);
					} else {
						List<Rectangle> subList =  aggregatedPositions.get(aggregatedPositions.size() - 1);
						subList.add(position);
					}
					previousValue = position;
				}
				Graphics2D g2d = ((J2DGraphics) g).getGraphics2D();
				g2d.drawRect(minX + dxOffset, minY + dyOffset, maxX-minX, maxY-minY);
		        g2d.setColor(red);
				for(List<Rectangle> aggregatedPosition : aggregatedPositions) {
					//paint from top to bottom
					aggregatedPosition.sort(new Comparator<Rectangle>() {
						@Override
						public int compare(Rectangle o1, Rectangle o2) {
							return o1.y - o2.y;
						}
					});
					
					int startY = minY + dyOffset;
					int currentIndex = 0;
					for(Rectangle position : aggregatedPosition) {
						g2d.setStroke(solid);
						int endY =  startY + position.y;
						if (currentIndex - 1 >= 0) {
							Rectangle previusLine = aggregatedPosition.get(currentIndex-1);
							endY -= (previusLine.y + previusLine.height);
						}
						g2d.drawLine(position.x + dxOffset, startY, position.x + dxOffset, endY);
						startY = endY;
						endY += position.height;
						g2d.setStroke(dashed);
						g2d.drawLine(position.x + dxOffset, startY, position.x + dxOffset, endY);
						startY = endY;
						currentIndex ++;
						if (currentIndex == aggregatedPosition.size()) {
							g2d.setStroke(solid);
							g2d.drawLine(position.x + dxOffset, startY, position.x + dxOffset, maxY + dyOffset);
						}
					}
					if (g2d.getColor() == red) {
						g2d.setColor(blue);
					} else {
						g2d.setColor(red);
					}
				}
			}

		}	
	}
}
