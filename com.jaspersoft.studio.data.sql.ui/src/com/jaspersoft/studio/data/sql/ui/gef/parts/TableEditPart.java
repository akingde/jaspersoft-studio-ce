/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.table.EditTable;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSqlTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTable;
import com.jaspersoft.studio.data.sql.model.query.from.MFromTableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoin;
import com.jaspersoft.studio.data.sql.model.query.from.TableJoinDetail;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectExpression;
import com.jaspersoft.studio.data.sql.model.query.subquery.MQueryTable;
import com.jaspersoft.studio.data.sql.prefs.SQLEditorPreferencesPage;
import com.jaspersoft.studio.data.sql.text2model.ConvertUtil;
import com.jaspersoft.studio.data.sql.ui.gef.SQLQueryDiagram;
import com.jaspersoft.studio.data.sql.ui.gef.anchors.BottomAnchor;
import com.jaspersoft.studio.data.sql.ui.gef.anchors.TopAnchor;
import com.jaspersoft.studio.data.sql.ui.gef.command.DeleteCommand;
import com.jaspersoft.studio.data.sql.ui.gef.figures.SqlTableFigure;
import com.jaspersoft.studio.data.sql.ui.gef.policy.TableLayoutEditPolicy;
import com.jaspersoft.studio.data.sql.ui.gef.policy.TableNodeEditPolicy;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MDummy;
import com.jaspersoft.studio.model.util.ModelVisitor;

public class TableEditPart extends AbstractGraphicalEditPart {
	private Map<String, MSelectColumn> set = new HashMap<>();
	private SQLQueryDesigner designer;

	public boolean isAllstar() {
		return isAllstar(Util.getKeyword(getModel(), MSelect.class));
	}

	public boolean isAllstar(MSelect msel) {
		boolean allstar = false;
		if (msel != null) {
			for (INode n : msel.getChildren())
				if (n instanceof MSelectExpression && n.getValue().equals("*")) {
					allstar = true;
					break;
				}
		}
		return allstar;
	}

	@Override
	protected IFigure createFigure() {
		return new SqlTableFigure("") {
			@Override
			public IFigure getToolTip() {
				IFigure t = super.getToolTip();
				if (t instanceof Label) {
					Label l = (Label) t;
					l.setText(getModel().getDisplayText());
					// Rectangle b = getBounds().getCopy();
					// l.setText(l.getText() + "\n" + b);
					// this.translateToAbsolute(b);
					// l.setText(l.getText() + "\n" + b);
					// this.translateToRelative(b);
					// l.setText(l.getText() + "\n" + b);
					// l.setText(l.getText() + "\nX"
					// + getModel().getPropertyValue(MFromTable.PROP_X));
					// l.setText(l.getText() + "\nY"
					// + getModel().getPropertyValue(MFromTable.PROP_Y));
				}
				return t;
			}
		};
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
	public FromEditPart getParent() {
		return (FromEditPart) super.getParent();
	}

	@Override
	protected void refreshVisuals() {
		SqlTableFigure f = getFigure();
		MFromTable fromTable = getModel();
		refreshModel();
		f.setToolTip(new Label(fromTable.getDisplayText()));
		String sm = (String) fromTable.getPropertyValue(MFromTable.SHOW_MODE_PROPERTY);
		if (sm == null)
			;
		else if (sm.equals("short"))
			f.setLabelIcon(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/null.png"));
		else if (sm.equals("keys"))
			f.setLabelIcon(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/null.png"));
		else
			f.setLabelIcon(null);

		FromEditPart parent = getParent();
		Point p = parent.readPoint(fromTable);
		if (p == null)
			return;
		Point pmin = parent.getMinPoint();
		if (pmin == null)
			return;
		Insets in = FromEditPart.INSETS;
		p.translate(pmin.getNegated().getTranslated(-in.left, -in.top));
		parent.setLayoutConstraint(this, f, new Rectangle(p.x, p.y, -1, -1));
	}

	public void refreshModel() {
		SqlTableFigure f = getFigure();
		MFromTable fromTable = getModel();
		MSqlTable table = fromTable.getValue();
		String tblName = ConvertUtil.cleanDbNameFull(table.getValue());
		if (fromTable.getAlias() != null)
			tblName += fromTable.getAliasKeyString() + fromTable.getAlias();

		f.setName(tblName);

		MSelect msel = Util.getKeyword(fromTable, MSelect.class);
		if (msel != null) {
			set.clear();
			if (!isAllstar(msel))
				for (INode n : msel.getChildren()) {
					if (n instanceof MSelectColumn) {
						MSelectColumn msc = (MSelectColumn) n;
						if (((MSelectColumn) n).getMFromTable().equals(fromTable))
							set.put(msc.getValue().getValue(), msc);
					}
				}
		}
	}

	@Override
	protected List<?> getModelChildren() {
		MFromTable m = getModel();
		String sm = (String) m.getPropertyValue(MFromTable.SHOW_MODE_PROPERTY);

		MSqlTable tbl = m.getValue();
		List<INode> lst = new ArrayList<>();
		if (sm != null && sm.equals("short"))
			return lst;
		for (INode n : tbl.getChildren()) {
			if (n instanceof MDummy)
				continue;
			if (n instanceof MSQLColumn) {
				if (sm != null && sm.equals("keys") && ((MSQLColumn) n).getPrimaryKey() == null
						&& ((MSQLColumn) n).getForeignKeys() == null)
					continue;

				lst.add(n);
			}
		}
		return lst;
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
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {
			@Override
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				DeleteCommand cmd = new DeleteCommand(getModel());
				cmd.setDropSubquery(getDesigner().getjConfig().getProperty(SQLEditorPreferencesPage.P_DELSUBQUERY,
						SQLEditorPreferencesPage.ASK));
				return cmd;
			}
		});

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

	protected boolean isSubQuery(TableJoin tj) {
		return tj.getFromTable().getValue() instanceof MQueryTable
				|| tj.getJoinTable().getValue() instanceof MQueryTable;
	}

	@Override
	protected List<?> getModelSourceConnections() {
		String dtype = getDesigner().getjConfig().getProperty(SQLEditorPreferencesPage.P_DIAGRAM_TYPE);
		if (dtype != null && dtype.equals(SQLEditorPreferencesPage.COARSE)) {
			if (getModel().getTableJoins() != null && !getModel().getTableJoins().isEmpty()) {
				List<TableJoin> joins = new ArrayList<>();
				for (TableJoin tj : getModel().getTableJoins()) {
					if (isSubQuery(tj))
						continue;
					joins.add(tj);
				}
				return joins;
			}
		} else {
			final List<TableJoinDetail> tjs = new ArrayList<>();
			final MFromTable m = getModel();
			List<TableJoinDetail> joins = m.getTableJoinDetails();
			if (joins != null)
				for (TableJoinDetail tjd : joins)
					checkIsConnection(tjs, m, tjd, tjd.getSrcTable());
			if (!tjs.isEmpty()) {
				return tjs;
			}
		}

		return super.getModelSourceConnections();
	}

	protected void checkIsConnection(final List<TableJoinDetail> tjs, final MFromTable m, final TableJoinDetail tjd,
			MFromTable mft) {
		if (mft.getValue() instanceof MQueryTable) {
			new ModelVisitor<Object>(mft) {

				@Override
				public boolean visit(INode n) {
					if (n instanceof MFromTable && n.getValue() instanceof MQueryTable)
						visit(n);
					else if (n instanceof MFromTable && n.equals(m)) {
						tjs.add(tjd);
						stop();
					}
					return true;
				}
			};
		} else if (mft.equals(m) || mft.getValue().equals(m.getValue()))
			tjs.add(tjd);
	}

	@Override
	protected List<?> getModelTargetConnections() {
		String dtype = getDesigner().getjConfig().getProperty(SQLEditorPreferencesPage.P_DIAGRAM_TYPE);
		if (dtype != null && dtype.equals(SQLEditorPreferencesPage.COARSE)) {
			if (getModel() instanceof MFromTableJoin) {
				List<TableJoin> joins = new ArrayList<>();
				TableJoin tj = ((MFromTableJoin) getModel()).getTableJoin();
				if (!isSubQuery(tj))
					joins.add(tj);
				return joins;
			}
		} else {
			List<TableJoinDetail> tjs = new ArrayList<>();
			MFromTable m = getModel();
			List<TableJoinDetail> joins = m.getTableJoinDetails();
			if (joins != null)
				for (TableJoinDetail tjd : joins) {
					checkIsConnection(tjs, m, tjd, tjd.getDestTable());
				}
			if (!tjs.isEmpty()) {
				return tjs;
			}
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
