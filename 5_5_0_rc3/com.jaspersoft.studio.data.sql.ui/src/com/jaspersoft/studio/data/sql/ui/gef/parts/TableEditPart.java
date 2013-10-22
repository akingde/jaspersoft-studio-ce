/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.table.EditTable;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.data.sql.ui.gef.SQLQueryDiagram;
import com.jaspersoft.studio.data.sql.ui.gef.anchors.BottomAnchor;
import com.jaspersoft.studio.data.sql.ui.gef.anchors.TopAnchor;
import com.jaspersoft.studio.data.sql.ui.gef.figures.SqlTableFigure;
import com.jaspersoft.studio.data.sql.ui.gef.policy.TableLayoutEditPolicy;
import com.jaspersoft.studio.data.sql.ui.gef.policy.TableNodeEditPolicy;
import com.jaspersoft.studio.model.INode;

public class TableEditPart extends AbstractGraphicalEditPart {
	private Map<String, MSelectColumn> set = new HashMap<String, MSelectColumn>();
	private SQLQueryDesigner designer;
	private boolean allstar;

	public boolean isAllstar() {
		return allstar;
	}

	@Override
	protected IFigure createFigure() {
		return new SqlTableFigure("");
	}

	public Map<String, MSelectColumn> getColumnMap() {
		return set;
	}

	@Override
	public MFromTable getModel() {
		return (MFromTable) super.getModel();
	}

	@Override
	public SqlTableFigure getFigure() {
		return (SqlTableFigure) super.getFigure();
	}

	@Override
	protected void refreshVisuals() {
		SqlTableFigure f = getFigure();

		MFromTable fromTable = getModel();
		MSqlTable table = fromTable.getValue();
		String tblName = table.getValue();
		if (fromTable.getAlias() != null)
			tblName += fromTable.getAliasKeyString() + fromTable.getAlias();

		f.setName(tblName);

		MSelect msel = Util.getKeyword(fromTable, MSelect.class);
		set.clear();
		allstar = false;
		for (INode n : msel.getChildren()) {
			if (n instanceof MSelectExpression && n.getValue().equals("*")) {
				allstar = true;
				break;
			}
		}
		if (!allstar)
			for (INode n : msel.getChildren()) {
				if (allstar || (n instanceof MSelectColumn && ((MSelectColumn) n).getMFromTable().equals(fromTable))) {
					MSelectColumn msc = (MSelectColumn) n;
					set.put(msc.getValue().getValue(), msc);
				}
			}
		AbstractGraphicalEditPart parent = (AbstractGraphicalEditPart) getParent();
		Point location = f.getLocation();
		Rectangle constraint = new Rectangle(location.x, location.y, -1, -1);
		parent.setLayoutConstraint(this, f, constraint);

		f.setToolTip(new Label(fromTable.getToolTip()));
	}

	@Override
	protected List getModelChildren() {
		return getModel().getValue().getChildren();
	}

	public SQLQueryDesigner getDesigner() {
		if (designer == null)
			designer = (SQLQueryDesigner) getViewer().getProperty(SQLQueryDiagram.SQLQUERYDIAGRAM);
		return designer;
	}

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			EditTable ct = getDesigner().getOutline().getAfactory().getAction(EditTable.class);
			if (ct.calculateEnabled(new Object[] { getModel() }))
				ct.run();
		}
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new TableNodeEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new TableLayoutEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new SelectionEditPolicy() {

			@Override
			protected void hideSelection() {
				getFigure().hideSelectedBorder();
			}

			@Override
			protected void showSelection() {
				getFigure().showSelectedBorder();
			}
		});
	}

	@Override
	protected List getModelSourceConnections() {
		if (getModel().getTableJoins() != null && !getModel().getTableJoins().isEmpty()) {
			List<TableJoin> joins = new ArrayList<TableJoin>();
			for (TableJoin tj : getModel().getTableJoins()) {
				if (isSubQuery(tj))
					continue;
				joins.add(tj);
			}
			return joins;
		}
		return super.getModelSourceConnections();
	}

	protected boolean isSubQuery(TableJoin tj) {
		return tj.getFromTable().getValue() instanceof MQueryTable || tj.getJoinTable().getValue() instanceof MQueryTable;
	}

	@Override
	protected List getModelTargetConnections() {
		if (getModel() instanceof MFromTableJoin) {
			List<TableJoin> joins = new ArrayList<TableJoin>();
			TableJoin tj = ((MFromTableJoin) getModel()).getTableJoin();
			if (!isSubQuery(tj))
				joins.add(tj);
			return joins;
		}
		return super.getModelTargetConnections();
	}

	/**
	 * @see NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new TopAnchor(getFigure());
	}

	/**
	 * @see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new TopAnchor(getFigure());
	}

	/**
	 * @see NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new BottomAnchor(getFigure());
	}

	/**
	 * @see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new BottomAnchor(getFigure());
	}
}
