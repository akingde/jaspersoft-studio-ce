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
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.data.sql.Util;
import com.jaspersoft.studio.data.sql.action.ActionFactory;
import com.jaspersoft.studio.data.sql.action.select.CreateColumn;
import com.jaspersoft.studio.data.sql.action.select.DeleteColumn;
import com.jaspersoft.studio.data.sql.model.metadata.MSQLColumn;
import com.jaspersoft.studio.data.sql.model.query.select.MSelect;
import com.jaspersoft.studio.data.sql.model.query.select.MSelectColumn;
import com.jaspersoft.studio.data.sql.ui.gef.SQLQueryDiagram;
import com.jaspersoft.studio.data.sql.ui.gef.figures.ColumnFigure;

public class ColumnEditPart extends AbstractGraphicalEditPart {

	private String colname;
	private MSelectColumn mSelCol;
	private ActionFactory afactory;
	private MSelect mselect;

	@Override
	protected IFigure createFigure() {
		colname = getModel().getValue();
		SQLQueryDesigner designer = (SQLQueryDesigner) getViewer().getProperty(SQLQueryDiagram.SQLQUERYDIAGRAM);
		afactory = designer.getOutline().getAfactory();
		mselect = Util.getKeyword(ColumnEditPart.this.getParent().getModel(), MSelect.class);
		ColumnFigure cbfig = new ColumnFigure(colname) {
			@Override
			protected void handleSelectionChanged() {
				super.handleSelectionChanged();
				if (isRefreshing)
					return;
				if (isSelected()) {
					CreateColumn ct = afactory.getAction(CreateColumn.class);
					if (ct.calculateEnabled(new Object[] { mselect })) {
						List<MSQLColumn> cols = new ArrayList<MSQLColumn>();
						cols.add(ColumnEditPart.this.getModel());
						ct.run(cols);
					}
				} else {
					DeleteColumn dc = afactory.getAction(DeleteColumn.class);
					if (dc.calculateEnabled(new Object[] { mSelCol }))
						dc.run();
				}
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
		mSelCol = getParent().getColumnMap().get(colname);
		getFigure().setSelected(mSelCol != null);
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
		// installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new
		// TableNodeEditPolicy());
	}

}
