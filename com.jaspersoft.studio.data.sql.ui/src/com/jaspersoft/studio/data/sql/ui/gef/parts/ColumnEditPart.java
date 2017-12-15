/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.parts;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.ActionFactory;
import com.jaspersoft.studio.data.sql.action.select.CreateColumn;
import com.jaspersoft.studio.data.sql.action.select.DeleteColumn;
import com.jaspersoft.studio.data.sql.messages.Messages;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.data.sql.ui.gef.SQLQueryDiagram;
import com.jaspersoft.studio.data.sql.ui.gef.command.CreateColumnCommand;
import com.jaspersoft.studio.data.sql.ui.gef.command.DeleteObjectCommand;
import com.jaspersoft.studio.data.sql.ui.gef.figures.ColumnFigure;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class ColumnEditPart extends AbstractGraphicalEditPart {

	private String colname;
	private MSelectColumn mSelCol;
	private ActionFactory afactory;
	private MSelect mselect;

	@Override
	protected IFigure createFigure() {
		colname = getModel().getValue();
		Image image = null;
		ImageDescriptor imgd = getModel().getImagePath();
		if (imgd != null)
			image = JasperReportsPlugin.getDefault().getImage(imgd);
		final SQLQueryDesigner designer = (SQLQueryDesigner) getViewer()
				.getProperty(SQLQueryDiagram.SQLQUERYDIAGRAM);
		afactory = designer.getOutline().getAfactory();
		final MFromTable tblModel = ColumnEditPart.this.getParent().getModel();
		mselect = Util.getKeyword(tblModel, MSelect.class);
		ColumnFigure cbfig = new ColumnFigure(colname, image) {
			@Override
			protected void handleSelectionChanged() {
				super.handleSelectionChanged();
				if (isRefreshing)
					return;
				CompoundCommand c = new CompoundCommand();
				CreateColumn ct = afactory.getAction(CreateColumn.class);
				if (isSelected()) {
					if (ct.calculateEnabled(new Object[] { mselect })) {
						MSelect mSelect = Util.getKeyword(tblModel,
								MSelect.class);
						c.add(new CreateColumnCommand(ColumnEditPart.this
								.getModel(), mSelect, -1, tblModel));
						// ct.run(ColumnEditPart.this.getModel(), tblModel);
					}
				} else {
					if (ColumnEditPart.this.getParent().isAllstar(mselect)) {
						// get all columns from tables
						// remove *
						boolean tmp = isRefreshing;
						isRefreshing = true;
						MSelectExpression todel = null;
						for (INode n : mselect.getChildren()) {
							if (n instanceof MSelectExpression
									&& n.getValue().equals("*")) { //$NON-NLS-1$
								todel = (MSelectExpression) n;
								break;
							}
						}
						if (todel != null) {
							List<ANode> tl = new ArrayList<ANode>();
							tl.add(todel);
							c.add(new DeleteObjectCommand(tl));
							// mselect.removeChild(todel);
						}
						MFromTable mFromTable = ColumnEditPart.this.getParent()
								.getModel();
						for (INode n : mFromTable.getValue().getChildren()) {
							if (n instanceof MSQLColumn) {
								boolean exists = false;
								for (INode sc : mselect.getChildren()) {
									if (sc instanceof MSelectColumn
											&& ((MSelectColumn) sc).getValue()
													.equals(n)) {
										exists = true;
										break;
									}
								}
								if (exists)
									continue;
								c.add(new CreateColumnCommand((MSQLColumn) n,
										mselect, -1, mFromTable));
								// ct.run((MSQLColumn) n, mFromTable, mselect,
								// -1);
							}
						}
						// setSelected(false);
						// designer.refreshQueryText();
						ColumnEditPart.this.getParent().refreshModel();
						ColumnEditPart.this.refreshVisuals();
						isRefreshing = tmp;
						// return;
					}
					// DeleteColumn dc = afactory.getAction(DeleteColumn.class);
					// if (dc.calculateEnabled(new Object[] { mSelCol }))
					// dc.run();
					List<ANode> todel = new ArrayList<ANode>();
					todel.add(mSelCol);
					if (!DeleteColumn.showConfirmation(designer,
							Messages.ColumnEditPart_1, todel))
						return;

					if (mSelCol == null) {
						Command cmddel = null;
						for (Command cmd : (List<Command>) c.getCommands()) {
							if (cmd instanceof CreateColumnCommand) {
								if (((CreateColumnCommand) cmd).getColumn()
										.getValue().equals(colname)) {
									cmddel = cmd;
									break;
								}
							}
						}
						c.getCommands().remove(cmddel);
					} else
						c.add(new DeleteObjectCommand(todel));
				}
				designer.getDiagram().getViewer().getEditDomain()
						.getCommandStack().execute(c);
				designer.getDiagram().scheduleRefresh(true, false);
			}
		};
		cbfig.setToolTip(new Label(getModel().getToolTip()));
		return cbfig;
	}

	@Override
	public TableEditPart getParent() {
		return (TableEditPart) super.getParent();
	}

	@Override
	public ColumnFigure getFigure() {
		return (ColumnFigure) super.getFigure();
	}

	private boolean isRefreshing = false;

	@Override
	protected void refreshVisuals() {
		isRefreshing = true;
		if (getParent().isAllstar())
			getFigure().setSelected(true);
		else {
			mSelCol = getParent().getColumnMap().get(colname);
			getFigure().setSelected(mSelCol != null);
		}
		isRefreshing = false;
	}

	public MSelectColumn getmSelectColumn() {
		return mSelCol;
	}

	@Override
	public MSQLColumn getModel() {
		return (MSQLColumn) super.getModel();
	}

	@Override
	protected void createEditPolicies() {
		// installEditPolicy(EditPolicy.LAYOUT_ROLE, new
		// ColumnLayoutEditPolicy());
		// installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new
		// TableNodeEditPolicy());
	}

}
